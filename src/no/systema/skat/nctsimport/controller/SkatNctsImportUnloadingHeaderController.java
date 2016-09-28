package no.systema.skat.nctsimport.controller;

import java.util.*;

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

import javax.annotation.PostConstruct;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.web.bind.ServletRequestDataBinder;
import org.springframework.web.bind.WebDataBinder;


//application imports
import no.systema.main.service.UrlCgiProxyService;
import no.systema.main.util.AppConstants;
import no.systema.main.util.DateTimeManager;
import no.systema.main.util.JsonDebugger;
import no.systema.skat.model.jsonjackson.avdsignature.JsonSkatAvdelningContainer;
import no.systema.skat.model.jsonjackson.avdsignature.JsonSkatAvdelningRecord;
import no.systema.skat.model.jsonjackson.avdsignature.JsonSkatSignatureContainer;
import no.systema.skat.model.jsonjackson.avdsignature.JsonSkatSignatureRecord;
import no.systema.skat.service.html.dropdown.SkatDropDownListPopulationService;
import no.systema.skat.nctsimport.validator.SkatNctsImportUnloadingValidator;

import no.systema.main.model.SystemaWebUser;
import no.systema.skat.nctsimport.model.jsonjackson.topic.JsonSkatNctsImportSpecificTopicContainer;
import no.systema.skat.nctsimport.model.jsonjackson.topic.JsonSkatNctsImportSpecificTopicRecord;
import no.systema.skat.nctsimport.model.jsonjackson.topic.unloading.JsonSkatNctsImportSpecificTopicUnloadingContainer;
import no.systema.skat.nctsimport.model.jsonjackson.topic.unloading.JsonSkatNctsImportSpecificTopicUnloadingRecord;


import no.systema.skat.nctsimport.service.SkatNctsImportSpecificTopicService;
import no.systema.skat.nctsimport.service.SkatNctsImportSpecificTopicUnloadingService;

import no.systema.skat.nctsimport.service.html.dropdown.SkatNctsImportDropDownListPopulationService;
import no.systema.skat.nctsimport.url.store.SkatNctsImportUrlDataStore;
import no.systema.skat.nctsimport.util.RpgReturnResponseHandler;
import no.systema.skat.url.store.SkatUrlDataStore;
import no.systema.skat.util.SkatConstants;
import no.systema.skat.nctsimport.util.manager.CodeDropDownMgr;

import no.systema.skat.nctsimport.mapper.url.request.UrlRequestParameterMapper;


/**
 * Skat-NCTS Import Topic Controller 
 * 
 * @author oscardelatorre
 * @date Apr 28, 2014
 */

@Controller
@Scope("session")
public class SkatNctsImportUnloadingHeaderController {
	private static final JsonDebugger jsonDebugger = new JsonDebugger(800);
	private static final Logger logger = Logger.getLogger(SkatNctsImportUnloadingHeaderController.class.getName());
	private UrlRequestParameterMapper urlRequestParameterMapper = new UrlRequestParameterMapper();
	private ModelAndView loginView = new ModelAndView("login");
	private CodeDropDownMgr codeDropDownMgr = new CodeDropDownMgr();
	private DateTimeManager dateTimeManager = new DateTimeManager();

	private ApplicationContext context;
	
	@InitBinder
    protected void initBinder(WebDataBinder binder) {
		//binder.setValidator(new NctsExportValidator()); //it must have spring form tags in the html otherwise = meaningless
    }
	
	@PostConstruct
	public void initIt() throws Exception {
		if("DEBUG".equals(AppConstants.LOG4J_LOGGER_LEVEL)){
			logger.setLevel(Level.DEBUG);
		}
	}
	
	
	/**
	 * Renders the create GUI view (without any logic)
	 * 
	 * @param session
	 * @param request
	 * @return
	 */
	@RequestMapping(value="skatnctsimport_unloading_edit.do",  params="action=doPrepareCreate", method={RequestMethod.GET, RequestMethod.POST })
	public ModelAndView doPrepareCreate(HttpSession session, HttpServletRequest request){
		
		Map model = new HashMap();
		SystemaWebUser appUser = (SystemaWebUser)session.getAttribute(AppConstants.SYSTEMA_WEB_USER_KEY);
		//String messageFromContext = this.context.getMessage("user.label",new Object[0], request.getLocale());
		ModelAndView successView = new ModelAndView("skatnctsimport_unloading_edit");
		logger.info("Method: doPrepareCreate [RequestMapping-->skatnctsimport_unloading_edit.do]");
		//check user (should be in session already)
		if(appUser==null){
			return loginView;
			
		}else{
			
			this.setCodeDropDownMgr(appUser, model);
	    		this.populateAvdelningHtmlDropDownsFromJsonString(model, appUser);
	    		this.populateSignatureHtmlDropDownsFromJsonString(model, appUser);
	    		
	    		//domain
	    		successView.addObject("model", model);
	    		successView.addObject(SkatConstants.EDIT_ACTION_ON_TOPIC, SkatConstants.ACTION_CREATE);

		}
		
		return successView;
	}
	
