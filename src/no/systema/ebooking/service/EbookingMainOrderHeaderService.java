/**
 * 
 */
package no.systema.ebooking.service;

import no.systema.ebooking.model.jsonjackson.JsonMainOrderHeaderContainer;
import no.systema.ebooking.model.jsonjackson.JsonMainOrderHeaderFraktbrevContainer;




/**
 * 
 * @author oscardelatorre
 * @date Jan 06, 2017
 * 
 *
 */
public interface EbookingMainOrderHeaderService {
	public JsonMainOrderHeaderContainer getContainer(String utfPayload);
	public JsonMainOrderHeaderFraktbrevContainer getFraktbrevContainer(String utfPayload);
	
}
