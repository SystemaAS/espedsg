/**
 * 
 */
package no.systema.skat.z.maintenance.main.service;

import no.systema.skat.z.maintenance.main.mapper.jsonjackson.dbtable.MaintDktfiMapper;
import no.systema.skat.z.maintenance.main.model.jsonjackson.dbtable.JsonMaintDktfiContainer;

/**
 * 
 * @author oscardelatorre
 * @date Mar 09, 2016
 * 
 * 
 */
public class MaintDktfiServiceImpl implements MaintDktfiService {
	/**
	 * 
	 */
	public JsonMaintDktfiContainer getList(String utfPayload) {
		JsonMaintDktfiContainer container = null;
		try{
			MaintDktfiMapper mapper = new MaintDktfiMapper();
			container = mapper.getContainer(utfPayload);
		}catch(Exception e){
			e.printStackTrace();
		}
		
		return container;
		
	}
	/**
	 * 
	 * @param utfPayload
	 * @return
	 */
	public JsonMaintDktfiContainer doUpdate(String utfPayload) {
		JsonMaintDktfiContainer container = null;
		try{
			MaintDktfiMapper mapper = new MaintDktfiMapper();
			container = mapper.getContainer(utfPayload);
		}catch(Exception e){
			e.printStackTrace();
		}
		
		return container;
		
	}

}
