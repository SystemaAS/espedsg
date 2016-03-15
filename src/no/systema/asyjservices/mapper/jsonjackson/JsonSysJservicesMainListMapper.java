/**
 * 
 */
package no.systema.asyjservices.mapper.jsonjackson;

//jackson library
import org.apache.log4j.Logger;
import org.codehaus.jackson.map.DeserializationConfig;
import org.codehaus.jackson.map.ObjectMapper;
//application library
import no.systema.asyjservices.model.jsonjackson.JsonSysJservicesMainListContainer;
import no.systema.asyjservices.model.jsonjackson.JsonSysJservicesMainListRecord;


/**
 * @author oscardelatorre
 * @date Nov 4, 2015
 * 
 */
public class JsonSysJservicesMainListMapper {
	private static final Logger logger = Logger.getLogger(JsonSysJservicesMainListMapper.class.getName());
	/**
	 * 
	 * @param utfPayload
	 * @return
	 * @throws Exception
	 */
	public JsonSysJservicesMainListContainer getContainer(String utfPayload) throws Exception{
		ObjectMapper mapper = new ObjectMapper();  
		mapper.configure(DeserializationConfig.Feature.FAIL_ON_UNKNOWN_PROPERTIES,false);
		//At this point we now have an UTF-8 payload
		JsonSysJservicesMainListContainer container = mapper.readValue(utfPayload.getBytes(), JsonSysJservicesMainListContainer.class); 
		logger.info("[JSON-String payload status=OK]  " + container.getUser());
		for (JsonSysJservicesMainListRecord record : container.getList()){
			//DEBUG
		}
		return container;
	}
	
}
