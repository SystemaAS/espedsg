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
import no.systema.tror.model.jsonjackson.logging.JsonTrorOrderHeaderLoggingLargeTextContainer;
import no.systema.tror.model.jsonjackson.logging.JsonTrorOrderHeaderLoggingLargeTextRecord;
//
import java.util.*;

/**
 * @author oscardelatorre
 * @date Sep 18, 2017
 * 
 */
public class JsonTrorOrderLoggingLargeTextMapper {
	private static final Logger logger = Logger.getLogger(JsonTrorOrderLoggingLargeTextMapper.class.getName());
	
	public JsonTrorOrderHeaderLoggingLargeTextContainer getContainer(String utfPayload) throws Exception{
		ObjectMapper mapper = new ObjectMapper();  
		mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES,false);
		
		//At this point we now have an UTF-8 payload
		JsonTrorOrderHeaderLoggingLargeTextContainer container = mapper.readValue(utfPayload.getBytes(), JsonTrorOrderHeaderLoggingLargeTextContainer.class); 
		//logger.info(mapper.writeValueAsString(topicListContainer));
		logger.info("[JSON-String payload status=OK]  " + container.getUser());
		//DEBUG
		Collection<JsonTrorOrderHeaderLoggingLargeTextRecord> list = container.getLoggtext();
		for(JsonTrorOrderHeaderLoggingLargeTextRecord record : list){
			//logger.info("Message nr (topic logging): " + record.getF0078a());
		}
		return container;
	}
}
