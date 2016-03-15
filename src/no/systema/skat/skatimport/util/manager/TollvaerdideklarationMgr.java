/**
 * 
 */
package no.systema.skat.skatimport.util.manager;

import java.util.Calendar;

import no.systema.skat.skatimport.model.jsonjackson.topic.JsonSkatImportSpecificTopicRecord;
import no.systema.skat.skatimport.service.SkatImportSpecificTopicItemService;
import no.systema.skat.skatimport.url.store.SkatImportUrlDataStore;
import no.systema.skat.skatimport.model.jsonjackson.topic.items.JsonSkatImportSpecificTopicItemAvgifterContainer;
import no.systema.skat.skatimport.model.jsonjackson.topic.items.JsonSkatImportSpecificTopicItemAvgifterRecord;
import no.systema.skat.skatimport.model.jsonjackson.topic.items.JsonSkatImportSpecificTopicItemRecord;
import no.systema.skat.skatimport.model.jsonjackson.topic.items.JsonSkatImportSpecificTopicItemToldvaerdiContainer;
import no.systema.skat.skatimport.model.jsonjackson.topic.items.JsonSkatImportSpecificTopicItemToldvaerdiTransportContainer;
import no.systema.skat.skatimport.model.jsonjackson.topic.items.JsonSkatImportSpecificTopicItemToldvaerdiRecord;

import no.systema.skat.util.SkatConstants;

import java.text.DecimalFormat;
import no.systema.main.model.SystemaWebUser;
import no.systema.main.service.UrlCgiProxyService;

import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;


/**
 * This class calculates some Tollværdideklaration values
 * 
 * @author oscardelatorre
 * @date Mar 07, 2014
 * 
 */
public class TollvaerdideklarationMgr {
	private static final Logger logger = Logger.getLogger(TollvaerdideklarationMgr.class.getName());
	private SkatImportSpecificTopicItemService skatImportSpecificTopicItemService;
	private UrlCgiProxyService urlCgiProxyService;
	
	/**
	 * Constructor
	 * @param itemService
	 * @param urlProxyService
	 */
	public TollvaerdideklarationMgr(SkatImportSpecificTopicItemService itemService, UrlCgiProxyService urlProxyService){
		this.skatImportSpecificTopicItemService = itemService;
		this.urlCgiProxyService = urlProxyService;
		
	}
	
