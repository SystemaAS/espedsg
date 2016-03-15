/**
 * 
 */
package no.systema.overview.ufortolledeoppdrag.mapper.jsonjackson;

//jackson library
import org.apache.log4j.Logger;
import org.codehaus.jackson.map.DeserializationConfig;
import org.codehaus.jackson.map.ObjectMapper;
//application library
import no.systema.overview.ufortolledeoppdrag.model.jsonjackson.tvinnlog.JsonTopicTvinnLogLargeTextContainer;
import no.systema.overview.ufortolledeoppdrag.model.jsonjackson.tvinnlog.JsonTopicTvinnLogLargeTextRecord;
//
import java.util.*;

/**
 * @author oscardelatorre
 * @date Sep 2, 2013
 * 
 */
public class JsonTopicTvinnLogLargeTextMapper {
	private static final Logger logger = Logger.getLogger(JsonTopicTvinnLogLargeTextMapper.class.getName());
	
	public JsonTopicTvinnLogLargeTextContainer getContainer(String utfPayload) throws Exception{
		ObjectMapper mapper = new ObjectMapper();  
		mapper.configure(DeserializationConfig.Feature.FAIL_ON_UNKNOWN_PROPERTIES,false);
		
		//At this point we now have an UTF-8 payload
		JsonTopicTvinnLogLargeTextContainer container = mapper.readValue(utfPayload.getBytes(), JsonTopicTvinnLogLargeTextContainer.class); 
		//logger.info(mapper.writeValueAsString(topicListContainer));
		logger.info("[JSON-String payload status=OK]  " + container.getUser());
		//DEBUG
		Collection<JsonTopicTvinnLogLargeTextRecord> list = container.getLoggtext();
		for(JsonTopicTvinnLogLargeTextRecord record : list){
			logger.info("Message nr (tvinn log): " + record.getF0078a());
		}
		return container;
	}
}
