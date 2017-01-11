/**
 * 
 */
package no.systema.skat.nctsexport.controller.ajax;

import java.util.*;


import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Required;
import org.springframework.stereotype.Controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;

import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import no.systema.main.model.jsonjackson.general.JsonCurrencyRateContainer;
import no.systema.main.model.jsonjackson.general.JsonCurrencyRateRecord;
import no.systema.main.service.UrlCgiProxyService;
import no.systema.main.service.UrlCgiProxyServiceImpl;
import no.systema.main.service.general.CurrencyRateService;
import no.systema.main.util.JavaReflectionUtil;

//SKAT
import no.systema.skat.url.store.SkatUrlDataStore;
import no.systema.skat.util.SkatConstants;
import no.systema.skat.model.jsonjackson.codes.JsonSkatTaricVarukodContainer;
import no.systema.skat.model.jsonjackson.codes.JsonSkatTaricVarukodRecord;
import no.systema.skat.model.jsonjackson.codes.JsonSkatNctsCodeContainer;
import no.systema.skat.model.jsonjackson.codes.JsonSkatNctsCodeRecord;
import no.systema.skat.model.jsonjackson.customer.JsonSkatCustomerRecord;
import no.systema.skat.service.SkatTaricVarukodService;
import no.systema.skat.service.html.dropdown.SkatDropDownListPopulationService;

import no.systema.skat.nctsexport.url.store.SkatNctsExportUrlDataStore;
import no.systema.skat.nctsexport.model.jsonjackson.topic.JsonSkatNctsExportSpecificTopicContainer;
import no.systema.skat.nctsexport.model.jsonjackson.topic.JsonSkatNctsExportSpecificTopicRecord;
import no.systema.skat.nctsexport.model.jsonjackson.topic.items.JsonSkatNctsExportSpecificTopicItemContainer;
import no.systema.skat.nctsexport.model.jsonjackson.topic.items.JsonSkatNctsExportSpecificTopicItemRecord;
import no.systema.skat.nctsexport.service.SkatNctsExportSpecificTopicService;
import no.systema.skat.nctsexport.service.SkatNctsExportSpecificTopicItemService;

import no.systema.skat.nctsexport.model.jsonjackson.topic.validation.JsonSkatNctsExportSpecificTopicGuaranteeValidatorContainer;
import no.systema.skat.nctsexport.model.jsonjackson.topic.items.JsonSkatNctsExportSpecificTopicItemSecurityContainer;
import no.systema.skat.nctsexport.model.jsonjackson.topic.items.JsonSkatNctsExportSpecificTopicItemSecurityRecord;

import no.systema.main.util.NumberFormatterLocaleAware;

/**
 * This Ajax handler is the class handling ajax request in SkatNctsExport. 
 * It will usually be called from within a jQuery function or other javascript alike... 
 * 
 * @author oscardelatorre
 * @date Apr 17, 2014
 * 
 */

@Controller

