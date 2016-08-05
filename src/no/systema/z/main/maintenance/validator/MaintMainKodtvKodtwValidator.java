package no.systema.z.main.maintenance.validator;

import org.apache.log4j.Logger;
import org.springframework.validation.Validator;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;

import no.systema.main.util.NumberFormatterLocaleAware;
import no.systema.z.main.maintenance.model.jsonjackson.dbtable.JsonMaintMainKodtvKodtwRecord;

/**
 * 
 * @author oscardelatorre
 * @date Aug 05, 2016
 * 
 *
 */
public class MaintMainKodtvKodtwValidator implements Validator {
	private static final Logger logger = Logger.getLogger(MaintMainKodtvKodtwValidator.class.getName());
	private NumberFormatterLocaleAware numberFormatter = new NumberFormatterLocaleAware();
	/**
	 * 
	 */
	public boolean supports(Class clazz) {
		return JsonMaintMainKodtvKodtwRecord.class.isAssignableFrom(clazz); 
	}
	
	/**
	 * @param obj
	 * @param errors
	 * 
	 */
	public void validate(Object obj, Errors errors) { 
		JsonMaintMainKodtvKodtwRecord record = (JsonMaintMainKodtvKodtwRecord)obj;
		
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "kovavd", "", "Avd er obligatorisk"); 
		
		//Logical (RULES) controls if we passed the NOT NULL errors
		if(!errors.hasFieldErrors()){
			if(record!=null){
				/*
				if( !this.validNumber(record.getKs1typ()) ){
					errors.rejectValue("ks1typ", "", "Kode value invalid. The value can not be greater than 99");
				}*/
			}
		}
		
	}
	/**
	 * 
	 * @param obj
	 * @param errors
	 */
	
	public void validateDelete(Object obj, Errors errors) { 
		
		JsonMaintMainKodtvKodtwRecord record = (JsonMaintMainKodtvKodtwRecord)obj;
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "kovavd", "", "Avd. er obligatorisk"); 
		
	}
	
}
