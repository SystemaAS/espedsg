package no.systema.ebooking.controller;

import java.lang.reflect.Field;
import java.util.*;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;


import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.springframework.validation.BindingResult;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Required;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Scope;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.springframework.web.bind.ServletRequestDataBinder;


//application imports
import no.systema.main.context.TdsAppContext;
import no.systema.main.service.UrlCgiProxyService;
import no.systema.main.validator.LoginValidator;
import no.systema.main.util.AppConstants;
import no.systema.main.util.JsonDebugger;
import no.systema.main.util.io.FileContentRenderer;
import no.systema.main.model.SystemaWebUser;

//TRANSPDISP
import no.systema.transportdisp.util.RpgReturnResponseHandler;
import no.systema.transportdisp.service.TransportDispWorkflowListService;
import no.systema.transportdisp.service.TransportDispWorkflowShippingPlanningOrdersListService;
import no.systema.transportdisp.service.TransportDispWorkflowSpecificOrderService;
import no.systema.transportdisp.service.TransportDispWorkflowSpecificTripService;
import no.systema.transportdisp.model.jsonjackson.workflow.order.JsonTransportDispWorkflowSpecificOrderContainer;
import no.systema.transportdisp.model.jsonjackson.workflow.order.JsonTransportDispWorkflowSpecificOrderFraktbrevContainer;
import no.systema.transportdisp.model.jsonjackson.workflow.order.JsonTransportDispWorkflowSpecificOrderFraktbrevPdfContainer;
import no.systema.transportdisp.model.jsonjackson.workflow.order.JsonTransportDispWorkflowSpecificOrderRecord;
import no.systema.transportdisp.model.jsonjackson.workflow.JsonTransportDispWorkflowSpecificTripContainer;
import no.systema.transportdisp.model.jsonjackson.workflow.JsonTransportDispWorkflowSpecificTripRecord;
import no.systema.transportdisp.model.jsonjackson.workflow.shippinglists.JsonTransportDispWorkflowShippingPlanningCurrentOrdersListContainer;
import no.systema.transportdisp.model.jsonjackson.workflow.shippinglists.JsonTransportDispWorkflowShippingPlanningCurrentOrdersListRecord;
import no.systema.transportdisp.model.jsonjackson.workflow.shippinglists.JsonTransportDispWorkflowShippingPlanningOpenOrdersListContainer;
import no.systema.transportdisp.model.jsonjackson.workflow.shippinglists.JsonTransportDispWorkflowShippingPlanningOpenOrdersListRecord;
import no.systema.transportdisp.filter.SearchFilterTransportDispWorkflowShippingPlanningOrdersList;
import no.systema.transportdisp.url.store.TransportDispUrlDataStore;
import no.systema.transportdisp.util.TransportDispConstants;
import no.systema.transportdisp.util.manager.ControllerAjaxCommonFunctionsMgr;
import no.systema.transportdisp.util.manager.java.reflect.ReflectionUrlStoreMgr;
import no.systema.z.main.maintenance.util.MainMaintenanceConstants;
//eBooking
import no.systema.ebooking.url.store.EbookingUrlDataStore;
import no.systema.ebooking.util.EbookingConstants;
import no.systema.ebooking.model.jsonjackson.JsonMainOrderHeaderContainer;
import no.systema.ebooking.model.jsonjackson.JsonMainOrderHeaderRecord;
import no.systema.ebooking.model.jsonjackson.JsonMainOrderTypesNewRecord;
import no.systema.ebooking.service.EbookingMainOrderHeaderService;





/**
 * ebooking Order Header Controller 
 * 
 * @author oscardelatorre
 * @date Jan 06, 2017
 * 
 */

@Controller
@SessionAttributes(AppConstants.SYSTEMA_WEB_USER_KEY)
@Scope("session")
public class EbookingMainOrderHeaderController {
	private static final JsonDebugger jsonDebugger = new JsonDebugger(1500);
	private static Logger logger = Logger.getLogger(EbookingMainOrderHeaderController.class.getName());
	private ModelAndView loginView = new ModelAndView("login");
	private ApplicationContext context;
	private LoginValidator loginValidator = new LoginValidator();
	private ControllerAjaxCommonFunctionsMgr controllerAjaxCommonFunctionsMgr;
	private ReflectionUrlStoreMgr reflectionUrlStoreMgr = new ReflectionUrlStoreMgr();
	@PostConstruct
	public void initIt() throws Exception {
		if("DEBUG".equals(AppConstants.LOG4J_LOGGER_LEVEL)){
			logger.setLevel(Level.DEBUG);
		}
	}
	