public class SkatNctsExportAjaxHandlerController {
	private static final Logger logger = Logger.getLogger(SkatNctsExportAjaxHandlerController.class.getName());
	private NumberFormatterLocaleAware numberFormatter = new NumberFormatterLocaleAware();
	/**
	 * 
	 * @param applicationUser
	 * @param elementValue
	 * @param avd
	 * @param opd
	 * @return
	 */
	@RequestMapping(value = "getSpecificTopicItemChosenFromGuiElement_SkatNctsExport.do", method = RequestMethod.GET)
	public @ResponseBody Set<JsonSkatNctsExportSpecificTopicItemRecord> getSpecificTopicItemChosenFromHtmlList
	  						(@RequestParam String applicationUser, @RequestParam String elementValue, 
	  						 @RequestParam String avd, @RequestParam String opd ) {
		 logger.info("Inside: getSpecificTopicItemChosenFromGuiElement_SkatNctsExport()");
		 Set result = new HashSet();
		 //prepare the access CGI with RPG back-end
		 String BASE_URL = SkatNctsExportUrlDataStore.NCTS_EXPORT_BASE_FETCH_SPECIFIC_TOPIC_ITEM_URL;
		 String[] fields = elementValue.split("_");
		 String lineNr = null;
		 String lineCounter = null;
		 if(fields!=null && fields.length==3){
			 logger.info("FIELD: " + fields[0] + " " + fields[1] + " " + fields[2]);
			 lineCounter = fields[1];
			 lineNr = fields[2];
			 logger.info(applicationUser + "-" + elementValue + "-" + avd + "-" + opd + "- linenr:" + lineNr);
			 String urlRequestParamsKeys = this.getRequestUrlKeyParametersForItem(applicationUser, avd, opd, lineNr);
			 logger.info("URL: " + BASE_URL);
			 logger.info("PARAMS: " + urlRequestParamsKeys);
			 logger.info(Calendar.getInstance().getTime() +  " CGI-start timestamp");
			 String jsonPayload = this.urlCgiProxyService.getJsonContent(BASE_URL, urlRequestParamsKeys);
			 logger.info(Calendar.getInstance().getTime() +  " CGI-end timestamp");		
			 if(jsonPayload!=null){
				 //we must replace wrong name in order to use the same JSON item record. The RPG name "oneline" should be replaced (at the back end)
				 //in the future by orderList... We do that here and now
				 jsonPayload = jsonPayload.replaceFirst("oneline", "orderList");
				 logger.info(jsonPayload);
				 JsonSkatNctsExportSpecificTopicItemContainer container = this.skatNctsExportSpecificTopicItemService.getNctsExportSpecificTopicItemContainer(jsonPayload);
				 if(container!=null){
					 for(JsonSkatNctsExportSpecificTopicItemRecord  record : container.getOrderList()){
						 record.setDebugPrintlnAjax(BASE_URL + "?" + urlRequestParamsKeys + " <JSON> " + jsonPayload + "</JSON>");
				         logger.info("=====>debugFetch: OK output on GUI");
				         //get security fields and add to master record
				         this.fetchSecurityRecord(record, applicationUser, avd, opd, lineNr);
				         //complete record including security	
				         result.add(record);
					 }
				 }
			 }
		 }else{
			 logger.error("[ERROR] on fields[]...null or incorrect length???...");
		 }
		 return result;
	 }
	 
	 
	  /**
	   * 
	   * @param applicationUser
	   * @param avd
	   * @return
	   */
	  @RequestMapping(value = "initCreateNewTopic_SkatNctsExport.do", method = RequestMethod.GET)
	  public @ResponseBody Set<JsonSkatCustomerRecord> initCreateNewTopic(@RequestParam String applicationUser, @RequestParam String avd) {
		 
		 	String method = "SkatNctsExportAjaxHandlerController.initCreateNewTopic_SkatNctsExport()";
		 	logger.info("Inside " + method);
		 	Set result = new HashSet();
		 	
		 	logger.info("FETCH record transaction...");
			//---------------------------
			//get BASE URL = RPG-PROGRAM
			//---------------------------
			String BASE_URL = SkatNctsExportUrlDataStore.NCTS_EXPORT_BASE_FETCH_SPECIFIC_TOPIC_URL;
			//url params
			String urlRequestParamsKeys = this.getRequestUrlKeyParametersForInitCreateNewTopic(applicationUser, avd);
			//for debug purposes in GUI
			logger.info(Calendar.getInstance().getTime() + " CGI-start timestamp");
			logger.info("URL: " + BASE_URL);
			logger.info("URL PARAMS: " + urlRequestParamsKeys);
			//--------------------------------------
			//EXECUTE the FETCH (RPG program) here
			//--------------------------------------
			String jsonPayload = this.urlCgiProxyService.getJsonContent(BASE_URL, urlRequestParamsKeys);
			//Debug --> 
			logger.info(method + " --> jsonPayload:" + jsonPayload);
			logger.info(Calendar.getInstance().getTime() +  " CGI-end timestamp");

			if(jsonPayload!=null){
			JsonSkatNctsExportSpecificTopicContainer container = this.skatNctsExportSpecificTopicService.getNctsExportSpecificTopicContainer(jsonPayload);
	    		if(container!=null){
	    			for(JsonSkatNctsExportSpecificTopicRecord  record : container.getOneorder()){
	    				logger.info("Deklarantens plats via AJAX: " + record.getThdst() );
	    				result.add(record);
	    			}
	    		}
	    	}
		return result;  
	  }
	  
