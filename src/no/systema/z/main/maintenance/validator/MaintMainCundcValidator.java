package no.systema.z.main.maintenance.validator;

import org.apache.log4j.Logger;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import no.systema.z.main.maintenance.model.jsonjackson.dbtable.JsonMaintMainCundcRecord;

/**
 * 
 * @author Fredrik Möller
 * @date Okt 28, 2016
 * 
 *
 */
public class MaintMainCundcValidator implements Validator {
	private static final Logger logger = Logger.getLogger(MaintMainCundcValidator.class.getName());

	@SuppressWarnings("rawtypes")
	public boolean supports(Class clazz) {
		return JsonMaintMainCundcRecord.class.isAssignableFrom(clazz); 
	}
	
	public void validate(Object obj, Errors errors) { 
		JsonMaintMainCundcRecord record = (JsonMaintMainCundcRecord)obj;
		
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "cfirma", "systema.maint.kunderegister.error.firma"); 
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "ccompn", "systema.maint.kunderegister.error.kundnr");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "cconta", "systema.maint.kunderegister.error.cconta");

		//Logical (RULES) controls if we passed the NOT NULL errors
		if(!errors.hasFieldErrors()){
			if(record!=null){
					//TODO: maybe
				}
		}
		
	}
	
	public void validateDelete(Object obj, Errors errors) { 
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "cfirma", "systema.maint.kunderegister.error.firma"); 
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "ccompn", "systema.maint.kunderegister.error.kundnr");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "cconta", "systema.maint.kunderegister.error.cconta");
		
		
	}
	
}
