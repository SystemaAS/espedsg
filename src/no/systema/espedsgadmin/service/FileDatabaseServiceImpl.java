/**
 * 
 */
package no.systema.espedsgadmin.service;

import java.util.List;

import no.systema.espedsgadmin.db.FileDatabaseManager;
import no.systema.espedsgadmin.model.CustomerApplicationObject;

/**
 * @author oscardelatorre
 * @date Apr 1, 2014
 * 
 *
 */
public class FileDatabaseServiceImpl implements FileDatabaseService {
	
	/**
	 * 
	 * @param utfPayload
	 * @return
	 * 
	 */
	public List<CustomerApplicationObject> getCustomerApplicationList(){
		
		FileDatabaseManager dbMgr = new FileDatabaseManager();
		List<CustomerApplicationObject> list = dbMgr.getCustAppMap();

		return list;
	}
}
