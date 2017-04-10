/**
 * 
 */
package no.systema.skat.z.maintenance.skatncts.service;

import no.systema.skat.z.maintenance.skatncts.mapper.jsonjackson.dbtable.MaintDkxkodfMapper;
import no.systema.skat.z.maintenance.skatncts.model.jsonjackson.dbtable.JsonMaintDkxkodfContainer;


/**
 * 
 * @author oscardelatorre
 * @date Apr 10, 2017
 * 
 * 
 */
public class MaintDkxkodfServiceImpl implements MaintDkxkodfService {
	/**
	 * 
	 */
	public JsonMaintDkxkodfContainer getList(String utfPayload) {
		JsonMaintDkxkodfContainer container = null;
		try{
			MaintDkxkodfMapper mapper = new MaintDkxkodfMapper();
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
	public JsonMaintDkxkodfContainer doUpdate(String utfPayload) {
		JsonMaintDkxkodfContainer container = null;
		try{
			MaintDkxkodfMapper mapper = new MaintDkxkodfMapper();
			container = mapper.getContainer(utfPayload);
		}catch(Exception e){
			e.printStackTrace();
		}
		
		return container;
		
	}

}
