package no.systema.tvinn.sad.z.maintenance.sadimport.controller.ajax;

import java.util.*;

import org.apache.log4j.Logger;
import org.springframework.validation.BindingResult;
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
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.web.bind.ServletRequestDataBinder;
import org.springframework.web.bind.WebDataBinder;

//application imports
import no.systema.main.context.TdsAppContext;
import no.systema.main.service.UrlCgiProxyService;
import no.systema.main.validator.LoginValidator;
import no.systema.main.util.AppConstants;
import no.systema.main.util.JsonDebugger;
import no.systema.main.model.SystemaWebUser;

import no.systema.tvinn.sad.z.maintenance.sadimport.mapper.url.request.UrlRequestParameterMapper;

import no.systema.tvinn.sad.z.maintenance.sadimport.model.jsonjackson.dbtable.JsonMaintSadImportKodtlikContainer;
import no.systema.tvinn.sad.z.maintenance.sadimport.model.jsonjackson.dbtable.JsonMaintSadImportKodtlikRecord;
import no.systema.tvinn.sad.z.maintenance.sadimport.model.jsonjackson.dbtable.JsonMaintSadImportKodtsiContainer;
import no.systema.tvinn.sad.z.maintenance.sadimport.model.jsonjackson.dbtable.JsonMaintSadImportKodtsiRecord;
import no.systema.tvinn.sad.z.maintenance.sadimport.model.jsonjackson.dbtable.JsonMaintSadImportKodtlbContainer;
import no.systema.tvinn.sad.z.maintenance.sadimport.model.jsonjackson.dbtable.JsonMaintSadImportKodtlbRecord;
import no.systema.tvinn.sad.z.maintenance.sadimport.model.jsonjackson.dbtable.JsonMaintSadImportCundfLikvKodeContainer;
import no.systema.tvinn.sad.z.maintenance.sadimport.model.jsonjackson.dbtable.JsonMaintSadImportCundfLikvKodeRecord;


import no.systema.tvinn.sad.z.maintenance.sadimport.service.MaintSadImportCundfLikvKodeService;
import no.systema.tvinn.sad.z.maintenance.sadimport.service.MaintSadImportKodtlikService;
import no.systema.tvinn.sad.z.maintenance.sadimport.service.MaintSadImportKodtsiService;
import no.systema.tvinn.sad.z.maintenance.sadimport.service.MaintSadImportKodtlbService;

import no.systema.tvinn.sad.z.maintenance.sadimport.url.store.TvinnSadMaintenanceImportUrlDataStore;


/**
 *  TVINN Maintenance Import Syft19r Controller 
 * 
 * @author oscardelatorre
 * @date Mar 30, 2016
 * 
 */

@Controller
@SessionAttributes(AppConstants.SYSTEMA_WEB_USER_KEY)
@Scope("session")
public class MaintSadImportAjaxHandlerController {
	private static final JsonDebugger jsonDebugger = new JsonDebugger();
	private static final Logger logger = Logger.getLogger(MaintSadImportAjaxHandlerController.class.getName());
	private ModelAndView loginView = new ModelAndView("login");
	private ApplicationContext context;
	private LoginValidator loginValidator = new LoginValidator();
	private UrlRequestParameterMapper urlRequestParameterMapper = new UrlRequestParameterMapper();
	
