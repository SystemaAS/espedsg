/**
 * 
 */
package no.systema.tvinn.sad.sadimport.mapper.jsonjackson;

//jackson library
import org.apache.log4j.Logger;
import org.codehaus.jackson.map.DeserializationConfig;
import org.codehaus.jackson.map.ObjectMapper;
//application library
import no.systema.tvinn.sad.sadimport.model.jsonjackson.topic.JsonSadImportTopicListContainer;
import no.systema.tvinn.sad.sadimport.model.jsonjackson.topic.JsonSadImportTopicListRecord;

/**
 * 
 * @author oscardelatorre
 * @date May 22, 2014
 * 
 * 
 */
public class SadImportTopicListMapper {
	private static final Logger logger = Logger.getLogger(SadImportTopicListMapper.class.getName());
	
	public JsonSadImportTopicListContainer getContainer(String utfPayload) throws Exception{
		ObjectMapper mapper = new ObjectMapper();  
		mapper.configure(DeserializationConfig.Feature.FAIL_ON_UNKNOWN_PROPERTIES,false);
		//At this point we now have an UTF-8 payload
		JsonSadImportTopicListContainer topicListContainer = mapper.readValue(utfPayload.getBytes(), JsonSadImportTopicListContainer.class); 
		//logger.info(mapper.writeValueAsString(topicListContainer));
		logger.info("[JSON-String payload status=OK]  " + topicListContainer.getUser());
		for (JsonSadImportTopicListRecord record : topicListContainer.getOrderList()){
			//DEBUG
			//logger.info(record.getAvsNavn());
		}
		return topicListContainer;
	}
}
