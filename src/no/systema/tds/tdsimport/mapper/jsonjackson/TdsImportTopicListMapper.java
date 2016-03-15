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
import no.systema.tds.tdsimport.model.jsonjackson.topic.JsonTdsImportTopicListContainer;
import no.systema.tds.tdsimport.model.jsonjackson.topic.JsonTdsImportTopicListRecord;
//
import java.util.*;

/**
 * @author oscardelatorre
 * @date Sep 2, 2013
 * 
 */
public class TdsImportTopicListMapper {
	private static final Logger logger = Logger.getLogger(TdsImportTopicListMapper.class.getName());
	
	public JsonTdsImportTopicListContainer getContainer(String utfPayload) throws Exception{
		ObjectMapper mapper = new ObjectMapper();  
		mapper.configure(DeserializationConfig.Feature.FAIL_ON_UNKNOWN_PROPERTIES,false);
		//At this point we now have an UTF-8 payload
		JsonTdsImportTopicListContainer topicListContainer = mapper.readValue(utfPayload.getBytes(), JsonTdsImportTopicListContainer.class); 
		//logger.info(mapper.writeValueAsString(topicListContainer));
		logger.info("[JSON-String payload status=OK]  " + topicListContainer.getUser());
		
		//DEBUG 
		/*
		Collection<JsonTdsImportTopicListRecord> list = topicListContainer.getOrderList();
		for(JsonTdsImportTopicListRecord record : list){
			logger.info("TullId: " + record.getTullId());
			logger.info("Avs�ndare: " + record.getAvsNavn());
			
		}*/
		
		
		return topicListContainer;
	}
}
