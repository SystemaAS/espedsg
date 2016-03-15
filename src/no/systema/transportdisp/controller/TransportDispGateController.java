package no.systema.transportdisp.controller;

import java.net.InetAddress;
import java.util.*;

import org.apache.log4j.Logger;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Required;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import no.systema.main.service.UrlCgiProxyService;
import no.systema.tvinn.sad.url.store.TvinnSadUrlDataStore;
import no.systema.tvinn.sad.util.TvinnSadConstants;
import no.systema.tvinn.sad.model.jsonjackson.authorization.JsonTvinnSadAuthorizationContainer;
import no.systema.tvinn.sad.model.jsonjackson.authorization.JsonTvinnSadAuthorizationRecord;
import no.systema.tvinn.sad.service.TvinnSadAuthorizationService;

//application imports
import no.systema.main.model.SystemaWebUser;
import no.systema.main.util.AppConstants;
//models


/**
 * Gateway to the TranspDisp Application
 * 
 * 
 * @author oscardelatorre
 * @date Jan 13, 2015
 * 
 * 	
 */

@Controller
public class TransportDispGateController {
	private static final Logger logger = Logger.getLogger(TransportDispGateController.class.getName());
	private ModelAndView loginView = new ModelAndView("login");
	
	/**
	 * 
	 * @param user
	 * @param result
	 * @param request
	 * @return
	 * 
	 */
	@RequestMapping(value="transportdispgate.do", method={RequestMethod.GET, RequestMethod.POST })
	public ModelAndView transportdispgate (HttpSession session, HttpServletRequest request){
		ModelAndView successView = new ModelAndView("transportdispgate");
		SystemaWebUser appUser = (SystemaWebUser)session.getAttribute(AppConstants.SYSTEMA_WEB_USER_KEY);
		
		if(appUser==null){
			return this.loginView;
		}else{
			//appUser.setActiveMenu("INIT");
			logger.info("Inside method: transportdispgate");
			logger.info("appUser user:" + appUser.getUser());
			logger.info("appUser lang:" + appUser.getUsrLang());
			logger.info("appUser userAS400:" + appUser.getUserAS400());
							
		}

	    	logger.info("Host via HttpServletRequest.getHeader('Host'): " + request.getHeader("Host"));
	    session.setAttribute(AppConstants.ACTIVE_URL_RPG, AppConstants.ACTIVE_URL_RPG_INITVALUE);
		    
		return successView;
			
		
	}

	/**
	 * 
	 * @param appUser
	 * @return
	 */
	private String getUnauthorizedTdsErrorMessage(SystemaWebUser appUser){
		
		StringBuffer sb = new StringBuffer();
		sb.append("Brugeren: " + appUser.getUserAS400() + " har ikke brugertilladelse for TVINN./n");
		sb.append("Kontakt din systemadministrator.");
		
		return sb.toString();
	}
	
	/**
	 * 
	 * @param appUser
	 * @return
	 */
	private String getRequestUrlKeyParameters(SystemaWebUser appUser){
		StringBuffer urlRequestParamsKeys = new StringBuffer();
		//String action = request.getParameter("action");
		urlRequestParamsKeys.append("user=" + appUser.getUser());
		urlRequestParamsKeys.append(TvinnSadConstants.URL_CHAR_DELIMETER_FOR_PARAMS_WITH_HTML_REQUEST + "usrAS400=" + appUser.getUserAS400());
		urlRequestParamsKeys.append(TvinnSadConstants.URL_CHAR_DELIMETER_FOR_PARAMS_WITH_HTML_REQUEST + "pwAS400=" + appUser.getPwAS400());
		
		return urlRequestParamsKeys.toString();
	}
	
	/**
	 * 
	 * @param container
	 * @param appUser
	 */
	private void updateAppUser(JsonTvinnSadAuthorizationContainer container, SystemaWebUser appUser){
		for(JsonTvinnSadAuthorizationRecord record : container.getSadtillatelse()){
			appUser.setAuthorizedTvinnSadUserAS400(record.getOk());
			appUser.setTvinnSadSign(record.getSign());
				
		}
	}
	/**
	 * TEST METHOD
	 * @param container
	 * @param appUser
	 */
	private void updateAppUserFejk(JsonTvinnSadAuthorizationContainer container, SystemaWebUser appUser){
		appUser.setAuthorizedTvinnSadUserAS400("Y");
		appUser.setSkatSign("OT");
		
	}
	
	
	//Wired - SERVICES
	@Qualifier ("urlCgiProxyService")
	private UrlCgiProxyService urlCgiProxyService;
	@Autowired
	@Required
	public void setUrlCgiProxyService (UrlCgiProxyService value){ this.urlCgiProxyService = value; }
	public UrlCgiProxyService getUrlCgiProxyService(){ return this.urlCgiProxyService; }
	
	
	@Qualifier ("tvinnSadAuthorizationService")
	private TvinnSadAuthorizationService tvinnSadAuthorizationService;
	@Autowired
	@Required
	public void setTvinnSadAuthorizationService (TvinnSadAuthorizationService value) { this.tvinnSadAuthorizationService = value; }
	public TvinnSadAuthorizationService getTvinnSadAuthorizationService(){ return this.tvinnSadAuthorizationService; }
		
}

