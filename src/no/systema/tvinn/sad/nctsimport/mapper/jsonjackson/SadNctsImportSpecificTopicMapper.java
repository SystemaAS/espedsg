/**
 * 
 */
package no.systema.tvinn.sad.nctsimport.mapper.jsonjackson;

//jackson library
import org.apache.log4j.Logger;
import org.codehaus.jackson.map.DeserializationConfig;
import org.codehaus.jackson.map.ObjectMapper;
//application library
import no.systema.tvinn.sad.nctsimport.model.jsonjackson.topic.JsonSadNctsImportSpecificTopicContainer;
import no.systema.tvinn.sad.nctsimport.model.jsonjackson.topic.JsonSadNctsImportSpecificTopicRecord;


//
import java.util.*;

/**
 * 
 * @author oscardelatorre
 * @date Mar 6, 2015
 * 
 * 
 */
public class SadNctsImportSpecificTopicMapper {
	private static final Logger logger = Logger.getLogger(SadNctsImportSpecificTopicMapper.class.getName());
	
	public JsonSadNctsImportSpecificTopicContainer getContainer(String utfPayload) throws Exception{
		ObjectMapper mapper = new ObjectMapper();  
		mapper.configure(DeserializationConfig.Feature.FAIL_ON_UNKNOWN_PROPERTIES,false);
		
		//At this point we now have an UTF-8 payload
		JsonSadNctsImportSpecificTopicContainer topicListContainer = mapper.readValue(utfPayload.getBytes(), JsonSadNctsImportSpecificTopicContainer.class); 
		//logger.info(mapper.writeValueAsString(topicListContainer));
		logger.info("[JSON-String payload status=OK]  " + topicListContainer.getUser());
		
		//DEBUG
		
		Collection<JsonSadNctsImportSpecificTopicRecord> fields = topicListContainer.getOneorder();
		for(JsonSadNctsImportSpecificTopicRecord record : fields){
			
		}
			
		return topicListContainer;
	}
}
