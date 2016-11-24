/**
 * 
 */
package no.systema.z.main.maintenance.mapper.jsonjackson.dbtable;

//jackson library
import org.apache.log4j.Logger;
import org.codehaus.jackson.map.DeserializationConfig;
import org.codehaus.jackson.map.ObjectMapper;

import no.systema.z.main.maintenance.model.jsonjackson.dbtable.JsonMaintMainCundcContainer;

/**
 * @author Fredrik Möller
 * @date Nov 3, 2016
 * 
 */
public class MaintMainCundcMapper {
	private static final Logger logger = Logger.getLogger(MaintMainCundcMapper.class.getName());
	
	public JsonMaintMainCundcContainer getContainer(String utfPayload) throws Exception{
		ObjectMapper mapper = new ObjectMapper();  
		mapper.configure(DeserializationConfig.Feature.FAIL_ON_UNKNOWN_PROPERTIES,false);
		
		//At this point we now have an UTF-8 payload
		JsonMaintMainCundcContainer container = mapper.readValue(utfPayload.getBytes(), JsonMaintMainCundcContainer.class); 
		
		return container;
	}
}
