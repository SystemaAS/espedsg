package no.systema.tvinn.sad.z.maintenance.sadimport.controller.ajax.manager;

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

import no.systema.tvinn.sad.z.maintenance.sadimport.model.jsonjackson.dbtable.gyldigekoder.JsonMaintSadImportKodts1Container;
import no.systema.tvinn.sad.z.maintenance.sadimport.model.jsonjackson.dbtable.gyldigekoder.JsonMaintSadImportKodts1Record;
import no.systema.tvinn.sad.z.maintenance.sadimport.model.jsonjackson.dbtable.gyldigekoder.JsonMaintSadImportKodts3Container;
import no.systema.tvinn.sad.z.maintenance.sadimport.model.jsonjackson.dbtable.gyldigekoder.JsonMaintSadImportKodts3Record;
import no.systema.tvinn.sad.z.maintenance.sadimport.model.jsonjackson.dbtable.gyldigekoder.JsonMaintSadImportKodts4Container;
import no.systema.tvinn.sad.z.maintenance.sadimport.model.jsonjackson.dbtable.gyldigekoder.JsonMaintSadImportKodts4Record;
import no.systema.tvinn.sad.z.maintenance.sadimport.model.jsonjackson.dbtable.gyldigekoder.JsonMaintSadImportKodts5Container;
import no.systema.tvinn.sad.z.maintenance.sadimport.model.jsonjackson.dbtable.gyldigekoder.JsonMaintSadImportKodts5Record;
import no.systema.tvinn.sad.z.maintenance.sadimport.model.jsonjackson.dbtable.gyldigekoder.JsonMaintSadImportKodts6Container;
import no.systema.tvinn.sad.z.maintenance.sadimport.model.jsonjackson.dbtable.gyldigekoder.JsonMaintSadImportKodts6Record;

import no.systema.tvinn.sad.z.maintenance.sadimport.model.jsonjackson.dbtable.gyldigekoder.JsonMaintSadImportKodts8Container;
import no.systema.tvinn.sad.z.maintenance.sadimport.model.jsonjackson.dbtable.gyldigekoder.JsonMaintSadImportKodts8Record;
import no.systema.tvinn.sad.z.maintenance.sadimport.service.gyldigekoder.MaintSadImportKodts1Service;
import no.systema.tvinn.sad.z.maintenance.sadimport.service.gyldigekoder.MaintSadImportKodts3Service;
import no.systema.tvinn.sad.z.maintenance.sadimport.service.gyldigekoder.MaintSadImportKodts4Service;
import no.systema.tvinn.sad.z.maintenance.sadimport.service.gyldigekoder.MaintSadImportKodts5Service;
import no.systema.tvinn.sad.z.maintenance.sadimport.service.gyldigekoder.MaintSadImportKodts6Service;
import no.systema.tvinn.sad.z.maintenance.sadimport.service.gyldigekoder.MaintSadImportKodts8Service;

import no.systema.tvinn.sad.z.maintenance.sadimport.url.store.TvinnSadMaintenanceImportUrlDataStoreGyldigeKoder;


/**
 *  TVINN Maintenance Import SAD002 - Gyldige koder Manager
 * 
 * @author oscardelatorre
 * @date May 23, 2016
 * 
 */
