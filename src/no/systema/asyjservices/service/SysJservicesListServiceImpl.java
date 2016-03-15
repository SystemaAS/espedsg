/**
 * 
 */
package no.systema.asyjservices.service;

import no.systema.asyjservices.mapper.jsonjackson.JsonSysJservicesMainListMapper;
import no.systema.asyjservices.model.jsonjackson.JsonSysJservicesMainListContainer;

/**
 * 
 * @author oscardelatorre
 * @date Nov 4, 2015
 * 
 * 
 */
public class SysJservicesListServiceImpl implements SysJservicesListService {

	/**
	 * 
	 */
	public JsonSysJservicesMainListContainer getMainListContainer(String utfPayload) {
		JsonSysJservicesMainListContainer container = null;
		try{
			JsonSysJservicesMainListMapper mapper = new JsonSysJservicesMainListMapper();
			container = mapper.getContainer(utfPayload);
		}catch(Exception e){
			e.printStackTrace();
		}
		return container;
	}
	

}
