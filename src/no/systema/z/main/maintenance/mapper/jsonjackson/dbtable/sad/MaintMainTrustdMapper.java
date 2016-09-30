/**
 * 
 */
package no.systema.z.main.maintenance.mapper.jsonjackson.dbtable.sad;

//jackson library
import org.apache.log4j.Logger;
import org.codehaus.jackson.map.DeserializationConfig;
import org.codehaus.jackson.map.ObjectMapper;
//application library
import no.systema.z.main.maintenance.model.jsonjackson.dbtable.sad.JsonMaintMainTrustdContainer;
import no.systema.z.main.maintenance.model.jsonjackson.dbtable.sad.JsonMaintMainTrustdRecord;
//
import java.util.*;

/**
 * @author oscardelatorre
 * @date Sep 30, 2016
 * 
 */
public class MaintMainTrustdMapper {
	private static final Logger logger = Logger.getLogger(MaintMainTrustdMapper.class.getName());
	
	public JsonMaintMainTrustdContainer getContainer(String utfPayload) throws Exception{
		ObjectMapper mapper = new ObjectMapper();  
		mapper.configure(DeserializationConfig.Feature.FAIL_ON_UNKNOWN_PROPERTIES,false);
		
		//At this point we now have an UTF-8 payload
		JsonMaintMainTrustdContainer container = mapper.readValue(utfPayload.getBytes(), JsonMaintMainTrustdContainer.class); 
		//logger.info("[JSON-String payload status=OK]  " + container.getUser());
		//DEBUG
		Collection<JsonMaintMainTrustdRecord> list = container.getList();
		for(JsonMaintMainTrustdRecord record : list){
			//logger.info(record.getKlikod());
		}
		return container;
	}
}
