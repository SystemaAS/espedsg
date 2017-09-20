/**
 * 
 */
package no.systema.tror.mapper.jsonjackson.order.frisokvei;

//jackson library
import org.apache.log4j.Logger;
import org.codehaus.jackson.map.DeserializationConfig;
import org.codehaus.jackson.map.ObjectMapper;
//application library
import no.systema.tror.model.jsonjackson.frisokvei.JsonTrorOrderHeaderFrisokveiContainer;
import no.systema.tror.model.jsonjackson.frisokvei.JsonTrorOrderHeaderFrisokveiRecord;

/**
 * @author oscardelatorre
 * @date Sep 20, 2017
 * 
 */
public class JsonTrorOrderHeaderFrisokveiMapper {
	private static final Logger logger = Logger.getLogger(JsonTrorOrderHeaderFrisokveiMapper.class.getName());
	/**
	 * 
	 * @param utfPayload
	 * @return
	 * @throws Exception
	 */
	public JsonTrorOrderHeaderFrisokveiContainer getContainer(String utfPayload) throws Exception{
		ObjectMapper mapper = new ObjectMapper();  
		mapper.configure(DeserializationConfig.Feature.FAIL_ON_UNKNOWN_PROPERTIES,false);
		//At this point we now have an UTF-8 payload
		JsonTrorOrderHeaderFrisokveiContainer container = mapper.readValue(utfPayload.getBytes(), JsonTrorOrderHeaderFrisokveiContainer.class); 
		//logger.info("[JSON-String payload status=OK]  " + container.getUser());
		if(container!=null && container.getAwblinelist()!=null){
			for (JsonTrorOrderHeaderFrisokveiRecord record : container.getAwblinelist()){
				//DEBUG
			}
		}
		return container;
	}
	
}
