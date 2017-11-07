/**
 * 
 */
package no.systema.skat.nctsimport.mapper.jsonjackson;

//
import java.util.Collection;

//jackson library
import org.apache.log4j.Logger;

import com.fasterxml.jackson.databind.DeserializationConfig;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;

//application library
import no.systema.skat.nctsimport.model.jsonjackson.topic.logging.JsonSkatNctsImportSpecificTopicLoggingLargeTextContainer;
import no.systema.skat.nctsimport.model.jsonjackson.topic.logging.JsonSkatNctsImportSpecificTopicLoggingLargeTextRecord;

/**
 * @author oscardelatorre
 * @date Apr 24, 2014
 * 
 */
public class SkatNctsImportSpecificTopicLoggingLargeTextMapper {
	private static final Logger logger = Logger.getLogger(SkatNctsImportSpecificTopicLoggingLargeTextMapper.class.getName());
	
	public JsonSkatNctsImportSpecificTopicLoggingLargeTextContainer getContainer(String utfPayload) throws Exception{
		ObjectMapper mapper = new ObjectMapper();  
		mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES,false);
		
		//At this point we now have an UTF-8 payload
		JsonSkatNctsImportSpecificTopicLoggingLargeTextContainer container = mapper.readValue(utfPayload.getBytes(), JsonSkatNctsImportSpecificTopicLoggingLargeTextContainer.class); 
		//logger.info(mapper.writeValueAsString(topicListContainer));
		logger.info("[JSON-String payload status=OK]  " + container.getUser());
		//DEBUG
		Collection<JsonSkatNctsImportSpecificTopicLoggingLargeTextRecord> list = container.getLoggtext();
		for(JsonSkatNctsImportSpecificTopicLoggingLargeTextRecord record : list){
			//logger.info("Message nr (topic logging): " + record.getF0078a());
		}
		return container;
	}
}
