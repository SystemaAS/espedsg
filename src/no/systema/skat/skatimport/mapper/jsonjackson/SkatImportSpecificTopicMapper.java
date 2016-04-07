/**
 * 
 */
package no.systema.skat.skatimport.mapper.jsonjackson;

//jackson library
import org.apache.log4j.Logger;
import org.codehaus.jackson.map.DeserializationConfig;
import org.codehaus.jackson.map.ObjectMapper;
//application library
import no.systema.skat.skatimport.model.jsonjackson.topic.JsonSkatImportSpecificTopicFaktTotalContainer;
import no.systema.skat.skatimport.model.jsonjackson.topic.JsonSkatImportSpecificTopicContainer;
import no.systema.skat.skatimport.model.jsonjackson.topic.JsonSkatImportSpecificTopicRecord;
import no.systema.skat.skatimport.model.jsonjackson.topic.JsonSkatImportSpecificTopicOmbudContainer;
import no.systema.skat.skatimport.model.jsonjackson.topic.JsonSkatImportSpecificTopicOmbudRecord;
import no.systema.skat.skatimport.model.jsonjackson.topic.JsonSkatImportSpecificTopicRecalculationContainer;



//
import java.util.*;

/**
 * @author oscardelatorre
 * @date Jan 27, 2014
 * 
 * 
 */
public class SkatImportSpecificTopicMapper {
	private static final Logger logger = Logger.getLogger(SkatImportSpecificTopicMapper.class.getName());
	
	/**
	 * 
	 * @param utfPayload
	 * @return
	 * @throws Exception
	 */
	public JsonSkatImportSpecificTopicContainer getContainer(String utfPayload) throws Exception{
		ObjectMapper mapper = new ObjectMapper();  
		mapper.configure(DeserializationConfig.Feature.FAIL_ON_UNKNOWN_PROPERTIES,false);
		
		//At this point we now have an UTF-8 payload
		JsonSkatImportSpecificTopicContainer container = mapper.readValue(utfPayload.getBytes(), JsonSkatImportSpecificTopicContainer.class); 
		//logger.info(mapper.writeValueAsString(topicListContainer));
		logger.info("[JSON-String payload status=OK]  " + container.getUser());
		
		//DEBUG
		Collection<JsonSkatImportSpecificTopicRecord> fields = container.getOneorder();
		for(JsonSkatImportSpecificTopicRecord record : fields){
			//logger.info("DKIH_t07a: " + record.getDkih_t07a());
			//logger.info("DKIH_t07d: " + record.getDkih_t07d());
		}
			
		return container;
	}
	
	/**
	 * 
	 * @param utfPayload
	 * @return
	 * @throws Exception
	 */
	public JsonSkatImportSpecificTopicOmbudContainer getOmbudContainer(String utfPayload) throws Exception{
		ObjectMapper mapper = new ObjectMapper();  
		mapper.configure(DeserializationConfig.Feature.FAIL_ON_UNKNOWN_PROPERTIES,false);
		
		//At this point we now have an UTF-8 payload
		JsonSkatImportSpecificTopicOmbudContainer container = mapper.readValue(utfPayload.getBytes(), JsonSkatImportSpecificTopicOmbudContainer.class); 
		//logger.info(mapper.writeValueAsString(topicListContainer));
		logger.info("[JSON-String payload status=OK]  " + container.getUser());
		
		//DEBUG
		
		Collection<JsonSkatImportSpecificTopicOmbudRecord> fields = container.getGetdepinf();
		for(JsonSkatImportSpecificTopicOmbudRecord record : fields){
			/*logger.info("SVEH_SYAV: " + record.getSveh_syav());
			logger.info("SVEH_SYOP: " + record.getSveh_syop());
			logger.info("SVEH_AVTL: " + record.getSveh_avtl());
			logger.info("SVEH_MOPA: " + record.getSveh_mopa());
			logger.info("SVEH_MOLK: " + record.getSveh_molk());
			logger.info("SVEH_FABL: " + record.getSveh_fabl());
			logger.info("SVEH_FATX: " + record.getSveh_fatx());
			logger.info("SVEH_VAKD: " + record.getSveh_vakd());
			*/
		}
			
		return container;
	}
	
	/**
	 * 
	 * @param utfPayload
	 * @return
	 * @throws Exception
	 */
	public JsonSkatImportSpecificTopicRecalculationContainer getRecalculationContainer(String utfPayload) throws Exception{
		ObjectMapper mapper = new ObjectMapper();  
		mapper.configure(DeserializationConfig.Feature.FAIL_ON_UNKNOWN_PROPERTIES,false);
		
		//At this point we now have an UTF-8 payload
		JsonSkatImportSpecificTopicRecalculationContainer container = mapper.readValue(utfPayload.getBytes(), JsonSkatImportSpecificTopicRecalculationContainer.class); 
		//logger.info(mapper.writeValueAsString(topicListContainer));
		logger.info("[JSON-String payload status=OK]  " + container.getUser());
			
		return container;
	}
	
	/**
	 * 
	 * @param utfPayload
	 * @return
	 * @throws Exception
	 */
	public JsonSkatImportSpecificTopicFaktTotalContainer getFaktTotalContainer(String utfPayload) throws Exception{
		ObjectMapper mapper = new ObjectMapper();  
		mapper.configure(DeserializationConfig.Feature.FAIL_ON_UNKNOWN_PROPERTIES,false);
		
		//At this point we now have an UTF-8 payload
		JsonSkatImportSpecificTopicFaktTotalContainer container = mapper.readValue(utfPayload.getBytes(), JsonSkatImportSpecificTopicFaktTotalContainer.class); 
		//logger.info(mapper.writeValueAsString(topicListContainer));
		logger.info("[JSON-String payload status=OK]  " + container.getUser());
		
		return container;
	}
	
	
}
