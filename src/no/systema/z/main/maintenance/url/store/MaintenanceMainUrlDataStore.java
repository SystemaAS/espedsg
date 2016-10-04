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
	//ALL --> http://gw.systema.no:8080/syjservicesbcore/syjsSYFA14R.do?user=OSCAR
	//ONE --> http://gw.systema.no:8080/syjservicesbcore/syjsSYFA14R.do?user=OSCAR...
	static public String MAINTENANCE_MAIN_BASE_SYFA14R_GET_LIST_URL = AppConstants.HTTP_ROOT_SERVLET_JSERVICES + "/syjservicesbcore/syjsSYFA14R.do";
	//ALL --> http://localhost:8080/syjservicesbcore/syjsSYFA26R.do?user=OSCAR&teavd=1
	static public String MAINTENANCE_MAIN_BASE_SYFA26R_GET_LIST_URL = AppConstants.HTTP_ROOT_SERVLET_JSERVICES + "/syjservicesbcore/syjsSYFA26R.do";
	//ALL --> http://gw.systema.no:8080/syjservicesbcore/syjsSYFA28R.do?user=OSCAR&kovavd=333
	static public String MAINTENANCE_MAIN_BASE_SYFA28R_GET_LIST_URL = AppConstants.HTTP_ROOT_SERVLET_JSERVICES + "/syjservicesbcore/syjsSYFA28R.do";
	//ALL --> http://localhost:8080/syjservicesbcore/syjsSYFA63R.do?user=OSCAR&koaavd=2
	static public String MAINTENANCE_MAIN_BASE_SYFA63R_GET_LIST_URL = AppConstants.HTTP_ROOT_SERVLET_JSERVICES + "/syjservicesbcore/syjsSYFA63R.do";
	//ALL --> http://localhost:8080/syjservicesbcore/syjsSYFA68R.do?user=OSCAR&kohavd=1
	static public String MAINTENANCE_MAIN_BASE_SYFA68R_GET_LIST_URL = AppConstants.HTTP_ROOT_SERVLET_JSERVICES + "/syjservicesbcore/syjsSYFA68R.do";
	//ALL --> http://gw.systema.no:8080/syjservicesbcore/syjsSYFIRMR.do?user=OSCAR
	static public String MAINTENANCE_MAIN_BASE_SYFIRMR_GET_LIST_URL = AppConstants.HTTP_ROOT_SERVLET_JSERVICES + "/syjservicesbcore/syjsSYFIRMR.do";
	
	//AVD SAD IMPORT ON GENERAL MAINT
	//http://gw.systema.no:8080/syjservicesbcore/syjsSYFTAAAR.do?user=OSCAR
	static public String MAINTENANCE_MAIN_BASE_SYFTAAAR_GET_LIST_URL = AppConstants.HTTP_ROOT_SERVLET_JSERVICES + "/syjservicesbcore/syjsSYFTAAAR.do";
	//AVD SAD EXPORT ON GENERAL MAINT
	//http://gw.systema.no:8080/syjservicesbcore/syjsSYFTAAAER.do?user=OSCAR
	static public String MAINTENANCE_MAIN_BASE_SYFTAAAER_GET_LIST_URL = AppConstants.HTTP_ROOT_SERVLET_JSERVICES + "/syjservicesbcore/syjsSYFTAAAER.do";
	//AVD SAD NCTS IMPORT ON GENERAL MAINT
	//http://gw.systema.no:8080/syjservicesbcore/syjsTR053R.do?user=OSCAR
	static public String MAINTENANCE_MAIN_BASE_TR053R_GET_LIST_URL = AppConstants.HTTP_ROOT_SERVLET_JSERVICES + "/syjservicesbcore/syjsTR053R.do";
	//AVD SAD NCTS EKSPORT ON GENERAL MAINT
	//http://gw.systema.no:8080/syjservicesbcore/syjsTR003R.do?user=OSCAR
	static public String MAINTENANCE_MAIN_BASE_TR003R_GET_LIST_URL = AppConstants.HTTP_ROOT_SERVLET_JSERVICES + "/syjservicesbcore/syjsTR003R.do";
	static public String MAINTENANCE_MAIN_BASE_TR003fvR_GET_LIST_URL = AppConstants.HTTP_ROOT_SERVLET_JSERVICES + "/syjservicesbcore/syjsTR003fvR.do";
	
		
	
	//CHILDREN List on SYFA28 --> http://gw.systema.no:8080/syjservicesbcore/syjsSYFA28ChildR.do?user=OSCAR&kopavd=333
	//ONE CHILD on SYFA28 --> http://localhost:8080/syjservicesbcore/syjsSYFA28ChildR.do?user=OSCAR&kopavd=333&koplnr=4
	static public String MAINTENANCE_MAIN_BASE_SYFA28R_GET_CHILDREN_LIST_URL = AppConstants.HTTP_ROOT_SERVLET_JSERVICES + "/syjservicesbcore/syjsSYFA28ChildR.do";
	
	//GET Customer (cundf)
	//ALL --> http://http://gw.systema.no:8080/syjservicesbcore/syjsSYCUNDFR.do?user=OSCAR&kundnr=1&firma=SY
	static public String MAINTENANCE_MAIN_BASE_SYCUNDFR_GET_LIST_URL = AppConstants.HTTP_ROOT_SERVLET_JSERVICES + "/syjservicesbcore/syjsSYCUNDFR.do";
	
	//GET EDI (edii)
	//ALL --> http://http://gw.systema.no:8080/syjservicesbcore/syjsSYEDIIR.do?user=OSCAR&inid=1&inna=TOLD
	static public String MAINTENANCE_MAIN_BASE_SYEDIIR_GET_LIST_URL = AppConstants.HTTP_ROOT_SERVLET_JSERVICES + "/syjservicesbcore/syjsSYEDIIR.do";
	
	//SAD GENERAL CODES (Language, Country, Tollsted (tullkontor))
	//http://gw.systema.no:8080/syjservicesbcore/syjsTRKODL01R.do?user=OSCAR
	static public String MAINTENANCE_MAIN_BASE_CODES_TRKODL01R_GET_LIST_URL = AppConstants.HTTP_ROOT_SERVLET_JSERVICES + "/syjservicesbcore/syjsTRKODL01R.do";
	
	//----------------------------------
	//[1] UPDATE DB record
	// mode = (U)pdate, (A)dd, (D)elete
	//----------------------------------
	//SYFT04R_U
	//ALL --> http://gw.systema.no:8080/syjservicesbcore/syjsSYFA14R_U.do?user=OSCAR&mode=U&<record>attributes...
	static public String MAINTENANCE_MAIN_BASE_SYFA14R_DML_UPDATE_URL = AppConstants.HTTP_ROOT_SERVLET_JSERVICES + "/syjservicesbcore/syjsSYFA14R_U.do";
	//SYFA26R_U
	static public String MAINTENANCE_MAIN_BASE_SYFA26R_DML_UPDATE_URL = AppConstants.HTTP_ROOT_SERVLET_JSERVICES + "/syjservicesbcore/syjsSYFA26R_U.do";
	
	//SYFA28R_U
	static public String MAINTENANCE_MAIN_BASE_SYFA28R_DML_UPDATE_URL = AppConstants.HTTP_ROOT_SERVLET_JSERVICES + "/syjservicesbcore/syjsSYFA28R_U.do";
	//SYFA28R_U CHILD (Del-2)
	static public String MAINTENANCE_MAIN_BASE_SYFA28R_DML_UPDATE_CHILD_URL = AppConstants.HTTP_ROOT_SERVLET_JSERVICES + "/syjservicesbcore/syjsSYFA28ChildR_U.do";
	//SYFA28DPTAvdR_U /Dupliser (Kopiera)
	//http://localhost:8080/syjservicesbcore/syjsSYFA28DPTAvdR_U.do?user=OSCAR&originalAvd=1&originalLnr=2&fromAvd=333&toAvd=334&mode=U
	static public String MAINTENANCE_MAIN_BASE_SYFA28DPTAvdR_DML_UPDATE_CHILD_URL = AppConstants.HTTP_ROOT_SERVLET_JSERVICES + "/syjservicesbcore/syjsSYFA28DPTAvdR_U.do";
	
	//SYFA63R_U
	static public String MAINTENANCE_MAIN_BASE_SYFA63R_DML_UPDATE_URL = AppConstants.HTTP_ROOT_SERVLET_JSERVICES + "/syjservicesbcore/syjsSYFA63R_U.do";
	//SYFA68R_U
	static public String MAINTENANCE_MAIN_BASE_SYFA68R_DML_UPDATE_URL = AppConstants.HTTP_ROOT_SERVLET_JSERVICES + "/syjservicesbcore/syjsSYFA68R_U.do";
	
	//AVD SAD IMPORT ON GENERAL MAINT
	static public String MAINTENANCE_MAIN_BASE_SYFTAAAR_DML_UPDATE_URL = AppConstants.HTTP_ROOT_SERVLET_JSERVICES + "/syjservicesbcore/syjsSYFTAAAR_U.do";
	//AVD SAD EXPORT ON GENERAL MAINT
	static public String MAINTENANCE_MAIN_BASE_SYFTAAAER_DML_UPDATE_URL = AppConstants.HTTP_ROOT_SERVLET_JSERVICES + "/syjservicesbcore/syjsSYFTAAAER_U.do";
	//AVD SAD NCTS IMPORT ON GENERAL MAINT
	static public String MAINTENANCE_MAIN_BASE_TR053R_DML_UPDATE_URL = AppConstants.HTTP_ROOT_SERVLET_JSERVICES + "/syjservicesbcore/syjsTR053R_U.do";
	//AVD SAD NCTS EKSPORT ON GENERAL MAINT
	static public String MAINTENANCE_MAIN_BASE_TR003R_DML_UPDATE_URL = AppConstants.HTTP_ROOT_SERVLET_JSERVICES + "/syjservicesbcore/syjsTR003R_U.do";
	
	
	//--------------------------
	//[3] GUI lists drop downs
	//--------------------------
	static public String MAINTENANCE_MAIN_BASE_DROPDOWN_SYFT02R_GET_CURRENCY_LIST_URL = AppConstants.HTTP_ROOT_SERVLET_JSERVICES + "/syjservicestn/syjsSYFT02R.do";
	static public String MAINTENANCE_MAIN_BASE_DROPDOWN_SYFA61R_GET_OPPTYPE_LIST_URL = AppConstants.HTTP_ROOT_SERVLET_JSERVICES + "/syjservicesbcore/syjsSYFA61R.do";
	
	
}
