/**
 * 
 */
package no.systema.fraktkalkulator.service;

import no.systema.fraktkalkulator.model.jsonjackson.dropdownlist.JsonFraktKalkulatorDropDownContainer;


/**
 * @author oscardelatorre
 * @date Mar 9, 2015
 *
 */
public interface FraktKalkulatorDropDownService {
	public JsonFraktKalkulatorDropDownContainer getContainer(String utfPayload);
	
}
