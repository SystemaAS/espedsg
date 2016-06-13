/**
 * 
 */
package no.systema.skat.z.maintenance.main.service;

import no.systema.skat.z.maintenance.main.mapper.jsonjackson.dbtable.MaintDktvkMapper;
import no.systema.skat.z.maintenance.main.model.jsonjackson.dbtable.JsonMaintDktvkContainer;

/**
 * 
 * @author oscardelatorre
 * @date Jun 13, 2016
 * 
 * 
 */
public class MaintDktvkServiceImpl implements MaintDktvkService {
	/**
	 * 
	 */
	public JsonMaintDktvkContainer getList(String utfPayload) {
		JsonMaintDktvkContainer container = null;
		try{
			MaintDktvkMapper mapper = new MaintDktvkMapper();
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
	public JsonMaintDktvkContainer doUpdate(String utfPayload) {
		JsonMaintDktvkContainer container = null;
		try{
			MaintDktvkMapper mapper = new MaintDktvkMapper();
			container = mapper.getContainer(utfPayload);
		}catch(Exception e){
			e.printStackTrace();
		}
		
		return container;
		
	}

}
