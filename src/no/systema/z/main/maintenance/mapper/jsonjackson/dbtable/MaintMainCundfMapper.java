/**
 * 
 */
package no.systema.z.main.maintenance.mapper.jsonjackson.dbtable;

//jackson library
import org.apache.log4j.Logger;
import org.codehaus.jackson.map.DeserializationConfig;
import org.codehaus.jackson.map.ObjectMapper;
//application library
import no.systema.z.main.maintenance.model.jsonjackson.dbtable.JsonMaintMainCundfContainer;
import no.systema.z.main.maintenance.model.jsonjackson.dbtable.JsonMaintMainCundfRecord;
//
import java.util.*;

/**
 * @author oscardelatorre
 * @date Aug 15, 2016
 * 
 */
public class MaintMainCundfMapper {
	private static final Logger logger = Logger.getLogger(MaintMainCundfMapper.class.getName());
	
	public JsonMaintMainCundfContainer getContainer(String utfPayload) throws Exception{
		ObjectMapper mapper = new ObjectMapper();  
		mapper.configure(DeserializationConfig.Feature.FAIL_ON_UNKNOWN_PROPERTIES,false);
		
		//At this point we now have an UTF-8 payload
		JsonMaintMainCundfContainer container = mapper.readValue(utfPayload.getBytes(), JsonMaintMainCundfContainer.class); 
		//logger.info("[JSON-String payload status=OK]  " + container.getUser());
		//DEBUG
		Collection<JsonMaintMainCundfRecord> list = container.getList();
		for(JsonMaintMainCundfRecord record : list){
			//logger.info(record.getKlikod());
		}
		return container;
	}
}
