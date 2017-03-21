/**
 * 
 */
package no.systema.skat.z.maintenance.main.service;

import no.systema.skat.z.maintenance.main.model.jsonjackson.dbtable.JsonMaintDkthaContainer;

/**
 * 
 * @author oscardelatorre
 * @date Mar 21, 2017
 * 
 *
 */
public interface MaintDkthaService {
	public JsonMaintDkthaContainer getList(String utfPayload);
	public JsonMaintDkthaContainer doUpdate(String utfPayload);
	
}
