package no.systema.asyjservices.controller;

import java.lang.reflect.Field;
import java.util.*;
import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;

import org.apache.log4j.Level;
import org.apache.log4j.Logger;
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
import no.systema.asyjservices.model.jsonjackson.JsonSysJservicesMainListContainer;
import no.systema.asyjservices.model.jsonjackson.JsonSysJservicesMainListRecord;
import no.systema.asyjservices.url.store.SysJservicesUrlDataStore;
import no.systema.asyjservices.service.SysJservicesListService;


/**
 * SysJservices main list Controller 
 * 
 * @author oscardelatorre
 * @date Nov 4, 2015
 * 
 */

@Controller
@SessionAttributes(AppConstants.SYSTEMA_WEB_USER_KEY)
@Scope("session")

public class JsonSysJservicesMainListController {
	private static final JsonDebugger jsonDebugger = new JsonDebugger(3000);
	private static Logger logger = Logger.getLogger(JsonSysJservicesMainListController.class.getName());
	private ModelAndView loginView = new ModelAndView("login");
	private ApplicationContext context;
	private LoginValidator loginValidator = new LoginValidator();
	
	
	
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
	@RequestMapping(value="asyjservices_mainlist.do", method={RequestMethod.GET, RequestMethod.POST} )
	public ModelAndView doFind(@ModelAttribute ("record") JsonSysJservicesMainListRecord recordToValidate, BindingResult bindingResult, 
												HttpSession session, HttpServletRequest request, HttpServletResponse response){
		Collection list = new ArrayList();
		
		
		
		Map model = new HashMap();
		
		ModelAndView successView = new ModelAndView("asyjservices_mainlist");
		SystemaWebUser appUser = this.loginValidator.getValidUser(session);
		
		//check user (should be in session already)
		if(appUser==null){
			return loginView;
		
		}else{
			appUser.setActiveMenu("JSERVICES");
			logger.info(Calendar.getInstance().getTime() + " CONTROLLER start - timestamp");
				
    		Map maxWarningMap = new HashMap<String,String>();
    		String BASE_URL = SysJservicesUrlDataStore.SYSJSERVICES_MAIN_CUNDF_LIST_URL;
    		String urlRequestParams = "user=" + appUser.getUser();
    		logger.info("URL: " + BASE_URL);
        	logger.info("URL PARAMS: " + urlRequestParams);
        	String jsonPayload = this.urlCgiProxyService.getJsonContent(BASE_URL, urlRequestParams);
        	//Debug --> 
        	logger.debug(jsonDebugger.debugJsonPayloadWithLog4J(jsonPayload));
        	logger.info(Calendar.getInstance().getTime() +  " CGI-end timestamp");
        	if(jsonPayload!=null){
        		JsonSysJservicesMainListContainer listContainer = this.sysJservicesListService.getMainListContainer(jsonPayload);
        		list = listContainer.getList();	
        		
        	}
        	
    		//--------------------------------------
    		//Final successView with domain objects
    		//--------------------------------------
    		successView.addObject("model" , model);
    		//domain and search filter
    		successView.addObject("list",list);
    		logger.info(Calendar.getInstance().getTime() + " CONTROLLER end - timestamp");
    		return successView;
		    
		}
	}
	
	
	
	//SERVICES
	@Qualifier ("urlCgiProxyService")
	private UrlCgiProxyService urlCgiProxyService;
	@Autowired
	@Required
	public void setUrlCgiProxyService (UrlCgiProxyService value){ this.urlCgiProxyService = value; }
	public UrlCgiProxyService getUrlCgiProxyService(){ return this.urlCgiProxyService; }
	
	@Qualifier ("sysJservicesListService")
	private SysJservicesListService sysJservicesListService;
	@Autowired
	@Required
	public void setSysJservicesListService (SysJservicesListService value){ this.sysJservicesListService = value; }
	public SysJservicesListService getSysJservicesListService(){ return this.sysJservicesListService; }
	
	
}

