/**
 * 
 */
package no.systema.fraktkalkulator.service;

import no.systema.fraktkalkulator.model.jsonjackson.JsonFraktKalkulatorUserContainer;
import no.systema.fraktkalkulator.mapper.jsonjackson.FraktKalkulatorUserMapper;

/**
 * @author oscardelatorre
 * @date Apr 30, 2014
 * 
 *
 */
public class FraktKalkulatorUserServiceImpl implements FraktKalkulatorUserService{
	/**
	 * 
	 */
	public JsonFraktKalkulatorUserContainer getContainer(String utfPayload){
		JsonFraktKalkulatorUserContainer container = null;
		try{
			FraktKalkulatorUserMapper mapper = new FraktKalkulatorUserMapper();
			container = mapper.getContainer(utfPayload);
		}catch(Exception e){
			e.printStackTrace();
		}
		
		return container;
		
	}
	
}
