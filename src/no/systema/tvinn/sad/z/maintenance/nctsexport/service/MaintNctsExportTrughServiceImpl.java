/**
 * 
 */
package no.systema.tvinn.sad.z.maintenance.nctsexport.service;

import no.systema.tvinn.sad.z.maintenance.nctsexport.mapper.jsonjackson.dbtable.MaintNctsExportTrughMapper;
import no.systema.tvinn.sad.z.maintenance.nctsexport.model.jsonjackson.dbtable.JsonMaintNctsTrughContainer;

/**
 * 
 * @author Fredrik Möller
 * @date Sep 19, 2016
 * 
 * 
 */
public class MaintNctsExportTrughServiceImpl implements MaintNctsExportTrughService {
	/**
	 * 
	 */
	public JsonMaintNctsTrughContainer getList(String utfPayload) {
		JsonMaintNctsTrughContainer container = null;
		try{
			MaintNctsExportTrughMapper mapper = new MaintNctsExportTrughMapper();
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
	public JsonMaintNctsTrughContainer doUpdate(String utfPayload) {
		JsonMaintNctsTrughContainer container = null;
		try{
			MaintNctsExportTrughMapper mapper = new MaintNctsExportTrughMapper();
			container = mapper.getContainer(utfPayload);
		}catch(Exception e){
			e.printStackTrace();
		}
		
		return container;
		
	}

}
