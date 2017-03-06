/**
 * 
 */
package no.systema.skat.z.maintenance.skatimport.service;

import no.systema.skat.z.maintenance.skatimport.mapper.jsonjackson.dbtable.MaintDktseMapper;
import no.systema.skat.z.maintenance.skatimport.model.jsonjackson.dbtable.JsonMaintDktseContainer;

/**
 * 
 * @author oscardelatorre
 * @date Mar 06, 2017
 * 
 * 
 */
public class MaintSkatImportDktseServiceImpl implements MaintSkatImportDktseService {
	/**
	 * 
	 */
	public JsonMaintDktseContainer getList(String utfPayload) {
		JsonMaintDktseContainer container = null;
		try{
			MaintDktseMapper mapper = new MaintDktseMapper();
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
	public JsonMaintDktseContainer doUpdate(String utfPayload) {
		JsonMaintDktseContainer container = null;
		try{
			MaintDktseMapper mapper = new MaintDktseMapper();
			container = mapper.getContainer(utfPayload);
		}catch(Exception e){
			e.printStackTrace();
		}
		
		return container;
		
	}

}
