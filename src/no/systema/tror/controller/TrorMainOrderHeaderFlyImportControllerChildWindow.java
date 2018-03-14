package no.systema.tror.controller;

import java.util.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

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

import no.systema.main.context.TdsAppContext;
import no.systema.main.model.SystemaWebUser;
//import no.systema.main.model.jsonjackson.general.postalcodes.JsonPostalCodesContainer;
//import no.systema.main.model.jsonjackson.general.postalcodes.JsonPostalCodesRecord;
import no.systema.main.service.UrlCgiProxyService;
import no.systema.main.util.AppConstants;
import no.systema.main.util.DateTimeManager;
import no.systema.main.util.JsonDebugger;
import no.systema.main.util.StringManager;
import no.systema.main.validator.LoginValidator;

//tror
import no.systema.tror.url.store.TrorUrlDataStore;
import no.systema.tror.util.TrorConstants;
import no.systema.z.main.maintenance.model.jsonjackson.dbtable.JsonMaintMainCundfContainer;
import no.systema.z.main.maintenance.model.jsonjackson.dbtable.JsonMaintMainCundfRecord;
import no.systema.z.main.maintenance.service.MaintMainCundfService;
import no.systema.z.main.maintenance.url.store.MaintenanceMainUrlDataStore;
import no.systema.z.main.maintenance.util.MainMaintenanceConstants;
import no.systema.tror.model.jsonjackson.order.landimport.childwindow.JsonTrorSellerDeliveryAddressContainer;
import no.systema.tror.model.jsonjackson.order.landimport.childwindow.JsonTrorSellerDeliveryAddressRecord;
import no.systema.tror.model.jsonjackson.order.landimport.childwindow.JsonTrorBuyerDeliveryAddressContainer;
import no.systema.tror.model.jsonjackson.order.landimport.childwindow.JsonTrorBuyerDeliveryAddressRecord;
import no.systema.tror.model.jsonjackson.order.childwindow.JsonTrorLosseLasteStedContainer;
import no.systema.tror.model.jsonjackson.order.childwindow.JsonTrorLosseLasteStedRecord;
import no.systema.tror.model.jsonjackson.order.childwindow.JsonTrorPostalCodeContainer;
import no.systema.tror.model.jsonjackson.order.childwindow.JsonTrorPostalCodeRecord;
import no.systema.tror.service.landimport.TrorMainOrderHeaderLandimportChildwindowService;
import no.systema.tror.service.TrorMainOrderHeaderChildwindowService;
import no.systema.jservices.common.dao.DokufDao;
//
import no.systema.jservices.common.dao.KodtflpDao;
import no.systema.jservices.common.dao.KodtfsDao;
import no.systema.jservices.common.dao.Sted2Dao;
import no.systema.jservices.common.dao.KodtfrDao;
import no.systema.jservices.common.json.JsonDtoContainer;
import no.systema.jservices.common.json.JsonReader;

/**
 * 
 * @author oscardelatorre
 * @date Mar 13, 2018
 */
@Controller
@SessionAttributes(AppConstants.SYSTEMA_WEB_USER_KEY)
@Scope("session")

public class TrorMainOrderHeaderFlyImportControllerChildWindow {
	
	private static final Logger logger = Logger.getLogger(TrorMainOrderHeaderFlyImportControllerChildWindow.class.getName());
	private static final JsonDebugger jsonDebugger = new JsonDebugger(1000);
	private DateTimeManager dateTimeManager = new DateTimeManager();
	private StringManager strMgr = new StringManager();
	
	private ModelAndView loginView = new ModelAndView("login");
	private ApplicationContext context;
	private LoginValidator loginValidator = new LoginValidator();
	
	 
	
