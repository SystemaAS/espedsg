/**
 * 
 */
package no.systema.transportdisp.mapper.jsonjackson;

//jackson library
import org.apache.log4j.Logger;
import org.codehaus.jackson.map.DeserializationConfig;
import org.codehaus.jackson.map.ObjectMapper;
//application library
import no.systema.transportdisp.model.jsonjackson.workflow.codes.JsonTransportDispCodeContainer;
import no.systema.transportdisp.model.jsonjackson.workflow.codes.JsonTransportDispCodeRecord;

import java.util.*;

/**
 * 
 * @author oscardelatorre
 * @date Apr 30, 2015
 * 
 * 
 */
public class JsonTransportDispCodeMapper {
	private static final Logger logger = Logger.getLogger(JsonTransportDispCodeMapper.class.getName());
	
	public JsonTransportDispCodeContainer getContainer(String utfPayload) throws Exception{
		ObjectMapper mapper = new ObjectMapper();  
		mapper.configure(DeserializationConfig.Feature.FAIL_ON_UNKNOWN_PROPERTIES,false);
		
		JsonTransportDispCodeContainer codeContainer = null;
		
		if(utfPayload!=null){
			//At this point we now have an UTF-8 payload
			codeContainer = mapper.readValue(utfPayload.getBytes(), JsonTransportDispCodeContainer.class); 
			//logger.info("Mapping Code object from JSON payload...");
			//logger.info("[JSON-String payload status=OK]  " + codeContainer.getUser());
			
			//DEBUG
			Collection<JsonTransportDispCodeRecord> fields = codeContainer.getKodlista();
			for(JsonTransportDispCodeRecord record : fields){
				/*logger.info("Code: " + record.getZkod());
				logger.info("Value: " + record.getZtxt());
				*/
			}
		}	
		return codeContainer;
	}
	
}
