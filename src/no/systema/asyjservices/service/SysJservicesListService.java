/**
 * 
 */
package no.systema.asyjservices.service;

import no.systema.asyjservices.model.jsonjackson.JsonSysJservicesMainListContainer;


/**
 * 
 * @author oscardelatorre
 * @date Nov 4, 2015
 * 
 *
 */
public interface SysJservicesListService {
	public JsonSysJservicesMainListContainer getMainListContainer(String utfPayload);

}
