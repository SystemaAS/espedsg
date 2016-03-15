/**
 * 
 */
package no.systema.skat.skatimport.service;

import no.systema.skat.skatimport.mapper.jsonjackson.SkatImportTopicListMapper;
import no.systema.skat.skatimport.model.jsonjackson.topic.JsonSkatImportTopicListContainer;

/**
 * 
 * @author oscardelatorre
 * @date Jan 24, 2014
 * 
 * 
 */
public class SkatImportTopicListServiceImpl implements SkatImportTopicListService {

	public JsonSkatImportTopicListContainer getSkatImportTopicListContainer(String utfPayload) {
		JsonSkatImportTopicListContainer listContainer = null;
		try{
			SkatImportTopicListMapper mapper = new SkatImportTopicListMapper();
			listContainer = mapper.getContainer(utfPayload);
		}catch(Exception e){
			e.printStackTrace();
		}
		
		return listContainer;
		
	}

}
