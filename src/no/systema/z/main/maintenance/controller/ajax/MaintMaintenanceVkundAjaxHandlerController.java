package no.systema.z.main.maintenance.controller.ajax;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Required;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttributes;

import no.systema.jservices.common.brreg.proxy.entities.Enhet;
import no.systema.jservices.common.dto.SyparfDto;
import no.systema.jservices.common.json.JsonDtoContainer;
import no.systema.jservices.common.json.JsonReader;
import no.systema.main.service.UrlCgiProxyService;
import no.systema.main.util.AppConstants;
import no.systema.main.util.JsonDebugger;
import no.systema.z.main.maintenance.controller.kund.VkundControllerUtil;
import no.systema.z.main.maintenance.model.jsonjackson.dbtable.JsonMaintMainCundcContainer;
import no.systema.z.main.maintenance.model.jsonjackson.dbtable.JsonMaintMainCundcRecord;
import no.systema.z.main.maintenance.model.jsonjackson.dbtable.JsonMaintMainSyparfContainer;
import no.systema.z.main.maintenance.model.jsonjackson.dbtable.JsonMaintMainSyparfRecord;
import no.systema.z.main.maintenance.service.MaintMainCundcService;
import no.systema.z.main.maintenance.service.MaintMainSyparfService;
import no.systema.z.main.maintenance.url.store.MaintenanceMainUrlDataStore;

/**
 * Vedlikehold Firmanivå Kunderegister AJAX Controller
 * 
 * @author Fredrik Möller
 * @date Nov 7, 2016
 * 
 */

@Controller
@SessionAttributes(AppConstants.SYSTEMA_WEB_USER_KEY)
@Scope("session")
public class MaintMaintenanceVkundAjaxHandlerController {
	private static final JsonDebugger jsonDebugger = new JsonDebugger();
	private static final Logger logger = Logger.getLogger(MaintMaintenanceVkundAjaxHandlerController.class.getName());


	@RequestMapping(value = "getSpecificRecord_syparf.do", method = { RequestMethod.GET, RequestMethod.POST })
	public @ResponseBody List<JsonMaintMainSyparfRecord> getRecordSyparf(@RequestParam String applicationUser, @RequestParam String sykunr, String syrecn) {
		final String METHOD = "[DEBUG] getSpecificRecord_syparf ";
		logger.info(METHOD + " applicationUser=" + applicationUser + ", sykunr=" + sykunr + ", syrecn=" + syrecn);

		return (List<JsonMaintMainSyparfRecord>) fetchSpecificSyparf(applicationUser, sykunr, syrecn);
	}	
	
	
	@RequestMapping(value = "getSpecificRecord_cundc.do", method = { RequestMethod.GET, RequestMethod.POST })
	public @ResponseBody List<JsonMaintMainCundcRecord> getRecordCundc(@RequestParam String applicationUser, @RequestParam String cfirma, String ccompn, String cconta, String ctype) {
		final String METHOD = "[DEBUG] getSpecificRecord_cundc ";
		logger.info(METHOD + " applicationUser=" + applicationUser + ", cfirma=" + cfirma + ", ccompn=" + ccompn+ ", cconta="+cconta+", ctype="+ctype);

		return (List<JsonMaintMainCundcRecord>) fetchSpecificCundc(applicationUser, cfirma, ccompn, cconta, ctype);
	}

	@RequestMapping(value = "getSpecificRecord_enhet_brreg.do", method = { RequestMethod.GET, RequestMethod.POST })
	public @ResponseBody Collection<Enhet> getRecordHovedEnhetBrreg(@RequestParam String applicationUser, @RequestParam String orgnr) {
		VkundControllerUtil util = new VkundControllerUtil(urlCgiProxyService);
		final String METHOD = "[DEBUG] getSpecificRecord_enhet_brreg ";
		logger.info(METHOD + " applicationUser=" + applicationUser + ", orgnr=" + orgnr );

		Collection<Enhet> list =  util.fetchSpecificEnhet(applicationUser, orgnr);
		
		return list;
	}


