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

import no.systema.tvinn.sad.sadimport.model.jsonjackson.topic.JsonSadImportSpecificTopicRecord;
import no.systema.tvinn.sad.z.maintenance.sadimport.mapper.url.request.UrlRequestParameterMapper;
import no.systema.tvinn.sad.z.maintenance.util.TvinnSadMaintenanceConstants;
import no.systema.tvinn.sad.z.maintenance.model.MaintenanceMainListObject;
import no.systema.tvinn.sad.z.maintenance.sadimport.model.jsonjackson.dbtable.JsonMaintSadImportKodtlikContainer;
import no.systema.tvinn.sad.z.maintenance.sadimport.model.jsonjackson.dbtable.JsonMaintSadImportKodtlikRecord;
import no.systema.tvinn.sad.z.maintenance.sadimport.service.MaintSadImportKodtlikService;
import no.systema.tvinn.sad.z.maintenance.sadimport.url.store.TvinnSadMaintenanceImportUrlDataStore;
import no.systema.tvinn.sad.z.maintenance.sadimport.validator.MaintSadImportSyft19rValidator;


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
	@RequestMapping(value="getSpecificRecord_syft19r.do", method={RequestMethod.GET, RequestMethod.POST})
	public @ResponseBody List<JsonMaintSadImportKodtlikRecord> getRecord
	  	(@RequestParam String applicationUser, @RequestParam String id) {
		final String METHOD = "[DEBUG] method:getSpecificTopicAvdData ";
		logger.info(METHOD + " Inside...");
		List<JsonMaintSadImportKodtlikRecord> result = new ArrayList();
	 	//get table
    	result = (List)this.fetchList(applicationUser, id);
    	
    	return result;
	
	}
	
	
	
	/**
	 * 
	 * @param applicationUser
	 * @param id
	 * @return
	 */
	private Collection<JsonMaintSadImportKodtlikRecord> fetchList(String applicationUser, String id){
		
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
	
}

