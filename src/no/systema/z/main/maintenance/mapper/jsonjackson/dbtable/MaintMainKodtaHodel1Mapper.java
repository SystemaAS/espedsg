/**
 * 
 */
package no.systema.z.main.maintenance.mapper.jsonjackson.dbtable;

//jackson library
import org.apache.log4j.Logger;
import org.codehaus.jackson.map.DeserializationConfig;
import org.codehaus.jackson.map.ObjectMapper;
//application library
import no.systema.z.main.maintenance.model.jsonjackson.dbtable.JsonMaintMainKodtaHodel1Container;
import no.systema.z.main.maintenance.model.jsonjackson.dbtable.JsonMaintMainKodtaHodel1Record;
//
import java.util.*;

/**
 * @author oscardelatorre
 * @date Aug 17, 2016
 * 
 */
public class MaintMainKodtaHodel1Mapper {
	private static final Logger logger = Logger.getLogger(MaintMainKodtaHodel1Mapper.class.getName());
	
	public JsonMaintMainKodtaHodel1Container getContainer(String utfPayload) throws Exception{
		ObjectMapper mapper = new ObjectMapper();  
		mapper.configure(DeserializationConfig.Feature.FAIL_ON_UNKNOWN_PROPERTIES,false);
		
		//At this point we now have an UTF-8 payload
		JsonMaintMainKodtaHodel1Container container = mapper.readValue(utfPayload.getBytes(), JsonMaintMainKodtaHodel1Container.class); 
		//logger.info("[JSON-String payload status=OK]  " + container.getUser());
		//DEBUG
		Collection<JsonMaintMainKodtaHodel1Record> list = container.getList();
		for(JsonMaintMainKodtaHodel1Record record : list){
			//logger.info(record.getKlikod());
		}
		return container;
	}
}
