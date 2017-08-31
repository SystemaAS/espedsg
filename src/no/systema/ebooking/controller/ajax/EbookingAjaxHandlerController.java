/**
 * 
 */
package no.systema.ebooking.controller.ajax;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.util.*;

import org.springframework.web.multipart.MultipartHttpServletRequest;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

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
import org.springframework.web.multipart.MultipartFile;

import no.systema.main.model.SystemaWebUser;
import no.systema.main.service.UrlCgiProxyService;
import no.systema.main.util.JsonDebugger;
import no.systema.main.model.jsonjackson.general.postalcodes.JsonPostalCodesContainer;
import no.systema.main.model.jsonjackson.general.postalcodes.JsonPostalCodesRecord;
//ebooking
import no.systema.ebooking.model.EbookingOrderLineValidationObject;
import no.systema.ebooking.model.jsonjackson.JsonMainOrderHeaderFraktbrevContainer;
import no.systema.ebooking.model.jsonjackson.JsonMainOrderHeaderFraktbrevRecord;
import no.systema.ebooking.model.jsonjackson.order.childwindow.JsonEbookingCustomerDeliveryAddressContainer;
import no.systema.ebooking.model.jsonjackson.order.childwindow.JsonEbookingCustomerDeliveryAddressRecord;
import no.systema.ebooking.model.jsonjackson.order.childwindow.JsonEbookingCustomerContainer;
import no.systema.ebooking.model.jsonjackson.order.childwindow.JsonEbookingCustomerRecord;
import no.systema.ebooking.model.jsonjackson.order.childwindow.JsonEbookingPackingCodesContainer;
import no.systema.ebooking.model.jsonjackson.order.childwindow.JsonEbookingPackingCodesRecord;
import no.systema.ebooking.model.jsonjackson.order.childwindow.JsonEbookingDangerousGoodsContainer;
import no.systema.ebooking.model.jsonjackson.order.childwindow.JsonEbookingDangerousGoodsRecord;


import no.systema.ebooking.service.EbookingChildWindowService;
import no.systema.ebooking.service.EbookingMainOrderHeaderService;
import no.systema.ebooking.url.store.EbookingUrlDataStore;
import no.systema.ebooking.util.EbookingConstants;
import no.systema.ebooking.util.RpgReturnResponseHandler;


/**
 * This Ajax handler is the class handling ajax request in Tranport Disp. 
 * It will usually be called from within a jQuery function or other javascript alike... 
 * 
 * @author oscardelatorre
 * @date Jan 30, 2017
 * 
 */

@Controller

