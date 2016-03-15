/**
 * 
 */
package no.systema.overview.sendingerlevtid.controller.ajax;

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
import no.systema.main.service.UrlCgiProxyService;


import no.systema.overview.sendingerlevtid.model.jsonjackson.carrier.JsonSendingerlevtidCarrierContainer;
import no.systema.overview.sendingerlevtid.model.jsonjackson.carrier.JsonSendingerlevtidCarrierRecord;
import no.systema.overview.sendingerlevtid.service.SendingerlevtidService;
import no.systema.overview.sendingerlevtid.service.SendingerlevtidCarrierService;
import no.systema.overview.sendingerlevtid.util.SendingerlevtidConstants;
import no.systema.overview.sendingerlevtid.url.store.UrlDataStore;


//this is in order to use the search customer routine
import no.systema.tds.tdsexport.model.jsonjackson.customer.JsonTdsExportCustomerContainer;
import no.systema.tds.tdsexport.model.jsonjackson.customer.JsonTdsExportCustomerRecord;
import no.systema.tds.tdsexport.service.TdsExportCustomerService;
import no.systema.tds.url.store.TdsUrlDataStore;
import no.systema.tds.util.TdsConstants;


/**
 * This Ajax handler is the class handling ajax request in SendingerLevtid. 
 * It will usually be called from within a jQuery function or other javascript alike... 
 * 
 * @author oscardelatorre
 * @date Oct 16, 2013
 * 
 */

@Controller
public class SendingerLevtidAjaxHandlerController {
	private static final Logger logger = Logger.getLogger(SendingerLevtidAjaxHandlerController.class.getName());
	 
	  /**
	   * Populates the GUI element with a list of carriers fulfilling the search condition
	   * @param carrierName
	   * @param carrierNumber
	   * @return
	   */
	  @RequestMapping(value = "searchCarrier_SendingerLevtid.do", method = {RequestMethod.GET, RequestMethod.POST})
	  public @ResponseBody Set<JsonSendingerlevtidCarrierRecord> searchCarrier(@RequestParam String applicationUser, @RequestParam String carrierName, @RequestParam String carrierNumber) {
		  logger.info("Inside SendingerLevtidAjaxHandlerController.searchCarrier()");
		  Set result = new HashSet();
		  //prepare the access CGI with RPG back-end
		  String BASE_URL = UrlDataStore.OVERVIEW_SENDINGER_LEVTID_FETCH_CARRIER_URL;
		  String urlRequestParamsKeys = this.getRequestUrlKeyParametersForSearchCarrier(applicationUser, carrierName, carrierNumber);
		  logger.info("URL: " + BASE_URL);
		  logger.info("PARAMS: " + urlRequestParamsKeys);
		  logger.info(Calendar.getInstance().getTime() +  " CGI-start timestamp");
		  String jsonPayload = this.urlCgiProxyService.getJsonContent(BASE_URL, urlRequestParamsKeys);
		  logger.info(jsonPayload);
		  logger.info(Calendar.getInstance().getTime() +  " CGI-end timestamp");
		  
		  if(jsonPayload!=null){
			  JsonSendingerlevtidCarrierContainer container = this.sendingerlevtidCarrierService.getContainer(jsonPayload);
		    		if(container!=null){
		    			for(JsonSendingerlevtidCarrierRecord  record : container.getTransporterlist()){
		    				logger.info("CARRIER via AJAX: " + record.getTnavn() + " TranNr:" + record.getTranNr());
		    				result.add(record);
		    			}
		    		}
		  }
		  return result;
		  
	  }
	  
