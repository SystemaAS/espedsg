/**
 * 
 */
package no.systema.skat.service;

import no.systema.skat.model.jsonjackson.customer.JsonSkatCustomerContainer;
import no.systema.skat.mapper.jsonjackson.SkatCustomerMapper;

/**
 * @author oscardelatorre
 * @date Mar 12, 2014
 * 
 *
 */
public class SkatCustomerServiceImpl implements SkatCustomerService{
	public JsonSkatCustomerContainer getSkatCustomerContainer(String utfPayload) {
		JsonSkatCustomerContainer container = null;
		try{
			SkatCustomerMapper mapper = new SkatCustomerMapper();
			container = mapper.getContainer(utfPayload);
		}catch(Exception e){
			e.printStackTrace();
		}
		
		return container;
		
	}
}
