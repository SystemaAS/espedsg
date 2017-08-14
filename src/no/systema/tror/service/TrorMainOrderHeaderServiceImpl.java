/**
 * 
 */
package no.systema.tror.service;

import no.systema.tror.mapper.jsonjackson.JsonTrorOrderHeaderMapper;
import no.systema.tror.model.jsonjackson.JsonTrorOrderHeaderContainer;

/**
 * 
 * @author oscardelatorre
 * @date Aug 11, 2017
 * 
 * 
 */
public class TrorMainOrderHeaderServiceImpl implements TrorMainOrderHeaderService {

	/**
	 * 
	 */
	public JsonTrorOrderHeaderContainer getOrderHeaderContainer(String utfPayload) {
		JsonTrorOrderHeaderContainer container = null;
		try{
			JsonTrorOrderHeaderMapper mapper = new JsonTrorOrderHeaderMapper();
			container = mapper.getContainer(utfPayload);
		}catch(Exception e){
			e.printStackTrace();
		}
		return container;
	}
	

}
