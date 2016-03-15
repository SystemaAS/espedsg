/**
 * 
 */
package no.systema.skat.service;

import no.systema.skat.mapper.jsonjackson.SkatTaricVarukodMapper;
import no.systema.skat.model.jsonjackson.codes.JsonSkatTaricVarukodContainer;

/**
 * This service could be moved to the service.html.dropdown package but we do not know if the Taric varukod
 * will be render as list or maybe as a tree. Could be a table. We do separate this service outside the html.dropdown package...
 * 
 * @author oscardelatorre
 * @date Feb 4, 2014
 */
public class SkatTaricVarukodServiceImpl implements SkatTaricVarukodService {
	private SkatTaricVarukodMapper taricMapper = new SkatTaricVarukodMapper();
	
	/**
	 * 
	 * @param utfPayload
	 * @return
	 * @throws Exception
	 */
	public JsonSkatTaricVarukodContainer getContainer(String utfPayload) throws Exception{
		return this.taricMapper.getContainer(utfPayload);
	}
}
