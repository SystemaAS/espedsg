/**
 * 
 */
package no.systema.efaktura.service;

import no.systema.efaktura.model.jsonjackson.JsonEfakturaMainListContainer;


/**
 * 
 * @author oscardelatorre
 * @date Jun 22, 2015
 * 
 *
 */
public interface EfakturaListService {
	public JsonEfakturaMainListContainer getMainListContainer(String utfPayload);

}
