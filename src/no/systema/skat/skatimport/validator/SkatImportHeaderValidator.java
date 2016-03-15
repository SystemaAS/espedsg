package no.systema.skat.skatimport.validator;

import java.lang.reflect.Array;
import java.util.*;

import org.springframework.validation.Validator;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;

import no.systema.main.util.DateTimeManager;
import no.systema.main.util.DateTimeManager;
import no.systema.main.validator.DateValidator;
import no.systema.skat.skatimport.model.jsonjackson.topic.JsonSkatImportSpecificTopicRecord;
import no.systema.tds.tdsimport.util.TdsImportCalculator;

/**
 * 
 * @author oscardelatorre
 * @date Jan 27, 2014
 *
 */
public class SkatImportHeaderValidator implements Validator {
	final String ANG_ART_FUA_01 = "01";
	final String ANG_ART_FOE_02 = "02";
	final String ANG_ART_SUP_03 = "03";
	final String ANG_ART_FUE_04 = "04";
	final String ANG_ART_FUET_05 = "05";
	final String ANG_ART_STF_07 = "07";
	final String ANG_ART_STFT_08 = "08";
	
	/**
	 * 
	 */
	public boolean supports(Class clazz) {
		return JsonSkatImportSpecificTopicRecord.class.isAssignableFrom(clazz); 
	}
	
