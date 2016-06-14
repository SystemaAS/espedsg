package no.systema.skat.z.maintenance.main.validator;

import org.apache.log4j.Logger;
import org.springframework.validation.Validator;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;

import no.systema.main.util.NumberFormatterLocaleAware;
import no.systema.skat.z.maintenance.main.model.jsonjackson.dbtable.JsonMaintDktvkRecord;

/**
 * 
 * @author oscardelatorre
 * @date Jun 7, 2016
 * 
 *
 */
public class MaintSkatImportDkt057rValidator implements Validator {
	private static final Logger logger = Logger.getLogger(MaintSkatImportDkt057rValidator.class.getName());
	private NumberFormatterLocaleAware numberFormatter = new NumberFormatterLocaleAware();
	/**
	 * 
	 */
	public boolean supports(Class clazz) {
		return JsonMaintDktvkRecord.class.isAssignableFrom(clazz); 
	}
	
	/**
	 * @param obj
	 * @param errors
	 * 
	 */
	public void validate(Object obj, Errors errors) { 
		JsonMaintDktvkRecord record = (JsonMaintDktvkRecord)obj;
		
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "dkvk_kd", "", "Sort er obligatorisk"); 
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "dkvk_krs", "", "Kurs er obligatorisk"); 
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "dkvk_omr", "", "Faktor er obligatorisk"); 
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "dkvk_dts", "", "Fra dato er obligatorisk"); 
		
		//Logical (RULES) controls if we passed the NOT NULL errors
		if(!errors.hasFieldErrors()){
			if(record!=null){
				if( !this.validNumber(record.getDkvk_krs()) ){
					errors.rejectValue("dkvk_omr", "", "Kurs: Invalid number. The value can not be greater than 9999,999999");
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
		
		JsonMaintDktvkRecord record = (JsonMaintDktvkRecord)obj;
		//logger.info(record.getTariff());
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "dkvk_kd", "", "Sort er obligatorisk"); 
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "dkvk_dts", "", "Fra dato er obligatorisk"); 

	}
	
	/**
	 * 
	 * @param value
	 * @return
	 */
	private boolean validNumber(String value){
		final Double UPPER_LIMIT = 9999.999999;
		boolean retval = true;
		if (value!=null && !"".equals(value)){
			String tmp = value.replace(",", ".");
			try{
				Double tmpDbl = Double.parseDouble(tmp);
				if(tmpDbl>UPPER_LIMIT){
					retval = false;
				}
			}catch(Exception e){
				retval = false;
			}
		}
		
		return retval;
	}
}
