/**
 * 
 */
package no.systema.z.main.maintenance.mapper.jsonjackson.dbtable.skat;

//jackson library
import org.apache.log4j.Logger;
import org.codehaus.jackson.map.DeserializationConfig;
import org.codehaus.jackson.map.ObjectMapper;
//application library
import no.systema.z.main.maintenance.model.jsonjackson.dbtable.skat.JsonMaintMainDkeaContainer;
import no.systema.z.main.maintenance.model.jsonjackson.dbtable.skat.JsonMaintMainDkeaRecord;
//
import java.util.*;

/**
 * @author oscardelatorre
 * @date Apr 26, 2017
 * 
 */
public class MaintMainDkeaMapper {
	private static final Logger logger = Logger.getLogger(MaintMainDkeaMapper.class.getName());
	
	public JsonMaintMainDkeaContainer getContainer(String utfPayload) throws Exception{
		ObjectMapper mapper = new ObjectMapper();  
		mapper.configure(DeserializationConfig.Feature.FAIL_ON_UNKNOWN_PROPERTIES,false);
		
		//At this point we now have an UTF-8 payload
		JsonMaintMainDkeaContainer container = mapper.readValue(utfPayload.getBytes(), JsonMaintMainDkeaContainer.class); 
		//logger.info("[JSON-String payload status=OK]  " + container.getUser());
		//DEBUG
		Collection<JsonMaintMainDkeaRecord> list = container.getList();
		for(JsonMaintMainDkeaRecord record : list){
			//logger.info(record.getKlikod());
		}
		return container;
	}
}
