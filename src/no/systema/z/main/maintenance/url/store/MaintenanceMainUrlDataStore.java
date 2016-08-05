/**
 * 
 */
package no.systema.z.main.maintenance.url.store;
import no.systema.main.util.AppConstants;
/**
 * 
 * Static URLs
 * @author oscardelatorre
 * @date Aug 3, 2016
 * 
 * 
 */
public final class MaintenanceMainUrlDataStore {
	//--------------------------------------------
	//[1] FETCH DB Table list or Specific record
	//--------------------------------------------
	//SYFA14R
	//ALL --> http://gw.systema.no:8080/syjservicesbcore/syjsSYFA14R.do?user=OSCAR
	//ONE --> http://gw.systema.no:8080/syjservicesbcore/syjsSYFA14R.do?user=OSCAR...
	static public String MAINTENANCE_MAIN_BASE_SYFA14R_GET_LIST_URL = AppConstants.HTTP_ROOT_SERVLET_JSERVICES + "/syjservicesbcore/syjsSYFA14R.do";
	//ALL --> http://gw.systema.no:8080/syjservicesbcore/syjsSYFIRMR.do?user=OSCAR
	static public String MAINTENANCE_MAIN_BASE_SYFIRMR_GET_LIST_URL = AppConstants.HTTP_ROOT_SERVLET_JSERVICES + "/syjservicesbcore/syjsSYFIRMR.do";
	
	//----------------------------------
	//[1] UPDATE DB record
	// mode = (U)pdate, (A)dd, (D)elete
	//----------------------------------
	//SYFT04R_U
	//ALL --> http://gw.systema.no:8080/syjservicesbcore/syjsSYFA14R_U.do?user=OSCAR&mode=U&<record>attributes...
	static public String MAINTENANCE_MAIN_BASE_SYFA14R_DML_UPDATE_URL = AppConstants.HTTP_ROOT_SERVLET_JSERVICES + "/syjservicesbcore/syjsSYFA14R_U.do";
	
	
	//--------------------------
	//[3] GUI lists drop downs
	//--------------------------
	//static public String MAINTENANCE_MAIN_BASE_DROPDOWN_SYFA14R_GET_TODOPOSTNR_LIST_URL = AppConstants.HTTP_ROOT_SERVLET_JSERVICES + "/syjservicesbcore/syjsSYFA14R_guipostnr.do";
	
	
}