	/**
	 * 
	 * @param session
	 * @param request
	 * @return
	 */
	@RequestMapping(value="ebooking_mainorder.do", method={RequestMethod.GET, RequestMethod.POST} )
	public ModelAndView doFind(@ModelAttribute ("record") SearchFilterTransportDispWorkflowShippingPlanningOrdersList recordToValidate, BindingResult bindingResult, HttpSession session, HttpServletRequest request){
		
		this.context = TdsAppContext.getApplicationContext();
		Collection<JsonTransportDispWorkflowShippingPlanningOpenOrdersListRecord> outputListOpenOrders = new ArrayList<JsonTransportDispWorkflowShippingPlanningOpenOrdersListRecord>();
		String wstur = request.getParameter("wstur");
		String wssavd = request.getParameter("wssavd");
		if(wssavd!=null && !"".equals(wssavd)){ recordToValidate.setAvd(wssavd); }
		if(wstur!=null && !"".equals(wstur)){ recordToValidate.setTur(wstur); }
		//
		String hereff = request.getParameter("hereff");
		String heunik = request.getParameter("unik");
		String action = request.getParameter("action");
		String selectedTypeWithCreateNew = request.getParameter("selectedType");
		
		
		Map model = new HashMap();
		//String messageFromContext = this.context.getMessage("user.label",new Object[0], request.getLocale());
		
		ModelAndView successView = new ModelAndView("ebooking_mainorder");
		SystemaWebUser appUser = this.loginValidator.getValidUser(session);
		
		//check user (should be in session already)
		if(appUser==null){
			return loginView;
			
		}else{
			//appUser.setActiveMenu(SystemaWebUser.ACTIVE_MENU_EBOOKING);
			//appUser.setUrlStoreProps(this.reflectionUrlStoreMgr.printProperties("no.systema.transportdisp.url.store.TransportDispUrlDataStore", "html")); //Debug info om UrlStore
			logger.info(Calendar.getInstance().getTime() + " CONTROLLER start - timestamp");
			if(EbookingConstants.ACTION_UPDATE.equals(action)){
				
			}else if(EbookingConstants.ACTION_DELETE.equals(action)){
				
			}
			
			//--------------
			//Fetch record
			//--------------
			JsonMainOrderTypesNewRecord orderTypes = this.getDefaultValuesForCreateNewOrder(selectedTypeWithCreateNew); 
			JsonMainOrderHeaderRecord headerOrderRecord = this.getOrderRecord(appUser, model, orderTypes, hereff, heunik);
			model.put(MainMaintenanceConstants.DOMAIN_RECORD, headerOrderRecord);
			//populate model
			if(action==null || "".equals(action)){
				action = "doUpdate";
			}
			model.put("action", action);
			model.put("hereff", hereff);
			
			successView.addObject(MainMaintenanceConstants.DOMAIN_MODEL , model);
			
			logger.info("Host via HttpServletRequest.getHeader('Host'): " + request.getHeader("Host"));
			
			return successView;
			
			//-----------
			//Validation
			//-----------
			/* TODO
			SadImportListValidator validator = new SadImportListValidator();
			logger.info("Host via HttpServletRequest.getHeader('Host'): " + request.getHeader("Host"));
		    validator.validate(recordToValidate, bindingResult);
		    */
		    //check for ERRORS
			/*if(bindingResult.hasErrors()){
	    		logger.info("[ERROR Validation] search-filter does not validate)");
	    		//put domain objects and do go back to the successView from here
	    		//drop downs
	    		this.setCodeDropDownMgr(appUser, model);
				//this.populateAvdelningHtmlDropDownsFromJsonString(model, appUser);
				//this.populateSignatureHtmlDropDownsFromJsonString(model, appUser);
				
				successView.addObject(TransportDispConstants.DOMAIN_MODEL, model);
	    		successView.addObject(TransportDispConstants.DOMAIN_LIST_CURRENT_ORDERS, new ArrayList());
	    		successView.addObject(TransportDispConstants.DOMAIN_LIST_OPEN_ORDERS, new ArrayList());
	    		successView.addObject("searchFilter", recordToValidate);
				return successView;
	    		
		    }else{
				//STEP [1]
		    	StringBuffer userAvd = new StringBuffer();
	    		outputListOpenOrders = this.getListOpenOrders(appUser, recordToValidate, model, userAvd);
	    		
	    		
	   		 	//--------------------------------------
				//Final successView with domain objects
				//--------------------------------------
				//drop downs
				//this.setCodeDropDownMgr(appUser, model);
				successView.addObject(TransportDispConstants.DOMAIN_MODEL , model);
	    		//domain and search filter
				successView.addObject(TransportDispConstants.DOMAIN_LIST_OPEN_ORDERS,outputListOpenOrders);
				//Put list for upcomming view (PDF, Excel, etc)
				if(outputListOpenOrders!=null){
					session.setAttribute(session.getId() + TransportDispConstants.SESSION_LIST, outputListOpenOrders);
				}
				successView.addObject("searchFilter", recordToValidate);
				
				logger.info(Calendar.getInstance().getTime() + " CONTROLLER end - timestamp");
				return successView;
			    	
		    }
		    */
		}
		
	}
	
