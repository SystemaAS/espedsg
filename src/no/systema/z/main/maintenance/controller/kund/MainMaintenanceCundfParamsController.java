package no.systema.z.main.maintenance.controller.kund;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang3.builder.ReflectionToStringBuilder;
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

import no.systema.jservices.common.dto.SyparfDto;
import no.systema.jservices.common.json.JsonDtoContainer;
import no.systema.jservices.common.json.JsonReader;
//application imports
import no.systema.main.model.SystemaWebUser;
import no.systema.main.service.UrlCgiProxyService;
import no.systema.main.util.AppConstants;
import no.systema.main.util.JsonDebugger;
import no.systema.z.main.maintenance.mapper.url.request.UrlRequestParameterMapper;
//models
import no.systema.z.main.maintenance.url.store.MaintenanceMainUrlDataStore;
import no.systema.z.main.maintenance.util.MainMaintenanceConstants;
import no.systema.z.main.maintenance.validator.MaintMainSyparfValidator;


/**
 * Diverse Parametere in Kunde
 * 
 * 
 * @author Fredrik Möller
 * @date Mar 3, 2017
 * 
 * 	
 */

@Controller
public class MainMaintenanceCundfParamsController {
	private static final Logger logger = Logger.getLogger(MainMaintenanceCundfParamsController.class.getName());
	private ModelAndView loginView = new ModelAndView("login");
	private static final JsonDebugger jsonDebugger = new JsonDebugger();
	private UrlRequestParameterMapper urlRequestParameterMapper = new UrlRequestParameterMapper();
	

	@RequestMapping(value = "mainmaintenancecundf_params_list.do", method = { RequestMethod.GET, RequestMethod.POST })
	public ModelAndView doParamsList(HttpSession session, HttpServletRequest request) {
		ModelAndView successView = new ModelAndView("mainmaintenancecundf_params_edit");
		SystemaWebUser appUser = (SystemaWebUser) session.getAttribute(AppConstants.SYSTEMA_WEB_USER_KEY);
		Map model = new HashMap();
		
		if (appUser == null) {
			return this.loginView;
		} else {
			KundeSessionParams kundeSessionParams = (KundeSessionParams) session.getAttribute(MainMaintenanceConstants.KUNDE_SESSION_PARAMS);
			String firma = kundeSessionParams.getFirma();
			String kundnr = kundeSessionParams.getKundnr();

			List<SyparfDto> list = new ArrayList();
			list = fetchList(appUser, kundnr, null);

			model.put("kundnr", kundnr);
			model.put("firma", firma);
			model.put(MainMaintenanceConstants.DOMAIN_LIST, list);
			
			successView.addObject(MainMaintenanceConstants.DOMAIN_MODEL, model);
			successView.addObject("tab_knavn_display", VkundControllerUtil.getTrimmedKnav(kundeSessionParams.getKnavn()));

			return successView;
		}
	}
	
	
	@RequestMapping(value="mainmaintenancecundf_params_edit.do", method={RequestMethod.GET, RequestMethod.POST })
	public ModelAndView mainmaintenancecundf_params_edit(@ModelAttribute ("record") SyparfDto recordToValidate, BindingResult bindingResult, HttpSession session, HttpServletRequest request){
		ModelAndView successView = new ModelAndView("mainmaintenancecundf_params_edit");
		SystemaWebUser appUser = (SystemaWebUser)session.getAttribute(AppConstants.SYSTEMA_WEB_USER_KEY);
		Map model = new HashMap();
		String action = request.getParameter("action");
		String updateId = request.getParameter("updateId");
		
		logger.info("action="+action);
		logger.info("updateId="+updateId);
		logger.info("recordToValidate="+ReflectionToStringBuilder.toString(recordToValidate));
		
		if (appUser == null) {
			return this.loginView;
		} else {
			KundeSessionParams kundeSessionParams = (KundeSessionParams) session.getAttribute(MainMaintenanceConstants.KUNDE_SESSION_PARAMS);
			adjustRecordToValidate(recordToValidate, kundeSessionParams);

			MaintMainSyparfValidator validator = new MaintMainSyparfValidator();
			if (MainMaintenanceConstants.ACTION_DELETE.equals(action)) {
				validator.validateDelete(recordToValidate, bindingResult);
			} else {
				validator.validate(recordToValidate, bindingResult);
			}
			if (bindingResult.hasErrors()) {
				logger.info("[ERROR Validation] Record does not validate)");
				if (updateId != null && !"".equals(updateId)) {
					// meaning bounced in an Update and not a Create new
					model.put("updateId", updateId);
				}
				model.put(MainMaintenanceConstants.DOMAIN_RECORD, recordToValidate);
			} else {
				StringBuffer errMsg = new StringBuffer();
				int dmlRetval = 0;
				
				if (MainMaintenanceConstants.ACTION_UPDATE.equals(action)) {
					if (updateId != null && !"".equals(updateId)) {
						dmlRetval = updateRecord(appUser.getUser(), recordToValidate, MainMaintenanceConstants.MODE_UPDATE, errMsg);
					} else {
						dmlRetval = updateRecord(appUser.getUser(), recordToValidate, MainMaintenanceConstants.MODE_ADD, errMsg);
					}
				} else if (MainMaintenanceConstants.ACTION_DELETE.equals(action)) {
					dmlRetval = updateRecord(appUser.getUser(), recordToValidate, MainMaintenanceConstants.MODE_DELETE, errMsg);
				}
				// check for Update errors
				if (dmlRetval < 0) {
					logger.info("[ERROR DML] Record does not validate)");
					if (updateId != null && !"".equals(updateId)) {
						// meaning bounced in an Update and not a Create new
						model.put("updateId", updateId);
					}
					model.put(MainMaintenanceConstants.ASPECT_ERROR_MESSAGE, errMsg.toString());
					model.put(MainMaintenanceConstants.DOMAIN_RECORD, recordToValidate);
				}

			}

			List<SyparfDto> list = new ArrayList();
			list = this.fetchList(appUser, kundeSessionParams.getKundnr(), null);

			model.put("kundnr", kundeSessionParams.getKundnr());
			model.put("firma", kundeSessionParams.getFirma());
			model.put(MainMaintenanceConstants.DOMAIN_LIST, list);

			successView.addObject(MainMaintenanceConstants.DOMAIN_MODEL, model);
			successView.addObject("tab_knavn_display", VkundControllerUtil.getTrimmedKnav(kundeSessionParams.getKnavn()));

			return successView;

		}

	}
	
