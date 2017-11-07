/**
 * 
 */
package no.systema.fraktkalkulator.mapper.jsonjackson;


import org.apache.log4j.Logger;

//application library
import no.systema.fraktkalkulator.model.jsonjackson.JsonFraktKalkulatorResultContainer;
import no.systema.main.mapper.jsonjackson.general.ObjectMapperAbstractGrandFather;

//
import java.util.*;

/**
 * 
 * @author oscardelatorre
 * @date Mar 04, 2015
 * 
 * 
 */
public class FraktKalkulatorResultMapper extends ObjectMapperAbstractGrandFather {
	private static final Logger logger = Logger.getLogger(FraktKalkulatorResultMapper.class.getName());
	/**
	 * 
	 * @param utfPayload
	 * @return
	 * @throws Exception
	 */
	public JsonFraktKalkulatorResultContainer getContainer(String utfPayload) throws Exception{
		
		//At this point we now have an UTF-8 payload
		JsonFraktKalkulatorResultContainer container = super.getObjectMapper().readValue(utfPayload.getBytes(), JsonFraktKalkulatorResultContainer.class); 
		
		return container;
	}
	
}
