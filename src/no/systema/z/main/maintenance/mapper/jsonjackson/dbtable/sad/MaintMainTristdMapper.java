/**
 * 
 */
package no.systema.z.main.maintenance.mapper.jsonjackson.dbtable.sad;

//jackson library
import org.apache.log4j.Logger;
import org.codehaus.jackson.map.DeserializationConfig;
import org.codehaus.jackson.map.ObjectMapper;
//application library
import no.systema.z.main.maintenance.model.jsonjackson.dbtable.sad.JsonMaintMainTristdContainer;
import no.systema.z.main.maintenance.model.jsonjackson.dbtable.sad.JsonMaintMainTristdRecord;
//
import java.util.*;

/**
 * @author oscardelatorre
 * @date Sep 20, 2016
 * 
 */
public class MaintMainTristdMapper {
	private static final Logger logger = Logger.getLogger(MaintMainTristdMapper.class.getName());
	
	public JsonMaintMainTristdContainer getContainer(String utfPayload) throws Exception{
		ObjectMapper mapper = new ObjectMapper();  
		mapper.configure(DeserializationConfig.Feature.FAIL_ON_UNKNOWN_PROPERTIES,false);
		
		//At this point we now have an UTF-8 payload
		JsonMaintMainTristdContainer container = mapper.readValue(utfPayload.getBytes(), JsonMaintMainTristdContainer.class); 
		//logger.info("[JSON-String payload status=OK]  " + container.getUser());
		//DEBUG
		Collection<JsonMaintMainTristdRecord> list = container.getList();
		for(JsonMaintMainTristdRecord record : list){
			//logger.info(record.getKlikod());
		}
		return container;
	}
}
