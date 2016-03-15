/**
 * 
 */
package no.systema.skat.skatexport.service;

import no.systema.skat.skatexport.mapper.jsonjackson.SkatExportTopicListMapper;
import no.systema.skat.skatexport.model.jsonjackson.topic.JsonSkatExportTopicListContainer;

/**
 * 
 * @author oscardelatorre
 * @date Feb 26, 2014
 * 
 * 
 */
public class SkatExportTopicListServiceImpl implements SkatExportTopicListService {

	public JsonSkatExportTopicListContainer getSkatExportTopicListContainer(String utfPayload) {
		JsonSkatExportTopicListContainer listContainer = null;
		try{
			SkatExportTopicListMapper mapper = new SkatExportTopicListMapper();
			listContainer = mapper.getContainer(utfPayload);
		}catch(Exception e){
			e.printStackTrace();
		}
		
		return listContainer;
		
	}

}
