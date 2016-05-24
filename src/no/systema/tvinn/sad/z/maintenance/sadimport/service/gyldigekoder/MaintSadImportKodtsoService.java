/**
 * 
 */
package no.systema.tvinn.sad.z.maintenance.sadimport.service.gyldigekoder;

import no.systema.tvinn.sad.z.maintenance.sadimport.model.jsonjackson.dbtable.gyldigekoder.JsonMaintSadImportKodtsoContainer;

/**
 * 
 * @author oscardelatorre
 * @date May 24, 2017
 * 
 *
 */
public interface MaintSadImportKodtsoService {
	public JsonMaintSadImportKodtsoContainer getList(String utfPayload);
	public JsonMaintSadImportKodtsoContainer doUpdate(String utfPayload);
	
}
