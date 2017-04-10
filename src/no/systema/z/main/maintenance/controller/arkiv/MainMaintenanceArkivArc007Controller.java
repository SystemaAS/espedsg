package no.systema.z.main.maintenance.controller.arkiv;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Required;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.ServletRequestDataBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import no.systema.jservices.common.dao.ArktxtDao;
import no.systema.jservices.common.dto.SyparfDto;
import no.systema.jservices.common.json.JsonDtoContainer;
import no.systema.jservices.common.json.JsonReader;
//application imports
import no.systema.main.model.SystemaWebUser;
import no.systema.main.service.UrlCgiProxyService;
import no.systema.main.util.AppConstants;
import no.systema.main.util.JsonDebugger;
import no.systema.z.main.maintenance.mapper.url.request.UrlRequestParameterMapper;
import no.systema.z.main.maintenance.model.jsonjackson.dbtable.JsonMaintMainFirmContainer;
import no.systema.z.main.maintenance.model.jsonjackson.dbtable.JsonMaintMainFirmRecord;
import no.systema.z.main.maintenance.model.jsonjackson.dbtable.JsonMaintMainKodtaContainer;
import no.systema.z.main.maintenance.model.jsonjackson.dbtable.JsonMaintMainKodtaKodthContainer;
import no.systema.z.main.maintenance.model.jsonjackson.dbtable.JsonMaintMainKodtaKodthRecord;
import no.systema.z.main.maintenance.model.jsonjackson.dbtable.JsonMaintMainKodtaRecord;
import no.systema.z.main.maintenance.model.jsonjackson.dbtable.JsonMaintMainKodtaTellContainer;
import no.systema.z.main.maintenance.model.jsonjackson.dbtable.JsonMaintMainKodtaTellRecord;
import no.systema.z.main.maintenance.model.jsonjackson.dbtable.JsonMaintMainKodtvKodtwRecord;
import no.systema.z.main.maintenance.service.MaintMainFirmService;
import no.systema.z.main.maintenance.service.MaintMainKodtaKodthService;
import no.systema.z.main.maintenance.service.MaintMainKodtaService;
import no.systema.z.main.maintenance.service.MaintMainKodtaTellService;
import no.systema.z.main.maintenance.service.MaintMainKodtvKodtwService;
//models
import no.systema.z.main.maintenance.url.store.MaintenanceMainUrlDataStore;
import no.systema.z.main.maintenance.util.MainMaintenanceConstants;
import no.systema.z.main.maintenance.validator.MaintMainKodtaValidator;


/**
 * Arkiv - Vedlikehold arkiv dokumenter
 * 
 * 
 * @author Fredrik Möller
 * @date Mar 6, 2017
 * 
 * 	
 */

@Controller
public class MainMaintenanceArkivArc007Controller {
	private static final Logger logger = Logger.getLogger(MainMaintenanceArkivArc007Controller.class.getName());
	private ModelAndView loginView = new ModelAndView("login");
	private static final JsonDebugger jsonDebugger = new JsonDebugger();
	private UrlRequestParameterMapper urlRequestParameterMapper = new UrlRequestParameterMapper();
	
