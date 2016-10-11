/**
 * 
 */
package no.systema.tvinn.sad.z.maintenance.sadimport.service.gyldigekoder;

import no.systema.tvinn.sad.z.maintenance.sadimport.model.jsonjackson.dbtable.gyldigekoder.JsonMaintSadImportKodts9Container;
import no.systema.tvinn.sad.z.maintenance.sadimport.mapper.jsonjackson.dbtable.gyldigekoder.MaintSadImportKodts9Mapper;

/**
 * 
 * @author oscardelatorre
 * @date Oct 6, 2016
 * 
 * 
 */
public class MaintSadImportKodts9ServiceImpl implements MaintSadImportKodts9Service {
	/**
	 * 
	 */
	public JsonMaintSadImportKodts9Container getList(String utfPayload) {
		JsonMaintSadImportKodts9Container container = null;
		try{
			MaintSadImportKodts9Mapper mapper = new MaintSadImportKodts9Mapper();
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
	public JsonMaintSadImportKodts9Container doUpdate(String utfPayload) {
		JsonMaintSadImportKodts9Container container = null;
		try{
			MaintSadImportKodts9Mapper mapper = new MaintSadImportKodts9Mapper();
			container = mapper.getContainer(utfPayload);
		}catch(Exception e){
			e.printStackTrace();
		}
		
		return container;
		
	}

}
