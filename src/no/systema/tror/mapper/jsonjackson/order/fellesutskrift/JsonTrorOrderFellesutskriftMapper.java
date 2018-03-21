/**
 * 
 */
package no.systema.tror.mapper.jsonjackson.order.fellesutskrift;

//jackson library
import org.apache.log4j.Logger;
import com.fasterxml.jackson.databind.DeserializationConfig;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper; 
//application library
import no.systema.tror.model.jsonjackson.fellesutskrift.JsonTrorOrderFellesutskriftContainer;
import no.systema.tror.model.jsonjackson.fellesutskrift.JsonTrorOrderFellesutskriftRecord;
//
import java.util.*;

/**
 * @author oscardelatorre
 * @date Mar 21, 2018
 * 
 */
public class JsonTrorOrderFellesutskriftMapper {
	private static final Logger logger = Logger.getLogger(JsonTrorOrderFellesutskriftMapper.class.getName());
	
	public JsonTrorOrderFellesutskriftContainer getContainer(String utfPayload) throws Exception{
		ObjectMapper mapper = new ObjectMapper();  
		mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES,false);
		
		//At this point we now have an UTF-8 payload
		JsonTrorOrderFellesutskriftContainer container = mapper.readValue(utfPayload.getBytes(), JsonTrorOrderFellesutskriftContainer.class); 
		//logger.info(mapper.writeValueAsString(topicListContainer));
		logger.info("[JSON-String payload status=OK]  " + container.getUser());
		//DEBUG
		Collection<JsonTrorOrderFellesutskriftRecord> list = container.getFellesutskrift();
		for(JsonTrorOrderFellesutskriftRecord record : list){
			//logger.info("wsavd" + record.getWsavd());
		}
		return container;
	}
}
