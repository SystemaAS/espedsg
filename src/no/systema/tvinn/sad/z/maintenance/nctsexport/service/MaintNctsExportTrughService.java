/**
 * 
 */
package no.systema.tvinn.sad.z.maintenance.nctsexport.service;

import no.systema.tvinn.sad.z.maintenance.nctsexport.model.jsonjackson.dbtable.JsonMaintNctsTrughContainer;

/**
 * 
 * @author Fredrik Möller
 * @date Sep 19, 2016
 * 
 *
 */
public interface MaintNctsExportTrughService {
	public JsonMaintNctsTrughContainer getList(String utfPayload);
	public JsonMaintNctsTrughContainer doUpdate(String utfPayload);
}
