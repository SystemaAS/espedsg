/**
 * 
 */
package no.systema.tvinn.sad.z.maintenance.sadimport.service;

import no.systema.tvinn.sad.z.maintenance.sadimport.model.jsonjackson.dbtable.JsonMaintSadImportSoktariContainer;
import no.systema.tvinn.sad.z.maintenance.sadimport.mapper.jsonjackson.dbtable.MaintSadImportSoktariMapper;

/**
 * 
 * @author oscardelatorre
 * @date May 26, 2016
 * 
 * 
 */
public class MaintSadImportSoktariServiceImpl implements MaintSadImportSoktariService {
	/**
	 * 
	 */
	public JsonMaintSadImportSoktariContainer getList(String utfPayload) {
		JsonMaintSadImportSoktariContainer container = null;
		try{
			MaintSadImportSoktariMapper mapper = new MaintSadImportSoktariMapper();
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
	public JsonMaintSadImportSoktariContainer doUpdate(String utfPayload) {
		JsonMaintSadImportSoktariContainer container = null;
		try{
			MaintSadImportSoktariMapper mapper = new MaintSadImportSoktariMapper();
			container = mapper.getContainer(utfPayload);
		}catch(Exception e){
			e.printStackTrace();
		}
		
		return container;
		
	}

}
