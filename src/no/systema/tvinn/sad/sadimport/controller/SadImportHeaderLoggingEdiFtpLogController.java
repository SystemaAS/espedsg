package no.systema.tvinn.sad.sadimport.controller;

import java.util.*;


import org.apache.log4j.Logger;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Required;
import org.springframework.context.annotation.Scope;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;


import org.springframework.web.bind.ServletRequestDataBinder;
import org.springframework.web.bind.WebDataBinder;
//import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;

//import no.systema.tds.service.MainHdTopicService;
import no.systema.main.service.UrlCgiProxyService;
import no.systema.main.service.general.EdiFtpLogService;
import no.systema.main.model.SystemaWebUser;
import no.systema.main.util.AppConstants;
import no.systema.main.util.JsonDebugger;
import no.systema.main.url.store.MainUrlDataStore;

import no.systema.main.mapper.url.request.UrlRequestParameterMapper;
import no.systema.main.model.jsonjackson.general.JsonEdiFtpLogContainer;
import no.systema.main.model.jsonjackson.general.JsonEdiFtpLogRecord;
import no.systema.main.url.store.MainUrlDataStore;


/**
 * Edi Ftp Log Controller
 * 
 * @author oscardelatorre
 * @date May 18, 2016
 * 
 */

@Controller
//@SessionAttributes(AppConstants.SYSTEMA_WEB_USER_KEY)
@Scope("session")
public class SadImportHeaderLoggingEdiFtpLogController {
	private static final Logger logger = Logger.getLogger(SadImportHeaderLoggingEdiFtpLogController.class.getName());
	private static final JsonDebugger jsonDebugger = new JsonDebugger();
	
	private UrlRequestParameterMapper urlRequestParameterMapper = new UrlRequestParameterMapper();
	private ModelAndView loginView = new ModelAndView("login");
	

	@InitBinder
    protected void initBinder(WebDataBinder binder) {

    }
	/**
	 * 
	 * @param session
	 * @param request
	 * @return
	 */
	@RequestMapping(value="tvinnsadimport_logging_ediftplog.do", method = RequestMethod.GET)
	public ModelAndView doEdiFtpLogList(HttpSession session, HttpServletRequest request){
		ModelAndView successView = new ModelAndView("tvinnsadimport_logging_ediftplog");
		SystemaWebUser appUser = (SystemaWebUser)session.getAttribute(AppConstants.SYSTEMA_WEB_USER_KEY);
		
		String sssn = request.getParameter("sssn");
		String level = request.getParameter("ftplev");
		Map model = new HashMap();
		
		if(appUser==null){
			return this.loginView;
		}else{
			String BASE_URL = MainUrlDataStore.SYSTEMA_EDI_FTP_LOG_FETCH_LIST_URL;
			String urlRequestParamsKeys = "user=" + appUser.getUser() + "&sssn=" + sssn;
			logger.info(Calendar.getInstance().getTime() + " CGI-start timestamp");
			logger.info("URL: " + BASE_URL);
			logger.info("URL PARAMS: " + urlRequestParamsKeys);
	    	//--------------------------------------
	    	//EXECUTE the FETCH (RPG program) here
	    	//--------------------------------------
	    	String jsonPayload = this.urlCgiProxyService.getJsonContent(BASE_URL, urlRequestParamsKeys);
	    	//Debug --> 
	    	logger.info("jsonPayload: " + jsonDebugger.debugJsonPayloadWithLog4J(jsonPayload));
	    	logger.info(Calendar.getInstance().getTime() +  " CGI-end timestamp");
	    	
	    	if(jsonPayload!=null){
	    		JsonEdiFtpLogContainer container = this.ediFtpLogService.getContainer(jsonPayload);
	    		List<JsonEdiFtpLogRecord> list = (List)container.getList();
	    		for(JsonEdiFtpLogRecord record : list){
	    			model.put(AppConstants.DOMAIN_RECORD, record);
	    			model.put(AppConstants.DOMAIN_LIST, list);
	    		}
	    	}
	    	model.put("level", level);
			successView.addObject(AppConstants.DOMAIN_MODEL , model);
			successView.addObject(AppConstants.DOMAIN_LIST,new ArrayList());
			logger.info("on method...");
	    	return successView;
		}
	}
	
	
	
	
	//SERVICES
	@Qualifier ("urlCgiProxyService")
	private UrlCgiProxyService urlCgiProxyService;
	@Autowired
	@Required
	public void setUrlCgiProxyService (UrlCgiProxyService value){ this.urlCgiProxyService = value; }
	public UrlCgiProxyService getUrlCgiProxyService(){ return this.urlCgiProxyService; }
	
	
	@Qualifier ("ediFtpLogService")
	private EdiFtpLogService ediFtpLogService;
	@Autowired
	public void setEdiFtpLogService (EdiFtpLogService value){ this.ediFtpLogService=value; }
	public EdiFtpLogService getEdiFtpLogService(){return this.ediFtpLogService;}
	
	 
}

