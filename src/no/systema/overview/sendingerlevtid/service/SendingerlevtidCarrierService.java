/**
 * 
 */
package no.systema.overview.sendingerlevtid.service;

import no.systema.overview.sendingerlevtid.model.jsonjackson.carrier.JsonSendingerlevtidCarrierContainer;

/**
 * 
 * @author oscardelatorre
 * @date Oct 16, 2013
 * 
 */
public interface SendingerlevtidCarrierService {
	public JsonSendingerlevtidCarrierContainer getContainer (String utfPayload);
}
