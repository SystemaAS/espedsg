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
import no.systema.skat.nctsimport.model.jsonjackson.topic.logging.JsonSkatNctsImportSpecificTopicLoggingContainer;
import no.systema.skat.nctsimport.model.jsonjackson.topic.logging.JsonSkatNctsImportSpecificTopicLoggingRecord;

/**
 * @author oscardelatorre
 * @date Apr 24, 2014
 * 
 */
public class SkatNctsImportSpecificTopicLoggingMapper {
	private static final Logger logger = Logger.getLogger(SkatNctsImportSpecificTopicLoggingMapper.class.getName());
	
	public JsonSkatNctsImportSpecificTopicLoggingContainer getContainer(String utfPayload) throws Exception{
		ObjectMapper mapper = new ObjectMapper();  
		mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES,false);
		
		//At this point we now have an UTF-8 payload
		JsonSkatNctsImportSpecificTopicLoggingContainer container = mapper.readValue(utfPayload.getBytes(), JsonSkatNctsImportSpecificTopicLoggingContainer.class); 
		//logger.info(mapper.writeValueAsString(topicListContainer));
		logger.info("[JSON-String payload status=OK]  " + container.getUser());
		//DEBUG
		Collection<JsonSkatNctsImportSpecificTopicLoggingRecord> list = container.getLogg();
		for(JsonSkatNctsImportSpecificTopicLoggingRecord record : list){
			//logger.info("Message nr (topic logging): " + record.getMmn());
		}
		return container;
	}
}
