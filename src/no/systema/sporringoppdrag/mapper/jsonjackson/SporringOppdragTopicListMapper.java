/**
 * 
 */
package no.systema.sporringoppdrag.mapper.jsonjackson;

//jackson library
import org.apache.log4j.Logger;
import org.codehaus.jackson.map.DeserializationConfig;
import org.codehaus.jackson.map.ObjectMapper;
//application library
import no.systema.sporringoppdrag.model.jsonjackson.topic.JsonSporringOppdragTopicListContainer;
import no.systema.sporringoppdrag.model.jsonjackson.topic.JsonSporringOppdragTopicListRecord;

/**
 * 
 * @author oscardelatorre
 * @date Feb 4, 2015
 * 
 * 
 */
public class SporringOppdragTopicListMapper {
	private static final Logger logger = Logger.getLogger(SporringOppdragTopicListMapper.class.getName());
	
	public JsonSporringOppdragTopicListContainer getContainer(String utfPayload) throws Exception{
		ObjectMapper mapper = new ObjectMapper();  
		mapper.configure(DeserializationConfig.Feature.FAIL_ON_UNKNOWN_PROPERTIES,false);
		//At this point we now have an UTF-8 payload
		JsonSporringOppdragTopicListContainer topicListContainer = mapper.readValue(utfPayload.getBytes(), JsonSporringOppdragTopicListContainer.class); 
		//logger.info(mapper.writeValueAsString(topicListContainer));
		logger.info("[JSON-String payload status=OK]  " + topicListContainer.getUser());
		for (JsonSporringOppdragTopicListRecord record : topicListContainer.getQryoppdrag()){
			//DEBUG
		}
		return topicListContainer;
	}
}
