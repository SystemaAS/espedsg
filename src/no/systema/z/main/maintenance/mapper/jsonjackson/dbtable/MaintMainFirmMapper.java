/**
 * 
 */
package no.systema.z.main.maintenance.mapper.jsonjackson.dbtable;

//jackson library
import org.apache.log4j.Logger;
import org.codehaus.jackson.map.DeserializationConfig;
import org.codehaus.jackson.map.ObjectMapper;
//application library
import no.systema.z.main.maintenance.model.jsonjackson.dbtable.JsonMaintMainFirmContainer;
import no.systema.z.main.maintenance.model.jsonjackson.dbtable.JsonMaintMainFirmRecord;
//
import java.util.*;

/**
 * @author oscardelatorre
 * @date Aug 4, 2016
 * 
 */
public class MaintMainFirmMapper {
	private static final Logger logger = Logger.getLogger(MaintMainFirmMapper.class.getName());
	
	public JsonMaintMainFirmContainer getContainer(String utfPayload) throws Exception{
		ObjectMapper mapper = new ObjectMapper();  
		mapper.configure(DeserializationConfig.Feature.FAIL_ON_UNKNOWN_PROPERTIES,false);
		
		//At this point we now have an UTF-8 payload
		JsonMaintMainFirmContainer container = mapper.readValue(utfPayload.getBytes(), JsonMaintMainFirmContainer.class); 
		//logger.info("[JSON-String payload status=OK]  " + container.getUser());
		//DEBUG
		Collection<JsonMaintMainFirmRecord> list = container.getList();
		for(JsonMaintMainFirmRecord record : list){
			//logger.info(record.getKlikod());
		}
		return container;
	}
}
