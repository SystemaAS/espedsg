/**
 * 
 */
package no.systema.tvinn.sad.z.maintenance.sadimport.service;

import no.systema.tvinn.sad.z.maintenance.sadimport.model.jsonjackson.dbtable.JsonMaintSadImportKodtlbContainer;

/**
 * 
 * @author oscardelatorre
 * @date Apr 29, 2016
 * 
 *
 */
public interface MaintSadImportKodtlbService {
	public JsonMaintSadImportKodtlbContainer getList(String utfPayload);
	public JsonMaintSadImportKodtlbContainer doUpdate(String utfPayload);
	
}
