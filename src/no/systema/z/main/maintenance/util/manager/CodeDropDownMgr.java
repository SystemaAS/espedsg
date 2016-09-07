package no.systema.z.main.maintenance.util.manager;

import java.util.*;

import no.systema.main.model.SystemaWebUser;
import no.systema.main.service.UrlCgiProxyService;
import no.systema.main.util.JsonDebugger;
import no.systema.tvinn.sad.z.maintenance.main.model.jsonjackson.dbtable.JsonMaintKodtvaContainer;
import no.systema.tvinn.sad.z.maintenance.main.model.jsonjackson.dbtable.JsonMaintKodtvaRecord;
import no.systema.tvinn.sad.z.maintenance.main.service.MaintKodtvaService;
import no.systema.z.main.maintenance.url.store.MaintenanceMainUrlDataStore;
import no.systema.z.main.maintenance.util.MainMaintenanceConstants;


import org.apache.log4j.Logger;

/**
 * 
 * @author oscardelatorre
 * @date Sep 7, 2016
 * 
 */
public class CodeDropDownMgr {
	private static final Logger logger = Logger.getLogger(CodeDropDownMgr.class.getName());
	private static final JsonDebugger jsonDebugger = new JsonDebugger();
	
	/**
	 * 
	 * @param urlCgiProxyService
	 * @param maintKodtvaService
	 * @param model
	 * @param applicationUser
	 */
	public void populateCurrencyCodesHtmlDropDownsSadImport(UrlCgiProxyService urlCgiProxyService, MaintKodtvaService maintKodtvaService, Map model, String applicationUser ){
	
		String BASE_URL = MaintenanceMainUrlDataStore.MAINTENANCE_MAIN_BASE_DROPDOWN_SYFT02R_GET_CURRENCY_LIST_URL;
		StringBuffer urlRequestParams = new StringBuffer();
		urlRequestParams.append("user="+ applicationUser + "&distinct=1");
		
		
		logger.info(Calendar.getInstance().getTime() + " CGI-start timestamp");
    	logger.info("URL: " + jsonDebugger.getBASE_URL_NoHostName(BASE_URL));
    	logger.info("URL PARAMS: " + urlRequestParams);
    	String jsonPayload = urlCgiProxyService.getJsonContent(BASE_URL, urlRequestParams.toString());
    	//extract
    	List<JsonMaintKodtvaRecord> list = new ArrayList();
    	if(jsonPayload!=null){
			//lists
    		JsonMaintKodtvaContainer container = maintKodtvaService.getList(jsonPayload);
	        if(container!=null){
	        	list = (List)container.getList();
	        	for(JsonMaintKodtvaRecord record : list){
	        		//logger.info("A" + record.getKvakod());
	        	}
	        }
    	}
    	model.put(MainMaintenanceConstants.CODE_MGR_CURRENCY_LIST, list); 	
	}
	
}
