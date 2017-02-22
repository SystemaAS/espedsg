/**
 * 
 */
package no.systema.skat.skatimport.controller.ajax;

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
import no.systema.skat.service.SkatTaricVarukodService;
import no.systema.skat.url.store.SkatUrlDataStore;
import no.systema.skat.util.SkatConstants;
import no.systema.skat.model.jsonjackson.codes.JsonSkatTaricVarukodContainer;
import no.systema.skat.model.jsonjackson.codes.JsonSkatTaricVarukodRecord;


import no.systema.skat.skatimport.model.jsonjackson.topic.JsonSkatImportTopicInvoiceExternalForUpdateContainer;
import no.systema.skat.skatimport.model.jsonjackson.topic.JsonSkatImportTopicInvoiceContainer;
import no.systema.skat.skatimport.model.jsonjackson.topic.JsonSkatImportTopicInvoiceRecord;
import no.systema.skat.skatimport.url.store.SkatImportUrlDataStore;
import no.systema.skat.skatimport.model.jsonjackson.topic.items.JsonSkatImportSpecificTopicItemCertificateNrAndCodeR442Container;
import no.systema.skat.skatimport.model.jsonjackson.topic.items.JsonSkatImportSpecificTopicItemCertificateNrAndCodeR442Record;
import no.systema.skat.skatimport.model.jsonjackson.topic.items.JsonSkatImportSpecificTopicItemContainer;
import no.systema.skat.skatimport.model.jsonjackson.topic.items.JsonSkatImportSpecificTopicItemRecord;
import no.systema.skat.skatimport.model.jsonjackson.topic.JsonSkatImportSpecificTopicOmbudContainer;
import no.systema.skat.skatimport.model.jsonjackson.topic.JsonSkatImportSpecificTopicOmbudRecord;
import no.systema.skat.skatimport.model.jsonjackson.topic.items.JsonSkatImportSpecificTopicItemToldvaerdiRecord;
import no.systema.skat.skatimport.service.SkatImportSpecificTopicService;
import no.systema.skat.skatimport.service.SkatImportSpecificTopicItemService;
import no.systema.skat.skatimport.util.SkatImportTweaker;
import no.systema.skat.skatimport.util.manager.TollvaerdideklarationMgr;



/**
 * This Ajax handler is the class handling ajax request in SkatImport. 
 * It will usually be called from within a jQuery function or other javascript alike... 
 * 
 * @author oscardelatorre
 * @date Jan 29, 2014
 * 
 */

@Controller

public class SkatImportAjaxHandlerController {
	private static final Logger logger = Logger.getLogger(SkatImportAjaxHandlerController.class.getName());
	private SkatImportTweaker skatImportTweaker = new SkatImportTweaker();
	 
	/**
	 * Gets the signature name (person)
	 * 
	 * @param applicationUser
	 * @param avd
	 * @param sign
	 * @return
	 */
	
	/**
	 * Fetches the dep. information (Ombud)
	 * 
	 * @param applicationUser
	 * @param avd
	 * @return
	 */
	
