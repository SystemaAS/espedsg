package no.systema.aespedsgtestersuite.controller;

import java.lang.reflect.Field;
import java.util.*;
import java.net.URL;
import java.net.HttpURLConnection;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;

import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
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
import no.systema.main.service.UrlCgiProxyService;
import no.systema.main.validator.LoginValidator;
import no.systema.main.util.AppConstants;
import no.systema.main.util.JsonDebugger;
import no.systema.main.model.SystemaWebUser;

//SysJservices
import no.systema.aespedsgtestersuite.model.TestersuiteObject;
import no.systema.tvinn.sad.url.store.TvinnSadUrlDataStore;
import no.systema.tds.url.store.TdsUrlDataStore;
import no.systema.skat.url.store.SkatUrlDataStore;
import no.systema.ebooking.url.store.EbookingUrlDataStore;
import no.systema.transportdisp.url.store.TransportDispUrlDataStore;
import no.systema.sporringoppdrag.url.store.SporringOppdragUrlDataStore;
import no.systema.fraktkalkulator.url.store.FraktKalkulatorUrlDataStore;
import no.systema.tror.url.store.TrorUrlDataStore;
import no.systema.overview.ufortolledeoppdrag.url.store.UrlDataStore;
import no.systema.efaktura.url.store.EfakturaUrlDataStore;
import no.systema.tvinn.sad.admin.url.store.SadAdminUrlDataStore;



/**
 * eSpedsg tester suite list Controller 
 * 
 * @author oscardelatorre
 * @date Feb 16, 2018
 * 
 */

@Controller
@SessionAttributes(AppConstants.SYSTEMA_WEB_USER_KEY)
@Scope("session")

