package no.systema.z.main.maintenance.controller.avd.sad;

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

import no.systema.z.main.maintenance.model.jsonjackson.dbtable.sad.JsonMaintMainTrkodl01Container;
import no.systema.z.main.maintenance.model.jsonjackson.dbtable.sad.JsonMaintMainTrkodl01Record;
import no.systema.z.main.maintenance.service.sad.MaintMainTrkodl01Service;


import no.systema.z.main.maintenance.url.store.MaintenanceMainUrlDataStore;
import no.systema.z.main.maintenance.util.MainMaintenanceConstants;


/**
 * MAIN Maintenance  Controller - Child Window popup
 * 
 * @author oscardelatorre
 * @date Sep 21, 2016
 * 
 */

@Controller
@SessionAttributes(AppConstants.SYSTEMA_WEB_USER_KEY)
@Scope("session")
public class MainMaintenanceAvdSadControllerChildWindow {
	
	private static final Logger logger = Logger.getLogger(MainMaintenanceAvdSadControllerChildWindow.class.getName());
	private static final JsonDebugger jsonDebugger = new JsonDebugger(800);
	//customer
	
	private ModelAndView loginView = new ModelAndView("login");
	private ApplicationContext context;
	private LoginValidator loginValidator = new LoginValidator();
	//private CodeDropDownMgr codeDropDownMgr = new CodeDropDownMgr();
	private DateTimeManager dateTimeMgr = new DateTimeManager();
	
	
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
		@RequestMapping(value="mainmaintenanceavdsad_childwindow_generalcodes.do", params="action=doFind",  method={RequestMethod.GET, RequestMethod.POST} )
		public ModelAndView searchCodes(HttpSession session, HttpServletRequest request){
			logger.info("Inside searchCodes");
			
			ModelAndView successView = new ModelAndView("mainmaintenanceavdsad_childwindow_generalcodes");
			Map model = new HashMap();
			String callerType = request.getParameter("ctype");
			String tkunik = request.getParameter("tkunik");
			String tkkode = request.getParameter("tkkode");
			String tktxtn = request.getParameter("tktxtn");
			logger.info(callerType);
			logger.info("tkunik:" + tkunik);
			
			
			
			SystemaWebUser appUser = this.loginValidator.getValidUser(session);
			
			if(appUser==null){
				return this.loginView;
					
			}else{
				Collection<JsonMaintMainTrkodl01Record> list = new ArrayList<JsonMaintMainTrkodl01Record>();
				//prepare the access CGI with RPG back-end
				
				String BASE_URL = MaintenanceMainUrlDataStore.MAINTENANCE_MAIN_BASE_CODES_TRKODL01R_GET_LIST_URL;
				String urlRequestParamsKeys = this.getRequestUrlKeyParametersForSearchCodes(appUser.getUser(), tkunik, tkkode);
				logger.info("URL: " + BASE_URL);
				logger.info("PARAMS: " + urlRequestParamsKeys);
				logger.info(Calendar.getInstance().getTime() +  " CGI-start timestamp");
				String jsonPayload = this.urlCgiProxyService.getJsonContent(BASE_URL, urlRequestParamsKeys);
				//debugger
				logger.debug(jsonDebugger.debugJsonPayloadWithLog4J(jsonPayload));
				logger.info(Calendar.getInstance().getTime() +  " CGI-end timestamp");
		    	if(jsonPayload!=null){
		    		JsonMaintMainTrkodl01Container container = this.maintMainTrkodl01Service.getList(jsonPayload);
		    		if(container!=null){
		    			list = container.getList();
		    			for(JsonMaintMainTrkodl01Record  record : list){
		    				
		    			}
		    		}
		    	}
				model.put("list", list);
				model.put("tkunik", tkunik);
				model.put("id", tkkode);
				model.put("text", tktxtn);
				
				model.put("ctype", callerType);
				
				successView.addObject(MainMaintenanceConstants.DOMAIN_MODEL , model);
				
		    	return successView;	
			  	
			}
			
		}	
		
	/**
	 * 
	 * @param applicationUser
	 * @param id
	 * @param name
	 * @return
	 */
	private String getRequestUrlKeyParametersForSearchCodes(String applicationUser, String tkunik, String tkkode){
		  StringBuffer sb = new StringBuffer();
		  sb.append("user=" + applicationUser);
		  sb.append("&tkunik=" + tkunik);
		  if(tkkode!=null && !"".equals(tkkode)){
			  sb.append( MainMaintenanceConstants.URL_CHAR_DELIMETER_FOR_PARAMS_WITH_HTML_REQUEST + "tkkode=" + tkkode );
		  }
		  return sb.toString();
	  }
	
	
	//SERVICES
	@Qualifier ("urlCgiProxyService")
	private UrlCgiProxyService urlCgiProxyService;
	@Autowired
	@Required
	public void setUrlCgiProxyService (UrlCgiProxyService value){ this.urlCgiProxyService = value; }
	public UrlCgiProxyService getUrlCgiProxyService(){ return this.urlCgiProxyService; }
	
	
	@Qualifier ("maintMainTrkodl01Service")
	private MaintMainTrkodl01Service maintMainTrkodl01Service;
	@Autowired
	@Required	
	public void setMaintMainTrkodl01Service(MaintMainTrkodl01Service value){this.maintMainTrkodl01Service = value;}
	public MaintMainTrkodl01Service getMaintMainTrkodl01Service(){ return this.maintMainTrkodl01Service; }
	
}

