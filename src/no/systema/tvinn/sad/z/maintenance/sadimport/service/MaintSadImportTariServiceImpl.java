/**
 * 
 */
package no.systema.tvinn.sad.z.maintenance.sadimport.service;

import no.systema.tvinn.sad.z.maintenance.sadimport.model.jsonjackson.dbtable.JsonMaintSadImportTariContainer;
import no.systema.tvinn.sad.z.maintenance.sadimport.mapper.jsonjackson.dbtable.MaintSadImportTariMapper;

/**
 * 
 * @author oscardelatorre
 * @date May 2, 2016
 * 
 * 
 */
public class MaintSadImportTariServiceImpl implements MaintSadImportTariService {
	/**
	 * 
	 */
	public JsonMaintSadImportTariContainer getList(String utfPayload) {
		JsonMaintSadImportTariContainer container = null;
		try{
			MaintSadImportTariMapper mapper = new MaintSadImportTariMapper();
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
	public JsonMaintSadImportTariContainer doUpdate(String utfPayload) {
		JsonMaintSadImportTariContainer container = null;
		try{
			MaintSadImportTariMapper mapper = new MaintSadImportTariMapper();
			container = mapper.getContainer(utfPayload);
		}catch(Exception e){
			e.printStackTrace();
		}
		
		return container;
		
	}

}
