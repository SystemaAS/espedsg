package no.systema.tvinn.sad.z.maintenance.nctsexport.validator;

import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import no.systema.tvinn.sad.z.maintenance.nctsexport.model.jsonjackson.dbtable.JsonMaintNctsTrkodfRecord;
import no.systema.tvinn.sad.z.maintenance.nctsexport.model.jsonjackson.dbtable.JsonMaintNctsTrughRecord;

/**
 * 
 * @author Fredrik Möller
 * @date Okt 17, 2016
 * 
 *
 */
public class MaintNctsExportTr001rValidator implements Validator {

	public boolean supports(Class clazz) {
		return JsonMaintNctsTrughRecord.class.isAssignableFrom(clazz); 
	}
	
	/**
	 * Validate on mandatory fields, in GUI.
	 * 
	 */
	public void validate(Object obj, Errors errors) { 
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "tkunik", "", "Kodetyp er obligatorisk"); 
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "tkkode", "", "Kode er obligatorisk"); 
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "tktxtn", "", "Beskrivelse(norsk) er obligatorisk"); 
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "tktxte", "", "Beskrivelse(engelsk) er obligatorisk"); 
		
	}

	public void validateDelete(Object obj, Errors errors) { 
		
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "tkunik", "", "Kodetyp er obligatorisk"); 
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "tkkode", "", "Kode er obligatorisk"); 
		
	}
	
	
}
