/**
 * 
 */
package no.systema.tvinn.sad.z.maintenance.sadexport.service;

import no.systema.tvinn.sad.z.maintenance.sadimport.mapper.jsonjackson.dbtable.gyldigekoder.MaintSadImportKodtseMapper;
import no.systema.tvinn.sad.z.maintenance.sadimport.model.jsonjackson.dbtable.gyldigekoder.JsonMaintSadImportKodtseContainer;

/**
 * 
 * @author Fredrik Möller
 * @date Sep 7, 2016
 * 
 * 
 */
public class MaintSadExportKodtseServiceImpl implements MaintSadExportKodtseService {
	/**
	 * 
	 */
	//TODO move to sad package
	public JsonMaintSadImportKodtseContainer getList(String utfPayload) {
		JsonMaintSadImportKodtseContainer container = null;
		try{
			MaintSadImportKodtseMapper mapper = new MaintSadImportKodtseMapper();
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
	public JsonMaintSadImportKodtseContainer doUpdate(String utfPayload) {
		JsonMaintSadImportKodtseContainer container = null;
		try{
			MaintSadImportKodtseMapper mapper = new MaintSadImportKodtseMapper();
			container = mapper.getContainer(utfPayload);
		}catch(Exception e){
			e.printStackTrace();
		}
		
		return container;
		
	}

}
