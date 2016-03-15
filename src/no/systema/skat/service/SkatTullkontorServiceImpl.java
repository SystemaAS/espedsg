/**
 * 
 */
package no.systema.skat.service;

import no.systema.skat.model.jsonjackson.tullkontor.JsonSkatTullkontorContainer;
import no.systema.skat.mapper.jsonjackson.SkatTullkontorMapper;

/**
 * 
 * @author oscardelatorre
 * @date Apr 18, 2014
 * 
 * 
 */
public class SkatTullkontorServiceImpl implements SkatTullkontorService{
	public JsonSkatTullkontorContainer getSkatTullkontorContainer(String utfPayload) {
		JsonSkatTullkontorContainer container = null;
		try{
			SkatTullkontorMapper mapper = new SkatTullkontorMapper();
			container = mapper.getContainer(utfPayload);
		}catch(Exception e){
			e.printStackTrace();
		}
		
		return container;
		
	}
}
