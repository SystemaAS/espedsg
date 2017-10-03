package no.systema.report.controller;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Required;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;

import no.systema.jservices.common.util.StringUtils;
import no.systema.main.model.SystemaWebUser;
import no.systema.main.service.UrlCgiProxyService;
import no.systema.main.util.AppConstants;
import no.systema.transportdisp.util.TransportDispConstants;
import no.systema.tror.service.html.dropdown.TrorDropDownListPopulationService;
import no.systema.tror.util.manager.CodeDropDownMgr;
import no.systema.z.main.maintenance.service.MaintMainKodtaService;

@Controller
@SessionAttributes(AppConstants.SYSTEMA_WEB_USER_KEY)
@Scope("session")
public class ReportDashboardController {
	private static final Logger logger = Logger.getLogger(ReportDashboardController.class.getName());
	private ModelAndView loginView = new ModelAndView("login");
	private CodeDropDownMgr codeDropDownMgr = new CodeDropDownMgr();

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
		Map model = new HashMap();
		logger.info("Inside: doReportDashboard");
		
		String report = request.getParameter("report");
		logger.info("report="+report);
		
		if(appUser==null){
			return loginView;
		}else{

			setCodeDropDownMgr(appUser, model);
			
			
			
			if (StringUtils.hasValue(report)) {
				successView.setViewName(report);
			} 

			successView.addObject(TransportDispConstants.DOMAIN_MODEL, model);			
			
			
			return successView;
		}
	}

	private void setCodeDropDownMgr(SystemaWebUser appUser, Map model){
		this.codeDropDownMgr.populateCodesHtmlDropDownsFromJsonSignature(this.urlCgiProxyService, trorDropDownListPopulationService, model, appUser);
		this.codeDropDownMgr.populateCodesHtmlDropDownsFromJsonAvdelning(this.urlCgiProxyService, maintMainKodtaService, model, appUser);
	}	
	
	@Qualifier ("urlCgiProxyService")
	private UrlCgiProxyService urlCgiProxyService;
	@Autowired
	@Required
	public void setUrlCgiProxyService (UrlCgiProxyService value){ this.urlCgiProxyService = value; }
	public UrlCgiProxyService getUrlCgiProxyService(){ return this.urlCgiProxyService; }	
	
	@Qualifier ("trorDropDownListPopulationService")
	private TrorDropDownListPopulationService trorDropDownListPopulationService;
	@Autowired
	@Required
	public void setTrorDropDownListPopulationService (TrorDropDownListPopulationService value){ this.trorDropDownListPopulationService = value; }
	public TrorDropDownListPopulationService getTrorDropDownListPopulationService(){ return this.trorDropDownListPopulationService; }	

	@Qualifier ("maintMainKodtaService")
	private MaintMainKodtaService maintMainKodtaService;
	@Autowired
	@Required
	public void setMaintMainKodtaService (MaintMainKodtaService value){ this.maintMainKodtaService = value; }
	public MaintMainKodtaService getMaintMainKodtaService(){ return this.maintMainKodtaService; }	
	
	
	
}
