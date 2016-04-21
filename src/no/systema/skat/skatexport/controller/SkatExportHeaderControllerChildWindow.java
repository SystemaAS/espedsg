package no.systema.skat.skatexport.controller;

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
import no.systema.skat.skatexport.service.SkatExportGeneralCodesChildWindowService;
import no.systema.skat.url.store.SkatUrlDataStore;
import no.systema.skat.util.SkatConstants;





/**
 * SKAT Export Header Controller - child windows popup
 * 
 * @author oscardelatorre
 * @date Apr 19, 2016
 * 
 */

@Controller
@SessionAttributes(AppConstants.SYSTEMA_WEB_USER_KEY)
@Scope("session")
public class SkatExportHeaderControllerChildWindow {
	
	private static final Logger logger = Logger.getLogger(SkatExportHeaderControllerChildWindow.class.getName());
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
	@RequestMapping(value="skatexport_edit_childwindow_generalcodes.do", params="action=doInit",  method={RequestMethod.GET} )
	public ModelAndView doInitGeneralCodes(@ModelAttribute ("record") JsonSkatCodeRecord recordToValidate, HttpSession session, HttpServletRequest request){
		this.context = TdsAppContext.getApplicationContext();
		logger.info("Inside: doInitGeneralCodes");
		Map model = new HashMap();
		String callerType = request.getParameter("ctype");
		String typeCode = request.getParameter("type");
		
		ModelAndView successView = new ModelAndView("skatexport_edit_childwindow_generalcodes");
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
	 * 
	 * @param recordToValidate
	 * @param session
	 * @param request
	 * @return
	 */
	@RequestMapping(value="skatexport_edit_childwindow_tullkontor.do", params="action=doInit",  method={RequestMethod.GET, RequestMethod.POST} )
	public ModelAndView doInitTullkontor(@ModelAttribute ("record") JsonSkatCodeRecord recordToValidate, HttpSession session, HttpServletRequest request){
		this.context = TdsAppContext.getApplicationContext();
		logger.info("Inside: doInitTullkontor");
		Map model = new HashMap();
		String callerType = request.getParameter("ctype");
		String typeCode = request.getParameter("type");
		String tullkontorCode = request.getParameter("tkkode");
		String tullkontorName = request.getParameter("tktxtn");
		
		ModelAndView successView = new ModelAndView("skatexport_edit_childwindow_tullkontor");
		SystemaWebUser appUser = this.loginValidator.getValidUser(session);
		//check user (should be in session already)
		if(appUser==null){
			return this.loginView;
			
		}else{
			List list = new ArrayList();
			if( (tullkontorName!=null && !"".equals(tullkontorName)) || (tullkontorCode!=null && !"".equals(tullkontorCode)) ){    
				list = this.getTullkontorList(appUser, typeCode, tullkontorName, tullkontorCode );
			}
			model.put("tullkontorList", list);
			model.put("callerType", callerType);
			model.put("type", typeCode);
			model.put("tkkode", tullkontorCode);
			model.put("tktxtn", tullkontorName);
			successView.addObject(SkatConstants.DOMAIN_MODEL , model);
			
	    	return successView;
		}
	}
 
	/**
	public static final String CODE_008_COUNTRY = "008";
	public static final String CODE_017_TRANSPORTDOK_SUMMARISKA_R40 = "017";
	public static final String CODE_022_SUPP_ENHEDER = "022"; //same as import
	
	public static final String CODE_102_ANGIVELSESARTER = "102";
	public static final String CODE_103_TOLDSTED = "103";
	public static final String CODE_104_ANGIVELSESTYPE = "104";
	public static final String CODE_106_INCOTERMS = "106";
	public static final String CODE_107_CURRENCY = "107";
	public static final String CODE_108_TRANSPORTMADE = "108";
	public static final String CODE_109_BETALNINGSMADE = "109";
	public static final String CODE_110_EMBALLAGE_R31 = "110";
	public static final String CODE_112_PROCEDURE_R37 = "112";
	public static final String CODE_113_CERTIFIKAT_R44_2 = "113";
	public static final String CODE_114_VAB_CERTIFIKAT_R44_3 = "114";
	public static final String CODE_115_FN_FARLIG_GODS_R44_4 = "115";
	public static final String CODE_116_TRANSPORTDOK_TYPE_R44_5_1 = "116";
	public static final String CODE_117_UDGANGSTOLDSTED = "117";
	public static final String CODE_118_TRANSPORTDOK_SUMMARISKA_KATEGORY_R40 = "118";
	public static final String CODE_119_EXPORTARTER = "119";
	public static final String CODE_122_ERKLAERINGER_YM = "122";
	public static final String CODE_123_T_STATUS = "123";
	public static final String CODE_124_SUPPL_ENHEDER_YM = "124";
	public static final String CODE_126_EU_ANGIVELSESARTER = "126";
	public static final String CODE_127_STATUS_KODER = "127";

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
		if(this.GENERAL_CODE_008_COUNTRY.equalsIgnoreCase(typeCode) || this.GENERAL_CODE_107_CURRENCY.equalsIgnoreCase(typeCode) ){
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
				container = this.skatExportGeneralCodesChildWindowService.getCodeContainer(jsonPayload);
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
	
	/**
	 * 
	 * @param appUser
	 * @param typeCode
	 * @return
	 */
	private List<JsonSkatCodeRecord> getTullkontorList(SystemaWebUser appUser, String typeCode, String tullkontorName, String tullkontorCode ){
		List<JsonSkatCodeRecord> list = new ArrayList<JsonSkatCodeRecord>();
		
		logger.info(Calendar.getInstance().getTime() + " CONTROLLER start - timestamp");
		String BASE_URL = SkatUrlDataStore.SKAT_CODES_URL;
		//Exception for CODE_URL (MUST be borrowed from SAD EKS/IMP
		if(this.GENERAL_CODE_008_COUNTRY.equalsIgnoreCase(typeCode) || this.GENERAL_CODE_107_CURRENCY.equalsIgnoreCase(typeCode) ){
		   BASE_URL = SkatUrlDataStore.SKAT_CODES_URL;
		}
		StringBuffer urlRequestParams = new StringBuffer();
		urlRequestParams.append("user=" + appUser.getUser());
		urlRequestParams.append("&typ=" + typeCode);
		//optionals
		if(tullkontorName!=null && !"".equals(tullkontorName)){
			urlRequestParams.append("&sonavn=" + tullkontorName);
		}
		if(tullkontorCode!=null && !"".equals(tullkontorCode)){
			urlRequestParams.append("&kode1=" + tullkontorCode);
		}
		
		
		logger.info(BASE_URL);
		logger.info(urlRequestParams);
		
		String jsonPayload = urlCgiProxyService.getJsonContent(BASE_URL, urlRequestParams.toString());
		
		JsonSkatCodeContainer container = null;
		try{
			if(jsonPayload!=null){
				container = this.skatExportGeneralCodesChildWindowService.getCodeContainer(jsonPayload);
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
	private SkatExportGeneralCodesChildWindowService skatExportGeneralCodesChildWindowService;
	@Autowired
	@Required	
	public void setSkatExportGeneralCodesChildWindowService(SkatExportGeneralCodesChildWindowService value){this.skatExportGeneralCodesChildWindowService = value;}
	public SkatExportGeneralCodesChildWindowService getSkatExportGeneralCodesChildWindowService(){ return this.skatExportGeneralCodesChildWindowService; }
	
	
}

