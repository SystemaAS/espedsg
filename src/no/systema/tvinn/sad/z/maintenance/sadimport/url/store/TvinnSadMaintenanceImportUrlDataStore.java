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
public final class TvinnSadMaintenanceImportUrlDataStore {
	//----------------------------
	//[1] FETCH DB Table list
	//----------------------------
	//ALL --> http://gw.systema.no:8080/syjservicestn/syjsSYFT19R.do?user=OSCAR
	//ONE --> http://gw.systema.no:8080/syjservicestn/syjsSYFT19R.do?user=OSCAR&klikod=D
	static public String TVINN_SAD_MAINTENANCE_IMPORT_BASE_SYFT19R_GET_LIST_URL = AppConstants.HTTP_ROOT_SERVLET_JSERVICES + "/syjservicestn/syjsSYFT19R.do";
	
}
