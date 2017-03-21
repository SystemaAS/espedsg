package no.systema.skat.z.maintenance.felles.validator;

import org.apache.log4j.Logger;
import org.springframework.validation.Validator;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;

import no.systema.main.util.NumberFormatterLocaleAware;
import no.systema.main.validator.DateValidator;
import no.systema.skat.z.maintenance.main.model.jsonjackson.dbtable.JsonMaintDktfiRecord;

/**
 * 
 * @author oscardelatorre
 * @date Mar 14, 2017
 * 
 *
 */
public class MaintSkatFellesDkt055rValidator implements Validator {
	private static final Logger logger = Logger.getLogger(MaintSkatFellesDkt055rValidator.class.getName());
	private NumberFormatterLocaleAware numberFormatter = new NumberFormatterLocaleAware();
	/**
	 * 
	 */
	public boolean supports(Class clazz) {
		return JsonMaintDktfiRecord.class.isAssignableFrom(clazz); 
	}
	
	/**
	 * @param obj
	 * @param errors
	 * 
	 */
	public void validate(Object obj, Errors errors) { 
		JsonMaintDktfiRecord record = (JsonMaintDktfiRecord)obj;
		final String TEST = "TEST";
		final String PROD = "PROD";
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "dktf_0004t", "", TEST + " UNB - Afsender id  er obligatorisk"); 
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "dktf_0010t", "", TEST + " UNB - Modtager id  er obligatorisk"); 
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "dktf_0022t", "", TEST + " UNB - Kodeord er obligatorisk"); 
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "dktf_ftipt", "", TEST + " FTP - Bruger id er obligatorisk"); 
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "dktf_ftust", "", TEST + " FTP - Kodeord er obligatorisk"); 
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "dktf_ftpwt", "", TEST + " FTP - Adresse er obligatorisk"); 
		//PROD
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "dktf_0004p", "", PROD + " UNB - Afsender id  er obligatorisk"); 
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "dktf_0010p", "", PROD + " UNB - Modtager id  er obligatorisk"); 
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "dktf_0022p", "", PROD + " UNB - Kodeord er obligatorisk"); 
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "dktf_ftipp", "", PROD + " FTP - Bruger id er obligatorisk"); 
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "dktf_ftusp", "", PROD + " FTP - Kodeord er obligatorisk"); 
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "dktf_ftpwp", "", PROD + " FTP - Adresse er obligatorisk"); 
		
		//Logical (RULES) controls if we passed the NOT NULL errors
		if(!errors.hasFieldErrors()){
			if(record!=null){
				//UNB validity
				if (!record.isValidSenderIdTest()){
					errors.rejectValue("validSenderIdTest", " ", TEST + " UNB - Afsender id er ugyldig"); 
				}
				if (!record.isValidReceiverIdTest()){
					errors.rejectValue("validReceiverIdTest", " ", TEST + " UNB - Modtager id er ugyldig"); 
				}
				if (!record.isValidSenderIdProd()){
					errors.rejectValue("validSenderIdProd", " ", PROD + " UNB - Afsender id er ugyldig"); 
				}
				if (!record.isValidReceiverIdProd()){
					errors.rejectValue("validReceiverIdProd", " ", PROD + " UNB - Modtager id er ugyldig"); 
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
		// N/A
		
	}
}
