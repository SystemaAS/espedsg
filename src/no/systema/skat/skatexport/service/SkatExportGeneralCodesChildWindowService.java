/**
 * 
 */
package no.systema.skat.skatexport.service;

import no.systema.skat.model.jsonjackson.codes.JsonSkatCodeContainer;


/**
 * @author oscardelatorre
 * @date Apr 19, 2016
 * 
 */
public interface SkatExportGeneralCodesChildWindowService {
	public JsonSkatCodeContainer getCodeContainer(String utfPayload) throws Exception;
}
