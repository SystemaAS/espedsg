/**
 * 
 */
package no.systema.tds.admin.mapper.jsonjackson;

//jackson library
import org.apache.log4j.Logger;
import org.codehaus.jackson.map.DeserializationConfig;
import org.codehaus.jackson.map.ObjectMapper;
//application library
import no.systema.main.controller.LoginController;
import no.systema.tds.admin.model.jsonjackson.topic.JsonTdsAdminTransportListContainer;
import no.systema.tds.admin.model.jsonjackson.topic.JsonTdsAdminTransportListRecord;

//
import java.util.*;

/**
 * 
 * @author oscardelatorre
 * @date Jan 14, 2014
 * 
 */
public class TdsAdminTransportListMapper {
	private static final Logger logger = Logger.getLogger(TdsAdminTransportListMapper.class.getName());
	
	public JsonTdsAdminTransportListContainer getContainer(String utfPayload) throws Exception{
		ObjectMapper mapper = new ObjectMapper();  
		mapper.configure(DeserializationConfig.Feature.FAIL_ON_UNKNOWN_PROPERTIES,false);
		//At this point we now have an UTF-8 payload
		JsonTdsAdminTransportListContainer topicListContainer = mapper.readValue(utfPayload.getBytes(), JsonTdsAdminTransportListContainer.class); 
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
