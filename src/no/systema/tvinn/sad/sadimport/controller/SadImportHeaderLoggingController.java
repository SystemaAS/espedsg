package no.systema.tvinn.sad.sadimport.controller;

import java.util.*;

import org.apache.log4j.Logger;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Required;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Scope;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.web.bind.WebDataBinder;


//application imports
import no.systema.main.service.UrlCgiProxyService;
import no.systema.main.util.AppConstants;
import no.systema.main.util.JsonDebugger;
import no.systema.main.util.io.PayloadContentFlusher;

import no.systema.main.model.SystemaWebUser;
import no.systema.tvinn.sad.sadimport.model.jsonjackson.topic.logging.JsonSadImportSpecificTopicLoggingContainer;
import no.systema.tvinn.sad.sadimport.model.jsonjackson.topic.logging.JsonSadImportSpecificTopicLoggingLargeTextContainer;
import no.systema.tvinn.sad.sadimport.model.jsonjackson.topic.logging.JsonSadImportSpecificTopicLoggingLargeTextRecord;
import no.systema.tvinn.sad.sadimport.model.jsonjackson.topic.logging.JsonSadImportSpecificTopicLoggingRecord;
import no.systema.tvinn.sad.sadimport.service.SadImportSpecificTopicService;
import no.systema.tvinn.sad.sadimport.url.store.SadImportUrlDataStore;
import no.systema.tvinn.sad.sadimport.util.RpgReturnResponseHandler;
import no.systema.tvinn.sad.util.TvinnSadConstants;


/**
 * SAD Import Topic Logging Controller 
 * 
 * @author oscardelatorre
 * @date Jul 30, 2014
 * 
 *
 */

@Controller
@Scope("session")
public class SadImportHeaderLoggingController {
	private static final JsonDebugger jsonDebugger = new JsonDebugger();
	private static final Logger logger = Logger.getLogger(SadImportHeaderLoggingController.class.getName());
	private PayloadContentFlusher payloadContentFlusher = new PayloadContentFlusher();
	
	private ModelAndView loginView = new ModelAndView("login");
	private ApplicationContext context;
	
	@InitBinder
    protected void initBinder(WebDataBinder binder) {
		
    }
	
	
	
	
	/**
	 * Renders the GUI view
	 * 
	 * @param session
	 * @param request
	 * @return
	 */
	@RequestMapping(value="tvinnsadimport_logging.do",  method= RequestMethod.GET)
	public ModelAndView doShowList(HttpSession session, HttpServletRequest request){
		Map model = new HashMap();
		SystemaWebUser appUser = (SystemaWebUser)session.getAttribute(AppConstants.SYSTEMA_WEB_USER_KEY);
		//String messageFromContext = this.context.getMessage("user.label",new Object[0], request.getLocale());
		ModelAndView successView = new ModelAndView("tvinnsadimport_logging");
		logger.info("Method: doShowList [RequestMapping-->tvinnsadimport_logging.do]");
		
		String opd = request.getParameter("opd");
		String avd = request.getParameter("avd");
		
		logger.info("Avd:" + avd);
		logger.info("Opd:" + opd);
		this.setDomainObjectsInView(request, model);
		//check user (should be in session already)
		if(appUser==null){
			return loginView;
			
		}else{
			//---------------------------
			//get BASE URL = RPG-PROGRAM
            //---------------------------
			String BASE_URL = SadImportUrlDataStore.SAD_IMPORT_BASE_LOG_LIST_FOR_SPECIFIC_TOPIC_URL;
			//url params
			String urlRequestParamsKeys = this.getRequestUrlKeyParameters(avd, opd, appUser);
			//for debug purposes in GUI
			session.setAttribute(TvinnSadConstants.ACTIVE_URL_RPG_TVINN_SAD, BASE_URL  + "==>params: " + urlRequestParamsKeys.toString()); 
			
			logger.info(Calendar.getInstance().getTime() + " CGI-start timestamp");
		    	logger.info("URL: " + jsonDebugger.getBASE_URL_NoHostName(BASE_URL));
		    	logger.info("URL PARAMS: " + urlRequestParamsKeys);
		    	//--------------------------------------
		    	//EXECUTE the FETCH (RPG program) here
		    	//--------------------------------------
		    	String jsonPayload = this.urlCgiProxyService.getJsonContent(BASE_URL, urlRequestParamsKeys);
			//Debug --> 
		    	logger.info(" --> jsonPayload:" + jsonPayload);
		    	logger.info(Calendar.getInstance().getTime() +  " CGI-end timestamp");
		    	
		    	if(jsonPayload!=null){
		    		JsonSadImportSpecificTopicLoggingContainer jsonSadImportSpecificTopicLoggingContainer = this.sadImportSpecificTopicService.getSadImportSpecificTopicLoggingContainer(jsonPayload);
		    		//add domain objects here
		    		this.setDomainObjectsInView(model, jsonSadImportSpecificTopicLoggingContainer);
		    		this.setDomainObjectsInView(request, model);
		    		
		    		successView.addObject(TvinnSadConstants.DOMAIN_MODEL, model);
				successView.addObject(TvinnSadConstants.DOMAIN_LIST,jsonSadImportSpecificTopicLoggingContainer.getLogg());
		    		
		    	}else{
				logger.fatal("NO CONTENT on jsonPayload from URL... ??? <Null>");
				return loginView;
			}

	   		
		}
		
		return successView;
	}
	
