/**
 * 
 */
package no.systema.tvinn.sad.z.maintenance.sadimport.mapper.jsonjackson.dbtable;

//jackson library
import org.apache.log4j.Logger;
import org.codehaus.jackson.map.DeserializationConfig;
import org.codehaus.jackson.map.ObjectMapper;
//application library
import no.systema.tvinn.sad.z.maintenance.sadimport.model.jsonjackson.dbtable.JsonMaintSadImportSadsdContainer;
import no.systema.tvinn.sad.z.maintenance.sadimport.model.jsonjackson.dbtable.JsonMaintSadImportSadsdRecord;
//
import java.util.*;

/**
 * @author oscardelatorre
 * @date May 12, 2016
 * 
 */
public class MaintSadImportSadsdMapper {
	private static final Logger logger = Logger.getLogger(MaintSadImportSadsdMapper.class.getName());
	
	public JsonMaintSadImportSadsdContainer getContainer(String utfPayload) throws Exception{
		ObjectMapper mapper = new ObjectMapper();  
		mapper.configure(DeserializationConfig.Feature.FAIL_ON_UNKNOWN_PROPERTIES,false);
		
		//At this point we now have an UTF-8 payload
		JsonMaintSadImportSadsdContainer container = mapper.readValue(utfPayload.getBytes(), JsonMaintSadImportSadsdContainer.class); 
		//logger.info("[JSON-String payload status=OK]  " + container.getUser());
		//DEBUG
		Collection<JsonMaintSadImportSadsdRecord> list = container.getList();
		for(JsonMaintSadImportSadsdRecord record : list){
			//logger.info(record.getKlikod());
		}
		return container;
	}
}
