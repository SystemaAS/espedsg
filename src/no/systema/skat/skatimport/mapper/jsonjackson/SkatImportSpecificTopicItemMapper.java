/**
 * 
 */
package no.systema.skat.skatimport.mapper.jsonjackson;

//
import java.util.Collection;

//jackson library
import org.apache.log4j.Logger;

import com.fasterxml.jackson.databind.DeserializationConfig;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;

import no.systema.skat.skatimport.model.jsonjackson.topic.items.JsonSkatImportSpecificTopicItemAvgifterContainer;
import no.systema.skat.skatimport.model.jsonjackson.topic.items.JsonSkatImportSpecificTopicItemAvgifterRecord;
import no.systema.skat.skatimport.model.jsonjackson.topic.items.JsonSkatImportSpecificTopicItemBilagdaHandlingarContainer;
import no.systema.skat.skatimport.model.jsonjackson.topic.items.JsonSkatImportSpecificTopicItemBilagdaHandlingarRecord;
import no.systema.skat.skatimport.model.jsonjackson.topic.items.JsonSkatImportSpecificTopicItemCertificateNrAndCodeR442Container;
import no.systema.skat.skatimport.model.jsonjackson.topic.items.JsonSkatImportSpecificTopicItemCertificateNrAndCodeR442Record;
//application library
import no.systema.skat.skatimport.model.jsonjackson.topic.items.JsonSkatImportSpecificTopicItemContainer;
import no.systema.skat.skatimport.model.jsonjackson.topic.items.JsonSkatImportSpecificTopicItemRecord;
import no.systema.skat.skatimport.model.jsonjackson.topic.items.JsonSkatImportSpecificTopicItemToldvaerdiContainer;
import no.systema.skat.skatimport.model.jsonjackson.topic.items.JsonSkatImportSpecificTopicItemToldvaerdiRecord;
import no.systema.skat.skatimport.model.jsonjackson.topic.items.JsonSkatImportSpecificTopicItemToldvaerdiTransportContainer;

/**
 * 
 * @author oscardelatorre
 * @date	 Jan 28, 2014
 * 
 * 
 */
public class SkatImportSpecificTopicItemMapper {
	private static final Logger logger = Logger.getLogger(SkatImportSpecificTopicItemMapper.class.getName());
	
	public JsonSkatImportSpecificTopicItemContainer getContainer(String utfPayload) throws Exception{
		ObjectMapper mapper = new ObjectMapper();  
		mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES,false);
		
		//At this point we now have an UTF-8 payload
		JsonSkatImportSpecificTopicItemContainer topicItemContainer = mapper.readValue(utfPayload.getBytes(), JsonSkatImportSpecificTopicItemContainer.class); 
		//logger.info(mapper.writeValueAsString(topicListContainer));
		logger.info("[JSON-String payload status=OK]  " + topicItemContainer.getUser());
		
