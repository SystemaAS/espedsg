/**
 * 
 */
package no.systema.skat.service;

import no.systema.skat.mapper.jsonjackson.authorization.SkatAuthorizationMapper;
import no.systema.skat.model.jsonjackson.authorization.JsonSkatAuthorizationContainer;


/**
 * 
 * @author oscardelatorre
 * @date Mar 4, 2014
 * 
 */
public class SkatAuthorizationServiceImpl implements SkatAuthorizationService {
	private SkatAuthorizationMapper mapper = new SkatAuthorizationMapper();
	
	/**
	 * 
	 * @param utfPayload
	 * @return
	 * @throws Exception
	 */
	public JsonSkatAuthorizationContainer getContainer(String utfPayload) throws Exception{
		return this.mapper.getContainer(utfPayload);
	}
	
}
