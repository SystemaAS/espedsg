package no.systema.efaktura.controller;

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
import org.springframework.web.bind.annotation.ResponseBody;
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
import no.systema.main.validator.LoginValidator;
import no.systema.main.util.AppConstants;
import no.systema.main.util.JsonDebugger;
import no.systema.main.util.io.PayloadContentFlusher;
import no.systema.main.model.SystemaWebUser;

//EFAKTURA
import no.systema.efaktura.service.EfakturaListService;
import no.systema.efaktura.model.jsonjackson.JsonEfakturaMainListContainer;
import no.systema.efaktura.model.jsonjackson.JsonEfakturaMainListRecord;
import no.systema.efaktura.filter.SearchFilterEfakturaMainList;
import no.systema.efaktura.url.store.EfakturaUrlDataStore;
import no.systema.efaktura.util.EfakturaConstants;
import no.systema.efaktura.util.RpgReturnResponseHandler;

/**
 * eFaktura main list Controller 
 * 
 * @author oscardelatorre
 * @date Jun 22, 2015
 * 
 */

@Controller
@SessionAttributes(AppConstants.SYSTEMA_WEB_USER_KEY)
@Scope("session")
public class EfakturaMainListController {
	private static final JsonDebugger jsonDebugger = new JsonDebugger(3000);
	private static Logger logger = Logger.getLogger(EfakturaMainListController.class.getName());
	private ModelAndView loginView = new ModelAndView("login");
	private ApplicationContext context;
	private LoginValidator loginValidator = new LoginValidator();
	private RpgReturnResponseHandler rpgReturnResponseHandler = new RpgReturnResponseHandler();
	private PayloadContentFlusher payloadContentFlusher = new PayloadContentFlusher();
	
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
	@RequestMapping(value="efaktura_mainlist.do", params="action=doFind",  method={RequestMethod.GET, RequestMethod.POST} )
	public ModelAndView doFind(@ModelAttribute ("record") SearchFilterEfakturaMainList recordToValidate, BindingResult bindingResult, HttpSession session, HttpServletRequest request){
		this.context = TdsAppContext.getApplicationContext();
		Collection logList = new ArrayList();
		logger.info("Inside: doFind");
		Map model = new HashMap();
		
		ModelAndView successView = new ModelAndView("efaktura_mainlist");
		SystemaWebUser appUser = this.loginValidator.getValidUser(session);
		String redirect = request.getParameter("rd");
		//check user (should be in session already)
		if(appUser==null){
			return loginView;
		
		}else{
			appUser.setActiveMenu(SystemaWebUser.ACTIVE_MENU_EFAKTURA);
			logger.info(Calendar.getInstance().getTime() + " CONTROLLER start - timestamp");
			logger.info("####!!!" + recordToValidate.getAvd());
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
	    		logger.info("[ERROR Validation] search-filter does not validate)");
	    		//put domain objects and do go back to the successView from here
	    		//drop downs
			
	    		successView.addObject(EfakturaConstants.DOMAIN_MODEL, model);
	    		successView.addObject("searchFilter", recordToValidate);
				return successView;
	    		
		    }else{
				
	    		Map maxWarningMap = new HashMap<String,String>();
	    		SearchFilterEfakturaMainList searchFilter = (SearchFilterEfakturaMainList)session.getAttribute(EfakturaConstants.SESSION_SEARCH_FILTER);
    			if(redirect!=null && !"".equals(redirect)){
	    			recordToValidate = searchFilter;
	    		}
    			//get list
	    		logList = this.getList(appUser, recordToValidate, maxWarningMap);
	    		//--------------------------------------
	    		//Final successView with domain objects
	    		//--------------------------------------
	    		//drop downs
	    		//this.setCodeDropDownMgr(appUser, model);
				
	    		successView.addObject(EfakturaConstants.DOMAIN_MODEL , model);
	    		//domain and search filter
	    		successView.addObject(EfakturaConstants.DOMAIN_LIST,logList);
	    		//Put list for upcomming view (PDF, Excel, etc)
	    		if(logList!=null && (redirect==null || "".equals(redirect)) ){
	    			session.setAttribute(session.getId() + EfakturaConstants.SESSION_LIST, logList);
	    			session.setAttribute(EfakturaConstants.SESSION_SEARCH_FILTER, recordToValidate);
	    		}
	    		if(redirect!=null && !"".equals(redirect)){
	    			//when this function is called with a redirect in another function
	    			successView.addObject("searchFilter", searchFilter);
	    		}else{
	    			//default
	    			successView.addObject("searchFilter", recordToValidate);
	    		}

	    		logger.info(Calendar.getInstance().getTime() + " CONTROLLER end - timestamp");
	    		return successView;
		    }
		}
	}
	
	/**
	 * 
	 * @param session
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value="efaktura_mainlist_renderArchive.do", method={ RequestMethod.GET })
	public ModelAndView doEfakturaMainlistRenderArchive(HttpSession session, HttpServletRequest request, HttpServletResponse response){
		logger.info("Inside doEfakturaMainlistRenderArchive...");
		SystemaWebUser appUser = (SystemaWebUser)session.getAttribute(AppConstants.SYSTEMA_WEB_USER_KEY);
		
		if(appUser==null){
			return this.loginView;
			
		}else{
			
			//appUser.setActiveMenu(SystemaWebUser.ACTIVE_MENU_SIGN_PKI);
			//session.setAttribute(TdsConstants.ACTIVE_URL_RPG, TdsConstants.ACTIVE_URL_RPG_INITVALUE); 
			String filePath = request.getParameter("fp");
			
			if(filePath!=null && !"".equals(filePath)){
				
                String absoluteFilePath = filePath;
                
                //must know the file type in order to put the correct content type on the Servlet response.
                String fileType = this.payloadContentFlusher.getFileType(filePath);
                if(AppConstants.DOCUMENTTYPE_PDF.equals(fileType)){
                		response.setContentType(AppConstants.HTML_CONTENTTYPE_PDF);
                }else if(AppConstants.DOCUMENTTYPE_TIFF.equals(fileType) || AppConstants.DOCUMENTTYPE_TIF.equals(fileType)){
            			response.setContentType(AppConstants.HTML_CONTENTTYPE_TIFF);
                }else if(AppConstants.DOCUMENTTYPE_JPEG.equals(fileType) || AppConstants.DOCUMENTTYPE_JPG.equals(fileType)){
                		response.setContentType(AppConstants.HTML_CONTENTTYPE_JPEG);
                }else if(AppConstants.DOCUMENTTYPE_TXT.equals(fileType)){
            			response.setContentType(AppConstants.HTML_CONTENTTYPE_TEXTHTML);
                }else if(AppConstants.DOCUMENTTYPE_DOC.equals(fileType)){
            			response.setContentType(AppConstants.HTML_CONTENTTYPE_WORD);
                }else if(AppConstants.DOCUMENTTYPE_XLS.equals(fileType)){
            			response.setContentType(AppConstants.HTML_CONTENTTYPE_EXCEL);
                }
                //--> with browser dialogbox: response.setHeader ("Content-disposition", "attachment; filename=\"edifactPayload.txt\"");
                response.setHeader ("Content-disposition", "filename=\"archiveDocument." + fileType + "\"");
                
                logger.info("Start flushing file payload...");
                //send the file output to the ServletOutputStream
                try{
                		this.payloadContentFlusher.flushServletOutput(response, absoluteFilePath);
                		//payloadContentFlusher.flushServletOutput(response, "plain text test...".getBytes());
                	
                }catch (Exception e){
                		e.printStackTrace();
                }
            }
			//this to present the output in an independent window
            return(null);
			
		}
			
	}
	/**
	 * 
	 * @param session
	 * @param request
	 * @return
	 */
	@RequestMapping(value="efaktura_mainlist_resend.do",  method={RequestMethod.GET, RequestMethod.POST} )
	public ModelAndView doResend(HttpSession session, HttpServletRequest request){
		this.context = TdsAppContext.getApplicationContext();
		Map model = new HashMap();
		logger.info("Inside: doResend");
		ModelAndView successView = new ModelAndView("redirect:efaktura_mainlist.do?action=doFind&rd=1");
		
		SystemaWebUser appUser = this.loginValidator.getValidUser(session);
		String invoiceNr = request.getParameter("fn");
		String status = request.getParameter("st");
		
		//check user (should be in session already)
		if(appUser==null){
			return loginView;
		
		}else{
			//appUser.setActiveMenu(SystemaWebUser.ACTIVE_MENU_EFAKTURA);
			logger.info(Calendar.getInstance().getTime() + " CONTROLLER start - timestamp");
			String BASE_URL = EfakturaUrlDataStore.EFAKTURA_BASE_MAIN_LOG_LIST_RESEND_INVOICE_URL;
			//add URL-parameters
			StringBuffer urlRequestParams = new StringBuffer();
			urlRequestParams.append("user=" + appUser.getUser());
			urlRequestParams.append("&fn=" + invoiceNr);
			urlRequestParams.append("&st=");
			
			//session.setAttribute(TransportDispConstants.ACTIVE_URL_RPG_TRANSPORT_DISP, BASE_URL + "==>params: " + urlRequestParams.toString()); 
	    	logger.info(Calendar.getInstance().getTime() + " CGI-start timestamp");
	    	logger.info("URL: " + BASE_URL);
	    	logger.info("URL PARAMS: " + urlRequestParams);
	    	String jsonPayload = this.urlCgiProxyService.getJsonContent(BASE_URL, urlRequestParams.toString());
	    	//Debug --> 
	    	logger.debug(jsonDebugger.debugJsonPayloadWithLog4J(jsonPayload));
	    	logger.info(Calendar.getInstance().getTime() +  " CGI-end timestamp");
	    	if(jsonPayload!=null){
	    		//Do something
	    	}		
			
    		logger.info(Calendar.getInstance().getTime() + " CONTROLLER end - timestamp");
    		return successView;
		    
		}
	}		
	
	/**
	 * 
	 * @param appUser
	 * @param wssavd
	 * @return
	 */
	private Collection<JsonEfakturaMainListRecord> getList(SystemaWebUser appUser, SearchFilterEfakturaMainList recordToValidate, Map<String,String> maxWarningMap){
		Collection<JsonEfakturaMainListRecord> outputList = new ArrayList();
		//---------------
    	//Get main list
		//---------------
		final String BASE_URL = EfakturaUrlDataStore.EFAKTURA_BASE_MAIN_LOG_LIST_URL;
		//add URL-parameters
		StringBuffer urlRequestParams = new StringBuffer();
		urlRequestParams.append("user=" + appUser.getUser());
		if(!"".equals(recordToValidate.getAvd())&& recordToValidate.getAvd()!=null ){ urlRequestParams.append("&avd=" + recordToValidate.getAvd()); }
		if(!"".equals(recordToValidate.getFaktnr())&& recordToValidate.getFaktnr()!=null ){ urlRequestParams.append("&fn=" + recordToValidate.getFaktnr()); }
		if(!"".equals(recordToValidate.getKundenr())&& recordToValidate.getKundenr()!=null ){ urlRequestParams.append("&fkn=" + recordToValidate.getKundenr()); }
		if(!"".equals(recordToValidate.getRfa())&& recordToValidate.getRfa()!=null ){ urlRequestParams.append("&rfa=" + recordToValidate.getRfa()); }
		if(!"".equals(recordToValidate.getFrom())&& recordToValidate.getFrom()!=null ){ urlRequestParams.append("&dtf=" + recordToValidate.getFrom()); }
		if(!"".equals(recordToValidate.getTo())&& recordToValidate.getTo()!=null ){ urlRequestParams.append("&dtt=" + recordToValidate.getTo()); }
		if(!"".equals(recordToValidate.getStatus())&& recordToValidate.getStatus()!=null ){ 
			urlRequestParams.append("&st=" + recordToValidate.getStatus());
		}else{
			urlRequestParams.append("&st=*");
		}
		
		
		//session.setAttribute(TransportDispConstants.ACTIVE_URL_RPG_TRANSPORT_DISP, BASE_URL + "==>params: " + urlRequestParams.toString()); 
    	logger.info(Calendar.getInstance().getTime() + " CGI-start timestamp");
    	logger.info("URL: " + BASE_URL);
    	logger.info("URL PARAMS: " + urlRequestParams);
    	String jsonPayload = this.urlCgiProxyService.getJsonContent(BASE_URL, urlRequestParams.toString());
    	//Debug --> 
    	logger.debug(jsonDebugger.debugJsonPayloadWithLog4J(jsonPayload));
    	logger.info(Calendar.getInstance().getTime() +  " CGI-end timestamp");
    	if(jsonPayload!=null){
    		JsonEfakturaMainListContainer listContainer = this.efakturaListService.getMainListContainer(jsonPayload);
    		outputList = listContainer.getOrderList();	
    		//maxWarningMap.put(EfakturaConstants.DOMAIN_MAX_WARNING_OPEN_ORDERS, jsonOpenOrdersListContainer.getMaxWarning());
    	}		
	    
		return outputList;
	}
	
	
	@RequestMapping(value="efaktura_jr001.do", method={ RequestMethod.GET })
	@ResponseBody
	public String foo() {
	    return "Response!";
	}
	
	
	
	
	//SERVICES
	@Qualifier ("urlCgiProxyService")
	private UrlCgiProxyService urlCgiProxyService;
	@Autowired
	@Required
	public void setUrlCgiProxyService (UrlCgiProxyService value){ this.urlCgiProxyService = value; }
	public UrlCgiProxyService getUrlCgiProxyService(){ return this.urlCgiProxyService; }
	
	
	@Qualifier ("efakturaListService")
	private EfakturaListService efakturaListService;
	@Autowired
	@Required
	public void setEfakturaListService (EfakturaListService value){ this.efakturaListService = value; }
	public EfakturaListService getEfakturaListService(){ return this.efakturaListService; }
	
	
}

