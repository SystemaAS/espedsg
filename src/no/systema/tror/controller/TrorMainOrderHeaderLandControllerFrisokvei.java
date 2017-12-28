package no.systema.tror.controller;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.util.*;

import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.springframework.validation.BindingResult;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Required;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Scope;

import javax.annotation.PostConstruct;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import org.springframework.web.bind.ServletRequestDataBinder;

//application imports
import no.systema.main.context.TdsAppContext;
import no.systema.main.service.UrlCgiProxyService;
import no.systema.main.service.UrlCgiProxyServiceImpl;
import no.systema.main.validator.LoginValidator;
import no.systema.main.util.AppConstants;
import no.systema.main.util.DateTimeManager;
import no.systema.main.util.EncodingTransformer;
import no.systema.main.util.JsonDebugger;
import no.systema.main.model.SystemaWebUser;

//Trans.Disp
import no.systema.transportdisp.service.TransportDispChildWindowService;
//import no.systema.transportdisp.service.TransportDispWorkflowSpecificTripService;
//import no.systema.transportdisp.service.TransportDispWorkflowSpecificOrderService;
//import no.systema.transportdisp.service.html.dropdown.TransportDispDropDownListPopulationService;
import no.systema.transportdisp.mapper.url.request.UrlRequestParameterMapper;

//tror
import no.systema.tror.validator.TrorOrderHeaderFrisokveiValidator;
import no.systema.tror.service.TrorMainOrderHeaderService;
import no.systema.tror.model.jsonjackson.frisokvei.JsonTrorOrderHeaderFrisokveiContainer;
import no.systema.tror.model.jsonjackson.frisokvei.JsonTrorOrderHeaderFrisokveiRecord;
import no.systema.tror.url.store.TrorUrlDataStore;
import no.systema.tror.util.TrorConstants;




/**
 * Tror Controller - Fri søkvei window
 * 
 * @author oscardelatorre
 * @date Sep 20, 2017
 * 
 */

@Controller
@SessionAttributes(AppConstants.SYSTEMA_WEB_USER_KEY)
@Scope("session")
public class TrorMainOrderHeaderLandControllerFrisokvei {
	private static final Logger logger = Logger.getLogger(TrorMainOrderHeaderLandControllerFrisokvei.class.getName());
	private static final JsonDebugger jsonDebugger = new JsonDebugger(2000);
	private UrlRequestParameterMapper urlRequestParameterMapper = new UrlRequestParameterMapper();
	
	private ModelAndView loginView = new ModelAndView("login");
	private ApplicationContext context;
	private LoginValidator loginValidator = new LoginValidator();
	private DateTimeManager dateTimeMgr = new DateTimeManager();
	
	
	
