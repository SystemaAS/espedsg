package no.systema.overview.ufortolledeoppdrag.controller;

import java.util.*;

import org.apache.log4j.Logger;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Required;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Scope;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.web.bind.WebDataBinder;


//application imports
import no.systema.main.service.UrlCgiProxyService;
import no.systema.main.util.AppConstants;
import no.systema.main.util.JsonDebugger;

import no.systema.main.model.SystemaWebUser;

import no.systema.overview.ufortolledeoppdrag.model.jsonjackson.JsonRenderSpecificTopicContainer;

import no.systema.overview.ufortolledeoppdrag.service.UoppdragService;
import no.systema.overview.ufortolledeoppdrag.url.store.UrlDataStore;
import no.systema.overview.ufortolledeoppdrag.util.UoppdragConstants;

/**
 * 
 * Uoppdrag Archive Controller 
 * 
 * @author oscardelatorre
 * @date Okt 15, 2013
 * 
 * 
 */

@Controller
public class UoppdragRenderController {
	
	private static final Logger logger = Logger.getLogger(UoppdragRenderController.class.getName());
	private JsonDebugger jsonDebugger = new JsonDebugger();
	
	private ModelAndView loginView = new ModelAndView("login");
	private ApplicationContext context;
	
	@InitBinder
    protected void initBinder(WebDataBinder binder) {
		//binder.setValidator(new TdsExportValidator()); //it must have spring form tags in the html otherwise = meaningless
    }
	
	
	
	
	/**
	 * Renders the GUI view
	 * 
	 * @param session
	 * @param request
	 * @return
	 */
	@RequestMapping(value="uoppdrag_render.do",  method= RequestMethod.GET)
	public ModelAndView doShowTopic(HttpSession session, HttpServletRequest request){
		Map model = new HashMap();
		SystemaWebUser appUser = (SystemaWebUser)session.getAttribute(AppConstants.SYSTEMA_WEB_USER_KEY);
		//String messageFromContext = this.context.getMessage("user.label",new Object[0], request.getLocale());
		ModelAndView successView = new ModelAndView("uoppdrag_render");
		logger.info("Method: doShowTopic [RequestMapping-->uoppdrag_render.do]");
		
		String opd = request.getParameter("opd");
		String avd = request.getParameter("avd");
		
		String uoType = request.getParameter("uoType");
		String startParam = request.getParameter("llim");
		String endParam = request.getParameter("ulim");
		String listSize = request.getParameter("lsize");
		String antHfakt = request.getParameter("antHfakt");
		String status = request.getParameter("status");
		//
		model.put("antHfakt", antHfakt); //archive
		model.put("status", status); //tvinn-log (EDI)
		
		//
		logger.info("Avd:" + avd);
		logger.info("Opd:" + opd);

		//check user (should be in session already)
		if(appUser==null){
			return loginView;
			
		}else{
			//---------------------------
			//get BASE URL = RPG-PROGRAM
            //---------------------------
			String BASE_URL = UrlDataStore.UOPPDRAG_BASE_RENDER_SPECIFIC_TOPIC_URL;
			//url params
			String urlRequestParamsKeys = this.getRequestUrlKeyParameters(avd, opd, appUser);
			//for debug purposes in GUI
			session.setAttribute(UoppdragConstants.ACTIVE_URL_RPG, BASE_URL  + "==>params: " + urlRequestParamsKeys.toString()); 
			
			logger.info(Calendar.getInstance().getTime() + " CGI-start timestamp");
		    	logger.info("URL: " + BASE_URL);
		    	logger.info("URL PARAMS: " + urlRequestParamsKeys);
		    	//--------------------------------------
		    	//EXECUTE the FETCH (RPG program) here
		    	//--------------------------------------
		    	String jsonPayload = this.urlCgiProxyService.getJsonContent(BASE_URL, urlRequestParamsKeys);
				//Debug --> 
		    this.jsonDebugger.debugJsonPayload(jsonPayload);
		    	logger.info(Calendar.getInstance().getTime() +  " CGI-end timestamp");
		    	
		    	if(jsonPayload!=null){
		    		JsonRenderSpecificTopicContainer container = this.uoppdragService.getRenderSpecificTopicContainer(jsonPayload);
		    		//add domain objects here
		    		this.setDomainObjectsInView(model, container);

		    		if(uoType!=null && !"".equals(uoType)){
		    			model.put(UoppdragConstants.DOMAIN_LIST_SIZE, listSize);
			    		model.put(UoppdragConstants.DOMAIN_LIST_TYPE, uoType);
	    				model.put(UoppdragConstants.DOMAIN_LIMIT_LOWER, startParam);
	    				model.put(UoppdragConstants.DOMAIN_LIMIT_UPPER, endParam);
	    				model.put(UoppdragConstants.DOMAIN_LIST_INTERVAL, startParam + " - " + endParam);
	    				
		    		}
		    		successView.addObject(UoppdragConstants.DOMAIN_MODEL, model);
		    		
		    	}else{
				logger.fatal("NO CONTENT on jsonPayload from URL... ??? <Null>");
				return loginView;
			}
			
	   		
		}
		
		return successView;
	}
	
	/**
	 * 
	 * @param avd
	 * @param opd
	 * @param appUser
	 * @return
	 */
	private String getRequestUrlKeyParameters(String avd, String opd, SystemaWebUser appUser){
		StringBuffer urlRequestParamsKeys = new StringBuffer();
		//String action = request.getParameter("action");
		urlRequestParamsKeys.append("user=" + appUser.getUser());
		urlRequestParamsKeys.append(UoppdragConstants.URL_CHAR_DELIMETER_FOR_PARAMS_WITH_HTML_REQUEST + "avd=" + avd);
		urlRequestParamsKeys.append(UoppdragConstants.URL_CHAR_DELIMETER_FOR_PARAMS_WITH_HTML_REQUEST + "opd=" + opd);
		
		return urlRequestParamsKeys.toString();
	}
	
	/**
	 * 
	 * 
	 * @param model
	 * @param jsonTdsExportSpecificTopicRecord
	 */
	private void setDomainObjectsInView(Map model, JsonRenderSpecificTopicContainer container){
		model.put(UoppdragConstants.DOMAIN_CONTAINER, container);
	}
	
			
	//SERVICES
	@Qualifier ("urlCgiProxyService")
	private UrlCgiProxyService urlCgiProxyService;
	@Autowired
	@Required
	public void setUrlCgiProxyService (UrlCgiProxyService value){ this.urlCgiProxyService = value; }
	public UrlCgiProxyService getUrlCgiProxyService(){ return this.urlCgiProxyService; }
	
	
	@Qualifier ("uoppdragService")
	private UoppdragService uoppdragService;
	@Autowired
	@Required
	public void setUoppdragService (UoppdragService value){ this.uoppdragService = value; }
	public UoppdragService getUoppdragService(){ return this.uoppdragService; }
	
	
	
}

