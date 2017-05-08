/**
 * 
 */
package no.systema.tds.z.maintenance.main.mapper.jsonjackson.dbtable;

//jackson library
import org.apache.log4j.Logger;
import org.codehaus.jackson.map.DeserializationConfig;
import org.codehaus.jackson.map.ObjectMapper;

import no.systema.tds.z.maintenance.main.model.jsonjackson.dbtable.JsonMaintSvthaContainer;
import no.systema.tds.z.maintenance.main.model.jsonjackson.dbtable.JsonMaintSvthaRecord;

//
import java.util.*;

/**
 * @author oscardelatorre
 * @date May 08, 2017
 * 
 */
public class MaintSvthaMapper {
	private static final Logger logger = Logger.getLogger(MaintSvthaMapper.class.getName());
	
	public JsonMaintSvthaContainer getContainer(String utfPayload) throws Exception{
		ObjectMapper mapper = new ObjectMapper();  
		mapper.configure(DeserializationConfig.Feature.FAIL_ON_UNKNOWN_PROPERTIES,false);
		
		//At this point we now have an UTF-8 payload
		JsonMaintSvthaContainer container = mapper.readValue(utfPayload.getBytes(), JsonMaintSvthaContainer.class); 
		//logger.info("[JSON-String payload status=OK]  " + container.getUser());
		//DEBUG
		/*Collection<JsonMaintDktardRecord> list = container.getList();
		for(JsonMaintDktardRecord record : list){
			//logger.info(record.getKlikod());
		}*/
		return container;
	}
}
