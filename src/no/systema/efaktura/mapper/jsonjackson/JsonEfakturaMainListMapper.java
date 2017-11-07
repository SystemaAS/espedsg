/**
 * 
 */
package no.systema.efaktura.mapper.jsonjackson;

//jackson library
import org.apache.log4j.Logger;

//application library
import no.systema.efaktura.model.jsonjackson.JsonEfakturaMainListContainer;
import no.systema.efaktura.model.jsonjackson.JsonEfakturaMainListRecord;
import no.systema.main.mapper.jsonjackson.general.ObjectMapperAbstractGrandFather;


/**
 * @author oscardelatorre
 * @date Jun 22, 2015
 * 
 */
public class JsonEfakturaMainListMapper extends ObjectMapperAbstractGrandFather {
	private static final Logger logger = Logger.getLogger(JsonEfakturaMainListMapper.class.getName());
	/**
	 * 
	 * @param utfPayload
	 * @return
	 * @throws Exception
	 */
	public JsonEfakturaMainListContainer getContainer(String utfPayload) throws Exception{
		//At this point we now have an UTF-8 payload
		JsonEfakturaMainListContainer container = super.getObjectMapper().readValue(utfPayload.getBytes(), JsonEfakturaMainListContainer.class); 
		logger.info("[JSON-String payload status=OK]  " + container.getUser());
		for (JsonEfakturaMainListRecord record : container.getOrderList()){
			//DEBUG
		}
		return container;
	}
	
}
