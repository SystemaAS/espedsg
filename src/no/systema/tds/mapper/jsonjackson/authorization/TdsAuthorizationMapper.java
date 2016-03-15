/**
 * 
 */
package no.systema.tds.mapper.jsonjackson.authorization;

//jackson library
import org.apache.log4j.Logger;
import org.codehaus.jackson.map.DeserializationConfig;
import org.codehaus.jackson.map.ObjectMapper;
//application library
import no.systema.tds.model.jsonjackson.authorization.JsonTdsAuthorizationContainer;
import no.systema.tds.model.jsonjackson.authorization.JsonTdsAuthorizationRecord;
import no.systema.tds.model.jsonjackson.authorization.JsonTdsPkiSmsCodeContainer;
import no.systema.tds.model.jsonjackson.authorization.JsonTdsPkiSmsCodeRecord;

import java.util.*;

/**
 * @author oscardelatorre
 * 
 */
public class TdsAuthorizationMapper {
	private static final Logger logger = Logger.getLogger(TdsAuthorizationMapper.class.getName());
	
	public JsonTdsAuthorizationContainer getContainer(String utfPayload) throws Exception{
		ObjectMapper mapper = new ObjectMapper();  
		mapper.configure(DeserializationConfig.Feature.FAIL_ON_UNKNOWN_PROPERTIES,false);
		JsonTdsAuthorizationContainer container = null;
		
		if(utfPayload!=null){
			//At this point we now have an UTF-8 payload
			container = mapper.readValue(utfPayload.getBytes(), JsonTdsAuthorizationContainer.class); 
			//logger.info(mapper.writeValueAsString(topicListContainer));
			logger.info("Mapping object from JSON payload...");
			logger.info("[JSON-String payload status=OK]  " + container.getUser());
			
			//DEBUG
			Collection<JsonTdsAuthorizationRecord> fields = container.getTdsBehorigKontroll();
			for(JsonTdsAuthorizationRecord record : fields){
				logger.info("bpki: " + record.getBpki());
				logger.info("btds: " + record.getBtds());
				logger.info("sign: " + record.getSign());
			}
		}
		return container;
	}
	
	
	/**
	 * PKI sms code mapper
	 * 
	 * @param utfPayload
	 * @return
	 * @throws Exception
	 */
	public JsonTdsPkiSmsCodeContainer getCodeContainerPkiSmsCode(String utfPayload) throws Exception{
		ObjectMapper mapper = new ObjectMapper();  
		mapper.configure(DeserializationConfig.Feature.FAIL_ON_UNKNOWN_PROPERTIES,false);
		
		//At this point we now have an UTF-8 payload
		JsonTdsPkiSmsCodeContainer container = mapper.readValue(utfPayload.getBytes(), JsonTdsPkiSmsCodeContainer.class); 
		//logger.info(mapper.writeValueAsString(topicListContainer));
		logger.info("Mapping object from JSON payload...");
		logger.info("[JSON-String payload status=OK]  " + container.getUser());
		
		//DEBUG
		Collection<JsonTdsPkiSmsCodeRecord> fields = container.getTdsSkapaengangskod();
		for(JsonTdsPkiSmsCodeRecord record : fields){
			logger.info("smsCode: " + record.getSmsKod());
		}
			
		return container;
	}
	
}