	@RequestMapping(value="mainmaintenancearkiv_arc007.do", method={RequestMethod.GET, RequestMethod.POST })
	public ModelAndView mainmaintenanceavd_arc007(HttpSession session, HttpServletRequest request){
		ModelAndView successView = new ModelAndView("mainmaintenancearkiv_arc007");
		SystemaWebUser appUser = (SystemaWebUser)session.getAttribute(AppConstants.SYSTEMA_WEB_USER_KEY);
		Map model = new HashMap();
		if(appUser==null){
			return this.loginView;
		}else{
			logger.info("Inside method: mainmaintenancearkiv_arc007");
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

	//TODO - all !!
	@RequestMapping(value="mainmaintenancearkiv_arc007_edit.do", method={RequestMethod.GET, RequestMethod.POST })
	public ModelAndView mainmaintenanceavd_arc007_edit(@ModelAttribute ("record") ArktxtDao recordToValidate, BindingResult bindingResult, HttpSession session, HttpServletRequest request){
		ModelAndView successView = new ModelAndView("mainmaintenancearkiv_arc007_edit");
		SystemaWebUser appUser = (SystemaWebUser)session.getAttribute(AppConstants.SYSTEMA_WEB_USER_KEY);
		Map model = new HashMap();
		String artype = request.getParameter("artype");
		String action = request.getParameter("action");
		String updateId = request.getParameter("updateId");
		
		
		if(appUser==null){
			return this.loginView;
		}else{
			appUser.setActiveMenu("INIT");
			logger.info("Inside method: mainmaintenancearkiv_arc007_edit");
			logger.info("appUser user:" + appUser.getUser());
			logger.info("appUser lang:" + appUser.getUsrLang());
			logger.info("appUser userAS400:" + appUser.getUserAS400());
			
			appUser.setActiveMenu(SystemaWebUser.ACTIVE_MENU_MAIN_MAINTENANCE);
			session.setAttribute(MainMaintenanceConstants.ACTIVE_URL_RPG_MAIN_MAINTENANCE, MainMaintenanceConstants.ACTIVE_URL_RPG_INITVALUE); 
			//--------------
			//UPDATE record
			//--------------
			if (MainMaintenanceConstants.ACTION_UPDATE.equals(action)){
				
				//Validate
				MaintMainKodtaValidator validator = new MaintMainKodtaValidator();
				validator.validate(recordToValidate, bindingResult);
				if(bindingResult.hasErrors()){
					logger.info("[ERROR Validation] Record does not validate)");
					model.put(MainMaintenanceConstants.DOMAIN_RECORD, recordToValidate);
					
				}else{
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
					}else{
						//post successful update operations
						updateId = recordToValidate.getArtype();
						//refresh
						ArktxtDao record = this.fetchRecord(appUser.getUser(), recordToValidate.getArtype());
						model.put(MainMaintenanceConstants.DOMAIN_RECORD, record);
					}
				}
				
				
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
					successView = new ModelAndView("redirect:mainmaintenancearkiv_arc007.do?id=ARKTXT");
					
				}
			}else{
				//-------------
				//Fetch record
				//-------------
				ArktxtDao record = new ArktxtDao();
				if(artype!=null && !"".equals(artype)){
					//get record including children records (listehode & oppdnrTur)
					record = this.fetchRecord(appUser.getUser(), artype);
					
				}
				model.put(MainMaintenanceConstants.DOMAIN_RECORD, record);
			}
			
			
			//populate model
			if(action==null || "".equals(action)){
				action = "doUpdate";
			}
			model.put("action", action);
			model.put("avd", artype);
			model.put("updateId", updateId);
			successView.addObject(MainMaintenanceConstants.DOMAIN_MODEL , model);
			
			logger.info("Host via HttpServletRequest.getHeader('Host'): " + request.getHeader("Host"));
		    
			return successView;
			
		}
	}
	
	private List<ArktxtDao> fetchList(String applicationUser) {
		JsonReader<JsonDtoContainer<ArktxtDao>> jsonReader = new JsonReader<JsonDtoContainer<ArktxtDao>>();
		jsonReader.set(new JsonDtoContainer<ArktxtDao>());
		String BASE_URL = MaintenanceMainUrlDataStore.MAINTENANCE_MAIN_BASE_ARKTXT_GET_URL;
		StringBuffer urlRequestParams = new StringBuffer();
		urlRequestParams.append("user=" + applicationUser);

		logger.info(Calendar.getInstance().getTime() + " CGI-start timestamp");
		logger.info("URL: " + jsonDebugger.getBASE_URL_NoHostName(BASE_URL));
		logger.info("URL PARAMS: " + urlRequestParams);
		String jsonPayload = urlCgiProxyService.getJsonContent(BASE_URL, urlRequestParams.toString());
		//logger.info("jsonPayload="+jsonPayload);
		List<ArktxtDao> list = null;
		if (jsonPayload != null) {
			JsonDtoContainer<ArktxtDao> container = (JsonDtoContainer<ArktxtDao>) jsonReader.get(jsonPayload);
				if (container != null) {
					list = container.getDtoList();
				}
		}
		return list;
	}	
	