	/**
	 * 
	 * @param user
	 * @param result
	 * @param request
	 * @return
	 */
	@RequestMapping(value="getSpecificRecord_syft18r.do", method={RequestMethod.GET, RequestMethod.POST})
	public @ResponseBody List<JsonMaintSadImportCundfLikvKodeRecord> getRecordSyft18
	  	(@RequestParam String applicationUser, @RequestParam String id) {
		final String METHOD = "[DEBUG] getRecordSyft18 ";
		logger.info(METHOD + " Inside...");
		List<JsonMaintSadImportCundfLikvKodeRecord> result = new ArrayList();
	 	//get table
    	result = (List)this.fetchListSyft18(applicationUser, id);
    	
    	return result;
	
	}
	/**
	 * 
	 * @param applicationUser
	 * @param id
	 * @return
	 */
	@RequestMapping(value="getSpecificRecord_syft19r.do", method={RequestMethod.GET, RequestMethod.POST})
	public @ResponseBody List<JsonMaintSadImportKodtlikRecord> getRecordSyft19
	  	(@RequestParam String applicationUser, @RequestParam String id) {
		final String METHOD = "[DEBUG] getRecordSyft19 ";
		logger.info(METHOD + " Inside...");
		List<JsonMaintSadImportKodtlikRecord> result = new ArrayList();
	 	//get table
    	result = (List)this.fetchListSyft19(applicationUser, id);
    	
    	return result;
	
	}
	/**
	 * 
	 * @param applicationUser
	 * @param id
	 * @return
	 */
	@RequestMapping(value="getSpecificRecord_syft10r.do", method={RequestMethod.GET, RequestMethod.POST})
	public @ResponseBody List<JsonMaintSadImportKodtsiRecord> getRecordSyft10
	  	(@RequestParam String applicationUser, @RequestParam String id) {
		final String METHOD = "[DEBUG] getRecordSyft10 ";
		logger.info(METHOD + " Inside...");
		List<JsonMaintSadImportKodtsiRecord> result = new ArrayList();
	 	//get table
    	result = (List)this.fetchListSyft10(applicationUser, id);
    	
    	return result;
	
	}
	
	
	/**
	 * 
	 * @param user
	 * @param result
	 * @param request
	 * @return
	 */
	@RequestMapping(value="getSpecificRecord_sad012r.do", method={RequestMethod.GET, RequestMethod.POST})
	public @ResponseBody List<JsonMaintSadImportKodtlbRecord> getRecordSad012
	  	(@RequestParam String applicationUser, @RequestParam String id) {
		final String METHOD = "[DEBUG] getRecordSad012 ";
		logger.info(METHOD + " Inside...");
		List<JsonMaintSadImportKodtlbRecord> result = new ArrayList();
	 	//get table
    	result = (List)this.fetchListSad012(applicationUser, id);
    	
    	return result;
	
	}
	
