package no.systema.skat.skatimport.controller;

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

import no.systema.skat.model.jsonjackson.codes.JsonSkatCodeContainer;
import no.systema.skat.model.jsonjackson.codes.JsonSkatCodeRecord;
import no.systema.skat.skatimport.service.SkatImportGeneralCodesChildWindowService;
import no.systema.skat.url.store.SkatUrlDataStore;
import no.systema.skat.util.SkatConstants;





/**
 * SKAT Import Header Controller - child windows popup
 * 
 * @author oscardelatorre
 * @date Apr 19, 2016
 * 
 */

@Controller
@SessionAttributes(AppConstants.SYSTEMA_WEB_USER_KEY)
@Scope("session")
public class SkatImportHeaderControllerChildWindow {
	
	private static final Logger logger = Logger.getLogger(SkatImportHeaderControllerChildWindow.class.getName());
	private static final JsonDebugger jsonDebugger = new JsonDebugger(2000);
	//customer
	private final String DATATABLE_LIST = "list";
	private final String GENERAL_CODE_008_COUNTRY = "008";
	private final String GENERAL_CODE_020_CURRENCY = "020";
	
	
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
	@RequestMapping(value="skatimport_edit_childwindow_generalcodes.do", params="action=doInit",  method={RequestMethod.GET} )
	public ModelAndView doInitGeneralCodes(@ModelAttribute ("record") JsonSkatCodeRecord recordToValidate, HttpSession session, HttpServletRequest request){
		this.context = TdsAppContext.getApplicationContext();
		logger.info("Inside: doInitGeneralCodes");
		Map model = new HashMap();
		String callerType = request.getParameter("ctype");
		String typeCode = request.getParameter("type");
		
		ModelAndView successView = new ModelAndView("skatimport_edit_childwindow_generalcodes");
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
	public static final String CODE_001_ANGIVELSESARTER = "001";
	public static final String CODE_005_CERTIFIKAT_R44_2 = "005";
	public static final String CODE_006_TOLDSTED = "006";
	public static final String CODE_007_EMBALLAGE_R31 = "007";
	public static final String CODE_008_COUNTRY = "008";
	public static final String CODE_009_OPLYSNINGSTYPE_R44_9 = "009";
	public static final String CODE_011_PROCEDURE_R37 = "011";
	public static final String CODE_012_PDOKUMENTATIONSKODER_R44_4 = "012";
	public static final String CODE_013_PREFERENCE_R36 = "013";
	public static final String CODE_015_STATUS_KODER = "015";
	public static final String CODE_017_TRANSPORTDOK_SUMMARISKA_R40 = "017";
	public static final String CODE_018_TRANSPORTKODER_R25R26 = "018";
	public static final String CODE_019_VAB_CERTIFIKAT_R44_3 = "019";
	public static final String CODE_020_CURRENCY = "020";
	public static final String CODE_021_SUPP_VAREOPL_R44_6 = "021";
	public static final String CODE_022_SUPP_ENHEDER_R41_1 = "022";
	public static final String CODE_109_BETALING_FOR_TRANSPORT_RS29 = "109";
	//Borrowed from Export
	public static final String CODE_106_INCOTERMS = "106";

	 * 
	 *  @param appUser
	 *  @param codeType
	 *  @return
	 * 
	 */
	
	private List<JsonSkatCodeRecord> getCodeList(SystemaWebUser appUser, String typeCode){
		List<JsonSkatCodeRecord> list = new ArrayList<JsonSkatCodeRecord>();
		
		logger.info(Calendar.getInstance().getTime() + " CONTROLLER start - timestamp");
		String BASE_URL = SkatUrlDataStore.SKAT_CODES_URL;
		//Exception for CODE_URL (MUST be borrowed from SAD EKS/IMP
		if(this.GENERAL_CODE_008_COUNTRY.equalsIgnoreCase(typeCode) || this.GENERAL_CODE_020_CURRENCY.equalsIgnoreCase(typeCode) ){
		   BASE_URL = SkatUrlDataStore.SKAT_CODES_URL;
		}
		StringBuffer urlRequestParams = new StringBuffer();
		urlRequestParams.append("user=" + appUser.getUser());
		urlRequestParams.append("&typ=" + typeCode);
		
		logger.info(BASE_URL);
		logger.info(urlRequestParams);
		
		String jsonPayload = urlCgiProxyService.getJsonContent(BASE_URL, urlRequestParams.toString());
		
		JsonSkatCodeContainer container = null;
		try{
			if(jsonPayload!=null){
				container = this.skatImportGeneralCodesChildWindowService.getCodeContainer(jsonPayload);
				if(container!=null){
					for(JsonSkatCodeRecord  record : container.getKodlista()){
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
	private SkatImportGeneralCodesChildWindowService skatImportGeneralCodesChildWindowService;
	@Autowired
	@Required	
	public void setSkatImportGeneralCodesChildWindowService(SkatImportGeneralCodesChildWindowService value){this.skatImportGeneralCodesChildWindowService = value;}
	public SkatImportGeneralCodesChildWindowService getSkatImportGeneralCodesChildWindowService(){ return this.skatImportGeneralCodesChildWindowService; }
	
	
}