	  /**
	   * 
	   * @param applicationUser
	   * @param guaranteeNumber
	   * @param guaranteeCode
	   * 
	   * @return
	   */
	  @RequestMapping(value = "validateGuaranteeNrAndCode_SkatNctsExport.do", method = RequestMethod.GET)
	  public @ResponseBody Set<JsonSkatCustomerRecord> validateGuaranteeNrAndCode(@RequestParam String applicationUser, @RequestParam String guaranteeNumber, @RequestParam String guaranteeCode ) {
		 
		 	String method = "SkatNctsExportAjaxHandlerController.validateGuaranteeNrAndCode()";
		 	logger.info("Inside " + method);
		 	Set result = new HashSet();
		 	
		 	logger.info("VALIDATION of Guarantee...");
			//---------------------------
			//get BASE URL = RPG-PROGRAM
			//---------------------------
			String BASE_URL = SkatNctsExportUrlDataStore.NCTS_EXPORT_BASE_VALIDATE_SPECIFIC_TOPIC_GUARRANTEE_URL;
			//url params
			String urlRequestParamsKeys = this.getRequestUrlKeyParametersForGuaranteeValidation(applicationUser,guaranteeNumber,guaranteeCode );
			//for debug purposes in GUI
			
			logger.info(Calendar.getInstance().getTime() + " CGI-start timestamp");
			logger.info("URL: " + BASE_URL);
			logger.info("URL PARAMS: " + urlRequestParamsKeys);
			//--------------------------------------
			//EXECUTE the FETCH (RPG program) here
			//--------------------------------------
			String jsonPayload = this.urlCgiProxyService.getJsonContent(BASE_URL, urlRequestParamsKeys);
			//Debug --> 
			logger.info(method + " --> jsonPayload:" + jsonPayload);
			logger.info(Calendar.getInstance().getTime() +  " CGI-end timestamp");

			if(jsonPayload!=null){
				JsonSkatNctsExportSpecificTopicGuaranteeValidatorContainer container = this.skatNctsExportSpecificTopicService.getNctsExportSpecificTopicGuaranteeValidatorContainer(jsonPayload);
				logger.info("errMsg on Guarantee: " + container.getErrMsg());
	    			if(container!=null){
	    				result.add(container);
	    			}
	    		}
			return result;
	  }	
	  
	  /**
	   * 
	   * @param applicationUser
	   * @param taricVarukod
	   * @return
	   */
	  
	  @RequestMapping(value = "searchTaricVarukod_SkatNctsExport.do", method = RequestMethod.GET)
	  public @ResponseBody Set<JsonSkatTaricVarukodRecord> getTaricVarukod(@RequestParam String applicationUser, @RequestParam String taricVarukod) {
		  logger.info("Inside searchTaricVarukod_SkatNctsExport()");
		  Set result = new HashSet();
		  String IMPORT_IE = "E";//
		  int VARUKOD_LENGTH_DK = 8;
		  String strDktara15 = "0.00";
		  JsonSkatTaricVarukodRecord defaultRecord = new JsonSkatTaricVarukodRecord();
		  try{
			  if(taricVarukod!=null && taricVarukod.length()==VARUKOD_LENGTH_DK){
				  String BASE_URL = SkatUrlDataStore.SKAT_FETCH_TARIC_VARUKODER_ITEMS_URL;
				  String urlRequestParamsKeys = "user=" + applicationUser + "&ie=" + IMPORT_IE + "&kod=" + taricVarukod;
				  UrlCgiProxyService urlCgiProxyService = new UrlCgiProxyServiceImpl();
				  String jsonPayload = urlCgiProxyService.getJsonContent(BASE_URL, urlRequestParamsKeys);
				  JsonSkatTaricVarukodContainer container = this.skatTaricVarukodService.getContainer(jsonPayload);
				  for(JsonSkatTaricVarukodRecord record : container.getTariclist()){
					  logger.info("dktara02:" + record.getDktara02());
					  logger.info("dktara63:" + record.getDktara63());
					  logger.info("dktara64:" + record.getDktara64());
					  logger.info("dktara15:" + record.getDktara15());
					  //format
					  if(record.getDktara15()!=null && !"".equals(record.getDktara15())){
						  if(record.getDktara15().length()==7){
							  String strInteger = record.getDktara15().substring(0,4);
							  String strDecimals = record.getDktara15().substring(3);
							  strDktara15 = strInteger + "." + strDecimals;
						  }
					  }
					  record.setDktara15(strDktara15);
					  logger.info("dktara15:" + record.getDktara15());
					  result.add(record);
				  }	
				  
			  }
			  //with no match
			  if(result.isEmpty()){
				  defaultRecord.setDktara15(strDktara15);
				  result.add(defaultRecord);
			  }
		  }catch(Exception e){
			  e.printStackTrace();
		  }
		  
		  return result;
	  }
	  
