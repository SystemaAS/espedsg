/**
 * 
 */
package no.systema.skat.nctsexport.mapper.jsonjackson;

//jackson library
import org.apache.log4j.Logger;
import org.codehaus.jackson.map.DeserializationConfig;
import org.codehaus.jackson.map.ObjectMapper;
//application library
import no.systema.skat.nctsexport.model.jsonjackson.topic.JsonSkatNctsExportTopicCopiedContainer;

/**
 * 
 * @author oscardelatorre
 * @date Apr 14, 2014
 * 
 */
public class SkatNctsExportTopicCopiedMapper {
	private static final Logger logger = Logger.getLogger(SkatNctsExportTopicCopiedMapper.class.getName());
	
	public JsonSkatNctsExportTopicCopiedContainer getContainer(String utfPayload) throws Exception{
		ObjectMapper mapper = new ObjectMapper();  
		mapper.configure(DeserializationConfig.Feature.FAIL_ON_UNKNOWN_PROPERTIES,false);
		//At this point we now have an UTF-8 payload
		JsonSkatNctsExportTopicCopiedContainer topicListContainer = mapper.readValue(utfPayload.getBytes(), JsonSkatNctsExportTopicCopiedContainer.class); 
		//logger.info(mapper.writeValueAsString(topicListContainer));
		logger.info("[JSON-String payload status=OK]  " + topicListContainer.getUser());
		
		return topicListContainer;
		
	}
}
