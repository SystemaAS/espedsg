/**
 * 
 */
package no.systema.tds.mapper.jsonjackson;

//jackson library
import org.apache.log4j.Logger;
import org.codehaus.jackson.map.DeserializationConfig;
import org.codehaus.jackson.map.ObjectMapper;
//application library
import no.systema.tds.model.jsonjackson.codes.JsonTdsTillaggskodContainer;
import no.systema.tds.model.jsonjackson.codes.JsonTdsBilagdaHandlingarYKoderContainer;
import no.systema.tds.model.jsonjackson.codes.JsonTdsBilagdaHandlingarYKoderRecord;

import java.util.*;

/**
 * @author oscardelatorre
 * 
 */
public class TdsBilagdaHandligarYKoderMapper {
	private static final Logger logger = Logger.getLogger(TdsBilagdaHandligarYKoderMapper.class.getName());
	
	
	/**
	 * Bilagda handlingar mapper
	 * 
	 * @param utfPayload
	 * @return
	 * @throws Exception
	 */
	public JsonTdsBilagdaHandlingarYKoderContainer getContainer(String utfPayload) throws Exception{
		ObjectMapper mapper = new ObjectMapper();  
		mapper.configure(DeserializationConfig.Feature.FAIL_ON_UNKNOWN_PROPERTIES,false);
		
		//At this point we now have an UTF-8 payload
		JsonTdsBilagdaHandlingarYKoderContainer container = mapper.readValue(utfPayload.getBytes(), JsonTdsBilagdaHandlingarYKoderContainer.class); 
		//logger.info(mapper.writeValueAsString(topicListContainer));
		logger.info("[JSON-String payload status=OK]  " + container.getUser());
		//DEBUG
		Collection<JsonTdsBilagdaHandlingarYKoderRecord> list = container.getBilhandykoder();
		for(JsonTdsBilagdaHandlingarYKoderRecord record : list){
			//logger.info("Item description: " + record.getSviv_stva());
		}
		return container;
	}		
	
}
