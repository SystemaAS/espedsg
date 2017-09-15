/**
 * 
 */
package no.systema.tror.mapper.jsonjackson.order.archive;

//jackson library
import org.apache.log4j.Logger;
import org.codehaus.jackson.map.DeserializationConfig;
import org.codehaus.jackson.map.ObjectMapper;
//application library
import no.systema.tror.model.jsonjackson.archive.JsonTrorOrderHeaderArchiveContainer;
import no.systema.tror.model.jsonjackson.archive.JsonTrorOrderHeaderArchiveRecord;
//
import java.util.*;

/**
 * @author oscardelatorre
 * @date Sep 15, 2017
 * 
 */
public class JsonTrorOrderArchiveMapper {
	private static final Logger logger = Logger.getLogger(JsonTrorOrderArchiveMapper.class.getName());
	
	public JsonTrorOrderHeaderArchiveContainer getContainer(String utfPayload) throws Exception{
		ObjectMapper mapper = new ObjectMapper();  
		mapper.configure(DeserializationConfig.Feature.FAIL_ON_UNKNOWN_PROPERTIES,false);
		
		//At this point we now have an UTF-8 payload
		JsonTrorOrderHeaderArchiveContainer container = mapper.readValue(utfPayload.getBytes(), JsonTrorOrderHeaderArchiveContainer.class); 
		//logger.info(mapper.writeValueAsString(topicListContainer));
		logger.info("[JSON-String payload status=OK]  " + container.getUser());
		//DEBUG
		Collection<JsonTrorOrderHeaderArchiveRecord> list = container.getArchiveElements();
		for(JsonTrorOrderHeaderArchiveRecord record : list){
			//logger.info("Url (archive): " + record.getUrl());
			//logger.info("Subject (archive): " + record.getSubject());
		}
		return container;
	}
}
