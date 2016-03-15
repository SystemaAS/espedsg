/**
 * 
 */
package no.systema.skat.admin.service;

import no.systema.skat.admin.model.jsonjackson.topic.JsonSkatAdminTransportListContainer;
import no.systema.skat.admin.model.jsonjackson.topic.JsonSkatAdminNorskImportListContainer;
import no.systema.skat.admin.model.jsonjackson.topic.JsonSkatAdminNorskExportListContainer;


/**
 * 
 * @author oscardelatorre
 * @date Feb 20, 2014
 * 
 */
public interface SkatAdminTransportService {
	public JsonSkatAdminTransportListContainer getSkatAdminTransportListContainer(String utfPayload);
	public JsonSkatAdminNorskImportListContainer getSkatAdminNorskImportListContainer(String utfPayload);
	public JsonSkatAdminNorskExportListContainer getSkatAdminNorskExportListContainer(String utfPayload);
	
}
