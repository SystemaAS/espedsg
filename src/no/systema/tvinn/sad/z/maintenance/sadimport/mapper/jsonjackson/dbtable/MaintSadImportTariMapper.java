/**
 * 
 */
package no.systema.tvinn.sad.z.maintenance.sadimport.mapper.jsonjackson.dbtable;

//jackson library
import org.apache.log4j.Logger;
import org.codehaus.jackson.map.DeserializationConfig;
import org.codehaus.jackson.map.ObjectMapper;
//application library
import no.systema.tvinn.sad.z.maintenance.sadimport.model.jsonjackson.dbtable.JsonMaintSadImportTariContainer;
import no.systema.tvinn.sad.z.maintenance.sadimport.model.jsonjackson.dbtable.JsonMaintSadImportTariRecord;
//
import java.util.*;

/**
 * @author oscardelatorre
 * @date May 6, 2016
 * 
 */
public class MaintSadImportTariMapper {
	private static final Logger logger = Logger.getLogger(MaintSadImportTariMapper.class.getName());
	
	public JsonMaintSadImportTariContainer getContainer(String utfPayload) throws Exception{
		ObjectMapper mapper = new ObjectMapper();  
		mapper.configure(DeserializationConfig.Feature.FAIL_ON_UNKNOWN_PROPERTIES,false);
		
		//At this point we now have an UTF-8 payload
		JsonMaintSadImportTariContainer container = mapper.readValue(utfPayload.getBytes(), JsonMaintSadImportTariContainer.class); 
		//logger.info("[JSON-String payload status=OK]  " + container.getUser());
		//DEBUG
		Collection<JsonMaintSadImportTariRecord> list = container.getList();
		for(JsonMaintSadImportTariRecord record : list){
			//logger.info(record.getKlikod());
		}
		return container;
	}
}
