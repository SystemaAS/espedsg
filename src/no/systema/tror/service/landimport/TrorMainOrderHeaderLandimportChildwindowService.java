package no.systema.tror.service.landimport;


import no.systema.tror.model.jsonjackson.order.landimport.childwindow.JsonTrorSellerDeliveryAddressContainer;
import no.systema.tror.model.jsonjackson.order.landimport.childwindow.JsonTrorBuyerDeliveryAddressContainer;



public interface TrorMainOrderHeaderLandimportChildwindowService {

	public JsonTrorBuyerDeliveryAddressContainer getBuyerDeliveryAddressContainer(String utfPayload);
	public JsonTrorSellerDeliveryAddressContainer getSellerDeliveryAddressContainer(String utfPayload);
}
