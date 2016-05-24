/**
 * 
 */
package no.systema.tvinn.sad.z.maintenance.sadimport.mapper.jsonjackson.dbtable.gyldigekoder;

//jackson library
import org.apache.log4j.Logger;
import org.codehaus.jackson.map.DeserializationConfig;
import org.codehaus.jackson.map.ObjectMapper;
//application library
import no.systema.tvinn.sad.z.maintenance.sadimport.model.jsonjackson.dbtable.gyldigekoder.JsonMaintSadImportKodtseContainer;
import no.systema.tvinn.sad.z.maintenance.sadimport.model.jsonjackson.dbtable.gyldigekoder.JsonMaintSadImportKodtseRecord;

//
import java.util.*;

/**
 * @author oscardelatorre
 * @date May 24, 2017
 * 
 */
public class MaintSadImportKodtseMapper {
	private static final Logger logger = Logger.getLogger(MaintSadImportKodtseMapper.class.getName());
	
	public JsonMaintSadImportKodtseContainer getContainer(String utfPayload) throws Exception{
		ObjectMapper mapper = new ObjectMapper();  
		mapper.configure(DeserializationConfig.Feature.FAIL_ON_UNKNOWN_PROPERTIES,false);
		
		//At this point we now have an UTF-8 payload
		JsonMaintSadImportKodtseContainer container = mapper.readValue(utfPayload.getBytes(), JsonMaintSadImportKodtseContainer.class); 
		
		return container;
	}
}
