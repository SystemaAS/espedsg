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
import no.systema.skat.z.maintenance.main.util.SkatMaintenanceConstants;
import no.systema.skat.z.maintenance.skatimport.model.jsonjackson.dbtable.JsonMaintDktseContainer;
import no.systema.skat.z.maintenance.skatimport.model.jsonjackson.dbtable.JsonMaintDktseRecord;
import no.systema.skat.z.maintenance.skatimport.service.MaintSkatImportDktseService;
import no.systema.skat.z.maintenance.skatimport.url.store.MaintenanceSkatImportUrlDataStore;
import no.systema.skat.z.maintenance.skatimport.validator.MaintSkatImportDkt058rValidator;
import no.systema.z.main.maintenance.model.jsonjackson.dbtable.JsonMaintMainCundfContainer;
import no.systema.z.main.maintenance.model.jsonjackson.dbtable.JsonMaintMainCundfRecord;
import no.systema.z.main.maintenance.service.MaintMainCundfService;
import no.systema.z.main.maintenance.url.store.MaintenanceMainUrlDataStore;

/**
 * SKAT Maintenance Import Topic Controller 
 * 
 * @author oscardelatorre
 * @date Mar 06, 2017
 * 
 */

@Controller
@SessionAttributes(AppConstants.SYSTEMA_WEB_USER_KEY)
@Scope("session")
public class MaintSkatImportKoderDkt058rController {
	private static final JsonDebugger jsonDebugger = new JsonDebugger();
	private static final Logger logger = Logger.getLogger(MaintSkatImportKoderDkt058rController.class.getName());
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
	@RequestMapping(value="skatmaintenanceimport_dkt058r.do", method={RequestMethod.GET, RequestMethod.POST})
	public ModelAndView doSkatImportList(HttpSession session, HttpServletRequest request){
		ModelAndView successView = new ModelAndView("skatmaintenanceimport_dkt058r");
		SystemaWebUser appUser = (SystemaWebUser)session.getAttribute(AppConstants.SYSTEMA_WEB_USER_KEY);
		String dbTable = request.getParameter("id");
		String dkse_knr = request.getParameter("search_dkse_knr");
		String dkse_331 = request.getParameter("search_dkse_331");
		
		Map model = new HashMap();
		if(appUser==null){
			return this.loginView;
		}else{
			appUser.setActiveMenu(SystemaWebUser.ACTIVE_MENU_SKAT_MAINTENANCE_IMPORT);
			session.setAttribute(SkatMaintenanceConstants.ACTIVE_URL_RPG_SKAT_MAINTENANCE, SkatMaintenanceConstants.ACTIVE_URL_RPG_INITVALUE); 
		
			//lists
			List list = this.fetchList(appUser.getUser(), dkse_knr, dkse_331);
			//set domain objects
			model.put("dbTable", dbTable);
			model.put("list", list);
			model.put("search_dkse_knr", dkse_knr);
			model.put("search_dkse_331", dkse_331);
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
	@RequestMapping(value="skatmaintenanceimport_dkt058r_edit.do", method={RequestMethod.GET, RequestMethod.POST})
	public ModelAndView doSadMaintImportEdit(@ModelAttribute ("record") JsonMaintDktseRecord recordToValidate, BindingResult bindingResult, HttpSession session, HttpServletRequest request){
		ModelAndView successView = new ModelAndView("skatmaintenanceimport_dkt058r");
		SystemaWebUser appUser = (SystemaWebUser)session.getAttribute(AppConstants.SYSTEMA_WEB_USER_KEY);
		
		String dbTable = request.getParameter("id");
		String updateId = request.getParameter("updateId");
		String action = request.getParameter("action");
		
		Map model = new HashMap();
		if(appUser==null){
			return this.loginView;
		}else{
			//Move on
			MaintSkatImportDkt058rValidator validator = new MaintSkatImportDkt058rValidator();
			if(SkatMaintenanceConstants.ACTION_DELETE.equals(action)){
				validator.validateDelete(recordToValidate, bindingResult);
			}else{
				recordToValidate.setValidCustomerNumber(this.customerExists(appUser.getUser(), recordToValidate));
				validator.validate(recordToValidate, bindingResult);
			}
			if(bindingResult.hasErrors()){
				//ERRORS
				logger.info("[ERROR Validation] Record does not validate)");
				model.put("dbTable", dbTable);
				//if(updateId!=null && !"".equals(updateId)){
					//meaning bounced in an Update and not a Create new
					//model.put("updateId", updateId);
				//}
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
						//N/A in this UC --> dmlRetval = this.updateRecord(appUser.getUser(), recordToValidate, SkatMaintenanceConstants.MODE_UPDATE, errMsg);
						
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
			List<JsonMaintDktseRecord> list = this.fetchList(appUser.getUser(), null, null);
	    	//set domain objets
	    	model.put("dbTable", dbTable);
	    	//model.put("dkkd_typ", recordToValidate.getDkkd_typ());
	    	model.put(SkatMaintenanceConstants.DOMAIN_LIST, list);
			successView.addObject(SkatMaintenanceConstants.DOMAIN_MODEL , model);
			
	    	return successView;
		}
	}
	
	/**
	 * 
	 * @param applicationUser
	 * @param dkse_knr
	 * @param dkse_331
	 * @return
	 */
	private List<JsonMaintDktseRecord> fetchList(String applicationUser, String dkse_knr, String dkse_331){
		
		String BASE_URL = MaintenanceSkatImportUrlDataStore.MAINTENANCE_BASE_DKT058R_GET_LIST_URL;
		StringBuffer urlRequestParams = new StringBuffer();
		//mandatory params
		urlRequestParams.append("user="+ applicationUser);
		if(dkse_knr != null && !"".equals(dkse_knr)) { urlRequestParams.append("&dkse_knr=" + dkse_knr);  }
		if(dkse_331 != null && !"".equals(dkse_331)) { urlRequestParams.append("&dkse_331=" + dkse_331);  }
		//urlRequestParams.append("&dkkd_typ=" + dkkd_typ);
		
		
		logger.info(Calendar.getInstance().getTime() + " CGI-start timestamp");
    	logger.info("URL: " + jsonDebugger.getBASE_URL_NoHostName(BASE_URL));
    	logger.info("URL PARAMS: " + urlRequestParams);
    	String jsonPayload = this.urlCgiProxyService.getJsonContent(BASE_URL, urlRequestParams.toString());
    	//extract
    	List<JsonMaintDktseRecord> list = new ArrayList();
    	if(jsonPayload!=null){
			//lists
    		JsonMaintDktseContainer container = this.maintSkatImportDktseService.getList(jsonPayload);
	        if(container!=null){
	        	list = (List)container.getDtoList();
	        	for(JsonMaintDktseRecord record : list){
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
	private int updateRecord(String applicationUser, JsonMaintDktseRecord record, String mode, StringBuffer errMsg){
		int retval = 0;
		
		String BASE_URL = MaintenanceSkatImportUrlDataStore.MAINTENANCE_BASE_DKT058R_DML_UPDATE_URL;
		String urlRequestParamsKeys = "user=" + applicationUser + "&mode=" + mode;
		String urlRequestParams = this.urlRequestParameterMapper.getUrlParameterValidString((record));
		//put the final valid param. string
		urlRequestParams = urlRequestParamsKeys + urlRequestParams;
		
		logger.info(Calendar.getInstance().getTime() + " CGI-start timestamp");
    	logger.info("URL: " + jsonDebugger.getBASE_URL_NoHostName(BASE_URL));
    	logger.info("URL PARAMS: " + urlRequestParams);
    	
    	String jsonPayload = this.urlCgiProxyService.getJsonContent(BASE_URL, urlRequestParams);
    	logger.info(jsonPayload);
    	//extract
    	if(jsonPayload!=null){
    		//lists
    		JsonMaintDktseContainer container = this.maintSkatImportDktseService.doUpdate(jsonPayload);
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
	/**
	 * 
	 * @param applicationUser
	 * @param record
	 * @return
	 */
	public boolean customerExists(String applicationUser, JsonMaintDktseRecord record){
		boolean retval = true;
		String DEFAULT_FIRMA_CUSTOMER_NR = "0";
		Collection<JsonMaintMainCundfRecord> list = new ArrayList<JsonMaintMainCundfRecord>();

		if( record.getDkse_knr()!=null && !"".equals(record.getDkse_knr()) ){
			if(!record.getDkse_knr().equals(DEFAULT_FIRMA_CUSTOMER_NR)){
				String BASE_URL = MaintenanceMainUrlDataStore.MAINTENANCE_MAIN_BASE_SYCUNDFR_GET_LIST_URL;
				String urlRequestParamsKeys = "user=" + applicationUser + "&kundnr=" + record.getDkse_knr();
				//logger.info("URL: " + BASE_URL);
				//logger.info("PARAMS: " + urlRequestParamsKeys);
				//logger.info(Calendar.getInstance().getTime() +  " CGI-start timestamp");
				String jsonPayload = this.urlCgiProxyService.getJsonContent(BASE_URL, urlRequestParamsKeys);
				//debugger
				//logger.debug(jsonDebugger.debugJsonPayloadWithLog4J(jsonPayload));
				//logger.info(Calendar.getInstance().getTime() +  " CGI-end timestamp");
		    	if(jsonPayload!=null){
		    		jsonPayload = jsonPayload.replaceFirst("Customerlist", "customerlist");
		    		JsonMaintMainCundfContainer container = this.maintMainCundfService.getList(jsonPayload);
		    		if(container!=null){
		    			if(container.getList()!=null && container.getList().size()>0){
		    				//OK
		    			}else{
		    				retval = false;
		    			}
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
	
	
	@Qualifier ("maintSkatImportDktseService")
	private MaintSkatImportDktseService maintSkatImportDktseService;
	@Autowired
	@Required
	public void setMaintSkatImportDktseService (MaintSkatImportDktseService value){ this.maintSkatImportDktseService = value; }
	public MaintSkatImportDktseService getMaintSkatImportDktseService(){ return this.maintSkatImportDktseService; }
	
	
	@Qualifier ("maintMainCundfService")
	private MaintMainCundfService maintMainCundfService;
	@Autowired
	@Required
	public void setMaintMainCundfService (MaintMainCundfService value){ this.maintMainCundfService = value; }
	public MaintMainCundfService getMaintMainCundfService(){ return this.maintMainCundfService; }
	
	

}

