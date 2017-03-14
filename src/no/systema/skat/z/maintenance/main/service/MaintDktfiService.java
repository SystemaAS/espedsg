/**
 * 
 */
package no.systema.skat.z.maintenance.main.service;

import no.systema.skat.z.maintenance.main.model.jsonjackson.dbtable.JsonMaintDktfiContainer;

/**
 * 
 * @author oscardelatorre
 * @date Mar 14, 2017
 * 
 *
 */
public interface MaintDktfiService {
	public JsonMaintDktfiContainer getList(String utfPayload);
	public JsonMaintDktfiContainer doUpdate(String utfPayload);
	
}
