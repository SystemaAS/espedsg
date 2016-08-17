/**
 * 
 */
package no.systema.z.main.maintenance.service;

import no.systema.z.main.maintenance.model.jsonjackson.dbtable.JsonMaintMainKodtaHodel1Container;
import no.systema.z.main.maintenance.mapper.jsonjackson.dbtable.MaintMainKodtaHodel1Mapper;

/**
 * 
 * @author oscardelatorre
 * @date Aug 9, 2016
 * 
 * 
 */
public class MaintMainKodtaHodel1ServiceImpl implements MaintMainKodtaHodel1Service {
	/**
	 * 
	 */
	public JsonMaintMainKodtaHodel1Container getList(String utfPayload) {
		JsonMaintMainKodtaHodel1Container container = null;
		try{
			MaintMainKodtaHodel1Mapper mapper = new MaintMainKodtaHodel1Mapper();
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
	public JsonMaintMainKodtaHodel1Container doUpdate(String utfPayload) {
		JsonMaintMainKodtaHodel1Container container = null;
		try{
			MaintMainKodtaHodel1Mapper mapper = new MaintMainKodtaHodel1Mapper();
			container = mapper.getContainer(utfPayload);
		}catch(Exception e){
			e.printStackTrace();
		}
		
		return container;
		
	}
	
}
