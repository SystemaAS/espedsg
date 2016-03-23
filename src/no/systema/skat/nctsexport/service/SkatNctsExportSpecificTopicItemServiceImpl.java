/**
 * 
 */
package no.systema.skat.nctsexport.service;

import no.systema.skat.nctsexport.mapper.jsonjackson.SkatNctsExportSpecificTopicItemMapper;
import no.systema.skat.nctsexport.mapper.jsonjackson.SkatNctsExportSpecificTopicItemSensitiveGoodsValidatorMapper;
import no.systema.skat.nctsexport.model.jsonjackson.topic.items.JsonSkatNctsExportSpecificTopicItemContainer;
import no.systema.skat.nctsexport.model.jsonjackson.topic.items.JsonSkatNctsExportSpecificTopicItemSecurityContainer;
import no.systema.skat.nctsexport.model.jsonjackson.topic.items.validation.JsonSkatNctsExportSpecificTopicItemSensitiveGoodsValidatorContainer;

/**
 * @author oscardelatorre
 * @date Apr 14, 2014
 *
 */
public class SkatNctsExportSpecificTopicItemServiceImpl implements SkatNctsExportSpecificTopicItemService{
	/**
	 * @param utfPayload
	 * @return
	 * 
	 */
	public JsonSkatNctsExportSpecificTopicItemContainer getNctsExportSpecificTopicItemContainer(String utfPayload) {
		JsonSkatNctsExportSpecificTopicItemContainer jsonNctsExportSpecificTopicItemContainer = null;
		try{
			SkatNctsExportSpecificTopicItemMapper nctsExportSpecificTopicItemMapper = new SkatNctsExportSpecificTopicItemMapper();
			jsonNctsExportSpecificTopicItemContainer = nctsExportSpecificTopicItemMapper.getContainer(utfPayload);
		}catch(Exception e){
			e.printStackTrace();
		}
		
		return jsonNctsExportSpecificTopicItemContainer;
		
	}

	/**
	 * @param utfPayload
	 * @return
	 * 
	 */
	public JsonSkatNctsExportSpecificTopicItemSensitiveGoodsValidatorContainer getJsonNctsExportSpecificTopicItemSensitiveGoodsValidatorContainer(String utfPayload){
		JsonSkatNctsExportSpecificTopicItemSensitiveGoodsValidatorContainer container = null;
		try{
			SkatNctsExportSpecificTopicItemSensitiveGoodsValidatorMapper mapper = new SkatNctsExportSpecificTopicItemSensitiveGoodsValidatorMapper();
			container = mapper.getContainer(utfPayload);
		}catch(Exception e){
			e.printStackTrace();
		}
		return container;
	}
	
	/**
	 * 
	 */
	public JsonSkatNctsExportSpecificTopicItemSecurityContainer getNctsExportSpecificTopicItemSecurityContainer(String utfPayload){
		JsonSkatNctsExportSpecificTopicItemSecurityContainer container = null;
		try{
			SkatNctsExportSpecificTopicItemMapper mapper = new SkatNctsExportSpecificTopicItemMapper();
			container = mapper.getSecurityContainer(utfPayload);
		}catch(Exception e){
			e.printStackTrace();
		}
		
		return container;
	}
}
