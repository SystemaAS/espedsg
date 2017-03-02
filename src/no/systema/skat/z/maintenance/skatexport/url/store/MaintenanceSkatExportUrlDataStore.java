/**
 * 
 */
package no.systema.skat.z.maintenance.skatexport.url.store;
import no.systema.main.util.AppConstants;
/**
 * 
 * Static URLs
 * @author oscardelatorre
 * @date Jun 13, 2016
 * 
 * 
 */
public final class MaintenanceSkatExportUrlDataStore {
	//--------------------------------------------
	//[1] FETCH DB Table list or Specific record
	//--------------------------------------------
	//DKG210R
	//Specific code --> http://gw.systema.no:8080/syjservicesst/syjsDKG210R.do?user=OSCAR&dkkd_typ=001&dkkd_kd=01
	static public String MAINTENANCE_BASE_DKG210R_GET_LIST_URL = AppConstants.HTTP_ROOT_SERVLET_JSERVICES + "/syjservicesst/syjsDKG210R.do";
	
	//----------------------------------
	//[1] UPDATE DB record
	// mode = (U)pdate, (A)dd, (D)elete
	//----------------------------------
	//DKG210R
	//Specific code --> TODO http://gw.systema.no:8080/syjservicesst/syjsDKX030R_U.do?user=OSCAR&mode=U&<record>attributes...
	static public String MAINTENANCE_BASE_DKG210R_DML_UPDATE_URL = AppConstants.HTTP_ROOT_SERVLET_JSERVICES + "/syjservicesst/syjsDKG210R_U.do";
	
	
}
