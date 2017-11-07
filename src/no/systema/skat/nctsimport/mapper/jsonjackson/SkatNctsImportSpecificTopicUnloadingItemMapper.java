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
import no.systema.skat.nctsimport.model.jsonjackson.topic.unloading.items.JsonSkatNctsImportSpecificTopicUnloadingItemContainer;
import no.systema.skat.nctsimport.model.jsonjackson.topic.unloading.items.JsonSkatNctsImportSpecificTopicUnloadingItemRecord;

/**
 * 
 * @author oscardelatorre
 * @date Apr 24, 2014
 * 
 */
public class SkatNctsImportSpecificTopicUnloadingItemMapper {
	private static final Logger logger = Logger.getLogger(SkatNctsImportSpecificTopicUnloadingItemMapper.class.getName());
	
	public JsonSkatNctsImportSpecificTopicUnloadingItemContainer getContainer(String utfPayload) throws Exception{
		ObjectMapper mapper = new ObjectMapper();  
		mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES,false);
		
		//At this point we now have an UTF-8 payload
		JsonSkatNctsImportSpecificTopicUnloadingItemContainer container = mapper.readValue(utfPayload.getBytes(), JsonSkatNctsImportSpecificTopicUnloadingItemContainer.class); 
		//logger.info(mapper.writeValueAsString(topicListContainer));
		logger.info("[JSON-String payload status=OK]  " + container.getUser());
		
		Collection<JsonSkatNctsImportSpecificTopicUnloadingItemRecord> fields = container.getOrderList();
		for(JsonSkatNctsImportSpecificTopicUnloadingItemRecord record : fields){
			/*logger.info("Bruttovikt: " + record.getTivkb());*/
		}
			
		return container;
	}
}
