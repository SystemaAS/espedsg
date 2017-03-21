package no.systema.skat.z.maintenance.felles.controller.ajax;

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
import no.systema.main.service.UrlCgiProxyService;
import no.systema.main.util.AppConstants;
import no.systema.main.util.JsonDebugger;


import no.systema.skat.z.maintenance.main.model.jsonjackson.dbtable.JsonMaintDktvkContainer;
import no.systema.skat.z.maintenance.main.model.jsonjackson.dbtable.JsonMaintDktvkRecord;
import no.systema.skat.z.maintenance.main.model.jsonjackson.dbtable.JsonMaintDktardContainer;
import no.systema.skat.z.maintenance.main.model.jsonjackson.dbtable.JsonMaintDktardRecord;
import no.systema.skat.z.maintenance.main.model.jsonjackson.dbtable.JsonMaintDkthaContainer;
import no.systema.skat.z.maintenance.main.model.jsonjackson.dbtable.JsonMaintDkthaRecord;

import no.systema.skat.z.maintenance.main.service.MaintDktardService;
import no.systema.skat.z.maintenance.main.service.MaintDktvkService;
import no.systema.skat.z.maintenance.main.service.MaintDkthaService;

import no.systema.skat.z.maintenance.main.url.store.MaintenanceUrlDataStore;



/**
 * Maintenance Ajax Controller 
 * 
 * @author oscardelatorre
 * @date Jun 13, 2016
 * 
 */

@Controller
@SessionAttributes(AppConstants.SYSTEMA_WEB_USER_KEY)
@Scope("session")
public class MaintSkatFellesAjaxHandlerController {
	private static final JsonDebugger jsonDebugger = new JsonDebugger();
	private static final Logger logger = Logger.getLogger(MaintSkatFellesAjaxHandlerController.class.getName());
	
	/**
	 * 
	 * @param user
	 * @param result
	 * @param request
	 * @return
	 */
	@RequestMapping(value="getSpecificRecord_dkt057r.do", method={RequestMethod.GET, RequestMethod.POST})
	public @ResponseBody List<JsonMaintDktvkRecord> getRecordDkt057
	  	(@RequestParam String applicationUser, @RequestParam String id, @RequestParam String date) {
		final String METHOD = "[DEBUG] getSpecificRecord_dkt057r";
		logger.info(METHOD + " Inside...");
		List<JsonMaintDktvkRecord> result = new ArrayList();
	 	//get table
    	result = (List)this.fetchListDkt057(applicationUser, id, date);
    	
    	return result;
	
	}
	/**
	 * 
	 * @param applicationUser
	 * @param id
	 * @param date
	 * @return
	 */
	@RequestMapping(value="getSpecificRecord_dktard.do", method={RequestMethod.GET, RequestMethod.POST})
	public @ResponseBody List<JsonMaintDktardRecord> getRecordDktard
	  	(@RequestParam String applicationUser, @RequestParam String id, @RequestParam String fromDate, String toDate) {
		final String METHOD = "[DEBUG] getSpecificRecord_dktard";
		logger.info(METHOD + " Inside...");
		List<JsonMaintDktardRecord> result = new ArrayList();
	 	//get table
    	result = (List)this.fetchListDktard(applicationUser, id, fromDate, toDate);
    	
    	return result;
	
	}
	
	/**
	 * 
	 * @param applicationUser
	 * @param id
	 * @return
	 */
	@RequestMapping(value="getSpecificRecord_dkt056r.do", method={RequestMethod.GET, RequestMethod.POST})
	public @ResponseBody List<JsonMaintDkthaRecord> getRecordDkt056r
	  	(@RequestParam String applicationUser, @RequestParam String id) {
		final String METHOD = "[DEBUG] getSpecificRecord_dktard";
		logger.info(METHOD + " Inside...");
		List<JsonMaintDkthaRecord> result = new ArrayList();
	 	//get table
    	result = (List)this.fetchListDkt056(applicationUser, id);
    	
    	return result;
	
	}
	
