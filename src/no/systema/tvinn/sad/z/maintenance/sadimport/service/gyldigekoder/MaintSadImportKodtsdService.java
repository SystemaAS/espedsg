/**
 * 
 */
package no.systema.tvinn.sad.z.maintenance.sadimport.service.gyldigekoder;

import no.systema.tvinn.sad.z.maintenance.sadimport.model.jsonjackson.dbtable.gyldigekoder.JsonMaintSadImportKodtsdContainer;

/**
 * 
 * @author oscardelatorre
 * @date May 23, 2017
 * 
 *
 */
public interface MaintSadImportKodtsdService {
	public JsonMaintSadImportKodtsdContainer getList(String utfPayload);
	public JsonMaintSadImportKodtsdContainer doUpdate(String utfPayload);
	
}
