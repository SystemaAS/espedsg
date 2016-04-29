/**
 * 
 */
package no.systema.tvinn.sad.z.maintenance.sadimport.mapper.jsonjackson.dbtable;

//jackson library
import org.apache.log4j.Logger;
import org.codehaus.jackson.map.DeserializationConfig;
import org.codehaus.jackson.map.ObjectMapper;
//application library
import no.systema.tvinn.sad.z.maintenance.sadimport.model.jsonjackson.dbtable.JsonMaintSadImportKodtlbContainer;
import no.systema.tvinn.sad.z.maintenance.sadimport.model.jsonjackson.dbtable.JsonMaintSadImportKodtlbRecord;
//
import java.util.*;

/**
 * @author oscardelatorre
 * @date Apr 29, 2016
 * 
 */
public class MaintSadImportKodtlbMapper {
	private static final Logger logger = Logger.getLogger(MaintSadImportKodtlbMapper.class.getName());
	
	public JsonMaintSadImportKodtlbContainer getContainer(String utfPayload) throws Exception{
		ObjectMapper mapper = new ObjectMapper();  
		mapper.configure(DeserializationConfig.Feature.FAIL_ON_UNKNOWN_PROPERTIES,false);
		
		//At this point we now have an UTF-8 payload
		JsonMaintSadImportKodtlbContainer container = mapper.readValue(utfPayload.getBytes(), JsonMaintSadImportKodtlbContainer.class); 
		//logger.info("[JSON-String payload status=OK]  " + container.getUser());
		//DEBUG
		Collection<JsonMaintSadImportKodtlbRecord> list = container.getList();
		for(JsonMaintSadImportKodtlbRecord record : list){
			//logger.info(record.getKlikod());
		}
		return container;
	}
}