	private List<SyparfDto> fetchList(SystemaWebUser appUser, String sykunr, String syrecn) {
		JsonReader<JsonDtoContainer<SyparfDto>> jsonReader = new JsonReader<JsonDtoContainer<SyparfDto>>();
		jsonReader.set(new JsonDtoContainer<SyparfDto>());
		String BASE_URL = MaintenanceMainUrlDataStore.MAINTENANCE_MAIN_BASE_SYPARF_GET_URL;
		StringBuilder urlRequestParams = new StringBuilder();
		urlRequestParams.append("user=" + appUser.getUser());
		urlRequestParams.append("&sykunr=" + sykunr);
		if (syrecn != null) {
			urlRequestParams.append("&syrecn=" + syrecn);
		}
		
		logger.info("URL: " + BASE_URL);
		logger.info("PARAMS: " + urlRequestParams.toString());
		String jsonPayload = urlCgiProxyService.getJsonContent(BASE_URL, urlRequestParams.toString());
		logger.info("jsonPayload=" + jsonPayload);
		List<SyparfDto> list = new ArrayList<SyparfDto>();
		JsonDtoContainer<SyparfDto> container = (JsonDtoContainer<SyparfDto>) jsonReader.get(jsonPayload);
		if (container != null) {
			list = container.getDtoList();
		}
		return list;
	}	
	
	
	private int updateRecord(String applicationUser, SyparfDto record, String mode, StringBuffer errMsg) {
		int retval = 0;
		JsonReader<JsonDtoContainer<SyparfDto>> jsonReader = new JsonReader<JsonDtoContainer<SyparfDto>>();
		jsonReader.set(new JsonDtoContainer<SyparfDto>());
		String BASE_URL = MaintenanceMainUrlDataStore.MAINTENANCE_MAIN_BASE_SYPARF_DML_UPDATE_URL;
		String urlRequestParamsKeys = "user=" + applicationUser + "&mode=" + mode;
		String urlRequestParams = urlRequestParameterMapper.getUrlParameterValidString(record);
		urlRequestParams = urlRequestParamsKeys + urlRequestParams;

		logger.info(Calendar.getInstance().getTime() + " CGI-start timestamp");
		logger.info("URL: " + jsonDebugger.getBASE_URL_NoHostName(BASE_URL));
		logger.info("URL PARAMS: " + urlRequestParams);
		String jsonPayload = this.urlCgiProxyService.getJsonContent(BASE_URL, urlRequestParams);
		logger.info("jsonPayload=" + jsonPayload);
		if (jsonPayload != null) {
			JsonDtoContainer<SyparfDto> container = (JsonDtoContainer<SyparfDto>) jsonReader.get(jsonPayload);
			if (container != null) {
				logger.info("container="+container);
				logger.info("container.getErrMsg()="+container.getErrMsg());
				if (container.getErrMsg() != null && !"".equals(container.getErrMsg())) {
					errMsg.append(container.getErrMsg());
					logger.info("errMsg="+errMsg.toString());
					retval = MainMaintenanceConstants.ERROR_CODE;
				}
			}			
		}

		return retval;
	}	
	
	
	private void adjustRecordToValidate(SyparfDto recordToValidate, KundeSessionParams kundeSessionParams) {
		recordToValidate.setSykunr(kundeSessionParams.getKundnr());
	}

	
	//Wired - SERVICES
	@Qualifier ("urlCgiProxyService")
	private UrlCgiProxyService urlCgiProxyService;
	@Autowired
	@Required
	public void setUrlCgiProxyService (UrlCgiProxyService value){ this.urlCgiProxyService = value; }
	public UrlCgiProxyService getUrlCgiProxyService(){ return this.urlCgiProxyService; }
	
		
}

