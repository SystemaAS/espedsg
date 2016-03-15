/**
 * 
 */
package no.systema.skat.nctsimport.service;

import no.systema.skat.nctsimport.model.jsonjackson.topic.JsonSkatNctsImportTopicListContainer;

/**
 * 
 * @author oscardelatorre
 * @date Apr 23, 2014
 * 
 *
 */
public interface SkatNctsImportTopicListService {
	public JsonSkatNctsImportTopicListContainer getNctsImportTopicListContainer(String utfPayload);
}