	 @RequestMapping(value = "getSpecificTopicOmbud_SkatImport.do", method = RequestMethod.GET)
	  public @ResponseBody Set<JsonSkatImportSpecificTopicOmbudRecord> getSpecificTopicOmbud
	  						(@RequestParam String applicationUser, @RequestParam String avd) {
		 logger.info("Inside: getSpecificTopicOmbud_SkatImport.do");
		 Set result = new HashSet();
		 //prepare the access CGI with RPG back-end
		 String BASE_URL = SkatImportUrlDataStore.SKAT_IMPORT_BASE_FETCH_OMBUD_DEFAULT_DATA_URL;
		 String urlRequestParamsKeys = "user=" + applicationUser + "&avd=" + avd;
		 logger.info("URL: " + BASE_URL);
		 logger.info("PARAMS: " + urlRequestParamsKeys);
		 logger.info(Calendar.getInstance().getTime() +  " CGI-start timestamp");
		 String jsonPayload = this.urlCgiProxyService.getJsonContent(BASE_URL, urlRequestParamsKeys);
		 logger.info(Calendar.getInstance().getTime() +  " CGI-end timestamp");
		 logger.info(jsonPayload);
		 if(jsonPayload!=null){
				 JsonSkatImportSpecificTopicOmbudContainer container = this.skatImportSpecificTopicService.getSkatImportSpecificTopicOmbudContainer(jsonPayload);
				 if(container!=null){
					 for(JsonSkatImportSpecificTopicOmbudRecord  record : container.getGetdepinf()){
						 //logger.info("#### DKIA_14a:" + record.getDkia_14a());
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
	@RequestMapping(value = "getSpecificTopicItemChosenFromGuiElement_SkatImport.do", method = RequestMethod.GET)
	public @ResponseBody Set<JsonSkatImportSpecificTopicItemRecord> getSpecificTopicItemChosenFromHtmlList
	  						(@RequestParam String applicationUser, @RequestParam String elementValue, 
	  						 @RequestParam String avd, @RequestParam String opd ) {
		 logger.info("Inside: getSpecificTopicItemChosenFromGuiElement_SkatImport()");
		 Set result = new HashSet();
		 //prepare the access CGI with RPG back-end
		 String BASE_URL = SkatImportUrlDataStore.SKAT_IMPORT_BASE_FETCH_SPECIFIC_TOPIC_ITEM_URL;
		 String[] fields = elementValue.split("_");
		 String lineNr = null;
		 String lineId = null;
		 if(fields!=null && fields.length==3){
			 logger.info("FIELD: " + fields[0] + " " + fields[1] + " " + fields[2]);
			 lineId = fields[1];
			 lineNr = fields[2];
			 //When lineId != lineNr use lineId (line-counter)
			 if(!lineId.equals(lineNr)){lineNr = lineId;}
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
				 JsonSkatImportSpecificTopicItemContainer container = this.skatImportSpecificTopicItemService.getSkatImportSpecificTopicItemContainer(jsonPayload);
				 if(container!=null){
					 for(JsonSkatImportSpecificTopicItemRecord  record : container.getOrderList()){
						 //we must tweak this in order to present the correct value at the GUI
						 record.setDkiv_402a(this.skatImportTweaker.setSummariskAngivelse_402a(record));
						 record.setDkiv_402b(this.skatImportTweaker.setSummariskAngivelse_402b(record));
						 record.setDkiv_402c(this.skatImportTweaker.setSummariskAngivelse_402c(record));
						 record.setDkiv_402d(this.skatImportTweaker.setSummariskAngivelse_402d(record));
						 record.setDkiv_402e(this.skatImportTweaker.setSummariskAngivelse_402e(record));
						 record.setDkiv_402f(this.skatImportTweaker.setSummariskAngivelse_402f(record));
						 record.setDkiv_402g(this.skatImportTweaker.setSummariskAngivelse_402g(record));
						 record.setDkiv_402h(this.skatImportTweaker.setSummariskAngivelse_402h(record));
						 record.setDkiv_402i(this.skatImportTweaker.setSummariskAngivelse_402i(record));
						 record.setDkiv_402j(this.skatImportTweaker.setSummariskAngivelse_402j(record));
						 record.setDkiv_402k(this.skatImportTweaker.setSummariskAngivelse_402k(record));
						 record.setDkiv_402l(this.skatImportTweaker.setSummariskAngivelse_402l(record));
						 record.setDkiv_402m(this.skatImportTweaker.setSummariskAngivelse_402m(record));
						 record.setDkiv_402n(this.skatImportTweaker.setSummariskAngivelse_402n(record));
						 record.setDkiv_402o(this.skatImportTweaker.setSummariskAngivelse_402o(record));
						 record.setDkiv_402p(this.skatImportTweaker.setSummariskAngivelse_402p(record));
						 record.setDkiv_402q(this.skatImportTweaker.setSummariskAngivelse_402q(record));
						 record.setDkiv_402r(this.skatImportTweaker.setSummariskAngivelse_402r(record));
						 record.setDkiv_402s(this.skatImportTweaker.setSummariskAngivelse_402s(record));
						 
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
	  @RequestMapping(value = "searchTaricVarukod_SkatImport.do", method = RequestMethod.GET)
	  public @ResponseBody Set<JsonSkatTaricVarukodRecord> getTaricVarukod(@RequestParam String applicationUser, @RequestParam String taricVarukod) {
		  logger.info("Inside getTaricVarukod()");
		  final String METHOD = "[DEBUG] method:getTaricVarukod ";
		  Set result = new HashSet();
		  String IMPORT_IE = "I";//
		  
		  try{
			  String BASE_URL = SkatUrlDataStore.SKAT_FETCH_TARIC_VARUKODER_ITEMS_URL;
			  String urlRequestParamsKeys = "user=" + applicationUser + "&ie=" + IMPORT_IE + "&kod=" + taricVarukod;
			  UrlCgiProxyService urlCgiProxyService = new UrlCgiProxyServiceImpl();
			  String jsonPayload = urlCgiProxyService.getJsonContent(BASE_URL, urlRequestParamsKeys);
			  JsonSkatTaricVarukodContainer container = this.skatTaricVarukodService.getContainer(jsonPayload);
			  for(JsonSkatTaricVarukodRecord record : container.getTariclist()){
				  //logger.info(METHOD + "dktara02:" + record.getDktara02());
				  //logger.info(METHOD + "dktara63:" + record.getDktara63());
				  //logger.info(METHOD + "dktara64:" + record.getDktara64());
				  //for certificate code mandatory flag ...
				  //logger.info(METHOD + "dktara54:" + record.getDktara54());
				  //logger.info(METHOD + "dktara55:" + record.getDktara55());
				  //logger.info(METHOD + "dktara56:" + record.getDktara56());
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
	   * @param dkihStr
	   * @param dkivStr
	   * @return
	   */
	  @RequestMapping(value = "calculateToldvaerdiSumsOnItem_SkatImport.do", method = RequestMethod.GET)
	  public @ResponseBody Set<JsonSkatImportSpecificTopicItemToldvaerdiRecord> calculateTollvaerdiSumsOnItem(@RequestParam String applicationUser, @RequestParam String dkStr) {
		  logger.info("Inside: calculateToldvaerdiSumsOnItem_SkatImport()");
		  final String METHOD = "[DEBUG] method:calculateTollvaerdiSumsOnItem ";
		  Set result = new HashSet();
		  try{
			  TollvaerdideklarationMgr tvMgr = new TollvaerdideklarationMgr(this.skatImportSpecificTopicItemService, this.urlCgiProxyService);
			  String urlRequestParams = dkStr;
			  JsonSkatImportSpecificTopicItemToldvaerdiRecord itemTollvaerdiRecord = tvMgr.calculateToldvaerdiSumsOnItem(applicationUser, urlRequestParams);
			  if(itemTollvaerdiRecord!=null){
				  result.add(itemTollvaerdiRecord);
			  }else{
				  logger.info(METHOD + "[ERROR] NULL object on return from toldvaerdiMgr... ? " );
			  }
		  }catch(Exception e){
			  e.printStackTrace();
		  }
		  
		  return result;
	  }
	  
	  /**
	   * 
	   * @param applicationUser
	   * @param dkStr
	   * @return
	   */
	  @RequestMapping(value = "calculateTransportCostsOnItem_SkatImport.do", method = RequestMethod.GET)
	  public @ResponseBody Set<JsonSkatImportSpecificTopicItemToldvaerdiRecord> calculateTransportCostsOnItem(@RequestParam String applicationUser, @RequestParam String dkStr) {
		  logger.info("Inside: calculateTransportCostsOnItem()");
		  final String METHOD = "[DEBUG] method:calculateTransportCostsOnItem ";
		  Set result = new HashSet();
		  try{
			  TollvaerdideklarationMgr tvMgr = new TollvaerdideklarationMgr(this.skatImportSpecificTopicItemService, this.urlCgiProxyService);
			  String urlRequestParams = dkStr;
			  JsonSkatImportSpecificTopicItemToldvaerdiRecord itemTollvaerdiRecord = tvMgr.calculateTransportCostsOnItem(applicationUser, urlRequestParams);
			  logger.info(METHOD + "dkiv_17a:" + itemTollvaerdiRecord.getDkiv_t17a());
			  logger.info(METHOD + "dkiv_19:" + itemTollvaerdiRecord.getDkiv_t19());
			  
			  if(itemTollvaerdiRecord!=null){
				  result.add(itemTollvaerdiRecord);
			  }else{
				  logger.info(METHOD + "[ERROR] NULL object on return from toldvaerdiMgr... ? " );
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
	   * @return
	   */
	  @RequestMapping(value = "getCurrencyRate_SkatImport.do", method = RequestMethod.GET)
	  public @ResponseBody Set getCurrencyRate(@RequestParam String applicationUser, @RequestParam String currencyCode, @RequestParam String isoDate) {
		  logger.info("Inside getCurrencyRate_SkatImport");
		  final String METHOD = "[DEBUG] method:getCurrencyRate_SkatImport "; 
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
	   * The method fetches a pair of Certificates (Number and Code) from a given customer-nr., sku-nr. and country code
	   * Is is only use with CREATE NEW ITEM.
	   * 
	   * @param applicationUser
	   * @param customerNumber
	   * @param sku
	   * @param countryCode
	   * @return
	   */
	  @RequestMapping(value = "getCertificateNrAndCodeR442_SkatImport.do", method = RequestMethod.GET)
	  public @ResponseBody Set<JsonSkatImportSpecificTopicItemCertificateNrAndCodeR442Container> getCertificateNrAndCode_R442(@RequestParam String applicationUser, @RequestParam String customerNumber, 
						  @RequestParam String sku, @RequestParam String countryCode) {
		  logger.info("Inside getCertificateNrAndCode_R442()");
		  final String METHOD = "[DEBUG] method:getCertificateNrAndCode_R442 "; 
		  Set result = new HashSet();
		  StringBuffer urlRequestParamsKeys = new StringBuffer();
		  try{
			  String BASE_URL = SkatImportUrlDataStore.SKAT_IMPORT_BASE_GET_CERTIFICATE_CODES_ON_442_URL;
			  urlRequestParamsKeys.append("user=" + applicationUser);
			  //optional since customer number could be null
			  if(customerNumber!=null && !"".equals(customerNumber)){	 urlRequestParamsKeys.append("&dkih_mokn=" + customerNumber); }
			  urlRequestParamsKeys.append("&dkiv_331=" + sku);
			  urlRequestParamsKeys.append("&dkiv_34=" + countryCode);
			  UrlCgiProxyService urlCgiProxyService = new UrlCgiProxyServiceImpl();
			  String jsonPayload = urlCgiProxyService.getJsonContent(BASE_URL, urlRequestParamsKeys.toString());
			  JsonSkatImportSpecificTopicItemCertificateNrAndCodeR442Container container = this.skatImportSpecificTopicItemService.getSkatCertificateNrAndCodeR442Container(jsonPayload);
			  for(JsonSkatImportSpecificTopicItemCertificateNrAndCodeR442Record record : container.getCertifikatList()){
				  logger.info(METHOD + "dkse_4421:" + record.getDkse_4421());
				  logger.info(METHOD + "dkse_442a:" + record.getDkse_442a());
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
	   * @param avd
	   * @param opd
	   * @param invoiceNr
	   * @return
	   */
	  @RequestMapping(value = "getInvoiceLine_SkatImport.do", method = RequestMethod.GET)
	  public @ResponseBody List<JsonSkatImportTopicInvoiceRecord> getInvoiceLine(@RequestParam String applicationUser, 
								@RequestParam String avd,	@RequestParam String opd, @RequestParam String invoiceNr) {
		  logger.info("Inside: getInvoiceLine()");
		  logger.info("InvoiceNr:" + invoiceNr);
		  List<JsonSkatImportTopicInvoiceRecord> list = new ArrayList<JsonSkatImportTopicInvoiceRecord>();
		  try{
			  String BASE_URL = SkatImportUrlDataStore.SKAT_IMPORT_BASE_FETCH_SPECIFIC_TOPIC_INVOICE_URL;
			  String urlRequestParams = "user=" + applicationUser + "&avd=" + avd +   "&opd=" + opd + "&fak=" + invoiceNr;
			  
			  logger.info(BASE_URL);
			  logger.info(urlRequestParams);
			  
			  UrlCgiProxyService urlCgiProxyService = new UrlCgiProxyServiceImpl();
			  String jsonPayload = urlCgiProxyService.getJsonContent(BASE_URL, urlRequestParams);
			  JsonSkatImportTopicInvoiceContainer container = null;
			  
			  try{
				  if(jsonPayload!=null){
					container = this.skatImportSpecificTopicService.getSkatImportTopicInvoiceContainerOneInvoice(jsonPayload);
					if(container!=null){
						for(JsonSkatImportTopicInvoiceRecord  record : container.getOneInvoice()){
							//logger.info(record.getDkif_fatx());
							list.add(record);
		    			}
					}
				  }
			  }catch(Exception e){
				  e.printStackTrace();
			  }
		  }catch(Exception e){
			  e.printStackTrace();
			  
		  }
		  
		  return list;
	  }
	  
	  /**
	   * 
	   * @param applicationUser
	   * @param requestParams
	   * @return
	   */
	  @RequestMapping(value = "updateExternalInvoiceLine_SkatImport.do", method = RequestMethod.GET)
	  public @ResponseBody Set<JsonSkatImportTopicInvoiceExternalForUpdateContainer> updateExternalInvoiceLineImport(@RequestParam String applicationUser, @RequestParam String requestParams) {
		  logger.info("Inside updateExternalInvoiceLineImport");
		  
		  Set<JsonSkatImportTopicInvoiceExternalForUpdateContainer> result = new HashSet<JsonSkatImportTopicInvoiceExternalForUpdateContainer>();
		  
		  try{
			  String BASE_URL = SkatImportUrlDataStore.SKAT_IMPORT_BASE_UPDATE_SPECIFIC_TOPIC_INVOICE_EXTERNAL_URL;
			  String urlRequestParamsKeys = "user=" + applicationUser + requestParams;
			  logger.info("URL:" + BASE_URL);
			  logger.info("PARAMS:" + urlRequestParamsKeys);
			  
			  UrlCgiProxyService urlCgiProxyService = new UrlCgiProxyServiceImpl();
			  String jsonPayload = urlCgiProxyService.getJsonContent(BASE_URL, urlRequestParamsKeys);
			  
			  JsonSkatImportTopicInvoiceExternalForUpdateContainer container = this.skatImportSpecificTopicService.getSkatImportTopicInvoiceContainerOneInvoiceExternalForUpdate(jsonPayload);
			  if(container!=null && ( container.getErrmsg()!=null && !"".equals(container.getErrmsg())) ){
				  logger.info("[ERROR] " + container.getErrmsg());
			  }else{
				  logger.info("[INFO]" + " Update successfully done!");
				  result.add(container);
				 
			  }
			  	
		  }catch(Exception e){
			  //e.printStackTrace();
		  }
		  
		  return result;
	  }
	  /**
	   * get correct date formatted
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
	  private SkatImportSpecificTopicItemService skatImportSpecificTopicItemService;
	  @Autowired
	  @Required	
	  public void setSkatImportSpecificTopicItemService(SkatImportSpecificTopicItemService value){this.skatImportSpecificTopicItemService = value;}
	  public SkatImportSpecificTopicItemService getSkatImportSpecificTopicItemService(){ return this.skatImportSpecificTopicItemService; }
	  
	  
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
		
	  
	  @Qualifier ("skatImportSpecificTopicService")
	  private SkatImportSpecificTopicService skatImportSpecificTopicService;
	  @Autowired
	  @Required
	  public void setSkatImportSpecificTopicService (SkatImportSpecificTopicService value){ this.skatImportSpecificTopicService = value; }
	  public SkatImportSpecificTopicService getSkatImportSpecificTopicService(){ return this.skatImportSpecificTopicService; }
	  
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
