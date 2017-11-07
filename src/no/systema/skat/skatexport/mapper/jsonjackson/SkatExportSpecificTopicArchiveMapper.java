/**
 * 
 */
package no.systema.skat.skatexport.mapper.jsonjackson;

//
import java.util.Collection;

//jackson library
import org.apache.log4j.Logger;

import com.fasterxml.jackson.databind.DeserializationConfig;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;

//application library
import no.systema.skat.skatexport.model.jsonjackson.topic.archive.JsonSkatExportSpecificTopicArchiveContainer;
import no.systema.skat.skatexport.model.jsonjackson.topic.archive.JsonSkatExportSpecificTopicArchiveRecord;

/**
 * @author oscardelatorre
 * @date Mar 11, 2014
 * 
 */
public class SkatExportSpecificTopicArchiveMapper {
	private static final Logger logger = Logger.getLogger(SkatExportSpecificTopicArchiveMapper.class.getName());
	
	public JsonSkatExportSpecificTopicArchiveContainer getContainer(String utfPayload) throws Exception{
		ObjectMapper mapper = new ObjectMapper();  
		mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES,false);
		
		//At this point we now have an UTF-8 payload
		JsonSkatExportSpecificTopicArchiveContainer container = mapper.readValue(utfPayload.getBytes(), JsonSkatExportSpecificTopicArchiveContainer.class); 
		//logger.info(mapper.writeValueAsString(topicListContainer));
		logger.info("[JSON-String payload status=OK]  " + container.getUser());
		//DEBUG
		Collection<JsonSkatExportSpecificTopicArchiveRecord> list = container.getArchiveElements();
		for(JsonSkatExportSpecificTopicArchiveRecord record : list){
			//logger.info("Url (archive): " + record.getUrl());
			//logger.info("Subject (archive): " + record.getSubject());
		}
		return container;
	}
}
