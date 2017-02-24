package no.systema.z.main.maintenance.controller.kund;

import java.util.ArrayList;
import java.util.Collection;
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

import no.systema.ebooking.model.jsonjackson.JsonMainOrderHeaderMessageNoteRecord;
import no.systema.ebooking.model.jsonjackson.JsonMainOrderHeaderRecord;
import no.systema.jservices.common.dao.FratxtDao;
import no.systema.jservices.common.dao.ValufDao;
import no.systema.jservices.common.json.JsonDtoContainer;
import no.systema.jservices.common.json.JsonReader;
//application imports
import no.systema.main.model.SystemaWebUser;
import no.systema.main.service.UrlCgiProxyService;
import no.systema.main.util.AppConstants;
import no.systema.tvinn.sad.z.maintenance.main.util.manager.CodeDropDownMgr;
import no.systema.z.main.maintenance.controller.ChildWindowKode;
import no.systema.z.main.maintenance.service.MaintMainCundcService;
import no.systema.z.main.maintenance.service.MaintMainKofastService;
import no.systema.z.main.maintenance.url.store.MaintenanceMainUrlDataStore;
import no.systema.z.main.maintenance.util.MainMaintenanceConstants;


/**
 * Fritekst in Kunde
 * 
 * 
 * @author Fredrik Möller
 * @date Feb 20, 2017
 * 
 * 	
 */

@Controller
public class MainMaintenanceCundfFritekstController {
	private static final Logger logger = Logger.getLogger(MainMaintenanceCundfFritekstController.class.getName());
	private ModelAndView loginView = new ModelAndView("login");
	private CodeDropDownMgr codeDropDownMgr = new CodeDropDownMgr();

	@RequestMapping(value = "mainmaintenancecundf_fritekst_edit.do", method = { RequestMethod.GET, RequestMethod.POST })
	public ModelAndView doFritekst(HttpSession session, HttpServletRequest request) {
		ModelAndView successView = new ModelAndView("mainmaintenancecundf_fritekst_edit");
		SystemaWebUser appUser = (SystemaWebUser) session.getAttribute(AppConstants.SYSTEMA_WEB_USER_KEY);
		Map model = new HashMap();
		String action = request.getParameter("action");
		String kfkod = request.getParameter("searchKfkod");
		logger.info("kfkod="+kfkod);
		
		if (appUser == null) {
			return this.loginView;
		} else {
			KundeSessionParams kundeSessionParams = (KundeSessionParams) session.getAttribute(MainMaintenanceConstants.KUNDE_SESSION_PARAMS);
			String firma = kundeSessionParams.getFirma();
			String kundnr = kundeSessionParams.getKundnr();

			if (MainMaintenanceConstants.ACTION_CREATE.equals(action)) {  //New
				
				
			} else if (MainMaintenanceConstants.ACTION_UPDATE.equals(action)) { //Update
				
			} else { //Fetch

				String fxtxt = fetchTekst(appUser,kundnr, kfkod );
				String changelog = fetchChangelog(appUser,kundnr, kfkod);
				
				logger.info("fxtxt="+fxtxt);
				logger.info("changelog="+changelog);
				
				model.put("fxtxt", fxtxt);
				model.put("changelog", changelog);

			}

			model.put("kundnr", kundnr);
			model.put("firma", firma);
			model.put("searchKfkod", kfkod);
			populateDropDowns(model,appUser.getUser());
			
			successView.addObject(MainMaintenanceConstants.DOMAIN_MODEL, model);
			successView.addObject("tab_knavn_display", VkundControllerUtil.getTrimmedKnav(kundeSessionParams.getKnavn()));
			

			return successView;
		}
	}
	
	
	private String fetchTekst(SystemaWebUser appUser, String kundnr, String kfkod) {
		StringBuilder concatText = new StringBuilder();
		List<FratxtDao> fratxtList = getFratxt(appUser, kundnr, kfkod);
		for (FratxtDao fratxtDao : fratxtList) {
			concatText.append(fratxtDao.getFxtxt()).append(System.getProperty("line.separator"));
		}

//		logger.info("concatText="+concatText.toString());
		return concatText.toString();

	}
	
	private String fetchChangelog(SystemaWebUser appUser, String kundnr, String kfkod) {
		StringBuilder concatLog = new StringBuilder();
		List<FratxtDao> fratxtList = getFratxt(appUser, kundnr, kfkod);
		for (FratxtDao fratxtDao : fratxtList) {
			concatLog.append(fratxtDao.getFxusr()).append(":").append(fratxtDao.getFxdt());
			concatLog.append(System.getProperty("line.separator"));
		}

		logger.info("concatLog="+concatLog.toString());
		return concatLog.toString();

	}	
	
	
	
