/**
 * 
 */
package no.systema.tds.mapper.jsonjackson.authorization.topic;

//jackson library
import org.apache.log4j.Logger;
import org.codehaus.jackson.map.DeserializationConfig;
import org.codehaus.jackson.map.ObjectMapper;
//application library
import no.systema.tds.model.jsonjackson.authorization.JsonTdsAuthorizationContainer;
import no.systema.tds.model.jsonjackson.authorization.topic.JsonTdsAuthorizationSignSpecificTopicContainer;

import java.util.*;

/**
 * @author oscardelatorre
 * 
 */
public class TdsAuthorizationSpecificTopicToSignMapper {
	private static final Logger logger = Logger.getLogger(TdsAuthorizationSpecificTopicToSignMapper.class.getName());
	
	public JsonTdsAuthorizationSignSpecificTopicContainer getContainer(String utfPayload) throws Exception{
		ObjectMapper mapper = new ObjectMapper();  
		mapper.configure(DeserializationConfig.Feature.FAIL_ON_UNKNOWN_PROPERTIES,false);
		JsonTdsAuthorizationSignSpecificTopicContainer container = null;
		
		if(utfPayload!=null){
			//At this point we now have an UTF-8 payload
			container = mapper.readValue(utfPayload.getBytes(), JsonTdsAuthorizationSignSpecificTopicContainer.class); 
			//logger.info(mapper.writeValueAsString(topicListContainer));
			logger.info("Mapping object from JSON payload...");
			logger.info("[JSON-String payload status=OK]  " + container.getUser());
			
			//DEBUG - No Record type
			/*Collection<JsonTdsAuthorizationSignSpecificTopicRecord> fields = container.getSigneringslista();
			for(JsonTdsAuthorizationTopicListRecord record : fields){
				logger.info("Syav: " + record.getSyav());
				
			}*/
		}
			
		return container;
	}
	
	

}