public class JsonEspedsgTestersuiteController {
	private static final JsonDebugger jsonDebugger = new JsonDebugger(3000);
	private static Logger logger = Logger.getLogger(JsonEspedsgTestersuiteController.class.getName());
	private ModelAndView loginView = new ModelAndView("login");
	private ApplicationContext context;
	private LoginValidator loginValidator = new LoginValidator();
	private final String GREEN_STATUS = "G";
	private final String RED_STATUS = "R";
	private final String YELLOW_STATUS = "Y";
	private final int TIMEOUT_LIMIT = 4000;
	private final String ERROR_PREFIX = "ERROR: ";
	private final String CONTROLLER_TEST_MODULE_URL = "aespedsgtestersuite_detail";
	//test modules
	private final String TEST_MODULE_OPPDREG = "oppdreg";
	private final String TEST_MODULE_TDS = "tds";
	private final String TEST_MODULE_TVINN = "tvinn";
	private final String TEST_MODULE_SKAT = "skat";
	private final String TEST_MODULE_AVG_GRUNNLAG = "avggrunn";
	private final String TEST_MODULE_EFAKTURA = "efaktura";
	private final String TEST_MODULE_EBOOKING = "ebooking";
	private final String TEST_MODULE_LASTETORG = "lastetorg";
	private final String TEST_MODULE_PRISKALK = "priskalk";
	private final String TEST_MODULE_UFORTOLL = "ufortoll";
	private final String TEST_MODULE_SPORROPPD = "sporroppd";
	private final String TEST_MODULE_ALTINN = "altinn";
	
	

	
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
	@RequestMapping(value="aespedsgtestersuite.do", method={RequestMethod.GET, RequestMethod.POST} )
	public ModelAndView doList( HttpSession session, HttpServletRequest request, HttpServletResponse response){
		List list = new ArrayList();
		
		Map model = new HashMap();
		
		ModelAndView successView = new ModelAndView("aespedsgtestersuite");
		SystemaWebUser appUser = this.loginValidator.getValidUser(session);
		
		//check user (should be in session already)
		if(appUser==null){
			return loginView;
		
		}else{
			appUser.setActiveMenu(SystemaWebUser.ACTIVE_MENU_TEST_SUITES);
			logger.info(Calendar.getInstance().getTime() + " CONTROLLER start - timestamp");
			list = this.initTesterSuiteSpecification();
			model.put("list",list);
			
    		//--------------------------------------
    		//Final successView with domain objects
    		//--------------------------------------
			successView.addObject("model", model);
    		logger.info(Calendar.getInstance().getTime() + " CONTROLLER end - timestamp");
    		return successView;
		    
		}
	}
	
	
	/**
	 * 
	 * @param session
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value="aespedsgtestersuite_detail.do", method={RequestMethod.GET, RequestMethod.POST} )
	public ModelAndView doTestGeneric( HttpSession session, HttpServletRequest request, HttpServletResponse response){
		List list = new ArrayList();
		
		Map model = new HashMap();
		String testModule = request.getParameter("tm");
		ModelAndView successView = new ModelAndView("aespedsgtestersuite_detail");
		SystemaWebUser appUser = this.loginValidator.getValidUser(session);
		
		//check user (should be in session already)
		if(appUser==null){
			return loginView;
		
		}else{
			//appUser.setActiveMenu(SystemaWebUser.ACTIVE_MENU_TEST_SUITES);
			logger.info(Calendar.getInstance().getTime() + " CONTROLLER start - timestamp");
			
			
			list = this.setTesterSuiteServices(appUser, testModule, model);
			model.put("listServices",list);
			
    		//--------------------------------------
    		//Final successView with domain objects
    		//--------------------------------------
			successView.addObject("model", model);
    		logger.info(Calendar.getInstance().getTime() + " CONTROLLER end - timestamp");
    		return successView;
		    
		}
	}
	/**
	 * 
	 * @param session
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value="aespedsgtestersuite_altinnproxy.do", method={RequestMethod.GET, RequestMethod.POST} )
	public ModelAndView doTestAltin( HttpSession session, HttpServletRequest request, HttpServletResponse response){
		List list = new ArrayList();
		
		Map model = new HashMap();
		String testModule = request.getParameter("tm");
		ModelAndView successView = new ModelAndView("aespedsgtestersuite_detail");
		SystemaWebUser appUser = this.loginValidator.getValidUser(session);
		
		//check user (should be in session already)
		if(appUser==null){
			return loginView;
		
		}else{
			//appUser.setActiveMenu(SystemaWebUser.ACTIVE_MENU_TEST_SUITES);
			logger.info(Calendar.getInstance().getTime() + " CONTROLLER start - timestamp");
			
			//http://gw.systema.no:8080/altinn-proxy/downloadDagsobjor.do?user=DEMO
			list = this.setTesterSuiteServicesAltin(appUser.getUser(),testModule, model);
			model.put("listServices",list);
			
    		//--------------------------------------
    		//Final successView with domain objects
    		//--------------------------------------
			successView.addObject("model", model);
    		logger.info(Calendar.getInstance().getTime() + " CONTROLLER end - timestamp");
    		return successView;
		    
		}
	}
	
	/**
	 * Since there is no data layer...
	 * @return
	 */
	private List initTesterSuiteSpecification(){
		List list = new ArrayList();
		TestersuiteObject obj =null;
		
		obj = new TestersuiteObject();
		obj.setId("s");obj.setModuleName("Altinn-proxy");
		obj.setStatus(GREEN_STATUS);
		obj.setServiceUrl("aespedsgtestersuite_altinnproxy");
		obj.setText(this.TEST_MODULE_ALTINN);
		//logger.info(obj.getServiceUrl());
		list.add(obj);
		//
		obj = new TestersuiteObject();
		obj.setId("s");obj.setModuleName("eBooking");
		obj.setStatus(GREEN_STATUS);
		obj.setServiceUrl(CONTROLLER_TEST_MODULE_URL);
		obj.setText(this.TEST_MODULE_EBOOKING);
		list.add(obj);
		//
		obj = new TestersuiteObject();
		obj.setId("s");obj.setModuleName("eFaktura Log - N");
		obj.setStatus(GREEN_STATUS);
		obj.setServiceUrl(CONTROLLER_TEST_MODULE_URL);
		obj.setText(this.TEST_MODULE_EFAKTURA);
		list.add(obj);
		//
		obj = new TestersuiteObject();
		obj.setId("s");obj.setModuleName("Fortolling - Avgiftsgrunnlag NO");
		obj.setStatus(GREEN_STATUS);
		obj.setServiceUrl(CONTROLLER_TEST_MODULE_URL);
		obj.setText(this.TEST_MODULE_AVG_GRUNNLAG);
		list.add(obj);
		//
		obj = new TestersuiteObject();
		obj.setId("s");obj.setModuleName("Fortolling - TVINN og Kundedatakontroll mot Brønnyøsund - NO");
		obj.setStatus(GREEN_STATUS);
		obj.setServiceUrl(CONTROLLER_TEST_MODULE_URL);
		obj.setText(this.TEST_MODULE_TVINN);
		list.add(obj);
		//
		obj = new TestersuiteObject();
		obj.setId("s");obj.setModuleName("Fortolling - TDS");
		obj.setStatus(GREEN_STATUS);
		obj.setServiceUrl(CONTROLLER_TEST_MODULE_URL);
		obj.setText(this.TEST_MODULE_TDS);
		list.add(obj);
		//
		obj = new TestersuiteObject();
		obj.setId("s");obj.setModuleName("Fortolling - SKAT");
		obj.setStatus(GREEN_STATUS);
		obj.setServiceUrl(CONTROLLER_TEST_MODULE_URL);
		obj.setText(this.TEST_MODULE_SKAT);
		list.add(obj);
		//
		obj = new TestersuiteObject();
		obj.setId("s");obj.setModuleName("Lastetorg");
		obj.setStatus(GREEN_STATUS);
		obj.setServiceUrl(CONTROLLER_TEST_MODULE_URL);
		obj.setText(TEST_MODULE_LASTETORG);
		list.add(obj);
		//
		obj = new TestersuiteObject();
		obj.setId("s");obj.setModuleName("Oppdragsregistrering");
		obj.setStatus(GREEN_STATUS);
		obj.setServiceUrl(CONTROLLER_TEST_MODULE_URL);
		obj.setText(this.TEST_MODULE_OPPDREG);
		list.add(obj);
		//
		obj = new TestersuiteObject();
		obj.setId("s");obj.setModuleName("Priskalkulator");
		obj.setStatus(GREEN_STATUS);
		obj.setServiceUrl(CONTROLLER_TEST_MODULE_URL);
		obj.setText(this.TEST_MODULE_PRISKALK);
		list.add(obj);
		//
		obj = new TestersuiteObject();
		obj.setId("s");obj.setModuleName("Spørring på Oppdrag");
		obj.setStatus(GREEN_STATUS);
		obj.setServiceUrl(CONTROLLER_TEST_MODULE_URL);
		obj.setText(this.TEST_MODULE_SPORROPPD);
		list.add(obj);
		//
		obj = new TestersuiteObject();
		obj.setId("s");obj.setModuleName("Ufortollede oppdrag");
		obj.setStatus(GREEN_STATUS);
		obj.setServiceUrl(CONTROLLER_TEST_MODULE_URL);
		obj.setText(this.TEST_MODULE_UFORTOLL);
		list.add(obj);
		//
		
		return list;
	}
	
