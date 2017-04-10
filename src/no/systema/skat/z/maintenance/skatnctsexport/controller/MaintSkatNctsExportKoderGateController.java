package no.systema.skat.z.maintenance.skatnctsexport.controller;

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

import no.systema.skat.z.maintenance.main.model.MaintenanceMainListObject;
import no.systema.skat.z.maintenance.main.util.SkatMaintenanceConstants;

/**
 * SKAT Maintenance NCTS Export Topic Controller 
 * 
 * @author oscardelatorre
 * @date Apr 10, 2017
 * 
 */

@Controller
@SessionAttributes(AppConstants.SYSTEMA_WEB_USER_KEY)
@Scope("session")
public class MaintSkatNctsExportKoderGateController {
	private static final JsonDebugger jsonDebugger = new JsonDebugger();
	private static final Logger logger = Logger.getLogger(MaintSkatNctsExportKoderGateController.class.getName());
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
	@RequestMapping(value="skatmaintenancenctsexport_kodergate.do", method=RequestMethod.GET)
	public ModelAndView doSkatImportList(HttpSession session, HttpServletRequest request){
		ModelAndView successView = new ModelAndView("skatmaintenancenctsexport_kodergate");
		SystemaWebUser appUser = (SystemaWebUser)session.getAttribute(AppConstants.SYSTEMA_WEB_USER_KEY);
		//SearchFilterSadExportTopicList searchFilter = new SearchFilterSadExportTopicList();
		
		Map model = new HashMap();
		if(appUser==null){
			return this.loginView;
		}else{
			appUser.setActiveMenu(SystemaWebUser.ACTIVE_MENU_SKAT_MAINTENANCE_NCTS_EXPORT);
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
		object.setSubject("Sprogkoder");
		object.setCode("012");
		object.setText("DKX001R / DKXKODF");
		object.setDbTable("DKXKODF");
		object.setPgm("dkx001r");
		object.setStatus("G");
		listObject.add(object);
		//
		object = new  MaintenanceMainListObject();
		object.setId("2");
		object.setSubject("Dokumentkode");
		object.setCode("013");
		object.setText("DKX001R / DKXKODF");
		object.setDbTable("DKXKODF");
		object.setPgm("dkx001r");
		object.setStatus("G");
		listObject.add(object);
		//
		object = new  MaintenanceMainListObject();
		object.setId("3");
		object.setSubject("Tidigare dokumnet");
		object.setCode("014");
		object.setText("DKX001R / DKXKODF");
		object.setDbTable("DKXKODF");
		object.setPgm("dkx001r");
		object.setStatus("G");
		listObject.add(object);
		//
		object = new  MaintenanceMainListObject();
		object.setId("4");
		object.setSubject("Kollislag");
		object.setCode("017");
		object.setText("DKX001R / DKXKODF");
		object.setDbTable("DKXKODF");
		object.setPgm("dkx001r");
		object.setStatus("G");
		listObject.add(object);
		//
		object = new  MaintenanceMainListObject();
		object.setId("5");
		object.setSubject("Angivelsesart");
		object.setCode("031");
		object.setText("DKX001R / DKXKODF");
		object.setDbTable("DKXKODF");
		object.setPgm("dkx001r");
		object.setStatus("G");
		listObject.add(object);
		//
		object = new  MaintenanceMainListObject();
		object.setId("6");
		object.setSubject("Tillæggsoplysninger");
		object.setCode("039");
		object.setText("DKX001R / DKXKODF");
		object.setDbTable("DKXKODF");
		object.setPgm("dkx001r");
		object.setStatus("G");
		listObject.add(object);
		//
		object = new  MaintenanceMainListObject();
		object.setId("7");
		object.setSubject("Kontrollresultat");
		object.setCode("047");
		object.setText("DKX001R / DKXKODF");
		object.setDbTable("DKXKODF");
		object.setPgm("dkx001r");
		object.setStatus("G");
		listObject.add(object);
		//
		object = new  MaintenanceMainListObject();
		object.setId("8");
		object.setSubject("Følsomme vare");
		object.setCode("064");
		object.setText("DKX001R / DKXKODF");
		object.setDbTable("DKXKODF");
		object.setPgm("dkx001r");
		object.setStatus("G");
		listObject.add(object);
		//
		object = new  MaintenanceMainListObject();
		object.setId("9");
		object.setSubject("Særlige omstændigheder ");
		object.setCode("096");
		object.setText("DKX001R / DKXKODF");
		object.setDbTable("DKXKODF");
		object.setPgm("dkx001r");
		object.setStatus("G");
		listObject.add(object);
		//
		object = new  MaintenanceMainListObject();
		object.setId("10");
		object.setSubject("Tillgångskod før garanti");
		object.setCode("105");
		object.setText("DKX001R / DKXKODF");
		object.setDbTable("DKXKODF");
		object.setPgm("dkx001r");
		object.setStatus("G");
		listObject.add(object);
		//
		object = new  MaintenanceMainListObject();
		object.setId("11");
		object.setSubject("Toldekspedition ref.nr");
		object.setCode("106");
		object.setText("DKX001R / DKXKODF");
		object.setDbTable("DKXKODF");
		object.setPgm("dkx001r");
		object.setStatus("G");
		listObject.add(object);
		//
		object = new  MaintenanceMainListObject();
		object.setId("12");
		object.setSubject("Betalningssætt transportkostnad");
		object.setCode("116");
		object.setText("DKX001R / DKXKODF");
		object.setDbTable("DKXKODF");
		object.setPgm("dkx001r");
		object.setStatus("G");
		listObject.add(object);
		//
		object = new  MaintenanceMainListObject();
		object.setId("13");
		object.setSubject("Transportmåde");
		object.setCode("108");
		object.setText("DKX001R / DKXKODF");
		object.setDbTable("DKXKODF");
		object.setPgm("dkx001r");
		object.setStatus("G");
		listObject.add(object);
		//
		object = new  MaintenanceMainListObject();
		object.setId("14");
		object.setSubject("Fejlkoder i CONTROL");
		object.setCode("023");
		object.setText("DKX001R / DKXKODF");
		object.setDbTable("DKXKODF");
		object.setPgm("dkx001r");
		object.setStatus("G");
		listObject.add(object);
		//
		object = new  MaintenanceMainListObject();
		object.setId("15");
		object.setSubject("Kontrollindikator varepost");
		object.setCode("041");
		object.setText("DKX001R / DKXKODF");
		object.setDbTable("DKXKODF");
		object.setPgm("dkx001r");
		object.setStatus("G");
		listObject.add(object);
		//
		object = new  MaintenanceMainListObject();
		object.setId("16");
		object.setSubject("Kontrollindikator Transit");
		object.setCode("042");
		object.setText("DKX001R / DKXKODF");
		object.setDbTable("DKXKODF");
		object.setPgm("dkx001r");
		object.setStatus("G");
		listObject.add(object);
		//
		object = new  MaintenanceMainListObject();
		object.setId("17");
		object.setSubject("Fejlkoder i CUSRES");
		object.setCode("049");
		object.setText("DKX001R / DKXKODF");
		object.setDbTable("DKXKODF");
		object.setPgm("dkx001r");
		object.setStatus("G");
		listObject.add(object);
		//
		object = new  MaintenanceMainListObject();
		object.setId("18");
		object.setSubject("Fejlkoder for datagrupp i CUSRES");
		object.setCode("149");
		object.setText("DKX001R / DKXKODF");
		object.setDbTable("DKXKODF");
		object.setPgm("dkx001r");
		object.setStatus("G");
		listObject.add(object);
		//
		object = new  MaintenanceMainListObject();
		object.setId("19");
		object.setSubject("Fejlkoder for garanti");
		object.setCode("209");
		object.setText("DKX001R / DKXKODF");
		object.setDbTable("DKXKODF");
		object.setPgm("dkx001r");
		object.setStatus("G");
		listObject.add(object);
		//
		object = new  MaintenanceMainListObject();
		object.setId("20");
		object.setSubject("Statuskoder eksport");
		object.setCode("302");
		object.setText("DKX001R / DKXKODF");
		object.setDbTable("DKXKODF");
		object.setPgm("dkx001r");
		object.setStatus("G");
		listObject.add(object);
		//
	    
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

