package no.systema.skat.skatimport.util.manager;

import java.util.*;

import org.apache.log4j.Logger;
import org.springframework.validation.ValidationUtils;

import no.systema.main.model.SystemaWebUser;
import no.systema.main.service.UrlCgiProxyService;

import no.systema.skat.util.SkatConstants;
import no.systema.main.util.NumberFormatterLocaleAware;
import no.systema.skat.skatimport.util.RpgReturnResponseHandler;
import no.systema.skat.skatimport.mapper.url.request.UrlRequestParameterMapper;
import no.systema.skat.skatimport.url.store.SkatImportUrlDataStore;
import no.systema.skat.skatimport.service.SkatImportSpecificTopicItemService;
import no.systema.skat.skatimport.model.jsonjackson.topic.JsonSkatImportSpecificTopicRecord;
import no.systema.skat.skatimport.model.jsonjackson.topic.items.JsonSkatImportSpecificTopicItemRecord;
import no.systema.skat.model.jsonjackson.JsonSkatAutoControlErrorContainer;

/**
 * AutoControl Manager 
 * @author oscardelatorre
 *
 * Apr 8, 2016
 */
public class SkatImportItemsAutoControlMgr {
	private static final Logger logger = Logger.getLogger(SkatImportItemsAutoControlMgr.class.getName());
	private UrlCgiProxyService urlCgiProxyService = null;
	private SkatImportSpecificTopicItemService skatImportSpecificTopicItemService = null;
	NumberFormatterLocaleAware formatter = new NumberFormatterLocaleAware();
	
	final String ANG_ART_FUA_01 = "01";
	final String ANG_ART_FOE_02 = "02";
	final String ANG_ART_SUP_03 = "03";
	final String ANG_ART_FUE_04 = "04";
	final String ANG_ART_FUET_05 = "05";
	final String ANG_ART_STF_07 = "07";
	final String ANG_ART_STFT_08 = "08";
	
	//Should be set after the constructor call
	private JsonSkatImportSpecificTopicItemRecord record = null;
	public void setRecord (JsonSkatImportSpecificTopicItemRecord recordToValidate){ 
		this.record = recordToValidate;
		this.validRecord = true;
	}
	
	//Set header record
	private JsonSkatImportSpecificTopicRecord headerRecord = null;
	public void setHeaderRecord (JsonSkatImportSpecificTopicRecord headerRecord){ 
		this.headerRecord = headerRecord;
	}
	
	//this is the global validRecord that will decide if the control passes
	private boolean validRecord = true;
	public boolean isValidRecord (){return this.validRecord;}
	
	public SkatImportItemsAutoControlMgr(UrlCgiProxyService urlCgiProxyService, SkatImportSpecificTopicItemService skatImportSpecificTopicItemService){
		this.urlCgiProxyService = urlCgiProxyService;
		this.skatImportSpecificTopicItemService = skatImportSpecificTopicItemService;
	}
	
	
	/**
	 * This method must comply with the section: ValidationUtils.rejectIfEmptyOrWhitespace in order to check the same mandatory fields as the normal validation method call 
	 *  
	 */
	
