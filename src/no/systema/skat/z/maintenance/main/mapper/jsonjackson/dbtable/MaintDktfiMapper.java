/**
 * 
 */
package no.systema.skat.z.maintenance.main.mapper.jsonjackson.dbtable;

//jackson library
import org.apache.log4j.Logger;

import com.fasterxml.jackson.databind.DeserializationConfig;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;

import no.systema.skat.z.maintenance.main.model.jsonjackson.dbtable.JsonMaintDktfiContainer;

/**
 * @author oscardelatorre
 * @date Mar 14, 2017
 * 
 */
public class MaintDktfiMapper {
	private static final Logger logger = Logger.getLogger(MaintDktfiMapper.class.getName());
	
	public JsonMaintDktfiContainer getContainer(String utfPayload) throws Exception{
		ObjectMapper mapper = new ObjectMapper();  
		mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES,false);
		
		//At this point we now have an UTF-8 payload
		JsonMaintDktfiContainer container = mapper.readValue(utfPayload.getBytes(), JsonMaintDktfiContainer.class); 
		//logger.info("[JSON-String payload status=OK]  " + container.getUser());
		//DEBUG
		/*Collection<JsonMaintDktardRecord> list = container.getList();
		for(JsonMaintDktardRecord record : list){
			//logger.info(record.getKlikod());
		}*/
		return container;
	}
}
