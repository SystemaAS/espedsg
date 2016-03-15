/**
 * 
 */
package no.systema.skat.service;

import no.systema.skat.model.jsonjackson.customer.JsonSkatCustomerContainer;

/**
 * @author oscardelatorre
 * @date Apr 18, 2014
 *
 */
public interface SkatCustomerService {
	public JsonSkatCustomerContainer getSkatCustomerContainer(String utfPayload);
}
