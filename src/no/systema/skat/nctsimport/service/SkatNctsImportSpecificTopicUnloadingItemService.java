/**
 * 
 */
package no.systema.skat.nctsimport.service;

import no.systema.skat.nctsimport.model.jsonjackson.topic.unloading.items.JsonSkatNctsImportSpecificTopicUnloadingItemContainer;


/**
 * @author oscardelatorre
 * @date Apr 24, 2014
 * 
 */
public interface SkatNctsImportSpecificTopicUnloadingItemService {
	public JsonSkatNctsImportSpecificTopicUnloadingItemContainer getNctsImportSpecificTopicUnloadingItemContainer(String utfPayload);
	
}
