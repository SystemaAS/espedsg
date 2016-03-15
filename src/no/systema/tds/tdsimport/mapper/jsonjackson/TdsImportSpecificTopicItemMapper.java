/**
 * 
 */
package no.systema.tds.tdsimport.mapper.jsonjackson;

//jackson library
import org.apache.log4j.Logger;
import org.codehaus.jackson.map.DeserializationConfig;
import org.codehaus.jackson.map.ObjectMapper;
//application library
import no.systema.tds.tdsimport.model.jsonjackson.topic.items.JsonTdsImportSpecificTopicItemContainer;
import no.systema.tds.tdsimport.model.jsonjackson.topic.items.JsonTdsImportSpecificTopicItemRecord;
import no.systema.tds.tdsimport.model.jsonjackson.topic.items.JsonTdsImportSpecificTopicItemAvgifterContainer;
import no.systema.tds.tdsimport.model.jsonjackson.topic.items.JsonTdsImportSpecificTopicItemAvgifterRecord;
import no.systema.tds.tdsimport.model.jsonjackson.topic.items.JsonTdsImportSpecificTopicItemBilagdaHandlingarContainer;
import no.systema.tds.tdsimport.model.jsonjackson.topic.items.JsonTdsImportSpecificTopicItemBilagdaHandlingarRecord;
import no.systema.tds.tdsimport.model.jsonjackson.topic.items.JsonTdsImportSpecificTopicItemFormanskoderContainer;
import no.systema.tds.tdsimport.model.jsonjackson.topic.items.JsonTdsImportSpecificTopicItemFormanskoderRecord;


//
import java.util.*;

/**
 * @author oscardelatorre
 * @date	 Sep 2, 2013
 * 
 */
public class TdsImportSpecificTopicItemMapper {
	private static final Logger logger = Logger.getLogger(TdsImportSpecificTopicItemMapper.class.getName());
	
	public JsonTdsImportSpecificTopicItemContainer getContainer(String utfPayload) throws Exception{
		ObjectMapper mapper = new ObjectMapper();  
		mapper.configure(DeserializationConfig.Feature.FAIL_ON_UNKNOWN_PROPERTIES,false);
		
		//At this point we now have an UTF-8 payload
		JsonTdsImportSpecificTopicItemContainer topicItemContainer = mapper.readValue(utfPayload.getBytes(), JsonTdsImportSpecificTopicItemContainer.class); 
		//logger.info(mapper.writeValueAsString(topicListContainer));
		//logger.info("[JSON-String payload status=OK]  " + topicItemContainer.getUser());
		//DEBUG
		Collection<JsonTdsImportSpecificTopicItemRecord> list = topicItemContainer.getOrderList();
		for(JsonTdsImportSpecificTopicItemRecord record : list){
			//logger.info("Item description: " + record.getSviv_vasl());
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
	public JsonTdsImportSpecificTopicItemAvgifterContainer getAvgifterContainer(String utfPayload) throws Exception{
		ObjectMapper mapper = new ObjectMapper();  
		mapper.configure(DeserializationConfig.Feature.FAIL_ON_UNKNOWN_PROPERTIES,false);
		
		//At this point we now have an UTF-8 payload
		JsonTdsImportSpecificTopicItemAvgifterContainer container = mapper.readValue(utfPayload.getBytes(), JsonTdsImportSpecificTopicItemAvgifterContainer.class); 
		//logger.info(mapper.writeValueAsString(topicListContainer));
		logger.info("[JSON-String payload status=OK]  " + container.getUser());
		//DEBUG
		Collection<JsonTdsImportSpecificTopicItemAvgifterRecord> list = container.getTaxcalc();
		for(JsonTdsImportSpecificTopicItemAvgifterRecord record : list){
			//logger.info("Item description: " + record.getSviv_stva());
		}
		return container;
	}
	
	/**
	 * Bilagda handlingar mapper
	 * 
	 * @param utfPayload
	 * @return
	 * @throws Exception
	 */
	public JsonTdsImportSpecificTopicItemBilagdaHandlingarContainer getBilagdaHandlingarContainer(String utfPayload) throws Exception{
		ObjectMapper mapper = new ObjectMapper();  
		mapper.configure(DeserializationConfig.Feature.FAIL_ON_UNKNOWN_PROPERTIES,false);
		
		//At this point we now have an UTF-8 payload
		JsonTdsImportSpecificTopicItemBilagdaHandlingarContainer container = mapper.readValue(utfPayload.getBytes(), JsonTdsImportSpecificTopicItemBilagdaHandlingarContainer.class); 
		//logger.info(mapper.writeValueAsString(topicListContainer));
		logger.info("[JSON-String payload status=OK]  " + container.getUser());
		//DEBUG
		Collection<JsonTdsImportSpecificTopicItemBilagdaHandlingarRecord> list = container.getBilhand();
		for(JsonTdsImportSpecificTopicItemBilagdaHandlingarRecord record : list){
			//logger.info("Item description: " + record.getSviv_stva());
		}
		return container;
	}
	
	/**
	 * 
	 * @param utfPayload
	 * @return
	 * @throws Exception
	 */
	public JsonTdsImportSpecificTopicItemFormanskoderContainer getFormanskoderContainer(String utfPayload) throws Exception{
		ObjectMapper mapper = new ObjectMapper();  
		mapper.configure(DeserializationConfig.Feature.FAIL_ON_UNKNOWN_PROPERTIES,false);
		
		//At this point we now have an UTF-8 payload
		JsonTdsImportSpecificTopicItemFormanskoderContainer container = mapper.readValue(utfPayload.getBytes(), JsonTdsImportSpecificTopicItemFormanskoderContainer.class); 
		//logger.info(mapper.writeValueAsString(topicListContainer));
		logger.info("[JSON-String payload status=OK]  " + container.getUser());
		//DEBUG
		Collection<JsonTdsImportSpecificTopicItemFormanskoderRecord> list = container.getForeslagenformanskod();
		for(JsonTdsImportSpecificTopicItemFormanskoderRecord record : list){
			//logger.info("Item description: " + record.getSviv_stva());
		}
		return container;
	}
	
	
}
