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

//SKAT
import no.systema.skat.url.store.SkatUrlDataStore;
import no.systema.skat.util.SkatConstants;
import no.systema.skat.model.jsonjackson.codes.JsonSkatTaricVarukodContainer;
import no.systema.skat.model.jsonjackson.codes.JsonSkatTaricVarukodRecord;
import no.systema.skat.model.jsonjackson.customer.JsonSkatCustomerRecord;
import no.systema.skat.service.SkatTaricVarukodService;

import no.systema.skat.nctsexport.url.store.SkatNctsExportUrlDataStore;
import no.systema.skat.nctsexport.model.jsonjackson.topic.JsonSkatNctsExportSpecificTopicContainer;
import no.systema.skat.nctsexport.model.jsonjackson.topic.JsonSkatNctsExportSpecificTopicRecord;
import no.systema.skat.nctsexport.model.jsonjackson.topic.items.JsonSkatNctsExportSpecificTopicItemContainer;
import no.systema.skat.nctsexport.model.jsonjackson.topic.items.JsonSkatNctsExportSpecificTopicItemRecord;
import no.systema.skat.nctsexport.service.SkatNctsExportSpecificTopicService;
import no.systema.skat.nctsexport.service.SkatNctsExportSpecificTopicItemService;

import no.systema.skat.nctsexport.model.jsonjackson.topic.validation.JsonSkatNctsExportSpecificTopicGuaranteeValidatorContainer;


//import no.systema.skat.skatimport.model.jsonjackson.topic.JsonSkatImportSpecificTopicOmbudContainer;
//import no.systema.skat.skatimport.model.jsonjackson.topic.JsonSkatImportSpecificTopicOmbudRecord;
//import no.systema.skat.service.SkatTaricVarukodService;
//import no.systema.skat.model.jsonjackson.codes.JsonSkatTaricVarukodContainer;
//import no.systema.skat.model.jsonjackson.codes.JsonSkatTaricVarukodRecord;


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
		  
		  try{
			  String BASE_URL = SkatUrlDataStore.SKAT_FETCH_TARIC_VARUKODER_ITEMS_URL;
			  String urlRequestParamsKeys = "user=" + applicationUser + "&ie=" + IMPORT_IE + "&kod=" + taricVarukod;
			  UrlCgiProxyService urlCgiProxyService = new UrlCgiProxyServiceImpl();
			  String jsonPayload = urlCgiProxyService.getJsonContent(BASE_URL, urlRequestParamsKeys);
			  JsonSkatTaricVarukodContainer container = this.skatTaricVarukodService.getContainer(jsonPayload);
			  for(JsonSkatTaricVarukodRecord record : container.getTariclist()){
				  /*logger.info("dktara02:" + record.getDktara02());
				  logger.info("dktara63:" + record.getDktara63());
				  logger.info("dktara64:" + record.getDktara64());
				  */
				  result.add(record);
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
	  
	  
	  
	  
	  /* ? to be commented as it was
	  @Qualifier
	  private TdsImportTullkontorService tdsImportTullkontorService;
	  @Autowired
	  @Required	
	  public void setTdsImportTullkontorService(TdsImportTullkontorService value){this.tdsImportTullkontorService = value;}
	  public TdsImportTullkontorService getTdsImportTullkontorService(){ return this.tdsImportTullkontorService; }
	   */
	 
	  /* ?
	  @Qualifier 
	  private TdsSignatureNameService tdsSignatureNameService;
	  @Autowired
	  @Required	
	  public void setTdsSignatureNameService(TdsSignatureNameService value){this.tdsSignatureNameService = value;}
	  public TdsSignatureNameService getTdsSignatureNameService(){ return this.tdsSignatureNameService; }
	  */
		
}
