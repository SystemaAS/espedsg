/**
 * 
 */
package no.systema.overview.ufortolledeoppdrag.mapper.jsonjackson;

//jackson library
import org.apache.log4j.Logger;
import org.codehaus.jackson.map.DeserializationConfig;
import org.codehaus.jackson.map.ObjectMapper;
//application library
import no.systema.overview.ufortolledeoppdrag.model.jsonjackson.tvinnlog.JsonTopicTvinnLogContainer;
import no.systema.overview.ufortolledeoppdrag.model.jsonjackson.tvinnlog.JsonTopicTvinnLogRecord;
//
import java.util.*;

/**
 * @author oscardelatorre
 * @date Oct 23, 2013
 * 
 */
public class JsonTopicTvinnLogMapper {
	private static final Logger logger = Logger.getLogger(JsonTopicTvinnLogMapper.class.getName());
	
	public JsonTopicTvinnLogContainer getContainer(String utfPayload) throws Exception{
		ObjectMapper mapper = new ObjectMapper();  
		mapper.configure(DeserializationConfig.Feature.FAIL_ON_UNKNOWN_PROPERTIES,false);
		
		//At this point we now have an UTF-8 payload
		JsonTopicTvinnLogContainer container = mapper.readValue(utfPayload.getBytes(), JsonTopicTvinnLogContainer.class); 
		//logger.info(mapper.writeValueAsString(topicListContainer));
		logger.info("[JSON-String payload status=OK]  " + container.getUser());
		//DEBUG
		Collection<JsonTopicTvinnLogRecord> list = container.getLogg();
		for(JsonTopicTvinnLogRecord record : list){
			//Debugg
			
		}
		return container;
	}
}
