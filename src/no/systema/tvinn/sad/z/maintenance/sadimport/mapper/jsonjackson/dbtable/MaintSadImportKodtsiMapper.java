/**
 * 
 */
package no.systema.tvinn.sad.z.maintenance.sadimport.mapper.jsonjackson.dbtable;

//jackson library
import org.apache.log4j.Logger;
import org.codehaus.jackson.map.DeserializationConfig;
import org.codehaus.jackson.map.ObjectMapper;
//application library
import no.systema.tvinn.sad.z.maintenance.sadimport.model.jsonjackson.dbtable.JsonMaintSadImportKodtsiContainer;
import no.systema.tvinn.sad.z.maintenance.sadimport.model.jsonjackson.dbtable.JsonMaintSadImportKodtsiRecord;
//
import java.util.*;

/**
 * @author oscardelatorre
 * @date Apr 9, 2016
 * 
 */
public class MaintSadImportKodtsiMapper {
	private static final Logger logger = Logger.getLogger(MaintSadImportKodtsiMapper.class.getName());
	
	public JsonMaintSadImportKodtsiContainer getContainer(String utfPayload) throws Exception{
		ObjectMapper mapper = new ObjectMapper();  
		mapper.configure(DeserializationConfig.Feature.FAIL_ON_UNKNOWN_PROPERTIES,false);
		
		//At this point we now have an UTF-8 payload
		JsonMaintSadImportKodtsiContainer container = mapper.readValue(utfPayload.getBytes(), JsonMaintSadImportKodtsiContainer.class); 
		//logger.info("[JSON-String payload status=OK]  " + container.getUser());
		//DEBUG
		Collection<JsonMaintSadImportKodtsiRecord> list = container.getList();
		for(JsonMaintSadImportKodtsiRecord record : list){
			//logger.info(record.getKlikod());
		}
		return container;
	}
}
