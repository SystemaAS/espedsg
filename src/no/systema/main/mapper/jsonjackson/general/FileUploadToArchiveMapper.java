/**
 * 
 */
package no.systema.main.mapper.jsonjackson.general;


import org.apache.log4j.Logger;
import org.codehaus.jackson.map.DeserializationConfig;
import org.codehaus.jackson.map.ObjectMapper;
//application library
import no.systema.main.model.jsonjackson.general.JsonFileUploadToArchiveValidationContainer;
//
import java.util.*;

/**
 * 
 * @author oscardelatorre
 * @date Feb 24, 2017
 * 
 * 
 */
public class FileUploadToArchiveMapper {
	private static final Logger logger = Logger.getLogger(FileUploadToArchiveMapper.class.getName());
	/**
	 * 
	 * @param utfPayload
	 * @return
	 * @throws Exception
	 */
	public JsonFileUploadToArchiveValidationContainer getFileUploadValidationContainer(String utfPayload) throws Exception{
		ObjectMapper mapper = new ObjectMapper();  
		mapper.configure(DeserializationConfig.Feature.FAIL_ON_UNKNOWN_PROPERTIES,false);
		
		//At this point we now have an UTF-8 payload
		JsonFileUploadToArchiveValidationContainer container = mapper.readValue(utfPayload.getBytes(), JsonFileUploadToArchiveValidationContainer.class); 
	
		return container;
	}
	
}
