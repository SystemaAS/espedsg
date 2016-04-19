/**
 * 
 */
package no.systema.skat.skatimport.service;

import no.systema.skat.model.jsonjackson.codes.JsonSkatCodeContainer;


/**
 * @author oscardelatorre
 * @date Apr 19, 2016
 * 
 */
public interface SkatImportGeneralCodesChildWindowService {
	public JsonSkatCodeContainer getCodeContainer(String utfPayload) throws Exception;
}