	public void checkValidMandatoryFields(){
		if( this.ANG_ART_FUA_01.equals(this.headerRecord.getDkih_aart()) ){
			if(record.getDkiv_315a()==null || "".equals(record.getDkiv_315a())){ this.validRecord = false; }
			if(record.getDkiv_35()==null || "".equals(record.getDkiv_35())){ this.validRecord = false; }
			
		}else if( this.ANG_ART_FOE_02.equals(this.headerRecord.getDkih_aart()) ){
			if(record.getDkiv_311a()==null || "".equals(record.getDkiv_311a())){ this.validRecord = false; }
			if(record.getDkiv_313a()==null || "".equals(record.getDkiv_313a())){ this.validRecord = false; }
			if(record.getDkiv_314a()==null || "".equals(record.getDkiv_314a())){ this.validRecord = false; }
			if(record.getDkiv_315a()==null || "".equals(record.getDkiv_315a())){ this.validRecord = false; }
			if(record.getDkiv_331()==null || "".equals(record.getDkiv_331())){ this.validRecord = false; }
			if(record.getDkiv_34()==null || "".equals(record.getDkiv_34())){ this.validRecord = false; }
			if(record.getDkiv_35()==null || "".equals(record.getDkiv_35())){ this.validRecord = false; }
			if(record.getDkiv_46()==null || "".equals(record.getDkiv_46())){ this.validRecord = false; }
			if(record.getDkiv_402a()==null || "".equals(record.getDkiv_402a())){ this.validRecord = false; }
			if(record.getDkiv_403a()==null || "".equals(record.getDkiv_403a())){ this.validRecord = false; }
			
			
		}else if( this.ANG_ART_SUP_03.equals(this.headerRecord.getDkih_aart()) ){
			if(record.getDkiv_36()==null || "".equals(record.getDkiv_36())){ this.validRecord = false; }
			if(record.getDkiv_37()==null || "".equals(record.getDkiv_37())){ this.validRecord = false; }
			if(record.getDkiv_38()==null || "".equals(record.getDkiv_38())){ this.validRecord = false; }
			if(record.getDkiv_42()==null || "".equals(record.getDkiv_42())){ this.validRecord = false; }
			if(record.getDkiv_46()==null || "".equals(record.getDkiv_46())){ this.validRecord = false; }
			
		}else if( this.ANG_ART_FUE_04.equals(this.headerRecord.getDkih_aart()) ){
			//logger.info("####### FUE_04");
			if(record.getDkiv_311a()==null || "".equals(record.getDkiv_311a())){ this.validRecord = false; }
			if(record.getDkiv_313a()==null || "".equals(record.getDkiv_313a())){ this.validRecord = false; }
			if(record.getDkiv_314a()==null || "".equals(record.getDkiv_314a())){ this.validRecord = false; }
			if(record.getDkiv_315a()==null || "".equals(record.getDkiv_315a())){ this.validRecord = false; }
			if(record.getDkiv_331()==null || "".equals(record.getDkiv_331())){ this.validRecord = false; }
			if(record.getDkiv_34()==null || "".equals(record.getDkiv_34())){ this.validRecord = false; }
			if(record.getDkiv_35()==null || "".equals(record.getDkiv_35())){ this.validRecord = false; }
			if(record.getDkiv_36()==null || "".equals(record.getDkiv_36())){ this.validRecord = false; }
			if(record.getDkiv_37()==null || "".equals(record.getDkiv_37())){ this.validRecord = false; }
			if(record.getDkiv_38()==null || "".equals(record.getDkiv_38())){ this.validRecord = false; }
			if(record.getDkiv_42()==null || "".equals(record.getDkiv_42())){ this.validRecord = false; }
			if(record.getDkiv_46()==null || "".equals(record.getDkiv_46())){ this.validRecord = false; }
			if(record.getDkiv_402a()==null || "".equals(record.getDkiv_402a())){ this.validRecord = false; }
			if(record.getDkiv_403a()==null || "".equals(record.getDkiv_403a())){ this.validRecord = false; }
			
		}else if( this.ANG_ART_FUET_05.equals(this.headerRecord.getDkih_aart()) ){
			if(record.getDkiv_311a()==null || "".equals(record.getDkiv_34())){ this.validRecord = false; }
			if(record.getDkiv_35()==null || "".equals(record.getDkiv_35())){ this.validRecord = false; }
			if(record.getDkiv_38()==null || "".equals(record.getDkiv_38())){ this.validRecord = false; }
			if(record.getDkiv_46()==null || "".equals(record.getDkiv_46())){ this.validRecord = false; }
			if(record.getDkiv_402a()==null || "".equals(record.getDkiv_402a())){ this.validRecord = false; }
			if(record.getDkiv_403a()==null || "".equals(record.getDkiv_403a())){ this.validRecord = false; }
			
		}else if( this.ANG_ART_STF_07.equals(this.headerRecord.getDkih_aart()) ){
			if(record.getDkiv_311a()==null || "".equals(record.getDkiv_311a())){ this.validRecord = false; }
			if(record.getDkiv_313a()==null || "".equals(record.getDkiv_313a())){ this.validRecord = false; }
			if(record.getDkiv_314a()==null || "".equals(record.getDkiv_314a())){ this.validRecord = false; }
			if(record.getDkiv_315a()==null || "".equals(record.getDkiv_315a())){ this.validRecord = false; }
			if(record.getDkiv_331()==null || "".equals(record.getDkiv_331())){ this.validRecord = false; }
			if(record.getDkiv_34()==null || "".equals(record.getDkiv_34())){ this.validRecord = false; }
			if(record.getDkiv_35()==null || "".equals(record.getDkiv_35())){ this.validRecord = false; }
			if(record.getDkiv_36()==null || "".equals(record.getDkiv_36())){ this.validRecord = false; }
			if(record.getDkiv_37()==null || "".equals(record.getDkiv_37())){ this.validRecord = false; }
			if(record.getDkiv_38()==null || "".equals(record.getDkiv_38())){ this.validRecord = false; }
			if(record.getDkiv_42()==null || "".equals(record.getDkiv_42())){ this.validRecord = false; }
			if(record.getDkiv_46()==null || "".equals(record.getDkiv_46())){ this.validRecord = false; }
			if(record.getDkiv_402a()==null || "".equals(record.getDkiv_402a())){ this.validRecord = false; }
			if(record.getDkiv_403a()==null || "".equals(record.getDkiv_403a())){ this.validRecord = false; }
			
		}else if( this.ANG_ART_STFT_08.equals(this.headerRecord.getDkih_aart()) ){
			if(record.getDkiv_38()==null || "".equals(record.getDkiv_38())){ this.validRecord = false; }
			if(record.getDkiv_46()==null || "".equals(record.getDkiv_46())){ this.validRecord = false; }
		
		}
	}
	

