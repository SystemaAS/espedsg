package no.systema.skat.skatexport.controller;

import java.lang.reflect.Field;
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
import no.systema.main.context.TdsAppContext;
import no.systema.main.service.UrlCgiProxyService;
import no.systema.main.validator.LoginValidator;
import no.systema.main.util.AppConstants;
import no.systema.main.util.JsonDebugger;



import no.systema.main.model.SystemaWebUser;

import no.systema.skat.service.html.dropdown.SkatDropDownListPopulationService;
import no.systema.skat.model.jsonjackson.avdsignature.JsonSkatAvdelningContainer;
import no.systema.skat.model.jsonjackson.avdsignature.JsonSkatAvdelningRecord;
import no.systema.skat.model.jsonjackson.avdsignature.JsonSkatSignatureContainer;
import no.systema.skat.model.jsonjackson.avdsignature.JsonSkatSignatureRecord;
import no.systema.skat.url.store.SkatUrlDataStore;
import no.systema.skat.util.SkatConstants;

//Export
import no.systema.skat.skatexport.model.jsonjackson.topic.JsonSkatExportTopicListContainer;
import no.systema.skat.skatexport.model.jsonjackson.topic.JsonSkatExportTopicListRecord;

import no.systema.skat.skatexport.service.SkatExportTopicListService;
import no.systema.skat.skatexport.filter.SearchFilterSkatExportTopicList;
import no.systema.skat.skatexport.validator.SkatExportListValidator;
import no.systema.skat.skatexport.url.store.SkatExportUrlDataStore;
import no.systema.skat.skatexport.util.manager.CodeDropDownMgr;
import no.systema.skat.skatexport.util.RpgReturnResponseHandler;


/**
 * SKAT Export Topic Controller 
 * 
 * @author oscardelatorre
 * @date Feb 24, 2014
 * 
 */

@Controller
@SessionAttributes(AppConstants.SYSTEMA_WEB_USER_KEY)
@Scope("session")
public class SkatExportController {
	private static final JsonDebugger jsonDebugger = new JsonDebugger();
	private static final Logger logger = Logger.getLogger(SkatExportController.class.getName());
	private ModelAndView loginView = new ModelAndView("login");
	private ApplicationContext context;
	private LoginValidator loginValidator = new LoginValidator();
	
	private CodeDropDownMgr codeDropDownMgr = new CodeDropDownMgr();
	
	
	@PostConstruct
	public void initIt() throws Exception {
		if("DEBUG".equals(AppConstants.LOG4J_LOGGER_LEVEL)){
			logger.setLevel(Level.DEBUG);
		}
	}	
	
