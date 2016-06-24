/**
 * 
 */
package no.systema.ebooking.mapper.jsonjackson;

//jackson library
import org.apache.log4j.Logger;
import org.codehaus.jackson.map.DeserializationConfig;
import org.codehaus.jackson.map.ObjectMapper;
//application library
import no.systema.ebooking.model.jsonjackson.JsonMainOrderListContainer;
import no.systema.ebooking.model.jsonjackson.JsonMainOrderListRecord;


/**
 * @author oscardelatorre
 * @date Jun 24, 2016
 * 
 */
public class JsonOrderListMapper {
	private static final Logger logger = Logger.getLogger(JsonOrderListMapper.class.getName());
	/**
	 * 
	 * @param utfPayload
	 * @return
	 * @throws Exception
	 */
	public JsonMainOrderListContainer getContainer(String utfPayload) throws Exception{
		ObjectMapper mapper = new ObjectMapper();  
		mapper.configure(DeserializationConfig.Feature.FAIL_ON_UNKNOWN_PROPERTIES,false);
		//At this point we now have an UTF-8 payload
		JsonMainOrderListContainer container = mapper.readValue(utfPayload.getBytes(), JsonMainOrderListContainer.class); 
		logger.info("[JSON-String payload status=OK]  " + container.getUser());
		for (JsonMainOrderListRecord record : container.getOrderList()){
			//DEBUG
		}
		return container;
	}
	
}
