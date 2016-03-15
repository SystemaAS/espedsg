/**
 * 
 */
package no.systema.fraktkalkulator.service;

import no.systema.fraktkalkulator.model.jsonjackson.JsonFraktKalkulatorResultContainer;
import no.systema.fraktkalkulator.mapper.jsonjackson.FraktKalkulatorResultMapper;

/**
 * @author oscardelatorre
 * @date Mar 04, 2015
 * 
 *
 */
public class FraktKalkulatorResultServiceImpl implements FraktKalkulatorResultService{
	/**
	 * 
	 */
	public JsonFraktKalkulatorResultContainer getContainer(String utfPayload){
		JsonFraktKalkulatorResultContainer container = null;
		try{
			FraktKalkulatorResultMapper mapper = new FraktKalkulatorResultMapper();
			container = mapper.getContainer(utfPayload);
		}catch(Exception e){
			e.printStackTrace();
		}
		
		return container;
		
	}
	
}