	/**
	 * 
	 * @param recordToValidate
	 * @param bindingResult
	 * @param session
	 * @param request
	 * @return
	 */
	@RequestMapping(value="tror_mainorderflyimport_childwindow_airproducts.do", params="action=doFind",  method={RequestMethod.GET, RequestMethod.POST} )
	public ModelAndView doFindAirProducts(@ModelAttribute ("record") KodtflpDao recordToValidate, BindingResult bindingResult, HttpSession session, HttpServletRequest request){
		this.context = TdsAppContext.getApplicationContext();
		logger.info("Inside: doFindAirProducts");
		Collection outputList = new ArrayList();
		Map model = new HashMap();
		ModelAndView successView = new ModelAndView("tror_mainorderflyimport_childwindow_airproducts");
		SystemaWebUser appUser = this.loginValidator.getValidUser(session);
		//check user (should be in session already)
		if(appUser==null){
			return loginView;
			
		}else{
			//appUser.setActiveMenu(SystemaWebUser.ACTIVE_MENU_FRAKTKALKULATOR);
			logger.info(Calendar.getInstance().getTime() + " CONTROLLER start - timestamp");
			
			//-----------
			//Validation
			//-----------
			/*FraktkalkulatorChildWindowSearchCustomerValidator validator = new FraktkalkulatorChildWindowSearchCustomerValidator();
			logger.info("Host via HttpServletRequest.getHeader('Host'): " + request.getHeader("Host"));
		    validator.validate(recordToValidate, bindingResult);
		    */
		    //check for ERRORS
			if(bindingResult.hasErrors()){
	    		logger.info("[ERROR Validation] search-filter does not validate)");
	    		//put domain objects and do go back to the successView from here
	    		//this.setCodeDropDownMgr(appUser, model);
	    		model.put(TrorConstants.DOMAIN_CONTAINER, recordToValidate);
				successView.addObject(TrorConstants.DOMAIN_MODEL, model);
				return successView;
	    		
		    }else{
		    	
		    	JsonReader<JsonDtoContainer<KodtflpDao>> jsonReader = new JsonReader<JsonDtoContainer<KodtflpDao>>();
				jsonReader.set(new JsonDtoContainer<KodtflpDao>());
				String BASE_URL = TrorUrlDataStore.TROR_BASE_CHILDWINDOW_AIRPRODUCTS_KODTFLP_URL;
				StringBuilder urlRequestParams = new StringBuilder();
				urlRequestParams.append("user=" + appUser.getUser());
				
				
				logger.info("URL: " + BASE_URL);
				logger.info("PARAMS: " + urlRequestParams.toString());
				String jsonPayload = urlCgiProxyService.getJsonContent(BASE_URL, urlRequestParams.toString());
				logger.info(jsonDebugger.debugJsonPayloadWithLog4J(jsonPayload));
				DokufDao record = null;
				JsonDtoContainer<KodtflpDao> container = (JsonDtoContainer<KodtflpDao>) jsonReader.get(jsonPayload);
				if (container != null) {
					for (KodtflpDao dao : container.getDtoList()) {
						outputList = container.getDtoList();
					}
					model.put("mainList", outputList);
	    			model.put(TrorConstants.DOMAIN_RECORD, recordToValidate);
				}
				successView.addObject(TrorConstants.DOMAIN_MODEL , model);
    			logger.info(Calendar.getInstance().getTime() + " CONTROLLER end - timestamp");
    			return successView;
		 
		    }
		}
	}
	/**
	 * 
	 * @param recordToValidate
	 * @param bindingResult
	 * @param session
	 * @param request
	 * @return
	 */
	@RequestMapping(value="tror_mainorderflyimport_childwindow_airlines.do", params="action=doFind",  method={RequestMethod.GET, RequestMethod.POST} )
	public ModelAndView doFindAirlines(@ModelAttribute ("record") Sted2Dao recordToValidate, BindingResult bindingResult, HttpSession session, HttpServletRequest request){
		this.context = TdsAppContext.getApplicationContext();
		logger.info("Inside: doFindAirlines");
		Collection outputList = new ArrayList();
		Map model = new HashMap();
		ModelAndView successView = new ModelAndView("tror_mainorderflyimport_childwindow_airlines");
		SystemaWebUser appUser = this.loginValidator.getValidUser(session);
		//check user (should be in session already)
		if(appUser==null){
			return loginView;
			
		}else{
			//appUser.setActiveMenu(SystemaWebUser.ACTIVE_MENU_FRAKTKALKULATOR);
			logger.info(Calendar.getInstance().getTime() + " CONTROLLER start - timestamp");
			
			//-----------
			//Validation
			//-----------
			/*FraktkalkulatorChildWindowSearchCustomerValidator validator = new FraktkalkulatorChildWindowSearchCustomerValidator();
			logger.info("Host via HttpServletRequest.getHeader('Host'): " + request.getHeader("Host"));
		    validator.validate(recordToValidate, bindingResult);
		    */
		    //check for ERRORS
			if(bindingResult.hasErrors()){
	    		logger.info("[ERROR Validation] search-filter does not validate)");
	    		//put domain objects and do go back to the successView from here
	    		//this.setCodeDropDownMgr(appUser, model);
	    		model.put(TrorConstants.DOMAIN_CONTAINER, recordToValidate);
				successView.addObject(TrorConstants.DOMAIN_MODEL, model);
				return successView;
	    		
		    }else{
		    	
		    	JsonReader<JsonDtoContainer<KodtfsDao>> jsonReader = new JsonReader<JsonDtoContainer<KodtfsDao>>();
				jsonReader.set(new JsonDtoContainer<KodtfsDao>());
				String BASE_URL = TrorUrlDataStore.TROR_BASE_CHILDWINDOW_AIRLINES_KODTFS_URL;
				StringBuilder urlRequestParams = new StringBuilder();
				urlRequestParams.append("user=" + appUser.getUser());
				
				
				logger.info("URL: " + BASE_URL);
				logger.info("PARAMS: " + urlRequestParams.toString());
				String jsonPayload = urlCgiProxyService.getJsonContent(BASE_URL, urlRequestParams.toString());
				logger.info(jsonDebugger.debugJsonPayloadWithLog4J(jsonPayload));
				DokufDao record = null;
				JsonDtoContainer<KodtfsDao> container = (JsonDtoContainer<KodtfsDao>) jsonReader.get(jsonPayload);
				if (container != null) {
					for (KodtfsDao dao : container.getDtoList()) {
						outputList = container.getDtoList();
					}
					model.put("mainList", outputList);
	    			model.put(TrorConstants.DOMAIN_RECORD, recordToValidate);
				}
				successView.addObject(TrorConstants.DOMAIN_MODEL , model);
    			logger.info(Calendar.getInstance().getTime() + " CONTROLLER end - timestamp");
    			return successView;
		 
		    }
		}
	}
	
