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
	//--------------------------------------------
	//[1] FETCH DB Table list or Specific record
	//--------------------------------------------
	
	//SYFT10R
	//ALL --> http://gw.systema.no:8080/syjservicestn/syjsSYFT10R.do?user=OSCAR
	//ONE --> http://gw.systema.no:8080/syjservicestn/syjsSYFT10R.do?user=OSCAR&klikod=D
	static public String TVINN_SAD_MAINTENANCE_IMPORT_BASE_SYFT10R_GET_LIST_URL = AppConstants.HTTP_ROOT_SERVLET_JSERVICES + "/syjservicestn/syjsSYFT10R.do";
	
	//SYFT18R
	//ALL --> http://gw.systema.no:8080/syjservicestn/syjsSYFT18R.do?user=OSCAR
	//ONE --> http://gw.systema.no:8080/syjservicestn/syjsSYFT18R.do?user=OSCAR&kundnr=1
	static public String TVINN_SAD_MAINTENANCE_IMPORT_BASE_SYFT18R_GET_LIST_URL = AppConstants.HTTP_ROOT_SERVLET_JSERVICES + "/syjservicestn/syjsSYFT18R.do";
	
	//SYFT19R
	//ALL --> http://gw.systema.no:8080/syjservicestn/syjsSYFT19R.do?user=OSCAR
	//ONE --> http://gw.systema.no:8080/syjservicestn/syjsSYFT19R.do?user=OSCAR&klikod=D
	static public String TVINN_SAD_MAINTENANCE_IMPORT_BASE_SYFT19R_GET_LIST_URL = AppConstants.HTTP_ROOT_SERVLET_JSERVICES + "/syjservicestn/syjsSYFT19R.do";
	
	//SAD012R
	//ALL --> http://gw.systema.no:8080/syjservicestn/syjsSAD012R.do?user=OSCAR
	//ONE --> http://gw.systema.no:8080/syjservicestn/syjsSAD012R.do?user=OSCAR&klbkod=D
	static public String TVINN_SAD_MAINTENANCE_IMPORT_BASE_SAD012R_GET_LIST_URL = AppConstants.HTTP_ROOT_SERVLET_JSERVICES + "/syjservicestn/syjsSAD012R.do";
	
	
	
	//----------------------------------
	//[1] UPDATE DB record
	// mode = (U)pdate, (A)dd, (D)elete
	//----------------------------------
	//SYFT10R_U
	//ALL --> http://gw.systema.no:8080/syjservicestn/syjsSYFT10R_U.do?user=OSCAR&mode=U&<record>attributes...
	static public String TVINN_SAD_MAINTENANCE_IMPORT_BASE_SYFT10R_DML_UPDATE_URL = AppConstants.HTTP_ROOT_SERVLET_JSERVICES + "/syjservicestn/syjsSYFT10R_U.do";
	
	//SYFT18R_U
	//ALL --> http://gw.systema.no:8080/syjservicestn/syjsSYFT18R_U.do?user=OSCAR&mode=U&<record>attributes...
	static public String TVINN_SAD_MAINTENANCE_IMPORT_BASE_SYFT18R_DML_UPDATE_URL = AppConstants.HTTP_ROOT_SERVLET_JSERVICES + "/syjservicestn/syjsSYFT18R_U.do";
	
	//SYFT19R_U
	//ALL --> http://gw.systema.no:8080/syjservicestn/syjsSYFT19R_U.do?user=OSCAR&mode=U&<record>attributes...
	static public String TVINN_SAD_MAINTENANCE_IMPORT_BASE_SYFT19R_DML_UPDATE_URL = AppConstants.HTTP_ROOT_SERVLET_JSERVICES + "/syjservicestn/syjsSYFT19R_U.do";
	
	//SAD012R_U
	//ALL --> http://gw.systema.no:8080/syjservicestn/syjsSAD012R_U.do?user=OSCAR&mode=U&<record>attributes...
	static public String TVINN_SAD_MAINTENANCE_IMPORT_BASE_SAD012R_DML_UPDATE_URL = AppConstants.HTTP_ROOT_SERVLET_JSERVICES + "/syjservicestn/syjsSAD012R_U.do";
	
	
}
