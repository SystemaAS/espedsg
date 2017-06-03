/**
 * 
 */
package no.systema.tds.z.maintenance.main.mapper.jsonjackson.dbtable;

//jackson library
import org.apache.log4j.Logger;
import org.codehaus.jackson.map.DeserializationConfig;
import org.codehaus.jackson.map.ObjectMapper;

import no.systema.tds.z.maintenance.main.model.jsonjackson.dbtable.JsonMaintSvtlvContainer;
import no.systema.tds.z.maintenance.main.model.jsonjackson.dbtable.JsonMaintSvtlvRecord;

//
import java.util.*;

/**
 * @author oscardelatorre
 * @date Jun 03, 2017
 * 
 */
public class MaintSvtlvMapper {
	private static final Logger logger = Logger.getLogger(MaintSvtlvMapper.class.getName());
	
	public JsonMaintSvtlvContainer getContainer(String utfPayload) throws Exception{
		ObjectMapper mapper = new ObjectMapper();  
		mapper.configure(DeserializationConfig.Feature.FAIL_ON_UNKNOWN_PROPERTIES,false);
		
		//At this point we now have an UTF-8 payload
		JsonMaintSvtlvContainer container = mapper.readValue(utfPayload.getBytes(), JsonMaintSvtlvContainer.class); 
		
		return container;
	}
}
