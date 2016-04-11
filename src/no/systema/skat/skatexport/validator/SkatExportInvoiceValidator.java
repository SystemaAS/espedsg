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
		
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "dkef_fatx", "systema.skat.export.header.error.null.invoice.invnr.dkef_fatx");
		//ValidationUtils.rejectIfEmptyOrWhitespace(errors, "dkef_faty", "systema.skat.export.header.error.null.invoice.typ.dkef_faty"); 
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "dkef_fabl", "systema.skat.export.header.error.null.invoice.belopp.dkef_fabl"); 
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "dkef_vakd", "systema.skat.export.header.error.null.invoice.valuta.dkef_vakd"); 
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "dkef_vaku", "systema.skat.export.header.error.null.invoice.valutaKurs.dkef_vaku"); 
		
		
	}
	/**
	 * Validate if this is an attempt for create new without invoice nr.
	 * @param record
	 * @return
	 */
	private boolean atLeastOneValueExists(JsonSkatExportTopicInvoiceRecord record){
		boolean retval = false;
		if(record!=null){
			/*
			if(!"".equals(record.getDkef_faty()) || !"".equals(record.getDkef_fabl()) || 
			   !"".equals(record.getDkef_vakd()) || !"".equals(record.getDkef_vaku()) ){
				retval = true;
			}*/
			if(!"".equals(record.getDkef_fabl()) || !"".equals(record.getDkef_vakd()) || !"".equals(record.getDkef_vaku()) ){
					retval = true;
			}
		}
		return retval;
	}
}