	  /**
	   * 
	   * @param applicationUser
	   * @param taricVarukod
	   * @return
	   */
	  @RequestMapping(value = "isFolsommeVare_SkatNctsExport.do", method = RequestMethod.GET)
	  public @ResponseBody Set<JsonSkatTaricVarukodRecord> isFolsommeVare(@RequestParam String applicationUser, @RequestParam String taricVarukod) {
		  logger.info("Inside isFolsommeVare_SkatNctsExport()");
		  Set result = new HashSet();
		  int VARUKOD_LENGTH_DK = 8;
		  String FOLSOMMEVARE_CODE = "064";
		  try{
			  if(taricVarukod!=null && taricVarukod.length()==VARUKOD_LENGTH_DK){
				  String BASE_URL = SkatUrlDataStore.SKAT_NCTS_CODES_URL;
				  String urlRequestParamsKeys = "user=" + applicationUser + "&typ=" + FOLSOMMEVARE_CODE + "&tariff=" + taricVarukod;
				  UrlCgiProxyService urlCgiProxyService = new UrlCgiProxyServiceImpl();
				  String jsonPayload = urlCgiProxyService.getJsonContent(BASE_URL, urlRequestParamsKeys);
				  JsonSkatNctsCodeContainer container = this.skatDropDownListPopulationService.getNctsCodeContainer(jsonPayload);
				  for(JsonSkatNctsCodeRecord record : container.getKodlista()){
					  //logger.info("tkkode:" + record.getTkkode());
					  result.add(record);
				  }	
			  }
		  }catch(Exception e){
			  e.printStackTrace();
		  }
		  
		  return result;
	  }
	  
	  /**
	   * 
	   * @param applicationUser
	   * @param guaranteeNumber
	   * @param guaranteeCode
	   * @return
	   */
	  private String getRequestUrlKeyParametersForGuaranteeValidation(String applicationUser,String guaranteeNumber,String guaranteeCode){
		  StringBuffer sb = new StringBuffer();
		  sb.append("user=" + applicationUser);
		  sb.append( SkatConstants.URL_CHAR_DELIMETER_FOR_PARAMS_WITH_HTML_REQUEST + "thgft1=" + guaranteeNumber );
		  sb.append( SkatConstants.URL_CHAR_DELIMETER_FOR_PARAMS_WITH_HTML_REQUEST + "thgadk=" + guaranteeCode );
		  return sb.toString();
	  }

	  /**
	   * 
	   * @param applicationUser
	   * @param avd
	   * @param opd
	   * @param lineNr
	   * @return
	   */
	  
	  private String getRequestUrlKeyParametersForItem(String applicationUser, String avd, String opd,  String lineNr){
		  StringBuffer sb = new StringBuffer();
		  sb.append("user=" + applicationUser);
		  if(avd!=null && !"".equals(avd) && opd!=null && !"".equals(opd) && lineNr!=null && !"".equals(lineNr)){
			  sb.append( SkatConstants.URL_CHAR_DELIMETER_FOR_PARAMS_WITH_HTML_REQUEST + "avd=" + avd );
			  sb.append( SkatConstants.URL_CHAR_DELIMETER_FOR_PARAMS_WITH_HTML_REQUEST + "opd=" + opd );
			  sb.append( SkatConstants.URL_CHAR_DELIMETER_FOR_PARAMS_WITH_HTML_REQUEST + "lin=" + lineNr );
		  }
		  return sb.toString();
	  }
	  
