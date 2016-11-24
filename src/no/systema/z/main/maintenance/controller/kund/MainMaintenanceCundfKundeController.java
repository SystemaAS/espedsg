package no.systema.z.main.maintenance.controller.kund;

import java.util.Calendar;
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
import no.systema.z.main.maintenance.model.jsonjackson.dbtable.JsonMaintMainCundcRecord;
import no.systema.z.main.maintenance.model.jsonjackson.dbtable.JsonMaintMainCundfContainer;
import no.systema.z.main.maintenance.model.jsonjackson.dbtable.JsonMaintMainCundfRecord;
import no.systema.z.main.maintenance.service.MaintMainCundfService;
//models
import no.systema.z.main.maintenance.url.store.MaintenanceMainUrlDataStore;
import no.systema.z.main.maintenance.util.MainMaintenanceConstants;
import no.systema.z.main.maintenance.validator.MaintMainCundfValidator;


/**
 * Gateway for Kunde detaljer
 * 
 * 
 * @author Fredrik Möller
 * @date Okt 26, 2016
 * 
 * 	
 */

@Controller
public class MainMaintenanceCundfKundeController {
	private static final Logger logger = Logger.getLogger(MainMaintenanceCundfKundeController.class.getName());
	private ModelAndView loginView = new ModelAndView("login");
	private static final JsonDebugger jsonDebugger = new JsonDebugger();
	private UrlRequestParameterMapper urlRequestParameterMapper = new UrlRequestParameterMapper();
	

	@RequestMapping(value="mainmaintenancecundf_kunde_edit.do", method={RequestMethod.GET, RequestMethod.POST })
	public ModelAndView mainmaintenancecundf_vkund_edit(@ModelAttribute ("record") JsonMaintMainCundfRecord recordToValidate, BindingResult bindingResult, HttpSession session, HttpServletRequest request){
		ModelAndView successView = new ModelAndView("mainmaintenancecundf_kunde_edit");
		SystemaWebUser appUser = (SystemaWebUser)session.getAttribute(AppConstants.SYSTEMA_WEB_USER_KEY);
		Map model = new HashMap();
		String action = request.getParameter("action");
		
		if (appUser == null) {
			return this.loginView;
		} else {

			KundeSessionParams kundeSessionParams = null;
			kundeSessionParams = (KundeSessionParams)session.getAttribute(TvinnSadMaintenanceConstants.KUNDE_SESSION_PARAMS);
		
			adjustRecordToValidate(recordToValidate, kundeSessionParams);

			//TODO: Create new

			if (MainMaintenanceConstants.ACTION_UPDATE.equals(action)) {  //Update
				// Validate
				MaintMainCundfValidator validator = new MaintMainCundfValidator();
				validator.validate(recordToValidate, bindingResult);
				if (bindingResult.hasErrors()) {
					logger.info("[ERROR Validation] Record does not validate)");
					model.put(MainMaintenanceConstants.DOMAIN_RECORD, recordToValidate);

				} else {
					StringBuffer errMsg = new StringBuffer();
					int dmlRetval = 0;

					dmlRetval = this.updateRecord(appUser.getUser(), recordToValidate, MainMaintenanceConstants.MODE_UPDATE, errMsg);
					// check for Update errors
					if (dmlRetval < 0) {
						logger.info("[ERROR Validation] Record does not validate)");
						model.put(MainMaintenanceConstants.ASPECT_ERROR_MESSAGE, errMsg.toString());
						model.put(MainMaintenanceConstants.DOMAIN_RECORD, recordToValidate);
					} else {
						JsonMaintMainCundfRecord record = this.fetchRecord(appUser.getUser(),
								recordToValidate.getKundnr(), recordToValidate.getFirma());
						model.put(MainMaintenanceConstants.DOMAIN_RECORD, record);
					}
				}

			} else { // Fetch

				JsonMaintMainCundfRecord record = fetchRecord(appUser.getUser(), kundeSessionParams.getKundnr(), kundeSessionParams.getFirma());
				model.put(MainMaintenanceConstants.DOMAIN_RECORD, record);

			}

			model.put("action", kundeSessionParams.getAction());
			model.put("kundnr", kundeSessionParams.getKundnr());
			model.put("firma", kundeSessionParams.getFirma());

			successView.addObject(MainMaintenanceConstants.DOMAIN_MODEL, model);
			successView.addObject("tab_knavn_display", getTrimmedKnav(kundeSessionParams.getKnavn()));

			return successView;		
		
		}

	}
	
