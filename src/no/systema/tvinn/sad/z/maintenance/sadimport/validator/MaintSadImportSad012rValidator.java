package no.systema.tvinn.sad.z.maintenance.sadimport.validator;

import org.apache.log4j.Logger;
import org.springframework.validation.Validator;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;

import no.systema.tvinn.sad.z.maintenance.sadimport.model.jsonjackson.dbtable.JsonMaintSadImportKodtlbRecord;

/**
 * 
 * @author oscardelatorre
 * @date Apr 29, 2016
 * 
 *
 */
public class MaintSadImportSad012rValidator implements Validator {
	private static final Logger logger = Logger.getLogger(MaintSadImportSad012rValidator.class.getName());
	/**
	 * 
	 */
	public boolean supports(Class clazz) {
		return JsonMaintSadImportKodtlbRecord.class.isAssignableFrom(clazz); 
	}
	
	/**
	 * @param obj
	 * @param errors
	 * 
	 */
	public void validate(Object obj, Errors errors) { 
		JsonMaintSadImportKodtlbRecord record = (JsonMaintSadImportKodtlbRecord)obj;
		
		//logger.info(record.getKsisig());
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "klbkod", "", "Lev.kode (KLBKOD) er obligatorisk"); 
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "klbkt", "", "Lev.tekst (KLBKT) er obligatorisk"); 
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "klbfok", "", "Fors.kode (KLBFOK) er obligatorisk"); 
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "klbfrk", "", "Fr.kode (KLBFRK) er obligatorisk"); 
		
	}
	/**
	 * 
	 * @param obj
	 * @param errors
	 */
	public void validateDelete(Object obj, Errors errors) { 
		JsonMaintSadImportKodtlbRecord record = (JsonMaintSadImportKodtlbRecord)obj;
		logger.info(record.getKlbkod());
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "klbkod", "", "Lev.kode (KLBKOD) er obligatorisk"); 
	}
}
