/**
 * 
 */
package no.systema.skat.mapper.jsonjackson;

//jackson library
import org.apache.log4j.Logger;
import org.codehaus.jackson.map.DeserializationConfig;
import org.codehaus.jackson.map.ObjectMapper;
//application library
import no.systema.skat.model.jsonjackson.codes.JsonSkatCodeContainer;
import no.systema.skat.model.jsonjackson.codes.JsonSkatCodeRecord;
import no.systema.skat.model.jsonjackson.codes.JsonSkatNctsCodeContainer;
import no.systema.skat.model.jsonjackson.codes.JsonSkatNctsCodeRecord;

import java.util.*;

/**
 * 
 * @author oscardelatorre
 * @date Jan 27, 2014
 * 
 * 
 */
public class SkatCodeMapper {
	private static final Logger logger = Logger.getLogger(SkatCodeMapper.class.getName());
	
	public JsonSkatCodeContainer getContainer(String utfPayload) throws Exception{
		ObjectMapper mapper = new ObjectMapper();  
		mapper.configure(DeserializationConfig.Feature.FAIL_ON_UNKNOWN_PROPERTIES,false);
		
		JsonSkatCodeContainer codeContainer = null;
		
		if(utfPayload!=null){
			//At this point we now have an UTF-8 payload
			codeContainer = mapper.readValue(utfPayload.getBytes(), JsonSkatCodeContainer.class); 
			//logger.info("Mapping Code object from JSON payload...");
			//logger.info("[JSON-String payload status=OK]  " + codeContainer.getUser());
			
			//DEBUG
			Collection<JsonSkatCodeRecord> fields = codeContainer.getKodlista();
			for(JsonSkatCodeRecord record : fields){
				/*logger.info("Code: " + record.getSvkd_kd());
				logger.info("Value: " + record.getSvkd_kbs());
				*/
			}
		}	
		return codeContainer;
	}
	
	/**
	 * 
	 * @param utfPayload
	 * @return
	 * @throws Exception
	 */
	
	public JsonSkatNctsCodeContainer getNctsContainer(String utfPayload) throws Exception{
		ObjectMapper mapper = new ObjectMapper();  
		mapper.configure(DeserializationConfig.Feature.FAIL_ON_UNKNOWN_PROPERTIES,false);
		
		JsonSkatNctsCodeContainer codeContainer = null;
		
		if(utfPayload!=null){
			//At this point we now have an UTF-8 payload
			codeContainer = mapper.readValue(utfPayload.getBytes(), JsonSkatNctsCodeContainer.class); 
			//logger.info(mapper.writeValueAsString(topicListContainer));
			//logger.info("Mapping Code object from JSON payload...");
			//logger.info("[JSON-String payload status=OK]  " + codeContainer.getUser());
			
			//DEBUG
			Collection<JsonSkatNctsCodeRecord> fields = codeContainer.getKodlista();
			for(JsonSkatNctsCodeRecord record : fields){
				//logger.info("Code: " + record.getTkkode());
			}
		}	
		return codeContainer;
	}
	
}
