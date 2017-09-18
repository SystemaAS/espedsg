/**
 * 
 */
package no.systema.tror.service;

import no.systema.tror.mapper.jsonjackson.order.archive.JsonTrorOrderArchiveMapper;
import no.systema.tror.model.jsonjackson.archive.JsonTrorOrderHeaderArchiveContainer;
import no.systema.tror.mapper.jsonjackson.order.logging.JsonTrorOrderLoggingMapper;
import no.systema.tror.mapper.jsonjackson.order.logging.JsonTrorOrderLoggingLargeTextMapper;
import no.systema.tror.model.jsonjackson.logging.JsonTrorOrderHeaderLoggingContainer;
import no.systema.tror.model.jsonjackson.logging.JsonTrorOrderHeaderLoggingLargeTextContainer;

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
	/**
	 * 
	 */
	public JsonTrorOrderHeaderLoggingContainer getLoggingContainer(String utfPayload){
		JsonTrorOrderHeaderLoggingContainer container = null;
		try{
			JsonTrorOrderLoggingMapper mapper = new JsonTrorOrderLoggingMapper();
			container = mapper.getContainer(utfPayload);
		}catch(Exception e){
			e.printStackTrace();
		}
		return container;
		
	}
	/**
	 * 
	 */
	public JsonTrorOrderHeaderLoggingLargeTextContainer getLoggingLargeTextContainer(String utfPayload){
		JsonTrorOrderHeaderLoggingLargeTextContainer container = null;
		try{
			JsonTrorOrderLoggingLargeTextMapper mapper = new JsonTrorOrderLoggingLargeTextMapper();
			container = mapper.getContainer(utfPayload);
		}catch(Exception e){
			e.printStackTrace();
		}
		return container;
		
	}

}
