/**
 * 
 */
package no.systema.skat.nctsimport.service;

import no.systema.skat.nctsimport.model.jsonjackson.topic.unloading.JsonSkatNctsImportSpecificTopicUnloadingContainer;


/**
 * @author oscardelatorre
 * @date Apr 24, 2014
 */
public interface SkatNctsImportSpecificTopicUnloadingService {
	public JsonSkatNctsImportSpecificTopicUnloadingContainer getNctsImportSpecificTopicUnloadingContainer(String utfPayload);
	
}
