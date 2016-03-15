/**
 * 
 */
package no.systema.skat.nctsimport.service;

import no.systema.skat.nctsimport.mapper.jsonjackson.SkatNctsImportTopicListMapper;
import no.systema.skat.nctsimport.model.jsonjackson.topic.JsonSkatNctsImportTopicListContainer;

/**
 * 
 * @author oscardelatorre
 * @date Apr 23, 2014
 * 
 * 
 */
public class SkatNctsImportTopicListServiceImpl implements SkatNctsImportTopicListService {

	public JsonSkatNctsImportTopicListContainer getNctsImportTopicListContainer(String utfPayload) {
		JsonSkatNctsImportTopicListContainer container = null;
		try{
			SkatNctsImportTopicListMapper mapper = new SkatNctsImportTopicListMapper();
			container = mapper.getContainer(utfPayload);
		}catch(Exception e){
			e.printStackTrace();
		}
		
		return container;
		
	}

}
