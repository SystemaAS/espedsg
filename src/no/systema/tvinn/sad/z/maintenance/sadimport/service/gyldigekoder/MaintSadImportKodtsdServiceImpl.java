/**
 * 
 */
package no.systema.tvinn.sad.z.maintenance.sadimport.service.gyldigekoder;

import no.systema.tvinn.sad.z.maintenance.sadimport.model.jsonjackson.dbtable.gyldigekoder.JsonMaintSadImportKodtsdContainer;
import no.systema.tvinn.sad.z.maintenance.sadimport.mapper.jsonjackson.dbtable.gyldigekoder.MaintSadImportKodtsdMapper;

/**
 * 
 * @author oscardelatorre
 * @date May 23, 2017
 * 
 * 
 */
public class MaintSadImportKodtsdServiceImpl implements MaintSadImportKodtsdService {
	/**
	 * 
	 */
	public JsonMaintSadImportKodtsdContainer getList(String utfPayload) {
		JsonMaintSadImportKodtsdContainer container = null;
		try{
			MaintSadImportKodtsdMapper mapper = new MaintSadImportKodtsdMapper();
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
	public JsonMaintSadImportKodtsdContainer doUpdate(String utfPayload) {
		JsonMaintSadImportKodtsdContainer container = null;
		try{
			MaintSadImportKodtsdMapper mapper = new MaintSadImportKodtsdMapper();
			container = mapper.getContainer(utfPayload);
		}catch(Exception e){
			e.printStackTrace();
		}
		return container;
	}

}
