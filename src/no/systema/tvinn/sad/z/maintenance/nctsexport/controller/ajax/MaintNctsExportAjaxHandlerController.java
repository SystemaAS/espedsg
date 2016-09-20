package no.systema.tvinn.sad.z.maintenance.nctsexport.controller.ajax;

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
import no.systema.tvinn.sad.z.maintenance.nctsexport.model.jsonjackson.dbtable.JsonMaintNctsTrughContainer;
import no.systema.tvinn.sad.z.maintenance.nctsexport.model.jsonjackson.dbtable.JsonMaintNctsTrughRecord;
import no.systema.tvinn.sad.z.maintenance.nctsexport.service.MaintNctsExportTrughService;
import no.systema.tvinn.sad.z.maintenance.nctsexport.url.store.TvinnNctsMaintenanceExportUrlDataStore;


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