	/**
	 * 
	 * @param testModule
	 * @param model
	 * @return
	 */
	private List setTesterSuiteServices(SystemaWebUser appUser, String testModule, Map<String, Object> model){
		List retval = new ArrayList();
		
		StringBuffer moduleChild = new StringBuffer();
		Object urlStoreObj = this.setModuleChildText(testModule, moduleChild);
		
		try{
			model.put("moduleChild", moduleChild.toString());
			
			//Start with Reflection
			Class cl = Class.forName(urlStoreObj.getClass().getCanonicalName());
			Field[] fields = cl.getDeclaredFields();
			List<Field> list = Arrays.asList(fields);
			TestersuiteObject testersuiteObject = null;
			logger.info("before loop...");
			for(Field field : list){
				try{
					field.setAccessible(true);
					String value = (String)field.get(urlStoreObj);
					if(value!=null && !"".equals(value)){
						logger.info(field.getName() + " Value:" + value);
						testersuiteObject = new TestersuiteObject();
						testersuiteObject.setModuleName(value);
						testersuiteObject.setServiceUrl(value);
						
						if(fireFirstSmokeTest(value, testersuiteObject, this.TIMEOUT_LIMIT)){
							testersuiteObject.setStatus(GREEN_STATUS);
							//====================
							//From here on: Note
							//====================
							//THIS 2nd-SMOKE-TEST IS NOT IN USED until further agreement.
							/*
							if(value.contains("syj") && field.getName().contains("UPDATE") ){
								//java services with Update require a valid USER or get a NullPointerException otherwise. 
								//We just leave all CRUD-services as = OK
								testersuiteObject.setStatus(GREEN_STATUS);
							}else{
								
								
								String BASE_URL = value;
								String urlRequestParams = "user=" + appUser.getUser();
								//fire the second smoke test
								String jsonReturnPayload = this.urlCgiProxyService.getJsonContent(BASE_URL, urlRequestParams);
								//logger.info(jsonReturnPayload);
								
								if(jsonReturnPayload!=null && (jsonReturnPayload.contains("user") || jsonReturnPayload.contains("JsonResponseOutputterController") )){
									testersuiteObject.setStatus(GREEN_STATUS);
								}else{
									testersuiteObject.setStatus(RED_STATUS);
									testersuiteObject.setErrMsg(ERROR_PREFIX + "json-payload [null]: second smoke test failure [http: code 500]...");
								}
							
							}
							*/
						}else{
							testersuiteObject.setStatus(RED_STATUS);
							
						}
						
						retval.add(testersuiteObject);
					}
				}catch(Exception e){
					continue;
				}
			}
		}catch(Exception e){
			logger.info(e.toString());
		}
		return retval;
	}
	/**
	 * 
	 * @param testModule
	 * @param model
	 * @return
	 */
	private List setTesterSuiteServicesAltin(String validUser, String testModule, Map<String, Object> model){
		List retval = new ArrayList();
		//String value = "http://gw.systema.no:8080/altinn-proxy/downloadDagsobjor.do?user=" + validUser;
		String value = AppConstants.HTTP_ROOT_SERVLET_JSERVICES + "/altinn-proxy/downloadDagsobjor.do?user=" + validUser;
		
		try{
			model.put("moduleChild", "Altinn-Proxy");
			
			TestersuiteObject testersuiteObject = null;
			testersuiteObject = new TestersuiteObject();
			testersuiteObject.setModuleName(value);
			testersuiteObject.setServiceUrl(value);
			
			if(fireFirstSmokeTest(value, testersuiteObject, this.TIMEOUT_LIMIT + 5000)){ //usually takes longer than other services
				String BASE_URL = value;
				//fire the second smoke test
				String payload = this.urlCgiProxyService.getJsonContent(BASE_URL, "");
				logger.info(payload);
				
				if(payload!=null && payload.contains("Dagsoppgjors") ){
					testersuiteObject.setStatus(GREEN_STATUS);
				}else{
					testersuiteObject.setStatus(RED_STATUS);
					testersuiteObject.setErrMsg(ERROR_PREFIX + "json-payload [null]: second smoke test failure [http: code 500]...");
				}
			}else{
				testersuiteObject.setStatus(RED_STATUS);
				
			}
			retval.add(testersuiteObject);
	
		}catch(Exception e){
			logger.info(e.toString());
		}
		return retval;
	}
	/**
	 * Checks it the url is available for connection. To avoid "hang" service...
	 * @param url
	 * @return
	 */
	private boolean fireFirstSmokeTest(String url, TestersuiteObject testersuiteObject, int timeout){
		boolean retval = false;
		try{
			URL u = new URL ( url);
			HttpURLConnection huc =  ( HttpURLConnection )  u.openConnection (); 
			huc.setRequestMethod ("GET");  //OR  huc.setRequestMethod ("HEAD"); 
			huc.setConnectTimeout(TIMEOUT_LIMIT); // x seconds connectTimeout
			huc.setReadTimeout(timeout);
			huc.connect () ; 
			int code = huc.getResponseCode() ;
			logger.info("CODE:" + code);
			
			retval = true;
		}catch(Exception e){
			testersuiteObject.setErrMsg(ERROR_PREFIX + e.toString());
			logger.info(e.toString());	
		}
		return retval;
	}
	
