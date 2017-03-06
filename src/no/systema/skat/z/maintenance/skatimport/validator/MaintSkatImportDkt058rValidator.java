package no.systema.skat.z.maintenance.skatimport.validator;

import org.apache.log4j.Logger;
import org.springframework.validation.Validator;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;

import no.systema.main.util.NumberFormatterLocaleAware;
import no.systema.skat.z.maintenance.skatimport.model.jsonjackson.dbtable.JsonMaintDktseContainer;
import no.systema.skat.z.maintenance.skatimport.model.jsonjackson.dbtable.JsonMaintDktseRecord;


/**
 * 
 * @author oscardelatorre
 * @date Mar 6, 2017
 * 
 *
 */
public class MaintSkatImportDkt058rValidator implements Validator {
	private static final Logger logger = Logger.getLogger(MaintSkatImportDkt058rValidator.class.getName());
	private NumberFormatterLocaleAware numberFormatter = new NumberFormatterLocaleAware();
	/**
	 * 
	 */
	public boolean supports(Class clazz) {
		return JsonMaintDktseRecord.class.isAssignableFrom(clazz); 
	}
	
	/**
	 * @param obj
	 * @param errors
	 * 
	 */
	public void validate(Object obj, Errors errors) { 
		JsonMaintDktseRecord record = (JsonMaintDktseRecord)obj;
		
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "dkse_knr", "", "Kundenr er obligatorisk"); 
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "dkse_331", "", "Varekode er obligatorisk"); 
		
		//Logical (RULES) controls if we passed the NOT NULL errors
		if(!errors.hasFieldErrors()){
			if(record!=null){
				//N/A
			}
		}
	}
	/**
	 * 
	 * @param obj
	 * @param errors
	 */
	
	public void validateDelete(Object obj, Errors errors) { 
		
		JsonMaintDktseRecord record = (JsonMaintDktseRecord)obj;
		//logger.info(record.getTariff());
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "dkse_knr", "", "Kundenr er obligatorisk"); 
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "dkse_331", "", "Varekode er obligatorisk"); 
		
	}

}
