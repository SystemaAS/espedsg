package no.systema.tror.service;

import no.systema.main.model.jsonjackson.general.postalcodes.JsonPostalCodesContainer;
import no.systema.tror.model.jsonjackson.order.childwindow.JsonTrorCustomerDeliveryAddressContainer;
import no.systema.tror.model.jsonjackson.order.childwindow.JsonTrorLoadUnloadPlacesContainer;



public interface TrorChildWindowService {
	//public JsonPostalCodesContainer getPostalCodesContainer(String utfPayload);
	//public JsonEbookingCustomerContainer getCustomerContainer(String utfPayload);
	//public JsonEbookingLoadUnloadPlacesContainer getLoadUnloadPlacesContainer(String utfPayload);
	public JsonTrorCustomerDeliveryAddressContainer getCustomerDeliveryAddressContainer(String utfPayload);
}
