package no.systema.efaktura.controller;

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

//Efaktura
import no.systema.efaktura.service.EfakturaChildWindowService;
import no.systema.efaktura.model.jsonjackson.childwindow.JsonEfakturaCustomerContainer;
import no.systema.efaktura.model.jsonjackson.childwindow.JsonEfakturaCustomerRecord;
import no.systema.efaktura.util.EfakturaConstants;
import no.systema.efaktura.url.store.EfakturaUrlDataStore;
//import no.systema.efaktura.validator.XX;




/**
 * Efaktura Controller - child windows for search
 * 
 * @author oscardelatorre
 * @date Nov 30, 2015
 * 
 */

@Controller
@SessionAttributes(AppConstants.SYSTEMA_WEB_USER_KEY)
@Scope("session")
public class EfakturaChildWindowController {
	
	private static final Logger logger = Logger.getLogger(EfakturaChildWindowController.class.getName());
	private static final JsonDebugger jsonDebugger = new JsonDebugger(2000);
	private DateTimeManager dateTimeManager = new DateTimeManager();
	
	//customer
	private final String DATATABLE_CUSTOMER_LIST = "customerList";
	
	
	
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
	 * Customer doInit
	 * @param recordToValidate
	 * @param session
	 * @param request
	 * @return
	 */
	@RequestMapping(value="efaktura_childwindow_customer.do", params="action=doInit",  method={RequestMethod.GET} )
	public ModelAndView doInitCustomer(@ModelAttribute ("record") JsonEfakturaCustomerContainer recordToValidate, HttpSession session, HttpServletRequest request){
		this.context = TdsAppContext.getApplicationContext();
		logger.info("Inside: doInitCustomer");
		Map model = new HashMap();
		ModelAndView successView = new ModelAndView("efaktura_childwindow_customer");
		SystemaWebUser appUser = this.loginValidator.getValidUser(session);
		//check user (should be in session already)
		if(appUser==null){
			return this.loginView;
			
		}else{
			logger.info(Calendar.getInstance().getTime() + " CONTROLLER start - timestamp");
			model.put(EfakturaConstants.DOMAIN_CONTAINER, recordToValidate);
			successView.addObject(EfakturaConstants.DOMAIN_MODEL , model);
	    		return successView;
		}
	}	
	
