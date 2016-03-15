/**
 * 
 */
package no.systema.skat.nctsimport.controller.ajax;

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
import no.systema.main.service.UrlCgiProxyServiceImpl;

import no.systema.skat.model.jsonjackson.customer.JsonSkatCustomerRecord;
import no.systema.skat.nctsimport.model.jsonjackson.topic.JsonSkatNctsImportSpecificTopicContainer;
import no.systema.skat.nctsimport.model.jsonjackson.topic.JsonSkatNctsImportSpecificTopicRecord;


import no.systema.skat.nctsimport.model.jsonjackson.topic.items.JsonSkatNctsImportSpecificTopicItemContainer;
import no.systema.skat.nctsimport.model.jsonjackson.topic.items.JsonSkatNctsImportSpecificTopicItemRecord;
import no.systema.skat.nctsimport.model.jsonjackson.topic.unloading.items.JsonSkatNctsImportSpecificTopicUnloadingItemContainer;
import no.systema.skat.nctsimport.model.jsonjackson.topic.unloading.items.JsonSkatNctsImportSpecificTopicUnloadingItemRecord;
import no.systema.skat.model.jsonjackson.codes.JsonSkatTaricVarukodContainer;
import no.systema.skat.model.jsonjackson.codes.JsonSkatTaricVarukodRecord;
import no.systema.skat.service.SkatTaricVarukodService;

import no.systema.skat.nctsimport.service.SkatNctsImportSpecificTopicService;
import no.systema.skat.nctsimport.service.SkatNctsImportSpecificTopicItemService;
import no.systema.skat.nctsimport.service.SkatNctsImportSpecificTopicUnloadingItemService;
import no.systema.skat.nctsimport.url.store.SkatNctsImportUrlDataStore;
import no.systema.skat.url.store.SkatUrlDataStore;

import no.systema.skat.util.SkatConstants;

/**
 * This Ajax handler is the class handling ajax request in Skat-NctsImport. 
 * It will usually be called from within a jQuery function or other javascript alike... 
 * 
 * @author oscardelatorre
 * @date Apr 24, 2014
 * 
 */

@Controller
public class SkatNctsImportAjaxHandlerController {
	private static final Logger logger = Logger.getLogger(SkatNctsImportAjaxHandlerController.class.getName());
	 
	
	
