/**
 * 
 */
package no.systema.tror.service.fellesutskrift;

import no.systema.tror.model.jsonjackson.fellesutskrift.JsonTrorOrderFellesutskriftContainer;
import no.systema.tror.mapper.jsonjackson.order.fellesutskrift.JsonTrorOrderFellesutskriftMapper;
import no.systema.tror.service.fellesutskrift.TrorMainOrderFellesutskriftService;

/**
 * 
 * @author oscardelatorre
 * @date Mars 21, 2018
 * 
 * 
 */
public class TrorMainOrderFellesutskriftServiceImpl implements TrorMainOrderFellesutskriftService {

	
	/**
	 * 
	 */
	public JsonTrorOrderFellesutskriftContainer getFellesutskriftContainer(String utfPayload){
		JsonTrorOrderFellesutskriftContainer container = null;
		try{
			JsonTrorOrderFellesutskriftMapper mapper = new JsonTrorOrderFellesutskriftMapper();
			container = mapper.getContainer(utfPayload);
		}catch(Exception e){
			e.printStackTrace();
		}
		return container;
	}
	
}
