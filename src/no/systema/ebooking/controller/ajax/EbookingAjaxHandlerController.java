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
/*
import no.systema.transportdisp.model.jsonjackson.workflow.JsonTransportDispWorkflowListContainer;
import no.systema.transportdisp.model.jsonjackson.workflow.JsonTransportDispWorkflowListRecord;
import no.systema.transportdisp.model.jsonjackson.workflow.JsonTransportDispWorkflowSpecificTripContainer;
import no.systema.transportdisp.model.jsonjackson.workflow.JsonTransportDispWorkflowSpecificTripRecord;
import no.systema.transportdisp.model.jsonjackson.workflow.budget.JsonTransportDispWorkflowSpecificBudgetContainer;
import no.systema.transportdisp.model.jsonjackson.workflow.budget.JsonTransportDispWorkflowSpecificBudgetRecord;
import no.systema.transportdisp.model.jsonjackson.workflow.order.JsonTransportDispWorkflowSpecificOrderFraktbrevRecord;
import no.systema.transportdisp.model.jsonjackson.workflow.order.JsonTransportDispWorkflowSpecificOrderRecord;
import no.systema.transportdisp.model.jsonjackson.workflow.order.JsonTransportDispWorkflowSpecificOrderContainer;


import no.systema.transportdisp.model.jsonjackson.workflow.JsonTransportDispWorkflowSpecificTripArchivedDocsContainer;
import no.systema.transportdisp.model.jsonjackson.workflow.JsonTransportDispWorkflowSpecificTripArchivedDocsRecord;
import no.systema.transportdisp.model.jsonjackson.workflow.JsonTransportDispWorkflowSpecificTripMessageNoteContainer;
import no.systema.transportdisp.model.jsonjackson.workflow.JsonTransportDispWorkflowSpecificTripMessageNoteRecord;
import no.systema.transportdisp.model.jsonjackson.workflow.order.childwindow.JsonTransportDispCustomerContainer;
import no.systema.transportdisp.model.jsonjackson.workflow.order.childwindow.JsonTransportDispCustomerRecord;
import no.systema.transportdisp.model.jsonjackson.workflow.order.childwindow.JsonTransportDispDangerousGoodsContainer;
import no.systema.transportdisp.model.jsonjackson.workflow.order.invoice.childwindow.JsonTransportDispSupplierContainer;
import no.systema.transportdisp.model.jsonjackson.workflow.order.invoice.childwindow.JsonTransportDispSupplierRecord;


import no.systema.transportdisp.model.jsonjackson.workflow.order.invoice.JsonTransportDispWorkflowSpecificOrderInvoiceContainer;
import no.systema.transportdisp.model.jsonjackson.workflow.order.invoice.JsonTransportDispWorkflowSpecificOrderInvoiceReadyMarkContainer;
import no.systema.transportdisp.model.jsonjackson.workflow.order.invoice.JsonTransportDispWorkflowSpecificOrderInvoiceRecord;
import no.systema.transportdisp.model.jsonjackson.workflow.triplist.childwindow.JsonTransportDispBilNrContainer;
import no.systema.transportdisp.model.jsonjackson.workflow.triplist.childwindow.JsonTransportDispBilNrRecord;
import no.systema.transportdisp.model.jsonjackson.workflow.triplist.childwindow.JsonTransportDispDriverContainer;
import no.systema.transportdisp.model.jsonjackson.workflow.triplist.childwindow.JsonTransportDispDriverRecord;
import no.systema.transportdisp.model.jsonjackson.workflow.triplist.childwindow.JsonTransportDispFileUploadValidationContainer;
import no.systema.transportdisp.model.jsonjackson.workflow.triplist.childwindow.JsonTransportDispTranspCarrierContainer;
import no.systema.transportdisp.model.jsonjackson.workflow.triplist.childwindow.JsonTransportDispTranspCarrierRecord;
import no.systema.transportdisp.model.jsonjackson.workflow.order.JsonTransportDispCustomerDeliveryAddressContainer;
import no.systema.transportdisp.model.jsonjackson.workflow.order.JsonTransportDispCustomerDeliveryAddressRecord;
import no.systema.transportdisp.model.jsonjackson.workflow.order.JsonTransportDispWorkflowSpecificOrderRecord;
import no.systema.transportdisp.model.jsonjackson.workflow.order.JsonTransportDispWorkflowSpecificOrderFraktbrevContainer;
import no.systema.transportdisp.model.jsonjackson.workflow.order.JsonTransportDispWorkflowSpecificOrderFraktbrevRecord;


import no.systema.transportdisp.model.jsonjackson.workflow.order.childwindow.JsonTransportDispDangerousGoodsContainer;
import no.systema.transportdisp.model.jsonjackson.workflow.order.childwindow.JsonTransportDispDangerousGoodsRecord;
import no.systema.transportdisp.model.jsonjackson.workflow.order.childwindow.JsonTransportDispPackingCodesContainer;
import no.systema.transportdisp.model.jsonjackson.workflow.order.childwindow.JsonTransportDispPackingCodesRecord;
import no.systema.transportdisp.model.jsonjackson.workflow.order.childwindow.JsonTransportDispTollstedCodesContainer;
import no.systema.transportdisp.model.jsonjackson.workflow.order.childwindow.JsonTransportDispTollstedCodesRecord;


import no.systema.transportdisp.model.workflow.order.OrderLineValidationObject;

import no.systema.main.model.jsonjackson.general.postalcodes.JsonPostalCodesContainer;
import no.systema.main.model.jsonjackson.general.postalcodes.JsonPostalCodesRecord;

import no.systema.transportdisp.service.TransportDispChildWindowService;
import no.systema.transportdisp.service.TransportDispWorkflowBudgetService;
import no.systema.transportdisp.service.TransportDispWorkflowSpecificTripService;
import no.systema.transportdisp.service.TransportDispWorkflowSpecificOrderService;

import no.systema.transportdisp.url.store.TransportDispUrlDataStore;
import no.systema.transportdisp.util.RpgReturnResponseHandler;
import no.systema.transportdisp.util.TransportDispConstants;
import no.systema.transportdisp.util.manager.ControllerAjaxCommonFunctionsMgr;
*/

