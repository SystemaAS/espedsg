/**
 * 
 */
package no.systema.skat.skatimport.mapper.jsonjackson;

//
import java.util.Collection;

//jackson library
import org.apache.log4j.Logger;

import com.fasterxml.jackson.databind.DeserializationConfig;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;

//application library
import no.systema.skat.skatimport.model.jsonjackson.topic.logging.JsonSkatImportSpecificTopicLoggingContainer;
import no.systema.skat.skatimport.model.jsonjackson.topic.logging.JsonSkatImportSpecificTopicLoggingRecord;

/**
 * @author oscardelatorre
 * @date Feb 14, 2014
 * 
 */
public class SkatImportSpecificTopicLoggingMapper {
	private static final Logger logger = Logger.getLogger(SkatImportSpecificTopicLoggingMapper.class.getName());
	
	public JsonSkatImportSpecificTopicLoggingContainer getContainer(String utfPayload) throws Exception{
		ObjectMapper mapper = new ObjectMapper();  
		mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES,false);
		
		//At this point we now have an UTF-8 payload
		JsonSkatImportSpecificTopicLoggingContainer container = mapper.readValue(utfPayload.getBytes(), JsonSkatImportSpecificTopicLoggingContainer.class); 
		//logger.info(mapper.writeValueAsString(topicListContainer));
		logger.info("[JSON-String payload status=OK]  " + container.getUser());
		
		//DEBUG
		Collection<JsonSkatImportSpecificTopicLoggingRecord> list = container.getLogg();
		for(JsonSkatImportSpecificTopicLoggingRecord record : list){
			//logger.info("Message nr (topic logging): " + record.getMmn());
		}
		return container;
	}
}
