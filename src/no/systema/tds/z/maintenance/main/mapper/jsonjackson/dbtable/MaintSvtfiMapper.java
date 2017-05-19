/**
 * 
 */
package no.systema.tds.z.maintenance.main.mapper.jsonjackson.dbtable;

//jackson library
import org.apache.log4j.Logger;
import org.codehaus.jackson.map.DeserializationConfig;
import org.codehaus.jackson.map.ObjectMapper;

import no.systema.tds.z.maintenance.main.model.jsonjackson.dbtable.JsonMaintSvtfiContainer;
import no.systema.tds.z.maintenance.main.model.jsonjackson.dbtable.JsonMaintSvtfiRecord;

//
import java.util.*;

/**
 * @author oscardelatorre
 * @date May 03, 2017
 * 
 */
public class MaintSvtfiMapper {
	private static final Logger logger = Logger.getLogger(MaintSvtfiMapper.class.getName());
	
	public JsonMaintSvtfiContainer getContainer(String utfPayload) throws Exception{
		ObjectMapper mapper = new ObjectMapper();  
		mapper.configure(DeserializationConfig.Feature.FAIL_ON_UNKNOWN_PROPERTIES,false);
		
		//At this point we now have an UTF-8 payload
		JsonMaintSvtfiContainer container = mapper.readValue(utfPayload.getBytes(), JsonMaintSvtfiContainer.class); 
		
		return container;
	}
}
