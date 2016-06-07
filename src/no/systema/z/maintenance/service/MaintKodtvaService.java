/**
 * 
 */
package no.systema.z.maintenance.service;

import no.systema.z.maintenance.model.jsonjackson.dbtable.JsonMaintKodtvaContainer;

/**
 * 
 * @author oscardelatorre
 * @date Jun 7, 2016
 * 
 *
 */
public interface MaintKodtvaService {
	public JsonMaintKodtvaContainer getList(String utfPayload);
	public JsonMaintKodtvaContainer doUpdate(String utfPayload);
	
}
