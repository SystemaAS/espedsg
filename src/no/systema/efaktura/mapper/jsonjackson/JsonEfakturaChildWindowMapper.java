/**
 * 
 */
package no.systema.efaktura.mapper.jsonjackson;

//jackson library
import org.apache.log4j.Logger;
import org.codehaus.jackson.map.DeserializationConfig;
import org.codehaus.jackson.map.ObjectMapper;
//application library
import no.systema.efaktura.model.jsonjackson.childwindow.JsonEfakturaCustomerContainer;
import no.systema.efaktura.model.jsonjackson.childwindow.JsonEfakturaCustomerRecord;;




/**
 * @author oscardelatorre
 * @date Nov 30, 2015
 * 
 */
public class JsonEfakturaChildWindowMapper {
	private static final Logger logger = Logger.getLogger(JsonEfakturaChildWindowMapper.class.getName());
	
	/**
	 * Get Customer
	 * @param utfPayload
	 * @return
	 * @throws Exception
	 */
	public JsonEfakturaCustomerContainer getCustomerContainer(String utfPayload) throws Exception{
		ObjectMapper mapper = new ObjectMapper();  
		mapper.configure(DeserializationConfig.Feature.FAIL_ON_UNKNOWN_PROPERTIES,false);
		//At this point we now have an UTF-8 payload
		JsonEfakturaCustomerContainer container = mapper.readValue(utfPayload.getBytes(), JsonEfakturaCustomerContainer.class); 
		logger.info("[JSON-String payload status=OK]  " + container.getUser());
		for (JsonEfakturaCustomerRecord record : container.getInqcustomer()){
			//DEBUG
		}
		return container;
	}

}