	//TODO: more to singel point
	private String getTrimmedKnav(String knavn) {
		StringBuilder knavn_display = new StringBuilder();
		int maxLenght = 10;
		if (knavn.length() > maxLenght) {
			knavn_display.append(knavn.substring(0, maxLenght));
			knavn_display.append("...");
			return knavn_display.toString();
		} else {
			return knavn;
		}
	}
	
	
	private JsonMaintMainCundfRecord fetchRecord(String applicationUser, String kundnr, String firma) {
		String BASE_URL = MaintenanceMainUrlDataStore.MAINTENANCE_MAIN_BASE_SYCUNDFR_GET_LIST_URL;
		StringBuilder urlRequestParams = new StringBuilder();
		urlRequestParams.append("user=" + applicationUser);
		urlRequestParams.append("&kundnr=" + kundnr);
		urlRequestParams.append("&firma=" + firma);

		logger.info("URL: " + BASE_URL);
		logger.info("PARAMS: " + urlRequestParams.toString());
		logger.info(Calendar.getInstance().getTime() + " CGI-start timestamp");
		String jsonPayload = this.urlCgiProxyService.getJsonContent(BASE_URL, urlRequestParams.toString());
		// debugger
		logger.debug(jsonDebugger.debugJsonPayloadWithLog4J(jsonPayload));
		logger.info(Calendar.getInstance().getTime() + " CGI-end timestamp");
		JsonMaintMainCundfRecord record = null;
		if (jsonPayload != null) {
			jsonPayload = jsonPayload.replaceFirst("Customerlist", "customerlist");
			JsonMaintMainCundfContainer container = this.maintMainCundfService.getList(jsonPayload);
			if (container != null) {
	
				for (Iterator<JsonMaintMainCundfRecord> iterator = container.getList().iterator(); iterator.hasNext();) {
					record = (JsonMaintMainCundfRecord) iterator.next();

				}
		
			}

		}

		return record;
	}
	

	private int updateRecord(String applicationUser, JsonMaintMainCundfRecord record, String mode,
			StringBuffer errMsg) {
		int retval = 0;

		String BASE_URL = MaintenanceMainUrlDataStore.MAINTENANCE_MAIN_BASE_SYCUNDFR_DML_UPDATE_URL;
		String urlRequestParamsKeys = "user=" + applicationUser + "&mode=" + mode;
		String urlRequestParams = this.urlRequestParameterMapper.getUrlParameterValidString((record));
		// put the final valid param. string
		urlRequestParams = urlRequestParamsKeys + urlRequestParams;

		logger.info(Calendar.getInstance().getTime() + " CGI-start timestamp");
		logger.info("URL: " + jsonDebugger.getBASE_URL_NoHostName(BASE_URL));
		logger.info("URL PARAMS: " + urlRequestParams);
		String jsonPayload = this.urlCgiProxyService.getJsonContent(BASE_URL, urlRequestParams);

		// extract
		if (jsonPayload != null) {
			// lists
			JsonMaintMainCundfContainer container = this.maintMainCundfService.doUpdate(jsonPayload);
			if (container != null) {
				if (container.getErrMsg() != null && !"".equals(container.getErrMsg())) {
					if (container.getErrMsg().toUpperCase().startsWith("ERROR")) {
						errMsg.append(container.getErrMsg());
						retval = MainMaintenanceConstants.ERROR_CODE;
					}
				}
			}
		}
		
		return retval;
	}
	
	
	private void adjustRecordToValidate(JsonMaintMainCundfRecord recordToValidate, KundeSessionParams kundeSessionParams) {
		recordToValidate.setFirma(kundeSessionParams.getFirma());
		recordToValidate.setKundnr(kundeSessionParams.getKundnr());
		recordToValidate.setSonavn(kundeSessionParams.getSonavn());
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

