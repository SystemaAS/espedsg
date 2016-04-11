package no.systema.skat.skatimport.validator;

import org.springframework.validation.Validator;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;

import no.systema.skat.skatimport.model.jsonjackson.topic.JsonSkatImportTopicInvoiceRecord;

/**
 * 
 * @author oscardelatorre
 * @date Apr 7, 2016
 * 
 */
public class SkatImportInvoiceValidator implements Validator {

	/**
	 * 
	 */
	public boolean supports(Class clazz) {
		return JsonSkatImportTopicInvoiceRecord.class.isAssignableFrom(clazz); 
	}
	
	/**
	 * @param obj
	 * @param errors
	 * 
	 */
	public void validate(Object obj, Errors errors) { 
		JsonSkatImportTopicInvoiceRecord record = (JsonSkatImportTopicInvoiceRecord)obj;

		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "dkif_fatx", "systema.skat.import.header.error.null.invoice.invnr.dkif_fatx");
		//ValidationUtils.rejectIfEmptyOrWhitespace(errors, "dkif_faty", "systema.skat.import.header.error.null.invoice.typ.dkif_faty"); 
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "dkif_fabl", "systema.skat.import.header.error.null.invoice.belopp.dkif_fabl"); 
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "dkif_vakd", "systema.skat.import.header.error.null.invoice.valuta.dkif_vakd"); 
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "dkif_vaku", "systema.skat.import.header.error.null.invoice.valutaKurs.dkif_vaku"); 
		
		
		
	}
	/**
	 * Validate if this is an attempt for create new without invoice nr.
	 * @param record
	 * @return
	 */
	private boolean atLeastOneValueExists(JsonSkatImportTopicInvoiceRecord record){
		boolean retval = false;
		if(record!=null){
			/*if(!"".equals(record.getSvif_faty()) || !"".equals(record.getSvif_fabl()) || 
			   !"".equals(record.getSvif_vakd()) || !"".equals(record.getSvif_vaku()) ){
				retval = true;
			}*/
			
			if(!"".equals(record.getDkif_fabl()) || !"".equals(record.getDkif_vakd()) || !"".equals(record.getDkif_vaku()) ){
				retval = true;
			}
		}
		
		return retval;
	}
}
