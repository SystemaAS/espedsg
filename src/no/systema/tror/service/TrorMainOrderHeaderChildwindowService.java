/**
 * 
 */
package no.systema.tror.service;

import no.systema.tror.model.jsonjackson.order.childwindow.JsonTrorCarrierContainer;

/**
 * 
 * @author oscardelatorre
 * @date Aug 31, 2017
 * 
 *
 */
public interface TrorMainOrderHeaderChildwindowService {
	public JsonTrorCarrierContainer getCarrierListContainer(String utfPayload);
	

}
