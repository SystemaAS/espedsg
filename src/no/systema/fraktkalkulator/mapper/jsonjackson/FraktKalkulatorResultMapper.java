/**
 * 
 */
package no.systema.fraktkalkulator.mapper.jsonjackson;


import org.apache.log4j.Logger;
import org.codehaus.jackson.map.DeserializationConfig;
import org.codehaus.jackson.map.ObjectMapper;
//application library
import no.systema.fraktkalkulator.model.jsonjackson.JsonFraktKalkulatorResultContainer;


//
import java.util.*;

/**
 * 
 * @author oscardelatorre
 * @date Mar 04, 2015
 * 
 * 
 */
public class FraktKalkulatorResultMapper {
	private static final Logger logger = Logger.getLogger(FraktKalkulatorResultMapper.class.getName());
	/**
	 * 
	 * @param utfPayload
	 * @return
	 * @throws Exception
	 */
	public JsonFraktKalkulatorResultContainer getContainer(String utfPayload) throws Exception{
		ObjectMapper mapper = new ObjectMapper();  
		mapper.configure(DeserializationConfig.Feature.FAIL_ON_UNKNOWN_PROPERTIES,false);
		
		//At this point we now have an UTF-8 payload
		JsonFraktKalkulatorResultContainer container = mapper.readValue(utfPayload.getBytes(), JsonFraktKalkulatorResultContainer.class); 
		
		return container;
	}
	
}
