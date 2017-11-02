/**
 * 
 */
package no.systema.tror.mapper.jsonjackson;

//jackson library
import org.apache.log4j.Logger;
import org.codehaus.jackson.map.DeserializationConfig;
import org.codehaus.jackson.map.ObjectMapper;
//application library
import no.systema.tror.model.jsonjackson.JsonTrorOrderHeaderContainer;
import no.systema.tror.model.jsonjackson.JsonTrorOrderHeaderRecord;
import no.systema.tror.model.jsonjackson.JsonTrorOrderHeaderRecordStatus;



/**
 * @author oscardelatorre
 * @date Aug 11, 2017
 * 
 */
public class JsonTrorOrderHeaderMapperLandimport {
	private static final Logger logger = Logger.getLogger(JsonTrorOrderHeaderMapperLandimport.class.getName());
	/**
	 * 
	 * @param utfPayload
	 * @return
	 * @throws Exception
	 */
	public JsonTrorOrderHeaderContainer getContainer(String utfPayload) throws Exception{
		ObjectMapper mapper = new ObjectMapper();  
		mapper.configure(DeserializationConfig.Feature.FAIL_ON_UNKNOWN_PROPERTIES,false);
		//At this point we now have an UTF-8 payload
		JsonTrorOrderHeaderContainer container = mapper.readValue(utfPayload.getBytes(), JsonTrorOrderHeaderContainer.class); 
		logger.info("[JSON-String payload status=OK]  " + container.getUser());
		for (JsonTrorOrderHeaderRecord record : container.getDtoList()){
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
	public JsonTrorOrderHeaderContainer getContainerStatusUpdate(String utfPayload) throws Exception{
		ObjectMapper mapper = new ObjectMapper();  
		mapper.configure(DeserializationConfig.Feature.FAIL_ON_UNKNOWN_PROPERTIES,false);
		//At this point we now have an UTF-8 payload
		JsonTrorOrderHeaderContainer container = mapper.readValue(utfPayload.getBytes(), JsonTrorOrderHeaderContainer.class); 
		logger.info("[JSON-String payload status=OK]  " + container.getUser());
		for (JsonTrorOrderHeaderRecordStatus record : container.getList()){
			//DEBUG
		}
		
		return container;
	}
	
	
}
