/**
 * 
 */
package no.systema.skat.nctsexport.service;

import no.systema.skat.model.jsonjackson.codes.JsonSkatCodeContainer;
import no.systema.skat.model.jsonjackson.codes.JsonSkatNctsCodeContainer;


/**
 * @author oscardelatorre
 * @date Apr 15, 2016
 * 
 */
public interface SkatNctsExportGeneralCodesChildWindowService {
	public JsonSkatCodeContainer getCodeContainer(String utfPayload) throws Exception;
	public JsonSkatNctsCodeContainer getNctsCodeContainer(String utfPayload) throws Exception;
}
