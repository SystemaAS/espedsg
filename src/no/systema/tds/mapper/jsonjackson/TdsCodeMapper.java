/**
 * 
 */
package no.systema.tds.mapper.jsonjackson;

//jackson library
import org.apache.log4j.Logger;
import org.codehaus.jackson.map.DeserializationConfig;
import org.codehaus.jackson.map.ObjectMapper;
//application library
import no.systema.tds.model.jsonjackson.codes.JsonTdsCodeContainer;
import no.systema.tds.model.jsonjackson.codes.JsonTdsCodeRecord;
import no.systema.tds.model.jsonjackson.codes.JsonTdsNctsCodeContainer;
import no.systema.tds.model.jsonjackson.codes.JsonTdsNctsCodeRecord;

import java.util.*;

/**
 * @author oscardelatorre
 * 
 */
public class TdsCodeMapper {
	private static final Logger logger = Logger.getLogger(TdsCodeMapper.class.getName());
	
	public JsonTdsCodeContainer getContainer(String utfPayload) throws Exception{
		ObjectMapper mapper = new ObjectMapper();  
		mapper.configure(DeserializationConfig.Feature.FAIL_ON_UNKNOWN_PROPERTIES,false);
		
		JsonTdsCodeContainer codeContainer = null;
		
		if(utfPayload!=null){
			//At this point we now have an UTF-8 payload
			codeContainer = mapper.readValue(utfPayload.getBytes(), JsonTdsCodeContainer.class); 
			//logger.info("Mapping Code object from JSON payload...");
			//logger.info("[JSON-String payload status=OK]  " + codeContainer.getUser());
			
			//DEBUG
			Collection<JsonTdsCodeRecord> fields = codeContainer.getKodlista();
			for(JsonTdsCodeRecord record : fields){
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
	public JsonTdsNctsCodeContainer getNctsContainer(String utfPayload) throws Exception{
		ObjectMapper mapper = new ObjectMapper();  
		mapper.configure(DeserializationConfig.Feature.FAIL_ON_UNKNOWN_PROPERTIES,false);
		
		JsonTdsNctsCodeContainer codeContainer = null;
		
		if(utfPayload!=null){
			//At this point we now have an UTF-8 payload
			codeContainer = mapper.readValue(utfPayload.getBytes(), JsonTdsNctsCodeContainer.class); 
			//logger.info(mapper.writeValueAsString(topicListContainer));
			logger.info("Mapping Code object from JSON payload...");
			logger.info("[JSON-String payload status=OK]  " + codeContainer.getUser());
			
			//DEBUG
			Collection<JsonTdsNctsCodeRecord> fields = codeContainer.getKodlista();
			for(JsonTdsNctsCodeRecord record : fields){
				//logger.info("Code: " + record.getTkkode());
			}
		}	
		return codeContainer;
	}
}