	private List<JsonMaintMainSyparfRecord> fetchSpecificSyparf(String appUser, String sykunr, String syrecn) {
		JsonReader<JsonDtoContainer<SyparfDto>> jsonReader = new JsonReader<JsonDtoContainer<SyparfDto>>();
		jsonReader.set(new JsonDtoContainer<SyparfDto>());
		String BASE_URL = MaintenanceMainUrlDataStore.MAINTENANCE_MAIN_BASE_SYPARF_GET_URL;
		StringBuilder urlRequestParams = new StringBuilder();
		urlRequestParams.append("user=" + appUser);
		urlRequestParams.append("&sykunr=" + sykunr);
		urlRequestParams.append("&syrecn=" + syrecn);
		
		logger.info("URL: " + BASE_URL);
		logger.info("PARAMS: " + urlRequestParams.toString());
		String jsonPayload = urlCgiProxyService.getJsonContent(BASE_URL, urlRequestParams.toString());
		logger.info("jsonPayload=" + jsonPayload);
		List<JsonMaintMainSyparfRecord> list = new ArrayList<JsonMaintMainSyparfRecord>();

 		JsonMaintMainSyparfContainer container = maintMainSyparfService.getContainer(jsonPayload);
		if (container != null) {
			for (JsonMaintMainSyparfRecord syparfDto : container.getDtoList()) {
				list.add(syparfDto);
			}
		}		
		
		return list;
	}		
	
	private Collection<JsonMaintMainCundcRecord> fetchSpecificCundc(String applicationUser, String cfirma, String ccompn, String cconta, String ctype) {
		String BASE_URL = MaintenanceMainUrlDataStore.MAINTENANCE_MAIN_BASE_CUNDC_GET_LIST_URL;
		StringBuilder urlRequestParams = new StringBuilder();
		urlRequestParams.append("user=" + applicationUser);
		urlRequestParams.append("&cfirma=" + cfirma);
		urlRequestParams.append("&ccompn=" + ccompn);
		urlRequestParams.append("&cconta=" + cconta);
		urlRequestParams.append("&ctype=" + ctype);
		

		logger.info("URL: " + BASE_URL);
		logger.info("PARAMS: " + urlRequestParams.toString());
		logger.info(Calendar.getInstance().getTime() + " CGI-start timestamp");
		String jsonPayload = this.urlCgiProxyService.getJsonContent(BASE_URL, urlRequestParams.toString());
		// debugger
		logger.debug(jsonDebugger.debugJsonPayloadWithLog4J(jsonPayload));
		logger.info(Calendar.getInstance().getTime() + " CGI-end timestamp");
		List<JsonMaintMainCundcRecord> list = new ArrayList();
		if (jsonPayload != null) {
			JsonMaintMainCundcContainer container = maintMainCundcService.getList(jsonPayload);
			if (container != null) {
				list = (List) container.getList();
/*				for (JsonMaintMainCundcRecord record : list) {
					logger.info("record=" + record);
				}
*/			}
		}

		return list;
	}

	// SERVICES
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

	@Qualifier("maintMainCundcService")
	private MaintMainCundcService maintMainCundcService;

	@Autowired
	@Required
	public void setMaintMainCundcService(MaintMainCundcService value) {
		this.maintMainCundcService = value;
	}
	
	public MaintMainCundcService getMaintMainCundcService() {
		return this.maintMainCundcService;
	}
	
	@Qualifier ("maintMainSyparfService")
	private MaintMainSyparfService maintMainSyparfService;
	@Autowired
	@Required
	public void setMaintMainSyparfService (MaintMainSyparfService value){ this.maintMainSyparfService = value; }
	public MaintMainSyparfService getMaintMainSyparfService(){ return this.maintMainSyparfService; }		
	
	
	

}
