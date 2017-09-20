/**
 * 
 */
package no.systema.tror.controller.ajax;

import java.util.*;


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


import no.systema.transportdisp.model.jsonjackson.workflow.order.invoice.JsonTransportDispWorkflowSpecificOrderInvoiceContainer;
import no.systema.transportdisp.model.jsonjackson.workflow.order.invoice.JsonTransportDispWorkflowSpecificOrderInvoiceRecord;
import no.systema.transportdisp.model.jsonjackson.workflow.order.invoice.childwindow.JsonTransportDispSupplierContainer;
import no.systema.transportdisp.model.jsonjackson.workflow.order.invoice.childwindow.JsonTransportDispSupplierRecord;
import no.systema.transportdisp.service.TransportDispChildWindowService;
import no.systema.transportdisp.service.TransportDispWorkflowSpecificTripService;
import no.systema.transportdisp.service.TransportDispWorkflowSpecificOrderService;

import no.systema.transportdisp.url.store.TransportDispUrlDataStore;
import no.systema.transportdisp.util.RpgReturnResponseHandler;
import no.systema.transportdisp.util.manager.ControllerAjaxCommonFunctionsMgr;

import no.systema.tror.model.jsonjackson.frisokvei.JsonTrorOrderHeaderFrisokveiContainer;
import no.systema.tror.service.TrorMainOrderHeaderService;
import no.systema.tror.url.store.TrorUrlDataStore;

import no.systema.tror.model.jsonjackson.budget.JsonTrorOrderHeaderBudgetContainer; 
import no.systema.tror.model.jsonjackson.budget.JsonTrorOrderHeaderBudgetRecord; 
/**
 * This Ajax handler is the class handling ajax request in TROR (Oppdragsreg.) - Landimport. 
 * It will usually be called from within a jQuery function or other javascript alike... 
 * 
 * @author oscardelatorre
 * @date Sep 6, 2017
 * 
 */

@Controller

public class TrorMainOrderHeaderLandImportAjaxHandlerController {
	private static final Logger logger = Logger.getLogger(TrorMainOrderHeaderLandImportAjaxHandlerController.class.getName());
	private RpgReturnResponseHandler rpgReturnResponseHandler = new RpgReturnResponseHandler();
	private ControllerAjaxCommonFunctionsMgr controllerAjaxCommonFunctionsMgr;
	
	
	  
	  
	  /**
	   * Gets a specific invoice line
	   * This method is to be ported to a real Landimport module (migration project).
	   * Right now we borrow all the functionality from Transp.Disp... (AS400 services)
	   * 
	   * @param applicationUser
	   * @param requestString
	   * @return
	   * 
	   */
		@RequestMapping(value = "getOrderInvoiceDetailLine_Landimport.do", method = RequestMethod.GET)
	    public @ResponseBody List<JsonTransportDispWorkflowSpecificOrderInvoiceRecord> getOrderInvoiceDetailLine
		  						(@RequestParam String applicationUser, @RequestParam String requestString){
			 logger.info("Inside: getOrderInvoiceDetailLine");
			 List<JsonTransportDispWorkflowSpecificOrderInvoiceRecord> result = new ArrayList<JsonTransportDispWorkflowSpecificOrderInvoiceRecord>();
			 //logger.info(requestString);
			 if(requestString!=null && !"".equals(requestString)){
			 	 final String BASE_URL = TransportDispUrlDataStore.TRANSPORT_DISP_BASE_WORKFLOW_FETCH_MAIN_ORDER_INVOICE_URL;
			 	 //http://gw.systema.no/sycgip/TJGE25R.pgm?user=JOVO&avd=80&opd=201523&lin=&type=A
			 	 
				 //add URL-parameters
				 String urlRequestParams = requestString;
				 logger.info(Calendar.getInstance().getTime() + " CGI-start timestamp");
				 logger.info("URL: " + BASE_URL);
				 logger.info("URL PARAMS: " + urlRequestParams);
				 String jsonPayload = this.urlCgiProxyService.getJsonContent(BASE_URL, urlRequestParams);
				 
				 if(jsonPayload!=null){
					 JsonTransportDispWorkflowSpecificOrderInvoiceContainer container = this.transportDispWorkflowSpecificOrderService.getOrderInvoiceContainer(jsonPayload);
		    		if(container!=null){
		    			List<JsonTransportDispWorkflowSpecificOrderInvoiceRecord> list = new ArrayList();
		    			for(JsonTransportDispWorkflowSpecificOrderInvoiceRecord  record : container.getInvoiceLines()){
		    				//logger.info("fask:" + record.getFask());
		    				list.add(record);
		    			}
		    			result = list;
		    		}
		    	  }
			 }
			 return result;
		}
		
