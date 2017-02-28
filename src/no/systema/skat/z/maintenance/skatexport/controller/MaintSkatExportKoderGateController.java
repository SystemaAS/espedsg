package no.systema.skat.z.maintenance.skatexport.controller;

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
 * SKAT Maintenance Export Topic Controller 
 * 
 * @author oscardelatorre
 * @date Feb 27, 2017
 * 
 */

@Controller
@SessionAttributes(AppConstants.SYSTEMA_WEB_USER_KEY)
@Scope("session")
public class MaintSkatExportKoderGateController {
	private static final JsonDebugger jsonDebugger = new JsonDebugger();
	private static final Logger logger = Logger.getLogger(MaintSkatExportKoderGateController.class.getName());
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
	@RequestMapping(value="skatmaintenanceexport_kodergate.do", method=RequestMethod.GET)
	public ModelAndView doSkatImportList(HttpSession session, HttpServletRequest request){
		ModelAndView successView = new ModelAndView("skatmaintenanceexport_kodergate");
		SystemaWebUser appUser = (SystemaWebUser)session.getAttribute(AppConstants.SYSTEMA_WEB_USER_KEY);
		//SearchFilterSadExportTopicList searchFilter = new SearchFilterSadExportTopicList();
		
		Map model = new HashMap();
		if(appUser==null){
			return this.loginView;
		}else{
			appUser.setActiveMenu(SystemaWebUser.ACTIVE_MENU_SKAT_MAINTENANCE_EXPORT);
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
		object.setSubject("Svartekstkoder");
		object.setCode("101");
		object.setText("DKG210D / DKTKD");
		object.setDbTable("DKTKD");
		object.setPgm("dkg210d");
		object.setStatus("G");
		listObject.add(object);
		//
		object = new  MaintenanceMainListObject();
		object.setId("2");
		object.setSubject("Angivelsesart");
		object.setCode("102");
		object.setText("DKG210D / DKTKD");
		object.setDbTable("DKTKD");
		object.setPgm("dkg210d");
		object.setStatus("G");
		listObject.add(object);
		//
		object = new  MaintenanceMainListObject();
		object.setId("3");
		object.setSubject("Ekspeditionssted");
		object.setCode("103");
		object.setText("DKG210D / DKTKD");
		object.setDbTable("DKTKD");
		object.setPgm("dkg210d");
		object.setStatus("G");
		listObject.add(object);
		//
		object = new  MaintenanceMainListObject();
		object.setId("4");
		object.setSubject("Angivelsestype R1.1");
		object.setCode("104");
		object.setText("DKG210D / DKTKD");
		object.setDbTable("DKTKD");
		object.setPgm("dkg210d");
		object.setStatus("G");
		listObject.add(object);
		//
		object = new  MaintenanceMainListObject();
		object.setId("5");
		object.setSubject("Bestemmelsesland");
		object.setCode("105");
		object.setText("DKG210D / DKTKD");
		object.setDbTable("DKTKD");
		object.setPgm("dkg210d");
		object.setStatus("G");
		listObject.add(object);
		//
		object = new  MaintenanceMainListObject();
		object.setId("6");
		object.setSubject("Leveringsbetingelser");
		object.setCode("106");
		object.setText("DKG210D / DKTKD");
		object.setDbTable("DKTKD");
		object.setPgm("dkg210d");
		object.setStatus("G");
		listObject.add(object);
		//
		object = new  MaintenanceMainListObject();
		object.setId("7");
		object.setSubject("Valutakoder");
		object.setCode("107");
		object.setText("DKG210D / DKTKD");
		object.setDbTable("DKTKD");
		object.setPgm("dkg210d");
		object.setStatus("G");
		listObject.add(object);
		//
		object = new  MaintenanceMainListObject();
		object.setId("8");
		object.setSubject("Transportmåde");
		object.setCode("108");
		object.setText("DKG210D / DKTKD");
		object.setDbTable("DKTKD");
		object.setPgm("dkg210d");
		object.setStatus("G");
		listObject.add(object);
		//
		object = new  MaintenanceMainListObject();
		object.setId("9");
		object.setSubject("Bet.måte transportutgifter");
		object.setCode("109");
		object.setText("DKG210D / DKTKD");
		object.setDbTable("DKTKD");
		object.setPgm("dkg210d");
		object.setStatus("G");
		listObject.add(object);
		//
		object = new  MaintenanceMainListObject();
		object.setId("10");
		object.setSubject("Emballagekoder");
		object.setCode("110");
		object.setText("DKG210D / DKTKD");
		object.setDbTable("DKTKD");
		object.setPgm("dkg210d");
		object.setStatus("G");
		listObject.add(object);
		//
		object = new  MaintenanceMainListObject();
		object.setId("11");
		object.setSubject("Indikator R.S32");
		object.setCode("111");
		object.setText("DKG210D / DKTKD");
		object.setDbTable("DKTKD");
		object.setPgm("dkg210d");
		object.setStatus("G");
		listObject.add(object);
		//
		object = new  MaintenanceMainListObject();
		object.setId("12");
		object.setSubject("Procedurekoder R37");
		object.setCode("112");
		object.setText("DKG210D / DKTKD");
		object.setDbTable("DKTKD");
		object.setPgm("dkg210d");
		object.setStatus("G");
		listObject.add(object);
		//
		object = new  MaintenanceMainListObject();
		object.setId("13");
		object.setSubject("Certifikattyper");
		object.setCode("113");
		object.setText("DKG210D / DKTKD");
		object.setDbTable("DKTKD");
		object.setPgm("dkg210d");
		object.setStatus("G");
		listObject.add(object);
		//
		object = new  MaintenanceMainListObject();
		object.setId("14");
		object.setSubject("VAB-kode R44.3");
		object.setCode("114");
		object.setText("DKG210D / DKTKD");
		object.setDbTable("DKTKD");
		object.setPgm("dkg210d");
		object.setStatus("G");
		listObject.add(object);
		//
		object = new  MaintenanceMainListObject();
		object.setId("15");
		object.setSubject("FN-kode R44.4");
		object.setCode("115");
		object.setText("DKG210D / DKTKD");
		object.setDbTable("DKTKD");
		object.setPgm("dkg210d");
		object.setStatus("G");
		listObject.add(object);
		//
		object = new  MaintenanceMainListObject();
		object.setId("16");
		object.setSubject("Transportdokumenter R44.5.1");
		object.setCode("116");
		object.setText("DKG210D / DKTKD");
		object.setDbTable("DKTKD");
		object.setPgm("dkg210d");
		object.setStatus("G");
		listObject.add(object);
		//
		object = new  MaintenanceMainListObject();
		object.setId("16");
		object.setSubject("Transportdokumenter R44.5.1");
		object.setCode("116");
		object.setText("DKG210D / DKTKD");
		object.setDbTable("DKTKD");
		object.setPgm("dkg210d");
		object.setStatus("G");
		listObject.add(object);
		//
		object = new  MaintenanceMainListObject();
		object.setId("17");
		object.setSubject("Udgangstoldsted");
		object.setCode("117");
		object.setText("DKG210D / DKTKD");
		object.setDbTable("DKTKD");
		object.setPgm("dkg210d");
		object.setStatus("G");
		listObject.add(object);
		//
		object = new  MaintenanceMainListObject();
		object.setId("18");
		object.setSubject("Summarisk angivelse");
		object.setCode("118");
		object.setText("DKG210D / DKTKD");
		object.setDbTable("DKTKD");
		object.setPgm("dkg210d");
		object.setStatus("G");
		listObject.add(object);
		//
		object = new  MaintenanceMainListObject();
		object.setId("19");
		object.setSubject("Eksportartkoder");
		object.setCode("119");
		object.setText("DKG210D / DKTKD");
		object.setDbTable("DKTKD");
		object.setPgm("dkg210d");
		object.setStatus("G");
		listObject.add(object);
		//
		object = new  MaintenanceMainListObject();
		object.setId("20");
		object.setSubject("Procedurekoder");
		object.setCode("120");
		object.setText("DKG210D / DKTKD");
		object.setDbTable("DKTKD");
		object.setPgm("dkg210d");
		object.setStatus("G");
		listObject.add(object);
		//
		object = new  MaintenanceMainListObject();
		object.setId("21");
		object.setSubject("Procedurekoder ECS-YM");
		object.setCode("121");
		object.setText("DKG210D / DKTKD");
		object.setDbTable("DKTKD");
		object.setPgm("dkg210d");
		object.setStatus("G");
		listObject.add(object);
		//
		object = new  MaintenanceMainListObject();
		object.setId("22");
		object.setSubject("Erklæringskoder");
		object.setCode("122");
		object.setText("DKG210D / DKTKD");
		object.setDbTable("DKTKD");
		object.setPgm("dkg210d");
		object.setStatus("G");
		listObject.add(object);
		//
		object = new  MaintenanceMainListObject();
		object.setId("23");
		object.setSubject("T-status");
		object.setCode("123");
		object.setText("DKG210D / DKTKD");
		object.setDbTable("DKTKD");
		object.setPgm("dkg210d");
		object.setStatus("G");
		listObject.add(object);
		//
		object = new  MaintenanceMainListObject();
		object.setId("24");
		object.setSubject("Alternativ mængde");
		object.setCode("124");
		object.setText("DKG210D / DKTKD");
		object.setDbTable("DKTKD");
		object.setPgm("dkg210d");
		object.setStatus("G");
		listObject.add(object);
		//
		object = new  MaintenanceMainListObject();
		object.setId("25");
		object.setSubject("Lokationskoder i eEksport");
		object.setCode("125");
		object.setText("DKG210D / DKTKD");
		object.setDbTable("DKTKD");
		object.setPgm("dkg210d");
		object.setStatus("G");
		listObject.add(object);
		//
		object = new  MaintenanceMainListObject();
		object.setId("26");
		object.setSubject("Angivelsestype R1.2");
		object.setCode("126");
		object.setText("DKG210D / DKTKD");
		object.setDbTable("DKTKD");
		object.setPgm("dkg210d");
		object.setStatus("G");
		listObject.add(object);
		//
		object = new  MaintenanceMainListObject();
		object.setId("27");
		object.setSubject("Statuskoder CUSRES");
		object.setCode("127");
		object.setText("DKG210D / DKTKD");
		object.setDbTable("DKTKD");
		object.setPgm("dkg210d");
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

