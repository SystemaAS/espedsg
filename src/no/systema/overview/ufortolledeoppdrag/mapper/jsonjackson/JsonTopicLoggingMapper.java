/**
 * 
 */
package no.systema.overview.ufortolledeoppdrag.mapper.jsonjackson;

//jackson library
import org.apache.log4j.Logger;

import no.systema.main.mapper.jsonjackson.general.ObjectMapperAbstractGrandFather;
//application library
import no.systema.overview.ufortolledeoppdrag.model.jsonjackson.logging.JsonTopicLoggingContainer;
import no.systema.overview.ufortolledeoppdrag.model.jsonjackson.logging.JsonTopicLoggingRecord;
//
import java.util.*;

/**
 * @author oscardelatorre
 * @date Sep 22, 2013
 * 
 */
public class JsonTopicLoggingMapper extends ObjectMapperAbstractGrandFather {
	private static final Logger logger = Logger.getLogger(JsonTopicLoggingMapper.class.getName());
	
	public JsonTopicLoggingContainer getContainer(String utfPayload) throws Exception{
		
		//At this point we now have an UTF-8 payload
		JsonTopicLoggingContainer container = super.getObjectMapper().readValue(utfPayload.getBytes(), JsonTopicLoggingContainer.class); 
		//logger.info(mapper.writeValueAsString(topicListContainer));
		logger.info("[JSON-String payload status=OK]  " + container.getUser());
		//DEBUG
		Collection<JsonTopicLoggingRecord> list = container.getTrackTraceEvents();
		for(JsonTopicLoggingRecord record : list){
			//Debugg
			
		}
		return container;
	}
}