	/**
	 * 
	 * @param applicationUser
	 * @param id
	 * @return
	 */
	private Collection<JsonMaintSadImportKodtlikRecord> fetchListSyft19(String applicationUser, String id){
		
		String BASE_URL = TvinnSadMaintenanceImportUrlDataStore.TVINN_SAD_MAINTENANCE_IMPORT_BASE_SYFT19R_GET_LIST_URL;
		String urlRequestParams = "user=" + applicationUser + "&klikod=" + id;
		logger.info(Calendar.getInstance().getTime() + " CGI-start timestamp");
    	logger.info("URL: " + jsonDebugger.getBASE_URL_NoHostName(BASE_URL));
    	logger.info("URL PARAMS: " + urlRequestParams);
    	String jsonPayload = this.urlCgiProxyService.getJsonContent(BASE_URL, urlRequestParams);
    	//extract
    	List<JsonMaintSadImportKodtlikRecord> list = new ArrayList();
    	if(jsonPayload!=null){
			//lists
	        JsonMaintSadImportKodtlikContainer container = this.maintSadImportKodtlikService.getList(jsonPayload);
	        if(container!=null){
	        	list = (List)container.getList();
	        	for(JsonMaintSadImportKodtlikRecord record: list){
	        		//logger.info(record.getKlikod());
	        	}
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
	private Collection<JsonMaintSadImportCundfLikvKodeRecord> fetchListSyft18(String applicationUser, String id){
		
		String BASE_URL = TvinnSadMaintenanceImportUrlDataStore.TVINN_SAD_MAINTENANCE_IMPORT_BASE_SYFT18R_GET_LIST_URL;
		String urlRequestParams = "user=" + applicationUser + "&kundnr=" + id;
		logger.info(Calendar.getInstance().getTime() + " CGI-start timestamp");
    	logger.info("URL: " + jsonDebugger.getBASE_URL_NoHostName(BASE_URL));
    	logger.info("URL PARAMS: " + urlRequestParams);
    	String jsonPayload = this.urlCgiProxyService.getJsonContent(BASE_URL, urlRequestParams);
    	//extract
    	List<JsonMaintSadImportCundfLikvKodeRecord> list = new ArrayList();
    	if(jsonPayload!=null){
			//lists
    		JsonMaintSadImportCundfLikvKodeContainer container = this.maintSadImportCundfLikvKodeService.getList(jsonPayload);
	        if(container!=null){
	        	//list = (List)container.getList();
	        	for(JsonMaintSadImportCundfLikvKodeRecord record : container.getList()){
	        		//Exception case for Systema. 
	        		if("SS".equals(record.getFirma()) ){
	        			//do not include
	        		}else{
	        			list.add(record);
	        		}
	        	}	   
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
	private Collection<JsonMaintSadImportKodtsiRecord> fetchListSyft10(String applicationUser, String id){
		
		String BASE_URL = TvinnSadMaintenanceImportUrlDataStore.TVINN_SAD_MAINTENANCE_IMPORT_BASE_SYFT10R_GET_LIST_URL;
		String urlRequestParams = "user=" + applicationUser + "&ksisig=" + id;
		logger.info(Calendar.getInstance().getTime() + " CGI-start timestamp");
    	logger.info("URL: " + jsonDebugger.getBASE_URL_NoHostName(BASE_URL));
    	logger.info("URL PARAMS: " + urlRequestParams);
    	String jsonPayload = this.urlCgiProxyService.getJsonContent(BASE_URL, urlRequestParams);
    	//extract
    	List<JsonMaintSadImportKodtsiRecord> list = new ArrayList();
    	if(jsonPayload!=null){
			//lists
    		JsonMaintSadImportKodtsiContainer container = this.maintSadImportKodtsiService.getList(jsonPayload);
	        if(container!=null){
	        	list = (List)container.getList();
	        	for(JsonMaintSadImportKodtsiRecord record: list){
	        		//logger.info(record.getKlikod());
	        	}
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
	private Collection<JsonMaintSadImportKodtlbRecord> fetchListSad012(String applicationUser, String id){
		
		String BASE_URL = TvinnSadMaintenanceImportUrlDataStore.TVINN_SAD_MAINTENANCE_IMPORT_BASE_SAD012R_GET_LIST_URL;
		String urlRequestParams = "user=" + applicationUser + "&klbkod=" + id;
		logger.info(Calendar.getInstance().getTime() + " CGI-start timestamp");
    	logger.info("URL: " + jsonDebugger.getBASE_URL_NoHostName(BASE_URL));
    	logger.info("URL PARAMS: " + urlRequestParams);
    	String jsonPayload = this.urlCgiProxyService.getJsonContent(BASE_URL, urlRequestParams);
    	//extract
    	List<JsonMaintSadImportKodtlbRecord> list = new ArrayList();
    	if(jsonPayload!=null){
			//lists
    		JsonMaintSadImportKodtlbContainer container = this.maintSadImportKodtlbService.getList(jsonPayload);
	        if(container!=null){
	        	list = (List)container.getList();
	        	for(JsonMaintSadImportKodtlbRecord record: list){
	        		logger.info("X"+record.getKlbxxx_mot()+"X");
	        	}
	        }
    	}
    	return list;
    	
	}
	
	
	//SERVICES
	@Qualifier ("urlCgiProxyService")
	private UrlCgiProxyService urlCgiProxyService;
	@Autowired
	@Required
	public void setUrlCgiProxyService (UrlCgiProxyService value){ this.urlCgiProxyService = value; }
	public UrlCgiProxyService getUrlCgiProxyService(){ return this.urlCgiProxyService; }
	
	
	@Qualifier ("maintSadImportKodtlikService")
	private MaintSadImportKodtlikService maintSadImportKodtlikService;
	@Autowired
	@Required
	public void setMaintSadImportKodtlikService (MaintSadImportKodtlikService value){ this.maintSadImportKodtlikService = value; }
	public MaintSadImportKodtlikService getMaintSadImportKodtlikService(){ return this.maintSadImportKodtlikService; }
	
	@Qualifier ("maintSadImportKodtsiService")
	private MaintSadImportKodtsiService maintSadImportKodtsiService;
	@Autowired
	@Required
	public void setMaintSadImportKodtsiService (MaintSadImportKodtsiService value){ this.maintSadImportKodtsiService = value; }
	public MaintSadImportKodtsiService getMaintSadImportKodtsiService(){ return this.maintSadImportKodtsiService; }


	@Qualifier ("maintSadImportKodtlbService")
	private MaintSadImportKodtlbService maintSadImportKodtlbService;
	@Autowired
	@Required
	public void setMaintSadImportKodtlbService (MaintSadImportKodtlbService value){ this.maintSadImportKodtlbService = value; }
	public MaintSadImportKodtlbService getMaintSadImportKodtlbService(){ return this.maintSadImportKodtlbService; }
	

	@Qualifier ("maintSadImportCundfLikvKodeService")
	private MaintSadImportCundfLikvKodeService maintSadImportCundfLikvKodeService;
	@Autowired
	@Required
	public void setMaintSadImportCundfLikvKodeService (MaintSadImportCundfLikvKodeService value){ this.maintSadImportCundfLikvKodeService = value; }
	public MaintSadImportCundfLikvKodeService getMaintSadImportCundfLikvKodeService(){ return this.maintSadImportCundfLikvKodeService; }
	
}

