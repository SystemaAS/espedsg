package no.systema.skat.controller;

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


import no.systema.skat.model.jsonjackson.customer.JsonSkatCustomerContainer;
import no.systema.skat.model.jsonjackson.customer.JsonSkatCustomerRecord;
import no.systema.skat.service.SkatCustomerService;
import no.systema.skat.url.store.SkatUrlDataStore;
import no.systema.skat.util.SkatConstants;








/**
 * SKAT  Controller - Child Window popup
 * 
 * @author oscardelatorre
 * @date Apr 15, 2016
 * 
 */

@Controller
@SessionAttributes(AppConstants.SYSTEMA_WEB_USER_KEY)
@Scope("session")
public class SkatControllerChildWindow {
	
	private static final Logger logger = Logger.getLogger(SkatControllerChildWindow.class.getName());
	private static final JsonDebugger jsonDebugger = new JsonDebugger(800);
	//customer
	
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
	 * @param session
	 * @param request
	 * @return
	 */
	@RequestMapping(value="skat_childwindow_customer.do", params="action=doFind",  method={RequestMethod.GET, RequestMethod.POST} )
	public ModelAndView searchCustomer(HttpSession session, HttpServletRequest request){
		logger.info("Inside searchCustomer");
		
		ModelAndView successView = new ModelAndView("skat_childwindow_customer");
		Map model = new HashMap();
		String callerType = request.getParameter("ctype");
		logger.info(callerType);
		String customerName = request.getParameter("sonavn");
		String customerNr = request.getParameter("knr");
		
		SystemaWebUser appUser = this.loginValidator.getValidUser(session);
		
		if(appUser==null){
			return this.loginView;
				
		}else{
			Collection<JsonSkatCustomerRecord> list = new ArrayList<JsonSkatCustomerRecord>();
			//prepare the access CGI with RPG back-end
			if( (customerNr!=null && !"".equals(customerNr)) || (customerName!=null && !"".equals(customerName)) ){
				
				String BASE_URL = SkatUrlDataStore.SKAT_FETCH_CUSTOMER_URL;
				String urlRequestParamsKeys = this.getRequestUrlKeyParametersForSearchCustomer(appUser.getUser(), customerName, customerNr);
				logger.info("URL: " + BASE_URL);
				logger.info("PARAMS: " + urlRequestParamsKeys);
				logger.info(Calendar.getInstance().getTime() +  " CGI-start timestamp");
				String jsonPayload = this.urlCgiProxyService.getJsonContent(BASE_URL, urlRequestParamsKeys);
				//Should be removed as soon as RPG return the correct container name = customerlist (not capitalized in the first letter)
				logger.debug(jsonDebugger.debugJsonPayloadWithLog4J(jsonPayload));
				logger.info(Calendar.getInstance().getTime() +  " CGI-end timestamp");
		    	if(jsonPayload!=null){
		    		jsonPayload = jsonPayload.replaceFirst("Customerlist", "customerlist");
		    		JsonSkatCustomerContainer container = this.skatCustomerService.getSkatCustomerContainer(jsonPayload);
		    		if(container!=null){
		    			list = container.getCustomerlist();
		    			for(JsonSkatCustomerRecord  record : container.getCustomerlist()){
		    				//logger.info("CUSTOMER via AJAX: " + record.getKnavn() + " NUMBER:" + record.getKundnr());
		    				//logger.info("KPERS: " + record.getKpers() + " TLF:" + record.getTlf());
		    			}
		    		}
		    	}
			}
			
			model.put("customerList", list);
			model.put("sonavn", customerName);
			model.put("knr", customerNr);
			model.put("ctype", callerType);
			
			successView.addObject(SkatConstants.DOMAIN_MODEL , model);
			
	    	return successView;	
		  	
		}
		
	}
 	
	/**
	 * 
	 * @param applicationUser
	 * @param customerName
	 * @param customerNumber
	 * @return
	 */
	private String getRequestUrlKeyParametersForSearchCustomer(String applicationUser, String customerName, String customerNumber){
		  StringBuffer sb = new StringBuffer();
		  sb.append("user=" + applicationUser);
		  if(customerName!=null && !"".equals(customerName) && customerNumber!=null && !"".equals(customerNumber)){
			  sb.append( SkatConstants.URL_CHAR_DELIMETER_FOR_PARAMS_WITH_HTML_REQUEST + "sonavn=" + customerName );
			  sb.append( SkatConstants.URL_CHAR_DELIMETER_FOR_PARAMS_WITH_HTML_REQUEST + "knr=" + customerNumber );
		  }else if (customerName!=null && !"".equals(customerName)){
			  sb.append( SkatConstants.URL_CHAR_DELIMETER_FOR_PARAMS_WITH_HTML_REQUEST + "sonavn=" + customerName );
		  }else if (customerNumber!=null && !"".equals(customerNumber)){
			  sb.append( SkatConstants.URL_CHAR_DELIMETER_FOR_PARAMS_WITH_HTML_REQUEST + "knr=" + customerNumber );
		  }
		  
		  return sb.toString();
	  }
	
	//SERVICES
	@Qualifier ("urlCgiProxyService")
	private UrlCgiProxyService urlCgiProxyService;
	@Autowired
	@Required
	public void setUrlCgiProxyService (UrlCgiProxyService value){ this.urlCgiProxyService = value; }
	public UrlCgiProxyService getUrlCgiProxyService(){ return this.urlCgiProxyService; }
	
	
	@Qualifier 
	private SkatCustomerService skatCustomerService;
	@Autowired
	@Required	
	public void setSkatCustomerService(SkatCustomerService value){this.skatCustomerService = value;}
	public SkatCustomerService getSkatCustomerService(){ return this.skatCustomerService; }
	
}

