package no.systema.z.main.maintenance.controller.avd;

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
import no.systema.main.service.UrlCgiProxyService;
import no.systema.main.util.AppConstants;
import no.systema.main.util.JsonDebugger;
import no.systema.main.model.SystemaWebUser;

import no.systema.z.main.maintenance.model.jsonjackson.dbtable.JsonMaintMainKodtpUtskrsContainer;
import no.systema.z.main.maintenance.model.jsonjackson.dbtable.JsonMaintMainKodtpUtskrsRecord;
import no.systema.z.main.maintenance.service.MaintMainKodtpUtskrsService;
import no.systema.z.main.maintenance.url.store.MaintenanceMainUrlDataStore;
import no.systema.z.main.maintenance.util.MainMaintenanceConstants;




/**
 * SYFA28 Child windows popup
 * 
 * @author oscardelatorre
 * @date Aug 10, 2016
 * 
 */

@Controller
@SessionAttributes(AppConstants.SYSTEMA_WEB_USER_KEY)
@Scope("session")
public class MainMaintenanceAvdFastDataSyfa28ControllerChildWindow {
	
	private static final Logger logger = Logger.getLogger(MainMaintenanceAvdFastDataSyfa28ControllerChildWindow.class.getName());
	private static final JsonDebugger jsonDebugger = new JsonDebugger(2000);
	
	private ModelAndView loginView = new ModelAndView("login");
	private ApplicationContext context;
	
	
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
	@RequestMapping(value="mainmaintenanceavd_syfa28r_edit_childwindow.do",  method={RequestMethod.GET, RequestMethod.POST })
	public ModelAndView mainMaintenanceSyfa28Childwindow(@ModelAttribute ("record") JsonMaintMainKodtpUtskrsRecord recordToValidate, BindingResult bindingResult, HttpSession session, HttpServletRequest request){
		logger.info("Inside: mainMaintenanceSyfa28Childwindow");
		
		ModelAndView successView = new ModelAndView("mainmaintenanceavd_syfa28r_edit_childwindow");
		SystemaWebUser appUser = (SystemaWebUser)session.getAttribute(AppConstants.SYSTEMA_WEB_USER_KEY);
		
		Map model = new HashMap();
		String urlRequestParamsKeys = null;
		//Catch required action (doFetch or doUpdate)
		String action = request.getParameter("action");
		logger.info("ACTION: " + action);
		
		if(appUser==null){
			return this.loginView;
		}else{
			//Keys and header information
			//String avd = request.getParameter("avd");
			//String lnr = request.getParameter("lnr");
			
			//this fragment gets some header fields needed for the validator
			//JsonTdsExportSpecificTopicRecord headerRecord = (JsonTdsExportSpecificTopicRecord)session.getAttribute(TdsConstants.DOMAIN_RECORD_TOPIC);
			//String invoiceTotalAmount = headerRecord.getSveh_fabl();
			
			//FETCH the ITEM
			//---------------------------
			//get BASE URL = RPG-PROGRAM
            //---------------------------
			String BASE_URL = MaintenanceMainUrlDataStore.MAINTENANCE_MAIN_BASE_SYFA28R_GET_CHILDREN_LIST_URL;
			String urlRequestParams = "user=" + appUser.getUser() + "&kopavd=" + recordToValidate.getKopavd() + "&koplnr=" + recordToValidate.getKoplnr();
			
			logger.info(Calendar.getInstance().getTime() + " CGI-start timestamp");
	    	logger.info("URL: " + jsonDebugger.getBASE_URL_NoHostName(BASE_URL));
	    	logger.info("URL PARAMS: " + urlRequestParams);
	    	String jsonPayload = this.urlCgiProxyService.getJsonContent(BASE_URL, urlRequestParams);
	    	//DEBUG
	    	this.jsonDebugger.debugJsonPayload(jsonPayload, 1000);
	    	//extract
	    	
	    	if(jsonPayload!=null){
				//lists
	    		JsonMaintMainKodtpUtskrsContainer container = this.getMaintMainKodtpUtskrsService().getList(jsonPayload);
		        if(container!=null){
		        	for(JsonMaintMainKodtpUtskrsRecord record : container.getList()){
		        		model.put(MainMaintenanceConstants.DOMAIN_RECORD, record);
		        	}
		        }
	    	}
	    	successView.addObject(MainMaintenanceConstants.DOMAIN_MODEL,model);
			
	    	logger.info("END of method");
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
	
	
	@Qualifier ("maintMainKodtpUtskrsService")
	private MaintMainKodtpUtskrsService maintMainKodtpUtskrsService;
	@Autowired
	@Required
	public void setMaintMainKodtpUtskrsService (MaintMainKodtpUtskrsService value){ this.maintMainKodtpUtskrsService = value; }
	public MaintMainKodtpUtskrsService getMaintMainKodtpUtskrsService(){ return this.maintMainKodtpUtskrsService; }
	
}

