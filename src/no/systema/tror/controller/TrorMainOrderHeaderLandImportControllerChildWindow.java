package no.systema.tror.controller;

import java.util.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Required;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;

import no.systema.main.context.TdsAppContext;
import no.systema.main.model.SystemaWebUser;
//import no.systema.main.model.jsonjackson.general.postalcodes.JsonPostalCodesContainer;
//import no.systema.main.model.jsonjackson.general.postalcodes.JsonPostalCodesRecord;
import no.systema.main.service.UrlCgiProxyService;
import no.systema.main.util.AppConstants;
import no.systema.main.util.DateTimeManager;
import no.systema.main.util.JsonDebugger;
import no.systema.main.util.StringManager;
import no.systema.main.validator.LoginValidator;

//tror
import no.systema.tror.url.store.TrorUrlDataStore;
import no.systema.tror.util.TrorConstants;
import no.systema.z.main.maintenance.model.jsonjackson.dbtable.JsonMaintMainCundfContainer;
import no.systema.z.main.maintenance.model.jsonjackson.dbtable.JsonMaintMainCundfRecord;
import no.systema.z.main.maintenance.service.MaintMainCundfService;
import no.systema.z.main.maintenance.url.store.MaintenanceMainUrlDataStore;
import no.systema.z.main.maintenance.util.MainMaintenanceConstants;
import no.systema.tror.model.jsonjackson.order.landimport.childwindow.JsonTrorSellerDeliveryAddressContainer;
import no.systema.tror.model.jsonjackson.order.landimport.childwindow.JsonTrorSellerDeliveryAddressRecord;
import no.systema.tror.model.jsonjackson.order.landimport.childwindow.JsonTrorBuyerDeliveryAddressContainer;
import no.systema.tror.model.jsonjackson.order.landimport.childwindow.JsonTrorBuyerDeliveryAddressRecord;
import no.systema.tror.model.jsonjackson.order.childwindow.JsonTrorLosseLasteStedContainer;
import no.systema.tror.model.jsonjackson.order.childwindow.JsonTrorLosseLasteStedRecord;
import no.systema.tror.model.jsonjackson.order.childwindow.JsonTrorPostalCodeContainer;
import no.systema.tror.model.jsonjackson.order.childwindow.JsonTrorPostalCodeRecord;
import no.systema.tror.service.landimport.TrorMainOrderHeaderLandimportChildwindowService;
import no.systema.tror.service.TrorMainOrderHeaderChildwindowService;
import no.systema.jservices.common.dao.DokufDao;
//
import no.systema.jservices.common.dao.PonrnDao;
import no.systema.jservices.common.json.JsonDtoContainer;
import no.systema.jservices.common.json.JsonReader;

/**
 * 
 * @author oscardelatorre
 * @date Aug 24, 2017
 */
@Controller
@SessionAttributes(AppConstants.SYSTEMA_WEB_USER_KEY)
@Scope("session")

public class TrorMainOrderHeaderLandImportControllerChildWindow {
	//Postal codes
	private final String DATATABLE_POSTALCODE_PONRN_LIST = "postalCodePonrnList";
	private final String POSTALCODE_DIRECTION = "direction";
	private final String DATATABLE_CUSTOMER_LIST = "customerList";
	private final String DATATABLE_SELLER_ADDRESSES_LIST = "sellerAddressesList";
	private final String DATATABLE_BUYER_ADDRESSES_LIST = "buyerAddressesList";
	
	private final String DATATABLE_LOAD_UNLOAD_PLACES_LIST = "loadUnloadPlacesList";
	private final String DATATABLE_PACKING_CODES_LIST = "packingCodesList";
	private final String DATATABLE_DANGEROUS_GOODS_LIST = "dangerousGoodsList";
	
	private static final Logger logger = Logger.getLogger(TrorMainOrderHeaderLandImportControllerChildWindow.class.getName());
	private static final JsonDebugger jsonDebugger = new JsonDebugger(1000);
	private DateTimeManager dateTimeManager = new DateTimeManager();
	private StringManager strMgr = new StringManager();
	
	private ModelAndView loginView = new ModelAndView("login");
	private ApplicationContext context;
	private LoginValidator loginValidator = new LoginValidator();
	
	 
	/**
	 * Postal Codes doInit
	 * 
	 * @param recordToValidate
	 * @param session
	 * @param request
	 * @return
	 */
	
