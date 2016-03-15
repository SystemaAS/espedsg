package no.systema.fraktkalkulator.controller;

import java.util.*;

import org.apache.log4j.Logger;
import org.springframework.validation.BindingResult;
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
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import org.springframework.web.bind.ServletRequestDataBinder;

//application imports
import no.systema.main.context.TdsAppContext;
import no.systema.main.service.UrlCgiProxyService;
import no.systema.main.validator.LoginValidator;
import no.systema.main.util.AppConstants;
import no.systema.main.util.DateTimeManager;
import no.systema.main.util.JsonDebugger;
import no.systema.main.model.SystemaWebUser;
import no.systema.main.model.jsonjackson.general.postalcodes.JsonPostalCodesContainer;
import no.systema.main.model.jsonjackson.general.postalcodes.JsonPostalCodesRecord;

//F.kalkulator
import no.systema.fraktkalkulator.service.FraktKalkulatorChildWindowsService;
import no.systema.fraktkalkulator.model.jsonjackson.customer.JsonFraktKalkulatorCustomerContainer;
import no.systema.fraktkalkulator.model.jsonjackson.customer.JsonFraktKalkulatorCustomerRecord;
import no.systema.fraktkalkulator.util.FraktKalkulatorConstants;
import no.systema.fraktkalkulator.url.store.FraktKalkulatorUrlDataStore;
import no.systema.fraktkalkulator.validator.FraktkalkulatorChildWindowSearchCustomerValidator;


/**
 * Priskalkulator Controller - child windows for search
 * 
 * @author oscardelatorre
 * @date Mar 10, 2015
 * 
 */

@Controller
@SessionAttributes(AppConstants.SYSTEMA_WEB_USER_KEY)
@Scope("session")
public class FraktKalkulatorControllerChildWindow {
	
	private static final Logger logger = Logger.getLogger(FraktKalkulatorControllerChildWindow.class.getName());
	private static final JsonDebugger jsonDebugger = new JsonDebugger(1000);
	//customer
	private final String DATATABLE_CUSTOMER_LIST = "customerList";
	//postal codes
	private final String POSTALCODE_DIRECTION = "direction";
	private final String POSTALCODE_DIRECTION_FRA = "fra";
	private final String POSTALCODE_DIRECTION_TIL = "til";
	private final String DATATABLE_POSTALCODES_LIST = "postalCodesList";
	
	private ModelAndView loginView = new ModelAndView("login");
	private ApplicationContext context;
	private LoginValidator loginValidator = new LoginValidator();
	//private CodeDropDownMgr codeDropDownMgr = new CodeDropDownMgr();
	private DateTimeManager dateTimeMgr = new DateTimeManager();
	
	/**
	 * 
	 * @param session
	 * @param request
	 * @return
	 */
	@RequestMapping(value="fraktkalkulator_childwindow_customer", params="action=doInit",  method={RequestMethod.GET} )
	public ModelAndView doInit(@ModelAttribute ("record") JsonFraktKalkulatorCustomerRecord recordToValidate, HttpSession session, HttpServletRequest request){
		this.context = TdsAppContext.getApplicationContext();
		logger.info("Inside: doInit");
		Map model = new HashMap();
		
		ModelAndView successView = new ModelAndView("fraktkalkulator_childwindow_customer");
		SystemaWebUser appUser = this.loginValidator.getValidUser(session);
		//check user (should be in session already)
		if(appUser==null){
			return this.loginView;
			
		}else{
			logger.info(Calendar.getInstance().getTime() + " CONTROLLER start - timestamp");
			model.put(FraktKalkulatorConstants.DOMAIN_RECORD, recordToValidate);
			successView.addObject(FraktKalkulatorConstants.DOMAIN_MODEL , model);
	    		return successView;
		}
	}	
	
	/**
	 * 
	 * @param session
	 * @param request
	 * @return
	 */
	
