/**
 * 
 */
package no.systema.skat.nctsimport.service;

import no.systema.skat.nctsimport.mapper.jsonjackson.SkatNctsImportSpecificTopicUnloadingItemMapper;
import no.systema.skat.nctsimport.model.jsonjackson.topic.unloading.items.JsonSkatNctsImportSpecificTopicUnloadingItemContainer;

/**
 * @author oscardelatorre
 * @date Apr 24, 2014
 */
public class SkatNctsImportSpecificTopicUnloadingItemServiceImpl implements SkatNctsImportSpecificTopicUnloadingItemService{
	
	/**
	 * 
	 * @param utfPayload
	 * @return
	 * 
	 * 
	 */
	public JsonSkatNctsImportSpecificTopicUnloadingItemContainer getNctsImportSpecificTopicUnloadingItemContainer(String utfPayload) {
		JsonSkatNctsImportSpecificTopicUnloadingItemContainer container = null;
		try{
			SkatNctsImportSpecificTopicUnloadingItemMapper mapper = new SkatNctsImportSpecificTopicUnloadingItemMapper();
			container = mapper.getContainer(utfPayload);
		}catch(Exception e){
			e.printStackTrace();
		}
		
		return container;
		
	}
	
	
	
}
