/**
 * 
 */
package no.systema.tds.tdsexport.mapper.jsonjackson;

//jackson library
import org.apache.log4j.Logger;
import org.codehaus.jackson.map.DeserializationConfig;
import org.codehaus.jackson.map.ObjectMapper;
//application library
import no.systema.tds.tdsexport.model.jsonjackson.tullkontor.JsonTdsExportTullkontorContainer;
import no.systema.tds.tdsexport.model.jsonjackson.tullkontor.JsonTdsExportTullkontorRecord;


//
import java.util.*;

/**
 * @author oscardelatorre
 * 
 */
public class TdsExportTullkontorMapper {
	private static final Logger logger = Logger.getLogger(TdsExportTullkontorMapper.class.getName());
	
	public JsonTdsExportTullkontorContainer getContainer(String utfPayload) throws Exception{
		ObjectMapper mapper = new ObjectMapper();  
		mapper.configure(DeserializationConfig.Feature.FAIL_ON_UNKNOWN_PROPERTIES,false);
		
		//At this point we now have an UTF-8 payload
		JsonTdsExportTullkontorContainer listContainer = mapper.readValue(utfPayload.getBytes(), JsonTdsExportTullkontorContainer.class); 
		//logger.info(mapper.writeValueAsString(topicListContainer));
		logger.info("Mapping Customer object from JSON payload...");
		logger.info("[JSON-String payload status=OK]  " + listContainer.getUser());
		
		//DEBUG
		Collection<JsonTdsExportTullkontorRecord> fields = listContainer.getCustomslist();
		for(JsonTdsExportTullkontorRecord record : fields){
			//logger.info("tullkontor-txt: " + record.getTktxtn());
			//logger.info("tullkontor-code: " + record.getTkkode());
			
		}
			
		return listContainer;
	}
}
