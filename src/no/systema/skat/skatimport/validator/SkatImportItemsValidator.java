	package no.systema.skat.skatimport.validator;

import org.springframework.validation.Validator;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;

import no.systema.skat.skatimport.model.jsonjackson.topic.items.JsonSkatImportSpecificTopicItemRecord;

/**
 * 
 * @author oscardelatorre
 * Feb 2, 2014
 */
public class SkatImportItemsValidator implements Validator {
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
		return JsonSkatImportSpecificTopicItemRecord.class.isAssignableFrom(clazz); 
	}
	
	/**
	 * 
	 * @param obj
	 * @param errors
	 * 
	 */
	public void validate(Object obj, Errors errors) { 
		
		JsonSkatImportSpecificTopicItemRecord record = (JsonSkatImportSpecificTopicItemRecord)obj;
		
		
		if( this.ANG_ART_FUA_01.equals(record.getHeader_dkih_aart()) ){
			ValidationUtils.rejectIfEmptyOrWhitespace(errors, "dkiv_315a", "systema.skat.import.header.error.null.item.dkiv_31_5");
			ValidationUtils.rejectIfEmptyOrWhitespace(errors, "dkiv_35", "systema.skat.import.header.error.null.item.dkiv_35"); 
			
		}else if( this.ANG_ART_FOE_02.equals(record.getHeader_dkih_aart()) ){
			ValidationUtils.rejectIfEmptyOrWhitespace(errors, "dkiv_311a", "systema.skat.import.header.error.null.item.dkiv_31_1");
			ValidationUtils.rejectIfEmptyOrWhitespace(errors, "dkiv_313a", "systema.skat.import.header.error.null.item.dkiv_31_3");
			ValidationUtils.rejectIfEmptyOrWhitespace(errors, "dkiv_314a", "systema.skat.import.header.error.null.item.dkiv_31_4");
			ValidationUtils.rejectIfEmptyOrWhitespace(errors, "dkiv_315a", "systema.skat.import.header.error.null.item.dkiv_31_5");
			ValidationUtils.rejectIfEmptyOrWhitespace(errors, "dkiv_331", "systema.skat.import.header.error.null.item.dkiv_33_1"); 
			ValidationUtils.rejectIfEmptyOrWhitespace(errors, "dkiv_34", "systema.skat.import.header.error.null.item.dkiv_34"); 
			ValidationUtils.rejectIfEmptyOrWhitespace(errors, "dkiv_35", "systema.skat.import.header.error.null.item.dkiv_35"); 
			ValidationUtils.rejectIfEmptyOrWhitespace(errors, "dkiv_46", "systema.skat.import.header.error.null.item.dkiv_46");
			ValidationUtils.rejectIfEmptyOrWhitespace(errors, "dkiv_402a", "systema.skat.import.header.error.null.item.dkiv_40_2");
			ValidationUtils.rejectIfEmptyOrWhitespace(errors, "dkiv_403a", "systema.skat.import.header.error.null.item.dkiv_40_3");
			
		}else if( this.ANG_ART_SUP_03.equals(record.getHeader_dkih_aart()) ){
			ValidationUtils.rejectIfEmptyOrWhitespace(errors, "dkiv_36", "systema.skat.import.header.error.null.item.dkiv_36");
			ValidationUtils.rejectIfEmptyOrWhitespace(errors, "dkiv_37", "systema.skat.import.header.error.null.item.dkiv_37");
			ValidationUtils.rejectIfEmptyOrWhitespace(errors, "dkiv_38", "systema.skat.import.header.error.null.item.dkiv_38");
			ValidationUtils.rejectIfEmptyOrWhitespace(errors, "dkiv_42", "systema.skat.import.header.error.null.item.dkiv_42");
			ValidationUtils.rejectIfEmptyOrWhitespace(errors, "dkiv_46", "systema.skat.import.header.error.null.item.dkiv_46");
			
		}else if( this.ANG_ART_FUE_04.equals(record.getHeader_dkih_aart()) ){
			ValidationUtils.rejectIfEmptyOrWhitespace(errors, "dkiv_311a", "systema.skat.import.header.error.null.item.dkiv_31_1");
			ValidationUtils.rejectIfEmptyOrWhitespace(errors, "dkiv_313a", "systema.skat.import.header.error.null.item.dkiv_31_3");
			ValidationUtils.rejectIfEmptyOrWhitespace(errors, "dkiv_314a", "systema.skat.import.header.error.null.item.dkiv_31_4");
			ValidationUtils.rejectIfEmptyOrWhitespace(errors, "dkiv_315a", "systema.skat.import.header.error.null.item.dkiv_31_5");
			ValidationUtils.rejectIfEmptyOrWhitespace(errors, "dkiv_331", "systema.skat.import.header.error.null.item.dkiv_33_1"); 
			ValidationUtils.rejectIfEmptyOrWhitespace(errors, "dkiv_34", "systema.skat.import.header.error.null.item.dkiv_34"); 
			ValidationUtils.rejectIfEmptyOrWhitespace(errors, "dkiv_35", "systema.skat.import.header.error.null.item.dkiv_35"); 
			ValidationUtils.rejectIfEmptyOrWhitespace(errors, "dkiv_36", "systema.skat.import.header.error.null.item.dkiv_36");
			ValidationUtils.rejectIfEmptyOrWhitespace(errors, "dkiv_37", "systema.skat.import.header.error.null.item.dkiv_37");
			ValidationUtils.rejectIfEmptyOrWhitespace(errors, "dkiv_38", "systema.skat.import.header.error.null.item.dkiv_38");
			ValidationUtils.rejectIfEmptyOrWhitespace(errors, "dkiv_42", "systema.skat.import.header.error.null.item.dkiv_42");
			ValidationUtils.rejectIfEmptyOrWhitespace(errors, "dkiv_46", "systema.skat.import.header.error.null.item.dkiv_46");
			ValidationUtils.rejectIfEmptyOrWhitespace(errors, "dkiv_402a", "systema.skat.import.header.error.null.item.dkiv_40_2");
			ValidationUtils.rejectIfEmptyOrWhitespace(errors, "dkiv_403a", "systema.skat.import.header.error.null.item.dkiv_40_3");
			
		}else if( this.ANG_ART_FUET_05.equals(record.getHeader_dkih_aart()) ){
			ValidationUtils.rejectIfEmptyOrWhitespace(errors, "dkiv_311a", "systema.skat.import.header.error.null.item.dkiv_31_1");
			ValidationUtils.rejectIfEmptyOrWhitespace(errors, "dkiv_35", "systema.skat.import.header.error.null.item.dkiv_35"); 
			ValidationUtils.rejectIfEmptyOrWhitespace(errors, "dkiv_38", "systema.skat.import.header.error.null.item.dkiv_38");
			ValidationUtils.rejectIfEmptyOrWhitespace(errors, "dkiv_46", "systema.skat.import.header.error.null.item.dkiv_46");
			ValidationUtils.rejectIfEmptyOrWhitespace(errors, "dkiv_402a", "systema.skat.import.header.error.null.item.dkiv_40_2");
			ValidationUtils.rejectIfEmptyOrWhitespace(errors, "dkiv_403a", "systema.skat.import.header.error.null.item.dkiv_40_3");
			
		}else if( this.ANG_ART_STF_07.equals(record.getHeader_dkih_aart()) ){
			ValidationUtils.rejectIfEmptyOrWhitespace(errors, "dkiv_311a", "systema.skat.import.header.error.null.item.dkiv_31_1");
			ValidationUtils.rejectIfEmptyOrWhitespace(errors, "dkiv_313a", "systema.skat.import.header.error.null.item.dkiv_31_3");
			ValidationUtils.rejectIfEmptyOrWhitespace(errors, "dkiv_314a", "systema.skat.import.header.error.null.item.dkiv_31_4");
			ValidationUtils.rejectIfEmptyOrWhitespace(errors, "dkiv_315a", "systema.skat.import.header.error.null.item.dkiv_31_5");
			ValidationUtils.rejectIfEmptyOrWhitespace(errors, "dkiv_331", "systema.skat.import.header.error.null.item.dkiv_33_1"); 
			ValidationUtils.rejectIfEmptyOrWhitespace(errors, "dkiv_34", "systema.skat.import.header.error.null.item.dkiv_34");
			ValidationUtils.rejectIfEmptyOrWhitespace(errors, "dkiv_35", "systema.skat.import.header.error.null.item.dkiv_35"); 
			ValidationUtils.rejectIfEmptyOrWhitespace(errors, "dkiv_36", "systema.skat.import.header.error.null.item.dkiv_36");
			ValidationUtils.rejectIfEmptyOrWhitespace(errors, "dkiv_37", "systema.skat.import.header.error.null.item.dkiv_37");
			ValidationUtils.rejectIfEmptyOrWhitespace(errors, "dkiv_38", "systema.skat.import.header.error.null.item.dkiv_38");
			ValidationUtils.rejectIfEmptyOrWhitespace(errors, "dkiv_42", "systema.skat.import.header.error.null.item.dkiv_42");
			ValidationUtils.rejectIfEmptyOrWhitespace(errors, "dkiv_46", "systema.skat.import.header.error.null.item.dkiv_46");
			ValidationUtils.rejectIfEmptyOrWhitespace(errors, "dkiv_402a", "systema.skat.import.header.error.null.item.dkiv_40_2");
			ValidationUtils.rejectIfEmptyOrWhitespace(errors, "dkiv_403a", "systema.skat.import.header.error.null.item.dkiv_40_3");
			
		}else if( this.ANG_ART_STFT_08.equals(record.getHeader_dkih_aart()) ){
			ValidationUtils.rejectIfEmptyOrWhitespace(errors, "dkiv_38", "systema.skat.import.header.error.null.item.dkiv_38");
			ValidationUtils.rejectIfEmptyOrWhitespace(errors, "dkiv_46", "systema.skat.import.header.error.null.item.dkiv_46");
			
		}
		
		//Some dependency validations
		/* COVI CB TODO
		if(record.getDkiv_446a()!=null && !"".equals(record.getDkiv_446a())){
			if(record.getDkiv_446b()!=null && !"".equals(record.getDkiv_446b())){
				//OK
			}else{
				errors.rejectValue("dkiv_446a", "systema.skat.import.header.error.null.item.dkiv_44_6");
			}
		}
		*/
		//41 either all or none
		if(record.getDkiv_412()!=null && !"".equals(record.getDkiv_412())){
			if(record.getDkiv_411()!=null && !"".equals(record.getDkiv_411())){
				//OK
			}else{
				errors.rejectValue("dkiv_412", "systema.skat.import.header.error.null.item.dkiv_41_2");
			}
		}
		if(record.getDkiv_411()!=null && !"".equals(record.getDkiv_411())){
			if(record.getDkiv_412()!=null && !"".equals(record.getDkiv_412())){
				//OK
			}else{
				errors.rejectValue("dkiv_412", "systema.skat.import.header.error.null.item.dkiv_41_2");
			}
		}
		
		//Logical (RULES) controls if we passed the NOT NULL errors
		if(!errors.hasFieldErrors()){
			if(record!=null){
				
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
								errors.rejectValue("dkiv_38", "systema.skat.import.header.error.rule.item.netWeightTooBig");
							}
						}catch(Exception e){
							//just take a phantom hit here 
						}
					}
				}
			}
			//Certifikat check on parent-child values
			if(this.manyCerifikatExist(record)){
				if(!this.isValidCertifikatValues(record)){
					errors.rejectValue("dkiv_442a", "systema.skat.import.header.error.rule.item.certificatValues");
				}
			}
			//Copy elements from start-line-nr to end-line-nr
			if(record.getCopyLineStartLineNr()!=null && !"".equals(record.getCopyLineStartLineNr()) ){
				//TODO COVI convert to Int and compare both...
				if(!this.isValidEndLineNr(record.getCopyLineStartLineNr(), record.getCopyLineEndLineNr()) ){
					errors.rejectValue("copyLineEndLineNr", "systema.skat.import.header.error.rule.item.copyLineStartLineEnd.ilogical");
				}	
			}
		}
	}
	/**
	 * 
	 * @param startLineNr
	 * @param endLineNr
	 * @return
	 */
	private boolean isValidEndLineNr(String startLineNr, String endLineNr){
		boolean retval = false;
		Integer startLineNrInt = 0;
		Integer endLineNrInt = 0;
		
		try{
			if (startLineNr!=null && !"".equals(startLineNr)){
				if (endLineNr!=null && !"".equals(endLineNr)){
					startLineNrInt = Integer.parseInt(startLineNr);
					endLineNrInt = Integer.parseInt(endLineNr);
					if(endLineNrInt>=startLineNrInt){
						retval = true;
					}
				}
			}
		}catch(Exception e){
			//nothing...
		}
		
		return retval;
	}
	/**
	 * 
	 * @param record
	 * @return
	 */
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
	/**
	 * 
	 * @param record
	 * @return
	 */
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
	
}