	/**
	 * 
	 * @param recordToValidate
	 * @param bindingResult
	 * @param session
	 * @param request
	 * @return
	 */
	@RequestMapping(value="tror_mainorderflyimport_childwindow_cities.do", params="action=doFind",  method={RequestMethod.GET, RequestMethod.POST} )
	public ModelAndView doFindCities(@ModelAttribute ("record") Sted2Dao recordToValidate, BindingResult bindingResult, HttpSession session, HttpServletRequest request){
		this.context = TdsAppContext.getApplicationContext();
		logger.info("Inside: doFindCities");
		Collection outputList = new ArrayList();
		String ctype= request.getParameter("ctype");
		Map model = new HashMap();
		model.put("ctype", ctype);
		
		ModelAndView successView = new ModelAndView("tror_mainorderflyimport_childwindow_cities");
		SystemaWebUser appUser = this.loginValidator.getValidUser(session);
		//check user (should be in session already)
		if(appUser==null){
			return loginView;
			
		}else{
			//appUser.setActiveMenu(SystemaWebUser.ACTIVE_MENU_FRAKTKALKULATOR);
			logger.info(Calendar.getInstance().getTime() + " CONTROLLER start - timestamp");
			
			//-----------
			//Validation
			//-----------
			/*FraktkalkulatorChildWindowSearchCustomerValidator validator = new FraktkalkulatorChildWindowSearchCustomerValidator();
			logger.info("Host via HttpServletRequest.getHeader('Host'): " + request.getHeader("Host"));
		    validator.validate(recordToValidate, bindingResult);
		    */
		    //check for ERRORS
			if(bindingResult.hasErrors()){
	    		logger.info("[ERROR Validation] search-filter does not validate)");
	    		//put domain objects and do go back to the successView from here
	    		//this.setCodeDropDownMgr(appUser, model);
	    		model.put(TrorConstants.DOMAIN_CONTAINER, recordToValidate);
				successView.addObject(TrorConstants.DOMAIN_MODEL, model);
				return successView;
	    		
		    }else{
		    	
		    	JsonReader<JsonDtoContainer<Sted2Dao>> jsonReader = new JsonReader<JsonDtoContainer<Sted2Dao>>();
				jsonReader.set(new JsonDtoContainer<Sted2Dao>());
				String BASE_URL = TrorUrlDataStore.TROR_BASE_CHILDWINDOW_CITIES_STED2_URL;
				StringBuilder urlRequestParams = new StringBuilder();
				urlRequestParams.append("user=" + appUser.getUser());
				urlRequestParams.append("&st2kod=" + recordToValidate.getSt2kod());
				
				
				logger.info("URL: " + BASE_URL);
				logger.info("PARAMS: " + urlRequestParams.toString());
				String jsonPayload = urlCgiProxyService.getJsonContent(BASE_URL, urlRequestParams.toString());
				logger.info(jsonDebugger.debugJsonPayloadWithLog4J(jsonPayload));
				DokufDao record = null;
				JsonDtoContainer<Sted2Dao> container = (JsonDtoContainer<Sted2Dao>) jsonReader.get(jsonPayload);
				if (container != null) {
					for (Sted2Dao dao : container.getDtoList()) {
						outputList = container.getDtoList();
					}
					model.put("mainList", outputList);
	    			model.put(TrorConstants.DOMAIN_RECORD, recordToValidate);
				}
				successView.addObject(TrorConstants.DOMAIN_MODEL , model);
    			logger.info(Calendar.getInstance().getTime() + " CONTROLLER end - timestamp");
    			return successView;
		 
		    }
		}
	}
	
