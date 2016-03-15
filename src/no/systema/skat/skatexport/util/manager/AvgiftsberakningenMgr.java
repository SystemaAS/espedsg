/**
 * 
 */
package no.systema.skat.skatexport.util.manager;

import java.util.Calendar;

import no.systema.skat.skatexport.model.jsonjackson.topic.JsonSkatExportSpecificTopicRecord;
import no.systema.skat.skatexport.service.SkatExportSpecificTopicItemService;
import no.systema.skat.skatexport.url.store.SkatExportUrlDataStore;
import no.systema.skat.skatexport.model.jsonjackson.topic.items.JsonSkatExportSpecificTopicItemAvgifterContainer;
import no.systema.skat.skatexport.model.jsonjackson.topic.items.JsonSkatExportSpecificTopicItemAvgifterRecord;
import no.systema.skat.skatexport.model.jsonjackson.topic.items.JsonSkatExportSpecificTopicItemRecord;

import no.systema.skat.util.SkatConstants;
import no.systema.main.model.SystemaWebUser;
import no.systema.main.service.UrlCgiProxyService;

import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;


/**
 * This class calculates the Stat. værdi in the SKAT - item scenario.
 * In SKAT Eksport there is no Avgifter what so ever...
 * 
 * 
 * @author oscardelatorre
 * @date Mar 24, 2014
 * 
 */
public class AvgiftsberakningenMgr {
	private static final Logger logger = Logger.getLogger(AvgiftsberakningenMgr.class.getName());
	private SkatExportSpecificTopicItemService skatExportSpecificTopicItemService;
	private UrlCgiProxyService urlCgiProxyService;
	
	/**
	 * Constructor
	 * @param itemService
	 * @param urlProxyService
	 */
	public AvgiftsberakningenMgr(SkatExportSpecificTopicItemService itemService, UrlCgiProxyService urlProxyService){
		this.skatExportSpecificTopicItemService = itemService;
		this.urlCgiProxyService = urlProxyService;
		
	}
	
	
	/**
	 * 
	 * @param session
	 * @param jsonTdsImportSpecificTopicItemRecord
	 * @param appUser
	 */
	public JsonSkatExportSpecificTopicItemAvgifterRecord calculateChargesOnItem(HttpSession session, JsonSkatExportSpecificTopicItemRecord jsonSkatExportSpecificTopicItemRecord, SystemaWebUser appUser ){
		JsonSkatExportSpecificTopicItemAvgifterRecord retval = null;
		//-----------------------------------------------------------------------
        //Now calculate the charges (Avgiftsberäkningen) always with CREATE NEW
        //-----------------------------------------------------------------------
        String BASE_URL_CALCULATION_AVGIFTER = SkatExportUrlDataStore.SKAT_EXPORT_BASE_AVGIFTS_CALCULATION_URL;
		logger.info("[INFO] Avgifts calculation process START");
		String urlRequestParamsKeys = this.getRequestUrlKeyParametersAvgiftsberakning(session, jsonSkatExportSpecificTopicItemRecord, appUser);
		//put the final valid param. string
		//for debug purposes in GUI
		logger.info(Calendar.getInstance().getTime() + " CGI-start timestamp");
		logger.info("URL: " + BASE_URL_CALCULATION_AVGIFTER);
    		logger.info("URL PARAMS: " + urlRequestParamsKeys);
    		String jsonPayload = this.urlCgiProxyService.getJsonContent(BASE_URL_CALCULATION_AVGIFTER, urlRequestParamsKeys);
			
    		JsonSkatExportSpecificTopicItemAvgifterContainer container = this.skatExportSpecificTopicItemService.getSkatExportSpecificTopicItemAvgifterContainer(jsonPayload);
    		logger.info("[INFO] Avgifts calculation process END");
		for(JsonSkatExportSpecificTopicItemAvgifterRecord record : container.getStatvaluecalc()){
			logger.info("[DEBUG] dkev_stva: " + record.getDkev_46());
			retval = record;
		}
		return retval;
	}
	
