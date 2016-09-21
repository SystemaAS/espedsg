/**
 * 
 */
package no.systema.tvinn.sad.admin.mapper.jsonjackson;

//jackson library
import org.apache.log4j.Logger;
import org.codehaus.jackson.map.DeserializationConfig;
import org.codehaus.jackson.map.ObjectMapper;
//application library
import no.systema.tvinn.sad.admin.model.jsonjackson.topic.JsonSadAdminAvggrunnlagListContainer;

//
import java.util.*;

/**
 * 
 * @author oscardelatorre
 * @date Sep 21, 2014
 * 
 */
public class SadAdminAvggrunnlagListMapper {
	private static final Logger logger = Logger.getLogger(SadAdminAvggrunnlagListMapper.class.getName());
	
	public JsonSadAdminAvggrunnlagListContainer getContainer(String utfPayload) throws Exception{
		ObjectMapper mapper = new ObjectMapper();  
		mapper.configure(DeserializationConfig.Feature.FAIL_ON_UNKNOWN_PROPERTIES,false);
		//At this point we now have an UTF-8 payload
		JsonSadAdminAvggrunnlagListContainer topicListContainer = mapper.readValue(utfPayload.getBytes(), JsonSadAdminAvggrunnlagListContainer.class); 
		//logger.info(mapper.writeValueAsString(topicListContainer));
		logger.info("[JSON-String payload status=OK]  " + topicListContainer.getUser());
		
		
		return topicListContainer;
	}
}
