/**
 * 
 */
package no.systema.z.main.maintenance.mapper.jsonjackson.dbtable.sad;

//jackson library
import org.apache.log4j.Logger;
import org.codehaus.jackson.map.DeserializationConfig;
import org.codehaus.jackson.map.ObjectMapper;
//application library
import no.systema.z.main.maintenance.model.jsonjackson.dbtable.sad.JsonMaintMainTrkodl01Container;
import no.systema.z.main.maintenance.model.jsonjackson.dbtable.sad.JsonMaintMainTrkodl01Record;
//
import java.util.*;

/**
 * @author oscardelatorre
 * @date Sep 20, 2016
 * 
 */
public class MaintMainTrkodl01Mapper {
	private static final Logger logger = Logger.getLogger(MaintMainTrkodl01Mapper.class.getName());
	
	public JsonMaintMainTrkodl01Container getContainer(String utfPayload) throws Exception{
		ObjectMapper mapper = new ObjectMapper();  
		mapper.configure(DeserializationConfig.Feature.FAIL_ON_UNKNOWN_PROPERTIES,false);
		
		//At this point we now have an UTF-8 payload
		JsonMaintMainTrkodl01Container container = mapper.readValue(utfPayload.getBytes(), JsonMaintMainTrkodl01Container.class); 
		//logger.info("[JSON-String payload status=OK]  " + container.getUser());
		//DEBUG
		Collection<JsonMaintMainTrkodl01Record> list = container.getList();
		for(JsonMaintMainTrkodl01Record record : list){
			//logger.info(record.getKlikod());
		}
		return container;
	}
}
