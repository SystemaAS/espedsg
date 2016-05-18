/**
 * 
 */
package no.systema.main.service.general;

import org.springframework.core.io.Resource;
import no.systema.main.model.jsonjackson.general.JsonEdiFtpLogContainer;
/**
 * 
 * This interface lends a cgi request to the back-end usually returning a Payload String (JSON or other list structure)
 * 
 * @author oscardelatorre
 * @date May 18, 2016
 * 
 */
public interface EdiFtpLogService {
	public JsonEdiFtpLogContainer getContainer(String utfPayload);
	
}
