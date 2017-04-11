package no.systema.z.main.maintenance.controller.arkiv;

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

import no.systema.jservices.common.dao.ArkextDao;
import no.systema.jservices.common.json.JsonDtoContainer;
import no.systema.jservices.common.json.JsonReader;
import no.systema.main.model.SystemaWebUser;
import no.systema.main.service.UrlCgiProxyService;
import no.systema.main.util.AppConstants;
import no.systema.main.util.JsonDebugger;
import no.systema.tvinn.sad.util.TvinnSadConstants;
import no.systema.z.main.maintenance.controller.ChildWindowKode;
import no.systema.z.main.maintenance.url.store.MaintenanceMainUrlDataStore;

/**
 * 
 * Child windows codes for Arkiv
 * 
 * 
 * @author Fredrik Möller
 * @date Apr 11, 2017
 * 	
 */

@Controller
public class MainMaintenanceArkivChildWindowsCodesController {
	private static final Logger logger = Logger.getLogger(MainMaintenanceArkivChildWindowsCodesController.class.getName());
	private ModelAndView loginView = new ModelAndView("login");
	private static final JsonDebugger jsonDebugger = new JsonDebugger();

	/**
	 * This method serve as data populater for all child windows codes for Arkiv.
	 * 
	 * 
	 * @param session
	 * @param request
	 * @return
	 */
	@RequestMapping(value="mainmaintenance_arkiv_edit_childwindow_codes.do",  method={RequestMethod.GET} )
	public ModelAndView getCodes(HttpSession session, HttpServletRequest request){
		ModelAndView successView = new ModelAndView("mainmaintenance_arkiv_edit_childwindow_codes");
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

		if ("arklag".equals(caller)) { // Mappe
			list = getArklagKoder(appUser);
		} else {
			throw new IllegalArgumentException(caller + " is not supported.");
		}

		return list;
	}
	
	
	private List<ChildWindowKode>  getArklagKoder(SystemaWebUser appUser) {
		JsonReader<JsonDtoContainer<ArkextDao>> jsonReader = new JsonReader<JsonDtoContainer<ArkextDao>>();
		jsonReader.set(new JsonDtoContainer<ArkextDao>());
		String BASE_URL = MaintenanceMainUrlDataStore.MAINTENANCE_MAIN_BASE_ARKEXT_GET_URL;
		StringBuffer urlRequestParams = new StringBuffer();
		urlRequestParams.append("user=" + appUser.getUser());

		logger.info(Calendar.getInstance().getTime() + " CGI-start timestamp");
		logger.info("URL: " + jsonDebugger.getBASE_URL_NoHostName(BASE_URL));
		logger.info("URL PARAMS: " + urlRequestParams);
		String jsonPayload = urlCgiProxyService.getJsonContent(BASE_URL, urlRequestParams.toString());
		//logger.info("jsonPayload="+jsonPayload);
		List <ChildWindowKode> kodeList = new ArrayList<ChildWindowKode>();
		ChildWindowKode kode = null;
		if (jsonPayload != null) {
			JsonDtoContainer<ArkextDao> container = (JsonDtoContainer<ArkextDao>) jsonReader.get(jsonPayload);
				if (container != null) {
					for (ArkextDao arkextDao :  container.getDtoList()) {
						kode = getChildWindowKode(arkextDao);
						kodeList.add(kode);					
					}
				}
		}
		return kodeList;
	}	
	
	private ChildWindowKode getChildWindowKode(ArkextDao dao) {
		ChildWindowKode kode = new ChildWindowKode();
		kode.setCode(dao.getArcext());
		kode.setDescription(dao.getArcane());

		return kode;
	}		
	
	//Wired - SERVICES
	@Qualifier ("urlCgiProxyService")
	private UrlCgiProxyService urlCgiProxyService;
	@Autowired
	@Required
	public void setUrlCgiProxyService (UrlCgiProxyService value){ this.urlCgiProxyService = value; }
	public UrlCgiProxyService getUrlCgiProxyService(){ return this.urlCgiProxyService; }
	
}

