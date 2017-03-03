package no.systema.skat.z.maintenance.skatimport.controller;

import java.util.*;

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
import javax.servlet.http.HttpSession;

import org.springframework.web.bind.ServletRequestDataBinder;
import org.springframework.web.bind.WebDataBinder;

//application imports
import no.systema.main.context.TdsAppContext;
import no.systema.main.service.UrlCgiProxyService;
import no.systema.main.validator.LoginValidator;
import no.systema.main.util.AppConstants;
import no.systema.main.util.JsonDebugger;
import no.systema.main.model.SystemaWebUser;

import no.systema.skat.z.maintenance.main.mapper.url.request.UrlRequestParameterMapper;
import no.systema.skat.z.maintenance.main.model.jsonjackson.dbtable.JsonMaintDktkdContainer;
import no.systema.skat.z.maintenance.main.model.jsonjackson.dbtable.JsonMaintDktkdRecord;
import no.systema.skat.z.maintenance.main.service.MaintDktkdService;
import no.systema.skat.z.maintenance.main.util.SkatMaintenanceConstants;
import no.systema.skat.z.maintenance.main.validator.MaintSkatMainDkg210dValidator;
import no.systema.skat.z.maintenance.main.url.store.MaintenanceUrlDataStore;


/**
 * SKAT Maintenance Import Topic Controller 
 * 
 * @author oscardelatorre
 * @date Mar 03, 2017
 * 
 */

@Controller
@SessionAttributes(AppConstants.SYSTEMA_WEB_USER_KEY)
@Scope("session")
public class MaintSkatImportKoderDkg210dController {
	private static final JsonDebugger jsonDebugger = new JsonDebugger();
	private static final Logger logger = Logger.getLogger(MaintSkatImportKoderDkg210dController.class.getName());
	private ModelAndView loginView = new ModelAndView("login");
	private ApplicationContext context;
	private LoginValidator loginValidator = new LoginValidator();
	private UrlRequestParameterMapper urlRequestParameterMapper = new UrlRequestParameterMapper();
	
