package no.systema.tvinn.sad.z.maintenance.sadexport.controller.ajax;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Required;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttributes;

import no.systema.main.service.UrlCgiProxyService;
import no.systema.main.util.AppConstants;
import no.systema.main.util.JsonDebugger;
import no.systema.tvinn.sad.z.maintenance.sadexport.model.jsonjackson.dbtable.JsonMaintSadExportTvineContainer;
import no.systema.tvinn.sad.z.maintenance.sadexport.model.jsonjackson.dbtable.JsonMaintSadExportTvineRecord;
import no.systema.tvinn.sad.z.maintenance.sadexport.service.MaintSadExportTvineService;
import no.systema.tvinn.sad.z.maintenance.sadexport.url.store.TvinnSadMaintenanceExportUrlDataStore;


/**
 *  TVINN Maintenance Export AJAX Controller 
 * 
 * @author Fredrik Möller
 * @date Aug 10, 2016
 * 
 */

@Controller
@SessionAttributes(AppConstants.SYSTEMA_WEB_USER_KEY)
@Scope("session")
public class MaintSadExportAjaxHandlerController {
	private static final JsonDebugger jsonDebugger = new JsonDebugger();
	private static final Logger logger = Logger.getLogger(MaintSadExportAjaxHandlerController.class.getName());
	
	/**
	 * 
	 * @param user
	 * @param result
	 * @param request
	 * @return
	 */
	@RequestMapping(value="getSpecificRecord_tvi99d.do", method={RequestMethod.GET, RequestMethod.POST})
	public @ResponseBody List<JsonMaintSadExportTvineRecord> getRecordTvi99d
	  	(@RequestParam String applicationUser, @RequestParam String id) {
		final String METHOD = "[DEBUG] getRecordTvi99d ";
		logger.info(METHOD + " applicationUser"+applicationUser+"id="+id);
		List<JsonMaintSadExportTvineRecord> result = new ArrayList();
	 	//get table
    	result = (List)this.fetchListTvi99d(applicationUser, id);
    	
    	return result;
	
	}
	/**
	 * 
	 * @param applicationUser
	 * @param id
	 * @return
	 */
	private Collection<JsonMaintSadExportTvineRecord> fetchListTvi99d(String applicationUser, String id){
		
		String BASE_URL = TvinnSadMaintenanceExportUrlDataStore.TVINN_SAD_MAINTENANCE_EXPORT_BASE_TVI99D_GET_LIST_URL;
		String urlRequestParams = "user=" + applicationUser + "&e9705=" + id;
		logger.info(Calendar.getInstance().getTime() + " CGI-start timestamp");
    	logger.info("URL: " + jsonDebugger.getBASE_URL_NoHostName(BASE_URL));
    	logger.info("URL PARAMS: " + urlRequestParams);
    	String jsonPayload = this.urlCgiProxyService.getJsonContent(BASE_URL, urlRequestParams);
    	//extract
    	List<JsonMaintSadExportTvineRecord> list = new ArrayList();
		if (jsonPayload != null) {
			// lists
			JsonMaintSadExportTvineContainer container = this.maintSadExportTvineService.getList(jsonPayload);
			if (container != null) {
				// list = (List)container.getList();
				for (JsonMaintSadExportTvineRecord record : container.getList()) {
					list.add(record);
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
	
	
	@Qualifier ("maintSadExportTvineService")
	private MaintSadExportTvineService maintSadExportTvineService;
	@Autowired
	@Required
	public void setMaintSadExportTvineService (MaintSadExportTvineService value){ this.maintSadExportTvineService = value; }
	public MaintSadExportTvineService getMaintSadExportTvineService(){ return this.maintSadExportTvineService; }
	
	
}

