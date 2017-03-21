/**
 * 
 */
package no.systema.skat.z.maintenance.main.service;

import no.systema.skat.z.maintenance.main.mapper.jsonjackson.dbtable.MaintDkthaMapper;
import no.systema.skat.z.maintenance.main.model.jsonjackson.dbtable.JsonMaintDkthaContainer;

/**
 * 
 * @author oscardelatorre
 * @date Mar 21, 2016
 * 
 * 
 */
public class MaintDkthaServiceImpl implements MaintDkthaService {
	/**
	 * 
	 */
	public JsonMaintDkthaContainer getList(String utfPayload) {
		JsonMaintDkthaContainer container = null;
		try{
			MaintDkthaMapper mapper = new MaintDkthaMapper();
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
	public JsonMaintDkthaContainer doUpdate(String utfPayload) {
		JsonMaintDkthaContainer container = null;
		try{
			MaintDkthaMapper mapper = new MaintDkthaMapper();
			container = mapper.getContainer(utfPayload);
		}catch(Exception e){
			e.printStackTrace();
		}
		
		return container;
		
	}

}
