/**
 * 
 */
package no.systema.overview.sendingerlevtid.service;

import no.systema.overview.sendingerlevtid.model.jsonjackson.carrier.*;
import no.systema.overview.sendingerlevtid.mapper.jsonjackson.JsonSendingerlevtidCarrierMapper;



/**
 * 
 * @author oscardelatorre
 * @date Oct 16, 2013
 * 
 * 
 */
public class SendingerlevtidCarrierServiceImpl implements SendingerlevtidCarrierService  {
	
	/**
	 * @utfPayload
	 * @return
	 * 
	 */
	public JsonSendingerlevtidCarrierContainer getContainer (String utfPayload){
		JsonSendingerlevtidCarrierContainer container = null;
		try{
			JsonSendingerlevtidCarrierMapper mapper = new JsonSendingerlevtidCarrierMapper();
			container = mapper.getContainer(utfPayload);
		}catch(Exception e){
			e.printStackTrace();
		}
		
		return container;
	}
	
	
}
