/**
 * 
 */
package no.systema.tds.tdsimport.mapper.jsonjackson;

//jackson library
import org.apache.log4j.Logger;
import org.codehaus.jackson.map.DeserializationConfig;
import org.codehaus.jackson.map.ObjectMapper;
//application library
import no.systema.main.controller.LoginController;
import no.systema.tds.tdsimport.model.jsonjackson.topic.JsonTdsImportTopicUtlamListContainer;
//
import java.util.*;

/**
 * @author oscardelatorre
 * 
 */
public class TdsImportTopicUtlamListMapper {
	private static final Logger logger = Logger.getLogger(TdsImportTopicUtlamListMapper.class.getName());
	
	public JsonTdsImportTopicUtlamListContainer getContainer(String utfPayload) throws Exception{
		ObjectMapper mapper = new ObjectMapper();  
		mapper.configure(DeserializationConfig.Feature.FAIL_ON_UNKNOWN_PROPERTIES,false);
		//At this point we now have an UTF-8 payload
		JsonTdsImportTopicUtlamListContainer topicListContainer = mapper.readValue(utfPayload.getBytes(), JsonTdsImportTopicUtlamListContainer.class); 
		//logger.info(mapper.writeValueAsString(topicListContainer));
		//logger.info("[JSON-String payload status=OK]  " + topicListContainer.getUser());
		
		return topicListContainer;
	}
}
