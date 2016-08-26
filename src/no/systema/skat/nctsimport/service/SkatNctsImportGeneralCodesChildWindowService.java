/**
 * 
 */
package no.systema.skat.nctsimport.service;

import no.systema.skat.model.jsonjackson.codes.JsonSkatCodeContainer;
import no.systema.skat.model.jsonjackson.codes.JsonSkatNctsCodeContainer;


/**
 * @author oscardelatorre
 * @date Aug 25, 2016
 * 
 */
public interface SkatNctsImportGeneralCodesChildWindowService {
	public JsonSkatCodeContainer getCodeContainer(String utfPayload) throws Exception;
	public JsonSkatNctsCodeContainer getNctsCodeContainer(String utfPayload) throws Exception;
}
