	/**
 * 
 */
package no.systema.tvinn.sad.z.maintenance.main.util.manager;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;

import no.systema.main.service.UrlCgiProxyService;
import no.systema.main.util.JsonDebugger;
import no.systema.tvinn.sad.z.maintenance.nctsexport.model.jsonjackson.dbtable.JsonMaintNctsTransitKodeTypeContainer;
import no.systema.tvinn.sad.z.maintenance.nctsexport.model.jsonjackson.dbtable.JsonMaintNctsTransitKodeTypeRecord;
import no.systema.tvinn.sad.z.maintenance.nctsexport.service.MaintNctsExportTrkodfService;
import no.systema.tvinn.sad.z.maintenance.nctsexport.url.store.TvinnNctsMaintenanceExportUrlDataStore;
import no.systema.tvinn.sad.z.maintenance.sadexport.model.jsonjackson.dbtable.JsonMaintSadExportKodts6Container;
import no.systema.tvinn.sad.z.maintenance.sadexport.model.jsonjackson.dbtable.JsonMaintSadExportKodts6Record;
import no.systema.tvinn.sad.z.maintenance.sadexport.service.MaintSadExportKodts6Service;
import no.systema.tvinn.sad.z.maintenance.sadexport.url.store.TvinnSadMaintenanceExportUrlDataStore;


/**
 * The class handles general gui drop downs aspect population for TVINN-SAD Vedlikehold
 *
 * This Manager is not instantiated by the Spring Container at start up. 
 * Instead, it is instantiated by a controller when needed.
 * 
 * 
 * 
 * @author Fredrik Möller
 * @date Sep 15, 2016
 * 
 * 	
 */

public class CodeDropDownMgr {
	private static final Logger logger = Logger.getLogger(CodeDropDownMgr.class.getName());
	private static final JsonDebugger jsonDebugger = new JsonDebugger();

	/**
	 * Populate model with available pref codes, derived from @see {@link TvinnSadMaintenanceExportUrlDataStore}
	 * 
	 * model attribute: prefCodeList
	 * 
	 * @param urlCgiProxyService
	 * @param maintSadExportKodts6Service
	 * @param model
	 * @param applicationUser
	 */
	public void populatePrefCodesHtmlDropDownsSadExport(UrlCgiProxyService urlCgiProxyService, MaintSadExportKodts6Service maintSadExportKodts6Service, Map model, String applicationUser ){
		String BASE_URL = TvinnSadMaintenanceExportUrlDataStore.TVINN_SAD_MAINTENANCE_EXPORT_BASE_SAD002_KODTS6R_GET_LIST_URL;
		StringBuffer urlRequestParams = new StringBuffer();
		urlRequestParams.append("user="+ applicationUser);
		
		logger.info(Calendar.getInstance().getTime() + " CGI-start timestamp");
    	logger.info("URL: " + jsonDebugger.getBASE_URL_NoHostName(BASE_URL));
    	logger.info("URL PARAMS: " + urlRequestParams);
    	String jsonPayload = urlCgiProxyService.getJsonContent(BASE_URL, urlRequestParams.toString());
    	//extract
    	List<JsonMaintSadExportKodts6Record> list = new ArrayList();
    	if(jsonPayload!=null){
			//lists
    		JsonMaintSadExportKodts6Container container = maintSadExportKodts6Service.getList(jsonPayload);
	        if(container!=null){
	        	list = (List)container.getList();
	        }
    	}
    	
    	model.put("kalle", list);  
    	
	}
	
	/**
	 * Populate r31List with J, N
	 * 
	 * model attribute: r31List
	 * 
	 * @param model
	 */
	public void populateR31HtmlDropDownsSadExport(Map model) {
		String[] r31List = {"J","N"};
		model.put("r31List", r31List);
	}
	
	
	/**
	 * Populate mfList with F
	 * 
	 * model attribute: mfList
	 * 
	 * @param model
	 */
	public void populateMfHtlDropDownSadExport(Map model) {
		String[] mfList = {"F"};
		model.put("mfList", mfList);
	}


	/**
	 * Populate all transit code for NCTS
	 * 
	 * model attribute: transitCodeList
	 * 
	 * @param urlCgiProxyService
	 * @param maintNctsExportTrkodfService
	 * @param model, put in to transitCodeList
	 * @param applicationUser
	 */
	public void populateTransitKoder(UrlCgiProxyService urlCgiProxyService, MaintNctsExportTrkodfService maintNctsExportTrkodfService, Map model,
			String applicationUser) {
		String BASE_URL = TvinnNctsMaintenanceExportUrlDataStore.TVINN_NCTS_MAINTENANCE_EXPORT_BASE_TR001R_GET_KODE_TYPER_URL;
		StringBuffer urlRequestParams = new StringBuffer();
		urlRequestParams.append("user="+ applicationUser);
		
		logger.info(Calendar.getInstance().getTime() + " CGI-start timestamp");
    	logger.info("URL: " + jsonDebugger.getBASE_URL_NoHostName(BASE_URL));
    	logger.info("URL PARAMS: " + urlRequestParams);
    	String jsonPayload = urlCgiProxyService.getJsonContent(BASE_URL, urlRequestParams.toString());
    	//extract
    	List<JsonMaintNctsTransitKodeTypeRecord> list = new ArrayList();
    	if(jsonPayload!=null){
			//lists
    		JsonMaintNctsTransitKodeTypeContainer container = maintNctsExportTrkodfService.getTransitKoderList(jsonPayload);
	        if(container!=null){
	        	list = (List)container.getList();
	        }
    	}
    	
    	model.put("transitCodeList", list);
	}	
	
}
