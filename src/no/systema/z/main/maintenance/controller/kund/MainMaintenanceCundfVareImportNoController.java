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
import no.systema.z.main.maintenance.mapper.url.request.UrlRequestParameterMapper;
import no.systema.z.main.maintenance.model.jsonjackson.dbtable.JsonMaintMainSadvareContainer;
import no.systema.z.main.maintenance.model.jsonjackson.dbtable.JsonMaintMainSadvareRecord;
import no.systema.z.main.maintenance.service.MaintMainSadvareService;
import no.systema.z.main.maintenance.url.store.MaintenanceMainUrlDataStore;
import no.systema.z.main.maintenance.util.MainMaintenanceConstants;

/**
 * Controller for Import(no) for Vareregister in Kunderegister
 * 
 * 
 * @author Fredrik Möller
 * @date Mar 15, 2017
 * 
 * 
 */

@Controller
public class MainMaintenanceCundfVareImportNoController {
	private static final Logger logger = Logger.getLogger(MainMaintenanceCundfVareImportNoController.class.getName());
	private ModelAndView loginView = new ModelAndView("login");
	private static final JsonDebugger jsonDebugger = new JsonDebugger();
	private UrlRequestParameterMapper urlRequestParameterMapper = new UrlRequestParameterMapper();

	@RequestMapping(value = "mainmaintenancecundf_vareimp_no.do", method = { RequestMethod.GET, RequestMethod.POST })
	public ModelAndView doVareRegister(HttpSession session, HttpServletRequest request) {
		ModelAndView successView = null;
		SystemaWebUser appUser = (SystemaWebUser) session.getAttribute(AppConstants.SYSTEMA_WEB_USER_KEY);
		
		if (appUser == null) {
			return this.loginView;
		} else  {
			KundeSessionParams kundeSessionParams = (KundeSessionParams) session.getAttribute(MainMaintenanceConstants.KUNDE_SESSION_PARAMS);
			String firma = kundeSessionParams.getFirma();
			String kundnr = kundeSessionParams.getKundnr();
			String knavn = kundeSessionParams.getKnavn();
			
			successView = vareImportNo(appUser, kundnr, firma, knavn);
		}

		return successView;
	}

	
	private ModelAndView vareImportNo(SystemaWebUser appUser, String kundnr, String firma, String knavn) {
		ModelAndView successView = new ModelAndView("mainmaintenancecundf_vareimp_no_edit");
		Map<String, Object> model = new HashMap<String, Object>();

		List<JsonMaintMainSadvareRecord> list = new ArrayList<JsonMaintMainSadvareRecord>();
		list = fetchList(appUser.getUser(), kundnr);

		model.put("kundnr", kundnr);
		model.put("firma", firma);
		model.put(MainMaintenanceConstants.DOMAIN_LIST, list);

		successView.addObject(MainMaintenanceConstants.DOMAIN_MODEL, model);
		successView.addObject("tab_knavn_display", VkundControllerUtil.getTrimmedKnav(knavn));

		return successView;
	}	
	
	private List<JsonMaintMainSadvareRecord> fetchList(String applicationUser, String kundnr) {
		String BASE_URL = MaintenanceMainUrlDataStore.MAINTENANCE_MAIN_BASE_SADVARE_GET_URL;
		StringBuffer urlRequestParams = new StringBuffer();
		urlRequestParams.append("user=" + applicationUser);
		urlRequestParams.append("&levenr=" + kundnr);

		logger.info(Calendar.getInstance().getTime() + " CGI-start timestamp");
		logger.info("URL: " + jsonDebugger.getBASE_URL_NoHostName(BASE_URL));
		logger.info("URL PARAMS: " + urlRequestParams);
		String jsonPayload = this.urlCgiProxyService.getJsonContent(BASE_URL, urlRequestParams.toString());
		//logger.info("jsonPayload="+jsonPayload);
		List<JsonMaintMainSadvareRecord> list = null;;
		if (jsonPayload != null) {
			JsonMaintMainSadvareContainer container = maintMainSadvareService.getContainer(jsonPayload);
			if (container != null) {
				list = (List<JsonMaintMainSadvareRecord>) container.getDtoList();
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

	@Qualifier("maintMainSadvareService")
	private MaintMainSadvareService maintMainSadvareService;
	@Autowired
	@Required
	public void setMaintMainSadvareService(MaintMainSadvareService value) {
		this.maintMainSadvareService = value;
	}
	public MaintMainSadvareService getMaintMainSadvareService() {
		return this.maintMainSadvareService;
	}

}
