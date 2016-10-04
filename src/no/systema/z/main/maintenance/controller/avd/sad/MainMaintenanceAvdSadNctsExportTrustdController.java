package no.systema.z.main.maintenance.controller.avd.sad;

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
import no.systema.main.util.DateTimeManager;

//models
import no.systema.z.main.maintenance.url.store.MaintenanceMainUrlDataStore;
import no.systema.z.main.maintenance.util.MainMaintenanceConstants;
import no.systema.z.main.maintenance.service.sad.MaintMainTrustdService;
import no.systema.z.main.maintenance.service.sad.MaintMainTrustdfvService;
import no.systema.z.main.maintenance.service.MaintMainEdiiService;

import no.systema.z.main.maintenance.model.jsonjackson.dbtable.JsonMaintMainKodtaKodthRecord;
import no.systema.z.main.maintenance.model.jsonjackson.dbtable.sad.JsonMaintMainTrustdContainer;
import no.systema.z.main.maintenance.model.jsonjackson.dbtable.sad.JsonMaintMainTrustdRecord;
import no.systema.z.main.maintenance.model.jsonjackson.dbtable.sad.JsonMaintMainTrustdfvContainer;
import no.systema.z.main.maintenance.model.jsonjackson.dbtable.sad.JsonMaintMainTrustdfvRecord;

import no.systema.z.main.maintenance.mapper.url.request.UrlRequestParameterMapper;
import no.systema.z.main.maintenance.validator.sad.MaintMainTrustdValidator;
import no.systema.z.main.maintenance.util.manager.CodeDropDownMgr;

import no.systema.tvinn.sad.util.TvinnSadDateFormatter;
import no.systema.tvinn.sad.z.maintenance.main.service.MaintKodtvaService;
import no.systema.z.main.maintenance.service.MaintMainKodtaService;

/**
 * Gateway to the Main Maintenance Application
 * 
 * 
 * @author oscardelatorre
 * @date Sep 30, 2016
 * 
 * 	
 */

