package no.systema.overview.ufortolledeoppdrag.controller;

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
import no.systema.overview.ufortolledeoppdrag.model.jsonjackson.tvinnlog.JsonTopicTvinnLogContainer;
import no.systema.overview.ufortolledeoppdrag.model.jsonjackson.tvinnlog.JsonTopicTvinnLogRecord;
import no.systema.overview.ufortolledeoppdrag.model.jsonjackson.tvinnlog.JsonTopicTvinnLogLargeTextContainer;
import no.systema.overview.ufortolledeoppdrag.model.jsonjackson.tvinnlog.JsonTopicTvinnLogLargeTextRecord;

import no.systema.overview.ufortolledeoppdrag.service.UoppdragService;
import no.systema.overview.ufortolledeoppdrag.url.store.UrlDataStore;
import no.systema.tds.tdsexport.util.RpgReturnResponseHandler;
import no.systema.overview.ufortolledeoppdrag.util.UoppdragConstants;
import no.systema.tds.util.TdsConstants;

/**
 * 
 * Uoppdrag Logging Controller 
 * 
 * @author oscardelatorre
 * @date Oct 22, 2013
 * 
 */

@Controller
public class UoppdragTvinnLogController {
	
	private static final Logger logger = Logger.getLogger(UoppdragTvinnLogController.class.getName());
	private PayloadContentFlusher payloadContentFlusher = new PayloadContentFlusher();
	private JsonDebugger jsonDebugger = new JsonDebugger();
	
	private ModelAndView loginView = new ModelAndView("login");
	private ApplicationContext context;
	
	@InitBinder
    protected void initBinder(WebDataBinder binder) {
		//binder.setValidator(new TdsExportValidator()); //it must have spring form tags in the html otherwise = meaningless
    }
	
	
	
	
	/**
	 * Renders the GUI view
	 * 
	 * @param session
	 * @param request
	 * @return
	 */
	@RequestMapping(value="uoppdrag_tvinnlog.do",  method= RequestMethod.GET)
	public ModelAndView doShowList(HttpSession session, HttpServletRequest request){
		Map model = new HashMap();
		SystemaWebUser appUser = (SystemaWebUser)session.getAttribute(AppConstants.SYSTEMA_WEB_USER_KEY);
		//String messageFromContext = this.context.getMessage("user.label",new Object[0], request.getLocale());
		ModelAndView successView = new ModelAndView("uoppdrag_tvinnlog");
		logger.info("Method: doShowList [RequestMapping-->uoppdrag_tvinnlog.do]");
		
		String opd = request.getParameter("opd");
		String avd = request.getParameter("avd");
		String sign = request.getParameter("sign");
		//
		String uoType = request.getParameter("uoType");
		String startParam = request.getParameter("llim");
		String endParam = request.getParameter("ulim");
		String antHfakt = request.getParameter("antHfakt");
		String status = request.getParameter("status");
		model.put("antHfakt", antHfakt); //archive
		model.put("status", status); //tvinn-log
		
		
		logger.info("Avd:" + avd);
		logger.info("Opd:" + opd);
		logger.info("uoType:" + uoType);
		logger.info("llim:" + startParam);
		logger.info("ulim:" + endParam);
		
		this.setDomainObjectsInView(request, model);
		//check user (should be in session already)
		if(appUser==null){
			return loginView;
			
		}else{
			//---------------------------
			//get BASE URL = RPG-PROGRAM
            //---------------------------
			String BASE_URL = UrlDataStore.UOPPDRAG_BASE_TVINN_LOG_FOR_SPECIFIC_TOPIC_URL;
			//url params
			String urlRequestParamsKeys = this.getRequestUrlKeyParameters(avd, opd, appUser);
			//for debug purposes in GUI
			session.setAttribute(UoppdragConstants.ACTIVE_URL_RPG, BASE_URL  + "==>params: " + urlRequestParamsKeys.toString()); 
			
			logger.info(Calendar.getInstance().getTime() + " CGI-start timestamp");
		    	logger.info("URL: " + BASE_URL);
		    	logger.info("URL PARAMS: " + urlRequestParamsKeys);
		    	//--------------------------------------
		    	//EXECUTE the FETCH (RPG program) here
		    	//--------------------------------------
		    String jsonPayload = this.urlCgiProxyService.getJsonContent(BASE_URL, urlRequestParamsKeys);
			//Debug --> 
		    this.jsonDebugger.debugJsonPayload(jsonPayload);
		    	logger.info(Calendar.getInstance().getTime() +  " CGI-end timestamp");
		    	if(jsonPayload!=null){
		    		JsonTopicTvinnLogContainer jsonContainer = this.uoppdragService.getTvinnLogContainer(jsonPayload);
		    		//add domain objects here
		    		this.setDomainObjectsInView(model, jsonContainer);
		    		this.setDomainObjectsInView(request, model);
		    		
		    		if(uoType!=null && !"".equals(uoType)){
			    		model.put(UoppdragConstants.DOMAIN_LIST_TYPE, uoType);
	    				model.put(UoppdragConstants.DOMAIN_LIMIT_LOWER, startParam);
	    				model.put(UoppdragConstants.DOMAIN_LIMIT_UPPER, endParam);
		    		}
		    		//logger.info("UOTYPE: " + model.get(UoppdragConstants.DOMAIN_LIST_TYPE));
		    		successView.addObject(UoppdragConstants.DOMAIN_MODEL, model);
				successView.addObject(UoppdragConstants.DOMAIN_LIST,jsonContainer.getLogg());	
				
		    	}else{
				logger.fatal("NO CONTENT on jsonPayload from URL... ??? <Null>");
				return loginView;
			}
			
		}
		
		return successView;
	}
	
