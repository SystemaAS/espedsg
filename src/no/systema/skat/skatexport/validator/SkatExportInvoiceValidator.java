package no.systema.skat.skatexport.validator;

import org.springframework.validation.Validator;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;

import no.systema.skat.skatexport.model.jsonjackson.topic.JsonSkatExportTopicInvoiceRecord;

/**
 * 
 * @author oscardelatorre
 * @date Apr 6, 2016
 * 
 */
public class SkatExportInvoiceValidator implements Validator {

	/**
	 * 
	 */
	public boolean supports(Class clazz) {
		return JsonSkatExportTopicInvoiceRecord.class.isAssignableFrom(clazz); 
	}
	
	/**
	 * @param obj
	 * @param errors
	 * 
	 */
	public void validate(Object obj, Errors errors) { 
		JsonSkatExportTopicInvoiceRecord record = (JsonSkatExportTopicInvoiceRecord)obj;
		
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
	private boolean atLeastOneValueExists(JsonSkatExportTopicInvoiceRecord record){
		boolean retval = false;
		if(record!=null){
			if(!"".equals(record.getSvef_faty()) || !"".equals(record.getSvef_fabl()) || 
			   !"".equals(record.getSvef_vakd()) || !"".equals(record.getSvef_vaku()) ){
				retval = true;
			}
		}
		
		return retval;
	}
}
