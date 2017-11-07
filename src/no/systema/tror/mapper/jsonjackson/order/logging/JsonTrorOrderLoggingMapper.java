/**
 * 
 */
package no.systema.tror.mapper.jsonjackson.order.logging;

//jackson library
import org.apache.log4j.Logger;
import com.fasterxml.jackson.databind.DeserializationConfig;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper; 
//application library
import no.systema.tror.model.jsonjackson.logging.JsonTrorOrderHeaderLoggingContainer;
import no.systema.tror.model.jsonjackson.logging.JsonTrorOrderHeaderLoggingRecord;
//
import java.util.*;

/**
 * @author oscardelatorre
 * @date Sep 18, 2017
 * 
 */
public class JsonTrorOrderLoggingMapper {
	private static final Logger logger = Logger.getLogger(JsonTrorOrderLoggingMapper.class.getName());
	
	public JsonTrorOrderHeaderLoggingContainer getContainer(String utfPayload) throws Exception{
		ObjectMapper mapper = new ObjectMapper();  
		mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES,false);
		
		//At this point we now have an UTF-8 payload
		JsonTrorOrderHeaderLoggingContainer container = mapper.readValue(utfPayload.getBytes(), JsonTrorOrderHeaderLoggingContainer.class); 
		//logger.info(mapper.writeValueAsString(topicListContainer));
		logger.info("[JSON-String payload status=OK]  " + container.getUser());
		//DEBUG
		Collection<JsonTrorOrderHeaderLoggingRecord> list = container.getLogg();
		for(JsonTrorOrderHeaderLoggingRecord record : list){
			//logger.info("Message nr (topic logging): " + record.getMmn());
		}
		return container;
	}
}