	/**
	 * Special case of mandatory field 41.
	 */
	public void checkValidMandatoryFields_41(){
		//41 either all or none
		if(record.getDkiv_412()!=null && !"".equals(record.getDkiv_412())){
			if(record.getDkiv_411()!=null && !"".equals(record.getDkiv_411())){
				//OK
			}else{
				this.validRecord = false;
			}
		}
		if(record.getDkiv_411()!=null && !"".equals(record.getDkiv_411())){
			if(record.getDkiv_412()!=null && !"".equals(record.getDkiv_412())){
				//OK
			}else{
				this.validRecord = false;
			}
		}
	}
	
	
	/**
	 *
	 */
	public void checkValidGrossAndNetWeight(){
		//---------------------
		//Gross and Net weight
		//---------------------
		if(record.getDkiv_35()!=null && !"".equals(record.getDkiv_35())){
			if(record.getDkiv_38()!=null && !"".equals(record.getDkiv_38())){
				try{
					String grossFormatTmp = record.getDkiv_35().replace(".", "");
					double grossWeight = Double.parseDouble(grossFormatTmp.replace(",", "."));
					String netFormatTmp = record.getDkiv_38().replace(".", "");
					double netWeight = Double.parseDouble(netFormatTmp.replace(",", "."));
					
					//Net can not be > than Gross
					if (netWeight>grossWeight){
						this.validRecord = false;
					}
				}catch(Exception e){
					//just take a phantom hit here 
				}
			}
		}
	}
	
	/**
	 *
	 */
	public void checkManyCertificatesExist(){
		if(this.manyCerifikatExist(record)){
			if(!this.isValidCertifikatValues(record)){
				this.validRecord = false;
			}
		}
	}
	private boolean manyCerifikatExist(JsonSkatImportSpecificTopicItemRecord record){
		boolean retval = false;
		if(record.getDkiv_442b()!=null && !"".equals(record.getDkiv_442b())){
			retval = true;
		}
		if(record.getDkiv_442c()!=null && !"".equals(record.getDkiv_442c())){
			retval = true;
		}
		if(record.getDkiv_442d()!=null && !"".equals(record.getDkiv_442d())){
			retval = true;
		}
		if(record.getDkiv_442e()!=null && !"".equals(record.getDkiv_442e())){
			retval = true;
		}
		if(record.getDkiv_442f()!=null && !"".equals(record.getDkiv_442f())){
			retval = true;
		}
		if(record.getDkiv_442g()!=null && !"".equals(record.getDkiv_442g())){
			retval = true;
		}
		if(record.getDkiv_442h()!=null && !"".equals(record.getDkiv_442h())){
			retval = true;
		}
		if(record.getDkiv_442i()!=null && !"".equals(record.getDkiv_442i())){
			retval = true;
		}
		
		return retval;
	}
	private boolean isValidCertifikatValues(JsonSkatImportSpecificTopicItemRecord record){
		boolean retval = true;
		
		if(record.getDkiv_442a()!=null && !"".equals(record.getDkiv_442a())){
			//nothing
 		}else{
 			//These fields should not contain values IF the parent 442a is empty...
 			if(record.getDkiv_442b()!=null && !"".equals(record.getDkiv_442b())){
 				retval = false;
 			}
 			if(record.getDkiv_442c()!=null && !"".equals(record.getDkiv_442c())){
 				retval = false;
 			}
 			if(record.getDkiv_442d()!=null && !"".equals(record.getDkiv_442d())){
 				retval = false;
 			}
 			if(record.getDkiv_442e()!=null && !"".equals(record.getDkiv_442e())){
 				retval = false;
 			}
 			if(record.getDkiv_442f()!=null && !"".equals(record.getDkiv_442f())){
 				retval = false;
 			}
 			if(record.getDkiv_442g()!=null && !"".equals(record.getDkiv_442g())){
 				retval = false;
 			}
 			if(record.getDkiv_442h()!=null && !"".equals(record.getDkiv_442h())){
 				retval = false;
 			}
 			if(record.getDkiv_442i()!=null && !"".equals(record.getDkiv_442i())){
 				retval = false;
 			}
 		}
		
		
		return retval;
	}
	
	/**
	 * 
	 * @param applicationUser
	 * @param errorFlag
	 */
	
