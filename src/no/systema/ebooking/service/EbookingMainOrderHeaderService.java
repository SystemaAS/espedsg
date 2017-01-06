/**
 * 
 */
package no.systema.ebooking.service;

import no.systema.ebooking.model.jsonjackson.JsonMainOrderHeaderContainer;



/**
 * 
 * @author oscardelatorre
 * @date Jan 06, 2017
 * 
 *
 */
public interface EbookingMainOrderHeaderService {
	public JsonMainOrderHeaderContainer getContainer(String utfPayload);
}
