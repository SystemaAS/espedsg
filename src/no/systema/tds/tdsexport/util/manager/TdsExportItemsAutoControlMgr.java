package no.systema.tds.tdsexport.util.manager;

import java.util.*;

import org.apache.log4j.Logger;
import no.systema.main.service.UrlCgiProxyService;
import no.systema.tds.service.TdsBilagdaHandlingarYKoderService;
import no.systema.tds.service.TdsTillaggskoderService;
import no.systema.tds.model.jsonjackson.codes.JsonTdsBilagdaHandlingarYKoderRecord;
import no.systema.tds.model.jsonjackson.codes.JsonTdsTillaggskodRecord;
import no.systema.tds.model.jsonjackson.validation.JsonTdsMangdEnhetContainer;
import no.systema.tds.model.jsonjackson.validation.JsonTdsMangdEnhetRecord;
import no.systema.tds.tdsexport.service.TdsExportSpecificTopicItemService;
import no.systema.tds.tdsexport.url.store.TdsExportUrlDataStore;
import no.systema.tds.tdsexport.util.RpgReturnResponseHandler;
import no.systema.tds.tdsexport.util.manager.TdsExportControllerAjaxCommonFunctionsMgr;
import no.systema.tds.tdsexport.controller.TdsExportItemsController;
import no.systema.tds.tdsexport.model.jsonjackson.topic.JsonTdsExportSpecificTopicRecord;
import no.systema.tds.tdsexport.model.jsonjackson.topic.items.JsonTdsExportSpecificTopicItemContainer;
import no.systema.tds.tdsexport.model.jsonjackson.topic.items.JsonTdsExportSpecificTopicItemRecord;
import no.systema.tds.tdsexport.model.jsonjackson.topic.items.JsonTdsExportSpecificTopicItemStatisticalValueRecord;
import no.systema.tds.tdsexport.mapper.url.request.UrlRequestParameterMapper;
import no.systema.tds.url.store.TdsUrlDataStore;
import no.systema.tds.util.TdsConstants;
import no.systema.tds.model.jsonjackson.JsonTdsAutoControlErrorContainer;



public class TdsExportItemsAutoControlMgr {
	private static final Logger logger = Logger.getLogger(TdsExportItemsAutoControlMgr.class.getName());
	private UrlCgiProxyService urlCgiProxyService = null;
	private TdsExportSpecificTopicItemService tdsExportSpecificTopicItemService = null;
	TdsExportControllerAjaxCommonFunctionsMgr commonMgr = new TdsExportControllerAjaxCommonFunctionsMgr();
	
	//Should be set after the constructor call
	private JsonTdsExportSpecificTopicItemRecord record = null;
	public void setRecord (JsonTdsExportSpecificTopicItemRecord recordToValidate){ 
		this.record = recordToValidate;
		this.validRecord = true;
	}
	
	private JsonTdsExportSpecificTopicRecord headerRecord = null;
	public void setHeaderRecord (JsonTdsExportSpecificTopicRecord headerRecord){ 
		this.headerRecord = headerRecord;
	}
	
	
	//this is the global validRecord that will decide if the control passes
	private boolean validRecord = true;
	public boolean isValidRecord (){return this.validRecord;}
	
	public TdsExportItemsAutoControlMgr(UrlCgiProxyService urlCgiProxyService, TdsExportSpecificTopicItemService tdsExportSpecificTopicItemService){
		this.urlCgiProxyService = urlCgiProxyService;
		this.tdsExportSpecificTopicItemService = tdsExportSpecificTopicItemService;
	}
	
