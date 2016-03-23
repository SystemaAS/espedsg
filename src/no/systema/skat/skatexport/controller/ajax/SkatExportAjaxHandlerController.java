/**
 * 
 */
package no.systema.skat.skatexport.controller.ajax;

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
import no.systema.main.util.DateTimeManager;

//SKAT
//import no.systema.skat.skatimport.util.manager.TollvaerdideklarationMgr;
//import no.systema.skat.skatimport.model.jsonjackson.topic.items.JsonSkatImportSpecificTopicItemToldvaerdiRecord;
//import no.systema.skat.service.SkatTaricVarukodService;

import no.systema.skat.model.jsonjackson.codes.JsonSkatTaricVarukodContainer;
import no.systema.skat.model.jsonjackson.codes.JsonSkatTaricVarukodRecord;
import no.systema.skat.service.SkatTaricVarukodService;
import no.systema.skat.url.store.SkatUrlDataStore;
import no.systema.skat.util.SkatConstants;

//EXPORT
import no.systema.skat.skatexport.util.SkatExportTweaker;
import no.systema.skat.skatexport.model.jsonjackson.topic.JsonSkatExportSpecificTopicContainer;
import no.systema.skat.skatexport.model.jsonjackson.topic.JsonSkatExportSpecificTopicRecord;
import no.systema.skat.skatexport.model.jsonjackson.topic.JsonSkatExportSpecificTopicOmbudContainer;
import no.systema.skat.skatexport.model.jsonjackson.topic.JsonSkatExportSpecificTopicOmbudRecord;
import no.systema.skat.skatexport.model.jsonjackson.topic.items.JsonSkatExportSpecificTopicItemAvgifterRecord;
import no.systema.skat.skatexport.model.jsonjackson.topic.items.JsonSkatExportSpecificTopicItemContainer;
import no.systema.skat.skatexport.model.jsonjackson.topic.items.JsonSkatExportSpecificTopicItemRecord;
import no.systema.skat.skatexport.service.SkatExportSpecificTopicService;
import no.systema.skat.skatexport.service.SkatExportSpecificTopicItemService;
import no.systema.skat.skatexport.url.store.SkatExportUrlDataStore;
import no.systema.skat.skatexport.util.manager.AvgiftsberakningenMgr;

/**
 * This Ajax handler is the class handling ajax request in SkatImport. 
 * It will usually be called from within a jQuery function or other javascript alike... 
 * 
 * @author oscardelatorre
 * @date Jan 29, 2014
 * 
 */

@Controller

public class SkatExportAjaxHandlerController {
	private static final Logger logger = Logger.getLogger(SkatExportAjaxHandlerController.class.getName());
	private SkatExportTweaker skatExportTweaker = new SkatExportTweaker();
	 
	/**
	 * Fetches the dep. information (Ombud)
	 * 
	 * @param applicationUser
	 * @param avd
	 * @return
	 */
	
	 @RequestMapping(value = "getSpecificTopicOmbud_SkatExport.do", method = RequestMethod.GET)
	  public @ResponseBody Set<JsonSkatExportSpecificTopicOmbudRecord> getSpecificTopicOmbud
	  						(@RequestParam String applicationUser, @RequestParam String avd) {
		 logger.info("Inside: getSpecificTopicOmbud_SkatExport.do");
		 Set result = new HashSet();
		 //prepare the access CGI with RPG back-end
		 String BASE_URL = SkatExportUrlDataStore.SKAT_EXPORT_BASE_FETCH_OMBUD_DEFAULT_DATA_URL;
		 String urlRequestParamsKeys = "user=" + applicationUser + "&avd=" + avd;
		 logger.info("URL: " + BASE_URL);
		 logger.info("PARAMS: " + urlRequestParamsKeys);
		 logger.info(Calendar.getInstance().getTime() +  " CGI-start timestamp");
		 String jsonPayload = this.urlCgiProxyService.getJsonContent(BASE_URL, urlRequestParamsKeys);
		 logger.info(Calendar.getInstance().getTime() +  " CGI-end timestamp");
		 logger.info(jsonPayload);
		 if(jsonPayload!=null){
				 JsonSkatExportSpecificTopicOmbudContainer container = this.skatExportSpecificTopicService.getSkatExportSpecificTopicOmbudContainer(jsonPayload);
				 if(container!=null){
					 for(JsonSkatExportSpecificTopicOmbudRecord  record : container.getGetdepinf()){
						 logger.info("#### dkea_14a:" + record.getDkea_14a());
						 result.add(record);
					 }
				 }
			 }
		 
		 return result;
	 }
	 
	
	/**
	 * 
	 * @param applicationUser
	 * @param elementValue
	 * @param avd
	 * @param opd
	 * @return
	 */
	
