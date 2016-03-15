/**
 * 
 */
package no.systema.tds.tdsimport.mapper.jsonjackson;

//jackson library
import org.apache.log4j.Logger;
import org.codehaus.jackson.map.DeserializationConfig;
import org.codehaus.jackson.map.ObjectMapper;
//application library
import no.systema.tds.tdsimport.model.jsonjackson.topic.JsonTdsImportTopicCopiedContainer;

/**
 * @author oscardelatorre
 * 
 */
public class TdsImportTopicCopiedMapper {
	private static final Logger logger = Logger.getLogger(TdsImportTopicCopiedMapper.class.getName());
	
	public JsonTdsImportTopicCopiedContainer getContainer(String utfPayload) throws Exception{
		ObjectMapper mapper = new ObjectMapper();  
		mapper.configure(DeserializationConfig.Feature.FAIL_ON_UNKNOWN_PROPERTIES,false);
		//At this point we now have an UTF-8 payload
		JsonTdsImportTopicCopiedContainer topicListContainer = mapper.readValue(utfPayload.getBytes(), JsonTdsImportTopicCopiedContainer.class); 
		//logger.info(mapper.writeValueAsString(topicListContainer));
		logger.info("[JSON-String payload status=OK]  " + topicListContainer.getUser());
		
		
		
		return topicListContainer;
	}
}
