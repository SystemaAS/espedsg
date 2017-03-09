/**
 * 
 */
package no.systema.skat.z.maintenance.main.service;

import no.systema.skat.z.maintenance.main.mapper.jsonjackson.dbtable.MaintDktardMapper;
import no.systema.skat.z.maintenance.main.model.jsonjackson.dbtable.JsonMaintDktardContainer;

/**
 * 
 * @author oscardelatorre
 * @date Mar 09, 2016
 * 
 * 
 */
public class MaintDktardServiceImpl implements MaintDktardService {
	/**
	 * 
	 */
	public JsonMaintDktardContainer getList(String utfPayload) {
		JsonMaintDktardContainer container = null;
		try{
			MaintDktardMapper mapper = new MaintDktardMapper();
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
	public JsonMaintDktardContainer doUpdate(String utfPayload) {
		JsonMaintDktardContainer container = null;
		try{
			MaintDktardMapper mapper = new MaintDktardMapper();
			container = mapper.getContainer(utfPayload);
		}catch(Exception e){
			e.printStackTrace();
		}
		
		return container;
		
	}

}
