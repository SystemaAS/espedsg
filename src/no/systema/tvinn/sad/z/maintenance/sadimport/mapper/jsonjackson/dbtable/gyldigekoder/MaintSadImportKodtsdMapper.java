/**
 * 
 */
package no.systema.tvinn.sad.z.maintenance.sadimport.mapper.jsonjackson.dbtable.gyldigekoder;

//jackson library
import org.apache.log4j.Logger;
import org.codehaus.jackson.map.DeserializationConfig;
import org.codehaus.jackson.map.ObjectMapper;
//application library
import no.systema.tvinn.sad.z.maintenance.sadimport.model.jsonjackson.dbtable.gyldigekoder.JsonMaintSadImportKodtsdContainer;
import no.systema.tvinn.sad.z.maintenance.sadimport.model.jsonjackson.dbtable.gyldigekoder.JsonMaintSadImportKodtsdRecord;

//
import java.util.*;

/**
 * @author oscardelatorre
 * @date May 24, 2017
 * 
 */
public class MaintSadImportKodtsdMapper {
	private static final Logger logger = Logger.getLogger(MaintSadImportKodtsdMapper.class.getName());
	
	public JsonMaintSadImportKodtsdContainer getContainer(String utfPayload) throws Exception{
		ObjectMapper mapper = new ObjectMapper();  
		mapper.configure(DeserializationConfig.Feature.FAIL_ON_UNKNOWN_PROPERTIES,false);
		
		//At this point we now have an UTF-8 payload
		JsonMaintSadImportKodtsdContainer container = mapper.readValue(utfPayload.getBytes(), JsonMaintSadImportKodtsdContainer.class); 
		
		return container;
	}
}
