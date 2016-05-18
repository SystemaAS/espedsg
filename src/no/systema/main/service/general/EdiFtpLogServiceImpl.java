/**
 * 
 */
package no.systema.main.service.general;

import no.systema.main.model.jsonjackson.general.JsonEdiFtpLogContainer;
import no.systema.main.mapper.jsonjackson.general.EdiFtpLogMapper;

/**
 * 
 * @author oscardelatorre
 * @date May 18, 2016
 * 
 */

public class EdiFtpLogServiceImpl implements EdiFtpLogService{
	/**
	 * 
	 */
	public JsonEdiFtpLogContainer getContainer (String utfPayload) {
		JsonEdiFtpLogContainer container = null;
		try{
			EdiFtpLogMapper mapper = new EdiFtpLogMapper();
			container = mapper.getContainer(utfPayload);
		}catch(Exception e){
			e.printStackTrace();
		}
		return container;
	}
	
}
