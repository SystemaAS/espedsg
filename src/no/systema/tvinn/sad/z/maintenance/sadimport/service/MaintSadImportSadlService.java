/**
 * 
 */
package no.systema.tvinn.sad.z.maintenance.sadimport.service;

import no.systema.tvinn.sad.z.maintenance.sadimport.model.jsonjackson.dbtable.JsonMaintSadImportSadlContainer;

/**
 * 
 * @author oscardelatorre
 * @date May 31, 2016
 * 
 *
 */
public interface MaintSadImportSadlService {
	public JsonMaintSadImportSadlContainer getList(String utfPayload);
	public JsonMaintSadImportSadlContainer doUpdate(String utfPayload);
	
}
