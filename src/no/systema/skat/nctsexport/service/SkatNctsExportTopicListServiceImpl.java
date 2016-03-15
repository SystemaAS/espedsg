/**
 * 
 */
package no.systema.skat.nctsexport.service;

import no.systema.skat.nctsexport.mapper.jsonjackson.SkatNctsExportTopicListMapper;
import no.systema.skat.nctsexport.model.jsonjackson.topic.JsonSkatNctsExportTopicListContainer;

/**
 * @author oscardelatorre
 * @date Apr 14, 2014
 */
public class SkatNctsExportTopicListServiceImpl implements SkatNctsExportTopicListService {

	public JsonSkatNctsExportTopicListContainer getNctsExportTopicListContainer(String utfPayload) {
		JsonSkatNctsExportTopicListContainer jsonNctsExportTopicListContainer = null;
		try{
			SkatNctsExportTopicListMapper nctsExportTopicListMapper = new SkatNctsExportTopicListMapper();
			jsonNctsExportTopicListContainer = nctsExportTopicListMapper.getContainer(utfPayload);
		}catch(Exception e){
			e.printStackTrace();
		}
		
		return jsonNctsExportTopicListContainer;
		
	}

}