	  /**
	   * 
	   * @param applicationUser
	   * @param customerName
	   * @param customerNumber
	   * @return
	   */
	  @RequestMapping(value = "searchCustomer_SendingerLevtid.do", method = {RequestMethod.GET, RequestMethod.POST})
	  public @ResponseBody Set<JsonTdsExportCustomerRecord> searchCustomer(@RequestParam String applicationUser, @RequestParam String customerName, @RequestParam String customerNumber) {
		  logger.info("Inside SendingerLevtidAjaxHandlerController.searchCustomer()");
		  Set result = new HashSet();
		  //prepare the access CGI with RPG back-end
		  String BASE_URL = TdsUrlDataStore.TDS_FETCH_CUSTOMER_URL;
		  String urlRequestParamsKeys = this.getRequestUrlKeyParametersForSearchCustomer(applicationUser, customerName, customerNumber);
		  logger.info("URL: " + BASE_URL);
		  logger.info("PARAMS: " + urlRequestParamsKeys);
		  logger.info(Calendar.getInstance().getTime() +  " CGI-start timestamp");
		  String jsonPayload = this.urlCgiProxyService.getJsonContent(BASE_URL, urlRequestParamsKeys);
		  logger.info(jsonPayload);
		  logger.info(Calendar.getInstance().getTime() +  " CGI-end timestamp");
		  
		  if(jsonPayload!=null){
		    		jsonPayload = jsonPayload.replaceFirst("Customerlist", "customerlist");
	  		  	JsonTdsExportCustomerContainer container = this.tdsExportCustomerService.getTdsExportCustomerContainer(jsonPayload);
		    		if(container!=null){
		    			for(JsonTdsExportCustomerRecord  record : container.getCustomerlist()){
		    				logger.info("CUSTOMER via AJAX: " + record.getKnavn() + " NUMBER:" + record.getKundnr());
		    				logger.info("KPERS: " + record.getKpers() + " TLF:" + record.getTlf());
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
	  private String getRequestUrlKeyParametersForSearchCarrier(String applicationUser, String carrierName, String carrierNumber){
		  StringBuffer sb = new StringBuffer();
		  sb.append("user=" + applicationUser);
		  if(carrierName!=null && !"".equals(carrierName) && carrierNumber!=null && !"".equals(carrierNumber)){
			  sb.append( SendingerlevtidConstants.URL_CHAR_DELIMETER_FOR_PARAMS_WITH_HTML_REQUEST + "sonavn=" + carrierNumber );
			  sb.append( SendingerlevtidConstants.URL_CHAR_DELIMETER_FOR_PARAMS_WITH_HTML_REQUEST + "tnr=" + carrierNumber );
		  }else if (carrierName!=null && !"".equals(carrierName)){
			  sb.append( SendingerlevtidConstants.URL_CHAR_DELIMETER_FOR_PARAMS_WITH_HTML_REQUEST + "sonavn=" + carrierName );
		  }else if (carrierNumber!=null && !"".equals(carrierNumber)){
			  sb.append( SendingerlevtidConstants.URL_CHAR_DELIMETER_FOR_PARAMS_WITH_HTML_REQUEST + "tnr=" + carrierNumber );
		  }
		  
		  return sb.toString();
	  }
	  	
	  /**
	   * 
	   * @param applicationUser
	   * @param customerName
	   * @param customerNumber
	   * @return
	   */
	  private String getRequestUrlKeyParametersForSearchCustomer(String applicationUser, String customerName, String customerNumber){
		  StringBuffer sb = new StringBuffer();
		  sb.append("user=" + applicationUser);
		  if(customerName!=null && !"".equals(customerName) && customerNumber!=null && !"".equals(customerNumber)){
			  sb.append( TdsConstants.URL_CHAR_DELIMETER_FOR_PARAMS_WITH_HTML_REQUEST + "sonavn=" + customerName );
			  sb.append( TdsConstants.URL_CHAR_DELIMETER_FOR_PARAMS_WITH_HTML_REQUEST + "knr=" + customerNumber );
		  }else if (customerName!=null && !"".equals(customerName)){
			  sb.append( TdsConstants.URL_CHAR_DELIMETER_FOR_PARAMS_WITH_HTML_REQUEST + "sonavn=" + customerName );
		  }else if (customerNumber!=null && !"".equals(customerNumber)){
			  sb.append( TdsConstants.URL_CHAR_DELIMETER_FOR_PARAMS_WITH_HTML_REQUEST + "knr=" + customerNumber );
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
	  private TdsExportCustomerService tdsExportCustomerService;
	  @Autowired
	  @Required	
	  public void setTdsExportCustomerService(TdsExportCustomerService value){this.tdsExportCustomerService = value;}
	  public TdsExportCustomerService getTdsExportCustomerService(){ return this.tdsExportCustomerService; }
	  
	  
	  
	  @Qualifier ("sendingerlevtidService")
	  private SendingerlevtidService sendingerlevtidService;
	  @Autowired
	  @Required
	  public void setSendingerlevtidService (SendingerlevtidService value) { this.sendingerlevtidService = value; }
	  public SendingerlevtidService getSendingerlevtidService(){ return this.sendingerlevtidService; }
	
	  
	  @Qualifier ("sendingerlevtidCarrierService")
	  private SendingerlevtidCarrierService sendingerlevtidCarrierService;
	  @Autowired
	  @Required
	  public void setSendingerlevtidCarrierService (SendingerlevtidCarrierService value) { this.sendingerlevtidCarrierService = value; }
	  public SendingerlevtidCarrierService getSendingerlevtidCarrierService(){ return this.sendingerlevtidCarrierService; }
	
	  
	  
		
}
