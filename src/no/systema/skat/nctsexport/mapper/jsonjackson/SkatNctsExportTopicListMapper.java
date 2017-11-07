/**
 * 
 */
package no.systema.skat.nctsexport.mapper.jsonjackson;

//jackson library
import org.apache.log4j.Logger;

import com.fasterxml.jackson.databind.DeserializationConfig;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;

//application library
import no.systema.skat.nctsexport.model.jsonjackson.topic.JsonSkatNctsExportTopicListContainer;

/**
 * 
 * @author oscardelatorre
 * @date Apr 14, 2014
 * 
 */
public class SkatNctsExportTopicListMapper {
	private static final Logger logger = Logger.getLogger(SkatNctsExportTopicListMapper.class.getName());
	
	public JsonSkatNctsExportTopicListContainer getContainer(String utfPayload) throws Exception{
		ObjectMapper mapper = new ObjectMapper();  
		mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES,false);
		//At this point we now have an UTF-8 payload
		JsonSkatNctsExportTopicListContainer topicListContainer = mapper.readValue(utfPayload.getBytes(), JsonSkatNctsExportTopicListContainer.class); 
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
