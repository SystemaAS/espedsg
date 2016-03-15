/**
 * 
 */
package no.systema.transportdisp.mapper.jsonjackson;

//jackson library
import org.apache.log4j.Logger;
import org.codehaus.jackson.map.DeserializationConfig;
import org.codehaus.jackson.map.ObjectMapper;
//application library
import no.systema.transportdisp.model.jsonjackson.workflow.budget.JsonTransportDispWorkflowSpecificBudgetContainer;
import no.systema.transportdisp.model.jsonjackson.workflow.budget.JsonTransportDispWorkflowSpecificBudgetRecord;




/**
 * @author oscardelatorre
 * @date Oct 12, 2015
 * 
 */
public class JsonTransportDispWorkflowBudgetMapper {
	private static final Logger logger = Logger.getLogger(JsonTransportDispWorkflowBudgetMapper.class.getName());
	/**
	 * 
	 * @param utfPayload
	 * @return
	 * @throws Exception
	 */
	public JsonTransportDispWorkflowSpecificBudgetContainer getContainer(String utfPayload) throws Exception{
		ObjectMapper mapper = new ObjectMapper();  
		mapper.configure(DeserializationConfig.Feature.FAIL_ON_UNKNOWN_PROPERTIES,false);
		//At this point we now have an UTF-8 payload
		JsonTransportDispWorkflowSpecificBudgetContainer container = mapper.readValue(utfPayload.getBytes(), JsonTransportDispWorkflowSpecificBudgetContainer.class); 
		
		return container;
	}
	
}
