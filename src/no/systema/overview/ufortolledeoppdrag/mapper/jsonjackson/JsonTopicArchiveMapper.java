/**
 * 
 */
package no.systema.overview.ufortolledeoppdrag.mapper.jsonjackson;

//jackson library
import org.apache.log4j.Logger;
import org.codehaus.jackson.map.DeserializationConfig;
import org.codehaus.jackson.map.ObjectMapper;
//application library
import no.systema.overview.ufortolledeoppdrag.model.jsonjackson.archive.JsonTopicArchiveContainer;
import no.systema.overview.ufortolledeoppdrag.model.jsonjackson.archive.JsonTopicArchiveRecord;
//
import java.util.*;

/**
 * @author oscardelatorre
 * @date Sep 22, 2013
 * 
 */
public class JsonTopicArchiveMapper {
	private static final Logger logger = Logger.getLogger(JsonTopicArchiveMapper.class.getName());
	
	public JsonTopicArchiveContainer getContainer(String utfPayload) throws Exception{
		ObjectMapper mapper = new ObjectMapper();  
		mapper.configure(DeserializationConfig.Feature.FAIL_ON_UNKNOWN_PROPERTIES,false);
		
		//At this point we now have an UTF-8 payload
		JsonTopicArchiveContainer container = mapper.readValue(utfPayload.getBytes(), JsonTopicArchiveContainer.class); 
		//logger.info(mapper.writeValueAsString(topicListContainer));
		logger.info("[JSON-String payload status=OK]  " + container.getUser());
		//DEBUG
		Collection<JsonTopicArchiveRecord> list = container.getArchiveElements();
		for(JsonTopicArchiveRecord record : list){
			logger.info("Url (archive): " + record.getUrl());
			logger.info("Subject (archive): " + record.getSubject());
			
		}
		return container;
	}
}
