/**
 * 
 */
package no.systema.tror.mapper.jsonjackson;

//jackson library
import org.apache.log4j.Logger;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper; 
//application library
import no.systema.tror.model.jsonjackson.JsonTrorOrderHeaderContainer;
import no.systema.tror.model.jsonjackson.JsonTrorOrderHeaderDummyContainer;
import no.systema.tror.model.jsonjackson.JsonTrorOrderHeaderRecord;
import no.systema.tror.model.jsonjackson.JsonTrorOrderHeaderRecordStatus;



/**
 * @author oscardelatorre
 * @date Dec 28, 2017
 * 
 */
public class JsonTrorOrderHeaderMapperLandExport {
	private static final Logger logger = Logger.getLogger(JsonTrorOrderHeaderMapperLandExport.class.getName());
	/**
	 * 
	 * @param utfPayload
	 * @return
	 * @throws Exception
	 */
	public JsonTrorOrderHeaderContainer getContainer(String utfPayload) throws Exception{
		ObjectMapper mapper = new ObjectMapper();  
		mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES,false);
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
		mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES,false);
		//At this point we now have an UTF-8 payload
		JsonTrorOrderHeaderContainer container = mapper.readValue(utfPayload.getBytes(), JsonTrorOrderHeaderContainer.class); 
		logger.info("[JSON-String payload status=OK]  " + container.getUser());
		for (JsonTrorOrderHeaderRecordStatus record : container.getList()){
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
	public JsonTrorOrderHeaderDummyContainer getDummyContainer(String utfPayload) throws Exception{
		ObjectMapper mapper = new ObjectMapper();  
		mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES,false);
		//At this point we now have an UTF-8 payload
		JsonTrorOrderHeaderDummyContainer container = mapper.readValue(utfPayload.getBytes(), JsonTrorOrderHeaderDummyContainer.class); 
		logger.info("[JSON-String payload status=OK]  " + container.getUser());
		for (JsonTrorOrderHeaderRecord record : container.getList()){
			//DEBUG
		}
		
		return container;
	}
	
	
}
