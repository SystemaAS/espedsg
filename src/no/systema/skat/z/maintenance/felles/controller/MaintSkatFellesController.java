package no.systema.skat.z.maintenance.felles.controller;

import java.util.*;

import org.apache.log4j.Logger;
import org.springframework.validation.BindingResult;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Required;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Scope;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.web.bind.ServletRequestDataBinder;
import org.springframework.web.bind.WebDataBinder;

//application imports
import no.systema.main.service.UrlCgiProxyService;
import no.systema.main.validator.LoginValidator;
import no.systema.main.util.AppConstants;
import no.systema.main.util.JsonDebugger;
import no.systema.main.model.SystemaWebUser;

import no.systema.skat.z.maintenance.main.model.MaintenanceMainListObject;
import no.systema.skat.z.maintenance.main.util.SkatMaintenanceConstants;

/**
 * SKAT Maintenance Felles gate Controller 
 * 
 * @author oscardelatorre
 * @date Feb 27, 2017
 * 
 */

@Controller
@SessionAttributes(AppConstants.SYSTEMA_WEB_USER_KEY)
@Scope("session")
public class MaintSkatFellesController {
	private static final JsonDebugger jsonDebugger = new JsonDebugger();
	private static final Logger logger = Logger.getLogger(MaintSkatFellesController.class.getName());
	private ModelAndView loginView = new ModelAndView("login");
	private ApplicationContext context;
	private LoginValidator loginValidator = new LoginValidator();
	
	/**
	 * 
	 * @param user
	 * @param result
	 * @param request
	 * @return
	 */
	@RequestMapping(value="skatmaintenancefelles.do", method=RequestMethod.GET)
	public ModelAndView doSkatImportList(HttpSession session, HttpServletRequest request){
		ModelAndView successView = new ModelAndView("skatmaintenancefelles");
		SystemaWebUser appUser = (SystemaWebUser)session.getAttribute(AppConstants.SYSTEMA_WEB_USER_KEY);
		//SearchFilterSadExportTopicList searchFilter = new SearchFilterSadExportTopicList();
		
		Map model = new HashMap();
		if(appUser==null){
			return this.loginView;
		}else{
			appUser.setActiveMenu(SystemaWebUser.ACTIVE_MENU_SKAT_MAINTENANCE_FELLES);
			session.setAttribute(SkatMaintenanceConstants.ACTIVE_URL_RPG_SKAT_MAINTENANCE, SkatMaintenanceConstants.ACTIVE_URL_RPG_INITVALUE); 
		
			//lists
			List list = this.populateMaintenanceMainList();
			//this.populateAvdelningHtmlDropDownsFromJsonString(model, appUser);
			//this.populateSignatureHtmlDropDownsFromJsonString(model, appUser);
			//this.setCodeDropDownMgr(appUser, model);
			//init filter with users signature (for starters)
			//searchFilter.setSg(appUser.getTvinnSadSign());
			//successView.addObject("searchFilter" , searchFilter);
			//init the rest
			model.put("list", list);
			successView.addObject(SkatMaintenanceConstants.DOMAIN_MODEL , model);
			//successView.addObject(TvinnSadConstants.DOMAIN_LIST,new ArrayList());
			
	    	return successView;
		}
	}
	/**
	 * 
	 * @return
	 */
	private List<MaintenanceMainListObject> populateMaintenanceMainList(){
		List<MaintenanceMainListObject> listObject = new ArrayList<MaintenanceMainListObject>();
		MaintenanceMainListObject object = new  MaintenanceMainListObject();
		        
		object.setId("1");
		object.setSubject("Vedligeholdelse af firmaoplysninger");
		object.setCode("SKAT_FIRMOPL");
		object.setText("DKT055 / DKTFI");
		object.setDbTable("DKTFI");
		object.setStatus("G");
		object.setPgm("dkt055r");
		listObject.add(object);
		//
		object = new  MaintenanceMainListObject();
		object.setId("2");
		object.setSubject("Vedligeholdelse af sagsbehandler");
		object.setCode("SKAT_SAGSBEH");
		object.setText("DKT056 / DKTHA");
		object.setDbTable("DKTHA");
		object.setStatus("G");
		object.setPgm("dkt056r");
		listObject.add(object);
		//
		object = new  MaintenanceMainListObject();
		object.setId("3");
		object.setSubject("Vedligeholdelse af Toldtariffen");
		object.setCode("SKAT_TOLDTARIF");
		object.setText("DKTARD");
		object.setDbTable("DKTARD");
		object.setStatus("G");
		object.setPgm("dktard");
		listObject.add(object);
		//
		object = new  MaintenanceMainListObject();
		object.setId("4");
		object.setSubject("Vedligeholdelse af valutakurser");
		object.setCode("SKAT_VALUTAKRS");
		object.setText("DKT057 / DKTVK");
		object.setDbTable("DKTVK");
		object.setStatus("G");
		object.setPgm("dkt057r");
		listObject.add(object);
		
		return listObject;
	}
	
	
	//SERVICES
	@Qualifier ("urlCgiProxyService")
	private UrlCgiProxyService urlCgiProxyService;
	@Autowired
	@Required
	public void setUrlCgiProxyService (UrlCgiProxyService value){ this.urlCgiProxyService = value; }
	public UrlCgiProxyService getUrlCgiProxyService(){ return this.urlCgiProxyService; }
	

}

