/**
 * 
 */
package no.systema.transportdisp.mapper.jsonjackson;

//jackson library
import org.apache.log4j.Logger;
import org.codehaus.jackson.map.DeserializationConfig;
import org.codehaus.jackson.map.ObjectMapper;
//application library
import no.systema.transportdisp.model.jsonjackson.workflow.order.logging.JsonTransportDispWorkflowSpecificOrderLoggingContainer;
import no.systema.transportdisp.model.jsonjackson.workflow.order.logging.JsonTransportDispWorkflowSpecificOrderLoggingRecord;


/**
 * @author oscardelatorre
 * @date Aug 14, 2015
 * 
 */
public class JsonTransportDispWorkflowSpecificOrderLoggingMapper {
	private static final Logger logger = Logger.getLogger(JsonTransportDispWorkflowSpecificOrderLoggingMapper.class.getName());
	/**
	 * 
	 * @param utfPayload
	 * @return
	 * @throws Exception
	 */
	public JsonTransportDispWorkflowSpecificOrderLoggingContainer getContainer(String utfPayload) throws Exception{
		ObjectMapper mapper = new ObjectMapper();  
		mapper.configure(DeserializationConfig.Feature.FAIL_ON_UNKNOWN_PROPERTIES,false);
		//At this point we now have an UTF-8 payload
		JsonTransportDispWorkflowSpecificOrderLoggingContainer container = mapper.readValue(utfPayload.getBytes(), JsonTransportDispWorkflowSpecificOrderLoggingContainer.class); 
		//logger.info("[JSON-String payload status=OK]  " + container.getUser());
		for (JsonTransportDispWorkflowSpecificOrderLoggingRecord record : container.getTrackTraceEvents()){
			//DEBUG
		}
		return container;
	}
	
}
