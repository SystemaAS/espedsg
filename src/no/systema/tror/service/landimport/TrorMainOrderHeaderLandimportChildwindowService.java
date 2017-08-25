package no.systema.tror.service.landimport;

import no.systema.main.model.jsonjackson.general.postalcodes.JsonPostalCodesContainer;
import no.systema.tror.model.jsonjackson.order.landimport.childwindow.JsonTrorSellerDeliveryAddressContainer;
import no.systema.tror.model.jsonjackson.order.landimport.childwindow.JsonTrorBuyerDeliveryAddressContainer;
import no.systema.tror.model.jsonjackson.order.landimport.childwindow.JsonTrorLoadUnloadPlacesContainer;



public interface TrorMainOrderHeaderLandimportChildwindowService {
	//public JsonPostalCodesContainer getPostalCodesContainer(String utfPayload);
	//public JsonEbookingCustomerContainer getCustomerContainer(String utfPayload);
	//public JsonEbookingLoadUnloadPlacesContainer getLoadUnloadPlacesContainer(String utfPayload);
	public JsonTrorBuyerDeliveryAddressContainer getBuyerDeliveryAddressContainer(String utfPayload);
	public JsonTrorSellerDeliveryAddressContainer getSellerDeliveryAddressContainer(String utfPayload);
}