		/**
		 * 
		 * @param applicationUser
		 * @param id
		 * @return
		 */
		/*
		@RequestMapping(value = "validateSupplierInvoiceDetailLine_TransportDisp.do", method = RequestMethod.GET)
	    public @ResponseBody List<JsonTransportDispSupplierRecord> validateSupplierInvoiceDetailLine
		  						(@RequestParam String applicationUser, @RequestParam String id){
			 logger.info("Inside: validateSupplierInvoiceDetailLine");
			 List<JsonTransportDispSupplierRecord> result = new ArrayList<JsonTransportDispSupplierRecord>();
			 //logger.info(id);
			 
		 	 final String BASE_URL = TransportDispUrlDataStore.TRANSPORT_DISP_BASE_CHILDWINDOW_SUPPLIER_URL;
		 	 //http://gw.systema.no/sycgip/TJGE25R.pgm?user=JOVO&avd=80&opd=201523&lin=&type=A
		 	 
			 //add URL-parameters
			 String urlRequestParams = "user=" + applicationUser + "&kode=" + id + "&getval=J";
			 logger.info(Calendar.getInstance().getTime() + " CGI-start timestamp");
			 logger.info("URL: " + BASE_URL);
			 logger.info("URL PARAMS: " + urlRequestParams);
			 String jsonPayload = this.urlCgiProxyService.getJsonContent(BASE_URL, urlRequestParams);
			 
			 if(jsonPayload!=null){
				JsonTransportDispSupplierContainer container = this.transportDispChildWindowService.getSupplierContainer(jsonPayload);
	    		if(container!=null){
	    			List<JsonTransportDispSupplierRecord> list = new ArrayList();
	    			for(JsonTransportDispSupplierRecord  record : container.getLeverandorer()){
	    				//logger.info("supplier:" + record.getLevnr() + " " + record.getLnavn());
	    				list.add(record);
	    			}
	    			result = list;
	    		}
	    	  }
			
			 return result;
		}
		*/
		
		@RequestMapping(value = "validateSupplier_Landimport.do", method = RequestMethod.GET)
	    public @ResponseBody List<JsonTransportDispSupplierRecord> validateSupplier
		  						(@RequestParam String applicationUser, @RequestParam String id){
			 logger.info("Inside: validateSupplier");
			 List<JsonTransportDispSupplierRecord> result = new ArrayList<JsonTransportDispSupplierRecord>();
			 //logger.info(id);
			 
		 	 final String BASE_URL = TransportDispUrlDataStore.TRANSPORT_DISP_BASE_CHILDWINDOW_SUPPLIER_URL;
		 	 //http://gw.systema.no/sycgip/TJGE25R.pgm?user=JOVO&avd=80&opd=201523&lin=&type=A
		 	 
			 //add URL-parameters
			 String urlRequestParams = "user=" + applicationUser + "&kode=" + id + "&getval=J";
			 logger.info(Calendar.getInstance().getTime() + " CGI-start timestamp");
			 logger.info("URL: " + BASE_URL);
			 logger.info("URL PARAMS: " + urlRequestParams);
			 String jsonPayload = this.urlCgiProxyService.getJsonContent(BASE_URL, urlRequestParams);
			 
			 if(jsonPayload!=null){
				JsonTransportDispSupplierContainer container = this.transportDispChildWindowService.getSupplierContainer(jsonPayload);
	    		if(container!=null){
	    			List<JsonTransportDispSupplierRecord> list = new ArrayList();
	    			for(JsonTransportDispSupplierRecord  record : container.getLeverandorer()){
	    				//logger.info("supplier:" + record.getLevnr() + " " + record.getLnavn());
	    				list.add(record);
	    			}
	    			result = list;
	    		}
	    	  }
			
			 return result;
		}
		