	/**
	 * 
	 * @param recordToValidate
	 * @param bindingResult
	 * @param session
	 * @param request
	 * @return
	 */
	@RequestMapping(value="tror_mainorderflyimport_childwindow_incoterms.do", params="action=doFind",  method={RequestMethod.GET, RequestMethod.POST} )
	public ModelAndView doFindIncoterms(@ModelAttribute ("record") KodtfrDao recordToValidate, BindingResult bindingResult, HttpSession session, HttpServletRequest request){
		this.context = TdsAppContext.getApplicationContext();
		logger.info("Inside: doFindIncoterms");
		Collection outputList = new ArrayList();
		Map model = new HashMap();
		ModelAndView successView = new ModelAndView("tror_mainorderflyimport_childwindow_incoterms");
		SystemaWebUser appUser = this.loginValidator.getValidUser(session);
		//check user (should be in session already)
		if(appUser==null){
			return loginView;
			
		}else{
			//appUser.setActiveMenu(SystemaWebUser.ACTIVE_MENU_FRAKTKALKULATOR);
			logger.info(Calendar.getInstance().getTime() + " CONTROLLER start - timestamp");
			
			//-----------
			//Validation
			//-----------
			/*FraktkalkulatorChildWindowSearchCustomerValidator validator = new FraktkalkulatorChildWindowSearchCustomerValidator();
			logger.info("Host via HttpServletRequest.getHeader('Host'): " + request.getHeader("Host"));
		    validator.validate(recordToValidate, bindingResult);
		    */
		    //check for ERRORS
			if(bindingResult.hasErrors()){
	    		logger.info("[ERROR Validation] search-filter does not validate)");
	    		//put domain objects and do go back to the successView from here
	    		//this.setCodeDropDownMgr(appUser, model);
	    		model.put(TrorConstants.DOMAIN_CONTAINER, recordToValidate);
				successView.addObject(TrorConstants.DOMAIN_MODEL, model);
				return successView;
	    		
		    }else{
		    	
		    	JsonReader<JsonDtoContainer<KodtfrDao>> jsonReader = new JsonReader<JsonDtoContainer<KodtfrDao>>();
				jsonReader.set(new JsonDtoContainer<KodtfrDao>());
				String BASE_URL = TrorUrlDataStore.TROR_BASE_CHILDWINDOW_INCOTERMS_KODTFR_URL;
				StringBuilder urlRequestParams = new StringBuilder();
				urlRequestParams.append("user=" + appUser.getUser());
				
				
				logger.info("URL: " + BASE_URL);
				logger.info("PARAMS: " + urlRequestParams.toString());
				String jsonPayload = urlCgiProxyService.getJsonContent(BASE_URL, urlRequestParams.toString());
				logger.info(jsonDebugger.debugJsonPayloadWithLog4J(jsonPayload));
				DokufDao record = null;
				JsonDtoContainer<KodtfrDao> container = (JsonDtoContainer<KodtfrDao>) jsonReader.get(jsonPayload);
				if (container != null) {
					for (KodtfrDao dao : container.getDtoList()) {
						outputList = container.getDtoList();
					}
					model.put("mainList", outputList);
	    			model.put(TrorConstants.DOMAIN_RECORD, recordToValidate);
				}
				successView.addObject(TrorConstants.DOMAIN_MODEL , model);
    			logger.info(Calendar.getInstance().getTime() + " CONTROLLER end - timestamp");
    			return successView;
		 
		    }
		}
	}
	
