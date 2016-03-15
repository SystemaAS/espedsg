/**
 * 
 */
package no.systema.fraktkalkulator.mapper.jsonjackson;


import org.apache.log4j.Logger;
import org.codehaus.jackson.map.DeserializationConfig;
import org.codehaus.jackson.map.ObjectMapper;
//application library
import no.systema.fraktkalkulator.model.jsonjackson.customer.JsonFraktKalkulatorCustomerContainer;
import no.systema.fraktkalkulator.model.jsonjackson.customer.JsonFraktKalkulatorCustomerRecord;


//
import java.util.*;

/**
 * 
 * @author oscardelatorre
 * @date Feb 21, 2015
 * 
 * 
 */
public class FraktKalkulatorCustomerMapper {
	private static final Logger logger = Logger.getLogger(FraktKalkulatorCustomerMapper.class.getName());
	/**
	 * 
	 * @param utfPayload
	 * @return
	 * @throws Exception
	 */
	public JsonFraktKalkulatorCustomerContainer getContainer(String utfPayload) throws Exception{
		ObjectMapper mapper = new ObjectMapper();  
		mapper.configure(DeserializationConfig.Feature.FAIL_ON_UNKNOWN_PROPERTIES,false);
		
		//At this point we now have an UTF-8 payload
		JsonFraktKalkulatorCustomerContainer customerListContainer = mapper.readValue(utfPayload.getBytes(), JsonFraktKalkulatorCustomerContainer.class); 
		//logger.info("Mapping Customer object from JSON payload...");
		//logger.info("[JSON-String payload status=OK]  " + customerListContainer.getUser());
		
		//DEBUG
		Collection<JsonFraktKalkulatorCustomerRecord> fields = customerListContainer.getCustomerlist();
		for(JsonFraktKalkulatorCustomerRecord record : fields){
			//logger.info("knavn: " + record.getKnavn());
			//logger.info("kundnr: " + record.getKundnr());
		}
		return customerListContainer;
	}
	
}
