package no.systema.ebooking.validator;

import org.apache.log4j.Logger;
import org.springframework.validation.Validator;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;

import no.systema.ebooking.model.jsonjackson.JsonMainOrderHeaderRecord;

/**
 * 
 * @author oscardelatorre
 * @date Jan 07, 2017
 * 
 *
 */
public class OrderHeaderValidator implements Validator {
	private static final Logger logger = Logger.getLogger(OrderHeaderValidator.class.getName());
	
	/**
	 * 
	 */
	public boolean supports(Class clazz) {
		return OrderHeaderValidator.class.isAssignableFrom(clazz); 
	}
	
	/**
	 * @param obj
	 * @param errors
	 * 
	 */
	public void validate(Object obj, Errors errors) { 
		JsonMainOrderHeaderRecord record = (JsonMainOrderHeaderRecord)obj;
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "hereff", "systema.ebooking.orders.form.update.error.null.from.hereff");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "henas", "systema.ebooking.orders.form.update.error.null.shipper.name.henas");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "heads1", "systema.ebooking.orders.form.update.error.null.shipper.name.heads1");
		
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "henak", "systema.ebooking.orders.form.update.error.null.consignee.name.henak");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "headk1", "systema.ebooking.orders.form.update.error.null.consignee.name.headk1");
		
		
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "helka", "systema.ebooking.orders.form.update.error.null.from.helka");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "hesdf", "systema.ebooking.orders.form.update.error.null.from.hesdf");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "hetri", "systema.ebooking.orders.form.update.error.null.to.hetri");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "hesdt", "systema.ebooking.orders.form.update.error.null.to.hesdt");
		//Check for Mandatory fields
		/*
		if(record!=null){
			if( "".equals(record.getXXX()) || "".equals(record.getFralk()) || "".equals(record.getTil()) ||"".equals(record.getTillk())){
				errors.rejectValue("fra", "systema.fraktkalkulator.main.form.search.notValidMandatoryFields");
			}
			
		}
		*/
	}	
}
