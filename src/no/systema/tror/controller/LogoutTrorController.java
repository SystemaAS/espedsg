package no.systema.tror.controller;

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
import no.systema.tror.util.TrorConstants;


@Controller
@SessionAttributes(AppConstants.SYSTEMA_WEB_USER_KEY)
@Scope("session")
public class LogoutTrorController {
	private static final Logger logger = Logger.getLogger(LogoutTrorController.class.getName());
	private ModelAndView successView = new ModelAndView("dashboard");
	private ModelAndView loginView = new ModelAndView("login");
	
	
	@RequestMapping(value="logoutTror.do", method=RequestMethod.GET)
	public ModelAndView logoutTror(HttpSession session, HttpServletResponse response){
		SystemaWebUser appUser = (SystemaWebUser)session.getAttribute(AppConstants.SYSTEMA_WEB_USER_KEY);
		ModelAndView view = null;
		
		if(appUser==null){
			 view = this.loginView;
		}else{
			logger.info("Logging out from Systema Tror ...");
			session.removeAttribute(AppConstants.ASPECT_ERROR_MESSAGE);
			session.removeAttribute(TrorConstants.SESSION_SEARCH_FILTER_TROR);
			//session.removeAttribute(TrorConstants.SESSION_RECORD_ORDER_TROR_LANDIMPORT);
			//session.removeAttribute(TrorConstants.SESSION_RECORD_ORDER_TROR_LANDEXPORT);
			session.removeAttribute(TrorConstants.SESSION_RECORD_ORDER_TROR_LAND);
			session.removeAttribute(TrorConstants.SESSION_RECORD_ORDER_TROR_FLY);
			session.removeAttribute(TrorConstants.SESSION_SUBSYSTEM_ORDER_TROR);
			
			
			
			view = this.successView;
		}
		return view;
	}

	
    
}

