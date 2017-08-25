/**
 * 
 */
package no.systema.tror.service.landimport;

import no.systema.tror.mapper.jsonjackson.JsonTrorOrderHeaderMapperLandimport;
import no.systema.tror.model.jsonjackson.JsonTrorOrderHeaderContainer;

/**
 * 
 * @author oscardelatorre
 * @date Aug 11, 2017
 * 
 * 
 */
public class TrorMainOrderHeaderLandimportServiceImpl implements TrorMainOrderHeaderLandimportService {

	/**
	 * 
	 */
	public JsonTrorOrderHeaderContainer getOrderHeaderContainer(String utfPayload) {
		JsonTrorOrderHeaderContainer container = null;
		try{
			JsonTrorOrderHeaderMapperLandimport mapper = new JsonTrorOrderHeaderMapperLandimport();
			container = mapper.getContainer(utfPayload);
		}catch(Exception e){
			e.printStackTrace();
		}
		return container;
	}
	

}
