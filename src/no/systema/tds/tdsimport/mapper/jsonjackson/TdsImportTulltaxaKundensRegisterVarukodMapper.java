/**
 * 
 */
package no.systema.tds.tdsimport.mapper.jsonjackson;

//jackson library
import org.apache.log4j.Logger;
import org.codehaus.jackson.map.DeserializationConfig;
import org.codehaus.jackson.map.ObjectMapper;
//application library
import no.systema.tds.tdsimport.model.jsonjackson.topic.items.JsonTdsImportTulltaxaKundensRegisterVarukodContainer;
import no.systema.tds.tdsimport.model.jsonjackson.topic.items.JsonTdsImportTulltaxaKundensRegisterVarukodRecord;

import java.util.*;

/**
 * 
 * @author oscardelatorre
 * @date Jan 25, 2016
 * 
 * 
 */
public class TdsImportTulltaxaKundensRegisterVarukodMapper {
	private static final Logger logger = Logger.getLogger(TdsImportTulltaxaKundensRegisterVarukodMapper.class.getName());
	
	/**
	 * 
	 * @param utfPayload
	 * @return
	 * @throws Exception
	 */
	public JsonTdsImportTulltaxaKundensRegisterVarukodContainer getContainer(String utfPayload) throws Exception{
		ObjectMapper mapper = new ObjectMapper();  
		mapper.configure(DeserializationConfig.Feature.FAIL_ON_UNKNOWN_PROPERTIES,false);
		mapper.configure(DeserializationConfig.Feature.WRAP_EXCEPTIONS,true);
		
		JsonTdsImportTulltaxaKundensRegisterVarukodContainer taricCodeContainer = null;
		
		if(utfPayload!=null){
			//At this point we now have an UTF-8 payload
			taricCodeContainer = mapper.readValue(utfPayload.getBytes(), JsonTdsImportTulltaxaKundensRegisterVarukodContainer.class); 
			//logger.info(mapper.writeValueAsString(topicListContainer));
			//logger.info("Mapping Code object from JSON payload...");
			//logger.info("[JSON-String payload status=OK]  " + taricCodeContainer.getUser());
			
			//DEBUG
			Collection<JsonTdsImportTulltaxaKundensRegisterVarukodRecord> fields = taricCodeContainer.getKundvarlist();
			for(JsonTdsImportTulltaxaKundensRegisterVarukodRecord record : fields){
				//logger.info("Varukod: " + record.getTatanr());
			}
		}	
		return taricCodeContainer;
	}
}
