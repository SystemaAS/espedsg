/**
 * 
 */
package no.systema.skat.skatexport.service;

import no.systema.skat.skatexport.mapper.jsonjackson.SkatExportTopicListMapper;
import no.systema.skat.skatexport.model.jsonjackson.topic.JsonSkatExportTopicListContainer;
import no.systema.skat.skatexport.model.jsonjackson.topic.JsonSkatExportTopicListExternalRefContainer;

/**
 * 
 * @author oscardelatorre
 * @date Feb 26, 2014
 * 
 * 
 */
public class SkatExportTopicListServiceImpl implements SkatExportTopicListService {

	public JsonSkatExportTopicListContainer getSkatExportTopicListContainer(String utfPayload) {
		JsonSkatExportTopicListContainer container = null;
		try{
			SkatExportTopicListMapper mapper = new SkatExportTopicListMapper();
			container = mapper.getContainer(utfPayload);
		}catch(Exception e){
			e.printStackTrace();
		}
		
		return container;
		
	}
	/**
	 * 
	 */
	public JsonSkatExportTopicListExternalRefContainer getSkatExportTopicListExternalRefContainer(String utfPayload){
		JsonSkatExportTopicListExternalRefContainer container = null;
		try{
			SkatExportTopicListMapper mapper = new SkatExportTopicListMapper();
			container = mapper.getContainerExternalRef(utfPayload);
		}catch(Exception e){
			e.printStackTrace();
		}
		
		return container;
	}

}