	/**
	 * 
	 * @param testModule
	 * @param moduleChildTitle
	 * @return
	 */
	private Object setModuleChildText(String testModule, StringBuffer moduleChildTitle){
		Object urlStoreObj = null;
		
		//Instantiate the correct test module
		
		if(this.TEST_MODULE_OPPDREG.equals(testModule)){ 
			urlStoreObj = new TrorUrlDataStore();
			moduleChildTitle.append(TEST_MODULE_OPPDREG.toUpperCase());
		
		}else if(this.TEST_MODULE_TVINN.equals(testModule)){ 
			urlStoreObj = new TvinnSadUrlDataStore();
			moduleChildTitle.append(TEST_MODULE_TVINN.toUpperCase());
		
		}else if(this.TEST_MODULE_TDS.equals(testModule)){ 
			urlStoreObj = new TdsUrlDataStore();
			moduleChildTitle.append(TEST_MODULE_TDS.toUpperCase());
		
		}else if(this.TEST_MODULE_SKAT.equals(testModule)){ 
			urlStoreObj = new SkatUrlDataStore();
			moduleChildTitle.append(TEST_MODULE_SKAT.toUpperCase());
		
		}else if(this.TEST_MODULE_EBOOKING.equals(testModule)){ 
			urlStoreObj = new EbookingUrlDataStore();
			moduleChildTitle.append(TEST_MODULE_EBOOKING.toUpperCase());
		
		}else if(this.TEST_MODULE_LASTETORG.equals(testModule)){ 
			urlStoreObj = new TransportDispUrlDataStore();
			moduleChildTitle.append(TEST_MODULE_LASTETORG.toUpperCase());
		
		}else if(this.TEST_MODULE_SPORROPPD.equals(testModule)){ 
			urlStoreObj = new SporringOppdragUrlDataStore();
			moduleChildTitle.append(TEST_MODULE_SPORROPPD.toUpperCase());
		
		}else if(this.TEST_MODULE_UFORTOLL.equals(testModule)){ 
			urlStoreObj = new UrlDataStore();
			moduleChildTitle.append(TEST_MODULE_UFORTOLL.toUpperCase());
		
		}else if(this.TEST_MODULE_PRISKALK.equals(testModule)){ 
			urlStoreObj = new FraktKalkulatorUrlDataStore();
			moduleChildTitle.append(TEST_MODULE_PRISKALK.toUpperCase());
		
		}else if(this.TEST_MODULE_AVG_GRUNNLAG.equals(testModule)){ 
			urlStoreObj = new SadAdminUrlDataStore();
			moduleChildTitle.append(TEST_MODULE_AVG_GRUNNLAG.toUpperCase());
		
		}else if(this.TEST_MODULE_EFAKTURA.equals(testModule)){ 
			urlStoreObj = new EfakturaUrlDataStore();
			moduleChildTitle.append(TEST_MODULE_EFAKTURA.toUpperCase());
		
		}
		

		return urlStoreObj;
	}
	
	
	//SERVICES
	@Qualifier ("urlCgiProxyService")
	private UrlCgiProxyService urlCgiProxyService;
	@Autowired
	@Required
	public void setUrlCgiProxyService (UrlCgiProxyService value){ this.urlCgiProxyService = value; }
	public UrlCgiProxyService getUrlCgiProxyService(){ return this.urlCgiProxyService; }
		
}

