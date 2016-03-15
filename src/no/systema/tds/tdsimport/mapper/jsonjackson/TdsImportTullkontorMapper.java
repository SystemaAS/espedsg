/**
 * 
 */
package no.systema.tds.tdsimport.mapper.jsonjackson;

//jackson library
import org.apache.log4j.Logger;
import org.codehaus.jackson.map.DeserializationConfig;
import org.codehaus.jackson.map.ObjectMapper;
//application library
import no.systema.tds.tdsimport.model.jsonjackson.tullkontor.JsonTdsImportTullkontorContainer;
import no.systema.tds.tdsimport.model.jsonjackson.tullkontor.JsonTdsImportTullkontorRecord;


//
import java.util.*;

/**
 * @author oscardelatorre
 * @date Sep 2, 2103
 * 
 */
public class TdsImportTullkontorMapper {
	private static final Logger logger = Logger.getLogger(TdsImportTullkontorMapper.class.getName());
	
	public JsonTdsImportTullkontorContainer getContainer(String utfPayload) throws Exception{
		ObjectMapper mapper = new ObjectMapper();  
		mapper.configure(DeserializationConfig.Feature.FAIL_ON_UNKNOWN_PROPERTIES,false);
		
		//At this point we now have an UTF-8 payload
		JsonTdsImportTullkontorContainer listContainer = mapper.readValue(utfPayload.getBytes(), JsonTdsImportTullkontorContainer.class); 
		//logger.info(mapper.writeValueAsString(topicListContainer));
		logger.info("Mapping Customer object from JSON payload...");
		logger.info("[JSON-String payload status=OK]  " + listContainer.getUser());
		
		//DEBUG
		Collection<JsonTdsImportTullkontorRecord> fields = listContainer.getCustomslist();
		for(JsonTdsImportTullkontorRecord record : fields){
			logger.info("tullkontor-txt: " + record.getTktxtn());
			logger.info("tullkontor-code: " + record.getTkkode());
			
		}
			
		return listContainer;
	}
}
