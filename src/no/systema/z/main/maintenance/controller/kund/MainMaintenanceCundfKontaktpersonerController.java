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
import no.systema.z.main.maintenance.model.jsonjackson.dbtable.JsonMaintMainCundcContainer;
import no.systema.z.main.maintenance.model.jsonjackson.dbtable.JsonMaintMainCundcRecord;
import no.systema.z.main.maintenance.service.MaintMainCundcService;
//models
import no.systema.z.main.maintenance.url.store.MaintenanceMainUrlDataStore;
import no.systema.z.main.maintenance.util.MainMaintenanceConstants;
import no.systema.z.main.maintenance.validator.MaintMainCundcValidator;


/**
 * Kontaktpersoner in Kunde
 * 
 * 
 * @author Fredrik Möller
 * @date Nov 2, 2016
 * 
 * 	
 */

@Controller
public class MainMaintenanceCundfKontaktpersonerController {
	private static final Logger logger = Logger.getLogger(MainMaintenanceCundfKontaktpersonerController.class.getName());
	private ModelAndView loginView = new ModelAndView("login");
	private static final JsonDebugger jsonDebugger = new JsonDebugger();
	private UrlRequestParameterMapper urlRequestParameterMapper = new UrlRequestParameterMapper();
	

	@RequestMapping(value = "mainmaintenancecundf_kontaktpersoner_list.do", method = { RequestMethod.GET, RequestMethod.POST })
	public ModelAndView doKontaktPersonerList(HttpSession session, HttpServletRequest request) {
		ModelAndView successView = new ModelAndView("mainmaintenancecundf_kontaktpersoner_edit");
		SystemaWebUser appUser = (SystemaWebUser) session.getAttribute(AppConstants.SYSTEMA_WEB_USER_KEY);
		Map model = new HashMap();

		if (appUser == null) {
			return this.loginView;
		} else {
			KundeSessionParams kundeSessionParams = (KundeSessionParams) session.getAttribute(TvinnSadMaintenanceConstants.KUNDE_SESSION_PARAMS);
			String firma = kundeSessionParams.getFirma();
			String kundnr = kundeSessionParams.getKundnr();

			List<JsonMaintMainCundcRecord> list = new ArrayList();
			list = this.fetchList(appUser.getUser(), firma, kundnr);

			model.put("kundnr", kundnr);
			model.put("firma", firma);
			model.put(MainMaintenanceConstants.DOMAIN_LIST, list);

			successView.addObject(MainMaintenanceConstants.DOMAIN_MODEL, model);

			return successView;

		}
	}
	
	
	
	@RequestMapping(value="mainmaintenancecundf_kontaktpersoner_edit.do", method={RequestMethod.GET, RequestMethod.POST })
	public ModelAndView mainmaintenancecundf_kontaktpersoner_edit(@ModelAttribute ("record") JsonMaintMainCundcRecord recordToValidate, BindingResult bindingResult, HttpSession session, HttpServletRequest request){
		ModelAndView successView = new ModelAndView("mainmaintenancecundf_kontaktpersoner_edit");
		SystemaWebUser appUser = (SystemaWebUser)session.getAttribute(AppConstants.SYSTEMA_WEB_USER_KEY);
		Map model = new HashMap();
		String action = request.getParameter("action");
		String updateId = request.getParameter("updateId");
		
		if (appUser == null) {
			return this.loginView;
		} else {
			KundeSessionParams kundeSessionParams = (KundeSessionParams)session.getAttribute(TvinnSadMaintenanceConstants.KUNDE_SESSION_PARAMS);
			String firma = kundeSessionParams.getFirma();
			String kundnr = kundeSessionParams.getKundnr();

			adjustRecordToValidate(recordToValidate, kundeSessionParams);
			
			MaintMainCundcValidator validator = new MaintMainCundcValidator();
			if(TvinnSadMaintenanceConstants.ACTION_DELETE.equals(action)){
				validator.validateDelete(recordToValidate, bindingResult);
			}else{
				validator.validate(recordToValidate, bindingResult);
			}
			if(bindingResult.hasErrors()){
				//ERRORS
				logger.info("[ERROR Validation] Record does not validate)");
				if(updateId!=null && !"".equals(updateId)){
					//meaning bounced in an Update and not a Create new
					model.put("updateId", updateId);
					
				}
				model.put(TvinnSadMaintenanceConstants.DOMAIN_RECORD, recordToValidate);
			}else{
				
				//------------
				//UPDATE table
				//------------
				StringBuffer errMsg = new StringBuffer();
				int dmlRetval = 0;
				//UPDATE
				if (TvinnSadMaintenanceConstants.ACTION_UPDATE.equals(action) ){
					if(updateId!=null && !"".equals(updateId)){
						//update
						logger.info(TvinnSadMaintenanceConstants.ACTION_UPDATE);
						dmlRetval = updateRecord(appUser.getUser(), recordToValidate, TvinnSadMaintenanceConstants.MODE_UPDATE, errMsg);
						
					//CREATE
					}else{
						//create new
						logger.info(TvinnSadMaintenanceConstants.ACTION_CREATE);
						dmlRetval = updateRecord(appUser.getUser(), recordToValidate, TvinnSadMaintenanceConstants.MODE_ADD, errMsg);
					}
				}else if(TvinnSadMaintenanceConstants.ACTION_DELETE.equals(action) ){
					//delete
					logger.info(TvinnSadMaintenanceConstants.ACTION_DELETE);
					dmlRetval = updateRecord(appUser.getUser(), recordToValidate, TvinnSadMaintenanceConstants.MODE_DELETE, errMsg);
				}
				//check for Update errors
				if( dmlRetval < 0){
					logger.info("[ERROR Validation] Record does not validate)");
					if(updateId!=null && !"".equals(updateId)){
						//meaning bounced in an Update and not a Create new
						model.put("updateId", updateId);
					}
					model.put(TvinnSadMaintenanceConstants.ASPECT_ERROR_MESSAGE, errMsg.toString());
					model.put(TvinnSadMaintenanceConstants.DOMAIN_RECORD, recordToValidate);
				}
				
			}
			//------------
			//FETCH table
			//------------

			List<JsonMaintMainCundcRecord> list = new ArrayList();
	    	list = this.fetchList(appUser.getUser(), firma, kundnr); 
	
	    	//model.put("action", kundeSessionParams.getAction());
			model.put("kundnr", kundnr);
			model.put("firma", firma);
	    	model.put(MainMaintenanceConstants.DOMAIN_LIST, list);

	    	successView.addObject(MainMaintenanceConstants.DOMAIN_MODEL, model);

			return successView;

		}

	}
	
