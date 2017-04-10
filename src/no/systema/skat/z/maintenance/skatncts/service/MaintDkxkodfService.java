/**
 * 
 */
package no.systema.skat.z.maintenance.skatncts.service;

import no.systema.skat.z.maintenance.skatncts.model.jsonjackson.dbtable.JsonMaintDkxkodfContainer;;

/**
 * 
 * @author oscardelatorre
 * @date Apr 10, 2016
 * 
 *
 */
public interface MaintDkxkodfService {
	public JsonMaintDkxkodfContainer getList(String utfPayload);
	public JsonMaintDkxkodfContainer doUpdate(String utfPayload);
	
}