	/**
	 * 
	 * @param headerRecord
	 */
	public void calculateStatisticalValuesOnItem(JsonTdsExportSpecificTopicRecord headerRecord, String applicationUser){
		StringBuffer urlRequestParams = new StringBuffer();
		if( (this.record.getSvev_stva()!=null && !"".equals(this.record.getSvev_stva()) ) && (this.record.getSvev_stva2()!=null && !"".equals(this.record.getSvev_stva2())) ){
			//nothing 
		}else{
			if(validStatisticalValuesParameters(headerRecord)){
				urlRequestParams.append("sveh_vakd=" + headerRecord.getSveh_vakd());
				urlRequestParams.append("&sveh_vaku=" + headerRecord.getSveh_vaku());
				urlRequestParams.append("&sveh_fabl=" + headerRecord.getSveh_fabl());
				urlRequestParams.append("&svev_fabl=" + this.record.getSvev_fabl());
				
				JsonTdsExportSpecificTopicItemStatisticalValueRecord statValueRecord = this.commonMgr.calculateStatisticalValuesOnItem(this.tdsExportSpecificTopicItemService, applicationUser, urlRequestParams.toString());
				if(statValueRecord!=null){
					this.record.setSvev_stva(statValueRecord.getSvev_stva());
					this.record.setSvev_stva2(statValueRecord.getSvev_stva2());
					
				}else{
					//nothing since this will be catched in: checkMandatoryFields...
				}
			}else{
				//nothing since this will be catched in: checkMandatoryFields... 
			}
		}
		
	}
	/**
	 * 
	 * @param headerRecord
	 * @return
	 */
	private boolean validStatisticalValuesParameters(JsonTdsExportSpecificTopicRecord headerRecord){
		boolean retval = false;
		if( (headerRecord.getSveh_vakd()!=null && !"".equals(headerRecord.getSveh_vakd())) && 
			(headerRecord.getSveh_vaku()!=null && !"".equals(headerRecord.getSveh_vaku())) &&	
			(headerRecord.getSveh_fabl()!=null && !"".equals(headerRecord.getSveh_fabl())) &&
			(this.record.getSvev_fabl()!=null && !"".equals(this.record.getSvev_fabl())) ){
			retval = true;
		}
		return retval;
	}
	/**
	 * Validation control
	 * Mandatory fields
	 */
	public void checkValidMandatoryFields(){
		if( this.isNotNull(record.getSvev_ulkd()) && this.isNotNull(record.getSvev_vata()) && this.isNotNull(record.getSvev_eup1()) && 
			this.isNotNull(record.getSvev_brut()) && this.isNotNull(record.getSvev_neto()) && this.isNotNull(record.getSvev_kosl()) && 
			this.isNotNull(record.getSvev_vasl()) && this.isNotNull(record.getSvev_godm()) && this.isNotNull(record.getSvev_fabl()) &&
			this.isNotNull(record.getSvev_call()) && this.isNotNull(record.getSvev_stva()) && this.isNotNull(record.getSvev_stva2()) ){
			//nothing
		}else{
			logger.info("FALSE");
			this.validRecord = false;
		}
	}
	
	/**
	 * 
	 * @param tdsTillaggskoderService
	 * @param applicationUser
	 * @param countryCode
	 * @param itemCode
	 */
	public void checkValidTillaggsKoder(TdsTillaggskoderService tdsTillaggskoderService, String applicationUser){
		List<JsonTdsTillaggskodRecord> list = this.commonMgr.fetchTillagskoder(tdsTillaggskoderService, applicationUser, this.headerRecord.getSveh_aube(), this.record.getSvev_vata());
		if(list!=null && list.size()>0){
			if(this.isNotNull(this.record.getSvev_vati()) ){
				//nothing
			}else{
				for(JsonTdsTillaggskodRecord record: list){
					this.record.setSvev_vati(record.getKod());
				}
			}
		}
	}
	