	/**
	 * 
	 * @param user
	 * @param result
	 * @param request
	 * @return
	 */
	/*
	@RequestMapping(value="skatexport.do", method=RequestMethod.GET)
	public ModelAndView doSkatImportList(HttpSession session, HttpServletRequest request){
		ModelAndView successView = new ModelAndView("skatexport");
		SystemaWebUser appUser = (SystemaWebUser)session.getAttribute(AppConstants.SYSTEMA_WEB_USER_KEY);
		SearchFilterSkatExportTopicList searchFilter = new SearchFilterSkatExportTopicList();
		
		Map model = new HashMap();
		if(appUser==null){
			return this.loginView;
		}else{
			appUser.setActiveMenu(SystemaWebUser.ACTIVE_MENU_SKAT_EXPORT);
			session.setAttribute(SkatConstants.ACTIVE_URL_RPG_SKAT, SkatConstants.ACTIVE_URL_RPG_INITVALUE); 
		
			//lists
			this.populateAvdelningHtmlDropDownsFromJsonString(model, appUser, session);
			this.populateSignatureHtmlDropDownsFromJsonString(model, appUser);
			this.setCodeDropDownMgr(appUser, model);
			//init filter with users signature (for starters)
			searchFilter.setSign(appUser.getSkatSign());
			successView.addObject("searchFilter" , searchFilter);
			//init the rest
			successView.addObject(SkatConstants.DOMAIN_MODEL , model);
			successView.addObject(SkatConstants.DOMAIN_LIST,new ArrayList());
			
	    	return successView;
		}
	}
	*/
	/**
	 * 
	 * @param session
	 * @param request
	 * @return
	 */
	@RequestMapping(value="skatexport", params="action=doFind",  method={RequestMethod.GET, RequestMethod.POST} )
	public ModelAndView doFind(@ModelAttribute ("record") SearchFilterSkatExportTopicList recordToValidate, BindingResult bindingResult, HttpSession session, HttpServletRequest request){
		this.context = TdsAppContext.getApplicationContext();
		Collection outputList = new ArrayList();
		Map model = new HashMap();
		//String messageFromContext = this.context.getMessage("user.label",new Object[0], request.getLocale());
		
		ModelAndView successView = new ModelAndView("skatexport");
		SystemaWebUser appUser = this.loginValidator.getValidUser(session);
		
		//check user (should be in session already)
		if(appUser==null){
			return loginView;
			
		}else{
			appUser.setActiveMenu(SystemaWebUser.ACTIVE_MENU_SKAT_EXPORT);
			session.setAttribute(SkatConstants.ACTIVE_URL_RPG_SKAT, SkatConstants.ACTIVE_URL_RPG_INITVALUE); 
		
			logger.info(Calendar.getInstance().getTime() + " CONTROLLER start - timestamp");
			
			//-----------
			//Validation
			//-----------
			SkatExportListValidator validator = new SkatExportListValidator();
			logger.info("Host via HttpServletRequest.getHeader('Host'): " + request.getHeader("Host"));
		    validator.validate(recordToValidate, bindingResult);
		    
		    //check for ERRORS
			if(bindingResult.hasErrors()){
	    		logger.info("[ERROR Validation] search-filter does not validate)");
	    		//put domain objects and do go back to the successView from here
	    		//drop downs
				this.populateAvdelningHtmlDropDownsFromJsonString(model, appUser, session);
				this.populateSignatureHtmlDropDownsFromJsonString(model, appUser);
				this.setCodeDropDownMgr(appUser, model);

				successView.addObject(SkatConstants.DOMAIN_MODEL, model);
		    	
				successView.addObject(SkatConstants.DOMAIN_LIST, new ArrayList());
				successView.addObject("searchFilter", recordToValidate);
				return successView;
	    		
		    }else{
				//----------------------------------------------
				//get Search Filter and populate (bind) it here
				//----------------------------------------------
		    	SearchFilterSkatExportTopicList searchFilter = new SearchFilterSkatExportTopicList();
				ServletRequestDataBinder binder = new ServletRequestDataBinder(searchFilter);
	            //binder.registerCustomEditor(...); // if needed
	            binder.bind(request);
	            
	            //Put in session for further use (within this module) ONLY with: POST method = doFind on search fields
	            if(request.getMethod().equalsIgnoreCase(RequestMethod.POST.toString())){
	            	session.setAttribute(SkatConstants.SESSION_SEARCH_FILTER_SKATEXPORT, searchFilter);
	            }else{
	            	SearchFilterSkatExportTopicList sessionFilter = (SearchFilterSkatExportTopicList)session.getAttribute(SkatConstants.SESSION_SEARCH_FILTER_SKATEXPORT);
	            	if(sessionFilter!=null){
	            		//Use the session filter when applicable
	            		searchFilter = sessionFilter;
	            	}
	            }
	            
	            //get BASE URL
	    		final String BASE_URL = SkatExportUrlDataStore.SKAT_EXPORT_BASE_TOPICLIST_URL;
	    		//add URL-parameters
	    		String urlRequestParams = this.getRequestUrlKeyParameters(searchFilter, appUser);
	    		session.setAttribute(SkatConstants.ACTIVE_URL_RPG_SKAT, BASE_URL + "==>params: " + urlRequestParams.toString()); 
		    	logger.info(Calendar.getInstance().getTime() + " CGI-start timestamp");
		    	logger.info("URL: " + BASE_URL);
		    	logger.info("URL PARAMS: " + urlRequestParams);
		    	
		    	String jsonPayload = this.urlCgiProxyService.getJsonContent(BASE_URL, urlRequestParams);

				//Debug --> 
		    	logger.debug(jsonDebugger.debugJsonPayloadWithLog4J(jsonPayload));
			    	logger.info(Calendar.getInstance().getTime() +  " CGI-end timestamp");
			    	if(jsonPayload!=null){
			    		
			    		JsonSkatExportTopicListContainer jsonSkatExportTopicListContainer = this.skatExportTopicListService.getSkatExportTopicListContainer(jsonPayload);
			    		//-----------------------------------------------------------
					//now filter the topic list with the search filter (if applicable)
					//-----------------------------------------------------------
					outputList = jsonSkatExportTopicListContainer.getOrderList();	
					//Remove all "D" status rows. These are to be shown if-and-only-if the user demand it explicitly in the search filter
					if(!"D".equals(searchFilter.getStatus())){
						//To avoid the ...ConcurrentModificationException we take a copy of the existing list and iterate over new copy
						for (JsonSkatExportTopicListRecord record : new ArrayList<JsonSkatExportTopicListRecord>(outputList)){
							if("D".equals(record.getStatus())){
								outputList.remove(record);
							}
						}
					}
					//--------------------------------------
					//Final successView with domain objects
					//--------------------------------------
					//drop downs
					this.populateAvdelningHtmlDropDownsFromJsonString(model, appUser, session);
					this.populateSignatureHtmlDropDownsFromJsonString(model, appUser);
					this.setCodeDropDownMgr(appUser, model);

					successView.addObject(SkatConstants.DOMAIN_MODEL , model);
			    		//domain and search filter
					successView.addObject(SkatConstants.DOMAIN_LIST,outputList);
					
					if (session.getAttribute(SkatConstants.SESSION_SEARCH_FILTER_SKATEXPORT) == null || session.getAttribute(SkatConstants.SESSION_SEARCH_FILTER_SKATEXPORT).equals("")){
						successView.addObject(SkatConstants.DOMAIN_SEARCH_FILTER_SKATEXPORT, searchFilter);
					}
					logger.info(Calendar.getInstance().getTime() + " CONTROLLER end - timestamp");
			    	return successView;
					
			    	}else{
					logger.fatal("NO CONTENT on jsonPayload from URL... ??? <Null>");
					return loginView;
				}
		    }
		}
		
	}

