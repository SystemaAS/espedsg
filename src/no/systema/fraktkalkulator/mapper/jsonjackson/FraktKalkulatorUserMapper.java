/**
 * 
 */
package no.systema.fraktkalkulator.mapper.jsonjackson;


import org.apache.log4j.Logger;
//application library
import no.systema.fraktkalkulator.model.jsonjackson.JsonFraktKalkulatorUserContainer;
import no.systema.main.mapper.jsonjackson.general.ObjectMapperAbstractGrandFather;

//
import java.util.*;

/**
 * 
 * @author oscardelatorre
 * @date Feb 21, 2015
 * 
 * 
 */
public class FraktKalkulatorUserMapper extends ObjectMapperAbstractGrandFather {
	private static final Logger logger = Logger.getLogger(FraktKalkulatorUserMapper.class.getName());
	/**
	 * 
	 * @param utfPayload
	 * @return
	 * @throws Exception
	 */
	public JsonFraktKalkulatorUserContainer getContainer(String utfPayload) throws Exception{
		
		//At this point we now have an UTF-8 payload
		JsonFraktKalkulatorUserContainer container = super.getObjectMapper().readValue(utfPayload.getBytes(), JsonFraktKalkulatorUserContainer.class); 
		//logger.info(mapper.writeValueAsString(topicListContainer));
		//logger.info("Mapping User object from JSON payload...");
		//logger.info("[JSON-String payload status=OK]  " + container.getWsuser());
		
		return container;
	}
	
}
