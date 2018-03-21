/**
 * 
 */
package no.systema.tror.service.fellesutskrift;

import no.systema.tror.model.jsonjackson.fellesutskrift.JsonTrorOrderFellesutskriftContainer;


/**
 * 
 * @author oscardelatorre
 * @date Mar 21, 2018
 * 
 *
 */
public interface TrorMainOrderFellesutskriftService {
	public JsonTrorOrderFellesutskriftContainer getFellesutskriftContainer(String utfPayload);
	

}
