/**
 * 
 */
package no.systema.tror.service;

import no.systema.tror.model.jsonjackson.JsonTrorOrderHeaderContainer;


/**
 * 
 * @author oscardelatorre
 * @date Aug 11, 2017
 * 
 *
 */
public interface TrorMainOrderHeaderService {
	public JsonTrorOrderHeaderContainer getOrderHeaderContainer(String utfPayload);

}
