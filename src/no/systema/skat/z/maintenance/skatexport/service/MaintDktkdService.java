/**
 * 
 */
package no.systema.skat.z.maintenance.skatexport.service;

import no.systema.skat.z.maintenance.skatexport.model.jsonjackson.dbtable.JsonMaintDktkdContainer;

/**
 * 
 * @author oscardelatorre
 * @date Sep 2, 2016
 * 
 *
 */
public interface MaintDktkdService {
	public JsonMaintDktkdContainer getList(String utfPayload);
	public JsonMaintDktkdContainer doUpdate(String utfPayload);
	
}
