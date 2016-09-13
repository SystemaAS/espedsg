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
import no.systema.z.main.maintenance.service.sad.MaintMainStandiService;
import no.systema.z.main.maintenance.service.MaintMainEdiiService;


import no.systema.z.main.maintenance.model.jsonjackson.dbtable.sad.JsonMaintMainStandiContainer;
import no.systema.z.main.maintenance.model.jsonjackson.dbtable.sad.JsonMaintMainStandiRecord;
import no.systema.z.main.maintenance.mapper.url.request.UrlRequestParameterMapper;
import no.systema.z.main.maintenance.validator.sad.MaintMainStandiValidator;
import no.systema.z.main.maintenance.util.manager.CodeDropDownMgr;

import no.systema.tvinn.sad.z.maintenance.main.service.MaintKodtvaService;
import no.systema.tvinn.sad.z.maintenance.sad.model.jsonjackson.dbtable.JsonMaintSadSadlRecord;
import no.systema.z.main.maintenance.service.MaintMainKodtaService;

/**
 * Gateway to the Main Maintenance Application
 * 
 * 
 * @author oscardelatorre
 * @date Aug 24, 2016
 * 
 * 	
 */

@Controller
public class MainMaintenanceAvdSadImportStandiController {
	private static final Logger logger = Logger.getLogger(MainMaintenanceAvdSadImportStandiController.class.getName());
	private ModelAndView loginView = new ModelAndView("login");
	private static final JsonDebugger jsonDebugger = new JsonDebugger();
	private UrlRequestParameterMapper urlRequestParameterMapper = new UrlRequestParameterMapper();
	private CodeDropDownMgr codeDropDownMgr = new CodeDropDownMgr();
	private DateTimeManager dateTimeMgr = new DateTimeManager();
	/**
	 * 
	 * @param user
	 * @param result
	 * @param request
	 * @return
	 * 
	 */
	@RequestMapping(value="mainmaintenanceavdsadimport_syftaaar.do", method={RequestMethod.GET, RequestMethod.POST })
	public ModelAndView mainmaintenanceavd_syfa14r(HttpSession session, HttpServletRequest request){
		ModelAndView successView = new ModelAndView("mainmaintenanceavdsadimport_syftaaar");
		SystemaWebUser appUser = (SystemaWebUser)session.getAttribute(AppConstants.SYSTEMA_WEB_USER_KEY);
		Map model = new HashMap();
		if(appUser==null){
			return this.loginView;
		}else{
			logger.info("Inside method: mainmaintenanceavdsadimport_syftaaar");
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
	
	@RequestMapping(value="mainmaintenanceavdsadimport_syftaaar_edit.do", method={RequestMethod.GET, RequestMethod.POST })
	public ModelAndView mainmaintenanceavdsadimport_syftaaar_edit(@ModelAttribute ("record") JsonMaintMainStandiRecord recordToValidate, BindingResult bindingResult, HttpSession session, HttpServletRequest request){
		ModelAndView successView = new ModelAndView("mainmaintenanceavdsadimport_syftaaar_edit");
		SystemaWebUser appUser = (SystemaWebUser)session.getAttribute(AppConstants.SYSTEMA_WEB_USER_KEY);
		Map model = new HashMap();
		String avd = request.getParameter("avd");
		String action = request.getParameter("action");
		String updateId = request.getParameter("updateId");
		
		if(appUser==null){
			return this.loginView;
		}else{
			logger.info("Inside method: mainmaintenanceavdsadimport_syftaaar_edit");
			logger.info("appUser user:" + appUser.getUser());
			logger.info("appUser lang:" + appUser.getUsrLang());
			logger.info("appUser userAS400:" + appUser.getUserAS400());
			boolean isValidOnUpdate = true;
			//--------------
			//UPDATE record
			//--------------
			if (MainMaintenanceConstants.ACTION_UPDATE.equals(action)){
				avd = recordToValidate.getSiavd();
				//Validate
				this. adjustSomeRecordValues(recordToValidate);
				
				MaintMainStandiValidator validator = new MaintMainStandiValidator();
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
						updateId = recordToValidate.getSiavd();
						
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
					successView = new ModelAndView("redirect:mainmaintenanceavdsadimport_syftaaar.do");
					
				}
			}
			//-------------
			//Fetch record
			//-------------
			if(isValidOnUpdate && (avd!=null && !"".equals(avd)) ){
				JsonMaintMainStandiRecord record = this.fetchRecord(appUser.getUser(), avd);
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
	 * @param model
	 * @param applicationUser
	 */
	private void populateDropDowns(Map model, String applicationUser){
		this.codeDropDownMgr.populateCurrencyCodesHtmlDropDownsSadImport(this.urlCgiProxyService, this.maintKodtvaService, model, applicationUser);
		this.codeDropDownMgr.populateAvdListHtmlDropDownsSadImport(this.urlCgiProxyService, this.maintMainKodtaService, model, applicationUser);
		
	}
	
	/**
	 * 
	 * @param applicationUser
	 * @return
	 */
	private List<JsonMaintMainStandiRecord> fetchList(String applicationUser){
		
		String BASE_URL = MaintenanceMainUrlDataStore.MAINTENANCE_MAIN_BASE_SYFTAAAR_GET_LIST_URL;
		String urlRequestParams = "user=" + applicationUser;
		logger.info(Calendar.getInstance().getTime() + " CGI-start timestamp");
    	logger.info("URL: " + jsonDebugger.getBASE_URL_NoHostName(BASE_URL));
    	logger.info("URL PARAMS: " + urlRequestParams);
    	String jsonPayload = this.urlCgiProxyService.getJsonContent(BASE_URL, urlRequestParams);
    	//DEBUG
    	this.jsonDebugger.debugJsonPayload(jsonPayload, 1000);
    	//extract
    	List<JsonMaintMainStandiRecord> list = new ArrayList();
    	if(jsonPayload!=null){
			//lists
    		JsonMaintMainStandiContainer container = this.maintMainStandiService.getList(jsonPayload);
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
	private JsonMaintMainStandiRecord fetchRecord(String applicationUser, String id){
		JsonMaintMainStandiRecord record = new JsonMaintMainStandiRecord();
    	
		String BASE_URL = MaintenanceMainUrlDataStore.MAINTENANCE_MAIN_BASE_SYFTAAAR_GET_LIST_URL;
		String urlRequestParams = "user=" + applicationUser + "&siavd=" + id;
		
		logger.info(Calendar.getInstance().getTime() + " CGI-start timestamp");
    	logger.info("URL: " + jsonDebugger.getBASE_URL_NoHostName(BASE_URL));
    	logger.info("URL PARAMS: " + urlRequestParams);
    	String jsonPayload = this.urlCgiProxyService.getJsonContent(BASE_URL, urlRequestParams);
    	//DEBUG
    	this.jsonDebugger.debugJsonPayload(jsonPayload, 1000);
    	//extract
    	List<JsonMaintMainStandiRecord> list = new ArrayList();
    	
    	if(jsonPayload!=null){
			//lists
    		JsonMaintMainStandiContainer container = this.maintMainStandiService.getList(jsonPayload);
	        if(container!=null){
	        	list = (List)container.getList();
	        	for(JsonMaintMainStandiRecord tmp : list){
	        		record = tmp;
	        		logger.info("sibel3:" + record.getSibel3());
	        		logger.info("sibel3NO:" + record.getSibel3NO());
	        		
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
	private int updateRecord(String applicationUser, JsonMaintMainStandiRecord record, String mode, StringBuffer errMsg){
		int retval = 0;
		
		String BASE_URL = MaintenanceMainUrlDataStore.MAINTENANCE_MAIN_BASE_SYFTAAAR_DML_UPDATE_URL;
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
    		JsonMaintMainStandiContainer container = this.maintMainStandiService.doUpdate(jsonPayload);
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
	 */
	private void adjustSomeRecordValues(JsonMaintMainStandiRecord recordToValidate){
		final String ZERO = "0";
		//-----------------
		//Decimal amounts
		//-----------------
		if(recordToValidate.getSibel1()!=null && !"".equals(recordToValidate.getSibel1())){
			String tmp = recordToValidate.getSibel1().replace(",", ".");
			recordToValidate.setSibel1(tmp);
		}else{
			recordToValidate.setSibel1(ZERO);	
		}
		if(recordToValidate.getSibel2()!=null && !"".equals(recordToValidate.getSibel2())){
			String tmp = recordToValidate.getSibel2().replace(",", ".");
			recordToValidate.setSibel2(tmp);
		}else{
			recordToValidate.setSibel2(ZERO);	
		}
		if(recordToValidate.getSibel3()!=null && !"".equals(recordToValidate.getSibel3())){
			String tmp = recordToValidate.getSibel3().replace(",", ".");
			recordToValidate.setSibel3(tmp);
		}else{
			recordToValidate.setSibel3(ZERO);	
		}
		if(recordToValidate.getSivku()!=null && !"".equals(recordToValidate.getSivku())){
			String tmp = recordToValidate.getSivku().replace(",", ".");
			recordToValidate.setSivku(tmp);
		}else{
			recordToValidate.setSivku(ZERO);
		}
		if(recordToValidate.getSias()!=null && !"".equals(recordToValidate.getSias())){
			String tmp = recordToValidate.getSias().replace(",", ".");
			recordToValidate.setSias(tmp);
		}else{
			recordToValidate.setSias(ZERO);
		}
		if(recordToValidate.getSibel8()!=null && !"".equals(recordToValidate.getSibel8())){
			String tmp = recordToValidate.getSibel8().replace(",", ".");
			recordToValidate.setSibel8(tmp);
		}else{
			recordToValidate.setSibel8(ZERO);
		}
		if(recordToValidate.getSibel9()!=null && !"".equals(recordToValidate.getSibel9())){
			String tmp = recordToValidate.getSibel9().replace(",", ".");
			recordToValidate.setSibel9(tmp);
		}else{
			recordToValidate.setSibel9(ZERO);
		}
		if(recordToValidate.getSibelb()!=null && !"".equals(recordToValidate.getSibelb())){
			String tmp = recordToValidate.getSibelb().replace(",", ".");
			recordToValidate.setSibelb(tmp);
		}else{
			recordToValidate.setSibelb(ZERO);
		}
		if(recordToValidate.getSibelr()!=null && !"".equals(recordToValidate.getSibelr())){
			String tmp = recordToValidate.getSibelr().replace(",", ".");
			recordToValidate.setSibelr(tmp);
		}else{
			recordToValidate.setSibelr(ZERO);
		}
		//----------
		//Dates
		//----------
		if(recordToValidate.getSidt()!=null && !"".equals(recordToValidate.getSidt()) ){
			if(recordToValidate.getSidt().length()< 6){
				recordToValidate.setSidt(this.dateTimeMgr.getCurrentDate_ISO());
			}
		}else{
			recordToValidate.setSidt(this.dateTimeMgr.getCurrentDate_ISO());
		}
			
		
	}
	
	//Wired - SERVICES
	@Qualifier ("urlCgiProxyService")
	private UrlCgiProxyService urlCgiProxyService;
	@Autowired
	@Required
	public void setUrlCgiProxyService (UrlCgiProxyService value){ this.urlCgiProxyService = value; }
	public UrlCgiProxyService getUrlCgiProxyService(){ return this.urlCgiProxyService; }
	
	
	@Qualifier ("maintMainStandiService")
	private MaintMainStandiService maintMainStandiService;
	@Autowired
	@Required
	public void setMaintMainStandiService (MaintMainStandiService value){ this.maintMainStandiService = value; }
	public MaintMainStandiService getMaintMainStandiService(){ return this.maintMainStandiService; }
	
	
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

