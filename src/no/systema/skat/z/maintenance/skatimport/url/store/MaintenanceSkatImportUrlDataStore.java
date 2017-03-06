/**
 * 
 */
package no.systema.skat.z.maintenance.skatimport.url.store;
import no.systema.main.util.AppConstants;
/**
 * 
 * Static URLs
 * @author oscardelatorre
 * @date Mar 06, 2017
 * 
 * 
 */
public final class MaintenanceSkatImportUrlDataStore {
	//--------------------------------------------
	//[1] FETCH DB Table list or Specific record
	//--------------------------------------------
	//SELECT --> http://gw.systema.no:8080/syjservicesst/syjsDKT058R.do?user=OSCAR&dkse_knr=0
	static public String MAINTENANCE_BASE_DKT058R_GET_LIST_URL = AppConstants.HTTP_ROOT_SERVLET_JSERVICES + "/syjservicesst/syjsDKT058R.do";

	
	//----------------------------------
	//[1] UPDATE DB record
	// mode = (U)pdate, (A)dd, (D)elete
	//----------------------------------
	//Specific http://gw.systema.no:8080/syjservicesst/syjsDKT058R_U.do?user=OSCAR&mode=U/A/D&dkse_knr=0&dkse_331=...
	static public String MAINTENANCE_BASE_DKT058R_DML_UPDATE_URL = AppConstants.HTTP_ROOT_SERVLET_JSERVICES + "/syjservicesst/syjsDKT058R_U.do";
		
	
	
}
