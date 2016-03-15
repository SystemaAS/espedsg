/**
 * 
 */
package no.systema.overview.ufortolledeoppdrag.mapper.jsonjackson;

import java.util.Collection;


import no.systema.overview.ufortolledeoppdrag.model.jsonjackson.JsonRenderSpecificTopicContainer;


import org.apache.log4j.Logger;
import org.codehaus.jackson.map.DeserializationConfig;
import org.codehaus.jackson.map.ObjectMapper;

/**
 * @author oscardelatorre
 * @date Oct 15, 2013
 * 
 */
public class JsonRenderSpecificTopicMapper {
	private static final Logger logger = Logger.getLogger(JsonRenderSpecificTopicMapper.class.getName());
	
	/**
	 * 
	 * @param utfPayload
	 * @return
	 * @throws Exception
	 */
	public JsonRenderSpecificTopicContainer getContainer(String utfPayload) throws Exception{
		ObjectMapper mapper = new ObjectMapper();  
		mapper.configure(DeserializationConfig.Feature.FAIL_ON_UNKNOWN_PROPERTIES,false);
		
		//At this point we now have an UTF-8 payload
		JsonRenderSpecificTopicContainer container = mapper.readValue(utfPayload.getBytes(), JsonRenderSpecificTopicContainer.class); 
		logger.info("Mapping Customer object from JSON payload...");
		
		//DEBUG
		logger.info(" --> json attribute: " + container.getTur());
			
		return container;
		
	}
}
