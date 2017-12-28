/**
 * 
 */
package no.systema.tror.mapper.jsonjackson;


import org.apache.log4j.Logger;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper; 
//application library

import no.systema.tror.model.jsonjackson.order.landexport.childwindow.JsonTrorSellerDeliveryAddressContainer;
import no.systema.tror.model.jsonjackson.order.landexport.childwindow.JsonTrorSellerDeliveryAddressRecord;
import no.systema.tror.model.jsonjackson.order.childwindow.JsonTrorLosseLasteStedContainer;
import no.systema.tror.model.jsonjackson.order.childwindow.JsonTrorLosseLasteStedRecord;
import no.systema.tror.model.jsonjackson.order.landexport.childwindow.JsonTrorBuyerDeliveryAddressContainer;
import no.systema.tror.model.jsonjackson.order.landexport.childwindow.JsonTrorBuyerDeliveryAddressRecord;


//
import java.util.*;

/**
 * 
 * @author oscardelatorre
 * @date Dec 28, 2017
 * 
 * 
 */
public class JsonTrorOrderHeaderMapperLandExportChildWindow {
	private static final Logger logger = Logger.getLogger(JsonTrorOrderHeaderMapperLandExportChildWindow.class.getName());
	
	/**
	 * 
	 * @param utfPayload
	 * @return
	 * @throws Exception
	 */
	public JsonTrorSellerDeliveryAddressContainer getSellerDeliveryAddressesContainer(String utfPayload) throws Exception{
		ObjectMapper mapper = new ObjectMapper();  
		mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES,false);
		
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
		mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES,false);
		
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
	public JsonTrorLosseLasteStedContainer getLosseLasteContainer(String utfPayload) throws Exception{
		ObjectMapper mapper = new ObjectMapper();  
		mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES,false);
		
		//At this point we now have an UTF-8 payload
		JsonTrorLosseLasteStedContainer container = mapper.readValue(utfPayload.getBytes(), JsonTrorLosseLasteStedContainer.class); 
		
		//DEBUG
		Collection<JsonTrorLosseLasteStedRecord> fields = container.getDtoList();
		for(JsonTrorLosseLasteStedRecord record : fields){
			//logger.info("todo: " + record.todo());

		}
		return container;
	}
	
}
