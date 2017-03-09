/**
 * 
 */
package no.systema.skat.z.maintenance.main.service;

import no.systema.skat.z.maintenance.main.model.jsonjackson.dbtable.JsonMaintDktardContainer;

/**
 * 
 * @author oscardelatorre
 * @date Mar 09, 2017
 * 
 *
 */
public interface MaintDktardService {
	public JsonMaintDktardContainer getList(String utfPayload);
	public JsonMaintDktardContainer doUpdate(String utfPayload);
	
}
