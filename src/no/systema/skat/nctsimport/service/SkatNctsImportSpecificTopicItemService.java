/**
 * 
 */
package no.systema.skat.nctsimport.service;

import no.systema.skat.nctsimport.model.jsonjackson.topic.items.JsonSkatNctsImportSpecificTopicItemContainer;


/**
 * @author oscardelatorre
 * @date Apr 24, 2014
 * 
 */
public interface SkatNctsImportSpecificTopicItemService {
	public JsonSkatNctsImportSpecificTopicItemContainer getNctsImportSpecificTopicItemContainer(String utfPayload);
}
