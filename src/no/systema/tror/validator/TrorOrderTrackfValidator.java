package no.systema.tror.validator;

import org.apache.log4j.Logger;
import org.springframework.validation.Validator;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import no.systema.jservices.common.dao.TrackfDao;
import no.systema.main.validator.DateValidator;
import no.systema.main.util.NumberFormatterLocaleAware;
import no.systema.main.util.StringManager;

/**
 * 
 * @author oscardelatorre
 * @date Sep 20, 2017
 * 
 *
 */
public class TrorOrderTrackfValidator implements Validator {
	private DateValidator dateValidator = new DateValidator();
	private static Logger logger = Logger.getLogger(TrorOrderTrackfValidator.class.getName());
	private NumberFormatterLocaleAware formatter = new NumberFormatterLocaleAware();
	private StringManager strMgr = new StringManager();
	
	/**
	 * 
	 */
	public boolean supports(Class clazz) {
		return TrackfDao.class.isAssignableFrom(clazz); 
	}
	
	/**
	 * @param obj
	 * @param errors
	 * 
	 */
	public void validate(Object obj, Errors errors) { 
		TrackfDao record = (TrackfDao)obj;
		
		//ValidationUtils.rejectIfEmptyOrWhitespace(errors, "ttavd", "systema.tror.orders.tt.logging.form.error.null.avd");
		//ValidationUtils.rejectIfEmptyOrWhitespace(errors, "ttopd", "systema.tror.orders.tt.logging.form.error.null.opd"); 
		//ValidationUtils.rejectIfEmptyOrWhitespace(errors, "ttdate", "systema.tror.orders.tt.logging.form.error.null.date"); 
		//ValidationUtils.rejectIfEmptyOrWhitespace(errors, "tttime", "systema.tror.orders.tt.logging.form.error.null.time"); 
		//rest of the gang
		//ValidationUtils.rejectIfEmptyOrWhitespace(errors, "ttfbnr", "systema.tror.orders.tt.logging.form.error.null.fbnr");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "ttacti", "systema.tror.orders.tt.logging.form.error.null.kode"); 
		
		//ValidationUtils.rejectIfEmptyOrWhitespace(errors, "tttexl", "systema.tror.orders.tt.logging.form.error.null.text"); 
		//ValidationUtils.rejectIfEmptyOrWhitespace(errors, "ttdatl", "systema.tror.orders.tt.logging.form.error.null.logdate"); 
		//ValidationUtils.rejectIfEmptyOrWhitespace(errors, "tttiml", "systema.tror.orders.tt.logging.form.error.null.logtid");
		//ValidationUtils.rejectIfEmptyOrWhitespace(errors, "ttuser", "systema.tror.orders.tt.logging.form.error.null.loguser"); 
		
		//Rule errors
		if(record!=null){
			//numerical fields
			if(record.getTtavd() == 0){
			     errors.rejectValue("ttavd", "systema.tror.orders.tt.logging.form.error.null.avd");
			}
			if(record.getTtopd() == 0){
			     errors.rejectValue("ttopd", "systema.tror.orders.tt.logging.form.error.null.opd");
			}
			if(record.getTtdate() == 0){
			     errors.rejectValue("ttdate", "systema.tror.orders.tt.logging.form.error.null.date");
			}
			if(record.getTttime() == 0){
			     errors.rejectValue("tttime", "systema.tror.orders.tt.logging.form.error.null.time");
			}
			//text (EN or NO = mandatory
			if( strMgr.isNotNull(record.getTttexl()) || strMgr.isNotNull(record.getTttext()) ){
				//OK
			}else{
				errors.rejectValue("tttexl", "systema.tror.orders.tt.logging.form.error.null.text");
			}
			
		}	
	}
	
	public void validateDelete(Object obj, Errors errors) { 
		TrackfDao record = (TrackfDao)obj;
		
		//Rule errors
		if(record!=null){
			if(record.getTtavd() == 0){
			     errors.rejectValue("ttavd", "systema.tror.orders.tt.logging.form.error.null.avd");
			}
			if(record.getTtopd() == 0){
			     errors.rejectValue("ttopd", "systema.tror.orders.tt.logging.form.error.null.opd");
			}
			if(record.getTtdate() == 0){
			     errors.rejectValue("ttdate", "systema.tror.orders.tt.logging.form.error.null.date");
			}
			if(record.getTttime() == 0){
			     errors.rejectValue("tttime", "systema.tror.orders.tt.logging.form.error.null.time");
			}
			if( strMgr.isNotNull(record.getTtacti()) && record.getTtacti().length()>3 ){
				errors.rejectValue("ttacti", "systema.tror.orders.tt.logging.form.error.invalid.kode");
			}
			if( strMgr.isNotNull(record.getTtedev()) && record.getTtedev().length()>3 ){
				errors.rejectValue("ttedev", "systema.tror.orders.tt.logging.form.error.invalid.event");
			}
			if( strMgr.isNotNull(record.getTtedre()) && record.getTtedre().length()>3 ){
				errors.rejectValue("ttedre", "systema.tror.orders.tt.logging.form.error.invalid.reason");
			}
			
		}	
	}
}
