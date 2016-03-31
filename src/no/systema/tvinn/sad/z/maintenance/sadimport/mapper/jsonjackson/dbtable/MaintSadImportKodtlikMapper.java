/**
 * 
 */
package no.systema.tvinn.sad.z.maintenance.sadimport.mapper.jsonjackson.dbtable;

//jackson library
import org.apache.log4j.Logger;
import org.codehaus.jackson.map.DeserializationConfig;
import org.codehaus.jackson.map.ObjectMapper;
//application library
import no.systema.tvinn.sad.z.maintenance.sadimport.model.jsonjackson.dbtable.JsonMaintSadImportKodtlikContainer;
import no.systema.tvinn.sad.z.maintenance.sadimport.model.jsonjackson.dbtable.JsonMaintSadImportKodtlikRecord;
//
import java.util.*;

/**
 * @author oscardelatorre
 * @date Mar 31, 2016
 * 
 */
public class MaintSadImportKodtlikMapper {
	private static final Logger logger = Logger.getLogger(MaintSadImportKodtlikMapper.class.getName());
	
	public JsonMaintSadImportKodtlikContainer getContainer(String utfPayload) throws Exception{
		ObjectMapper mapper = new ObjectMapper();  
		mapper.configure(DeserializationConfig.Feature.FAIL_ON_UNKNOWN_PROPERTIES,false);
		
		//At this point we now have an UTF-8 payload
		JsonMaintSadImportKodtlikContainer container = mapper.readValue(utfPayload.getBytes(), JsonMaintSadImportKodtlikContainer.class); 
		//logger.info(mapper.writeValueAsString(topicListContainer));
		logger.info("[JSON-String payload status=OK]  " + container.getUser());
		//DEBUG
		Collection<JsonMaintSadImportKodtlikRecord> list = container.getList();
		for(JsonMaintSadImportKodtlikRecord record : list){
			//logger.info("todo");
		}
		return container;
	}
}
