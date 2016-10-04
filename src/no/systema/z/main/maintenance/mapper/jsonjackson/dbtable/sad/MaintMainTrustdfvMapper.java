/**
 * 
 */
package no.systema.z.main.maintenance.mapper.jsonjackson.dbtable.sad;

//jackson library
import org.apache.log4j.Logger;
import org.codehaus.jackson.map.DeserializationConfig;
import org.codehaus.jackson.map.ObjectMapper;
//application library
import no.systema.z.main.maintenance.model.jsonjackson.dbtable.sad.JsonMaintMainTrustdfvContainer;
import no.systema.z.main.maintenance.model.jsonjackson.dbtable.sad.JsonMaintMainTrustdfvRecord;
//
import java.util.*;

/**
 * @author oscardelatorre
 * @date Oct 4, 2016
 * 
 */
public class MaintMainTrustdfvMapper {
	private static final Logger logger = Logger.getLogger(MaintMainTrustdfvMapper.class.getName());
	
	public JsonMaintMainTrustdfvContainer getContainer(String utfPayload) throws Exception{
		ObjectMapper mapper = new ObjectMapper();  
		mapper.configure(DeserializationConfig.Feature.FAIL_ON_UNKNOWN_PROPERTIES,false);
		
		//At this point we now have an UTF-8 payload
		JsonMaintMainTrustdfvContainer container = mapper.readValue(utfPayload.getBytes(), JsonMaintMainTrustdfvContainer.class); 
		//logger.info("[JSON-String payload status=OK]  " + container.getUser());
		//DEBUG
		Collection<JsonMaintMainTrustdfvRecord> list = container.getList();
		for(JsonMaintMainTrustdfvRecord record : list){
			//logger.info(record.getKlikod());
		}
		return container;
	}
}
