/**
 * 
 */
package no.systema.tds.z.maintenance.tdsncts.mapper.jsonjackson.dbtable;

//jackson library
import org.apache.log4j.Logger;
import org.codehaus.jackson.map.DeserializationConfig;
import org.codehaus.jackson.map.ObjectMapper;
//application library
import no.systema.tds.z.maintenance.tdsncts.model.jsonjackson.dbtable.JsonMaintSvxkodfContainer;
import no.systema.tds.z.maintenance.tdsncts.model.jsonjackson.dbtable.JsonMaintSvxkodfRecord;
//
import java.util.*;

/**
 * @author oscardelatorre
 * @date Jun 22, 2017
 * 
 */
public class MaintSvxkodfMapper {
	private static final Logger logger = Logger.getLogger(MaintSvxkodfMapper.class.getName());
	
	public JsonMaintSvxkodfContainer getContainer(String utfPayload) throws Exception{
		ObjectMapper mapper = new ObjectMapper();  
		mapper.configure(DeserializationConfig.Feature.FAIL_ON_UNKNOWN_PROPERTIES,false);
		
		//At this point we now have an UTF-8 payload
		JsonMaintSvxkodfContainer container = mapper.readValue(utfPayload.getBytes(), JsonMaintSvxkodfContainer.class); 
		//logger.info("[JSON-String payload status=OK]  " + container.getUser());
		//DEBUG
		Collection<JsonMaintSvxkodfRecord> list = container.getList();
		for(JsonMaintSvxkodfRecord record : list){
			//logger.info(record.getKlikod());
		}
		return container;
	}
}