	/**
	 * 
	 * @param applicationUser
	 * @param id
	 * @param date
	 * 
	 * @return
	 * 
	 */
	private Collection<JsonMaintDktvkRecord> fetchListDkt057(String applicationUser, String id, String fromDate){
		
		String BASE_URL = MaintenanceUrlDataStore.MAINTENANCE_BASE_DKT057R_GET_LIST_URL;
		String urlRequestParams = "user=" + applicationUser + "&dkvk_kd=" + id + "&dkvk_dts=" + fromDate;
		logger.info(Calendar.getInstance().getTime() + " CGI-start timestamp");
    	logger.info("URL: " + jsonDebugger.getBASE_URL_NoHostName(BASE_URL));
    	logger.info("URL PARAMS: " + urlRequestParams);
    	String jsonPayload = this.urlCgiProxyService.getJsonContent(BASE_URL, urlRequestParams);
    	//extract
    	List<JsonMaintDktvkRecord> list = new ArrayList();
    	if(jsonPayload!=null){
			//lists
    		JsonMaintDktvkContainer container = this.maintDktvkService.getList(jsonPayload);
	        if(container!=null){
	        	list = (List)container.getList();
	        	for(JsonMaintDktvkRecord record: list){
	        		//logger.info(record.getDkvk_kd());
	        	}
	        }
    	}
    	return list;
	}
	/**
	 * 
	 * @param applicationUser
	 * @param id
	 * @param fromDate
	 * @param toDate
	 * @return
	 */
	private Collection<JsonMaintDktardRecord> fetchListDktard(String applicationUser, String id, String fromDate, String toDate){
		
		String BASE_URL = MaintenanceUrlDataStore.MAINTENANCE_BASE_DKTARDR_GET_LIST_URL;
		String urlRequestParams = "user=" + applicationUser + "&dktard01=" + id + "&dktard02=" + fromDate + "&dktard03=" + toDate;
		logger.info(Calendar.getInstance().getTime() + " CGI-start timestamp");
    	logger.info("URL: " + jsonDebugger.getBASE_URL_NoHostName(BASE_URL));
    	logger.info("URL PARAMS: " + urlRequestParams);
    	String jsonPayload = this.urlCgiProxyService.getJsonContent(BASE_URL, urlRequestParams);
    	//extract
    	List<JsonMaintDktardRecord> list = new ArrayList();
    	if(jsonPayload!=null){
			//lists
    		JsonMaintDktardContainer container = this.maintDktardService.getList(jsonPayload);
	        if(container!=null){
	        	list = (List)container.getDtoList();
	        	for(JsonMaintDktardRecord record: list){
	        		//logger.info(record.getDktard01());
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
	private Collection<JsonMaintDkthaRecord> fetchListDkt056(String applicationUser, String id){
		
		String BASE_URL = MaintenanceUrlDataStore.MAINTENANCE_BASE_DKT056R_GET_LIST_URL;
		String urlRequestParams = "user=" + applicationUser + "&dkth_sysg=" + id ;
		logger.info(Calendar.getInstance().getTime() + " CGI-start timestamp");
    	logger.info("URL: " + jsonDebugger.getBASE_URL_NoHostName(BASE_URL));
    	logger.info("URL PARAMS: " + urlRequestParams);
    	String jsonPayload = this.urlCgiProxyService.getJsonContent(BASE_URL, urlRequestParams);
    	//extract
    	List<JsonMaintDkthaRecord> list = new ArrayList();
    	if(jsonPayload!=null){
			//lists
    		JsonMaintDkthaContainer container = this.maintDkthaService.getList(jsonPayload);
	        if(container!=null){
	        	list = (List)container.getDtoList();
	        	for(JsonMaintDkthaRecord record: list){
	        		//logger.info(record.getDkth_sysg());
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
	
	
	@Qualifier ("maintDktvkService")
	private MaintDktvkService maintDktvkService;
	@Autowired
	@Required
	public void setMaintDktvkService (MaintDktvkService value){ this.maintDktvkService = value; }
	public MaintDktvkService getMaintDktvkService(){ return this.maintDktvkService; }
	
	
	@Qualifier ("maintDktardService")
	private MaintDktardService maintDktardService;
	@Autowired
	@Required
	public void setMaintDktardService (MaintDktardService value){ this.maintDktardService = value; }
	public MaintDktardService getMaintDktardService(){ return this.maintDktardService; }
	
	@Qualifier ("maintDkthaService")
	private MaintDkthaService maintDkthaService;
	@Autowired
	@Required
	public void setMaintDkthaService (MaintDkthaService value){ this.maintDkthaService = value; }
	public MaintDkthaService getMaintDkthaService(){ return this.maintDkthaService; }
	
	
	
}

