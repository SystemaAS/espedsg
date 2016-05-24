/**
 * 
 */
package no.systema.tvinn.sad.z.maintenance.sadimport.service.gyldigekoder;

import no.systema.tvinn.sad.z.maintenance.sadimport.model.jsonjackson.dbtable.gyldigekoder.JsonMaintSadImportKodtsaContainer;
import no.systema.tvinn.sad.z.maintenance.sadimport.mapper.jsonjackson.dbtable.gyldigekoder.MaintSadImportKodtsaMapper;

/**
 * 
 * @author oscardelatorre
 * @date May 23, 2017
 * 
 * 
 */
public class MaintSadImportKodtsaServiceImpl implements MaintSadImportKodtsaService {
	/**
	 * 
	 */
	public JsonMaintSadImportKodtsaContainer getList(String utfPayload) {
		JsonMaintSadImportKodtsaContainer container = null;
		try{
			MaintSadImportKodtsaMapper mapper = new MaintSadImportKodtsaMapper();
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
	public JsonMaintSadImportKodtsaContainer doUpdate(String utfPayload) {
		JsonMaintSadImportKodtsaContainer container = null;
		try{
			MaintSadImportKodtsaMapper mapper = new MaintSadImportKodtsaMapper();
			container = mapper.getContainer(utfPayload);
		}catch(Exception e){
			e.printStackTrace();
		}
		return container;
	}

}
