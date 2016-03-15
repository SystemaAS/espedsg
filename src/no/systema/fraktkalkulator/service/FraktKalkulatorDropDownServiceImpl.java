/**
 * 
 */
package no.systema.fraktkalkulator.service;

import no.systema.fraktkalkulator.model.jsonjackson.dropdownlist.JsonFraktKalkulatorDropDownContainer;
import no.systema.fraktkalkulator.mapper.jsonjackson.FraktKalkulatorDropDownMapper;

/**
 * @author oscardelatorre
 * @date Apr 30, 2014
 * 
 *
 */
public class FraktKalkulatorDropDownServiceImpl implements FraktKalkulatorDropDownService{
	/**
	 * 
	 */
	public JsonFraktKalkulatorDropDownContainer getContainer(String utfPayload){
		JsonFraktKalkulatorDropDownContainer container = null;
		try{
			FraktKalkulatorDropDownMapper mapper = new FraktKalkulatorDropDownMapper();
			container = mapper.getContainer(utfPayload);
		}catch(Exception e){
			e.printStackTrace();
		}
		
		return container;
		
	}
	
}
