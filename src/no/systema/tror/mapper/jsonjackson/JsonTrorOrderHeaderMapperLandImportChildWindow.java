/**
 * 
 */
package no.systema.tror.mapper.jsonjackson;


import org.apache.log4j.Logger;
import org.codehaus.jackson.map.DeserializationConfig;
import org.codehaus.jackson.map.ObjectMapper;
//application library

import no.systema.tror.model.jsonjackson.order.landimport.childwindow.JsonTrorSellerDeliveryAddressContainer;
import no.systema.tror.model.jsonjackson.order.landimport.childwindow.JsonTrorSellerDeliveryAddressRecord;
import no.systema.tror.model.jsonjackson.order.landimport.childwindow.JsonTrorBuyerDeliveryAddressContainer;
import no.systema.tror.model.jsonjackson.order.landimport.childwindow.JsonTrorBuyerDeliveryAddressRecord;

import no.systema.tror.model.jsonjackson.order.landimport.childwindow.JsonTrorLoadUnloadPlacesContainer;
import no.systema.tror.model.jsonjackson.order.landimport.childwindow.JsonTrorLoadUnloadPlacesRecord;

//
import java.util.*;

/**
 * 
 * @author oscardelatorre
 * @date Aug 24, 2017
 * 
 * 
 */
public class JsonTrorOrderHeaderMapperLandImportChildWindow {
	private static final Logger logger = Logger.getLogger(JsonTrorOrderHeaderMapperLandImportChildWindow.class.getName());
	
	/**
	 * 
	 * @param utfPayload
	 * @return
	 * @throws Exception
	 */
	public JsonTrorSellerDeliveryAddressContainer getSellerDeliveryAddressesContainer(String utfPayload) throws Exception{
		ObjectMapper mapper = new ObjectMapper();  
		mapper.configure(DeserializationConfig.Feature.FAIL_ON_UNKNOWN_PROPERTIES,false);
		
		//At this point we now have an UTF-8 payload
		JsonTrorSellerDeliveryAddressContainer container = mapper.readValue(utfPayload.getBytes(), JsonTrorSellerDeliveryAddressContainer.class); 
		
		//DEBUG
		Collection<JsonTrorSellerDeliveryAddressRecord> fields = container.getDtoList();
		for(JsonTrorSellerDeliveryAddressRecord record : fields){
			//logger.info("knavn: " + record.getKnavn());
			//logger.info("kundnr: " + record.getKundnr());
		}
		return container;
	}
	
	/**
	 * 
	 * @param utfPayload
	 * @return
	 * @throws Exception
	 */
	public JsonTrorBuyerDeliveryAddressContainer getBuyerDeliveryAddressesContainer(String utfPayload) throws Exception{
		ObjectMapper mapper = new ObjectMapper();  
		mapper.configure(DeserializationConfig.Feature.FAIL_ON_UNKNOWN_PROPERTIES,false);
		
		//At this point we now have an UTF-8 payload
		JsonTrorBuyerDeliveryAddressContainer container = mapper.readValue(utfPayload.getBytes(), JsonTrorBuyerDeliveryAddressContainer.class); 
		
		//DEBUG
		Collection<JsonTrorBuyerDeliveryAddressRecord> fields = container.getDtoList();
		for(JsonTrorBuyerDeliveryAddressRecord record : fields){
			//logger.info("knavn: " + record.getKnavn());
			//logger.info("kundnr: " + record.getKundnr());
		}
		return container;
	}
	/**
	 * 
	 * @param utfPayload
	 * @return
	 * @throws Exception
	 */
	/*
	public JsonEbookingLoadUnloadPlacesContainer getLoadUnloadPlacesContainer(String utfPayload) throws Exception{
		ObjectMapper mapper = new ObjectMapper();  
		mapper.configure(DeserializationConfig.Feature.FAIL_ON_UNKNOWN_PROPERTIES,false);
		
		//At this point we now have an UTF-8 payload
		JsonEbookingLoadUnloadPlacesContainer container = mapper.readValue(utfPayload.getBytes(), JsonEbookingLoadUnloadPlacesContainer.class); 
		
		//DEBUG
		Collection<JsonEbookingLoadUnloadPlacesRecord> fields = container.getInqlosslass();
		for(JsonEbookingLoadUnloadPlacesRecord record : fields){
			//logger.info("knavn: " + record.getKnavn());
			//logger.info("kundnr: " + record.getKundnr());
		}
		return container;
	}
	*/
	
}
