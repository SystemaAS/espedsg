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
import no.systema.main.util.AppConstants;

@Controller
@SessionAttributes(AppConstants.SYSTEMA_WEB_USER_KEY)
@Scope("session")
public class ReportDashboardController {
	private static final Logger logger = Logger.getLogger(ReportDashboardController.class.getName());

	/**
	 * @Example
	 * 		http://localhost:8080/espedsg/report_dashboard.do
	 * 
	 * @param session
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "report_dashboard.do", method = RequestMethod.GET)
	public ModelAndView doReportDashboard(HttpSession session, HttpServletRequest request) {
		ModelAndView successView = new ModelAndView("report_dashboard_SYS");  //default
		logger.info("Inside: doReportDashboard");
		String lib = request.getParameter("lib");
		logger.info("lib="+lib);
		
		if (StringUtils.hasValue(lib)) {
			if (lib.endsWith("DC")){
				successView = new ModelAndView("report_dashboard_DC");  
			} else if (lib.endsWith("GoogleChart")) {
				successView = new ModelAndView("report_dashboard_GoogleChart");
			} else if (lib.endsWith("D3")) {
				successView = new ModelAndView("report_dashboard_D3");
			} else if (lib.endsWith("dt")) {
				successView = new ModelAndView("report_dashboard_dt");
			} else if (lib.endsWith("untappd")) {
				successView = new ModelAndView("report_dashboard_untappd");
			}
		} 
		
		logger.info("successView="+successView);
		
		return successView;
	}

}
