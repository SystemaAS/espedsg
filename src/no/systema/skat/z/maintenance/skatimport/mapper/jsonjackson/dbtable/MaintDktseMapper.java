/**
 * 
 */
package no.systema.skat.z.maintenance.skatimport.mapper.jsonjackson.dbtable;

//jackson library
import org.apache.log4j.Logger;
import org.codehaus.jackson.map.DeserializationConfig;
import org.codehaus.jackson.map.ObjectMapper;

import no.systema.skat.z.maintenance.skatimport.model.jsonjackson.dbtable.JsonMaintDktseContainer;
import no.systema.skat.z.maintenance.skatimport.model.jsonjackson.dbtable.JsonMaintDktseRecord;

//
import java.util.*;

/**
 * @author oscardelatorre
 * @date Mar 06, 2017
 * 
 */
public class MaintDktseMapper {
	private static final Logger logger = Logger.getLogger(MaintDktseMapper.class.getName());
	
	public JsonMaintDktseContainer getContainer(String utfPayload) throws Exception{
		ObjectMapper mapper = new ObjectMapper();  
		mapper.configure(DeserializationConfig.Feature.FAIL_ON_UNKNOWN_PROPERTIES,false);
		
		//At this point we now have an UTF-8 payload
		JsonMaintDktseContainer container = mapper.readValue(utfPayload.getBytes(), JsonMaintDktseContainer.class); 
		logger.info("[JSON-String payload status=OK]  " + container.getUser());
		
		return container;
	}
}
