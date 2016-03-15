/**
 * 
 */
package no.systema.overview.sendingerlevtid.service;

import no.systema.overview.sendingerlevtid.model.jsonjackson.JsonTopicContainer;
import no.systema.overview.sendingerlevtid.mapper.jsonjackson.JsonTopicMapper;



/**
 * @author oscardelatorre
 * @date Aug 27, 2013
 * 
 */
public class SendingerlevtidServiceImpl implements SendingerlevtidService  {
	
	/**
	 * @utfPayload
	 * @return
	 * 
	 */
	public JsonTopicContainer getContainer (String utfPayload){
		JsonTopicContainer container = null;
		try{
			JsonTopicMapper mapper = new JsonTopicMapper();
			container = mapper.getContainer(utfPayload);
		}catch(Exception e){
			e.printStackTrace();
		}
		
		return container;
	}
	
	
}
