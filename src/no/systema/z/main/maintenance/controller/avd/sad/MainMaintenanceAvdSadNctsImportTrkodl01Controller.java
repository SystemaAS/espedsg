package no.systema.z.main.maintenance.controller.avd.sad;

import java.util.*;

import org.apache.log4j.Logger;
import org.springframework.validation.BindingResult;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Required;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import no.systema.main.service.UrlCgiProxyService;

//application imports
import no.systema.main.model.SystemaWebUser;
import no.systema.main.util.AppConstants;
import no.systema.main.util.JsonDebugger;
import no.systema.main.util.DateTimeManager;

//models
import no.systema.z.main.maintenance.url.store.MaintenanceMainUrlDataStore;
import no.systema.z.main.maintenance.util.MainMaintenanceConstants;
import no.systema.z.main.maintenance.service.sad.MaintMainTrkodl01Service;

import no.systema.z.main.maintenance.model.jsonjackson.dbtable.sad.JsonMaintMainTrkodl01Container;
import no.systema.z.main.maintenance.model.jsonjackson.dbtable.sad.JsonMaintMainTrkodl01Record;
import no.systema.z.main.maintenance.mapper.url.request.UrlRequestParameterMapper;


/**
 * Gateway to the Main Maintenance Application
 * 
 * 
 * @author oscardelatorre
 * @date Sep 21, 2016
 * 
 * 	
 */

@Controller
public class MainMaintenanceAvdSadNctsImportTrkodl01Controller {
	private static final Logger logger = Logger.getLogger(MainMaintenanceAvdSadNctsImportTrkodl01Controller.class.getName());
	private ModelAndView loginView = new ModelAndView("login");
	private static final JsonDebugger jsonDebugger = new JsonDebugger();
	private UrlRequestParameterMapper urlRequestParameterMapper = new UrlRequestParameterMapper();
	private DateTimeManager dateTimeMgr = new DateTimeManager();
	/**
	 * 
	 * @param user
	 * @param result
	 * @param request
	 * @return
	 * 
	 */
	@RequestMapping(value="mainmaintenanceavdsad_trkodl01r.do", method={RequestMethod.GET, RequestMethod.POST })
	public ModelAndView mainmaintenanceavdsad_trkodl01r (HttpSession session, HttpServletRequest request){
		ModelAndView successView = new ModelAndView("mainmaintenanceavdsad_trkodl01r");
		SystemaWebUser appUser = (SystemaWebUser)session.getAttribute(AppConstants.SYSTEMA_WEB_USER_KEY);
		Map model = new HashMap();
		
		String action = request.getParameter("action");
		String uniqueCode = request.getParameter("tkunik");
		String id =  request.getParameter("tkkode");
			
		if(appUser==null){
			return this.loginView;
		}else{
			logger.info("Inside method: mainmaintenanceavdsad_trkodl01r");
			logger.info("appUser user:" + appUser.getUser());
			logger.info("appUser lang:" + appUser.getUsrLang());
			logger.info("appUser userAS400:" + appUser.getUserAS400());
			
			//Get list
	 		List list = this.fetchList(appUser.getUser(), uniqueCode, id);
			model.put("list", list);
			successView.addObject(MainMaintenanceConstants.DOMAIN_MODEL , model);
			
			logger.info("Host via HttpServletRequest.getHeader('Host'): " + request.getHeader("Host"));
		    
			return successView;
			
		}
	}
	
	
	/**
	 * 
	 * @param applicationUser
	 * @param uniqueCode
	 * @param id
	 * @return
	 */
	private List<JsonMaintMainTrkodl01Record> fetchList(String applicationUser, String uniqueCode, String id){
		
		String BASE_URL = MaintenanceMainUrlDataStore.MAINTENANCE_MAIN_BASE_CODES_TRKODL01R_GET_LIST_URL;
		StringBuffer urlRequestParams = new StringBuffer();
		urlRequestParams.append("user=" + applicationUser + "&tkunik=" + uniqueCode);
		if(id!=null && !"".equals(id)){
			urlRequestParams.append("&tkkode=" + id);
		}
		logger.info(Calendar.getInstance().getTime() + " CGI-start timestamp");
    	logger.info("URL: " + jsonDebugger.getBASE_URL_NoHostName(BASE_URL));
    	logger.info("URL PARAMS: " + urlRequestParams);
    	String jsonPayload = this.urlCgiProxyService.getJsonContent(BASE_URL, urlRequestParams.toString());
    	//DEBUG
    	this.jsonDebugger.debugJsonPayload(jsonPayload, 500);
    	//extract
    	List<JsonMaintMainTrkodl01Record> list = new ArrayList();
    	if(jsonPayload!=null){
			//lists
    		if(uniqueCode!=null && !"".equals(uniqueCode)){ //uniqueCode is mandatory 
	    		JsonMaintMainTrkodl01Container container = this.maintMainTrkodl01Service.getList(jsonPayload);
		        if(container!=null){
		        	list = (List)container.getList();
		        }
    		}
    	}
    	return list;
    	
	}
	
	
	//Wired - SERVICES
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
	public void setMaintMainTrkodl01Service (MaintMainTrkodl01Service value){ this.maintMainTrkodl01Service = value; }
	public MaintMainTrkodl01Service getMaintMainTrkodl01Service(){ return this.maintMainTrkodl01Service; }
	
	
	

}

