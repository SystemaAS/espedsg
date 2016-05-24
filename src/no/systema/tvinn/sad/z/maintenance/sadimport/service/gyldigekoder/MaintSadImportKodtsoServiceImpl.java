/**
 * 
 */
package no.systema.tvinn.sad.z.maintenance.sadimport.service.gyldigekoder;

import no.systema.tvinn.sad.z.maintenance.sadimport.model.jsonjackson.dbtable.gyldigekoder.JsonMaintSadImportKodtsoContainer;
import no.systema.tvinn.sad.z.maintenance.sadimport.mapper.jsonjackson.dbtable.gyldigekoder.MaintSadImportKodtsoMapper;

/**
 * 
 * @author oscardelatorre
 * @date May 23, 2017
 * 
 * 
 */
public class MaintSadImportKodtsoServiceImpl implements MaintSadImportKodtsoService {
	/**
	 * 
	 */
	public JsonMaintSadImportKodtsoContainer getList(String utfPayload) {
		JsonMaintSadImportKodtsoContainer container = null;
		try{
			MaintSadImportKodtsoMapper mapper = new MaintSadImportKodtsoMapper();
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
	public JsonMaintSadImportKodtsoContainer doUpdate(String utfPayload) {
		JsonMaintSadImportKodtsoContainer container = null;
		try{
			MaintSadImportKodtsoMapper mapper = new MaintSadImportKodtsoMapper();
			container = mapper.getContainer(utfPayload);
		}catch(Exception e){
			e.printStackTrace();
		}
		return container;
	}

}
