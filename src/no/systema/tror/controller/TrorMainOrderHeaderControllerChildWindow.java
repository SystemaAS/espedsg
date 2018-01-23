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
import no.systema.tvinn.sad.z.maintenance.sadimport.url.store.TvinnSadMaintenanceImportUrlDataStoreGyldigeKoder;
import no.systema.z.main.maintenance.model.jsonjackson.dbtable.JsonMaintMainKodtaContainer;
import no.systema.z.main.maintenance.model.jsonjackson.dbtable.JsonMaintMainKodtaRecord;
import no.systema.z.main.maintenance.service.MaintMainKodtaService;
import no.systema.z.main.maintenance.url.store.MaintenanceMainUrlDataStore;
import no.systema.tvinn.sad.z.maintenance.nctsexport.service.MaintNctsExportTrkodfService;
import no.systema.tvinn.sad.z.maintenance.nctsexport.url.store.TvinnNctsMaintenanceExportUrlDataStore;
import no.systema.tvinn.sad.z.maintenance.sadimport.model.jsonjackson.dbtable.gyldigekoder.JsonMaintSadImportKodts4Container;
import no.systema.tvinn.sad.z.maintenance.sadimport.model.jsonjackson.dbtable.gyldigekoder.JsonMaintSadImportKodts4Record;
import no.systema.tvinn.sad.z.maintenance.sadimport.service.gyldigekoder.MaintSadImportKodts4Service;
import no.systema.tror.service.TrorMainOrderHeaderChildwindowService;
import no.systema.tror.service.html.dropdown.TrorDropDownListPopulationService;
import no.systema.tror.model.jsonjackson.order.childwindow.JsonTrorCarrierContainer;
import no.systema.tror.model.jsonjackson.order.childwindow.JsonTrorCarrierRecord;
import no.systema.tror.model.jsonjackson.order.childwindow.JsonTrorPostalCodeContainer;
import no.systema.tror.model.jsonjackson.order.childwindow.JsonTrorPostalCodeRecord;
import no.systema.tror.model.jsonjackson.order.childwindow.JsonTrorTollstedContainer;
import no.systema.tror.model.jsonjackson.order.childwindow.JsonTrorTollstedRecord;
import no.systema.tror.model.jsonjackson.order.childwindow.JsonTrorLosseLasteStedContainer;
import no.systema.tror.model.jsonjackson.order.childwindow.JsonTrorLosseLasteStedRecord;

//
import no.systema.tror.model.jsonjackson.codes.JsonTrorCodeContainer;
import no.systema.tror.model.jsonjackson.codes.JsonTrorCodeRecord;
import no.systema.tror.model.jsonjackson.codes.JsonTrorOppdragsTypeCodeContainer;
import no.systema.tror.model.jsonjackson.codes.JsonTrorOppdragsTypeCodeRecord;
import no.systema.tror.model.jsonjackson.codes.JsonTrorIncotermsCodeContainer;
import no.systema.tror.model.jsonjackson.codes.JsonTrorIncotermsCodeRecord;
import no.systema.tror.model.jsonjackson.codes.JsonTrorProductCodeContainer;
import no.systema.tror.model.jsonjackson.codes.JsonTrorProductCodeRecord;
import no.systema.jservices.common.dao.DokufeDao;
import no.systema.jservices.common.dao.DokufmDao;
import no.systema.jservices.common.json.JsonDtoContainer;
import no.systema.jservices.common.json.JsonReader;
//
import no.systema.tvinn.sad.z.maintenance.nctsexport.model.jsonjackson.dbtable.JsonMaintNctsTrkodfContainer;
import no.systema.tvinn.sad.z.maintenance.nctsexport.model.jsonjackson.dbtable.JsonMaintNctsTrkodfRecord;


/**
 * 
 * @author oscardelatorre
 * @date Aug 24, 2017
 */
@Controller
@SessionAttributes(AppConstants.SYSTEMA_WEB_USER_KEY)
@Scope("session")

public class TrorMainOrderHeaderControllerChildWindow {
	//Postal codes
	private final String POSTALCODE_DIRECTION = "direction";
	private final String DATATABLE_TOLLSTED_LIST = "tollstedList";
	private final String DATATABLE_POSTALCODE_LIST = "postalCodeList";
	private final String DATATABLE_CARRIER_LIST = "carrierList";
	private final String DATATABLE_LOAD_UNLOAD_PLACES_LIST = "loadUnloadPlacesList";
	private final String DATATABLE_PACKING_CODES_LIST = "packingCodesList";

	private static final Logger logger = Logger.getLogger(TrorMainOrderHeaderControllerChildWindow.class.getName());
	private static final JsonDebugger jsonDebugger = new JsonDebugger(2000);
	private DateTimeManager dateTimeManager = new DateTimeManager();
	private StringManager strMgr = new StringManager();
	
	private ModelAndView loginView = new ModelAndView("login");
	private ApplicationContext context;
	private LoginValidator loginValidator = new LoginValidator();
	
	 
	
	/**
	 * Carrier
	 * 
	 * @param recordToValidate
	 * @param bindingResult
	 * @param session
	 * @param request
	 * @return
	 */
	