	  /**
	   * 
	   * @param applicationUser
	   * @param avd
	   * @return
	   */
	  private String getRequestUrlKeyParametersForInitCreateNewTopic(String applicationUser, String avd){
		  StringBuffer sb = new StringBuffer();
		  sb.append("user=" + applicationUser);
		  sb.append( SkatConstants.URL_CHAR_DELIMETER_FOR_PARAMS_WITH_HTML_REQUEST + "avd=" + avd );
		  return sb.toString();
	  }
	  
	  /**
	   * 
	   * @param jsonNctsExportSpecificTopicItemRecord
	   * @param user
	   * @param avd
	   * @param opd
	   * @param lin
	   */
	  private void fetchSecurityRecord(JsonSkatNctsExportSpecificTopicItemRecord jsonNctsExportSpecificTopicItemRecord, String user, String avd, String opd, String lin){
			String method = "fetchSecurityRecord";
			logger.info("starting " + method + " transaction...");
			
			String BASE_URL = SkatNctsExportUrlDataStore.NCTS_EXPORT_BASE_FETCH_SPECIFIC_SIKKERHET_TOPIC_ITEM_URL;
			String urlRequestParamsKeys = "user=" + user + "&avd=" + avd + "&opd=" + opd + "&lin=" + lin;   
			logger.info(Calendar.getInstance().getTime() + " CGI-start timestamp");
	    	logger.info("URL: " + BASE_URL);
	    	logger.info("URL PARAMS: " + urlRequestParamsKeys);
	    	JsonSkatNctsExportSpecificTopicItemSecurityRecord securityRecord = null;
	    	JsonSkatNctsExportSpecificTopicItemRecord targetRecord = jsonNctsExportSpecificTopicItemRecord;
	    	
	    	//------------------------
	    	//EXECUTE (Sikkerhet) here
	    	//------------------------
	    	String jsonPayload = this.urlCgiProxyService.getJsonContent(BASE_URL, urlRequestParamsKeys);
	    	logger.info(method + " --> jsonPayload:" + jsonPayload);
	    	logger.info(Calendar.getInstance().getTime() +  " CGI-end timestamp");
	    	if(jsonPayload!=null){
	    		JsonSkatNctsExportSpecificTopicItemSecurityContainer securityContainer = this.skatNctsExportSpecificTopicItemService.getNctsExportSpecificTopicItemSecurityContainer(jsonPayload);
	        	if(securityContainer!=null && securityContainer.getSecurityline()!=null){
	        		for (JsonSkatNctsExportSpecificTopicItemSecurityRecord thisRecord : securityContainer.getSecurityline()){
	        			securityRecord = thisRecord;
	        			logger.info("Tvknss" + securityRecord.getTvknss());
	            	}
	        		//copy delta to target record
	        		if(securityRecord!=null && targetRecord!=null){
		        		JavaReflectionUtil.setFields(securityRecord, targetRecord);
		        		//Replace the orginal with the complete record (including security record, if any)
		        		jsonNctsExportSpecificTopicItemRecord = targetRecord;
	        		}else{
	        			/* TEST on Copy
	        			securityRecord = new JsonSadNctsExportSpecificTopicItemSecurityRecord();
		        		securityRecord.setTvnass("TARZAN");
		        		logger.info("SOURCE:" + securityRecord.getTvnass());
		        		JavaReflectionUtil.setFields(securityRecord, targetRecord);
		        		//Replace the orginal with the complete record (including security record, if any)
		        		jsonNctsExportSpecificTopicItemRecord = targetRecord;
		        		logger.info("TARGET:" + jsonNctsExportSpecificTopicItemRecord.getTvnass());
		        		*/
	        		}
	        	}
	    	}
		}
		
