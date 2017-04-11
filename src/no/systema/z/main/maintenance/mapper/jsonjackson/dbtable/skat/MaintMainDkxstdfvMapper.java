/**
 * 
 */
package no.systema.z.main.maintenance.mapper.jsonjackson.dbtable.skat;

//jackson library
import org.apache.log4j.Logger;
import org.codehaus.jackson.map.DeserializationConfig;
import org.codehaus.jackson.map.ObjectMapper;
//application library
import no.systema.z.main.maintenance.model.jsonjackson.dbtable.skat.JsonMaintMainDkxstdfvContainer;
import no.systema.z.main.maintenance.model.jsonjackson.dbtable.skat.JsonMaintMainDkxstdfvRecord;
//
import java.util.*;

/**
 * @author oscardelatorre
 * @date Apr 11, 2017
 * 
 */
public class MaintMainDkxstdfvMapper {
	private static final Logger logger = Logger.getLogger(MaintMainDkxstdfvMapper.class.getName());
	
	public JsonMaintMainDkxstdfvContainer getContainer(String utfPayload) throws Exception{
		ObjectMapper mapper = new ObjectMapper();  
		mapper.configure(DeserializationConfig.Feature.FAIL_ON_UNKNOWN_PROPERTIES,false);
		
		//At this point we now have an UTF-8 payload
		JsonMaintMainDkxstdfvContainer container = mapper.readValue(utfPayload.getBytes(), JsonMaintMainDkxstdfvContainer.class); 
		//logger.info("[JSON-String payload status=OK]  " + container.getUser());
		//DEBUG
		Collection<JsonMaintMainDkxstdfvRecord> list = container.getList();
		for(JsonMaintMainDkxstdfvRecord record : list){
			//logger.info(record.getKlikod());
		}
		return container;
	}
}
