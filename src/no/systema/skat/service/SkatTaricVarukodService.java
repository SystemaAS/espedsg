/**
 * 
 */
package no.systema.skat.service;

import no.systema.skat.model.jsonjackson.codes.JsonSkatTaricVarukodContainer;

/**
 * @author oscardelatorre
 * @date Feb 4, 2014
 * 
 *
 */
public interface SkatTaricVarukodService {
	public JsonSkatTaricVarukodContainer getContainer(String utfPayload) throws Exception;
}
