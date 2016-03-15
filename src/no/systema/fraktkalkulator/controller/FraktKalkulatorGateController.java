package no.systema.fraktkalkulator.controller;

import org.apache.log4j.Logger;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.*;

//application imports
import no.systema.main.model.SystemaWebUser;
import no.systema.main.util.AppConstants;
//models
import no.systema.main.util.DateTimeManager;
import no.systema.sporringoppdrag.util.SporringOppdragConstants;


/**
 * Gateway to the FraktKalkulator Application
 * 
 * 
 * @author oscardelatorre
 * @date Feb 20, 2015
 * 
 * 	
 */

@Controller
public class FraktKalkulatorGateController {
	private static final Logger logger = Logger.getLogger(FraktKalkulatorGateController.class.getName());
	private ModelAndView loginView = new ModelAndView("login");
	private DateTimeManager dateTimeMgr = new DateTimeManager();
	
	/**
	 * 
	 * @param user
	 * @param result
	 * @param request
	 * @return
	 * 
	 */
	@RequestMapping(value="fraktkalkulatorgate.do", method={RequestMethod.GET, RequestMethod.POST })
	public ModelAndView fraktkalkulatorgate (HttpSession session, HttpServletRequest request){
		//ModelAndView successView = new ModelAndView("fraktkalkulatorgate");//this option is when the user MUST click the menu item by him/herself
		ModelAndView successView = new ModelAndView("redirect:fraktkalkulator.do?action=doInit");
		
		SystemaWebUser appUser = (SystemaWebUser)session.getAttribute(AppConstants.SYSTEMA_WEB_USER_KEY);
		Map model = new HashMap();
		if(appUser==null){
			return this.loginView;
		}else{
			//appUser.setActiveMenu("INIT");
			logger.info("Inside method: fraktkalkulatorgate");
			logger.info("appUser user:" + appUser.getUser());
			logger.info("appUser lang:" + appUser.getUsrLang());
			logger.info("appUser userAS400:" + appUser.getUserAS400());
			
		}
	    	logger.info("Host via HttpServletRequest.getHeader('Host'): " + request.getHeader("Host"));
	    session.setAttribute(AppConstants.ACTIVE_URL_RPG, AppConstants.ACTIVE_URL_RPG_INITVALUE);
		return successView;
		
	}
	
}

