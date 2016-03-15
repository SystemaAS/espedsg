package no.systema.skat.nctsimport.validator;

import java.text.SimpleDateFormat;
import java.util.*;

import org.apache.log4j.Logger;
import org.springframework.validation.Validator;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;

import no.systema.main.service.UrlCgiProxyService;
import no.systema.main.service.UrlCgiProxyServiceImpl;
import no.systema.skat.nctsimport.service.SkatNctsImportSpecificTopicService;
import no.systema.skat.nctsimport.service.SkatNctsImportSpecificTopicServiceImpl;

import no.systema.skat.nctsimport.model.jsonjackson.topic.JsonSkatNctsImportSpecificTopicRecord;


/**
 * 
 * @author oscardelatorre
 * @date Apr 24, 2014
 * 
 */
public class SkatNctsImportHeaderValidator implements Validator {
	private static final Logger logger = Logger.getLogger(SkatNctsImportHeaderValidator.class.getName());
	//Intantiate services here since we are not capable to configure injection with Autowired. Check that further...
	private UrlCgiProxyService urlCgiProxyService = new UrlCgiProxyServiceImpl();
	private SkatNctsImportSpecificTopicService nctsImportSpecificTopicService = new SkatNctsImportSpecificTopicServiceImpl();
	   
	
	/**
	 * 
	 */
	public boolean supports(Class clazz) {
		return JsonSkatNctsImportSpecificTopicRecord.class.isAssignableFrom(clazz); 
	}
	
	/**
	 * @param obj
	 * @param errors
	 * 
	 */
	public void validate(Object obj, Errors errors) { 
		JsonSkatNctsImportSpecificTopicRecord record = (JsonSkatNctsImportSpecificTopicRecord)obj;
		System.out.print("Inside SkatNctsImportHeaderValidator");
		
		//Check for Mandatory fields first
		/* TODO - COVI activate after testing
		
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "tiavd", "systema.skat.ncts.import.header.error.null.tiavd"); 
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "tisg", "systema.skat.ncts.import.header.error.null.tisg"); 
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "tienkl", "systema.skat.ncts.import.header.error.null.tienkl"); 
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "tign", "systema.skat.ncts.import.header.error.null.tign"); 
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "titrnr", "systema.skat.ncts.import.header.error.null.titrnr"); 
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "tignsk", "systema.skat.ncts.import.header.error.null.tignsk"); 
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "tialk", "systema.skat.ncts.import.header.error.null.tialk"); 
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "titsb", "systema.skat.ncts.import.header.error.null.titsb"); 
		//ansvarig
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "tina", "systema.skat.ncts.import.header.error.null.tina"); 
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "titin", "systema.skat.ncts.import.header.error.null.titin"); 
		*/
		
		//Logical controls if we passed the NOT NULL errors
		if(!errors.hasFieldErrors()){
			if(record!=null){
				//MRN = 18 char
				if( record.getTitrnr().length() != 18 ){
					errors.rejectValue("titrnr", "systema.skat.ncts.import.header.error.rule.mrnnr.mandatory.size");
				}
				
				//Förenklad förfarande	
				if( "J".equals(record.getTienkl()) ){
					if(record.getTiacts()!=null && !"".equals(record.getTiacts())){
						errors.rejectValue("tiacts", "systema.skat.ncts.import.header.error.rule.tienkl.rejectKontrollPlatsOrAvtaladLagerPlats");
						logger.info("INVALID tiacts");
					}else if (this.avtalLagerPlatsOneOfTrioExists(record)){
						errors.rejectValue("tialsk", "systema.skat.ncts.import.header.error.rule.tienkl.rejectKontrollPlatsOrAvtaladLagerPlats");
						logger.info("INVALID tialskTrio");	
					}
				//Normal förfarande						
				}else if ( "N".equals(record.getTienkl()) ){
					if(record.getTiglsk()!=null && !"".equals(record.getTiglsk())){
						errors.rejectValue("tiglsk", "systema.skat.ncts.import.header.error.rule.tienklNormal.rejectGodkandLagerPlats");
						logger.info("INVALID tiglsk");
					}
					if(this.avtalLagerPlatsOneOfTrioExists(record)){
						if(!this.avtalLagerPlatsDuetExists(record)){
							errors.rejectValue("tialsk", "systema.skat.ncts.import.header.error.rule.tienklNormal.incompleteAvtaladLagerPlats");
							logger.info("INVALID tialsk");
							
						}
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
	private boolean avtalLagerPlatsOneOfTrioExists(JsonSkatNctsImportSpecificTopicRecord record){
		boolean retval = false;
		if(record.getTialsk()!=null || record.getTialss()!=null || record.getTials()!=null){
			if(!"".equals(record.getTialsk()) || !"".equals(record.getTialss()) || !"".equals(record.getTials()) ){
				retval = true;
			}
		}
		
		return retval;
	}
	/**
	 * 
	 * @param record
	 * @return
	 */
	private boolean avtalLagerPlatsDuetExists(JsonSkatNctsImportSpecificTopicRecord record){
		boolean retval = false;
		if(record.getTialsk()!=null || record.getTialss()!=null){
			if(!"".equals(record.getTialsk()) && !"".equals(record.getTialss()) ){
				retval = true;
			}
		}
		
		return retval;
	}
	/**
	 * 
	 * @param rawValue
	 * @return
	 */
	private boolean isValidDate(String rawValue){
		boolean retval = false;
		SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMdd");
		formatter.setLenient(false); //in order to put logical control for month
		try{
			Date tmp = formatter.parse(rawValue);
			retval = true;
		}catch(Exception e){
			//nothing
		}
		return retval;
	}
	
	
	  
}