	  /**
	   * Copied from SkatExportAjaxHandler (jan 10th, 2017)
	   * 
	   * @param applicationUser
	   * @param currencyCode
	   * @param isoDate
	   * @param invoiceAmount
	   * @return
	   */
	  @RequestMapping(value = "getCurrencyRate_SkatNctsExport.do", method = RequestMethod.GET)
	  public @ResponseBody Set getCurrencyRate(@RequestParam String applicationUser, @RequestParam String currencyCode, 
			  @RequestParam String isoDate, @RequestParam String invoiceAmount, @RequestParam String toldsats) {
		  final String METHOD = "[DEBUG] getCurrencyRate_SkatNctsExport.do "; 
		  logger.info("Inside " + METHOD);
		  Set result = new HashSet();
		  //String validDate = this.getValidCurrencyDate(isoDate);
		  String validDate = isoDate;
		  if(toldsats==null || "".equals(toldsats)){
			  toldsats = "0.00";
		  }
		  //build
		  String BASE_URL_CURRENCY_RATE = SkatUrlDataStore.SKAT_FETCH_CURRENCY_RATE_URL;
		  StringBuffer urlRequestParamsCurrencyRate = new StringBuffer();
		  urlRequestParamsCurrencyRate.append("user=" + applicationUser);
		  urlRequestParamsCurrencyRate.append(SkatConstants.URL_CHAR_DELIMETER_FOR_PARAMS_WITH_HTML_REQUEST + "kod=" + currencyCode);
		  urlRequestParamsCurrencyRate.append(SkatConstants.URL_CHAR_DELIMETER_FOR_PARAMS_WITH_HTML_REQUEST + "datum=" + validDate);
		  //execute
		  String jsonPayloadCurrencyRate = this.urlCgiProxyService.getJsonContent(BASE_URL_CURRENCY_RATE, urlRequestParamsCurrencyRate.toString());
		  logger.info(METHOD + "CURRENCY URL:" + BASE_URL_CURRENCY_RATE);
		  logger.info(METHOD + "CURRENCY PARAMS:" + urlRequestParamsCurrencyRate.toString());
		  logger.info(METHOD + jsonPayloadCurrencyRate);
		  //now map and process json
		  if(jsonPayloadCurrencyRate!=null){
			  JsonCurrencyRateContainer jsonCurrencyRateContainer = this.currencyRateService.getCurrencyRateContainer(jsonPayloadCurrencyRate);
			  for(JsonCurrencyRateRecord record : jsonCurrencyRateContainer.getValutakurs()){
				  //Debug
				  logger.info(METHOD + "Currency RATE: " + record.getDkvk_krs());
				  logger.info(METHOD + "Currency FACTOR: " + record.getDkvs_omr());
				  //extra fields
				  if(invoiceAmount!=null && !"".equals(invoiceAmount)){
					  Double dblAmount = Double.parseDouble(invoiceAmount.replace(",", "."));
					  Double dblToldsats = Double.parseDouble(toldsats.replace(",", "."));
					  Double dblKurs = Double.parseDouble(record.getDkvk_krs().replace(",", "."));
					  Integer intFactor = Integer.parseInt(record.getDkvs_omr());
					  logger.info(METHOD + "Before math... ");
					  //(1) do the math
					  Double dblAmountDKK = (dblAmount * dblKurs) / intFactor;
					  Double dblTollvDKK = (dblAmountDKK * dblToldsats);
					  Double dblSubTotalExklMoms = (dblAmountDKK + dblTollvDKK);
					  Double dblMomsDKK = (dblSubTotalExklMoms) * 0.25;
					  Double dblGrandTotalDKK = dblSubTotalExklMoms + dblMomsDKK;
					  logger.info(METHOD + "After math... " + dblAmountDKK + "-"+dblTollvDKK + "-" + dblSubTotalExklMoms + "-" + dblMomsDKK + "-" + dblGrandTotalDKK);
					  //format decimals numbers
					  //dblAmountDKK = this.numberFormatter.getDouble(dblAmountDKK, 2);
					  //dblTollvDKK = this.numberFormatter.getDouble(dblTollvDKK, 2);
					  //dblSubTotalExklMoms = this.numberFormatter.getDouble(dblSubTotalExklMoms, 2);
					  //dblMomsDKK = this.numberFormatter.getDouble(dblMomsDKK, 2);
					  //dblGrandTotalDKK = this.numberFormatter.getDouble(dblGrandTotalDKK, 2);
					  //logger.info(METHOD + "After format... " + dblAmountDKK + "-"+dblTollvDKK + "-" + dblSubTotalExklMoms + "-" + dblMomsDKK + "-" + dblGrandTotalDKK);
					  ///(2) setters
					  //--String strAmountDKK = String.valueOf(dblAmountDKK);
					  //--String strAmountDKK = this.numberFormatter.getDoubleEuropeanFormat(dblAmountDKK, 2);
					  String strAmountDKK = String.valueOf(this.numberFormatter.getDoubleEuropeanFormat(dblAmountDKK, 2, false));
					  record.setOwn_blpDKK(strAmountDKK);
					  //Tollverdi
					  //--String strTollvDKK = String.valueOf(dblTollvDKK);
					  //--record.setOwn_tollvDKK(strTollvDKK.replace(".", ","));
					  String strTollvDKK = String.valueOf(this.numberFormatter.getDoubleEuropeanFormat(dblTollvDKK, 2, false));
					  record.setOwn_tollvDKK(strTollvDKK);
					  //Moms
					  //--String strMomsDKK = String.valueOf(dblMomsDKK);
					  //--record.setOwn_momsDKK(strMomsDKK.replace(".", ","));
					  String strMomsDKK = String.valueOf(this.numberFormatter.getDoubleEuropeanFormat(dblMomsDKK, 2, false));
					  record.setOwn_momsDKK(strMomsDKK);
					  //Grand total
					  //--String strGrandTotalDKK = String.valueOf(dblGrandTotalDKK);
					  //--record.setOwn_grandTotalDKK(strGrandTotalDKK.replace(".", ","));
					  String setOwn_grandTotalDKK = String.valueOf(this.numberFormatter.getDoubleEuropeanFormat(dblGrandTotalDKK, 2, false));
					  record.setOwn_grandTotalDKK(setOwn_grandTotalDKK);
					  logger.info(METHOD + "Grand Total: " + record.getOwn_grandTotalDKK());
					  
				  }
				  result.add(record);
			  }
		  } 
		  return result;
	  }
	  	
