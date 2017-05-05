/**
 * 
 */
package no.systema.z.main.maintenance.mapper.jsonjackson.dbtable;

//jackson library
import org.apache.log4j.Logger;
import org.codehaus.jackson.map.DeserializationConfig;
import org.codehaus.jackson.map.ObjectMapper;
//application library
import no.systema.z.main.maintenance.model.jsonjackson.dbtable.JsonMaintMainQaokp08aContainer;
import no.systema.z.main.maintenance.model.jsonjackson.dbtable.JsonMaintMainQaokp08aRecord;
//
import java.util.*;

/**
 * @author oscardelatorre
 * @date May 05, 2017
 * 
 */
public class MaintMainQaokp08aMapper {
	private static final Logger logger = Logger.getLogger(MaintMainQaokp08aMapper.class.getName());
	
	public JsonMaintMainQaokp08aContainer getContainer(String utfPayload) throws Exception{
		ObjectMapper mapper = new ObjectMapper();  
		mapper.configure(DeserializationConfig.Feature.FAIL_ON_UNKNOWN_PROPERTIES,false);
		
		//At this point we now have an UTF-8 payload
		JsonMaintMainQaokp08aContainer container = mapper.readValue(utfPayload.getBytes(), JsonMaintMainQaokp08aContainer.class); 
		//logger.info("[JSON-String payload status=OK]  " + container.getUser());
		//DEBUG
		Collection<JsonMaintMainQaokp08aRecord> list = container.getList();
		for(JsonMaintMainQaokp08aRecord record : list){
			//logger.info(record.getKlikod());
		}
		return container;
	}
}
