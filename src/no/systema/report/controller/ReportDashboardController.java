package no.systema.report.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;

import no.systema.jservices.common.util.StringUtils;
import no.systema.main.model.SystemaWebUser;
import no.systema.main.util.AppConstants;

@Controller
@SessionAttributes(AppConstants.SYSTEMA_WEB_USER_KEY)
@Scope("session")
public class ReportDashboardController {
	private static final Logger logger = Logger.getLogger(ReportDashboardController.class.getName());
	private ModelAndView loginView = new ModelAndView("login");

	/**
	 * @Example
	 * 		http://localhost:8080/espedsg/report_dashboard.do?report=trafikkregnskap
	 * 
	 * @param session
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "report_dashboard.do", method={RequestMethod.GET, RequestMethod.POST})
	public ModelAndView doReportDashboard(HttpSession session, HttpServletRequest request) {
		SystemaWebUser appUser = (SystemaWebUser)session.getAttribute(AppConstants.SYSTEMA_WEB_USER_KEY);
		ModelAndView successView = new ModelAndView("report_trafikkregnskap"); //default trafikkregnskap
		logger.info("Inside: doReportDashboard");
		
		String report = request.getParameter("report");
		logger.info("report="+report);
		
		if(appUser==null){
			return loginView;
		}else{
			if (StringUtils.hasValue(report)) {
				successView.setViewName(report);
			} 
			
			return successView;
		}
	}

}
