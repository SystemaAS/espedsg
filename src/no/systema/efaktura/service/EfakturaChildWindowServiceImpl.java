/**
 * 
 */
package no.systema.efaktura.service;

import no.systema.efaktura.mapper.jsonjackson.JsonEfakturaChildWindowMapper;
import no.systema.efaktura.model.jsonjackson.childwindow.JsonEfakturaCustomerContainer;

/**
 * 
 * @author oscardelatorre
 * @date Nov 30, 2015
 * 
 * 
 */
public class EfakturaChildWindowServiceImpl implements EfakturaChildWindowService {
	
	/**
	 * Customer
	 */
	public JsonEfakturaCustomerContainer getCustomerContainer(String utfPayload){
		JsonEfakturaCustomerContainer container = null;
		try{
			JsonEfakturaChildWindowMapper mapper = new JsonEfakturaChildWindowMapper();
			container = mapper.getCustomerContainer(utfPayload);
		}catch(Exception e){
			e.printStackTrace();
		}
		return container;
	}
}