	/**
	 * Creates or Updates a new Topic (Arende)
	 * @param recordToValidate
	 * @param bindingResult
	 * @param session
	 * @param request
	 * @return
	 */
	@RequestMapping(value="skatnctsimport_unloading_edit.do")
	public ModelAndView doNctsImportUnloadingEdit(@ModelAttribute ("record") JsonSkatNctsImportSpecificTopicUnloadingRecord recordToValidate, BindingResult bindingResult, HttpSession session, HttpServletRequest request){
		
		ModelAndView successView = new ModelAndView("skatnctsimport_unloading_edit");
		String method = "doNctsImportEdit [RequestMapping-->skatnctsimport_unloading_edit.do]";
		RpgReturnResponseHandler rpgReturnResponseHandler = new RpgReturnResponseHandler();
		
		JsonSkatNctsImportSpecificTopicRecord recordTopicHeader = (JsonSkatNctsImportSpecificTopicRecord)session.getAttribute(SkatConstants.DOMAIN_RECORD_TOPIC_SKAT);
		SystemaWebUser appUser = (SystemaWebUser)session.getAttribute(AppConstants.SYSTEMA_WEB_USER_KEY);
		logger.info("Method: " + method);
		//---------------------------------
		//Crucial request parameters (Keys
		//---------------------------------
		String action = this.getAction(request.getParameter("action"));
		
		String opd = request.getParameter("opd");
		String avd = request.getParameter("avd");
		String sign = request.getParameter("sign");
		String origin = request.getParameter("origo");
		
		//Action (doFetch, doUpdate, doCreate)
		logger.info("Action:" + action);
		logger.info("Opd:" + opd);
		logger.info("Origo:" + origin);
		boolean isValid = true;
		
		Map model = new HashMap();
		
		if(appUser==null){
			return this.loginView;

		}else{
			if(action!=null){
		    	//----------------------------
				//UPDATE RECORD
				//----------------------------	
				if(SkatConstants.ACTION_UPDATE.equals(action)){
					//---------------------
					//Validation Light GUI
					//---------------------
					SkatNctsImportUnloadingValidator validator = new SkatNctsImportUnloadingValidator();
					logger.info("VALIDATING...");
					validator.validate(recordToValidate, bindingResult);
					
				    //check for ERRORS
					if(bindingResult.hasErrors()){
				    	logger.info("[ERROR Validation] Record does not validate)");
				    	origin="list";
				    	isValid = false;
				    	//put domain objects and do go back to the original view...
					    //recordToValidate.setTiavd(avd);
				    	//recordToValidate.setTisg(sign);
					    //this.setDomainObjectsInView(session, model, recordToValidate);
					    	
				    }else{
			    		JsonSkatNctsImportSpecificTopicUnloadingRecord jsonNctsImportSpecificTopicUnloadingRecord = null;
						String tuid = null;
						
						if(opd!=null && !"".equals(opd)){
							logger.info("PURE UPDATE transaction...");
							//PURE UDPATE transaction
							//this means that the update is an update of an existing record
							jsonNctsImportSpecificTopicUnloadingRecord = new JsonSkatNctsImportSpecificTopicUnloadingRecord();
							ServletRequestDataBinder binder = new ServletRequestDataBinder(jsonNctsImportSpecificTopicUnloadingRecord);
							
				            binder.bind(request);
				            //binder.registerCustomEditor(...); // if needed
							logger.info("TIENKL [after bind]: " + jsonNctsImportSpecificTopicUnloadingRecord.getTienkl());
						}
						//--------------------------------------------------
						//At this point we are ready to do an update
						//--------------------------------------------------
						
					    String BASE_URL = SkatNctsImportUrlDataStore.NCTS_IMPORT_BASE_UPDATE_SPECIFIC_TOPIC_UNLOADING_URL;
						
						//-----------------------------------
						//add URL-parameter "mode=U" (Update)
						//-----------------------------------
						String urlRequestParamsKeys = this.getRequestUrlKeyParameters(action, avd, opd, sign, appUser);
						//build the url parameters (from GUI-form) to send on a GET/POST method AFTER the keyParameters
						String urlRequestParamsTopic = this.urlRequestParameterMapper.getUrlParameterValidString((jsonNctsImportSpecificTopicUnloadingRecord));
						//put the final valid param. string
						String urlRequestParams = urlRequestParamsKeys + urlRequestParamsTopic;
						//for debug purposes in GUI
						session.setAttribute(SkatConstants.ACTIVE_URL_RPG_SKAT, BASE_URL); 
				    	
						logger.info(Calendar.getInstance().getTime() + " CGI-start timestamp");
					    	logger.info("URL: " + BASE_URL);
					    	logger.info("URL PARAMS: " + urlRequestParams);
					    	//----------------------------------------------------------------------------
					    	//EXECUTE the UPDATE (RPG program) here (STEP [2] when creating a new record)
					    	//----------------------------------------------------------------------------
					    	String rpgReturnPayload = this.urlCgiProxyService.getJsonContent(BASE_URL, urlRequestParams);
							//Debug --> 
					    	logger.info("Checking errMsg in rpgReturnPayload" + rpgReturnPayload);
					    	//we must evaluate a return RPG code in order to know if the Update was OK or not
					    	rpgReturnResponseHandler.evaluateRpgResponseOnTopicUpdate(rpgReturnPayload);
					    	if(rpgReturnResponseHandler.getErrorMessage()!=null && !"".equals(rpgReturnResponseHandler.getErrorMessage())){
					    		rpgReturnResponseHandler.setErrorMessage("[ERROR] FATAL on UPDATE: " + rpgReturnResponseHandler.getErrorMessage());
					    		this.setFatalError(model, rpgReturnResponseHandler, jsonNctsImportSpecificTopicUnloadingRecord);
					    	}else{
					    		//Update succefully done!
					    		logger.info("[INFO] Record successfully updated, OK ");	
					    	}		
				    }
				}
				
				//-------------
				//FETCH RECORD
				//-------------
				logger.info("FETCH record transaction...");
				//---------------------------
				//get BASE URL = RPG-PROGRAM
	            //---------------------------
				String BASE_URL = SkatNctsImportUrlDataStore.NCTS_IMPORT_BASE_FETCH_SPECIFIC_TOPIC_UNLOADING_URL;
				//url params
				action = SkatConstants.ACTION_FETCH;
				String urlRequestParamsKeys = this.getRequestUrlKeyParameters(action, avd, opd, sign, appUser);
				//for debug purposes in GUI
				session.setAttribute(SkatConstants.ACTIVE_URL_RPG_SKAT, BASE_URL  + "==>params: " + urlRequestParamsKeys.toString()); 
				
				logger.info(Calendar.getInstance().getTime() + " CGI-start timestamp");
		    	logger.info("URL: " + BASE_URL);
		    	logger.info("URL PARAMS: " + urlRequestParamsKeys);
		    	//--------------------------------------
		    	//EXECUTE the FETCH (RPG program) here
		    	//--------------------------------------
		    	String jsonPayload = this.urlCgiProxyService.getJsonContent(BASE_URL, urlRequestParamsKeys);
				//Debug --> 
		    	logger.debug(jsonDebugger.debugJsonPayloadWithLog4J(jsonPayload));
		    	logger.info(Calendar.getInstance().getTime() +  " CGI-end timestamp");
		    	if(jsonPayload!=null){
		    		JsonSkatNctsImportSpecificTopicUnloadingContainer jsonNctsImportSpecificTopicUnloadingContainer = this.skatNctsImportSpecificTopicUnloadingService.getNctsImportSpecificTopicUnloadingContainer(jsonPayload);
		    		//add drop-downs
		    		this.setCodeDropDownMgr(appUser, model);
		    		//only when validation = OK
		    		if(isValid){
		    			this.setDomainObjectsInView(session, model, jsonNctsImportSpecificTopicUnloadingContainer);
		    		}else{
		    			model.put(SkatConstants.DOMAIN_RECORD, recordToValidate);
		    		}
		    		//We must fetch the parent topic record when the end user is coming from the list of topics and not from the topic view
		    		if(origin!=null && origin.equals("list")){
		    			logger.info("Fetching the specific topic from origin=list...");
		    			this.getSpecificTopicRecord(session,avd,opd,sign,appUser);
		    		}
		    		
		    		
	    		successView.addObject(SkatConstants.DOMAIN_MODEL, model);
				//put the doUpdate action since we are preparing the record for an update (when saving)
				successView.addObject(SkatConstants.EDIT_ACTION_ON_TOPIC, SkatConstants.ACTION_UPDATE);
		    		
		    	}else{
		    		logger.fatal("NO CONTENT on jsonPayload from URL... ??? <Null>");
		    		return loginView;
				}    	
				
			}
		
		successView.addObject(SkatConstants.EDIT_ACTION_ON_TOPIC, SkatConstants.ACTION_UPDATE); //remove this line	
	    	
		return successView;

		}
	}
	
	
	/**
	 * 
	 * @param session
	 * @param request
	 * @return
	 */
	@RequestMapping(value="skatnctsimport_unloading_send.do")
	public ModelAndView doNctsImportUnloadingSend(HttpSession session, HttpServletRequest request){
		SystemaWebUser appUser = (SystemaWebUser)session.getAttribute(AppConstants.SYSTEMA_WEB_USER_KEY);
		appUser.setActiveMenu(SystemaWebUser.ACTIVE_MENU_SKAT_NCTS_IMPORT);
		ModelAndView successView = new ModelAndView("redirect:skatnctsimport.do?action=doFind&sign=" + appUser.getSkatSign());
		
		RpgReturnResponseHandler rpgReturnResponseHandler = new RpgReturnResponseHandler();
		
		//---------------------------------
		//Crucial request parameters (Keys
		//---------------------------------
		String action = SkatConstants.ACTION_SEND;
		String opd = request.getParameter("opd");
		String avd = request.getParameter("avd");
		String sign = request.getParameter("sign");
		
		//Action (doFetch, doUpdate, doCreate)
		logger.info("Action:" + action);
		Map model = new HashMap();
		
		
		if(appUser==null){
			return this.loginView;
		}else{
		    //---------------------------
			//get BASE URL = RPG-PROGRAM
            //---------------------------
			String BASE_URL = SkatNctsImportUrlDataStore.NCTS_IMPORT_BASE_UPDATE_SPECIFIC_TOPIC_UNLOADING_URL;
			
			//-----------------------------------
			//add URL-parameter "mode=S" (Send)
			//-----------------------------------
			String urlRequestParamsKeys = this.getRequestUrlKeyParameters(action, avd, opd, sign, appUser);
			//there is only key parameters in doSend. No other topic (record) specific parameters from GUI or such
			String urlRequestParams = urlRequestParamsKeys;
			//for debugging purposes
			session.setAttribute(SkatConstants.ACTIVE_URL_RPG_SKAT, BASE_URL); 
			
			logger.info(Calendar.getInstance().getTime() + " CGI-start timestamp");
		    	logger.info("URL: " + BASE_URL);
		    	logger.info("URL PARAMS: " + urlRequestParams);
		    	//----------------------------------------------------------------------------
		    	//EXECUTE the UPDATE (RPG program) here (STEP [2] when creating a new record)
		    	//----------------------------------------------------------------------------
		    	String rpgReturnPayload = this.urlCgiProxyService.getJsonContent(BASE_URL, urlRequestParams);
				//Debug --> 
		    	logger.info("Checking errMsg in rpgReturnPayload" + rpgReturnPayload);
		    	//we must evaluate a return RPG code in order to know if the Update was OK or not
		    	rpgReturnResponseHandler.evaluateRpgResponseOnTopicUpdate(rpgReturnPayload);
		    	if(rpgReturnResponseHandler.getErrorMessage()!=null && !"".equals(rpgReturnResponseHandler.getErrorMessage())){
		    		rpgReturnResponseHandler.setErrorMessage("[ERROR] FATAL on UPDATE: " + rpgReturnResponseHandler.getErrorMessage());
		    		//this.setFatalError(model, rpgReturnResponseHandler, jsonTdsExportSpecificTopicRecord);
		    		//TODO ERROR HANDLING HERE... stay in the same page ?
		    	}else{
		    		//Update succefully done!
		    		logger.info("[INFO] Record successfully sent [changed status], OK ");
		    		//put domain objects
		    		//this.setDomainObjectsInView(session, model, jsonTdsExportSpecificTopicRecord);
		    		//TODO SUCCESS should stay at the same side or not? Right now we go to the list of topics
		    	}
			
		}
		return successView;
	}
	
