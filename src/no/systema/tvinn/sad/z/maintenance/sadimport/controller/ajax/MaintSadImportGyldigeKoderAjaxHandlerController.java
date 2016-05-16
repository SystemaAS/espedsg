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
import no.systema.tvinn.sad.z.maintenance.sadimport.model.jsonjackson.dbtable.gyldigekoder.JsonMaintSadImportKodts8Container;
import no.systema.tvinn.sad.z.maintenance.sadimport.model.jsonjackson.dbtable.gyldigekoder.JsonMaintSadImportKodts8Record;
import no.systema.tvinn.sad.z.maintenance.sadimport.service.gyldigekoder.MaintSadImportKodts8Service;

import no.systema.tvinn.sad.z.maintenance.sadimport.url.store.TvinnSadMaintenanceImportUrlDataStoreGyldigeKoder;


/**
 *  TVINN Maintenance Import SAD002 - Gyldige koder Controller 
 * 
 * @author oscardelatorre
 * @date May 16, 2016
 * 
 */

@Controller
@SessionAttributes(AppConstants.SYSTEMA_WEB_USER_KEY)
@Scope("session")
public class MaintSadImportGyldigeKoderAjaxHandlerController {
	private static final JsonDebugger jsonDebugger = new JsonDebugger();
	private static final Logger logger = Logger.getLogger(MaintSadImportGyldigeKoderAjaxHandlerController.class.getName());
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
	@RequestMapping(value="getSpecificRecord_sad002_kodts8r.do", method={RequestMethod.GET, RequestMethod.POST})
	public @ResponseBody List <JsonMaintSadImportKodts8Record>getRecordSyft18
	  	(@RequestParam String applicationUser, @RequestParam String avgId, @RequestParam String skvId) {
		final String METHOD = "[DEBUG] getSpecificRecord_sad002_kodts8r.do ";
		logger.info(METHOD + " Inside...");
		List<JsonMaintSadImportKodts8Record> result = new ArrayList();
	 	//get table
    	result = (List)this.fetchListKodts8(applicationUser, avgId, skvId);
    	
    	return result;
	
	}
	
	/**
	 * 
	 * @param applicationUser
	 * @param id
	 * @return
	 */
	private Collection<JsonMaintSadImportKodts8Record> fetchListKodts8(String applicationUser, String avgId, String skvId){
		
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
	
	
	@Qualifier ("maintSadImportKodts8Service")
	private MaintSadImportKodts8Service maintSadImportKodts8Service;
	@Autowired
	@Required
	public void setMaintSadImportKodts8Service (MaintSadImportKodts8Service value){ this.maintSadImportKodts8Service = value; }
	public MaintSadImportKodts8Service getMaintSadImportKodts8Service(){ return this.maintSadImportKodts8Service; }
	
	
	
}

