/**
 * 
 */
package no.systema.skat.nctsimport.service;

import no.systema.skat.nctsimport.mapper.jsonjackson.SkatNctsImportSpecificTopicUnloadingMapper;
import no.systema.skat.nctsimport.model.jsonjackson.topic.unloading.JsonSkatNctsImportSpecificTopicUnloadingContainer;

/**
 * @author oscardelatorre
 * @date Apr 24, 2014
 * 
 */
public class SkatNctsImportSpecificTopicUnloadingServiceImpl implements SkatNctsImportSpecificTopicUnloadingService{
	
	/**
	 * 
	 * @param utfPayload
	 * @return
	 * 
	 * 
	 */
	public JsonSkatNctsImportSpecificTopicUnloadingContainer getNctsImportSpecificTopicUnloadingContainer(String utfPayload) {
		JsonSkatNctsImportSpecificTopicUnloadingContainer container = null;
		try{
			SkatNctsImportSpecificTopicUnloadingMapper mapper = new SkatNctsImportSpecificTopicUnloadingMapper();
			container = mapper.getContainer(utfPayload);
		}catch(Exception e){
			e.printStackTrace();
		}
		
		return container;
		
	}
	
	
	
}