	@RequestMapping(value = "getSpecificTopicUnloadingItemChosenFromGuiElement_SkatNctsImport.do", method = RequestMethod.GET)
	  public @ResponseBody Set<JsonSkatNctsImportSpecificTopicUnloadingItemRecord> getSpecificTopicUnloadingItemRecord
	  						(@RequestParam String applicationUser, @RequestParam String elementValue, 
	  						 @RequestParam String avd, @RequestParam String opd ) {
		 logger.info("Inside: getSpecificTopicUnloadingItemChosenFromGuiElement_SkatNctsImport()");
		 Set result = new HashSet();
		 //prepare the access CGI with RPG back-end
		 String BASE_URL = SkatNctsImportUrlDataStore.NCTS_IMPORT_BASE_FETCH_SPECIFIC_TOPIC_UNLOADING_ITEM_URL;
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
				 //in the future by orderList... We do that here and now. Same as for avd and opd (in order to comply with the model record attributes)
				 jsonPayload = jsonPayload.replaceFirst("avd", "tvavd");
				 jsonPayload = jsonPayload.replaceFirst("opd", "tvtdn");
				 jsonPayload = jsonPayload.replaceFirst("oneline", "orderList");
				 
				 logger.info(jsonPayload);
				 JsonSkatNctsImportSpecificTopicUnloadingItemContainer container = this.skatNctsImportSpecificTopicUnloadingItemService.getNctsImportSpecificTopicUnloadingItemContainer(jsonPayload);
				 if(container!=null){
					 for(JsonSkatNctsImportSpecificTopicUnloadingItemRecord  record : container.getOrderList()){
						 logger.info("Item Description: " + record.getNvvt());
						 logger.info("Item line nr: " + record.getTvli());
						 
						 //this.concatenateInformationFields(record);
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
	  * @param elementValue
	  * @param user
	  * @param avd
	  * @param opd
	  * @return
	  */
	 
	 @RequestMapping(value = "getSpecificTopicItemChosenFromGuiElement_SkatNctsImport.do", method = RequestMethod.GET)
	  public @ResponseBody Set<JsonSkatNctsImportSpecificTopicItemRecord> getSpecificTopicItemChosenFromGuiElement
	  						(@RequestParam String applicationUser, @RequestParam String elementValue, 
	  						 @RequestParam String avd, @RequestParam String opd ) {
		 logger.info("Inside: getSpecificTopicItemChosenFromGuiElement_SkatNctsImport()");
		 Set result = new HashSet();
		 //prepare the access CGI with RPG back-end
		 String BASE_URL = SkatNctsImportUrlDataStore.NCTS_IMPORT_BASE_FETCH_SPECIFIC_TOPIC_ITEM_URL;
		 String[] fields = elementValue.split("_");
		 String lineNr = null;
		 String varupostNr = null;
		 if(fields!=null && fields.length==3){
			 logger.info("FIELD: " + fields[0] + " " + fields[1] + " " + fields[2]);
			 lineNr = fields[1];
			 varupostNr = fields[2];
			 logger.info(applicationUser + "-" + elementValue + "-" + avd + "-" + opd + "- linenr:" + lineNr);
			 String urlRequestParamsKeys = this.getRequestUrlKeyParametersForItem(applicationUser, avd, opd, lineNr);
			 logger.info("URL: " + BASE_URL);
			 logger.info("PARAMS: " + urlRequestParamsKeys);
			 logger.info(Calendar.getInstance().getTime() +  " CGI-start timestamp");
			 String jsonPayload = this.urlCgiProxyService.getJsonContent(BASE_URL, urlRequestParamsKeys);
			 logger.info(Calendar.getInstance().getTime() +  " CGI-end timestamp");		
			 if(jsonPayload!=null){
				 //we must replace wrong name in order to use the same JSON item record. The RPG name "oneline" should be replaced (at the back end)
				 //in the future by orderList... We do that here and now. Same as for avd and opd (in order to comply with the model record attributes)
				 jsonPayload = jsonPayload.replaceFirst("avd", "tvavd");
				 jsonPayload = jsonPayload.replaceFirst("opd", "tvtdn");
				 jsonPayload = jsonPayload.replaceFirst("oneline", "orderList");
				 
				 logger.info(jsonPayload);
				 JsonSkatNctsImportSpecificTopicItemContainer container = this.skatNctsImportSpecificTopicItemService.getNctsImportSpecificTopicItemContainer(jsonPayload);
				 if(container!=null){
					 for(JsonSkatNctsImportSpecificTopicItemRecord  record : container.getOrderList()){
						 logger.info("Event Description: " + record.getTvinf1());
						 logger.info("Plats: " + record.getTvst());
						 this.concatenateInformationFields(record);
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
	  * The method is used for fetching default values on "Create new"
	  * It is triggered when the end user chooses a department (Avdelning)
	  * 
	  * @param applicationUser
	  * @param customerName
	  * @param customerNumber
	  * @return
	  */
	 
	 @RequestMapping(value = "initCreateNewTopic_SkatNctsImport.do", method = RequestMethod.GET)
	  public @ResponseBody Set<JsonSkatCustomerRecord> initCreateNewTopic(@RequestParam String applicationUser, @RequestParam String avd) {
		 
		 	String method = "NctsImportAjaxHandlerController.initCreateNewTopic_SkatNctsImport()";
		 	logger.info("Inside " + method);
		 	Set result = new HashSet();
		 	
		 	logger.info("FETCH record transaction...");
			//---------------------------
			//get BASE URL = RPG-PROGRAM
			//---------------------------
			String BASE_URL = SkatNctsImportUrlDataStore.NCTS_IMPORT_BASE_FETCH_SPECIFIC_TOPIC_URL;
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
	    		JsonSkatNctsImportSpecificTopicContainer container = this.skatNctsImportSpecificTopicService.getNctsImportSpecificTopicContainer(jsonPayload);
	    		if(container!=null){
	    			for(JsonSkatNctsImportSpecificTopicRecord  record : container.getOneorder()){
	    				logger.info("Registreringsdatum: " + record.getTidt() );
	    				result.add(record);
	    			}
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
	  
	  @RequestMapping(value = "searchTaricVarukodNcts_SkatNctsImport.do", method = RequestMethod.GET)
	  public @ResponseBody Set<JsonSkatTaricVarukodRecord> getTaricVarukodNcts(@RequestParam String applicationUser, @RequestParam String taricVarukod) {
		  logger.info("Inside method: getTaricVarukodNcts()");
		  Set result = new HashSet();
		  String EXPORT_IE = "I";//always I since we are in IMPORT mode
		  
		  try{
			  String BASE_URL = SkatUrlDataStore.SKAT_FETCH_TARIC_VARUKODER_ITEMS_URL;
			  String urlRequestParamsKeys = "user=" + applicationUser + "&ie=" + EXPORT_IE + "&kod=" + taricVarukod;
			  UrlCgiProxyService urlCgiProxyService = new UrlCgiProxyServiceImpl();
			  String jsonPayload = urlCgiProxyService.getJsonContent(BASE_URL, urlRequestParamsKeys);
			  JsonSkatTaricVarukodContainer container = this.skatTaricVarukodService.getContainer(jsonPayload);
			  for(JsonSkatTaricVarukodRecord record : container.getTariclist()){
				  //logger.info("02:" + record.getDktara02());
				  //logger.info("63:" + record.getDktara63());
				  //logger.info("64:" + record.getDktara64());
				  
				  result.add(record);
			  }	
		  }catch(Exception e){
			  e.printStackTrace();
		  }
		  
		  return result;
	  }
	
	  
	 /**
	  * 
	  * @param record
	  */
	 private void concatenateInformationFields(JsonSkatNctsImportSpecificTopicItemRecord record){
		 String inf = null;
		 
		 //record.setTvinf(value)
		 if(record.getTvinf1()!=null && !"".equals(record.getTvinf1())){
			 inf = record.getTvinf1();
		 }
		 if(record.getTvinf2()!=null && !"".equals(record.getTvinf2())){
			 inf += " " + record.getTvinf2();
		 }
		 if(record.getTvinf3()!=null && !"".equals(record.getTvinf3())){
			 inf += " " + record.getTvinf3();
		 }
		 if(record.getTvinf4()!=null && !"".equals(record.getTvinf4())){
			 inf += " " + record.getTvinf4();
		 }
		 
		 record.setTvinf(inf);
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

	    
	  @Qualifier ("skatNctsImportSpecificTopicService")
	  private SkatNctsImportSpecificTopicService skatNctsImportSpecificTopicService;
	  @Autowired
	  @Required
	  public void setSkatNctsImportSpecificTopicService (SkatNctsImportSpecificTopicService value){ this.skatNctsImportSpecificTopicService = value; }
	  public SkatNctsImportSpecificTopicService getSkatNctsImportSpecificTopicService(){ return this.skatNctsImportSpecificTopicService; }
	  
	  
	  @Qualifier ("skatNctsImportSpecificTopicItemService")
	  private SkatNctsImportSpecificTopicItemService skatNctsImportSpecificTopicItemService;
	  @Autowired
	  @Required	
	  public void setSkatNctsImportSpecificTopicItemService(SkatNctsImportSpecificTopicItemService value){this.skatNctsImportSpecificTopicItemService = value;}
	  public SkatNctsImportSpecificTopicItemService getSkatNctsImportSpecificTopicItemService(){ return this.skatNctsImportSpecificTopicItemService; }
	  
	  
	  @Qualifier ("skatNctsImportSpecificTopicUnloadingItemService")
	  private SkatNctsImportSpecificTopicUnloadingItemService skatNctsImportSpecificTopicUnloadingItemService;
	  @Autowired
	  @Required	
	  public void setSkatNctsImportSpecificTopicUnloadingItemService(SkatNctsImportSpecificTopicUnloadingItemService value){this.skatNctsImportSpecificTopicUnloadingItemService = value;}
	  public SkatNctsImportSpecificTopicUnloadingItemService getSkatNctsImportSpecificTopicUnloadingItemService(){ return this.skatNctsImportSpecificTopicUnloadingItemService; }
	  
	  
	  @Qualifier ("skatTaricVarukodService")
	  private SkatTaricVarukodService skatTaricVarukodService;
	  @Autowired
	  @Required	
	  public void setSkatTaricVarukodService(SkatTaricVarukodService value){this.skatTaricVarukodService = value;}
	  public SkatTaricVarukodService getSkatTaricVarukodService(){ return this.skatTaricVarukodService; }
	  
	  
	  
		
}
