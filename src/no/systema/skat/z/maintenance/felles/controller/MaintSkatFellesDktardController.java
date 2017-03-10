package no.systema.skat.z.maintenance.felles.controller;

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
import no.systema.skat.z.maintenance.main.model.jsonjackson.dbtable.JsonMaintDktardContainer;
import no.systema.skat.z.maintenance.main.model.jsonjackson.dbtable.JsonMaintDktardRecord;
import no.systema.skat.z.maintenance.main.service.MaintDktkdService;
import no.systema.skat.z.maintenance.main.service.MaintDktardService;
import no.systema.skat.z.maintenance.main.service.html.dropdown.SkatMaintMainDropDownListPopulationService;
import no.systema.skat.z.maintenance.main.url.store.MaintenanceUrlDataStore;
import no.systema.skat.z.maintenance.main.util.SkatMaintenanceConstants;
import no.systema.skat.z.maintenance.felles.validator.MaintSkatFellesDktardrValidator;


/**
 *  SKAT Maintenance Felles Dktard Controller (no AS400 GUI exists)
 * 
 * @author oscardelatorre
 * @date Mar 07, 2017
 * 
 */

@Controller
@SessionAttributes(AppConstants.SYSTEMA_WEB_USER_KEY)
@Scope("session")
public class MaintSkatFellesDktardController {
	
	private static final JsonDebugger jsonDebugger = new JsonDebugger();
	private static final Logger logger = Logger.getLogger(MaintSkatFellesDktardController.class.getName());
	private ModelAndView loginView = new ModelAndView("login");
	private ApplicationContext context;
	private LoginValidator loginValidator = new LoginValidator();
	private UrlRequestParameterMapper urlRequestParameterMapper = new UrlRequestParameterMapper();
	private String SKAT_IMPORT_SUPPL_ENHETER_KODE = "022"; //We use the ones in Skat-Import
	