	@PostConstruct
	public void initIt() throws Exception {
		if("DEBUG".equals(AppConstants.LOG4J_LOGGER_LEVEL)){
			logger.setLevel(Level.DEBUG);
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
	@RequestMapping(value="tror_mainorderland_frisokvei.do", method={RequestMethod.GET} )
	public ModelAndView doInit(@ModelAttribute ("record") JsonTrorOrderHeaderFrisokveiRecord recordToValidate, BindingResult bindingResult, HttpSession session, HttpServletRequest request){
		this.context = TdsAppContext.getApplicationContext();
		Map model = new HashMap();
		
		String action = request.getParameter("action");
		String avd = request.getParameter("avd"); 
		String opd = request.getParameter("opd"); 
		
		
		logger.info("ACTION: " + action);
		//ModelAndView successView = new ModelAndView("transportdisp_mainorder_invoice");
		ModelAndView successView = new ModelAndView("tror_mainorderland_frisokvei");
		SystemaWebUser appUser = this.loginValidator.getValidUser(session);
		
		//check user (should be in session already)
		if(appUser==null){
			return loginView;
			
		}else{
			logger.info(Calendar.getInstance().getTime() + " CONTROLLER start - timestamp");
			this.fetchItemLines(appUser, avd, opd, model, session);
			model.put("record", recordToValidate);
	    	//
	    	successView.addObject(TrorConstants.DOMAIN_MODEL , model);
	    	return successView;
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
	@RequestMapping(value="tror_mainorderland_frisokvei_edit.do",  method={RequestMethod.GET, RequestMethod.POST} )
	public ModelAndView doEditBudget(@ModelAttribute ("record") JsonTrorOrderHeaderFrisokveiRecord recordToValidate, BindingResult bindingResult, HttpSession session, HttpServletRequest request){
		this.context = TdsAppContext.getApplicationContext();
		Map model = new HashMap();
		
		String action = request.getParameter("action");
		String avd = request.getParameter("avd");
		String opd = request.getParameter("opd");
		logger.info("isModeUpdate:" + recordToValidate.getIsModeUpdate());
		
		//Params
		StringBuffer params = new StringBuffer();
		//params.append("&bnr=" + lineId);
		if(avd!=null && !"".equals(avd)){ params.append("&avd=" + avd); }
		if(opd!=null && !"".equals(opd)){ params.append("&opd=" + opd); }
		
		logger.info("ACTION: " + action);
		ModelAndView successView = new ModelAndView("redirect:tror_mainorderland_frisokvei.do?action=doFind" + params.toString() );
		ModelAndView errorView = new ModelAndView("tror_mainorderland_frisokvei");
		
		SystemaWebUser appUser = this.loginValidator.getValidUser(session);
		
		//check user (should be in session already)
		if(appUser==null){
			return loginView;
		}else{
			logger.info(Calendar.getInstance().getTime() + " CONTROLLER start - timestamp");
			
			if(TrorConstants.ACTION_UPDATE.equals(action)){
				logger.info("[INFO] doUpdate action ...");
				TrorOrderHeaderFrisokveiValidator validator = new TrorOrderHeaderFrisokveiValidator();
				
				//Validate
				validator.validate(recordToValidate, bindingResult);
				//check for ERRORS
				if(bindingResult.hasErrors()){
					logger.info("[ERROR Validation] Record does not validate)");
			    	logger.info("[INFO Kod/sokText] " + recordToValidate.getFskode() + " " + recordToValidate.getFssok());
			    	//fetch of lines
					this.fetchItemLines(appUser, avd, opd, model, session);
					model.put("record", recordToValidate);
			    	
					errorView.addObject(TrorConstants.DOMAIN_MODEL , model);
			    	
			    	return errorView;
			    	
				}else{
					
					String MODE = "U";
					if(recordToValidate.getIsModeUpdate()!=null && "true".equalsIgnoreCase(recordToValidate.getIsModeUpdate())){
						//UPDATE
						logger.info("[INFO] UPDATE code: " + recordToValidate.getFskode() + " start process... ");
					}else{
						//CREATE NEW
						MODE = "A";
						logger.info("[INFO] CREATE new line in process...");
					}
					
					//-------------------------------
					//Execute back-end Update/Create
					//-------------------------------
					JsonTrorOrderHeaderFrisokveiContainer container = this.executeUpdateLine(appUser, recordToValidate, MODE, avd, opd);
					if(container!=null){
	    				if(container.getErrMsg()!=null && !"".equals(container.getErrMsg())){
	    					logger.info("[ERROR] Back-end Error: " + container.getErrMsg());
	    					this.populateAspectsOnBackendError(appUser, container.getErrMsg(), recordToValidate, model, session);
	    					//fetch item lines
	    					this.fetchItemLines(appUser, avd, opd, model, session);
	    					
	    			    	errorView.addObject(TrorConstants.DOMAIN_MODEL , model);
	    					return errorView;
	    				}else{
	    					//succefully done!
	    		    		logger.info("[INFO] Valid Update -- Record successfully updated, OK ");
	    				}
	    			}
					logger.info("[INFO] UPDATE code: " + recordToValidate.getFskode() + " end process. ");
				}
				
			}else if(TrorConstants.ACTION_DELETE.equals(action)){
				String DELETE_MODE = "D";
				JsonTrorOrderHeaderFrisokveiContainer container = this.executeUpdateLine(appUser, recordToValidate, DELETE_MODE, avd, opd);
				if(container!=null){
    				if(container.getErrMsg()!=null && !"".equals(container.getErrMsg())){
    					this.populateAspectsOnBackendError(appUser, container.getErrMsg(), recordToValidate, model, session);
    			    	errorView.addObject(TrorConstants.DOMAIN_MODEL , model);
    					return errorView;
    				}else{
    					//Delete succefully done!
    		    		logger.info("[INFO] Valid Delete -- Record successfully deleted, OK ");
    				}
    			}
				
			}
			//fetch of lines
			this.fetchItemLines(appUser, avd, opd, model, session);
			
	    	return successView;
		}
	}
	
	/**
	 * Update line ( (A)dd, (U)pdate, (D)elete )
	 * @param appUser
	 * @param recordToValidate
	 * @param mode
	 * @param avd
	 * @param opd
	 * @return
	 */
	private JsonTrorOrderHeaderFrisokveiContainer executeUpdateLine(SystemaWebUser appUser, JsonTrorOrderHeaderFrisokveiRecord recordToValidate, String mode,
										String avd, String opd){
		JsonTrorOrderHeaderFrisokveiContainer retval = null;
		
		logger.info("[INFO] EXECUTE Update(D/A/U) line nr:" + recordToValidate.getFskode() + " start process... ");
		String BASE_URL = TrorUrlDataStore.TROR_BASE_UPDATE_MAIN_ORDER_FRISOKVEI_URL;
    	//add URL-parameters
		StringBuffer urlRequestParams = new StringBuffer();
		urlRequestParams.append("user=" + appUser.getUser());
		urlRequestParams.append("&avd=" + avd);
		urlRequestParams.append("&opd=" + opd);
		urlRequestParams.append("&mode=" + mode);
		
		if("U".equals(mode)){
			urlRequestParams.append("&fskode=" + recordToValidate.getFskode());
			urlRequestParams.append("&fssok=" + recordToValidate.getFssok());
			urlRequestParams.append("&fsdokk=" + recordToValidate.getFsdokk());
			urlRequestParams.append("&o_fskode=" + recordToValidate.getFskodeKey());
			urlRequestParams.append("&o_fssok=" + recordToValidate.getFssokKey());
			
		}else if("A".equals(mode)){
			urlRequestParams.append("&fskode=" + recordToValidate.getFskode());
			urlRequestParams.append("&fssok=" + recordToValidate.getFssok());
			urlRequestParams.append("&fsdokk=" + recordToValidate.getFsdokk());
			
		}else if("D".equals(mode)){
			urlRequestParams.append("&o_fskode=" + recordToValidate.getFskode());
			urlRequestParams.append("&o_fssok=" + recordToValidate.getFssok());
			
		}
		
		logger.info("URL: " + BASE_URL);
		logger.info("PARAMS: " + urlRequestParams);
		logger.info(Calendar.getInstance().getTime() +  " CGI-start timestamp");
		String jsonPayload = this.urlCgiProxyService.getJsonContent(BASE_URL, urlRequestParams.toString());
		//Debug -->
		logger.debug(jsonDebugger.debugJsonPayloadWithLog4J(jsonPayload));
		logger.info(Calendar.getInstance().getTime() +  " CGI-end timestamp");
	
		if(jsonPayload!=null){
			JsonTrorOrderHeaderFrisokveiContainer container = this.trorMainOrderHeaderService.getOrderFrisokveiContainer(jsonPayload);
			retval = container;
			if(container.getErrMsg()!=null){
				logger.info(container.getErrMsg());
			}
		}
		return retval;
	}
	/**
	 * 
	 * @param appUser
	 * @param avd
	 * @param opd
	 * @param model
	 * @param session
	 */
	private void fetchItemLines(SystemaWebUser appUser, String avd, String opd, Map model, HttpSession session ){
		final String BASE_URL = TrorUrlDataStore.TROR_BASE_FETCH_MAIN_ORDER_FRISOKVEI_URL;
		//add URL-parameters
		StringBuffer urlRequestParams = new StringBuffer();
		urlRequestParams.append("user=" + appUser.getUser());
		urlRequestParams.append("&avd=" + avd); 
		urlRequestParams.append("&opd=" + opd);
		
		logger.info("URL: " + BASE_URL);
		logger.info("PARAMS: " + urlRequestParams.toString());
		String jsonPayload = this.urlCgiProxyService.getJsonContent(BASE_URL, urlRequestParams.toString());
		logger.debug(this.jsonDebugger.debugJsonPayloadWithLog4J(jsonPayload));
		logger.info(Calendar.getInstance().getTime() +  " CGI-end timestamp");
		if(jsonPayload!=null){
			JsonTrorOrderHeaderFrisokveiContainer container = this.trorMainOrderHeaderService.getOrderFrisokveiContainer(jsonPayload);
			if(container!=null){
				this.setDomainObjectsInView(model, container, session);
			}	
		}
	}
	
	/**
	 * 
	 * @param appUser
	 * @param errorMessage
	 * @param recordToValidate
	 * @param model
	 * @param parentTrip
	 * @param session
	 */
	private void populateAspectsOnBackendError(SystemaWebUser appUser, String errorMessage, JsonTrorOrderHeaderFrisokveiRecord recordToValidate, Map model, HttpSession session ){
		model.put(TrorConstants.ASPECT_ERROR_MESSAGE, "Kode/sokText:[" + recordToValidate.getFskode() + " " +  recordToValidate.getFssok() + "] " +  errorMessage);
		
    	//return objects on validation errors
		model.put("record", recordToValidate);
	}
	
	
	/**
	 * 
	 * @param model
	 * @param container
	 * @param session
	 */
	private void setDomainObjectsInView(Map model, JsonTrorOrderHeaderFrisokveiContainer container, HttpSession session){
		Collection<JsonTrorOrderHeaderFrisokveiRecord> list = new ArrayList<JsonTrorOrderHeaderFrisokveiRecord>();
		//could be two options
		if(container.getAwblinelist()!=null){
			list = container.getAwblinelist();
			/*
			for (JsonTransportDispWorkflowSpecificOrderFrisokveiRecord record: container.getAwblinelist()){
				
				list.add(record);
			}*/
		}
		
		//always keep track of the total nr of item lines
		//String nrOfItems = String.valueOf(list.size());
		//container.setTotalNumberOfItemLines(nrOfItems);
		
		logger.info("putting on model...");
		model.put(TrorConstants.DOMAIN_CONTAINER, container);
		model.put(TrorConstants.DOMAIN_LIST, list);
		//put the objects in session ONLY for the validation errors routine in an UPDATE. Otherwise we do have to retrive th
		//session.setAttribute(session.getId() + TransportDispConstants.DOMAIN_CONTAINER, container);
		//session.setAttribute(session.getId() + TransportDispConstants.DOMAIN_LIST, list);
		
	}
	
	
	/**
	 * 
	 * @param ccyy
	 * @return
	 */
	private String getCentury2digits(String ccyy){
		String retval = ccyy;
		if(ccyy!=null && !"".equals(ccyy)){
		  if(ccyy.length()==4){
			  retval = ccyy.substring(0,2);
		  }
		}
		return retval;
	}
	/**
	 * 
	 * @param ccyy
	 * @return
	 */
	private String getYear2digits(String ccyy){
		String retval = ccyy;
		if(ccyy!=null && !"".equals(ccyy)){
		  if(ccyy.length()==4){
			  retval = ccyy.substring(2);
		  }
		}
		return retval;
	}
	
	//SERVICES
	@Qualifier ("urlCgiProxyService")
	private UrlCgiProxyService urlCgiProxyService;
	@Autowired
	@Required
	public void setUrlCgiProxyService (UrlCgiProxyService value){ this.urlCgiProxyService = value; }
	public UrlCgiProxyService getUrlCgiProxyService(){ return this.urlCgiProxyService; }
	
	
	@Qualifier 
	private TransportDispChildWindowService transportDispChildWindowService;
	@Autowired
	@Required	
	public void setTransportDispChildWindowService(TransportDispChildWindowService value){this.transportDispChildWindowService = value;}
	public TransportDispChildWindowService getTransportDispChildWindowService(){ return this.transportDispChildWindowService; }
	
	/*	
	@Qualifier ("transportDispDropDownListPopulationService")
	private TransportDispDropDownListPopulationService transportDispDropDownListPopulationService;
	@Autowired
	public void setTransportDispDropDownListPopulationService (TransportDispDropDownListPopulationService value){ this.transportDispDropDownListPopulationService=value; }
	public TransportDispDropDownListPopulationService getTransportDispDropDownListPopulationService(){return this.transportDispDropDownListPopulationService;}
	

	@Qualifier ("transportDispWorkflowSpecificOrderService")
	private TransportDispWorkflowSpecificOrderService transportDispWorkflowSpecificOrderService;
	@Autowired
	public void setTransportDispWorkflowSpecificOrderService (TransportDispWorkflowSpecificOrderService value){ this.transportDispWorkflowSpecificOrderService=value; }
	public TransportDispWorkflowSpecificOrderService getTransportDispWorkflowSpecificOrderService(){return this.transportDispWorkflowSpecificOrderService;}
	
	@Qualifier 
	private TransportDispWorkflowSpecificTripService transportDispWorkflowSpecificTripService;
	@Autowired
	@Required	
	public void setTransportDispWorkflowSpecificTripService(TransportDispWorkflowSpecificTripService value){this.transportDispWorkflowSpecificTripService = value;}
	public TransportDispWorkflowSpecificTripService getTransportDispWorkflowSpecificTripService(){ return this.transportDispWorkflowSpecificTripService; }
	*/
	
	@Qualifier 
	private TrorMainOrderHeaderService trorMainOrderHeaderService;
	@Autowired
	@Required	
	public void setTrorMainOrderHeaderService(TrorMainOrderHeaderService value){this.trorMainOrderHeaderService = value;}
	public TrorMainOrderHeaderService getTrorMainOrderHeaderService(){ return this.trorMainOrderHeaderService; }
	
	
	
	
}

