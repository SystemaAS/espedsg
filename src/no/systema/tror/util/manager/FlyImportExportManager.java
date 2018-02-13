package no.systema.tror.util.manager;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import org.apache.log4j.Logger;

import no.systema.jservices.common.dao.TrackfDao;
import no.systema.jservices.common.json.JsonDtoContainer;
import no.systema.jservices.common.json.JsonReader;
import no.systema.main.model.SystemaWebUser;
import no.systema.main.service.UrlCgiProxyService;
import no.systema.main.util.JsonDebugger;
import no.systema.tror.mapper.url.request.UrlRequestParameterMapper;
import no.systema.tror.url.store.TrorUrlDataStore;
import no.systema.tror.util.TrorConstants;
import no.systema.tvinn.sad.z.maintenance.nctsexport.model.jsonjackson.dbtable.JsonMaintNctsTrkodfContainer;
import no.systema.tvinn.sad.z.maintenance.nctsexport.model.jsonjackson.dbtable.JsonMaintNctsTrkodfRecord;
import no.systema.tvinn.sad.z.maintenance.nctsexport.service.MaintNctsExportTrkodfService;
import no.systema.tvinn.sad.z.maintenance.nctsexport.url.store.TvinnNctsMaintenanceExportUrlDataStore;

public class FlyImportExportManager {
	
	private static final JsonDebugger jsonDebugger = new JsonDebugger(1500);
	private static Logger logger = Logger.getLogger(FlyImportExportManager.class.getName());
	/**
	 * UnitOfMeasure
	 * 
	 * @param appUser
	 * @param uom
	 * @return
	 */
	public boolean findUnitOfMeasure (UrlCgiProxyService urlCgiProxyService, MaintNctsExportTrkodfService  maintNctsExportTrkodfService, SystemaWebUser appUser, String uom){
		boolean retval = false;
		String BASE_URL = TvinnNctsMaintenanceExportUrlDataStore.TVINN_NCTS_MAINTENANCE_EXPORT_BASE_TR001R_GET_LIST_URL;
		String urlRequestParamsKeys = "user=" + appUser.getUser() + "&tkunik=017";
		logger.info("URL: " + BASE_URL);
		logger.info("PARAMS: " + urlRequestParamsKeys);
		logger.info(Calendar.getInstance().getTime() +  " CGI-start timestamp");
		String jsonPayload = urlCgiProxyService.getJsonContent(BASE_URL, urlRequestParamsKeys);
		//Debug -->
    	logger.debug(jsonDebugger.debugJsonPayloadWithLog4J(jsonPayload));
		logger.info(Calendar.getInstance().getTime() +  " CGI-end timestamp");
    
		if(jsonPayload!=null){
			JsonMaintNctsTrkodfContainer container = null;
			try{
				container = maintNctsExportTrkodfService.getList(jsonPayload);
			}catch(Exception e){
				e.printStackTrace();
			}
			//go on
    		if(container!=null){
    			List<JsonMaintNctsTrkodfRecord> list = new ArrayList<JsonMaintNctsTrkodfRecord>();
    			for(JsonMaintNctsTrkodfRecord  record : container.getList()){
    				if(uom.equals(record.getTkkode())){
    					retval = true;
    				}
    			}
    		}
		}
		return retval;
	}
	/**
	 * 
	 * @param appUser
	 * @param record
	 * @param mode
	 * @param errMsg
	 * @return
	 */
	public int updateRecord(SystemaWebUser appUser, TrackfDao record, String mode, StringBuffer errMsg, UrlCgiProxyService urlCgiProxyService, UrlRequestParameterMapper urlRequestParameterMapper  ) {
		//Locale locale = VkundControllerUtil.getLocale(appUser.getUsrLang(), "svew");
		int retval = 0;
		JsonReader<JsonDtoContainer<TrackfDao>> jsonReader = new JsonReader<JsonDtoContainer<TrackfDao>>();
		jsonReader.set(new JsonDtoContainer<TrackfDao>());
		String BASE_URL = TrorUrlDataStore.TROR_BASE_UPDATE_TRACK_AND_TRACE_URL;
		String urlRequestParamsKeys = "user=" + appUser.getUser() + "&mode=" + mode + "&lang=" + appUser.getUsrLang();
		String urlRequestParams = urlRequestParameterMapper.getUrlParameterValidString(record);
		urlRequestParams = urlRequestParamsKeys + urlRequestParams;

		logger.info(Calendar.getInstance().getTime() + " CGI-start timestamp");
		logger.info("URL: " + jsonDebugger.getBASE_URL_NoHostName(BASE_URL));
		logger.info("URL PARAMS: " + urlRequestParams);
		String jsonPayload = urlCgiProxyService.getJsonContent(BASE_URL, urlRequestParams);
		if (jsonPayload != null) {
			JsonDtoContainer<TrackfDao> container = (JsonDtoContainer<TrackfDao>) jsonReader.get(jsonPayload);
			if (container != null) {
				if (container.getErrMsg() != null && !"".equals(container.getErrMsg())) {
					errMsg.append(container.getErrMsg());
					retval = TrorConstants.ERROR_CODE;
				}
			}			
		}

		return retval;
	}	
}
