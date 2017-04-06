package no.systema.z.main.maintenance.controller.kund;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import no.systema.main.model.SystemaWebUser;
import no.systema.main.util.AppConstants;
import no.systema.main.util.JsonDebugger;
import no.systema.z.main.maintenance.mapper.url.request.UrlRequestParameterMapper;
import no.systema.z.main.maintenance.util.MainMaintenanceConstants;

/**
 * Gateway for Vare register in Kunderegister
 * 
 * 
 * @author Fredrik Möller
 * @date Mar 14, 2017
 * 
 * 
 */

@Controller
public class MainMaintenanceCundfVareRegisterController {
	private static final Logger logger = Logger.getLogger(MainMaintenanceCundfVareRegisterController.class.getName());
	private ModelAndView loginView = new ModelAndView("login");

	@RequestMapping(value = "mainmaintenancecundf_vareregister.do", method = { RequestMethod.GET, RequestMethod.POST })
	public ModelAndView doVareRegister(HttpSession session, HttpServletRequest request) {
		ModelAndView successView = null;
		SystemaWebUser appUser = (SystemaWebUser) session.getAttribute(AppConstants.SYSTEMA_WEB_USER_KEY);
		
		if (appUser == null) {
			return this.loginView;
		} else { 
			KundeSessionParams kundeSessionParams = (KundeSessionParams) session.getAttribute(MainMaintenanceConstants.KUNDE_SESSION_PARAMS);
			String firma = kundeSessionParams.getFirma();
			String kundnr = kundeSessionParams.getKundnr();
			String knavn = kundeSessionParams.getKnavn();
			
			if ("NO".equals(appUser.getFiland())) {
				successView = new ModelAndView("redirect:mainmaintenancecundf_vareexp_no.do");
			} else if ("DK".equals(appUser.getFiland())) {
				// TODO
			} else if ("SE".equals(appUser.getFiland())) {
				// TODO
			} else { // default Export(no)
				successView = new ModelAndView("redirect:mainmaintenancecundf_vareexp_no.do");
			}
		}

		return successView;
	}


}
