package no.systema.z.main.maintenance.controller.kund;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
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

import no.systema.jservices.common.dao.KodtlkDao;
import no.systema.jservices.common.dao.KodtotyDao;
import no.systema.jservices.common.dao.ValufDao;
import no.systema.jservices.common.json.JsonDtoContainer;
import no.systema.jservices.common.json.JsonReader;
import no.systema.jservices.common.values.FasteKoder;
import no.systema.main.model.SystemaWebUser;
import no.systema.main.service.UrlCgiProxyService;
import no.systema.main.service.UrlCgiProxyServiceImpl;
import no.systema.main.util.AppConstants;
import no.systema.main.util.JsonDebugger;
import no.systema.tvinn.sad.util.TvinnSadConstants;
import no.systema.z.main.maintenance.controller.ChildWindowKode;
import no.systema.z.main.maintenance.mapper.url.request.UrlRequestParameterMapper;
import no.systema.z.main.maintenance.model.jsonjackson.dbtable.JsonMaintMainChildWindowKofastContainer;
import no.systema.z.main.maintenance.model.jsonjackson.dbtable.JsonMaintMainChildWindowKofastRecord;
import no.systema.z.main.maintenance.model.jsonjackson.dbtable.JsonMaintMainCundfContainer;
import no.systema.z.main.maintenance.model.jsonjackson.dbtable.JsonMaintMainCundfRecord;
import no.systema.z.main.maintenance.service.MaintMainChildWindowService;
import no.systema.z.main.maintenance.service.MaintMainCundfService;
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
	private boolean KOFAST_NO_ID = true; 
	private UrlRequestParameterMapper urlRequestParameterMapper = new UrlRequestParameterMapper();
	
	@RequestMapping(value="mainmaintenancecundf_vkund.do", method={RequestMethod.GET, RequestMethod.POST })
	public ModelAndView mainmaintenancecundf_vkund(HttpSession session, HttpServletRequest request){
		ModelAndView successView = new ModelAndView("mainmaintenancecundf_vkund");
		SystemaWebUser appUser = (SystemaWebUser)session.getAttribute(AppConstants.SYSTEMA_WEB_USER_KEY);
		String dbTable = request.getParameter("id");
		String kundnr = request.getParameter("searchKundnr");
		String knavn = request.getParameter("searchKnavn");
		String firma = request.getParameter("firma");
		
		Map model = new HashMap();
		if (appUser == null) {
			return this.loginView;
		} else {

			Collection<JsonMaintMainCundfRecord> list = new ArrayList<JsonMaintMainCundfRecord>();
			//search filter. Note: if both are present then we go for the kundnr.
			if( (kundnr!=null && !"".equals(kundnr)) ){
				list = this.fetchList(appUser.getUser(), kundnr, firma);
			}else if( (knavn!=null && !"".equals(knavn)) ){
				list = this.fetchList(appUser.getUser(), null, null, knavn);
			}
			//set domain objets
	    	model.put("dbTable", dbTable);
	    	model.put("knavn", knavn);
	    	model.put("kundnr", kundnr);
	    	
			model.put("list", list);
			successView.addObject(MainMaintenanceConstants.DOMAIN_MODEL, model);

			return successView;

		}
	}


	@RequestMapping(value="mainmaintenancecundf_vkund_edit.do", method={RequestMethod.GET, RequestMethod.POST })
	public ModelAndView mainmaintenancecundf_vkund_edit(@ModelAttribute ("record") JsonMaintMainCundfRecord recordToValidate, BindingResult bindingResult, HttpSession session, HttpServletRequest request){
		ModelAndView successView = new ModelAndView("mainmaintenancecundf_kunde_edit"); //NOTE: not name correlated jsp, default to Kunde tab
		SystemaWebUser appUser = (SystemaWebUser)session.getAttribute(AppConstants.SYSTEMA_WEB_USER_KEY);
		VkundControllerUtil util = new VkundControllerUtil(urlCgiProxyService);
		Map model = new HashMap();
		String action = request.getParameter("action");
		String updateId = request.getParameter("updateId");
		String kundnr = recordToValidate.getKundnr();
		String knavn = recordToValidate.getKnavn();
		String firma = recordToValidate.getFirma();
		StringBuffer errMsg = new StringBuffer();
		int dmlRetval = 0;

		KundeSessionParams kundeSessionParams = new KundeSessionParams();

		if (appUser == null) {
			return this.loginView;
		} else {

			if (MainMaintenanceConstants.ACTION_UPDATE.equals(action)) { // Open for edit
				kundeSessionParams.setKundnr(kundnr);
				kundeSessionParams.setKnavn(knavn);
				kundeSessionParams.setFirma(firma);
				kundeSessionParams.setSonavn(recordToValidate.getSonavn());

				JsonMaintMainCundfRecord record = this.fetchRecord(appUser.getUser(), kundnr, firma);
				model.put(MainMaintenanceConstants.DOMAIN_RECORD, record);
				
				successView.addObject("tab_knavn_display", VkundControllerUtil.getTrimmedKnav(kundeSessionParams.getKnavn()));

				model.put("kundnr", kundnr);
				model.put("firma", firma);
				model.put("updateId", updateId);

			} else if (MainMaintenanceConstants.ACTION_CREATE.equals(action)) { // Lage ny

			} else if (MainMaintenanceConstants.ACTION_DELETE.equals(action)) { // Delete from list
				dmlRetval = deleteRecord(appUser.getUser(), kundnr, firma, MainMaintenanceConstants.MODE_DELETE, errMsg);
				session.removeAttribute(MainMaintenanceConstants.KUNDE_SESSION_PARAMS);
				if (dmlRetval < 0) {
					logger.info("[ERROR DML] Record does not validate)");
					model.put(MainMaintenanceConstants.ASPECT_ERROR_MESSAGE, errMsg.toString());
					model.put(MainMaintenanceConstants.DOMAIN_RECORD, recordToValidate);
				} else {
					successView = new ModelAndView("redirect:mainmaintenancecundf_vkund.do");
				}
			}

			session.setAttribute(MainMaintenanceConstants.KUNDE_SESSION_PARAMS, kundeSessionParams);
			model.put("action", action);
			successView.addObject(MainMaintenanceConstants.DOMAIN_MODEL, model);

			return successView;
		}

	}

	/**
	 * This method serve as data populater for all child windows for Kunderegister - VKUND.
	 * 
	 * 
	 * @param session
	 * @param request
	 * @return
	 */
	@RequestMapping(value="mainmaintenance_vkund_edit_childwindow_codes.do",  method={RequestMethod.GET} )
	public ModelAndView getCodes(HttpSession session, HttpServletRequest request){
		ModelAndView successView = new ModelAndView("mainmaintenance_vkund_edit_childwindow_codes");
		SystemaWebUser appUser = (SystemaWebUser)session.getAttribute(AppConstants.SYSTEMA_WEB_USER_KEY);
		Map model = new HashMap();
		String caller = request.getParameter("caller");  //Field in jsp
		
		if(appUser==null){
			return this.loginView;
		}else{
			  
			List list = getCodeList(appUser, caller);
			model.put("codeList", list);
			model.put("caller", caller);
			
			successView.addObject(TvinnSadConstants.DOMAIN_MODEL , model);
			
	    	return successView;
		}
	}
		
	private List<ChildWindowKode> getCodeList(SystemaWebUser appUser, String caller) {
		List<ChildWindowKode> list = null;

		if ("ctype".equals(caller)) { // Funksjon
			list = getFunksjonKoder(appUser, KOFAST_NO_ID);
		} else if ("ctype_ref".equals(caller)) {
			list = getFunksjonKoder(appUser, !KOFAST_NO_ID);
		} else if ("valkod".equals(caller)) { //Valutakod
			list = getValkoder(appUser);
		} else if ("syland".equals(caller)) { //Landkode
			list = getLandkoder(appUser);
		} else if ("syopdt".equals(caller)) { //Oppdragstype
			list = getOppdragsTyper(appUser);
		} 
		
		else {
			throw new IllegalArgumentException(caller + "is not supported.");
		}

		return list;
	}
	
	private List<ChildWindowKode> getFunksjonKoder(SystemaWebUser appUser, boolean noId) {
		String BASE_URL = MaintenanceMainUrlDataStore.MAINTENANCE_MAIN_BASE_KOFAST_GET_LIST_URL;
		StringBuffer urlRequestParams = new StringBuffer();
		urlRequestParams.append("user=" + appUser.getUser());
		urlRequestParams.append("&kftyp=" + FasteKoder.FUNKSJON.toString());
		logger.info(BASE_URL);
		logger.info(urlRequestParams);

		UrlCgiProxyService urlCgiProxyService = new UrlCgiProxyServiceImpl();
		String jsonPayload = urlCgiProxyService.getJsonContent(BASE_URL, urlRequestParams.toString());
		JsonMaintMainChildWindowKofastContainer container = null;
		List <ChildWindowKode> kodeList = new ArrayList<ChildWindowKode>();
		ChildWindowKode kode = null;
		try {
			if (jsonPayload != null) {
				container = maintMainChildWindowService.getContainer(jsonPayload);
				if (container != null) {
					for (JsonMaintMainChildWindowKofastRecord record : container.getList()) {
						kode = getChildWindowKode(record, noId);
						kodeList.add(kode);
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return kodeList;
	}

	
	private List<ChildWindowKode> getLandkoder(SystemaWebUser appUser) {
		JsonReader<JsonDtoContainer<KodtlkDao>> jsonReader = new JsonReader<JsonDtoContainer<KodtlkDao>>();
		jsonReader.set(new JsonDtoContainer<KodtlkDao>());
		String BASE_URL = MaintenanceMainUrlDataStore.MAINTENANCE_MAIN_BASE_KODTLK_GET_URL;
		StringBuilder urlRequestParams = new StringBuilder();
		urlRequestParams.append("user=" + appUser.getUser());

		logger.info("URL: " + BASE_URL);
		logger.info("PARAMS: " + urlRequestParams.toString());
		String jsonPayload = urlCgiProxyService.getJsonContent(BASE_URL, urlRequestParams.toString());
		logger.info("jsonPayload="+jsonPayload);
		List <ChildWindowKode> kodeList = new ArrayList<ChildWindowKode>();
		ChildWindowKode kode = null;
		JsonDtoContainer<KodtlkDao> container =  (JsonDtoContainer<KodtlkDao> )jsonReader.get(jsonPayload);
		if (container != null) {
			for (KodtlkDao kodtlkDao : container.getDtoList()) {
				kode = getChildWindowKode(kodtlkDao);
				kodeList.add(kode);
			}
		}
		return kodeList;
	}	

	private ChildWindowKode getChildWindowKode(KodtlkDao kodtlkDao) {
		ChildWindowKode kode = new ChildWindowKode();
		kode.setCode(kodtlkDao.getKlklk());
		kode.setDescription(kodtlkDao.getKlknvn());

		return kode;
	}	
		
	private List<ChildWindowKode> getOppdragsTyper(SystemaWebUser appUser) {
		JsonReader<JsonDtoContainer<KodtotyDao>> jsonReader = new JsonReader<JsonDtoContainer<KodtotyDao>>();
		jsonReader.set(new JsonDtoContainer<KodtotyDao>());
		String BASE_URL = MaintenanceMainUrlDataStore.MAINTENANCE_MAIN_BASE_KODTOTY_GET_URL;
		StringBuilder urlRequestParams = new StringBuilder();
		urlRequestParams.append("user=" + appUser.getUser());

		logger.info("URL: " + BASE_URL);
		logger.info("PARAMS: " + urlRequestParams.toString());
		String jsonPayload = urlCgiProxyService.getJsonContent(BASE_URL, urlRequestParams.toString());
		logger.info("jsonPayload="+jsonPayload);
		List <ChildWindowKode> kodeList = new ArrayList<ChildWindowKode>();
		ChildWindowKode kode = null;
		JsonDtoContainer<KodtotyDao> container =  (JsonDtoContainer<KodtotyDao> )jsonReader.get(jsonPayload);
		if (container != null) {
			for (KodtotyDao kodtotyDao : container.getDtoList()) {
				kode = getChildWindowKode(kodtotyDao);
				kodeList.add(kode);
			}
		}
		return kodeList;
	}	

	private ChildWindowKode getChildWindowKode(KodtotyDao kodtotyDao) {
		ChildWindowKode kode = new ChildWindowKode();
		kode.setCode(kodtotyDao.getKo1kod());
		kode.setDescription(kodtotyDao.getKo1ntx());

		return kode;
	}	
	
	private List<ChildWindowKode> getValkoder(SystemaWebUser appUser) {
		JsonReader<JsonDtoContainer<ValufDao>> jsonReader = new JsonReader<JsonDtoContainer<ValufDao>>();
		jsonReader.set(new JsonDtoContainer<ValufDao>());
		String BASE_URL = MaintenanceMainUrlDataStore.MAINTENANCE_MAIN_BASE_VALUF_GET_URL;
		StringBuilder urlRequestParams = new StringBuilder();
		urlRequestParams.append("user=" + appUser.getUser());

		logger.info("URL: " + BASE_URL);
		logger.info("PARAMS: " + urlRequestParams.toString());
		String jsonPayload = urlCgiProxyService.getJsonContent(BASE_URL, urlRequestParams.toString());
		logger.info("jsonPayload="+jsonPayload);
		List <ChildWindowKode> kodeList = new ArrayList<ChildWindowKode>();
		ChildWindowKode kode = null;
		JsonDtoContainer<ValufDao> container =  (JsonDtoContainer<ValufDao> )jsonReader.get(jsonPayload);
		if (container != null) {
			for (ValufDao valufDao : container.getDtoList()) {
				kode = getChildWindowKode(valufDao);
				kodeList.add(kode);
			}
		}
		return kodeList;
	}	

	private ChildWindowKode getChildWindowKode(ValufDao valufDao) {
		ChildWindowKode kode = new ChildWindowKode();
		kode.setCode(valufDao.getValkod());
		kode.setDescription(valufDao.getValtek());

		return kode;
	}	
	
	
	private ChildWindowKode getChildWindowKode(JsonMaintMainChildWindowKofastRecord record, boolean noId) {
		ChildWindowKode kode = new ChildWindowKode();
		if (noId) {
			kode.setCode("*" + record.getKftxt());
		} else {
			kode.setCode(record.getKfkod());
		}
		kode.setDescription(record.getKftxt());

		return kode;
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
	/**
	 * 
	 * @param applicationUser
	 * @param kundnr
	 * @param firma
	 * @return
	 */
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
		String jsonPayload = this.urlCgiProxyService.getJsonContent(BASE_URL, urlRequestParams.toString());
		Collection<JsonMaintMainCundfRecord> list = new ArrayList<JsonMaintMainCundfRecord>();
		if (jsonPayload != null) {
			jsonPayload = jsonPayload.replaceFirst("Customerlist", "customerlist");
			JsonMaintMainCundfContainer container = this.maintMainCundfService.getList(jsonPayload);
			if (container != null) {
				list = container.getList();
/*		        for(JsonMaintMainCundfRecord record : list){
	        	  logger.info("record:" + record.toString());
	        	}	
*/			}
		}

		return list;
	}

	/**
	 * 
	 * @param applicationUser
	 * @param kundnr
	 * @param firma
	 * @param knavn
	 * @return
	 */
	private Collection<JsonMaintMainCundfRecord> fetchList(String applicationUser, String kundnr, String firma, String knavn) {
		String BASE_URL = MaintenanceMainUrlDataStore.MAINTENANCE_MAIN_BASE_SYCUNDFR_GET_LIST_URL;
		StringBuilder urlRequestParams = new StringBuilder();
		urlRequestParams.append("user=" + applicationUser);
		if (kundnr != null && firma != null) {
			urlRequestParams.append("&kundnr=" + kundnr);
			urlRequestParams.append("&firma=" + firma);
		}else{
			urlRequestParams.append("&knavn=" + knavn);
		}

		logger.info("URL: " + BASE_URL);
		logger.info("PARAMS: " + urlRequestParams.toString());
		String jsonPayload = this.urlCgiProxyService.getJsonContent(BASE_URL, urlRequestParams.toString());
		Collection<JsonMaintMainCundfRecord> list = new ArrayList<JsonMaintMainCundfRecord>();
		if (jsonPayload != null) {
			jsonPayload = jsonPayload.replaceFirst("Customerlist", "customerlist");
			JsonMaintMainCundfContainer container = this.maintMainCundfService.getList(jsonPayload);
			if (container != null) {
				list = container.getList();
/*		        for(JsonMaintMainCundfRecord record : list){
	        	  logger.info("record:" + record.toString());
	        	}	
*/			}
		}

		return list;
	}

	/**
	 * 
	 * @param applicationUser
	 * @param kundnr
	 * @param firma
	 * @param mode
	 * @param errMsg
	 * @return
	 */
	private int deleteRecord(String applicationUser, String kundnr, String firma, String mode, StringBuffer errMsg) {
		int retval = 0;
		String BASE_URL = MaintenanceMainUrlDataStore.MAINTENANCE_MAIN_BASE_SYCUNDFR_DML_UPDATE_URL;
		StringBuilder urlRequestParams = new StringBuilder();
		urlRequestParams.append("user=" + applicationUser);
		urlRequestParams.append("&mode=" + mode);
		urlRequestParams.append("&kundnr=" + kundnr);
		urlRequestParams.append("&firma=" + firma);

		logger.info(Calendar.getInstance().getTime() + " CGI-start timestamp");
		logger.info("URL: " + jsonDebugger.getBASE_URL_NoHostName(BASE_URL));
		logger.info("URL PARAMS: " + urlRequestParams);
		String jsonPayload = this.urlCgiProxyService.getJsonContent(BASE_URL, urlRequestParams.toString());
		if (jsonPayload != null) {
			JsonMaintMainCundfContainer container = this.maintMainCundfService.doUpdate(jsonPayload);
			if (container != null) {
				if (container.getErrMsg() != null && !"".equals(container.getErrMsg())) {
					errMsg.append(container.getErrMsg());
					retval = MainMaintenanceConstants.ERROR_CODE;
				}
			}
		}

		return retval;
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
	
	@Qualifier ("maintMainChildWindowService")
	private MaintMainChildWindowService maintMainChildWindowService;
	@Autowired
	@Required
	public void setMaintMainChildWindowService (MaintMainChildWindowService value){ this.maintMainChildWindowService = value; }
	public MaintMainChildWindowService getMaintMainChildWindowService(){ return this.maintMainChildWindowService; }
	
		
}

