/**
 * 
 */
package no.systema.overview.ufortolledeoppdrag.mapper.jsonjackson;

import java.util.Collection;

import no.systema.main.mapper.jsonjackson.general.ObjectMapperAbstractGrandFather;
import no.systema.overview.ufortolledeoppdrag.model.jsonjackson.JsonTopicContainer;
import no.systema.overview.ufortolledeoppdrag.model.jsonjackson.JsonTopicRecord;
import org.apache.log4j.Logger;

/**
 * @author oscardelatorre
 * @date Aug 27, 2013
 * 
 */
public class JsonTopicMapper extends ObjectMapperAbstractGrandFather {
	private static final Logger logger = Logger.getLogger(JsonTopicMapper.class.getName());
	
	/**
	 * 
	 * @param utfPayload
	 * @return
	 * @throws Exception
	 */
	public JsonTopicContainer getContainer(String utfPayload) throws Exception{
		
		//At this point we now have an UTF-8 payload
		JsonTopicContainer container = super.getObjectMapper().readValue(utfPayload.getBytes(), JsonTopicContainer.class); 
		//logger.info(mapper.writeValueAsString(topicListContainer));
		logger.info("Mapping Customer object from JSON payload...");
		//logger.info("[JSON-String payload status=OK]  " + container.getUfortList());
		
		//DEBUG
		Collection<JsonTopicRecord> fields = container.getUfortList();
		String prompt= "["+ JsonTopicMapper.class.getCanonicalName() + "]";
		
		for(JsonTopicRecord record : fields){
			//logger.info(prompt + " --> json attribute, getMottaker: " + record.getMottaker());
		}
			
		return container;
		
	}
}
