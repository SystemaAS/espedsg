/**
 * 
 */
package no.systema.skat.nctsexport.service;

import no.systema.skat.nctsexport.model.jsonjackson.topic.JsonSkatNctsExportTopicListContainer;

/**
 * @author oscardelatorre
 * @date Apr 14, 2014
 *
 */
public interface SkatNctsExportTopicListService {
	public JsonSkatNctsExportTopicListContainer getNctsExportTopicListContainer(String utfPayload);
}
