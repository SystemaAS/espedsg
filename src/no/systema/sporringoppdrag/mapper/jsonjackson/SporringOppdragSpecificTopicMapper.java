/**
 * 
 */
package no.systema.sporringoppdrag.mapper.jsonjackson;

//jackson library
import org.apache.log4j.Logger;
import org.codehaus.jackson.map.DeserializationConfig;
import org.codehaus.jackson.map.ObjectMapper;
//application library
import no.systema.sporringoppdrag.model.jsonjackson.topic.JsonSporringOppdragSpecificTopicContainer;
import no.systema.sporringoppdrag.model.jsonjackson.topic.JsonSporringOppdragSpecificTopicRecord;
import no.systema.sporringoppdrag.model.jsonjackson.topic.JsonSporringOppdragSpecificTopicChildDocumentContainer;
import no.systema.sporringoppdrag.model.jsonjackson.topic.JsonSporringOppdragSpecificTopicChildDocumentRecord;
import no.systema.sporringoppdrag.model.jsonjackson.topic.JsonSporringOppdragSpecificTopicChildFreetextContainer;
import no.systema.sporringoppdrag.model.jsonjackson.topic.JsonSporringOppdragSpecificTopicChildFreetextRecord;
import no.systema.sporringoppdrag.model.jsonjackson.topic.JsonSporringOppdragSpecificTopicChildInvoiceContainer;
import no.systema.sporringoppdrag.model.jsonjackson.topic.JsonSporringOppdragSpecificTopicChildInvoiceRecord;
import no.systema.sporringoppdrag.model.jsonjackson.topic.JsonSporringOppdragSpecificTopicChildFriesokeVeierContainer;
import no.systema.sporringoppdrag.model.jsonjackson.topic.JsonSporringOppdragSpecificTopicChildFriesokeVeierRecord;
import no.systema.sporringoppdrag.model.jsonjackson.topic.JsonSporringOppdragSpecificTopicChildHendelseslogContainer;
import no.systema.sporringoppdrag.model.jsonjackson.topic.JsonSporringOppdragSpecificTopicChildHendelseslogRecord;




/**
 * 
 * @author oscardelatorre
 * @date Feb 11, 2015
 * 
 * 
 */
