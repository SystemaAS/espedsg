package no.systema.z.main.maintenance.controller.kund;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Required;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

//application imports
import no.systema.main.model.SystemaWebUser;
import no.systema.main.service.UrlCgiProxyService;
import no.systema.main.util.AppConstants;
import no.systema.main.util.JsonDebugger;
import no.systema.tvinn.sad.z.maintenance.main.util.TvinnSadMaintenanceConstants;
import no.systema.z.main.maintenance.mapper.url.request.UrlRequestParameterMapper;
import no.systema.z.main.maintenance.model.jsonjackson.dbtable.JsonMaintMainCundfContainer;
import no.systema.z.main.maintenance.model.jsonjackson.dbtable.JsonMaintMainCundfRecord;
import no.systema.z.main.maintenance.service.MaintMainCundfService;
import no.systema.z.main.maintenance.service.MaintMainFirmService;
import no.systema.z.main.maintenance.service.MaintMainKodtaKodthService;
import no.systema.z.main.maintenance.service.MaintMainKodtaService;
import no.systema.z.main.maintenance.service.MaintMainKodtaTellService;
import no.systema.z.main.maintenance.service.MaintMainKodtvKodtwService;
//models
import no.systema.z.main.maintenance.url.store.MaintenanceMainUrlDataStore;
import no.systema.z.main.maintenance.util.MainMaintenanceConstants;


/**
 * Gateway for Kunderegister
 * 
 * Listing of Kunder
 * 
 * Edit and create new
 * 
 * 
 * @author Fredrik Möller
 * @date Okt 26, 2016
 * 
 * 	
 */

@Controller
public class MainMaintenanceCundfVkundController {
	private static final Logger logger = Logger.getLogger(MainMaintenanceCundfVkundController.class.getName());
	private ModelAndView loginView = new ModelAndView("login");
	private static final JsonDebugger jsonDebugger = new JsonDebugger();
	
	@RequestMapping(value="mainmaintenancecundf_vkund.do", method={RequestMethod.GET, RequestMethod.POST })
	public ModelAndView mainmaintenancecundf_vkund(HttpSession session, HttpServletRequest request){
		ModelAndView successView = new ModelAndView("mainmaintenancecundf_vkund");
		SystemaWebUser appUser = (SystemaWebUser)session.getAttribute(AppConstants.SYSTEMA_WEB_USER_KEY);
		
		Map model = new HashMap();
		if (appUser == null) {
			return this.loginView;
		} else {

			Collection<JsonMaintMainCundfRecord> list = new ArrayList<JsonMaintMainCundfRecord>();
			list = this.fetchList(appUser.getUser(), null, null);
			model.put("list", list);
			successView.addObject(MainMaintenanceConstants.DOMAIN_MODEL, model);

			return successView;

		}
	}


	@RequestMapping(value="mainmaintenancecundf_vkund_edit.do", method={RequestMethod.GET, RequestMethod.POST })
	public ModelAndView mainmaintenancecundf_vkund_edit(@ModelAttribute ("record") JsonMaintMainCundfRecord recordToValidate, BindingResult bindingResult, HttpSession session, HttpServletRequest request){
		ModelAndView successView = new ModelAndView("mainmaintenancecundf_kunde_edit"); //NOTE: not name correlated jsp, default to Kunde tab
		SystemaWebUser appUser = (SystemaWebUser)session.getAttribute(AppConstants.SYSTEMA_WEB_USER_KEY);
		Map model = new HashMap();
		String action = request.getParameter("action");
		String updateId = request.getParameter("updateId");
		String kundnr = request.getParameter("kundnr");
		String knavn = request.getParameter("knavn");
		String firma = request.getParameter("firma");
		
		
//		logger.info("recordToValidate="+recordToValidate.toString());
		
		//Setting kundnr and firma in session to simplify access when navigating in children
		KundeSessionParams kundeSessionParams = new KundeSessionParams();
		if (kundnr != null && firma != null) {
			kundeSessionParams.setKundnr(kundnr);
			kundeSessionParams.setKnavn(knavn);
			kundeSessionParams.setFirma(firma);
			action = MainMaintenanceConstants.ACTION_UPDATE;  //TODO, se över action, kanske ta bort lite malplacerad...
			kundeSessionParams.setAction(action);  //Here we are in update of existing
		}
		
		session.setAttribute(TvinnSadMaintenanceConstants.KUNDE_SESSION_PARAMS, kundeSessionParams);
		
		if(appUser==null){
			return this.loginView;
		}else{
			if (MainMaintenanceConstants.ACTION_UPDATE.equals(action)){
				
				JsonMaintMainCundfRecord record = this.fetchRecord(appUser.getUser(), kundnr, firma);
				model.put(MainMaintenanceConstants.DOMAIN_RECORD, record);
			
			} 
			
			model.put("action", action);
			model.put("kundnr", kundnr);
			model.put("firma", firma);
			model.put("updateId", updateId);

			successView.addObject(MainMaintenanceConstants.DOMAIN_MODEL, model);

			return successView;
		}

	}
	

