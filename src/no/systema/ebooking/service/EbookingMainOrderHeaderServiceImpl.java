/**
 * 
 */
package no.systema.ebooking.service;

import no.systema.ebooking.mapper.jsonjackson.JsonOrderHeaderMapper;
import no.systema.ebooking.model.jsonjackson.JsonMainOrderHeaderContainer;
import no.systema.ebooking.model.jsonjackson.JsonMainOrderHeaderFraktbrevContainer;

/**
 * 
 * @author oscardelatorre
 * @date Jan 06, 2017
 * 
 * 
 */
public class EbookingMainOrderHeaderServiceImpl implements EbookingMainOrderHeaderService {

	/**
	 * 
	 */
	public JsonMainOrderHeaderContainer getContainer(String utfPayload) {
		JsonMainOrderHeaderContainer container = null;
		try{
			JsonOrderHeaderMapper mapper = new JsonOrderHeaderMapper();
			container = mapper.getContainer(utfPayload);
		}catch(Exception e){
			e.printStackTrace();
		}
		return container;
	}
	
	/**
	 * 
	 */
	public JsonMainOrderHeaderFraktbrevContainer getFraktbrevContainer(String utfPayload){
		JsonMainOrderHeaderFraktbrevContainer container = null;
		try{
			JsonOrderHeaderMapper mapper = new JsonOrderHeaderMapper();
			container = mapper.getFraktbrevContainer(utfPayload);
		}catch(Exception e){
			e.printStackTrace();
		}
		return container;
	}

}
