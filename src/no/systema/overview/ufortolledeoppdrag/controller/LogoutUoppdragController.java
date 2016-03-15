package no.systema.overview.ufortolledeoppdrag.controller;

import java.io.File;
import java.util.Calendar;

import javax.servlet.http.HttpServletRequest;
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
import no.systema.main.session.SessionMgr;

import no.systema.overview.ufortolledeoppdrag.util.UoppdragConstants;
import no.systema.overview.util.io.FileManager;

/**
 * 
 * @author oscardelatorre
 * @date Aug 27, 2013
 * 
 */
@Controller
@SessionAttributes(AppConstants.SYSTEMA_WEB_USER_KEY)
@Scope("session")
public class LogoutUoppdragController {
	private static final Logger logger = Logger.getLogger(LogoutUoppdragController.class.getName());
	private SessionMgr sessionMgr = new SessionMgr();
	
	private ModelAndView successView = new ModelAndView("dashboard");
	private ModelAndView loginView = new ModelAndView("login");
	
	
	/**
	 * 
	 * @param session
	 * @param request
	 * @return
	 */
	@RequestMapping(value="logoutUoppdrag.do", method=RequestMethod.GET)
	public ModelAndView logoutUoppdrag(HttpSession session, HttpServletRequest request){
		SystemaWebUser appUser = (SystemaWebUser)session.getAttribute(AppConstants.SYSTEMA_WEB_USER_KEY);
		ModelAndView view = null;
		String sessionId = request.getSession().getId();
		
		if(appUser==null){
			 view = this.loginView;
		}else{
			//clean module garbage
			new FileManager().deleteOldChartFile((File)session.getAttribute(sessionId));
			this.sessionMgr.removeSessionAttributes_UOPP(session);
			//remove filter from session
			session.removeAttribute(sessionId + UoppdragConstants.DOMAIN_SEARCH_FILTER_GATE_CHAR);
			
			logger.info("Logging out from Systema Ufortollede Oppdrag...");
			view = this.successView;
		}
		return view;
	}

	
    
}

