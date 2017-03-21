/**
 * 
 */
package no.systema.skat.z.maintenance.main.mapper.jsonjackson.dbtable;

//jackson library
import org.apache.log4j.Logger;
import org.codehaus.jackson.map.DeserializationConfig;
import org.codehaus.jackson.map.ObjectMapper;

import no.systema.skat.z.maintenance.main.model.jsonjackson.dbtable.JsonMaintDkthaContainer;


/**
 * @author oscardelatorre
 * @date Mar 21, 2017
 * 
 */
public class MaintDkthaMapper {
	private static final Logger logger = Logger.getLogger(MaintDkthaMapper.class.getName());
	
	public JsonMaintDkthaContainer getContainer(String utfPayload) throws Exception{
		ObjectMapper mapper = new ObjectMapper();  
		mapper.configure(DeserializationConfig.Feature.FAIL_ON_UNKNOWN_PROPERTIES,false);
		
		//At this point we now have an UTF-8 payload
		JsonMaintDkthaContainer container = mapper.readValue(utfPayload.getBytes(), JsonMaintDkthaContainer.class); 
		//logger.info("[JSON-String payload status=OK]  " + container.getUser());
		//DEBUG
		/*Collection<JsonMaintDktardRecord> list = container.getList();
		for(JsonMaintDktardRecord record : list){
			//logger.info(record.getKlikod());
		}*/
		return container;
	}
}
