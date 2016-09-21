/**
 * 
 */
package no.systema.z.main.maintenance.service.sad;

import no.systema.z.main.maintenance.model.jsonjackson.dbtable.sad.JsonMaintMainTrkodl01Container;

/**
 * 
 * @author oscardelatorre
 * @date Sep 21, 2016
 * 
 *
 */
public interface MaintMainTrkodl01Service {
	public JsonMaintMainTrkodl01Container getList(String utfPayload);
	public JsonMaintMainTrkodl01Container doUpdate(String utfPayload);
	
}