	@RequestMapping(value = "getSpecificTopicItemChosenFromGuiElement_SkatExport.do", method = RequestMethod.GET)
	public @ResponseBody Set<JsonSkatExportSpecificTopicItemRecord> getSpecificTopicItemChosenFromHtmlList
	  						(@RequestParam String applicationUser, @RequestParam String elementValue, 
	  						 @RequestParam String avd, @RequestParam String opd ) {
		 logger.info("Inside: getSpecificTopicItemChosenFromGuiElement_SkatExport()");
		 Set result = new HashSet();
		 //prepare the access CGI with RPG back-end
		 String BASE_URL = SkatExportUrlDataStore.SKAT_EXPORT_BASE_FETCH_SPECIFIC_TOPIC_ITEM_URL;
		 String[] fields = elementValue.split("_");
		 String lineNr = null;
		 String lineId = null;
		 if(fields!=null && fields.length==3){
			 logger.info("FIELD: " + fields[0] + " " + fields[1] + " " + fields[2]);
			 lineId = fields[1];
			 lineNr = fields[2];
			 logger.info(applicationUser + "-" + elementValue + "-" + avd + "-" + opd + "- linenr:" + lineNr);
			//When lineId != lineNr use lineId...(Varupostnr)
			 if(!lineId.equals(lineNr)){lineNr = lineId;}
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
				 JsonSkatExportSpecificTopicItemContainer container = this.skatExportSpecificTopicItemService.getSkatExportSpecificTopicItemContainer(jsonPayload);
				 
				 if(container!=null){
					 for(JsonSkatExportSpecificTopicItemRecord  record : container.getOrderList()){
						 //we must tweak this in order to present the correct value at the GUI
						 record.setDkev_402a(this.skatExportTweaker.setSummariskAngivelse_402a(record));
						 record.setDkev_402b(this.skatExportTweaker.setSummariskAngivelse_402b(record));
						 record.setDkev_402c(this.skatExportTweaker.setSummariskAngivelse_402c(record));
						 record.setDkev_402d(this.skatExportTweaker.setSummariskAngivelse_402d(record));
						 record.setDkev_402e(this.skatExportTweaker.setSummariskAngivelse_402e(record));
						 record.setDkev_402f(this.skatExportTweaker.setSummariskAngivelse_402f(record));
						 record.setDkev_402g(this.skatExportTweaker.setSummariskAngivelse_402g(record));
						 record.setDkev_402h(this.skatExportTweaker.setSummariskAngivelse_402h(record));
						 record.setDkev_402i(this.skatExportTweaker.setSummariskAngivelse_402i(record));
						 record.setDkev_402j(this.skatExportTweaker.setSummariskAngivelse_402j(record));
						 record.setDkev_402k(this.skatExportTweaker.setSummariskAngivelse_402k(record));
						 record.setDkev_402l(this.skatExportTweaker.setSummariskAngivelse_402l(record));
						 record.setDkev_402m(this.skatExportTweaker.setSummariskAngivelse_402m(record));
						 record.setDkev_402n(this.skatExportTweaker.setSummariskAngivelse_402n(record));
						 record.setDkev_402o(this.skatExportTweaker.setSummariskAngivelse_402o(record));
						 record.setDkev_402p(this.skatExportTweaker.setSummariskAngivelse_402p(record));
						 record.setDkev_402q(this.skatExportTweaker.setSummariskAngivelse_402q(record));
						 record.setDkev_402r(this.skatExportTweaker.setSummariskAngivelse_402r(record));
						 record.setDkev_402s(this.skatExportTweaker.setSummariskAngivelse_402s(record));
						 
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
	   * @param taricVarukod
	   * @return
	   */
	  @RequestMapping(value = "searchTaricVarukod_SkatExport.do", method = RequestMethod.GET)
	  public @ResponseBody Set<JsonSkatTaricVarukodRecord> getTaricVarukod(@RequestParam String applicationUser, @RequestParam String taricVarukod) {
		  logger.info("Inside getTaricVarukod()");
		  Set result = new HashSet();
		  String TYPE_IE = "E";//
		  
		  try{
			  String BASE_URL = SkatUrlDataStore.SKAT_FETCH_TARIC_VARUKODER_ITEMS_URL;
			  String urlRequestParamsKeys = "user=" + applicationUser + "&ie=" + TYPE_IE + "&kod=" + taricVarukod;
			  UrlCgiProxyService urlCgiProxyService = new UrlCgiProxyServiceImpl();
			  String jsonPayload = urlCgiProxyService.getJsonContent(BASE_URL, urlRequestParamsKeys);
			  JsonSkatTaricVarukodContainer container = this.skatTaricVarukodService.getContainer(jsonPayload);
			  for(JsonSkatTaricVarukodRecord record : container.getTariclist()){
				  //logger.info("dktara02:" + record.getDktara02() + " -->" + "dktara64:" + record.getDktara64());
				  //logger.info("dktara63:" + record.getDktara63());
				  //logger.info("");
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
	   * @param svihStr
	   * @param svivStr
	   * @return
	   */
	  
	  @RequestMapping(value = "calculateStatistisktVarde_SkatExport.do", method = RequestMethod.GET)
	  public @ResponseBody Set<JsonSkatExportSpecificTopicItemRecord> calculateStatistisktVarde(@RequestParam String applicationUser, @RequestParam String dkehStr, @RequestParam String dkevStr) {
		  logger.info("Inside: calculateStatistisktVarde_SkatExport()");
		  Set result = new HashSet();
		  try{
			  
			  AvgiftsberakningenMgr avgiftsMgr = new AvgiftsberakningenMgr(this.skatExportSpecificTopicItemService, this.urlCgiProxyService);
			  String urlRequestParams = dkehStr + dkevStr;
			  JsonSkatExportSpecificTopicItemAvgifterRecord itemAvgiftsRecord = avgiftsMgr.calculateChargesOnItem(applicationUser, urlRequestParams);
			  if(itemAvgiftsRecord!=null){
				  result.add(itemAvgiftsRecord);
				  logger.info("Dkev_46: " + itemAvgiftsRecord.getDkev_46());
			  }else{
				  logger.info("[ERROR] NULL object on return from avgiftsMgr... ? " );
				  
			  }
			  
		  }catch(Exception e){
			  e.printStackTrace();
			  
		  }
		  
		  return result;
	  }
	  
	  /**
	   * 
	   * @param applicationUser
	   * @param currencyCode
	   * @param isoDate
	   * @return
	   */
	  @RequestMapping(value = "getCurrencyRate_SkatExport.do", method = RequestMethod.GET)
	  public @ResponseBody Set getCurrencyRate(@RequestParam String applicationUser, @RequestParam String currencyCode, @RequestParam String isoDate) {
		  final String METHOD = "[DEBUG] method:getCurrencyRate_SkatExport "; 
		  logger.info("Inside " + METHOD);
		  Set result = new HashSet();
		  String validDate = this.getValidCurrencyDate(isoDate);
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
				  logger.info(METHOD + "Currency RATE: " + record.getSvvk_krs() + " " + record.getDkvk_krs());
				  logger.info(METHOD + "Currency FACTOR: " + record.getSvvs_omr() + " " + record.getDkvs_omr());
				  result.add(record);
			  }
		  } 
		  return result;
	  }
	  
	  /**
	   * 
	   * @param applicationUser
	   * @param avd
	   * @param opd
	   * @return
	   */
	  @RequestMapping(value = "getSpecificTopic_SkatExport.do", method = RequestMethod.GET)
	  public @ResponseBody Set<JsonSkatExportSpecificTopicRecord> getSpecificTopic (@RequestParam String applicationUser, @RequestParam String avd, @RequestParam String opd) {
		 logger.info("Inside: getSpecificTopic_SkatExport.do");
		 Set result = new HashSet();
		 String BASE_URL = SkatExportUrlDataStore.SKAT_EXPORT_BASE_FETCH_SPECIFIC_TOPIC_URL;
		 //url params
	 	 String urlRequestParamsKeys = "user=" + applicationUser + "&avd=" + avd + "&opd=" + opd;
		 //for debug purposes in GUI
		 
		 logger.info(Calendar.getInstance().getTime() + " CGI-start timestamp");
    	 logger.info("URL: " + BASE_URL);
    	 logger.info("URL PARAMS: " + urlRequestParamsKeys);
    	 //--------------------------------------
    	 //EXECUTE the FETCH (RPG program) here
    	 //--------------------------------------
    	 String jsonPayload = this.urlCgiProxyService.getJsonContent(BASE_URL, urlRequestParamsKeys);
		 //Debug --> 
    	 logger.info(Calendar.getInstance().getTime() +  " CGI-end timestamp");
    	 if(jsonPayload!=null){
    		JsonSkatExportSpecificTopicContainer container = this.skatExportSpecificTopicService.getSkatExportSpecificTopicContainer(jsonPayload);
    		if(container!=null && container.getOneorder()!=null){
    			for(JsonSkatExportSpecificTopicRecord record : container.getOneorder()){
  				  //Debug
  				  logger.info("Avs:" + record.getDkeh_02b());
  				  //logger.info(record.getDkeh_08b());
  				  result.add(record);
  			  	}
    		}
    	 }
		 return result;
	 }
	  /**
	   * 
	   * @param isoDate
	   * @return
	   */
	  private String getValidCurrencyDate (String isoDate){
		  String retval = null;
		  if (isoDate!=null && !"".equals(isoDate)){
			  if(isoDate.length()>8){
				  retval = isoDate.substring(0,8);
			  }
		  }else{
			  DateTimeManager mgr = new DateTimeManager();
			  retval = mgr.getCurrentDate_ISO();
		  }
		  return retval;
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
	  	
	  //SERVICES
	  @Qualifier ("urlCgiProxyService")
	  private UrlCgiProxyService urlCgiProxyService;
	  @Autowired
	  @Required
	  public void setUrlCgiProxyService (UrlCgiProxyService value){ this.urlCgiProxyService = value; }
	  public UrlCgiProxyService getUrlCgiProxyService(){ return this.urlCgiProxyService; }
	  
	   
	  @Qualifier 
	  private SkatExportSpecificTopicItemService skatExportSpecificTopicItemService;
	  @Autowired
	  @Required	
	  public void setSkatExportSpecificTopicItemService(SkatExportSpecificTopicItemService value){this.skatExportSpecificTopicItemService = value;}
	  public SkatExportSpecificTopicItemService getSkatImportSpecificTopicItemService(){ return this.skatExportSpecificTopicItemService; }
	  
	  
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
		
	  
	  @Qualifier ("skatExportSpecificTopicService")
	  private SkatExportSpecificTopicService skatExportSpecificTopicService;
	  @Autowired
	  @Required
	  public void setSkatExportSpecificTopicService (SkatExportSpecificTopicService value){ this.skatExportSpecificTopicService = value; }
	  public SkatExportSpecificTopicService getSkatExportSpecificTopicService(){ return this.skatExportSpecificTopicService; }
	  
	  /*
	  @Qualifier
	  private TdsImportTullkontorService tdsImportTullkontorService;
	  @Autowired
	  @Required	
	  public void setTdsImportTullkontorService(TdsImportTullkontorService value){this.tdsImportTullkontorService = value;}
	  public TdsImportTullkontorService getTdsImportTullkontorService(){ return this.tdsImportTullkontorService; }
	   */
	  
	  
	  /*
	  @Qualifier 
	  private TdsSignatureNameService tdsSignatureNameService;
	  @Autowired
	  @Required	
	  public void setTdsSignatureNameService(TdsSignatureNameService value){this.tdsSignatureNameService = value;}
	  public TdsSignatureNameService getTdsSignatureNameService(){ return this.tdsSignatureNameService; }
	  */
		
}
