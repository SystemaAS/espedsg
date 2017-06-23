/**
 * 
 */
package no.systema.tds.z.maintenance.tdsnctsexport.mapper.jsonjackson.dbtable;

//jackson library
import org.apache.log4j.Logger;
import org.codehaus.jackson.map.DeserializationConfig;
import org.codehaus.jackson.map.ObjectMapper;
//application library
import no.systema.tds.z.maintenance.tdsnctsexport.model.jsonjackson.dbtable.JsonMaintSvxghContainer;
import no.systema.tds.z.maintenance.tdsnctsexport.model.jsonjackson.dbtable.JsonMaintSvxghRecord;
//
import java.util.*;

/**
 * @author oscardelatorre
 * @date Jun 23, 2017
 * 
 */
public class MaintSvxghMapper {
	private static final Logger logger = Logger.getLogger(MaintSvxghMapper.class.getName());
	
	public JsonMaintSvxghContainer getContainer(String utfPayload) throws Exception{
		ObjectMapper mapper = new ObjectMapper();  
		mapper.configure(DeserializationConfig.Feature.FAIL_ON_UNKNOWN_PROPERTIES,false);
		
		//At this point we now have an UTF-8 payload
		JsonMaintSvxghContainer container = mapper.readValue(utfPayload.getBytes(), JsonMaintSvxghContainer.class); 
		//logger.info("[JSON-String payload status=OK]  " + container.getUser());
		//DEBUG
		Collection<JsonMaintSvxghRecord> list = container.getList();
		for(JsonMaintSvxghRecord record : list){
			//logger.info(record.getKlikod());
		}
		return container;
	}
}
