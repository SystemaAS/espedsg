/**
 * 
 */
package no.systema.skat.skatexport.mapper.jsonjackson;

//jackson library
import org.apache.log4j.Logger;

import com.fasterxml.jackson.databind.DeserializationConfig;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;

//application library
import no.systema.skat.skatexport.model.jsonjackson.topic.JsonSkatExportTopicCopiedFromTransportUppdragContainer;

/**
 * 
 * @author oscardelatorre
 * @date Mar 12, 2014
 * 
 */
public class SkatExportTopicCopiedFromTransportUppdragMapper {
	private static final Logger logger = Logger.getLogger(SkatExportTopicCopiedFromTransportUppdragMapper.class.getName());
	
	public JsonSkatExportTopicCopiedFromTransportUppdragContainer getContainer(String utfPayload) throws Exception{
		ObjectMapper mapper = new ObjectMapper();  
		mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES,false);
		//At this point we now have an UTF-8 payload
		JsonSkatExportTopicCopiedFromTransportUppdragContainer topicListContainer = mapper.readValue(utfPayload.getBytes(), JsonSkatExportTopicCopiedFromTransportUppdragContainer.class); 
		//logger.info(mapper.writeValueAsString(topicListContainer));
		logger.info("[JSON-String payload status=OK]  " + topicListContainer.getUser());
		
		//DEBUG 
		/*
		Collection<JsonTdsExportTopicListRecord> list = topicListContainer.getOrderList();
		for(JsonTdsExportTopicListRecord record : list){
			logger.info("TullId: " + record.getTullId());
			logger.info("Avs�ndare: " + record.getAvsNavn());
			
		}*/
		
		
		return topicListContainer;
	}
}
