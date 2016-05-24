/**
 * 
 */
package no.systema.tvinn.sad.z.maintenance.sadimport.service.gyldigekoder;

import no.systema.tvinn.sad.z.maintenance.sadimport.model.jsonjackson.dbtable.gyldigekoder.JsonMaintSadImportKodtseContainer;
import no.systema.tvinn.sad.z.maintenance.sadimport.mapper.jsonjackson.dbtable.gyldigekoder.MaintSadImportKodtseMapper;

/**
 * 
 * @author oscardelatorre
 * @date May 23, 2017
 * 
 * 
 */
public class MaintSadImportKodtseServiceImpl implements MaintSadImportKodtseService {
	/**
	 * 
	 */
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
