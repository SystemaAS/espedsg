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


//application imports
import no.systema.main.model.SystemaWebUser;
import no.systema.main.util.AppConstants;
import no.systema.main.util.JsonDebugger;
import no.systema.main.util.StringManager;
import no.systema.main.service.UrlCgiProxyService;
//models
import no.systema.z.main.maintenance.url.store.MaintenanceMainUrlDataStore;
import no.systema.z.main.maintenance.util.MainMaintenanceConstants;
import no.systema.z.main.maintenance.service.MaintMainKodtvKodtwService;
import no.systema.z.main.maintenance.service.MaintMainKodtpUtskrsService;
import no.systema.z.main.maintenance.model.jsonjackson.dbtable.JsonMaintMainKodtvKodtwContainer;
import no.systema.z.main.maintenance.model.jsonjackson.dbtable.JsonMaintMainKodtvKodtwRecord;
import no.systema.z.main.maintenance.model.jsonjackson.dbtable.JsonMaintMainKodtpUtskrsContainer;
import no.systema.z.main.maintenance.model.jsonjackson.dbtable.JsonMaintMainKodtpUtskrsRecord;

import no.systema.z.main.maintenance.mapper.url.request.UrlRequestParameterMapper;
import no.systema.z.main.maintenance.validator.MaintMainKodtvKodtwValidator;

/**
 * Gateway to the Main Maintenance Application
 * 
 * 
 * @author oscardelatorre
 * @date Aug 05, 2016
 * 
 * 	
 */