	private ArktxtDao fetchRecord(String applicationUser, String artype) {
		JsonReader<JsonDtoContainer<ArktxtDao>> jsonReader = new JsonReader<JsonDtoContainer<ArktxtDao>>();
		jsonReader.set(new JsonDtoContainer<ArktxtDao>());
		ArktxtDao record = new ArktxtDao();
		String BASE_URL = MaintenanceMainUrlDataStore.MAINTENANCE_MAIN_BASE_ARKTXT_GET_URL;
//		String urlRequestParams = "user=" + applicationUser + "&artype=" + artype;
		StringBuffer urlRequestParams = new StringBuffer();
		urlRequestParams.append("user=" + applicationUser);
		urlRequestParams.append("&artype=" + artype);		
		
		logger.info(Calendar.getInstance().getTime() + " CGI-start timestamp");
		logger.info("URL: " + jsonDebugger.getBASE_URL_NoHostName(BASE_URL));
		logger.info("URL PARAMS: " + urlRequestParams);
		String jsonPayload = this.urlCgiProxyService.getJsonContent(BASE_URL, urlRequestParams.toString());
		if (jsonPayload != null) {
			JsonDtoContainer<ArktxtDao> container = (JsonDtoContainer<ArktxtDao>) jsonReader.get(jsonPayload);
			if (container != null) {
				for (ArktxtDao arktxtDao : container.getDtoList()) {
					record = arktxtDao;
				}
			}
		}

		return record;
	}
	
