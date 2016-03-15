/**
 * 
 */
package no.systema.tds.nctsimport.mapper.jsonjackson;

//jackson library
import org.apache.log4j.Logger;
import org.codehaus.jackson.map.DeserializationConfig;
import org.codehaus.jackson.map.ObjectMapper;
//application library
import no.systema.tds.nctsimport.model.jsonjackson.topic.archive.JsonNctsImportSpecificTopicArchiveContainer;
import no.systema.tds.nctsimport.model.jsonjackson.topic.archive.JsonNctsImportSpecificTopicArchiveRecord;
//
import java.util.*;

/**
 * @author oscardelatorre
 * @date Apr 4, 2014
 * 
 */
public class NctsImportSpecificTopicArchiveMapper {
	private static final Logger logger = Logger.getLogger(NctsImportSpecificTopicArchiveMapper.class.getName());
	
	public JsonNctsImportSpecificTopicArchiveContainer getContainer(String utfPayload) throws Exception{
		ObjectMapper mapper = new ObjectMapper();  
		mapper.configure(DeserializationConfig.Feature.FAIL_ON_UNKNOWN_PROPERTIES,false);
		
		//At this point we now have an UTF-8 payload
		JsonNctsImportSpecificTopicArchiveContainer container = mapper.readValue(utfPayload.getBytes(), JsonNctsImportSpecificTopicArchiveContainer.class); 
		//logger.info(mapper.writeValueAsString(topicListContainer));
		logger.info("[JSON-String payload status=OK]  " + container.getUser());
		//DEBUG
		Collection<JsonNctsImportSpecificTopicArchiveRecord> list = container.getArchiveElements();
		for(JsonNctsImportSpecificTopicArchiveRecord record : list){
			logger.info("Url (archive): " + record.getUrl());
			logger.info("Subject (archive): " + record.getSubject());
			
		}
		return container;
	}
}
