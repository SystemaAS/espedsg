/**
 * 
 */
package no.systema.tvinn.sad.z.maintenance.sadimport.mapper.jsonjackson.dbtable.gyldigekoder;

//jackson library
import org.apache.log4j.Logger;
import org.codehaus.jackson.map.DeserializationConfig;
import org.codehaus.jackson.map.ObjectMapper;
//application library
import no.systema.tvinn.sad.z.maintenance.sadimport.model.jsonjackson.dbtable.gyldigekoder.JsonMaintSadImportKodts7Container;
import no.systema.tvinn.sad.z.maintenance.sadimport.model.jsonjackson.dbtable.gyldigekoder.JsonMaintSadImportKodts7Record;

//
import java.util.*;

/**
 * @author oscardelatorre
 * @date May 23, 2017
 * 
 */
public class MaintSadImportKodts7Mapper {
	private static final Logger logger = Logger.getLogger(MaintSadImportKodts7Mapper.class.getName());
	
	public JsonMaintSadImportKodts7Container getContainer(String utfPayload) throws Exception{
		ObjectMapper mapper = new ObjectMapper();  
		mapper.configure(DeserializationConfig.Feature.FAIL_ON_UNKNOWN_PROPERTIES,false);
		
		//At this point we now have an UTF-8 payload
		JsonMaintSadImportKodts7Container container = mapper.readValue(utfPayload.getBytes(), JsonMaintSadImportKodts7Container.class); 
		//logger.info("[JSON-String payload status=OK]  " + container.getUser());
		//DEBUG
		/*Collection<JsonMaintSadImportKodts7Record> list = container.getList();
		for(JsonMaintSadImportKodts7Record record : list){
			//logger.info(record.getKlikod());
		}*/
		return container;
	}
}
