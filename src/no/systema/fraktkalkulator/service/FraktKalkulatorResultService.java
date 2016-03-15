/**
 * 
 */
package no.systema.fraktkalkulator.service;

import no.systema.fraktkalkulator.model.jsonjackson.JsonFraktKalkulatorResultContainer;


/**
 * @author oscardelatorre
 * @date Mar 04, 2015
 *
 */
public interface FraktKalkulatorResultService {
	public JsonFraktKalkulatorResultContainer getContainer(String utfPayload);
	
}
