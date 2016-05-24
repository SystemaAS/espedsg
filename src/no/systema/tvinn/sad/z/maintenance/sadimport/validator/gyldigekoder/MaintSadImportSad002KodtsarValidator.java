package no.systema.tvinn.sad.z.maintenance.sadimport.validator.gyldigekoder;

import org.apache.log4j.Logger;
import org.springframework.validation.Validator;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;

import no.systema.main.util.NumberFormatterLocaleAware;
import no.systema.tvinn.sad.z.maintenance.sadimport.model.jsonjackson.dbtable.gyldigekoder.JsonMaintSadImportKodtsaRecord;

/**
 * 
 * @author oscardelatorre
 * @date May 24, 2016
 * 
 *
 */
public class MaintSadImportSad002KodtsarValidator implements Validator {
	private static final Logger logger = Logger.getLogger(MaintSadImportSad002KodtsarValidator.class.getName());
	private NumberFormatterLocaleAware numberFormatter = new NumberFormatterLocaleAware();
	/**
	 * 
	 */
	public boolean supports(Class clazz) {
		return JsonMaintSadImportKodtsaRecord.class.isAssignableFrom(clazz); 
	}
	
	/**
	 * @param obj
	 * @param errors
	 * 
	 */
	public void validate(Object obj, Errors errors) { 
		JsonMaintSadImportKodtsaRecord record = (JsonMaintSadImportKodtsaRecord)obj;
		
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "ksakd", "", "Kode (KSAKD) er obligatorisk"); 
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "ksaft", "", "Fritekst (KSAFT) er obligatorisk"); 
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
		
		JsonMaintSadImportKodtsaRecord record = (JsonMaintSadImportKodtsaRecord)obj;
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "ksakd", "", "Kode (KSAKD) er obligatorisk"); 
		
	}
	
}
