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
		
		/*
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "svef_fatx", "systema.tds.export.header.error.null.invoice.invnr.svef_fatx");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "svef_faty", "systema.tds.export.header.error.null.invoice.typ.svef_faty"); 
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "svef_fabl", "systema.tds.export.header.error.null.invoice.belopp.svef_fabl"); 
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "svef_vakd", "systema.tds.export.header.error.null.invoice.valuta.svef_vakd"); 
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "svef_vaku", "systema.tds.export.header.error.null.invoice.valutaKurs.svef_vaku"); 
		*/
		
		
	}
	/**
	 * Validate if this is an attempt for create new without invoice nr.
	 * @param record
	 * @return
	 */
	private boolean atLeastOneValueExists(JsonSkatImportTopicInvoiceRecord record){
		boolean retval = false;
		if(record!=null){
			if(!"".equals(record.getSvif_faty()) || !"".equals(record.getSvif_fabl()) || 
			   !"".equals(record.getSvif_vakd()) || !"".equals(record.getSvif_vaku()) ){
				retval = true;
			}
		}
		
		return retval;
	}
}
