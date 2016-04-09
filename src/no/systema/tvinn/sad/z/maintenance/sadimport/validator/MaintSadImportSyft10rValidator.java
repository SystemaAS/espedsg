package no.systema.tvinn.sad.z.maintenance.sadimport.validator;

import org.apache.log4j.Logger;
import org.springframework.validation.Validator;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;

import no.systema.tvinn.sad.z.maintenance.sadimport.model.jsonjackson.dbtable.JsonMaintSadImportKodtsiRecord;

/**
 * 
 * @author oscardelatorre
 * @date Apr 9, 2016
 * 
 *
 */
public class MaintSadImportSyft10rValidator implements Validator {
	private static final Logger logger = Logger.getLogger(MaintSadImportSyft10rValidator.class.getName());
	/**
	 * 
	 */
	public boolean supports(Class clazz) {
		return JsonMaintSadImportKodtsiRecord.class.isAssignableFrom(clazz); 
	}
	
	/**
	 * @param obj
	 * @param errors
	 * 
	 */
	public void validate(Object obj, Errors errors) { 
		JsonMaintSadImportKodtsiRecord record = (JsonMaintSadImportKodtsiRecord)obj;
		/* TODO
		logger.info(record.getKlikod());
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "klikod", "", "KLIKOD er obligatorisk"); 
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "klinav", "", "KLINAV er obligatorisk"); 
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "klisto", "", "KLISTO er obligatorisk"); 
		*/
	}
	/**
	 * 
	 * @param obj
	 * @param errors
	 */
	public void validateDelete(Object obj, Errors errors) { 
		/* TODO
		JsonMaintSadImportKodtsiRecord record = (JsonMaintSadImportKodtsiRecord)obj;
		logger.info(record.getKlikod());
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "klikod", "", "KLIKOD er obligatorisk"); 
		*/
	}
}
