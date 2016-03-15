/**
 * 
 */
package no.systema.skat.nctsimport.service;

import no.systema.skat.nctsimport.mapper.jsonjackson.SkatNctsImportSpecificTopicItemMapper;
import no.systema.skat.nctsimport.model.jsonjackson.topic.items.JsonSkatNctsImportSpecificTopicItemContainer;

/**
 * @author oscardelatorre
 * @date Apr 24, 2014
 *
 */
public class SkatNctsImportSpecificTopicItemServiceImpl implements SkatNctsImportSpecificTopicItemService{
	/**
	 * @param utfPayload
	 * @return
	 * 
	 */
	public JsonSkatNctsImportSpecificTopicItemContainer getNctsImportSpecificTopicItemContainer(String utfPayload) {
		JsonSkatNctsImportSpecificTopicItemContainer container = null;
		try{
			SkatNctsImportSpecificTopicItemMapper mapper = new SkatNctsImportSpecificTopicItemMapper();
			container = mapper.getContainer(utfPayload);
		}catch(Exception e){
			e.printStackTrace();
		}
		return container;
	}
	
}