	private JsonMaintMainCundfRecord fetchRecord(String applicationUser, String kundnr, String firma) {
		JsonMaintMainCundfRecord record = new JsonMaintMainCundfRecord();
		Collection<JsonMaintMainCundfRecord> recordList = fetchList(applicationUser, kundnr, firma);
		if (recordList.size() > 1) {
			// abort
			logger.info("Incorrect data when searching for specific CUNDF object, on params kundnr=" + kundnr
					+ ", firma=" + firma);
			return null;
		}
		for (Iterator<JsonMaintMainCundfRecord> iterator = recordList.iterator(); iterator.hasNext();) {
			record = (JsonMaintMainCundfRecord) iterator.next();

		}

		return record;

	}
	
	private Collection<JsonMaintMainCundfRecord> fetchList(String applicationUser, String kundnr, String firma) {
		String BASE_URL = MaintenanceMainUrlDataStore.MAINTENANCE_MAIN_BASE_SYCUNDFR_GET_LIST_URL;
		StringBuilder urlRequestParams = new StringBuilder();
		urlRequestParams.append("user=" + applicationUser);
		if (kundnr != null && firma != null) {
			urlRequestParams.append("&kundnr=" + kundnr);
			urlRequestParams.append("&firma=" + firma);
		}

		logger.info("URL: " + BASE_URL);
		logger.info("PARAMS: " + urlRequestParams.toString());
		logger.info(Calendar.getInstance().getTime() + " CGI-start timestamp");
		String jsonPayload = this.urlCgiProxyService.getJsonContent(BASE_URL, urlRequestParams.toString());
		// debugger
		logger.debug(jsonDebugger.debugJsonPayloadWithLog4J(jsonPayload));
		logger.info(Calendar.getInstance().getTime() + " CGI-end timestamp");
		Collection<JsonMaintMainCundfRecord> list = new ArrayList<JsonMaintMainCundfRecord>();

		if (jsonPayload != null) {
			jsonPayload = jsonPayload.replaceFirst("Customerlist", "customerlist");
			JsonMaintMainCundfContainer container = this.maintMainCundfService.getList(jsonPayload);
			if (container != null) {
				list = container.getList();
			}
		}

		return list;
	}

	
	//Wired - SERVICES
	@Qualifier ("urlCgiProxyService")
	private UrlCgiProxyService urlCgiProxyService;
	@Autowired
	@Required
	public void setUrlCgiProxyService (UrlCgiProxyService value){ this.urlCgiProxyService = value; }
	public UrlCgiProxyService getUrlCgiProxyService(){ return this.urlCgiProxyService; }
	
	@Qualifier ("maintMainCundfService")
	private MaintMainCundfService maintMainCundfService;
	@Autowired
	@Required
	public void setMaintMainCundfService (MaintMainCundfService value){ this.maintMainCundfService = value; }
	public MaintMainCundfService getMaintMainCundfService(){ return this.maintMainCundfService; }

		
}

