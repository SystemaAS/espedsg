/**
 * 
 */
package no.systema.z.main.maintenance.mapper.jsonjackson.dbtable.tds;

//jackson library
import org.apache.log4j.Logger;
import org.codehaus.jackson.map.DeserializationConfig;
import org.codehaus.jackson.map.ObjectMapper;
//application library
import no.systema.z.main.maintenance.model.jsonjackson.dbtable.tds.JsonMaintMainSviaContainer;
import no.systema.z.main.maintenance.model.jsonjackson.dbtable.tds.JsonMaintMainSviaRecord;
//
import java.util.*;

/**
 * @author oscardelatorre
 * @date Jun 13, 2017
 * 
 */
public class MaintMainSviaMapper {
	private static final Logger logger = Logger.getLogger(MaintMainSviaMapper.class.getName());
	
	public JsonMaintMainSviaContainer getContainer(String utfPayload) throws Exception{
		ObjectMapper mapper = new ObjectMapper();  
		mapper.configure(DeserializationConfig.Feature.FAIL_ON_UNKNOWN_PROPERTIES,false);
		
		//At this point we now have an UTF-8 payload
		JsonMaintMainSviaContainer container = mapper.readValue(utfPayload.getBytes(), JsonMaintMainSviaContainer.class); 
		//logger.info("[JSON-String payload status=OK]  " + container.getUser());
		//DEBUG
		Collection<JsonMaintMainSviaRecord> list = container.getList();
		for(JsonMaintMainSviaRecord record : list){
			//logger.info(record.getKlikod());
		}
		return container;
	}
}
