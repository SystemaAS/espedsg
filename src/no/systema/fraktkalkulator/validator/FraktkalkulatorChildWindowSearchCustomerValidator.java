package no.systema.fraktkalkulator.validator;

import org.apache.log4j.Logger;
import org.springframework.validation.Validator;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;

import no.systema.fraktkalkulator.controller.FraktKalkulatorController;
import no.systema.fraktkalkulator.model.jsonjackson.customer.JsonFraktKalkulatorCustomerRecord;

/**
 * 
 * @author oscardelatorre
 * @date Mar 11, 2015
 * 
 *
 */
public class FraktkalkulatorChildWindowSearchCustomerValidator implements Validator {
	private static final Logger logger = Logger.getLogger(FraktkalkulatorChildWindowSearchCustomerValidator.class.getName());
	
	/**
	 * 
	 */
	public boolean supports(Class clazz) {
		return JsonFraktKalkulatorCustomerRecord.class.isAssignableFrom(clazz); 
	}
	
	/**
	 * @param obj
	 * @param errors
	 * 
	 */
	public void validate(Object obj, Errors errors) { 
		JsonFraktKalkulatorCustomerRecord record = (JsonFraktKalkulatorCustomerRecord)obj;
		//Check for Mandatory fields
		if(record!=null){
			if( "".equals(record.getKundnr()) && "".equals(record.getKnavn()) ){ 
				errors.rejectValue("kundnr", "systema.fraktkalkulator.childwindow.customer.notValidFilter"); 
			}
			
		}
	}	
}
