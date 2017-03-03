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
import no.systema.skat.z.maintenance.main.service.MaintDktvkService;
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
		final String METHOD = "[DEBUG] getRecordDkt057";
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
	
	
}

