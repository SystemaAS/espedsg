package no.systema.tror.service.landexport;


import no.systema.tror.model.jsonjackson.order.landimport.childwindow.JsonTrorSellerDeliveryAddressContainer;
import no.systema.tror.model.jsonjackson.order.landimport.childwindow.JsonTrorBuyerDeliveryAddressContainer;



public interface TrorMainOrderHeaderLandexportChildwindowService {

	public JsonTrorBuyerDeliveryAddressContainer getBuyerDeliveryAddressContainer(String utfPayload);
	public JsonTrorSellerDeliveryAddressContainer getSellerDeliveryAddressContainer(String utfPayload);
}
