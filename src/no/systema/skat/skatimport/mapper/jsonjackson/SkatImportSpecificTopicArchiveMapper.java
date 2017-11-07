/**
 * 
 */
package no.systema.skat.skatimport.mapper.jsonjackson;

//
import java.util.Collection;

//jackson library
import org.apache.log4j.Logger;

import com.fasterxml.jackson.databind.DeserializationConfig;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;

//application library
import no.systema.skat.skatimport.model.jsonjackson.topic.archive.JsonSkatImportSpecificTopicArchiveContainer;
import no.systema.skat.skatimport.model.jsonjackson.topic.archive.JsonSkatImportSpecificTopicArchiveRecord;

/**
 * @author oscardelatorre
 * @date Mar 4, 2014
 * 
 */
public class SkatImportSpecificTopicArchiveMapper {
	private static final Logger logger = Logger.getLogger(SkatImportSpecificTopicArchiveMapper.class.getName());
	
	public JsonSkatImportSpecificTopicArchiveContainer getContainer(String utfPayload) throws Exception{
		ObjectMapper mapper = new ObjectMapper();  
		mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES,false);
		
		//At this point we now have an UTF-8 payload
		JsonSkatImportSpecificTopicArchiveContainer container = mapper.readValue(utfPayload.getBytes(), JsonSkatImportSpecificTopicArchiveContainer.class); 
		//logger.info(mapper.writeValueAsString(topicListContainer));
		logger.info("[JSON-String payload status=OK]  " + container.getUser());
		
		//DEBUG
		Collection<JsonSkatImportSpecificTopicArchiveRecord> list = container.getArchiveElements();
		for(JsonSkatImportSpecificTopicArchiveRecord record : list){
			//logger.info("Url (archive): " + record.getUrl());
			//logger.info("Subject (archive): " + record.getSubject());
		}
		return container;
	}
}