	/**
	 * Render the EDIFACT - content on given path (text payload)
	 * 
	 * @param session
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value="uoppdrag_tvinnlog_renderEdifact.do", method={ RequestMethod.GET })
	public ModelAndView doUoppdragTvinnlogRenderEdifact(HttpSession session, HttpServletRequest request, HttpServletResponse response){
		logger.info("Inside doUoppdragTvinnlogRenderEdifact...");
		SystemaWebUser appUser = (SystemaWebUser)session.getAttribute(AppConstants.SYSTEMA_WEB_USER_KEY);
		
		if(appUser==null){
			return this.loginView;
			
		}else{
			
			//appUser.setActiveMenu(SystemaWebUser.ACTIVE_MENU_SIGN_PKI);
			session.setAttribute(TdsConstants.ACTIVE_URL_RPG, TdsConstants.ACTIVE_URL_RPG_INITVALUE); 
			String filePath = request.getParameter("fp");
			
			if(filePath!=null && !"".equals(filePath)){
				String absoluteFilePath = filePath;
				if(!filePath.startsWith("/") ){
					absoluteFilePath = "/"+ absoluteFilePath;
				}
				
				logger.info("[INFO] Edifact-payload path:" + absoluteFilePath);
                response.setContentType(AppConstants.HTML_CONTENTTYPE_TEXTHTML);
                //--> with browser dialogbox: response.setHeader ("Content-disposition", "attachment; filename=\"edifactPayload.txt\"");
                response.setHeader ("Content-disposition", "filename=\"edifactPayload.txt\"");
                
                logger.info("Start flushing file payload...");
                //send the file output to the ServletOutputStream
                try{
                		payloadContentFlusher.flushServletOutput(response, absoluteFilePath);
                		
                }catch (Exception e){
                		e.printStackTrace();
                }
            }
			//this to present the output in an independent window
            return(null);
			
		}
			
	}	
	
	@RequestMapping(value="uoppdrag_tvinnlog_renderLargeText.do",  method= RequestMethod.GET)
	public ModelAndView doUoppdragRenderLargeText(HttpSession session, HttpServletRequest request, HttpServletResponse response){
		Map model = new HashMap();
		SystemaWebUser appUser = (SystemaWebUser)session.getAttribute(AppConstants.SYSTEMA_WEB_USER_KEY);
		logger.info("Inside doUoppdragRenderLargeText...");
		
		//Message number
		String fmn = request.getParameter("fmn");
		//check user (should be in session already)
		if(appUser==null){
			return loginView;
			
		}else{
			//---------------------------
			//get BASE URL = RPG-PROGRAM
            //---------------------------
			String BASE_URL = UrlDataStore.UOPPDRAG_BASE_TVINN_LOG_LARGE_TEXT_FOR_SPECIFIC_TOPIC_URL;
			//url params
			String urlRequestParamsKeys = "user=" + appUser.getUser() + "&fmn=" + fmn;
			//for debug purposes in GUI
			session.setAttribute(TdsConstants.ACTIVE_URL_RPG, BASE_URL  + "==>params: " + urlRequestParamsKeys.toString()); 
			
			logger.info(Calendar.getInstance().getTime() + " CGI-start timestamp");
		    	logger.info("URL: " + BASE_URL);
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
	 * @param jsonPayload
	 * @return
	 */
	private String setLargeTextInDomainObject(String jsonPayload){
		StringBuffer sb = new StringBuffer();
		JsonTopicTvinnLogLargeTextContainer container = this.uoppdragService.getTvinnLogLargeTextContainer(jsonPayload);
		//list of objects
		Collection<JsonTopicTvinnLogLargeTextRecord> list = container.getLoggtext();
		for(JsonTopicTvinnLogLargeTextRecord record: list){
			sb.append(record.getF0078a());
			sb.append("<br>"); //since the output will be in HTML
			sb.append(record.getF0078b());
			sb.append("<br>");
		}
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
		//SADINO = Sad Import Norge , SADENO = SAD Eksport Norge,  TDSI = TDS Import , TDSE = TDS Eksport  eller BLANK = ALLT 
		String TYPE = "SADINO";
		//String action = request.getParameter("action");
		urlRequestParamsKeys.append("user=" + appUser.getUser());
		urlRequestParamsKeys.append(UoppdragConstants.URL_CHAR_DELIMETER_FOR_PARAMS_WITH_HTML_REQUEST + "avd=" + avd);
		urlRequestParamsKeys.append(UoppdragConstants.URL_CHAR_DELIMETER_FOR_PARAMS_WITH_HTML_REQUEST + "opd=" + opd);
		urlRequestParamsKeys.append(UoppdragConstants.URL_CHAR_DELIMETER_FOR_PARAMS_WITH_HTML_REQUEST + "typ=" + TYPE);
		
		
		return urlRequestParamsKeys.toString();
	}
	
