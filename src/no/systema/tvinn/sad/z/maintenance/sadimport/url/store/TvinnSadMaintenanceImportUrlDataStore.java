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
	
	//SAD001AR
	//ALL --> http://gw.systema.no:8080/syjservicestn/syjsSAD001AR.do?user=OSCAR&levenr=1
	//ONE --> http://gw.systema.no:8080/syjservicestn/syjsSAD001AR.do?user=OSCAR&levenr=1&varenr=2042100
	static public String TVINN_SAD_MAINTENANCE_IMPORT_BASE_SAD001AR_GET_LIST_URL = AppConstants.HTTP_ROOT_SERVLET_JSERVICES + "/syjservicestn/syjsSAD001AR.do";
	
	//SAD004R
	//ALL --> http://gw.systema.no:8080/syjservicestn/syjsSAD004R.do?user=OSCAR&slknr=1
	//ONE --> http://gw.systema.no:8080/syjservicestn/syjsSAD004R.do?user=OSCAR&slknr=1&slalfa=2042100
	static public String TVINN_SAD_MAINTENANCE_IMPORT_BASE_SAD004R_GET_LIST_URL = AppConstants.HTTP_ROOT_SERVLET_JSERVICES + "/syjservicestn/syjsSAD004R.do";
	
	//SAD012R
	//ALL --> http://gw.systema.no:8080/syjservicestn/syjsSAD012R.do?user=OSCAR
	//ONE --> http://gw.systema.no:8080/syjservicestn/syjsSAD012R.do?user=OSCAR&klbkod=D
	static public String TVINN_SAD_MAINTENANCE_IMPORT_BASE_SAD012R_GET_LIST_URL = AppConstants.HTTP_ROOT_SERVLET_JSERVICES + "/syjservicestn/syjsSAD012R.do";
	
	//SAD010R
	//ALL --> http://gw.systema.no:8080/syjservicestn/syjsSAD010R.do?user=OSCAR
	//ONE --> http://gw.systema.no:8080/syjservicestn/syjsSAD010R.do?user=OSCAR&tatanr=2042100
	static public String TVINN_SAD_MAINTENANCE_IMPORT_BASE_SAD010R_GET_LIST_URL = AppConstants.HTTP_ROOT_SERVLET_JSERVICES + "/syjservicestn/syjsSAD010R.do";
	
	//SAD062R
	//ALL --> http://gw.systema.no:8080/syjservicestn/syjsSAD062R.do?user=OSCAR
	//ONE --> http://gw.systema.no:8080/syjservicestn/syjsSAD062R.do?user=OSCAR&tariff=2042100
	static public String TVINN_SAD_MAINTENANCE_IMPORT_BASE_SAD062R_GET_LIST_URL = AppConstants.HTTP_ROOT_SERVLET_JSERVICES + "/syjservicestn/syjsSAD062R.do";
	
	//SAD999R
	//ALL --> http://gw.systema.no:8080/syjservicestn/syjsSAD999R.do?user=OSCAR
	//ONE --> http://gw.systema.no:8080/syjservicestn/syjsSAD999R.do?user=OSCAR&sdtnrf=98
	static public String TVINN_SAD_MAINTENANCE_IMPORT_BASE_SAD999R_GET_LIST_URL = AppConstants.HTTP_ROOT_SERVLET_JSERVICES + "/syjservicestn/syjsSAD999R.do";
	
	
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
	
	//SAD001AR_U
	//ALL --> http://gw.systema.no:8080/syjservicestn/syjsSAD001AR_U.do?user=OSCAR&mode=U&<record>attributes...
	static public String TVINN_SAD_MAINTENANCE_IMPORT_BASE_SAD001AR_DML_UPDATE_URL = AppConstants.HTTP_ROOT_SERVLET_JSERVICES + "/syjservicestn/syjsSAD001AR_U.do";
	
	//SAD004R_U
	//ALL --> http://gw.systema.no:8080/syjservicestn/syjsSAD004R_U.do?user=OSCAR&mode=U&<record>attributes...
	static public String TVINN_SAD_MAINTENANCE_IMPORT_BASE_SAD004R_DML_UPDATE_URL = AppConstants.HTTP_ROOT_SERVLET_JSERVICES + "/syjservicestn/syjsSAD004R_U.do";
	
	//SAD012R_U
	//ALL --> http://gw.systema.no:8080/syjservicestn/syjsSAD012R_U.do?user=OSCAR&mode=U&<record>attributes...
	static public String TVINN_SAD_MAINTENANCE_IMPORT_BASE_SAD012R_DML_UPDATE_URL = AppConstants.HTTP_ROOT_SERVLET_JSERVICES + "/syjservicestn/syjsSAD012R_U.do";
	
	//SAD010R_U
	//ALL --> http://gw.systema.no:8080/syjservicestn/syjsSAD010R_U.do?user=OSCAR&mode=U&<record>attributes...
	static public String TVINN_SAD_MAINTENANCE_IMPORT_BASE_SAD010R_DML_UPDATE_URL = AppConstants.HTTP_ROOT_SERVLET_JSERVICES + "/syjservicestn/syjsSAD010R_U.do";
	
	//SAD062R_U
	//ALL --> http://gw.systema.no:8080/syjservicestn/syjsSAD062R_U.do?user=OSCAR&mode=U&<record>attributes...
	static public String TVINN_SAD_MAINTENANCE_IMPORT_BASE_SAD062R_DML_UPDATE_URL = AppConstants.HTTP_ROOT_SERVLET_JSERVICES + "/syjservicestn/syjsSAD062R_U.do";
	
	//SAD999R_U
	//ALL --> http://gw.systema.no:8080/syjservicestn/syjsSAD999R_U.do?user=OSCAR&mode=U&<record>attributes...
	static public String TVINN_SAD_MAINTENANCE_IMPORT_BASE_SAD999R_DML_UPDATE_URL = AppConstants.HTTP_ROOT_SERVLET_JSERVICES + "/syjservicestn/syjsSAD999R_U.do";
	
	
}
