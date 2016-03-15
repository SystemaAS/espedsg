/**
 * 
 */
package no.systema.tds.tdsexport.mapper.jsonjackson;

//jackson library
import org.apache.log4j.Logger;
import org.codehaus.jackson.map.DeserializationConfig;
import org.codehaus.jackson.map.ObjectMapper;
//application library
import no.systema.tds.tdsexport.model.jsonjackson.topic.archive.JsonTdsExportSpecificTopicArchiveContainer;
import no.systema.tds.tdsexport.model.jsonjackson.topic.archive.JsonTdsExportSpecificTopicArchiveRecord;
//
import java.util.*;

/**
 * @author oscardelatorre
 * @date June 28, 2013
 * 
 */
public class TdsExportSpecificTopicArchiveMapper {
	private static final Logger logger = Logger.getLogger(TdsExportSpecificTopicArchiveMapper.class.getName());
	
	public JsonTdsExportSpecificTopicArchiveContainer getContainer(String utfPayload) throws Exception{
		ObjectMapper mapper = new ObjectMapper();  
		mapper.configure(DeserializationConfig.Feature.FAIL_ON_UNKNOWN_PROPERTIES,false);
		
		//At this point we now have an UTF-8 payload
		JsonTdsExportSpecificTopicArchiveContainer container = mapper.readValue(utfPayload.getBytes(), JsonTdsExportSpecificTopicArchiveContainer.class); 
		//logger.info(mapper.writeValueAsString(topicListContainer));
		logger.info("[JSON-String payload status=OK]  " + container.getUser());
		//DEBUG
		Collection<JsonTdsExportSpecificTopicArchiveRecord> list = container.getArchiveElements();
		for(JsonTdsExportSpecificTopicArchiveRecord record : list){
			logger.info("Url (archive): " + record.getUrl());
			logger.info("Subject (archive): " + record.getSubject());
			
		}
		return container;
	}
}
