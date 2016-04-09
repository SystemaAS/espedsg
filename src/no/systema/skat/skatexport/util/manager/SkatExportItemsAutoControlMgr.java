package no.systema.skat.skatexport.util.manager;

import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.log4j.Logger;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;

import no.systema.main.model.SystemaWebUser;
import no.systema.main.service.UrlCgiProxyService;

import no.systema.skat.util.SkatConstants;
import no.systema.main.util.NumberFormatterLocaleAware;
import no.systema.skat.skatexport.util.RpgReturnResponseHandler;
import no.systema.skat.skatexport.mapper.url.request.UrlRequestParameterMapper;
import no.systema.skat.skatexport.url.store.SkatExportUrlDataStore;
import no.systema.skat.skatexport.service.SkatExportSpecificTopicItemService;
import no.systema.skat.skatexport.model.jsonjackson.topic.JsonSkatExportSpecificTopicRecord;
import no.systema.skat.skatexport.model.jsonjackson.topic.items.JsonSkatExportSpecificTopicItemRecord;
import no.systema.skat.model.jsonjackson.JsonSkatAutoControlErrorContainer;

/**
 * AutoControl Manager (Copied from Tvinn SAD Export)
 * @author oscardelatorre
 *
 * Apr 7, 2016
 */
public class SkatExportItemsAutoControlMgr {
	private static final Logger logger = Logger.getLogger(SkatExportItemsAutoControlMgr.class.getName());
	private UrlCgiProxyService urlCgiProxyService = null;
	private SkatExportSpecificTopicItemService skatExportSpecificTopicItemService = null;
	NumberFormatterLocaleAware formatter = new NumberFormatterLocaleAware();
	
	final String ANG_ART_20_ALU = "20";
	final String ANG_ART_21_FOU = "21";
	final String ANG_ART_22_SUP = "22";
	final String ANG_ART_23_SUPH = "23";
	final String ANG_ART_24_FUAA = "24";
	final String ANG_ART_25_FUAF = "25";
	final String ANG_ART_26_PRO = "26";
	final String ANG_ART_27_PROM = "27";
	final String ANG_ART_28_EFU = "28";
	final String ANG_ART_30_EXS = "30";
	final String ANG_ART_31_YM_FUAGo = "31";
	final String ANG_ART_32_YM_FUAAo = "32";
	final String ANG_ART_50_IE507 = "50";
	
	
	//Should be set after the constructor call
	private JsonSkatExportSpecificTopicItemRecord record = null;
	public void setRecord (JsonSkatExportSpecificTopicItemRecord recordToValidate){ 
		this.record = recordToValidate;
		this.validRecord = true;
	}
	//Set header record
	private JsonSkatExportSpecificTopicRecord headerRecord = null;
	public void setHeaderRecord (JsonSkatExportSpecificTopicRecord headerRecord){ 
		this.headerRecord = headerRecord;
	}
	
	//this is the global validRecord that will decide if the control passes
	private boolean validRecord = true;
	public boolean isValidRecord (){return this.validRecord;}
	
	public SkatExportItemsAutoControlMgr(UrlCgiProxyService urlCgiProxyService, SkatExportSpecificTopicItemService skatExportSpecificTopicItemService){
		this.urlCgiProxyService = urlCgiProxyService;
		this.skatExportSpecificTopicItemService = skatExportSpecificTopicItemService;
	}
	
	
	
	/**
	 * This method must comply with the section: ValidationUtils.rejectIfEmptyOrWhitespace in order to check the same mandatory fields as the normal validation method call 
	 *  
	 */
	
