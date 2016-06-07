/**
 * 
 */
package no.systema.z.maintenance.service;

import no.systema.z.maintenance.model.jsonjackson.dbtable.JsonMaintKodtvaContainer;
import no.systema.z.maintenance.mapper.jsonjackson.dbtable.MaintKodtvaMapper;;

/**
 * 
 * @author oscardelatorre
 * @date Jun 7, 2016
 * 
 * 
 */
public class MaintKodtvaServiceImpl implements MaintKodtvaService {
	/**
	 * 
	 */
	public JsonMaintKodtvaContainer getList(String utfPayload) {
		JsonMaintKodtvaContainer container = null;
		try{
			MaintKodtvaMapper mapper = new MaintKodtvaMapper();
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
	public JsonMaintKodtvaContainer doUpdate(String utfPayload) {
		JsonMaintKodtvaContainer container = null;
		try{
			MaintKodtvaMapper mapper = new MaintKodtvaMapper();
			container = mapper.getContainer(utfPayload);
		}catch(Exception e){
			e.printStackTrace();
		}
		
		return container;
		
	}

}
