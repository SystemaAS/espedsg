package no.systema.z.main.maintenance.validator;

import org.apache.log4j.Logger;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import no.systema.jservices.common.dao.SadvareDao;

/**
 * 
 * @author Fredrik Möller
 * @date Mar 28, 2017
 * 
 *
 */
public class MaintMainSadvareValidator implements Validator {
	private static final Logger logger = Logger.getLogger(MaintMainSadvareValidator.class.getName());

	@SuppressWarnings("rawtypes")
	public boolean supports(Class clazz) {
		return SadvareDao.class.isAssignableFrom(clazz);
	}

	public void validate(Object obj, Errors errors) {
		// SadvareDao record = (SadvareDao)obj;

		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "levenr", "systema.maint.params.error.levenr");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "varenr", "systema.maint.params.error.varenr");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "varebe", "systema.maint.params.error.varebe");

	}

	public void validateDelete(Object obj, Errors errors) {
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "levenr", "systema.maint.params.error.levenr");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "varenr", "systema.maint.params.error.varenr");
	}

}
