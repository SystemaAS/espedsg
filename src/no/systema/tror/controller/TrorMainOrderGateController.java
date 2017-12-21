package no.systema.tror.controller;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.lang.reflect.Field;
import java.util.*;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;


import org.apache.log4j.Level;
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
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.springframework.web.bind.ServletRequestDataBinder;


//application imports
import no.systema.main.context.TdsAppContext;
import no.systema.main.service.UrlCgiProxyService;
import no.systema.main.service.general.notisblock.NotisblockService;
import no.systema.main.validator.LoginValidator;
import no.systema.main.url.store.MainUrlDataStore;
import no.systema.main.util.AppConstants;
import no.systema.main.util.JsonDebugger;
import no.systema.main.util.MessageNoteManager;
import no.systema.main.util.NumberFormatterLocaleAware;
import no.systema.main.util.StringManager;

import no.systema.main.util.io.FileContentRenderer;
import no.systema.main.model.SystemaWebUser;
import no.systema.main.model.jsonjackson.general.notisblock.JsonNotisblockContainer;


//eBooking
import no.systema.tror.url.store.TrorUrlDataStore;
import no.systema.tror.util.TrorConstants;
import no.systema.tror.util.RpgReturnResponseHandler;
import no.systema.tror.util.manager.CodeDropDownMgr;
import no.systema.tror.model.jsonjackson.JsonTrorOrderHeaderContainer;
import no.systema.tror.model.jsonjackson.codes.JsonTrorCodeContainer;
import no.systema.tror.model.jsonjackson.codes.JsonTrorCodeRecord;

//import no.systema.ebooking.model.jsonjackson.JsonMainOrderHeaderFraktbrevRecord;
//import no.systema.ebooking.model.jsonjackson.JsonMainOrderHeaderMessageNoteContainer;
//import no.systema.ebooking.model.jsonjackson.JsonMainOrderHeaderMessageNoteRecord;

import no.systema.tror.model.jsonjackson.JsonTrorOrderHeaderRecord;
import no.systema.ebooking.model.jsonjackson.JsonMainOrderTypesNewRecord;
//import no.systema.ebooking.model.jsonjackson.order.childwindow.JsonEbookingCustomerContainer;
//import no.systema.ebooking.model.jsonjackson.order.childwindow.JsonEbookingCustomerRecord;
import no.systema.ebooking.service.EbookingChildWindowService;
import no.systema.tror.service.html.dropdown.TrorDropDownListPopulationService;
import no.systema.tror.service.landimport.TrorMainOrderHeaderLandimportService;
import no.systema.tror.mapper.url.request.UrlRequestParameterMapper;
import no.systema.tror.validator.TrorOrderHeaderValidator;


/**
 * Tror - Order Header Controller 
 * 
 * @author oscardelatorre
 * @date Aug 17, 2017
 * 
 */

@Controller
@SessionAttributes(AppConstants.SYSTEMA_WEB_USER_KEY)
@Scope("session")
public class TrorMainOrderGateController {
	private static final JsonDebugger jsonDebugger = new JsonDebugger(1500);
	private static Logger logger = Logger.getLogger(TrorMainOrderGateController.class.getName());
	private ModelAndView loginView = new ModelAndView("login");
	private ApplicationContext context;
	private LoginValidator loginValidator = new LoginValidator();
	//
	private CodeDropDownMgr codeDropDownMgr = new CodeDropDownMgr();
	private UrlRequestParameterMapper urlRequestParameterMapper = new UrlRequestParameterMapper();
	private RpgReturnResponseHandler rpgReturnResponseHandler = new RpgReturnResponseHandler();
	private MessageNoteManager messageNoteMgr = new MessageNoteManager();
	private NumberFormatterLocaleAware numberFormatter = new NumberFormatterLocaleAware();
	private StringManager strMgr = new StringManager();
	//private ReflectionUrlStoreMgr reflectionUrlStoreMgr = new ReflectionUrlStoreMgr();
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
	@RequestMapping(value="tror_mainordergate.do", method={RequestMethod.GET, RequestMethod.POST} )
	public ModelAndView doMainOrderGate(@ModelAttribute ("record") JsonTrorOrderHeaderRecord recordToValidate, BindingResult bindingResult, HttpSession session, HttpServletRequest request){
		this.context = TdsAppContext.getApplicationContext();
		Map model = new HashMap();
		
		String action = request.getParameter("action");
		//special case on Create New comming from the order list "Create new order"
		String selectedTypeWithCreateNew = request.getParameter("selectedType");
		ModelAndView successView = null;
		
		if("A".equals(selectedTypeWithCreateNew)){
			successView = new ModelAndView("redirect:tror_mainorderlandimport.do?action=doInit&heavd=" + recordToValidate.getHeavd());
		}else if("B".equals(selectedTypeWithCreateNew)){
			successView = new ModelAndView("redirect:tror_mainorderlandexport.do?action=doInit");
		}else if("C".equals(selectedTypeWithCreateNew)){
			successView = new ModelAndView("redirect:tror_mainorderairimport.do?action=doInit");
		}else if("D".equals(selectedTypeWithCreateNew)){
			successView = new ModelAndView("redirect:tror_mainorderairexport.do?action=doInit");
		}
		
		SystemaWebUser appUser = this.loginValidator.getValidUser(session);
		
		//check user (should be in session already)
		if(appUser==null){
			return loginView;
			
		}else{
			logger.info(Calendar.getInstance().getTime() + " CONTROLLER start - timestamp");
			
			
			logger.info("Host via HttpServletRequest.getHeader('Host'): " + request.getHeader("Host"));
			return successView;

		}
		
	}
	
	/**
	 * 
	 * @param model
	 * @param rpgReturnResponseHandler
	 */
	private void setAspectsInView (Map model, RpgReturnResponseHandler rpgReturnResponseHandler){
		model.put(TrorConstants.ASPECT_ERROR_MESSAGE, rpgReturnResponseHandler.getErrorMessage());
		//extra error information
		StringBuffer errorMetaInformation = new StringBuffer();
		errorMetaInformation.append(rpgReturnResponseHandler.getUser());
		errorMetaInformation.append(rpgReturnResponseHandler.getHereff());
		model.put(TrorConstants.ASPECT_ERROR_META_INFO, errorMetaInformation);
	}
	
	
	//SERVICES
	@Qualifier ("urlCgiProxyService")
	private UrlCgiProxyService urlCgiProxyService;
	@Autowired
	@Required
	public void setUrlCgiProxyService (UrlCgiProxyService value){ this.urlCgiProxyService = value; }
	public UrlCgiProxyService getUrlCgiProxyService(){ return this.urlCgiProxyService; }
	

}

