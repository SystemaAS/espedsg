package no.systema.skat.nctsimport.validator;

import java.text.SimpleDateFormat;
import java.util.*;

import org.apache.log4j.Logger;
import org.springframework.validation.Validator;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;

import no.systema.main.service.UrlCgiProxyService;
import no.systema.main.service.UrlCgiProxyServiceImpl;
//import no.systema.skat.nctsimport.service.SkatNctsImportSpecificTopicService;
//import no.systema.skat.nctsimport.service.SkatNctsImportSpecificTopicServiceImpl;

import no.systema.skat.nctsimport.model.jsonjackson.topic.unloading.JsonSkatNctsImportSpecificTopicUnloadingRecord;


/**
 * 
 * @author oscardelatorre
 * @date Aug 20, 2014
 * 
 */
public class SkatNctsImportUnloadingValidator implements Validator {
	private static final Logger logger = Logger.getLogger(SkatNctsImportUnloadingValidator.class.getName());
	//Intantiate services here since we are not capable to configure injection with Autowired. Check that further...
	private UrlCgiProxyService urlCgiProxyService = new UrlCgiProxyServiceImpl();
	//private SkatNctsImportSpecificTopicService nctsImportSpecificTopicService = new SkatNctsImportSpecificTopicServiceImpl();
	   
	
	/**
	 * 
	 */
	public boolean supports(Class clazz) {
		return JsonSkatNctsImportSpecificTopicUnloadingRecord.class.isAssignableFrom(clazz); 
	}
	
	/**
	 * @param obj
	 * @param errors
	 * 
	 */
	public void validate(Object obj, Errors errors) { 
		JsonSkatNctsImportSpecificTopicUnloadingRecord record = (JsonSkatNctsImportSpecificTopicUnloadingRecord)obj;
		logger.info("Inside module...");
		
		//Check for Mandatory fields first
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "nidtl", "systema.skat.ncts.import.header.error.null.unloading.conform.nidtl"); 
		
		//Logical controls if we passed the NOT NULL errors
		if(!errors.hasFieldErrors()){
			if(record!=null){
				//Konform
				if( "0".equals(record.getNikonf())){
					if(!this.isValidKonformMandatoryFields(record)){
						errors.rejectValue("nikonf", "systema.skat.ncts.import.header.error.null.unloading.conform.nikonf");
					}
				}else{
					//with konform = 1; OT&DI must be empty 
					if(this.isValidKonformMandatoryFields(record)){
						errors.rejectValue("nikonf", "systema.skat.ncts.import.header.error.notnull.unloading.conform.nikonf");
					}
				}
			}
		}
		
	}
	
	/**
	 * 
	 * @param record
	 * @return
	 */
	private boolean isValidKonformMandatoryFields(JsonSkatNctsImportSpecificTopicUnloadingRecord record){
		boolean retval = false;
		if( (record.getNictb()!=null && !"".equals(record.getNictb()) ) || (record.getNictb2()!=null && !"".equals(record.getNictb2()) ) ){
			retval = true;
			/*
			if(record.getNictsk()!=null &&!"".equals(record.getNictsk())){
				retval = true;
			}*/
			
		}
		
		return retval;
	}
	
}
