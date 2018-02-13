package no.systema.tror.controller;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.util.*;

import org.apache.commons.lang3.builder.ReflectionToStringBuilder;
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

//application imports
import no.systema.main.context.TdsAppContext;
import no.systema.main.service.UrlCgiProxyService;
import no.systema.main.validator.LoginValidator;
import no.systema.main.util.AppConstants;
import no.systema.main.util.DateTimeManager;
import no.systema.main.util.JsonDebugger;
import no.systema.main.util.StringManager;
import no.systema.main.model.SystemaWebUser;


//Trans.Disp
import no.systema.transportdisp.service.TransportDispChildWindowService;
import no.systema.tror.mapper.url.request.UrlRequestParameterMapper;
//common
import no.systema.jservices.common.dao.TrackfDao;
import no.systema.jservices.common.json.JsonDtoContainer;
import no.systema.jservices.common.json.JsonReader;
//tror
import no.systema.tror.validator.TrorOrderTrackfValidator;
import no.systema.tror.service.TrorMainOrderHeaderService;
import no.systema.tror.service.html.dropdown.TrorDropDownListPopulationService;
import no.systema.tror.url.store.TrorUrlDataStore;
import no.systema.tror.util.TrorConstants;
import no.systema.tror.util.manager.CodeDropDownMgr;
import no.systema.tror.util.manager.FlyImportExportManager;





/**
 * Tror Controller - Track&Trace window
 * 
 * @author oscardelatorre
 * @date Feb 13, 2018
 * 
 */

@Controller
@SessionAttributes(AppConstants.SYSTEMA_WEB_USER_KEY)
@Scope("session")
public class TrorMainOrderHeaderFlyControllerTrackTraceGeneral {
	private static final Logger logger = Logger.getLogger(TrorMainOrderHeaderFlyControllerTrackTraceGeneral.class.getName());
	private static final JsonDebugger jsonDebugger = new JsonDebugger(2000);
	private UrlRequestParameterMapper urlRequestParameterMapper = new UrlRequestParameterMapper();
	//
	private ModelAndView loginView = new ModelAndView("login");
	private ApplicationContext context;
	private LoginValidator loginValidator = new LoginValidator();
	private DateTimeManager dateTimeMgr = new DateTimeManager();
	private CodeDropDownMgr codeDropDownMgr = new CodeDropDownMgr();
	private FlyImportExportManager flyMgr = new FlyImportExportManager();
	//
	private StringManager strMgr = new StringManager();
	
	
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
	@RequestMapping(value="tror_mainorderfly_ttrace.do",  method= RequestMethod.GET)
	public ModelAndView doMoreTrackAndTrace(HttpSession session, HttpServletRequest request){
		Map model = new HashMap();
		SystemaWebUser appUser = (SystemaWebUser)session.getAttribute(AppConstants.SYSTEMA_WEB_USER_KEY);
		//String messageFromContext = this.context.getMessage("user.label",new Object[0], request.getLocale());
		ModelAndView successView = new ModelAndView("tror_mainorderfly_ttrace");
		logger.info("Method: doMoreTrackAndTrace [RequestMapping-->tror_mainorderfly_ttrace.do]");
		
		String avd = request.getParameter("avd");
		String sign = request.getParameter("sign");
		String opd = request.getParameter("opd");
		
		//this.setDomainObjectsInView(request, model);
		//check user (should be in session already)
		if(appUser==null){
			return loginView;
			
		}else{
			//---------------------------
			//get BASE URL = RPG-PROGRAM
            //---------------------------
			/*String BASE_URL = TrorUrlDataStore.TROR_BASE_ARCHIVE_FOR_SPECIFIC_TOPIC_URL;
			//url params
			String urlRequestParamsKeys = this.getRequestUrlKeyParameters(avd, opd, appUser);
			//for debug purposes in GUI
			session.setAttribute(TrorConstants.ACTIVE_URL_RPG_TROR, BASE_URL  + "==>params: " + urlRequestParamsKeys.toString()); 
			
			logger.info(Calendar.getInstance().getTime() + " CGI-start timestamp");
		    	logger.info("URL: " + jsonDebugger.getBASE_URL_NoHostName(BASE_URL));
		    	logger.info("URL PARAMS: " + urlRequestParamsKeys);
		    	//--------------------------------------
		    	//EXECUTE the FETCH (RPG program) here
		    	//--------------------------------------
		    	String jsonPayload = this.urlCgiProxyService.getJsonContent(BASE_URL, urlRequestParamsKeys);
				//Debug --> 
		    	logger.info(" --> jsonPayload:" + jsonPayload);
		    	logger.info(Calendar.getInstance().getTime() +  " CGI-end timestamp");
		    	
			if(jsonPayload!=null){
				JsonTrorOrderHeaderArchiveContainer container = this.trorMainOrderHeaderService.getArchiveContainer(jsonPayload);
	    		//add domain objects here
	    		this.setDomainObjectsInView(model, container);
	    		this.setDomainObjectsInView(request, model);
	    		
	    		successView.addObject(TrorConstants.DOMAIN_MODEL, model);
				successView.addObject(TrorConstants.DOMAIN_LIST,container.getArchiveElements());
		    		
	    	}else{
				logger.fatal("NO CONTENT on jsonPayload from URL... ??? <Null>");
				return loginView;
			}
			*/
	   		
		}
		return successView;
	}
	