public class EbookingAjaxHandlerController {
	private static final Logger logger = Logger.getLogger(EbookingAjaxHandlerController.class.getName());
	private RpgReturnResponseHandler rpgReturnResponseHandler = new RpgReturnResponseHandler();
	//private ControllerAjaxCommonFunctionsMgr controllerAjaxCommonFunctionsMgr;
	private static final JsonDebugger jsonDebugger = new JsonDebugger(2000);
	
	
	/**
	 * 
	 * @param applicationUser
	 * @param requestString
	 * @return
	 */
	@RequestMapping(value = "updateOrderDetailLine_Ebooking.do", method = RequestMethod.GET)
    public @ResponseBody Set<JsonMainOrderHeaderFraktbrevRecord> updateOrderDetailLine
	  						(@RequestParam String applicationUser, @RequestParam String requestString){
		 logger.info("Inside: updateOrderDetailLine");
		 Set<JsonMainOrderHeaderFraktbrevRecord> result = new HashSet<JsonMainOrderHeaderFraktbrevRecord>();
		 //logger.info(requestString);
		 if(requestString!=null && !"".equals(requestString)){
		 	 final String BASE_URL = EbookingUrlDataStore.EBOOKING_BASE_WORKFLOW_UPDATE_LINE_MAIN_ORDER_FRAKTBREV_URL;
			 //add URL-parameters
			 String urlRequestParams = requestString;
			 logger.info(Calendar.getInstance().getTime() + " CGI-start timestamp");
			 logger.info("URL: " + BASE_URL);
			 logger.info("URL PARAMS: " + urlRequestParams);
			 String rpgReturnPayload = this.urlCgiProxyService.getJsonContent(BASE_URL, urlRequestParams);
			 
			 JsonMainOrderHeaderFraktbrevRecord placeHolderObj = new JsonMainOrderHeaderFraktbrevRecord();	
			 //Debug --> 
			 logger.info("Checking errMsg in rpgReturnPayload" + rpgReturnPayload);
			 //we must evaluate a return RPG code in order to know if the Update was OK or not
			 if(rpgReturnPayload!=null){
				 rpgReturnResponseHandler.setErrorMessage(null);
				 rpgReturnResponseHandler.evaluateRpgResponseOnEditSpecificOrder(rpgReturnPayload);
				 if(rpgReturnResponseHandler.getErrorMessage()!=null && !"".equals(rpgReturnResponseHandler.getErrorMessage())){
					 rpgReturnResponseHandler.setErrorMessage("[ERROR] FATAL on UPDATE: " + rpgReturnResponseHandler.getErrorMessage());
					 //TODO -->this.setFatalErrorAddRemoveOrders(model, rpgReturnResponseHandler, recordToValidate);
					 logger.info(rpgReturnResponseHandler.getErrorMessage());
					 placeHolderObj.setFvlinr("-1");
				 }else{
					 placeHolderObj.setFvlinr("1");
				 }
			 }
			 result.add(placeHolderObj);
		 }
		 return result;
	}
	/**
	 * 
	 * @param applicationUser
	 * @param requestString
	 * @return
	 */
	@RequestMapping(value = "getSpecificTopicItemChosenFromGuiElement_Ebooking.do", method = RequestMethod.GET)
    public @ResponseBody Set<JsonMainOrderHeaderFraktbrevRecord> getSpecificTopicItemChosenFromHtmlList
	  						(@RequestParam String applicationUser, @RequestParam String requestString){
		 logger.info("Inside: getSpecificTopicItemChosenFromHtmlList");
		 
		 Set<JsonMainOrderHeaderFraktbrevRecord> result = new HashSet<JsonMainOrderHeaderFraktbrevRecord>();
		 //logger.info(requestString);
		 if(requestString!=null && !"".equals(requestString)){
		 	final String BASE_URL = EbookingUrlDataStore.EBOOKING_BASE_WORKFLOW_FETCH_LINE_MAIN_ORDER_FRAKTBREV_URL;
			//add URL-parameters
		 	String urlRequestParams = requestString;
			logger.info(Calendar.getInstance().getTime() + " CGI-start timestamp");
			logger.info("URL: " + BASE_URL);
			logger.info("URL PARAMS: " + urlRequestParams);
			String jsonPayload = this.urlCgiProxyService.getJsonContent(BASE_URL, urlRequestParams);
			JsonMainOrderHeaderFraktbrevContainer container = this.ebookingMainOrderHeaderService.getFraktbrevContainer(jsonPayload);
			if(container!=null){
				for (JsonMainOrderHeaderFraktbrevRecord fraktbrevRecord: container.getAwblineGet()){
					result.add(fraktbrevRecord);
					//logger.info(fraktbrevRecord.getFvant());
				}
			}
		 }
		 
		 return result;
		 
	}
	
