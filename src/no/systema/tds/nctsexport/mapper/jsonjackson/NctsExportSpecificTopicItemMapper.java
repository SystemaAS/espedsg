/**
 * 
 */
package no.systema.tds.nctsexport.mapper.jsonjackson;

//jackson library
import org.apache.log4j.Logger;
import org.codehaus.jackson.map.DeserializationConfig;
import org.codehaus.jackson.map.ObjectMapper;
//application library
import no.systema.tds.nctsexport.model.jsonjackson.topic.items.JsonNctsExportSpecificTopicItemContainer;
import no.systema.tds.nctsexport.model.jsonjackson.topic.items.JsonNctsExportSpecificTopicItemRecord;
//
import java.util.*;

/**
 * @author oscardelatorre
 * 
 */
public class NctsExportSpecificTopicItemMapper {
	private static final Logger logger = Logger.getLogger(NctsExportSpecificTopicItemMapper.class.getName());
	
	public JsonNctsExportSpecificTopicItemContainer getContainer(String utfPayload) throws Exception{
		ObjectMapper mapper = new ObjectMapper();  
		mapper.configure(DeserializationConfig.Feature.FAIL_ON_UNKNOWN_PROPERTIES,false);
		
		//At this point we now have an UTF-8 payload
		JsonNctsExportSpecificTopicItemContainer topicItemContainer = mapper.readValue(utfPayload.getBytes(), JsonNctsExportSpecificTopicItemContainer.class); 
		//logger.info(mapper.writeValueAsString(topicListContainer));
		logger.info("[JSON-String payload status=OK]  " + topicItemContainer.getUser());
		//DEBUG
		Collection<JsonNctsExportSpecificTopicItemRecord> list = topicItemContainer.getOrderList();
		for(JsonNctsExportSpecificTopicItemRecord record : list){
			logger.info("Item description: " + record.getTvvt());
			logger.info("Sender name: " + record.getTvnas());
			logger.info("Receiver name: " + record.getTvnak());
			
			
		}
		return topicItemContainer;
	}
}
