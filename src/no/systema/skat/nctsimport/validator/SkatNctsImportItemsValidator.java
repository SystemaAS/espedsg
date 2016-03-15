package no.systema.skat.nctsimport.validator;

import java.util.Calendar;

import org.apache.log4j.Logger;
import org.springframework.validation.Validator;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;

import no.systema.main.service.UrlCgiProxyService;
import no.systema.main.service.UrlCgiProxyServiceImpl;
import no.systema.skat.nctsimport.model.jsonjackson.topic.JsonSkatNctsImportSpecificTopicRecord;
import no.systema.skat.nctsimport.model.jsonjackson.topic.items.JsonSkatNctsImportSpecificTopicItemRecord;
import no.systema.skat.nctsimport.service.SkatNctsImportSpecificTopicItemService;
import no.systema.skat.nctsimport.service.SkatNctsImportSpecificTopicItemServiceImpl;
import no.systema.skat.nctsimport.url.store.SkatNctsImportUrlDataStore;
/**
 * 
 * @author oscardelatorre
 * @date Apr 24, 2014
 * 
 */
public class SkatNctsImportItemsValidator implements Validator {
	private static final Logger logger = Logger.getLogger(SkatNctsImportItemsValidator.class.getName());
	//Intantiate services here since we are not capable to configure injection with Autowired. Check that further...
	private UrlCgiProxyService urlCgiProxyService = new UrlCgiProxyServiceImpl();
	private SkatNctsImportSpecificTopicItemService skatNctsImportSpecificTopicItemService = new SkatNctsImportSpecificTopicItemServiceImpl();
		
	/**
	 * 
	 */
	public boolean supports(Class clazz) {
		return JsonSkatNctsImportSpecificTopicItemRecord.class.isAssignableFrom(clazz); 
	}
	
	/**
	 * @param obj
	 * @param errors
	 * 
	 */
	public void validate(Object obj, Errors errors) { 
		JsonSkatNctsImportSpecificTopicItemRecord record = (JsonSkatNctsImportSpecificTopicItemRecord)obj;

		//Check for Mandatory fields first
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "tvst", "systema.skat.ncts.import.header.error.null.item.plats.tvst"); 
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "tvstlk", "systema.skat.ncts.import.header.error.null.item.platscountry.tvstlk");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "tvinf", "systema.skat.ncts.import.header.error.null.item.eventtext.tvinf1");
						
		//Logical controls if we passed the NOT NULL errors
		if(!errors.hasFieldErrors()){
			if(record!=null){
				
				
				//------------------------------------------------------------
				//Känsliga varor must always check for mandatory input or none
				//-------------------------------------------------------------
				/*if(record.getTvvnt()!=null && !"".equals(record.getTvvnt()) ){
					if(this.isVarukodWithSensitiveGoodsFlag(record)){
						if(record.getTvfvnt()!=null && !"".equals(record.getTvfvnt())){
							//nothing since it has been filled in
						}else{
							errors.rejectValue("tvfvnt", "systema.ncts.export.header.error.rule.item.tvfvnt.mustExist");
						}
					}else{
						//back to default values
						record.setTvfv(null);
						record.setTvfvnt(null);
					}
				}*/
				
			}
			
		}
			
	}
	
	
	
}
