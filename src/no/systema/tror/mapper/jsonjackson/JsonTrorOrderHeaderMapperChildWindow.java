/**
 * 
 */
package no.systema.tror.mapper.jsonjackson;


import org.apache.log4j.Logger;
import org.codehaus.jackson.map.DeserializationConfig;
import org.codehaus.jackson.map.ObjectMapper;
//application library

import no.systema.tror.model.jsonjackson.order.childwindow.JsonTrorCarrierContainer;
import no.systema.tror.model.jsonjackson.order.childwindow.JsonTrorCarrierRecord;


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
		mapper.configure(DeserializationConfig.Feature.FAIL_ON_UNKNOWN_PROPERTIES,false);
		
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
	
}
