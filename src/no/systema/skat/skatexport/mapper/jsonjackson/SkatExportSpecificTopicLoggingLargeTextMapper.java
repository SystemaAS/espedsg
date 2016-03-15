/**
 * 
 */
package no.systema.skat.skatexport.mapper.jsonjackson;

//jackson library
import org.apache.log4j.Logger;
import org.codehaus.jackson.map.DeserializationConfig;
import org.codehaus.jackson.map.ObjectMapper;
//application library
import no.systema.skat.skatexport.model.jsonjackson.topic.logging.JsonSkatExportSpecificTopicLoggingLargeTextContainer;
import no.systema.skat.skatexport.model.jsonjackson.topic.logging.JsonSkatExportSpecificTopicLoggingLargeTextRecord;
//
import java.util.*;

/**
 * @author oscardelatorre
 * @date Mar 11, 2014
 * 
 */
public class SkatExportSpecificTopicLoggingLargeTextMapper {
	private static final Logger logger = Logger.getLogger(SkatExportSpecificTopicLoggingLargeTextMapper.class.getName());
	
	public JsonSkatExportSpecificTopicLoggingLargeTextContainer getContainer(String utfPayload) throws Exception{
		ObjectMapper mapper = new ObjectMapper();  
		mapper.configure(DeserializationConfig.Feature.FAIL_ON_UNKNOWN_PROPERTIES,false);
		
		//At this point we now have an UTF-8 payload
		JsonSkatExportSpecificTopicLoggingLargeTextContainer container = mapper.readValue(utfPayload.getBytes(), JsonSkatExportSpecificTopicLoggingLargeTextContainer.class); 
		//logger.info(mapper.writeValueAsString(topicListContainer));
		logger.info("[JSON-String payload status=OK]  " + container.getUser());
		//DEBUG
		Collection<JsonSkatExportSpecificTopicLoggingLargeTextRecord> list = container.getLoggtext();
		for(JsonSkatExportSpecificTopicLoggingLargeTextRecord record : list){
			//logger.info("Message nr (topic logging): " + record.getF0078a());
		}
		return container;
	}
}
