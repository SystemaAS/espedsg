/**
 * 
 */
package no.systema.fraktkalkulator.controller.ajax;

import java.util.*;


import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Required;
import org.springframework.stereotype.Controller;

import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import no.systema.main.model.jsonjackson.general.JsonCurrencyRateContainer;
import no.systema.main.model.jsonjackson.general.JsonCurrencyRateRecord;
import no.systema.main.service.UrlCgiProxyService;
import no.systema.main.service.general.CurrencyRateService;


import no.systema.fraktkalkulator.url.store.FraktKalkulatorUrlDataStore;
import no.systema.fraktkalkulator.util.FraktKalkulatorConstants;
import no.systema.fraktkalkulator.model.jsonjackson.customer.JsonFraktKalkulatorCustomerContainer;
import no.systema.fraktkalkulator.model.jsonjackson.customer.JsonFraktKalkulatorCustomerRecord;
import no.systema.fraktkalkulator.service.FraktKalkulatorChildWindowsService;
import no.systema.fraktkalkulator.service.FraktKalkulatorDropDownService;

import no.systema.fraktkalkulator.model.jsonjackson.dropdownlist.JsonFraktKalkulatorDropDownContainer;
import no.systema.fraktkalkulator.model.jsonjackson.dropdownlist.JsonFraktKalkulatorOppdragTypeRecord;
import no.systema.fraktkalkulator.model.jsonjackson.dropdownlist.JsonFraktKalkulatorProductRecord;
import no.systema.fraktkalkulator.model.jsonjackson.dropdownlist.JsonFraktKalkulatorIncotermsRecord;



/**
 * This Ajax handler is the class handling ajax request in Fraktkalkulator (general)
 * It will usually be called from within a jQuery function or other javascript alike... 
 * 
 * @author oscardelatorre
 * @date Feb 21, 2015
 * 
 */

@Controller

public class FraktKalkulatorAjaxHandlerController {
	private static final Logger logger = Logger.getLogger(FraktKalkulatorAjaxHandlerController.class.getName());
	 
	
	  /**
	   * Populates the GUI element with a list of customers fulfilling the search condition
	   * @param customerName
	   * @param customerNumber
	   * @return
	   */
	 