	/**
	 * @param obj
	 * @param errors
	 * 
	 */
	public void validate(Object obj, Errors errors) { 
		JsonSkatImportSpecificTopicRecord record = (JsonSkatImportSpecificTopicRecord)obj;
		//Check for Mandatory fields first
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "dkih_syav", "systema.skat.import.header.error.null.dkih_syav");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "dkih_sysg", "systema.skat.import.header.error.null.dkih_sysg");
		//
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "dkih_r011", "systema.skat.import.header.error.null.dkih_r011");
		//ValidationUtils.rejectIfEmptyOrWhitespace(errors, "dkih_r012", "systema.skat.import.header.error.null.dkih_r012");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "dkih_aart", "systema.skat.import.header.error.null.dkih_aart");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "dkih_a", "systema.skat.import.header.error.null.dkih_a");
		//Afsender
		/*se nedan*/
		
		//Modtager
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "dkih_08a", "systema.skat.import.header.error.null.dkih_08a");
		//ValidationUtils.rejectIfEmptyOrWhitespace(errors, "dkih_08b", "systema.skat.import.header.error.null.dkih_08b");
		//ValidationUtils.rejectIfEmptyOrWhitespace(errors, "dkih_08c", "systema.skat.import.header.error.null.dkih_08c");
		//ValidationUtils.rejectIfEmptyOrWhitespace(errors, "dkih_08d", "systema.skat.import.header.error.null.dkih_08d");
		//ValidationUtils.rejectIfEmptyOrWhitespace(errors, "dkih_08e", "systema.skat.import.header.error.null.dkih_08e");
		//ValidationUtils.rejectIfEmptyOrWhitespace(errors, "dkih_08f", "systema.skat.import.header.error.null.dkih_08f");
		//Repræsentant
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "dkih_14a", "systema.skat.import.header.error.null.dkih_14a");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "dkih_14b", "systema.skat.import.header.error.null.dkih_14b");
		//Afsend.land
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "dkih_15", "systema.skat.import.header.error.null.dkih_15");

		//-----------------------------
		//Olika typer av Angivelsesart
		//-----------------------------
		if( this.ANG_ART_FUA_01.equals(record.getDkih_aart()) ){
			
			ValidationUtils.rejectIfEmptyOrWhitespace(errors, "dkih_dtm1", "systema.skat.import.header.error.null.dkih_dtm1");
			ValidationUtils.rejectIfEmptyOrWhitespace(errors, "dkih_181", "systema.skat.import.header.error.null.dkih_181");
			
			//30.x
			boolean b301_null = false; boolean b302_null = false; boolean b303_null = false; boolean b304_null = false;
			if( "".equals(record.getDkih_301()) ){
				b301_null=true;
			}
			if( "".equals(record.getDkih_302()) ){
				b302_null=true;
			}
			if( "".equals(record.getDkih_303()) ){
				b303_null=true;
			}
			if( "".equals(record.getDkih_304()) ){
				b304_null=true;
			}
			//alla måste vara ifyllda
			if(b301_null || b302_null || b303_null || b304_null){
				errors.rejectValue("dkih_301", "systema.skat.import.header.error.null.dkih_30");
			}
			
		}else if( this.ANG_ART_FOE_02.equals(record.getDkih_aart()) ){
			ValidationUtils.rejectIfEmptyOrWhitespace(errors, "dkih_r012", "systema.skat.import.header.error.null.dkih_r012");
			ValidationUtils.rejectIfEmptyOrWhitespace(errors, "dkih_25", "systema.skat.import.header.error.null.dkih_25"); 
			ValidationUtils.rejectIfEmptyOrWhitespace(errors, "dkih_dtm1", "systema.skat.import.header.error.null.dkih_dtm1"); 
			
		}else if( this.ANG_ART_SUP_03.equals(record.getDkih_aart()) ){
			ValidationUtils.rejectIfEmptyOrWhitespace(errors, "dkih_r012", "systema.skat.import.header.error.null.dkih_r012");
			ValidationUtils.rejectIfEmptyOrWhitespace(errors, "dkih_26", "systema.skat.import.header.error.null.dkih_26"); 
			ValidationUtils.rejectIfEmptyOrWhitespace(errors, "dkih_221", "systema.skat.import.header.error.null.dkih_221"); 
			ValidationUtils.rejectIfEmptyOrWhitespace(errors, "dkih_222", "systema.skat.import.header.error.null.dkih_222"); 
			//Afsender
			ValidationUtils.rejectIfEmptyOrWhitespace(errors, "dkih_02b", "systema.skat.import.header.error.null.dkih_02b");
			ValidationUtils.rejectIfEmptyOrWhitespace(errors, "dkih_02c", "systema.skat.import.header.error.null.dkih_02c");
			ValidationUtils.rejectIfEmptyOrWhitespace(errors, "dkih_02d", "systema.skat.import.header.error.null.dkih_02d");
			ValidationUtils.rejectIfEmptyOrWhitespace(errors, "dkih_02e", "systema.skat.import.header.error.null.dkih_02e");
			ValidationUtils.rejectIfEmptyOrWhitespace(errors, "dkih_02f", "systema.skat.import.header.error.null.dkih_02f");
			
		}else if( this.ANG_ART_FUE_04.equals(record.getDkih_aart()) ){
			ValidationUtils.rejectIfEmptyOrWhitespace(errors, "dkih_r012", "systema.skat.import.header.error.null.dkih_r012");
			ValidationUtils.rejectIfEmptyOrWhitespace(errors, "dkih_25", "systema.skat.import.header.error.null.dkih_25"); 
			ValidationUtils.rejectIfEmptyOrWhitespace(errors, "dkih_26", "systema.skat.import.header.error.null.dkih_26");
			ValidationUtils.rejectIfEmptyOrWhitespace(errors, "dkih_221", "systema.skat.import.header.error.null.dkih_221"); 
			ValidationUtils.rejectIfEmptyOrWhitespace(errors, "dkih_222", "systema.skat.import.header.error.null.dkih_222"); 
			ValidationUtils.rejectIfEmptyOrWhitespace(errors, "dkih_dtm1", "systema.skat.import.header.error.null.dkih_dtm1"); 
			//Afsender
			ValidationUtils.rejectIfEmptyOrWhitespace(errors, "dkih_02b", "systema.skat.import.header.error.null.dkih_02b");
			ValidationUtils.rejectIfEmptyOrWhitespace(errors, "dkih_02c", "systema.skat.import.header.error.null.dkih_02c");
			ValidationUtils.rejectIfEmptyOrWhitespace(errors, "dkih_02d", "systema.skat.import.header.error.null.dkih_02d");
			ValidationUtils.rejectIfEmptyOrWhitespace(errors, "dkih_02e", "systema.skat.import.header.error.null.dkih_02e");
			ValidationUtils.rejectIfEmptyOrWhitespace(errors, "dkih_02f", "systema.skat.import.header.error.null.dkih_02f");
			//Ref.nr could be null (since an external partner has already done a deklaration (MIO).
			//In this case the user should register the ref.nr. [Ref.nr ACW]
			/* Don't make this mandatory since it could be a problem to know which one is ALREADY registered...
			if("".equals(record.getDkih_07())){
				if("".equals(record.getDkih_07c()) ){
					errors.rejectValue("dkih_07c", "systema.skat.import.header.error.null.dkih_07c");
				}
			}*/
			
		}else if( this.ANG_ART_FUET_05.equals(record.getDkih_aart()) ){
			ValidationUtils.rejectIfEmptyOrWhitespace(errors, "dkih_r012", "systema.skat.import.header.error.null.dkih_r012");
			ValidationUtils.rejectIfEmptyOrWhitespace(errors, "dkih_dtm1", "systema.skat.import.header.error.null.dkih_dtm1"); 
			
		}else if(this.ANG_ART_STF_07.equals(record.getDkih_aart())){
			if( !"".equals(record.getDkih_07a()) || !"".equals(record.getDkih_07b()) || !"".equals(record.getDkih_07c()) || !"".equals(record.getDkih_07d()) ){
				//nothing
			}else{
				errors.rejectValue("dkih_07a", "systema.skat.import.header.error.null.dkih_07");
			}
			ValidationUtils.rejectIfEmptyOrWhitespace(errors, "dkih_r012", "systema.skat.import.header.error.null.dkih_r012");
			ValidationUtils.rejectIfEmptyOrWhitespace(errors, "dkih_26", "systema.skat.import.header.error.null.dkih_26"); 
			ValidationUtils.rejectIfEmptyOrWhitespace(errors, "dkih_221", "systema.skat.import.header.error.null.dkih_221"); 
			ValidationUtils.rejectIfEmptyOrWhitespace(errors, "dkih_222", "systema.skat.import.header.error.null.dkih_222"); 
			//Afsender
			ValidationUtils.rejectIfEmptyOrWhitespace(errors, "dkih_02b", "systema.skat.import.header.error.null.dkih_02b");
			ValidationUtils.rejectIfEmptyOrWhitespace(errors, "dkih_02c", "systema.skat.import.header.error.null.dkih_02c");
			ValidationUtils.rejectIfEmptyOrWhitespace(errors, "dkih_02d", "systema.skat.import.header.error.null.dkih_02d");
			ValidationUtils.rejectIfEmptyOrWhitespace(errors, "dkih_02e", "systema.skat.import.header.error.null.dkih_02e");
			ValidationUtils.rejectIfEmptyOrWhitespace(errors, "dkih_02f", "systema.skat.import.header.error.null.dkih_02f");
			
		}else if(this.ANG_ART_STFT_08.equals(record.getDkih_aart())){
			ValidationUtils.rejectIfEmptyOrWhitespace(errors, "dkih_r012", "systema.skat.import.header.error.null.dkih_r012");
		}

		//Logical (RULES) controls if we passed the NOT NULL errors
		if(!errors.hasFieldErrors()){
			
			if(record!=null){
				//------
				//dates 
				//------
				//Check for valid dates (when applicable)
				if(record.getDkih_dtm1()!=null && !"".equals(record.getDkih_dtm1())){
					//This can not be implemented in all status since the dtm1 could be back in time at the time the user sends a final Angivelse with
					//the final dtm2. Currently this dtm1 IS ACCEPTED several days back in time...
					//The only exception being status= empty or status="M"
					if(record.getDkih_syst()==null || "".equals(record.getDkih_syst()) || "M".equals(record.getDkih_syst()) ){
						String shortDkih_dtm1 = this.getShortDate(record.getDkih_dtm1());
						boolean isValidDate = new DateTimeManager().isValidCurrentAndForwardDate(shortDkih_dtm1, "yyyyMMdd");
						if(!isValidDate){
							errors.rejectValue("dkih_dtm1", "systema.skat.import.header.error.rule.ankomstTidDtm1NotValid"); 
						}
					}

					boolean isValidISOFormat = new DateValidator().validateDateIso203_YYYYMMDDhhmm(record.getDkih_dtm1());
					if(!isValidISOFormat){
						errors.rejectValue("dkih_dtm1", "systema.skat.import.header.error.rule.ankomstTidDtm1NotValidMask"); 
					}
				}
				if (record.getDkih_dtm2()!=null && !"".equals(record.getDkih_dtm2())){
					boolean isValidISOFormat = new DateValidator().validateDateIso203_YYYYMMDDhhmm(record.getDkih_dtm2());
					if(!isValidISOFormat){
						errors.rejectValue("dkih_dtm2", "systema.skat.import.header.error.rule.ankomstTidDtm2NotValidMask"); 
					}
				}
				//--------------
				//Angivelsestyp
				//--------------
				if( record.getDkih_15()!=null && !"".equals(record.getDkih_15()) ){ 
					if(!this.isValidAngivelseTypeForEftaCountry(record)){
						errors.rejectValue("dkih_15", "systema.skat.import.header.error.rule.eftaCountriesAngType"); 
					}
					if(!this.isValidAngivelseTypeForNoneEftaCountry(record)){
						errors.rejectValue("dkih_15", "systema.skat.import.header.error.rule.noneEftaCountriesAngType"); 
					}
				}
				
				//CVR/SE-nr Prefix (DK) requirement
				if( record.getDkih_08a()!=null && !"".equals(record.getDkih_08a()) ){ 
					if(!record.getDkih_08a().startsWith("DK")){
						errors.rejectValue("dkih_08a", "systema.skat.import.header.error.rule.cvrNrPrefixModtager"); 
					}
				}
				if( record.getDkih_14a()!=null && !"".equals(record.getDkih_14a()) ){ 
					if(!record.getDkih_14a().startsWith("DK")){
						errors.rejectValue("dkih_14a", "systema.skat.import.header.error.rule.cvrNrPrefixRepresentat"); 
					}
				}
				
				//Ajour.type rule
				if("8".equals(record.getDkih_ajou())){
					if( record.getDkih_bega()!=null && !"".equals(record.getDkih_bega()) ){
						//Valid and ok
					}else{
						errors.rejectValue("dkih_bega", "systema.skat.import.header.error.rule.ajourtype.fasthold"); 
					}
				}
				//Currency validation _221
				if( record.getDkih_221()!=null && !"".equals(record.getDkih_221()) ){ 
					if( !isValidCurrencyMandatoryFields (record)){ 
						errors.rejectValue("dkih_221", "systema.skat.import.header.error.rule.null.currencyMandatoryFields"); 
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
	private String getShortDate(String value){
		String retval = null;
		if(value!=null && !"".equals(value)){
			if(value.length()>8){
				try{
					retval = value.substring(0,8);
				}catch (Exception e){
					//nothing
				}
			}
		}
		
		return retval;
	}
	/**
	 * 
	 * @param record
	 * @return
	 */
	private boolean isValidCurrencyMandatoryFields(JsonSkatImportSpecificTopicRecord record){
		boolean retval = false;
		boolean _221b = false;
		boolean _221c = false;
		
		if(record.getDkih_221b()!=null && !"".equals(record.getDkih_221b())){
			_221b = true;
		}
		if(record.getDkih_221c()!=null && !"".equals(record.getDkih_221c())){
			_221c = true;
		}
		//test mandatory
		if(_221b && _221c){
			retval = true;
		}
		
		return retval;
	}
	
	/**
	 * 
	 * @param value
	 * @return
	 */
	private boolean isValidAngivelseTypeForEftaCountry(JsonSkatImportSpecificTopicRecord record){
		String[] eftaCountry = {"NO","IS","CH"};
		boolean retval = true;
		
		if(record!=null){
			String afsenderLand = record.getDkih_15();
			
			List<String> list = Arrays.asList(eftaCountry);
			for(String field: list){
				if(!"EU".equals(record.getDkih_r011())){
					if(field.equals(afsenderLand)){
						retval = false;
						break;
					}
				}
			}
		}
		return retval;
	}
	
	/**
	 * 
	 * @param value
	 * @return
	 */
	private boolean isValidAngivelseTypeForNoneEftaCountry(JsonSkatImportSpecificTopicRecord record){
		String[] eftaCountry = {"NO","IS","CH"};
		boolean retval = true;
		boolean eftaMatch = false;
		
		if(record!=null){
			String afsenderLand = record.getDkih_15();
			//Catch the possible Efta country (if any)
			List<String> list = Arrays.asList(eftaCountry);
			for(String field: list){
				//if("EU".equals(record.getDkih_r011())){
					if(field.equals(afsenderLand)){
						eftaMatch = true;
						break;
					}
				}
			}
			//Now check if valid
			if("EU".equals(record.getDkih_r011()) && !eftaMatch){
				retval = false;
			}
		
		return retval;
	}
	
}
