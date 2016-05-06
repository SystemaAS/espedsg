package no.systema.tvinn.sad.z.maintenance.sadimport.validator;

import org.apache.log4j.Logger;
import org.springframework.validation.Validator;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;

import no.systema.tvinn.sad.z.maintenance.sadimport.model.jsonjackson.dbtable.JsonMaintSadImportTariRecord;

/**
 * 
 * @author oscardelatorre
 * @date May 6, 2016
 * 
 *
 */
public class MaintSadImportSad010rValidator implements Validator {
	private static final Logger logger = Logger.getLogger(MaintSadImportSad010rValidator.class.getName());
	/**
	 * 
	 */
	public boolean supports(Class clazz) {
		return JsonMaintSadImportTariRecord.class.isAssignableFrom(clazz); 
	}
	
	/**
	 * @param obj
	 * @param errors
	 * 
	 */
	public void validate(Object obj, Errors errors) { 
		JsonMaintSadImportTariRecord record = (JsonMaintSadImportTariRecord)obj;
		
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "tatanr", "", "Tariffnr. (TATANR) er obligatorisk"); 
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "taalfa", "", "Søkebegrep (TAALFA) er obligatorisk"); 
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "tadtr", "", "Opd.dato (TADTR) er obligatorisk"); 
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "tadato", "", "F.o.m dato (TADATO) er obligatorisk"); 
		
	}
	/**
	 * 
	 * @param obj
	 * @param errors
	 */
	
	public void validateDelete(Object obj, Errors errors) { 
		
		JsonMaintSadImportTariRecord record = (JsonMaintSadImportTariRecord)obj;
		logger.info(record.getTatanr());
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "tatanr", "", "Tariffnr. (TATANR) er obligatorisk"); 
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "taalfa", "", "Søkebegrep (TAALFA) er obligatorisk"); 
		

	}
}
