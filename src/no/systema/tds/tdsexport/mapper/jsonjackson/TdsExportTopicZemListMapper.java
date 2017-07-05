/**
 * 
 */
package no.systema.tds.tdsexport.mapper.jsonjackson;

//jackson library
import org.apache.log4j.Logger;
import org.codehaus.jackson.map.DeserializationConfig;
import org.codehaus.jackson.map.ObjectMapper;
//application library
import no.systema.main.controller.LoginController;
import no.systema.tds.tdsexport.model.jsonjackson.topic.JsonTdsExportTopicZemListContainer;
//
import java.util.*;

/**
 * @author oscardelatorre
 * 
 */
public class TdsExportTopicZemListMapper {
	private static final Logger logger = Logger.getLogger(TdsExportTopicZemListMapper.class.getName());
	
	public JsonTdsExportTopicZemListContainer getContainer(String utfPayload) throws Exception{
		ObjectMapper mapper = new ObjectMapper();  
		mapper.configure(DeserializationConfig.Feature.FAIL_ON_UNKNOWN_PROPERTIES,false);
		//At this point we now have an UTF-8 payload
		JsonTdsExportTopicZemListContainer topicListContainer = mapper.readValue(utfPayload.getBytes(), JsonTdsExportTopicZemListContainer.class); 
		//logger.info(mapper.writeValueAsString(topicListContainer));
		//logger.info("[JSON-String payload status=OK]  " + topicListContainer.getUser());
		
		return topicListContainer;
	}
}
