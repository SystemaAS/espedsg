/**
 * 
 */
package no.systema.efaktura.service;

import no.systema.efaktura.model.jsonjackson.childwindow.JsonEfakturaCustomerContainer;


/**
 * 
 * @author oscardelatorre
 * @date Nov 30, 2015
 * 
 *
 */
public interface EfakturaChildWindowService {
	public JsonEfakturaCustomerContainer getCustomerContainer(String utfPayload);
	
}
