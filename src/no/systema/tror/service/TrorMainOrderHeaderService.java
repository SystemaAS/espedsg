/**
 * 
 */
package no.systema.tror.service;

import no.systema.tror.model.jsonjackson.archive.JsonTrorOrderHeaderArchiveContainer;

/**
 * 
 * @author oscardelatorre
 * @date Sep 15, 2017
 * 
 *
 */
public interface TrorMainOrderHeaderService {
	public JsonTrorOrderHeaderArchiveContainer getArchiveContainer(String utfPayload);

}
