/**
 * 
 */
package no.systema.transportdisp.mapper.jsonjackson.oppdragstype;

//jackson library
import org.apache.log4j.Logger;
import org.codehaus.jackson.map.DeserializationConfig;
import org.codehaus.jackson.map.ObjectMapper;
//application library
import no.systema.transportdisp.model.jsonjackson.workflow.oppdragstype.JsonTransportDispOppdragTypeContainer;
import no.systema.transportdisp.model.jsonjackson.workflow.oppdragstype.JsonTransportDispOppdragTypeRecord;
import no.systema.transportdisp.model.jsonjackson.workflow.oppdragstype.JsonTransportDispOppdragTypeParametersContainer;
import no.systema.transportdisp.model.jsonjackson.workflow.oppdragstype.JsonTransportDispOppdragTypeParametersRecord;

import java.util.*;

/**
 * @author oscardelatorre
 * @date Aug 07, 2015
 * 
 * 
 */
public class JsonTransportDispOppdragTypeMapper {
	private static final Logger logger = Logger.getLogger(JsonTransportDispOppdragTypeMapper.class.getName());
	
	/**
	 * 
	 * @param utfPayload
	 * @return
	 * @throws Exception
	 */
	public JsonTransportDispOppdragTypeContainer getContainer(String utfPayload) throws Exception{
		ObjectMapper mapper = new ObjectMapper();  
		mapper.configure(DeserializationConfig.Feature.FAIL_ON_UNKNOWN_PROPERTIES,false);
		JsonTransportDispOppdragTypeContainer container = null;
		if(utfPayload!=null){
			//At this point we now have an UTF-8 payload
			container = mapper.readValue(utfPayload.getBytes(), JsonTransportDispOppdragTypeContainer.class); 
			//logger.info(mapper.writeValueAsString(topicListContainer));
			//logger.info("Mapping Code object from JSON payload...");
			//logger.info("[JSON-String payload status=OK]  " + taricCodeContainer.getUser());
			
			//DEBUG
			Collection<JsonTransportDispOppdragTypeRecord> fields = container.getOppdragsTyper();
			for(JsonTransportDispOppdragTypeRecord record : fields){
				
			}
		}
			
		return container;
	}
	/**
	 * 
	 * @param utfPayload
	 * @return
	 * @throws Exception
	 */
	public JsonTransportDispOppdragTypeParametersContainer getOppdragTypeParametersContainer(String utfPayload) throws Exception{
		ObjectMapper mapper = new ObjectMapper();  
		mapper.configure(DeserializationConfig.Feature.FAIL_ON_UNKNOWN_PROPERTIES,false);
		JsonTransportDispOppdragTypeParametersContainer container = null;
		if(utfPayload!=null){
			//At this point we now have an UTF-8 payload
			container = mapper.readValue(utfPayload.getBytes(), JsonTransportDispOppdragTypeParametersContainer.class); 
			//logger.info(mapper.writeValueAsString(topicListContainer));
			//logger.info("Mapping Code object from JSON payload...");
			//logger.info("[JSON-String payload status=OK]  " + taricCodeContainer.getUser());
			
		}
			
		return container;
	}
	
}
