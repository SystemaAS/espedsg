/**
 * 
 */
package no.systema.skat.service;

import no.systema.skat.model.jsonjackson.tullkontor.JsonSkatTullkontorContainer;

/**
 * @author oscardelatorre
 * @date Apr 18, 2014
 *
 */
public interface SkatTullkontorService {
	public JsonSkatTullkontorContainer getSkatTullkontorContainer(String utfPayload);
}
