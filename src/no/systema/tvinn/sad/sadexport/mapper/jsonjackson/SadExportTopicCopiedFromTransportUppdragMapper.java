/**
 * 
 */
package no.systema.tvinn.sad.sadexport.mapper.jsonjackson;

//jackson library
import org.apache.log4j.Logger;
import org.codehaus.jackson.map.DeserializationConfig;
import org.codehaus.jackson.map.ObjectMapper;
//application library
import no.systema.tvinn.sad.sadexport.model.jsonjackson.topic.JsonSadExportTopicCopiedFromTransportUppdragContainer;

/**
 * 
 * @author oscardelatorre
 * @date Maj 2, 2014
 * 
 */
public class SadExportTopicCopiedFromTransportUppdragMapper {
	private static final Logger logger = Logger.getLogger(SadExportTopicCopiedFromTransportUppdragMapper.class.getName());
	
	public JsonSadExportTopicCopiedFromTransportUppdragContainer getContainer(String utfPayload) throws Exception{
		ObjectMapper mapper = new ObjectMapper();  
		mapper.configure(DeserializationConfig.Feature.FAIL_ON_UNKNOWN_PROPERTIES,false);
		//At this point we now have an UTF-8 payload
		JsonSadExportTopicCopiedFromTransportUppdragContainer topicListContainer = mapper.readValue(utfPayload.getBytes(), JsonSadExportTopicCopiedFromTransportUppdragContainer.class); 
		//logger.info(mapper.writeValueAsString(topicListContainer));
		logger.info("[JSON-String payload status=OK]  " + topicListContainer.getUser());
		
		
		return topicListContainer;
	}
}
