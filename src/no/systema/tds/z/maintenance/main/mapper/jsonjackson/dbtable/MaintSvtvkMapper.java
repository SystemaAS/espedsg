/**
 * 
 */
package no.systema.tds.z.maintenance.main.mapper.jsonjackson.dbtable;

//jackson library
import org.apache.log4j.Logger;
import org.codehaus.jackson.map.DeserializationConfig;
import org.codehaus.jackson.map.ObjectMapper;

import no.systema.tds.z.maintenance.main.model.jsonjackson.dbtable.JsonMaintSvtvkContainer;
import no.systema.tds.z.maintenance.main.model.jsonjackson.dbtable.JsonMaintSvtvkRecord;

//
import java.util.*;

/**
 * @author oscardelatorre
 * @date May 19, 2017
 * 
 */
public class MaintSvtvkMapper {
	private static final Logger logger = Logger.getLogger(MaintSvtvkMapper.class.getName());
	
	public JsonMaintSvtvkContainer getContainer(String utfPayload) throws Exception{
		ObjectMapper mapper = new ObjectMapper();  
		mapper.configure(DeserializationConfig.Feature.FAIL_ON_UNKNOWN_PROPERTIES,false);
		
		//At this point we now have an UTF-8 payload
		JsonMaintSvtvkContainer container = mapper.readValue(utfPayload.getBytes(), JsonMaintSvtvkContainer.class); 
		
		return container;
	}
}
