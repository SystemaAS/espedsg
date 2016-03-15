/**
 * 
 */
package no.systema.skat.nctsexport.mapper.jsonjackson;



import org.apache.log4j.Logger;
import org.codehaus.jackson.map.DeserializationConfig;
import org.codehaus.jackson.map.ObjectMapper;

import no.systema.skat.nctsexport.model.jsonjackson.topic.validation.JsonSkatNctsExportSpecificTopicGuaranteeValidatorContainer;


/**
 * 
 * @author oscardelatorre
 * @date Apr 14, 2014
 * 
 *
 */
public class SkatNctsExportSpecificTopicGuaranteeValidatorMapper {
	private static final Logger logger = Logger.getLogger(SkatNctsExportSpecificTopicGuaranteeValidatorMapper.class.getName());
		
	public JsonSkatNctsExportSpecificTopicGuaranteeValidatorContainer getContainer(String utfPayload) throws Exception{
		ObjectMapper mapper = new ObjectMapper();  
		mapper.configure(DeserializationConfig.Feature.FAIL_ON_UNKNOWN_PROPERTIES,false);
		
		//At this point we now have an UTF-8 payload
		JsonSkatNctsExportSpecificTopicGuaranteeValidatorContainer container = mapper.readValue(utfPayload.getBytes(), JsonSkatNctsExportSpecificTopicGuaranteeValidatorContainer.class); 
		//logger.info(mapper.writeValueAsString(topicListContainer));
		logger.info("Mapping Customer object from JSON payload...");
		logger.info("[JSON-String payload status=OK]  " + container.getUser());
			
		return container;
	}
}


