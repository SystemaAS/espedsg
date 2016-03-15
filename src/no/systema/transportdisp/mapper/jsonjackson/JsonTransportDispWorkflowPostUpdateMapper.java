/**
 * 
 */
package no.systema.transportdisp.mapper.jsonjackson;

//jackson library
import org.apache.log4j.Logger;
import org.codehaus.jackson.map.DeserializationConfig;
import org.codehaus.jackson.map.ObjectMapper;
//application library
import no.systema.transportdisp.model.jsonjackson.workflow.JsonTransportDispWorkflowPostUpdateContainer;



/**
 * @author oscardelatorre
 * @date Sep 15, 2015
 * 
 */
public class JsonTransportDispWorkflowPostUpdateMapper {
	private static final Logger logger = Logger.getLogger(JsonTransportDispWorkflowPostUpdateMapper.class.getName());
	/**
	 * 
	 * @param utfPayload
	 * @return
	 * @throws Exception
	 */
	public JsonTransportDispWorkflowPostUpdateContainer getContainer(String utfPayload) throws Exception{
		ObjectMapper mapper = new ObjectMapper();  
		mapper.configure(DeserializationConfig.Feature.FAIL_ON_UNKNOWN_PROPERTIES,false);
		//At this point we now have an UTF-8 payload
		JsonTransportDispWorkflowPostUpdateContainer container = mapper.readValue(utfPayload.getBytes(), JsonTransportDispWorkflowPostUpdateContainer.class); 
		//logger.info("[JSON-String payload status=OK]  " + container.getUser());
		/*for (JsonTransportDispWorkflowSpecificOrderRecord record : container.getDspoppdrag()){
			//DEBUG
		}*/
		return container;
	}
	
}