@Component
public class MaintSadImportGyldigeKoderAjaxHandlerManager {
	private static final JsonDebugger jsonDebugger = new JsonDebugger();
	private static final Logger logger = Logger.getLogger(MaintSadImportGyldigeKoderAjaxHandlerManager.class.getName());
		
	
	/**
	 * 
	 * @param maintSadImportKodts1Service
	 * @param applicationUser
	 * @param id
	 * @return
	 */
	public Collection<JsonMaintSadImportKodts1Record> fetchListKodts1(String applicationUser, String id){
		
		String BASE_URL = TvinnSadMaintenanceImportUrlDataStoreGyldigeKoder.TVINN_SAD_MAINTENANCE_IMPORT_BASE_SAD002_KODTS1R_GET_LIST_URL;
		String urlRequestParams = "user=" + applicationUser + "&ks1typ=" + id ;
		logger.info(Calendar.getInstance().getTime() + " CGI-start timestamp");
    	logger.info("URL: " + jsonDebugger.getBASE_URL_NoHostName(BASE_URL));
    	logger.info("URL PARAMS: " + urlRequestParams);
    	logger.info("A");
    	String jsonPayload = this.urlCgiProxyService.getJsonContent(BASE_URL, urlRequestParams);
    	logger.info("B");
		
    	//extract
    	List<JsonMaintSadImportKodts1Record> list = new ArrayList();
    	if(jsonPayload!=null){
			//lists
    		JsonMaintSadImportKodts1Container container = this.maintSadImportKodts1Service.getList(jsonPayload);
    		if(container!=null){
	        	list = (List)container.getList();
	        	for(JsonMaintSadImportKodts1Record record: list){
	        		logger.info(record.getKs1typ());
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
	public Collection<JsonMaintSadImportKodts3Record> fetchListKodts3(String applicationUser, String id){
		
		String BASE_URL = TvinnSadMaintenanceImportUrlDataStoreGyldigeKoder.TVINN_SAD_MAINTENANCE_IMPORT_BASE_SAD002_KODTS3R_GET_LIST_URL;
		String urlRequestParams = "user=" + applicationUser + "&ks3trt=" + id ;
		logger.info(Calendar.getInstance().getTime() + " CGI-start timestamp");
    	logger.info("URL: " + jsonDebugger.getBASE_URL_NoHostName(BASE_URL));
    	logger.info("URL PARAMS: " + urlRequestParams);
    	String jsonPayload = this.urlCgiProxyService.getJsonContent(BASE_URL, urlRequestParams);
    	//extract
    	List<JsonMaintSadImportKodts3Record> list = new ArrayList();
    	if(jsonPayload!=null){
			//lists
    		JsonMaintSadImportKodts3Container container = this.maintSadImportKodts3Service.getList(jsonPayload);
	        if(container!=null){
	        	list = (List)container.getList();
	        	for(JsonMaintSadImportKodts3Record record: list){
	        		logger.info(record.getKs3trt());
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
	public Collection<JsonMaintSadImportKodts4Record> fetchListKodts4(String applicationUser, String id){
		
		String BASE_URL = TvinnSadMaintenanceImportUrlDataStoreGyldigeKoder.TVINN_SAD_MAINTENANCE_IMPORT_BASE_SAD002_KODTS4R_GET_LIST_URL;
		String urlRequestParams = "user=" + applicationUser + "&ks4trm=" + id ;
		logger.info(Calendar.getInstance().getTime() + " CGI-start timestamp");
    	logger.info("URL: " + jsonDebugger.getBASE_URL_NoHostName(BASE_URL));
    	logger.info("URL PARAMS: " + urlRequestParams);
    	String jsonPayload = this.urlCgiProxyService.getJsonContent(BASE_URL, urlRequestParams);
    	//extract
    	List<JsonMaintSadImportKodts4Record> list = new ArrayList();
    	if(jsonPayload!=null){
			//lists
    		JsonMaintSadImportKodts4Container container = this.maintSadImportKodts4Service.getList(jsonPayload);
	        if(container!=null){
	        	list = (List)container.getList();
	        	for(JsonMaintSadImportKodts4Record record: list){
	        		logger.info(record.getKs4trm());
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
	public Collection<JsonMaintSadImportKodts5Record> fetchListKodts5(String applicationUser, String id){
		
		String BASE_URL = TvinnSadMaintenanceImportUrlDataStoreGyldigeKoder.TVINN_SAD_MAINTENANCE_IMPORT_BASE_SAD002_KODTS5R_GET_LIST_URL;
		String urlRequestParams = "user=" + applicationUser + "&ks5tln=" + id ;
		logger.info(Calendar.getInstance().getTime() + " CGI-start timestamp");
    	logger.info("URL: " + jsonDebugger.getBASE_URL_NoHostName(BASE_URL));
    	logger.info("URL PARAMS: " + urlRequestParams);
    	String jsonPayload = this.urlCgiProxyService.getJsonContent(BASE_URL, urlRequestParams);
    	//extract
    	List<JsonMaintSadImportKodts5Record> list = new ArrayList();
    	if(jsonPayload!=null){
			//lists
    		JsonMaintSadImportKodts5Container container = this.maintSadImportKodts5Service.getList(jsonPayload);
	        if(container!=null){
	        	list = (List)container.getList();
	        	for(JsonMaintSadImportKodts5Record record: list){
	        		logger.info(record.getKs5tln());
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
	public Collection<JsonMaintSadImportKodts6Record> fetchListKodts6(String applicationUser, String id){
		
		String BASE_URL = TvinnSadMaintenanceImportUrlDataStoreGyldigeKoder.TVINN_SAD_MAINTENANCE_IMPORT_BASE_SAD002_KODTS6R_GET_LIST_URL;
		String urlRequestParams = "user=" + applicationUser + "&ks6pre=" + id ;
		logger.info(Calendar.getInstance().getTime() + " CGI-start timestamp");
    	logger.info("URL: " + jsonDebugger.getBASE_URL_NoHostName(BASE_URL));
    	logger.info("URL PARAMS: " + urlRequestParams);
    	String jsonPayload = this.urlCgiProxyService.getJsonContent(BASE_URL, urlRequestParams);
    	//extract
    	List<JsonMaintSadImportKodts6Record> list = new ArrayList();
    	if(jsonPayload!=null){
			//lists
    		JsonMaintSadImportKodts6Container container = this.maintSadImportKodts6Service.getList(jsonPayload);
	        if(container!=null){
	        	list = (List)container.getList();
	        	for(JsonMaintSadImportKodts6Record record: list){
	        		//logger.info(record.getKs6pre());
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
	public Collection<JsonMaintSadImportKodts8Record> fetchListKodts8(String applicationUser, String avgId, String skvId){
		
		String BASE_URL = TvinnSadMaintenanceImportUrlDataStoreGyldigeKoder.TVINN_SAD_MAINTENANCE_IMPORT_BASE_SAD002_KODTS8R_GET_LIST_URL;
		String urlRequestParams = "user=" + applicationUser + "&ks8avg=" + avgId + "&ks8skv=" + skvId;
		logger.info(Calendar.getInstance().getTime() + " CGI-start timestamp");
    	logger.info("URL: " + jsonDebugger.getBASE_URL_NoHostName(BASE_URL));
    	logger.info("URL PARAMS: " + urlRequestParams);
    	String jsonPayload = this.urlCgiProxyService.getJsonContent(BASE_URL, urlRequestParams);
    	//extract
    	List<JsonMaintSadImportKodts8Record> list = new ArrayList();
    	if(jsonPayload!=null){
			//lists
    		JsonMaintSadImportKodts8Container container = this.maintSadImportKodts8Service.getList(jsonPayload);
	        if(container!=null){
	        	list = (List)container.getList();
	        	for(JsonMaintSadImportKodts8Record record: list){
	        		logger.info(record.getKs8avg());
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
	
	
	@Qualifier ("maintSadImportKodts1Service")
	private MaintSadImportKodts1Service maintSadImportKodts1Service;
	@Autowired
	@Required
	public void setMaintSadImportKodts1Service (MaintSadImportKodts1Service value){ this.maintSadImportKodts1Service = value; }
	public MaintSadImportKodts1Service getMaintSadImportKodts1Service(){ return this.maintSadImportKodts1Service; }
	
	
	@Qualifier ("maintSadImportKodts3Service")
	private MaintSadImportKodts3Service maintSadImportKodts3Service;
	@Autowired
	@Required
	public void setMaintSadImportKodts3Service (MaintSadImportKodts3Service value){ this.maintSadImportKodts3Service = value; }
	public MaintSadImportKodts3Service getMaintSadImportKodts3Service(){ return this.maintSadImportKodts3Service; }
	
	@Qualifier ("maintSadImportKodts4Service")
	private MaintSadImportKodts4Service maintSadImportKodts4Service;
	@Autowired
	@Required
	public void setMaintSadImportKodts4Service (MaintSadImportKodts4Service value){ this.maintSadImportKodts4Service = value; }
	public MaintSadImportKodts4Service getMaintSadImportKodts4Service(){ return this.maintSadImportKodts4Service; }
	
	@Qualifier ("maintSadImportKodts5Service")
	private MaintSadImportKodts5Service maintSadImportKodts5Service;
	@Autowired
	@Required
	public void setMaintSadImportKodts5Service (MaintSadImportKodts5Service value){ this.maintSadImportKodts5Service = value; }
	public MaintSadImportKodts5Service getMaintSadImportKodts5Service(){ return this.maintSadImportKodts5Service; }
	
	@Qualifier ("maintSadImportKodts6Service")
	private MaintSadImportKodts6Service maintSadImportKodts6Service;
	@Autowired
	@Required
	public void setMaintSadImportKodts6Service (MaintSadImportKodts6Service value){ this.maintSadImportKodts6Service = value; }
	public MaintSadImportKodts6Service getMaintSadImportKodts6Service(){ return this.maintSadImportKodts6Service; }
	
	
	@Qualifier ("maintSadImportKodts8Service")
	private MaintSadImportKodts8Service maintSadImportKodts8Service;
	@Autowired
	@Required
	public void setMaintSadImportKodts8Service (MaintSadImportKodts8Service value){ this.maintSadImportKodts8Service = value; }
	public MaintSadImportKodts8Service getMaintSadImportKodts8Service(){ return this.maintSadImportKodts8Service; }
	
	
	
	
}

