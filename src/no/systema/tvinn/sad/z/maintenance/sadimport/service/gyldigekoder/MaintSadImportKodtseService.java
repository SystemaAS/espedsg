/**
 * 
 */
package no.systema.tvinn.sad.z.maintenance.sadimport.service.gyldigekoder;

import no.systema.tvinn.sad.z.maintenance.sadimport.model.jsonjackson.dbtable.gyldigekoder.JsonMaintSadImportKodtseContainer;

/**
 * 
 * @author oscardelatorre
 * @date May 23, 2017
 * 
 *
 */
public interface MaintSadImportKodtseService {
	public JsonMaintSadImportKodtseContainer getList(String utfPayload);
	public JsonMaintSadImportKodtseContainer doUpdate(String utfPayload);
	
}
