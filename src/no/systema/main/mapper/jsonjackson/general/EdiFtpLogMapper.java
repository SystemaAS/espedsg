/**
 * 
 */
package no.systema.main.mapper.jsonjackson.general;

//jackson library
import org.apache.log4j.Logger;
import org.codehaus.jackson.map.DeserializationConfig;
import org.codehaus.jackson.map.ObjectMapper;
//application library
import no.systema.main.model.jsonjackson.general.JsonEdiFtpLogContainer;
import no.systema.main.model.jsonjackson.general.JsonEdiFtpLogRecord;

//java lib
import java.util.*;

/**
 * General mapper to the main package (Systema Web eSped)
 * 
 * @author oscardelatorre
 * @date May 18, 2016
 * 
 */
public class EdiFtpLogMapper {
	private static final Logger logger = Logger.getLogger(EdiFtpLogMapper.class.getName());
	
	public JsonEdiFtpLogContainer getContainer(String utfPayload) throws Exception{
		ObjectMapper mapper = new ObjectMapper();  
		mapper.configure(DeserializationConfig.Feature.FAIL_ON_UNKNOWN_PROPERTIES,false);
		
		//At this point we now have an UTF-8 payload
		JsonEdiFtpLogContainer container = mapper.readValue(utfPayload.getBytes(), JsonEdiFtpLogContainer.class); 
		//logger.info("Mapping currency rate object from JSON payload...");
		//logger.info("[JSON-String payload status=OK]  " + systemaUserContainer.getUser());
		
		//DEBUG
		Collection<JsonEdiFtpLogRecord> fields = container.getList();
		for(JsonEdiFtpLogRecord record : fields){
			//logger.info("Currency factor: " + record.getSvvs_omr());
			//logger.info("Currency rate: " + record.getSvvk_krs());
		}
			
		return container;
	}
}
