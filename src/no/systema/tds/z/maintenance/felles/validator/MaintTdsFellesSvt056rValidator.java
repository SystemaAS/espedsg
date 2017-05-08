package no.systema.tds.z.maintenance.felles.validator;

import org.apache.log4j.Logger;
import org.springframework.validation.Validator;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;

import no.systema.main.util.NumberFormatterLocaleAware;
import no.systema.main.validator.DateValidator;
import no.systema.tds.z.maintenance.main.model.jsonjackson.dbtable.JsonMaintSvthaRecord;

/**
 * 
 * @author oscardelatorre
 * @date May 08, 2017
 * 
 *
 */
public class MaintTdsFellesSvt056rValidator implements Validator {
	private static final Logger logger = Logger.getLogger(MaintTdsFellesSvt056rValidator.class.getName());
	private NumberFormatterLocaleAware numberFormatter = new NumberFormatterLocaleAware();
	/**
	 * 
	 */
	public boolean supports(Class clazz) {
		return JsonMaintSvthaRecord.class.isAssignableFrom(clazz); 
	}
	
	/**
	 * @param obj
	 * @param errors
	 * 
	 */
	public void validate(Object obj, Errors errors) { 
		JsonMaintSvthaRecord record = (JsonMaintSvthaRecord)obj;
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "svth_sysg", "", " Signatur är obligatorisk"); 
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "svth_namn", "", " Namn är obligatorisk"); 
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "svth_usid", "", " AS400 Userid är obligatorisk"); 
		
		
		//Logical (RULES) controls if we passed the NOT NULL errors
		if(!errors.hasFieldErrors()){
			if(record!=null){
				
				//TODO validity
				/*
				if (!record.isValidSenderId()){
					errors.rejectValue("validSenderId", " ", " UNB - Avsändarid är ogiltigt"); 
				}
				if (!record.isValidReceiverId()){
					errors.rejectValue("validReceiverId", " ", " UNB - Mottag.id är ogiltigt"); 
				}
				if (!record.isValidSmsUserId()){
					errors.rejectValue("validSmsUserId", " ", " SMS-sender Userid/Adress är ogiltigt"); 
				}
				*/
			}
		}
	}
	/**
	 * 
	 * @param obj
	 * @param errors
	 */
	
	public void validateDelete(Object obj, Errors errors) { 
		// N/A
		
	}
}
