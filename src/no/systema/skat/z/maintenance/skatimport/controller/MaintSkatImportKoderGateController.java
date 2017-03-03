package no.systema.skat.z.maintenance.skatimport.controller;

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
 * SKAT Maintenance Import Topic Controller 
 * 
 * @author oscardelatorre
 * @date Mar 03, 2017
 * 
 */

@Controller
@SessionAttributes(AppConstants.SYSTEMA_WEB_USER_KEY)
@Scope("session")
public class MaintSkatImportKoderGateController {
	private static final JsonDebugger jsonDebugger = new JsonDebugger();
	private static final Logger logger = Logger.getLogger(MaintSkatImportKoderGateController.class.getName());
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
	@RequestMapping(value="skatmaintenanceimport_kodergate.do", method=RequestMethod.GET)
	public ModelAndView doSkatImportList(HttpSession session, HttpServletRequest request){
		ModelAndView successView = new ModelAndView("skatmaintenanceimport_kodergate");
		SystemaWebUser appUser = (SystemaWebUser)session.getAttribute(AppConstants.SYSTEMA_WEB_USER_KEY);
		
		Map model = new HashMap();
		if(appUser==null){
			return this.loginView;
		}else{
			appUser.setActiveMenu(SystemaWebUser.ACTIVE_MENU_SKAT_MAINTENANCE_IMPORT);
			session.setAttribute(SkatMaintenanceConstants.ACTIVE_URL_RPG_SKAT_MAINTENANCE, SkatMaintenanceConstants.ACTIVE_URL_RPG_INITVALUE); 
		
			//lists
			List list = this.populateMaintenanceMainList();
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
		object.setSubject("Angivelsesarter");
		object.setCode("001");
		object.setText("DKG210D / DKTKD");
		object.setDbTable("DKTKD");
		object.setPgm("dkg210d");
		object.setStatus("G");
		listObject.add(object);
		//
		object = new  MaintenanceMainListObject();
		object.setId("2");
		object.setSubject("Angivelsesmedier");
		object.setCode("002");
		object.setText("DKG210D / DKTKD");
		object.setDbTable("DKTKD");
		object.setPgm("dkg210d");
		object.setStatus("G");
		listObject.add(object);
		//
		object = new  MaintenanceMainListObject();
		object.setId("3");
		object.setSubject("Beregningsarter, præferencekoder");
		object.setCode("003");
		object.setText("DKG210D / DKTKD");
		object.setDbTable("DKTKD");
		object.setPgm("dkg210d");
		object.setStatus("G");
		listObject.add(object);
		//
		object = new  MaintenanceMainListObject();
		object.setId("4");
		object.setSubject("Beregningslinier, arter");
		object.setCode("004");
		object.setText("DKG210D / DKTKD");
		object.setDbTable("DKTKD");
		object.setPgm("dkg210d");
		object.setStatus("G");
		listObject.add(object);
		//
		object = new  MaintenanceMainListObject();
		object.setId("5");
		object.setSubject("Certifikatkoder R44.2");
		object.setCode("005");
		object.setText("DKG210D / DKTKD");
		object.setDbTable("DKTKD");
		object.setPgm("dkg210d");
		object.setStatus("G");
		listObject.add(object);
		//
		object = new  MaintenanceMainListObject();
		object.setId("6");
		object.setSubject("Ekspeditionssted RA.2");
		object.setCode("006");
		object.setText("DKG210D / DKTKD");
		object.setDbTable("DKTKD");
		object.setPgm("dkg210d");
		object.setStatus("G");
		listObject.add(object);
		//
		object = new  MaintenanceMainListObject();
		object.setId("7");
		object.setSubject("Emballagekoder R31.4");
		object.setCode("007");
		object.setText("DKG210D / DKTKD");
		object.setDbTable("DKTKD");
		object.setPgm("dkg210d");
		object.setStatus("G");
		listObject.add(object);
		//
		object = new  MaintenanceMainListObject();
		object.setId("8");
		object.setSubject("Landekoder R15, R34");
		object.setCode("008");
		object.setText("DKG210D / DKTKD");
		object.setDbTable("DKTKD");
		object.setPgm("dkg210d");
		object.setStatus("G");
		listObject.add(object);
		//
		object = new  MaintenanceMainListObject();
		object.setId("9");
		object.setSubject("Oplysningstypekoder R44.9");
		object.setCode("009");
		object.setText("DKG210D / DKTKD");
		object.setDbTable("DKTKD");
		object.setPgm("dkg210d");
		object.setStatus("G");
		listObject.add(object);
		//
		object = new  MaintenanceMainListObject();
		object.setId("10");
		object.setSubject("Procedurekoder og bevillingskoder");
		object.setCode("010");
		object.setText("DKG210D / DKTKD");
		object.setDbTable("DKTKD");
		object.setPgm("dkg210d");
		object.setStatus("G");
		listObject.add(object);
		//
		object = new  MaintenanceMainListObject();
		object.setId("11");
		object.setSubject("Procedurekoder R37");
		object.setCode("011");
		object.setText("DKG210D / DKTKD");
		object.setDbTable("DKTKD");
		object.setPgm("dkg210d");
		object.setStatus("G");
		listObject.add(object);
		//
		object = new  MaintenanceMainListObject();
		object.setId("12");
		object.setSubject("Præferencedokumentationskoder R44.4");
		object.setCode("012");
		object.setText("DKG210D / DKTKD");
		object.setDbTable("DKTKD");
		object.setPgm("dkg210d");
		object.setStatus("G");
		listObject.add(object);
		//
		object = new  MaintenanceMainListObject();
		object.setId("13");
		object.setSubject("Præferencekoder R36");
		object.setCode("013");
		object.setText("DKG210D / DKTKD");
		object.setDbTable("DKTKD");
		object.setPgm("dkg210d");
		object.setStatus("G");
		listObject.add(object);
		//
		object = new  MaintenanceMainListObject();
		object.setId("14");
		object.setSubject("Præferencekoder, toldordninger, noter");
		object.setCode("014");
		object.setText("DKG210D / DKTKD");
		object.setDbTable("DKTKD");
		object.setPgm("dkg210d");
		object.setStatus("G");
		listObject.add(object);
		//
		object = new  MaintenanceMainListObject();
		object.setId("15");
		object.setSubject("Statuskoder, toldordninger, noter");
		object.setCode("015");
		object.setText("DKG210D / DKTKD");
		object.setDbTable("DKTKD");
		object.setPgm("dkg210d");
		object.setStatus("G");
		listObject.add(object);
		//
		object = new  MaintenanceMainListObject();
		object.setId("16");
		object.setSubject("Svartekstkoder");
		object.setCode("016");
		object.setText("DKG210D / DKTKD");
		object.setDbTable("DKTKD");
		object.setPgm("dkg210d");
		object.setStatus("G");
		listObject.add(object);
		//
		object = new  MaintenanceMainListObject();
		object.setId("17");
		object.setSubject("Transportdokumentkoder R40");
		object.setCode("017");
		object.setText("DKG210D / DKTKD");
		object.setDbTable("DKTKD");
		object.setPgm("dkg210d");
		object.setStatus("G");
		listObject.add(object);
		//
		object = new  MaintenanceMainListObject();
		object.setId("18");
		object.setSubject("Transportkoder R25, R26");
		object.setCode("018");
		object.setText("DKG210D / DKTKD");
		object.setDbTable("DKTKD");
		object.setPgm("dkg210d");
		object.setStatus("G");
		listObject.add(object);
		//
		object = new  MaintenanceMainListObject();
		object.setId("19");
		object.setSubject("VAB, certifikatkoder R44.3, R44.2");
		object.setCode("019");
		object.setText("DKG210D / DKTKD");
		object.setDbTable("DKTKD");
		object.setPgm("dkg210d");
		object.setStatus("G");
		listObject.add(object);
		//
		object = new  MaintenanceMainListObject();
		object.setId("20");
		object.setSubject("Valutakoder R22");
		object.setCode("020");
		object.setText("DKG210D / DKTKD");
		object.setDbTable("DKTKD");
		object.setPgm("dkg210d");
		object.setStatus("G");
		listObject.add(object);
		//
		object = new  MaintenanceMainListObject();
		object.setId("21");
		object.setSubject("Suppl_vare_opl_kod R44.6a");
		object.setCode("021");
		object.setText("DKG210D / DKTKD");
		object.setDbTable("DKTKD");
		object.setPgm("dkg210d");
		object.setStatus("G");
		listObject.add(object);
		//
		object = new  MaintenanceMainListObject();
		object.setId("22");
		object.setSubject("Suppl_enheder.koder R41.1");
		object.setCode("022");
		object.setText("DKG210D / DKTKD");
		object.setDbTable("DKTKD");
		object.setPgm("dkg210d");
		object.setStatus("G");
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