	private List<FratxtDao> getFratxt(SystemaWebUser appUser, String kundnr, String kfkod) {
		JsonReader<JsonDtoContainer<FratxtDao>> jsonReader = new JsonReader<JsonDtoContainer<FratxtDao>>();
		jsonReader.set(new JsonDtoContainer<FratxtDao>());
		String BASE_URL = MaintenanceMainUrlDataStore.MAINTENANCE_MAIN_BASE_FRATXT_GET_URL;
		StringBuilder urlRequestParams = new StringBuilder();
		urlRequestParams.append("user=" + appUser.getUser());
		urlRequestParams.append("&fxknr=" + kundnr);
		urlRequestParams.append("&delsys=" + kfkod);
		
		logger.info("URL: " + BASE_URL);
		logger.info("PARAMS: " + urlRequestParams.toString());
		String jsonPayload = urlCgiProxyService.getJsonContent(BASE_URL, urlRequestParams.toString());
		logger.info("jsonPayload=" + jsonPayload);
		List<FratxtDao> list = new ArrayList<FratxtDao>();
		JsonDtoContainer<FratxtDao> container = (JsonDtoContainer<FratxtDao>) jsonReader.get(jsonPayload);
		if (container != null) {
			list = container.getDtoList();
		}
		return list;
	}		
	

	
	private void populateDropDowns(Map model, String applicationUser){
		codeDropDownMgr.popluateDelsystem(urlCgiProxyService, maintMainKofastService, model, applicationUser);
	}	
	
/*	private void populateMessageNotes(SystemaWebUser appUser, JsonMainOrderHeaderRecord orderRecord){
		
		Collection<JsonMainOrderHeaderMessageNoteRecord> messageNoteConsignee = null;
		Collection<JsonMainOrderHeaderMessageNoteRecord> messageNoteCarrier = null;
		Collection<JsonMainOrderHeaderMessageNoteRecord> messageNoteInternal = null;
		
		messageNoteConsignee = this.fetchMessageNote(appUser.getUser(), orderRecord, JsonMainOrderHeaderRecord.MESSAGE_NOTE_CONSIGNEE);
		messageNoteCarrier = this.fetchMessageNote(appUser.getUser(), orderRecord, JsonMainOrderHeaderRecord.MESSAGE_NOTE_CARRIER);
		messageNoteInternal = this.fetchMessageNote(appUser.getUser(), orderRecord, JsonMainOrderHeaderRecord.MESSAGE_NOTE_INTERNAL);
		
		StringBuffer brConsignee = new StringBuffer();
		for(JsonMainOrderHeaderMessageNoteRecord record: messageNoteConsignee ){
			if(record.getFrtli()!=null || !"".equals(record.getFrtli())){
				brConsignee.append(record.getFrttxt() + "\n");
			}
			
		}
		StringBuffer brCarrier = new StringBuffer();
		for(JsonMainOrderHeaderMessageNoteRecord record: messageNoteCarrier ){
			if(record.getFrtli()!=null || !"".equals(record.getFrtli())){
				brCarrier.append(record.getFrttxt() + "\n");
			}
		}
		StringBuffer brInternal = new StringBuffer();
		for(JsonMainOrderHeaderMessageNoteRecord record: messageNoteInternal ){
			if(record.getFrtkod()==null || "".equals(record.getFrtkod())){ //since we must filter in this specific type (blank)
				if(record.getFrtli()!=null || !"".equals(record.getFrtli())){
					brInternal.append(record.getFrttxt() + "\n");
				}
			}
		}
		//logger.info("******************************" + brInternal.toString());
		//populate final message notes now
		orderRecord.setMessageNoteConsignee(brConsignee.toString());
		orderRecord.setMessageNoteCarrier(brCarrier.toString());
		orderRecord.setMessageNoteInternal(brInternal.toString());
		//populate auxiliary arrays
		orderRecord.setMessageNoteConsigneeRaw((List)messageNoteConsignee);
		orderRecord.setMessageNoteCarrierRaw((List)messageNoteCarrier);
		orderRecord.setMessageNoteInternalRaw((List)messageNoteInternal);
	}
	
*/	
	
	
	//Wired - SERVICES
	@Qualifier ("urlCgiProxyService")
	private UrlCgiProxyService urlCgiProxyService;
	@Autowired
	@Required
	public void setUrlCgiProxyService (UrlCgiProxyService value){ this.urlCgiProxyService = value; }
	public UrlCgiProxyService getUrlCgiProxyService(){ return this.urlCgiProxyService; }
	
	@Qualifier ("maintMainCundcService")
	private MaintMainCundcService maintMainCundcService;
	@Autowired
	@Required
	public void setMaintMainCundcService (MaintMainCundcService value){ this.maintMainCundcService = value; }
	public MaintMainCundcService getMaintMainCundcService(){ return this.maintMainCundcService; }

	@Qualifier ("maintMainKofastService")
	private MaintMainKofastService maintMainKofastService;
	@Autowired
	@Required
	public void setMaintMainKofastService (MaintMainKofastService value){ this.maintMainKofastService = value; }
	public MaintMainKofastService getMaintMainKofastService(){ return this.maintMainKofastService; }	
	
		
}

