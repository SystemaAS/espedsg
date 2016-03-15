package no.systema.skat.nctsimport.validator;

import java.util.Calendar;

import org.apache.log4j.Logger;
import org.springframework.validation.Validator;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;

import no.systema.main.service.UrlCgiProxyService;
import no.systema.main.service.UrlCgiProxyServiceImpl;

import no.systema.skat.nctsimport.model.jsonjackson.topic.unloading.items.JsonSkatNctsImportSpecificTopicUnloadingItemRecord;

import no.systema.skat.nctsimport.service.SkatNctsImportSpecificTopicItemService;
import no.systema.skat.nctsimport.service.SkatNctsImportSpecificTopicItemServiceImpl;
import no.systema.skat.nctsimport.service.SkatNctsImportSpecificTopicUnloadingItemService;
import no.systema.skat.nctsimport.service.SkatNctsImportSpecificTopicUnloadingItemServiceImpl;

import no.systema.skat.nctsimport.url.store.SkatNctsImportUrlDataStore;
/**
 * 
 * @author oscardelatorre
 * @date Apr 24, 2014
 * 
 */
public class SkatNctsImportUnloadingItemsValidator implements Validator {
	private static final Logger logger = Logger.getLogger(SkatNctsImportUnloadingItemsValidator.class.getName());
	//Intantiate services here since we are not capable to configure injection with Autowired. Check that further...
	private UrlCgiProxyService urlCgiProxyService = new UrlCgiProxyServiceImpl();
	private SkatNctsImportSpecificTopicUnloadingItemService skatNctsImportSpecificTopicUnloadingItemServiceImpl = new SkatNctsImportSpecificTopicUnloadingItemServiceImpl();
		
	/**
	 * 
	 */
	public boolean supports(Class clazz) {
		return JsonSkatNctsImportSpecificTopicUnloadingItemRecord.class.isAssignableFrom(clazz); 
	}
	
	/**
	 * @param obj
	 * @param errors
	 * 
	 */
	public void validate(Object obj, Errors errors) { 
		JsonSkatNctsImportSpecificTopicUnloadingItemRecord record = (JsonSkatNctsImportSpecificTopicUnloadingItemRecord)obj;

		//Check for Mandatory fields first
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "nvvt", "systema.skat.ncts.import.header.error.null.unloading.item.description.nvvt"); 
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "nvmn", "systema.skat.ncts.import.header.error.null.unloading.item.godsmarkning.nvmn");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "nveh", "systema.skat.ncts.import.header.error.null.unloading.item.kollislag.nveh");
		//-----------------------------------------------------------
		//Check for Mandatory fields if Konform (nikonf-HEADER) != 1
		//-----------------------------------------------------------
		if(!"1".equals(record.getHeader_nikonf())){
			if( record.getNvct()==null || "".equals(record.getNvct())){
				errors.rejectValue("nvct", "systema.skat.ncts.import.header.error.null.unloading.item.kode.nvct");
			}
			/*
			if( record.getNvctsk()==null || "".equals(record.getNvctsk())){
				errors.rejectValue("nvctsk", "systema.skat.ncts.import.header.error.null.unloading.item.sprogkode.nvctsk");
			}
			if( record.getNvctb()==null || "".equals(record.getNvctb())){
				errors.rejectValue("nvctb", "systema.skat.ncts.import.header.error.null.unloading.item.beskrivelse.nvctb");
			}*/
		}
		if("OT".equals(record.getNvct())){
			if( record.getNvctb()==null || "".equals(record.getNvctb())){
				errors.rejectValue("nvctb", "systema.skat.ncts.import.header.error.null.unloading.item.beskrivelse.nvctb");
			}	
		}
		
		
		//Logical controls if we passed the NOT NULL errors
		if(!errors.hasFieldErrors()){
			if(record!=null){
				//---------------------
				//Gross and Net weight
				//---------------------
				if(record.getNvvktb()!=null && !"".equals(record.getNvvktb())){
					if(record.getNvvktn()!=null && !"".equals(record.getNvvktn())){
						try{
							String grossFormatTmp = record.getNvvktb().replace(".", "");
							double grossWeight = Double.parseDouble(grossFormatTmp.replace(",", "."));
							String netFormatTmp = record.getNvvktn().replace(".", "");
							double netWeight = Double.parseDouble(netFormatTmp.replace(",", "."));
							
							//Net can not be > than Gross
							if (netWeight>grossWeight){
								errors.rejectValue("nvvktb", "systema.skat.ncts.import.unloading.error.rule.item.netWeightTooBig");
							}
						}catch(Exception e){
							//just take a phantom hit here 
						}
					}
				}
				
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
