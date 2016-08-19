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
	//ALL --> http://gw.systema.no:8080/syjservicesbcore/syjsSYFA28R.do?user=OSCAR&kovavd=333
	static public String MAINTENANCE_MAIN_BASE_SYFA28R_GET_LIST_URL = AppConstants.HTTP_ROOT_SERVLET_JSERVICES + "/syjservicesbcore/syjsSYFA28R.do";
	//ALL --> http://localhost:8080/syjservicesbcore/syjsSYFA63R.do?user=OSCAR&koaavd=2
	static public String MAINTENANCE_MAIN_BASE_SYFA63R_GET_LIST_URL = AppConstants.HTTP_ROOT_SERVLET_JSERVICES + "/syjservicesbcore/syjsSYFA63R.do";
	
	
	//CHILDREN List on SYFA28 --> http://gw.systema.no:8080/syjservicesbcore/syjsSYFA28ChildR.do?user=OSCAR&kopavd=333
	//ONE CHILD on SYFA28 --> http://localhost:8080/syjservicesbcore/syjsSYFA28ChildR.do?user=OSCAR&kopavd=333&koplnr=4
	static public String MAINTENANCE_MAIN_BASE_SYFA28R_GET_CHILDREN_LIST_URL = AppConstants.HTTP_ROOT_SERVLET_JSERVICES + "/syjservicesbcore/syjsSYFA28ChildR.do";
	
	//GET Customer (cundf)
	//ALL --> http://http://gw.systema.no:8080/syjservicesbcore/syjsSYCUNDFR.do?user=OSCAR&kundnr=1&firma=SY
	static public String MAINTENANCE_MAIN_BASE_SYCUNDFR_GET_LIST_URL = AppConstants.HTTP_ROOT_SERVLET_JSERVICES + "/syjservicesbcore/syjsSYCUNDFR.do";
	
	//----------------------------------
	//[1] UPDATE DB record
	// mode = (U)pdate, (A)dd, (D)elete
	//----------------------------------
	//SYFT04R_U
	//ALL --> http://gw.systema.no:8080/syjservicesbcore/syjsSYFA14R_U.do?user=OSCAR&mode=U&<record>attributes...
	static public String MAINTENANCE_MAIN_BASE_SYFA14R_DML_UPDATE_URL = AppConstants.HTTP_ROOT_SERVLET_JSERVICES + "/syjservicesbcore/syjsSYFA14R_U.do";
	//SYFA28R_U
	static public String MAINTENANCE_MAIN_BASE_SYFA28R_DML_UPDATE_URL = AppConstants.HTTP_ROOT_SERVLET_JSERVICES + "/syjservicesbcore/syjsSYFA28R_U.do";
	//SYFA28R_U CHILD (Del-2)
	static public String MAINTENANCE_MAIN_BASE_SYFA28R_DML_UPDATE_CHILD_URL = AppConstants.HTTP_ROOT_SERVLET_JSERVICES + "/syjservicesbcore/syjsSYFA28ChildR_U.do";
	//SYFA63R_U
	static public String MAINTENANCE_MAIN_BASE_SYFA63R_DML_UPDATE_URL = AppConstants.HTTP_ROOT_SERVLET_JSERVICES + "/syjservicesbcore/syjsSYFA63R_U.do";
	
	
	//--------------------------
	//[3] GUI lists drop downs
	//--------------------------
	//static public String MAINTENANCE_MAIN_BASE_DROPDOWN_SYFA14R_GET_TODOPOSTNR_LIST_URL = AppConstants.HTTP_ROOT_SERVLET_JSERVICES + "/syjservicesbcore/syjsSYFA14R_guipostnr.do";
	
	
}
