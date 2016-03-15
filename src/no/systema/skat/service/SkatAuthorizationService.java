/**
 * 
 */
package no.systema.skat.service;

import no.systema.skat.model.jsonjackson.authorization.JsonSkatAuthorizationContainer;



/**
 * @author oscardelatorre
 * @date March 2014
 * 
 */
public interface SkatAuthorizationService {
	public JsonSkatAuthorizationContainer getContainer(String utfPayload) throws Exception;
	
}
