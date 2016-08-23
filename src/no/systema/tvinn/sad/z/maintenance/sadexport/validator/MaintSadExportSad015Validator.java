package no.systema.tvinn.sad.z.maintenance.sadexport.validator;

import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import no.systema.tvinn.sad.z.maintenance.sadexport.model.jsonjackson.dbtable.JsonMaintSadExportSadavgeRecord;

/**
 * 
 * @author Fredrik Möller
 * @date Aug 22, 2016
 * 
 *
 */
public class MaintSadExportSad015Validator implements Validator {

	/**
	 * 
	 */
	public boolean supports(Class clazz) {
		return JsonMaintSadExportSadavgeRecord.class.isAssignableFrom(clazz);
	}

	/**
	 * @param obj
	 * @param errors
	 * 
	 */
	public void validate(Object obj, Errors errors) {

		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "agtanr", "", "Tariffnr. (AGTANR) er obligatorisk");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "agskv", "", "Avg. (AGSKV) er obligatorisk");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "agdtf", "", "F.o.m dato (AGDTF) er obligatorisk");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "agdtt", "", "T.o.m dato (AGDTT) er obligatorisk");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "agsats", "", "Sats (AGSATS) er obligatorisk");

	}

	/**
	 * 
	 * @param obj
	 * @param errors
	 */

	public void validateDelete(Object obj, Errors errors) {

		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "agtanr", "", "Tariffnr. (AGTANR) er obligatorisk");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "agakd", "", "Tariffnr. (AGAKD) er obligatorisk");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "agskv", "", "Avg. (AGSKV) er obligatorisk");

	}

}
