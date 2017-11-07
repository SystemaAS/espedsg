/**
 * 
 */
package no.systema.efaktura.mapper.jsonjackson;

//jackson library
import org.apache.log4j.Logger;

//application library
import no.systema.efaktura.model.jsonjackson.childwindow.JsonEfakturaCustomerContainer;
import no.systema.efaktura.model.jsonjackson.childwindow.JsonEfakturaCustomerRecord;
import no.systema.main.mapper.jsonjackson.general.ObjectMapperAbstractGrandFather;;




/**
 * @author oscardelatorre
 * @date Nov 30, 2015
 * 
 */
public class JsonEfakturaChildWindowMapper extends ObjectMapperAbstractGrandFather {
	private static final Logger logger = Logger.getLogger(JsonEfakturaChildWindowMapper.class.getName());
	
	/**
	 * Get Customer
	 * @param utfPayload
	 * @return
	 * @throws Exception
	 */
	public JsonEfakturaCustomerContainer getCustomerContainer(String utfPayload) throws Exception{
		//At this point we now have an UTF-8 payload
		JsonEfakturaCustomerContainer container = super.getObjectMapper().readValue(utfPayload.getBytes(), JsonEfakturaCustomerContainer.class); 
		logger.info("[JSON-String payload status=OK]  " + container.getUser());
		for (JsonEfakturaCustomerRecord record : container.getInqcustomer()){
			//DEBUG
		}
		return container;
	}

}