	/**
	 * The gross and net weight ARE NOT ALLOWED to have decimals if the value is >=1
	 * @return
	 */
	public void checkValidGrossAndNetWeight(){
		try{
			double grossWeight = Double.valueOf(this.record.getSvev_brut().replace(",", "."));
			double netWeight = Double.valueOf(this.record.getSvev_neto().replace(",", "."));
			//check gross
			if(grossWeight>=1){
				if(Math.floor(grossWeight)!= grossWeight){
					this.validRecord = false;
				}
			}
			//check net
			if(netWeight>=1){
				if(Math.floor(netWeight)!= netWeight){
					this.validRecord = false;
				}
			}
			//compare gross vs net
			if(netWeight>grossWeight){
				this.validRecord = false;
			}
		}catch(Exception e){
			this.validRecord = false;
		}
		
	}
	/**
	 * Bilagda handlingar
	 * @param tdsBilagdaHandlingarYKoderService
	 * @param applicationUser
	 */
	public void checkValidBilagdaHandlingar(TdsBilagdaHandlingarYKoderService tdsBilagdaHandlingarYKoderService, String applicationUser){
		List<JsonTdsBilagdaHandlingarYKoderRecord> list = this.commonMgr.fetchBilagdaHandlingar(tdsBilagdaHandlingarYKoderService, applicationUser, this.headerRecord.getSveh_aube(), this.record.getSvev_vata());
		if(list!=null && list.size()>0){
			if(this.isNotNull(this.record.getSvev_bit1()) ){
				if(this.record.getSvev_bit1().startsWith("Y") || this.record.getSvev_bit1().startsWith("X")){
					//nothing
				}else{
					if(this.isNotNull(this.record.getSvev_bit2()) ){
						if(this.record.getSvev_bit2().startsWith("Y") || this.record.getSvev_bit2().startsWith("X")){
							//nothing
						}else{
							if(this.isNotNull(this.record.getSvev_bit3()) ){
								if(this.record.getSvev_bit3().startsWith("Y") || this.record.getSvev_bit3().startsWith("X")){
									//nothing
								}else{
									if(this.isNotNull(this.record.getSvev_bit4()) ){
										if(this.record.getSvev_bit4().startsWith("Y") || this.record.getSvev_bit4().startsWith("X")){
											//nothing
										}else{
											this.validRecord = false;
										}
									}else{
										this.validRecord = false;
									}
								}
							}else{
								this.validRecord = false;
							}
						}
					}else{
						this.validRecord = false;
					}
				}
			}else{
				this.validRecord = false;
			}
		}
	}
	/**
	 * 
	 * @param applicationUser
	 */
	public void checkValidExtraMangdEnhet(String applicationUser, String sveh_aube){
		if(getMandatoryMangdEnhetDirective(applicationUser, sveh_aube)){
			if(this.record.getSvev_ankv()!=null && !"".equals(this.record.getSvev_ankv())){
				//valid
			}else{
				this.validRecord = false;
			}
		}else{
			if(this.record.getSvev_ankv()!=null && !"".equals(this.record.getSvev_ankv())){
				this.validRecord = false;
			}
		}
	}
	/**
	 * 
	 * @param applicationUser
	 * @return
	 */
	public boolean updateItemRecord(String applicationUser){
		boolean retval = true;
		UrlRequestParameterMapper urlRequestParameterMapper = new UrlRequestParameterMapper();
		RpgReturnResponseHandler rpgReturnResponseHandler = new RpgReturnResponseHandler();
		
		String BASE_URL_UPDATE = TdsExportUrlDataStore.TDS_EXPORT_BASE_UPDATE_SPECIFIC_TOPIC_ITEM_URL;
		logger.info("[INFO] UPDATE (light) to be done with lineNr [svev_syli]:" + this.record.getSvev_syli());
		
		StringBuffer urlRequestParamsKeys = new StringBuffer();
		urlRequestParamsKeys.append("user=" + applicationUser);
		urlRequestParamsKeys.append(TdsConstants.URL_CHAR_DELIMETER_FOR_PARAMS_WITH_HTML_REQUEST + "avd=" + this.record.getSvev_syav());
		urlRequestParamsKeys.append(TdsConstants.URL_CHAR_DELIMETER_FOR_PARAMS_WITH_HTML_REQUEST + "opd=" + this.record.getSvev_syop());
		urlRequestParamsKeys.append(TdsConstants.URL_CHAR_DELIMETER_FOR_PARAMS_WITH_HTML_REQUEST + "lin=" + this.record.getSvev_syli());
		urlRequestParamsKeys.append(TdsConstants.URL_CHAR_DELIMETER_FOR_PARAMS_WITH_HTML_REQUEST + "mode=U");
		urlRequestParamsKeys.append(TdsConstants.URL_CHAR_DELIMETER_FOR_PARAMS_WITH_HTML_REQUEST + "svev_ligh=X"); //light update flag
		
		String urlRequestParamsItem = urlRequestParameterMapper.getUrlParameterValidString((this.record));
		//put the final valid param. string
		String urlRequestParams = urlRequestParamsKeys.toString() + urlRequestParamsItem;
		//DEBUG
		logger.info(Calendar.getInstance().getTime() + " CGI-start timestamp");
    	logger.info("URL: " + BASE_URL_UPDATE);
    	logger.info("URL PARAMS: " + urlRequestParams);
    	
    	//----------------------------------------------------------------------------
    	//EXECUTE the UPDATE (RPG program) here (STEP [2] when creating a new record)
    	//----------------------------------------------------------------------------
		String rpgReturnPayload = this.urlCgiProxyService.getJsonContent(BASE_URL_UPDATE, urlRequestParams);
		//Debug --> 
    	//logger.info("Checking errMsg in rpgReturnPayload" + rpgReturnPayload);
    	//we must evaluate a return RPG code in order to know if the Update was OK or not
    	rpgReturnResponseHandler.evaluateRpgResponseOnTopicItemCreateOrUpdate(rpgReturnPayload);
    	if(rpgReturnResponseHandler.getErrorMessage()!=null && !"".equals(rpgReturnResponseHandler.getErrorMessage())){
    		rpgReturnResponseHandler.setErrorMessage("[ERROR] FATAL on UPDATE: " + rpgReturnResponseHandler.getErrorMessage());
    		retval = false;
    	}else{
    		//Update succefully done!
    		logger.info("[INFO] Valid light Update -- Record successfully updated, OK ");    		
    	}
    	return retval;
	}
	/**
	 * 
	 * @param applicationUser
	 */
	public void updateItemWithAutoControlError(String applicationUser, String errorFlag){
		String BASE_URL_UPDATE_ERR = TdsExportUrlDataStore.TDS_EXPORT_BASE_UPDATE_SPECIFIC_TOPIC_ITEM_AUTOCONTROL_ERROR_URL;
		StringBuffer urlRequestParamsKeysAutoControl = new StringBuffer();
		urlRequestParamsKeysAutoControl.append("user=" + applicationUser);
		urlRequestParamsKeysAutoControl.append("&avd=" + this.record.getSvev_syav());
		urlRequestParamsKeysAutoControl.append("&opd=" + this.record.getSvev_syop());
		urlRequestParamsKeysAutoControl.append("&lin=" + this.record.getSvev_syli());
		if(errorFlag!=null){
			urlRequestParamsKeysAutoControl.append("&svev_err=" + errorFlag);
		}else{
			urlRequestParamsKeysAutoControl.append("&svev_err=");
		}
		/*DEBUG
		logger.info(Calendar.getInstance().getTime() + " CGI-start timestamp");
		logger.info("FETCH item list... ");
    	logger.info("URL: " + BASE_URL_UPDATE_ERR);
    	logger.info("URL PARAMS: " + urlRequestParamsKeysAutoControl);
    	*/
    	//--------------------------------------
    	//EXECUTE the FETCH (RPG program) here
    	//--------------------------------------
		String jsonPayload = this.urlCgiProxyService.getJsonContent(BASE_URL_UPDATE_ERR, urlRequestParamsKeysAutoControl.toString());
		JsonTdsAutoControlErrorContainer container = this.tdsExportSpecificTopicItemService.getTdsExportSpecificTopicItemAutoControlErrorContainer(jsonPayload);
    	if(container!=null){
    		if(container.getErrMsg()!=null && !"".equals(container.getErrMsg())){
    			logger.info(container.getErrMsg());
    		}else{
    			logger.info("[OK] Update successfully done on AutoControl");
    		}
    	}
    }
	
	
	/**
	 * 
	 * @param value
	 * @return
	 */
	private boolean isNotNull(String value){
		boolean retval = false;
		if(value!=null && !"".equals(value)){
			retval = true;
		}
		return retval;
	}
	/**
	 * 
	 * @param applicationUser
	 * @param sveh_aube
	 * @return
	 */
	private boolean getMandatoryMangdEnhetDirective(String applicationUser, String sveh_aube){
		boolean retval = false;
		String TDS_IE = "E";
		
		String BASE_URL_FETCH = TdsUrlDataStore.TDS_CHECK_EXTRA_MANGDENHET;
		String urlRequestParamsKeys = "user="+ applicationUser + "&ie=" + TDS_IE + "&kod=" + this.record.getSvev_vata() + "&lk=" + sveh_aube;
		//String urlRequestParamsKeys = "user="+ applicationUser + "&ie=" + TDS_IE + "&kod=" + this.record.getSvev_vata() + "&lk=" + this.record.getSvev_ulkd();
		/*DEBUG
		logger.info(Calendar.getInstance().getTime() + " CGI-start timestamp");
		logger.info("FETCH av mangdenhet... ");
    	logger.info("URL: " + BASE_URL_FETCH);
    	logger.info("URL PARAMS: " + urlRequestParamsKeys);
    	*/
    	//-----------------
    	//Json and execute 
    	//-----------------
		String jsonPayload = this.urlCgiProxyService.getJsonContent(BASE_URL_FETCH, urlRequestParamsKeys);
		logger.info(jsonPayload);
		JsonTdsMangdEnhetContainer container = this.tdsExportSpecificTopicItemService.getTdsMangdEnhetContainer(jsonPayload);
		for(JsonTdsMangdEnhetRecord record: container.getXtramangdenhet()){
			if(record.getXtra()!=null && record.getXtra().toUpperCase().equals("Y")){
				retval = true;
			}
		}
		
		return retval;
	}
	
}
