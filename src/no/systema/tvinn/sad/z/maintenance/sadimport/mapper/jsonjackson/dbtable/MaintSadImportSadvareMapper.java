/**
 * 
 */
package no.systema.tvinn.sad.z.maintenance.sadimport.mapper.jsonjackson.dbtable;

//jackson library
import org.apache.log4j.Logger;
import org.codehaus.jackson.map.DeserializationConfig;
import org.codehaus.jackson.map.ObjectMapper;
//application library
import no.systema.tvinn.sad.z.maintenance.sadimport.model.jsonjackson.dbtable.JsonMaintSadImportSadvareContainer;
import no.systema.tvinn.sad.z.maintenance.sadimport.model.jsonjackson.dbtable.JsonMaintSadImportSadvareRecord;
//
import java.util.*;

/**
 * @author oscardelatorre
 * @date May 27, 2016
 * 
 */
public class MaintSadImportSadvareMapper {
	private static final Logger logger = Logger.getLogger(MaintSadImportSadvareMapper.class.getName());
	
	public JsonMaintSadImportSadvareContainer getContainer(String utfPayload) throws Exception{
		ObjectMapper mapper = new ObjectMapper();  
		mapper.configure(DeserializationConfig.Feature.FAIL_ON_UNKNOWN_PROPERTIES,false);
		
		//At this point we now have an UTF-8 payload
		JsonMaintSadImportSadvareContainer container = mapper.readValue(utfPayload.getBytes(), JsonMaintSadImportSadvareContainer.class); 
		//logger.info("[JSON-String payload status=OK]  " + container.getUser());
		//DEBUG
		Collection<JsonMaintSadImportSadvareRecord> list = container.getList();
		for(JsonMaintSadImportSadvareRecord record : list){
			//logger.info(record.getKlikod());
		}
		return container;
	}
}
