package no.systema.tvinn.sad.z.maintenance.sadimport.controller;

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
import no.systema.main.context.TdsAppContext;
import no.systema.main.service.UrlCgiProxyService;
import no.systema.main.validator.LoginValidator;
import no.systema.main.util.AppConstants;
import no.systema.main.util.JsonDebugger;
import no.systema.main.model.SystemaWebUser;

import no.systema.tvinn.sad.z.maintenance.util.TvinnSadMaintenanceConstants;
import no.systema.tvinn.sad.z.maintenance.model.MaintenanceMainListObject;

/**
 * TVINN Maintenance Import Topic Controller 
 * 
 * @author oscardelatorre
 * @date Mar 29, 2016
 * 
 */

@Controller
@SessionAttributes(AppConstants.SYSTEMA_WEB_USER_KEY)
@Scope("session")
public class MaintSadImportController {
	private static final JsonDebugger jsonDebugger = new JsonDebugger();
	private static final Logger logger = Logger.getLogger(MaintSadImportController.class.getName());
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
	@RequestMapping(value="tvinnsadmaintenanceimport.do", method=RequestMethod.GET)
	public ModelAndView doSkatImportList(HttpSession session, HttpServletRequest request){
		ModelAndView successView = new ModelAndView("tvinnsadmaintenanceimport");
		SystemaWebUser appUser = (SystemaWebUser)session.getAttribute(AppConstants.SYSTEMA_WEB_USER_KEY);
		//SearchFilterSadExportTopicList searchFilter = new SearchFilterSadExportTopicList();
		
		Map model = new HashMap();
		if(appUser==null){
			return this.loginView;
		}else{
			appUser.setActiveMenu(SystemaWebUser.ACTIVE_MENU_TVINN_SAD_MAINTENANCE_IMPORT);
			session.setAttribute(TvinnSadMaintenanceConstants.ACTIVE_URL_RPG_TVINN_SAD_MAINTENANCE, TvinnSadMaintenanceConstants.ACTIVE_URL_RPG_INITVALUE); 
		
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
			successView.addObject(TvinnSadMaintenanceConstants.DOMAIN_MODEL , model);
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
		object.setSubject("Avdelinger");
		object.setCode("SADI_AVD");
		object.setText("SKFTAAA / STANDI");
		object.setDbTable("STANDI");
		listObject.add(object);
		//
		object = new  MaintenanceMainListObject();
		object.setId("2");
		object.setSubject("Kunders vareregister");
		object.setCode("SADI_KUNDVAREREG");
		object.setText("SAD001A / SADVARE");
		object.setDbTable("SADVARE");
		listObject.add(object);
		//
		object = new  MaintenanceMainListObject();
		object.setId("3");
		object.setSubject("Løpenummer/dato");
		object.setCode("SADI_LOPE");
		object.setText("SAD006 / SADH,HEADF");
		object.setDbTable("SADH,HEADF");
		listObject.add(object);
		//
		object = new  MaintenanceMainListObject();
		object.setId("4");
		object.setSubject("Leveringsbetingelser");
		object.setCode("SADI_LEVBETING");
		object.setText("SAD012 / KODTLB");
		object.setDbTable("KODTLB");
		object.setStatus("G");
		object.setPgm("sad012r");
		listObject.add(object);
		//
		object = new  MaintenanceMainListObject();
		object.setId("5");
		object.setSubject("Tolltariff");
		object.setCode("SADI_TOLLTARIFF");
		object.setText("SAD010 / TARI");
		object.setDbTable("TARI");
		object.setStatus("G");
		object.setPgm("sad010r");
		listObject.add(object);
		//
		object = new  MaintenanceMainListObject();
		object.setId("6");
		object.setSubject("Gyldige tariffører");
		object.setCode("SADI_GYLD_TARIFFOR");
		object.setText("SYFT10 / KODTSI");
		object.setDbTable("KODTSI");
		object.setStatus("G");
		object.setPgm("syft10r");
		listObject.add(object);
		//
		object = new  MaintenanceMainListObject();
		object.setId("7");
		object.setSubject("Gyldige tollsteder");
		object.setCode("SADI_GYLD_TOLLSTEDER");
		object.setText("SYFT04 / KODTTST,KODTTSTX");
		object.setDbTable("KODTTST,KODTTSTX");
		listObject.add(object);
		//
		object = new  MaintenanceMainListObject();
		object.setId("8");
		object.setSubject("Gyldige koder(SAD)");
		object.setCode("SADI_GYLD_KODER");
		object.setText("SAD002 / KODTS1-KODTS9,KODTSA-KODTSO");
		object.setDbTable("KODTS1,KODTS2,KODTS3,KODTS4,KODTS5,KODTS6,KODTS7,KODTS8,KODTS9,KODTSA,KODTSB,KODTSC,KODTSD,KODTSE,KODTSO");
		listObject.add(object);
		//
		object = new  MaintenanceMainListObject();
		object.setId("9");
		object.setSubject("Søkeveier til tariff");
		object.setCode("SADI_SOKEVEI_TARIFF");
		object.setText("SAD062R / SOKTARI");
		object.setDbTable("SOKTARI");
		listObject.add(object);
		//
		object = new  MaintenanceMainListObject();
		object.setId("10");
		object.setSubject("Særavgifter");
		object.setCode("SADI_SAERAVG");
		object.setText("SAD999 / SADSD");
		object.setDbTable("SADSD");
		listObject.add(object);
		//
		object = new  MaintenanceMainListObject();
		object.setId("11");
		object.setSubject("Gml. kundes varereg.");
		object.setCode("SADI_GML_KUNDVAREREG");
		object.setText("SAD004 / SAD2");
		object.setDbTable("SAD2");
		listObject.add(object);
		//
		object = new  MaintenanceMainListObject();
		object.setId("12");
		object.setSubject("Gyldige likvidit.koder");
		object.setCode("SADI_GYLD_LIKV_KODER");
		object.setText("SYFT19 / KODTLIK");
		object.setDbTable("KODTLIK");
		object.setStatus("G");
		object.setPgm("syft19r");
		
		listObject.add(object);
		//
		object = new  MaintenanceMainListObject();
		object.setId("13");
		object.setSubject("Kunders likvidit.koder");
		object.setCode("SADI_KUNDGYLD_LIKV_KODER");
		object.setText("SYFT18 / CUNDF");
		object.setDbTable("CUNDF");
		object.setStatus("G");
		object.setPgm("syft18r");
		listObject.add(object);
		//
		object = new  MaintenanceMainListObject();
		object.setId("14");
		object.setSubject("Forendre Status på dekl.");
		object.setCode("SADI_STATUS_DEKL");
		object.setText("SAD013 / SADTT,STS");
		object.setDbTable("SADTT,STS");
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

