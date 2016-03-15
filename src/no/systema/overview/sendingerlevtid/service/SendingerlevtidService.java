/**
 * 
 */
package no.systema.overview.sendingerlevtid.service;

import no.systema.overview.sendingerlevtid.model.jsonjackson.JsonTopicContainer;

/**
 * 
 * @author oscardelatorre
 * @date Oct 4, 2013
 * 
 */
public interface SendingerlevtidService {
	public JsonTopicContainer getContainer (String utfPayload);
}