	  //SERVICES
	  @Qualifier ("urlCgiProxyService")
	  private UrlCgiProxyService urlCgiProxyService;
	  @Autowired
	  @Required
	  public void setUrlCgiProxyService (UrlCgiProxyService value){ this.urlCgiProxyService = value; }
	  public UrlCgiProxyService getUrlCgiProxyService(){ return this.urlCgiProxyService; }
	  
	  
	  
	  @Qualifier ("skatNctsExportSpecificTopicService")
	  private SkatNctsExportSpecificTopicService skatNctsExportSpecificTopicService;
	  @Autowired
	  @Required
	  public void setSkatNctsExportSpecificTopicService (SkatNctsExportSpecificTopicService value){ this.skatNctsExportSpecificTopicService = value; }
	  public SkatNctsExportSpecificTopicService getSkatNctsExportSpecificTopicService(){ return this.skatNctsExportSpecificTopicService; }
	  
	  
	  @Qualifier ("skatNctsExportSpecificTopicItemService")
	  private SkatNctsExportSpecificTopicItemService skatNctsExportSpecificTopicItemService;
	  @Autowired
	  @Required	
	  public void setSkatNctsExportSpecificTopicItemService(SkatNctsExportSpecificTopicItemService value){this.skatNctsExportSpecificTopicItemService = value;}
	  public SkatNctsExportSpecificTopicItemService getSkatNctsExportSpecificTopicItemService(){ return this.skatNctsExportSpecificTopicItemService; }

	  
	  @Qualifier ("currencyRateService")
	  private CurrencyRateService currencyRateService;
	  @Autowired
	  public void setCurrencyRateService (CurrencyRateService value){ this.currencyRateService=value; }
	  public CurrencyRateService getCurrencyRateService(){return this.currencyRateService;}
	  	

	  @Qualifier 
	  private SkatTaricVarukodService skatTaricVarukodService;
	  @Autowired
	  @Required	
	  public void setSkatTaricVarukodService(SkatTaricVarukodService value){this.skatTaricVarukodService = value;}
	  public SkatTaricVarukodService getSkatTaricVarukodService(){ return this.skatTaricVarukodService; }
	  
	  @Qualifier ("skatDropDownListPopulationService")
		private SkatDropDownListPopulationService skatDropDownListPopulationService;
		@Autowired
		public void setSkatDropDownListPopulationService (SkatDropDownListPopulationService value){ this.skatDropDownListPopulationService=value; }
		public SkatDropDownListPopulationService getSkatDropDownListPopulationService(){return this.skatDropDownListPopulationService;}
		
	  
}