	public void checkValidMandatoryFields(){
		if( this.ANG_ART_20_ALU.equals(this.headerRecord.getDkeh_aart()) ){
			this.mandatoryFields_01();
			
		}else if( this.ANG_ART_21_FOU.equals(this.headerRecord.getDkeh_aart()) ){
			this.mandatoryFields_02();
			
		}else if( this.ANG_ART_22_SUP.equals(this.headerRecord.getDkeh_aart()) ){
			if(record.getDkev_35()==null || "".equals(record.getDkev_35())){ this.validRecord = false; }
			if(record.getDkev_402a()==null || "".equals(record.getDkev_402a())){ this.validRecord = false; }
			if(record.getDkev_403a()==null || "".equals(record.getDkev_403a())){ this.validRecord = false; }
			
		}else if( this.ANG_ART_23_SUPH.equals(this.headerRecord.getDkeh_aart()) ){
			if(record.getDkev_35()==null || "".equals(record.getDkev_35())){ this.validRecord = false; }
			if(record.getDkev_402a()==null || "".equals(record.getDkev_402a())){ this.validRecord = false; }
			if(record.getDkev_403a()==null || "".equals(record.getDkev_403a())){ this.validRecord = false; }
			
		}else if(this.ANG_ART_24_FUAA.equals(this.headerRecord.getDkeh_aart())){
			this.mandatoryFields_02();

		}else if(this.ANG_ART_25_FUAF.equals(this.headerRecord.getDkeh_aart())){
			this.mandatoryFields_02();

		}else if(this.ANG_ART_28_EFU.equals(this.headerRecord.getDkeh_aart())){
			this.mandatoryFields_02();
			
		}else if(this.ANG_ART_26_PRO.equals(this.headerRecord.getDkeh_aart())){
			this.mandatoryFields_02();
			
		}else if(this.ANG_ART_27_PROM.equals(this.headerRecord.getDkeh_aart())){
			this.mandatoryFields_02();
			
		}else if(this.ANG_ART_30_EXS.equals(this.headerRecord.getDkeh_aart())){
			if(record.getDkev_311()==null || "".equals(record.getDkev_311())){ this.validRecord = false; }
			if(record.getDkev_313()==null || "".equals(record.getDkev_313())){ this.validRecord = false; }
			if(record.getDkev_314()==null || "".equals(record.getDkev_314())){ this.validRecord = false; }
			if(record.getDkev_315()==null || "".equals(record.getDkev_315())){ this.validRecord = false; }
			if(record.getDkev_331()==null || "".equals(record.getDkev_331())){ this.validRecord = false; }
			if(record.getDkev_35()==null || "".equals(record.getDkev_35())){ this.validRecord = false; }
			if(record.getDkev_402a()==null || "".equals(record.getDkev_402a())){ this.validRecord = false; }
			if(record.getDkev_403a()==null || "".equals(record.getDkev_403a())){ this.validRecord = false; }
			
			
		}else if(this.ANG_ART_50_IE507.equals(this.headerRecord.getDkeh_aart())){
			
			
		}else if(this.ANG_ART_31_YM_FUAGo.equals(this.headerRecord.getDkeh_aart())){
			this.mandatoryFields_02();
			if(record.getDkev_34a()==null || "".equals(record.getDkev_34a())){ this.validRecord = false; }
			
		}else if(this.ANG_ART_32_YM_FUAAo.equals(this.headerRecord.getDkeh_aart())){
			this.mandatoryFields_02();
			if(record.getDkev_34a()==null || "".equals(record.getDkev_34a())){ this.validRecord = false; }
		}
		
		
	}
	/**
	 * 
	 */
	private void mandatoryFields_01(){
		if(record.getDkev_311()==null || "".equals(record.getDkev_311())){ this.validRecord = false; }
		if(record.getDkev_313()==null || "".equals(record.getDkev_313())){ this.validRecord = false; }
		if(record.getDkev_314()==null || "".equals(record.getDkev_314())){ this.validRecord = false; }
		if(record.getDkev_315()==null || "".equals(record.getDkev_315())){ this.validRecord = false; }
		if(record.getDkev_331()==null || "".equals(record.getDkev_331())){ this.validRecord = false; }
		if(record.getDkev_35()==null || "".equals(record.getDkev_35())){ this.validRecord = false; }
		if(record.getDkev_37()==null || "".equals(record.getDkev_37())){ this.validRecord = false; }
		if(record.getDkev_38()==null || "".equals(record.getDkev_38())){ this.validRecord = false; }
		if(record.getDkev_402a()==null || "".equals(record.getDkev_402a())){ this.validRecord = false; }
		if(record.getDkev_403a()==null || "".equals(record.getDkev_403a())){ this.validRecord = false; }
		if(record.getDkev_46()==null || "".equals(record.getDkev_46())){ this.validRecord = false; }
		
	}
	/**
	 * 
	 */
	private void mandatoryFields_02(){
		if(record.getDkev_311()==null || "".equals(record.getDkev_311())){ this.validRecord = false; }
		if(record.getDkev_313()==null || "".equals(record.getDkev_313())){ this.validRecord = false; }
		if(record.getDkev_314()==null || "".equals(record.getDkev_314())){ this.validRecord = false; }
		if(record.getDkev_315()==null || "".equals(record.getDkev_315())){ this.validRecord = false; }
		if(record.getDkev_331()==null || "".equals(record.getDkev_331())){ this.validRecord = false; }
		if(record.getDkev_35()==null || "".equals(record.getDkev_35())){ this.validRecord = false; }
		if(record.getDkev_37()==null || "".equals(record.getDkev_37())){ this.validRecord = false; }
		if(record.getDkev_38()==null || "".equals(record.getDkev_38())){ this.validRecord = false; }
		if(record.getDkev_402a()==null || "".equals(record.getDkev_402a())){ this.validRecord = false; }
		if(record.getDkev_403a()==null || "".equals(record.getDkev_403a())){ this.validRecord = false; }
		if(record.getDkev_46()==null || "".equals(record.getDkev_46())){ this.validRecord = false; }
		if(record.getDkev_42()==null || "".equals(record.getDkev_42())){ this.validRecord = false; }
	}
	
