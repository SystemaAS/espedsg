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
import no.systema.transportdisp.model.jsonjackson.workflow.JsonTransportDispWorkflowSpecificTripMessageNoteRecord;
import no.systema.z.main.maintenance.model.jsonjackson.dbtable.sad.JsonMaintMainStandiContainer;
import no.systema.z.main.maintenance.model.jsonjackson.dbtable.sad.JsonMaintMainStandiRecord;
import no.systema.z.main.maintenance.url.store.MaintenanceMainUrlDataStore;
import no.systema.z.main.maintenance.util.MainMaintenanceConstants;

//TRANSPDISP
/*
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
*/
//import no.systema.transportdisp.filter.SearchFilterTransportDispWorkflowShippingPlanningOrdersList;
//import no.systema.transportdisp.util.manager.ControllerAjaxCommonFunctionsMgr;
//import no.systema.transportdisp.util.manager.java.reflect.ReflectionUrlStoreMgr;
//import no.systema.z.main.maintenance.util.MainMaintenanceConstants;
//eBooking
import no.systema.ebooking.url.store.EbookingUrlDataStore;
import no.systema.ebooking.util.EbookingConstants;
import no.systema.ebooking.util.RpgReturnResponseHandler;
import no.systema.ebooking.util.manager.CodeDropDownMgr;
import no.systema.ebooking.model.jsonjackson.JsonMainOrderHeaderContainer;
import no.systema.ebooking.model.jsonjackson.JsonMainOrderHeaderRecord;
import no.systema.ebooking.model.jsonjackson.JsonMainOrderTypesNewRecord;
import no.systema.ebooking.service.EbookingMainOrderHeaderService;
import no.systema.ebooking.service.html.dropdown.EbookingDropDownListPopulationService;
import no.systema.ebooking.mapper.url.request.UrlRequestParameterMapper;
import no.systema.ebooking.validator.OrderHeaderValidator;






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
	private static final JsonDebugger jsonDebugger = new JsonDebugger(4000);
	private static Logger logger = Logger.getLogger(EbookingMainOrderHeaderController.class.getName());
	private ModelAndView loginView = new ModelAndView("login");
	private ApplicationContext context;
	private LoginValidator loginValidator = new LoginValidator();
	//
	private CodeDropDownMgr codeDropDownMgr = new CodeDropDownMgr();
	private UrlRequestParameterMapper urlRequestParameterMapper = new UrlRequestParameterMapper();
	private RpgReturnResponseHandler rpgReturnResponseHandler = new RpgReturnResponseHandler();
	//private ControllerAjaxCommonFunctionsMgr controllerAjaxCommonFunctionsMgr;
	//private ReflectionUrlStoreMgr reflectionUrlStoreMgr = new ReflectionUrlStoreMgr();
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
	public ModelAndView doFind(@ModelAttribute ("record") JsonMainOrderHeaderRecord recordToValidate, BindingResult bindingResult, HttpSession session, HttpServletRequest request){
		this.context = TdsAppContext.getApplicationContext();
		Map model = new HashMap();
		
		String hereff = request.getParameter("hereff");
		String heunik = request.getParameter("heunik");
		String action = request.getParameter("action");
		boolean isValidRecord = true;
		//special case on Create New comming from the order list "Create new order"
		String selectedTypeWithCreateNew = request.getParameter("selectedType");
		JsonMainOrderTypesNewRecord orderTypes = this.getDefaultValuesForCreateNewOrder(model, selectedTypeWithCreateNew); 
		
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
				//Validation here TODO ... 
				//don't forget model.put("selectedTypeWithCreateNew", selectedTypeWithCreateNew) --> if selectedTypeWithCreateNew!=null...
				//...
				OrderHeaderValidator validator = new OrderHeaderValidator();
				logger.info("Host via HttpServletRequest.getHeader('Host'): " + request.getHeader("Host"));
			    validator.validate(recordToValidate, bindingResult);
			    if(bindingResult.hasErrors()){
		    		logger.info("[ERROR Validation] record does not validate)");
		    		model.put(EbookingConstants.DOMAIN_RECORD, recordToValidate);
		    		isValidRecord = false;
		    		
		    		
		    		//put domain objects and do go back to the successView from here
		    		//drop downs
		    		//this.setCodeDropDownMgr(appUser, model);
					//this.populateAvdelningHtmlDropDownsFromJsonString(model, appUser);
					//this.populateSignatureHtmlDropDownsFromJsonString(model, appUser);
					
		    		//set aspects
		    		
					//successView.addObject(EbookingConstants.DOMAIN_MODEL, model);
		    		//successView.addObject(EbookingConstants.DOMAIN_LIST_CURRENT_ORDERS, new ArrayList());
		    		//successView.addObject(EbookingConstants.DOMAIN_LIST_OPEN_ORDERS, new ArrayList());
		    		//successView.addObject("searchFilter", recordToValidate);
		    		
			    }else{	
					//Start DML operations if applicable
					StringBuffer errMsg = new StringBuffer();
					int dmlRetval = 0;
					if(recordToValidate.getHeunik()!=null && !"".equals(recordToValidate.getHeunik())){
						//update
						logger.info("doUpdate");
						dmlRetval = this.updateRecord(model, appUser.getUser(), recordToValidate, EbookingConstants.MODE_UPDATE, errMsg);
						if(dmlRetval==0){
							//TODO
						}
					}else{
						//create new
						logger.info("doCreate");
						dmlRetval = this.updateRecord(model, appUser.getUser(), recordToValidate, EbookingConstants.MODE_ADD, errMsg);
						model.put("selectType", "");
					}
			    }
				
			}else if(EbookingConstants.ACTION_DELETE.equals(action)){
				
			}
			
			//--------------
			//Fetch record
			//--------------
			if(isValidRecord){
				JsonMainOrderHeaderRecord headerOrderRecord = this.getOrderRecord(appUser, model, orderTypes, hereff, heunik);
				model.put(EbookingConstants.DOMAIN_RECORD, headerOrderRecord);
			}
			//get dropdowns
			this.setCodeDropDownMgr(appUser, model);
			//populate model
			if(action==null || "".equals(action)){
				action = "doUpdate";
			}
			model.put("action", action);
			successView.addObject(EbookingConstants.DOMAIN_MODEL , model);
			
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
	 * @param model
	 * @param applicationUser
	 * @param recordToValidate
	 * @param mode
	 * @param errMsg
	 * @return
	 */
	private int updateRecord(Map model, String applicationUser, JsonMainOrderHeaderRecord recordToValidate, String mode, StringBuffer errMsg){
		int retval = 0;
		
		final String BASE_URL = EbookingUrlDataStore.EBOOKING_BASE_UPDATE_SPECIFIC_ORDER_URL;
		String urlRequestParamsKeys = "user=" + applicationUser + "&mode=" + mode;
		String urlRequestParams = this.urlRequestParameterMapper.getUrlParameterValidString((recordToValidate));
		//put the final valid param. string
		urlRequestParams = urlRequestParamsKeys + urlRequestParams;
		
		logger.info(Calendar.getInstance().getTime() + " CGI-start timestamp");
    	logger.info("URL: " + jsonDebugger.getBASE_URL_NoHostName(BASE_URL));
    	logger.info("URL PARAMS: " + urlRequestParams);
    	
    	/*
    	String rpgReturnPayload = this.urlCgiProxyService.getJsonContent(BASE_URL, urlRequestParams);
    	
    	rpgReturnResponseHandler.evaluateRpgResponseOnUpdate(rpgReturnPayload);
    	if(rpgReturnResponseHandler.getErrorMessage()!=null && !"".equals(rpgReturnResponseHandler.getErrorMessage())){
    		rpgReturnResponseHandler.setErrorMessage("[ERROR] FATAL on UPDATE: " + rpgReturnResponseHandler.getErrorMessage());
    		//this.setFatalError(model, rpgReturnResponseHandler, recordToValidate);
    		//isValidCreatedRecordTransactionOnRPG = false;
    		//retval = -1; 
    		
    	}else{
    		//Update successfully done!
    		logger.info("[INFO] Record successfully updated, OK ");
    		
    		/*TODO messages ...
    		this.updateMessageNote(messageNote, recordToValidate.getTuavd(), recordToValidate.getTupro(), appUser);
    		
    		//set message note (after update aka refresh)
    		Collection<JsonTransportDispWorkflowSpecificTripMessageNoteRecord> messageNoteAfterUpdate = null;
    		messageNoteAfterUpdate = this.controllerAjaxCommonFunctionsMgr.fetchMessageNote(appUser.getUser(), recordToValidate.getTuavd(), recordToValidate.getTupro());
    		StringBuffer br = new StringBuffer();
    		for(JsonTransportDispWorkflowSpecificTripMessageNoteRecord record:messageNoteAfterUpdate ){
    			br.append(record.getFrttxt() + "\n");
    		}
    		recordToValidate.setMessageNote(br.toString());
    		//logger.info(recordToValidate.getMessageNote());
    		 
			//put domain objects
	    	this.setDomainObjectsInView(session, model, recordToValidate );
	    	
    	}
    	

    	/*
    	if(jsonPayload!=null){
			//lists
    		JsonMaintMainStandiContainer container = this.maintMainStandiService.doUpdate(jsonPayload);
	        if(container!=null){
	        	if(container.getErrMsg()!=null && !"".equals(container.getErrMsg())){
	        		if(container.getErrMsg().toUpperCase().startsWith("ERROR")){
	        			errMsg.append(container.getErrMsg());
	        			retval = MainMaintenanceConstants.ERROR_CODE;
	        		}
	        	}
	        }
    	} 
    	*/   	
    	return retval;
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
			urlRequestParams.append("&newavd=" + orderTypes.getNewAvd() + "&newmodul=" + orderTypes.getNewModul()+ "&newmodul2=" + orderTypes.getNewModul2());
			urlRequestParams.append("&newlandkode=" + orderTypes.getNewLandKode() + "&newsidesk=" + orderTypes.getNewSideSK() + "&newtext=" +  orderTypes.getNewText());
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
    		model.put(EbookingConstants.DOMAIN_CONTAINER_OPEN_ORDERS, container);
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
	private void setDomainObjectsInView(Map model, JsonMainOrderHeaderRecord record){
		//model.put(EbookingConstants.DOMAIN_RECORD, record);
	}
	
	/**
	 * 
	 * @param appUser
	 * @param model
	 */
	private void setCodeDropDownMgr(SystemaWebUser appUser, Map model){
		this.codeDropDownMgr.populateCodesHtmlDropDownsFromJsonString(this.urlCgiProxyService, this.ebookingDropDownListPopulationService,
				 model,appUser,CodeDropDownMgr.CODE_2_COUNTRY, null, null);
		
	}


	/**
	 * 
	 * @param model
	 * @param selectedTypeWithCreateNew
	 * @return
	 */
	private JsonMainOrderTypesNewRecord getDefaultValuesForCreateNewOrder(Map model, String selectedTypeWithCreateNew){
		final String FIELD_SEPARATOR = "@";
		JsonMainOrderTypesNewRecord record = new JsonMainOrderTypesNewRecord();
		//this will be true ONLY when the record is new. Normal Updates of existent records will not be in this category...
		if(selectedTypeWithCreateNew!=null && !"".equals(selectedTypeWithCreateNew)){
			if(selectedTypeWithCreateNew.contains(FIELD_SEPARATOR)){
				String[] str = selectedTypeWithCreateNew.split(FIELD_SEPARATOR);
				if(str.length==6){
					record = new JsonMainOrderTypesNewRecord();
					record.setNewAvd(str[0]);
					record.setNewModul(str[1]);
					record.setNewModul2(str[2]);
					record.setNewLandKode(str[3]);
					record.setNewSideSK(str[4]);
					record.setNewText(str[5]);
					//save to future validation errors
					model.put("selectedType", selectedTypeWithCreateNew);
				}
				
			}
		}
		return record;
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
	
	
	@Qualifier ("ebookingDropDownListPopulationService")
	private EbookingDropDownListPopulationService ebookingDropDownListPopulationService;
	@Autowired
	@Required
	public void setEbookingDropDownListPopulationService (EbookingDropDownListPopulationService value){ this.ebookingDropDownListPopulationService = value; }
	public EbookingDropDownListPopulationService getEbookingDropDownListPopulationService(){ return this.ebookingDropDownListPopulationService; }
	
	
}

