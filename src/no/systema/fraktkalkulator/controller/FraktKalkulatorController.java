package no.systema.fraktkalkulator.controller;

import java.util.*;

import org.apache.log4j.Logger;
import org.springframework.validation.BindingResult;
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
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import org.springframework.web.bind.ServletRequestDataBinder;

//application imports
import no.systema.main.context.TdsAppContext;
import no.systema.main.service.UrlCgiProxyService;
import no.systema.main.validator.LoginValidator;
import no.systema.main.util.AppConstants;
import no.systema.main.util.DateTimeManager;
import no.systema.main.util.JsonDebugger;
import no.systema.main.model.SystemaWebUser;

//F.kalkulator
import no.systema.fraktkalkulator.model.jsonjackson.JsonFraktKalkulatorUserContainer;
import no.systema.fraktkalkulator.model.jsonjackson.JsonFraktKalkulatorResultContainer;
import no.systema.fraktkalkulator.model.jsonjackson.dropdownlist.JsonFraktKalkulatorDropDownContainer;
import no.systema.fraktkalkulator.model.jsonjackson.dropdownlist.JsonFraktKalkulatorProductRecord;
import no.systema.fraktkalkulator.model.jsonjackson.dropdownlist.JsonFraktKalkulatorOppdragTypeRecord;
import no.systema.fraktkalkulator.model.jsonjackson.dropdownlist.JsonFraktKalkulatorTjenesteTypeRecord;
import no.systema.fraktkalkulator.model.jsonjackson.dropdownlist.JsonFraktKalkulatorIncotermsRecord;
import no.systema.fraktkalkulator.service.FraktKalkulatorChildWindowsService;
import no.systema.fraktkalkulator.service.FraktKalkulatorUserService;
import no.systema.fraktkalkulator.service.FraktKalkulatorResultService;
import no.systema.fraktkalkulator.service.FraktKalkulatorDropDownService;
import no.systema.fraktkalkulator.util.FraktKalkulatorConstants;
import no.systema.fraktkalkulator.validator.FraktkalkulatorValidator;
import no.systema.fraktkalkulator.url.store.FraktKalkulatorUrlDataStore;



/**
 * Priskalkulator Controller 
 * 
 * @author oscardelatorre
 * @date Feb 20, 2015
 * 
 */

@Controller
@SessionAttributes(AppConstants.SYSTEMA_WEB_USER_KEY)
@Scope("session")
public class FraktKalkulatorController {
	
	private static final Logger logger = Logger.getLogger(FraktKalkulatorController.class.getName());
	private static final JsonDebugger jsonDebugger = new JsonDebugger(1000);
	private final String FRAKTKALK_USER_STR = "user";
	private final String FRAKTKALK_DROP_DOWN_PROD = "prodList";
	private final String FRAKTKALK_DROP_DOWN_OPPDRAG = "oppdragList";
	private final String FRAKTKALK_DROP_DOWN_TJENESTE = "tjenesteList";
	private final String FRAKTKALK_DROP_DOWN_INCOTERMS = "incotermsList";
	
	private ModelAndView loginView = new ModelAndView("login");
	private ApplicationContext context;
	private LoginValidator loginValidator = new LoginValidator();
	//private CodeDropDownMgr codeDropDownMgr = new CodeDropDownMgr();
	private DateTimeManager dateTimeMgr = new DateTimeManager();
	
	/**
	 * 
	 * @param session
	 * @param request
	 * @return
	 */
	@RequestMapping(value="fraktkalkulator", params="action=doInit",  method={RequestMethod.GET} )
	public ModelAndView doInit(HttpSession session, HttpServletRequest request){
		this.context = TdsAppContext.getApplicationContext();
		logger.info("Inside: doInit");
		Collection outputList = new ArrayList();
		Map model = new HashMap();
		
		ModelAndView successView = new ModelAndView("fraktkalkulator");
		SystemaWebUser appUser = this.loginValidator.getValidUser(session);

		//check user (should be in session already)
		if(appUser==null){
			return this.loginView;
			
		}else{
			appUser.setActiveMenu(SystemaWebUser.ACTIVE_MENU_FRAKTKALKULATOR);
			logger.info(Calendar.getInstance().getTime() + " CONTROLLER start - timestamp");
			//Get default data
			JsonFraktKalkulatorUserContainer userContainer = this.getUserData(model, appUser.getUser());
	    	//Get drop down lists
			this.getDropDownLists(model, appUser.getUser(), userContainer);
		    	
    		successView.addObject(FraktKalkulatorConstants.DOMAIN_MODEL , model);
    		return successView;
		}
	}	
	