	/**
	 * 
	 * @param recordToValidate
	 * @param bindingResult
	 * @param session
	 * @param request
	 * @return
	 */
	@RequestMapping(value="tror_mainorderfly_ttrace_general.do", method={RequestMethod.GET, RequestMethod.POST} )
	public ModelAndView doInit( @ModelAttribute ("record") TrackfDao recordToValidate, BindingResult bindingResult, HttpSession session, HttpServletRequest request){
		this.context = TdsAppContext.getApplicationContext();
		Map model = new HashMap();
		
		String action = request.getParameter("action");
		
		logger.info("ACTION: " + action);
		//ModelAndView successView = new ModelAndView("transportdisp_mainorder_invoice");
		ModelAndView successView = new ModelAndView("tror_mainorderfly_ttrace_general");
		SystemaWebUser appUser = this.loginValidator.getValidUser(session);
		
		//check user (should be in session already)
		if(appUser==null){
			return loginView;
			
		}else{
			logger.info(Calendar.getInstance().getTime() + " CONTROLLER start - timestamp");
			List list = (List)this.fetchItemLines(appUser, recordToValidate);
			model.put(TrorConstants.DOMAIN_LIST, list);
			
			this.adjustDefaultsOnGui(recordToValidate);
			model.put(TrorConstants.DOMAIN_RECORD, recordToValidate);
			this.setCodeDropDownMgr(appUser, model);
			//
			successView.addObject(TrorConstants.DOMAIN_MODEL , model);
	    	return successView;
		}
	}
	/**
	 * For defaults returning to GUI
	 * @param recordToValidate
	 */
	private void adjustDefaultsOnGui(TrackfDao recordToValidate){
		int nowDate = Integer.parseInt(new DateTimeManager().getCurrentDate_ISO());
		int nowTime = Integer.parseInt(new DateTimeManager().getCurrentDate_ISO("HHmmss"));
		
		if(recordToValidate.getTtdatl() == 0){ recordToValidate.setTtdatl(nowDate); }
		if(recordToValidate.getTtdate() == 0){ recordToValidate.setTtdate(nowDate); }
		if(recordToValidate.getTttiml() == 0){ recordToValidate.setTttiml(nowTime); }
		if(recordToValidate.getTttime() == 0){ recordToValidate.setTttime(nowTime); }
		
	}
	/**
	 * 
	 * @param recordToValidate
	 * @param bindingResult
	 * @param session
	 * @param request
	 * @return
	 */
	@RequestMapping(value="tror_mainorderfly_ttrace_general_edit.do",  method={RequestMethod.GET, RequestMethod.POST} )
	public ModelAndView doEditTrackAndTraceGeneral(@ModelAttribute ("record") TrackfDao recordToValidate, BindingResult bindingResult, HttpSession session, HttpServletRequest request){
		this.context = TdsAppContext.getApplicationContext();
		Map model = new HashMap();
		
		String action = request.getParameter("action");
		String updateId = request.getParameter("updateId");
		
		//Params
		StringBuffer params = new StringBuffer();
		//params.append("&bnr=" + lineId);
		params.append("&ttavd=" + recordToValidate.getTtavd());
		params.append("&ttopd=" + recordToValidate.getTtopd());
		
		logger.info("ACTION: " + action);
		ModelAndView successView = new ModelAndView("redirect:tror_mainorderfly_ttrace_general.do?action=doFind" + params.toString() );
		ModelAndView errorView = new ModelAndView("tror_mainorderfly_ttrace_general");
		
		SystemaWebUser appUser = this.loginValidator.getValidUser(session);
		
		//check user (should be in session already)
		if(appUser==null){
			return loginView;
		}else{
			logger.info(Calendar.getInstance().getTime() + " CONTROLLER start - timestamp");
			this.adjustRecordToValidate(recordToValidate);
			
			TrorOrderTrackfValidator validator = new TrorOrderTrackfValidator();
			if (TrorConstants.ACTION_DELETE.equals(action)) {
				validator.validateDelete(recordToValidate, bindingResult);
			} else {
				validator.validate(recordToValidate, bindingResult);
			}
			if (bindingResult.hasErrors()) {
				logger.info("[ERROR Validation] Record does not validate)");
				if (updateId != null && !"".equals(updateId)) {
					// meaning bounced in an Update and not a Create new
					model.put("updateId", updateId);
				}
				model.put(TrorConstants.DOMAIN_RECORD, recordToValidate);
				successView = errorView;
				
			} else {
				StringBuffer errMsg = new StringBuffer();
				int dmlRetval = 0;
				
				if (TrorConstants.ACTION_UPDATE.equals(action)) {
					if (updateId != null && !"".equals(updateId)) {
						//logger.info("UPDATE!!!");
						dmlRetval = this.flyMgr.updateRecord(appUser, recordToValidate, TrorConstants.MODE_UPDATE, errMsg, this.urlCgiProxyService, this.urlRequestParameterMapper);
					} else {
						//logger.info("CREATE NEW!!!");
						dmlRetval = this.flyMgr.updateRecord(appUser, recordToValidate, TrorConstants.MODE_ADD, errMsg, this.urlCgiProxyService, this.urlRequestParameterMapper);
					}
				} else if (TrorConstants.ACTION_DELETE.equals(action)) {
					//logger.info("DELETE !!!");
					dmlRetval = this.flyMgr.updateRecord(appUser, recordToValidate, TrorConstants.MODE_DELETE, errMsg, this.urlCgiProxyService, this.urlRequestParameterMapper);
				}
				// check for Update errors
				if (dmlRetval < 0) {
					logger.info("[ERROR DML] Record does not validate)");
					logger.info(" errMsg.toString()="+ errMsg.toString());
					model.put(TrorConstants.ASPECT_ERROR_MESSAGE, errMsg.toString());
					logger.info("recordToValidate, err="+ReflectionToStringBuilder.toString(recordToValidate));
					model.put(TrorConstants.DOMAIN_RECORD, recordToValidate); 
					
					if (updateId != null && !"".equals(updateId)) {
						// meaning bounced in an Update and not a Create new
						model.put("updateId", updateId);
						successView = errorView;
					}
				}

			}

			List list = (List)this.fetchItemLines(appUser, recordToValidate);
			model.put(TrorConstants.DOMAIN_LIST, list);
			this.setCodeDropDownMgr(appUser, model);
			successView.addObject(TrorConstants.DOMAIN_MODEL, model);
			
	    	return successView;
		}
	}
	
