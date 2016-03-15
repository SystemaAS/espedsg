/**
 * 
 */
package no.systema.skat.mapper.jsonjackson;

//jackson library
import org.apache.log4j.Logger;
import org.codehaus.jackson.map.DeserializationConfig;
import org.codehaus.jackson.map.ObjectMapper;
//application library
import no.systema.skat.model.jsonjackson.tullkontor.JsonSkatTullkontorContainer;
import no.systema.skat.model.jsonjackson.tullkontor.JsonSkatTullkontorRecord;


//
import java.util.*;

/**
 * @author oscardelatorre
 * @date Apr 18, 2014
 * 
 * 
 */
public class SkatTullkontorMapper {
	private static final Logger logger = Logger.getLogger(SkatTullkontorMapper.class.getName());
	
	public JsonSkatTullkontorContainer getContainer(String utfPayload) throws Exception{
		ObjectMapper mapper = new ObjectMapper();  
		mapper.configure(DeserializationConfig.Feature.FAIL_ON_UNKNOWN_PROPERTIES,false);
		
		//At this point we now have an UTF-8 payload
		JsonSkatTullkontorContainer listContainer = mapper.readValue(utfPayload.getBytes(), JsonSkatTullkontorContainer.class); 
		//logger.info(mapper.writeValueAsString(topicListContainer));
		logger.info("Mapping Customer object from JSON payload...");
		logger.info("[JSON-String payload status=OK]  " + listContainer.getUser());
		
		//DEBUG
		Collection<JsonSkatTullkontorRecord> fields = listContainer.getCustomslist();
		for(JsonSkatTullkontorRecord record : fields){
			//logger.info("tullkontor-txt: " + record.getTktxtn());
			//logger.info("tullkontor-code: " + record.getTkkode());
			
		}
			
		return listContainer;
	}
}
