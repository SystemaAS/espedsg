/**
 * 
 */
package no.systema.fraktkalkulator.service;

import no.systema.fraktkalkulator.model.jsonjackson.JsonFraktKalkulatorUserContainer;


/**
 * @author oscardelatorre
 * @date Feb 27, 2015
 *
 */
public interface FraktKalkulatorUserService {
	public JsonFraktKalkulatorUserContainer getContainer(String utfPayload);
	
}