	/**
	 * 
	 * @param selectedTypeWithCreateNew
	 * @return
	 */
	private JsonMainOrderTypesNewRecord getDefaultValuesForCreateNewOrder(String selectedTypeWithCreateNew){
		JsonMainOrderTypesNewRecord record = null;
		if(selectedTypeWithCreateNew!=null && !"".equals(selectedTypeWithCreateNew)){
			String[] str = selectedTypeWithCreateNew.split("@");
			record = new JsonMainOrderTypesNewRecord();
			record.setNewAvd(str[0]);
			record.setNewModul(str[1]);
			record.setNewModul2(str[2]);
			record.setNewLandKode(str[3]);
			record.setNewSideSK(str[4]);
		}
		return record;
	}
	
	/**
	 * 
	 * @param appUser
	 * @param model
	 * @param orderTypes
	 * @param hereff
	 * @param heunik
	 * @return
	 */
	private JsonMainOrderHeaderRecord getOrderRecord(SystemaWebUser appUser, Map model, JsonMainOrderTypesNewRecord orderTypes, String hereff, String heunik ){
		JsonMainOrderHeaderRecord record = new JsonMainOrderHeaderRecord();
			
		final String BASE_URL = EbookingUrlDataStore.EBOOKING_BASE_FETCH_SPECIFIC_ORDER_URL;
		//add URL-parameters
		StringBuffer urlRequestParams = new StringBuffer();
		urlRequestParams.append("user=" + appUser.getUser() + "&mode=G");
		if( (hereff!=null && !"".equals(hereff)) && (heunik!=null && !"".equals(heunik)) ){
			urlRequestParams.append("&HEUNIK=" + heunik + "&HEREFF=" + hereff );
		}else{
			urlRequestParams.append("&HEUNIK=&HEREFF=");
		}
		//Only when new order (to get default values)
		if(orderTypes!=null){
			urlRequestParams.append("&newavd=" + orderTypes.getNewAvd() + "&newmodul=" + orderTypes.getNewModul()+ "&newmodul2=" +orderTypes.getNewModul2());
			urlRequestParams.append("&newlandkode=" + orderTypes.getNewLandKode() + "&newsidesk=" + orderTypes.getNewSideSK() + "&newtext=");
		}
		
		//session.setAttribute(TransportDispConstants.ACTIVE_URL_RPG_TRANSPORT_DISP, BASE_URL + "==>params: " + urlRequestParams.toString()); 
    	logger.info(Calendar.getInstance().getTime() + " CGI-start timestamp");
    	logger.info("URL: " + BASE_URL);
    	logger.info("URL PARAMS: " + urlRequestParams);
    	String jsonPayload = this.urlCgiProxyService.getJsonContent(BASE_URL, urlRequestParams.toString());
    	//Debug --> 
    	logger.debug(jsonDebugger.debugJsonPayloadWithLog4J(jsonPayload));
    	logger.info(Calendar.getInstance().getTime() +  " CGI-end timestamp");
    	if(jsonPayload!=null){
    		JsonMainOrderHeaderContainer container = this.ebookingMainOrderHeaderService.getContainer(jsonPayload);
    		model.put(TransportDispConstants.DOMAIN_CONTAINER_OPEN_ORDERS, container);
    		if(container!=null){
    			if(container.getOneorder()!=null){
	    			for( JsonMainOrderHeaderRecord headerRecord: container.getOneorder()){
	    				record = headerRecord;
		    		}
    			}
    		}
    	}		
    	
		return record;
	}
	
	
	
	
	/**
	 * 
	 * @param model
	 * @param record
	 */
	private void setDomainObjectsInView(Map model, SearchFilterTransportDispWorkflowShippingPlanningOrdersList record){
		//SET HEADER RECORDS  (from RPG)
		model.put(TransportDispConstants.DOMAIN_RECORD, record);
	}
	
	/**
	 * 
	 * @param appUser
	 * @param model
	 */
	private void setCodeDropDownMgr(SystemaWebUser appUser, Map model){
		/* TODO COVI Status
		 * 
		this.codeDropDownMgr.populateCodesHtmlDropDownsFromJsonString(this.urlCgiProxyService, this.tvinnSadDropDownListPopulationService,
				 model,appUser,CodeDropDownMgr.CODE_2_COUNTRY, null, null);
		*/
	}

	//SERVICES
	@Qualifier ("urlCgiProxyService")
	private UrlCgiProxyService urlCgiProxyService;
	@Autowired
	@Required
	public void setUrlCgiProxyService (UrlCgiProxyService value){ this.urlCgiProxyService = value; }
	public UrlCgiProxyService getUrlCgiProxyService(){ return this.urlCgiProxyService; }
	
	@Qualifier ("ebookingMainOrderHeaderService")
	private EbookingMainOrderHeaderService ebookingMainOrderHeaderService;
	@Autowired
	@Required
	public void setEbookingMainOrderHeaderService (EbookingMainOrderHeaderService value){ this.ebookingMainOrderHeaderService = value; }
	public EbookingMainOrderHeaderService getEbookingMainOrderHeaderService(){ return this.ebookingMainOrderHeaderService; }
	
	
}