	/**
	 * 
	 * @param avd
	 * @param opd
	 * @param sign
	 * @param appUser
	 */
	private void getSpecificTopicRecord(HttpSession session, String avd, String opd, String sign, SystemaWebUser appUser){
		//---------------------------
		//get BASE URL = RPG-PROGRAM
        //---------------------------
		String BASE_URL = SkatNctsImportUrlDataStore.NCTS_IMPORT_BASE_FETCH_SPECIFIC_TOPIC_URL;
		//url params
		String urlRequestParamsKeys = this.getRequestUrlKeyParameters(SkatConstants.ACTION_FETCH, avd, opd, sign, appUser);
		//for debug purposes in GUI
		session.setAttribute(SkatConstants.ACTIVE_URL_RPG_SKAT, BASE_URL  + "==>params: " + urlRequestParamsKeys.toString()); 
		
		logger.info(Calendar.getInstance().getTime() + " CGI-start timestamp");
	    	logger.info("URL: " + BASE_URL);
	    	logger.info("URL PARAMS: " + urlRequestParamsKeys);
	    	//--------------------------------------
	    	//EXECUTE the FETCH (RPG program) here
	    	//--------------------------------------
	    	String jsonPayload = this.urlCgiProxyService.getJsonContent(BASE_URL, urlRequestParamsKeys);
		//Debug --> 
	    	//logger.debug(jsonDebugger.debugJsonPayloadWithLog4J(jsonPayload));
	    	//logger.info(Calendar.getInstance().getTime() +  " CGI-end timestamp");
	    	if(jsonPayload!=null){
	    		JsonSkatNctsImportSpecificTopicContainer jsonNctsImportSpecificTopicContainer = this.skatNctsImportSpecificTopicService.getNctsImportSpecificTopicContainer(jsonPayload);
	    		for (JsonSkatNctsImportSpecificTopicRecord record : jsonNctsImportSpecificTopicContainer.getOneorder()){
	    			//model.put(TdsConstants.DOMAIN_RECORD, record);
	    			
	    			//put the header topic in session for the coming item lines
	    			session.setAttribute(SkatConstants.DOMAIN_RECORD_TOPIC_SKAT, record);
	    		}
	    		
	    	}
	}
	
