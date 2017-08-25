/**
 * 
 */
package no.systema.tror.service.landimport;

import no.systema.tror.model.jsonjackson.JsonTrorOrderHeaderContainer;


/**
 * 
 * @author oscardelatorre
 * @date Aug 11, 2017
 * 
 *
 */
public interface TrorMainOrderHeaderLandimportService {
	public JsonTrorOrderHeaderContainer getOrderHeaderContainer(String utfPayload);

}
