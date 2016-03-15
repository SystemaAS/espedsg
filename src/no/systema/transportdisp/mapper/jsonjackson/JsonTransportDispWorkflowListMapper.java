/**
 * 
 */
package no.systema.transportdisp.mapper.jsonjackson;

//jackson library
import org.apache.log4j.Logger;
import org.codehaus.jackson.map.DeserializationConfig;
import org.codehaus.jackson.map.ObjectMapper;
//application library
import no.systema.transportdisp.model.jsonjackson.workflow.JsonTransportDispWorkflowListContainer;
import no.systema.transportdisp.model.jsonjackson.workflow.JsonTransportDispWorkflowListRecord;
import no.systema.transportdisp.model.jsonjackson.workflow.JsonTransportDispWorkflowListGodsAndLastListContainer;


/**
 * @author oscardelatorre
 * @date Mar 31, 2015
 * 
 */
public class JsonTransportDispWorkflowListMapper {
	private static final Logger logger = Logger.getLogger(JsonTransportDispWorkflowListMapper.class.getName());
	/**
	 * 
	 * @param utfPayload
	 * @return
	 * @throws Exception
	 */
	public JsonTransportDispWorkflowListContainer getContainer(String utfPayload) throws Exception{
		ObjectMapper mapper = new ObjectMapper();  
		mapper.configure(DeserializationConfig.Feature.FAIL_ON_UNKNOWN_PROPERTIES,false);
		//At this point we now have an UTF-8 payload
		JsonTransportDispWorkflowListContainer container = mapper.readValue(utfPayload.getBytes(), JsonTransportDispWorkflowListContainer.class); 
		logger.info("[JSON-String payload status=OK]  " + container.getUser());
		for (JsonTransportDispWorkflowListRecord record : container.getWrktriplist()){
			//DEBUG
		}
		return container;
	}
	
	/**
	 * Render build and render pdf
	 * @param utfPayload
	 * @return
	 * @throws Exception
	 */
	public JsonTransportDispWorkflowListGodsAndLastListContainer getGodsAndLastListContainer(String utfPayload) throws Exception{
		ObjectMapper mapper = new ObjectMapper();  
		mapper.configure(DeserializationConfig.Feature.FAIL_ON_UNKNOWN_PROPERTIES,false);
		//At this point we now have an UTF-8 payload
		JsonTransportDispWorkflowListGodsAndLastListContainer container = mapper.readValue(utfPayload.getBytes(), JsonTransportDispWorkflowListGodsAndLastListContainer.class); 
		logger.info("[JSON-String payload status=OK]  " + container.getUser());
		/*for (JsonTransportDispWorkflowListRecord record : container.getWrktriplist()){
			//DEBUG
		}*/
		return container;
	}
	
}
