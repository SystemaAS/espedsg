package no.systema.tror.controller;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Required;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;

import no.systema.jservices.common.dao.KodtvaDao;
import no.systema.jservices.common.dao.KufastDao;
import no.systema.jservices.common.json.JsonDtoContainer;
import no.systema.jservices.common.json.JsonReader;
import no.systema.jservices.common.values.FasteKoder;
//application imports
import no.systema.main.context.TdsAppContext;
import no.systema.main.model.SystemaWebUser;
import no.systema.main.service.UrlCgiProxyService;
import no.systema.main.util.AppConstants;
import no.systema.main.util.JsonDebugger;
import no.systema.main.util.StringManager;
import no.systema.main.validator.LoginValidator;
import no.systema.tror.filter.SearchFilterTrorMainList;
//EBOOKING
import no.systema.tror.model.jsonjackson.JsonTrorOrderListContainer;
import no.systema.tror.model.jsonjackson.JsonTrorOrderListRecord;
import no.systema.tror.service.TrorMainOrderListService;
import no.systema.tror.service.html.dropdown.TrorDropDownListPopulationService;
import no.systema.tror.url.store.TrorUrlDataStore;
import no.systema.tror.util.manager.CodeDropDownMgr;
import no.systema.tvinn.sad.sadimport.filter.SearchFilterSadImportTopicList;
import no.systema.tvinn.sad.util.TvinnSadConstants;
import no.systema.tror.util.RpgReturnResponseHandler;
import no.systema.tror.util.TrorConstants;
import no.systema.z.main.maintenance.model.jsonjackson.dbtable.JsonMaintMainChildWindowKofastContainer;
import no.systema.z.main.maintenance.model.jsonjackson.dbtable.JsonMaintMainChildWindowKofastRecord;
import no.systema.z.main.maintenance.service.MaintMainKodtaService;
import no.systema.z.main.maintenance.service.MaintMainKofastService;
import no.systema.z.main.maintenance.url.store.MaintenanceMainUrlDataStore;

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
	private StringManager strMgr = new StringManager();
	private CodeDropDownMgr codeDropDownMgr = new CodeDropDownMgr();
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
	    		successView.addObject(TrorConstants.DOMAIN_SEARCH_FILTER_TROR, recordToValidate);
				return successView;
	    		
		    }else{
		    	//Put in session for further use (within this module) ONLY with: POST method = doFind on search fields
	            if(request.getMethod().equalsIgnoreCase(RequestMethod.POST.toString())){
            		session.setAttribute(TrorConstants.SESSION_SEARCH_FILTER_TROR, recordToValidate);
            		
	            }else{
	            	SearchFilterTrorMainList sessionFilter = (SearchFilterTrorMainList)session.getAttribute(TrorConstants.SESSION_SEARCH_FILTER_TROR);
	            	if(sessionFilter!=null){
	            		//Use the session filter when applicable
	            		recordToValidate = sessionFilter;
	            		
	            	}
	            }
		    		
				//STEP [1]
		    	StringBuffer userAvd = new StringBuffer();
		    	outputListOpenOrders = this.getListOpenOrders(appUser, recordToValidate, model);
	    		
	   		 	//--------------------------------------
				//Final successView with domain objects
				//--------------------------------------
				//drop downs
				this.setCodeDropDownMgr(appUser, model);
				successView.addObject(TrorConstants.DOMAIN_MODEL , model);
	    		//domain and search filter
				successView.addObject(TrorConstants.DOMAIN_LIST_OPEN_ORDERS,outputListOpenOrders);
				//Put list for upcomming view (PDF, Excel, etc)
				if(outputListOpenOrders!=null){
					session.setAttribute(session.getId() + TrorConstants.SESSION_LIST, outputListOpenOrders);
				}
				//check filter
				if (session.getAttribute(TrorConstants.SESSION_SEARCH_FILTER_TROR) == null || session.getAttribute(TrorConstants.SESSION_SEARCH_FILTER_TROR).equals("")){
					successView.addObject(TrorConstants.DOMAIN_SEARCH_FILTER_TROR, recordToValidate);
				}
				
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
    		//user parameter dftdg (go esped-->8 (parameters).
    		if(strMgr.isNotNull(appUser.getDftdg())){
    			urlRequestParams.append("&dftdg=" + appUser.getDftdg());
    		}
    		
    		if(strMgr.isNotNull(recordToValidate.getAvd())){ urlRequestParams.append("&heavd=" + recordToValidate.getAvd()); }
    		if(strMgr.isNotNull(recordToValidate.getOrderNr())){ urlRequestParams.append("&heopd=" + recordToValidate.getOrderNr()); }
    		if(strMgr.isNotNull(recordToValidate.getSign())){ urlRequestParams.append("&hesg=" + recordToValidate.getSign()); }
    		if(strMgr.isNotNull(recordToValidate.getDate())){ urlRequestParams.append("&hedtop=" + recordToValidate.getDate()); }
    		if(strMgr.isNotNull(recordToValidate.getFromDate())){ urlRequestParams.append("&todoFromDate=" + recordToValidate.getFromDate()); }
    		if(strMgr.isNotNull(recordToValidate.getToDate())){ urlRequestParams.append("&todoToDate=" + recordToValidate.getToDate()); }
    		//From and dates
    		if(strMgr.isNotNull(recordToValidate.getSender())){ urlRequestParams.append("&henas=" + recordToValidate.getSender()); }
    		if(strMgr.isNotNull(recordToValidate.getReceiver())){ urlRequestParams.append("&henak=" + recordToValidate.getReceiver()); }
    		if(strMgr.isNotNull(recordToValidate.getFrom())){ urlRequestParams.append("&hesdf=" + recordToValidate.getFrom()); }
    		//To and dates
    		if(strMgr.isNotNull(recordToValidate.getTo())){ urlRequestParams.append("&hesdt=" + recordToValidate.getTo()); }
    		//other
    		if(strMgr.isNotNull(recordToValidate.getStatus())){ urlRequestParams.append("&hest=" + recordToValidate.getStatus()); }
    		if(strMgr.isNotNull(recordToValidate.getTtype())){ urlRequestParams.append("&heur=" + recordToValidate.getTtype()); }
    		if(strMgr.isNotNull(recordToValidate.getGodsNr())){ urlRequestParams.append("&hegn=" + recordToValidate.getGodsNr()); }
    		
    		
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
		//Sign / AVD
		this.codeDropDownMgr.populateCodesHtmlDropDownsFromJsonSignature(this.urlCgiProxyService, trorDropDownListPopulationService, model, appUser);
		this.codeDropDownMgr.populateCodesHtmlDropDownsFromJsonAvdelning(this.urlCgiProxyService, maintMainKodtaService, model, appUser);
				
		this.codeDropDownMgr.populateCodesHtmlDropDownsFromJsonString(urlCgiProxyService, trorDropDownListPopulationService, model, appUser, this.codeDropDownMgr.CODE_TYPE_DELSYSTEM);
	}

	/* @Example on KODTVA, pattern works also for KODTLK, KODTOTY, KODTFR, KODTSF  */
	private List<KodtvaDao> getValutaKoder(String applicationUser, String kundnr) {
		JsonReader<JsonDtoContainer<KodtvaDao>> jsonReader = new JsonReader<JsonDtoContainer<KodtvaDao>>();
		jsonReader.set(new JsonDtoContainer<KodtvaDao>());
		String BASE_URL = TrorUrlDataStore.TROR_BASE_KODTVA_GET_LIST_URL;
		StringBuffer urlRequestParams = new StringBuffer();
		urlRequestParams.append("user=" + applicationUser);

		logger.info(Calendar.getInstance().getTime() + " CGI-start timestamp");
		logger.info("URL: " + jsonDebugger.getBASE_URL_NoHostName(BASE_URL));
		logger.info("URL PARAMS: " + urlRequestParams);
		List<KodtvaDao> daoList = new ArrayList<KodtvaDao>();
		String jsonPayload = urlCgiProxyService.getJsonContent(BASE_URL, urlRequestParams.toString());

		if (jsonPayload != null) {
			JsonDtoContainer<KodtvaDao> container = (JsonDtoContainer<KodtvaDao>) jsonReader.get(jsonPayload);
				if (container != null) {
					logger.info("Do your thingy...or just return list");
					daoList = container.getDtoList();
				}
		}
		return daoList;
	}
	
	/* @Example on KUFAST  */
	private List<KufastDao> getProduktKoder(String applicationUser, String kundnr) {
		JsonReader<JsonDtoContainer<KufastDao>> jsonReader = new JsonReader<JsonDtoContainer<KufastDao>>();
		jsonReader.set(new JsonDtoContainer<KufastDao>());
		String BASE_URL = TrorUrlDataStore.TROR_BASE_KUFAST_GET_LIST_URL;
		StringBuffer urlRequestParams = new StringBuffer();
		urlRequestParams.append("user=" + applicationUser);
		urlRequestParams.append("&kftyp=" + FasteKoder.PRODTYPE.name());
		
		logger.info(Calendar.getInstance().getTime() + " CGI-start timestamp");
		logger.info("URL: " + jsonDebugger.getBASE_URL_NoHostName(BASE_URL));
		logger.info("URL PARAMS: " + urlRequestParams);
		List<KufastDao> daoList = new ArrayList<KufastDao>();
		String jsonPayload = urlCgiProxyService.getJsonContent(BASE_URL, urlRequestParams.toString());

		if (jsonPayload != null) {
			JsonDtoContainer<KufastDao> container = (JsonDtoContainer<KufastDao>) jsonReader.get(jsonPayload);
				if (container != null) {
					for (KufastDao dao :  container.getDtoList()) {
						if (!"DEFN".equals(dao.getKfkod())) {  //Remove header
							daoList.add(dao);
							logger.info("Do your thingy...or just return list");
						}
					}
				}
		}
		return daoList;
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
	
	@Qualifier ("trorDropDownListPopulationService")
	private TrorDropDownListPopulationService trorDropDownListPopulationService;
	@Autowired
	@Required
	public void setTrorDropDownListPopulationService (TrorDropDownListPopulationService value){ this.trorDropDownListPopulationService = value; }
	public TrorDropDownListPopulationService getTrorDropDownListPopulationService(){ return this.trorDropDownListPopulationService; }
	
	@Qualifier ("maintMainKodtaService")
	private MaintMainKodtaService maintMainKodtaService;
	@Autowired
	@Required
	public void setMaintMainKodtaService (MaintMainKodtaService value){ this.maintMainKodtaService = value; }
	public MaintMainKodtaService getMaintMainKodtaService(){ return this.maintMainKodtaService; }
	
}

