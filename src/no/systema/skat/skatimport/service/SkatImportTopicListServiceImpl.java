/**
 * 
 */
package no.systema.skat.skatimport.service;

import no.systema.skat.skatimport.mapper.jsonjackson.SkatImportTopicListMapper;
import no.systema.skat.skatimport.model.jsonjackson.topic.JsonSkatImportTopicListContainer;
import no.systema.skat.skatimport.model.jsonjackson.topic.JsonSkatImportTopicInvoiceExternalContainer;
import no.systema.skat.skatimport.model.jsonjackson.topic.JsonSkatImportTopicListExternalRefContainer;


/**
 * 
 * @author oscardelatorre
 * @date Jan 24, 2014
 * 
 * 
 */
public class SkatImportTopicListServiceImpl implements SkatImportTopicListService {

	/**
	 * 
	 */
	public JsonSkatImportTopicListContainer getSkatImportTopicListContainer(String utfPayload) {
		JsonSkatImportTopicListContainer container = null;
		try{
			SkatImportTopicListMapper mapper = new SkatImportTopicListMapper();
			container = mapper.getContainer(utfPayload);
		}catch(Exception e){
			e.printStackTrace();
		}
		
		return container;
		
	}
	
	/**
	 * 
	 */
	public JsonSkatImportTopicListExternalRefContainer getSkatImportTopicListExternalRefContainer(String utfPayload) {
		JsonSkatImportTopicListExternalRefContainer container = null;
		try{
			SkatImportTopicListMapper mapper = new SkatImportTopicListMapper();
			container = mapper.getContainerExternalRef(utfPayload);
		}catch(Exception e){
			e.printStackTrace();
		}
		
		return container;
		
	}

}
