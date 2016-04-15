/**
 * 
 */
package no.systema.skat.controller.ajax;

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
import no.systema.main.service.general.CurrencyRateService;

//SKAT
//import no.systema.skat.skatimport.util.manager.TollvaerdideklarationMgr;
//import no.systema.skat.skatimport.model.jsonjackson.topic.items.JsonSkatImportSpecificTopicItemToldvaerdiRecord;
//import no.systema.skat.service.SkatTaricVarukodService;

import no.systema.skat.url.store.SkatUrlDataStore;
import no.systema.skat.util.SkatConstants;
import no.systema.skat.model.jsonjackson.tullkontor.JsonSkatTullkontorContainer;
import no.systema.skat.model.jsonjackson.tullkontor.JsonSkatTullkontorRecord;
import no.systema.skat.model.jsonjackson.customer.JsonSkatCustomerContainer;
import no.systema.skat.model.jsonjackson.customer.JsonSkatCustomerRecord;
import no.systema.skat.service.SkatTullkontorService;
import no.systema.skat.service.SkatCustomerService;

/**
 * This Ajax handler is the class handling ajax request in Skat (general)
 * It will usually be called from within a jQuery function or other javascript alike... 
 * 
 * @author oscardelatorre
 * @date Apr 18, 2014
 * 
 */

@Controller

public class SkatAjaxHandlerController {
	private static final Logger logger = Logger.getLogger(SkatAjaxHandlerController.class.getName());
	 
	/**
	 * Gets the signature name (person)
	 * 
	 * @param applicationUser
	 * @param avd
	 * @param sign
	 * @return
	 */
	/*
	@RequestMapping(value = "getSignatureName_TdsImport.do", method = RequestMethod.GET)
     public @ResponseBody Set<JsonTdsSignatureNameRecord> getSignatureName
	  						(@RequestParam String applicationUser, @RequestParam String avd, 
	  						 @RequestParam String sign) {
		 logger.info("Inside: getSignatureName_TdsImport.do");
		 Set result = new HashSet();
		 //prepare the access CGI with RPG back-end
		 String BASE_URL = TdsUrlDataStore.TDS_FETCH_SIGNATURE_NAME_URL;
		 String urlRequestParamsKeys = "user=" + applicationUser + "&avd=" + avd + "&sign=" + sign;
		 logger.info("URL: " + BASE_URL);
		 logger.info("PARAMS: " + urlRequestParamsKeys);
		 logger.info(Calendar.getInstance().getTime() +  " CGI-start timestamp");
		 String jsonPayload = this.urlCgiProxyService.getJsonContent(BASE_URL, urlRequestParamsKeys);
		 logger.info(Calendar.getInstance().getTime() +  " CGI-end timestamp");
		 logger.info(jsonPayload);
		 if(jsonPayload!=null){
			 	try{
			 		JsonTdsSignatureNameContainer container = this.tdsSignatureNameService.getContainer(jsonPayload);
					if(container!=null){
						 for(JsonTdsSignatureNameRecord  record : container.getGetsignname()){
							 logger.info("#### SVTH_NAMN:" + record.getSvth_namn());
							 result.add(record);
						 }
					}
			 	}catch(Exception e){
			 		e.printStackTrace();
			 	}
			 }
		 
		 return result;
	}
	  */
	
	
	 
	  /**
	   * Populates the GUI element with a list of customers fulfilling the search condition
	   * 
	   * @param customerName
	   * @param customerNumber
	   * @return
	   */
	  
	  @RequestMapping(value = "searchCustomer_Skat.do", method = RequestMethod.GET)
	  public @ResponseBody Set<JsonSkatCustomerRecord> searchCustomer(@RequestParam String applicationUser, @RequestParam String customerName, @RequestParam String customerNumber) {
		  logger.info("Inside SkatAjaxHandlerController.searchCustomer()");
		  Set result = new HashSet();
		  //prepare the access CGI with RPG back-end
		  String BASE_URL = SkatUrlDataStore.SKAT_FETCH_CUSTOMER_URL;
		  String urlRequestParamsKeys = this.getRequestUrlKeyParametersForSearchCustomer(applicationUser, customerName, customerNumber);
		  logger.info("URL: " + BASE_URL);
		  logger.info("PARAMS: " + urlRequestParamsKeys);
		  logger.info(Calendar.getInstance().getTime() +  " CGI-start timestamp");
		  String jsonPayload = this.urlCgiProxyService.getJsonContent(BASE_URL, urlRequestParamsKeys);
		  //Should be removed as soon as RPG return the correct container name = customerlist (not capitalized in the first letter)
		  logger.info(jsonPayload);
		  logger.info(Calendar.getInstance().getTime() +  " CGI-end timestamp");
	    	if(jsonPayload!=null){
	    		jsonPayload = jsonPayload.replaceFirst("Customerlist", "customerlist");
	    		JsonSkatCustomerContainer container = this.skatCustomerService.getSkatCustomerContainer(jsonPayload);
	    		if(container!=null){
	    			for(JsonSkatCustomerRecord  record : container.getCustomerlist()){
	    				//logger.info("CUSTOMER via AJAX: " + record.getKnavn() + " NUMBER:" + record.getKundnr());
	    				//logger.info("KPERS: " + record.getKpers() + " TLF:" + record.getTlf());
	    				result.add(record);
	    			}
	    		}
	    	}
		  return result;
		  
	  }
	  
