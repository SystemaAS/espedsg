/**
 * 
 */
package no.systema.tvinn.sad.z.maintenance.sadimport.service;

import no.systema.tvinn.sad.z.maintenance.sadimport.model.jsonjackson.dbtable.JsonMaintSadImportKodtsiContainer;

/**
 * 
 * @author oscardelatorre
 * @date Apr 9, 2016
 * 
 *
 */
public interface MaintSadImportKodtsiService {
	public JsonMaintSadImportKodtsiContainer getList(String utfPayload);
	public JsonMaintSadImportKodtsiContainer doUpdate(String utfPayload);
	
}