		/**
		 * 
		 * @param applicationUser
		 * @param id
		 * @return
		 */
		/*
		@RequestMapping(value = "validateCustomerInvoiceDetailLine_TransportDisp.do", method = RequestMethod.GET)
	    public @ResponseBody List<JsonTransportDispCustomerRecord> validateCustomerDetailLine
		  						(@RequestParam String applicationUser, @RequestParam String id){
			 logger.info("Inside: validateCustomerInvoiceDetailLine");
			 List<JsonTransportDispCustomerRecord> result = new ArrayList<JsonTransportDispCustomerRecord>();
			 //logger.info(id);
			 
		 	 final String BASE_URL = TransportDispUrlDataStore.TRANSPORT_DISP_BASE_CHILDWINDOW_CUSTOMER_URL;
		 	 //http://gw.systema.no/sycgip/TJGE25R.pgm?user=JOVO&avd=80&opd=201523&lin=&type=A
		 	 
			 //add URL-parametersƒ
			 String urlRequestParams = "user=" + applicationUser + "&sokknr=" + id + "&getval=J";
			 logger.info(Calendar.getInstance().getTime() + " CGI-start timestamp");
			 logger.info("URL: " + BASE_URL);
			 logger.info("URL PARAMS: " + urlRequestParams);
			 String jsonPayload = this.urlCgiProxyService.getJsonContent(BASE_URL, urlRequestParams);
			 
			 if(jsonPayload!=null){
				 JsonTransportDispCustomerContainer container = this.transportDispChildWindowService.getCustomerContainer(jsonPayload);
	    		if(container!=null){
	    			List<JsonTransportDispCustomerRecord> list = new ArrayList();
	    			for(JsonTransportDispCustomerRecord  record : container.getInqcustomer()){
	    				logger.info("customer aktkod:" + record.getAktkod());
	    				list.add(record);
	    			}
	    			result = list;
	    		}
	    	  }
			
			 return result;
		}
		*/
		/**
		 * 
		 * @param applicationUser
		 * @param avd
		 * @param opd
		 * @return
		 */
		/*
		@RequestMapping(value = "updateReadyMarkInvoice.do", method = RequestMethod.GET)
	 	public @ResponseBody List<JsonTransportDispWorkflowSpecificOrderInvoiceReadyMarkContainer> doReadyMarkInvoice (@RequestParam String applicationUser, @RequestParam String avd, @RequestParam String opd){
			logger.info("Inside: validateCustomerInvoiceDetailLine");
			List<JsonTransportDispWorkflowSpecificOrderInvoiceReadyMarkContainer> result = new ArrayList<JsonTransportDispWorkflowSpecificOrderInvoiceReadyMarkContainer>();
			
			logger.info(" Ready mark start process... ");
			String BASE_URL = TransportDispUrlDataStore.TRANSPORT_DISP_BASE_WORKFLOW_UPDATE_STATUS_READYMARK_MAIN_ORDER_INVOICE_URL;
	    	//add URL-parameters
			StringBuffer urlRequestParams = new StringBuffer();
			urlRequestParams.append("user=" + applicationUser); 
			urlRequestParams.append("&avd=" + avd);
			urlRequestParams.append("&opd=" + opd);
			urlRequestParams.append("&mode=");
			
			logger.info("URL: " + BASE_URL);
			logger.info("PARAMS: " + urlRequestParams);
			logger.info(Calendar.getInstance().getTime() +  " CGI-start timestamp");
			String jsonPayload = this.urlCgiProxyService.getJsonContent(BASE_URL, urlRequestParams.toString());
			//Debug -->
			logger.info(jsonPayload);
			logger.info(Calendar.getInstance().getTime() +  " CGI-end timestamp");
		
			if(jsonPayload!=null){
				JsonTransportDispWorkflowSpecificOrderInvoiceReadyMarkContainer container = this.transportDispWorkflowSpecificOrderService.getOrderInvoiceReadyMarkContainer(jsonPayload);
				result.add(container);
			}
			return result;
		}
		*/
			
	/**
	   * Gets a specific invoice line
	   * This method is to be ported to a real Landimport module (migration project).
	   * Right now we borrow all the functionality from Transp.Disp... (AS400 services)
	   
	   * @param applicationUser
	   * @param requestString
	   * @return
	   */
	
