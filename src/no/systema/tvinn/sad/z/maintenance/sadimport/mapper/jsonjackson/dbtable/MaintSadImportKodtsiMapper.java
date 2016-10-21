/**
 * 
 */
package no.systema.tvinn.sad.z.maintenance.sadimport.mapper.jsonjackson.dbtable;

//jackson library
import org.apache.log4j.Logger;
import org.codehaus.jackson.map.DeserializationConfig;
import org.codehaus.jackson.map.ObjectMapper;
//application library
import no.systema.tvinn.sad.z.maintenance.felles.model.jsonjackson.dbtable.JsonMaintSadFellesKodtsiContainer;
import no.systema.tvinn.sad.z.maintenance.felles.model.jsonjackson.dbtable.JsonMaintSadFellesKodtsiRecord;
//
import java.util.*;

/**
 * @author oscardelatorre
 * @date Apr 9, 2016
 * 
 */
public class MaintSadImportKodtsiMapper {
	private static final Logger logger = Logger.getLogger(MaintSadImportKodtsiMapper.class.getName());
	
	public JsonMaintSadFellesKodtsiContainer getContainer(String utfPayload) throws Exception{
		ObjectMapper mapper = new ObjectMapper();  
		mapper.configure(DeserializationConfig.Feature.FAIL_ON_UNKNOWN_PROPERTIES,false);
		
		//At this point we now have an UTF-8 payload
		JsonMaintSadFellesKodtsiContainer container = mapper.readValue(utfPayload.getBytes(), JsonMaintSadFellesKodtsiContainer.class); 
		//logger.info("[JSON-String payload status=OK]  " + container.getUser());
		//DEBUG
		Collection<JsonMaintSadFellesKodtsiRecord> list = container.getList();
		for(JsonMaintSadFellesKodtsiRecord record : list){
			//logger.info(record.getKlikod());
		}
		return container;
	}
}
