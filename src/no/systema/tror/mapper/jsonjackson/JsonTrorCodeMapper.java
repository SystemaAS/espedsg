/**
 * 
 */
package no.systema.tror.mapper.jsonjackson;

//jackson library
import org.apache.log4j.Logger;
import org.codehaus.jackson.map.DeserializationConfig;
import org.codehaus.jackson.map.ObjectMapper;
//application library
import no.systema.tror.model.jsonjackson.codes.JsonTrorCodeContainer;
import no.systema.tror.model.jsonjackson.codes.JsonTrorCodeRecord;

import java.util.*;

/**
 * 
 * @author oscardelatorre
 * @date Aug 11, 2017
 * 
 * 
 */
public class JsonTrorCodeMapper {
	private static final Logger logger = Logger.getLogger(JsonTrorCodeMapper.class.getName());
	
	public JsonTrorCodeContainer getContainer(String utfPayload) throws Exception{
		ObjectMapper mapper = new ObjectMapper();  
		mapper.configure(DeserializationConfig.Feature.FAIL_ON_UNKNOWN_PROPERTIES,false);
		
		JsonTrorCodeContainer codeContainer = null;
		
		if(utfPayload!=null){
			//At this point we now have an UTF-8 payload
			codeContainer = mapper.readValue(utfPayload.getBytes(), JsonTrorCodeContainer.class); 
			//logger.info("Mapping Code object from JSON payload...");
			//logger.info("[JSON-String payload status=OK]  " + codeContainer.getUser());
			
			//DEBUG
			Collection<JsonTrorCodeRecord> fields = codeContainer.getList();
			for(JsonTrorCodeRecord record : fields){

			}
		}	
		return codeContainer;
	}
	
	
	
}