	/**
	 * 
	 * @param session
	 * @param request
	 * @return
	 */
	@RequestMapping(value="tvinnsadimport_renderEdifact.do", method={ RequestMethod.GET })
	public ModelAndView doSadImportRenderEdifact(HttpSession session, HttpServletRequest request, HttpServletResponse response){
		logger.info("Inside doSadImportRenderEdifact...");
		SystemaWebUser appUser = (SystemaWebUser)session.getAttribute(AppConstants.SYSTEMA_WEB_USER_KEY);
		
		if(appUser==null){
			return this.loginView;
			
		}else{
			
			//appUser.setActiveMenu(SystemaWebUser.ACTIVE_MENU_SIGN_PKI);
			session.setAttribute(TvinnSadConstants.ACTIVE_URL_RPG_TVINN_SAD, TvinnSadConstants.ACTIVE_URL_RPG_INITVALUE); 
			String filePath = request.getParameter("fp");
			
			if(filePath!=null && !"".equals(filePath)){
                String absoluteFilePath = filePath;
                //String absoluteFilePath = appUser.getServletHost() + filePath;

                response.setContentType(AppConstants.HTML_CONTENTTYPE_TEXTHTML);
                //--> with browser dialogbox: response.setHeader ("Content-disposition", "attachment; filename=\"edifactPayload.txt\"");
                response.setHeader ("Content-disposition", "filename=\"edifactPayload.txt\"");
                
                logger.info("Start flushing file payload...");
                //send the file output to the ServletOutputStream
                try{
                		payloadContentFlusher.flushServletOutput(response, absoluteFilePath);
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
	@RequestMapping(value="tvinnsadimport_renderLargeText.do",  method= RequestMethod.GET)
	public ModelAndView doSadImportRenderLargeText(HttpSession session, HttpServletRequest request, HttpServletResponse response){
		Map model = new HashMap();
		SystemaWebUser appUser = (SystemaWebUser)session.getAttribute(AppConstants.SYSTEMA_WEB_USER_KEY);
		logger.info("Inside doSadImportRenderLargeText...");
		
		//Message number
		String fmn = request.getParameter("fmn");
		//check user (should be in session already)
		if(appUser==null){
			return loginView;
			
		}else{
			//---------------------------
			//get BASE URL = RPG-PROGRAM
            //---------------------------
			String BASE_URL = SadImportUrlDataStore.SAD_IMPORT_BASE_LOG_LARGE_TEXT_FOR_SPECIFIC_TOPIC_URL;
			//url params
			String urlRequestParamsKeys = this.getRequestUrlKeyParametersForLargeText(fmn, appUser);
			//for debug purposes in GUI
			session.setAttribute(TvinnSadConstants.ACTIVE_URL_RPG_TVINN_SAD, BASE_URL  + "==>params: " + urlRequestParamsKeys.toString()); 
			
			logger.info(Calendar.getInstance().getTime() + " CGI-start timestamp");
		    	logger.info("URL: " + jsonDebugger.getBASE_URL_NoHostName(BASE_URL));
		    	logger.info("URL PARAMS: " + urlRequestParamsKeys);
		    	//--------------------------------------
		    	//EXECUTE the FETCH (RPG program) here
		    	//--------------------------------------
		    	String jsonPayload = this.urlCgiProxyService.getJsonContent(BASE_URL, urlRequestParamsKeys);
				//Debug --> 
		    	logger.info(" --> jsonPayload:" + jsonPayload);
		    	logger.info(Calendar.getInstance().getTime() +  " CGI-end timestamp");
		    	if(jsonPayload!=null){
		    		String largeText = this.setLargeTextInDomainObject(jsonPayload);
		    		if(largeText!=null && !"".equals(largeText)){
		                
		                response.setContentType(AppConstants.HTML_CONTENTTYPE_TEXTHTML);
		                //--> with browser dialogbox: response.setHeader ("Content-disposition", "attachment; filename=\"edifactPayload.txt\"");
		                response.setHeader ("Content-disposition", "filename=\"largeLogContent.txt\"");
		                
		                try{
		                		//send the output to the ServletOutputStream
			                payloadContentFlusher.flushServletOutput(response, largeText.getBytes());
			                
		                	}catch (Exception e){
		                		e.printStackTrace();
		                }
		            }
		    	}else{
				logger.fatal("NO CONTENT on jsonPayload from URL... ??? <Null>");
				return loginView;
			}

	   		
		}
		return(null);
	}

	/**
	 * 
	 * Gets the large text of a specific log record
	 * @jsonPayload
	 * @return
	 */
	private String setLargeTextInDomainObject(String jsonPayload){
		StringBuffer sb = new StringBuffer();
		JsonSadImportSpecificTopicLoggingLargeTextContainer container = this.sadImportSpecificTopicService.getSadImportSpecificTopicLoggingLargeTextContainer(jsonPayload);
		logger.info(container.getUser());
		//list of objects
		Collection<JsonSadImportSpecificTopicLoggingLargeTextRecord> list = container.getLoggtext();
		
		//Debug
		for(JsonSadImportSpecificTopicLoggingLargeTextRecord record : list){
			sb.append(record.getF0078a());
			sb.append("<br>"); //since the output will be in HTML
			sb.append(record.getF0078b());
			sb.append("<br>"); //since the output will be in HTML
			sb.append(record.getF0078c());
			sb.append("<br><br>");
			
		}//add domain objects here
		
		return sb.toString();
		
	}
	
	/**
	 * 
	 * @param avd
	 * @param opd
	 * @param appUser
	 * @return
	 */
	private String getRequestUrlKeyParameters(String avd, String opd, SystemaWebUser appUser){
		StringBuffer urlRequestParamsKeys = new StringBuffer();
		//String action = request.getParameter("action");
		urlRequestParamsKeys.append("user=" + appUser.getUser());
		urlRequestParamsKeys.append(TvinnSadConstants.URL_CHAR_DELIMETER_FOR_PARAMS_WITH_HTML_REQUEST + "avd=" + avd);
		urlRequestParamsKeys.append(TvinnSadConstants.URL_CHAR_DELIMETER_FOR_PARAMS_WITH_HTML_REQUEST + "opd=" + opd);
		
		return urlRequestParamsKeys.toString();
	}
	/**
	 * 
	 * @param fmn
	 * @param appUser
	 * @return
	 */
	private String getRequestUrlKeyParametersForLargeText(String fmn, SystemaWebUser appUser){
		StringBuffer urlRequestParamsKeys = new StringBuffer();
		//String action = request.getParameter("action");
		urlRequestParamsKeys.append("user=" + appUser.getUser());
		urlRequestParamsKeys.append(TvinnSadConstants.URL_CHAR_DELIMETER_FOR_PARAMS_WITH_HTML_REQUEST + "fmn=" + fmn);
		
		return urlRequestParamsKeys.toString();
	}
	/**
	 * log Errors in Aspects and Domain objects in order to render on GUI
	 * @param model
	 * @param rpgReturnResponseHandler
	 * @param record
	 */
	private void setFatalError(Map model, RpgReturnResponseHandler rpgReturnResponseHandler, JsonSadImportSpecificTopicLoggingRecord record){
		logger.info(rpgReturnResponseHandler.getErrorMessage());
		this.setAspectsInView(model, rpgReturnResponseHandler);
		//No refresh on jsonRecord is done for the GUI (form fields). Must be implemented right here, if required. !!
        this.setDomainObjectsInView(model, record);
	}
	
	/**
	 * Populates the html object (model map for the JSTL)
	 * 
	 * @param session
	 * @param model
	 * @param container
	 * @return
	 */
	private void setDomainObjectsInView(Map model, JsonSadImportSpecificTopicLoggingContainer container){
		//SET HEADER RECORDS  (from RPG)
		for (JsonSadImportSpecificTopicLoggingRecord record : container.getLogg()){
			model.put(TvinnSadConstants.DOMAIN_RECORD, record);
		}
		
	}
	
	/**
	 * In order to pass some crucial GET parameters coming from the specific topic selection in previous update-topic action
	 * 
	 * @param request
	 * @param model
	 */
	private void setDomainObjectsInView(HttpServletRequest request, Map model){
		//SET HEADER RECORDS  (from request)
		String opd = request.getParameter("opd");
		String avd = request.getParameter("avd");
		String sign = request.getParameter("sign");
		String status = request.getParameter("status");
		String tullid = request.getParameter("tullId");
		String datum = request.getParameter("datum");
		String omberegningFlag = request.getParameter("o2_sist");
		String omberegningDate = request.getParameter("o2_sidt");
		
		
		model.put("opd", opd);
		model.put("avd", avd);
		model.put("sign", sign);
		model.put("status", status);
		model.put("tullId", tullid);
		model.put("datum", datum);
		model.put("o2_sist", omberegningFlag);
		model.put("o2_sidt", omberegningDate);
		
	}
	
	/**
	 * 
	 * 
	 * @param model
	 * @param record
	 */
	private void setDomainObjectsInView(Map model, JsonSadImportSpecificTopicLoggingRecord record){
		//SET HEADER RECORDS  (from RPG)
		model.put(TvinnSadConstants.DOMAIN_RECORD, record);
	}
	/**
	 * Sets aspects 
	 * Usually error objects, log objects, other non-domain related objects
	 * @param model
	 */
	
	private void setAspectsInView (Map model, RpgReturnResponseHandler rpgReturnResponseHandler){
		model.put(TvinnSadConstants.ASPECT_ERROR_MESSAGE, rpgReturnResponseHandler.getErrorMessage());
		//extra error information
		StringBuffer errorMetaInformation = new StringBuffer();
		errorMetaInformation.append(rpgReturnResponseHandler.getUser());
		errorMetaInformation.append(rpgReturnResponseHandler.getOpd());
		model.put(TvinnSadConstants.ASPECT_ERROR_META_INFO, errorMetaInformation);
		
	}
			
	//SERVICES
	@Qualifier ("urlCgiProxyService")
	private UrlCgiProxyService urlCgiProxyService;
	@Autowired
	@Required
	public void setUrlCgiProxyService (UrlCgiProxyService value){ this.urlCgiProxyService = value; }
	public UrlCgiProxyService getUrlCgiProxyService(){ return this.urlCgiProxyService; }
	
	@Qualifier ("sadImportSpecificTopicService")
	private SadImportSpecificTopicService sadImportSpecificTopicService;
	@Autowired
	@Required
	public void setSadImportSpecificTopicService (SadImportSpecificTopicService value){ this.sadImportSpecificTopicService = value; }
	public SadImportSpecificTopicService getSadImportSpecificTopicService(){ return this.sadImportSpecificTopicService; }
	
	
	
}

