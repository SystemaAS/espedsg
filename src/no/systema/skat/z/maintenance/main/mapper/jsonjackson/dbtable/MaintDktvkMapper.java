/**
 * 
 */
package no.systema.skat.z.maintenance.main.mapper.jsonjackson.dbtable;

//
import java.util.Collection;

//jackson library
import org.apache.log4j.Logger;

import com.fasterxml.jackson.databind.DeserializationConfig;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;

//application library
import no.systema.skat.z.maintenance.main.model.jsonjackson.dbtable.JsonMaintDktvkContainer;
import no.systema.skat.z.maintenance.main.model.jsonjackson.dbtable.JsonMaintDktvkRecord;

/**
 * @author oscardelatorre
 * @date Jun 13, 2016
 * 
 */
public class MaintDktvkMapper {
	private static final Logger logger = Logger.getLogger(MaintDktvkMapper.class.getName());
	
	public JsonMaintDktvkContainer getContainer(String utfPayload) throws Exception{
		ObjectMapper mapper = new ObjectMapper();  
		mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES,false);
		
		//At this point we now have an UTF-8 payload
		JsonMaintDktvkContainer container = mapper.readValue(utfPayload.getBytes(), JsonMaintDktvkContainer.class); 
		//logger.info("[JSON-String payload status=OK]  " + container.getUser());
		//DEBUG
		Collection<JsonMaintDktvkRecord> list = container.getList();
		for(JsonMaintDktvkRecord record : list){
			//logger.info(record.getKlikod());
		}
		return container;
	}
}
