/**
 * 
 */
package no.systema.tvinn.sad.z.maintenance.sadimport.service;

import no.systema.tvinn.sad.z.maintenance.sadimport.model.jsonjackson.dbtable.JsonMaintSadImportSadlContainer;
import no.systema.tvinn.sad.z.maintenance.sadimport.mapper.jsonjackson.dbtable.MaintSadImportSadlMapper;

/**
 * 
 * @author oscardelatorre
 * @date May 12, 2016
 * 
 * 
 */
public class MaintSadImportSadlServiceImpl implements MaintSadImportSadlService {
	/**
	 * 
	 */
	public JsonMaintSadImportSadlContainer getList(String utfPayload) {
		JsonMaintSadImportSadlContainer container = null;
		try{
			MaintSadImportSadlMapper mapper = new MaintSadImportSadlMapper();
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
	public JsonMaintSadImportSadlContainer doUpdate(String utfPayload) {
		JsonMaintSadImportSadlContainer container = null;
		try{
			MaintSadImportSadlMapper mapper = new MaintSadImportSadlMapper();
			container = mapper.getContainer(utfPayload);
		}catch(Exception e){
			e.printStackTrace();
		}
		
		return container;
		
	}

}
