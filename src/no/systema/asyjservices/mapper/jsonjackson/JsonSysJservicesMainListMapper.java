/**
 * 
 */
package no.systema.asyjservices.mapper.jsonjackson;

//jackson library
import org.apache.log4j.Logger;

//application library
import no.systema.asyjservices.model.jsonjackson.JsonSysJservicesMainListContainer;
import no.systema.asyjservices.model.jsonjackson.JsonSysJservicesMainListRecord;
import no.systema.main.mapper.jsonjackson.general.ObjectMapperAbstractGrandFather;


/**
 * @author oscardelatorre
 * @date Nov 4, 2015
 * 
 */
public class JsonSysJservicesMainListMapper extends ObjectMapperAbstractGrandFather {
	private static final Logger logger = Logger.getLogger(JsonSysJservicesMainListMapper.class.getName());
	/**
	 * 
	 * @param utfPayload
	 * @return
	 * @throws Exception
	 */
	public JsonSysJservicesMainListContainer getContainer(String utfPayload) throws Exception{
		//At this point we now have an UTF-8 payload
		JsonSysJservicesMainListContainer container = super.getObjectMapper().readValue(utfPayload.getBytes(), JsonSysJservicesMainListContainer.class); 
		logger.info("[JSON-String payload status=OK]  " + container.getUser());
		for (JsonSysJservicesMainListRecord record : container.getList()){
			//DEBUG
		}
		return container;
	}
	
}
