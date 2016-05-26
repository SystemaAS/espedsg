/**
 * 
 */
package no.systema.tvinn.sad.z.maintenance.sadimport.service;

import no.systema.tvinn.sad.z.maintenance.sadimport.model.jsonjackson.dbtable.JsonMaintSadImportSoktariContainer;

/**
 * 
 * @author oscardelatorre
 * @date May 26, 2016
 * 
 *
 */
public interface MaintSadImportSoktariService {
	public JsonMaintSadImportSoktariContainer getList(String utfPayload);
	public JsonMaintSadImportSoktariContainer doUpdate(String utfPayload);
	
}
