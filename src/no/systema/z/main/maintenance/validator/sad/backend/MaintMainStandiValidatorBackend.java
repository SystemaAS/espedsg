	/**
 * 
 */
package no.systema.z.main.maintenance.validator.sad.backend;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;

import no.systema.main.model.SystemaWebUser;
import no.systema.main.service.UrlCgiProxyService;
import no.systema.main.util.JsonDebugger;


import no.systema.z.main.maintenance.service.MaintMainEdiiService;
import no.systema.z.main.maintenance.url.store.MaintenanceMainUrlDataStore;
import no.systema.z.main.maintenance.model.jsonjackson.dbtable.JsonMaintMainEdiiContainer;
import no.systema.z.main.maintenance.model.jsonjackson.dbtable.JsonMaintMainEdiiRecord;
import no.systema.z.main.maintenance.model.jsonjackson.dbtable.sad.JsonMaintMainStandiRecord;


/**
 * 
 *
 * This Validator is not instantiated by the Spring Container at start up. 
 * Instead, it is instantiated by a caller when needed.
 * 
 * It aims to provide a single-point-of-entry for back-end validator behavior.
 *  
 * 
 * @author oscardelatorre
 * @date Sep 12, 2016
 * 
 * 
 */
public class MaintMainStandiValidatorBackend {
	private static final Logger logger = Logger.getLogger(MaintMainStandiValidatorBackend.class.getName());
	private static final JsonDebugger jsonDebugger = new JsonDebugger(1000);
	private MaintMainEdiiService maintMainEdiiService;
	private UrlCgiProxyService urlCgiProxyService;
	private StringBuffer errMsgBackend = new StringBuffer();
	
	
	public StringBuffer getErrMsgBackend(){
		return this.errMsgBackend;
	}

	
	public MaintMainStandiValidatorBackend(UrlCgiProxyService urlCgiProxyService, MaintMainEdiiService maintMainEdiiService){
		this.maintMainEdiiService = maintMainEdiiService;
		this.urlCgiProxyService = urlCgiProxyService;
	}
	
	
	/**
	 * 
	 * @param urlCgiProxyService
	 * @param maintMainEdiiService
	 * @param applicationUser
	 * @param id
	 * @return
	 */
	private boolean exchangeIdExist(String applicationUser, String id ){
		boolean recordExists = false;
		Collection<JsonMaintMainEdiiRecord> list = new ArrayList<JsonMaintMainEdiiRecord>();
		//prepare the access CGI with RPG back-end
		
		String BASE_URL = MaintenanceMainUrlDataStore.MAINTENANCE_MAIN_BASE_SYEDIIR_GET_LIST_URL;
		String urlRequestParamsKeys = "user=" + applicationUser + "&inid=" + id;
		logger.info("URL: " + BASE_URL);
		logger.info("PARAMS: " + urlRequestParamsKeys);
		logger.info(Calendar.getInstance().getTime() +  " CGI-start timestamp");
		String jsonPayload = urlCgiProxyService.getJsonContent(BASE_URL, urlRequestParamsKeys);
		//debugger
		logger.debug(jsonDebugger.debugJsonPayloadWithLog4J(jsonPayload));
		logger.info(Calendar.getInstance().getTime() +  " CGI-end timestamp");
    	if(jsonPayload!=null){
    		JsonMaintMainEdiiContainer container = this.maintMainEdiiService.getList(jsonPayload);
    		if(container!=null){
    			if(container.getList()!=null && container.getList().size()==1){
    				recordExists = true;
    			}
    		}
    	}

    	return recordExists;
	}

	/**
	 * 
	 * @param applicationUser
	 * @param recordToValidate
	 * @return
	 */
	public boolean validate(String applicationUser, JsonMaintMainStandiRecord recordToValidate){
		boolean retval = true;
		if("J".equals(recordToValidate.getSitolk()) ){
			retval = this.validateExchanges(applicationUser, recordToValidate);
		}
		//put more conditions if needed
		//TODO ...
		
		return retval;
	}
	
	/**
	 * 
	 * @param applicationUser
	 * @param recordToValidate
	 * @return
	 */
	private boolean validateExchanges(String applicationUser, JsonMaintMainStandiRecord recordToValidate){
		boolean retval = false;
		
		boolean s0004Exists = exchangeIdExist(applicationUser, recordToValidate.getS0004());
		boolean s0010Exists = exchangeIdExist(applicationUser, recordToValidate.getS0010());
		//valid only if both exchanges exist
		if(s0004Exists && s0010Exists){
			retval = true;
		}else{
			if(!s0004Exists){
				this.errMsgBackend.append(" UtvekslingsId Avd. er fejl\n ");
			}
			if(!s0010Exists){
				this.errMsgBackend.append(" UtvekslingsId Tollvesenet er fejl\n ");
			}
		}
		return retval;
	}

}
