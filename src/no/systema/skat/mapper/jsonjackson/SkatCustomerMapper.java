/**
 * 
 */
package no.systema.skat.mapper.jsonjackson;

//
import java.util.Collection;

//jackson library
import org.apache.log4j.Logger;

import com.fasterxml.jackson.databind.DeserializationConfig;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;

//application library
import no.systema.skat.model.jsonjackson.customer.JsonSkatCustomerContainer;
import no.systema.skat.model.jsonjackson.customer.JsonSkatCustomerRecord;

/**
 * 
 * @author oscardelatorre
 * @date Apr 18, 2014
 * 
 * 
 */
public class SkatCustomerMapper {
	private static final Logger logger = Logger.getLogger(SkatCustomerMapper.class.getName());
	
	public JsonSkatCustomerContainer getContainer(String utfPayload) throws Exception{
		ObjectMapper mapper = new ObjectMapper();  
		mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES,false);
		
		//At this point we now have an UTF-8 payload
		JsonSkatCustomerContainer customerListContainer = mapper.readValue(utfPayload.getBytes(), JsonSkatCustomerContainer.class); 
		//logger.info(mapper.writeValueAsString(topicListContainer));
		logger.info("Mapping Customer object from JSON payload...");
		logger.info("[JSON-String payload status=OK]  " + customerListContainer.getUser());
		
		//DEBUG
		Collection<JsonSkatCustomerRecord> fields = customerListContainer.getCustomerlist();
		for(JsonSkatCustomerRecord record : fields){
			//logger.info("knavn: " + record.getKnavn());
			//logger.info("kundnr: " + record.getKundnr());
		}
		return customerListContainer;
	}
}
