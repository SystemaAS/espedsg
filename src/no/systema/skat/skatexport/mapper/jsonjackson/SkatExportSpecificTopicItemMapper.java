/**
 * 
 */
package no.systema.skat.skatexport.mapper.jsonjackson;

//jackson library
import org.apache.log4j.Logger;
import org.codehaus.jackson.map.DeserializationConfig;
import org.codehaus.jackson.map.ObjectMapper;
//application library
import no.systema.skat.skatexport.model.jsonjackson.topic.items.JsonSkatExportSpecificTopicItemContainer;
import no.systema.skat.skatexport.model.jsonjackson.topic.items.JsonSkatExportSpecificTopicItemRecord;

import no.systema.skat.skatexport.model.jsonjackson.topic.items.JsonSkatExportSpecificTopicItemAvgifterContainer;
import no.systema.skat.skatexport.model.jsonjackson.topic.items.JsonSkatExportSpecificTopicItemAvgifterRecord;

//
import java.util.*;

/**
 * 
 * @author oscardelatorre
 * @date	 Mar 10, 2014
 * 
 * 
 */
public class SkatExportSpecificTopicItemMapper {
	private static final Logger logger = Logger.getLogger(SkatExportSpecificTopicItemMapper.class.getName());
	
	public JsonSkatExportSpecificTopicItemContainer getContainer(String utfPayload) throws Exception{
		ObjectMapper mapper = new ObjectMapper();  
		mapper.configure(DeserializationConfig.Feature.FAIL_ON_UNKNOWN_PROPERTIES,false);
		
		//At this point we now have an UTF-8 payload
		JsonSkatExportSpecificTopicItemContainer topicItemContainer = mapper.readValue(utfPayload.getBytes(), JsonSkatExportSpecificTopicItemContainer.class); 
		//logger.info(mapper.writeValueAsString(topicListContainer));
		logger.info("[JSON-String payload status=OK]  " + topicItemContainer.getUser());
		//DEBUG
		Collection<JsonSkatExportSpecificTopicItemRecord> list = topicItemContainer.getOrderList();
		logger.info("Record list(size): " + list.size());
		
		for(JsonSkatExportSpecificTopicItemRecord record : list){
			//logger.info("Item description(dkev_315a): " + record.getDkev_315());
		}
		return topicItemContainer;
	}
	
	/**
	 * Avgifts mapper
	 * 
	 * @param utfPayload
	 * @return
	 * @throws Exception
	 */
	public JsonSkatExportSpecificTopicItemAvgifterContainer getAvgifterContainer(String utfPayload) throws Exception{
		ObjectMapper mapper = new ObjectMapper();  
		mapper.configure(DeserializationConfig.Feature.FAIL_ON_UNKNOWN_PROPERTIES,false);
		
		//At this point we now have an UTF-8 payload
		JsonSkatExportSpecificTopicItemAvgifterContainer container = mapper.readValue(utfPayload.getBytes(), JsonSkatExportSpecificTopicItemAvgifterContainer.class); 
		//logger.info(mapper.writeValueAsString(topicListContainer));
		logger.info("[JSON-String payload status=OK]  " + container.getUser());
		//DEBUG
		Collection<JsonSkatExportSpecificTopicItemAvgifterRecord> list = container.getStatvaluecalc();
		for(JsonSkatExportSpecificTopicItemAvgifterRecord record : list){
			//logger.info("Item description: " + record.getSviv_stva());
		}
		return container;
	}
	
	
	
	
}
