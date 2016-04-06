/**
 * 
 */
package no.systema.skat.skatexport.mapper.jsonjackson;

//jackson library
import org.apache.log4j.Logger;
import org.codehaus.jackson.map.DeserializationConfig;
import org.codehaus.jackson.map.ObjectMapper;
//application library
import no.systema.skat.skatexport.model.jsonjackson.topic.JsonSkatExportTopicInvoiceContainer;
import no.systema.skat.skatexport.model.jsonjackson.topic.JsonSkatExportTopicInvoiceRecord;
import no.systema.skat.skatexport.model.jsonjackson.topic.JsonSkatExportTopicInvoiceExternalContainer;
import no.systema.skat.skatexport.model.jsonjackson.topic.JsonSkatExportTopicInvoiceExternalRecord;
import no.systema.skat.skatexport.model.jsonjackson.topic.JsonSkatExportTopicInvoiceExternalForUpdateContainer;


/**
 * 
 * @author oscardelatorre
 * @date  Apr 6, 2016
 * 
 * 
 */
public class SkatExportTopicInvoiceMapper {
	private static final Logger logger = Logger.getLogger(SkatExportTopicInvoiceMapper.class.getName());
	
	public JsonSkatExportTopicInvoiceContainer getContainer(String utfPayload) throws Exception{
		ObjectMapper mapper = new ObjectMapper();  
		mapper.configure(DeserializationConfig.Feature.FAIL_ON_UNKNOWN_PROPERTIES,false);
		//At this point we now have an UTF-8 payload
		JsonSkatExportTopicInvoiceContainer topicListContainer = mapper.readValue(utfPayload.getBytes(), JsonSkatExportTopicInvoiceContainer.class); 
		//logger.info(mapper.writeValueAsString(topicListContainer));
		logger.info("User:" + topicListContainer.getUser());
		for (JsonSkatExportTopicInvoiceRecord record : topicListContainer.getInvList()){
			//DEBUG
			//logger.info(record.getSftxt());
		}
		return topicListContainer;
	}
	/**
	 * 
	 * @param utfPayload
	 * @return
	 * @throws Exception
	 */
	public JsonSkatExportTopicInvoiceContainer getContainerOneInvoice(String utfPayload) throws Exception{
		ObjectMapper mapper = new ObjectMapper();  
		mapper.configure(DeserializationConfig.Feature.FAIL_ON_UNKNOWN_PROPERTIES,false);
		//At this point we now have an UTF-8 payload
		JsonSkatExportTopicInvoiceContainer container = mapper.readValue(utfPayload.getBytes(), JsonSkatExportTopicInvoiceContainer.class); 
		//logger.info(mapper.writeValueAsString(topicListContainer));
		logger.info("User:" + container.getUser());
		//logger.info("oneInvoice:" + container.getOneInvoice());
		for (JsonSkatExportTopicInvoiceRecord record : container.getOneInvoice()){
			//DEBUG
			//logger.info(record.getSftxt());
		}
		return container;
	}
	
	/**
	 * External invoice
	 * @param utfPayload
	 * @return
	 * @throws Exception
	 */
	public JsonSkatExportTopicInvoiceExternalContainer getContainerInvoiceExternal(String utfPayload) throws Exception{
		ObjectMapper mapper = new ObjectMapper();  
		mapper.configure(DeserializationConfig.Feature.FAIL_ON_UNKNOWN_PROPERTIES,false);
		//At this point we now have an UTF-8 payload
		JsonSkatExportTopicInvoiceExternalContainer container = mapper.readValue(utfPayload.getBytes(), JsonSkatExportTopicInvoiceExternalContainer.class); 
		//logger.info(mapper.writeValueAsString(topicListContainer));
		logger.info("User:" + container.getUser());
		for (JsonSkatExportTopicInvoiceExternalRecord record : container.getListexternfakt()){
			//DEBUG
			//logger.info(record.getSftxt());
		}
		return container;
	}
	
	/**
	 * Get one specific external invoice
	 * 
	 * @param utfPayload
	 * @return
	 * @throws Exception
	 */
	public JsonSkatExportTopicInvoiceExternalContainer getContainerOneInvoiceInvoiceExternal(String utfPayload) throws Exception{
		ObjectMapper mapper = new ObjectMapper();  
		mapper.configure(DeserializationConfig.Feature.FAIL_ON_UNKNOWN_PROPERTIES,false);
		//At this point we now have an UTF-8 payload
		JsonSkatExportTopicInvoiceExternalContainer container = mapper.readValue(utfPayload.getBytes(), JsonSkatExportTopicInvoiceExternalContainer.class); 
		//logger.info(mapper.writeValueAsString(topicListContainer));
		logger.info("User:" + container.getUser());
		for (JsonSkatExportTopicInvoiceExternalRecord record : container.getGetexternfakt()){
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
	public JsonSkatExportTopicInvoiceExternalForUpdateContainer getContainerOneInvoiceInvoiceExternalForUpdate(String utfPayload) throws Exception{
		ObjectMapper mapper = new ObjectMapper();  
		mapper.configure(DeserializationConfig.Feature.FAIL_ON_UNKNOWN_PROPERTIES,false);
		//At this point we now have an UTF-8 payload
		JsonSkatExportTopicInvoiceExternalForUpdateContainer container = mapper.readValue(utfPayload.getBytes(), JsonSkatExportTopicInvoiceExternalForUpdateContainer.class); 
		//logger.info(mapper.writeValueAsString(topicListContainer));
		logger.info("User:" + container.getUser());
		
		return container;
	}
}