	private void adjustRecordToValidate(TrackfDao recordToValidate) {
		//...
	}
	/**
	 * 
	 * @param appUser
	 * @param avd
	 * @param opd
	 */
	private Collection<TrackfDao> fetchItemLines(SystemaWebUser appUser, TrackfDao record){
		//===========
		//FETCH LIST
		//===========
		JsonReader<JsonDtoContainer<TrackfDao>> jsonReader = new JsonReader<JsonDtoContainer<TrackfDao>>();
		jsonReader.set(new JsonDtoContainer<TrackfDao>());
		
		 logger.info("Inside: fetchItemLines");
		 //prepare the access CGI with RPG back-end
		 String BASE_URL = TrorUrlDataStore.TROR_BASE_FETCH_TRACK_AND_TRACE_URL;
		 String urlRequestParamsKeys = "user=" + appUser.getUser() + "&ttavd=" + record.getTtavd() + "&ttopd=" + record.getTtopd();
		 
		 logger.info("URL: " + BASE_URL);
		 logger.info("PARAMS: " + urlRequestParamsKeys);
		 logger.info(Calendar.getInstance().getTime() +  " CGI-start timestamp");
		 logger.info(Calendar.getInstance().getTime() +  " CGI-end timestamp");
		 
		 List<TrackfDao> daoList = new ArrayList<TrackfDao>();
		 String jsonPayload = this.urlCgiProxyService.getJsonContent(BASE_URL, urlRequestParamsKeys);
		 logger.debug(jsonDebugger.debugJsonPayloadWithLog4J(jsonPayload));
		 
		 if(jsonPayload!=null){
		 	try{
		 		JsonDtoContainer<TrackfDao> container = (JsonDtoContainer<TrackfDao>) jsonReader.get(jsonPayload);
				if(container!=null){
					daoList = container.getDtoList();
				}
				
		 	}catch(Exception e){
		 		e.printStackTrace();
		 	}
		 }
		 return daoList;
		 
	}
	/**
	 * 
	 * @param appUser
	 * @param record
	 * @param mode
	 * @param errMsg
	 * @return
	 */
	/*
	private int updateRecord(SystemaWebUser appUser, TrackfDao record, String mode, StringBuffer errMsg) {
		//Locale locale = VkundControllerUtil.getLocale(appUser.getUsrLang(), "svew");
		int retval = 0;
		JsonReader<JsonDtoContainer<TrackfDao>> jsonReader = new JsonReader<JsonDtoContainer<TrackfDao>>();
		jsonReader.set(new JsonDtoContainer<TrackfDao>());
		String BASE_URL = TrorUrlDataStore.TROR_BASE_UPDATE_TRACK_AND_TRACE_URL;
		String urlRequestParamsKeys = "user=" + appUser.getUser() + "&mode=" + mode + "&lang=" + appUser.getUsrLang();
		String urlRequestParams = this.urlRequestParameterMapper.getUrlParameterValidString(record);
		urlRequestParams = urlRequestParamsKeys + urlRequestParams;

		logger.info(Calendar.getInstance().getTime() + " CGI-start timestamp");
		logger.info("URL: " + jsonDebugger.getBASE_URL_NoHostName(BASE_URL));
		logger.info("URL PARAMS: " + urlRequestParams);
		String jsonPayload = this.urlCgiProxyService.getJsonContent(BASE_URL, urlRequestParams);
		if (jsonPayload != null) {
			JsonDtoContainer<TrackfDao> container = (JsonDtoContainer<TrackfDao>) jsonReader.get(jsonPayload);
			if (container != null) {
				if (container.getErrMsg() != null && !"".equals(container.getErrMsg())) {
					errMsg.append(container.getErrMsg());
					retval = TrorConstants.ERROR_CODE;
				}
			}			
		}

		return retval;
	}	
	*/
	/**
	 * 
	 * @param appUser
	 * @param model
	 */
	private void setCodeDropDownMgr(SystemaWebUser appUser, Map model){
		//general
		this.codeDropDownMgr.populateCodesHtmlDropDownsFromJsonString(urlCgiProxyService, this.trorDropDownListPopulationService, model, appUser, this.codeDropDownMgr.CODE_TYPE_TRACKT);
		
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
	
	
	@Qualifier 
	private TrorMainOrderHeaderService trorMainOrderHeaderService;
	@Autowired
	@Required	
	public void setTrorMainOrderHeaderService(TrorMainOrderHeaderService value){this.trorMainOrderHeaderService = value;}
	public TrorMainOrderHeaderService getTrorMainOrderHeaderService(){ return this.trorMainOrderHeaderService; }
	
	@Qualifier ("trorDropDownListPopulationService")
	private TrorDropDownListPopulationService trorDropDownListPopulationService;
	@Autowired
	@Required
	public void setTrorDropDownListPopulationService (TrorDropDownListPopulationService value){ this.trorDropDownListPopulationService = value; }
	public TrorDropDownListPopulationService getTrorDropDownListPopulationService(){ return this.trorDropDownListPopulationService; }
	
	
	
	
}

