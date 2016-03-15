/**
 * 
 */
package no.systema.main.mapper.jsonjackson;

//jackson library
import org.apache.log4j.Logger;
import org.codehaus.jackson.map.DeserializationConfig;
import org.codehaus.jackson.map.ObjectMapper;
//application library
import no.systema.main.model.jsonjackson.JsonSystemaUserContainer;
import no.systema.main.model.jsonjackson.JsonSystemaUserRecord;

//java lib
import java.util.*;

/**
 * General mapper to the main package (Systema Web eSped)
 * 
 * @author oscardelatorre
 * 
 */
public class SystemaUserMapper {
	private static final Logger logger = Logger.getLogger(SystemaUserMapper.class.getName());
	
	public JsonSystemaUserContainer getContainer(String utfPayload) throws Exception{
		ObjectMapper mapper = new ObjectMapper();  
		mapper.configure(DeserializationConfig.Feature.FAIL_ON_UNKNOWN_PROPERTIES,false);
		
		//At this point we now have an UTF-8 payload
		JsonSystemaUserContainer systemaUserContainer = mapper.readValue(utfPayload.getBytes(), JsonSystemaUserContainer.class); 
		logger.info("Mapping Systema User object from JSON payload...");
		logger.info("[JSON-String payload status=OK]  " + systemaUserContainer.getUser());
		
		//DEBUG
		Collection<JsonSystemaUserRecord> fields = systemaUserContainer.getMenuList();
		for(JsonSystemaUserRecord record : fields){
			//logger.info("Program: " + record.getProg());
			//logger.info("Program text: " + record.getPrTxt());
		}
			
		return systemaUserContainer;
	}
}
