/**
 * 
 */
package no.systema.skat.skatexport.mapper.jsonjackson;

//
import java.util.Collection;

//jackson library
import org.apache.log4j.Logger;

import com.fasterxml.jackson.databind.DeserializationConfig;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;

//application library
import no.systema.skat.skatexport.model.jsonjackson.topic.logging.JsonSkatExportSpecificTopicLoggingContainer;
import no.systema.skat.skatexport.model.jsonjackson.topic.logging.JsonSkatExportSpecificTopicLoggingRecord;

/**
 * @author oscardelatorre
 * @date Mar 11, 2014
 * 
 */
public class SkatExportSpecificTopicLoggingMapper {
	private static final Logger logger = Logger.getLogger(SkatExportSpecificTopicLoggingMapper.class.getName());
	
	public JsonSkatExportSpecificTopicLoggingContainer getContainer(String utfPayload) throws Exception{
		ObjectMapper mapper = new ObjectMapper();  
		mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES,false);
		
		//At this point we now have an UTF-8 payload
		JsonSkatExportSpecificTopicLoggingContainer container = mapper.readValue(utfPayload.getBytes(), JsonSkatExportSpecificTopicLoggingContainer.class); 
		//logger.info(mapper.writeValueAsString(topicListContainer));
		logger.info("[JSON-String payload status=OK]  " + container.getUser());
		//DEBUG
		Collection<JsonSkatExportSpecificTopicLoggingRecord> list = container.getLogg();
		for(JsonSkatExportSpecificTopicLoggingRecord record : list){
			//logger.info("Message nr (topic logging): " + record.getMmn());
		}
		return container;
	}
}
