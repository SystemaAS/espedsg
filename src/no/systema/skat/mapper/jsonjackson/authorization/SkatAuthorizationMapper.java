/**
 * 
 */
package no.systema.skat.mapper.jsonjackson.authorization;

//jackson library
import org.apache.log4j.Logger;
import org.codehaus.jackson.map.DeserializationConfig;
import org.codehaus.jackson.map.ObjectMapper;
//application library
import no.systema.skat.model.jsonjackson.authorization.JsonSkatAuthorizationContainer;
import no.systema.skat.model.jsonjackson.authorization.JsonSkatAuthorizationRecord;

import java.util.*;

/**
 * 
 * @author oscardelatorre
 * @date Mar 4, 2014
 * 
 * 
 */
public class SkatAuthorizationMapper {
	private static final Logger logger = Logger.getLogger(SkatAuthorizationMapper.class.getName());
	
	public JsonSkatAuthorizationContainer getContainer(String utfPayload) throws Exception{
		ObjectMapper mapper = new ObjectMapper();  
		mapper.configure(DeserializationConfig.Feature.FAIL_ON_UNKNOWN_PROPERTIES,false);
		JsonSkatAuthorizationContainer container = null;
		
		if(utfPayload!=null){
			//At this point we now have an UTF-8 payload
			container = mapper.readValue(utfPayload.getBytes(), JsonSkatAuthorizationContainer.class); 
			//logger.info(mapper.writeValueAsString(topicListContainer));
			logger.info("Mapping object from JSON payload...");
			logger.info("[JSON-String payload status=OK]  " + container.getUser());
			
			//DEBUG
			Collection<JsonSkatAuthorizationRecord> fields = container.getSkatBrugertilladelse();
			for(JsonSkatAuthorizationRecord record : fields){
				logger.info("ok: " + record.getOk());
				logger.info("sign: " + record.getSign());
				
			}
		}
		return container;
	}
	
}
