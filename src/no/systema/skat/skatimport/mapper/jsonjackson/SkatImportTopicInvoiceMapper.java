/**
 * 
 */
package no.systema.skat.skatimport.mapper.jsonjackson;

//jackson library
import org.apache.log4j.Logger;

import com.fasterxml.jackson.databind.DeserializationConfig;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;

import no.systema.skat.skatimport.model.jsonjackson.topic.JsonSkatImportTopicInvoiceContainer;
import no.systema.skat.skatimport.model.jsonjackson.topic.JsonSkatImportTopicInvoiceExternalContainer;
//application library
import no.systema.skat.skatimport.model.jsonjackson.topic.JsonSkatImportTopicInvoiceExternalForUpdateContainer;
import no.systema.skat.skatimport.model.jsonjackson.topic.JsonSkatImportTopicInvoiceExternalRecord;
import no.systema.skat.skatimport.model.jsonjackson.topic.JsonSkatImportTopicInvoiceRecord;


/**
 * 
 * @author oscardelatorre
 * @date Apr 6, 2016
 * 
 * 
 */
public class SkatImportTopicInvoiceMapper {
	private static final Logger logger = Logger.getLogger(SkatImportTopicInvoiceMapper.class.getName());
	
	public JsonSkatImportTopicInvoiceContainer getContainer(String utfPayload) throws Exception{
		ObjectMapper mapper = new ObjectMapper();  
		mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES,false);
		//At this point we now have an UTF-8 payload
		JsonSkatImportTopicInvoiceContainer container = mapper.readValue(utfPayload.getBytes(), JsonSkatImportTopicInvoiceContainer.class); 
		//logger.info(mapper.writeValueAsString(topicListContainer));
		logger.info("[JSON-String payload status=OK]  " + container.getUser());
		for (JsonSkatImportTopicInvoiceRecord record : container.getInvList()){
			//DEBUG
			//logger.info(record.getSftxt());
		}
		return container;
	}
	/**
	 * 
	 * @param utfPayload
	 * @return
	 * @throws Exception
	 */
	public JsonSkatImportTopicInvoiceContainer getContainerOneInvoice(String utfPayload) throws Exception{
		ObjectMapper mapper = new ObjectMapper();  
		mapper.configure(DeserializationConfig.Feature.FAIL_ON_UNKNOWN_PROPERTIES,false);
		//At this point we now have an UTF-8 payload
		JsonSkatImportTopicInvoiceContainer container = mapper.readValue(utfPayload.getBytes(), JsonSkatImportTopicInvoiceContainer.class); 
		//logger.info(mapper.writeValueAsString(topicListContainer));
		logger.info("USER:" + container.getUser());
		for (JsonSkatImportTopicInvoiceRecord record : container.getOneInvoice()){
			//DEBUG
			//logger.info(record.getSvif_fatx());
		}
		return container;
	}
	
	/**
	 * 
	 * @param utfPayload
	 * @return
	 * @throws Exception
	 */
	public JsonSkatImportTopicInvoiceExternalContainer getContainerInvoiceExternal(String utfPayload) throws Exception{
		ObjectMapper mapper = new ObjectMapper();  
		mapper.configure(DeserializationConfig.Feature.FAIL_ON_UNKNOWN_PROPERTIES,false);
		//At this point we now have an UTF-8 payload
		JsonSkatImportTopicInvoiceExternalContainer container = mapper.readValue(utfPayload.getBytes(), JsonSkatImportTopicInvoiceExternalContainer.class); 
		//logger.info(mapper.writeValueAsString(topicListContainer));
		logger.info("[JSON-String payload status=OK]  " + container.getUser());
		for (JsonSkatImportTopicInvoiceExternalRecord record : container.getListexternfakt()){
			//DEBUG
			//logger.info(record.getSftxt());
		}
		return container;
	}
	
	/**
	 * 
	 * @param utfPayload
	 * @return
	 * @throws Exception
	 */
	public JsonSkatImportTopicInvoiceExternalContainer getContainerOneInvoiceInvoiceExternal(String utfPayload) throws Exception{
		ObjectMapper mapper = new ObjectMapper();  
		mapper.configure(DeserializationConfig.Feature.FAIL_ON_UNKNOWN_PROPERTIES,false);
		//At this point we now have an UTF-8 payload
		JsonSkatImportTopicInvoiceExternalContainer container = mapper.readValue(utfPayload.getBytes(), JsonSkatImportTopicInvoiceExternalContainer.class); 
		//logger.info(mapper.writeValueAsString(topicListContainer));
		logger.info("[JSON-String payload status=OK]  " + container.getUser());
		for (JsonSkatImportTopicInvoiceExternalRecord record : container.getGetexternfakt()){
			//DEBUG
			//logger.info(record.getSftxt());
		}
		return container;
	}
	/**
	 * 
	 * @param utfPayload
	 * @return
	 * @throws Exception
	 */
	public JsonSkatImportTopicInvoiceExternalForUpdateContainer getContainerOneInvoiceInvoiceExternalForUpdate(String utfPayload) throws Exception{
		ObjectMapper mapper = new ObjectMapper();  
		mapper.configure(DeserializationConfig.Feature.FAIL_ON_UNKNOWN_PROPERTIES,false);
		//At this point we now have an UTF-8 payload
		JsonSkatImportTopicInvoiceExternalForUpdateContainer container = mapper.readValue(utfPayload.getBytes(), JsonSkatImportTopicInvoiceExternalForUpdateContainer.class); 
		//logger.info(mapper.writeValueAsString(topicListContainer));
		logger.info("User:" + container.getUser());
		
		return container;
	}
}


