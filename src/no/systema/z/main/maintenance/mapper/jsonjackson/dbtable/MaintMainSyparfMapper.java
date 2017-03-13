/**
 * 
 */
package no.systema.z.main.maintenance.mapper.jsonjackson.dbtable;

//jackson library
import org.apache.log4j.Logger;
import org.codehaus.jackson.map.DeserializationConfig;
import org.codehaus.jackson.map.ObjectMapper;

import no.systema.z.main.maintenance.model.jsonjackson.dbtable.JsonMaintMainSyparfContainer;

/**
 * @author Fredrik Möller
 * @date Nov 3, 2016
 * 
 */
public class MaintMainSyparfMapper {
	private static final Logger logger = Logger.getLogger(MaintMainSyparfMapper.class.getName());
	
	public JsonMaintMainSyparfContainer getContainer(String utfPayload) throws Exception{
		ObjectMapper mapper = new ObjectMapper();  
		mapper.configure(DeserializationConfig.Feature.FAIL_ON_UNKNOWN_PROPERTIES,false);
		
		//At this point we now have an UTF-8 payload
		JsonMaintMainSyparfContainer container = mapper.readValue(utfPayload.getBytes(), JsonMaintMainSyparfContainer.class); 
		
		return container;
	}
}
