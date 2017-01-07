/**
 * 
 */
package no.systema.ebooking.mapper.jsonjackson;

//jackson library
import org.apache.log4j.Logger;
import org.codehaus.jackson.map.DeserializationConfig;
import org.codehaus.jackson.map.ObjectMapper;
//application library
import no.systema.ebooking.model.jsonjackson.codes.JsonEbookingCodeContainer;
import no.systema.ebooking.model.jsonjackson.codes.JsonEbookingCodeRecord;

import java.util.*;

/**
 * 
 * @author oscardelatorre
 * @date Jan 07, 2017
 * 
 * 
 */
public class JsonEbookingCodeMapper {
	private static final Logger logger = Logger.getLogger(JsonEbookingCodeMapper.class.getName());
	
	public JsonEbookingCodeContainer getContainer(String utfPayload) throws Exception{
		ObjectMapper mapper = new ObjectMapper();  
		mapper.configure(DeserializationConfig.Feature.FAIL_ON_UNKNOWN_PROPERTIES,false);
		
		JsonEbookingCodeContainer codeContainer = null;
		
		if(utfPayload!=null){
			//At this point we now have an UTF-8 payload
			codeContainer = mapper.readValue(utfPayload.getBytes(), JsonEbookingCodeContainer.class); 
			//logger.info("Mapping Code object from JSON payload...");
			//logger.info("[JSON-String payload status=OK]  " + codeContainer.getUser());
			
			//DEBUG
			Collection<JsonEbookingCodeRecord> fields = codeContainer.getKodlista();
			for(JsonEbookingCodeRecord record : fields){

			}
		}	
		return codeContainer;
	}
	
}
