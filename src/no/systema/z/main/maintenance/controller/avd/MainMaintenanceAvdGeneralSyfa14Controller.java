package no.systema.z.main.maintenance.controller.avd;

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
//models
import no.systema.tvinn.sad.z.maintenance.main.util.TvinnSadMaintenanceConstants;
import no.systema.z.main.maintenance.url.store.MaintenanceMainUrlDataStore;
import no.systema.z.main.maintenance.util.MainMaintenanceConstants;
import no.systema.z.main.maintenance.service.MaintMainKodtaService;
import no.systema.z.main.maintenance.model.jsonjackson.dbtable.JsonMaintMainKodtaContainer;
import no.systema.z.main.maintenance.model.jsonjackson.dbtable.JsonMaintMainKodtaRecord;
import no.systema.z.main.maintenance.mapper.url.request.UrlRequestParameterMapper;
import no.systema.z.main.maintenance.validator.MaintMainKodtaValidator;

/**
 * Gateway to the Main Maintenance Application
 * 
 * 
 * @author oscardelatorre
 * @date Jul 01, 2016
 * 
 * 	
 */

@Controller
public class MainMaintenanceAvdGeneralSyfa14Controller {
	private static final Logger logger = Logger.getLogger(MainMaintenanceAvdGeneralSyfa14Controller.class.getName());
	private ModelAndView loginView = new ModelAndView("login");
	private static final JsonDebugger jsonDebugger = new JsonDebugger();
	private UrlRequestParameterMapper urlRequestParameterMapper = new UrlRequestParameterMapper();
	
