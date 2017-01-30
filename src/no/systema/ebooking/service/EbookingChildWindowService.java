package no.systema.ebooking.service;

import no.systema.main.model.jsonjackson.general.postalcodes.JsonPostalCodesContainer;
import no.systema.ebooking.model.jsonjackson.order.childwindow.JsonEbookingCustomerContainer;


public interface EbookingChildWindowService {
	public JsonPostalCodesContainer getPostalCodesContainer(String utfPayload);
	public JsonEbookingCustomerContainer getCustomerContainer(String utfPayload);
	
	
}