	/**
	 * 
	 * @param applicationUser
	 * @param requestString
	 * @param lineNr
	 * @return
	 */
	@RequestMapping(value = "validateCurrentOrderDetailLine_Ebooking.do", method = RequestMethod.GET)
    public @ResponseBody Set<EbookingOrderLineValidationObject> validateCurrentOrderDetailLine
	  						(@RequestParam String applicationUser, @RequestParam String requestString, @RequestParam String lineNr ){
		 logger.info("Inside: validateCurrentOrderDetailLine");
		 Set<EbookingOrderLineValidationObject> result = new HashSet<EbookingOrderLineValidationObject>();
		 EbookingOrderLineValidationObject orderLineValidationObject = new EbookingOrderLineValidationObject();
		 //logger.info(requestString);
		 if(requestString!=null && !"".equals(requestString)){
		 	 String BASE_URL = null;
		 	 BASE_URL = EbookingUrlDataStore.EBOOKING_BASE_WORKFLOW_VALIDATE_LINE_MAIN_ORDER_FRAKTBREV_2_URL;
		 	 
		 	 
			 //add URL-parameters
			 String urlRequestParams = requestString;
			 logger.info(Calendar.getInstance().getTime() + " CGI-start timestamp");
			 logger.info("URL: " + BASE_URL);
			 logger.info("URL PARAMS: " + urlRequestParams);
			 String jsonPayload = this.urlCgiProxyService.getJsonContent(BASE_URL, urlRequestParams);
			 
			 if(jsonPayload!=null){
				 JsonMainOrderHeaderFraktbrevContainer container = this.ebookingMainOrderHeaderService.getFraktbrevContainer(jsonPayload);
				 if(container!=null){
					 for(JsonMainOrderHeaderFraktbrevRecord record : container.getAwblineValidate()){
						 orderLineValidationObject.setLinenr(lineNr);
						 orderLineValidationObject.setFvlm(record.getFvlm());
						 orderLineValidationObject.setFvlm2(record.getFvlm2());
						 logger.info(orderLineValidationObject.getFvlm());
						 logger.info(orderLineValidationObject.getFvlm2());
						 
						 
					 }	
					 logger.info("errMsg:" + container.getErrMsg());
					 //hand over
					 orderLineValidationObject.setErrMsg(container.getErrMsg());
					 orderLineValidationObject.setInfoMsg(container.getInfoMsg());
					 
				 }
			 }
			 result.add(orderLineValidationObject);
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
	  @RequestMapping(value = "searchCustomer_Ebooking.do", method = RequestMethod.GET)
	  public @ResponseBody List<JsonEbookingCustomerContainer> searchCustomer(@RequestParam String applicationUser, @RequestParam String customerName, @RequestParam String customerNumber) {
		  logger.info("Inside searchCustomer");
		  List result = new ArrayList();
		  
		  JsonEbookingCustomerDeliveryAddressRecord deliveryAddressRecord = getDeliveryAddress(applicationUser, customerNumber);
		  JsonEbookingCustomerRecord targetRecord = null;
		  //check if this customer has an existent Delivery address. In that case use it!
		  if(deliveryAddressRecord!=null){
			  targetRecord = new JsonEbookingCustomerRecord();
			  //Hand over the delivery address fields to the customer fields
			  /*from TranspDisp 
			  targetRecord.setAuxnavn(deliveryAddressRecord.getVadrna());
			  targetRecord.setAdr1(deliveryAddressRecord.getVadrn1());
			  targetRecord.setAdr2(deliveryAddressRecord.getVadrn2());
			  targetRecord.setAdresse(deliveryAddressRecord.getVadrn3());
			  targetRecord.setAuxtlf(deliveryAddressRecord.getVatlf());
			  targetRecord.setAuxfax(deliveryAddressRecord.getVafax());
			  targetRecord.setAuxmail(deliveryAddressRecord.getVamail());
			  */
			  targetRecord.setAuxnavn(deliveryAddressRecord.getVadrna());
			  targetRecord.setGateAdr(deliveryAddressRecord.getVadrn1());
			  targetRecord.setAdresse2(deliveryAddressRecord.getVadrn2());
			  targetRecord.setPostnrSted(deliveryAddressRecord.getVadrn3());
			  targetRecord.setAuxtlf(deliveryAddressRecord.getVatlf());
			  targetRecord.setAuxfax(deliveryAddressRecord.getVafax());
			  targetRecord.setAuxmail(deliveryAddressRecord.getVamail());
		  }
		  //search in the customer register (deep search)
		  String BASE_URL = EbookingUrlDataStore.EBOOKING_BASE_CHILDWINDOW_CUSTOMER_URL;
		  String urlRequestParamsKeys = this.getRequestUrlKeyParametersForSearchCustomer(applicationUser, customerName, customerNumber);
		  logger.info("URL: " + BASE_URL);
		  logger.info("PARAMS: " + urlRequestParamsKeys);
		  logger.info(Calendar.getInstance().getTime() +  " CGI-start timestamp");
		  String jsonPayload = this.urlCgiProxyService.getJsonContent(BASE_URL, urlRequestParamsKeys);
		  //Should be removed as soon as RPG return the correct container name = customerlist (not capitalized in the first letter)
		  logger.info(jsonPayload);
		  logger.info(Calendar.getInstance().getTime() +  " CGI-end timestamp");
    		  if(jsonPayload!=null){
    			  JsonEbookingCustomerContainer container = this.ebookingChildWindowService.getCustomerContainer(jsonPayload);
	    		if(container!=null){
	    			for(JsonEbookingCustomerRecord  record : container.getInqFkund()){
	    				if(record.getKundnr().equals(customerNumber)){
	    					//logger.info("CUSTOMER via AJAX: " + record.getNavn() + " NUMBER:" + record.getKundnr());
	    					if(targetRecord!=null){
	    						targetRecord.setKundnr(record.getKundnr());
	    						//Set the real customer name & land
	    						targetRecord.setNavn(record.getNavn());
	    						targetRecord.setLand(record.getLand());
	    						//targetRecord.setFakknr(record.getFakknr());
	    						//DEBUG
	    						logger.info("TJINQKUND.pgm:");
	    						logger.info("navn:" + targetRecord.getNavn());
	    						//logger.info("auxnavn:" + targetRecord.getAuxnavn());
	    						//logger.info("fakknr:" + targetRecord.getFakknr());
	    						//logger.info(targetRecord.getAdr1());
	    						//logger.info(targetRecord.getLand());
	    						result.add(targetRecord);
	    					}else{
	    						result.add(record);
	    					}
	    					
	    				}
	    			}
	    		}
	    	  }
	    	  
	    	  return result;
	  }
	  
	  /**
	   * 
	   * @param applicationUser
	   * @param requestString
	   * @return
	   */
	  @RequestMapping(value = "getCustomerDeliveryAddress_Ebooking.do", method = RequestMethod.GET)
	    public @ResponseBody Set<JsonEbookingCustomerDeliveryAddressRecord> getCustomerDeliveryAddress_Ebooking
		  						(@RequestParam String applicationUser, @RequestParam String kundnr, @RequestParam String vadrnr){
			 logger.info("Inside: getCustomerDeliveryAddress_Ebooking");
			 
			 Set<JsonEbookingCustomerDeliveryAddressRecord> result = new HashSet<JsonEbookingCustomerDeliveryAddressRecord>();
				//prepare the access CGI with RPG back-end
				String BASE_URL = EbookingUrlDataStore.EBOOKING_BASE_CHILDWINDOW_CUSTOMER_DELIVERY_ADDRESS_URL;
				String urlRequestParams = "user=" + applicationUser + "&wkundnr=" + kundnr+ "&wvadrnr=" + vadrnr; 
				logger.info("URL: " + BASE_URL);
				logger.info("PARAMS: " + urlRequestParams);
				logger.info(Calendar.getInstance().getTime() +  " CGI-start timestamp");
				String jsonPayload = this.urlCgiProxyService.getJsonContent(BASE_URL, urlRequestParams);
				//Debug -->
		    	logger.debug(jsonDebugger.debugJsonPayloadWithLog4J(jsonPayload));
				logger.info(Calendar.getInstance().getTime() +  " CGI-end timestamp");
		    
				if(jsonPayload!=null){
					JsonEbookingCustomerDeliveryAddressContainer container = this.ebookingChildWindowService.getCustomerDeliveryAddressContainer(jsonPayload);
		    		if(container!=null){
		    			for(JsonEbookingCustomerDeliveryAddressRecord  record : container.getInqdeladdr()){
		    				result.add(record);
		    			}
		    		}
				}
				return result;
			 
			 
			 /*
			 Set<JsonMainOrderHeaderFraktbrevRecord> result = new HashSet<JsonMainOrderHeaderFraktbrevRecord>();
			 //logger.info(requestString);
			 if(requestString!=null && !"".equals(requestString)){
			 	final String BASE_URL = EbookingUrlDataStore.EBOOKING_BASE_WORKFLOW_FETCH_LINE_MAIN_ORDER_FRAKTBREV_URL;
				//add URL-parameters
			 	String urlRequestParams = requestString;
				logger.info(Calendar.getInstance().getTime() + " CGI-start timestamp");
				logger.info("URL: " + BASE_URL);
				logger.info("URL PARAMS: " + urlRequestParams);
				String jsonPayload = this.urlCgiProxyService.getJsonContent(BASE_URL, urlRequestParams);
				JsonMainOrderHeaderFraktbrevContainer container = this.ebookingMainOrderHeaderService.getFraktbrevContainer(jsonPayload);
				if(container!=null){
					for (JsonMainOrderHeaderFraktbrevRecord fraktbrevRecord: container.getAwblineGet()){
						result.add(fraktbrevRecord);
						//logger.info(fraktbrevRecord.getFvant());
					}
				}
			 }
			 
			 return result;
			 */
		}
		
	  
	  /**
	   * 
	   * @param applicationUser
	   * @param id
	   * @param countryCode
	   * @return
	   */
	  @RequestMapping(value = "searchPostNumber_Ebooking.do", method = RequestMethod.GET)
	  public @ResponseBody Collection<JsonPostalCodesRecord> searchPostNumber(@RequestParam String applicationUser, @RequestParam String id, @RequestParam String countryCode) {
		  logger.info("Inside searchPostNumber");
		  Collection<JsonPostalCodesRecord> result = new ArrayList<JsonPostalCodesRecord>();
		  
		  String BASE_URL = EbookingUrlDataStore.EBOOKING_BASE_CHILDWINDOW_POSTAL_CODES_URL;
		  JsonPostalCodesRecord recordToValidate = new JsonPostalCodesRecord();
		  recordToValidate.setSt2kod(id);
		  recordToValidate.setSt2lk(countryCode);
		  boolean exactMatch = true;
			String urlRequestParamsKeys = this.getRequestUrlKeyParametersSearchPostalCodes(applicationUser, recordToValidate, exactMatch);
			logger.info("URL: " + BASE_URL);
			logger.info("PARAMS: " + urlRequestParamsKeys);
			logger.info(Calendar.getInstance().getTime() +  " CGI-start timestamp");
			String jsonPayload = this.urlCgiProxyService.getJsonContent(BASE_URL, urlRequestParamsKeys);
			//Debug -->
			logger.debug(jsonDebugger.debugJsonPayloadWithLog4J(jsonPayload));
			logger.info(Calendar.getInstance().getTime() +  " CGI-end timestamp");
	    
			if(jsonPayload!=null){
				JsonPostalCodesContainer container = this.ebookingChildWindowService.getPostalCodesContainer(jsonPayload);
	    			if(container!=null){
	    				result = container.getPostnrlist();
	    				for(JsonPostalCodesRecord  record : result){
	    				//DEBUG
	    				}
	    			}
			}	
		  return result;
	  }
	  
	  @RequestMapping(value = "searchPackingCodes_Ebooking.do", method = RequestMethod.GET)
	  public @ResponseBody Collection<JsonEbookingPackingCodesRecord> searchPackingCodes(@RequestParam String applicationUser,@RequestParam String kode) {
		  	Collection<JsonEbookingPackingCodesRecord> result = new ArrayList<JsonEbookingPackingCodesRecord>();
		  	logger.info("Inside searchPackingCodes...");
		  
	  		//prepare the access CGI with RPG back-end
			String BASE_URL = EbookingUrlDataStore.EBOOKING_BASE_CHILDWINDOW_PACKING_CODES_URL;
			
			String urlRequestParamsKeys = "user=" + applicationUser + "&kode=" + kode + "&fullinfo=J&getval=J";
			logger.info("URL: " + BASE_URL);
			logger.info("PARAMS: " + urlRequestParamsKeys);
			logger.info(Calendar.getInstance().getTime() +  " CGI-start timestamp");
			String jsonPayload = this.urlCgiProxyService.getJsonContent(BASE_URL, urlRequestParamsKeys);
			//Debug -->
			logger.info(jsonPayload);
			logger.info(Calendar.getInstance().getTime() +  " CGI-end timestamp");
			
			if(jsonPayload!=null){
				JsonEbookingPackingCodesContainer container = this.ebookingChildWindowService.getPackingCodesContainer(jsonPayload);
				if(container!=null){
					result = container.getForpaknKoder();
				}else{
					logger.info("**************** CONTAINER = NULL");
				}
			}
			//logger.info("**************** List Size:" + result.size());
			return result;
	  }
	  
	  /**
	   * 
	   * @param applicationUser
	   * @param id
	   * @param countryCode
	   * @return
	   */
	  @RequestMapping(value = "searchDangerousGoods_Ebooking.do", method = RequestMethod.GET)
	  public @ResponseBody Collection<JsonEbookingDangerousGoodsRecord> searchDangerousGoods(@RequestParam String applicationUser, 
																	@RequestParam String unnr, @RequestParam String embg , @RequestParam String indx) {
		  	Collection<JsonEbookingDangerousGoodsRecord> result = new ArrayList<JsonEbookingDangerousGoodsRecord>();

		  	//this.controllerAjaxCommonFunctionsMgr = new ControllerAjaxCommonFunctionsMgr (this.urlCgiProxyService, this.transportDispChildWindowService);
		  	JsonEbookingDangerousGoodsContainer record = new JsonEbookingDangerousGoodsContainer();
		  	record.setUnnr(unnr);
		  	if(!"?".equals(embg)){record.setEmbg(embg);}
		  	if(!"?".equals(indx)){record.setIndx(indx);}
		  
		  	logger.info("Inside searchDangerousGoods...");
		  
	  		//prepare the access CGI with RPG back-end
			String BASE_URL = EbookingUrlDataStore.EBOOKING_BASE_CHILDWINDOW_DANGEROUS_GOODS_URL;
			//adjust from jquery/jsp
			if("?".equals(record.getEmbg())){ record.setEmbg(""); }
			if("?".equals(record.getIndx())){ record.setIndx(""); }
			
			String urlRequestParamsKeys = this.getRequestUrlKeyParametersDangerousGoods(applicationUser, record);
			logger.info("URL: " + BASE_URL);
			logger.info("PARAMS: " + urlRequestParamsKeys);
			logger.info(Calendar.getInstance().getTime() +  " CGI-start timestamp");
			String jsonPayload = this.urlCgiProxyService.getJsonContent(BASE_URL, urlRequestParamsKeys);
			//Debug -->
			logger.debug(jsonPayload);
			logger.info(Calendar.getInstance().getTime() +  " CGI-end timestamp");
			
			if(jsonPayload!=null){
				JsonEbookingDangerousGoodsContainer container = this.ebookingChildWindowService.getDangerousGoodsContainer(jsonPayload);
				if(container!=null){
					result = container.getUnNumbers();
				}else{
					logger.info("**************** CONTAINER = NULL");
				}
			}
			//logger.info("**************** List Size:" + result.size());
			return result;
	  }
	  
	  /**
	   * 
	   * @param applicationUser
	   * @param searchFilter
	   * @return
	   */
	  private String getRequestUrlKeyParametersDangerousGoods(String applicationUser, JsonEbookingDangerousGoodsContainer searchFilter){
		  	String CODE_UNNR = "U";
		  	String CODE_EMBG = "E";
		  	String CODE_INDX = "J";
			StringBuffer urlRequestParamsKeys = new StringBuffer();
			urlRequestParamsKeys.append("user=" + applicationUser);
			String matchOnlyCode = CODE_UNNR; //Deafault
			
			if(searchFilter.getUnnr()!=null && !"".equals(searchFilter.getUnnr())){
				urlRequestParamsKeys.append(EbookingConstants.URL_CHAR_DELIMETER_FOR_PARAMS_WITH_HTML_REQUEST + "unnr=" + searchFilter.getUnnr());
			}
			//user=JOVO&unnr=1950=&embg=&indx=&getval=&fullinfo=J
			
			if(searchFilter.getEmbg()!=null && !"".equals(searchFilter.getEmbg())){
				//searching for perfect match (otherwise it will return from an unnr-number and forward...)
				matchOnlyCode = CODE_EMBG;
				urlRequestParamsKeys.append(EbookingConstants.URL_CHAR_DELIMETER_FOR_PARAMS_WITH_HTML_REQUEST + "embg=" + searchFilter.getEmbg());
			}
			if(searchFilter.getIndx()!=null && !"".equals(searchFilter.getIndx())){
				//searching for perfect match (otherwise it will return from an unnr-number and forward...)
				matchOnlyCode = CODE_INDX;
				urlRequestParamsKeys.append(EbookingConstants.URL_CHAR_DELIMETER_FOR_PARAMS_WITH_HTML_REQUEST + "indx=" + searchFilter.getIndx());
			}
			
			urlRequestParamsKeys.append(EbookingConstants.URL_CHAR_DELIMETER_FOR_PARAMS_WITH_HTML_REQUEST + "matchOnly=" + matchOnlyCode); 
			//urlRequestParamsKeys.append(TransportDispConstants.URL_CHAR_DELIMETER_FOR_PARAMS_WITH_HTML_REQUEST + "fullinfo=J"); //always the max. nr of columns (as default)
			
			return urlRequestParamsKeys.toString();
		}
	  /**
	   * 
	   * @param applicationUser
	   * @param searchFilterRecord
	   * @param exactMatch
	   * @return
	   */
	  private String getRequestUrlKeyParametersSearchPostalCodes(String applicationUser, JsonPostalCodesRecord searchFilterRecord, boolean exactMatch){
			final String POSTALCODE_DIRECTION_FRA = "fra";
			final String POSTALCODE_DIRECTION_TIL = "til";
			
			StringBuffer urlRequestParamsKeys = new StringBuffer();
			urlRequestParamsKeys.append("user=" + applicationUser);
			if(POSTALCODE_DIRECTION_FRA.equals(searchFilterRecord.getDirection())){
				urlRequestParamsKeys.append("&varlk=fralk");
				urlRequestParamsKeys.append("&varkod=fra");
			}else if(POSTALCODE_DIRECTION_TIL.equals(searchFilterRecord.getDirection())){
				urlRequestParamsKeys.append("&varlk=tillk");
				urlRequestParamsKeys.append("&varkod=til");
			}
			
			if(searchFilterRecord.getSt2lk()!=null && !"".equals(searchFilterRecord.getSt2lk())){
				urlRequestParamsKeys.append(EbookingConstants.URL_CHAR_DELIMETER_FOR_PARAMS_WITH_HTML_REQUEST + "soklk=" + searchFilterRecord.getSt2lk());
			}
			if(searchFilterRecord.getSt2nvn()!=null && !"".equals(searchFilterRecord.getSt2nvn())){
				urlRequestParamsKeys.append(EbookingConstants.URL_CHAR_DELIMETER_FOR_PARAMS_WITH_HTML_REQUEST + "soknvn=" + searchFilterRecord.getSt2nvn());
			}
			if(searchFilterRecord.getWskunpa()!=null && !"".equals(searchFilterRecord.getWskunpa())){
				urlRequestParamsKeys.append(EbookingConstants.URL_CHAR_DELIMETER_FOR_PARAMS_WITH_HTML_REQUEST + "wskunpa=" + searchFilterRecord.getWskunpa());
			}
			if(searchFilterRecord.getSt2kod()!=null && !"".equals(searchFilterRecord.getSt2kod())){
				//urlRequestParamsKeys.append(TransportDispConstants.URL_CHAR_DELIMETER_FOR_PARAMS_WITH_HTML_REQUEST + "wst2kod=" + searchFilterRecord.getSt2kod());
				urlRequestParamsKeys.append(EbookingConstants.URL_CHAR_DELIMETER_FOR_PARAMS_WITH_HTML_REQUEST + "sokkod=" + searchFilterRecord.getSt2kod());
			}
			if(exactMatch){
				urlRequestParamsKeys.append(EbookingConstants.URL_CHAR_DELIMETER_FOR_PARAMS_WITH_HTML_REQUEST + "getval=J");
			}
			
			return urlRequestParamsKeys.toString();
		}
	  /**
	   * 
	   * @param applicationUser
	   * @param customerNumber
	   * @return
	   */
	  private JsonEbookingCustomerDeliveryAddressRecord getDeliveryAddress(String applicationUser, String customerNumber){
		  JsonEbookingCustomerDeliveryAddressRecord retval = null;
		//prepare the access CGI with RPG back-end
		  String BASE_URL = EbookingUrlDataStore.EBOOKING_BASE_CHILDWINDOW_CUSTOMER_DELIVERY_ADDRESS_URL;
		  String urlRequestParamsKeys = "user=" + applicationUser + "&wkundnr=" + customerNumber + "&wvadrnr=1" ;
		  logger.info("URL: " + BASE_URL);
		  logger.info("PARAMS: " + urlRequestParamsKeys);
		  String jsonPayload = this.urlCgiProxyService.getJsonContent(BASE_URL, urlRequestParamsKeys);
		  //Should be removed as soon as RPG return the correct container name = customerlist (not capitalized in the first letter)
		  logger.info(jsonPayload);
    		  if(jsonPayload!=null){
    			  JsonEbookingCustomerDeliveryAddressContainer container = this.ebookingMainOrderHeaderService.getDeliveryAddressContainer(jsonPayload);
	    		if(container!=null){
	    			for(JsonEbookingCustomerDeliveryAddressRecord  record : container.getInqdeladdr()){
	    				if(record!=null & record.getVadrnr()!=null){
	    					retval = record;
	    					logger.info("AA");
	    					logger.info("PICK_UP or DELIVERY-ADDRESS");
	    					logger.info("Vadrna:" + record.getVadrna());
	    					logger.info("vadrn1:" + record.getVadrn1());
	    					logger.info("vadrn2:" + record.getVadrn2());
	    					logger.info("vadrn3:" + record.getVadrn3());
	    					
	    				}
	    			}
	    		}
	    	  }
    		  return retval;
	  }
			
	  /**
	   * 
	   * @param applicationUser
	   * @param customerName
	   * @param customerNumber
	   * @return
	   */
	  private String getRequestUrlKeyParametersForSearchCustomer(String applicationUser, String customerName, String customerNumber){
			StringBuffer urlRequestParamsKeys = new StringBuffer();
			urlRequestParamsKeys.append("user=" + applicationUser);
			
			if(customerNumber!=null && !"".equals(customerNumber)){
				urlRequestParamsKeys.append(EbookingConstants.URL_CHAR_DELIMETER_FOR_PARAMS_WITH_HTML_REQUEST + "sokknr=" + customerNumber);
			}
			if(customerName!=null && !"".equals(customerName)){
				urlRequestParamsKeys.append(EbookingConstants.URL_CHAR_DELIMETER_FOR_PARAMS_WITH_HTML_REQUEST + "soknvn=" + customerName);
			}
			return urlRequestParamsKeys.toString();
		}
	
	  //SERVICES
	  @Qualifier ("urlCgiProxyService")
	  private UrlCgiProxyService urlCgiProxyService;
	  @Autowired
	  @Required
	  public void setUrlCgiProxyService (UrlCgiProxyService value){ this.urlCgiProxyService = value; }
	  public UrlCgiProxyService getUrlCgiProxyService(){ return this.urlCgiProxyService; }
	  
	  
	  @Qualifier ("ebookingChildWindowService")
	  private EbookingChildWindowService ebookingChildWindowService;
	  @Autowired
	  @Required
	  public void setEbookingChildWindowService (EbookingChildWindowService value){ this.ebookingChildWindowService = value; }
	  public EbookingChildWindowService getEbookingChildWindowService(){ return this.ebookingChildWindowService; }
		
	  
	  @Qualifier ("ebookingMainOrderHeaderService")
	  private EbookingMainOrderHeaderService ebookingMainOrderHeaderService;
	  @Autowired
	  @Required
	  public void setEbookingMainOrderHeaderService (EbookingMainOrderHeaderService value){ this.ebookingMainOrderHeaderService = value; }
	  public EbookingMainOrderHeaderService getEbookingMainOrderHeaderService(){ return this.ebookingMainOrderHeaderService; }
		
	  /*
	  @Qualifier 
	  private TransportDispWorkflowSpecificTripService transportDispWorkflowSpecificTripService;
	  @Autowired
	  @Required	
	  public void setTransportDispWorkflowSpecificTripService(TransportDispWorkflowSpecificTripService value){this.transportDispWorkflowSpecificTripService = value;}
	  public TransportDispWorkflowSpecificTripService getTransportDispWorkflowSpecificTripService(){ return this.transportDispWorkflowSpecificTripService; }
	  
	  @Qualifier 
	  private TransportDispWorkflowSpecificOrderService transportDispWorkflowSpecificOrderService;
	  @Autowired
	  @Required	
	  public void setTransportDispWorkflowSpecificOrderService(TransportDispWorkflowSpecificOrderService value){this.transportDispWorkflowSpecificOrderService = value;}
	  public TransportDispWorkflowSpecificOrderService getTransportDispWorkflowSpecificOrderService(){ return this.transportDispWorkflowSpecificOrderService; }
	
	  @Qualifier ("transportDispWorkflowBudgetService")
	  private TransportDispWorkflowBudgetService transportDispWorkflowBudgetService;
	  @Autowired
	  public void setTransportDispWorkflowBudgetService (TransportDispWorkflowBudgetService value){ this.transportDispWorkflowBudgetService=value; }
	  public TransportDispWorkflowBudgetService getTransportDispWorkflowBudgetService(){return this.transportDispWorkflowBudgetService;}
		*/
		
		
	  
}