		//DEBUG
		Collection<JsonSkatImportSpecificTopicItemRecord> list = topicItemContainer.getOrderList();
		for(JsonSkatImportSpecificTopicItemRecord record : list){
			//logger.info("Item description(dkiv_315a): " + record.getDkiv_315a());
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
	public JsonSkatImportSpecificTopicItemAvgifterContainer getAvgifterContainer(String utfPayload) throws Exception{
		ObjectMapper mapper = new ObjectMapper();  
		mapper.configure(DeserializationConfig.Feature.FAIL_ON_UNKNOWN_PROPERTIES,false);
		
		//At this point we now have an UTF-8 payload
		JsonSkatImportSpecificTopicItemAvgifterContainer container = mapper.readValue(utfPayload.getBytes(), JsonSkatImportSpecificTopicItemAvgifterContainer.class); 
		//logger.info(mapper.writeValueAsString(topicListContainer));
		logger.info("[JSON-String payload status=OK]  " + container.getUser());
		//DEBUG
		Collection<JsonSkatImportSpecificTopicItemAvgifterRecord> list = container.getStatvaluecalc();
		for(JsonSkatImportSpecificTopicItemAvgifterRecord record : list){
			//logger.info("Item description: " + record.getSviv_stva());
		}
		return container;
	}
	
	/**
	 * 
	 * @param utfPayload
	 * @return
	 * @throws Exception
	 * 
	 */
	public JsonSkatImportSpecificTopicItemToldvaerdiContainer getTullvaerdiContainer(String utfPayload) throws Exception{
		ObjectMapper mapper = new ObjectMapper();  
		mapper.configure(DeserializationConfig.Feature.FAIL_ON_UNKNOWN_PROPERTIES,false);
		
		//At this point we now have an UTF-8 payload
		JsonSkatImportSpecificTopicItemToldvaerdiContainer container = mapper.readValue(utfPayload.getBytes(), JsonSkatImportSpecificTopicItemToldvaerdiContainer.class); 
		//logger.info(mapper.writeValueAsString(topicListContainer));
		logger.info("[JSON-String payload status=OK]  " + container.getUser());
		//DEBUG
		Collection<JsonSkatImportSpecificTopicItemToldvaerdiRecord> list = container.getTv1calc();
		for(JsonSkatImportSpecificTopicItemToldvaerdiRecord record : list){
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
	public JsonSkatImportSpecificTopicItemToldvaerdiTransportContainer getTullvaerdiTransportContainer(String utfPayload) throws Exception{
		ObjectMapper mapper = new ObjectMapper();  
		mapper.configure(DeserializationConfig.Feature.FAIL_ON_UNKNOWN_PROPERTIES,false);
		
		//At this point we now have an UTF-8 payload
		JsonSkatImportSpecificTopicItemToldvaerdiTransportContainer container = mapper.readValue(utfPayload.getBytes(), JsonSkatImportSpecificTopicItemToldvaerdiTransportContainer.class); 
		//logger.info(mapper.writeValueAsString(topicListContainer));
		logger.info("[JSON-String payload status=OK]  " + container.getUser());
		//DEBUG
		Collection<JsonSkatImportSpecificTopicItemToldvaerdiRecord> list = container.getT17at19calc();
		for(JsonSkatImportSpecificTopicItemToldvaerdiRecord record : list){
			//logger.info("dkiv_t17a: " + record.getDkiv_t17a());
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
	public JsonSkatImportSpecificTopicItemBilagdaHandlingarContainer getBilagdaHandlingarContainer(String utfPayload) throws Exception{
		ObjectMapper mapper = new ObjectMapper();  
		mapper.configure(DeserializationConfig.Feature.FAIL_ON_UNKNOWN_PROPERTIES,false);
		
		//At this point we now have an UTF-8 payload
		JsonSkatImportSpecificTopicItemBilagdaHandlingarContainer container = mapper.readValue(utfPayload.getBytes(), JsonSkatImportSpecificTopicItemBilagdaHandlingarContainer.class); 
		//logger.info(mapper.writeValueAsString(topicListContainer));
		logger.info("[JSON-String payload status=OK]  " + container.getUser());
		//DEBUG
		Collection<JsonSkatImportSpecificTopicItemBilagdaHandlingarRecord> list = container.getBilhand();
		for(JsonSkatImportSpecificTopicItemBilagdaHandlingarRecord record : list){
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
	public JsonSkatImportSpecificTopicItemCertificateNrAndCodeR442Container getCertificateNrAndCodeR442Container(String utfPayload) throws Exception{
		ObjectMapper mapper = new ObjectMapper();  
		mapper.configure(DeserializationConfig.Feature.FAIL_ON_UNKNOWN_PROPERTIES,false);
		
		//At this point we now have an UTF-8 payload
		JsonSkatImportSpecificTopicItemCertificateNrAndCodeR442Container container = mapper.readValue(utfPayload.getBytes(), JsonSkatImportSpecificTopicItemCertificateNrAndCodeR442Container.class); 
		//logger.info(mapper.writeValueAsString(topicListContainer));
		logger.info("[JSON-String payload status=OK]  " + container.getUser());
		//DEBUG
		Collection<JsonSkatImportSpecificTopicItemCertificateNrAndCodeR442Record> list = container.getCertifikatList();
		for(JsonSkatImportSpecificTopicItemCertificateNrAndCodeR442Record record : list){
			//logger.info("TODO");
		}
		return container;
	}
	
}
