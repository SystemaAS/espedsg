/**
 * 
 */
package no.systema.tvinn.sad.z.maintenance.sadimport.service;

import no.systema.tvinn.sad.z.maintenance.sadimport.model.jsonjackson.dbtable.JsonMaintSadImportKodtsiContainer;
import no.systema.tvinn.sad.z.maintenance.sadimport.mapper.jsonjackson.dbtable.MaintSadImportKodtsiMapper;

/**
 * 
 * @author oscardelatorre
 * @date May 22, 2014
 * 
 * 
 */
public class MaintSadImportKodtsiServiceImpl implements MaintSadImportKodtsiService {
	/**
	 * 
	 */
	public JsonMaintSadImportKodtsiContainer getList(String utfPayload) {
		JsonMaintSadImportKodtsiContainer container = null;
		try{
			MaintSadImportKodtsiMapper mapper = new MaintSadImportKodtsiMapper();
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
	public JsonMaintSadImportKodtsiContainer doUpdate(String utfPayload) {
		JsonMaintSadImportKodtsiContainer container = null;
		try{
			MaintSadImportKodtsiMapper mapper = new MaintSadImportKodtsiMapper();
			container = mapper.getContainer(utfPayload);
		}catch(Exception e){
			e.printStackTrace();
		}
		
		return container;
		
	}

}
