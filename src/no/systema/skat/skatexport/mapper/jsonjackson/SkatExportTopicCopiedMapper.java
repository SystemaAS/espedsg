/**
 * 
 */
package no.systema.skat.skatexport.mapper.jsonjackson;

//jackson library
import org.apache.log4j.Logger;
import org.codehaus.jackson.map.DeserializationConfig;
import org.codehaus.jackson.map.ObjectMapper;
//application library
import no.systema.skat.skatexport.model.jsonjackson.topic.JsonSkatExportTopicCopiedContainer;

/**
 * 
 * @author oscardelatorre
 * @date Mar 12, 2014
 * 
 */
public class SkatExportTopicCopiedMapper {
	private static final Logger logger = Logger.getLogger(SkatExportTopicCopiedMapper.class.getName());
	
	public JsonSkatExportTopicCopiedContainer getContainer(String utfPayload) throws Exception{
		ObjectMapper mapper = new ObjectMapper();  
		mapper.configure(DeserializationConfig.Feature.FAIL_ON_UNKNOWN_PROPERTIES,false);
		//At this point we now have an UTF-8 payload
		JsonSkatExportTopicCopiedContainer topicListContainer = mapper.readValue(utfPayload.getBytes(), JsonSkatExportTopicCopiedContainer.class); 
		//logger.info(mapper.writeValueAsString(topicListContainer));
		logger.info("[JSON-String payload status=OK]  " + topicListContainer.getUser());
		
		return topicListContainer;
	}
}
