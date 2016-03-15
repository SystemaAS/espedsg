/**
 * 
 */
package no.systema.tds.nctsexport.mapper.jsonjackson;

//jackson library
import org.apache.log4j.Logger;
import org.codehaus.jackson.map.DeserializationConfig;
import org.codehaus.jackson.map.ObjectMapper;
//application library
import no.systema.tds.nctsexport.model.jsonjackson.topic.logging.JsonNctsExportSpecificTopicLoggingContainer;
import no.systema.tds.nctsexport.model.jsonjackson.topic.logging.JsonNctsExportSpecificTopicLoggingRecord;
//
import java.util.*;

/**
 * @author oscardelatorre
 * @date Aug 7, 2013
 * 
 */
public class NctsExportSpecificTopicLoggingMapper {
	private static final Logger logger = Logger.getLogger(NctsExportSpecificTopicLoggingMapper.class.getName());
	
	public JsonNctsExportSpecificTopicLoggingContainer getContainer(String utfPayload) throws Exception{
		ObjectMapper mapper = new ObjectMapper();  
		mapper.configure(DeserializationConfig.Feature.FAIL_ON_UNKNOWN_PROPERTIES,false);
		
		//At this point we now have an UTF-8 payload
		JsonNctsExportSpecificTopicLoggingContainer container = mapper.readValue(utfPayload.getBytes(), JsonNctsExportSpecificTopicLoggingContainer.class); 
		//logger.info(mapper.writeValueAsString(topicListContainer));
		logger.info("[JSON-String payload status=OK]  " + container.getUser());
		//DEBUG
		Collection<JsonNctsExportSpecificTopicLoggingRecord> list = container.getLogg();
		for(JsonNctsExportSpecificTopicLoggingRecord record : list){
			logger.info("Message nr (topic logging): " + record.getMmn());
		}
		return container;
	}
}