	@RequestMapping(value="skatexport.do", params="action=doDelete",  method={RequestMethod.GET} )
	public ModelAndView doDelete(HttpSession session, HttpServletRequest request){
		this.context = TdsAppContext.getApplicationContext();
		RpgReturnResponseHandler rpgReturnResponseHandler = new RpgReturnResponseHandler();
		
		Collection outputList = new ArrayList();
		Map model = new HashMap();
		logger.info("Inside doDelete");
		SystemaWebUser appUser = this.loginValidator.getValidUser(session);
		ModelAndView successView = new ModelAndView("redirect:skatexport.do?action=doFind&sign=" + appUser.getSkatSign());
		
		String opd = request.getParameter("opd");
		String avd = request.getParameter("avd");

		
		//check user (should be in session already)
		if(appUser==null){
			return loginView;
			
		}else{
			logger.info(Calendar.getInstance().getTime() + " CONTROLLER start - timestamp");
			//---------------------------
			//get BASE URL = RPG-PROGRAM
            //---------------------------
			String BASE_URL = SkatExportUrlDataStore.SKAT_EXPORT_BASE_UPDATE_SPECIFIC_TOPIC_URL;
			
			//-----------------------------------
			//add URL-parameter "mode=D" (Delete)
			//-----------------------------------
			String urlRequestParams = this.getRequestUrlKeyParametersDoDelete(avd, opd, appUser);
			//for debug purposes in GUI
			session.setAttribute(SkatConstants.ACTIVE_URL_RPG_SKAT, BASE_URL); 
	    	
			logger.info(Calendar.getInstance().getTime() + " CGI-start timestamp");
		    	logger.info("URL: " + BASE_URL);
		    	logger.info("URL PARAMS: " + urlRequestParams);
		    	//--------------------------------------
		    	//EXECUTE the DELETE (RPG program) here 
		    	//--------------------------------------
		    	String rpgReturnPayload = this.urlCgiProxyService.getJsonContent(BASE_URL, urlRequestParams);
		    	//Debug --> 
		    	logger.info("Checking errMsg in rpgReturnPayload" + rpgReturnPayload);
		    	//we must evaluate a return RPG code in order to know if the Update was OK or not
		    	rpgReturnResponseHandler.evaluateRpgResponseOnTopicUpdate(rpgReturnPayload);
		    	if(rpgReturnResponseHandler.getErrorMessage()!=null && !"".equals(rpgReturnResponseHandler.getErrorMessage())){
		    		rpgReturnResponseHandler.setErrorMessage("[ERROR] FATAL on DELETE: " + rpgReturnResponseHandler.getErrorMessage());
		    		this.setFatalError(model, rpgReturnResponseHandler);
		    	}else{
		    		//Update succefully done!
		    		logger.info("[INFO] Record successfully updated, OK ");
		    		//put domain objects
		    		//this.setDomainObjectsInView(session, model, jsonSkatImportSpecificTopicRecord, sumOfAntalKolliInItemLines, sumOfAntalItemLines );
		    	}
			
			//init filter 
			SearchFilterSkatExportTopicList searchFilter = new SearchFilterSkatExportTopicList();
			//lists
			this.populateAvdelningHtmlDropDownsFromJsonString(model, appUser, session);
			this.populateSignatureHtmlDropDownsFromJsonString(model, appUser);
			this.setCodeDropDownMgr(appUser, model);
			
			//init filter with users signature (for starters)
			searchFilter.setSign(appUser.getSkatSign());
			successView.addObject("searchFilter" , searchFilter);
			//init the rest
			successView.addObject(SkatConstants.DOMAIN_MODEL , model);
			successView.addObject(SkatConstants.DOMAIN_LIST,new ArrayList());
			
			return successView;
			
		}
		
	}
	
