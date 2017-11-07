/**
 * 
 */
package no.systema.tror.mapper.jsonjackson.order.logging;

//jackson library
import org.apache.log4j.Logger;
import com.fasterxml.jackson.databind.DeserializationConfig;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper; 
//application library
import no.systema.tror.model.jsonjackson.logging.JsonTrorOrderHeaderTrackAndTraceLoggingContainer;
import no.systema.tror.model.jsonjackson.logging.JsonTrorOrderHeaderTrackAndTraceLoggingRecord;

/**
 * @author oscardelatorre
 * @date Set 18, 2017
 * 
 */
public class JsonTrorOrderTrackAndTraceLoggingMapper {
	private static final Logger logger = Logger.getLogger(JsonTrorOrderTrackAndTraceLoggingMapper.class.getName());
	/**
	 * 
	 * @param utfPayload
	 * @return
	 * @throws Exception
	 */
	public JsonTrorOrderHeaderTrackAndTraceLoggingContainer getContainer(String utfPayload) throws Exception{
		ObjectMapper mapper = new ObjectMapper();  
		mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES,false);
		//At this point we now have an UTF-8 payload
		JsonTrorOrderHeaderTrackAndTraceLoggingContainer container = mapper.readValue(utfPayload.getBytes(), JsonTrorOrderHeaderTrackAndTraceLoggingContainer.class); 
		//logger.info("[JSON-String payload status=OK]  " + container.getUser());
		for (JsonTrorOrderHeaderTrackAndTraceLoggingRecord record : container.getTrackTraceEvents()){
			//DEBUG
		}
		return container;
	}
	
}