	  @RequestMapping(value = "searchCustomer_Fraktkalkulator.do", method = RequestMethod.GET)
	  public @ResponseBody Set<JsonFraktKalkulatorCustomerRecord> searchCustomer(@RequestParam String applicationUser, @RequestParam String customerName, @RequestParam String customerNumber) {
		  logger.info("Inside searchCustomer");
		  Set<JsonFraktKalkulatorCustomerRecord> result = new HashSet<JsonFraktKalkulatorCustomerRecord>();
		  //prepare the access CGI with RPG back-end
		  String BASE_URL = FraktKalkulatorUrlDataStore.FRAKTKALKULATOR_FETCH_CUSTOMER_URL;
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
	    		JsonFraktKalkulatorCustomerContainer container = this.fraktKalkulatorCustomerService.getCustomerContainer(jsonPayload);
	    		if(container!=null){
	    			for(JsonFraktKalkulatorCustomerRecord  record : container.getCustomerlist()){
	    				result.add(record);
	    			}
	    		}
	    	  }
	    	  return result;
		   
	  }
	  /**
	   * 
	   * @param applicationUser
	   * @param wsavd
	   * @param avvknr
	   * @return
	   */
	  @RequestMapping(value = "updateProductList_Fraktkalkulator.do", method = RequestMethod.GET)
	  public @ResponseBody Set<JsonFraktKalkulatorProductRecord> updateProduktList(@RequestParam String applicationUser, @RequestParam String wsavd,  @RequestParam String avvknr) {
		  String method = "updateProduktList() ";
		  logger.info(method + " Inside...");
		  Set<JsonFraktKalkulatorProductRecord> result = new HashSet<JsonFraktKalkulatorProductRecord>();
		  //prepare the access CGI with RPG back-end
		  String BASE_URL = FraktKalkulatorUrlDataStore.FRAKTKALKULATOR_FETCH_DROPDOWN_PRODUCT_DATA_URL;
		  StringBuffer urlRequestParamsKeys = new StringBuffer();
		  urlRequestParamsKeys.append("user=" + applicationUser + "&wsavd=" + wsavd);
		  if(avvknr!=null && !"".equals(avvknr)){
			  urlRequestParamsKeys.append("&avvknr=" + avvknr);
		  }
		  logger.info("URL: " + BASE_URL);
		  logger.info("PARAMS: " + urlRequestParamsKeys);
		  logger.info(Calendar.getInstance().getTime() +  " CGI-start timestamp");
		  String jsonPayload = this.urlCgiProxyService.getJsonContent(BASE_URL, urlRequestParamsKeys.toString());
		  logger.info(jsonPayload);
		  logger.info(method + Calendar.getInstance().getTime() +  " CGI-end timestamp");
	    	  if(jsonPayload!=null){
	    		  JsonFraktKalkulatorDropDownContainer container = this.fraktKalkulatorDropDownService.getContainer(jsonPayload);
	    		  if(container!=null){
	    			  for(JsonFraktKalkulatorProductRecord  record : container.getPricecalclist_prod()){
	    				  //logger.info(method + "TEXT: " + record.getProdtxt());
	    				  result.add(record); 
	    			  }
	    		  }
	    	  }
	    	
	    	return result;
		  
	  }
	  /**
	   * 
	   * @param applicationUser
	   * @param wsavd
	   * @return
	   */
	  @RequestMapping(value = "updateOppdragType_Fraktkalkulator.do", method = RequestMethod.GET)
	  public @ResponseBody Set<JsonFraktKalkulatorOppdragTypeRecord> updateOppdragType(@RequestParam String applicationUser, @RequestParam String wsavd, @RequestParam String avvknr) {
		  String method = "updateOppdragType() ";
		  logger.info(method + " Inside...");
		  Set<JsonFraktKalkulatorOppdragTypeRecord> result = new HashSet<JsonFraktKalkulatorOppdragTypeRecord>();
		  //prepare the access CGI with RPG back-end
		  String BASE_URL = FraktKalkulatorUrlDataStore.FRAKTKALKULATOR_FETCH_DROPDOWN_OPPDRAG_TYPE_DATA_URL;
		  StringBuffer urlRequestParamsKeys = new StringBuffer();
		  urlRequestParamsKeys.append("user=" + applicationUser + "&wsavd=" + wsavd);
		  if(avvknr!=null && !"".equals(avvknr)){
			  urlRequestParamsKeys.append("&avvknr=" + avvknr);
		  }
		  
		  logger.info("URL: " + BASE_URL);
		  logger.info("PARAMS: " + urlRequestParamsKeys);
		  logger.info(Calendar.getInstance().getTime() +  " CGI-start timestamp");
		  String jsonPayload = this.urlCgiProxyService.getJsonContent(BASE_URL, urlRequestParamsKeys.toString());
		  logger.info(jsonPayload);
		  logger.info(method + Calendar.getInstance().getTime() +  " CGI-end timestamp");
	    	  if(jsonPayload!=null){
	    		  JsonFraktKalkulatorDropDownContainer container = this.fraktKalkulatorDropDownService.getContainer(jsonPayload);
	    		  if(container!=null){
	    			  for(JsonFraktKalkulatorOppdragTypeRecord  record : container.getPricecalclist_ot()){
	    				  //logger.info(method + "TEXT: " + record.getWsot2());
	    				  result.add(record);
	    			  }
	    		  }
	    	  }
	    	return result;
		  
	  }
	  /**
	   * 
	   * @param applicationUser
	   * @param wsavd
	   * @return
	   */
	  @RequestMapping(value = "updateIncoterms_Fraktkalkulator.do", method = RequestMethod.GET)
	  public @ResponseBody Set<JsonFraktKalkulatorIncotermsRecord> updateIncoterms(@RequestParam String applicationUser, @RequestParam String wsavd, @RequestParam String avvknr) {
		  String method = "updateIncoterms() ";
		  logger.info(method + " Inside...");
		  Set<JsonFraktKalkulatorIncotermsRecord> result = new HashSet<JsonFraktKalkulatorIncotermsRecord>();
		  //prepare the access CGI with RPG back-end
		  String BASE_URL = FraktKalkulatorUrlDataStore.FRAKTKALKULATOR_FETCH_DROPDOWN_INCOTERMS_DATA_URL;
		  StringBuffer urlRequestParamsKeys = new StringBuffer();
		  urlRequestParamsKeys.append("user=" + applicationUser + "&wsavd=" + wsavd);
		  if(avvknr!=null && !"".equals(avvknr)){
			  urlRequestParamsKeys.append("&avvknr=" + avvknr);
		  }
		  
		  logger.info("URL: " + BASE_URL);
		  logger.info("PARAMS: " + urlRequestParamsKeys);
		  logger.info(Calendar.getInstance().getTime() +  " CGI-start timestamp");
		  String jsonPayload = this.urlCgiProxyService.getJsonContent(BASE_URL, urlRequestParamsKeys.toString());
		  logger.info(jsonPayload);
		  logger.info(method + Calendar.getInstance().getTime() +  " CGI-end timestamp");
	    	  if(jsonPayload!=null){
	    		  JsonFraktKalkulatorDropDownContainer container = this.fraktKalkulatorDropDownService.getContainer(jsonPayload);
	    		  if(container!=null){
	    			  for(JsonFraktKalkulatorIncotermsRecord  record : container.getPricecalclist_levv()){
	    				  result.add(record);
	    			  }
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
			  sb.append( FraktKalkulatorConstants.URL_CHAR_DELIMETER_FOR_PARAMS_WITH_HTML_REQUEST + "sonavn=" + customerName );
			  sb.append( FraktKalkulatorConstants.URL_CHAR_DELIMETER_FOR_PARAMS_WITH_HTML_REQUEST + "knr=" + customerNumber );
		  }else if (customerName!=null && !"".equals(customerName)){
			  sb.append( FraktKalkulatorConstants.URL_CHAR_DELIMETER_FOR_PARAMS_WITH_HTML_REQUEST + "sonavn=" + customerName );
		  }else if (customerNumber!=null && !"".equals(customerNumber)){
			  sb.append( FraktKalkulatorConstants.URL_CHAR_DELIMETER_FOR_PARAMS_WITH_HTML_REQUEST + "knr=" + customerNumber );
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
	  
	  @Qualifier 
	  private FraktKalkulatorChildWindowsService fraktKalkulatorCustomerService;
	  @Autowired
	  @Required	
	  public void setFraktKalkulatorCustomerService(FraktKalkulatorChildWindowsService value){this.fraktKalkulatorCustomerService = value;}
	  public FraktKalkulatorChildWindowsService getFraktKalkulatorCustomerService(){ return this.fraktKalkulatorCustomerService; }
		
	  
	  @Qualifier 
	  private FraktKalkulatorDropDownService fraktKalkulatorDropDownService;
	  @Autowired
	  @Required	
	  public void setFraktKalkulatorDropDownService(FraktKalkulatorDropDownService value){this.fraktKalkulatorDropDownService = value;}
	  public FraktKalkulatorDropDownService getFraktKalkulatorDropDownService(){ return this.fraktKalkulatorDropDownService; }
			  
	  
}
