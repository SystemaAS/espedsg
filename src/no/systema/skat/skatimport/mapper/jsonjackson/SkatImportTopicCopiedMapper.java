/**
 * 
 */
package no.systema.skat.skatimport.mapper.jsonjackson;

//jackson library
import org.apache.log4j.Logger;

import com.fasterxml.jackson.databind.DeserializationConfig;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;

//application library
import no.systema.skat.skatimport.model.jsonjackson.topic.JsonSkatImportTopicCopiedContainer;

/**
 * 
 * @author oscardelatorre
 * @date Feb 21, 2014
 * 
 */
public class SkatImportTopicCopiedMapper {
	private static final Logger logger = Logger.getLogger(SkatImportTopicCopiedMapper.class.getName());
	
	public JsonSkatImportTopicCopiedContainer getContainer(String utfPayload) throws Exception{
		ObjectMapper mapper = new ObjectMapper();  
		mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES,false);
		//At this point we now have an UTF-8 payload
		JsonSkatImportTopicCopiedContainer topicListContainer = mapper.readValue(utfPayload.getBytes(), JsonSkatImportTopicCopiedContainer.class); 
		//logger.info(mapper.writeValueAsString(topicListContainer));
		logger.info("[JSON-String payload status=OK]  " + topicListContainer.getUser());
		
		return topicListContainer;
	}
}
