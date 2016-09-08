/**
 * 
 */
package no.systema.tvinn.sad.z.maintenance.sadexport.mapper.jsonjackson.dbtable;

import org.codehaus.jackson.map.DeserializationConfig;
import org.codehaus.jackson.map.ObjectMapper;

import no.systema.tvinn.sad.z.maintenance.sadexport.model.jsonjackson.dbtable.JsonMaintSadExportKodts6Container;

/**
 * @author Fredrik Möller
 * @date Aug 16, 2016
 * 
 */
public class MaintSadExportKodts6Mapper {
	
	public JsonMaintSadExportKodts6Container getContainer(String utfPayload) throws Exception{
		ObjectMapper mapper = new ObjectMapper();  
		mapper.configure(DeserializationConfig.Feature.FAIL_ON_UNKNOWN_PROPERTIES,false);
		
		//At this point we now have an UTF-8 payload
		JsonMaintSadExportKodts6Container container = mapper.readValue(utfPayload.getBytes(), JsonMaintSadExportKodts6Container.class); 
		return container;
	}
}
