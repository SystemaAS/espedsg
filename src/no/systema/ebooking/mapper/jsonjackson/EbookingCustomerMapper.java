/**
 * 
 */
package no.systema.ebooking.mapper.jsonjackson;


import org.apache.log4j.Logger;
import org.codehaus.jackson.map.DeserializationConfig;
import org.codehaus.jackson.map.ObjectMapper;
//application library

import no.systema.ebooking.model.jsonjackson.order.childwindow.JsonEbookingCustomerContainer;
import no.systema.ebooking.model.jsonjackson.order.childwindow.JsonEbookingCustomerRecord;

//
import java.util.*;

/**
 * 
 * @author oscardelatorre
 * @date Jan 30, 2017
 * 
 * 
 */
public class EbookingCustomerMapper {
	private static final Logger logger = Logger.getLogger(EbookingCustomerMapper.class.getName());
	/**
	 * 
	 * @param utfPayload
	 * @return
	 * @throws Exception
	 */
	public JsonEbookingCustomerContainer getContainer(String utfPayload) throws Exception{
		ObjectMapper mapper = new ObjectMapper();  
		mapper.configure(DeserializationConfig.Feature.FAIL_ON_UNKNOWN_PROPERTIES,false);
		
		//At this point we now have an UTF-8 payload
		JsonEbookingCustomerContainer container = mapper.readValue(utfPayload.getBytes(), JsonEbookingCustomerContainer.class); 
		
		//DEBUG
		Collection<JsonEbookingCustomerRecord> fields = container.getInqcustomer();
		for(JsonEbookingCustomerRecord record : fields){
			//logger.info("knavn: " + record.getKnavn());
			//logger.info("kundnr: " + record.getKundnr());
		}
		return container;
	}
	
}
