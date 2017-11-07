/**
 * 
 */
package no.systema.skat.skatimport.mapper.jsonjackson;

//
import java.util.Collection;

//jackson library
import org.apache.log4j.Logger;

import com.fasterxml.jackson.databind.DeserializationConfig;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;

import no.systema.skat.skatimport.model.jsonjackson.topic.JsonSkatImportTopicListContainer;
import no.systema.skat.skatimport.model.jsonjackson.topic.JsonSkatImportTopicListExternalRefContainer;
import no.systema.skat.skatimport.model.jsonjackson.topic.JsonSkatImportTopicListRecord;

/**
 * 
 * @author oscardelatorre
 * @date Jan 24, 2014
 * 
 * 
 */
public class SkatImportTopicListMapper {
	private static final Logger logger = Logger.getLogger(SkatImportTopicListMapper.class.getName());
	
	public JsonSkatImportTopicListContainer getContainer(String utfPayload) throws Exception{
		ObjectMapper mapper = new ObjectMapper();  
		mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES,false);
		//At this point we now have an UTF-8 payload
		JsonSkatImportTopicListContainer topicListContainer = mapper.readValue(utfPayload.getBytes(), JsonSkatImportTopicListContainer.class); 
		//logger.info(mapper.writeValueAsString(topicListContainer));
		logger.info("[JSON-String payload status=OK]  " + topicListContainer.getUser());
		
		//DEBUG 
		
		Collection<JsonSkatImportTopicListRecord> list = topicListContainer.getOrderList();
		for(JsonSkatImportTopicListRecord record : list){
			//logger.info("Avs: " + record.getAvsNavn());
			
		}
		return topicListContainer;
	}
	
	/**
	 * 
	 * @param utfPayload
	 * @return
	 * @throws Exception
	 */
	public JsonSkatImportTopicListExternalRefContainer getContainerExternalRef(String utfPayload) throws Exception{
		ObjectMapper mapper = new ObjectMapper();  
		mapper.configure(DeserializationConfig.Feature.FAIL_ON_UNKNOWN_PROPERTIES,false);
		//At this point we now have an UTF-8 payload
		JsonSkatImportTopicListExternalRefContainer container = mapper.readValue(utfPayload.getBytes(), JsonSkatImportTopicListExternalRefContainer.class); 
		//logger.info(mapper.writeValueAsString(topicListContainer));
		logger.info("[JSON-String payload status=OK]  " + container.getUser());
		
		return container;
	}
}
