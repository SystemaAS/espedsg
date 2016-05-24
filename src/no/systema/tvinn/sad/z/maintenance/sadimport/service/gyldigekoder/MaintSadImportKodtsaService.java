/**
 * 
 */
package no.systema.tvinn.sad.z.maintenance.sadimport.service.gyldigekoder;

import no.systema.tvinn.sad.z.maintenance.sadimport.model.jsonjackson.dbtable.gyldigekoder.JsonMaintSadImportKodtsaContainer;

/**
 * 
 * @author oscardelatorre
 * @date May 23, 2017
 * 
 *
 */
public interface MaintSadImportKodtsaService {
	public JsonMaintSadImportKodtsaContainer getList(String utfPayload);
	public JsonMaintSadImportKodtsaContainer doUpdate(String utfPayload);
	
}