	/**
	 *
	 */
	public void checkValidGrossAndNetWeight(){
		//Gross must always be >= Net
		if(record.getDkev_35()!=null && !"".equals(record.getDkev_35())){
			if(record.getDkev_38()!=null && !"".equals(record.getDkev_38())){
				try{
					String grossFormatTmp = record.getDkev_35().replace(".", "");
					double grossWeight = Double.parseDouble(grossFormatTmp.replace(",", "."));
					String netFormatTmp = record.getDkev_38().replace(".", "");
					double netWeight = Double.parseDouble(netFormatTmp.replace(",", "."));
					
					//Net can not be > than Gross
					if (netWeight>grossWeight){
						this.validRecord = false;
					}
				}catch(Exception e){
					//just take a fantom hit here 
				}
			}
		}
		//-----------------------------------------
		//No decimals are allowed with weights > 1
		//-----------------------------------------
		//Gross
		if(record.getDkev_35()!=null && !"".equals(record.getDkev_35())){
			String grossFormatTmp = record.getDkev_35().replace(".", "");
			double grossWeight = Double.parseDouble(grossFormatTmp.replace(",", "."));
			if(grossWeight>1){
				if(grossWeight%1==0){
					//nothing since there are no decimals (mathematically)
				}else{
					this.validRecord = false;
				}
			}
		}
		//Net
		if(record.getDkev_38()!=null && !"".equals(record.getDkev_38())){
			String netFormatTmp = record.getDkev_38().replace(".", "");
			double netWeight = Double.parseDouble(netFormatTmp.replace(",", "."));
			if(netWeight>1){
				if(netWeight%1==0){
					//nothing since there are no decimals (mathematically)
				}else{
					this.validRecord = false;
				}
			}
		}
		
	}

	/**
	 *
	 */
	public void checkProviantRestrictions(){
		//----------------------
		//Proviant restrictions
		//----------------------
		if(this.ANG_ART_26_PRO.equals(this.headerRecord.getDkeh_aart()) || this.ANG_ART_27_PROM.equals(this.headerRecord.getDkeh_aart())){
			boolean match = false;
			String [] validKodes = {"99302400","99302700","99309900"};
			for(String code: validKodes){
				if(code.equals(record.getDkev_331())){
					match = true;
					break;
				}
			}
			if(!match){
				this.validRecord = false;
			}
		}
			
	}
	
	
	/**
	 * 
	 */
	public void checkSuppEnhetsvalue_41(){
		//41 either all or none
		if(record.getDkev_412()!=null && !"".equals(record.getDkev_412())){
			if(record.getDkev_411()!=null && !"".equals(record.getDkev_411())){
				//OK
			}else{
				this.validRecord = false;
			}
		}
		if(record.getDkev_411()!=null && !"".equals(record.getDkev_411())){
			if(record.getDkev_412()!=null && !"".equals(record.getDkev_412())){
				//OK
			}else{
				this.validRecord = false;
			}
		}
		
	}
	
	
	/**
	 *
	 */
	public void checkIdentAfOplag_49(){
		if(!"".equals(record.getDkev_37())){
			if(record.getDkev_37().startsWith("3171")){
				if("".equals(record.getDkev_49())){
					this.validRecord = false; 
				}else{
					//check if RegEx on 49. Ident. af oplag is correct
					if(!this.isValidRegExDkev_49(record.getDkev_49())){
						this.validRecord = false; 
					}
						
				}
			}
		}
		
	}
	/**
	 * 
	 * @param value
	 * @return
	 */
	private boolean isValidRegExDkev_49(String value) {
        boolean retVal = false;
        try {
            if (value != null) {
                //String nameRegex = "^(\\d{4})$";
                //String nameRegex = "^\\w{2}\\d{9}\\w{3}$";
            	    String nameRegex = "[a-zA-Z]{1}[0-9]{6}DK";
                Pattern namePattern = Pattern.compile(nameRegex);
                Matcher nameMatcher = namePattern.matcher(value);
                boolean matchFound = nameMatcher.find();
                if (matchFound) {
                    retVal = true;
                } 
            }
        } catch (Exception e) {
            e.printStackTrace();
            
        }
        return retVal;
    }
	
	
	
