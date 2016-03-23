package no.systema.skat.nctsexport.controller;

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


import no.systema.skat.skatexport.filter.SearchFilterSkatExportTopicList;
import no.systema.skat.skatexport.model.jsonjackson.topic.JsonSkatExportTopicListContainer;
import no.systema.skat.skatexport.model.jsonjackson.topic.JsonSkatExportTopicListRecord;
import no.systema.skat.skatexport.service.SkatExportTopicListService;
import no.systema.skat.skatexport.url.store.SkatExportUrlDataStore;
import no.systema.skat.util.SkatConstants;

/**
 * SKAT NCTS Export Item Controller - child windows popup
 * 
 * @author oscardelatorre
 * @date Mar 23, 2016
 * 
 */

@Controller
@SessionAttributes(AppConstants.SYSTEMA_WEB_USER_KEY)
@Scope("session")
public class SkatNctsExportItemsControllerChildWindow {
	
	private static final Logger logger = Logger.getLogger(SkatNctsExportItemsControllerChildWindow.class.getName());
	private static final JsonDebugger jsonDebugger = new JsonDebugger(2000);
	
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
	@RequestMapping(value="skatnctsexport_edit_items_childwindow_angivelselist.do", params="action=doFind",  method={RequestMethod.GET, RequestMethod.POST } )
	public ModelAndView doInitAngivelseList(@ModelAttribute ("record") SearchFilterSkatExportTopicList searchFilter, HttpSession session, HttpServletRequest request){
		this.context = TdsAppContext.getApplicationContext();
		logger.info("Inside: doInitAngivelseList");
		Map model = new HashMap();
		
		ModelAndView successView = new ModelAndView("skatnctsexport_edit_items_childwindow_angivelselist");
		SystemaWebUser appUser = this.loginValidator.getValidUser(session);
		//check user (should be in session already)
		if(appUser==null){
			return this.loginView;
			
		}else{
			
			List<JsonSkatExportTopicListRecord> list = (List)this.getAngivelseList(appUser, searchFilter);
			model.put("angivelseList", list);
			successView.addObject(SkatConstants.DOMAIN_SEARCH_FILTER , searchFilter);
			successView.addObject(SkatConstants.DOMAIN_MODEL , model);
			
	    	return successView;
		}
	}
	
	/**
	 * 
	 * @param appUser
	 * @param searchFilter
	 * @return
	 */
	private Collection<JsonSkatExportTopicListRecord> getAngivelseList(SystemaWebUser appUser, SearchFilterSkatExportTopicList searchFilter){
		Collection<JsonSkatExportTopicListRecord> list = new ArrayList<JsonSkatExportTopicListRecord>();
		
		logger.info(Calendar.getInstance().getTime() + " CONTROLLER start - timestamp");
		
		//get BASE URL
		final String BASE_URL = SkatExportUrlDataStore.SKAT_EXPORT_BASE_TOPICLIST_URL;
		//add URL-parameters
		String urlRequestParams = this.getRequestUrlKeyParameters(searchFilter, appUser);
		logger.info(Calendar.getInstance().getTime() + " CGI-start timestamp");
    	logger.info("URL: " + BASE_URL);
    	logger.info("URL PARAMS: " + urlRequestParams);
    	
    	String jsonPayload = this.urlCgiProxyService.getJsonContent(BASE_URL, urlRequestParams);

    	//Debug --> 
    	logger.info(jsonPayload);
    	logger.info(Calendar.getInstance().getTime() +  " CGI-end timestamp");
    	if(jsonPayload!=null){
    		JsonSkatExportTopicListContainer jsonSkatExportTopicListContainer = this.skatExportTopicListService.getSkatExportTopicListContainer(jsonPayload);
			//-----------------------------------------------------------
			//now filter the topic list with the search filter (if applicable)
			//-----------------------------------------------------------
			list = jsonSkatExportTopicListContainer.getOrderList();	
			//Remove all "D" status rows. These are to be shown if-and-only-if the user demand it explicitly in the search filter
			if(!"D".equals(searchFilter.getStatus())){
				//To avoid the ...ConcurrentModificationException we take a copy of the existing list and iterate over new copy
				for (JsonSkatExportTopicListRecord record : new ArrayList<JsonSkatExportTopicListRecord>(list)){
					if("D".equals(record.getStatus())){
						list.remove(record);
					}
				}
			}
    	}
		return list;
	}
    	
