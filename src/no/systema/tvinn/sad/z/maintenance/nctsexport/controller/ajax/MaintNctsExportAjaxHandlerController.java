package no.systema.tvinn.sad.z.maintenance.nctsexport.controller.ajax;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.Iterator;
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
import no.systema.tvinn.sad.z.maintenance.nctsexport.model.jsonjackson.dbtable.JsonMaintNctsTrughContainer;
import no.systema.tvinn.sad.z.maintenance.nctsexport.model.jsonjackson.dbtable.JsonMaintNctsTrughRecord;
import no.systema.tvinn.sad.z.maintenance.nctsexport.service.MaintNctsExportTrughService;
import no.systema.tvinn.sad.z.maintenance.nctsexport.url.store.TvinnNctsMaintenanceExportUrlDataStore;
import no.systema.z.main.maintenance.model.jsonjackson.dbtable.JsonMaintMainCundfContainer;
import no.systema.z.main.maintenance.model.jsonjackson.dbtable.JsonMaintMainCundfRecord;


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
public class MaintNctsExportAjaxHandlerController {
	private static final JsonDebugger jsonDebugger = new JsonDebugger();
	private static final Logger logger = Logger.getLogger(MaintNctsExportAjaxHandlerController.class.getName());

	@RequestMapping(value = "getSpecificRecord_tr030r.do", method = { RequestMethod.GET, RequestMethod.POST })
	public @ResponseBody List<JsonMaintNctsTrughRecord> getRecordTvi99d(@RequestParam String applicationUser,
			@RequestParam String id) {
		final String METHOD = "[DEBUG] getRecordTr030r ";
		logger.info(METHOD + " applicationUser" + applicationUser + "id=" + id);
		List<JsonMaintNctsTrughRecord> result = new ArrayList();
		// get table
		return (List) this.fetchSpecificTr030r(applicationUser, id);
	}
	
	/**
	 * Primary used in ajax-call as support for user when register Garantiref. 
	 * Getting kundedata
	 * 
	 * @param applicationUser
	 * @param id, kundnr
	 * @return 
	 */
	@RequestMapping(value = "searchCustomer_TvinnSadNcts.do", method = RequestMethod.GET)
	public @ResponseBody List<JsonMaintNctsTrughRecord> searchCustomer(@RequestParam String applicationUser, @RequestParam String customerNumber) {
		final String METHOD = "[DEBUG] searchCustomer_TvinnSadNcts ";
		logger.info(METHOD + " applicationUser" + applicationUser + "id=" + customerNumber);
		List<JsonMaintMainCundfRecord> result = new ArrayList();
		// get table
		return (List) this.fetchSpecificCustomer(applicationUser, customerNumber);
	}
	
	
	
	private Collection<JsonMaintNctsTrughRecord> fetchSpecificTr030r(String applicationUser, String tggnr){
		String BASE_URL = TvinnNctsMaintenanceExportUrlDataStore.TVINN_NCTS_MAINTENANCE_EXPORT_BASE_TR030R_GET_LIST_URL;
		String urlRequestParams = "user=" + applicationUser + "&tggnr=" + tggnr;
		logger.info(Calendar.getInstance().getTime() + " CGI-start timestamp");
    	logger.info("URL: " + jsonDebugger.getBASE_URL_NoHostName(BASE_URL));
    	logger.info("URL PARAMS: " + urlRequestParams);
    	String jsonPayload = this.urlCgiProxyService.getJsonContent(BASE_URL, urlRequestParams);
    	//extract
    	List<JsonMaintNctsTrughRecord> list = new ArrayList();
    	if(jsonPayload!=null){
    		JsonMaintNctsTrughContainer container = this.maintNctsExportTrughService.getList(jsonPayload);
    		list=  (List<JsonMaintNctsTrughRecord>) container.getList();
    	}
    	return list;
	}	
		

	private Collection<JsonMaintMainCundfRecord> fetchSpecificCustomer(String applicationUser, String kundnr){
		String BASE_URL = TvinnNctsMaintenanceExportUrlDataStore.TVINN_NCTS_MAINTENANCE_EXPORT_BASE_TR030R_GET_CUSTOMER_URL;
		String urlRequestParams = "user=" + applicationUser + "&kundnr=" + kundnr;
		logger.info(Calendar.getInstance().getTime() + " CGI-start timestamp");
    	logger.info("URL: " + jsonDebugger.getBASE_URL_NoHostName(BASE_URL));
    	logger.info("URL PARAMS: " + urlRequestParams);
    	String jsonPayload = this.urlCgiProxyService.getJsonContent(BASE_URL, urlRequestParams);
    	//extract
    	List<JsonMaintMainCundfRecord> list = new ArrayList();
    	if(jsonPayload!=null){
 
    		JsonMaintMainCundfContainer container = this.maintNctsExportTrughService.getCustomer(jsonPayload);
    		list=  (List<JsonMaintMainCundfRecord>) container.getList();
    		for (Iterator iterator = list.iterator(); iterator.hasNext();) {
				JsonMaintMainCundfRecord jsonMaintMainCundfRecord = (JsonMaintMainCundfRecord) iterator.next();
				logger.info("jsonMaintMainCundfRecord="+jsonMaintMainCundfRecord.toString());
				
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
	
	@Qualifier ("maintNctsExportTrughService")
	private MaintNctsExportTrughService maintNctsExportTrughService;
	@Autowired
	@Required
	public void setMaintNctsExportTrughService (MaintNctsExportTrughService value){ this.maintNctsExportTrughService = value; }
	public MaintNctsExportTrughService getMaintNctsExportTrughService(){ return this.maintNctsExportTrughService; }
	

}

