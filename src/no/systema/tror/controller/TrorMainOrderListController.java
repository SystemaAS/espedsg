package no.systema.tror.controller;

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
import javax.servlet.http.HttpSession;
import org.springframework.web.bind.ServletRequestDataBinder;


//application imports
import no.systema.main.context.TdsAppContext;
import no.systema.main.service.UrlCgiProxyService;
import no.systema.main.validator.LoginValidator;
import no.systema.main.util.AppConstants;
import no.systema.main.util.JsonDebugger;
import no.systema.main.model.SystemaWebUser;

//EBOOKING
import no.systema.tror.model.jsonjackson.JsonTrorOrderListContainer;
import no.systema.tror.model.jsonjackson.JsonTrorOrderListRecord;

import no.systema.tror.filter.SearchFilterTrorMainList;
import no.systema.tror.service.TrorMainOrderListService;
import no.systema.tror.url.store.TrorUrlDataStore;
import no.systema.tror.util.TrorConstants;
import no.systema.tror.util.RpgReturnResponseHandler;

/**
 * Transport-Oppdragregistrering Order List Controller 
 * 
 * @author oscardelatorre
 * @date Jul 04, 2017
 * 
 */

@Controller
@SessionAttributes(AppConstants.SYSTEMA_WEB_USER_KEY)
@Scope("session")
public class TrorMainOrderListController {
	private static final JsonDebugger jsonDebugger = new JsonDebugger(1500);
	private static Logger logger = Logger.getLogger(TrorMainOrderListController.class.getName());
	private ModelAndView loginView = new ModelAndView("login");
	private ApplicationContext context;
	private LoginValidator loginValidator = new LoginValidator();
	private RpgReturnResponseHandler rpgReturnResponseHandler = new RpgReturnResponseHandler();
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
	@RequestMapping(value="tror_mainorderlist.do", params="action=doFind",  method={RequestMethod.GET, RequestMethod.POST} )
	public ModelAndView doFind(@ModelAttribute ("record") SearchFilterTrorMainList recordToValidate, BindingResult bindingResult, HttpSession session, HttpServletRequest request){
		
		this.context = TdsAppContext.getApplicationContext();
		Collection<JsonTrorOrderListRecord> outputListOpenOrders = new ArrayList<JsonTrorOrderListRecord>();

		
		Map model = new HashMap();
		//String messageFromContext = this.context.getMessage("user.label",new Object[0], request.getLocale());
		
		ModelAndView successView = new ModelAndView("tror_mainorderlist");
		SystemaWebUser appUser = this.loginValidator.getValidUser(session);
		
		//check user (should be in session already)
		if(appUser==null){
			return loginView;
			
		}else{
			appUser.setActiveMenu(SystemaWebUser.ACTIVE_MENU_TROR);
			//appUser.setUrlStoreProps(this.reflectionUrlStoreMgr.printProperties("no.systema.transportdisp.url.store.TransportDispUrlDataStore", "html")); //Debug info om UrlStore
			logger.info(Calendar.getInstance().getTime() + " CONTROLLER start - timestamp");
			//-----------
			//Validation
			//-----------
			/* TODO
			SadImportListValidator validator = new SadImportListValidator();
			logger.info("Host via HttpServletRequest.getHeader('Host'): " + request.getHeader("Host"));
		    validator.validate(recordToValidate, bindingResult);
		    */
		    //check for ERRORS
			if(bindingResult.hasErrors()){
	    		logger.info("[ERROR Validation] search-filter does not validate)");
	    		//put domain objects and do go back to the successView from here
	    		//drop downs
	    		this.setCodeDropDownMgr(appUser, model);
				//this.populateAvdelningHtmlDropDownsFromJsonString(model, appUser);
				//this.populateSignatureHtmlDropDownsFromJsonString(model, appUser);
				
				successView.addObject(TrorConstants.DOMAIN_MODEL, model);
	    		successView.addObject(TrorConstants.DOMAIN_LIST_CURRENT_ORDERS, new ArrayList());
	    		successView.addObject(TrorConstants.DOMAIN_LIST_OPEN_ORDERS, new ArrayList());
	    		successView.addObject("searchFilter", recordToValidate);
				return successView;
	    		
		    }else{
				//STEP [1]
		    	StringBuffer userAvd = new StringBuffer();
	    		outputListOpenOrders = this.getListOpenOrders(appUser, recordToValidate, model);
	    		
	   		 	//--------------------------------------
				//Final successView with domain objects
				//--------------------------------------
				//drop downs
				//this.setCodeDropDownMgr(appUser, model);
				successView.addObject(TrorConstants.DOMAIN_MODEL , model);
	    		//domain and search filter
				successView.addObject(TrorConstants.DOMAIN_LIST_OPEN_ORDERS,outputListOpenOrders);
				//Put list for upcomming view (PDF, Excel, etc)
				if(outputListOpenOrders!=null){
					session.setAttribute(session.getId() + TrorConstants.SESSION_LIST, outputListOpenOrders);
				}
				successView.addObject("searchFilter", recordToValidate);
				
				logger.info(Calendar.getInstance().getTime() + " CONTROLLER end - timestamp");
				return successView;
			    	
		    }
		}
		
	}
	
	
	/**
	 * Permanent deletion of a specific order from the order list
	 * 
	 * @param recordToValidate
	 * @param bindingResult
	 * @param session
	 * @param request
	 * @return
	 */
	/*
	
	@RequestMapping(value="tror_mainorderlist_permanently_delete_order.do",  method={RequestMethod.GET} )
	public ModelAndView doPermanentlyDeleteOrder(@ModelAttribute ("record") JsonMainOrderHeaderRecord recordToValidate, BindingResult bindingResult, HttpSession session, HttpServletRequest request){
		RpgReturnResponseHandler rpgReturnResponseHandler = new RpgReturnResponseHandler();
		Map model = new HashMap();

		logger.info("#HEUNIK:" + recordToValidate.getHeunik());
		logger.info("#HEREFF:" + recordToValidate.getHereff());
		ModelAndView errorView = new ModelAndView("tror_mainorderlist");
		StringBuffer params = new StringBuffer();
		if( (recordToValidate.getHeunik()!=null && !"".equals(recordToValidate.getHeunik())) && 
			(recordToValidate.getHereff()!=null && !"".equals(recordToValidate.getHereff())) ){	
			params.append("&heunik=" + recordToValidate.getHeunik() + "&hereff=" + recordToValidate.getHereff());
		}
		ModelAndView successView = new ModelAndView("redirect:tror_mainorderlist.do?action=doFind");
		
		SystemaWebUser appUser = this.loginValidator.getValidUser(session);
		//check user (should be in session already)
		if(appUser==null){
			return loginView;
			
		}else{
			logger.info(Calendar.getInstance().getTime() + " CONTROLLER start - timestamp");
			final String BASE_URL = EbookingUrlDataStore.EBOOKING_BASE_UPDATE_SPECIFIC_ORDER_URL;
			String urlRequestParamsKeys = "user=" + appUser.getUser() + "&mode=" + EbookingConstants.MODE_DELETE;
			StringBuffer urlRequestParamsBfr = new StringBuffer();
			urlRequestParamsBfr.append("&heunik=" + recordToValidate.getHeunik() + "&hereff=" + recordToValidate.getHereff());
			//put the final valid param. string
			String urlRequestParams = urlRequestParamsKeys + urlRequestParamsBfr.toString();
			
			logger.info("URL: " + jsonDebugger.getBASE_URL_NoHostName(BASE_URL));
	    	logger.info("URL PARAMS: " + urlRequestParams);
	    	
	    	String rpgReturnPayload = this.urlCgiProxyService.getJsonContent(BASE_URL, urlRequestParams);
	    	logger.info(Calendar.getInstance().getTime() + " CGI-stop timestamp");
	    	
	    	rpgReturnResponseHandler.evaluateRpgResponseOnUpdate(rpgReturnPayload);
	    	if(rpgReturnResponseHandler.getErrorMessage()!=null && !"".equals(rpgReturnResponseHandler.getErrorMessage())){
	    		rpgReturnResponseHandler.setErrorMessage("[ERROR] FATAL on UPDATE: " + rpgReturnResponseHandler.getErrorMessage());
	    		//this.setFatalError(model, rpgReturnResponseHandler, recordToValidate);
	    		//isValidCreatedRecordTransactionOnRPG = false;
	    		
	    	}else{
	    		//Update successfully done!
	    		logger.info("[INFO] Record successfully deleted, OK ");

	    	}

		}
		return successView;
	}
	*/
	/**
	 * 
	 * @param recordToValidate
	 * @param bindingResult
	 * @param session
	 * @param request
	 * @return
	 */
	/*
	@RequestMapping(value="tror_mainorderlist_send_order.do",  method={RequestMethod.GET} )
	public ModelAndView doSendOrder(@ModelAttribute ("record") JsonMainOrderHeaderRecord recordToValidate, BindingResult bindingResult, HttpSession session, HttpServletRequest request){
		RpgReturnResponseHandler rpgReturnResponseHandler = new RpgReturnResponseHandler();
		Map model = new HashMap();
		SystemaWebUser appUser = this.loginValidator.getValidUser(session);
		
		logger.info("#HEUNIK:" + recordToValidate.getHeunik());
		ModelAndView errorView = new ModelAndView("ebooking_mainorderlist");
		ModelAndView successView = new ModelAndView("redirect:tror_mainorderlist.do?action=doFind");

		//check user (should be in session already)
		if(appUser==null){
			return loginView;
			
		}else{
			logger.info(Calendar.getInstance().getTime() + " CONTROLLER start - timestamp");
			final String BASE_URL = EbookingUrlDataStore.EBOOKING_BASE_SEND_SPECIFIC_ORDER_URL;
			String urlRequestParamsKeys = "user=" + appUser.getUser() + "&heunik=" + recordToValidate.getHeunik();
			//put the final valid param. string
			String urlRequestParams = urlRequestParamsKeys;
			
			logger.info("URL: " + jsonDebugger.getBASE_URL_NoHostName(BASE_URL));
	    	logger.info("URL PARAMS: " + urlRequestParams);
	    	
	    	String rpgReturnPayload = this.urlCgiProxyService.getJsonContent(BASE_URL, urlRequestParams);
	    	logger.info(Calendar.getInstance().getTime() + " CGI-stop timestamp");
	    	
	    	rpgReturnResponseHandler.evaluateRpgResponseOnUpdate(rpgReturnPayload);
	    	if(rpgReturnResponseHandler.getErrorMessage()!=null && !"".equals(rpgReturnResponseHandler.getErrorMessage())){
	    		rpgReturnResponseHandler.setErrorMessage("[ERROR] FATAL on UPDATE: " + rpgReturnResponseHandler.getErrorMessage());
	    		//this.setFatalError(model, rpgReturnResponseHandler, recordToValidate);
	    		//isValidCreatedRecordTransactionOnRPG = false;
	    		
	    	}else{
	    		//Update successfully done!
	    		logger.info("[INFO] Record successfully sent, OK ");
	    	}
		}
		return successView;
	}
	*/

	
	/**
	 * 
	 * @param appUser
	 * @param wssavd
	 * @return
	 */
	private Collection<JsonTrorOrderListRecord> getListOpenOrders(SystemaWebUser appUser, SearchFilterTrorMainList recordToValidate, Map model){
		Collection<JsonTrorOrderListRecord> outputListOpenOrders = new ArrayList();
		//------------------------------------
        //[STEP 2] get Open Orders BASE URL
    		//------------------------------------
			
    		final String BASE_URL = TrorUrlDataStore.TROR_BASE_MAIN_ORDER_LIST_URL;
    		//add URL-parameters
    		StringBuffer urlRequestParams = new StringBuffer();
    		urlRequestParams.append("user=" + appUser.getUser());
    		urlRequestParams.append("&limit=500"); //make this limit dynamic
    		
    		if(!"".equals(recordToValidate.getOrderNr())&& recordToValidate.getOrderNr()!=null ){ urlRequestParams.append("&hereff=" + recordToValidate.getOrderNr()); }
    		if(!"".equals(recordToValidate.getDate())&& recordToValidate.getDate()!=null ){ urlRequestParams.append("&hedtop=" + recordToValidate.getDate()); }
    		if(!"".equals(recordToValidate.getFromDate())&& recordToValidate.getFromDate()!=null ){ urlRequestParams.append("&todoFromDate=" + recordToValidate.getFromDate()); }
    		if(!"".equals(recordToValidate.getToDate())&& recordToValidate.getToDate()!=null ){ urlRequestParams.append("&todoToDate=" + recordToValidate.getToDate()); }
    		//From and dates
    		if(!"".equals(recordToValidate.getSender())&& recordToValidate.getSender()!=null ){ urlRequestParams.append("&henas=" + recordToValidate.getSender()); }
    		if(!"".equals(recordToValidate.getReceiver())&& recordToValidate.getReceiver()!=null ){ urlRequestParams.append("&henak=" + recordToValidate.getReceiver()); }
    		if(!"".equals(recordToValidate.getFrom())&& recordToValidate.getFrom()!=null ){ urlRequestParams.append("&hesdf=" + recordToValidate.getFrom()); }
    		//To and dates
    		if(!"".equals(recordToValidate.getTo())&& recordToValidate.getTo()!=null ){ urlRequestParams.append("&hesdt=" + recordToValidate.getTo()); }
    		
    		
    		//session.setAttribute(TransportDispConstants.ACTIVE_URL_RPG_TRANSPORT_DISP, BASE_URL + "==>params: " + urlRequestParams.toString()); 
	    	logger.info(Calendar.getInstance().getTime() + " CGI-start timestamp");
	    	logger.info("URL: " + BASE_URL);
	    	logger.info("URL PARAMS: " + urlRequestParams);
	    	String jsonPayload = this.urlCgiProxyService.getJsonContent(BASE_URL, urlRequestParams.toString());
	    	//Debug --> 
	    	logger.debug(jsonDebugger.debugJsonPayloadWithLog4J(jsonPayload));
	    	logger.info(Calendar.getInstance().getTime() +  " CGI-end timestamp");
	    	if(jsonPayload!=null){
	    		JsonTrorOrderListContainer orderListContainer = this.trorMainOrderListService.getMainListContainer(jsonPayload);
	    		model.put(TrorConstants.DOMAIN_CONTAINER_OPEN_ORDERS, orderListContainer);
	    		if(orderListContainer!=null){
		    		outputListOpenOrders = orderListContainer.getDtoList();
	    		}
	    	}		

		return outputListOpenOrders;
	}
	
	/**
	 * 
	 * @param model
	 * @param record
	 */
	private void setDomainObjectsInView(Map model, SearchFilterTrorMainList record){
		//SET HEADER RECORDS  (from RPG)
		model.put(TrorConstants.DOMAIN_RECORD, record);
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
	
	@Qualifier ("trorMainOrderListService")
	private TrorMainOrderListService trorMainOrderListService;
	@Autowired
	@Required
	public void setTrorMainOrderListService (TrorMainOrderListService value){ this.trorMainOrderListService = value; }
	public TrorMainOrderListService getTrorMainOrderListService(){ return this.trorMainOrderListService; }
	
	
}

