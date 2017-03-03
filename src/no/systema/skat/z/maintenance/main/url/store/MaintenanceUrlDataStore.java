/**
 * 
 */
package no.systema.skat.z.maintenance.main.url.store;
import no.systema.main.util.AppConstants;
/**
 * 
 * Static URLs
 * @author oscardelatorre
 * @date Jun 13, 2016
 * 
 * 
 */
public final class MaintenanceUrlDataStore {
	//--------------------------------------------
	//[1] FETCH DB Table list or Specific record
	//--------------------------------------------
	//DKT057R
	//ALL --> http://gw.systema.no:8080/syjservicesst/syjsDKT057R.do?user=OSCAR
	//ONE --> http://gw.systema.no:8080/syjservicesst/syjsDKT057R.do?user=OSCAR&kvakod=USD
	static public String MAINTENANCE_BASE_DKT057R_GET_LIST_URL = AppConstants.HTTP_ROOT_SERVLET_JSERVICES + "/syjservicesst/syjsDKT057R.do";
	//DKG210R
	//Specific code --> http://gw.systema.no:8080/syjservicesst/syjsDKG210R.do?user=OSCAR&dkkd_typ=001&dkkd_kd=01
	static public String MAINTENANCE_BASE_DKG210R_GET_LIST_URL = AppConstants.HTTP_ROOT_SERVLET_JSERVICES + "/syjservicesst/syjsDKG210R.do";

	//----------------------------------
	//[1] UPDATE DB record
	// mode = (U)pdate, (A)dd, (D)elete
	//----------------------------------
	//DKT057R_U
	//ALL --> http://gw.systema.no:8080/syjservicesst/syjsDKT057R_U.do?user=OSCAR&mode=U&<record>attributes...
	static public String MAINTENANCE_BASE_DKT057R_DML_UPDATE_URL = AppConstants.HTTP_ROOT_SERVLET_JSERVICES + "/syjservicesst/syjsDKT057R_U.do";
	//DKG210R
	//Specific code --> TODO http://gw.systema.no:8080/syjservicesst/syjsDKX030R_U.do?user=OSCAR&mode=U&<record>attributes...
	static public String MAINTENANCE_BASE_DKG210R_DML_UPDATE_URL = AppConstants.HTTP_ROOT_SERVLET_JSERVICES + "/syjservicesst/syjsDKG210R_U.do";
	
}