	/**
	 * 
	 * @param action
	 * @return
	 */
	private String getAction(String action){
		String retval = action;
		
		//default behavior
		if(action==null || "".equals(action)){
			retval = SkatConstants.ACTION_FETCH;
			logger.info("setting default value <doFetch> to action");
		}
		
		return retval;
	}
	
	
	/**
	 * log Errors in Aspects and Domain objects in order to render on GUI
	 * @param model
	 * @param rpgReturnResponseHandler
	 * @param jsonTdsExportSpecificTopicRecord
	 */
	private void setFatalError(Map model, RpgReturnResponseHandler rpgReturnResponseHandler, JsonSkatNctsImportSpecificTopicUnloadingRecord record){
		logger.info(rpgReturnResponseHandler.getErrorMessage());
		this.setAspectsInView(model, rpgReturnResponseHandler);
		//No refresh on jsonRecord is done for the GUI (form fields). Must be implemented right here, if required. !!
        this.setDomainObjectsInView(model, record);
	}

	
	/**
	 * Gets the key parameter string (such as avd, opd, user, mode)
	 * @param action
	 * @param avd
	 * @param opd
	 * @param sign
	 * @param appUser
	 * @return
	 */
	private String getRequestUrlKeyParameters(String action, String avd, String opd, String sign, SystemaWebUser appUser){
		StringBuffer urlRequestParamsKeys = new StringBuffer();
		//String action = request.getParameter("action");
		
		if(SkatConstants.ACTION_FETCH.equals(action)){
			urlRequestParamsKeys.append("user=" + appUser.getUser());
			urlRequestParamsKeys.append(SkatConstants.URL_CHAR_DELIMETER_FOR_PARAMS_WITH_HTML_REQUEST + "avd=" + avd);
			urlRequestParamsKeys.append(SkatConstants.URL_CHAR_DELIMETER_FOR_PARAMS_WITH_HTML_REQUEST + "opd=" + opd);
			
		}else if(SkatConstants.ACTION_UPDATE.equals(action)){
			urlRequestParamsKeys.append("user=" + appUser.getUser());
			urlRequestParamsKeys.append(SkatConstants.URL_CHAR_DELIMETER_FOR_PARAMS_WITH_HTML_REQUEST + "tiavd=" + avd); //tiavd is the one used in the AS400 pgm (sends in the jsonRecord bean but this must be sent, in addition)
			urlRequestParamsKeys.append(SkatConstants.URL_CHAR_DELIMETER_FOR_PARAMS_WITH_HTML_REQUEST + "titdn=" + opd); //titdn is the one used in the AS400 pgm (sends in the jsonRecord bean but this must be sent, in addition)
			urlRequestParamsKeys.append(SkatConstants.URL_CHAR_DELIMETER_FOR_PARAMS_WITH_HTML_REQUEST + "mode=" + SkatConstants.MODE_UPDATE);
			
		}else if(SkatConstants.ACTION_CREATE.equals(action)){
			urlRequestParamsKeys.append("user=" + appUser.getUser());
			urlRequestParamsKeys.append(SkatConstants.URL_CHAR_DELIMETER_FOR_PARAMS_WITH_HTML_REQUEST + "N/A=" + avd);
			urlRequestParamsKeys.append(SkatConstants.URL_CHAR_DELIMETER_FOR_PARAMS_WITH_HTML_REQUEST + "N/A=" + opd);
			urlRequestParamsKeys.append(SkatConstants.URL_CHAR_DELIMETER_FOR_PARAMS_WITH_HTML_REQUEST + "N/A=" + sign);
			urlRequestParamsKeys.append(SkatConstants.URL_CHAR_DELIMETER_FOR_PARAMS_WITH_HTML_REQUEST + "mode=" + SkatConstants.MODE_ADD);
			
		}else if(SkatConstants.ACTION_SEND.equals(action)){
			urlRequestParamsKeys.append("user=" + appUser.getUser());
			urlRequestParamsKeys.append(SkatConstants.URL_CHAR_DELIMETER_FOR_PARAMS_WITH_HTML_REQUEST + "tiavd=" + avd);
			urlRequestParamsKeys.append(SkatConstants.URL_CHAR_DELIMETER_FOR_PARAMS_WITH_HTML_REQUEST + "titdn=" + opd);
			urlRequestParamsKeys.append(SkatConstants.URL_CHAR_DELIMETER_FOR_PARAMS_WITH_HTML_REQUEST + "mode=" + SkatConstants.MODE_SEND);
			
		}
		return urlRequestParamsKeys.toString();
	}
	
	
	/**
	 * Populates the html object (model map for the JSTL)
	 * 
	 * @param session
	 * @param model
	 * @param jsonNctsImportSpecificTopicUnloadingContainer
	 * @return
	 */
	private void setDomainObjectsInView(HttpSession session, Map model, JsonSkatNctsImportSpecificTopicUnloadingContainer container){
		//SET HEADER RECORDS  (from RPG)
		
		for (JsonSkatNctsImportSpecificTopicUnloadingRecord record : container.getOneorder()){
			logger.info("nidfkd:" + record.getNidfkd());
			//set default current date as unloading date ...
			if(record.getNidtl()==null || "".equals(record.getNidtl())){
				//OBSOLETE --> record.setNidtl(this.dateTimeManager.getCurrentDate_ISO());
			}
			model.put(SkatConstants.DOMAIN_RECORD, record);
			//put the header topic in session for the coming item lines
			session.setAttribute(SkatConstants.DOMAIN_RECORD_TOPIC_SKAT_UNLOADING, record);
		}

	}
	
