package no.systema.fraktkalkulator.validator;

import org.apache.log4j.Logger;
import org.springframework.validation.Validator;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;

import no.systema.fraktkalkulator.controller.FraktKalkulatorController;
import no.systema.fraktkalkulator.model.jsonjackson.JsonFraktKalkulatorUserContainer;

/**
 * 
 * @author oscardelatorre
 * @date Mar 12, 2015
 * 
 *
 */
public class FraktkalkulatorValidator implements Validator {
	private static final Logger logger = Logger.getLogger(FraktKalkulatorController.class.getName());
	
	/**
	 * 
	 */
	public boolean supports(Class clazz) {
		return JsonFraktKalkulatorUserContainer.class.isAssignableFrom(clazz); 
	}
	
	/**
	 * @param obj
	 * @param errors
	 * 
	 */
	public void validate(Object obj, Errors errors) { 
		JsonFraktKalkulatorUserContainer record = (JsonFraktKalkulatorUserContainer)obj;
		//Check for Mandatory fields
		if(record!=null){
			if( "".equals(record.getFra()) || "".equals(record.getFralk()) || "".equals(record.getTil()) ||"".equals(record.getTillk())){
				errors.rejectValue("fra", "systema.fraktkalkulator.main.form.search.notValidMandatoryFields");
			}
			
		}
	}	
}