	/**
	 * 
	 * @param user
	 * @param result
	 * @param request
	 * @return
	 * 
	 */
	@RequestMapping(value="mainmaintenanceavd_syfa14r.do", method={RequestMethod.GET, RequestMethod.POST })
	public ModelAndView mainmaintenanceavd_syfa14r(HttpSession session, HttpServletRequest request){
		ModelAndView successView = new ModelAndView("mainmaintenanceavd_syfa14r");
		SystemaWebUser appUser = (SystemaWebUser)session.getAttribute(AppConstants.SYSTEMA_WEB_USER_KEY);
		Map model = new HashMap();
		if(appUser==null){
			return this.loginView;
		}else{
			appUser.setActiveMenu("INIT");
			logger.info("Inside method: mainmaintenanceavd_syfa14r");
			logger.info("appUser user:" + appUser.getUser());
			logger.info("appUser lang:" + appUser.getUsrLang());
			logger.info("appUser userAS400:" + appUser.getUserAS400());
			
			appUser.setActiveMenu(SystemaWebUser.ACTIVE_MENU_MAIN_MAINTENANCE);
			session.setAttribute(MainMaintenanceConstants.ACTIVE_URL_RPG_MAIN_MAINTENANCE, MainMaintenanceConstants.ACTIVE_URL_RPG_INITVALUE); 
			
			//Get list
	 		List list = this.fetchList(appUser.getUser());
			model.put("list", list);
			successView.addObject(MainMaintenanceConstants.DOMAIN_MODEL , model);
			
			logger.info("Host via HttpServletRequest.getHeader('Host'): " + request.getHeader("Host"));
		    
			return successView;
			
		}
	}
	/**
	 * 
	 * @param session
	 * @param request
	 * @return
	 */
	@RequestMapping(value="mainmaintenanceavd_syfa14r_edit.do", method={RequestMethod.GET, RequestMethod.POST })
	public ModelAndView mainmaintenanceavd_syfa14r_edit(@ModelAttribute ("record") JsonMaintMainKodtaRecord recordToValidate, BindingResult bindingResult, HttpSession session, HttpServletRequest request){
		ModelAndView successView = new ModelAndView("mainmaintenanceavd_syfa14r_edit");
		SystemaWebUser appUser = (SystemaWebUser)session.getAttribute(AppConstants.SYSTEMA_WEB_USER_KEY);
		Map model = new HashMap();
		String avd = request.getParameter("avd");
		String action = request.getParameter("action");
		boolean updateValid = false;
		
		if(appUser==null){
			return this.loginView;
		}else{
			appUser.setActiveMenu("INIT");
			logger.info("Inside method: mainmaintenanceavd_syfa14r_edit");
			logger.info("appUser user:" + appUser.getUser());
			logger.info("appUser lang:" + appUser.getUsrLang());
			logger.info("appUser userAS400:" + appUser.getUserAS400());
			
			appUser.setActiveMenu(SystemaWebUser.ACTIVE_MENU_MAIN_MAINTENANCE);
			session.setAttribute(MainMaintenanceConstants.ACTIVE_URL_RPG_MAIN_MAINTENANCE, MainMaintenanceConstants.ACTIVE_URL_RPG_INITVALUE); 
			//UPDATE
			if (MainMaintenanceConstants.ACTION_UPDATE.equals(action)){
				avd = recordToValidate.getKoaavd();
				//Validate
				MaintMainKodtaValidator validator = new MaintMainKodtaValidator();
				validator.validate(recordToValidate, bindingResult);
				if(bindingResult.hasErrors()){
					//ERRORS
					logger.info("[ERROR Validation] Record does not validate)");
					//model.put("dbTable", dbTable);
					model.put(MainMaintenanceConstants.DOMAIN_RECORD, recordToValidate);
					
				}else{
					//Update table
					StringBuffer errMsg = new StringBuffer();
					int dmlRetval = 0;
					dmlRetval = this.updateRecord(appUser.getUser(), recordToValidate, MainMaintenanceConstants.MODE_UPDATE, errMsg);
					
					//check for Update errors
					if( dmlRetval < 0){
						logger.info("[ERROR Validation] Record does not validate)");
						//model.put("dbTable", dbTable);
						model.put(MainMaintenanceConstants.ASPECT_ERROR_MESSAGE, errMsg.toString());
						model.put(MainMaintenanceConstants.DOMAIN_RECORD, recordToValidate);
						
					}
				}
			}else{
				//-------------
				//Fetch record
				//-------------
				JsonMaintMainKodtaRecord record = this.fetchRecord(appUser.getUser(), avd);
				
				//this.populateAvdelningHtmlDropDownsFromJsonString(model, appUser);
				//this.populateSignatureHtmlDropDownsFromJsonString(model, appUser);
				//this.setCodeDropDownMgr(appUser, model);
				
				//only if update = OK
				model.put(MainMaintenanceConstants.DOMAIN_RECORD, record);
			}
			
			model.put("avd", avd);
			successView.addObject(MainMaintenanceConstants.DOMAIN_MODEL , model);
			
			logger.info("Host via HttpServletRequest.getHeader('Host'): " + request.getHeader("Host"));
		    
			return successView;
			
		}
	}

	/**
	 * 
	 * @param applicationUser
	 * @return
	 */
	private List<JsonMaintMainKodtaRecord> fetchList(String applicationUser){
		
		String BASE_URL = MaintenanceMainUrlDataStore.MAINTENANCE_MAIN_BASE_SYFA14R_GET_LIST_URL;
		String urlRequestParams = "user=" + applicationUser;
		logger.info(Calendar.getInstance().getTime() + " CGI-start timestamp");
    	logger.info("URL: " + jsonDebugger.getBASE_URL_NoHostName(BASE_URL));
    	logger.info("URL PARAMS: " + urlRequestParams);
    	String jsonPayload = this.urlCgiProxyService.getJsonContent(BASE_URL, urlRequestParams);
    	//DEBUG
    	this.jsonDebugger.debugJsonPayload(jsonPayload, 1000);
    	//extract
    	List<JsonMaintMainKodtaRecord> list = new ArrayList();
    	if(jsonPayload!=null){
			//lists
    		JsonMaintMainKodtaContainer container = this.maintMainKodtaService.getList(jsonPayload);
	        if(container!=null){
	        	list = (List)container.getList();
	        }
    	}
    	return list;
    	
	}
	
