/**
 * 
 */
package no.systema.z.main.maintenance.service;

import no.systema.z.main.maintenance.model.jsonjackson.dbtable.JsonMaintMainKodtvKodtwContainer;
import no.systema.z.main.maintenance.mapper.jsonjackson.dbtable.MaintMainKodtvKodtwMapper;

/**
 * 
 * @author oscardelatorre
 * @date Aug 5, 2016
 * 
 * 
 */
public class MaintMainKodtvKodtwServiceImpl implements MaintMainKodtvKodtwService {
	/**
	 * 
	 */
	public JsonMaintMainKodtvKodtwContainer getList(String utfPayload) {
		JsonMaintMainKodtvKodtwContainer container = null;
		try{
			MaintMainKodtvKodtwMapper mapper = new MaintMainKodtvKodtwMapper();
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
	public JsonMaintMainKodtvKodtwContainer doUpdate(String utfPayload) {
		JsonMaintMainKodtvKodtwContainer container = null;
		try{
			MaintMainKodtvKodtwMapper mapper = new MaintMainKodtvKodtwMapper();
			container = mapper.getContainer(utfPayload);
		}catch(Exception e){
			e.printStackTrace();
		}
		
		return container;
		
	}

}
