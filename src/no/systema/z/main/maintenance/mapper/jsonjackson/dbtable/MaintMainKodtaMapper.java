/**
 * 
 */
package no.systema.z.main.maintenance.mapper.jsonjackson.dbtable;

//jackson library
import org.apache.log4j.Logger;
import org.codehaus.jackson.map.DeserializationConfig;
import org.codehaus.jackson.map.ObjectMapper;
//application library
import no.systema.z.main.maintenance.model.jsonjackson.dbtable.JsonMaintMainKodtaContainer;
import no.systema.z.main.maintenance.model.jsonjackson.dbtable.JsonMaintMainKodtaRecord;
//
import java.util.*;

/**
 * @author oscardelatorre
 * @date Aug 3, 2016
 * 
 */
public class MaintMainKodtaMapper {
	private static final Logger logger = Logger.getLogger(MaintMainKodtaMapper.class.getName());
	
	public JsonMaintMainKodtaContainer getContainer(String utfPayload) throws Exception{
		ObjectMapper mapper = new ObjectMapper();  
		mapper.configure(DeserializationConfig.Feature.FAIL_ON_UNKNOWN_PROPERTIES,false);
		
		//At this point we now have an UTF-8 payload
		JsonMaintMainKodtaContainer container = mapper.readValue(utfPayload.getBytes(), JsonMaintMainKodtaContainer.class); 
		//logger.info("[JSON-String payload status=OK]  " + container.getUser());
		//DEBUG
		Collection<JsonMaintMainKodtaRecord> list = container.getList();
		for(JsonMaintMainKodtaRecord record : list){
			//logger.info(record.getKlikod());
		}
		return container;
	}
}