	/**
	 * 
	 * @param session
	 * @param jsonTdsImportSpecificTopicItemRecord
	 * @param appUser
	 * @return
	 */
	public JsonSkatExportSpecificTopicItemAvgifterRecord calculateChargesOnItem(String appUser, String urlRequestParams){
		JsonSkatExportSpecificTopicItemAvgifterRecord retval = null;
		//-----------------------------------------------------------------------
        //Now calculate the charges (Avgiftsberäkningen) always with CREATE NEW
        //-----------------------------------------------------------------------
        String BASE_URL_CALCULATION_AVGIFTER = SkatExportUrlDataStore.SKAT_EXPORT_BASE_AVGIFTS_CALCULATION_URL;
		logger.info("[INFO] Avgifts calculation process START");
		String urlRequestParamsKeys = "user=" + appUser + SkatConstants.URL_CHAR_DELIMETER_FOR_PARAMS_WITH_HTML_REQUEST + urlRequestParams;
		//put the final valid param. string
		//for debug purposes in GUI
		logger.info(Calendar.getInstance().getTime() + " CGI-start timestamp");
		logger.info("URL: " + BASE_URL_CALCULATION_AVGIFTER);
    		logger.info("URL PARAMS: " + urlRequestParamsKeys);
    		String jsonPayload = this.urlCgiProxyService.getJsonContent(BASE_URL_CALCULATION_AVGIFTER, urlRequestParamsKeys);
    		logger.info(jsonPayload);
		
    		JsonSkatExportSpecificTopicItemAvgifterContainer container = this.skatExportSpecificTopicItemService.getSkatExportSpecificTopicItemAvgifterContainer(jsonPayload);
    		logger.info("[INFO] Avgifts calculation process END");
		for(JsonSkatExportSpecificTopicItemAvgifterRecord record : container.getStatvaluecalc()){
			//logger.info("[DEBUG] sviv_stva: " + record.getSviv_stva());
			//logger.info("[DEBUG] sviv_abb1: " + record.getSviva_abb1());
			
			//store debug std output
			record.setDebugPrintlnAjax(BASE_URL_CALCULATION_AVGIFTER + "?" + urlRequestParamsKeys + " <JSON> " + jsonPayload + "</JSON>");
			
			retval = record;
		}
		
		return retval;
	}
	
