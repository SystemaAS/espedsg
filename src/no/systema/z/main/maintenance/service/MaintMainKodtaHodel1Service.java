/**
 * 
 */
package no.systema.z.main.maintenance.service;

import no.systema.z.main.maintenance.model.jsonjackson.dbtable.JsonMaintMainKodtaHodel1Container;

/**
 * 
 * @author oscardelatorre
 * @date Aug 17, 2016
 * 
 *
 */
public interface MaintMainKodtaHodel1Service {
	public JsonMaintMainKodtaHodel1Container getList(String utfPayload);
	public JsonMaintMainKodtaHodel1Container doUpdate(String utfPayload);
	
	
}
