package no.systema.skat.z.maintenance.felles.validator;

import org.apache.log4j.Logger;
import org.springframework.validation.Validator;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;

import no.systema.main.util.NumberFormatterLocaleAware;
import no.systema.main.validator.DateValidator;
import no.systema.skat.z.maintenance.main.model.jsonjackson.dbtable.JsonMaintDktardRecord;

/**
 * 
 * @author oscardelatorre
 * @date Mar 09, 2017
 * 
 *
 */
public class MaintSkatFellesDktardrValidator implements Validator {
	private static final Logger logger = Logger.getLogger(MaintSkatFellesDktardrValidator.class.getName());
	private NumberFormatterLocaleAware numberFormatter = new NumberFormatterLocaleAware();
	/**
	 * 
	 */
	public boolean supports(Class clazz) {
		return JsonMaintDktardRecord.class.isAssignableFrom(clazz); 
	}
	
	/**
	 * @param obj
	 * @param errors
	 * 
	 */
	public void validate(Object obj, Errors errors) { 
		JsonMaintDktardRecord record = (JsonMaintDktardRecord)obj;
		
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "dktard01", "", "Varekode er obligatorisk"); 
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "dktard02", "", "Start dato er obligatorisk"); 
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "dktard03", "", "Slut dato er obligatorisk"); 
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "dktard48", "", "Varetekst er obligatorisk"); 
		
		//Logical (RULES) controls if we passed the NOT NULL errors
		if(!errors.hasFieldErrors()){
			if(record!=null){
				//Start dato
				if (record.getDktard02()!=null && !"".equals(record.getDktard02())){
					boolean isValidISOFormat = new DateValidator().validateDateIso203_YYYYMMDD(record.getDktard02());
					if(!isValidISOFormat){
						errors.rejectValue("dktard02", "", "Start dato er ugyldig"); 
					}
					//length validation
					if(record.getDktard02().length()<8){
						errors.rejectValue("dktard02", "Start dato er ugyldig"); 
					}
				}
				//Slut dato
				if (record.getDktard03()!=null && !"".equals(record.getDktard03())){
					//99999999 is valid for Slut dato (default to infinity in this system)
					if(!"99999999".equals(record.getDktard03())){
						boolean isValidISOFormat = new DateValidator().validateDateIso203_YYYYMMDD(record.getDktard02());
						if(!isValidISOFormat){
							errors.rejectValue("dktard02", "", "Start dato er ugyldig"); 
						}
						//length validation
						if(record.getDktard02().length()<8){
							errors.rejectValue("dktard02", "Start dato er ugyldig"); 
						}
					}
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
		JsonMaintDktardRecord record = (JsonMaintDktardRecord)obj;
		
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "dktard01", "", "Varekode er obligatorisk"); 
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "dktard02", "", "Start dato er obligatorisk"); 
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "dktard03", "", "Slut dato er obligatorisk"); 
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "dktard48", "", "Varetekst er obligatorisk"); 
		
	}
}