	  /**
	   * Populates the GUI element with a list of tullkontor-offices fulfilling the search condition
	   * 
	   * @param applicationUser
	   * @param soName
	   * @param code
	   * @return
	   */
	  @RequestMapping(value = "searchUtfartsTullkontor_Skat.do", method = RequestMethod.GET)
	  public @ResponseBody List<JsonSkatTullkontorRecord> searchUtfartsTullkontor(@RequestParam String applicationUser, 
			  @RequestParam String tullkontorName, @RequestParam String tullkontorCode, @RequestParam String kontorType) {
		  logger.info("Inside searchUtfartsTullkontor_Skat()");
		  logger.info("kontorType:" + kontorType);
		  List result = new ArrayList();
		  //prepare the access CGI with RPG back-end
		  String BASE_URL = SkatUrlDataStore.SKAT_FETCH_UTFARTS_TULLKONTOR_URL;
		  String urlRequestParamsKeys = this.getRequestUrlKeyParametersForSearchUtfartsTullkontor(applicationUser, tullkontorName, tullkontorCode, kontorType);
		  logger.info("URL: " + BASE_URL);
		  logger.info("PARAMS: " + urlRequestParamsKeys);
		  logger.info(Calendar.getInstance().getTime() +  " CGI-start timestamp");
		  String jsonPayload = this.urlCgiProxyService.getJsonContent(BASE_URL, urlRequestParamsKeys);
		  logger.info(jsonPayload);
		  logger.info(Calendar.getInstance().getTime() +  " CGI-end timestamp");
		  if(jsonPayload!=null){
			  JsonSkatTullkontorContainer container = this.skatTullkontorService.getSkatTullkontorContainer(jsonPayload);
			  if(container!=null){
				  for(JsonSkatTullkontorRecord  record : container.getCustomslist()){
					  //logger.info("Kontorsnamn via AJAX: " + record.getTktxtn() + " Code:" + record.getTkkode());
					  result.add(record);
				  }
			  }
		  }
		  return result;
		  
	  }
	  
