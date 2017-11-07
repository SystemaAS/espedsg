/**
 * 
 */
package no.systema.skat.mapper.jsonjackson;

import java.util.Collection;

//jackson library
import org.apache.log4j.Logger;

import com.fasterxml.jackson.databind.DeserializationConfig;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;

//application library
import no.systema.skat.model.jsonjackson.codes.JsonSkatTaricVarukodContainer;
import no.systema.skat.model.jsonjackson.codes.JsonSkatTaricVarukodRecord;

/**
 * 
 * @author oscardelatorre
 * @date Feb 4, 2014
 * 
 * 
 */
public class SkatTaricVarukodMapper {
	private static final Logger logger = Logger.getLogger(SkatTaricVarukodMapper.class.getName());
	
	/**
	 * 
	 * @param utfPayload
	 * @return
	 * @throws Exception
	 */
	public JsonSkatTaricVarukodContainer getContainer(String utfPayload) throws Exception{
		ObjectMapper mapper = new ObjectMapper();  
		mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES,false);
		mapper.configure(DeserializationFeature.WRAP_EXCEPTIONS,true);
		
		JsonSkatTaricVarukodContainer taricCodeContainer = null;
		
		if(utfPayload!=null){
			//At this point we now have an UTF-8 payload
			taricCodeContainer = mapper.readValue(utfPayload.getBytes(), JsonSkatTaricVarukodContainer.class); 
			//logger.info(mapper.writeValueAsString(topicListContainer));
			//logger.info("Mapping Code object from JSON payload...");
			//logger.info("[JSON-String payload status=OK]  " + taricCodeContainer.getUser());
			
			//DEBUG
			Collection<JsonSkatTaricVarukodRecord> fields = taricCodeContainer.getTariclist();
			for(JsonSkatTaricVarukodRecord record : fields){
				/*logger.info("Vata: " + record.getSvvs_vata());
				logger.info("VataK: " + record.getSvvs_vatak());
				logger.info("Txt: " + record.getSvvs_txtk());
				*/
			}
		}	
		return taricCodeContainer;
	}
}