	/**
	 * 
	 * @param applicationUser
	 * @param errorFlag
	 */
	
	public void updateItemWithAutoControlError(String applicationUser, String errorFlag){
		String BASE_URL_UPDATE_ERR = SkatExportUrlDataStore.SKAT_EXPORT_BASE_UPDATE_SPECIFIC_TOPIC_ITEM_AUTOCONTROL_ERROR_URL;
		StringBuffer urlRequestParamsKeysAutoControl = new StringBuffer();
		urlRequestParamsKeysAutoControl.append("user=" + applicationUser);
		urlRequestParamsKeysAutoControl.append("&avd=" + this.record.getDkev_syav());
		urlRequestParamsKeysAutoControl.append("&opd=" + this.record.getDkev_syop());
		urlRequestParamsKeysAutoControl.append("&lin=" + this.record.getDkev_syli());
		if(errorFlag!=null){
			urlRequestParamsKeysAutoControl.append("&dkev_err=" + errorFlag);
		}else{
			urlRequestParamsKeysAutoControl.append("&dkev_err=");
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
		JsonSkatAutoControlErrorContainer container = this.skatExportSpecificTopicItemService.getSkatExportSpecificTopicItemAutoControlErrorContainer(jsonPayload);
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
		
		String BASE_URL_UPDATE = SkatExportUrlDataStore.SKAT_EXPORT_BASE_UPDATE_SPECIFIC_TOPIC_ITEM_URL;
		logger.info("[INFO] UPDATE (light) to be done with lineNr [svli]:" + this.record.getDkev_syli());
		
		StringBuffer urlRequestParamsKeys = new StringBuffer();
		urlRequestParamsKeys.append("user=" + applicationUser);
		urlRequestParamsKeys.append(SkatConstants.URL_CHAR_DELIMETER_FOR_PARAMS_WITH_HTML_REQUEST + "avd=" + this.record.getDkev_syav());
		urlRequestParamsKeys.append(SkatConstants.URL_CHAR_DELIMETER_FOR_PARAMS_WITH_HTML_REQUEST + "opd=" + this.record.getDkev_syop());
		urlRequestParamsKeys.append(SkatConstants.URL_CHAR_DELIMETER_FOR_PARAMS_WITH_HTML_REQUEST + "lin=" + this.record.getDkev_syli());
		urlRequestParamsKeys.append(SkatConstants.URL_CHAR_DELIMETER_FOR_PARAMS_WITH_HTML_REQUEST + "mode=U");
		urlRequestParamsKeys.append(SkatConstants.URL_CHAR_DELIMETER_FOR_PARAMS_WITH_HTML_REQUEST + "svligh=X"); //light update flag
		
		
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
	public void calculateNetWeight(JsonSkatExportSpecificTopicRecord headerRecord, SystemaWebUser appUser){
		  double grossNetFactor = 0.9; //default;
		  String grossWeight = this.record.getDkev_35();
		  String netWeight = this.record.getDkev_38();
		  try{
			  //Gross weight exists but not net weight
			  if(grossWeight!=null && !"".equals(grossWeight) && (netWeight==null || "".equals(netWeight))  ){
				  /*NOTE! this field does not exist in SKAT (as for today)
				  if(headerRecord.getSefvk()!=null && !"".equals(headerRecord.getSefvk())){
					  String tmp = headerRecord.getSefvk().replace("," , ".");
					  grossNetFactor = Double.parseDouble(tmp);
				  }*/
				  //operation
				  grossWeight = grossWeight.replace("," , ".");
				  double grossWeightDbl = Double.parseDouble(grossWeight);
				  double netWeightDbl = grossWeightDbl * grossNetFactor;
				  NumberFormatterLocaleAware formatter = new NumberFormatterLocaleAware();
				  netWeight = String.valueOf(formatter.getDoubleEuropeanFormat(netWeightDbl, 3, false));
				  //final result
				  this.record.setDkev_38(netWeight);
				  
			  }
			  
		  }catch (Exception e){
			  logger.info("[ERROR] on Net weight calculation - Auto control:" + e.toString());
		  }
		
	}
	
}
