package no.systema.tror.validator;

import org.apache.log4j.Logger;
import org.springframework.validation.Validator;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import no.systema.tror.model.jsonjackson.frisokvei.JsonTrorOrderHeaderFrisokveiRecord;
import no.systema.main.validator.DateValidator;
import no.systema.main.util.NumberFormatterLocaleAware;

/**
 * 
 * @author oscardelatorre
 * @date Sep 20, 2017
 * 
 *
 */
public class TrorOrderHeaderFrisokveiValidator implements Validator {
	private DateValidator dateValidator = new DateValidator();
	private static Logger logger = Logger.getLogger(TrorOrderHeaderFrisokveiValidator.class.getName());
	private NumberFormatterLocaleAware formatter = new NumberFormatterLocaleAware();
	
	/**
	 * 
	 */
	public boolean supports(Class clazz) {
		return JsonTrorOrderHeaderFrisokveiRecord.class.isAssignableFrom(clazz); 
	}
	
	/**
	 * @param obj
	 * @param errors
	 * 
	 */
	public void validate(Object obj, Errors errors) { 
		JsonTrorOrderHeaderFrisokveiRecord record = (JsonTrorOrderHeaderFrisokveiRecord)obj;
		
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "fskode", "systema.tror.frisokvei.form.error.null.fskode");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "fssok", "systema.tror.frisokvei.form.error.null.fssok"); 
		
		//Rule errors
		if(record!=null){
			/*
			if(!record.TODO().equals(record.TODO())){
			    	//TODO errors.rejectValue("fskode", "systema.transportdisp.frisokvei.form.error.null.fskode");
			}*/
			 
		}	
	}
}