	private int updateRecord(String applicationUser, ArktxtDao record, String mode, StringBuffer errMsg){
		int retval = 0;
		JsonReader<JsonDtoContainer<ArktxtDao>> jsonReader = new JsonReader<JsonDtoContainer<ArktxtDao>>();
		jsonReader.set(new JsonDtoContainer<ArktxtDao>());
		
		String BASE_URL = MaintenanceMainUrlDataStore.MAINTENANCE_MAIN_BASE_ARKTXT_DML_UPDATE_URL;
		String urlRequestParamsKeys = "user=" + applicationUser + "&mode=" + mode;
		String urlRequestParams = this.urlRequestParameterMapper.getUrlParameterValidString((record));
		//put the final valid param. string
		urlRequestParams = urlRequestParamsKeys + urlRequestParams;
		
		logger.info(Calendar.getInstance().getTime() + " CGI-start timestamp");
    	logger.info("URL: " + jsonDebugger.getBASE_URL_NoHostName(BASE_URL));
    	logger.info("URL PARAMS: " + urlRequestParams);
    	String jsonPayload = urlCgiProxyService.getJsonContent(BASE_URL, urlRequestParams);
		if (jsonPayload != null) {
			JsonDtoContainer<ArktxtDao> container = (JsonDtoContainer<ArktxtDao>) jsonReader.get(jsonPayload);
			if (container != null) {
				if (container.getErrMsg() != null && !"".equals(container.getErrMsg())) {
					errMsg.append(container.getErrMsg());
					retval = MainMaintenanceConstants.ERROR_CODE;
				}
			}			
		} 	
    	
    	return retval;
	}
	
	
	/**
	 * 
	 * @param applicationUser
	 * @param record
	 * @param mode
	 * @param errMsg
	 * @return
	 */
	public int updateChildRecord(String applicationUser, JsonMaintMainKodtaKodthRecord record, String mode, StringBuffer errMsg){
		int retval = 0;
		
		String BASE_URL = MaintenanceMainUrlDataStore.MAINTENANCE_MAIN_BASE_SYFA68R_DML_UPDATE_URL;
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
    		JsonMaintMainKodtaKodthContainer container = this.maintMainKodtaKodthService.doUpdate(jsonPayload);
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
	 * @param applicationUser
	 * @param record
	 * @param mode
	 * @param errMsg
	 * @return
	 */
	private int updateChildRecord(String applicationUser, JsonMaintMainKodtaTellRecord record, String mode, StringBuffer errMsg){
		int retval = 0;
		
		String BASE_URL = MaintenanceMainUrlDataStore.MAINTENANCE_MAIN_BASE_SYFA26R_DML_UPDATE_URL;
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
    		JsonMaintMainKodtaTellContainer container = this.maintMainKodtaTellService.doUpdate(jsonPayload);
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
	 * @param applicationUser
	 * @param recordToValidate
	 * @param listeHodeRecord
	 * @param oppnrTurRecord
	 * @param errMsg
	 * @return
	 */
	private int updateChilden(String applicationUser, JsonMaintMainKodtaRecord recordToValidate, JsonMaintMainKodtaKodthRecord listeHodeRecord, JsonMaintMainKodtaTellRecord oppnrTurRecord,  StringBuffer errMsg){
		int dmlRetval = 0;
		if(listeHodeRecord!=null && oppnrTurRecord!=null){
			//UPDATE ListeHode
			if(listeHodeRecord.getKohavd()!=null && !"".equals(listeHodeRecord.getKohavd())){
				//DEBUG -->logger.info("UPDATE child: listeHode...");
				dmlRetval = this.updateChildRecord(applicationUser, listeHodeRecord, MainMaintenanceConstants.MODE_UPDATE, errMsg);
			}else{
				listeHodeRecord.setKohavd(recordToValidate.getKoaavd());
				dmlRetval = this.updateChildRecord(applicationUser, listeHodeRecord, MainMaintenanceConstants.MODE_ADD, errMsg);
			}
			//UPDATE OppnrTur
			if(oppnrTurRecord.getTeavd()!=null && !"".equals(oppnrTurRecord.getTeavd())){
				//DEBUG -->logger.info("UPDATE child: listeHode...");
				dmlRetval = this.updateChildRecord(applicationUser, oppnrTurRecord, MainMaintenanceConstants.MODE_UPDATE, errMsg);
			}else{
				oppnrTurRecord.setTeavd(recordToValidate.getKoaavd());
				dmlRetval = this.updateChildRecord(applicationUser, oppnrTurRecord, MainMaintenanceConstants.MODE_ADD, errMsg);
			}
		}
		return dmlRetval;
	}
	/**
	 * 
	 * @param applicationUser
	 * @param recordToValidate
	 * @param listeHodeRecord
	 * @param oppnrTurRecord
	 * @param errMsg
	 * @return
	 */
	private int createChilden(String applicationUser, JsonMaintMainKodtaRecord recordToValidate, JsonMaintMainKodtaKodthRecord listeHodeRecord, JsonMaintMainKodtaTellRecord oppnrTurRecord,  StringBuffer errMsg){
		int dmlRetval = 0;
		if(listeHodeRecord!=null && oppnrTurRecord!=null){
			//(1)
			listeHodeRecord.setKohavd(recordToValidate.getKoaavd());
			dmlRetval = this.updateChildRecord(applicationUser, listeHodeRecord, MainMaintenanceConstants.MODE_ADD, errMsg);
			//(2)
			oppnrTurRecord.setTeavd(recordToValidate.getKoaavd());
			dmlRetval = this.updateChildRecord(applicationUser, oppnrTurRecord, MainMaintenanceConstants.MODE_ADD, errMsg);
		}
		return dmlRetval;
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
	
	
	@Qualifier ("maintMainFirmService")
	private MaintMainFirmService maintMainFirmService;
	@Autowired
	@Required
	public void setMaintMainFirmService (MaintMainFirmService value){ this.maintMainFirmService = value; }
	public MaintMainFirmService getMaintMainFirmService(){ return this.maintMainFirmService; }
	
	
	@Qualifier ("maintMainKodtvKodtwService")
	private MaintMainKodtvKodtwService maintMainKodtvKodtwService;
	@Autowired
	@Required
	public void setMaintMainKodtvKodtwService (MaintMainKodtvKodtwService value){ this.maintMainKodtvKodtwService = value; }
	public MaintMainKodtvKodtwService getMaintMainKodtvKodtwService(){ return this.maintMainKodtvKodtwService; }
	
	//Child record
	@Qualifier ("maintMainKodtaKodthService")
	private MaintMainKodtaKodthService maintMainKodtaKodthService;
	@Autowired
	@Required
	public void setMaintMainKodtaKodthService (MaintMainKodtaKodthService value){ this.maintMainKodtaKodthService = value; }
	public MaintMainKodtaKodthService getMaintMainKodtaKodthService(){ return this.maintMainKodtaKodthService; }
	
	//Child record
	@Qualifier ("maintMainKodtaTellService")
	private MaintMainKodtaTellService maintMainKodtaTellService;
	@Autowired
	@Required
	public void setMaintMainKodtaTellService (MaintMainKodtaTellService value){ this.maintMainKodtaTellService = value; }
	public MaintMainKodtaTellService getMaintMainKodtaTellService(){ return this.maintMainKodtaTellService; }
	
		
}

