/**
 * 
 */
package no.systema.tvinn.sad.z.maintenance.sadimport.service;

import no.systema.tvinn.sad.z.maintenance.sadimport.model.jsonjackson.dbtable.JsonMaintSadImportKodtlikContainer;
import no.systema.tvinn.sad.z.maintenance.sadimport.mapper.jsonjackson.dbtable.MaintSadImportKodtlikMapper;

/**
 * 
 * @author oscardelatorre
 * @date May 22, 2014
 * 
 * 
 */
public class MaintSadImportKodtlikServiceImpl implements MaintSadImportKodtlikService {
	/**
	 * 
	 */
	public JsonMaintSadImportKodtlikContainer getList(String utfPayload) {
		JsonMaintSadImportKodtlikContainer list = null;
		try{
			MaintSadImportKodtlikMapper mapper = new MaintSadImportKodtlikMapper();
			list = mapper.getContainer(utfPayload);
		}catch(Exception e){
			e.printStackTrace();
		}
		
		return list;
		
	}

}
