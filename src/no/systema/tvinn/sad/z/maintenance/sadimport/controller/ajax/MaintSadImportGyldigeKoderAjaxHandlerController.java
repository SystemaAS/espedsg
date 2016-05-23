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

import no.systema.tvinn.sad.z.maintenance.sadimport.model.jsonjackson.dbtable.gyldigekoder.JsonMaintSadImportKodts1Record;
import no.systema.tvinn.sad.z.maintenance.sadimport.model.jsonjackson.dbtable.gyldigekoder.JsonMaintSadImportKodts3Record;
import no.systema.tvinn.sad.z.maintenance.sadimport.model.jsonjackson.dbtable.gyldigekoder.JsonMaintSadImportKodts4Record;
import no.systema.tvinn.sad.z.maintenance.sadimport.model.jsonjackson.dbtable.gyldigekoder.JsonMaintSadImportKodts8Record;
import no.systema.tvinn.sad.z.maintenance.sadimport.controller.ajax.manager.MaintSadImportGyldigeKoderAjaxHandlerManager;

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
	
	/**
	 * 
	 * @param applicationUser
	 * @param avgId
	 * @param skvId
	 * @return
	 */
	@RequestMapping(value="getSpecificRecord_sad002_kodts1r.do", method={RequestMethod.GET, RequestMethod.POST})
	public @ResponseBody List <JsonMaintSadImportKodts1Record>getRecordSad002_kodts1
	  	(@RequestParam String applicationUser, @RequestParam String id) {
		final String METHOD = "[DEBUG] getSpecificRecord_sad002_kodts1r.do ";
		logger.info(METHOD + " Inside...");
		List<JsonMaintSadImportKodts1Record> result = new ArrayList();
	 	//get table
		result = (List)this.maintSadImportGyldigeKoderAjaxHandlerManager.fetchListKodts1(applicationUser, id);
    	
    	return result;
	
	}
	
	/**
	 * 
	 * @param applicationUser
	 * @param avgId
	 * @param skvId
	 * @return
	 */
	@RequestMapping(value="getSpecificRecord_sad002_kodts3r.do", method={RequestMethod.GET, RequestMethod.POST})
	public @ResponseBody List <JsonMaintSadImportKodts3Record>getRecordSad002_kodts3
	  	(@RequestParam String applicationUser, @RequestParam String id) {
		final String METHOD = "[DEBUG] getSpecificRecord_sad002_kodts3r.do ";
		logger.info(METHOD + " Inside...");
		List<JsonMaintSadImportKodts3Record> result = new ArrayList();
	 	//get table
		result = (List)this.maintSadImportGyldigeKoderAjaxHandlerManager.fetchListKodts3(applicationUser, id);
    	
    	return result;
	
	}
	/**
	 * 
	 * @param applicationUser
	 * @param id
	 * @return
	 */
	@RequestMapping(value="getSpecificRecord_sad002_kodts4r.do", method={RequestMethod.GET, RequestMethod.POST})
	public @ResponseBody List <JsonMaintSadImportKodts4Record>getRecordSad002_kodts4
	  	(@RequestParam String applicationUser, @RequestParam String id) {
		final String METHOD = "[DEBUG] getSpecificRecord_sad002_kodts4r.do ";
		logger.info(METHOD + " Inside...");
		List<JsonMaintSadImportKodts4Record> result = new ArrayList();
	 	//get table
		result = (List)this.maintSadImportGyldigeKoderAjaxHandlerManager.fetchListKodts4(applicationUser, id);
    	
    	return result;
	
	}
	/**
	 * 
	 * @param user
	 * @param result
	 * @param request
	 * @return
	 */
	@RequestMapping(value="getSpecificRecord_sad002_kodts8r.do", method={RequestMethod.GET, RequestMethod.POST})
	public @ResponseBody List <JsonMaintSadImportKodts8Record>getRecordSad002_kodts8
	  	(@RequestParam String applicationUser, @RequestParam String avgId, @RequestParam String skvId) {
		final String METHOD = "[DEBUG] getSpecificRecord_sad002_kodts8r.do ";
		logger.info(METHOD + " Inside...");
		List<JsonMaintSadImportKodts8Record> result = new ArrayList();
	 	//get table
		result = (List)this.maintSadImportGyldigeKoderAjaxHandlerManager.fetchListKodts8(applicationUser, avgId, skvId);
    	
    	return result;
	
	}
	
	
	@Qualifier ("maintSadImportGyldigeKoderAjaxHandlerManager")
	private MaintSadImportGyldigeKoderAjaxHandlerManager maintSadImportGyldigeKoderAjaxHandlerManager;
	@Autowired
	@Required
	public void setMaintSadImportGyldigeKoderAjaxHandlerManager (MaintSadImportGyldigeKoderAjaxHandlerManager value){ this.maintSadImportGyldigeKoderAjaxHandlerManager = value; }
	public MaintSadImportGyldigeKoderAjaxHandlerManager getMaintSadImportGyldigeKoderAjaxHandlerManager(){ return this.maintSadImportGyldigeKoderAjaxHandlerManager; }
	
	
	
	
}

