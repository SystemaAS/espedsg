/**
 * 
 */
package no.systema.transportdisp.mapper.jsonjackson;

//jackson library
import org.apache.log4j.Logger;
import org.codehaus.jackson.map.DeserializationConfig;
import org.codehaus.jackson.map.ObjectMapper;
//application library
import no.systema.transportdisp.model.jsonjackson.workflow.order.frisokvei.JsonTransportDispWorkflowSpecificOrderFrisokveiContainer;
import no.systema.transportdisp.model.jsonjackson.workflow.order.frisokvei.JsonTransportDispWorkflowSpecificOrderFrisokveiRecord;

/**
 * @author oscardelatorre
 * @date Jun 09, 2017
 * 
 */
public class JsonTransportDispWorkflowSpecificOrderFrisokveiMapper {
	private static final Logger logger = Logger.getLogger(JsonTransportDispWorkflowSpecificOrderFrisokveiMapper.class.getName());
	/**
	 * 
	 * @param utfPayload
	 * @return
	 * @throws Exception
	 */
	public JsonTransportDispWorkflowSpecificOrderFrisokveiContainer getContainer(String utfPayload) throws Exception{
		ObjectMapper mapper = new ObjectMapper();  
		mapper.configure(DeserializationConfig.Feature.FAIL_ON_UNKNOWN_PROPERTIES,false);
		//At this point we now have an UTF-8 payload
		JsonTransportDispWorkflowSpecificOrderFrisokveiContainer container = mapper.readValue(utfPayload.getBytes(), JsonTransportDispWorkflowSpecificOrderFrisokveiContainer.class); 
		//logger.info("[JSON-String payload status=OK]  " + container.getUser());
		if(container!=null && container.getAwblinelist()!=null){
			for (JsonTransportDispWorkflowSpecificOrderFrisokveiRecord record : container.getAwblinelist()){
				//DEBUG
			}
		}
		return container;
	}
	
}
