package no.systema.skat.z.maintenance.felles.validator;

import org.apache.log4j.Logger;
import org.springframework.validation.Validator;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;

import no.systema.main.util.NumberFormatterLocaleAware;
import no.systema.main.validator.DateValidator;
import no.systema.skat.z.maintenance.main.model.jsonjackson.dbtable.JsonMaintDkthaRecord;

/**
 * 
 * @author oscardelatorre
 * @date Mar 21, 2017
 * 
 *
 */
public class MaintSkatFellesDkt056Validator implements Validator {
	private static final Logger logger = Logger.getLogger(MaintSkatFellesDkt056Validator.class.getName());
	private NumberFormatterLocaleAware numberFormatter = new NumberFormatterLocaleAware();
	/**
	 * 
	 */
	public boolean supports(Class clazz) {
		return JsonMaintDkthaRecord.class.isAssignableFrom(clazz); 
	}
	
	/**
	 * @param obj
	 * @param errors
	 * 
	 */
	public void validate(Object obj, Errors errors) { 
		JsonMaintDkthaRecord record = (JsonMaintDkthaRecord)obj;

		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "dkth_sysg", "", "Signatur er obligatorisk"); 
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "dkth_namn", "", "Navn er obligatorisk"); 
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "dkth_usid", "", "Userid er obligatorisk"); 

		
		//Logical (RULES) controls if we passed the NOT NULL errors
		if(!errors.hasFieldErrors()){
			if(record!=null){
				//TODO validation of parent Sign
				if (!record.isValidSignature()){
					errors.rejectValue("validSignature", " ", "Signatur er ugyldig"); 
				}
			}
		}
	}
	/**
	 * 
	 * @param obj
	 * @param errors
	 */
	
	public void validateDelete(Object obj, Errors errors) { 
		JsonMaintDkthaRecord record = (JsonMaintDkthaRecord)obj;
		
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "dkth_sysg", "", "Sign er obligatorisk"); 
		
		
	}
}