	/**
	 * Customer
	 * 
	 * @param recordToValidate
	 * @param bindingResult
	 * @param session
	 * @param request
	 * @return
	 */
	@RequestMapping(value="efaktura_childwindow_customer.do", params="action=doFind",  method={RequestMethod.GET, RequestMethod.POST} )
	public ModelAndView doFindCustomer(@ModelAttribute ("record") JsonEfakturaCustomerContainer recordToValidate, BindingResult bindingResult, HttpSession session, HttpServletRequest request){
		this.context = TdsAppContext.getApplicationContext();
		logger.info("Inside: doFindCustomer");
		Collection outputList = new ArrayList();
		Map model = new HashMap();
		ModelAndView successView = new ModelAndView("efaktura_childwindow_customer");
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
			/*EfakturaChildWindowSearchCustomerValidator validator = new FraktkalkulatorChildWindowSearchCustomerValidator();
			logger.info("Host via HttpServletRequest.getHeader('Host'): " + request.getHeader("Host"));
		    validator.validate(recordToValidate, bindingResult);
		    */
		    //check for ERRORS
			if(bindingResult.hasErrors()){
		    		logger.info("[ERROR Validation] search-filter does not validate)");
		    		//put domain objects and do go back to the successView from here
		    		//this.setCodeDropDownMgr(appUser, model);
		    		model.put(EfakturaConstants.DOMAIN_CONTAINER, recordToValidate);
				successView.addObject(EfakturaConstants.DOMAIN_MODEL, model);
				return successView;
	    		
		    }else{
				
		    		//prepare the access CGI with RPG back-end
		    		String BASE_URL = EfakturaUrlDataStore.EFAKTURA_BASE_CHILDWINDOW_CUSTOMER_URL;
		    		String urlRequestParamsKeys = this.getRequestUrlKeyParametersSearchChildWindow(recordToValidate, appUser);
		    		logger.info("URL: " + BASE_URL);
		    		logger.info("PARAMS: " + urlRequestParamsKeys);
		    		logger.info(Calendar.getInstance().getTime() +  " CGI-start timestamp");
		    		String jsonPayload = this.urlCgiProxyService.getJsonContent(BASE_URL, urlRequestParamsKeys);
		    		//Debug -->
			    	logger.debug(jsonDebugger.debugJsonPayloadWithLog4J(jsonPayload));
		    		logger.info(Calendar.getInstance().getTime() +  " CGI-end timestamp");
			    
		    		if(jsonPayload!=null){
		    			JsonEfakturaCustomerContainer container = this.efakturaChildWindowService.getCustomerContainer(jsonPayload);
			    		if(container!=null){
			    			List<JsonEfakturaCustomerRecord> list = new ArrayList<JsonEfakturaCustomerRecord>();
			    			for(JsonEfakturaCustomerRecord  record : container.getInqcustomer()){
			    				//logger.info("CUSTOMER NO: " + record.getKundnr());
			    				//logger.info("NAME: " + record.getNavn());
			    				list.add(record);
			    			}
			    			model.put(this.DATATABLE_CUSTOMER_LIST, list);
			    			model.put(EfakturaConstants.DOMAIN_CONTAINER, recordToValidate);
			    		}
		    			successView.addObject(EfakturaConstants.DOMAIN_MODEL , model);
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
	 * Customer
	 * @param searchFilter
	 * @param appUser
	 * @return
	 */
	private String getRequestUrlKeyParametersSearchChildWindow(JsonEfakturaCustomerContainer searchFilter, SystemaWebUser appUser){
		StringBuffer urlRequestParamsKeys = new StringBuffer();
		urlRequestParamsKeys.append("user=" + appUser.getUser());
		
		if(searchFilter.getSokknr()!=null && !"".equals(searchFilter.getSokknr())){
			urlRequestParamsKeys.append(EfakturaConstants.URL_CHAR_DELIMETER_FOR_PARAMS_WITH_HTML_REQUEST + "sokknr=" + searchFilter.getSokknr());
		}
		if(searchFilter.getSoknvn()!=null && !"".equals(searchFilter.getSoknvn())){
			urlRequestParamsKeys.append(EfakturaConstants.URL_CHAR_DELIMETER_FOR_PARAMS_WITH_HTML_REQUEST + "soknvn=" + searchFilter.getSoknvn());
		}
		if(searchFilter.getKunpnsted()!=null && !"".equals(searchFilter.getKunpnsted())){
			urlRequestParamsKeys.append(EfakturaConstants.URL_CHAR_DELIMETER_FOR_PARAMS_WITH_HTML_REQUEST + "kunpnsted=" + searchFilter.getKunpnsted());
		}
		if(searchFilter.getWsvarnv()!=null && !"".equals(searchFilter.getWsvarnv())){
			urlRequestParamsKeys.append(EfakturaConstants.URL_CHAR_DELIMETER_FOR_PARAMS_WITH_HTML_REQUEST + "wsvarnv=" + searchFilter.getWsvarnv());
		}
		if(searchFilter.getMaxv()!=null && !"".equals(searchFilter.getMaxv())){
			urlRequestParamsKeys.append(EfakturaConstants.URL_CHAR_DELIMETER_FOR_PARAMS_WITH_HTML_REQUEST + "maxv=" + searchFilter.getMaxv());
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
	private EfakturaChildWindowService efakturaChildWindowService;
	@Autowired
	@Required	
	public void setEfakturaChildWindowService(EfakturaChildWindowService value){this.efakturaChildWindowService = value;}
	public EfakturaChildWindowService getEfakturaChildWindowService(){ return this.efakturaChildWindowService; }
	
}