	/**
	 * 
	 * @param user
	 * @param result
	 * @param request
	 * @return
	 */
	@RequestMapping(value="skatmaintenancefelles_dktard.do", method={RequestMethod.GET, RequestMethod.POST})
	public ModelAndView doSadMaintImportList(HttpSession session, HttpServletRequest request){
		ModelAndView successView = new ModelAndView("skatmaintenancefelles_dktard");
		SystemaWebUser appUser = (SystemaWebUser)session.getAttribute(AppConstants.SYSTEMA_WEB_USER_KEY);
		//SearchFilterSadExportTopicList searchFilter = new SearchFilterSadExportTopicList();
		String dbTable = request.getParameter("id");
		String id = request.getParameter("searchDktard01");
		String startDato = request.getParameter("searchDktard02");
		
		
		Map model = new HashMap();
		if(appUser==null){
			return this.loginView;
		}else{
			//get table
	    	List<JsonMaintDktardRecord> list = new ArrayList();
	    	list = this.fetchList(appUser.getUser(), id, startDato);
	    	//drop downs
	    	List codeList022 = this.fetchListKoder(appUser.getUser(), this.SKAT_IMPORT_SUPPL_ENHETER_KODE);
	    	List codeListToldsatstype = this.skatMaintMainDropDownListPopulationService.getToldsatstypeList();
	    	model.put("codeList022", codeList022);
	    	model.put("codeListToldsatstype", codeListToldsatstype);
	    	
	    	
	    	//set domain objets
	    	model.put("dbTable", dbTable);
	    	model.put("searchDktard01", id);
	    	model.put("searchDktard02", startDato);
	    	
	    	model.put(SkatMaintenanceConstants.DOMAIN_LIST, list);
	    	successView.addObject(SkatMaintenanceConstants.DOMAIN_MODEL , model);
			
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
	@RequestMapping(value="skatmaintenancefelles_dktard_edit.do", method={RequestMethod.GET, RequestMethod.POST})
	public ModelAndView doSadMaintImportEdit(@ModelAttribute ("record") JsonMaintDktardRecord recordToValidate, BindingResult bindingResult, HttpSession session, HttpServletRequest request){
		ModelAndView successView = new ModelAndView("skatmaintenancefelles_dktard");
		SystemaWebUser appUser = (SystemaWebUser)session.getAttribute(AppConstants.SYSTEMA_WEB_USER_KEY);
		
		String dbTable = request.getParameter("id");
		String updateId = request.getParameter("updateId");
		String action = request.getParameter("action");
		String id = request.getParameter("searchDktard01");
		String startDato = request.getParameter("searchDktard02");
		
		Map model = new HashMap();
		if(appUser==null){
			return this.loginView;
		}else{
			//Move on
			MaintSkatFellesDktardrValidator validator = new MaintSkatFellesDktardrValidator();
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
				//recordToValidate.setDk(null);
			}
			List<JsonMaintDktardRecord> list = this.fetchList(appUser.getUser(), id, startDato);
			//drop downs
	    	List codeList022 = this.fetchListKoder(appUser.getUser(), this.SKAT_IMPORT_SUPPL_ENHETER_KODE);
	    	List codeListToldsatstype = this.skatMaintMainDropDownListPopulationService.getToldsatstypeList();
	    	model.put("codeList022", codeList022);
	    	model.put("codeListToldsatstype", codeListToldsatstype);
	    	
	    	//set domain objets
	    	model.put("dbTable", dbTable);
	    	model.put(SkatMaintenanceConstants.DOMAIN_LIST, list);
			successView.addObject(SkatMaintenanceConstants.DOMAIN_MODEL , model);
			
	    	return successView;
		}
	}
	/**
	 * 
	 * @param applicationUser
	 * @param id
	 * @param startDato
	 * @return
	 */
	private List<JsonMaintDktardRecord> fetchList(String applicationUser, String id, String startDato){
		
		String BASE_URL = MaintenanceUrlDataStore.MAINTENANCE_BASE_DKTARDR_GET_LIST_URL;
		StringBuffer urlRequestParams = new StringBuffer();
		urlRequestParams.append("user="+ applicationUser);
		urlRequestParams.append("&dktard01="+ id);
		//other params
		if(startDato!=null && !"".equals(startDato)){
			urlRequestParams.append("&dktard02=" + startDato);
		}
		
		logger.info(Calendar.getInstance().getTime() + " CGI-start timestamp");
    	logger.info("URL: " + jsonDebugger.getBASE_URL_NoHostName(BASE_URL));
    	logger.info("URL PARAMS: " + urlRequestParams);
    	String jsonPayload = this.urlCgiProxyService.getJsonContent(BASE_URL, urlRequestParams.toString());
    	//extract
    	List<JsonMaintDktardRecord> list = new ArrayList();
    	if(jsonPayload!=null){
			//lists
    		JsonMaintDktardContainer container = this.maintDktardService.getList(jsonPayload);
	        if(container!=null){
	        	list = (List)container.getDtoList();
	        	for(JsonMaintDktardRecord record : list){
	        		//logger.info("LEVENR:" + record.getLevenr());
	        	}
	        }
    	}
    	return list;
    	
	}
	
	
	/**
	 * 
	 * @param applicationUser
	 * @param dkkd_typ
	 * @return
	 */
	private List<JsonMaintDktkdRecord> fetchListKoder(String applicationUser, String dkkd_typ){
		
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
	 * UPDATE/CREATE/DELETE
	 * 
	 * @param applicationUser
	 * @param record
	 * @param mode
	 * @return
	 */
	private int updateRecord(String applicationUser, JsonMaintDktardRecord record, String mode, StringBuffer errMsg){
		int retval = 0;
		
		String BASE_URL = MaintenanceUrlDataStore.MAINTENANCE_BASE_DKTARDR_DML_UPDATE_URL;
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
    		JsonMaintDktardContainer container = this.maintDktardService.doUpdate(jsonPayload);
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
	
	
	@Qualifier ("maintDktardService")
	private MaintDktardService maintDktardService;
	@Autowired
	@Required
	public void setMaintDktardService (MaintDktardService value){ this.maintDktardService = value; }
	public MaintDktardService getMaintDktardService(){ return this.maintDktardService; }
	
	
	@Qualifier ("maintDktkdService")
	private MaintDktkdService maintDktkdService;
	@Autowired
	@Required
	public void setMaintDktkdService (MaintDktkdService value){ this.maintDktkdService = value; }
	public MaintDktkdService getMaintDktkdService(){ return this.maintDktkdService; }
	
	
	@Qualifier ("skatMaintMainDropDownListPopulationService")
	private SkatMaintMainDropDownListPopulationService skatMaintMainDropDownListPopulationService;
	@Autowired
	@Required
	public void setSkatMaintMainDropDownListPopulationService (SkatMaintMainDropDownListPopulationService value){ this.skatMaintMainDropDownListPopulationService = value; }
	public SkatMaintMainDropDownListPopulationService getSkatMaintMainDropDownListPopulationService(){ return this.skatMaintMainDropDownListPopulationService; }
	
	
	
}

