/**
 * 
 */
package no.systema.z.main.maintenance.mapper.jsonjackson.dbtable.sad;

//jackson library
import org.apache.log4j.Logger;
import org.codehaus.jackson.map.DeserializationConfig;
import org.codehaus.jackson.map.ObjectMapper;
//application library
import no.systema.z.main.maintenance.model.jsonjackson.dbtable.sad.JsonMaintMainStandeContainer;
import no.systema.z.main.maintenance.model.jsonjackson.dbtable.sad.JsonMaintMainStandeRecord;
//
import java.util.*;

/**
 * @author oscardelatorre
 * @date Sep 16, 2016
 * 
 */
public class MaintMainStandeMapper {
	private static final Logger logger = Logger.getLogger(MaintMainStandeMapper.class.getName());
	
	public JsonMaintMainStandeContainer getContainer(String utfPayload) throws Exception{
		ObjectMapper mapper = new ObjectMapper();  
		mapper.configure(DeserializationConfig.Feature.FAIL_ON_UNKNOWN_PROPERTIES,false);
		
		//At this point we now have an UTF-8 payload
		JsonMaintMainStandeContainer container = mapper.readValue(utfPayload.getBytes(), JsonMaintMainStandeContainer.class); 
		//logger.info("[JSON-String payload status=OK]  " + container.getUser());
		//DEBUG
		Collection<JsonMaintMainStandeRecord> list = container.getList();
		for(JsonMaintMainStandeRecord record : list){
			//logger.info(record.getKlikod());
		}
		return container;
	}
}
