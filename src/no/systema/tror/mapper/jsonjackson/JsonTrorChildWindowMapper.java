/**
 * 
 */
package no.systema.tror.mapper.jsonjackson;


import org.apache.log4j.Logger;
import org.codehaus.jackson.map.DeserializationConfig;
import org.codehaus.jackson.map.ObjectMapper;
//application library

import no.systema.tror.model.jsonjackson.order.childwindow.JsonTrorCustomerDeliveryAddressContainer;
import no.systema.tror.model.jsonjackson.order.childwindow.JsonTrorCustomerDeliveryAddressRecord;
import no.systema.tror.model.jsonjackson.order.childwindow.JsonTrorLoadUnloadPlacesContainer;
import no.systema.tror.model.jsonjackson.order.childwindow.JsonTrorLoadUnloadPlacesRecord;
//
import java.util.*;

/**
 * 
 * @author oscardelatorre
 * @date Aug 24, 2017
 * 
 * 
 */
public class JsonTrorChildWindowMapper {
	private static final Logger logger = Logger.getLogger(JsonTrorChildWindowMapper.class.getName());
	
	/**
	 * 
	 * @param utfPayload
	 * @return
	 * @throws Exception
	 */
	public JsonTrorCustomerDeliveryAddressContainer getCustomerDeliveryAddressesContainer(String utfPayload) throws Exception{
		ObjectMapper mapper = new ObjectMapper();  
		mapper.configure(DeserializationConfig.Feature.FAIL_ON_UNKNOWN_PROPERTIES,false);
		
		//At this point we now have an UTF-8 payload
		JsonTrorCustomerDeliveryAddressContainer container = mapper.readValue(utfPayload.getBytes(), JsonTrorCustomerDeliveryAddressContainer.class); 
		
		//DEBUG
		Collection<JsonTrorCustomerDeliveryAddressRecord> fields = container.getDtoList();
		for(JsonTrorCustomerDeliveryAddressRecord record : fields){
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