	/**
	 * Populates the html object (model map for the JSTL)
	 * 
	 * @param model
	 * @param jsonTdsExportSpecificTopicRecord
	 * @return
	 */
	private void setDomainObjectsInView(HttpSession session, Map model, JsonSkatNctsImportSpecificTopicUnloadingRecord record){
		//SET HEADER RECORDS  (from RPG)
		model.put(SkatConstants.DOMAIN_RECORD, record);
		//put the header topic in session for the coming item lines
		session.setAttribute(SkatConstants.DOMAIN_RECORD_TOPIC_SKAT, record);
	}
	/**
	 * 
	 * 
	 * @param model
	 * @param jsonTdsExportSpecificTopicRecord
	 */
	private void setDomainObjectsInView(Map model, JsonSkatNctsImportSpecificTopicUnloadingRecord record){
		//SET HEADER RECORDS  (from RPG)
		model.put(SkatConstants.DOMAIN_RECORD, record);
	}
	/**
	 * Sets aspects 
	 * Usually error objects, log objects, other non-domain related objects
	 * @param model
	 */
	
	private void setAspectsInView (Map model, RpgReturnResponseHandler rpgReturnResponseHandler){
		model.put(SkatConstants.ASPECT_ERROR_MESSAGE, rpgReturnResponseHandler.getErrorMessage());
		//extra error information
		StringBuffer errorMetaInformation = new StringBuffer();
		errorMetaInformation.append(rpgReturnResponseHandler.getUser());
		errorMetaInformation.append(rpgReturnResponseHandler.getTitdn());
		model.put(SkatConstants.ASPECT_ERROR_META_INFO, errorMetaInformation);
		
	}
			
	
	/**
	 * 
	 * @param model
	 * @param appUser
	 */
	private void populateAvdelningHtmlDropDownsFromJsonString(Map model, SystemaWebUser appUser){
		//fill in html lists here
		String NCTS_IMPORT_IE = "N"; //Import
		try{
			String BASE_URL = SkatUrlDataStore.SKAT_FETCH_AVDELNINGAR_URL;
			StringBuffer urlRequestParamsKeys = new StringBuffer();
			urlRequestParamsKeys.append("user=" + appUser.getUser());
			urlRequestParamsKeys.append(SkatConstants.URL_CHAR_DELIMETER_FOR_PARAMS_WITH_HTML_REQUEST + "ie=" + NCTS_IMPORT_IE);
			//Now build the URL and send to the back end via the drop down service
			String url = this.urlCgiProxyService.getJsonContent(BASE_URL, urlRequestParamsKeys.toString());
			logger.info("AVD BASE_URL:" + BASE_URL);
			logger.info("AVD BASE_PARAMS:" + urlRequestParamsKeys.toString());
			
			JsonSkatAvdelningContainer container = this.skatDropDownListPopulationService.getAvdelningContainer(url);
			List<JsonSkatAvdelningRecord> list = new ArrayList();
			for(JsonSkatAvdelningRecord record: container.getAvdelningar()){
				list.add(record);
			}
			model.put(SkatConstants.RESOURCE_MODEL_KEY_AVD_LIST, list);
			
			
		}catch(Exception e){
			e.printStackTrace();
		}
		
	}	
	
