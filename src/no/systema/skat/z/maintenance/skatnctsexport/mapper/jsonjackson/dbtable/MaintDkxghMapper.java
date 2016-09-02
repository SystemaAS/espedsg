/**
 * 
 */
package no.systema.skat.z.maintenance.skatnctsexport.mapper.jsonjackson.dbtable;

//jackson library
import org.apache.log4j.Logger;
import org.codehaus.jackson.map.DeserializationConfig;
import org.codehaus.jackson.map.ObjectMapper;
//application library
import no.systema.skat.z.maintenance.skatnctsexport.model.jsonjackson.dbtable.JsonMaintDkxghContainer;
import no.systema.skat.z.maintenance.skatnctsexport.model.jsonjackson.dbtable.JsonMaintDkxghRecord;
//
import java.util.*;

/**
 * @author oscardelatorre
 * @date Sep 2, 2016
 * 
 */
public class MaintDkxghMapper {
	private static final Logger logger = Logger.getLogger(MaintDkxghMapper.class.getName());
	
	public JsonMaintDkxghContainer getContainer(String utfPayload) throws Exception{
		ObjectMapper mapper = new ObjectMapper();  
		mapper.configure(DeserializationConfig.Feature.FAIL_ON_UNKNOWN_PROPERTIES,false);
		
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
