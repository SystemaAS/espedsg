/**
 * 
 */
package no.systema.tds.nctsimport.mapper.jsonjackson;

//jackson library
import org.apache.log4j.Logger;
import org.codehaus.jackson.map.DeserializationConfig;
import org.codehaus.jackson.map.ObjectMapper;
//application library
import no.systema.tds.nctsimport.model.jsonjackson.topic.unloading.JsonNctsImportSpecificTopicUnloadingContainer;
import no.systema.tds.nctsimport.model.jsonjackson.topic.unloading.JsonNctsImportSpecificTopicUnloadingRecord;


//
import java.util.*;

/**
 * 
 * @author oscardelatorre
 * @date Dec 19, 2013
 * 
 */
public class NctsImportSpecificTopicUnloadingMapper {
	private static final Logger logger = Logger.getLogger(NctsImportSpecificTopicUnloadingMapper.class.getName());
	
	public JsonNctsImportSpecificTopicUnloadingContainer getContainer(String utfPayload) throws Exception{
		ObjectMapper mapper = new ObjectMapper();  
		mapper.configure(DeserializationConfig.Feature.FAIL_ON_UNKNOWN_PROPERTIES,false);
		
		//At this point we now have an UTF-8 payload
		JsonNctsImportSpecificTopicUnloadingContainer container = mapper.readValue(utfPayload.getBytes(), JsonNctsImportSpecificTopicUnloadingContainer.class); 
		//logger.info(mapper.writeValueAsString(topicListContainer));
		logger.info("[JSON-String payload status=OK]  " + container.getUser());
		
		//DEBUG
		
		Collection<JsonNctsImportSpecificTopicUnloadingRecord> fields = container.getOneorder();
		for(JsonNctsImportSpecificTopicUnloadingRecord record : fields){
			
			/*logger.info("Bruttovikt: " + record.getTivkb());*/
		}
			
		return container;
	}
}
