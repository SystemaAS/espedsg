/**
 * 
 */
package no.systema.tds.nctsimport.mapper.jsonjackson;

//jackson library
import org.apache.log4j.Logger;
import org.codehaus.jackson.map.DeserializationConfig;
import org.codehaus.jackson.map.ObjectMapper;
//application library
import no.systema.tds.nctsimport.model.jsonjackson.topic.items.JsonNctsImportSpecificTopicItemContainer;
import no.systema.tds.nctsimport.model.jsonjackson.topic.items.JsonNctsImportSpecificTopicItemRecord;
//
import java.util.*;

/**
 * @author oscardelatorre
 * @date Dec 16, 2013
 * 
 */
public class NctsImportSpecificTopicItemMapper {
	private static final Logger logger = Logger.getLogger(NctsImportSpecificTopicItemMapper.class.getName());
	
	public JsonNctsImportSpecificTopicItemContainer getContainer(String utfPayload) throws Exception{
		ObjectMapper mapper = new ObjectMapper();  
		mapper.configure(DeserializationConfig.Feature.FAIL_ON_UNKNOWN_PROPERTIES,false);
		
		//At this point we now have an UTF-8 payload
		JsonNctsImportSpecificTopicItemContainer topicItemContainer = mapper.readValue(utfPayload.getBytes(), JsonNctsImportSpecificTopicItemContainer.class); 
		//logger.info(mapper.writeValueAsString(topicListContainer));
		logger.info("[JSON-String payload status=OK]  " + topicItemContainer.getUser());
		//DEBUG
		Collection<JsonNctsImportSpecificTopicItemRecord> list = topicItemContainer.getOrderList();
		for(JsonNctsImportSpecificTopicItemRecord record : list){
			logger.info("Item description (event desc): " + record.getTvinf1());
			logger.info("Place name: " + record.getTvst());
			logger.info("Transport Id: " + record.getTvtaid());
			
			
		}
		return topicItemContainer;
	}
}
