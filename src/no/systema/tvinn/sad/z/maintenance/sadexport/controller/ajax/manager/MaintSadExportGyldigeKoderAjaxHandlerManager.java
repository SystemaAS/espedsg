package no.systema.tvinn.sad.z.maintenance.sadexport.controller.ajax.manager;

import java.util.*;

import org.apache.log4j.Logger;
import org.springframework.validation.BindingResult;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.stereotype.Component;
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


//application imports
import no.systema.main.service.UrlCgiProxyService;
import no.systema.main.util.JsonDebugger;
import no.systema.main.model.SystemaWebUser;

import no.systema.tvinn.sad.z.maintenance.sadexport.model.jsonjackson.dbtable.gyldigekoder.*;
import no.systema.tvinn.sad.z.maintenance.sadexport.service.gyldigekoder.*;

import no.systema.tvinn.sad.z.maintenance.sadexport.url.store.TvinnSadMaintenanceExportUrlDataStoreGyldigeKoder;


/**
 *  TVINN Maintenance Export SAD002 - Gyldige koder Manager
 * 
 * @author oscardelatorre
 * @date Okt 26, 2016
 * 
 */
@Component
public class MaintSadExportGyldigeKoderAjaxHandlerManager {
	private static final JsonDebugger jsonDebugger = new JsonDebugger();
	private static final Logger logger = Logger.getLogger(MaintSadExportGyldigeKoderAjaxHandlerManager.class.getName());
		
	
	/**
	 * 
	 * @param maintSadImportKodts9Service
	 * @param applicationUser
	 * @param id
	 * @return
	 */
	public Collection<JsonMaintSadExportKodts9Record> fetchListKodts9(String applicationUser, String id){
		
		String BASE_URL = TvinnSadMaintenanceExportUrlDataStoreGyldigeKoder.TVINN_SAD_MAINTENANCE_EXPORT_BASE_SAD002_KODTS9R_GET_LIST_URL;
		String urlRequestParams = "user=" + applicationUser + "&ks9typ=" + id ;
		logger.info(Calendar.getInstance().getTime() + " CGI-start timestamp");
    	logger.info("URL: " + jsonDebugger.getBASE_URL_NoHostName(BASE_URL));
    	logger.info("URL PARAMS: " + urlRequestParams);
    	String jsonPayload = this.urlCgiProxyService.getJsonContent(BASE_URL, urlRequestParams);
    	
    	//extract
    	List<JsonMaintSadExportKodts9Record> list = new ArrayList();
    	if(jsonPayload!=null){
			//lists
    		JsonMaintSadExportKodts9Container container = this.maintSadExportKodts9Service.getList(jsonPayload);
    		if(container!=null){
	        	list = (List)container.getList();
	        	for(JsonMaintSadExportKodts9Record record: list){
	        		logger.info(record.getKs9typ());
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
	public Collection<JsonMaintSadExportKodtscRecord> fetchListKodtsc(String applicationUser, String id){
		
		String BASE_URL = TvinnSadMaintenanceExportUrlDataStoreGyldigeKoder.TVINN_SAD_MAINTENANCE_EXPORT_BASE_SAD002_KODTSCR_GET_LIST_URL;
		String urlRequestParams = "user=" + applicationUser + "&ksckd=" + id ;
		logger.info(Calendar.getInstance().getTime() + " CGI-start timestamp");
    	logger.info("URL: " + jsonDebugger.getBASE_URL_NoHostName(BASE_URL));
    	logger.info("URL PARAMS: " + urlRequestParams);
    	String jsonPayload = this.urlCgiProxyService.getJsonContent(BASE_URL, urlRequestParams);
    	
    	//extract
    	List<JsonMaintSadExportKodtscRecord> list = new ArrayList();
    	if(jsonPayload!=null){
			//lists
    		JsonMaintSadExportKodtscContainer container = this.maintSadExportKodtscService.getList(jsonPayload);
    		if(container!=null){
	        	list = (List)container.getList();
	        	for(JsonMaintSadExportKodtscRecord record: list){
	        		//logger.info(record.getKsckd());
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
	
	
	@Qualifier ("maintSadExportKodts9Service")
	private MaintSadExportKodts9Service maintSadExportKodts9Service;
	@Autowired
	@Required
	public void setMaintSadExportKodts9Service (MaintSadExportKodts9Service value){ this.maintSadExportKodts9Service = value; }
	public MaintSadExportKodts9Service getMaintSadExportKodts9Service(){ return this.maintSadExportKodts9Service; }
	
	
	@Qualifier ("maintSadExportKodtscService")
	private MaintSadExportKodtscService maintSadExportKodtscService;
	@Autowired
	@Required
	public void setMaintSadExportKodtscService (MaintSadExportKodtscService value){ this.maintSadExportKodtscService = value; }
	public MaintSadExportKodtscService getMaintSadExportKodtscService(){ return this.maintSadExportKodtscService; }
	
	
	
}

