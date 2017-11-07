/**
 * 
 */
package no.systema.skat.nctsimport.mapper.jsonjackson;

//
import java.util.Collection;

//jackson library
import org.apache.log4j.Logger;

import com.fasterxml.jackson.databind.DeserializationConfig;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;

//application library
import no.systema.skat.nctsimport.model.jsonjackson.topic.archive.JsonSkatNctsImportSpecificTopicArchiveContainer;
import no.systema.skat.nctsimport.model.jsonjackson.topic.archive.JsonSkatNctsImportSpecificTopicArchiveRecord;

/**
 * @author oscardelatorre
 * @date Apr 24, 2014
 * 
 */
public class SkatNctsImportSpecificTopicArchiveMapper {
	private static final Logger logger = Logger.getLogger(SkatNctsImportSpecificTopicArchiveMapper.class.getName());
	
	public JsonSkatNctsImportSpecificTopicArchiveContainer getContainer(String utfPayload) throws Exception{
		ObjectMapper mapper = new ObjectMapper();  
		mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES,false);
		
		//At this point we now have an UTF-8 payload
		JsonSkatNctsImportSpecificTopicArchiveContainer container = mapper.readValue(utfPayload.getBytes(), JsonSkatNctsImportSpecificTopicArchiveContainer.class); 
		//logger.info(mapper.writeValueAsString(topicListContainer));
		logger.info("[JSON-String payload status=OK]  " + container.getUser());
		//DEBUG
		Collection<JsonSkatNctsImportSpecificTopicArchiveRecord> list = container.getArchiveElements();
		for(JsonSkatNctsImportSpecificTopicArchiveRecord record : list){
			/*logger.info("Url (archive): " + record.getUrl());
			logger.info("Subject (archive): " + record.getSubject());
			*/
		}
		return container;
	}
}
