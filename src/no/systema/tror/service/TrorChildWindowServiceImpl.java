package no.systema.tror.service;

import no.systema.tror.model.jsonjackson.order.childwindow.JsonTrorCustomerDeliveryAddressContainer;
import no.systema.tror.model.jsonjackson.order.childwindow.JsonTrorLoadUnloadPlacesContainer;
import no.systema.tror.mapper.jsonjackson.JsonTrorChildWindowMapper;
//import no.systema.main.model.jsonjackson.general.postalcodes.JsonPostalCodesContainer;
//import no.systema.main.mapper.jsonjackson.general.PostalCodesMapper;

/**
 * 
 * @author oscardelatorre
 * @date Aug 24, 2017 
 * 
 */
public class TrorChildWindowServiceImpl implements TrorChildWindowService {
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
	 * 
	 */
	public JsonTrorCustomerDeliveryAddressContainer getCustomerDeliveryAddressContainer(String utfPayload){
		JsonTrorCustomerDeliveryAddressContainer container = null;
		try{
			JsonTrorChildWindowMapper mapper = new JsonTrorChildWindowMapper();
			container = mapper.getCustomerDeliveryAddressesContainer(utfPayload);
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
