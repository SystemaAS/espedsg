/**
 * 
 */
package no.systema.z.main.maintenance.mapper.jsonjackson.dbtable.sad;

//jackson library
import org.apache.log4j.Logger;
import org.codehaus.jackson.map.DeserializationConfig;
import org.codehaus.jackson.map.ObjectMapper;
//application library
import no.systema.z.main.maintenance.model.jsonjackson.dbtable.sad.JsonMaintMainTrkodfContainer;
import no.systema.z.main.maintenance.model.jsonjackson.dbtable.sad.JsonMaintMainTrkodfRecord;
//
import java.util.*;

/**
 * @author oscardelatorre
 * @date Sep 20, 2016
 * 
 */
public class MaintMainTrkodfMapper {
	private static final Logger logger = Logger.getLogger(MaintMainTrkodfMapper.class.getName());
	
	public JsonMaintMainTrkodfContainer getContainer(String utfPayload) throws Exception{
		ObjectMapper mapper = new ObjectMapper();  
		mapper.configure(DeserializationConfig.Feature.FAIL_ON_UNKNOWN_PROPERTIES,false);
		
		//At this point we now have an UTF-8 payload
		JsonMaintMainTrkodfContainer container = mapper.readValue(utfPayload.getBytes(), JsonMaintMainTrkodfContainer.class); 
		//logger.info("[JSON-String payload status=OK]  " + container.getUser());
		//DEBUG
		Collection<JsonMaintMainTrkodfRecord> list = container.getList();
		for(JsonMaintMainTrkodfRecord record : list){
			//logger.info(record.getKlikod());
		}
		return container;
	}
}
