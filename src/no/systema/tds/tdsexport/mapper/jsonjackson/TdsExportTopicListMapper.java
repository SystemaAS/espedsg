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
import no.systema.tds.tdsexport.model.jsonjackson.topic.JsonTdsExportTopicListContainer;
import no.systema.tds.tdsexport.model.jsonjackson.topic.JsonTdsExportTopicListRecord;
//
import java.util.*;

/**
 * @author oscardelatorre
 * 
 */
public class TdsExportTopicListMapper {
	private static final Logger logger = Logger.getLogger(TdsExportTopicListMapper.class.getName());
	
	public JsonTdsExportTopicListContainer getContainer(String utfPayload) throws Exception{
		ObjectMapper mapper = new ObjectMapper();  
		mapper.configure(DeserializationConfig.Feature.FAIL_ON_UNKNOWN_PROPERTIES,false);
		//At this point we now have an UTF-8 payload
		JsonTdsExportTopicListContainer topicListContainer = mapper.readValue(utfPayload.getBytes(), JsonTdsExportTopicListContainer.class); 
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