	/**
	 * 
	 * @param session
	 * @param request
	 * @return
	 */
	@RequestMapping(value="fraktkalkulator", params="action=doFind",  method={RequestMethod.GET, RequestMethod.POST} )
	public ModelAndView doFind(@ModelAttribute ("record") JsonFraktKalkulatorUserContainer recordToValidate, BindingResult bindingResult, HttpSession session, HttpServletRequest request){
		this.context = TdsAppContext.getApplicationContext();
		logger.info("Inside: doFind");
		Collection outputList = new ArrayList();
		Map model = new HashMap();
		
		ModelAndView successView = new ModelAndView("fraktkalkulator");
		SystemaWebUser appUser = this.loginValidator.getValidUser(session);
		this.populateFarligGods(request, recordToValidate);
		logger.info("FARLIG GODS:" + recordToValidate.getFarlig());
		//check user (should be in session already)
		if(appUser==null){
			return loginView;
			
		}else{
			appUser.setActiveMenu(SystemaWebUser.ACTIVE_MENU_FRAKTKALKULATOR);
			logger.info(Calendar.getInstance().getTime() + " CONTROLLER start - timestamp");
			
			//-----------
			//Validation
			//-----------
			FraktkalkulatorValidator validator = new FraktkalkulatorValidator();
			logger.info("Host via HttpServletRequest.getHeader('Host'): " + request.getHeader("Host"));
		    validator.validate(recordToValidate, bindingResult);
		    
		    //check for ERRORS
			if(bindingResult.hasErrors()){
				logger.info("[ERROR Validation] search-filter does not validate)");
				//set model
	    		model.put(this.FRAKTKALK_USER_STR, recordToValidate);
	    		this.getDropDownLists(model, appUser.getUser(), recordToValidate);
		    	successView.addObject(FraktKalkulatorConstants.DOMAIN_MODEL , model);
				return successView;
	    		
		    }else{
		    	//Escape some special characters...
			    if("%".equals(recordToValidate.getProd()) ){
			    	//escape it for URL -encode
			    	recordToValidate.setProd("%25");
			    }
	            //get BASE URL
	    		final String BASE_URL = FraktKalkulatorUrlDataStore.FRAKTKALKULATOR_FETCH_RESULT_DATA_URL;
	    		//add URL-parameters
	    		String urlRequestParams = this.getRequestUrlKeyParameters(recordToValidate, appUser);
	    		session.setAttribute(FraktKalkulatorConstants.ACTIVE_URL_RPG_FRAKT_KALKULATOR, BASE_URL + "==>params: " + urlRequestParams.toString()); 
		    	logger.info(Calendar.getInstance().getTime() + " CGI-start timestamp");
		    	logger.info("URL: " + BASE_URL);
		    	logger.info("URL PARAMS: " + urlRequestParams);
		    	
		    	String jsonPayload = this.urlCgiProxyService.getJsonContent(BASE_URL, urlRequestParams);
				//Debug -->
		    	jsonDebugger.debugJsonPayload(jsonPayload);
		    	if(jsonPayload!=null && jsonPayload.length()>500){ logger.info(jsonPayload.substring(0,500));}
		    	else{logger.info(jsonPayload);}
			    	
		    	logger.info(Calendar.getInstance().getTime() +  " CGI-end timestamp");
		    	if(jsonPayload!=null){
		    		JsonFraktKalkulatorResultContainer jsonFraktKalkulatorResultContainer = this.fraktKalkulatorResultService.getContainer(jsonPayload);
		    		//Set back special characters
		    		if("%25".equals(recordToValidate.getProd())){ recordToValidate.setProd("%"); }
		    		
		    		//--------------------------------------
		    		//Final successView with domain objects
		    		//--------------------------------------
		    		model.put(this.FRAKTKALK_USER_STR, recordToValidate);
		    		//Get drop down lists
			    	this.getDropDownLists(model, appUser.getUser(), recordToValidate);
			    	//Put domain objects
		    		this.setDomainObjectsInView(session, model, jsonFraktKalkulatorResultContainer);
		    		successView.addObject(FraktKalkulatorConstants.DOMAIN_MODEL , model);
		    		//domain and search filter
		    		//successView.addObject("searchFilter", searchFilter);
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
	 * @param request
	 * @param recordToValidate
	 */
	private void populateFarligGods(HttpServletRequest request, JsonFraktKalkulatorUserContainer recordToValidate){
		String CHECKED = "1";
		String farlig = request.getParameter("farlig");
		String varme = request.getParameter("varme");
		String lengde = request.getParameter("lengde");
		
		if(CHECKED.equals(farlig)){	recordToValidate.setFarlig("J"); }
		if(CHECKED.equals(varme)){	recordToValidate.setVarme("J"); }
		if(CHECKED.equals(lengde)){	recordToValidate.setLengde("J"); }
	}
	
	
	/**
	 * 
	 * @param model
	 * @param applicationUser
	 * @return
	 */
	private JsonFraktKalkulatorUserContainer getUserData(Map model, String applicationUser) {
		logger.info("Inside getUserData");
		List list = new ArrayList();
		//prepare the access CGI with RPG back-end
		String BASE_URL = FraktKalkulatorUrlDataStore.FRAKTKALKULATOR_FETCH_USER_DATA_URL;
		String urlRequestParamsKeys = "user=" + applicationUser;
		logger.info("URL: " + BASE_URL);
		logger.info("PARAMS: " + urlRequestParamsKeys);
		logger.info(Calendar.getInstance().getTime() +  " CGI-start timestamp");
		
		String jsonPayload = this.urlCgiProxyService.getJsonContent(BASE_URL, urlRequestParamsKeys);
		logger.info(jsonPayload);
		logger.info(Calendar.getInstance().getTime() +  " CGI-end timestamp");
		JsonFraktKalkulatorUserContainer container = null;
		if(jsonPayload!=null){
	    		container = this.fraktKalkulatorUserService.getContainer(jsonPayload);
	    		if(container!=null){
	    			model.put(this.FRAKTKALK_USER_STR, container);
	    		}	
	    	}
	    	
		return container;
	}
	
	/**
	 * 
	 * @param model
	 * @param applicationUser
	 * @param userContainer
	 */
	private void getProductList(Map model, String applicationUser, JsonFraktKalkulatorUserContainer userContainer) {
		  logger.info("Inside getProductList");
		  List list = new ArrayList();
		  //prepare the access CGI with RPG back-end
		  String BASE_URL = FraktKalkulatorUrlDataStore.FRAKTKALKULATOR_FETCH_DROPDOWN_PRODUCT_DATA_URL;
		  StringBuffer sb = new StringBuffer();
		  sb.append("user=" + applicationUser);
		  sb.append("&wsavd=" + userContainer.getWsavd());
		  String urlRequestParamsKeys = sb.toString();
		  
		  logger.info("URL: " + BASE_URL);
		  logger.info("PARAMS: " + urlRequestParamsKeys);
		  logger.info(Calendar.getInstance().getTime() +  " CGI-start timestamp");
		  
		  String jsonPayload = this.urlCgiProxyService.getJsonContent(BASE_URL, urlRequestParamsKeys);

		  logger.info (jsonPayload);
		  logger.info(Calendar.getInstance().getTime() +  " CGI-end timestamp");
	    	if(jsonPayload!=null){
	    		jsonPayload = jsonPayload.replaceFirst("Customerlist", "customerlist");
	    		JsonFraktKalkulatorDropDownContainer container = this.fraktKalkulatorDropDownService.getContainer(jsonPayload);
	    		if(container!=null){
	    			for(JsonFraktKalkulatorProductRecord  record : container.getPricecalclist_prod()){
	    				list.add(record);
	    			}
	    			model.put(this.FRAKTKALK_DROP_DOWN_PROD, list);
	    		}
	    	}
		  
	}
	
	/**
	 * 
	 * @param model
	 * @param applicationUser
	 * @param userContainer
	 */
	private void getOppdragTypeList(Map model, String applicationUser, JsonFraktKalkulatorUserContainer userContainer) {
		  //logger.info("Inside getOppdragTypeList");
		  List list = new ArrayList();
		  //prepare the access CGI with RPG back-end
		  String BASE_URL = FraktKalkulatorUrlDataStore.FRAKTKALKULATOR_FETCH_DROPDOWN_OPPDRAG_TYPE_DATA_URL;
		  StringBuffer sb = new StringBuffer();
		  sb.append("user=" + applicationUser);
		  sb.append("&wsavd=" + userContainer.getWsavd());
		  String urlRequestParamsKeys = sb.toString();
		  /*logger.info("URL: " + BASE_URL);
		  logger.info("PARAMS: " + urlRequestParamsKeys);
		  logger.info(Calendar.getInstance().getTime() +  " CGI-start timestamp");
		  */
		  String jsonPayload = this.urlCgiProxyService.getJsonContent(BASE_URL, urlRequestParamsKeys);

		  //logger.info (jsonPayload);
		  //logger.info(Calendar.getInstance().getTime() +  " CGI-end timestamp");
	    	if(jsonPayload!=null){
	    		JsonFraktKalkulatorDropDownContainer container = this.fraktKalkulatorDropDownService.getContainer(jsonPayload);
	    		if(container!=null){
	    			for(JsonFraktKalkulatorOppdragTypeRecord  record : container.getPricecalclist_ot()){
	    				list.add(record);
	    			}
	    			model.put(this.FRAKTKALK_DROP_DOWN_OPPDRAG, list);
	    		}
	    	}
		  
	}
	
	/**
	 * 
	 * @param model
	 * @param applicationUser
	 * @param userContainer
	 */
	private void getTjenesteTypeList(Map model, String applicationUser, JsonFraktKalkulatorUserContainer userContainer) {
		  //logger.info("Inside getTjenesteTypeList");
		  List list = new ArrayList();
		  //prepare the access CGI with RPG back-end
		  String BASE_URL = FraktKalkulatorUrlDataStore.FRAKTKALKULATOR_FETCH_DROPDOWN_TJENESTE_TYPE_DATA_URL;
		  StringBuffer sb = new StringBuffer();
		  sb.append("user=" + applicationUser);
		  sb.append("&wsavd=" + userContainer.getWsavd());
		  String urlRequestParamsKeys = sb.toString();
		  /*logger.info("URL: " + BASE_URL);
		  logger.info("PARAMS: " + urlRequestParamsKeys);
		  logger.info(Calendar.getInstance().getTime() +  " CGI-start timestamp");
		  */
		  String jsonPayload = this.urlCgiProxyService.getJsonContent(BASE_URL, urlRequestParamsKeys);
		  
		  //logger.info (jsonPayload);
		  //logger.info(Calendar.getInstance().getTime() +  " CGI-end timestamp");
	    	if(jsonPayload!=null){
	    		JsonFraktKalkulatorDropDownContainer container = this.fraktKalkulatorDropDownService.getContainer(jsonPayload);
	    		if(container!=null){
	    			for(JsonFraktKalkulatorTjenesteTypeRecord  record : container.getPricecalclist_tjen()){
	    				list.add(record);
	    			}
	    			model.put(this.FRAKTKALK_DROP_DOWN_TJENESTE, list);
	    		}
	    	}
		  
	}
	
	/**
	 * 
	 * @param model
	 * @param applicationUser
	 * @param userContainer
	 */
	private void getIncotermsList(Map model, String applicationUser, JsonFraktKalkulatorUserContainer userContainer) {
		  //logger.info("Inside getIncotermsList");
		  List list = new ArrayList();
		  //prepare the access CGI with RPG back-end
		  String BASE_URL = FraktKalkulatorUrlDataStore.FRAKTKALKULATOR_FETCH_DROPDOWN_INCOTERMS_DATA_URL;
		  StringBuffer sb = new StringBuffer();
		  sb.append("user=" + applicationUser);
		  sb.append("&wsavd=" + userContainer.getWsavd());
		  String urlRequestParamsKeys = sb.toString();
		  /*logger.info("URL: " + BASE_URL);
		  logger.info("PARAMS: " + urlRequestParamsKeys);
		  logger.info(Calendar.getInstance().getTime() +  " CGI-start timestamp");
		  */
		  String jsonPayload = this.urlCgiProxyService.getJsonContent(BASE_URL, urlRequestParamsKeys);

		  //logger.info (jsonPayload);
		  //logger.info(Calendar.getInstance().getTime() +  " CGI-end timestamp");
	    	if(jsonPayload!=null){
	    		JsonFraktKalkulatorDropDownContainer container = this.fraktKalkulatorDropDownService.getContainer(jsonPayload);
	    		if(container!=null){
	    			for(JsonFraktKalkulatorIncotermsRecord  record : container.getPricecalclist_levv()){
	    				list.add(record);
	    			}
	    			model.put(this.FRAKTKALK_DROP_DOWN_INCOTERMS, list);
	    		}
	    	}
		  
	}
	
	/**
	 * 
	 * @param model
	 * @param applicationUser
	 * @param userContainer
	 */
	private void getDropDownLists(Map model, String applicationUser, JsonFraktKalkulatorUserContainer userContainer){
		if(userContainer!=null){
			this.getProductList(model, applicationUser, userContainer);
			this.getTjenesteTypeList(model, applicationUser, userContainer);
			this.getIncotermsList(model, applicationUser, userContainer);
			this.getOppdragTypeList(model, applicationUser, userContainer);
		}else{
			logger.info("ERROR on <getDropDownList>. userContainer param = null ???; corrupt AS400 payload...");
		}
	}
	
	/**
	 * 
	 * @param searchFilter
	 * @param appUser
	 * @return
	 */
	private String getRequestUrlKeyParameters(JsonFraktKalkulatorUserContainer searchFilter, SystemaWebUser appUser){
		StringBuffer urlRequestParamsKeys = new StringBuffer();
		
		urlRequestParamsKeys.append("user=" + appUser.getUser());
		if(searchFilter.getWsavd()!=null && !"".equals(searchFilter.getWsavd())){
			urlRequestParamsKeys.append(FraktKalkulatorConstants.URL_CHAR_DELIMETER_FOR_PARAMS_WITH_HTML_REQUEST + "wsavd=" + searchFilter.getWsavd());
		}
		if(searchFilter.getAvvknr()!=null && !"".equals(searchFilter.getAvvknr())){
			urlRequestParamsKeys.append(FraktKalkulatorConstants.URL_CHAR_DELIMETER_FOR_PARAMS_WITH_HTML_REQUEST + "avvknr=" + searchFilter.getAvvknr());
		}
		if(searchFilter.getFra()!=null && !"".equals(searchFilter.getFra())){
			urlRequestParamsKeys.append(FraktKalkulatorConstants.URL_CHAR_DELIMETER_FOR_PARAMS_WITH_HTML_REQUEST + "fra=" + searchFilter.getFra());
		}
		if(searchFilter.getFralk()!=null && !"".equals(searchFilter.getFralk())){
			urlRequestParamsKeys.append(FraktKalkulatorConstants.URL_CHAR_DELIMETER_FOR_PARAMS_WITH_HTML_REQUEST + "fralk=" + searchFilter.getFralk());
		}
		
		if(searchFilter.getTil()!=null && !"".equals(searchFilter.getTil())){
			urlRequestParamsKeys.append(FraktKalkulatorConstants.URL_CHAR_DELIMETER_FOR_PARAMS_WITH_HTML_REQUEST + "til=" + searchFilter.getTil());
		}
		if(searchFilter.getTillk()!=null && !"".equals(searchFilter.getTillk())){
			urlRequestParamsKeys.append(FraktKalkulatorConstants.URL_CHAR_DELIMETER_FOR_PARAMS_WITH_HTML_REQUEST + "tillk=" + searchFilter.getTillk());
		}
		if(searchFilter.getVkt()!=null && !"".equals(searchFilter.getVkt())){
			urlRequestParamsKeys.append(FraktKalkulatorConstants.URL_CHAR_DELIMETER_FOR_PARAMS_WITH_HTML_REQUEST + "vkt=" + searchFilter.getVkt());
		}
		if(searchFilter.getAnt()!=null && !"".equals(searchFilter.getAnt())){
			urlRequestParamsKeys.append(FraktKalkulatorConstants.URL_CHAR_DELIMETER_FOR_PARAMS_WITH_HTML_REQUEST + "ant=" + searchFilter.getAnt());
		}
		if(searchFilter.getM3()!=null && !"".equals(searchFilter.getM3())){
			urlRequestParamsKeys.append(FraktKalkulatorConstants.URL_CHAR_DELIMETER_FOR_PARAMS_WITH_HTML_REQUEST + "m3=" + searchFilter.getM3());
		}
		if(searchFilter.getLm()!=null && !"".equals(searchFilter.getLm())){
			urlRequestParamsKeys.append(FraktKalkulatorConstants.URL_CHAR_DELIMETER_FOR_PARAMS_WITH_HTML_REQUEST + "lm=" + searchFilter.getLm());
		}
		if(searchFilter.getFarlig()!=null && !"".equals(searchFilter.getFarlig())){
			urlRequestParamsKeys.append(FraktKalkulatorConstants.URL_CHAR_DELIMETER_FOR_PARAMS_WITH_HTML_REQUEST + "farlig=" + searchFilter.getFarlig());
		}
		if(searchFilter.getVarme()!=null && !"".equals(searchFilter.getVarme())){
			urlRequestParamsKeys.append(FraktKalkulatorConstants.URL_CHAR_DELIMETER_FOR_PARAMS_WITH_HTML_REQUEST + "varme=" + searchFilter.getVarme());
		}
		if(searchFilter.getLengde()!=null && !"".equals(searchFilter.getLengde())){
			urlRequestParamsKeys.append(FraktKalkulatorConstants.URL_CHAR_DELIMETER_FOR_PARAMS_WITH_HTML_REQUEST + "lengde=" + searchFilter.getLengde());
		}
		if(searchFilter.getProd()!=null && !"".equals(searchFilter.getProd())){
			urlRequestParamsKeys.append(FraktKalkulatorConstants.URL_CHAR_DELIMETER_FOR_PARAMS_WITH_HTML_REQUEST + "prod=" + searchFilter.getProd());
		}
		if(searchFilter.getFrankod()!=null && !"".equals(searchFilter.getFrankod())){
			urlRequestParamsKeys.append(FraktKalkulatorConstants.URL_CHAR_DELIMETER_FOR_PARAMS_WITH_HTML_REQUEST + "fran=" + searchFilter.getFrankod());
		}
		if(searchFilter.getWsot()!=null && !"".equals(searchFilter.getWsot())){
			urlRequestParamsKeys.append(FraktKalkulatorConstants.URL_CHAR_DELIMETER_FOR_PARAMS_WITH_HTML_REQUEST + "wsot=" + searchFilter.getWsot());
		}
		if(searchFilter.getWsot1()!=null && !"".equals(searchFilter.getWsot1())){
			urlRequestParamsKeys.append(FraktKalkulatorConstants.URL_CHAR_DELIMETER_FOR_PARAMS_WITH_HTML_REQUEST + "wsot1=" + searchFilter.getWsot1());
		}
		
		return urlRequestParamsKeys.toString();
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
	
	/**
	 * 
	 * @param session
	 * @param model
	 * @param container
	 */
	private void setDomainObjectsInView(HttpSession session, Map model, JsonFraktKalkulatorResultContainer container){
		//SET HEADER RECORDS  (from RPG)
		/*
		for (JsonSporringOppdragTopicListRecord record : container.getQryoppdrag()){
			model.put(SporringOppdragConstants.DOMAIN_RECORD, record);
		}*/
		model.put(FraktKalkulatorConstants.DOMAIN_CONTAINER, container);
		//Put list for upcomming view (PDF, Excel, etc)
		/*if(container.getQryoppdrag()!=null){
			session.setAttribute(session.getId() + FraktKalkulatorConstants.SESSION_LIST, container.getQryoppdrag());
		}*/
	}

	//SERVICES
	@Qualifier ("urlCgiProxyService")
	private UrlCgiProxyService urlCgiProxyService;
	@Autowired
	@Required
	public void setUrlCgiProxyService (UrlCgiProxyService value){ this.urlCgiProxyService = value; }
	public UrlCgiProxyService getUrlCgiProxyService(){ return this.urlCgiProxyService; }
	
	@Qualifier 
	private FraktKalkulatorChildWindowsService fraktKalkulatorChildWindowsService;
	@Autowired
	@Required	
	public void setFraktKalkulatorChildWindowsService(FraktKalkulatorChildWindowsService value){this.fraktKalkulatorChildWindowsService = value;}
	public FraktKalkulatorChildWindowsService getFraktKalkulatorChildWindowsService(){ return this.fraktKalkulatorChildWindowsService; }
	
	@Qualifier 
	private FraktKalkulatorUserService fraktKalkulatorUserService;
	@Autowired
	@Required	
	public void setFraktKalkulatorUserService(FraktKalkulatorUserService value){this.fraktKalkulatorUserService = value;}
	public FraktKalkulatorUserService getFraktKalkulatorUserService(){ return this.fraktKalkulatorUserService; }
	
	@Qualifier 
	private FraktKalkulatorResultService fraktKalkulatorResultService;
	@Autowired
	@Required	
	public void setFraktKalkulatorResultService(FraktKalkulatorResultService value){this.fraktKalkulatorResultService = value;}
	public FraktKalkulatorResultService getFraktKalkulatorResultService(){ return this.fraktKalkulatorResultService; }
	
	@Qualifier 
	private FraktKalkulatorDropDownService fraktKalkulatorDropDownService;
	@Autowired
	@Required	
	public void setFraktKalkulatorDropDownService(FraktKalkulatorDropDownService value){this.fraktKalkulatorDropDownService = value;}
	public FraktKalkulatorDropDownService getFraktKalkulatorDropDownService(){ return this.fraktKalkulatorDropDownService; }
	
	
}