	/**
	 * 
	 * @param model
	 * @param appUser
	 */
	private void populateSignatureHtmlDropDownsFromJsonString(Map model, SystemaWebUser appUser){
		//fill in html lists here
		String NCTS_IMPORT_IE = "N"; //NCTS = ie=N 
		
		try{
			String BASE_URL = SkatUrlDataStore.SKAT_FETCH_SIGNATURE_URL;
			StringBuffer urlRequestParamsKeys = new StringBuffer();
			urlRequestParamsKeys.append("user=" + appUser.getUser());
			urlRequestParamsKeys.append(SkatConstants.URL_CHAR_DELIMETER_FOR_PARAMS_WITH_HTML_REQUEST + "ie=" + NCTS_IMPORT_IE);
			//Now build the URL and send to the back end via the drop down service
			String url = this.urlCgiProxyService.getJsonContent(BASE_URL, urlRequestParamsKeys.toString());
			logger.info("SIGN BASE_URL:" + BASE_URL);
			logger.info("SIGN BASE_PARAMS:" + urlRequestParamsKeys.toString());
			
			JsonSkatSignatureContainer container = this.skatDropDownListPopulationService.getSignatureContainer(url);
			List<JsonSkatSignatureRecord> list = new ArrayList();
			for(JsonSkatSignatureRecord record: container.getSignaturer()){
				list.add(record);
			}
			model.put(SkatConstants.RESOURCE_MODEL_KEY_SIGN_LIST, list);
			
			
		}catch(Exception e){
			e.printStackTrace();
		}
		
	}	
	

