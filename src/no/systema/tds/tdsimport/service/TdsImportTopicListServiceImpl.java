/**
 * 
 */
package no.systema.tds.tdsimport.service;

import no.systema.tds.tdsimport.mapper.jsonjackson.TdsImportTopicListMapper;
import no.systema.tds.tdsimport.model.jsonjackson.topic.JsonTdsImportTopicListContainer;

/**
 * @author oscardelatorre
 * @date Sep 2, 2013
 */
public class TdsImportTopicListServiceImpl implements TdsImportTopicListService {

	public JsonTdsImportTopicListContainer getTdsImportTopicListContainer(String utfPayload) {
		JsonTdsImportTopicListContainer listContainer = null;
		try{
			TdsImportTopicListMapper tdsExportTopicListMapper = new TdsImportTopicListMapper();
			listContainer = tdsExportTopicListMapper.getContainer(utfPayload);
		}catch(Exception e){
			e.printStackTrace();
		}
		
		return listContainer;
		
	}

}