	/**
	 * log Errors in Aspects and Domain objects in order to render on GUI
	 * @param model
	 * @param rpgReturnResponseHandler
	 * @param jsonTdsExportSpecificTopicRecord
	 */
	private void setFatalError(Map model, RpgReturnResponseHandler rpgReturnResponseHandler, JsonTopicTvinnLogRecord jsonTopicLoggingRecord){
		logger.info(rpgReturnResponseHandler.getErrorMessage());
		this.setAspectsInView(model, rpgReturnResponseHandler);
		//No refresh on jsonRecord is done for the GUI (form fields). Must be implemented right here, if required. !!
        this.setDomainObjectsInView(model, jsonTopicLoggingRecord);
	}
	
	/**
	 * Populates the html object (model map for the JSTL)
	 * 
	 * @param session
	 * @param model
	 * @param jsonTdsExportSpecificTopicContainer
	 * @return
	 */
	private void setDomainObjectsInView(Map model, JsonTopicTvinnLogContainer container){
		//SET HEADER RECORDS  (from RPG)
		model.put(UoppdragConstants.DOMAIN_CONTAINER, container);
		
		for (JsonTopicTvinnLogRecord record : container.getLogg()){
			model.put(UoppdragConstants.DOMAIN_RECORD, record);
		}
		
	}
	
	/**
	 * In order to pass some crucial GET parameters coming from the specific topic selection in previous update-topic action
	 * 
	 * @param request
	 * @param model
	 * @param jsonTdsExportSpecificTopicLoggingContainer
	 */
	private void setDomainObjectsInView(HttpServletRequest request, Map model){
		//SET HEADER RECORDS  (from request)
		String opd = request.getParameter("opd");
		String avd = request.getParameter("avd");
		String sign = request.getParameter("sign");
		String status = request.getParameter("status");
		String tullid = request.getParameter("tullId");
		String datum = request.getParameter("datum");
		model.put("opd", opd);
		model.put("avd", avd);
		model.put("sign", sign);
		model.put("status", status);
		model.put("tullId", tullid);
		model.put("datum", datum);
		
	}
	
	/**
	 * 
	 * 
	 * @param model
	 * @param jsonTdsExportSpecificTopicRecord
	 */
	private void setDomainObjectsInView(Map model, JsonTopicTvinnLogRecord jsonTopicLoggingRecord){
		//SET HEADER RECORDS  (from RPG)
		model.put(UoppdragConstants.DOMAIN_RECORD, jsonTopicLoggingRecord);
	}
	/**
	 * Sets aspects 
	 * Usually error objects, log objects, other non-domain related objects
	 * @param model
	 */
	
	private void setAspectsInView (Map model, RpgReturnResponseHandler rpgReturnResponseHandler){
		model.put(UoppdragConstants.ASPECT_ERROR_MESSAGE, rpgReturnResponseHandler.getErrorMessage());
		//extra error information
		StringBuffer errorMetaInformation = new StringBuffer();
		errorMetaInformation.append(rpgReturnResponseHandler.getUser());
		errorMetaInformation.append(rpgReturnResponseHandler.getSveh_syop());
		model.put(UoppdragConstants.ASPECT_ERROR_META_INFO, errorMetaInformation);
	}
			
	//SERVICES
	@Qualifier ("urlCgiProxyService")
	private UrlCgiProxyService urlCgiProxyService;
	@Autowired
	@Required
	public void setUrlCgiProxyService (UrlCgiProxyService value){ this.urlCgiProxyService = value; }
	public UrlCgiProxyService getUrlCgiProxyService(){ return this.urlCgiProxyService; }
	
	
	@Qualifier ("uoppdragService")
	private UoppdragService uoppdragService;
	@Autowired
	@Required
	public void setUoppdragService (UoppdragService value){ this.uoppdragService = value; }
	public UoppdragService getUoppdragService(){ return this.uoppdragService; }
	
	
	
}

