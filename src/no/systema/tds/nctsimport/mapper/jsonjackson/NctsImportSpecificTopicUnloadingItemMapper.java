/**
 * 
 */
package no.systema.tds.nctsimport.mapper.jsonjackson;

//jackson library
import org.apache.log4j.Logger;
import org.codehaus.jackson.map.DeserializationConfig;
import org.codehaus.jackson.map.ObjectMapper;
//application library
import no.systema.tds.nctsimport.model.jsonjackson.topic.unloading.items.JsonNctsImportSpecificTopicUnloadingItemContainer;
import no.systema.tds.nctsimport.model.jsonjackson.topic.unloading.items.JsonNctsImportSpecificTopicUnloadingItemRecord;


//
import java.util.*;

/**
 * 
 * @author oscardelatorre
 * @date Dec 19, 2013
 * 
 */
public class NctsImportSpecificTopicUnloadingItemMapper {
	private static final Logger logger = Logger.getLogger(NctsImportSpecificTopicUnloadingItemMapper.class.getName());
	
	public JsonNctsImportSpecificTopicUnloadingItemContainer getContainer(String utfPayload) throws Exception{
		ObjectMapper mapper = new ObjectMapper();  
		mapper.configure(DeserializationConfig.Feature.FAIL_ON_UNKNOWN_PROPERTIES,false);
		
		//At this point we now have an UTF-8 payload
		JsonNctsImportSpecificTopicUnloadingItemContainer container = mapper.readValue(utfPayload.getBytes(), JsonNctsImportSpecificTopicUnloadingItemContainer.class); 
		//logger.info(mapper.writeValueAsString(topicListContainer));
		logger.info("[JSON-String payload status=OK]  " + container.getUser());
		
		//DEBUG
		
		Collection<JsonNctsImportSpecificTopicUnloadingItemRecord> fields = container.getOrderList();
		for(JsonNctsImportSpecificTopicUnloadingItemRecord record : fields){
			
			/*logger.info("Bruttovikt: " + record.getTivkb());*/
		}
			
		return container;
	}
}
