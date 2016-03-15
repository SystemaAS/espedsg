/**
 * 
 */
package no.systema.tds.mapper.jsonjackson.authorization.topic;

//jackson library
import org.apache.log4j.Logger;
import org.codehaus.jackson.map.DeserializationConfig;
import org.codehaus.jackson.map.ObjectMapper;
//application library
import no.systema.tds.model.jsonjackson.authorization.topic.JsonTdsAuthorizationTopicListContainer;
import no.systema.tds.model.jsonjackson.authorization.topic.JsonTdsAuthorizationTopicListRecord;

import java.util.*;

/**
 * @author oscardelatorre
 * 
 */
public class TdsAuthorizationTopicListMapper {
	private static final Logger logger = Logger.getLogger(TdsAuthorizationTopicListMapper.class.getName());
	
	public JsonTdsAuthorizationTopicListContainer getContainer(String utfPayload) throws Exception{
		ObjectMapper mapper = new ObjectMapper();  
		mapper.configure(DeserializationConfig.Feature.FAIL_ON_UNKNOWN_PROPERTIES,false);
		JsonTdsAuthorizationTopicListContainer container = null;
		
		if(utfPayload!=null){
			//At this point we now have an UTF-8 payload
			container = mapper.readValue(utfPayload.getBytes(), JsonTdsAuthorizationTopicListContainer.class); 
			//logger.info(mapper.writeValueAsString(topicListContainer));
			logger.info("Mapping object from JSON payload...");
			logger.info("[JSON-String payload status=OK]  " + container.getUser());
			
			//DEBUG
			Collection<JsonTdsAuthorizationTopicListRecord> fields = container.getSigneringslista();
			for(JsonTdsAuthorizationTopicListRecord record : fields){
				logger.info("Syav: " + record.getSyav());
				
			}
		}	
		return container;
	}
	
	

}
