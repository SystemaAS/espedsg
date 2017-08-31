/**
 * 
 */
package no.systema.tror.service;

import no.systema.tror.mapper.jsonjackson.JsonTrorOrderHeaderMapperChildWindow;
import no.systema.tror.model.jsonjackson.order.childwindow.JsonTrorCarrierContainer;

/**
 * 
 * @author oscardelatorre
 * @date Aug 31, 2017
 * 
 * 
 */
public class TrorMainOrderHeaderChildwindowServiceImpl implements TrorMainOrderHeaderChildwindowService {

	/**
	 * 
	 */
	public JsonTrorCarrierContainer getCarrierListContainer(String utfPayload){
		JsonTrorCarrierContainer container = null;
		try{
			JsonTrorOrderHeaderMapperChildWindow mapper = new JsonTrorOrderHeaderMapperChildWindow();
			container = mapper.getCarrierContainer(utfPayload);
		}catch(Exception e){
			e.printStackTrace();
		}
		return container;
		
		
	}

}
