/**
 * 
 */
package no.systema.skat.z.maintenance.main.service;

import no.systema.skat.z.maintenance.main.model.jsonjackson.dbtable.JsonMaintDktvkContainer;

/**
 * 
 * @author oscardelatorre
 * @date Jun 13, 2016
 * 
 *
 */
public interface MaintDktvkService {
	public JsonMaintDktvkContainer getList(String utfPayload);
	public JsonMaintDktvkContainer doUpdate(String utfPayload);
	
}
