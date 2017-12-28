package no.systema.tror.service.landexport;

import no.systema.tror.mapper.jsonjackson.JsonTrorOrderHeaderMapperLandImportChildWindow;
//import no.systema.main.model.jsonjackson.general.postalcodes.JsonPostalCodesContainer;
//import no.systema.main.mapper.jsonjackson.general.PostalCodesMapper;
import no.systema.tror.model.jsonjackson.order.landimport.childwindow.JsonTrorSellerDeliveryAddressContainer;
import no.systema.tror.model.jsonjackson.order.landimport.childwindow.JsonTrorBuyerDeliveryAddressContainer;
import no.systema.tror.model.jsonjackson.order.landimport.childwindow.JsonTrorLoadUnloadPlacesContainer;

/**
 * 
 * @author oscardelatorre
 * @date Aug 24, 2017 
 * 
 */
public class TrorMainOrderHeaderLandexportChildwindowServiceImpl implements TrorMainOrderHeaderLandexportChildwindowService {
	/**
	 * 
	 */
	/*
	public JsonPostalCodesContainer getPostalCodesContainer(String utfPayload){
		JsonPostalCodesContainer container = null;
		try{
			PostalCodesMapper mapper = new PostalCodesMapper();
			container = mapper.getContainer(utfPayload);
		}catch(Exception e){
			e.printStackTrace();
		}
		return container;
	}
	*/
	
	/**
	 * SELLER
	 */
	public JsonTrorSellerDeliveryAddressContainer getSellerDeliveryAddressContainer(String utfPayload){
		JsonTrorSellerDeliveryAddressContainer container = null;
		try{
			JsonTrorOrderHeaderMapperLandImportChildWindow mapper = new JsonTrorOrderHeaderMapperLandImportChildWindow();
			container = mapper.getSellerDeliveryAddressesContainer(utfPayload);
		}catch(Exception e){
			e.printStackTrace();
		}
		return container;
	}
	/**
	 * BUYER
	 * @param utfPayload
	 * @return
	 */
	public JsonTrorBuyerDeliveryAddressContainer getBuyerDeliveryAddressContainer(String utfPayload){
		JsonTrorBuyerDeliveryAddressContainer container = null;
		try{
			JsonTrorOrderHeaderMapperLandImportChildWindow mapper = new JsonTrorOrderHeaderMapperLandImportChildWindow();
			container = mapper.getBuyerDeliveryAddressesContainer(utfPayload);
		}catch(Exception e){
			e.printStackTrace();
		}
		return container;
	}
	/**
	 * 
	 */
	/*
	public JsonEbookingLoadUnloadPlacesContainer getLoadUnloadPlacesContainer(String utfPayload){
		JsonEbookingLoadUnloadPlacesContainer container = null;
		try{
			JsonEbookingChildWindowMapper mapper = new JsonEbookingChildWindowMapper();
			container = mapper.getLoadUnloadPlacesContainer(utfPayload);
		}catch(Exception e){
			e.printStackTrace();
		}
		return container;
	}
	*/
	/**
	 * 
	 */
	}