	public void updateItemWithAutoControlError(String applicationUser, String errorFlag){
		String BASE_URL_UPDATE_ERR = SkatImportUrlDataStore.SKAT_IMPORT_BASE_UPDATE_SPECIFIC_TOPIC_ITEM_AUTOCONTROL_ERROR_URL;
		StringBuffer urlRequestParamsKeysAutoControl = new StringBuffer();
		urlRequestParamsKeysAutoControl.append("user=" + applicationUser);
		urlRequestParamsKeysAutoControl.append("&avd=" + this.record.getDkiv_syav());
		urlRequestParamsKeysAutoControl.append("&opd=" + this.record.getDkiv_syop());
		urlRequestParamsKeysAutoControl.append("&lin=" + this.record.getDkiv_syli());
		if(errorFlag!=null){
			urlRequestParamsKeysAutoControl.append("&dkiv_err=" + errorFlag);
		}else{
			urlRequestParamsKeysAutoControl.append("&dkiv_err=");
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
		JsonSkatAutoControlErrorContainer container = this.skatImportSpecificTopicItemService.getSkatImportSpecificTopicItemAutoControlErrorContainer(jsonPayload);
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
	 * @return
	 */
	public boolean updateItemRecord(String applicationUser){
		boolean retval = true;
		UrlRequestParameterMapper urlRequestParameterMapper = new UrlRequestParameterMapper();
		RpgReturnResponseHandler rpgReturnResponseHandler = new RpgReturnResponseHandler();
		
		String BASE_URL_UPDATE = SkatImportUrlDataStore.SKAT_IMPORT_BASE_UPDATE_SPECIFIC_TOPIC_ITEM_URL;
		logger.info("[INFO] UPDATE (light) to be done with lineNr [dkiv_syli]:" + this.record.getDkiv_syli());
		
		StringBuffer urlRequestParamsKeys = new StringBuffer();
		urlRequestParamsKeys.append("user=" + applicationUser);
		urlRequestParamsKeys.append(SkatConstants.URL_CHAR_DELIMETER_FOR_PARAMS_WITH_HTML_REQUEST + "avd=" + this.record.getDkiv_syav());
		urlRequestParamsKeys.append(SkatConstants.URL_CHAR_DELIMETER_FOR_PARAMS_WITH_HTML_REQUEST + "opd=" + this.record.getDkiv_syop());
		urlRequestParamsKeys.append(SkatConstants.URL_CHAR_DELIMETER_FOR_PARAMS_WITH_HTML_REQUEST + "lin=" + this.record.getDkiv_syli());
		urlRequestParamsKeys.append(SkatConstants.URL_CHAR_DELIMETER_FOR_PARAMS_WITH_HTML_REQUEST + "mode=U");
		urlRequestParamsKeys.append(SkatConstants.URL_CHAR_DELIMETER_FOR_PARAMS_WITH_HTML_REQUEST + "dkiv_ligh=X"); //light update flag
		
		
		String urlRequestParamsItem = urlRequestParameterMapper.getUrlParameterValidString((this.record));
		//put the final valid param. string
		String urlRequestParams = urlRequestParamsKeys.toString() + urlRequestParamsItem;
		/*DEBUG 
		logger.info(Calendar.getInstance().getTime() + " CGI-start timestamp");
    	logger.info("URL: " + BASE_URL_UPDATE);
    	logger.info("URL PARAMS: " + urlRequestParams);
    	*/
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
	 * @param headerRecord
	 * @param appUser
	 */
	public void calculateNetWeight(JsonSkatImportSpecificTopicRecord headerRecord, SystemaWebUser appUser){
		  double grossNetFactor = 0.9; //default;
		  String grossWeight = this.record.getDkiv_35();
		  String netWeight = this.record.getDkiv_38();
		  try{
			  //Gross weight exists but not net weight
			  if(grossWeight!=null && !"".equals(grossWeight) && (netWeight==null || "".equals(netWeight))  ){
				  /*NOTE! this field does not exist in SKAT (as for today)
				  if(headerRecord.getSefvk()!=null && !"".equals(headerRecord.getSefvk())){
					  String tmp = headerRecord.getSefvk().replace("," , ".");
					  grossNetFactor = Double.parseDouble(tmp);
				  }
				  */
				  //operation
				  grossWeight = grossWeight.replace("," , ".");
				  double grossWeightDbl = Double.parseDouble(grossWeight);
				  double netWeightDbl = grossWeightDbl * grossNetFactor;
				  NumberFormatterLocaleAware formatter = new NumberFormatterLocaleAware();
				  netWeight = String.valueOf(formatter.getDoubleEuropeanFormat(netWeightDbl, 3, false));
				  //final result
				  this.record.setDkiv_38(netWeight);
				  
			  }
			  
		  }catch (Exception e){
			  logger.info("[ERROR] on Net weight calculation - Auto control:" + e.toString());
		  }
		
	}
	
}
