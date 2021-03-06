package no.systema.skat.controller;

import java.util.Calendar;

import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;

//application imports
import no.systema.main.model.SystemaWebUser;
import no.systema.main.util.AppConstants;
import no.systema.skat.util.SkatConstants;


@Controller
@SessionAttributes(AppConstants.SYSTEMA_WEB_USER_KEY)
@Scope("session")
public class LogoutSkatController {
	private static final Logger logger = Logger.getLogger(LogoutSkatController.class.getName());
	private ModelAndView successView = new ModelAndView("dashboard");
	private ModelAndView loginView = new ModelAndView("login");
	
	
	@RequestMapping(value="logoutSkat.do", method=RequestMethod.GET)
	public ModelAndView logoutTds(HttpSession session, HttpServletResponse response){
		SystemaWebUser appUser = (SystemaWebUser)session.getAttribute(AppConstants.SYSTEMA_WEB_USER_KEY);
		ModelAndView view = null;
		
		if(appUser==null){
			 view = this.loginView;
		}else{
			logger.info("Logging out from Systema SKAT Toldsystem...");
			session.removeAttribute(AppConstants.ASPECT_ERROR_MESSAGE);
			//Code managers
			session.removeAttribute(SkatConstants.SESSION_CODE_MANAGER_EXISTS_SKATIMPORT);
			session.removeAttribute(SkatConstants.SESSION_CODE_MANAGER_EXISTS_SKATEXPORT);
			session.removeAttribute(SkatConstants.SESSION_CODE_MANAGER_EXISTS_SKATIMPORT_NCTS);
			session.removeAttribute(SkatConstants.SESSION_CODE_MANAGER_EXISTS_SKATEXPORT_NCTS);
			
			view = this.successView;
		}
		return view;
	}

	
    
}

