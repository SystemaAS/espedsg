/**
 * 
 */
package no.systema.skat.skatexport.service;

import no.systema.skat.skatexport.model.jsonjackson.topic.items.JsonSkatExportSpecificTopicItemContainer;
import no.systema.skat.skatexport.model.jsonjackson.topic.items.JsonSkatExportSpecificTopicItemAvgifterContainer;
import no.systema.skat.model.jsonjackson.JsonSkatAutoControlErrorContainer;

/**
 * 
 * @author oscardelatorre
 * @date Mar 10, 2014
 * 
 *
 */
public interface SkatExportSpecificTopicItemService {
	
	public JsonSkatExportSpecificTopicItemContainer getSkatExportSpecificTopicItemContainer(String utfPayload);
	public JsonSkatExportSpecificTopicItemAvgifterContainer getSkatExportSpecificTopicItemAvgifterContainer(String utfPayload);
	public JsonSkatAutoControlErrorContainer getSkatExportSpecificTopicItemAutoControlErrorContainer(String utfPayload);
	
	
}
