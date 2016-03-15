/**
 * 
 */
package no.systema.skat.nctsexport.service;

import no.systema.skat.nctsexport.model.jsonjackson.topic.items.JsonSkatNctsExportSpecificTopicItemContainer;
import no.systema.skat.nctsexport.model.jsonjackson.topic.items.validation.JsonSkatNctsExportSpecificTopicItemSensitiveGoodsValidatorContainer;


/**
 * 
 * @author oscardelatorre
 * @date Apr 14, 2014
 * 
 */
public interface SkatNctsExportSpecificTopicItemService {
	public JsonSkatNctsExportSpecificTopicItemContainer getNctsExportSpecificTopicItemContainer(String utfPayload);
	public JsonSkatNctsExportSpecificTopicItemSensitiveGoodsValidatorContainer getJsonNctsExportSpecificTopicItemSensitiveGoodsValidatorContainer(String utfPayload);
}
