/**
 * 
 */
package no.systema.tvinn.sad.z.maintenance.felles.service;

import no.systema.tvinn.sad.z.maintenance.felles.model.jsonjackson.dbtable.JsonMaintSadFellesKodtsiContainer;
import no.systema.tvinn.sad.z.maintenance.sadimport.mapper.jsonjackson.dbtable.MaintSadImportKodtsiMapper;

/**
 * 
 * @author oscardelatorre
 * @date Okt 20, 2016
 * 
 * 
 */
public class MaintSadFellesKodtsiServiceImpl implements MaintSadFellesKodtsiService {
	/**
	 * 
	 */
	public JsonMaintSadFellesKodtsiContainer getList(String utfPayload) {
		JsonMaintSadFellesKodtsiContainer container = null;
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
	public JsonMaintSadFellesKodtsiContainer doUpdate(String utfPayload) {
		JsonMaintSadFellesKodtsiContainer container = null;
		try{
			MaintSadImportKodtsiMapper mapper = new MaintSadImportKodtsiMapper();
			container = mapper.getContainer(utfPayload);
		}catch(Exception e){
			e.printStackTrace();
		}
		
		return container;
		
	}

}