		@RequestMapping(value = "getBudgetDetailLine_Landimport.do", method = RequestMethod.GET)
	    public @ResponseBody List<JsonTrorOrderHeaderBudgetRecord> getBudgetDetailLine
		  						(@RequestParam String applicationUser, @RequestParam String requestString){
			 logger.info("Inside: getBudgetDetailLine");
			 List<JsonTrorOrderHeaderBudgetRecord> result = new ArrayList<JsonTrorOrderHeaderBudgetRecord>();
			 //logger.info(requestString);
			 if(requestString!=null && !"".equals(requestString)){
			 	 final String BASE_URL = TrorUrlDataStore.TROR_BASE_FETCH_MAIN_ORDER_BUDGET_URL;
			 	 
				 //add URL-parameters
				 String urlRequestParams = requestString;
				 logger.info(Calendar.getInstance().getTime() + " CGI-start timestamp");
				 logger.info("URL: " + BASE_URL);
				 logger.info("URL PARAMS: " + urlRequestParams);
				 String jsonPayload = this.urlCgiProxyService.getJsonContent(BASE_URL, urlRequestParams);
				 
				 if(jsonPayload!=null){
					 JsonTrorOrderHeaderBudgetContainer container = this.trorMainOrderHeaderService.getBudgetContainer(jsonPayload);
		    		if(container!=null){
		    			List<JsonTrorOrderHeaderBudgetRecord> list = new ArrayList();
		    			for(JsonTrorOrderHeaderBudgetRecord  record : container.getBudgetLines()){
		    				logger.info(record.getBubnr());
		    				list.add(record);
		    			}
		    			result = list;
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
		
		@RequestMapping(value = "getFrisokveiDetailLine_Landimport.do", method = RequestMethod.GET)
	    public @ResponseBody List<JsonTrorOrderHeaderFrisokveiContainer> getFrisokveiDetailLine
		  						(@RequestParam String applicationUser, @RequestParam String requestString){
			 logger.info("Inside: getFrisokveiDetailLine");
			 List<JsonTrorOrderHeaderFrisokveiContainer> result = new ArrayList<JsonTrorOrderHeaderFrisokveiContainer>();
			 //logger.info(requestString);
			 if(requestString!=null && !"".equals(requestString)){
			 	 final String BASE_URL = TrorUrlDataStore.TROR_BASE_UPDATE_MAIN_ORDER_FRISOKVEI_URL;
			 	 
				 //add URL-parameters
				 String urlRequestParams = requestString;
				 logger.info(Calendar.getInstance().getTime() + " CGI-start timestamp");
				 logger.info("URL: " + BASE_URL);
				 logger.info("URL PARAMS: " + urlRequestParams);
				 String jsonPayload = this.urlCgiProxyService.getJsonContent(BASE_URL, urlRequestParams);
				 
				 if(jsonPayload!=null){
					 JsonTrorOrderHeaderFrisokveiContainer container = this.trorMainOrderHeaderService.getOrderFrisokveiContainer(jsonPayload);
		    		if(container!=null){
		    			result.add(container);
		    		}
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
	  
	  @Qualifier 
	  private TransportDispWorkflowSpecificTripService transportDispWorkflowSpecificTripService;
	  @Autowired
	  @Required	
	  public void setTransportDispWorkflowSpecificTripService(TransportDispWorkflowSpecificTripService value){this.transportDispWorkflowSpecificTripService = value;}
	  public TransportDispWorkflowSpecificTripService getTransportDispWorkflowSpecificTripService(){ return this.transportDispWorkflowSpecificTripService; }
	  
	  
	  @Qualifier 
	  private TransportDispChildWindowService transportDispChildWindowService;
	  @Autowired
	  @Required	
	  public void setTransportDispChildWindowService(TransportDispChildWindowService value){this.transportDispChildWindowService = value;}
	  public TransportDispChildWindowService getTransportDispChildWindowService(){ return this.transportDispChildWindowService; }
	   
	  @Qualifier 
	  private TransportDispWorkflowSpecificOrderService transportDispWorkflowSpecificOrderService;
	  @Autowired
	  @Required	
	  public void setTransportDispWorkflowSpecificOrderService(TransportDispWorkflowSpecificOrderService value){this.transportDispWorkflowSpecificOrderService = value;}
	  public TransportDispWorkflowSpecificOrderService getTransportDispWorkflowSpecificOrderService(){ return this.transportDispWorkflowSpecificOrderService; }
		
	  @Qualifier ("trorMainOrderHeaderService")
	  private TrorMainOrderHeaderService trorMainOrderHeaderService;
	  @Autowired
	  public void setTrorMainOrderHeaderService (TrorMainOrderHeaderService value){ this.trorMainOrderHeaderService=value; }
	  public TrorMainOrderHeaderService getTrorMainOrderHeaderService(){return this.trorMainOrderHeaderService;}
		
		
	  
}
