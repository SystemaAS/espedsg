/**
 * 
 */
package no.systema.sporringoppdrag.mapper.jsonjackson;

//jackson library
import org.apache.log4j.Logger;
import org.codehaus.jackson.map.DeserializationConfig;
import org.codehaus.jackson.map.ObjectMapper;
//application library
import no.systema.sporringoppdrag.model.jsonjackson.topic.invoice.JsonSporringOppdragSpecificTopicSingleChildInvoiceContainer;
import no.systema.sporringoppdrag.model.jsonjackson.topic.invoice.JsonSporringOppdragSpecificTopicSingleChildInvoiceRecord;
import no.systema.sporringoppdrag.model.jsonjackson.topic.colli.JsonSporringOppdragSpecificTopicSingleChildColliContainer;
import no.systema.sporringoppdrag.model.jsonjackson.topic.colli.JsonSporringOppdragSpecificTopicSingleChildColliRecord;


/**
 * 
 * @author oscardelatorre
 * @date Feb 25, 2015
 * 
 * 
 */
public class SporringOppdragSpecificTopicSingleChildMapper {
	private static final Logger logger = Logger.getLogger(SporringOppdragSpecificTopicSingleChildMapper.class.getName());
	/**
	 * Invoice child
	 * @param utfPayload
	 * @return
	 * @throws Exception
	 */
	public JsonSporringOppdragSpecificTopicSingleChildInvoiceContainer getInvoiceContainer(String utfPayload) throws Exception{
		ObjectMapper mapper = new ObjectMapper();  
		mapper.configure(DeserializationConfig.Feature.FAIL_ON_UNKNOWN_PROPERTIES,false);
		//At this point we now have an UTF-8 payload
		JsonSporringOppdragSpecificTopicSingleChildInvoiceContainer container = mapper.readValue(utfPayload.getBytes(), JsonSporringOppdragSpecificTopicSingleChildInvoiceContainer.class); 
		//logger.info(mapper.writeValueAsString(topicListContainer));
		logger.info("[JSON-String payload status=OK]  " + container.getUser());
		for (JsonSporringOppdragSpecificTopicSingleChildInvoiceRecord record : container.getInvlindet()){
			//DEBUG
		}
		return container;
	}
	/**
	 * Colli child
	 * @param utfPayload
	 * @return
	 * @throws Exception
	 */
	public JsonSporringOppdragSpecificTopicSingleChildColliContainer getColliContainer(String utfPayload) throws Exception{
		ObjectMapper mapper = new ObjectMapper();  
		mapper.configure(DeserializationConfig.Feature.FAIL_ON_UNKNOWN_PROPERTIES,false);
		//At this point we now have an UTF-8 payload
		JsonSporringOppdragSpecificTopicSingleChildColliContainer container = mapper.readValue(utfPayload.getBytes(), JsonSporringOppdragSpecificTopicSingleChildColliContainer.class); 
		//logger.info(mapper.writeValueAsString(topicListContainer));
		logger.info("[JSON-String payload status=OK]  " + container.getUser());
		for (JsonSporringOppdragSpecificTopicSingleChildColliRecord record : container.getDspcolli()){
			//DEBUG
		}
		return container;
	}
	
}
