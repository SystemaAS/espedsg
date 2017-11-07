/**
 * 
 */
package no.systema.skat.nctsexport.mapper.jsonjackson;

import org.apache.log4j.Logger;

import com.fasterxml.jackson.databind.DeserializationConfig;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;

import no.systema.skat.nctsexport.model.jsonjackson.topic.items.validation.JsonSkatNctsExportSpecificTopicItemSensitiveGoodsValidatorContainer;



/**
 * 
 * @author oscardelatorre
 * @date Apr 14, 2014
 * 
 *
 */
public class SkatNctsExportSpecificTopicItemSensitiveGoodsValidatorMapper {
	private static final Logger logger = Logger.getLogger(SkatNctsExportSpecificTopicItemSensitiveGoodsValidatorMapper.class.getName());
		
	public JsonSkatNctsExportSpecificTopicItemSensitiveGoodsValidatorContainer getContainer(String utfPayload) throws Exception{
		ObjectMapper mapper = new ObjectMapper();  
		mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES,false);
		
		//At this point we now have an UTF-8 payload
		JsonSkatNctsExportSpecificTopicItemSensitiveGoodsValidatorContainer container = mapper.readValue(utfPayload.getBytes(), JsonSkatNctsExportSpecificTopicItemSensitiveGoodsValidatorContainer.class); 
		//logger.info(mapper.writeValueAsString(topicListContainer));
		logger.info("Mapping object from JSON payload...");
		logger.info("[JSON-String payload status=OK]  " + container.getUser());
			
		return container;
	}
}