	/**
	 * 
	 * @param model
	 * @param rpgReturnResponseHandler
	 * @param record
	 */
	private void setFatalError(Map model, RpgReturnResponseHandler rpgReturnResponseHandler){
		logger.info(rpgReturnResponseHandler.getErrorMessage());
		this.setAspectsInView(model, rpgReturnResponseHandler);
		//No refresh on jsonRecord is done for the GUI (form fields). Must be implemented right here, if required. !!
        //this.setDomainObjectsInView(model, record);
	}
	
	/**
	 * 
	 * @param model
	 * @param rpgReturnResponseHandler
	 */
	private void setAspectsInView (Map model, RpgReturnResponseHandler rpgReturnResponseHandler){
		model.put(SkatConstants.ASPECT_ERROR_MESSAGE, rpgReturnResponseHandler.getErrorMessage());
		//extra error information
		StringBuffer errorMetaInformation = new StringBuffer();
		errorMetaInformation.append(rpgReturnResponseHandler.getUser());
		errorMetaInformation.append(rpgReturnResponseHandler.getDkeh_syop());
		model.put(SkatConstants.ASPECT_ERROR_META_INFO, errorMetaInformation);
		
	}
	/**
	 * 
	 * @param model
	 * @param appUser
	 */
	private void populateAvdelningHtmlDropDownsFromJsonString(Map model, SystemaWebUser appUser, HttpSession session){
		//fill in html lists here
		String SKAT_IE = "E"; 
		try{
			String BASE_URL = SkatUrlDataStore.SKAT_FETCH_AVDELNINGAR_URL;
			StringBuffer urlRequestParamsKeys = new StringBuffer();
			urlRequestParamsKeys.append("user=" + appUser.getUser());
			urlRequestParamsKeys.append(SkatConstants.URL_CHAR_DELIMETER_FOR_PARAMS_WITH_HTML_REQUEST + "ie=" + SKAT_IE);
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
			session.setAttribute(SkatConstants.RESOURCE_MODEL_KEY_AVD_LIST_SESSION_TEST_FLAG, list);
			
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
		String SKAT_EXPORT_IMPORT_IE = "F"; //Fortullning. The other one is: NCTS = ie=N 
		
		try{
			String BASE_URL = SkatUrlDataStore.SKAT_FETCH_SIGNATURE_URL;
			StringBuffer urlRequestParamsKeys = new StringBuffer();
			urlRequestParamsKeys.append("user=" + appUser.getUser());
			urlRequestParamsKeys.append(SkatConstants.URL_CHAR_DELIMETER_FOR_PARAMS_WITH_HTML_REQUEST + "ie=" + SKAT_EXPORT_IMPORT_IE);
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
	 * @param searchFilter
	 * @param appUser
	 * @return
	 */
	private String getRequestUrlKeyParameters(SearchFilterSkatExportTopicList searchFilter, SystemaWebUser appUser){
		StringBuffer urlRequestParamsKeys = new StringBuffer();
		//String action = request.getParameter("action");
		
		urlRequestParamsKeys.append("user=" + appUser.getUser());
		if(searchFilter.getAvd()!=null && !"".equals(searchFilter.getAvd())){
			urlRequestParamsKeys.append(SkatConstants.URL_CHAR_DELIMETER_FOR_PARAMS_WITH_HTML_REQUEST + "avd=" + searchFilter.getAvd());
		}
		if(searchFilter.getOpd()!=null && !"".equals(searchFilter.getOpd())){
			urlRequestParamsKeys.append(SkatConstants.URL_CHAR_DELIMETER_FOR_PARAMS_WITH_HTML_REQUEST + "opd=" + searchFilter.getOpd());
		}
		if(searchFilter.getSign()!=null && !"".equals(searchFilter.getSign())){
			urlRequestParamsKeys.append(SkatConstants.URL_CHAR_DELIMETER_FOR_PARAMS_WITH_HTML_REQUEST + "sign=" + searchFilter.getSign());
		}
		if(searchFilter.getRefnr()!=null && !"".equals(searchFilter.getRefnr())){
			urlRequestParamsKeys.append(SkatConstants.URL_CHAR_DELIMETER_FOR_PARAMS_WITH_HTML_REQUEST + "refnr=" + searchFilter.getRefnr());
		}
		if(searchFilter.getXrefnr()!=null && !"".equals(searchFilter.getXrefnr())){
			urlRequestParamsKeys.append(SkatConstants.URL_CHAR_DELIMETER_FOR_PARAMS_WITH_HTML_REQUEST + "xref=" + searchFilter.getXrefnr());
		}
		if(searchFilter.getDatum()!=null && !"".equals(searchFilter.getDatum())){
			urlRequestParamsKeys.append(SkatConstants.URL_CHAR_DELIMETER_FOR_PARAMS_WITH_HTML_REQUEST + "datum=" + searchFilter.getDatum());
		}
		if(searchFilter.getStatus()!=null && !"".equals(searchFilter.getStatus())){
			urlRequestParamsKeys.append(SkatConstants.URL_CHAR_DELIMETER_FOR_PARAMS_WITH_HTML_REQUEST + "status=" + searchFilter.getStatus());
		}
		if(searchFilter.getAvsNavn()!=null && !"".equals(searchFilter.getAvsNavn())){
			urlRequestParamsKeys.append(SkatConstants.URL_CHAR_DELIMETER_FOR_PARAMS_WITH_HTML_REQUEST + "avsNavn=" + searchFilter.getAvsNavn());
		}
		if(searchFilter.getMotNavn()!=null && !"".equals(searchFilter.getMotNavn())){
			urlRequestParamsKeys.append(SkatConstants.URL_CHAR_DELIMETER_FOR_PARAMS_WITH_HTML_REQUEST + "motNavn=" + searchFilter.getMotNavn());
		}
		if(searchFilter.getInternFakturanr()!=null && !"".equals(searchFilter.getInternFakturanr())){
			urlRequestParamsKeys.append(SkatConstants.URL_CHAR_DELIMETER_FOR_PARAMS_WITH_HTML_REQUEST + "fakt=" + searchFilter.getInternFakturanr());
		}
		if(searchFilter.getAart()!=null && !"".equals(searchFilter.getAart())){
			urlRequestParamsKeys.append(SkatConstants.URL_CHAR_DELIMETER_FOR_PARAMS_WITH_HTML_REQUEST + "aart=" + searchFilter.getAart());
		}
		
		return urlRequestParamsKeys.toString();
	}
	
	/**
	 * 
	 * @param avd
	 * @param opd
	 * @param appUser
	 * @return
	 */
	private String getRequestUrlKeyParametersDoDelete(String avd, String opd, SystemaWebUser appUser){
		final String MODE_DELETE = "D";
		StringBuffer urlRequestParamsKeys = new StringBuffer();
		//String action = request.getParameter("action");
		urlRequestParamsKeys.append("user=" + appUser.getUser());
		urlRequestParamsKeys.append(SkatConstants.URL_CHAR_DELIMETER_FOR_PARAMS_WITH_HTML_REQUEST + "avd=" + avd);
		urlRequestParamsKeys.append(SkatConstants.URL_CHAR_DELIMETER_FOR_PARAMS_WITH_HTML_REQUEST + "opd=" + opd);
		urlRequestParamsKeys.append(SkatConstants.URL_CHAR_DELIMETER_FOR_PARAMS_WITH_HTML_REQUEST + "mode=" + MODE_DELETE);
		
		return urlRequestParamsKeys.toString();
	}
	
	/**
	 * 
	 * @param appUser
	 * @param model
	 */
	private void setCodeDropDownMgr(SystemaWebUser appUser, Map model){
		this.codeDropDownMgr.populateCodesHtmlDropDownsFromJsonString(this.urlCgiProxyService, this.skatDropDownListPopulationService,
				 model,appUser,CodeDropDownMgr.CODE_102_ANGIVELSESARTER, null, null);
		this.codeDropDownMgr.populateCodesHtmlDropDownsFromJsonString(this.urlCgiProxyService, this.skatDropDownListPopulationService,
				 model,appUser,CodeDropDownMgr.CODE_127_STATUS_KODER, null, null);
		
	}

	
	//SERVICES
	
	@Qualifier ("urlCgiProxyService")
	private UrlCgiProxyService urlCgiProxyService;
	@Autowired
	@Required
	public void setUrlCgiProxyService (UrlCgiProxyService value){ this.urlCgiProxyService = value; }
	public UrlCgiProxyService getUrlCgiProxyService(){ return this.urlCgiProxyService; }
	
	
	@Qualifier ("skatExportTopicListService")
	private SkatExportTopicListService skatExportTopicListService;
	@Autowired
	@Required
	public void setSkatExportTopicListService (SkatExportTopicListService value){ this.skatExportTopicListService = value; }
	public SkatExportTopicListService getSkatImportTopicListService(){ return this.skatExportTopicListService; }
	
	
	@Qualifier ("skatDropDownListPopulationService")
	private SkatDropDownListPopulationService skatDropDownListPopulationService;
	@Autowired
	public void setSkatDropDownListPopulationService (SkatDropDownListPopulationService value){ this.skatDropDownListPopulationService=value; }
	public SkatDropDownListPopulationService getSkatDropDownListPopulationService(){return this.skatDropDownListPopulationService;}
	
	
}

