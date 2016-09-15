/**
 * 
 */
package no.systema.tvinn.sad.z.maintenance.sadexport.service;

import no.systema.tvinn.sad.z.maintenance.sadimport.model.jsonjackson.dbtable.gyldigekoder.JsonMaintSadImportKodtseContainer;

/**
 * 
 * @author Fredrik Möller
 * @date Sept 12, 2016
 * 
 *
 */
public interface MaintSadExportKodtseService {
	public JsonMaintSadImportKodtseContainer getList(String utfPayload);
	public JsonMaintSadImportKodtseContainer doUpdate(String utfPayload);
}
