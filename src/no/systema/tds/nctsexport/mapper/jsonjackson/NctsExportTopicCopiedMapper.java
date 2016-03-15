/**
 * 
 */
package no.systema.tds.nctsexport.mapper.jsonjackson;

//jackson library
import org.apache.log4j.Logger;
import org.codehaus.jackson.map.DeserializationConfig;
import org.codehaus.jackson.map.ObjectMapper;
//application library
import no.systema.tds.nctsexport.model.jsonjackson.topic.JsonNctsExportTopicCopiedContainer;

/**
 * @author oscardelatorre
 * 
 */
public class NctsExportTopicCopiedMapper {
	private static final Logger logger = Logger.getLogger(NctsExportTopicCopiedMapper.class.getName());
	
	public JsonNctsExportTopicCopiedContainer getContainer(String utfPayload) throws Exception{
		ObjectMapper mapper = new ObjectMapper();  
		mapper.configure(DeserializationConfig.Feature.FAIL_ON_UNKNOWN_PROPERTIES,false);
		//At this point we now have an UTF-8 payload
		JsonNctsExportTopicCopiedContainer topicListContainer = mapper.readValue(utfPayload.getBytes(), JsonNctsExportTopicCopiedContainer.class); 
		//logger.info(mapper.writeValueAsString(topicListContainer));
		logger.info("[JSON-String payload status=OK]  " + topicListContainer.getUser());
		
		
		return topicListContainer;
	}
}