	//SERVICES
	@Qualifier ("urlCgiProxyService")
	private UrlCgiProxyService urlCgiProxyService;
	@Autowired
	@Required
	public void setUrlCgiProxyService (UrlCgiProxyService value){ this.urlCgiProxyService = value; }
	public UrlCgiProxyService getUrlCgiProxyService(){ return this.urlCgiProxyService; }
	
	
	@Qualifier ("trorMainOrderHeaderLandimportChildwindowService")
	private TrorMainOrderHeaderLandimportChildwindowService trorMainOrderHeaderLandimportChildwindowService;
	@Autowired
	@Required
	public void setTrorMainOrderHeaderLandimportChildwindowService (TrorMainOrderHeaderLandimportChildwindowService value){ this.trorMainOrderHeaderLandimportChildwindowService = value; }
	public TrorMainOrderHeaderLandimportChildwindowService getTrorMainOrderHeaderLandimportChildwindowService(){ return this.trorMainOrderHeaderLandimportChildwindowService; }
	
	
	@Qualifier ("trorMainOrderHeaderChildwindowService")
	private TrorMainOrderHeaderChildwindowService trorMainOrderHeaderChildwindowService;
	@Autowired
	@Required
	public void setTrorMainOrderHeaderChildwindowService (TrorMainOrderHeaderChildwindowService value){ this.trorMainOrderHeaderChildwindowService = value; }
	public TrorMainOrderHeaderChildwindowService getTrorMainOrderHeaderChildwindowService(){ return this.trorMainOrderHeaderChildwindowService; }
	
	
	@Qualifier 
	private MaintMainCundfService maintMainCundfService;
	@Autowired
	@Required	
	public void setMaintMainCundfService(MaintMainCundfService value){this.maintMainCundfService = value;}
	public MaintMainCundfService getMaintMainCundfService(){ return this.maintMainCundfService; }
	
	
}
