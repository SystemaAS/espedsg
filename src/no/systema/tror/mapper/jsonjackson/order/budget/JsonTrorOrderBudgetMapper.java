/**
 * 
 */
package no.systema.tror.mapper.jsonjackson.order.budget;

//jackson library
import org.apache.log4j.Logger;
import com.fasterxml.jackson.databind.DeserializationConfig;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper; 
//application library
import no.systema.tror.model.jsonjackson.budget.JsonTrorOrderHeaderBudgetContainer;
import no.systema.tror.model.jsonjackson.budget.JsonTrorOrderHeaderBudgetRecord;



/**
 * @author oscardelatorre
 * @date Sep 18, 2017
 * 
 */
public class JsonTrorOrderBudgetMapper {
	private static final Logger logger = Logger.getLogger(JsonTrorOrderBudgetMapper.class.getName());
	/**
	 * 
	 * @param utfPayload
	 * @return
	 * @throws Exception
	 */
	public JsonTrorOrderHeaderBudgetContainer getContainer(String utfPayload) throws Exception{
		ObjectMapper mapper = new ObjectMapper();  
		mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES,false);
		//At this point we now have an UTF-8 payload
		JsonTrorOrderHeaderBudgetContainer container = mapper.readValue(utfPayload.getBytes(), JsonTrorOrderHeaderBudgetContainer.class); 
		
		return container;
	}
	
}