	@RequestMapping(value="fraktkalkulator_childwindow_customer", params="action=doFind",  method={RequestMethod.GET, RequestMethod.POST} )
	public ModelAndView doFind(@ModelAttribute ("record") JsonFraktKalkulatorCustomerRecord recordToValidate, BindingResult bindingResult, HttpSession session, HttpServletRequest request){
		this.context = TdsAppContext.getApplicationContext();
		logger.info("Inside: doFind");
		Collection outputList = new ArrayList();
		Map model = new HashMap();
		
		ModelAndView successView = new ModelAndView("fraktkalkulator_childwindow_customer");
		SystemaWebUser appUser = this.loginValidator.getValidUser(session);
		//check user (should be in session already)
		if(appUser==null){
			return loginView;
			
		}else{
			//appUser.setActiveMenu(SystemaWebUser.ACTIVE_MENU_FRAKTKALKULATOR);
			logger.info(Calendar.getInstance().getTime() + " CONTROLLER start - timestamp");
			
			//-----------
			//Validation
			//-----------
			FraktkalkulatorChildWindowSearchCustomerValidator validator = new FraktkalkulatorChildWindowSearchCustomerValidator();
			logger.info("Host via HttpServletRequest.getHeader('Host'): " + request.getHeader("Host"));
		    validator.validate(recordToValidate, bindingResult);
		    
		    //check for ERRORS
			if(bindingResult.hasErrors()){
		    		logger.info("[ERROR Validation] search-filter does not validate)");
		    		//put domain objects and do go back to the successView from here
		    		//this.setCodeDropDownMgr(appUser, model);
		    		model.put(FraktKalkulatorConstants.DOMAIN_RECORD, recordToValidate);
				successView.addObject(FraktKalkulatorConstants.DOMAIN_MODEL, model);
				return successView;
	    		
		    }else{
				
		    		//prepare the access CGI with RPG back-end
		    		String BASE_URL = FraktKalkulatorUrlDataStore.FRAKTKALKULATOR_FETCH_CUSTOMER_URL;
		    		String urlRequestParamsKeys = this.getRequestUrlKeyParametersSearchCustomer(recordToValidate, appUser);
		    		logger.info("URL: " + BASE_URL);
		    		logger.info("PARAMS: " + urlRequestParamsKeys);
		    		logger.info(Calendar.getInstance().getTime() +  " CGI-start timestamp");
		    		String jsonPayload = this.urlCgiProxyService.getJsonContent(BASE_URL, urlRequestParamsKeys);
		    		//Debug -->
			    	jsonDebugger.debugJsonPayload(jsonPayload);
		    		logger.info(Calendar.getInstance().getTime() +  " CGI-end timestamp");
			    
		    		if(jsonPayload!=null){
			    		jsonPayload = jsonPayload.replaceFirst("Customerlist", "customerlist");
			    		JsonFraktKalkulatorCustomerContainer container = this.fraktKalkulatorChildWindowsService.getCustomerContainer(jsonPayload);
			    		
			    		if(container!=null){
			    			List<JsonFraktKalkulatorCustomerRecord> list = new ArrayList();
			    			for(JsonFraktKalkulatorCustomerRecord  record : container.getCustomerlist()){
			    				//logger.info("CUSTOMER NO: " + record.getKundnr());
			    				//logger.info("NAME: " + record.getKnavn());
			    				list.add(record);
			    			}
			    			model.put(this.DATATABLE_CUSTOMER_LIST, list);
			    			model.put(FraktKalkulatorConstants.DOMAIN_RECORD, recordToValidate);
			    		}
		    			successView.addObject(FraktKalkulatorConstants.DOMAIN_MODEL , model);
					logger.info(Calendar.getInstance().getTime() + " CONTROLLER end - timestamp");
					return successView;
					
			    	}else{
					logger.fatal("NO CONTENT on jsonPayload from URL... ??? <Null>");
					return loginView;
				}
				
		    }
		}
	}	
	
	/**
	 * 
	 * @param session
	 * @param request
	 * @return
	 */
	@RequestMapping(value="fraktkalkulator_childwindow_postalcodes", params="action=doInit",  method={RequestMethod.GET} )
	public ModelAndView doInitPostalCodes(@ModelAttribute ("record") JsonPostalCodesRecord recordToValidate, HttpSession session, HttpServletRequest request){
		this.context = TdsAppContext.getApplicationContext();
		logger.info("Inside: doInitPostalCodes");
		Map model = new HashMap();

		StringBuffer paramsRedirect = new StringBuffer();
		paramsRedirect.append("&direction=" + recordToValidate.getDirection());
		if(recordToValidate.getSt2lk()!=null && !"".equals(recordToValidate.getSt2lk())){
			paramsRedirect.append("&st2lk=" + recordToValidate.getSt2lk());
		}
		ModelAndView successView = new ModelAndView("redirect:fraktkalkulator_childwindow_postalcodes?action=doFind" + paramsRedirect.toString());
		SystemaWebUser appUser = this.loginValidator.getValidUser(session);
		//check user (should be in session already)
		if(appUser==null){
			return this.loginView;
			
		}else{
			logger.info(Calendar.getInstance().getTime() + " CONTROLLER start - timestamp");
			model.put(this.POSTALCODE_DIRECTION, recordToValidate.getDirection());
			model.put(FraktKalkulatorConstants.DOMAIN_RECORD, recordToValidate);
			
			successView.addObject(FraktKalkulatorConstants.DOMAIN_MODEL , model);
	    		return successView;
		}
	}	
	
