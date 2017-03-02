package no.systema.skat.z.maintenance.skatexport.validator;

import org.apache.log4j.Logger;
import org.springframework.validation.Validator;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;

import no.systema.main.util.NumberFormatterLocaleAware;
import no.systema.skat.z.maintenance.skatexport.model.jsonjackson.dbtable.JsonMaintDktkdContainer;
import no.systema.skat.z.maintenance.skatexport.model.jsonjackson.dbtable.JsonMaintDktkdRecord;


/**
 * 
 * @author oscardelatorre
 * @date Mar 2, 2017
 * 
 *
 */
public class MaintSkatExportDkg210dValidator implements Validator {
	private static final Logger logger = Logger.getLogger(MaintSkatExportDkg210dValidator.class.getName());
	private NumberFormatterLocaleAware numberFormatter = new NumberFormatterLocaleAware();
	/**
	 * 
	 */
	public boolean supports(Class clazz) {
		return JsonMaintDktkdRecord.class.isAssignableFrom(clazz); 
	}
	
	/**
	 * @param obj
	 * @param errors
	 * 
	 */
	public void validate(Object obj, Errors errors) { 
		JsonMaintDktkdRecord record = (JsonMaintDktkdRecord)obj;
		
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "dkkd_typ", "", "Id-Key er obligatorisk"); 
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "dkkd_kd", "", "Kode er obligatorisk"); 
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "dkkd_txt", "", "Tekst er obligatorisk"); 
		
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
		
		JsonMaintDktkdRecord record = (JsonMaintDktkdRecord)obj;
		//logger.info(record.getTariff());
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "dkkd_typ", "", "Id-Key er obligatorisk"); 
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "dkkd_kd", "", "Kode er obligatorisk"); 

	}

}