	/**
	 * 
	 * @param appUser
	 * @param model
	 */
	private void setCodeDropDownMgr(SystemaWebUser appUser, Map model){
		this.codeDropDownMgr.populateCodesHtmlDropDownsFromJsonString(this.urlCgiProxyService, this.skatDropDownListPopulationService,
				 model,appUser,CodeDropDownMgr.CODE_008_COUNTRY, null, null);
		this.codeDropDownMgr.populateCodesHtmlDropDownsFromJsonString(this.urlCgiProxyService, this.skatDropDownListPopulationService,
				 model,appUser,CodeDropDownMgr.CODE_012_SPRAK, null, null);
		this.codeDropDownMgr.populateCodesHtmlDropDownsFromJsonString(this.urlCgiProxyService, this.skatDropDownListPopulationService,
				 model,appUser,CodeDropDownMgr.CODE_031_DEKLTYPE, null, null);
		this.codeDropDownMgr.populateCodesHtmlDropDownsFromJsonString(this.urlCgiProxyService, this.skatDropDownListPopulationService,
				 model,appUser,CodeDropDownMgr.CODE_301_STATUS_KODER, null, null);
		
		
	}
	
	
	//SERVICES
	@Qualifier ("urlCgiProxyService")
	private UrlCgiProxyService urlCgiProxyService;
	@Autowired
	@Required
	public void setUrlCgiProxyService (UrlCgiProxyService value){ this.urlCgiProxyService = value; }
	public UrlCgiProxyService getUrlCgiProxyService(){ return this.urlCgiProxyService; }
	
	
	@Qualifier ("skatNctsImportSpecificTopicService")
	private SkatNctsImportSpecificTopicService skatNctsImportSpecificTopicService;
	@Autowired
	@Required
	public void setSkatNctsImportSpecificTopicService (SkatNctsImportSpecificTopicService value){ this.skatNctsImportSpecificTopicService = value; }
	public SkatNctsImportSpecificTopicService getSkatNctsImportSpecificTopicService(){ return this.skatNctsImportSpecificTopicService; }
	
	
	@Qualifier ("skatNctsImportSpecificTopicUnloadingService")
	private SkatNctsImportSpecificTopicUnloadingService skatNctsImportSpecificTopicUnloadingService;
	@Autowired
	@Required
	public void setSkatNctsImportSpecificTopicUnloadingService (SkatNctsImportSpecificTopicUnloadingService value){ this.skatNctsImportSpecificTopicUnloadingService = value; }
	public SkatNctsImportSpecificTopicUnloadingService getSkatNctsImportSpecificTopicUnloadingService(){ return this.skatNctsImportSpecificTopicUnloadingService; }
	
	
	@Qualifier ("skatNctsImportDropDownListPopulationService")
	private SkatNctsImportDropDownListPopulationService skatNctsImportDropDownListPopulationService;
	@Autowired
	public void setSkatNctsImportDropDownListPopulationService(SkatNctsImportDropDownListPopulationService value){ this.skatNctsImportDropDownListPopulationService=value; }
	public SkatNctsImportDropDownListPopulationService getSkatNctsImportDropDownListPopulationService(){return this.skatNctsImportDropDownListPopulationService;}
	
	
	@Qualifier ("skatDropDownListPopulationService")
	private SkatDropDownListPopulationService skatDropDownListPopulationService;
	@Autowired
	public void setSkatDropDownListPopulationService (SkatDropDownListPopulationService value){ this.skatDropDownListPopulationService=value; }
	public SkatDropDownListPopulationService getSkatDropDownListPopulationService(){return this.skatDropDownListPopulationService;}
	
	
}