	/**
	 * 
	 * @param recordToValidate
	 * @param session
	 * @param request
	 * @return
	 */
	@RequestMapping(value="tror_mainorderlandimport_childwindow_seller_addresses.do", params="action=doInit",  method={RequestMethod.GET} )
	public ModelAndView doInitSellerAddresses(@ModelAttribute ("record") JsonTrorSellerDeliveryAddressContainer recordToValidate, HttpSession session, HttpServletRequest request){
		this.context = TdsAppContext.getApplicationContext();
		logger.info("Inside: doInitCustomerAddresses");
		Map model = new HashMap();
		ModelAndView successView = new ModelAndView("tror_mainorderlandimport_childwindow_seller_addresses");
		SystemaWebUser appUser = this.loginValidator.getValidUser(session);
		//check user (should be in session already)
		if(appUser==null){
			return this.loginView;
			
		}else{
			logger.info(Calendar.getInstance().getTime() + " CONTROLLER start - timestamp");
			model.put(TrorConstants.DOMAIN_CONTAINER, recordToValidate);
			successView.addObject(TrorConstants.DOMAIN_MODEL , model);
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
	@RequestMapping(value="tror_mainorderlandimport_childwindow_seller_addresses.do", params="action=doFind",  method={RequestMethod.GET, RequestMethod.POST} )
	public ModelAndView doFindSellerAddresses(@ModelAttribute ("record") JsonTrorSellerDeliveryAddressContainer recordToValidate, BindingResult bindingResult, HttpSession session, HttpServletRequest request){
		this.context = TdsAppContext.getApplicationContext();
		logger.info("Inside: doFindSellerAddresses");
		Collection outputList = new ArrayList();
		Map model = new HashMap();
		ModelAndView successView = new ModelAndView("tror_mainorderlandimport_childwindow_seller_addresses");
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
			/*FraktkalkulatorChildWindowSearchCustomerValidator validator = new FraktkalkulatorChildWindowSearchCustomerValidator();
			logger.info("Host via HttpServletRequest.getHeader('Host'): " + request.getHeader("Host"));
		    validator.validate(recordToValidate, bindingResult);
		    */
		    //check for ERRORS
			if(bindingResult.hasErrors()){
	    		logger.info("[ERROR Validation] search-filter does not validate)");
	    		//put domain objects and do go back to the successView from here
	    		//this.setCodeDropDownMgr(appUser, model);
	    		model.put(TrorConstants.DOMAIN_CONTAINER, recordToValidate);
				successView.addObject(TrorConstants.DOMAIN_MODEL, model);
				return successView;
	    		
		    }else{
				
	    		//prepare the access CGI with RPG back-end
	    		String BASE_URL = TrorUrlDataStore.TROR_BASE_CHILDWINDOW_DELIVERY_ADDRESS_LANDIMPORT_SELLER_URL;
	    		String urlRequestParamsKeys = "user=" + appUser.getUser() + "&kukun1=" + recordToValidate.getKukun1(); 
	    		logger.info("URL: " + BASE_URL);
	    		logger.info("PARAMS: " + urlRequestParamsKeys);
	    		logger.info(Calendar.getInstance().getTime() +  " CGI-start timestamp");
	    		String jsonPayload = this.urlCgiProxyService.getJsonContent(BASE_URL, urlRequestParamsKeys);
	    		//Debug -->
		    	logger.debug(jsonDebugger.debugJsonPayloadWithLog4J(jsonPayload));
	    		logger.info(Calendar.getInstance().getTime() +  " CGI-end timestamp");
		    
	    		if(jsonPayload!=null){
	    			JsonTrorSellerDeliveryAddressContainer container = this.trorMainOrderHeaderLandimportChildwindowService.getSellerDeliveryAddressContainer(jsonPayload);
		    		if(container!=null){
		    			List<JsonTrorSellerDeliveryAddressRecord> list = new ArrayList<JsonTrorSellerDeliveryAddressRecord>();
		    			for(JsonTrorSellerDeliveryAddressRecord  record : container.getDtoList()){
		    				//Must now complete the address in 2 steps:
		    				//Step 1: check VADR. If MATCH = catch the address. If NO MATCH. go to Step 2
		    				//Step 2: check CUNDF. 
		    				boolean match1 = this.getFromVadr(record, appUser);
		    				if (!match1){
		    					this.getFromCundf(record, appUser);
		    				}
		    				//
		    				list.add(record);
		    			}
		    			model.put(this.DATATABLE_SELLER_ADDRESSES_LIST, list);
		    			model.put(TrorConstants.DOMAIN_CONTAINER, recordToValidate);
		    		}
		    		successView.addObject(TrorConstants.DOMAIN_MODEL , model);
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
	 * @param recordToValidate
	 * @param bindingResult
	 * @param session
	 * @param request
	 * @return
	 */
	@RequestMapping(value="tror_mainorder_childwindow_postalcodes_ponrn.do", params="action=doFind",  method={RequestMethod.GET, RequestMethod.POST} )
	public ModelAndView doFindPostalCodesPonrn(@ModelAttribute ("record") PonrnDao recordToValidate, BindingResult bindingResult, HttpSession session, HttpServletRequest request){
		this.context = TdsAppContext.getApplicationContext();
		logger.info("Inside: doFindPostalCodes PONRN");
		Collection outputList = new ArrayList();
		Map model = new HashMap();
		ModelAndView successView = new ModelAndView("tror_mainorder_childwindow_postalcodes_ponrn");
		SystemaWebUser appUser = this.loginValidator.getValidUser(session);
		//to catch the sender since there could be more then one caller field
		String ctype = request.getParameter("ctype");
		model.put("ctype", ctype);
		
		if(appUser==null){
			return loginView;
			
		}else{
			//appUser.setActiveMenu(SystemaWebUser.ACTIVE_MENU_FRAKTKALKULATOR);
			logger.info(Calendar.getInstance().getTime() + " CONTROLLER start - timestamp");
			
			//-----------
			//Validation
			//-----------
			/*FraktkalkulatorChildWindowSearchCustomerValidator validator = new FraktkalkulatorChildWindowSearchCustomerValidator();
			logger.info("Host via HttpServletRequest.getHeader('Host'): " + request.getHeader("Host"));
		    validator.validate(recordToValidate, bindingResult);
		    */
		    //check for ERRORS
			if(bindingResult.hasErrors()){
	    		logger.info("[ERROR Validation] search-filter does not validate)");
	    		//put domain objects and do go back to the successView from here
	    		//this.setCodeDropDownMgr(appUser, model);
	    		model.put(TrorConstants.DOMAIN_RECORD, recordToValidate);
				successView.addObject(TrorConstants.DOMAIN_MODEL, model);
				return successView;
	    		
		    }else{
				
		    	JsonReader<JsonDtoContainer<PonrnDao>> jsonReader = new JsonReader<JsonDtoContainer<PonrnDao>>();
				jsonReader.set(new JsonDtoContainer<PonrnDao>());
				String BASE_URL = TrorUrlDataStore.TROR_BASE_CHILDWINDOW_POSTALCODE_PONRN_URL;
				StringBuilder urlRequestParams = new StringBuilder();
				urlRequestParams.append("user=" + appUser.getUser());
				if(strMgr.isNotNull(recordToValidate.getPonnr()) ){
					urlRequestParams.append("&ponnr=" + recordToValidate.getPonnr());
				}
				
				logger.info("URL: " + BASE_URL);
				logger.info("PARAMS: " + urlRequestParams.toString());
				String jsonPayload = urlCgiProxyService.getJsonContent(BASE_URL, urlRequestParams.toString());
				logger.info(jsonDebugger.debugJsonPayloadWithLog4J(jsonPayload));
				DokufDao record = null;
				JsonDtoContainer<PonrnDao> container = (JsonDtoContainer<PonrnDao>) jsonReader.get(jsonPayload);
				if (container != null) {
					for (PonrnDao dao : container.getDtoList()) {
						outputList = container.getDtoList();
					}
					model.put(this.DATATABLE_POSTALCODE_PONRN_LIST, outputList);
	    			model.put(TrorConstants.DOMAIN_RECORD, recordToValidate);
				}
				successView.addObject(TrorConstants.DOMAIN_MODEL , model);
    			logger.info(Calendar.getInstance().getTime() + " CONTROLLER end - timestamp");
    			return successView;
		    }
		}
	}
	
	
	/**
	 * 
	 * @param parentRecord
	 * @param appUser
	 * @return
	 */
	private boolean getFromVadr(JsonTrorSellerDeliveryAddressRecord parentRecord, SystemaWebUser appUser ){
		boolean retval = false;
		//prepare the access CGI with RPG back-end
		String BASE_URL = TrorUrlDataStore.TROR_BASE_CHILDWINDOW_DELIVERY_ADDRESS_LANDIMPORT_BUYER_URL;
		StringBuffer urlRequestParamsKeys = new StringBuffer();
		urlRequestParamsKeys.append("user=" + appUser.getUser() + "&kundnr=" + parentRecord.getKukun2());
		urlRequestParamsKeys.append("&vadrnr=" + parentRecord.getKuvadr());
		//default
		String firma = appUser.getFallbackCompanyCode();
		if(strMgr.isNotNull(appUser.getCompanyCode())){ firma = appUser.getFallbackCompanyCode(); }
		//firma
		urlRequestParamsKeys.append("&firma=" + firma);
		
		//logger.info("URL: " + BASE_URL);
		//logger.info("PARAMS: " + urlRequestParamsKeys);
		//logger.info(Calendar.getInstance().getTime() +  " CGI-start timestamp");
		String jsonPayload = this.urlCgiProxyService.getJsonContent(BASE_URL, urlRequestParamsKeys.toString());
		//Debug -->
    	//logger.debug(jsonDebugger.debugJsonPayloadWithLog4J(jsonPayload));
		//logger.info(Calendar.getInstance().getTime() +  " CGI-end timestamp");
    
		if(jsonPayload!=null){
			JsonTrorBuyerDeliveryAddressContainer container = this.trorMainOrderHeaderLandimportChildwindowService.getBuyerDeliveryAddressContainer(jsonPayload);
    		if(container!=null){
    			List<JsonTrorBuyerDeliveryAddressRecord> list = new ArrayList<JsonTrorBuyerDeliveryAddressRecord>();
    			for(JsonTrorBuyerDeliveryAddressRecord  record : container.getDtoList()){
    				//for presentation purposes
    				parentRecord.setKuvadr(record.getVadrna() + " " + record.getVadrn2());
    				//for domain realted purposes
    				parentRecord.setNavn(record.getVadrna());
    				parentRecord.setAdr1(record.getVadrn1());
    				parentRecord.setAdr2(record.getVadrn2());
    				parentRecord.setAdr3(record.getVadrn3());
    				
    				retval = true;
    			}
    		}
		}
		return retval;
		
	}
	
	/**
	 * 
	 * @param parentRecord
	 * @param appUser
	 * @return
	 */
	private boolean getFromCundf(JsonTrorSellerDeliveryAddressRecord parentRecord, SystemaWebUser appUser ){
		boolean retval = false;
		String BASE_URL = MaintenanceMainUrlDataStore.MAINTENANCE_MAIN_BASE_SYCUNDFR_GET_LIST_URL;
		StringBuffer urlRequestParamsKeys = new StringBuffer();
		
		urlRequestParamsKeys.append("user=" + appUser.getUser() + "&kundnr=" + parentRecord.getKukun2() );
		urlRequestParamsKeys.append( "&firma=" + appUser.getFallbackCompanyCode() );
		
		//logger.info("URL: " + BASE_URL);
		//logger.info("PARAMS: " + urlRequestParamsKeys);
		//logger.info(Calendar.getInstance().getTime() +  " CGI-start timestamp");
		String jsonPayload = this.urlCgiProxyService.getJsonContent(BASE_URL, urlRequestParamsKeys.toString());
		//debugger
		//logger.debug(jsonDebugger.debugJsonPayloadWithLog4J(jsonPayload));
		//logger.info(Calendar.getInstance().getTime() +  " CGI-end timestamp");
		if(jsonPayload!=null){
			jsonPayload = jsonPayload.replaceFirst("Customerlist", "customerlist");
			JsonMaintMainCundfContainer container = this.maintMainCundfService.getList(jsonPayload);
			if(container!=null){
				for(JsonMaintMainCundfRecord  record : container.getList()){
					//for presentation purposes
					parentRecord.setKuvadr(record.getKnavn() + " " + record.getPostnr() + " " + record.getAdr3());
					//for domain realted purposes
    				parentRecord.setNavn(record.getKnavn());
    				parentRecord.setAdr1(record.getAdr1());
    				parentRecord.setAdr2(record.getAdr2());
    				parentRecord.setAdr3(record.getPostnr() + " " + record.getAdr3());
					
					retval = true;
					
				}
			}
		}	
		return retval;
		
	}
	
	
	
	
	
	
	/**
	 * 
	 * @param recordToValidate
	 * @param session
	 * @param request
	 * @return
	 */
	@RequestMapping(value="tror_mainorderlandimport_childwindow_seller_addresses_vedlikehold.do", params="action=doInit",  method={RequestMethod.GET} )
	public ModelAndView doInitSellerAddressesVedlikehold(@ModelAttribute ("record") JsonTrorSellerDeliveryAddressContainer recordToValidate, HttpSession session, HttpServletRequest request){
		this.context = TdsAppContext.getApplicationContext();
		logger.info("Inside: doInitSellerAddressesVedlikehold");
		Map model = new HashMap();
		ModelAndView successView = new ModelAndView("tror_mainorderlandimport_childwindow_seller_addresses_vedlikehold");
		SystemaWebUser appUser = this.loginValidator.getValidUser(session);
		//check user (should be in session already)
		if(appUser==null){
			return this.loginView;
			
		}else{
			logger.info(Calendar.getInstance().getTime() + " CONTROLLER start - timestamp");
			model.put(TrorConstants.DOMAIN_CONTAINER, recordToValidate);
			successView.addObject(TrorConstants.DOMAIN_MODEL , model);
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
	@RequestMapping(value="tror_mainorderlandimport_childwindow_seller_addresses_vedlikehold.do", params="action=doUpdate",  method={RequestMethod.GET, RequestMethod.POST} )
	public ModelAndView doUpdateSellerAddressesVedlikehold(@ModelAttribute ("record") JsonTrorSellerDeliveryAddressContainer recordToValidate, HttpSession session, HttpServletRequest request){
		this.context = TdsAppContext.getApplicationContext();
		logger.info("Inside: doUpdateSellerAddressesVedlikehold");
		Map model = new HashMap();
		SystemaWebUser appUser = this.loginValidator.getValidUser(session);
		//check user (should be in session already)
		
		//check if it is a delete action
		String removeFlag = request.getParameter("rm");
		ModelAndView successView = new ModelAndView("tror_mainorderlandimport_childwindow_seller_addresses_vedlikehold");
		if(strMgr.isNotNull(removeFlag)){
			successView = new ModelAndView("redirect:tror_mainorderlandimport_childwindow_seller_addresses.do?action=doFind&ctype=s&wkundnr=" + appUser.getCustNr());
		}
		
		if(appUser==null){
			return this.loginView;
			
		}else{
			//TODO massor med Create or Update or Delete
			//väntar på JOVOs CRUD
			//...
			
			logger.info(Calendar.getInstance().getTime() + " CONTROLLER start - timestamp");
			model.put(TrorConstants.DOMAIN_CONTAINER, recordToValidate);
			successView.addObject(TrorConstants.DOMAIN_MODEL , model);
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
	@RequestMapping(value="tror_mainorderlandimport_childwindow_buyer_addresses.do", params="action=doInit",  method={RequestMethod.GET} )
	public ModelAndView doInitBuyerAddresses(@ModelAttribute ("record") JsonTrorBuyerDeliveryAddressContainer recordToValidate, HttpSession session, HttpServletRequest request){
		this.context = TdsAppContext.getApplicationContext();
		logger.info("Inside: doInitBuyerAddresses");
		Map model = new HashMap();
		ModelAndView successView = new ModelAndView("tror_mainorderlandimport_childwindow_buyer_addresses");
		SystemaWebUser appUser = this.loginValidator.getValidUser(session);
		//check user (should be in session already)
		if(appUser==null){
			return this.loginView;
			
		}else{
			logger.info(Calendar.getInstance().getTime() + " CONTROLLER start - timestamp");
			model.put(TrorConstants.DOMAIN_CONTAINER, recordToValidate);
			successView.addObject(TrorConstants.DOMAIN_MODEL , model);
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
	@RequestMapping(value="tror_mainorderlandimport_childwindow_buyer_addresses.do", params="action=doFind",  method={RequestMethod.GET, RequestMethod.POST} )
	public ModelAndView doFindBuyerAddresses(@ModelAttribute ("record") JsonTrorBuyerDeliveryAddressContainer recordToValidate, BindingResult bindingResult, HttpSession session, HttpServletRequest request){
		this.context = TdsAppContext.getApplicationContext();
		logger.info("Inside: doFindCustomerAddresses");
		Collection outputList = new ArrayList();
		Map model = new HashMap();
		ModelAndView successView = new ModelAndView("tror_mainorderlandimport_childwindow_buyer_addresses");
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
			/*FraktkalkulatorChildWindowSearchCustomerValidator validator = new FraktkalkulatorChildWindowSearchCustomerValidator();
			logger.info("Host via HttpServletRequest.getHeader('Host'): " + request.getHeader("Host"));
		    validator.validate(recordToValidate, bindingResult);
		    */
		    //check for ERRORS
			if(bindingResult.hasErrors()){
	    		logger.info("[ERROR Validation] search-filter does not validate)");
	    		//put domain objects and do go back to the successView from here
	    		//this.setCodeDropDownMgr(appUser, model);
	    		model.put(TrorConstants.DOMAIN_CONTAINER, recordToValidate);
				successView.addObject(TrorConstants.DOMAIN_MODEL, model);
				return successView;
	    		
		    }else{
				
	    		//prepare the access CGI with RPG back-end
	    		String BASE_URL = TrorUrlDataStore.TROR_BASE_CHILDWINDOW_DELIVERY_ADDRESS_LANDIMPORT_BUYER_URL;
	    		String urlRequestParamsKeys = "user=" + appUser.getUser() + "&kundnr=" + recordToValidate.getKundnr() + "&firma=" + appUser.getFallbackCompanyCode(); 
	    		logger.info("URL: " + BASE_URL);
	    		logger.info("PARAMS: " + urlRequestParamsKeys);
	    		logger.info(Calendar.getInstance().getTime() +  " CGI-start timestamp");
	    		String jsonPayload = this.urlCgiProxyService.getJsonContent(BASE_URL, urlRequestParamsKeys);
	    		//Debug -->
		    	logger.debug(jsonDebugger.debugJsonPayloadWithLog4J(jsonPayload));
	    		logger.info(Calendar.getInstance().getTime() +  " CGI-end timestamp");
		    
	    		if(jsonPayload!=null){
	    			JsonTrorBuyerDeliveryAddressContainer container = this.trorMainOrderHeaderLandimportChildwindowService.getBuyerDeliveryAddressContainer(jsonPayload);
		    		if(container!=null){
		    			List<JsonTrorBuyerDeliveryAddressRecord> list = new ArrayList<JsonTrorBuyerDeliveryAddressRecord>();
		    			for(JsonTrorBuyerDeliveryAddressRecord  record : container.getDtoList()){
		    				//only qualifying records 
		    				if( record.getVadrnr()>0 ) {
		    					list.add(record);
		    				}
		    			}
		    			model.put(this.DATATABLE_BUYER_ADDRESSES_LIST, list);
		    			model.put(TrorConstants.DOMAIN_CONTAINER, recordToValidate);
		    		}
		    		successView.addObject(TrorConstants.DOMAIN_MODEL , model);
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
	 * @param recordToValidate
	 * @param session
	 * @param request
	 * @return
	 */
	@RequestMapping(value="tror_mainorderlandimport_childwindow_buyer_addresses_vedlikehold.do", params="action=doInit",  method={RequestMethod.GET} )
	public ModelAndView doInitBuyerAddressesVedlikehold(@ModelAttribute ("record") JsonTrorBuyerDeliveryAddressContainer recordToValidate, HttpSession session, HttpServletRequest request){
		this.context = TdsAppContext.getApplicationContext();
		logger.info("Inside: doInitBuyerAddressesVedlikehold");
		Map model = new HashMap();
		ModelAndView successView = new ModelAndView("tror_mainorderlandimport_childwindow_buyer_addresses_vedlikehold");
		SystemaWebUser appUser = this.loginValidator.getValidUser(session);
		//check user (should be in session already)
		if(appUser==null){
			return this.loginView;
			
		}else{
			logger.info(Calendar.getInstance().getTime() + " CONTROLLER start - timestamp");
			model.put(TrorConstants.DOMAIN_CONTAINER, recordToValidate);
			successView.addObject(TrorConstants.DOMAIN_MODEL , model);
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
	@RequestMapping(value="tror_mainorderlandimport_childwindow_buyer_addresses_vedlikehold.do", params="action=doUpdate",  method={RequestMethod.GET, RequestMethod.POST} )
	public ModelAndView doUpdateBuyerAddressesVedlikehold(@ModelAttribute ("record") JsonTrorBuyerDeliveryAddressContainer recordToValidate, HttpSession session, HttpServletRequest request){
		this.context = TdsAppContext.getApplicationContext();
		logger.info("Inside: doUpdateBuyerAddressesVedlikehold");
		Map model = new HashMap();
		SystemaWebUser appUser = this.loginValidator.getValidUser(session);
		//check user (should be in session already)
		
		//check if it is a delete action
		String removeFlag = request.getParameter("rm");
		ModelAndView successView = new ModelAndView("tror_mainorderlandimport_childwindow_buyer_addresses_vedlikehold");
		if(strMgr.isNotNull(removeFlag)){
			successView = new ModelAndView("redirect:tror_mainorderlandimport_childwindow_buyer_addresses.do?action=doFind&ctype=s&wkundnr=" + appUser.getCustNr());
		}
		
		if(appUser==null){
			return this.loginView;
			
		}else{
			//TODO massor med Create or Update or Delete
			//väntar på JOVOs CRUD
			//...
			
			logger.info(Calendar.getInstance().getTime() + " CONTROLLER start - timestamp");
			model.put(TrorConstants.DOMAIN_CONTAINER, recordToValidate);
			successView.addObject(TrorConstants.DOMAIN_MODEL , model);
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
	/*
	@RequestMapping(value="tror_childwindow_loadunloadplaces.do", params="action=doInit",  method={RequestMethod.GET} )
	public ModelAndView doInitLoadUloadPlaces(@ModelAttribute ("record") JsonTrorLosseLasteStedContainer recordToValidate, HttpSession session, HttpServletRequest request){
		this.context = TdsAppContext.getApplicationContext();
		logger.info("Inside: doInitLoadUloadPlaces");
		Map model = new HashMap();
		ModelAndView successView = new ModelAndView("tror_childwindow_loadunloadplaces");
		SystemaWebUser appUser = this.loginValidator.getValidUser(session);
		
		//check user (should be in session already)
		if(appUser==null){
			return this.loginView;
			
		}else{
			logger.info(Calendar.getInstance().getTime() + " CONTROLLER start - timestamp");
			model.put(TrorConstants.DOMAIN_CONTAINER, recordToValidate);
			successView.addObject(TrorConstants.DOMAIN_MODEL , model);
	    		return successView;
		}
	}	
	*/
	/**
	 * 
	 * @param recordToValidate
	 * @param bindingResult
	 * @param session
	 * @param request
	 * @return
	 */
	/*
	@RequestMapping(value="tror_childwindow_loadunloadplaces.do", params="action=doFind",  method={RequestMethod.GET, RequestMethod.POST} )
	public ModelAndView doFindLoadUnloadPlaces(@ModelAttribute ("record") JsonTrorLosseLasteStedContainer recordToValidate, BindingResult bindingResult, HttpSession session, HttpServletRequest request){
		this.context = TdsAppContext.getApplicationContext();
		logger.info("Inside: doFindLoadUploadPlaces");
		Collection outputList = new ArrayList();
		Map model = new HashMap();
		ModelAndView successView = new ModelAndView("tror_childwindow_loadunloadplaces");
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
			/*FraktkalkulatorChildWindowSearchCustomerValidator validator = new FraktkalkulatorChildWindowSearchCustomerValidator();
			logger.info("Host via HttpServletRequest.getHeader('Host'): " + request.getHeader("Host"));
		    validator.validate(recordToValidate, bindingResult);
		    
		    //check for ERRORS
			if(bindingResult.hasErrors()){
	    		logger.info("[ERROR Validation] search-filter does not validate)");
	    		//put domain objects and do go back to the successView from here
	    		//this.setCodeDropDownMgr(appUser, model);
	    		model.put(TrorConstants.DOMAIN_CONTAINER, recordToValidate);
				successView.addObject(TrorConstants.DOMAIN_MODEL, model);
				return successView;
	    		
		    }else{
				
		    		//prepare the access CGI with RPG back-end
		    		String BASE_URL = TrorUrlDataStore.TROR_BASE_CHILDWINDOW_LOAD_UNLOAD_PLACES_URL;
		    		String urlRequestParamsKeys = "user=" + appUser.getUser();
		    		logger.info("URL: " + BASE_URL);
		    		logger.info("PARAMS: " + urlRequestParamsKeys);
		    		logger.info(Calendar.getInstance().getTime() +  " CGI-start timestamp");
		    		String jsonPayload = this.urlCgiProxyService.getJsonContent(BASE_URL, urlRequestParamsKeys);
		    		//Debug -->
			    	logger.debug(jsonDebugger.debugJsonPayloadWithLog4J(jsonPayload));
		    		logger.info(Calendar.getInstance().getTime() +  " CGI-end timestamp");
			    
		    		if(jsonPayload!=null){
		    			JsonTrorLosseLasteStedContainer container = this.trorMainOrderHeaderChildwindowService.getLosseLasteStedContainer(jsonPayload);
			    		if(container!=null){
			    			List<JsonTrorLosseLasteStedRecord> list = new ArrayList<JsonTrorLosseLasteStedRecord>();
			    			for(JsonTrorLosseLasteStedRecord  record : container.getDtoList()){
			    				//logger.info("Load PLACE: " + record.getKotmnv());
			    				list.add(record);
			    			}
			    			model.put(this.DATATABLE_LOAD_UNLOAD_PLACES_LIST, list);
			    			model.put(TrorConstants.DOMAIN_CONTAINER, recordToValidate);
			    		}
		    			successView.addObject(TrorConstants.DOMAIN_MODEL , model);
					logger.info(Calendar.getInstance().getTime() + " CONTROLLER end - timestamp");
					return successView;
					
			    	}else{
					logger.fatal("NO CONTENT on jsonPayload from URL... ??? <Null>");
					return loginView;
				}
				
		    }
		}
	}
	*/
	/**
	 * 
	 * @param recordToValidate
	 * @param session
	 * @param request
	 * @return
	 */
	/*
	@RequestMapping(value="ebooking_childwindow_packingcodes.do", params="action=doInit",  method={RequestMethod.GET} )
	public ModelAndView doInitPackingCodes(@ModelAttribute ("record") JsonEbookingPackingCodesContainer recordToValidate, HttpSession session, HttpServletRequest request){
		this.context = TdsAppContext.getApplicationContext();
		logger.info("Inside: doInitPackingCodes");
		Map model = new HashMap();
		
		ModelAndView successView = new ModelAndView("ebooking_childwindow_packingcodes");
		SystemaWebUser appUser = this.loginValidator.getValidUser(session);
		//check user (should be in session already)
		if(appUser==null){
			return this.loginView;
			
		}else{
			logger.info(Calendar.getInstance().getTime() + " CONTROLLER start - timestamp");
			model.put(EbookingConstants.DOMAIN_RECORD, recordToValidate);
			successView.addObject(EbookingConstants.DOMAIN_MODEL , model);
	    		return successView;
		}
	}	
	*/
	/**
	 * 
	 * @param recordToValidate
	 * @param bindingResult
	 * @param session
	 * @param request
	 * @return
	 */
	/*
	@RequestMapping(value="ebooking_childwindow_packingcodes.do", params="action=doFind",  method={RequestMethod.GET, RequestMethod.POST} )
	public ModelAndView doFindPackingCodes(@ModelAttribute ("record") JsonEbookingPackingCodesContainer recordToValidate, BindingResult bindingResult, HttpSession session, HttpServletRequest request){
		this.context = TdsAppContext.getApplicationContext();
		logger.info("Inside: doFindPackingCodes");
		Collection<JsonEbookingPackingCodesRecord> outputList = new ArrayList();
		Map model = new HashMap();
		
		ModelAndView successView = new ModelAndView("ebooking_childwindow_packingcodes");
		SystemaWebUser appUser = this.loginValidator.getValidUser(session);
		//check user (should be in session already)
		if(appUser==null){
			return loginView;
			
		}else{
			logger.info(Calendar.getInstance().getTime() + " CONTROLLER start - timestamp");
		    //check for ERRORS
			if(bindingResult.hasErrors()){
	    		logger.info("[ERROR Validation] search-filter does not validate)");
	    		//put domain objects and do go back to the successView from here
	    		//this.setCodeDropDownMgr(appUser, model);
	    		model.put(EbookingConstants.DOMAIN_CONTAINER, recordToValidate);
				successView.addObject(EbookingConstants.DOMAIN_MODEL, model);
				return successView;
	    		
		    }else{
		    	String BASE_URL = EbookingUrlDataStore.EBOOKING_BASE_CHILDWINDOW_PACKING_CODES_URL;
		    	String urlRequestParamsKeys = this.getRequestUrlKeyParametersSearchChildWindow(recordToValidate, appUser);
				
		    	logger.info("URL: " + BASE_URL);
				logger.info("PARAMS: " + urlRequestParamsKeys);
				logger.info(Calendar.getInstance().getTime() +  " CGI-start timestamp");
				String jsonPayload = this.urlCgiProxyService.getJsonContent(BASE_URL, urlRequestParamsKeys);
				//Debug -->
		    	logger.debug(jsonDebugger.debugJsonPayloadWithLog4J(jsonPayload));
				logger.info(Calendar.getInstance().getTime() +  " CGI-end timestamp");
				
				if(jsonPayload!=null){
					JsonEbookingPackingCodesContainer container = this.ebookingChildWindowService.getPackingCodesContainer(jsonPayload);
		    			if(container!=null){
		    				outputList = container.getForpaknKoder();
		    			}
				}
		    	
    			model.put(this.DATATABLE_PACKING_CODES_LIST, outputList);
    			model.put(EbookingConstants.DOMAIN_CONTAINER, recordToValidate);
    			successView.addObject(EbookingConstants.DOMAIN_MODEL , model);
    			logger.info(Calendar.getInstance().getTime() + " CONTROLLER end - timestamp");
    			return successView;
				
		    }
			
		}
	}
	*/
	
	
	
	/**
	 * 	
	 * @param applicationUser
	 * @param recordToValidate
	 * @param exactMatch
	 * @return
	 */
	/*
	public Collection<JsonPostalCodesRecord> fetchPostalCodes (String applicationUser,JsonPostalCodesRecord recordToValidate, boolean exactMatch){
		Collection<JsonPostalCodesRecord> outputList = new ArrayList<JsonPostalCodesRecord>();
		//prepare the access CGI with RPG back-end
		String BASE_URL = EbookingUrlDataStore.EBOOKING_BASE_CHILDWINDOW_POSTAL_CODES_URL;
		String urlRequestParamsKeys = this.getRequestUrlKeyParametersSearchPostalCodes(applicationUser, recordToValidate, exactMatch);
		logger.info("URL: " + BASE_URL);
		logger.info("PARAMS: " + urlRequestParamsKeys);
		logger.info(Calendar.getInstance().getTime() +  " CGI-start timestamp");
		String jsonPayload = this.urlCgiProxyService.getJsonContent(BASE_URL, urlRequestParamsKeys);
		//Debug -->
		logger.debug(jsonDebugger.debugJsonPayloadWithLog4J(jsonPayload));
		logger.info(Calendar.getInstance().getTime() +  " CGI-end timestamp");
    
		if(jsonPayload!=null){
			JsonPostalCodesContainer container = this.ebookingChildWindowService.getPostalCodesContainer(jsonPayload);
    			if(container!=null){
    				outputList = container.getPostnrlist();
    				for(JsonPostalCodesRecord  record : outputList){
    				//DEBUG
    				}
    			}
		}	
		return outputList;
	}
	*/
	/**
	 * 
	 * @param applicationUser
	 * @param searchFilterRecord
	 * @param exactMatch
	 * @return
	 */
	/*
	private String getRequestUrlKeyParametersSearchPostalCodes(String applicationUser, JsonPostalCodesRecord searchFilterRecord, boolean exactMatch){
		final String POSTALCODE_DIRECTION_FRA = "fra";
		final String POSTALCODE_DIRECTION_TIL = "til";
		
		StringBuffer urlRequestParamsKeys = new StringBuffer();
		urlRequestParamsKeys.append("user=" + applicationUser);
		if(POSTALCODE_DIRECTION_FRA.equals(searchFilterRecord.getDirection())){
			urlRequestParamsKeys.append("&varlk=fralk");
			urlRequestParamsKeys.append("&varkod=fra");
		}else if(POSTALCODE_DIRECTION_TIL.equals(searchFilterRecord.getDirection())){
			urlRequestParamsKeys.append("&varlk=tillk");
			urlRequestParamsKeys.append("&varkod=til");
		}
		
		if(searchFilterRecord.getSt2lk()!=null && !"".equals(searchFilterRecord.getSt2lk())){
			urlRequestParamsKeys.append(EbookingConstants.URL_CHAR_DELIMETER_FOR_PARAMS_WITH_HTML_REQUEST + "soklk=" + searchFilterRecord.getSt2lk());
		}
		if(searchFilterRecord.getSt2nvn()!=null && !"".equals(searchFilterRecord.getSt2nvn())){
			urlRequestParamsKeys.append(EbookingConstants.URL_CHAR_DELIMETER_FOR_PARAMS_WITH_HTML_REQUEST + "soknvn=" + searchFilterRecord.getSt2nvn());
		}
		if(searchFilterRecord.getWskunpa()!=null && !"".equals(searchFilterRecord.getWskunpa())){
			urlRequestParamsKeys.append(EbookingConstants.URL_CHAR_DELIMETER_FOR_PARAMS_WITH_HTML_REQUEST + "wskunpa=" + searchFilterRecord.getWskunpa());
		}
		if(searchFilterRecord.getSt2kod()!=null && !"".equals(searchFilterRecord.getSt2kod())){
			urlRequestParamsKeys.append(EbookingConstants.URL_CHAR_DELIMETER_FOR_PARAMS_WITH_HTML_REQUEST + "sokkod=" + searchFilterRecord.getSt2kod());
		}
		if(exactMatch){
			urlRequestParamsKeys.append(EbookingConstants.URL_CHAR_DELIMETER_FOR_PARAMS_WITH_HTML_REQUEST + "getval=J");
		}
		
		return urlRequestParamsKeys.toString();
	}
	*/

	/**
	 * 
	 * @param searchFilter
	 * @param appUser
	 * @return
	 */
	/*
	private String getRequestUrlKeyParametersSearchChildWindow(JsonTrorLoadUnloadPlacesContainer searchFilter, SystemaWebUser appUser){
		StringBuffer urlRequestParamsKeys = new StringBuffer();
		urlRequestParamsKeys.append("user=" + appUser.getUser());
		
		if(searchFilter.getSoknvn()!=null && !"".equals(searchFilter.getSoknvn())){
			urlRequestParamsKeys.append(TrorConstants.URL_CHAR_DELIMETER_FOR_PARAMS_WITH_HTML_REQUEST + "soknvn=" + searchFilter.getSoknvn());
		}
		if(searchFilter.getSokkod()!=null && !"".equals(searchFilter.getSokkod())){
			urlRequestParamsKeys.append(TrorConstants.URL_CHAR_DELIMETER_FOR_PARAMS_WITH_HTML_REQUEST + "sokkod=" + searchFilter.getSokkod());
		}
		return urlRequestParamsKeys.toString();
	}
	*/
	/**
	 * 
	 * @param searchFilter
	 * @param appUser
	 * @return
	 */
	/*
	private String getRequestUrlKeyParametersSearchChildWindow(JsonEbookingPackingCodesContainer searchFilter, SystemaWebUser appUser){
		StringBuffer urlRequestParamsKeys = new StringBuffer();
		urlRequestParamsKeys.append("user=" + appUser.getUser());
		
		if(searchFilter.getKode()!=null && !"".equals(searchFilter.getKode())){
			urlRequestParamsKeys.append(EbookingConstants.URL_CHAR_DELIMETER_FOR_PARAMS_WITH_HTML_REQUEST + "kode=" + searchFilter.getKode());
		}
		if(searchFilter.getTekst()!=null && !"".equals(searchFilter.getTekst())){
			urlRequestParamsKeys.append(EbookingConstants.URL_CHAR_DELIMETER_FOR_PARAMS_WITH_HTML_REQUEST + "tekst=" + searchFilter.getTekst());
		}
		//urlRequestParamsKeys.append(TransportDispConstants.URL_CHAR_DELIMETER_FOR_PARAMS_WITH_HTML_REQUEST + "getval=J");
		urlRequestParamsKeys.append(EbookingConstants.URL_CHAR_DELIMETER_FOR_PARAMS_WITH_HTML_REQUEST + "fullinfo=J"); //always the max. nr of columns (as default)		

		return urlRequestParamsKeys.toString();
	}
	*/
	/**
	 * 
	 * @param searchFilter
	 * @param appUser
	 * @return
	 */
	/*
	private String getRequestUrlKeyParametersSearchChildWindow(JsonEbookingDangerousGoodsContainer searchFilter, SystemaWebUser appUser){
		StringBuffer urlRequestParamsKeys = new StringBuffer();
		urlRequestParamsKeys.append("user=" + appUser.getUser());
		
		if(searchFilter.getUnnr()!=null && !"".equals(searchFilter.getUnnr())){
			urlRequestParamsKeys.append(EbookingConstants.URL_CHAR_DELIMETER_FOR_PARAMS_WITH_HTML_REQUEST + "unnr=" + searchFilter.getUnnr());
		}
		//user=JOVO&unnr=1950=&embg=&indx=&getval=&fullinfo=J
		urlRequestParamsKeys.append(EbookingConstants.URL_CHAR_DELIMETER_FOR_PARAMS_WITH_HTML_REQUEST + "fullinfo=J"); //always the max. nr of columns (as default)
		
		return urlRequestParamsKeys.toString();
	}
	*/
	
	//SERVICES
	@Qualifier ("urlCgiProxyService")
	private UrlCgiProxyService urlCgiProxyService;
	@Autowired
	@Required
	public void setUrlCgiProxyService (UrlCgiProxyService value){ this.urlCgiProxyService = value; }
	public UrlCgiProxyService getUrlCgiProxyService(){ return this.urlCgiProxyService; }
	
	
	@Qualifier ("trorMainOrderHeaderLandimportChildwindowService")
	private TrorMainOrderHeaderLandimportChildwindowService trorMainOrderHeaderLandimportChildwindowService;
	@Autowired
	@Required
	public void setTrorMainOrderHeaderLandimportChildwindowService (TrorMainOrderHeaderLandimportChildwindowService value){ this.trorMainOrderHeaderLandimportChildwindowService = value; }
	public TrorMainOrderHeaderLandimportChildwindowService getTrorMainOrderHeaderLandimportChildwindowService(){ return this.trorMainOrderHeaderLandimportChildwindowService; }
	
	
	@Qualifier ("trorMainOrderHeaderChildwindowService")
	private TrorMainOrderHeaderChildwindowService trorMainOrderHeaderChildwindowService;
	@Autowired
	@Required
	public void setTrorMainOrderHeaderChildwindowService (TrorMainOrderHeaderChildwindowService value){ this.trorMainOrderHeaderChildwindowService = value; }
	public TrorMainOrderHeaderChildwindowService getTrorMainOrderHeaderChildwindowService(){ return this.trorMainOrderHeaderChildwindowService; }
	
	
	@Qualifier 
	private MaintMainCundfService maintMainCundfService;
	@Autowired
	@Required	
	public void setMaintMainCundfService(MaintMainCundfService value){this.maintMainCundfService = value;}
	public MaintMainCundfService getMaintMainCundfService(){ return this.maintMainCundfService; }
	
	
}
