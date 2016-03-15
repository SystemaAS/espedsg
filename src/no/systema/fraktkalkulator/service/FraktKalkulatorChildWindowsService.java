/**
 * 
 */
package no.systema.fraktkalkulator.service;

import no.systema.fraktkalkulator.model.jsonjackson.customer.JsonFraktKalkulatorCustomerContainer;
import no.systema.main.model.jsonjackson.general.postalcodes.JsonPostalCodesContainer;



/**
 * @author oscardelatorre
 * @date Feb 21, 2015
 *
 */
public interface FraktKalkulatorChildWindowsService {
	public JsonFraktKalkulatorCustomerContainer getCustomerContainer(String utfPayload);
	public JsonPostalCodesContainer getPostalCodesContainer(String utfPayload);
	
}