	/**
	 * 
	 * @param recordToValidate
	 * @param bindingResult
	 * @param session
	 * @param request
	 * @return
	 */
	@RequestMapping(value="fraktkalkulator_childwindow_postalcodes", params="action=doFind",  method={RequestMethod.GET, RequestMethod.POST} )
	public ModelAndView doFindPostalCodes(@ModelAttribute ("record") JsonPostalCodesRecord recordToValidate, BindingResult bindingResult, HttpSession session, HttpServletRequest request){
		this.context = TdsAppContext.getApplicationContext();
		logger.info("Inside: doFind");
		Collection outputList = new ArrayList();
		Map model = new HashMap();
		
		ModelAndView successView = new ModelAndView("fraktkalkulator_childwindow_postalcodes");
		SystemaWebUser appUser = this.loginValidator.getValidUser(session);
		//check user (should be in session already)
		if(appUser==null){
			return loginView;
			
		}else{
			//appUser.setActiveMenu(SystemaWebUser.ACTIVE_MENU_FRAKTKALKULATOR);
			logger.info(Calendar.getInstance().getTime() + " CONTROLLER start - timestamp");
			
			//-----------
			//Validation
			//-----------
			/* TODO
			SadImportListValidator validator = new SadImportListValidator();
			logger.info("Host via HttpServletRequest.getHeader('Host'): " + request.getHeader("Host"));
		    validator.validate(recordToValidate, bindingResult);
		    */
		    //check for ERRORS
			if(bindingResult.hasErrors()){
		    		/*logger.info("[ERROR Validation] search-filter does not validate)");
		    		//put domain objects and do go back to the successView from here
		    		//drop downs
		    		this.setCodeDropDownMgr(appUser, model);
				successView.addObject(SporringOppdragConstants.DOMAIN_MODEL, model);
		    		successView.addObject(SporringOppdragConstants.DOMAIN_LIST, new ArrayList());
				successView.addObject("searchFilter", recordToValidate);
				*/
				return successView;
	    		
		    }else{
				
		    		//prepare the access CGI with RPG back-end
		    		String BASE_URL = FraktKalkulatorUrlDataStore.FRAKTKALKULATOR_FETCH_POSTAL_CODES_URL;
		    		String urlRequestParamsKeys = this.getRequestUrlKeyParametersSearchPostalCodes(recordToValidate, appUser);
		    		logger.info("URL: " + BASE_URL);
		    		logger.info("PARAMS: " + urlRequestParamsKeys);
		    		logger.info(Calendar.getInstance().getTime() +  " CGI-start timestamp");
		    		String jsonPayload = this.urlCgiProxyService.getJsonContent(BASE_URL, urlRequestParamsKeys);
		    		//Debug -->
			    	jsonDebugger.debugJsonPayload(jsonPayload);
		    		logger.info(Calendar.getInstance().getTime() +  " CGI-end timestamp");
			    
		    		if(jsonPayload!=null){
			    		JsonPostalCodesContainer container = this.fraktKalkulatorChildWindowsService.getPostalCodesContainer(jsonPayload);
			    		
			    		if(container!=null){
			    			List<JsonPostalCodesRecord> list = new ArrayList();
			    			for(JsonPostalCodesRecord  record : container.getPostnrlist()){
			    				list.add(record);
			    			}
			    			model.put(this.DATATABLE_POSTALCODES_LIST, list);
			    			model.put(FraktKalkulatorConstants.DOMAIN_RECORD, recordToValidate);
			    			model.put(this.POSTALCODE_DIRECTION, recordToValidate.getDirection());
			    		}
		    			successView.addObject(FraktKalkulatorConstants.DOMAIN_MODEL , model);
					logger.info(Calendar.getInstance().getTime() + " CONTROLLER end - timestamp");
					return successView;
					
			    	}else{
					logger.fatal("NO CONTENT on jsonPayload from URL... ??? <Null>");
					return loginView;
				}
				
		    }
		}
	}
	
