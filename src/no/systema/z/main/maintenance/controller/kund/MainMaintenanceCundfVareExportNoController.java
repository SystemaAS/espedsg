package no.systema.z.main.maintenance.controller.kund;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Required;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import no.systema.main.model.SystemaWebUser;
import no.systema.main.service.UrlCgiProxyService;
import no.systema.main.util.AppConstants;
import no.systema.main.util.JsonDebugger;
import no.systema.tvinn.sad.z.maintenance.sad.model.jsonjackson.dbtable.JsonMaintSadSadlContainer;
import no.systema.tvinn.sad.z.maintenance.sad.model.jsonjackson.dbtable.JsonMaintSadSadlRecord;
import no.systema.tvinn.sad.z.maintenance.sad.service.MaintSadSadlService;
import no.systema.tvinn.sad.z.maintenance.sadexport.url.store.TvinnSadMaintenanceExportUrlDataStore;
import no.systema.tvinn.sad.z.maintenance.sadimport.service.MaintSadImportSadvareService;
import no.systema.z.main.maintenance.mapper.url.request.UrlRequestParameterMapper;
import no.systema.z.main.maintenance.service.MaintMainSyparfService;
import no.systema.z.main.maintenance.util.MainMaintenanceConstants;

/**
 * Controller for Export(no) for Vareregister in Kunderegister
 * 
 * 
 * @author Fredrik Möller
 * @date Mar 15, 2017
 * 
 * 
 */

@Controller
public class MainMaintenanceCundfVareExportNoController {
	private static final Logger logger = Logger.getLogger(MainMaintenanceCundfVareExportNoController.class.getName());
	private ModelAndView loginView = new ModelAndView("login");
	private static final JsonDebugger jsonDebugger = new JsonDebugger();

	@RequestMapping(value = "mainmaintenancecundf_vareexp_no.do", method = { RequestMethod.GET, RequestMethod.POST })
	public ModelAndView doVareRegister(HttpSession session, HttpServletRequest request) {
		ModelAndView successView = new ModelAndView("mainmaintenancecundf_vareexp_no_edit");
		SystemaWebUser appUser = (SystemaWebUser) session.getAttribute(AppConstants.SYSTEMA_WEB_USER_KEY);
		Map model = new HashMap();
		
		if (appUser == null) {
			return this.loginView;
		} else  {
			KundeSessionParams kundeSessionParams = (KundeSessionParams) session.getAttribute(MainMaintenanceConstants.KUNDE_SESSION_PARAMS);
			String firma = kundeSessionParams.getFirma();
			String kundnr = kundeSessionParams.getKundnr();
			String knavn = kundeSessionParams.getKnavn();
			
			List<JsonMaintSadSadlRecord> list = fetchList(appUser.getUser(), kundnr);

			model.put("kundnr", kundnr);
			model.put("firma", firma);
			model.put(MainMaintenanceConstants.DOMAIN_LIST, list);

			successView.addObject(MainMaintenanceConstants.DOMAIN_MODEL, model);
			successView.addObject("tab_knavn_display", VkundControllerUtil.getTrimmedKnav(knavn));

			return successView;

		}

	}

	private List<JsonMaintSadSadlRecord> fetchList(String applicationUser, String levenr){
		String BASE_URL = TvinnSadMaintenanceExportUrlDataStore.TVINN_SAD_MAINTENANCE_EXPORT_BASE_SAD004R_GET_LIST_URL;
		StringBuffer urlRequestParams = new StringBuffer();
		urlRequestParams.append("user="+ applicationUser);
		urlRequestParams.append("&slknr=" + levenr);
		
		logger.info(Calendar.getInstance().getTime() + " CGI-start timestamp");
    	logger.info("URL: " + jsonDebugger.getBASE_URL_NoHostName(BASE_URL));
    	logger.info("URL PARAMS: " + urlRequestParams);
    	String jsonPayload = this.urlCgiProxyService.getJsonContent(BASE_URL, urlRequestParams.toString());
    	//extract
    	List<JsonMaintSadSadlRecord> list = new ArrayList();
    	if(jsonPayload!=null){
			//lists
    		JsonMaintSadSadlContainer container = this.maintSadSadlService.getList(jsonPayload);
	        if(container!=null){
	        	list = (List)container.getList();
	        }
    	}
    	return list;
    	
	}	
	
	// Wired - SERVICES
	@Qualifier("urlCgiProxyService")
	private UrlCgiProxyService urlCgiProxyService;

	@Autowired
	@Required
	public void setUrlCgiProxyService(UrlCgiProxyService value) {
		this.urlCgiProxyService = value;
	}

	public UrlCgiProxyService getUrlCgiProxyService() {
		return this.urlCgiProxyService;
	}

	@Qualifier("maintMainSyparfService")
	private MaintMainSyparfService maintMainSyparfService;

	@Autowired
	@Required
	public void setMaintMainSyparfService(MaintMainSyparfService value) {
		this.maintMainSyparfService = value;
	}

	public MaintMainSyparfService getMaintMainSyparfService() {
		return this.maintMainSyparfService;
	}

	@Qualifier("maintSadSadlService")
	private MaintSadSadlService maintSadSadlService;

	@Autowired
	@Required
	public void setMaintSadSadlService(MaintSadSadlService value) {
		this.maintSadSadlService = value;
	}

	public MaintSadSadlService getMaintSadSadlService() {
		return this.maintSadSadlService;
	}

}
