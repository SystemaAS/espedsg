/**
 * 
 */
package no.systema.tds.tdsexport.mapper.jsonjackson;

//jackson library
import org.apache.log4j.Logger;
import org.codehaus.jackson.map.DeserializationConfig;
import org.codehaus.jackson.map.ObjectMapper;
//application library
import no.systema.tds.tdsexport.model.jsonjackson.topic.items.JsonTdsExportTulltaxaKundensRegisterVarukodContainer;
import no.systema.tds.tdsexport.model.jsonjackson.topic.items.JsonTdsExportTulltaxaKundensRegisterVarukodRecord;

import java.util.*;

/**
 * 
 * @author oscardelatorre
 * @date Jan 28, 2016
 * 
 * 
 */
public class TdsExportTulltaxaKundensRegisterVarukodMapper {
	private static final Logger logger = Logger.getLogger(TdsExportTulltaxaKundensRegisterVarukodMapper.class.getName());
	
	/**
	 * 
	 * @param utfPayload
	 * @return
	 * @throws Exception
	 */
	public JsonTdsExportTulltaxaKundensRegisterVarukodContainer getContainer(String utfPayload) throws Exception{
		ObjectMapper mapper = new ObjectMapper();  
		mapper.configure(DeserializationConfig.Feature.FAIL_ON_UNKNOWN_PROPERTIES,false);
		mapper.configure(DeserializationConfig.Feature.WRAP_EXCEPTIONS,true);
		
		JsonTdsExportTulltaxaKundensRegisterVarukodContainer taricCodeContainer = null;
		
		if(utfPayload!=null){
			//At this point we now have an UTF-8 payload
			taricCodeContainer = mapper.readValue(utfPayload.getBytes(), JsonTdsExportTulltaxaKundensRegisterVarukodContainer.class); 
			//logger.info(mapper.writeValueAsString(topicListContainer));
			//logger.info("Mapping Code object from JSON payload...");
			//logger.info("[JSON-String payload status=OK]  " + taricCodeContainer.getUser());
			
			//DEBUG
			Collection<JsonTdsExportTulltaxaKundensRegisterVarukodRecord> fields = taricCodeContainer.getKundvarlist();
			for(JsonTdsExportTulltaxaKundensRegisterVarukodRecord record : fields){
				//logger.info("Varukod: " + record.getTatanr());
			}
		}	
		return taricCodeContainer;
	}
}
