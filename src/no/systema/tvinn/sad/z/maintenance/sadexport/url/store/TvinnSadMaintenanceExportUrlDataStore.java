/**
 * 
 */
package no.systema.tvinn.sad.z.maintenance.sadexport.url.store;
import no.systema.main.util.AppConstants;
/**
 * 
 * Static URLs
 * @author Fredrik Möller
 * @date Aug 10, 2016
 * 
 * 
 */
public final class TvinnSadMaintenanceExportUrlDataStore {
	//--------------------------------------------
	//[1] FETCH DB Table list or Specific record
	//--------------------------------------------
	
	/**
	 * TVI99D
	 * 
	 * /syjservicestn/syjsTVI99D.do
	 */
	static public String TVINN_SAD_MAINTENANCE_EXPORT_BASE_TVI99D_GET_LIST_URL = AppConstants.HTTP_ROOT_SERVLET_JSERVICES + "/syjservicestn/syjsTVI99D.do";
	
	
	//----------------------------------
	//[1] UPDATE DB record
	// mode = (U)pdate, (A)dd, (D)elete
	//----------------------------------
	
	/**
	 * TVI99D_U
	 * 
	 * /syjservicestn/syjsTVI99D_U.do
	 * 
	 */
	static public String TVINN_SAD_MAINTENANCE_EXPORT_BASE_TVI99D_DML_UPDATE_URL = AppConstants.HTTP_ROOT_SERVLET_JSERVICES + "/syjservicestn/syjsTVI99D_U.do";
	
	
	//--------------------------
	//[3] GUI lists drop downs
	//--------------------------
	static public String TVINN_SAD_MAINTENANCE_IMPORT_BASE_DROPDOWN_SYFT04R_GET_POSTNR_LIST_URL = AppConstants.HTTP_ROOT_SERVLET_JSERVICES + "/syjservicestn/syjsSYFT04R_guipostnr.do";
	
	
}
