/**
 * 
 */
package no.systema.tds.nctsimport.mapper.jsonjackson;

//jackson library
import org.apache.log4j.Logger;
import org.codehaus.jackson.map.DeserializationConfig;
import org.codehaus.jackson.map.ObjectMapper;
//application library
import no.systema.tds.nctsimport.model.jsonjackson.topic.logging.JsonNctsImportSpecificTopicLoggingContainer;
import no.systema.tds.nctsimport.model.jsonjackson.topic.logging.JsonNctsImportSpecificTopicLoggingRecord;
//
import java.util.*;

/**
 * @author oscardelatorre
 * @date Apr 4, 2014
 * 
 */
public class NctsImportSpecificTopicLoggingMapper {
	private static final Logger logger = Logger.getLogger(NctsImportSpecificTopicLoggingMapper.class.getName());
	
	public JsonNctsImportSpecificTopicLoggingContainer getContainer(String utfPayload) throws Exception{
		ObjectMapper mapper = new ObjectMapper();  
		mapper.configure(DeserializationConfig.Feature.FAIL_ON_UNKNOWN_PROPERTIES,false);
		
		//At this point we now have an UTF-8 payload
		JsonNctsImportSpecificTopicLoggingContainer container = mapper.readValue(utfPayload.getBytes(), JsonNctsImportSpecificTopicLoggingContainer.class); 
		//logger.info(mapper.writeValueAsString(topicListContainer));
		logger.info("[JSON-String payload status=OK]  " + container.getUser());
		//DEBUG
		Collection<JsonNctsImportSpecificTopicLoggingRecord> list = container.getLogg();
		for(JsonNctsImportSpecificTopicLoggingRecord record : list){
			logger.info("Message nr (topic logging): " + record.getMmn());
		}
		return container;
	}
}
