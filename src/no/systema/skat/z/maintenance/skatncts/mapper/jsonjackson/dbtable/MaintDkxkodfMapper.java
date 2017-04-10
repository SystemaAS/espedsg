/**
 * 
 */
package no.systema.skat.z.maintenance.skatncts.mapper.jsonjackson.dbtable;

//jackson library
import org.apache.log4j.Logger;
import org.codehaus.jackson.map.DeserializationConfig;
import org.codehaus.jackson.map.ObjectMapper;
//application library
import no.systema.skat.z.maintenance.skatncts.model.jsonjackson.dbtable.JsonMaintDkxkodfContainer;
import no.systema.skat.z.maintenance.skatncts.model.jsonjackson.dbtable.JsonMaintDkxkodfRecord;
//
import java.util.*;

/**
 * @author oscardelatorre
 * @date Apr 10, 2017
 * 
 */
public class MaintDkxkodfMapper {
	private static final Logger logger = Logger.getLogger(MaintDkxkodfMapper.class.getName());
	
	public JsonMaintDkxkodfContainer getContainer(String utfPayload) throws Exception{
		ObjectMapper mapper = new ObjectMapper();  
		mapper.configure(DeserializationConfig.Feature.FAIL_ON_UNKNOWN_PROPERTIES,false);
		
		//At this point we now have an UTF-8 payload
		JsonMaintDkxkodfContainer container = mapper.readValue(utfPayload.getBytes(), JsonMaintDkxkodfContainer.class); 
		//logger.info("[JSON-String payload status=OK]  " + container.getUser());
		//DEBUG
		Collection<JsonMaintDkxkodfRecord> list = container.getList();
		for(JsonMaintDkxkodfRecord record : list){
			//logger.info(record.getKlikod());
		}
		return container;
	}
}