	  /**
	   * 
	   * @param applicationUser
	   * @param currencyCode
	   * @return
	   */
	  @RequestMapping(value = "getCurrencyRate_Skat.do", method = RequestMethod.GET)
	  public @ResponseBody Set getCurrencyRate(@RequestParam String applicationUser, @RequestParam String currencyCode) {
		  logger.info("Inside getCurrencyRate_Skat");
		  Set result = new HashSet();
		  
		  String BASE_URL_CURRENCY_RATE = SkatUrlDataStore.SKAT_FETCH_CURRENCY_RATE_URL;
		  StringBuffer urlRequestParamsCurrencyRate = new StringBuffer();
		  urlRequestParamsCurrencyRate.append("user=" + applicationUser);
		  urlRequestParamsCurrencyRate.append(SkatConstants.URL_CHAR_DELIMETER_FOR_PARAMS_WITH_HTML_REQUEST + "kod=" + currencyCode);
		  String jsonPayloadCurrencyRate = this.urlCgiProxyService.getJsonContent(BASE_URL_CURRENCY_RATE, urlRequestParamsCurrencyRate.toString());
		  logger.info("CURRENCY URL:" + BASE_URL_CURRENCY_RATE);
		  logger.info("CURRENCY PARAMS:" + urlRequestParamsCurrencyRate.toString());
		  logger.info(jsonPayloadCurrencyRate);
		  //now map and process json
		  if(jsonPayloadCurrencyRate!=null){
			  JsonCurrencyRateContainer jsonCurrencyRateContainer = this.currencyRateService.getCurrencyRateContainer(jsonPayloadCurrencyRate);
			  for(JsonCurrencyRateRecord record : jsonCurrencyRateContainer.getValutakurs()){
				  //Debug
				  logger.info("Currency RATE: " + record.getSvvk_krs() + " " + record.getDkvk_krs());
				  logger.info("Currency FACTOR: " + record.getSvvs_omr() + " " + record.getDkvs_omr());
				  result.add(record);
			  }
		  } 
		  return result;
	  }
	  
	  
	  /**
	   * Forms the correct parameter list according to a correct html POST
	   * @param applicationUser
	   * @param customerName
	   * @param customerNumber
	   * @return
	   */
	  private String getRequestUrlKeyParametersForSearchCustomer(String applicationUser, String customerName, String customerNumber){
		  StringBuffer sb = new StringBuffer();
		  sb.append("user=" + applicationUser);
		  if(customerName!=null && !"".equals(customerName) && customerNumber!=null && !"".equals(customerNumber)){
			  sb.append( SkatConstants.URL_CHAR_DELIMETER_FOR_PARAMS_WITH_HTML_REQUEST + "sonavn=" + customerName );
			  sb.append( SkatConstants.URL_CHAR_DELIMETER_FOR_PARAMS_WITH_HTML_REQUEST + "knr=" + customerNumber );
		  }else if (customerName!=null && !"".equals(customerName)){
			  sb.append( SkatConstants.URL_CHAR_DELIMETER_FOR_PARAMS_WITH_HTML_REQUEST + "sonavn=" + customerName );
		  }else if (customerNumber!=null && !"".equals(customerNumber)){
			  sb.append( SkatConstants.URL_CHAR_DELIMETER_FOR_PARAMS_WITH_HTML_REQUEST + "knr=" + customerNumber );
		  }
		  
		  return sb.toString();
	  }
	  /**
	   * 
	   * @param applicationUser
	   * @param soName
	   * @param code
	   * @return
	   */
	  private String getRequestUrlKeyParametersForSearchUtfartsTullkontor(String applicationUser, String soName, String code, String kontorType){
		  StringBuffer sb = new StringBuffer();
		  sb.append("user=" + applicationUser);
		  if(soName!=null && !"".equals(soName) && code!=null && !"".equals(code)){
			  sb.append( SkatConstants.URL_CHAR_DELIMETER_FOR_PARAMS_WITH_HTML_REQUEST + "sonavn=" + soName );
			  sb.append( SkatConstants.URL_CHAR_DELIMETER_FOR_PARAMS_WITH_HTML_REQUEST + "kod=" + code );
		  }else if (soName!=null && !"".equals(soName)){
			  sb.append( SkatConstants.URL_CHAR_DELIMETER_FOR_PARAMS_WITH_HTML_REQUEST + "sonavn=" + soName );
		  }else if (code!=null && !"".equals(code)){
			  sb.append( SkatConstants.URL_CHAR_DELIMETER_FOR_PARAMS_WITH_HTML_REQUEST + "kod=" + code );
		  }
		  //append the type when applicable
		  if (kontorType!=null && !"".equals(kontorType)){
			  sb.append( SkatConstants.URL_CHAR_DELIMETER_FOR_PARAMS_WITH_HTML_REQUEST);
			  if("avg".equals(kontorType)){
				  sb.append("avg=J");
			  }else if ("ank".equals(kontorType)){
				  sb.append("ank=J");
			  }else if ("trs".equals(kontorType)){
				  sb.append("trs=J");
			  }
		  }
		  
		  return sb.toString();
	  }
	  
	  
	  	
	  //SERVICES
	  @Qualifier ("urlCgiProxyService")
	  private UrlCgiProxyService urlCgiProxyService;
	  @Autowired
	  @Required
	  public void setUrlCgiProxyService (UrlCgiProxyService value){ this.urlCgiProxyService = value; }
	  public UrlCgiProxyService getUrlCgiProxyService(){ return this.urlCgiProxyService; }
	  
	  @Qualifier ("skatTullkontorService")
	  private SkatTullkontorService skatTullkontorService;
	  @Autowired
	  @Required
	  public void setSkatTullkontorService (SkatTullkontorService value){ this.skatTullkontorService = value; }
	  public SkatTullkontorService getSkatTullkontorService(){ return this.skatTullkontorService; }
	  
	  @Qualifier 
	  private SkatCustomerService skatCustomerService;
	  @Autowired
	  @Required	
	  public void setSkatCustomerService(SkatCustomerService value){this.skatCustomerService = value;}
	  public SkatCustomerService getSkatCustomerService(){ return this.skatCustomerService; }
	  
	  @Qualifier ("currencyRateService")
	  private CurrencyRateService currencyRateService;
	  @Autowired
	  public void setCurrencyRateService (CurrencyRateService value){ this.currencyRateService=value; }
	  public CurrencyRateService getCurrencyRateService(){return this.currencyRateService;}
	  
	   
  	  /*
	  @Qualifier 
	  private TdsSignatureNameService tdsSignatureNameService;
	  @Autowired
	  @Required	
	  public void setTdsSignatureNameService(TdsSignatureNameService value){this.tdsSignatureNameService = value;}
	  public TdsSignatureNameService getTdsSignatureNameService(){ return this.tdsSignatureNameService; }
	  */
		
}
