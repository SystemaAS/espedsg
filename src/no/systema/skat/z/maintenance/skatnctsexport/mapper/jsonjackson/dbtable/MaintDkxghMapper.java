/**
 * 
 */
package no.systema.skat.z.maintenance.skatnctsexport.mapper.jsonjackson.dbtable;

//
import java.util.Collection;

//jackson library
import org.apache.log4j.Logger;

import com.fasterxml.jackson.databind.DeserializationConfig;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;

//application library
import no.systema.skat.z.maintenance.skatnctsexport.model.jsonjackson.dbtable.JsonMaintDkxghContainer;
import no.systema.skat.z.maintenance.skatnctsexport.model.jsonjackson.dbtable.JsonMaintDkxghRecord;

/**
 * @author oscardelatorre
 * @date Sep 2, 2016
 * 
 */
public class MaintDkxghMapper {
	private static final Logger logger = Logger.getLogger(MaintDkxghMapper.class.getName());
	
	public JsonMaintDkxghContainer getContainer(String utfPayload) throws Exception{
		ObjectMapper mapper = new ObjectMapper();  
		mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES,false);
		
		//At this point we now have an UTF-8 payload
		JsonMaintDkxghContainer container = mapper.readValue(utfPayload.getBytes(), JsonMaintDkxghContainer.class); 
		//logger.info("[JSON-String payload status=OK]  " + container.getUser());
		//DEBUG
		Collection<JsonMaintDkxghRecord> list = container.getList();
		for(JsonMaintDkxghRecord record : list){
			//logger.info(record.getKlikod());
		}
		return container;
	}
}
