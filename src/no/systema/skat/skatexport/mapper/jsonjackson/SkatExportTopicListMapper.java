/**
 * 
 */
package no.systema.skat.skatexport.mapper.jsonjackson;

//jackson library
import org.apache.log4j.Logger;

import com.fasterxml.jackson.databind.DeserializationConfig;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;

import no.systema.skat.skatexport.model.jsonjackson.topic.JsonSkatExportTopicListContainer;
import no.systema.skat.skatexport.model.jsonjackson.topic.JsonSkatExportTopicListExternalRefContainer;

/**
 * 
 * @author oscardelatorre
 * @date Feb 26, 2014
 * 
 * 
 */
public class SkatExportTopicListMapper {
	private static final Logger logger = Logger.getLogger(SkatExportTopicListMapper.class.getName());
	
	public JsonSkatExportTopicListContainer getContainer(String utfPayload) throws Exception{
		ObjectMapper mapper = new ObjectMapper();  
		mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES,false);
		//At this point we now have an UTF-8 payload
		JsonSkatExportTopicListContainer container = mapper.readValue(utfPayload.getBytes(), JsonSkatExportTopicListContainer.class); 
		//logger.info(mapper.writeValueAsString(topicListContainer));
		logger.info("[JSON-String payload status=OK]  " + container.getUser());
		
		return container;
	}
	/**
	 * 
	 * @param utfPayload
	 * @return
	 * @throws Exception
	 */
	public JsonSkatExportTopicListExternalRefContainer getContainerExternalRef(String utfPayload) throws Exception{
		ObjectMapper mapper = new ObjectMapper();  
		mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES,false);
		//At this point we now have an UTF-8 payload
		JsonSkatExportTopicListExternalRefContainer container = mapper.readValue(utfPayload.getBytes(), JsonSkatExportTopicListExternalRefContainer.class); 
		//logger.info(mapper.writeValueAsString(topicListContainer));
		logger.info("[JSON-String payload status=OK]  " + container.getUser());
		
		return container;
	}
}
