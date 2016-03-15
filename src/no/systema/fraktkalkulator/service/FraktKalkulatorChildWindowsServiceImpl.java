/**
 * 
 */
package no.systema.fraktkalkulator.service;

import no.systema.fraktkalkulator.model.jsonjackson.customer.JsonFraktKalkulatorCustomerContainer;
import no.systema.fraktkalkulator.mapper.jsonjackson.FraktKalkulatorCustomerMapper;
import no.systema.main.mapper.jsonjackson.general.PostalCodesMapper;
import no.systema.main.model.jsonjackson.general.postalcodes.JsonPostalCodesContainer;



/**
 * @author oscardelatorre
 * @date Apr 30, 2014
 * 
 *
 */
public class FraktKalkulatorChildWindowsServiceImpl implements FraktKalkulatorChildWindowsService{
	/**
	 * 
	 */
	public JsonFraktKalkulatorCustomerContainer getCustomerContainer(String utfPayload){
		JsonFraktKalkulatorCustomerContainer container = null;
		try{
			FraktKalkulatorCustomerMapper mapper = new FraktKalkulatorCustomerMapper();
			container = mapper.getContainer(utfPayload);
		}catch(Exception e){
			e.printStackTrace();
		}
		
		return container;
		
	}
	
	/**
	 * 
	 */
	public JsonPostalCodesContainer getPostalCodesContainer(String utfPayload){
		JsonPostalCodesContainer container = null;
		try{
			PostalCodesMapper mapper = new PostalCodesMapper();
			container = mapper.getContainer(utfPayload);
		}catch(Exception e){
			e.printStackTrace();
		}
		
		return container;
		
	}
	
}
