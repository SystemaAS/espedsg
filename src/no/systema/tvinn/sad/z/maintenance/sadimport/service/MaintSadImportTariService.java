/**
 * 
 */
package no.systema.tvinn.sad.z.maintenance.sadimport.service;

import no.systema.tvinn.sad.z.maintenance.sadimport.model.jsonjackson.dbtable.JsonMaintSadImportTariContainer;

/**
 * 
 * @author oscardelatorre
 * @date May 6, 2016
 * 
 *
 */
public interface MaintSadImportTariService {
	public JsonMaintSadImportTariContainer getList(String utfPayload);
	public JsonMaintSadImportTariContainer doUpdate(String utfPayload);
	
}
