package no.systema.ebooking.service;

import no.systema.main.model.jsonjackson.general.postalcodes.JsonPostalCodesContainer;

public interface EbookingChildWindowService {
	public JsonPostalCodesContainer getPostalCodesContainer(String utfPayload);
}
