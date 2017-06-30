/**
 * 
 */
package no.systema.z.main.maintenance.mapper.jsonjackson.dbtable.tds;

//jackson library
import org.apache.log4j.Logger;
import org.codehaus.jackson.map.DeserializationConfig;
import org.codehaus.jackson.map.ObjectMapper;
//application library
import no.systema.z.main.maintenance.model.jsonjackson.dbtable.tds.JsonMaintMainSvnstdContainer;
import no.systema.z.main.maintenance.model.jsonjackson.dbtable.tds.JsonMaintMainSvnstdRecord;
//
import java.util.*;

/**
 * @author oscardelatorre
 * @date Jun 26, 2017
 * 
 */
public class MaintMainSvnstdMapper {
	private static final Logger logger = Logger.getLogger(MaintMainSvnstdMapper.class.getName());
	
	public JsonMaintMainSvnstdContainer getContainer(String utfPayload) throws Exception{
		ObjectMapper mapper = new ObjectMapper();  
		mapper.configure(DeserializationConfig.Feature.FAIL_ON_UNKNOWN_PROPERTIES,false);
		
		//At this point we now have an UTF-8 payload
		JsonMaintMainSvnstdContainer container = mapper.readValue(utfPayload.getBytes(), JsonMaintMainSvnstdContainer.class); 
		//logger.info("[JSON-String payload status=OK]  " + container.getUser());
		//DEBUG
		Collection<JsonMaintMainSvnstdRecord> list = container.getList();
		for(JsonMaintMainSvnstdRecord record : list){
			//logger.info(record.getKlikod());
		}
		return container;
	}
}