	@RequestMapping(value="tror_mainorder_childwindow_carrier.do", params="action=doFind",  method={RequestMethod.GET, RequestMethod.POST} )
	public ModelAndView doFindCarrier(@ModelAttribute ("record") JsonTrorCarrierRecord recordToValidate, BindingResult bindingResult, HttpSession session, HttpServletRequest request){
		this.context = TdsAppContext.getApplicationContext();
		logger.info("Inside: doFindCarrier");
		Collection outputList = new ArrayList();
		Map model = new HashMap();
		ModelAndView successView = new ModelAndView("tror_mainorder_childwindow_carrier");
		SystemaWebUser appUser = this.loginValidator.getValidUser(session);
		String ctype = request.getParameter("ctype");
		model.put("ctype", ctype);
		
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
				
		    		//prepare the access CGI with RPG back-end
		    		String BASE_URL = TrorUrlDataStore.TROR_BASE_CHILDWINDOW_CARRIER_URL;
		    		String urlRequestParamsKeys = this.getRequestUrlKeyParametersSearchChildWindow(recordToValidate, appUser);
		    		logger.info("URL: " + BASE_URL);
		    		logger.info("PARAMS: " + urlRequestParamsKeys);
		    		logger.info(Calendar.getInstance().getTime() +  " CGI-start timestamp");
		    		String jsonPayload = this.urlCgiProxyService.getJsonContent(BASE_URL, urlRequestParamsKeys);
		    		//Debug -->
			    	logger.debug(jsonDebugger.debugJsonPayloadWithLog4J(jsonPayload));
		    		logger.info(Calendar.getInstance().getTime() +  " CGI-end timestamp");
			    
		    		if(jsonPayload!=null){
		    			JsonTrorCarrierContainer container = this.trorMainOrderHeaderChildwindowService.getCarrierListContainer(jsonPayload);
			    		if(container!=null){
			    			List<JsonTrorCarrierRecord> list = new ArrayList<JsonTrorCarrierRecord>();
			    			for(JsonTrorCarrierRecord  record : container.getDtoList()){
			    				//logger.info("ID:" + record.getVmtran());
			    				//logger.info("NAME:" + record.getVmnavn());
			    				list.add(record);
			    			}
			    			model.put(this.DATATABLE_CARRIER_LIST, list);
			    			model.put(TrorConstants.DOMAIN_CONTAINER, recordToValidate);
			    		}
		    			successView.addObject(TrorConstants.DOMAIN_MODEL , model);
					logger.info(Calendar.getInstance().getTime() + " CONTROLLER end - timestamp");
					return successView;
					
			    	}else{
					logger.fatal("NO CONTENT on jsonPayload from URL... ??? <Null>");
					return loginView;
				}
				
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
	@RequestMapping(value="tror_mainorder_childwindow_postalcodes_sted2.do", params="action=doFind",  method={RequestMethod.GET, RequestMethod.POST} )
	public ModelAndView doFindPostalCodes(@ModelAttribute ("record") JsonTrorPostalCodeRecord recordToValidate, BindingResult bindingResult, HttpSession session, HttpServletRequest request){
		this.context = TdsAppContext.getApplicationContext();
		logger.info("Inside: doFindPostalCodes");
		Collection outputList = new ArrayList();
		Map model = new HashMap();
		ModelAndView successView = new ModelAndView("tror_mainorder_childwindow_postalcodes_sted2");
		SystemaWebUser appUser = this.loginValidator.getValidUser(session);
		//to catch the sender since there could be more then one caller field
		String ctype = request.getParameter("ctype");
		model.put("ctype", ctype);
		
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
	    		model.put(TrorConstants.DOMAIN_RECORD, recordToValidate);
				successView.addObject(TrorConstants.DOMAIN_MODEL, model);
				return successView;
	    		
		    }else{
				
	    		//prepare the access CGI with RPG back-end
	    		String BASE_URL = TrorUrlDataStore.TROR_BASE_CHILDWINDOW_POSTALCODE_STED2_URL;
	    		String urlRequestParamsKeys = this.getRequestUrlKeyParametersSearchChildWindow(recordToValidate, appUser);
	    		logger.info("URL: " + BASE_URL);
	    		logger.info("PARAMS: " + urlRequestParamsKeys);
	    		logger.info(Calendar.getInstance().getTime() +  " CGI-start timestamp");
	    		String jsonPayload = this.urlCgiProxyService.getJsonContent(BASE_URL, urlRequestParamsKeys);
	    		//Debug -->
		    	logger.debug(jsonDebugger.debugJsonPayloadWithLog4J(jsonPayload));
	    		logger.info(Calendar.getInstance().getTime() +  " CGI-end timestamp");
		    
	    		if(jsonPayload!=null){
	    			JsonTrorPostalCodeContainer container = this.trorMainOrderHeaderChildwindowService.getPostalCodeListContainer(jsonPayload);
		    		if(container!=null){
		    			List<JsonTrorPostalCodeRecord> list = new ArrayList<JsonTrorPostalCodeRecord>();
		    			for(JsonTrorPostalCodeRecord  record : container.getDtoList()){
		    				//logger.info("ID:" + record.getVmtran());
		    				//logger.info("NAME:" + record.getVmnavn());
		    				list.add(record);
		    			}
		    			model.put(this.DATATABLE_POSTALCODE_LIST, list);
		    			model.put(TrorConstants.DOMAIN_RECORD, recordToValidate);
		    		}
	    			successView.addObject(TrorConstants.DOMAIN_MODEL , model);
	    			logger.info(Calendar.getInstance().getTime() + " CONTROLLER end - timestamp");
	    			return successView;
	    			
		    	}else{
		    		logger.fatal("NO CONTENT on jsonPayload from URL... ??? <Null>");
		    		return loginView;
		    	}
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
	@RequestMapping(value="tror_mainorder_childwindow_tollsted.do", params="action=doFind",  method={RequestMethod.GET, RequestMethod.POST} )
	public ModelAndView doFindTollsted(@ModelAttribute ("record") JsonTrorTollstedRecord recordToValidate, BindingResult bindingResult, HttpSession session, HttpServletRequest request){
		this.context = TdsAppContext.getApplicationContext();
		logger.info("Inside: doFindTollsted");
		Collection outputList = new ArrayList();
		Map model = new HashMap();
		ModelAndView successView = new ModelAndView("tror_mainorder_childwindow_tollsted");
		SystemaWebUser appUser = this.loginValidator.getValidUser(session);
		//to catch the sender since there could be more then one caller field
		String ctype = request.getParameter("ctype");
		model.put("ctype", ctype);
		
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
	    		model.put(TrorConstants.DOMAIN_RECORD, recordToValidate);
				successView.addObject(TrorConstants.DOMAIN_MODEL, model);
				return successView;
	    		
		    }else{
				
	    		//prepare the access CGI with RPG back-end
	    		String BASE_URL = TrorUrlDataStore.TROR_BASE_CHILDWINDOW_TOLLSTED_URL;
	    		String urlRequestParamsKeys = this.getRequestUrlKeyParametersSearchChildWindow(recordToValidate, appUser);
	    		logger.info("URL: " + BASE_URL);
	    		logger.info("PARAMS: " + urlRequestParamsKeys);
	    		logger.info(Calendar.getInstance().getTime() +  " CGI-start timestamp");
	    		String jsonPayload = this.urlCgiProxyService.getJsonContent(BASE_URL, urlRequestParamsKeys);
	    		//Debug -->
		    	logger.debug(jsonDebugger.debugJsonPayloadWithLog4J(jsonPayload));
	    		logger.info(Calendar.getInstance().getTime() +  " CGI-end timestamp");
		    
	    		if(jsonPayload!=null){
	    			JsonTrorTollstedContainer container = this.trorMainOrderHeaderChildwindowService.getTollstedListContainer(jsonPayload);
		    		if(container!=null){
		    			List<JsonTrorTollstedRecord> list = new ArrayList<JsonTrorTollstedRecord>();
		    			for(JsonTrorTollstedRecord  record : container.getDtoList()){
		    				//logger.info("ID:" + record.getVmtran());
		    				//logger.info("NAME:" + record.getVmnavn());
		    				list.add(record);
		    			}
		    			model.put(this.DATATABLE_TOLLSTED_LIST, list);
		    			model.put(TrorConstants.DOMAIN_RECORD, recordToValidate);
		    		}
	    			successView.addObject(TrorConstants.DOMAIN_MODEL , model);
	    			logger.info(Calendar.getInstance().getTime() + " CONTROLLER end - timestamp");
	    			return successView;
	    			
		    	}else{
		    		logger.fatal("NO CONTENT on jsonPayload from URL... ??? <Null>");
		    		return loginView;
		    	}
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
	@RequestMapping(value="tror_mainorder_childwindow_generalcodes.do", params="action=doFind",  method={RequestMethod.GET, RequestMethod.POST} )
	public ModelAndView doFindGeneralCodes(@ModelAttribute ("record") JsonTrorCodeRecord recordToValidate, BindingResult bindingResult, HttpSession session, HttpServletRequest request){
		this.context = TdsAppContext.getApplicationContext();
		logger.info("Inside: doFindGeneralCodes");
		Collection outputList = new ArrayList();
		Map model = new HashMap();
		ModelAndView successView = new ModelAndView("tror_mainorder_childwindow_generalcodes");
		SystemaWebUser appUser = this.loginValidator.getValidUser(session);
		//to catch the sender since there could be more then one caller field
		String ctype = request.getParameter("ctype");
		String kftype = request.getParameter("kftype");
		
		model.put("ctype", ctype);
		model.put("kftype", kftype);
		
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
	    		model.put(TrorConstants.DOMAIN_RECORD, recordToValidate);
				successView.addObject(TrorConstants.DOMAIN_MODEL, model);
				return successView;
	    		
		    }else{
				
	    		//prepare the access CGI with RPG back-end
	    		String BASE_URL = TrorUrlDataStore.TROR_GENERAL_CODES_URL;
	    		String urlRequestParamsKeys = "user=" + appUser.getUser() + "&kftyp=" + kftype;
	    		logger.info("URL: " + BASE_URL);
	    		logger.info("PARAMS: " + urlRequestParamsKeys);
	    		logger.info(Calendar.getInstance().getTime() +  " CGI-start timestamp");
	    		String jsonPayload = this.urlCgiProxyService.getJsonContent(BASE_URL, urlRequestParamsKeys);
	    		//Debug -->
		    	logger.debug(jsonDebugger.debugJsonPayloadWithLog4J(jsonPayload));
	    		logger.info(Calendar.getInstance().getTime() +  " CGI-end timestamp");
		    
	    		if(jsonPayload!=null){
	    			JsonTrorCodeContainer container = null;
	    			try{
	    				container = this.trorDropDownListPopulationService.getContainer(jsonPayload);
	    			}catch(Exception e){
	    				e.printStackTrace();
	    			}
	    			//go on
		    		if(container!=null){
		    			List<JsonTrorCodeRecord> list = new ArrayList<JsonTrorCodeRecord>();
		    			for(JsonTrorCodeRecord  record : container.getList()){
		    				list.add(record);
		    			}
		    			model.put(TrorConstants.RESOURCE_MODEL_KEY_MLAPKOD_CODE_LIST, list);
		    			model.put(TrorConstants.DOMAIN_RECORD, recordToValidate);
		    		}
	    			successView.addObject(TrorConstants.DOMAIN_MODEL , model);
	    			logger.info(Calendar.getInstance().getTime() + " CONTROLLER end - timestamp");
	    			return successView;
	    			
		    	}else{
		    		logger.fatal("NO CONTENT on jsonPayload from URL... ??? <Null>");
		    		return loginView;
		    	}
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
	@RequestMapping(value="tror_mainorder_childwindow_incoterms.do", params="action=doFind",  method={RequestMethod.GET, RequestMethod.POST} )
	public ModelAndView doFindIncoterms(@ModelAttribute ("record") JsonTrorIncotermsCodeRecord recordToValidate, BindingResult bindingResult, HttpSession session, HttpServletRequest request){
		this.context = TdsAppContext.getApplicationContext();
		logger.info("Inside: doFindIncoterms");
		Collection outputList = new ArrayList();
		Map model = new HashMap();
		ModelAndView successView = new ModelAndView("tror_mainorder_childwindow_incoterms");
		SystemaWebUser appUser = this.loginValidator.getValidUser(session);
		//to catch the sender since there could be more then one caller field
		String ctype = request.getParameter("ctype");
		model.put("ctype", ctype);
		
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
	    		model.put(TrorConstants.DOMAIN_RECORD, recordToValidate);
				successView.addObject(TrorConstants.DOMAIN_MODEL, model);
				return successView;
	    		
		    }else{
				
	    		//prepare the access CGI with RPG back-end
	    		String BASE_URL = TrorUrlDataStore.TROR_INCOTERMS_CODES_URL;
	    		String urlRequestParamsKeys = "user=" + appUser.getUser();
	    		logger.info("URL: " + BASE_URL);
	    		logger.info("PARAMS: " + urlRequestParamsKeys);
	    		logger.info(Calendar.getInstance().getTime() +  " CGI-start timestamp");
	    		String jsonPayload = this.urlCgiProxyService.getJsonContent(BASE_URL, urlRequestParamsKeys);
	    		//Debug -->
		    	logger.debug(jsonDebugger.debugJsonPayloadWithLog4J(jsonPayload));
	    		logger.info(Calendar.getInstance().getTime() +  " CGI-end timestamp");
		    
	    		if(jsonPayload!=null){
	    			JsonTrorIncotermsCodeContainer container = null;
	    			try{
	    				container = this.trorDropDownListPopulationService.getIncotermsContainer(jsonPayload);
	    			}catch(Exception e){
	    				e.printStackTrace();
	    			}
	    			//go on
		    		if(container!=null){
		    			List<JsonTrorIncotermsCodeRecord> list = new ArrayList<JsonTrorIncotermsCodeRecord>();
		    			for(JsonTrorIncotermsCodeRecord  record : container.getDtoList()){
		    				//logger.info("ID:" + record.getVmtran());
		    				//logger.info("NAME:" + record.getVmnavn());
		    				list.add(record);
		    			}
		    			model.put(TrorConstants.RESOURCE_MODEL_KEY_INCOTERMS_LIST, list);
		    			model.put(TrorConstants.DOMAIN_RECORD, recordToValidate);
		    		}
	    			successView.addObject(TrorConstants.DOMAIN_MODEL , model);
	    			logger.info(Calendar.getInstance().getTime() + " CONTROLLER end - timestamp");
	    			return successView;
	    			
		    	}else{
		    		logger.fatal("NO CONTENT on jsonPayload from URL... ??? <Null>");
		    		return loginView;
		    	}
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
	@RequestMapping(value="tror_mainorder_childwindow_oppdragstype.do", params="action=doFind",  method={RequestMethod.GET, RequestMethod.POST} )
	public ModelAndView doFindOppdragstype(@ModelAttribute ("record") JsonTrorOppdragsTypeCodeRecord recordToValidate, BindingResult bindingResult, HttpSession session, HttpServletRequest request){
		this.context = TdsAppContext.getApplicationContext();
		logger.info("Inside: doFindOppdragstype");
		Collection outputList = new ArrayList();
		Map model = new HashMap();
		ModelAndView successView = new ModelAndView("tror_mainorder_childwindow_oppdragstype");
		SystemaWebUser appUser = this.loginValidator.getValidUser(session);
		//to catch the sender since there could be more then one caller field
		String ctype = request.getParameter("ctype");
		model.put("ctype", ctype);
		
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
	    		model.put(TrorConstants.DOMAIN_RECORD, recordToValidate);
				successView.addObject(TrorConstants.DOMAIN_MODEL, model);
				return successView;
	    		
		    }else{
				
	    		//prepare the access CGI with RPG back-end
	    		String BASE_URL = TrorUrlDataStore.TROR_OPPDRAGSTYPE_CODES_URL;
	    		String urlRequestParamsKeys = "user=" + appUser.getUser();
	    		logger.info("URL: " + BASE_URL);
	    		logger.info("PARAMS: " + urlRequestParamsKeys);
	    		logger.info(Calendar.getInstance().getTime() +  " CGI-start timestamp");
	    		String jsonPayload = this.urlCgiProxyService.getJsonContent(BASE_URL, urlRequestParamsKeys);
	    		//Debug -->
		    	logger.debug(jsonDebugger.debugJsonPayloadWithLog4J(jsonPayload));
	    		logger.info(Calendar.getInstance().getTime() +  " CGI-end timestamp");
		    
	    		if(jsonPayload!=null){
	    			JsonTrorOppdragsTypeCodeContainer container = null;
	    			try{
	    				container = this.trorDropDownListPopulationService.getOppdragsTypeContainer(jsonPayload);
	    			}catch(Exception e){
	    				e.printStackTrace();
	    			}
	    			//go on
		    		if(container!=null){
		    			List<JsonTrorOppdragsTypeCodeRecord> list = new ArrayList<JsonTrorOppdragsTypeCodeRecord>();
		    			for(JsonTrorOppdragsTypeCodeRecord  record : container.getDtoList()){
		    				//logger.info("ID:" + record.getVmtran());
		    				//logger.info("NAME:" + record.getVmnavn());
		    				list.add(record);
		    			}
		    			model.put(TrorConstants.RESOURCE_MODEL_KEY_OPPDRAGSTYPE_LIST, list);
		    			model.put(TrorConstants.DOMAIN_RECORD, recordToValidate);
		    		}
	    			successView.addObject(TrorConstants.DOMAIN_MODEL , model);
	    			logger.info(Calendar.getInstance().getTime() + " CONTROLLER end - timestamp");
	    			return successView;
	    			
		    	}else{
		    		logger.fatal("NO CONTENT on jsonPayload from URL... ??? <Null>");
		    		return loginView;
		    	}
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
	@RequestMapping(value="tror_mainorder_childwindow_productcodes.do", params="action=doFind",  method={RequestMethod.GET, RequestMethod.POST} )
	public ModelAndView doFindProductCodes(@ModelAttribute ("record") JsonTrorProductCodeRecord recordToValidate, BindingResult bindingResult, HttpSession session, HttpServletRequest request){
		this.context = TdsAppContext.getApplicationContext();
		logger.info("Inside: doFindProductCodes");
		Collection outputList = new ArrayList();
		Map model = new HashMap();
		ModelAndView successView = new ModelAndView("tror_mainorder_childwindow_productcodes");
		SystemaWebUser appUser = this.loginValidator.getValidUser(session);
		//to catch the sender since there could be more then one caller field
		String ctype = request.getParameter("ctype");
		model.put("ctype", ctype);
		
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
	    		model.put(TrorConstants.DOMAIN_RECORD, recordToValidate);
				successView.addObject(TrorConstants.DOMAIN_MODEL, model);
				return successView;
	    		
		    }else{
				
	    		//prepare the access CGI with RPG back-end
	    		String BASE_URL = TrorUrlDataStore.TROR_PRODUCT_CODES_URL;
	    		String urlRequestParamsKeys = "user=" + appUser.getUser() + "&kftyp=PRODTYPE";
	    		logger.info("URL: " + BASE_URL);
	    		logger.info("PARAMS: " + urlRequestParamsKeys);
	    		logger.info(Calendar.getInstance().getTime() +  " CGI-start timestamp");
	    		String jsonPayload = this.urlCgiProxyService.getJsonContent(BASE_URL, urlRequestParamsKeys);
	    		//Debug -->
		    	logger.debug(jsonDebugger.debugJsonPayloadWithLog4J(jsonPayload));
	    		logger.info(Calendar.getInstance().getTime() +  " CGI-end timestamp");
		    
	    		if(jsonPayload!=null){
	    			JsonTrorProductCodeContainer container = null;
	    			try{
	    				container = this.trorDropDownListPopulationService.getProductContainer(jsonPayload);
	    			}catch(Exception e){
	    				e.printStackTrace();
	    			}
	    			//go on
		    		if(container!=null){
		    			List<JsonTrorProductCodeRecord> list = new ArrayList<JsonTrorProductCodeRecord>();
		    			for(JsonTrorProductCodeRecord  record : container.getDtoList()){
		    				//logger.info("ID:" + record.getVmtran());
		    				//logger.info("NAME:" + record.getVmnavn());
		    				list.add(record);
		    			}
		    			model.put(TrorConstants.RESOURCE_MODEL_KEY_PRODUCT_CODE_LIST, list);
		    			model.put(TrorConstants.DOMAIN_RECORD, recordToValidate);
		    		}
	    			successView.addObject(TrorConstants.DOMAIN_MODEL , model);
	    			logger.info(Calendar.getInstance().getTime() + " CONTROLLER end - timestamp");
	    			return successView;
	    			
		    	}else{
		    		logger.fatal("NO CONTENT on jsonPayload from URL... ??? <Null>");
		    		return loginView;
		    	}
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
	@RequestMapping(value="tror_mainorder_childwindow_productcodes_landimport_fraktbrev.do", params="action=doFind",  method={RequestMethod.GET, RequestMethod.POST} )
	public ModelAndView doFindProductCodesLandImportFraktbrev(@ModelAttribute ("record") JsonTrorProductCodeRecord recordToValidate, BindingResult bindingResult, HttpSession session, HttpServletRequest request){
		this.context = TdsAppContext.getApplicationContext();
		logger.info("Inside: doFindProductCodes");
		Collection outputList = new ArrayList();
		Map model = new HashMap();
		ModelAndView successView = new ModelAndView("tror_mainorder_childwindow_productcodes");
		SystemaWebUser appUser = this.loginValidator.getValidUser(session);
		//to catch the sender since there could be more then one caller field
		String ctype = request.getParameter("ctype");
		model.put("ctype", ctype);
		
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
	    		model.put(TrorConstants.DOMAIN_RECORD, recordToValidate);
				successView.addObject(TrorConstants.DOMAIN_MODEL, model);
				return successView;
	    		
		    }else{
				
	    		//prepare the access CGI with RPG back-end
	    		String BASE_URL = TrorUrlDataStore.TROR_GENERAL_CODES_URL;
	    		String urlRequestParamsKeys = "user=" + appUser.getUser() + "&kftyp=FRBREVPRO";
	    		logger.info("URL: " + BASE_URL);
	    		logger.info("PARAMS: " + urlRequestParamsKeys);
	    		logger.info(Calendar.getInstance().getTime() +  " CGI-start timestamp");
	    		String jsonPayload = this.urlCgiProxyService.getJsonContent(BASE_URL, urlRequestParamsKeys);
	    		//Debug -->
		    	logger.debug(jsonDebugger.debugJsonPayloadWithLog4J(jsonPayload));
	    		logger.info(Calendar.getInstance().getTime() +  " CGI-end timestamp");
		    
	    		if(jsonPayload!=null){
	    			JsonTrorProductCodeContainer container = null;
	    			try{
	    				container = this.trorDropDownListPopulationService.getProductContainer(jsonPayload);
	    			}catch(Exception e){
	    				e.printStackTrace();
	    			}
	    			//go on
	    			if(container!=null){
		    			List<JsonTrorProductCodeRecord> list = new ArrayList<JsonTrorProductCodeRecord>();
		    			for(JsonTrorProductCodeRecord  record : container.getList()){
		    				//logger.info("ID:" + record.getKfkod());
		    				//logger.info("NAME:" + record.getKftxt());
		    				list.add(record);
		    			}
		    			model.put(TrorConstants.RESOURCE_MODEL_KEY_PRODUCT_CODE_LIST, list);
		    			model.put(TrorConstants.DOMAIN_RECORD, recordToValidate);
		    		}
	    			successView.addObject(TrorConstants.DOMAIN_MODEL , model);
	    			logger.info(Calendar.getInstance().getTime() + " CONTROLLER end - timestamp");
	    			return successView;
	    			
		    	}else{
		    		logger.fatal("NO CONTENT on jsonPayload from URL... ??? <Null>");
		    		return loginView;
		    	}
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
	@RequestMapping(value="tror_mainorder_childwindow_transporttypes.do", params="action=doFind",  method={RequestMethod.GET, RequestMethod.POST} )
	public ModelAndView doFindTransportTypes(@ModelAttribute ("record") JsonMaintSadImportKodts4Record recordToValidate, BindingResult bindingResult, HttpSession session, HttpServletRequest request){
		this.context = TdsAppContext.getApplicationContext();
		logger.info("Inside: doFindIncoterms");
		Collection outputList = new ArrayList();
		Map model = new HashMap();
		ModelAndView successView = new ModelAndView("tror_mainorder_childwindow_transporttypes");
		SystemaWebUser appUser = this.loginValidator.getValidUser(session);
		//to catch the sender since there could be more then one caller field
		String ctype = request.getParameter("ctype");
		model.put("ctype", ctype);
		
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
	    		model.put(TrorConstants.DOMAIN_RECORD, recordToValidate);
				successView.addObject(TrorConstants.DOMAIN_MODEL, model);
				return successView;
	    		
		    }else{
				
	    		//prepare the access CGI with RPG back-end
	    		String BASE_URL = TvinnSadMaintenanceImportUrlDataStoreGyldigeKoder.TVINN_SAD_MAINTENANCE_IMPORT_BASE_SAD002_KODTS4R_GET_LIST_URL;
	    		String urlRequestParamsKeys = "user=" + appUser.getUser();
	    		logger.info("URL: " + BASE_URL);
	    		logger.info("PARAMS: " + urlRequestParamsKeys);
	    		logger.info(Calendar.getInstance().getTime() +  " CGI-start timestamp");
	    		String jsonPayload = this.urlCgiProxyService.getJsonContent(BASE_URL, urlRequestParamsKeys);
	    		//Debug -->
		    	logger.debug(jsonDebugger.debugJsonPayloadWithLog4J(jsonPayload));
	    		logger.info(Calendar.getInstance().getTime() +  " CGI-end timestamp");
		    
	    		if(jsonPayload!=null){
	    			JsonMaintSadImportKodts4Container container = null;
	    			try{
	    				container = this.maintSadImportKodts4Service.getList(jsonPayload);
	    			}catch(Exception e){
	    				e.printStackTrace();
	    			}
	    			//go on
		    		if(container!=null){
		    			List<JsonMaintSadImportKodts4Record> list = new ArrayList<JsonMaintSadImportKodts4Record>();
		    			for(JsonMaintSadImportKodts4Record  record : container.getList()){
		    				//logger.info("ID:" + record.getVmtran());
		    				//logger.info("NAME:" + record.getVmnavn());
		    				list.add(record);
		    			}
		    			model.put(TrorConstants.RESOURCE_MODEL_KEY_TRANSPORTTYPE_CODE_LIST, list);
		    			model.put(TrorConstants.DOMAIN_RECORD, recordToValidate);
		    		}
	    			successView.addObject(TrorConstants.DOMAIN_MODEL , model);
	    			logger.info(Calendar.getInstance().getTime() + " CONTROLLER end - timestamp");
	    			return successView;
	    			
		    	}else{
		    		logger.fatal("NO CONTENT on jsonPayload from URL... ??? <Null>");
		    		return loginView;
		    	}
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
	@RequestMapping(value="tror_mainorder_childwindow_unitcodes.do", params="action=doFind",  method={RequestMethod.GET, RequestMethod.POST} )
	public ModelAndView doFindUnitCodes(@ModelAttribute ("record") JsonMaintSadImportKodts4Record recordToValidate, BindingResult bindingResult, HttpSession session, HttpServletRequest request){
		this.context = TdsAppContext.getApplicationContext();
		logger.info("Inside: doFindIncoterms");
		Collection outputList = new ArrayList();
		Map model = new HashMap();
		ModelAndView successView = new ModelAndView("tror_mainorder_childwindow_unitcodes");
		SystemaWebUser appUser = this.loginValidator.getValidUser(session);
		//to catch the sender since there could be more then one caller field
		String ctype = request.getParameter("ctype");
		model.put("ctype", ctype);
		
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
	    		model.put(TrorConstants.DOMAIN_RECORD, recordToValidate);
				successView.addObject(TrorConstants.DOMAIN_MODEL, model);
				return successView;
	    		
		    }else{
				
	    		//prepare the access CGI with RPG back-end
	    		String BASE_URL = TvinnNctsMaintenanceExportUrlDataStore.TVINN_NCTS_MAINTENANCE_EXPORT_BASE_TR001R_GET_LIST_URL;
	    		String urlRequestParamsKeys = "user=" + appUser.getUser() + "&tkunik=017";
	    		logger.info("URL: " + BASE_URL);
	    		logger.info("PARAMS: " + urlRequestParamsKeys);
	    		logger.info(Calendar.getInstance().getTime() +  " CGI-start timestamp");
	    		String jsonPayload = this.urlCgiProxyService.getJsonContent(BASE_URL, urlRequestParamsKeys);
	    		//Debug -->
		    	logger.debug(jsonDebugger.debugJsonPayloadWithLog4J(jsonPayload));
	    		logger.info(Calendar.getInstance().getTime() +  " CGI-end timestamp");
		    
	    		if(jsonPayload!=null){
	    			JsonMaintNctsTrkodfContainer container = null;
	    			try{
	    				container = this.maintNctsExportTrkodfService.getList(jsonPayload);
	    			}catch(Exception e){
	    				e.printStackTrace();
	    			}
	    			//go on
		    		if(container!=null){
		    			List<JsonMaintNctsTrkodfRecord> list = new ArrayList<JsonMaintNctsTrkodfRecord>();
		    			for(JsonMaintNctsTrkodfRecord  record : container.getList()){
		    				//logger.info("ID:" + record.getVmtran());
		    				//logger.info("NAME:" + record.getVmnavn());
		    				list.add(record);
		    			}
		    			model.put(TrorConstants.RESOURCE_MODEL_KEY_ENHET_CODE_LIST, list);
		    			model.put(TrorConstants.DOMAIN_RECORD, recordToValidate);
		    		}
	    			successView.addObject(TrorConstants.DOMAIN_MODEL , model);
	    			logger.info(Calendar.getInstance().getTime() + " CONTROLLER end - timestamp");
	    			return successView;
	    			
		    	}else{
		    		logger.fatal("NO CONTENT on jsonPayload from URL... ??? <Null>");
		    		return loginView;
		    	}
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
	@RequestMapping(value="tror_mainorder_childwindow_loadunloadplaces.do", params="action=doFind",  method={RequestMethod.GET, RequestMethod.POST} )
	public ModelAndView doFindLoadUnload(@ModelAttribute ("record") JsonTrorLosseLasteStedRecord recordToValidate, BindingResult bindingResult, HttpSession session, HttpServletRequest request){
		this.context = TdsAppContext.getApplicationContext();
		logger.info("Inside: doFindLoadUnload");
		Collection outputList = new ArrayList();
		Map model = new HashMap();
		ModelAndView successView = new ModelAndView("tror_mainorder_childwindow_loadunloadplaces");
		SystemaWebUser appUser = this.loginValidator.getValidUser(session);
		//to catch the sender since there could be more then one caller field
		String ctype = request.getParameter("ctype");
		model.put("ctype", ctype);
		
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
	    		model.put(TrorConstants.DOMAIN_RECORD, recordToValidate);
				successView.addObject(TrorConstants.DOMAIN_MODEL, model);
				return successView;
	    		
		    }else{
				
	    		//prepare the access CGI with RPG back-end
	    		String BASE_URL = TrorUrlDataStore.TROR_BASE_CHILDWINDOW_LOAD_UNLOAD_PLACES_URL;
	    		String urlRequestParamsKeys = "user=" + appUser.getUser();
	    		logger.info("URL: " + BASE_URL);
	    		logger.info("PARAMS: " + urlRequestParamsKeys);
	    		logger.info(Calendar.getInstance().getTime() +  " CGI-start timestamp");
	    		String jsonPayload = this.urlCgiProxyService.getJsonContent(BASE_URL, urlRequestParamsKeys);
	    		//Debug -->
		    	logger.debug(jsonDebugger.debugJsonPayloadWithLog4J(jsonPayload));
	    		logger.info(Calendar.getInstance().getTime() +  " CGI-end timestamp");
		    
	    		if(jsonPayload!=null){
	    			JsonTrorLosseLasteStedContainer container = null;
	    			try{
	    				container = this.trorMainOrderHeaderChildwindowService.getLosseLasteStedContainer(jsonPayload);
	    			}catch(Exception e){
	    				e.printStackTrace();
	    			}
	    			//go on
		    		if(container!=null){
		    			List<JsonTrorLosseLasteStedRecord> list = new ArrayList<JsonTrorLosseLasteStedRecord>();
		    			for(JsonTrorLosseLasteStedRecord  record : container.getDtoList()){
		    				//logger.info("ID:" + record.getVmtran());
		    				//logger.info("NAME:" + record.getVmnavn());
		    				list.add(record);
		    			}
		    			model.put(TrorConstants.RESOURCE_MODEL_KEY_LOADUNLOAD_CODE_LIST, list);
		    			model.put(TrorConstants.DOMAIN_RECORD, recordToValidate);
		    		}
	    			successView.addObject(TrorConstants.DOMAIN_MODEL , model);
	    			logger.info(Calendar.getInstance().getTime() + " CONTROLLER end - timestamp");
	    			return successView;
	    			
		    	}else{
		    		logger.fatal("NO CONTENT on jsonPayload from URL... ??? <Null>");
		    		return loginView;
		    	}
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
	@RequestMapping(value="tror_mainorder_childwindow_avd.do", params="action=doFind",  method={RequestMethod.GET, RequestMethod.POST} )
	public ModelAndView doFindAvd(@ModelAttribute ("record") JsonMaintMainKodtaRecord recordToValidate, BindingResult bindingResult, HttpSession session, HttpServletRequest request){
		this.context = TdsAppContext.getApplicationContext();
		logger.info("Inside: doFindAvd");
		Collection outputList = new ArrayList();
		Map model = new HashMap();
		ModelAndView successView = new ModelAndView("tror_mainorder_childwindow_avd");
		SystemaWebUser appUser = this.loginValidator.getValidUser(session);
		//to catch the sender since there could be more then one caller field
		String ctype = request.getParameter("ctype");
		model.put("ctype", ctype);
		
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
	    		model.put(TrorConstants.DOMAIN_RECORD, recordToValidate);
				successView.addObject(TrorConstants.DOMAIN_MODEL, model);
				return successView;
	    		
		    }else{
		    	
		    	String BASE_URL = MaintenanceMainUrlDataStore.MAINTENANCE_MAIN_BASE_SYFA14R_GET_LIST_URL;
				String urlRequestParams = "user=" + appUser.getUser();
				logger.info(Calendar.getInstance().getTime() + " CGI-start timestamp");
		    	logger.info("URL: " + BASE_URL);
		    	logger.info("URL PARAMS: " + urlRequestParams);
		    	String jsonPayload = urlCgiProxyService.getJsonContent(BASE_URL, urlRequestParams);
		    	//extract
		    	List<JsonMaintMainKodtaRecord> list = new ArrayList();
		    	if(jsonPayload!=null){
					//lists
		    		JsonMaintMainKodtaContainer container = this.maintMainKodtaService.getList(jsonPayload);
			        if(container!=null){
			        	list = (List)container.getList();
			        }
		    	}
		  
				model.put(TrorConstants.RESOURCE_MODEL_KEY_AVD_LIST, list);
				successView.addObject(TrorConstants.DOMAIN_MODEL , model);
		    	
				return successView;
		    	
		    	
		    	
		    	
				/* OLD template
	    		//prepare the access CGI with RPG back-end
	    		String BASE_URL = TvinnSadMaintenanceImportUrlDataStoreGyldigeKoder.TVINN_SAD_MAINTENANCE_IMPORT_BASE_SAD002_KODTS4R_GET_LIST_URL;
	    		String urlRequestParamsKeys = "user=" + appUser.getUser();
	    		logger.info("URL: " + BASE_URL);
	    		logger.info("PARAMS: " + urlRequestParamsKeys);
	    		logger.info(Calendar.getInstance().getTime() +  " CGI-start timestamp");
	    		String jsonPayload = this.urlCgiProxyService.getJsonContent(BASE_URL, urlRequestParamsKeys);
	    		//Debug -->
		    	logger.debug(jsonDebugger.debugJsonPayloadWithLog4J(jsonPayload));
	    		logger.info(Calendar.getInstance().getTime() +  " CGI-end timestamp");
		    
	    		if(jsonPayload!=null){
	    			JsonMaintSadImportKodts4Container container = null;
	    			try{
	    				container = this.maintSadImportKodts4Service.getList(jsonPayload);
	    			}catch(Exception e){
	    				e.printStackTrace();
	    			}
	    			//go on
		    		if(container!=null){
		    			List<JsonMaintSadImportKodts4Record> list = new ArrayList<JsonMaintSadImportKodts4Record>();
		    			for(JsonMaintSadImportKodts4Record  record : container.getList()){
		    				//logger.info("ID:" + record.getVmtran());
		    				//logger.info("NAME:" + record.getVmnavn());
		    				list.add(record);
		    			}
		    			model.put(TrorConstants.RESOURCE_MODEL_KEY_TRANSPORTTYPE_CODE_LIST, list);
		    			model.put(TrorConstants.DOMAIN_RECORD, recordToValidate);
		    		}
	    			successView.addObject(TrorConstants.DOMAIN_MODEL , model);
	    			logger.info(Calendar.getInstance().getTime() + " CONTROLLER end - timestamp");
	    			return successView;
	    			
		    	}else{
		    		logger.fatal("NO CONTENT on jsonPayload from URL... ??? <Null>");
		    		return loginView;
		    	}
		    	*/
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
	@RequestMapping(value="tror_mainorder_childwindow_freightbill_merke_kolli.do", params="action=doFind",  method={RequestMethod.GET, RequestMethod.POST} )
	public ModelAndView doFindMerkeKolliLinjerInFreightbill(@ModelAttribute ("record") DokufmDao recordToValidate, BindingResult bindingResult, HttpSession session, HttpServletRequest request){
		this.context = TdsAppContext.getApplicationContext();
		logger.info("Inside: doFindMerkeKolliLinjerInFreightbill");
		Collection outputList = new ArrayList();
		Map model = new HashMap();
		ModelAndView successView = new ModelAndView("tror_mainorder_childwindow_freightbill_merke_kolli");
		SystemaWebUser appUser = this.loginValidator.getValidUser(session);
		//to catch the sender since there could be more then one caller field
		//String ctype = request.getParameter("ctype");
		//String kftype = request.getParameter("kftype");
		
		//model.put("ctype", ctype);
		//model.put("kftype", kftype);
		
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
	    		model.put(TrorConstants.DOMAIN_RECORD, recordToValidate);
				successView.addObject(TrorConstants.DOMAIN_MODEL, model);
				return successView;
	    		
		    }else{
		    	 
		    	 //===========
				 //FETCH LIST
				 //===========
		    	 List<DokufmDao> list = new ArrayList<DokufmDao>();
				 JsonReader<JsonDtoContainer<DokufmDao>> jsonReader = new JsonReader<JsonDtoContainer<DokufmDao>>();
				 jsonReader.set(new JsonDtoContainer<DokufmDao>());
				
		    	
				//prepare the access CGI with RPG back-end
				 String BASE_URL = TrorUrlDataStore.TROR_BASE_FETCH_DOKUFM_URL;
				 StringBuffer urlRequestParamsKeys = new StringBuffer();
				 urlRequestParamsKeys.append("user=" + appUser.getUser() + "&fmavd=" + recordToValidate.getFmavd() + "&fmopd=" + recordToValidate.getFmopd());
				 urlRequestParamsKeys.append("&fmfbnr=" + recordToValidate.getFmfbnr()); 
				 
				 logger.info("URL: " + BASE_URL);
				 logger.info("PARAMS: " + urlRequestParamsKeys);
				 logger.info(Calendar.getInstance().getTime() +  " CGI-start timestamp");
				 String jsonPayload = this.urlCgiProxyService.getJsonContent(BASE_URL, urlRequestParamsKeys.toString());
				 logger.info(Calendar.getInstance().getTime() +  " CGI-end timestamp");
				 logger.debug(jsonDebugger.debugJsonPayloadWithLog4J(jsonPayload));
				 
				 JsonDtoContainer<DokufmDao> container = (JsonDtoContainer<DokufmDao>) jsonReader.get(jsonPayload);
				 if (container != null) {
					if(container.getDtoList()!=null){
						list = container.getDtoList();
					}
				 }
				 
				 model.put(TrorConstants.DOMAIN_LIST, list);
				 model.put(TrorConstants.DOMAIN_RECORD, recordToValidate);

				 successView.addObject(TrorConstants.DOMAIN_MODEL , model);
				 logger.info(Calendar.getInstance().getTime() + " CONTROLLER end - timestamp");
   	
				 return successView;
		    }
		}
	}
	/**
	 * 
	 * @param searchFilter
	 * @param appUser
	 * @return
	 */
	
	private String getRequestUrlKeyParametersSearchChildWindow(JsonTrorCarrierRecord searchFilter, SystemaWebUser appUser){
		StringBuffer urlRequestParamsKeys = new StringBuffer();
		urlRequestParamsKeys.append("user=" + appUser.getUser());
		/*
		if(searchFilter.getTODO()!=null && !"".equals(searchFilter.getTODO())){
			urlRequestParamsKeys.append(EbookingConstants.URL_CHAR_DELIMETER_FOR_PARAMS_WITH_HTML_REQUEST + "todo=" + searchFilter.getTODO());
		}
		*/
		return urlRequestParamsKeys.toString();
	}
	
	/**
	 * 
	 * @param searchFilter
	 * @param appUser
	 * @return
	 */
	private String getRequestUrlKeyParametersSearchChildWindow(JsonTrorPostalCodeRecord searchFilter, SystemaWebUser appUser){
		StringBuffer urlRequestParamsKeys = new StringBuffer();
		urlRequestParamsKeys.append("user=" + appUser.getUser());
		if(strMgr.isNotNull(searchFilter.getSt2lk())){
			urlRequestParamsKeys.append("&st2lk=" + searchFilter.getSt2lk());
		}
		if(strMgr.isNotNull(searchFilter.getSt2kod())){
			urlRequestParamsKeys.append("&st2kod=" + searchFilter.getSt2kod());
		}
		
		return urlRequestParamsKeys.toString();
	}
	/**
	 * 
	 * @param searchFilter
	 * @param appUser
	 * @return
	 */
	private String getRequestUrlKeyParametersSearchChildWindow(JsonTrorTollstedRecord searchFilter, SystemaWebUser appUser){
		StringBuffer urlRequestParamsKeys = new StringBuffer();
		urlRequestParamsKeys.append("user=" + appUser.getUser());
		if(strMgr.isNotNull(searchFilter.getKtskod())){
			urlRequestParamsKeys.append("&ktskod=" + searchFilter.getKtskod());
		}
		if(strMgr.isNotNull(searchFilter.getKtsnav())){
			urlRequestParamsKeys.append("&ktsnav=" + searchFilter.getKtsnav());
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
	
	
	@Qualifier ("trorMainOrderHeaderChildwindowService")
	private TrorMainOrderHeaderChildwindowService trorMainOrderHeaderChildwindowService;
	@Autowired
	@Required
	public void setTrorMainOrderHeaderChildwindowService (TrorMainOrderHeaderChildwindowService value){ this.trorMainOrderHeaderChildwindowService = value; }
	public TrorMainOrderHeaderChildwindowService getTrorMainOrderHeaderChildwindowService(){ return this.trorMainOrderHeaderChildwindowService; }
	
	
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
	
	@Qualifier ("maintSadImportKodts4Service")
	private MaintSadImportKodts4Service maintSadImportKodts4Service;
	@Autowired
	@Required
	public void setMaintSadImportKodts4Service (MaintSadImportKodts4Service value){ this.maintSadImportKodts4Service = value; }
	public MaintSadImportKodts4Service getMaintSadImportKodts4Service(){ return this.maintSadImportKodts4Service; }
	
	@Qualifier ("maintNctsExportTrkodfService")
	private MaintNctsExportTrkodfService  maintNctsExportTrkodfService;
	@Autowired
	@Required
	public void setMaintNctsExportTrkodfService (MaintNctsExportTrkodfService value){ this.maintNctsExportTrkodfService = value; }
	public MaintNctsExportTrkodfService getMaintNctsExportTrkodfService(){ return this.maintNctsExportTrkodfService; }

}
