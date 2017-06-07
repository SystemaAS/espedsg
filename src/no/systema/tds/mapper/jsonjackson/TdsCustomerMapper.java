/**
 * 
 */
package no.systema.tds.mapper.jsonjackson;

//jackson library
import org.apache.log4j.Logger;
import org.codehaus.jackson.map.DeserializationConfig;
import org.codehaus.jackson.map.ObjectMapper;
//application library
import no.systema.tds.model.jsonjackson.customer.JsonTdsCustomerContainer;
import no.systema.tds.model.jsonjackson.customer.JsonTdsCustomerRecord;


//
import java.util.*;

/**
 * @author oscardelatorre
 * 
 */
public class TdsCustomerMapper {
	private static final Logger logger = Logger.getLogger(TdsCustomerMapper.class.getName());
	
	public JsonTdsCustomerContainer getContainer(String utfPayload) throws Exception{
		ObjectMapper mapper = new ObjectMapper();  
		mapper.configure(DeserializationConfig.Feature.FAIL_ON_UNKNOWN_PROPERTIES,false);
		
		//At this point we now have an UTF-8 payload
		JsonTdsCustomerContainer customerListContainer = mapper.readValue(utfPayload.getBytes(), JsonTdsCustomerContainer.class); 
		//logger.info(mapper.writeValueAsString(topicListContainer));
		logger.info("Mapping Customer object from JSON payload...");
		logger.info("[JSON-String payload status=OK]  " + customerListContainer.getUser());
		
		//DEBUG
		Collection<JsonTdsCustomerRecord> fields = customerListContainer.getCustomerlist();
		for(JsonTdsCustomerRecord record : fields){
			logger.info("knavn: " + record.getKnavn());
			logger.info("kundnr: " + record.getKundnr());
			
		}
			
		return customerListContainer;
	}
}
