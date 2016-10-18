package no.systema.z.main.maintenance.controller.sign;

import java.util.*;

import org.apache.log4j.Logger;
import org.springframework.validation.BindingResult;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.ServletRequestDataBinder;
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
import no.systema.z.main.maintenance.url.store.MaintenanceMainUrlDataStore;
import no.systema.z.main.maintenance.util.MainMaintenanceConstants;
import no.systema.z.main.maintenance.service.MaintMainKodtsfSyparfService;


import no.systema.z.main.maintenance.model.jsonjackson.dbtable.JsonMaintMainKodtaKodthRecord;

import no.systema.z.main.maintenance.model.jsonjackson.dbtable.JsonMaintMainKodtsfSyparfContainer;
import no.systema.z.main.maintenance.model.jsonjackson.dbtable.JsonMaintMainKodtsfSyparfRecord;

import no.systema.z.main.maintenance.model.jsonjackson.dbtable.JsonMaintMainKodtaTellRecord;
import no.systema.z.main.maintenance.mapper.url.request.UrlRequestParameterMapper;
import no.systema.z.main.maintenance.validator.MaintMainKodtsfSyparfValidator;


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
public class MainMaintenanceSignSyfa60Controller {
	private static final Logger logger = Logger.getLogger(MainMaintenanceSignSyfa60Controller.class.getName());
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
	@RequestMapping(value="mainmaintenancesign_syfa60r.do", method={RequestMethod.GET, RequestMethod.POST })
	public ModelAndView mainmaintenancesign_syfa60r(HttpSession session, HttpServletRequest request){
		ModelAndView successView = new ModelAndView("mainmaintenancesign_syfa60r_edit");
		SystemaWebUser appUser = (SystemaWebUser)session.getAttribute(AppConstants.SYSTEMA_WEB_USER_KEY);
		Map model = new HashMap();
		if(appUser==null){
			return this.loginView;
		}else{
			logger.info("Inside method: mainmaintenancesign_syfa60r");
			logger.info("appUser user:" + appUser.getUser());
			logger.info("appUser lang:" + appUser.getUsrLang());
			logger.info("appUser userAS400:" + appUser.getUserAS400());
			
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

	@RequestMapping(value="mainmaintenancesign_syfa60r_edit.do", method={RequestMethod.GET, RequestMethod.POST })
	public ModelAndView mainmaintenancesign_syfa60r_edit(@ModelAttribute ("record") JsonMaintMainKodtsfSyparfRecord recordToValidate, BindingResult bindingResult, HttpSession session, HttpServletRequest request){
		ModelAndView successView = new ModelAndView("mainmaintenancesign_syfa60r_edit");
		SystemaWebUser appUser = (SystemaWebUser)session.getAttribute(AppConstants.SYSTEMA_WEB_USER_KEY);
		Map model = new HashMap();
		//String id = request.getParameter("kosfsi");
		String action = request.getParameter("action");
		String updateId = request.getParameter("updateId");
		
		
		if(appUser==null){
			return this.loginView;
		}else{
			appUser.setActiveMenu("INIT");
			logger.info("Inside method: mainmaintenancesign_syfa60r_edit");
			logger.info("appUser user:" + appUser.getUser());
			logger.info("appUser lang:" + appUser.getUsrLang());
			logger.info("appUser userAS400:" + appUser.getUserAS400());
			
			appUser.setActiveMenu(SystemaWebUser.ACTIVE_MENU_MAIN_MAINTENANCE);
			session.setAttribute(MainMaintenanceConstants.ACTIVE_URL_RPG_MAIN_MAINTENANCE, MainMaintenanceConstants.ACTIVE_URL_RPG_INITVALUE); 
			//--------------
			//UPDATE record
			//--------------
			if (MainMaintenanceConstants.ACTION_UPDATE.equals(action)){
				//avd = recordToValidate.getKosfsi();
				//bind child records
				//JsonMaintMainKodtaKodthRecord listeHodeRecord = this.bindChildListeHode(request);
				//JsonMaintMainKodtaTellRecord oppnrTurRecord = this.bindChildOppnrTur(request);
				
				
				//Validate
				MaintMainKodtsfSyparfValidator validator = new MaintMainKodtsfSyparfValidator();
				validator.validate(recordToValidate, bindingResult);
				if(bindingResult.hasErrors()){
					//ERRORS
					logger.info("[ERROR Validation] Record does not validate)");
					//reload children
					//recordToValidate.setListeHodeRecord(listeHodeRecord);
					//recordToValidate.setOppnrTurRecord(oppnrTurRecord);
					model.put(MainMaintenanceConstants.DOMAIN_RECORD, recordToValidate);
					//get list
					model.put("list", this.fetchList(appUser.getUser()));
					
				}else{
					//Update table
					StringBuffer errMsg = new StringBuffer();
					int dmlRetval = 0;
					
					if(updateId!=null && !"".equals(updateId)){
						//update
						logger.info(MainMaintenanceConstants.MODE_UPDATE);
						dmlRetval = this.updateRecord(appUser.getUser(), recordToValidate, MainMaintenanceConstants.MODE_UPDATE, errMsg);
						//------------------------
						//now update all children
						//------------------------
						//UPDATE children
						//dmlRetval = this.updateChilden(appUser.getUser(), recordToValidate, listeHodeRecord, oppnrTurRecord, errMsg);
						
					}else{
						//create new
						logger.info(MainMaintenanceConstants.MODE_ADD);
						//dmlRetval = this.updateRecord(appUser.getUser(), recordToValidate, MainMaintenanceConstants.MODE_ADD, errMsg);
						//------------------------
						//now create all children
						//------------------------
						//Create Children
						//dmlRetval = this.createChilden(appUser.getUser(), recordToValidate, listeHodeRecord, oppnrTurRecord, errMsg);
						
					}
					
					//check for Update errors
					if( dmlRetval < 0){
						logger.info("[ERROR Validation] Record does not validate)");
						model.put(MainMaintenanceConstants.ASPECT_ERROR_MESSAGE, errMsg.toString());
						model.put(MainMaintenanceConstants.DOMAIN_RECORD, recordToValidate);
						//get list
						model.put("list", this.fetchList(appUser.getUser()));
					}else{
						//post successful update operations
						updateId = recordToValidate.getKosfsi();
						//refresh
						JsonMaintMainKodtsfSyparfRecord record = this.fetchRecord(appUser.getUser(), recordToValidate.getKosfsi());
						model.put(MainMaintenanceConstants.DOMAIN_RECORD, record);
						//post successful update operations
						successView = new ModelAndView("redirect:mainmaintenancesign_syfa60r.do?id=KODTSF");
					}
				}
					
			//DELETE	
			}else if(MainMaintenanceConstants.ACTION_DELETE.equals(action)){
				StringBuffer errMsg = new StringBuffer();
				int dmlRetval = 0;
				
				logger.info(MainMaintenanceConstants.MODE_DELETE);
				//dmlRetval = this.updateRecord(appUser.getUser(), recordToValidate, MainMaintenanceConstants.MODE_DELETE, errMsg);
				
				//check for Update errors
				if( dmlRetval < 0){
					logger.info("[ERROR Validation] Record does not validate)");
					model.put(MainMaintenanceConstants.ASPECT_ERROR_MESSAGE, errMsg.toString());
					model.put(MainMaintenanceConstants.DOMAIN_RECORD, recordToValidate);
					//get list
					model.put("list", this.fetchList(appUser.getUser()));
				}else{
					//post successful update operations
					successView = new ModelAndView("redirect:mainmaintenancesign_syfa60r.do?id=KODTSF");
				}
			}
			//--------------
			//Fetch record
			//--------------
			//JsonMaintMainKodtsfSyparfRecord record = new JsonMaintMainKodtsfSyparfRecord();
			//if(recordToValidate.getKosfsi()!=null && !"".equals(recordToValidate.getKosfsi())){
			//	record = this.fetchRecord(appUser.getUser(), recordToValidate.getKosfsi());
			//}
			//model.put(MainMaintenanceConstants.DOMAIN_RECORD, record);
			
			//populate model
			if(action==null || "".equals(action)){
				action = "doUpdate";
			}
			model.put("action", action);
			//model.put("avd", avd);
			model.put("updateId", updateId);
			successView.addObject(MainMaintenanceConstants.DOMAIN_MODEL , model);
			
			logger.info("Host via HttpServletRequest.getHeader('Host'): " + request.getHeader("Host"));
		    
			return successView;
			
		}
	}
	
	/**
	 * 
	 * @param request
	 * @return
	 */
	private JsonMaintMainKodtaKodthRecord bindChildListeHode (HttpServletRequest request){
		JsonMaintMainKodtaKodthRecord record = new JsonMaintMainKodtaKodthRecord();
		ServletRequestDataBinder binder = new ServletRequestDataBinder(record);
		binder.bind(request);
		return record;
	}
	/**
	 * 
	 * @param request
	 * @return
	 */
	private JsonMaintMainKodtaTellRecord bindChildOppnrTur (HttpServletRequest request){
		JsonMaintMainKodtaTellRecord record = new JsonMaintMainKodtaTellRecord();
		ServletRequestDataBinder binder = new ServletRequestDataBinder(record);
		binder.bind(request);
		return record;
	}
	
	
	/**
	 * 
	 * @param applicationUser
	 * @return
	 */
	private List<JsonMaintMainKodtsfSyparfRecord> fetchList(String applicationUser){
		
		String BASE_URL = MaintenanceMainUrlDataStore.MAINTENANCE_MAIN_BASE_SYFA60R_GET_LIST_URL;
		String urlRequestParams = "user=" + applicationUser;
		logger.info(Calendar.getInstance().getTime() + " CGI-start timestamp");
    	logger.info("URL: " + jsonDebugger.getBASE_URL_NoHostName(BASE_URL));
    	logger.info("URL PARAMS: " + urlRequestParams);
    	String jsonPayload = this.urlCgiProxyService.getJsonContent(BASE_URL, urlRequestParams);
    	//DEBUG
    	this.jsonDebugger.debugJsonPayload(jsonPayload, 1000);
    	//extract
    	List<JsonMaintMainKodtsfSyparfRecord> list = new ArrayList();
    	if(jsonPayload!=null){
			//lists
    		JsonMaintMainKodtsfSyparfContainer container = this.maintMainKodtsfSyparfService.getList(jsonPayload);
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

	private JsonMaintMainKodtsfSyparfRecord fetchRecord(String applicationUser, String id){
		JsonMaintMainKodtsfSyparfRecord record = new JsonMaintMainKodtsfSyparfRecord();
    	
		String BASE_URL = MaintenanceMainUrlDataStore.MAINTENANCE_MAIN_BASE_SYFA60R_GET_LIST_URL;
		String urlRequestParams = "user=" + applicationUser + "&kosfsi=" + id;
		
		logger.info(Calendar.getInstance().getTime() + " CGI-start timestamp");
    	logger.info("URL: " + jsonDebugger.getBASE_URL_NoHostName(BASE_URL));
    	logger.info("URL PARAMS: " + urlRequestParams);
    	String jsonPayload = this.urlCgiProxyService.getJsonContent(BASE_URL, urlRequestParams);
    	//DEBUG
    	this.jsonDebugger.debugJsonPayload(jsonPayload, 1000);
    	//extract
    	List<JsonMaintMainKodtsfSyparfRecord> list = new ArrayList();
    	
    	if(jsonPayload!=null){
			//lists
    		JsonMaintMainKodtsfSyparfContainer container = this.maintMainKodtsfSyparfService.getList(jsonPayload);
	        if(container!=null){
	        	list = (List)container.getList();
	        	for(JsonMaintMainKodtsfSyparfRecord tmp : list){
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
	
	private int updateRecord(String applicationUser, JsonMaintMainKodtsfSyparfRecord record, String mode, StringBuffer errMsg){
		int retval = 0;
		
		String BASE_URL = MaintenanceMainUrlDataStore.MAINTENANCE_MAIN_BASE_SYFA60R_DML_UPDATE_URL;
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
    		JsonMaintMainKodtsfSyparfContainer container = this.maintMainKodtsfSyparfService.doUpdate(jsonPayload);
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
	
	
	@Qualifier ("maintMainKodtsfSyparfService")
	private MaintMainKodtsfSyparfService maintMainKodtsfSyparfService;
	@Autowired
	@Required
	public void setMaintMainKodtsfSyparfService (MaintMainKodtsfSyparfService value){ this.maintMainKodtsfSyparfService = value; }
	public MaintMainKodtsfSyparfService getMaintMainKodtsfSyparfService(){ return this.maintMainKodtsfSyparfService; }
	
	
	
}

