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
import no.systema.tds.admin.model.jsonjackson.topic.JsonTdsAdminNorskImportListContainer;
import no.systema.tds.admin.model.jsonjackson.topic.JsonTdsAdminNorskImportListRecord;

//
import java.util.*;

/**
 * 
 * @author oscardelatorre
 * @date Jan 14, 2014
 * 
 */
public class TdsAdminNorskImportListMapper {
	private static final Logger logger = Logger.getLogger(TdsAdminNorskImportListMapper.class.getName());
	
	public JsonTdsAdminNorskImportListContainer getContainer(String utfPayload) throws Exception{
		ObjectMapper mapper = new ObjectMapper();  
		mapper.configure(DeserializationConfig.Feature.FAIL_ON_UNKNOWN_PROPERTIES,false);
		//At this point we now have an UTF-8 payload
		JsonTdsAdminNorskImportListContainer topicListContainer = mapper.readValue(utfPayload.getBytes(), JsonTdsAdminNorskImportListContainer.class); 
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
