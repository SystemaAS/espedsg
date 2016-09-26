/**
 * 
 */
package no.systema.tvinn.sad.kundekontroll.brreg.mapper;

import org.codehaus.jackson.map.DeserializationConfig;
import org.codehaus.jackson.map.ObjectMapper;

import no.systema.tvinn.sad.kundekontroll.brreg.jsonjackson.JsonEnhetsRegisteretDataCheckContainer;

/**
 * @author Fredrik Möller
 * @date Sep 26, 2016
 * 
 */
public class BrregEnhetsRegisteretMapper {
	
	public JsonEnhetsRegisteretDataCheckContainer getContainer(String utfPayload) throws Exception{
		ObjectMapper mapper = new ObjectMapper();  
		mapper.configure(DeserializationConfig.Feature.FAIL_ON_UNKNOWN_PROPERTIES,false);
		
		//At this point we now have an UTF-8 payload
		return mapper.readValue(utfPayload.getBytes(), JsonEnhetsRegisteretDataCheckContainer.class); 
		
	}
}
