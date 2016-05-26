/**
 * 
 */
package no.systema.tvinn.sad.z.maintenance.sadimport.mapper.jsonjackson.dbtable;

//jackson library
import org.apache.log4j.Logger;
import org.codehaus.jackson.map.DeserializationConfig;
import org.codehaus.jackson.map.ObjectMapper;
//application library
import no.systema.tvinn.sad.z.maintenance.sadimport.model.jsonjackson.dbtable.JsonMaintSadImportSoktariContainer;
import no.systema.tvinn.sad.z.maintenance.sadimport.model.jsonjackson.dbtable.JsonMaintSadImportSoktariRecord;
//
import java.util.*;

/**
 * @author oscardelatorre
 * @date May 26, 2016
 * 
 */
public class MaintSadImportSoktariMapper {
	private static final Logger logger = Logger.getLogger(MaintSadImportSoktariMapper.class.getName());
	
	public JsonMaintSadImportSoktariContainer getContainer(String utfPayload) throws Exception{
		ObjectMapper mapper = new ObjectMapper();  
		mapper.configure(DeserializationConfig.Feature.FAIL_ON_UNKNOWN_PROPERTIES,false);
		
		//At this point we now have an UTF-8 payload
		JsonMaintSadImportSoktariContainer container = mapper.readValue(utfPayload.getBytes(), JsonMaintSadImportSoktariContainer.class); 
		//logger.info("[JSON-String payload status=OK]  " + container.getUser());
		//DEBUG
		Collection<JsonMaintSadImportSoktariRecord> list = container.getList();
		for(JsonMaintSadImportSoktariRecord record : list){
			//logger.info(record.getKlikod());
		}
		return container;
	}
}