	/**
	 * 
	 * @param searchFilter
	 * @param appUser
	 * @return
	 */
	private String getRequestUrlKeyParametersSearchCustomer(JsonFraktKalkulatorCustomerRecord searchFilter, SystemaWebUser appUser){
		StringBuffer urlRequestParamsKeys = new StringBuffer();
		urlRequestParamsKeys.append("user=" + appUser.getUser());
		
		if(searchFilter.getKundnr()!=null && !"".equals(searchFilter.getKundnr())){
			urlRequestParamsKeys.append(FraktKalkulatorConstants.URL_CHAR_DELIMETER_FOR_PARAMS_WITH_HTML_REQUEST + "knr=" + searchFilter.getKundnr());
		}
		if(searchFilter.getKnavn()!=null && !"".equals(searchFilter.getKnavn())){
			urlRequestParamsKeys.append(FraktKalkulatorConstants.URL_CHAR_DELIMETER_FOR_PARAMS_WITH_HTML_REQUEST + "sonavn=" + searchFilter.getKnavn());
		}
		return urlRequestParamsKeys.toString();
	}
	/**
	 * 
	 * @param searchFilter
	 * @param appUser
	 * @return
	 */
	private String getRequestUrlKeyParametersSearchPostalCodes(JsonPostalCodesRecord searchFilter, SystemaWebUser appUser){
		StringBuffer urlRequestParamsKeys = new StringBuffer();
		urlRequestParamsKeys.append("user=" + appUser.getUser());
		
		if(this.POSTALCODE_DIRECTION_FRA.equals(searchFilter.getDirection())){
			urlRequestParamsKeys.append("&varlk=fralk");
			urlRequestParamsKeys.append("&varkod=fra");
		}else if(this.POSTALCODE_DIRECTION_TIL.equals(searchFilter.getDirection())){
			urlRequestParamsKeys.append("&varlk=tillk");
			urlRequestParamsKeys.append("&varkod=til");
		}
		
		if(searchFilter.getSt2lk()!=null && !"".equals(searchFilter.getSt2lk())){
			urlRequestParamsKeys.append(FraktKalkulatorConstants.URL_CHAR_DELIMETER_FOR_PARAMS_WITH_HTML_REQUEST + "soklk=" + searchFilter.getSt2lk());
		}
		if(searchFilter.getSt2nvn()!=null && !"".equals(searchFilter.getSt2nvn())){
			urlRequestParamsKeys.append(FraktKalkulatorConstants.URL_CHAR_DELIMETER_FOR_PARAMS_WITH_HTML_REQUEST + "soknvn=" + searchFilter.getSt2nvn());
		}
		if(searchFilter.getWskunpa()!=null && !"".equals(searchFilter.getWskunpa())){
			urlRequestParamsKeys.append(FraktKalkulatorConstants.URL_CHAR_DELIMETER_FOR_PARAMS_WITH_HTML_REQUEST + "wskunpa=" + searchFilter.getWskunpa());
		}
		
		return urlRequestParamsKeys.toString();
		
	}
	
	/**
	 * 
	 * @param appUser
	 * @param model
	 */
	private void setCodeDropDownMgr(SystemaWebUser appUser, Map model){
		/* TODO COVI Status
		 * 
		this.codeDropDownMgr.populateCodesHtmlDropDownsFromJsonString(this.urlCgiProxyService, this.tvinnSadDropDownListPopulationService,
				 model,appUser,CodeDropDownMgr.CODE_2_COUNTRY, null, null);
		*/
	}
	
	

	//SERVICES
	@Qualifier ("urlCgiProxyService")
	private UrlCgiProxyService urlCgiProxyService;
	@Autowired
	@Required
	public void setUrlCgiProxyService (UrlCgiProxyService value){ this.urlCgiProxyService = value; }
	public UrlCgiProxyService getUrlCgiProxyService(){ return this.urlCgiProxyService; }
	
	
	@Qualifier 
	private FraktKalkulatorChildWindowsService fraktKalkulatorChildWindowsService;
	@Autowired
	@Required	
	public void setFraktKalkulatorChildWindowsService(FraktKalkulatorChildWindowsService value){this.fraktKalkulatorChildWindowsService = value;}
	public FraktKalkulatorChildWindowsService getFraktKalkulatorChildWindowsService(){ return this.fraktKalkulatorChildWindowsService; }
	
	
}