	/**
	 * 
	 * @param user
	 * @param result
	 * @param request
	 * @return
	 */
	@RequestMapping(value="skatmaintenanceimport_dkg210d.do", method=RequestMethod.GET)
	public ModelAndView doSkatImportList(HttpSession session, HttpServletRequest request){
		ModelAndView successView = new ModelAndView("skatmaintenanceimport_dkg210d");
		SystemaWebUser appUser = (SystemaWebUser)session.getAttribute(AppConstants.SYSTEMA_WEB_USER_KEY);
		//SearchFilterSadExportTopicList searchFilter = new SearchFilterSadExportTopicList();
		String dbTable = request.getParameter("id");
		String dkkd_typ = request.getParameter("dkkd_typ");
		String legend = request.getParameter("legend");
		
		
		Map model = new HashMap();
		if(appUser==null){
			return this.loginView;
		}else{
			appUser.setActiveMenu(SystemaWebUser.ACTIVE_MENU_SKAT_MAINTENANCE_IMPORT);
			session.setAttribute(SkatMaintenanceConstants.ACTIVE_URL_RPG_SKAT_MAINTENANCE, SkatMaintenanceConstants.ACTIVE_URL_RPG_INITVALUE); 
		
			//lists
			List list = this.fetchList(appUser.getUser(), dkkd_typ);
			//set domain objects
			model.put("dbTable", dbTable);
			model.put("legend", legend);
			model.put("dkkd_typ", dkkd_typ);
			model.put("list", list);
			successView.addObject(SkatMaintenanceConstants.DOMAIN_MODEL , model);
			//successView.addObject(TvinnSadConstants.DOMAIN_LIST,new ArrayList());
			
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
	@RequestMapping(value="skatmaintenanceimport_dkg210d_edit.do", method={RequestMethod.GET, RequestMethod.POST})
	public ModelAndView doSadMaintImportEdit(@ModelAttribute ("record") JsonMaintDktkdRecord recordToValidate, BindingResult bindingResult, HttpSession session, HttpServletRequest request){
		ModelAndView successView = new ModelAndView("skatmaintenanceimport_dkg210d");
		SystemaWebUser appUser = (SystemaWebUser)session.getAttribute(AppConstants.SYSTEMA_WEB_USER_KEY);
		
		String dbTable = request.getParameter("id");
		String updateId = request.getParameter("updateId");
		String action = request.getParameter("action");
		
		Map model = new HashMap();
		if(appUser==null){
			return this.loginView;
		}else{
			//Move on
			MaintSkatMainDkg210dValidator validator = new MaintSkatMainDkg210dValidator();
			if(SkatMaintenanceConstants.ACTION_DELETE.equals(action)){
				validator.validateDelete(recordToValidate, bindingResult);
			}else{
				validator.validate(recordToValidate, bindingResult);
			}
			if(bindingResult.hasErrors()){
				//ERRORS
				logger.info("[ERROR Validation] Record does not validate)");
				model.put("dbTable", dbTable);
				if(updateId!=null && !"".equals(updateId)){
					//meaning bounced in an Update and not a Create new
					model.put("updateId", updateId);
				}
				model.put(SkatMaintenanceConstants.DOMAIN_RECORD, recordToValidate);
			}else{
				
				//------------
				//UPDATE table
				//------------
				StringBuffer errMsg = new StringBuffer();
				int dmlRetval = 0;
				//UPDATE
				if (SkatMaintenanceConstants.ACTION_UPDATE.equals(action) ){
					if(updateId!=null && !"".equals(updateId)){
						//update
						logger.info(SkatMaintenanceConstants.ACTION_UPDATE);
						dmlRetval = this.updateRecord(appUser.getUser(), recordToValidate, SkatMaintenanceConstants.MODE_UPDATE, errMsg);
						
					//CREATE
					}else{
						//create new
						logger.info(SkatMaintenanceConstants.ACTION_CREATE);
						dmlRetval = this.updateRecord(appUser.getUser(), recordToValidate, SkatMaintenanceConstants.MODE_ADD, errMsg);
					}
				}else if(SkatMaintenanceConstants.ACTION_DELETE.equals(action) ){
					//delete
					logger.info(SkatMaintenanceConstants.ACTION_DELETE);
					dmlRetval = this.updateRecord(appUser.getUser(), recordToValidate, SkatMaintenanceConstants.MODE_DELETE, errMsg);
				}
				//check for Update errors
				if( dmlRetval < 0){
					logger.info("[ERROR Validation] Record does not validate)");
					model.put("dbTable", dbTable);
					model.put(SkatMaintenanceConstants.ASPECT_ERROR_MESSAGE, errMsg.toString());
					model.put(SkatMaintenanceConstants.DOMAIN_RECORD, recordToValidate);
				}
				
			}
			//------------
			//FETCH table
			//------------
			if(SkatMaintenanceConstants.ACTION_DELETE.equals(action) || SkatMaintenanceConstants.ACTION_UPDATE.equals(action) ){
				//this in order to present the complete list to the end user after a DML-operation
				//recordToValidate.setDkvk_kd(null);
			}
			List<JsonMaintDktkdRecord> list = this.fetchList(appUser.getUser(), recordToValidate.getDkkd_typ());
	    	//set domain objets
	    	model.put("dbTable", dbTable);
	    	model.put("dkkd_typ", recordToValidate.getDkkd_typ());
	    	model.put(SkatMaintenanceConstants.DOMAIN_LIST, list);
			successView.addObject(SkatMaintenanceConstants.DOMAIN_MODEL , model);
			
	    	return successView;
		}
	}
	
	/**
	 * 
	 * @param applicationUser
	 * @param id
	 * @return
	 */
	private List<JsonMaintDktkdRecord> fetchList(String applicationUser, String dkkd_typ){
		
		String BASE_URL = MaintenanceUrlDataStore.MAINTENANCE_BASE_DKG210R_GET_LIST_URL;
		StringBuffer urlRequestParams = new StringBuffer();
		//mandatory params
		urlRequestParams.append("user="+ applicationUser);
		urlRequestParams.append("&dkkd_typ=" + dkkd_typ);
		
		
		logger.info(Calendar.getInstance().getTime() + " CGI-start timestamp");
    	logger.info("URL: " + jsonDebugger.getBASE_URL_NoHostName(BASE_URL));
    	logger.info("URL PARAMS: " + urlRequestParams);
    	String jsonPayload = this.urlCgiProxyService.getJsonContent(BASE_URL, urlRequestParams.toString());
    	//extract
    	List<JsonMaintDktkdRecord> list = new ArrayList();
    	if(jsonPayload!=null){
			//lists
    		JsonMaintDktkdContainer container = this.maintDktkdService.getList(jsonPayload);
	        if(container!=null){
	        	list = (List)container.getList();
	        	for(JsonMaintDktkdRecord record : list){
	        		//logger.info("TGGNR:" + record.getTggnr());
	        	}
	        }
    	}
    	return list;
    	
	}
	
	/**
	 * 
	 * @param applicationUser
	 * @param record
	 * @param mode
	 * @param errMsg
	 * @return
	 */
	private int updateRecord(String applicationUser, JsonMaintDktkdRecord record, String mode, StringBuffer errMsg){
		int retval = 0;
		
		String BASE_URL = MaintenanceUrlDataStore.MAINTENANCE_BASE_DKG210R_DML_UPDATE_URL;
		String urlRequestParamsKeys = "user=" + applicationUser + "&mode=" + mode;
		String urlRequestParams = this.urlRequestParameterMapper.getUrlParameterValidString((record));
		//put the final valid param. string
		urlRequestParams = urlRequestParamsKeys + urlRequestParams;
		
		logger.info(Calendar.getInstance().getTime() + " CGI-start timestamp");
    	logger.info("URL: " + jsonDebugger.getBASE_URL_NoHostName(BASE_URL));
    	logger.info("URL PARAMS: " + urlRequestParams);
    	String jsonPayload = this.urlCgiProxyService.getJsonContent(BASE_URL, urlRequestParams);
    	
    	//extract
    	if(jsonPayload!=null){
			//lists
    		JsonMaintDktkdContainer container = this.maintDktkdService.doUpdate(jsonPayload);
	        if(container!=null){
	        	if(container.getErrMsg()!=null && !"".equals(container.getErrMsg())){
	        		if(container.getErrMsg().toUpperCase().startsWith("ERROR")){
	        			errMsg.append(container.getErrMsg());
	        			retval = SkatMaintenanceConstants.ERROR_CODE;
	        		}
	        	}
	        }
    	}
    	return retval;
	}
	
	//SERVICES
	@Qualifier ("urlCgiProxyService")
	private UrlCgiProxyService urlCgiProxyService;
	@Autowired
	@Required
	public void setUrlCgiProxyService (UrlCgiProxyService value){ this.urlCgiProxyService = value; }
	public UrlCgiProxyService getUrlCgiProxyService(){ return this.urlCgiProxyService; }
	
	@Qualifier ("maintDktkdService")
	private MaintDktkdService maintDktkdService;
	@Autowired
	@Required
	public void setMaintDktkdService (MaintDktkdService value){ this.maintDktkdService = value; }
	public MaintDktkdService getMaintDktkdService(){ return this.maintDktkdService; }
	
	

}

