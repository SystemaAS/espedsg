/**
 * 
 */
package no.systema.tror.service;

import no.systema.tror.mapper.jsonjackson.order.archive.JsonTrorOrderArchiveMapper;
import no.systema.tror.model.jsonjackson.archive.JsonTrorOrderHeaderArchiveContainer;

/**
 * 
 * @author oscardelatorre
 * @date Sep 15, 2017
 * 
 * 
 */
public class TrorMainOrderHeaderServiceImpl implements TrorMainOrderHeaderService {

	/**
	 * 
	 */
	public JsonTrorOrderHeaderArchiveContainer getArchiveContainer(String utfPayload) {
		JsonTrorOrderHeaderArchiveContainer container = null;
		try{
			JsonTrorOrderArchiveMapper mapper = new JsonTrorOrderArchiveMapper();
			container = mapper.getContainer(utfPayload);
		}catch(Exception e){
			e.printStackTrace();
		}
		return container;
	}


}
