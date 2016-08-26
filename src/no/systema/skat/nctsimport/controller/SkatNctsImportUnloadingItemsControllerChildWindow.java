package no.systema.skat.nctsimport.controller;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.util.*;

import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.springframework.validation.BindingResult;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Required;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Scope;

import javax.annotation.PostConstruct;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import org.springframework.web.bind.ServletRequestDataBinder;

//application imports
import no.systema.main.context.TdsAppContext;
import no.systema.main.service.UrlCgiProxyService;
import no.systema.main.service.UrlCgiProxyServiceImpl;
import no.systema.main.validator.LoginValidator;
import no.systema.main.util.AppConstants;
import no.systema.main.util.DateTimeManager;
import no.systema.main.util.EncodingTransformer;
import no.systema.main.util.JsonDebugger;
import no.systema.main.model.SystemaWebUser;

import no.systema.skat.model.jsonjackson.codes.JsonSkatNctsCodeContainer;
import no.systema.skat.model.jsonjackson.codes.JsonSkatNctsCodeRecord;

import no.systema.skat.nctsimport.service.SkatNctsImportGeneralCodesChildWindowService;
import no.systema.skat.url.store.SkatUrlDataStore;
import no.systema.skat.util.SkatConstants;





/**
 * SKAT NCTS Import Unloading Items Controller - child windows popup
 * 
 * @author oscardelatorre
 * @date Aug 25, 2016
 * 
 */

@Controller
@SessionAttributes(AppConstants.SYSTEMA_WEB_USER_KEY)
@Scope("session")
public class SkatNctsImportUnloadingItemsControllerChildWindow {
	
	private static final Logger logger = Logger.getLogger(SkatNctsImportUnloadingItemsControllerChildWindow.class.getName());
	private static final JsonDebugger jsonDebugger = new JsonDebugger(2000);
	//customer
	private final String DATATABLE_LIST = "list";
	private final String GENERAL_CODE_008_COUNTRY = "008";
	private final String GENERAL_CODE_107_CURRENCY = "107";
	
	
	private ModelAndView loginView = new ModelAndView("login");
	private ApplicationContext context;
	private LoginValidator loginValidator = new LoginValidator();
	//private CodeDropDownMgr codeDropDownMgr = new CodeDropDownMgr();
	private DateTimeManager dateTimeMgr = new DateTimeManager();
	
	
	@PostConstruct
	public void initIt() throws Exception {
		if("DEBUG".equals(AppConstants.LOG4J_LOGGER_LEVEL)){
			logger.setLevel(Level.DEBUG);
		}
	}
	
	
	/**
	 * 
	 * @param recordToValidate
	 * @param session
	 * @param request
	 * @return
	 */
	@RequestMapping(value="skatnctsimport_unloading_edit_items_childwindow_generalcodes.do", params="action=doInit",  method={RequestMethod.GET} )
	public ModelAndView doInitGeneralCodes(@ModelAttribute ("record") JsonSkatNctsCodeRecord recordToValidate, HttpSession session, HttpServletRequest request){
		this.context = TdsAppContext.getApplicationContext();
		logger.info("Inside: doInitGeneralCodes");
		Map model = new HashMap();
		String callerType = request.getParameter("ctype");
		String typeCode = request.getParameter("type");
		
		ModelAndView successView = new ModelAndView("skatnctsimport_unloading_edit_items_childwindow_generalcodes");
		SystemaWebUser appUser = this.loginValidator.getValidUser(session);
		//check user (should be in session already)
		if(appUser==null){
			return this.loginView;
			
		}else{
			  
			List list = this.getCodeList(appUser, typeCode);
			model.put("generalCodeList", list);
			model.put("callerType", callerType);
			
			successView.addObject(SkatConstants.DOMAIN_MODEL , model);
			
	    	return successView;
		}
	}
 
	/**
		012= KOD_SPRAK
		013= KOD_DOK
		014= KOD_TIDIGARE_DOK
		017= KOD_KOLLI_TYP
		031= KOD_DEKL_TYP
		039= KOD_TILLAGSUPP
		047= KOD_KONTROLLRESULTAT
		064= KOD_KANSLIGVARA
		096= KOD_SPEC_OMST
		105= KOD_TILLGANGASKOD_GARANTI
		106= KOD_TULLKONTOR_REF
		108= KOD_TRANSPORTMÅDE
		116= KOD_BETALNINGSSATT_TRANSPORTKOSTNAD
		302= KOD_STATUS_KODER_NCTS_EXPORT
		
		BORROWED FROM SKAT EXPORT/IMPORT
		008= Country code
		107= Currency code

	 * 
	 *  @param appUser
	 *  @param codeType
	 *  @return
	 * 
	 */
	
	private List<JsonSkatNctsCodeRecord> getCodeList(SystemaWebUser appUser, String typeCode){
		List<JsonSkatNctsCodeRecord> list = new ArrayList<JsonSkatNctsCodeRecord>();
		
		logger.info(Calendar.getInstance().getTime() + " CONTROLLER start - timestamp");
		String BASE_URL = SkatUrlDataStore.SKAT_NCTS_CODES_URL;
		//Exception for CODE_URL (MUST be borrowed from SAD EKS/IMP
		if(this.GENERAL_CODE_008_COUNTRY.equalsIgnoreCase(typeCode) || this.GENERAL_CODE_107_CURRENCY.equalsIgnoreCase(typeCode) ){
		   BASE_URL = SkatUrlDataStore.SKAT_CODES_URL;
		}
		StringBuffer urlRequestParams = new StringBuffer();
		urlRequestParams.append("user=" + appUser.getUser());
		urlRequestParams.append("&typ=" + typeCode);
		
		logger.info(BASE_URL);
		logger.info(urlRequestParams);
		
		String jsonPayload = urlCgiProxyService.getJsonContent(BASE_URL, urlRequestParams.toString());
		
		JsonSkatNctsCodeContainer container = null;
		try{
			if(jsonPayload!=null){
				container = this.skatNctsImportGeneralCodesChildWindowService.getNctsCodeContainer(jsonPayload);
				if(container!=null){
					for(JsonSkatNctsCodeRecord  record : container.getKodlista()){
						list.add(record);
					}
				}
			}
		}catch(Exception e){
			e.printStackTrace();
		}
		return list;
	}
	
	
	
	//SERVICES
	@Qualifier ("urlCgiProxyService")
	private UrlCgiProxyService urlCgiProxyService;
	@Autowired
	@Required
	public void setUrlCgiProxyService (UrlCgiProxyService value){ this.urlCgiProxyService = value; }
	public UrlCgiProxyService getUrlCgiProxyService(){ return this.urlCgiProxyService; }
	
	
	@Qualifier 
	private SkatNctsImportGeneralCodesChildWindowService skatNctsImportGeneralCodesChildWindowService;
	@Autowired
	@Required	
	public void setSkatNctsImportGeneralCodesChildWindowService(SkatNctsImportGeneralCodesChildWindowService value){this.skatNctsImportGeneralCodesChildWindowService = value;}
	public SkatNctsImportGeneralCodesChildWindowService getSkatNctsImportGeneralCodesChildWindowService(){ return this.skatNctsImportGeneralCodesChildWindowService; }
	
	
	
	
}

