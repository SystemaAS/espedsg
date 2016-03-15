/**
 * 
 */
package no.systema.tds.tdsexport.service;

import no.systema.tds.tdsexport.model.jsonjackson.topic.JsonTdsExportTopicListContainer;

/**
 * @author oscardelatorre
 * @date Nov 25, 2012
 *
 */
public interface TdsExportTopicListService {
	public JsonTdsExportTopicListContainer getTdsExportTopicListContainer(String utfPayload);
}
