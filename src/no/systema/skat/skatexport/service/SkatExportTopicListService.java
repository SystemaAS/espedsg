/**
 * 
 */
package no.systema.skat.skatexport.service;

import no.systema.skat.skatexport.model.jsonjackson.topic.JsonSkatExportTopicListContainer;

/**
 * 
 * @author oscardelatorre
 * @date Feb 26, 2014
 * 
 *
 */
public interface SkatExportTopicListService {
	public JsonSkatExportTopicListContainer getSkatExportTopicListContainer(String utfPayload);
}
