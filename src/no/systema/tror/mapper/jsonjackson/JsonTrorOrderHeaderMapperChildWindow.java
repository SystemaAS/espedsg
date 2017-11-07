/**
 * 
 */
package no.systema.tror.mapper.jsonjackson;


import org.apache.log4j.Logger;
import com.fasterxml.jackson.databind.DeserializationConfig;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper; 
//application library

import no.systema.tror.model.jsonjackson.order.childwindow.JsonTrorCarrierContainer;
import no.systema.tror.model.jsonjackson.order.childwindow.JsonTrorCarrierRecord;
import no.systema.tror.model.jsonjackson.order.childwindow.JsonTrorPostalCodeContainer;
import no.systema.tror.model.jsonjackson.order.childwindow.JsonTrorPostalCodeRecord;
import no.systema.tror.model.jsonjackson.order.childwindow.JsonTrorTollstedContainer;
import no.systema.tror.model.jsonjackson.order.childwindow.JsonTrorTollstedRecord;



//
import java.util.*;

/**
 * 
 * @author oscardelatorre
 * @date Aug 27, 2017
 * 
 * 
 */
public class JsonTrorOrderHeaderMapperChildWindow {
	private static final Logger logger = Logger.getLogger(JsonTrorOrderHeaderMapperChildWindow.class.getName());
	
	/**
	 * 
	 * @param utfPayload
	 * @return
	 * @throws Exception
	 */
	public JsonTrorCarrierContainer getCarrierContainer(String utfPayload) throws Exception{
		ObjectMapper mapper = new ObjectMapper();  
		mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES,false);
		
		//At this point we now have an UTF-8 payload
		JsonTrorCarrierContainer container = mapper.readValue(utfPayload.getBytes(), JsonTrorCarrierContainer.class); 
		
		//DEBUG
		Collection<JsonTrorCarrierRecord> fields = container.getDtoList();
		for(JsonTrorCarrierRecord record : fields){
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
	public JsonTrorPostalCodeContainer getPostnrContainer(String utfPayload) throws Exception{
		ObjectMapper mapper = new ObjectMapper();  
		mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES,false);
		
		//At this point we now have an UTF-8 payload
		JsonTrorPostalCodeContainer container = mapper.readValue(utfPayload.getBytes(), JsonTrorPostalCodeContainer.class); 
		
		//DEBUG
		Collection<JsonTrorPostalCodeRecord> fields = container.getDtoList();
		for(JsonTrorPostalCodeRecord record : fields){
			//logger.info("knavn: " + record.todo());
			//logger.info("kundnr: " + record.todo());
		}
		return container;
	}
	/**
	 * 
	 * @param utfPayload
	 * @return
	 * @throws Exception
	 */
	public JsonTrorTollstedContainer getTollstedContainer(String utfPayload) throws Exception{
		ObjectMapper mapper = new ObjectMapper();  
		mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES,false);
		
		//At this point we now have an UTF-8 payload
		JsonTrorTollstedContainer container = mapper.readValue(utfPayload.getBytes(), JsonTrorTollstedContainer.class); 
		
		//DEBUG
		Collection<JsonTrorTollstedRecord> fields = container.getDtoList();
		for(JsonTrorTollstedRecord record : fields){
			//logger.info("knavn: " + record.todo());
			//logger.info("kundnr: " + record.todo());
		}
		return container;
	}
	
	
}
