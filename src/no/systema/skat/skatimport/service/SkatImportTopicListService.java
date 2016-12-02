/**
 * 
 */
package no.systema.skat.skatimport.service;

import no.systema.skat.skatimport.model.jsonjackson.topic.JsonSkatImportTopicListExternalRefContainer;
import no.systema.skat.skatimport.model.jsonjackson.topic.JsonSkatImportTopicListContainer;

/**
 * 
 * @author oscardelatorre
 * @date Jan 27, 2014
 * 
 *
 */
public interface SkatImportTopicListService {
	public JsonSkatImportTopicListContainer getSkatImportTopicListContainer(String utfPayload);
	public JsonSkatImportTopicListExternalRefContainer getSkatImportTopicListExternalRefContainer(String utfPayload);
}
