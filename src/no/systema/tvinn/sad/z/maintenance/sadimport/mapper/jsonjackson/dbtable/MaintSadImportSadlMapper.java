/**
 * 
 */
package no.systema.tvinn.sad.z.maintenance.sadimport.mapper.jsonjackson.dbtable;

//jackson library
import org.apache.log4j.Logger;
import org.codehaus.jackson.map.DeserializationConfig;
import org.codehaus.jackson.map.ObjectMapper;
//application library
import no.systema.tvinn.sad.z.maintenance.sadimport.model.jsonjackson.dbtable.JsonMaintSadImportSadlContainer;
import no.systema.tvinn.sad.z.maintenance.sadimport.model.jsonjackson.dbtable.JsonMaintSadImportSadlRecord;
//
import java.util.*;

/**
 * @author oscardelatorre
 * @date May 31, 2016
 * 
 */
public class MaintSadImportSadlMapper {
	private static final Logger logger = Logger.getLogger(MaintSadImportSadlMapper.class.getName());
	
	public JsonMaintSadImportSadlContainer getContainer(String utfPayload) throws Exception{
		ObjectMapper mapper = new ObjectMapper();  
		mapper.configure(DeserializationConfig.Feature.FAIL_ON_UNKNOWN_PROPERTIES,false);
		
		//At this point we now have an UTF-8 payload
		JsonMaintSadImportSadlContainer container = mapper.readValue(utfPayload.getBytes(), JsonMaintSadImportSadlContainer.class); 
		//logger.info("[JSON-String payload status=OK]  " + container.getUser());
		//DEBUG
		Collection<JsonMaintSadImportSadlRecord> list = container.getList();
		for(JsonMaintSadImportSadlRecord record : list){
			//logger.info(record.getKlikod());
		}
		return container;
	}
}
