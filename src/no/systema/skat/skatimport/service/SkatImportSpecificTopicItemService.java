/**
 * 
 */
package no.systema.skat.skatimport.service;

import no.systema.skat.skatimport.model.jsonjackson.topic.items.JsonSkatImportSpecificTopicItemContainer;
import no.systema.skat.skatimport.model.jsonjackson.topic.items.JsonSkatImportSpecificTopicItemAvgifterContainer;
import no.systema.skat.skatimport.model.jsonjackson.topic.items.JsonSkatImportSpecificTopicItemBilagdaHandlingarContainer;
import no.systema.skat.skatimport.model.jsonjackson.topic.items.JsonSkatImportSpecificTopicItemToldvaerdiContainer;
import no.systema.skat.skatimport.model.jsonjackson.topic.items.JsonSkatImportSpecificTopicItemToldvaerdiTransportContainer;
import no.systema.skat.skatimport.model.jsonjackson.topic.items.JsonSkatImportSpecificTopicItemCertificateNrAndCodeR442Container;

/**
 * 
 * @author oscardelatorre
 * @date Sep 2, 2013
 * 
 *
 */
public interface SkatImportSpecificTopicItemService {
	
	public JsonSkatImportSpecificTopicItemContainer getSkatImportSpecificTopicItemContainer(String utfPayload);
	public JsonSkatImportSpecificTopicItemAvgifterContainer getSkatImportSpecificTopicItemAvgifterContainer(String utfPayload);
	public JsonSkatImportSpecificTopicItemBilagdaHandlingarContainer getSkatBilagdaHandlingarContainer (String utfPayload);
	public JsonSkatImportSpecificTopicItemToldvaerdiContainer getSkatTullvaerdiContainer (String utfPayload);
	public JsonSkatImportSpecificTopicItemToldvaerdiTransportContainer getSkatTullvaerdiTransportContainer (String utfPayload);
	public JsonSkatImportSpecificTopicItemCertificateNrAndCodeR442Container getSkatCertificateNrAndCodeR442Container (String utfPayload);
	
	
}