//ebooking
import no.systema.ebooking.model.jsonjackson.order.childwindow.JsonEbookingCustomerContainer;
import no.systema.ebooking.model.jsonjackson.order.childwindow.JsonEbookingCustomerRecord;
import no.systema.ebooking.service.EbookingChildWindowService;
import no.systema.ebooking.service.EbookingMainOrderHeaderService;
import no.systema.ebooking.url.store.EbookingUrlDataStore;
import no.systema.ebooking.util.EbookingConstants;
import no.systema.ebooking.util.RpgReturnResponseHandler;
import no.systema.ebooking.model.jsonjackson.JsonMainOrderHeaderCustomerDeliveryAddressContainer;
import no.systema.ebooking.model.jsonjackson.JsonMainOrderHeaderCustomerDeliveryAddressRecord;




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
		  JsonMainOrderHeaderCustomerDeliveryAddressRecord deliveryAddressRecord = getDeliveryAddress(applicationUser, customerNumber);
		  JsonEbookingCustomerRecord targetRecord = null;
		  //check if this customer has an existent Delivery address. In that case use it!
		  if(deliveryAddressRecord!=null){
			  targetRecord = new JsonEbookingCustomerRecord();
			  //Hand over the delivery address fields to the customer fields
			  targetRecord.setAuxnavn(deliveryAddressRecord.getVadrna());
			  targetRecord.setAdr1(deliveryAddressRecord.getVadrn1());
			  targetRecord.setAdr2(deliveryAddressRecord.getVadrn2());
			  targetRecord.setAdresse(deliveryAddressRecord.getVadrn3());
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
	    			for(JsonEbookingCustomerRecord  record : container.getInqcustomer()){
	    				if(record.getKundnr().equals(customerNumber)){
	    					//logger.info("CUSTOMER via AJAX: " + record.getNavn() + " NUMBER:" + record.getKundnr());
	    					if(targetRecord!=null){
	    						targetRecord.setKundnr(record.getKundnr());
	    						//Set the real customer name & land
	    						targetRecord.setNavn(record.getNavn());
	    						targetRecord.setLand(record.getLand());
	    						targetRecord.setFakknr(record.getFakknr());
	    						//DEBUG
	    						logger.info("TJINQKUND.pgm:");
	    						logger.info("navn:" + targetRecord.getNavn());
	    						logger.info("auxnavn:" + targetRecord.getAuxnavn());
	    						logger.info("fakknr:" + targetRecord.getFakknr());
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
	   * @param customerNumber
	   * @return
	   */
	  
	  private JsonMainOrderHeaderCustomerDeliveryAddressRecord getDeliveryAddress(String applicationUser, String customerNumber){
		  JsonMainOrderHeaderCustomerDeliveryAddressRecord retval = null;
		//prepare the access CGI with RPG back-end
		  String BASE_URL = EbookingUrlDataStore.EBOOKING_BASE_CHILDWINDOW_CUSTOMER_DELIVERY_ADDRESS_URL;
		  String urlRequestParamsKeys = "user=" + applicationUser + "&wkundnr=" + customerNumber + "&wvadrnr=1" ;
		  logger.info("URL: " + BASE_URL);
		  logger.info("PARAMS: " + urlRequestParamsKeys);
		  String jsonPayload = this.urlCgiProxyService.getJsonContent(BASE_URL, urlRequestParamsKeys);
		  //Should be removed as soon as RPG return the correct container name = customerlist (not capitalized in the first letter)
		  logger.info(jsonPayload);
    		  if(jsonPayload!=null){
    			  JsonMainOrderHeaderCustomerDeliveryAddressContainer container = this.ebookingMainOrderHeaderService.getDeliveryAddressContainer(jsonPayload);
	    		if(container!=null){
	    			for(JsonMainOrderHeaderCustomerDeliveryAddressRecord  record : container.getInqdeladdr()){
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
