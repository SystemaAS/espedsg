/**
 * 
 */
package no.systema.tds.nctsexport.mapper.jsonjackson;

//jackson library
import org.apache.log4j.Logger;
import org.codehaus.jackson.map.DeserializationConfig;
import org.codehaus.jackson.map.ObjectMapper;
//application library
import no.systema.main.controller.LoginController;
import no.systema.tds.nctsexport.model.jsonjackson.topic.JsonNctsExportTopicListContainer;
import no.systema.tds.nctsexport.model.jsonjackson.topic.JsonNctsExportTopicListRecord;
//
import java.util.*;

/**
 * @author oscardelatorre
 * 
 */
public class NctsExportTopicListMapper {
	private static final Logger logger = Logger.getLogger(NctsExportTopicListMapper.class.getName());
	
	public JsonNctsExportTopicListContainer getContainer(String utfPayload) throws Exception{
		ObjectMapper mapper = new ObjectMapper();  
		mapper.configure(DeserializationConfig.Feature.FAIL_ON_UNKNOWN_PROPERTIES,false);
		//At this point we now have an UTF-8 payload
		JsonNctsExportTopicListContainer topicListContainer = mapper.readValue(utfPayload.getBytes(), JsonNctsExportTopicListContainer.class); 
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
