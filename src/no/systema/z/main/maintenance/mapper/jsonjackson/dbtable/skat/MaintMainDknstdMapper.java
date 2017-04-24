/**
 * 
 */
package no.systema.z.main.maintenance.mapper.jsonjackson.dbtable.skat;

//jackson library
import org.apache.log4j.Logger;
import org.codehaus.jackson.map.DeserializationConfig;
import org.codehaus.jackson.map.ObjectMapper;
//application library
import no.systema.z.main.maintenance.model.jsonjackson.dbtable.skat.JsonMaintMainDknstdContainer;
import no.systema.z.main.maintenance.model.jsonjackson.dbtable.skat.JsonMaintMainDknstdRecord;
//
import java.util.*;

/**
 * @author oscardelatorre
 * @date Apr 24, 2017
 * 
 */
public class MaintMainDknstdMapper {
	private static final Logger logger = Logger.getLogger(MaintMainDknstdMapper.class.getName());
	
	public JsonMaintMainDknstdContainer getContainer(String utfPayload) throws Exception{
		ObjectMapper mapper = new ObjectMapper();  
		mapper.configure(DeserializationConfig.Feature.FAIL_ON_UNKNOWN_PROPERTIES,false);
		
		//At this point we now have an UTF-8 payload
		JsonMaintMainDknstdContainer container = mapper.readValue(utfPayload.getBytes(), JsonMaintMainDknstdContainer.class); 
		//logger.info("[JSON-String payload status=OK]  " + container.getUser());
		//DEBUG
		Collection<JsonMaintMainDknstdRecord> list = container.getList();
		for(JsonMaintMainDknstdRecord record : list){
			//logger.info(record.getKlikod());
		}
		return container;
	}
}