@Controller
public class MainMaintenanceAvdFastDataSyfa28Controller {
	private static final Logger logger = Logger.getLogger(MainMaintenanceAvdFastDataSyfa28Controller.class.getName());
	private ModelAndView loginView = new ModelAndView("login");
	private static final JsonDebugger jsonDebugger = new JsonDebugger();
	private UrlRequestParameterMapper urlRequestParameterMapper = new UrlRequestParameterMapper();
	private StringManager strManager = new StringManager();
	/**
	 * 
	 * @param user
	 * @param result
	 * @param request
	 * @return
	 * 
	 */
	/* N/A TODO
	@RequestMapping(value="mainmaintenanceavd_syfa28r.do", method={RequestMethod.GET, RequestMethod.POST })
	public ModelAndView mainmaintenanceavd_syfa14r(HttpSession session, HttpServletRequest request){
		ModelAndView successView = new ModelAndView("mainmaintenanceavd_syfa28r");
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
	*/
	/**
	 * 
	 * @param session
	 * @param request
	 * @return
	 */
	@RequestMapping(value="mainmaintenanceavd_syfa28r_edit.do", method={RequestMethod.GET, RequestMethod.POST })
	public ModelAndView mainmaintenanceavd_syfa14r_edit(@ModelAttribute ("record") JsonMaintMainKodtvKodtwRecord recordToValidate, BindingResult bindingResult, HttpSession session, HttpServletRequest request){
		ModelAndView successView = new ModelAndView("mainmaintenanceavd_syfa28r_edit");
		SystemaWebUser appUser = (SystemaWebUser)session.getAttribute(AppConstants.SYSTEMA_WEB_USER_KEY);
		Map model = new HashMap();
		String avd = request.getParameter("avd");
		String action = request.getParameter("action");
		String updateId = request.getParameter("updateId");
		
		
		if(appUser==null){
			return this.loginView;
		}else{
			appUser.setActiveMenu("INIT");
			logger.info("Inside method: mainmaintenanceavd_syfa28r_edit");
			logger.info("appUser user:" + appUser.getUser());
			logger.info("appUser lang:" + appUser.getUsrLang());
			logger.info("appUser userAS400:" + appUser.getUserAS400());
			
			//--------------
			//UPDATE record
			//--------------
			if (MainMaintenanceConstants.ACTION_UPDATE.equals(action)){
				avd = recordToValidate.getKovavd();
				
				//Validate
				MaintMainKodtvKodtwValidator validator = new MaintMainKodtvKodtwValidator();
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
					this.populateKovxxxField(recordToValidate);
					logger.info(recordToValidate.getKovxxx());
					if(updateId!=null && !"".equals(updateId)){
						//update
						logger.info(MainMaintenanceConstants.MODE_UPDATE);
						dmlRetval = this.updateRecord(appUser.getUser(), recordToValidate, MainMaintenanceConstants.MODE_UPDATE, errMsg);
					}else{
						//create new
						logger.info(MainMaintenanceConstants.MODE_ADD);
						dmlRetval = this.updateRecord(appUser.getUser(), recordToValidate, MainMaintenanceConstants.MODE_ADD, errMsg);
						
					}
					
					//check for Update errors
					if( dmlRetval < 0){
						logger.info("[ERROR Validation] Record does not validate)");
						model.put(MainMaintenanceConstants.ASPECT_ERROR_MESSAGE, errMsg.toString());
						model.put(MainMaintenanceConstants.DOMAIN_RECORD, recordToValidate);
					}else{
						//post successful update operations
						updateId = recordToValidate.getKovavd();
					}
				}
				model.put(MainMaintenanceConstants.DOMAIN_RECORD, recordToValidate);
				
				
			//DELETE	
			}else if(MainMaintenanceConstants.ACTION_DELETE.equals(action)){
				/*
				StringBuffer errMsg = new StringBuffer();
				int dmlRetval = 0;
				
				logger.info(MainMaintenanceConstants.MODE_DELETE);
				dmlRetval = this.updateRecord(appUser.getUser(), recordToValidate, MainMaintenanceConstants.MODE_DELETE, errMsg);
				
				//check for Update errors
				if( dmlRetval < 0){
					logger.info("[ERROR Validation] Record does not validate)");
					model.put(MainMaintenanceConstants.ASPECT_ERROR_MESSAGE, errMsg.toString());
					model.put(MainMaintenanceConstants.DOMAIN_RECORD, recordToValidate);
				}else{
					//post successful update operations
					successView = new ModelAndView("redirect:mainmaintenanceavd_syfa28r_TODO.do?id=KODTA");
					
				}
				*/
			}else{
				//---------------------------
				//Fetch record and child list
				//---------------------------
				JsonMaintMainKodtvKodtwRecord record = this.fetchRecord(appUser.getUser(), avd);
				record.setChildList(this.fetchChildList(appUser.getUser(), avd));
				//DEBUG
				/*for (JsonMaintMainKodtpUtskrsRecord cRecord : record.getChildList()){
					logger.info(cRecord.getKoplnr());
				}*/
				model.put(MainMaintenanceConstants.DOMAIN_RECORD, record);
				
			}
			
			
			//populate model
			if(action==null || "".equals(action)){
				action = "doUpdate";
			}
			model.put("action", action);
			model.put("avd", avd);
			model.put("updateId", updateId);
			successView.addObject(MainMaintenanceConstants.DOMAIN_MODEL , model);
			
			logger.info("Host via HttpServletRequest.getHeader('Host'): " + request.getHeader("Host"));
		    
			return successView;
			
		}
	}

	/**
	 * We must populate the kovxxx field from all html fields since this field saves values in different positions
	 * 
	 * @param recordToValidate
	 */
	private void populateKovxxxField(JsonMaintMainKodtvKodtwRecord recordToValidate){
		String SPACE = " ";
		//(0, 1-char)
		if(recordToValidate.getKovxxx0()!=null && !"".equals(recordToValidate.getKovxxx0())){
			String tmp = recordToValidate.getKovxxx0();
			recordToValidate.setKovxxx(tmp);
		}else{
			recordToValidate.setKovxxx(SPACE);
		}
		//(1, 2-chars)
		if(recordToValidate.getKovxxx1()!=null && !"".equals(recordToValidate.getKovxxx1())){
			String tmp = recordToValidate.getKovxxx() + recordToValidate.getKovxxx1();
			recordToValidate.setKovxxx(strManager.paddingString(tmp, 2));
		}else{
			recordToValidate.setKovxxx(recordToValidate.getKovxxx() + SPACE + SPACE);
		}
		//(3, 1-char)
		if(recordToValidate.getKovxxx3()!=null && !"".equals(recordToValidate.getKovxxx3())){
			String tmp = recordToValidate.getKovxxx() + recordToValidate.getKovxxx3();
			recordToValidate.setKovxxx(strManager.paddingString(tmp, 1));
		}else{
			recordToValidate.setKovxxx(recordToValidate.getKovxxx() + SPACE);
		}
		//(4, 1-char)
		if(recordToValidate.getKovxxx4()!=null && !"".equals(recordToValidate.getKovxxx4())){
			String tmp = recordToValidate.getKovxxx() + recordToValidate.getKovxxx4();
			recordToValidate.setKovxxx(strManager.paddingString(tmp, 1));
		}else{
			recordToValidate.setKovxxx(recordToValidate.getKovxxx() + SPACE);
		}
		//(5, 2-char)
		if(recordToValidate.getKovxxx5()!=null && !"".equals(recordToValidate.getKovxxx5())){
			String tmp = recordToValidate.getKovxxx() + recordToValidate.getKovxxx5();
			recordToValidate.setKovxxx(strManager.paddingString(tmp, 2));
		}else{
			recordToValidate.setKovxxx(recordToValidate.getKovxxx() + SPACE + SPACE);
		}
	}
	
	/**
	 * 
	 * @param applicationUser
	 * @return
	 */
	/* N/A TODO
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
	*/
	
	/**
	 * 
	 * @param applicationUser
	 * @param id
	 * @return
	 */
	private JsonMaintMainKodtvKodtwRecord fetchRecord(String applicationUser, String id){
		JsonMaintMainKodtvKodtwRecord record = new JsonMaintMainKodtvKodtwRecord();
    	
		String BASE_URL = MaintenanceMainUrlDataStore.MAINTENANCE_MAIN_BASE_SYFA28R_GET_LIST_URL;
		String urlRequestParams = "user=" + applicationUser + "&kovavd=" + id;
		
		logger.info(Calendar.getInstance().getTime() + " CGI-start timestamp");
    	logger.info("URL: " + jsonDebugger.getBASE_URL_NoHostName(BASE_URL));
    	logger.info("URL PARAMS: " + urlRequestParams);
    	String jsonPayload = this.urlCgiProxyService.getJsonContent(BASE_URL, urlRequestParams);
    	//DEBUG
    	this.jsonDebugger.debugJsonPayload(jsonPayload, 1000);
    	//extract
    	List<JsonMaintMainKodtvKodtwRecord> list = new ArrayList();
    	
    	if(jsonPayload!=null){
			//lists
    		JsonMaintMainKodtvKodtwContainer container = this.maintMainKodtvKodtwService.getList(jsonPayload);
	        if(container!=null){
	        	list = (List)container.getList();
	        	for(JsonMaintMainKodtvKodtwRecord tmp : list){
	        		record = tmp;
	        	}
	        }
    	}
    	return record;
    	
	}
	/**
	 * Gets the children list of the child section (FASTE DATA Del-2)
	 * 
	 * @param applicationUser
	 * @param id
	 * @return
	 */
	private List<JsonMaintMainKodtpUtskrsRecord> fetchChildList(String applicationUser, String id){
		List <JsonMaintMainKodtpUtskrsRecord> list = new ArrayList<JsonMaintMainKodtpUtskrsRecord>();
    	
		String BASE_URL = MaintenanceMainUrlDataStore.MAINTENANCE_MAIN_BASE_SYFA28R_GET_CHILDREN_LIST_URL;
		String urlRequestParams = "user=" + applicationUser + "&kopavd=" + id;
		
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
	        	list = (List)container.getList();
	        }
    	}
    	return list;
    	
	}
	
	/**
	 * 
	 * @param applicationUser
	 * @param record
	 * @param mode
	 * @param errMsg
	 * @return
	 */
	private int updateRecord(String applicationUser, JsonMaintMainKodtvKodtwRecord record, String mode, StringBuffer errMsg){
		int retval = 0;
		
		String BASE_URL = MaintenanceMainUrlDataStore.MAINTENANCE_MAIN_BASE_SYFA28R_DML_UPDATE_URL;
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
    		JsonMaintMainKodtvKodtwContainer container = this.maintMainKodtvKodtwService.doUpdate(jsonPayload);
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
	
	
	@Qualifier ("maintMainKodtvKodtwService")
	private MaintMainKodtvKodtwService maintMainKodtvKodtwService;
	@Autowired
	@Required
	public void setMaintMainKodtvKodtwService (MaintMainKodtvKodtwService value){ this.maintMainKodtvKodtwService = value; }
	public MaintMainKodtvKodtwService getMaintMainKodtvKodtwService(){ return this.maintMainKodtvKodtwService; }
	
	
	@Qualifier ("maintMainKodtpUtskrsService")
	private MaintMainKodtpUtskrsService maintMainKodtpUtskrsService;
	@Autowired
	@Required
	public void setMaintMainKodtpUtskrsService (MaintMainKodtpUtskrsService value){ this.maintMainKodtpUtskrsService = value; }
	public MaintMainKodtpUtskrsService getMaintMainKodtpUtskrsService(){ return this.maintMainKodtpUtskrsService; }
	
	

		
}

