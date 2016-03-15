/**
 * 
 */
package no.systema.skat.skatexport.service;

import no.systema.skat.skatexport.mapper.jsonjackson.SkatExportSpecificTopicItemMapper;
import no.systema.skat.skatexport.model.jsonjackson.topic.items.JsonSkatExportSpecificTopicItemContainer;
import no.systema.skat.skatexport.model.jsonjackson.topic.items.JsonSkatExportSpecificTopicItemAvgifterContainer;

/**
 * @author oscardelatorre
 * @date Mar 10, 2014
 * 
 *
 */
public class SkatExportSpecificTopicItemServiceImpl implements SkatExportSpecificTopicItemService{
	/**
	 * @param utfPayload
	 * @return
	 * 
	 */
	public JsonSkatExportSpecificTopicItemContainer getSkatExportSpecificTopicItemContainer(String utfPayload) {
		JsonSkatExportSpecificTopicItemContainer container = null;
		try{
			SkatExportSpecificTopicItemMapper mapper = new SkatExportSpecificTopicItemMapper();
			container = mapper.getContainer(utfPayload);
		}catch(Exception e){
			e.printStackTrace();
			

		}
		
		return container;
		
	}
	
	/**
	 * Maps the avgifter
	 * 
	 * @param utfPayload
	 * @return
	 * 
	 */
	
	public JsonSkatExportSpecificTopicItemAvgifterContainer getSkatExportSpecificTopicItemAvgifterContainer(String utfPayload) {
		JsonSkatExportSpecificTopicItemAvgifterContainer container = null;
		try{
			SkatExportSpecificTopicItemMapper mapper = new SkatExportSpecificTopicItemMapper();
			container = mapper.getAvgifterContainer(utfPayload);
		}catch(Exception e){
			e.printStackTrace();
		}
		
		return container;
		
	}
	
	

}
