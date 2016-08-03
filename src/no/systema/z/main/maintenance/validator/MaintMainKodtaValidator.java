package no.systema.z.main.maintenance.validator;

import org.apache.log4j.Logger;
import org.springframework.validation.Validator;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;

import no.systema.main.util.NumberFormatterLocaleAware;
import no.systema.z.main.maintenance.model.jsonjackson.dbtable.JsonMaintMainKodtaRecord;

/**
 * 
 * @author oscardelatorre
 * @date Aug 3, 2016
 * 
 *
 */
public class MaintMainKodtaValidator implements Validator {
	private static final Logger logger = Logger.getLogger(MaintMainKodtaValidator.class.getName());
	private NumberFormatterLocaleAware numberFormatter = new NumberFormatterLocaleAware();
	/**
	 * 
	 */
	public boolean supports(Class clazz) {
		return JsonMaintMainKodtaRecord.class.isAssignableFrom(clazz); 
	}
	
	/**
	 * @param obj
	 * @param errors
	 * 
	 */
	public void validate(Object obj, Errors errors) { 
		JsonMaintMainKodtaRecord record = (JsonMaintMainKodtaRecord)obj;
		
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "koaavd", "", "Avd er obligatorisk"); 
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "koanvn", "", "Avd.navn er obligatorisk"); 
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "koafir", "", "Firma er obligatorisk"); 
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "koaknr", "", "Kundenr. er obligatorisk"); 
		
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
		
		JsonMaintMainKodtaRecord record = (JsonMaintMainKodtaRecord)obj;
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "koaavd", "", "Avd. er obligatorisk"); 
		
	}
	/**
	 * 
	 * @param value
	 * @return
	 */
	private boolean validNumber(String value){
		final Integer UPPER_LIMIT = 99;
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
