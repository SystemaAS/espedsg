/**
 * 
 */
package no.systema.tvinn.sad.z.maintenance.sadexport.mapper.jsonjackson.dbtable.gyldigekoder;

//jackson library
import org.apache.log4j.Logger;
import org.codehaus.jackson.map.DeserializationConfig;
import org.codehaus.jackson.map.ObjectMapper;
//application library
import no.systema.tvinn.sad.z.maintenance.sadexport.model.jsonjackson.dbtable.gyldigekoder.JsonMaintSadExportKodtscContainer;
import no.systema.tvinn.sad.z.maintenance.sadexport.model.jsonjackson.dbtable.gyldigekoder.JsonMaintSadExportKodtscRecord;

//
import java.util.*;

/**
 * @author oscardelatorre
 * @date Okt 26, 2016
 * 
 */
public class MaintSadExportKodtscMapper {
	private static final Logger logger = Logger.getLogger(MaintSadExportKodtscMapper.class.getName());
	
	public JsonMaintSadExportKodtscContainer getContainer(String utfPayload) throws Exception{
		ObjectMapper mapper = new ObjectMapper();  
		mapper.configure(DeserializationConfig.Feature.FAIL_ON_UNKNOWN_PROPERTIES,false);
		
		//At this point we now have an UTF-8 payload
		JsonMaintSadExportKodtscContainer container = mapper.readValue(utfPayload.getBytes(), JsonMaintSadExportKodtscContainer.class); 
		//logger.info("[JSON-String payload status=OK]  " + container.getUser());
		//DEBUG
		Collection<JsonMaintSadExportKodtscRecord> list = container.getList();
		for(JsonMaintSadExportKodtscRecord record : list){
			//logger.info(record.getKlikod());
		}
		return container;
	}
}
