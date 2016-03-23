/**
 * 
 */
package no.systema.skat.nctsexport.mapper.jsonjackson;

//jackson library
import org.apache.log4j.Logger;
import org.codehaus.jackson.map.DeserializationConfig;
import org.codehaus.jackson.map.ObjectMapper;
//application library
import no.systema.skat.nctsexport.model.jsonjackson.topic.items.JsonSkatNctsExportSpecificTopicItemContainer;
import no.systema.skat.nctsexport.model.jsonjackson.topic.items.JsonSkatNctsExportSpecificTopicItemRecord;
import no.systema.skat.nctsexport.model.jsonjackson.topic.items.JsonSkatNctsExportSpecificTopicItemSecurityContainer;
import no.systema.skat.nctsexport.model.jsonjackson.topic.items.JsonSkatNctsExportSpecificTopicItemSecurityRecord;
//
import java.util.*;

/**
 * @author oscardelatorre
 * @date Apr 14, 2014
 * 
 */
public class SkatNctsExportSpecificTopicItemMapper {
	private static final Logger logger = Logger.getLogger(SkatNctsExportSpecificTopicItemMapper.class.getName());
	
	public JsonSkatNctsExportSpecificTopicItemContainer getContainer(String utfPayload) throws Exception{
		ObjectMapper mapper = new ObjectMapper();  
		mapper.configure(DeserializationConfig.Feature.FAIL_ON_UNKNOWN_PROPERTIES,false);
		
		//At this point we now have an UTF-8 payload
		JsonSkatNctsExportSpecificTopicItemContainer topicItemContainer = mapper.readValue(utfPayload.getBytes(), JsonSkatNctsExportSpecificTopicItemContainer.class); 
		//logger.info(mapper.writeValueAsString(topicListContainer));
		logger.info("[JSON-String payload status=OK]  " + topicItemContainer.getUser());
		//DEBUG
		Collection<JsonSkatNctsExportSpecificTopicItemRecord> list = topicItemContainer.getOrderList();
		for(JsonSkatNctsExportSpecificTopicItemRecord record : list){
			/*logger.info("Item description: " + record.getTvvt());
			logger.info("Sender name: " + record.getTvnas());
			logger.info("Receiver name: " + record.getTvnak());
			logger.info("Tvdref: " + record.getTvdref());
			*/
			
		}
		return topicItemContainer;
	}
	
	/**
	 * 
	 * @param utfPayload
	 * @return
	 * @throws Exception
	 */
	public JsonSkatNctsExportSpecificTopicItemSecurityContainer getSecurityContainer(String utfPayload) throws Exception{
		ObjectMapper mapper = new ObjectMapper();  
		mapper.configure(DeserializationConfig.Feature.FAIL_ON_UNKNOWN_PROPERTIES,false);
		
		//At this point we now have an UTF-8 payload
		JsonSkatNctsExportSpecificTopicItemSecurityContainer container = mapper.readValue(utfPayload.getBytes(), JsonSkatNctsExportSpecificTopicItemSecurityContainer.class); 
		//logger.info(mapper.writeValueAsString(topicListContainer));
		logger.info("[JSON-String payload status=OK]  " + container.getUser());
		//DEBUG
		Collection<JsonSkatNctsExportSpecificTopicItemSecurityRecord> list = container.getSecurityline();
		for(JsonSkatNctsExportSpecificTopicItemSecurityRecord record : list){
			//DEBUG logger.info("Tvtinks (security): " + record.getTvtinks());
		}
		return container;
	}
}
