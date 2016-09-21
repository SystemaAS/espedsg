/**
 * 
 */
package no.systema.z.main.maintenance.service.sad;

import no.systema.z.main.maintenance.model.jsonjackson.dbtable.sad.JsonMaintMainTrkodl01Container;
import no.systema.z.main.maintenance.mapper.jsonjackson.dbtable.sad.MaintMainTrkodl01Mapper;

/**
 * 
 * @author oscardelatorre
 * @date Sep 21, 2016
 * 
 * 
 */
public class MaintMainTrkodl01ServiceImpl implements MaintMainTrkodl01Service {
	/**
	 * 
	 */
	public JsonMaintMainTrkodl01Container getList(String utfPayload) {
		JsonMaintMainTrkodl01Container container = null;
		try{
			MaintMainTrkodl01Mapper mapper = new MaintMainTrkodl01Mapper();
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
	public JsonMaintMainTrkodl01Container doUpdate(String utfPayload) {
		JsonMaintMainTrkodl01Container container = null;
		try{
			MaintMainTrkodl01Mapper mapper = new MaintMainTrkodl01Mapper();
			container = mapper.getContainer(utfPayload);
		}catch(Exception e){
			e.printStackTrace();
		}
		
		return container;
		
	}

}