	/**
	 * 
	 * @param applicationUser
	 * @param id
	 * @return
	 */
	private JsonMaintMainKodtaRecord fetchRecord(String applicationUser, String id){
		JsonMaintMainKodtaRecord record = new JsonMaintMainKodtaRecord();
    	
		String BASE_URL = MaintenanceMainUrlDataStore.MAINTENANCE_MAIN_BASE_SYFA14R_GET_LIST_URL;
		String urlRequestParams = "user=" + applicationUser + "&koaavd=" + id;
		
		logger.info(Calendar.getInstance().getTime() + " CGI-start timestamp");
    	logger.info("URL: " + jsonDebugger.getBASE_URL_NoHostName(BASE_URL));
    	logger.info("URL PARAMS: " + urlRequestParams);
    	String jsonPayload = this.urlCgiProxyService.getJsonContent(BASE_URL, urlRequestParams);
    	//DEBUG
    	this.jsonDebugger.debugJsonPayload(jsonPayload, 1000);
    	//extract
    	List<JsonMaintMainKodtaRecord> list = new ArrayList();
    	
    	if(jsonPayload!=null){
			//lists
    		JsonMaintMainKodtaContainer container = this.maintMainKodtaService.getList(jsonPayload);
	        if(container!=null){
	        	list = (List)container.getList();
	        	for(JsonMaintMainKodtaRecord tmp : list){
	        		record = tmp;
	        	}
	        }
    	}
    	return record;
    	
	}
	
	/**
	 * 
	 * @param applicationUser
	 * @param record
	 * @param mode
	 * @param errMsg
	 * @return
	 */
	private int updateRecord(String applicationUser, JsonMaintMainKodtaRecord record, String mode, StringBuffer errMsg){
		int retval = 0;
		
		String BASE_URL = MaintenanceMainUrlDataStore.MAINTENANCE_MAIN_BASE_SYFA14R_DML_UPDATE_URL;
		String urlRequestParamsKeys = "user=" + applicationUser + "&mode=" + mode;
		String urlRequestParams = this.urlRequestParameterMapper.getUrlParameterValidString((record));
		//put the final valid param. string
		urlRequestParams = urlRequestParamsKeys + urlRequestParams;
		
		logger.info(Calendar.getInstance().getTime() + " CGI-start timestamp");
    	logger.info("URL: " + jsonDebugger.getBASE_URL_NoHostName(BASE_URL));
    	logger.info("URL PARAMS: " + urlRequestParams);
    	String jsonPayload = this.urlCgiProxyService.getJsonContent(BASE_URL, urlRequestParams);
    	
    	//extract
    	if(jsonPayload!=null){
			//lists
    		JsonMaintMainKodtaContainer container = this.maintMainKodtaService.doUpdate(jsonPayload);
	        if(container!=null){
	        	if(container.getErrMsg()!=null && !"".equals(container.getErrMsg())){
	        		if(container.getErrMsg().toUpperCase().startsWith("ERROR")){
	        			errMsg.append(container.getErrMsg());
	        			retval = MainMaintenanceConstants.ERROR_CODE;
	        		}
	        	}
	        }
    	}    	
    	return retval;
	}
	
	//Wired - SERVICES
	@Qualifier ("urlCgiProxyService")
	private UrlCgiProxyService urlCgiProxyService;
	@Autowired
	@Required
	public void setUrlCgiProxyService (UrlCgiProxyService value){ this.urlCgiProxyService = value; }
	public UrlCgiProxyService getUrlCgiProxyService(){ return this.urlCgiProxyService; }
	
	
	@Qualifier ("maintMainKodtaService")
	private MaintMainKodtaService maintMainKodtaService;
	@Autowired
	@Required
	public void setMaintMainKodtaService (MaintMainKodtaService value){ this.maintMainKodtaService = value; }
	public MaintMainKodtaService getMaintMainKodtaService(){ return this.maintMainKodtaService; }
	
	
	
		
}