    /**
     * 	
     * @param searchFilter
     * @param appUser
     * @return
     */
	private String getRequestUrlKeyParameters(SearchFilterSkatExportTopicList searchFilter, SystemaWebUser appUser){
		StringBuffer urlRequestParamsKeys = new StringBuffer();
		//String action = request.getParameter("action");
		
		urlRequestParamsKeys.append("user=" + appUser.getUser());
		if(searchFilter.getAvd()!=null && !"".equals(searchFilter.getAvd())){
			urlRequestParamsKeys.append(SkatConstants.URL_CHAR_DELIMETER_FOR_PARAMS_WITH_HTML_REQUEST + "avd=" + searchFilter.getAvd());
		}
		if(searchFilter.getOpd()!=null && !"".equals(searchFilter.getOpd())){
			urlRequestParamsKeys.append(SkatConstants.URL_CHAR_DELIMETER_FOR_PARAMS_WITH_HTML_REQUEST + "opd=" + searchFilter.getOpd());
		}
		if(searchFilter.getSign()!=null && !"".equals(searchFilter.getSign())){
			urlRequestParamsKeys.append(SkatConstants.URL_CHAR_DELIMETER_FOR_PARAMS_WITH_HTML_REQUEST + "sign=" + searchFilter.getSign());
		}
		if(searchFilter.getRefnr()!=null && !"".equals(searchFilter.getRefnr())){
			urlRequestParamsKeys.append(SkatConstants.URL_CHAR_DELIMETER_FOR_PARAMS_WITH_HTML_REQUEST + "refnr=" + searchFilter.getRefnr());
		}
		if(searchFilter.getXrefnr()!=null && !"".equals(searchFilter.getXrefnr())){
			urlRequestParamsKeys.append(SkatConstants.URL_CHAR_DELIMETER_FOR_PARAMS_WITH_HTML_REQUEST + "xref=" + searchFilter.getXrefnr());
		}
		if(searchFilter.getDatum()!=null && !"".equals(searchFilter.getDatum())){
			urlRequestParamsKeys.append(SkatConstants.URL_CHAR_DELIMETER_FOR_PARAMS_WITH_HTML_REQUEST + "datum=" + searchFilter.getDatum());
		}
		if(searchFilter.getStatus()!=null && !"".equals(searchFilter.getStatus())){
			urlRequestParamsKeys.append(SkatConstants.URL_CHAR_DELIMETER_FOR_PARAMS_WITH_HTML_REQUEST + "status=" + searchFilter.getStatus());
		}
		if(searchFilter.getAvsNavn()!=null && !"".equals(searchFilter.getAvsNavn())){
			urlRequestParamsKeys.append(SkatConstants.URL_CHAR_DELIMETER_FOR_PARAMS_WITH_HTML_REQUEST + "avsNavn=" + searchFilter.getAvsNavn());
		}
		if(searchFilter.getMotNavn()!=null && !"".equals(searchFilter.getMotNavn())){
			urlRequestParamsKeys.append(SkatConstants.URL_CHAR_DELIMETER_FOR_PARAMS_WITH_HTML_REQUEST + "motNavn=" + searchFilter.getMotNavn());
		}
		if(searchFilter.getInternFakturanr()!=null && !"".equals(searchFilter.getInternFakturanr())){
			urlRequestParamsKeys.append(SkatConstants.URL_CHAR_DELIMETER_FOR_PARAMS_WITH_HTML_REQUEST + "fakt=" + searchFilter.getInternFakturanr());
		}
		if(searchFilter.getAart()!=null && !"".equals(searchFilter.getAart())){
			urlRequestParamsKeys.append(SkatConstants.URL_CHAR_DELIMETER_FOR_PARAMS_WITH_HTML_REQUEST + "aart=" + searchFilter.getAart());
		}
		return urlRequestParamsKeys.toString();
	}	
	
	//SERVICES
	@Qualifier ("urlCgiProxyService")
	private UrlCgiProxyService urlCgiProxyService;
	@Autowired
	@Required
	public void setUrlCgiProxyService (UrlCgiProxyService value){ this.urlCgiProxyService = value; }
	public UrlCgiProxyService getUrlCgiProxyService(){ return this.urlCgiProxyService; }
	
	
	@Qualifier ("skatExportTopicListService")
	private SkatExportTopicListService skatExportTopicListService;
	@Autowired
	@Required
	public void setSkatExportTopicListService (SkatExportTopicListService value){ this.skatExportTopicListService = value; }
	public SkatExportTopicListService getSkatImportTopicListService(){ return this.skatExportTopicListService; }
	
	
}

