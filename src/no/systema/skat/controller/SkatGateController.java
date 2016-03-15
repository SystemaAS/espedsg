package no.systema.skat.controller;

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
import no.systema.skat.url.store.SkatUrlDataStore;
import no.systema.skat.util.SkatConstants;
import no.systema.skat.model.jsonjackson.authorization.JsonSkatAuthorizationContainer;
import no.systema.skat.model.jsonjackson.authorization.JsonSkatAuthorizationRecord;
import no.systema.skat.service.SkatAuthorizationService;
import no.systema.skat.url.store.SkatUrlDataStore;
import no.systema.skat.util.SkatConstants;

//application imports
import no.systema.main.model.SystemaWebUser;
import no.systema.main.util.AppConstants;
//models


/**
 * Gateway to the SKAT Application
 * 
 * 
 * @author oscardelatorre
 * @date Jan 23, 2014
 * 
 * 	
 */

@Controller
public class SkatGateController {
	private static final Logger logger = Logger.getLogger(SkatGateController.class.getName());
	private ModelAndView loginView = new ModelAndView("login");
	
	/**
	 * 
	 * @param user
	 * @param result
	 * @param request
	 * @return
	 * 
	 */
	@RequestMapping(value="skatgate.do", method={RequestMethod.GET, RequestMethod.POST })
	public ModelAndView skatgate(HttpSession session, HttpServletRequest request){
		ModelAndView successView = new ModelAndView("skatgate");
		SystemaWebUser appUser = (SystemaWebUser)session.getAttribute(AppConstants.SYSTEMA_WEB_USER_KEY);
		
		if(appUser==null){
			return this.loginView;
		}else{
			appUser.setActiveMenu("INIT");
			logger.info("Inside method: skatgate");
			logger.info("appUser user:" + appUser.getUser());
			logger.info("appUser lang:" + appUser.getUsrLang());
			logger.info("appUser userAS400:" + appUser.getUserAS400());
			
			String authorizationOn = appUser.getAuthorizedSkatUserAS400();
			String formSubmit = request.getParameter("formSubmit");
			
			if(authorizationOn!=null && !"".equals(authorizationOn)){
				//nothing since user has previously been granted permission.
			}else{
				if(formSubmit!=null && "Y".equals(formSubmit)){
					String BASE_URL = SkatUrlDataStore.SKAT_GET_AUTHORIZATION_CODE;
					//url params
					String urlRequestParamsKeys = this.getRequestUrlKeyParameters(appUser);
					//for debug purposes in GUI
					session.setAttribute(SkatConstants.ACTIVE_URL_RPG_SKAT, BASE_URL  + "==>params: " + urlRequestParamsKeys.toString()); 
					
					logger.info(Calendar.getInstance().getTime() + " CGI-start timestamp");
				    	logger.info("URL: " + BASE_URL);
				    	logger.info("URL PARAMS: " + urlRequestParamsKeys);
				    	//--------------------------------------
				    	//EXECUTE the FETCH (RPG program) here
				    	//--------------------------------------
				    	String jsonPayload = this.urlCgiProxyService.getJsonContent(BASE_URL, urlRequestParamsKeys);
				    	JsonSkatAuthorizationContainer authorizationContainer = null;
				    	if(jsonPayload!=null){
				    		try{
				    			authorizationContainer = this.skatAuthorizationService.getContainer(jsonPayload);
				    			if(authorizationContainer.getErrMsg()!=null && !"".equals(authorizationContainer.getErrMsg())){
				    				session.setAttribute(AppConstants.ASPECT_ERROR_MESSAGE, authorizationContainer.getErrMsg());
					    			session.setAttribute(AppConstants.SYSTEMA_WEB_USER_KEY, appUser);
					    							    				
				    			}else{
				    				//get now the authorization flags for both: SKAT-sign and OK flag = Y
				    				this.updateAppUser(authorizationContainer, appUser);
				    				//session updates
				    				session.setAttribute(AppConstants.SYSTEMA_WEB_USER_KEY, appUser);
				    				session.removeAttribute(AppConstants.ASPECT_ERROR_MESSAGE);
				    				//debug
				    				logger.info("[After returning AS400]sign: " + appUser.getSkatSign());
				    			}
				    		}catch(Exception e){
				    			e.printStackTrace();
				    		}
				    		
				    	}
				    	
					//Debug --> 
				    	logger.info(" --> jsonPayload:" + jsonPayload);
				    	logger.info(Calendar.getInstance().getTime() +  " CGI-end timestamp");
				    	
				}
				
			}

		    	logger.info("Host via HttpServletRequest.getHeader('Host'): " + request.getHeader("Host"));
		    session.setAttribute(AppConstants.ACTIVE_URL_RPG, AppConstants.ACTIVE_URL_RPG_INITVALUE);
		    
			return successView;
			
		}
	}

	/**
	 * 
	 * @param appUser
	 * @return
	 */
	private String getUnauthorizedTdsErrorMessage(SystemaWebUser appUser){
		
		StringBuffer sb = new StringBuffer();
		sb.append("Brugeren: " + appUser.getUserAS400() + " har ikke brugertilladelse for SKAT./n");
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
		urlRequestParamsKeys.append(SkatConstants.URL_CHAR_DELIMETER_FOR_PARAMS_WITH_HTML_REQUEST + "usrAS400=" + appUser.getUserAS400());
		urlRequestParamsKeys.append(SkatConstants.URL_CHAR_DELIMETER_FOR_PARAMS_WITH_HTML_REQUEST + "pwAS400=" + appUser.getPwAS400());
		
		return urlRequestParamsKeys.toString();
	}
	
	/**
	 * 
	 * @param container
	 * @param appUser
	 */
	private void updateAppUser(JsonSkatAuthorizationContainer container, SystemaWebUser appUser){
		for(JsonSkatAuthorizationRecord record : container.getSkatBrugertilladelse()){
			appUser.setAuthorizedSkatUserAS400(record.getOk());
			appUser.setSkatSign(record.getSign());
			
		}
	}
	/**
	 * TEST METHOD
	 * @param container
	 * @param appUser
	 */
	private void updateAppUserFejk(JsonSkatAuthorizationContainer container, SystemaWebUser appUser){
		appUser.setAuthorizedSkatUserAS400("Y");
		appUser.setSkatSign("OT");
		
	}
	
	
	
	
	//Wired - SERVICES
	@Qualifier ("urlCgiProxyService")
	private UrlCgiProxyService urlCgiProxyService;
	@Autowired
	@Required
	public void setUrlCgiProxyService (UrlCgiProxyService value){ this.urlCgiProxyService = value; }
	public UrlCgiProxyService getUrlCgiProxyService(){ return this.urlCgiProxyService; }
	
	
	@Qualifier ("skatAuthorizationService")
	private SkatAuthorizationService skatAuthorizationService;
	@Autowired
	@Required
	public void setSkatAuthorizationService (SkatAuthorizationService value) { this.skatAuthorizationService = value; }
	public SkatAuthorizationService getSkatAuthorizationService(){ return this.skatAuthorizationService; }
		
}

