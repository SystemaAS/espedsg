/**
 * 
 */
package no.systema.skat.z.maintenance.skatimport.service;

import no.systema.skat.z.maintenance.skatimport.model.jsonjackson.dbtable.JsonMaintDktseContainer;

/**
 * 
 * @author oscardelatorre
 * @date Mar 6, 2017
 * 
 *
 */
public interface MaintSkatImportDktseService {
	public JsonMaintDktseContainer getList(String utfPayload);
	public JsonMaintDktseContainer doUpdate(String utfPayload);
	
}