public class SporringOppdragSpecificTopicMapper {
	private static final Logger logger = Logger.getLogger(SporringOppdragSpecificTopicMapper.class.getName());
	/**
	 * 
	 * @param utfPayload
	 * @return
	 * @throws Exception
	 */
	public JsonSporringOppdragSpecificTopicContainer getContainer(String utfPayload) throws Exception{
		ObjectMapper mapper = new ObjectMapper();  
		mapper.configure(DeserializationConfig.Feature.FAIL_ON_UNKNOWN_PROPERTIES,false);
		//At this point we now have an UTF-8 payload
		JsonSporringOppdragSpecificTopicContainer container = mapper.readValue(utfPayload.getBytes(), JsonSporringOppdragSpecificTopicContainer.class); 
		//logger.info(mapper.writeValueAsString(topicListContainer));
		logger.info("[JSON-String payload status=OK]  " + container.getUser());
		for (JsonSporringOppdragSpecificTopicRecord record : container.getDspoppdrag()){
			//DEBUG
		}
		return container;
	}
	/**
	 * 
	 * @param utfPayload
	 * @return
	 * @throws Exception
	 */
	public JsonSporringOppdragSpecificTopicChildDocumentContainer getChildDocumentContainer(String utfPayload) throws Exception{
		ObjectMapper mapper = new ObjectMapper();  
		mapper.configure(DeserializationConfig.Feature.FAIL_ON_UNKNOWN_PROPERTIES,false);
		//At this point we now have an UTF-8 payload
		JsonSporringOppdragSpecificTopicChildDocumentContainer container = mapper.readValue(utfPayload.getBytes(), JsonSporringOppdragSpecificTopicChildDocumentContainer.class); 
		//logger.info(mapper.writeValueAsString(topicListContainer));
		logger.info("[JSON-String payload status=OK]  " + container.getUser());
		for (JsonSporringOppdragSpecificTopicChildDocumentRecord record : container.getGetdoc()){
			//DEBUG
		}
		return container;
	}
	/**
	 * 
	 * @param utfPayload
	 * @return
	 * @throws Exception
	 */
	public JsonSporringOppdragSpecificTopicChildFreetextContainer getChildFreetextContainer(String utfPayload) throws Exception{
		ObjectMapper mapper = new ObjectMapper();  
		mapper.configure(DeserializationConfig.Feature.FAIL_ON_UNKNOWN_PROPERTIES,false);
		//At this point we now have an UTF-8 payload
		JsonSporringOppdragSpecificTopicChildFreetextContainer container = mapper.readValue(utfPayload.getBytes(), JsonSporringOppdragSpecificTopicChildFreetextContainer.class); 
		//logger.info(mapper.writeValueAsString(topicListContainer));
		logger.info("[JSON-String payload status=OK]  " + container.getUser());
		for (JsonSporringOppdragSpecificTopicChildFreetextRecord record : container.getFreetextlistA()){
			//DEBUG
		}
		return container;
	}
	/**
	 * 
	 * @param utfPayload
	 * @return
	 * @throws Exception
	 */
	public JsonSporringOppdragSpecificTopicChildInvoiceContainer getChildInvoiceContainer(String utfPayload) throws Exception{
		ObjectMapper mapper = new ObjectMapper();  
		mapper.configure(DeserializationConfig.Feature.FAIL_ON_UNKNOWN_PROPERTIES,false);
		//At this point we now have an UTF-8 payload
		JsonSporringOppdragSpecificTopicChildInvoiceContainer container = mapper.readValue(utfPayload.getBytes(), JsonSporringOppdragSpecificTopicChildInvoiceContainer.class); 
		//logger.info(mapper.writeValueAsString(topicListContainer));
		logger.info("[JSON-String payload status=OK]  " + container.getUser());
		for (JsonSporringOppdragSpecificTopicChildInvoiceRecord record : container.getGetfak()){
			//DEBUG
		}
		return container;
	}
	
	/**
	 * 
	 * @param utfPayload
	 * @return
	 * @throws Exception
	 */
	public JsonSporringOppdragSpecificTopicChildFriesokeVeierContainer getChildFriesokeVeierContainer(String utfPayload) throws Exception{
		ObjectMapper mapper = new ObjectMapper();  
		mapper.configure(DeserializationConfig.Feature.FAIL_ON_UNKNOWN_PROPERTIES,false);
		//At this point we now have an UTF-8 payload
		JsonSporringOppdragSpecificTopicChildFriesokeVeierContainer container = mapper.readValue(utfPayload.getBytes(), JsonSporringOppdragSpecificTopicChildFriesokeVeierContainer.class); 
		//logger.info(mapper.writeValueAsString(topicListContainer));
		logger.info("[JSON-String payload status=OK]  " + container.getUser());
		for (JsonSporringOppdragSpecificTopicChildFriesokeVeierRecord record : container.getGetfriesokeveier()){
			//DEBUG
		}
		return container;
	}
	
	/**
	 * 
	 * @param utfPayload
	 * @return
	 * @throws Exception
	 */
	public JsonSporringOppdragSpecificTopicChildHendelseslogContainer getChildHendelseslogContainer(String utfPayload) throws Exception{
		ObjectMapper mapper = new ObjectMapper();  
		mapper.configure(DeserializationConfig.Feature.FAIL_ON_UNKNOWN_PROPERTIES,false);
		//At this point we now have an UTF-8 payload
		JsonSporringOppdragSpecificTopicChildHendelseslogContainer container = mapper.readValue(utfPayload.getBytes(), JsonSporringOppdragSpecificTopicChildHendelseslogContainer.class); 
		//logger.info(mapper.writeValueAsString(topicListContainer));
		logger.info("[JSON-String payload status=OK]  " + container.getUser());
		for (JsonSporringOppdragSpecificTopicChildHendelseslogRecord record : container.getGettrackandtrace()){
			//DEBUG
		}
		return container;
	}
}
