/**
 * 
 */
package no.systema.z.main.maintenance.mapper.jsonjackson.dbtable;

//jackson library
import org.apache.log4j.Logger;
import org.codehaus.jackson.map.DeserializationConfig;
import org.codehaus.jackson.map.ObjectMapper;
//application library
import no.systema.z.main.maintenance.model.jsonjackson.dbtable.JsonMaintMainKodtvKodtwContainer;
import no.systema.z.main.maintenance.model.jsonjackson.dbtable.JsonMaintMainKodtvKodtwRecord;
//
import java.util.*;

/**
 * @author oscardelatorre
 * @date Aug 5, 2016
 * 
 */
public class MaintMainKodtvKodtwMapper {
	private static final Logger logger = Logger.getLogger(MaintMainKodtvKodtwMapper.class.getName());
	
	public JsonMaintMainKodtvKodtwContainer getContainer(String utfPayload) throws Exception{
		ObjectMapper mapper = new ObjectMapper();  
		mapper.configure(DeserializationConfig.Feature.FAIL_ON_UNKNOWN_PROPERTIES,false);
		
		//At this point we now have an UTF-8 payload
		JsonMaintMainKodtvKodtwContainer container = mapper.readValue(utfPayload.getBytes(), JsonMaintMainKodtvKodtwContainer.class); 
		//logger.info("[JSON-String payload status=OK]  " + container.getUser());
		//DEBUG
		Collection<JsonMaintMainKodtvKodtwRecord> list = container.getList();
		for(JsonMaintMainKodtvKodtwRecord record : list){
			//logger.info(record.getKlikod());
		}
		return container;
	}
}
