/**
 * 
 */
package no.systema.skat.skatexport.mapper.jsonjackson;

import java.util.*;

//jackson library
import org.apache.log4j.Logger;
import org.codehaus.jackson.map.DeserializationConfig;
import org.codehaus.jackson.map.ObjectMapper;
//application library
import no.systema.skat.skatexport.model.jsonjackson.topic.JsonSkatExportSpecificTopicContainer;
import no.systema.skat.skatexport.model.jsonjackson.topic.JsonSkatExportSpecificTopicRecord;
import no.systema.skat.skatexport.model.jsonjackson.topic.JsonSkatExportSpecificTopicOmbudContainer;
import no.systema.skat.skatexport.model.jsonjackson.topic.JsonSkatExportSpecificTopicOmbudRecord;


/**
 * @author oscardelatorre
 * @date Feb 26, 2014
 * 
 * 
 */
public class SkatExportSpecificTopicMapper {
	private static final Logger logger = Logger.getLogger(SkatExportSpecificTopicMapper.class.getName());
	
	/**
	 * 
	 * @param utfPayload
	 * @return
	 * @throws Exception
	 */
	public JsonSkatExportSpecificTopicContainer getContainer(String utfPayload) throws Exception{
		ObjectMapper mapper = new ObjectMapper();  
		mapper.configure(DeserializationConfig.Feature.FAIL_ON_UNKNOWN_PROPERTIES,false);
		
		//At this point we now have an UTF-8 payload
		JsonSkatExportSpecificTopicContainer container = mapper.readValue(utfPayload.getBytes(), JsonSkatExportSpecificTopicContainer.class); 
		//logger.info(mapper.writeValueAsString(topicListContainer));
		logger.info("[JSON-String payload status=OK]  " + container.getUser());
		
		//DEBUG
		Collection<JsonSkatExportSpecificTopicRecord> fields = container.getOneorder();
		for(JsonSkatExportSpecificTopicRecord record : fields){
			//debug here when applicable
		}
			
		return container;
	}
	
	/**
	 * 
	 * @param utfPayload
	 * @return
	 * @throws Exception
	 */
	
	public JsonSkatExportSpecificTopicOmbudContainer getOmbudContainer(String utfPayload) throws Exception{
		ObjectMapper mapper = new ObjectMapper();  
		mapper.configure(DeserializationConfig.Feature.FAIL_ON_UNKNOWN_PROPERTIES,false);
		
		//At this point we now have an UTF-8 payload
		JsonSkatExportSpecificTopicOmbudContainer container = mapper.readValue(utfPayload.getBytes(), JsonSkatExportSpecificTopicOmbudContainer.class); 
		//logger.info(mapper.writeValueAsString(topicListContainer));
		logger.info("[JSON-String payload status=OK]  " + container.getUser());
		//DEBUG
		Collection<JsonSkatExportSpecificTopicOmbudRecord> fields = container.getGetdepinf();
		for(JsonSkatExportSpecificTopicOmbudRecord record : fields){
			//debug here when applicable
		}
		
			
		return container;
	}
	
}