	//Dont know to put from jsp, here here, explicit hardwired
	private void adjustRecordToValidate(JsonMaintMainCundcRecord recordToValidate, KundeSessionParams kundeSessionParams) {
		recordToValidate.setCfirma(kundeSessionParams.getFirma());
		recordToValidate.setCcompn(kundeSessionParams.getKundnr());
		recordToValidate.setSonavn(kundeSessionParams.getSonavn());
	}

	private List<JsonMaintMainCundcRecord> fetchList(String applicationUser, String cfirma, String ccompn){
		String BASE_URL = MaintenanceMainUrlDataStore.MAINTENANCE_MAIN_BASE_CUNDC_GET_LIST_URL;
		StringBuilder urlRequestParams = new StringBuilder();
		urlRequestParams.append("user="+ applicationUser);
		urlRequestParams.append("&cfirma="+cfirma);
		urlRequestParams.append("&ccompn="+ccompn);
		
		logger.info(Calendar.getInstance().getTime() + " CGI-start timestamp");
    	logger.info("URL: " + jsonDebugger.getBASE_URL_NoHostName(BASE_URL));
    	logger.info("URL PARAMS: " + urlRequestParams);
    	String jsonPayload = this.urlCgiProxyService.getJsonContent(BASE_URL, urlRequestParams.toString());
    	//extract
    	List<JsonMaintMainCundcRecord> list = new ArrayList();
    	if(jsonPayload!=null){
			//lists
    		JsonMaintMainCundcContainer container = this.maintMainCundcService.getList(jsonPayload);
	        if(container!=null){
	        	list = (List)container.getList();
/*	        	for(JsonMaintMainCundcRecord record : list){
	        	  logger.info("record:" + record.toString());
	        	}
*/	        }
    	}
    	return list;
    	
	}
	
	
	private int updateRecord(String applicationUser, JsonMaintMainCundcRecord record, String mode, StringBuffer errMsg) {
		int retval = 0;

		String BASE_URL = MaintenanceMainUrlDataStore.MAINTENANCE_MAIN_BASE_CUNDC_DML_UPDATE_URL;
		String urlRequestParamsKeys = "user=" + applicationUser + "&mode=" + mode;
		String urlRequestParams = this.urlRequestParameterMapper.getUrlParameterValidString((record));
		urlRequestParams = urlRequestParamsKeys + urlRequestParams;

		logger.info(Calendar.getInstance().getTime() + " CGI-start timestamp");
		logger.info("URL: " + jsonDebugger.getBASE_URL_NoHostName(BASE_URL));
		logger.info("URL PARAMS: " + urlRequestParams);
		String jsonPayload = this.urlCgiProxyService.getJsonContent(BASE_URL, urlRequestParams);
		// extract
		if (jsonPayload != null) {
			// lists
			JsonMaintMainCundcContainer container = this.maintMainCundcService.doUpdate(jsonPayload);
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

		
}