	/**
	 * 
	 * @param session
	 * @param itemRecord
	 * @param appUser
	 * @return
	 */
	private String getRequestUrlKeyParametersAvgiftsberakning(HttpSession session, JsonSkatExportSpecificTopicItemRecord itemRecord, SystemaWebUser appUser ){
		JsonSkatExportSpecificTopicRecord topicHeaderRecord = (JsonSkatExportSpecificTopicRecord)session.getAttribute(SkatConstants.DOMAIN_RECORD_TOPIC_SKAT);
		StringBuffer urlRequestParamsKeys = new StringBuffer();
		
		urlRequestParamsKeys.append("user=" + appUser.getUser());
		/*
		urlRequestParamsKeys.append(SkatConstants.URL_CHAR_DELIMETER_FOR_PARAMS_WITH_HTML_REQUEST + "svih_vufr=" + topicHeaderRecord.getSvih_vufr());
		urlRequestParamsKeys.append(SkatConstants.URL_CHAR_DELIMETER_FOR_PARAMS_WITH_HTML_REQUEST + "svih_vuva=" + topicHeaderRecord.getSvih_vuva());
		urlRequestParamsKeys.append(SkatConstants.URL_CHAR_DELIMETER_FOR_PARAMS_WITH_HTML_REQUEST + "svih_vuku=" + topicHeaderRecord.getSvih_vuku());
		urlRequestParamsKeys.append(SkatConstants.URL_CHAR_DELIMETER_FOR_PARAMS_WITH_HTML_REQUEST + "svih_vufo=" + topicHeaderRecord.getSvih_vufo());
		urlRequestParamsKeys.append(SkatConstants.URL_CHAR_DELIMETER_FOR_PARAMS_WITH_HTML_REQUEST + "svih_ovko=" + topicHeaderRecord.getSvih_ovko());
		urlRequestParamsKeys.append(SkatConstants.URL_CHAR_DELIMETER_FOR_PARAMS_WITH_HTML_REQUEST + "svih_kara=" + topicHeaderRecord.getSvih_kara());
		urlRequestParamsKeys.append(SkatConstants.URL_CHAR_DELIMETER_FOR_PARAMS_WITH_HTML_REQUEST + "svih_anra=" + topicHeaderRecord.getSvih_anra());
		urlRequestParamsKeys.append(SkatConstants.URL_CHAR_DELIMETER_FOR_PARAMS_WITH_HTML_REQUEST + "svih_lekd=" + topicHeaderRecord.getSvih_lekd());
		urlRequestParamsKeys.append(SkatConstants.URL_CHAR_DELIMETER_FOR_PARAMS_WITH_HTML_REQUEST + "svih_vakd=" + topicHeaderRecord.getSvih_vakd());
		urlRequestParamsKeys.append(SkatConstants.URL_CHAR_DELIMETER_FOR_PARAMS_WITH_HTML_REQUEST + "svih_vaku=" + topicHeaderRecord.getSvih_vaku());
		urlRequestParamsKeys.append(SkatConstants.URL_CHAR_DELIMETER_FOR_PARAMS_WITH_HTML_REQUEST + "svih_fabl=" + topicHeaderRecord.getSvih_fabl());
		urlRequestParamsKeys.append(SkatConstants.URL_CHAR_DELIMETER_FOR_PARAMS_WITH_HTML_REQUEST + "sviv_vata=" + itemRecord.getSviv_vata());
		urlRequestParamsKeys.append(SkatConstants.URL_CHAR_DELIMETER_FOR_PARAMS_WITH_HTML_REQUEST + "sviv_ulkd=" + itemRecord.getSviv_ulkd());
		urlRequestParamsKeys.append(SkatConstants.URL_CHAR_DELIMETER_FOR_PARAMS_WITH_HTML_REQUEST + "sviv_fokd=" + itemRecord.getSviv_fokd());
		urlRequestParamsKeys.append(SkatConstants.URL_CHAR_DELIMETER_FOR_PARAMS_WITH_HTML_REQUEST + "sviv_eup1=" + itemRecord.getSviv_eup1());
		urlRequestParamsKeys.append(SkatConstants.URL_CHAR_DELIMETER_FOR_PARAMS_WITH_HTML_REQUEST + "sviv_ankv=" + itemRecord.getSviv_ankv());
		urlRequestParamsKeys.append(SkatConstants.URL_CHAR_DELIMETER_FOR_PARAMS_WITH_HTML_REQUEST + "sviv_stva=" + itemRecord.getSviv_stva());
		urlRequestParamsKeys.append(SkatConstants.URL_CHAR_DELIMETER_FOR_PARAMS_WITH_HTML_REQUEST + "sviv_stva2=" + itemRecord.getSviv_stva2());
		urlRequestParamsKeys.append(SkatConstants.URL_CHAR_DELIMETER_FOR_PARAMS_WITH_HTML_REQUEST + "sviv_fabl=" + itemRecord.getSviv_fabl());
		*/
		
		return urlRequestParamsKeys.toString();
	}
	/**
	 * 
	 * Hand over of attributes from the source record (JSON) to the target record (JSON)
	 * @param sourceRecord
	 * @param targetRecord
	 * 
	 */
	public void handOverAttributes(JsonSkatExportSpecificTopicItemAvgifterRecord sourceRecord, JsonSkatExportSpecificTopicItemRecord targetRecord){
		/*
		//Statistiskt värde
		targetRecord.setSviv_stva(sourceRecord.getSviv_stva());
		targetRecord.setSviv_stva2(sourceRecord.getSviv_stva2());
		
		//Slag (kod)
		targetRecord.setSviva_abk1(sourceRecord.getSviva_abk1());
		targetRecord.setSviva_abk2(sourceRecord.getSviva_abk2());
		targetRecord.setSviva_abk3(sourceRecord.getSviva_abk3());
		targetRecord.setSviva_abk4(sourceRecord.getSviva_abk4());
		targetRecord.setSviva_abk5(sourceRecord.getSviva_abk5());
		//Grund
		targetRecord.setSviva_abg1(sourceRecord.getSviva_abg1());
		targetRecord.setSviva_abg2(sourceRecord.getSviva_abg2());
		targetRecord.setSviva_abg3(sourceRecord.getSviva_abg3());
		targetRecord.setSviva_abg4(sourceRecord.getSviva_abg4());
		targetRecord.setSviva_abg5(sourceRecord.getSviva_abg5());
		//Sats
		targetRecord.setSviva_abs1(sourceRecord.getSviva_abs1());
		targetRecord.setSviva_abs2(sourceRecord.getSviva_abs2());
		targetRecord.setSviva_abs3(sourceRecord.getSviva_abs3());
		targetRecord.setSviva_abs4(sourceRecord.getSviva_abs4());
		targetRecord.setSviva_abs5(sourceRecord.getSviva_abs5());
		//Enhet
		targetRecord.setSviva_abx1(sourceRecord.getSviva_abx1());
		targetRecord.setSviva_abx2(sourceRecord.getSviva_abx2());
		targetRecord.setSviva_abx3(sourceRecord.getSviva_abx3());
		targetRecord.setSviva_abx4(sourceRecord.getSviva_abx4());
		targetRecord.setSviva_abx5(sourceRecord.getSviva_abx5());
		//Belopp
		targetRecord.setSviva_abb1(sourceRecord.getSviva_abb1());
		targetRecord.setSviva_abb2(sourceRecord.getSviva_abb2());
		targetRecord.setSviva_abb3(sourceRecord.getSviva_abb3());
		targetRecord.setSviva_abb4(sourceRecord.getSviva_abb4());
		targetRecord.setSviva_abb5(sourceRecord.getSviva_abb5());
		*/
		
	}
	
}