@Controller
public class MainMaintenanceAvdSadNctsExportTrustdController {
	private static final Logger logger = Logger.getLogger(MainMaintenanceAvdSadNctsExportTrustdController.class.getName());
	private ModelAndView loginView = new ModelAndView("login");
	private static final JsonDebugger jsonDebugger = new JsonDebugger();
	private UrlRequestParameterMapper urlRequestParameterMapper = new UrlRequestParameterMapper();
	private CodeDropDownMgr codeDropDownMgr = new CodeDropDownMgr();
	private DateTimeManager dateTimeMgr = new DateTimeManager();
	private TvinnSadDateFormatter dateFormatter = new TvinnSadDateFormatter();
	/**
	 * 
	 * @param user
	 * @param result
	 * @param request
	 * @return
	 * 
	 */
	@RequestMapping(value="mainmaintenanceavdsadnctsexport_tr003r.do", method={RequestMethod.GET, RequestMethod.POST })
	public ModelAndView mainmaintenanceavdsadnctsimport_tr003r (HttpSession session, HttpServletRequest request){
		ModelAndView successView = new ModelAndView("mainmaintenanceavdsadnctsexport_tr003r");
		SystemaWebUser appUser = (SystemaWebUser)session.getAttribute(AppConstants.SYSTEMA_WEB_USER_KEY);
		Map model = new HashMap();
		if(appUser==null){
			return this.loginView;
		}else{
			logger.info("Inside method: mainmaintenanceavdsadnctsexport_tr003r");
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
	
	@RequestMapping(value="mainmaintenanceavdsadnctsexport_tr003r_edit.do", method={RequestMethod.GET, RequestMethod.POST })
	public ModelAndView mainmaintenanceavdsadnctsimport_tr003_edit(@ModelAttribute ("record") JsonMaintMainTrustdRecord recordToValidate, BindingResult bindingResult, HttpSession session, HttpServletRequest request){
		ModelAndView successView = new ModelAndView("mainmaintenanceavdsadnctsexport_tr003r_edit");
		SystemaWebUser appUser = (SystemaWebUser)session.getAttribute(AppConstants.SYSTEMA_WEB_USER_KEY);
		Map model = new HashMap();
		String avd = request.getParameter("avd");
		String action = request.getParameter("action");
		String updateId = request.getParameter("updateId");
		
		if(appUser==null){
			return this.loginView;
		}else{
			logger.info("Inside method: mainmaintenanceavdsadnctsexport_tr003_edit");
			logger.info("appUser user:" + appUser.getUser());
			logger.info("appUser lang:" + appUser.getUsrLang());
			logger.info("appUser userAS400:" + appUser.getUserAS400());
			boolean isValidOnUpdate = true;
			//--------------
			//UPDATE record
			//--------------
			if (MainMaintenanceConstants.ACTION_UPDATE.equals(action)){
				avd = recordToValidate.getThavd();
				//bind child record
				JsonMaintMainTrustdfvRecord sikkerhedChildRecord = this.bindChildSikkerhed(request);
				//Adjust
				this. adjustSomeRecordValues(recordToValidate, sikkerhedChildRecord);
				recordToValidate.setSikkerhedChildRecord(sikkerhedChildRecord);
				//Validate
				MaintMainTrustdValidator validator = new MaintMainTrustdValidator();
				validator.validate(recordToValidate, bindingResult);
				if(bindingResult.hasErrors()){
					//ERRORS
					logger.info("[ERROR Validation] Record does not validate)");
					//model.put("dbTable", dbTable);
					model.put(MainMaintenanceConstants.DOMAIN_RECORD, recordToValidate);
					isValidOnUpdate = false;
				}else{
					
					//Update table
					StringBuffer errMsg = new StringBuffer();
					int dmlRetval = 0;
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
						isValidOnUpdate = false;
					}else{
						//post successful update operations
						updateId = recordToValidate.getThavd();
						
					}
					
				}
				model.put(MainMaintenanceConstants.DOMAIN_RECORD, recordToValidate);
				
				
			//DELETE	
			}else if(MainMaintenanceConstants.ACTION_DELETE.equals(action)){
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
					successView = new ModelAndView("redirect:mainmaintenanceavdsadnctsimport_tr003r.do");
					
				}
			}
			//-------------
			//Fetch record
			//-------------
			if(isValidOnUpdate && (avd!=null && !"".equals(avd)) ){
				JsonMaintMainTrustdRecord record = this.fetchRecord(appUser.getUser(), avd);
				JsonMaintMainTrustdfvRecord childRecord = this.fetchChildRecordSikkerhed(appUser.getUser(), avd);
				record.setSikkerhedChildRecord(childRecord);
				model.put(MainMaintenanceConstants.DOMAIN_RECORD, record);
			}
			
			//populate model
			if(action==null || "".equals(action)){
				action = "doUpdate";
			}
			this.populateDropDowns(model, appUser.getUser());
			model.put("action", action);
			model.put("avd", avd);
			model.put("updateId", updateId);
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
	private List<JsonMaintMainTrustdRecord> fetchList(String applicationUser){
		
		String BASE_URL = MaintenanceMainUrlDataStore.MAINTENANCE_MAIN_BASE_TR003R_GET_LIST_URL;
		String urlRequestParams = "user=" + applicationUser;
		logger.info(Calendar.getInstance().getTime() + " CGI-start timestamp");
    	logger.info("URL: " + jsonDebugger.getBASE_URL_NoHostName(BASE_URL));
    	logger.info("URL PARAMS: " + urlRequestParams);
    	String jsonPayload = this.urlCgiProxyService.getJsonContent(BASE_URL, urlRequestParams);
    	//DEBUG
    	this.jsonDebugger.debugJsonPayload(jsonPayload, 1000);
    	//extract
    	List<JsonMaintMainTrustdRecord> list = new ArrayList();
    	if(jsonPayload!=null){
			//lists
    		JsonMaintMainTrustdContainer container = this.maintMainTrustdService.getList(jsonPayload);
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
	private JsonMaintMainTrustdRecord fetchRecord(String applicationUser, String id){
		JsonMaintMainTrustdRecord record = new JsonMaintMainTrustdRecord();
    	
		String BASE_URL = MaintenanceMainUrlDataStore.MAINTENANCE_MAIN_BASE_TR003R_GET_LIST_URL;
		String urlRequestParams = "user=" + applicationUser + "&thavd=" + id;
		
		logger.info(Calendar.getInstance().getTime() + " CGI-start timestamp");
    	logger.info("URL: " + jsonDebugger.getBASE_URL_NoHostName(BASE_URL));
    	logger.info("URL PARAMS: " + urlRequestParams);
    	String jsonPayload = this.urlCgiProxyService.getJsonContent(BASE_URL, urlRequestParams);
    	//DEBUG
    	this.jsonDebugger.debugJsonPayload(jsonPayload, 1000);
    	//extract
    	List<JsonMaintMainTrustdRecord> list = new ArrayList();
    	
    	if(jsonPayload!=null){
			//lists
    		JsonMaintMainTrustdContainer container = this.maintMainTrustdService.getList(jsonPayload);
	        if(container!=null){
	        	list = (List)container.getList();
	        	for(JsonMaintMainTrustdRecord tmp : list){
	        		record = tmp;

	        	}
	        }
    	}
    	return record;
    	
	}
	/**
	 * Sikkerhed child record 
	 * @param applicationUser
	 * @param id
	 * @return
	 */
	private JsonMaintMainTrustdfvRecord fetchChildRecordSikkerhed(String applicationUser, String id){
		JsonMaintMainTrustdfvRecord record = new JsonMaintMainTrustdfvRecord();
    	
		String BASE_URL = MaintenanceMainUrlDataStore.MAINTENANCE_MAIN_BASE_TR003fvR_GET_LIST_URL;
		String urlRequestParams = "user=" + applicationUser + "&thavd=" + id;
		
		logger.info(Calendar.getInstance().getTime() + " CGI-start timestamp");
    	logger.info("URL: " + jsonDebugger.getBASE_URL_NoHostName(BASE_URL));
    	logger.info("URL PARAMS: " + urlRequestParams);
    	String jsonPayload = this.urlCgiProxyService.getJsonContent(BASE_URL, urlRequestParams);
    	//DEBUG
    	this.jsonDebugger.debugJsonPayload(jsonPayload, 1000);
    	//extract
    	List<JsonMaintMainTrustdfvRecord> list = new ArrayList();
    	
    	if(jsonPayload!=null){
			//lists
    		JsonMaintMainTrustdfvContainer container = this.maintMainTrustdfvService.getList(jsonPayload);
	        if(container!=null){
	        	list = (List)container.getList();
	        	for(JsonMaintMainTrustdfvRecord tmp : list){
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
	private int updateRecord(String applicationUser, JsonMaintMainTrustdRecord record, String mode, StringBuffer errMsg){
		int retval = 0;
		
		String BASE_URL = MaintenanceMainUrlDataStore.MAINTENANCE_MAIN_BASE_TR003R_DML_UPDATE_URL;
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
    		JsonMaintMainTrustdContainer container = this.maintMainTrustdService.doUpdate(jsonPayload);
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
	/**
	 * 
	 * @param recordToValidate
	 * @param sikkerhedChildRecord
	 */
	private void adjustSomeRecordValues(JsonMaintMainTrustdRecord recordToValidate, JsonMaintMainTrustdfvRecord sikkerhedChildRecord){
		//----------
		//Dates
		//----------
		if(recordToValidate.getThddt()!=null && !"".equals(recordToValidate.getThddt()) ){
			if(recordToValidate.getThddt().length()== 6){
				recordToValidate.setThddt(dateFormatter.convertToDate_ISO(recordToValidate.getThddt()));
			}
		}
		
		if(sikkerhedChildRecord.getThdta()!=null && !"".equals(sikkerhedChildRecord.getThdta()) ){
			if(sikkerhedChildRecord.getThdta().length()== 6){
				sikkerhedChildRecord.setThdta(dateFormatter.convertToDate_ISO(sikkerhedChildRecord.getThdta()));
			}
		}
		
	}
	
	/**
	 * 
	 * @param model
	 * @param applicationUser
	 */
	private void populateDropDowns(Map model, String applicationUser){
		this.codeDropDownMgr.populateCurrencyCodesHtmlDropDownsSad(this.urlCgiProxyService, this.maintKodtvaService, model, applicationUser);
		this.codeDropDownMgr.populateAvdListHtmlDropDownsSad(this.urlCgiProxyService, this.maintMainKodtaService, model, applicationUser, "nealist");
		
	}
	
	/**
	 * 
	 * @param request
	 * @return
	 */
	private JsonMaintMainTrustdfvRecord bindChildSikkerhed (HttpServletRequest request){
		JsonMaintMainTrustdfvRecord record = new JsonMaintMainTrustdfvRecord();
		ServletRequestDataBinder binder = new ServletRequestDataBinder(record);
		binder.bind(request);
		return record;
	}
	
	
	//Wired - SERVICES
	@Qualifier ("urlCgiProxyService")
	private UrlCgiProxyService urlCgiProxyService;
	@Autowired
	@Required
	public void setUrlCgiProxyService (UrlCgiProxyService value){ this.urlCgiProxyService = value; }
	public UrlCgiProxyService getUrlCgiProxyService(){ return this.urlCgiProxyService; }
	
	
	@Qualifier ("maintMainTrustdService")
	private MaintMainTrustdService maintMainTrustdService;
	@Autowired
	@Required
	public void setMaintMainTrustdService (MaintMainTrustdService value){ this.maintMainTrustdService = value; }
	public MaintMainTrustdService getMaintMainTrustdService(){ return this.maintMainTrustdService; }
	
	@Qualifier ("maintMainTrustdfvService")
	private MaintMainTrustdfvService maintMainTrustdfvService;
	@Autowired
	@Required
	public void setMaintMainTrustdfvService (MaintMainTrustdfvService value){ this.maintMainTrustdfvService = value; }
	public MaintMainTrustdfvService getMaintMainTrustdfvService(){ return this.maintMainTrustdfvService; }
	
	
	@Qualifier ("maintKodtvaService")
	private MaintKodtvaService maintKodtvaService;
	@Autowired
	@Required
	public void setMaintKodtvaService (MaintKodtvaService value){ this.maintKodtvaService = value; }
	public MaintKodtvaService getMaintKodtvaService(){ return this.maintKodtvaService; }
	
	
	@Qualifier ("maintMainKodtaService")
	private MaintMainKodtaService maintMainKodtaService;
	@Autowired
	@Required
	public void setMaintMainKodtaService (MaintMainKodtaService value){ this.maintMainKodtaService = value; }
	public MaintMainKodtaService getMaintMainKodtaService(){ return this.maintMainKodtaService; }
	
	
	@Qualifier ("maintMainEdiiService")
	private MaintMainEdiiService maintMainEdiiService;
	@Autowired
	@Required
	public void setMaintMainEdiiService (MaintMainEdiiService value){ this.maintMainEdiiService = value; }
	public MaintMainEdiiService getMaintMainEdiiService(){ return this.maintMainEdiiService; }
	

}

