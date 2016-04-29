/**
 * 
 */
package no.systema.tvinn.sad.z.maintenance.sadimport.service;

import no.systema.tvinn.sad.z.maintenance.sadimport.model.jsonjackson.dbtable.JsonMaintSadImportKodtlbContainer;
import no.systema.tvinn.sad.z.maintenance.sadimport.mapper.jsonjackson.dbtable.MaintSadImportKodtlbMapper;

/**
 * 
 * @author oscardelatorre
 * @date Apr 29, 2016
 * 
 * 
 */
public class MaintSadImportKodtlbServiceImpl implements MaintSadImportKodtlbService {
	/**
	 * 
	 */
	public JsonMaintSadImportKodtlbContainer getList(String utfPayload) {
		JsonMaintSadImportKodtlbContainer container = null;
		try{
			MaintSadImportKodtlbMapper mapper = new MaintSadImportKodtlbMapper();
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
	public JsonMaintSadImportKodtlbContainer doUpdate(String utfPayload) {
		JsonMaintSadImportKodtlbContainer container = null;
		try{
			MaintSadImportKodtlbMapper mapper = new MaintSadImportKodtlbMapper();
			container = mapper.getContainer(utfPayload);
		}catch(Exception e){
			e.printStackTrace();
		}
		
		return container;
		
	}

}
