/**
 * 
 */
package no.systema.efaktura.service;

import no.systema.efaktura.mapper.jsonjackson.JsonEfakturaMainListMapper;
import no.systema.efaktura.model.jsonjackson.JsonEfakturaMainListContainer;

/**
 * 
 * @author oscardelatorre
 * @date Jun 22, 2015
 * 
 * 
 */
public class EfakturaListServiceImpl implements EfakturaListService {

	/**
	 * 
	 */
	public JsonEfakturaMainListContainer getMainListContainer(String utfPayload) {
		JsonEfakturaMainListContainer container = null;
		try{
			JsonEfakturaMainListMapper mapper = new JsonEfakturaMainListMapper();
			container = mapper.getContainer(utfPayload);
		}catch(Exception e){
			e.printStackTrace();
		}
		return container;
	}
	

}
