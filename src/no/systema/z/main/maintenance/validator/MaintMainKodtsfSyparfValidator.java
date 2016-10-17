package no.systema.z.main.maintenance.validator;

import org.apache.log4j.Logger;
import org.springframework.validation.Validator;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;

import no.systema.main.util.NumberFormatterLocaleAware;
import no.systema.z.main.maintenance.model.jsonjackson.dbtable.JsonMaintMainKodtsfSyparfRecord;

/**
 * 
 * @author oscardelatorre
 * @date Okt 17, 2016
 * 
 *
 */
public class MaintMainKodtsfSyparfValidator implements Validator {
	private static final Logger logger = Logger.getLogger(MaintMainKodtsfSyparfValidator.class.getName());
	private NumberFormatterLocaleAware numberFormatter = new NumberFormatterLocaleAware();
	/**
	 * 
	 */
	public boolean supports(Class clazz) {
		return JsonMaintMainKodtsfSyparfRecord.class.isAssignableFrom(clazz); 
	}
	
	/**
	 * @param obj
	 * @param errors
	 * 
	 */
	public void validate(Object obj, Errors errors) { 
		JsonMaintMainKodtsfSyparfRecord record = (JsonMaintMainKodtsfSyparfRecord)obj;
		
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "kosfsi", "", "Signatur er obligatorisk"); 
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "kosfnv", "", "Navn er obligatorisk"); 
		
		
		//Logical (RULES) controls if we passed the NOT NULL errors
		if(!errors.hasFieldErrors()){
			if(record!=null){
				/* TODO
				if( !this.validInteger( record.getTeopdn()) ){
					errors.rejectValue("teopdn", "", "Oppnr er ugyldig. Oppdragsnr. må være større enn 0.");
					
				}else if( !this.validInteger( record.getTeturn()) ){
					errors.rejectValue("teturn", "", "Turnr. er ugyldig. Turnr. må være større enn 0. ");
				
				}else if( !this.validNumberTetmin(record) ){
					errors.rejectValue("tetmin", "", "Minm.turnr er ugyldig. Minmumturnr. kan ikke være større enn turnr. ");
				}
				*/
			}
		}
		
	}
	/**
	 * 
	 * @param obj
	 * @param errors
	 */
	
	public void validateDelete(Object obj, Errors errors) { 
		
		JsonMaintMainKodtsfSyparfRecord record = (JsonMaintMainKodtsfSyparfRecord)obj;
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "kosfsi", "", "Signatur er obligatorisk"); 
		
	}
	
	
	/**
	 * 
	 * @param value
	 * @return
	 */
	private boolean validInteger(String value){
		final Integer LOWER_LIMIT = 1;
		boolean retval = true;
		if (value!=null && !"".equals(value)){
			try{
				Integer tmpInt = Integer.parseInt(value);
				if(tmpInt < LOWER_LIMIT){
					retval = false;
				}
			}catch(Exception e){
				retval = false;
			}
		}
		
		return retval;
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
