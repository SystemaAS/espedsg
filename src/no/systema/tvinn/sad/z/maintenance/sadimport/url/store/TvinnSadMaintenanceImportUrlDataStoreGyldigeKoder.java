/**
 * 
 */
package no.systema.tvinn.sad.z.maintenance.sadimport.url.store;
import no.systema.main.util.AppConstants;
/**
 * 
 * Static URLs
 * @author oscardelatorre
 * @date Mar 20, 2016
 * 
 * 
 */
public final class TvinnSadMaintenanceImportUrlDataStoreGyldigeKoder {
	//--------------------------------------------
	//[1] FETCH DB Table list or Specific record
	//--------------------------------------------
	
	//SYFT10R
	//ALL --> http://gw.systema.no:8080/syjservicestn/syjsSAD002_KODTS8R.do?user=OSCAR
	//ONE --> http://gw.systema.no:8080/syjservicestn/syjsSAD002_KODTS8R.do?user=OSCAR&ks8avg=BE
	static public String TVINN_SAD_MAINTENANCE_IMPORT_BASE_SAD002_KODTS8R_GET_LIST_URL = AppConstants.HTTP_ROOT_SERVLET_JSERVICES + "/syjservicestn/syjsSAD002_KODTS8R.do";
	
	//----------------------------------
	//[1] UPDATE DB record
	// mode = (U)pdate, (A)dd, (D)elete
	//----------------------------------
	//SYFT10R_U
	//ALL --> http://gw.systema.no:8080/syjservicestn/syjsSAD002_KODTS8R_U.do?user=OSCAR&mode=U&<record>attributes...
	static public String TVINN_SAD_MAINTENANCE_IMPORT_BASE_SAD002_KODTS8R_DML_UPDATE_URL = AppConstants.HTTP_ROOT_SERVLET_JSERVICES + "/syjservicestn/syjsSAD002_KODTS8R_U.do";
	
	
}
