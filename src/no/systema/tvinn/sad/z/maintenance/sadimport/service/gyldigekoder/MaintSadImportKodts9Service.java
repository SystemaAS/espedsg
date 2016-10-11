/**
 * 
 */
package no.systema.tvinn.sad.z.maintenance.sadimport.service.gyldigekoder;

import no.systema.tvinn.sad.z.maintenance.sadimport.model.jsonjackson.dbtable.gyldigekoder.JsonMaintSadImportKodts9Container;

/**
 * 
 * @author oscardelatorre
 * @date Oct 20, 2016
 * 
 *
 */
public interface MaintSadImportKodts9Service {
	public JsonMaintSadImportKodts9Container getList(String utfPayload);
	public JsonMaintSadImportKodts9Container doUpdate(String utfPayload);
	
}
