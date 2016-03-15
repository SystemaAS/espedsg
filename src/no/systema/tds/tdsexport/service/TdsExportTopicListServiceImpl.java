/**
 * 
 */
package no.systema.tds.tdsexport.service;

import no.systema.tds.tdsexport.mapper.jsonjackson.TdsExportTopicListMapper;
import no.systema.tds.tdsexport.model.jsonjackson.topic.JsonTdsExportTopicListContainer;

/**
 * @author oscardelatorre
 * @date Nov 25, 2012
 */
public class TdsExportTopicListServiceImpl implements TdsExportTopicListService {

	public JsonTdsExportTopicListContainer getTdsExportTopicListContainer(String utfPayload) {
		JsonTdsExportTopicListContainer jsonTdsExportTopicListContainer = null;
		try{
			TdsExportTopicListMapper tdsExportTopicListMapper = new TdsExportTopicListMapper();
			jsonTdsExportTopicListContainer = tdsExportTopicListMapper.getContainer(utfPayload);
		}catch(Exception e){
			e.printStackTrace();
		}
		
		return jsonTdsExportTopicListContainer;
		
	}

}
