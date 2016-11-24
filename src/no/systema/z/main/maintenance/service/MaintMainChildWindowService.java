/**
 * 
 */
package no.systema.z.main.maintenance.service;

import no.systema.z.main.maintenance.model.jsonjackson.dbtable.JsonMaintMainChildWindowsKodeContainer;
import no.systema.z.main.maintenance.model.jsonjackson.dbtable.JsonMaintMainCundcContainer;

/**
 * 
 * @author Fredrik Möller
 * @date Nov 22, 2016
 * 
 *
 */
public interface MaintMainChildWindowService {
	/**
	 * Get JsonMaintMainChildWindowsKodeContainer
	 * 
	 * @param utfPayload
	 * @return JsonMaintMainChildWindowsKodeContainer
	 */
	public JsonMaintMainChildWindowsKodeContainer getContainer(String utfPayload);
	public JsonMaintMainCundcContainer doUpdate(String utfPayload);
	
}
