/**
 * 
 */
package no.systema.transportdisp.mapper.jsonjackson.frankatur;

//jackson library
import org.apache.log4j.Logger;
import org.codehaus.jackson.map.DeserializationConfig;
import org.codehaus.jackson.map.ObjectMapper;
//application library
import no.systema.transportdisp.model.jsonjackson.workflow.frankatur.JsonTransportDispFrankaturContainer;
import no.systema.transportdisp.model.jsonjackson.workflow.frankatur.JsonTransportDispFrankaturRecord;

import java.util.*;

/**
 * @author oscardelatorre
 * @date Aug 07, 2015
 * 
 * 
 */
public class JsonTransportDispFrankaturMapper {
	private static final Logger logger = Logger.getLogger(JsonTransportDispFrankaturMapper.class.getName());
	
	/**
	 * 
	 * @param utfPayload
	 * @return
	 * @throws Exception
	 */
	public JsonTransportDispFrankaturContainer getContainer(String utfPayload) throws Exception{
		ObjectMapper mapper = new ObjectMapper();  
		mapper.configure(DeserializationConfig.Feature.FAIL_ON_UNKNOWN_PROPERTIES,false);
		JsonTransportDispFrankaturContainer container = null;
		if(utfPayload!=null){
			//At this point we now have an UTF-8 payload
			container = mapper.readValue(utfPayload.getBytes(), JsonTransportDispFrankaturContainer.class); 
			//logger.info(mapper.writeValueAsString(topicListContainer));
			//logger.info("Mapping Code object from JSON payload...");
			//logger.info("[JSON-String payload status=OK]  " + taricCodeContainer.getUser());
			
			//DEBUG
			Collection<JsonTransportDispFrankaturRecord> fields = container.getFrankaturer();
			for(JsonTransportDispFrankaturRecord record : fields){
				
			}
		}
			
		return container;
	}
	
}