	/**
	 * 
	 * @param session
	 * @param record
	 * @param appUser
	 * @param headerRecord
	 * 
	 * @return
	 * 
	 */
	public void calculateToldVaerdiItemLinesTotalAmount(JsonSkatImportSpecificTopicItemRecord record, JsonSkatImportSpecificTopicRecord headerRecord ){
		DecimalFormat decimalFormatter = new DecimalFormat("#.00");
		//Default values
		String exchangeRateStr = headerRecord.getDkih_221b();
		double exchangeRate = 0.00D;
		String itemAmount_42Str = record.getDkiv_42();
		double itemAmount_42 = 0.00D;
		String factorStr = headerRecord.getDkih_221c();
		double factor = 0.00D;
		
		String dkiv_t12Str = null;
		double dkiv_t12 = 0.00D;
		
		if(itemAmount_42Str!=null && !"".equals(itemAmount_42Str)){
			try{
				itemAmount_42Str = itemAmount_42Str.replace(",", ".");
				itemAmount_42 = Double.parseDouble(itemAmount_42Str);
				//
				exchangeRateStr = exchangeRateStr.replace(",", ".");
				exchangeRate = Double.parseDouble(exchangeRateStr);
				//
				factorStr = factorStr.replace(",", ".");
				factor = Double.parseDouble(factorStr);
				//t12
				dkiv_t12 = (itemAmount_42 * exchangeRate)/factor;
				dkiv_t12 = Double.parseDouble(decimalFormatter.format(dkiv_t12));
				dkiv_t12Str = String.valueOf(dkiv_t12).replace(".", ",");
			}catch(Exception e){
				//Nothing
			}
			//Now fill in the results
			record.setDkiv_t12(dkiv_t12Str); 
			record.setDkiv_t24(record.getDkiv_46()); 
		}
		
	}
	
	
	/**
	 * 
	 * @param appUser
	 * @param urlRequestParams
	 * @return
	 */
	public JsonSkatImportSpecificTopicItemToldvaerdiRecord calculateToldvaerdiSumsOnItem(String appUser, String urlRequestParams){
		JsonSkatImportSpecificTopicItemToldvaerdiRecord retval = null;
		//----------------------------
        //Now calculate the SUMS
        //----------------------------
        String BASE_URL_TOLDVAERDI_SUMS = SkatImportUrlDataStore.SKAT_IMPORT_BASE_GET_TOLDVAERDI_URL;
		logger.info("[INFO] Tollvaerdidekl. calculation process START");
		String urlRequestParamsKeys = "user=" + appUser + SkatConstants.URL_CHAR_DELIMETER_FOR_PARAMS_WITH_HTML_REQUEST + urlRequestParams;
		//put the final valid param. string
		//for debug purposes in GUI
		logger.info(Calendar.getInstance().getTime() + " CGI-start timestamp");
		logger.info("URL: " + BASE_URL_TOLDVAERDI_SUMS);
    		logger.info("URL PARAMS: " + urlRequestParamsKeys);
    		String jsonPayload = this.urlCgiProxyService.getJsonContent(BASE_URL_TOLDVAERDI_SUMS, urlRequestParamsKeys);
    		logger.info(jsonPayload);
		
    		JsonSkatImportSpecificTopicItemToldvaerdiContainer container = this.skatImportSpecificTopicItemService.getSkatTullvaerdiContainer(jsonPayload);
    		logger.info("[INFO] Toldvaerdi calculation process END");
		for(JsonSkatImportSpecificTopicItemToldvaerdiRecord record : container.getTv1calc()){
			//logger.info("[DEBUG] dkiva_t24: " + record.getDkiv_t24());
			//store debug std output
			record.setDebugPrintlnAjax(BASE_URL_TOLDVAERDI_SUMS + "?" + urlRequestParamsKeys + " <JSON> " + jsonPayload + "</JSON>");
			retval = record;
			
		}
		
		return retval;
	}
	
	/**
	 * 
	 * @param appUser
	 * @param urlRequestParams
	 * @param jsonSkatImportSpecificTopicItemRecord
	 * 
	 */
	public JsonSkatImportSpecificTopicItemToldvaerdiRecord calculateTransportCostsOnItem(String appUser, String urlRequestParams){
		JsonSkatImportSpecificTopicItemToldvaerdiRecord retval = null;
		//----------------------------
        //Now calculate the SUMS
        //----------------------------
        String BASE_URL_TANSPORT = SkatImportUrlDataStore.SKAT_IMPORT_BASE_GET_TOLDVAERDI_INTERNAL_EXTERNAL_TRANSPORT_URL;
		logger.info("[INFO] Tollvaerdidekl. transport calculation(dkiv_17a;dkiv_19) process START");
		String urlRequestParamsKeys = "user=" + appUser + SkatConstants.URL_CHAR_DELIMETER_FOR_PARAMS_WITH_HTML_REQUEST + urlRequestParams;
		//put the final valid param. string
		//for debug purposes in GUI
		logger.info(Calendar.getInstance().getTime() + " CGI-start timestamp");
		logger.info("URL: " + BASE_URL_TANSPORT);
    		logger.info("URL PARAMS: " + urlRequestParamsKeys);
    		String jsonPayload = this.urlCgiProxyService.getJsonContent(BASE_URL_TANSPORT, urlRequestParamsKeys);
    		logger.info(jsonPayload);
		
    		JsonSkatImportSpecificTopicItemToldvaerdiTransportContainer container = this.skatImportSpecificTopicItemService.getSkatTullvaerdiTransportContainer(jsonPayload);
    		logger.info("[INFO] Toldvaerdidekl. tranport calculation(dkiv_17a;dkiv_19) process END");
		for(JsonSkatImportSpecificTopicItemToldvaerdiRecord record : container.getT17at19calc()){
			//logger.info("[DEBUG] dkiva_t24: " + record.getDkiv_t24());
			//store debug std output
			//record.setDebugPrintlnAjax(BASE_URL_TANSPORT + "?" + urlRequestParamsKeys + " <JSON> " + jsonPayload + "</JSON>");
			retval = record;
			
		}
		
		return retval;
	}
	
	
}
