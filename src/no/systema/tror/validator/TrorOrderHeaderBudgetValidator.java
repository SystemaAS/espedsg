package no.systema.tror.validator;

import org.apache.log4j.Logger;
import org.springframework.validation.Validator;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import no.systema.tror.model.jsonjackson.budget.JsonTrorOrderHeaderBudgetContainer;
import no.systema.tror.model.jsonjackson.budget.JsonTrorOrderHeaderBudgetRecord;
import no.systema.main.validator.DateValidator;
import no.systema.main.util.NumberFormatterLocaleAware;

/**
 * 
 * @author oscardelatorre
 * @date Sep 18, 2017
 * 
 *
 */
public class TrorOrderHeaderBudgetValidator implements Validator {
	private DateValidator dateValidator = new DateValidator();
	private static Logger logger = Logger.getLogger(TrorOrderHeaderBudgetValidator.class.getName());
	private NumberFormatterLocaleAware formatter = new NumberFormatterLocaleAware();
	
	/**
	 * 
	 */
	public boolean supports(Class clazz) {
		return JsonTrorOrderHeaderBudgetRecord.class.isAssignableFrom(clazz); 
	}
	
	/**
	 * @param obj
	 * @param errors
	 * 
	 */
	public void validate(Object obj, Errors errors) { 
		JsonTrorOrderHeaderBudgetRecord record = (JsonTrorOrderHeaderBudgetRecord)obj;
		
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "buvk", "systema.tror.budget.form.error.null.gebyr.buvk");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "bubl", "systema.tror.budget.form.error.null.belop.bubl"); 
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "buval", "systema.tror.budget.form.error.null.valuta.buval"); 
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "butype", "systema.tror.budget.form.error.null.type.butype"); 
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "bublst", "systema.tror.budget.form.error.null.type.bublst"); 
		
		//Rule errors
		if(record!=null){
			if( record.getHepro()!=null && !"".equals(record.getHepro()) ){
				//the budget record belongs to the Trip-Use Case.
			}else{
				//If the budget record belongs to the Order-Use Case then this fields are mandatory
				ValidationUtils.rejectIfEmptyOrWhitespace(errors, "buoavd", "systema.tror.budget.form.error.null.type.buoavd"); 
				ValidationUtils.rejectIfEmptyOrWhitespace(errors, "buopd", "systema.tror.budget.form.error.null.type.buopd"); 
			}
		}	
	}
}
