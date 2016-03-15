/**
 * 
 */
package no.systema.tds.tdsimport.mapper.jsonjackson;

//jackson library
import org.apache.log4j.Logger;
import org.codehaus.jackson.map.DeserializationConfig;
import org.codehaus.jackson.map.ObjectMapper;
//application library
import no.systema.tds.tdsimport.model.jsonjackson.customer.JsonTdsImportCustomerContainer;
import no.systema.tds.tdsimport.model.jsonjackson.customer.JsonTdsImportCustomerRecord;


//
import java.util.*;

/**
 * @author oscardelatorre
 * @date Sep 2, 2103
 * 
 */
public class TdsImportCustomerMapper {
	private static final Logger logger = Logger.getLogger(TdsImportCustomerMapper.class.getName());
	
	public JsonTdsImportCustomerContainer getContainer(String utfPayload) throws Exception{
		ObjectMapper mapper = new ObjectMapper();  
		mapper.configure(DeserializationConfig.Feature.FAIL_ON_UNKNOWN_PROPERTIES,false);
		
		//At this point we now have an UTF-8 payload
		JsonTdsImportCustomerContainer customerListContainer = mapper.readValue(utfPayload.getBytes(), JsonTdsImportCustomerContainer.class); 
		//logger.info(mapper.writeValueAsString(topicListContainer));
		logger.info("Mapping Customer object from JSON payload...");
		logger.info("[JSON-String payload status=OK]  " + customerListContainer.getUser());
		
		//DEBUG
		Collection<JsonTdsImportCustomerRecord> fields = customerListContainer.getCustomerlist();
		for(JsonTdsImportCustomerRecord record : fields){
			logger.info("knavn: " + record.getKnavn());
			logger.info("kundnr: " + record.getKundnr());
			
		}
			
		return customerListContainer;
	}
}
