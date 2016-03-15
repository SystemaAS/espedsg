/**
 * 
 */
package no.systema.efaktura.mapper.jsonjackson;

//jackson library
import org.apache.log4j.Logger;
import org.codehaus.jackson.map.DeserializationConfig;
import org.codehaus.jackson.map.ObjectMapper;
//application library
import no.systema.efaktura.model.jsonjackson.JsonEfakturaMainListContainer;
import no.systema.efaktura.model.jsonjackson.JsonEfakturaMainListRecord;


/**
 * @author oscardelatorre
 * @date Jun 22, 2015
 * 
 */
public class JsonEfakturaMainListMapper {
	private static final Logger logger = Logger.getLogger(JsonEfakturaMainListMapper.class.getName());
	/**
	 * 
	 * @param utfPayload
	 * @return
	 * @throws Exception
	 */
	public JsonEfakturaMainListContainer getContainer(String utfPayload) throws Exception{
		ObjectMapper mapper = new ObjectMapper();  
		mapper.configure(DeserializationConfig.Feature.FAIL_ON_UNKNOWN_PROPERTIES,false);
		//At this point we now have an UTF-8 payload
		JsonEfakturaMainListContainer container = mapper.readValue(utfPayload.getBytes(), JsonEfakturaMainListContainer.class); 
		logger.info("[JSON-String payload status=OK]  " + container.getUser());
		for (JsonEfakturaMainListRecord record : container.getOrderList()){
			//DEBUG
		}
		return container;
	}
	
}
