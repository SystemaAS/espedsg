/**
 * 
 */
package no.systema.z.main.maintenance.service;

import no.systema.z.main.maintenance.model.jsonjackson.dbtable.JsonMaintMainKodtvKodtwContainer;

/**
 * 
 * @author oscardelatorre
 * @date Aug 5, 2016
 * 
 *
 */
public interface MaintMainKodtvKodtwService {
	public JsonMaintMainKodtvKodtwContainer getList(String utfPayload);
	public JsonMaintMainKodtvKodtwContainer doUpdate(String utfPayload);
	
}
