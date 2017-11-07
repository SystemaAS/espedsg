/**
 * 
 */
package no.systema.skat.nctsimport.mapper.jsonjackson;

//jackson library
import org.apache.log4j.Logger;

import com.fasterxml.jackson.databind.DeserializationConfig;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;

import no.systema.skat.nctsimport.model.jsonjackson.topic.JsonSkatNctsImportTopicListContainer;

/**
 * 
 * @author oscardelatorre
 * @date Apr 23, 2014
 * 
 * 
 */
public class SkatNctsImportTopicListMapper {
	private static final Logger logger = Logger.getLogger(SkatNctsImportTopicListMapper.class.getName());
	
	public JsonSkatNctsImportTopicListContainer getContainer(String utfPayload) throws Exception{
		ObjectMapper mapper = new ObjectMapper();  
		mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES,false);
		//At this point we now have an UTF-8 payload
		JsonSkatNctsImportTopicListContainer topicListContainer = mapper.readValue(utfPayload.getBytes(), JsonSkatNctsImportTopicListContainer.class); 
		//logger.info(mapper.writeValueAsString(topicListContainer));
		logger.info("[JSON-String payload status=OK]  " + topicListContainer.getUser());
		
		//DEBUG 
		/*
		Collection<JsonTdsExportTopicListRecord> list = topicListContainer.getOrderList();
		for(JsonTdsExportTopicListRecord record : list){
			logger.info("TullId: " + record.getTullId());
			logger.info("Avs.: " + record.getAvsNavn());
			
		}*/
		
		
		return topicListContainer;
	}
}
