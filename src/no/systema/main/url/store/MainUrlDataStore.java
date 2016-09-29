/**
 * 
 */
package no.systema.main.url.store;
import no.systema.main.util.AppConstants;
/**
 * Static URLs
 * @author oscardelatorre
 *
 */
public final class MainUrlDataStore {
	//------------------------------
	//[1] LOGIN - SYSTEMA WEB eSped
	//------------------------------
	static public String SYSTEMA_WEB_LOGIN_URL = AppConstants.HTTP_ROOT_CGI + "/sycgip/symn0J.pgm";
	
	//---------------
	//[2] Notisblock 
	//---------------
	static public String SYSTEMA_NOTIS_BLOCK_FETCH_LIST_URL = AppConstants.HTTP_ROOT_CGI + "/sycgip/TJGE08R.pgm"; 
	static public String SYSTEMA_NOTIS_BLOCK_FETCH_ITEMLINE_URL = AppConstants.HTTP_ROOT_CGI + "/sycgip/TJGE09R.pgm"; 
	static public String SYSTEMA_NOTIS_BLOCK_UPDATE_ITEMLINE_URL = AppConstants.HTTP_ROOT_CGI + "/sycgip/TJGE10R.pgm"; 
		
	//---------------
	//[3] Edi ftp log 
	//---------------
	static public String SYSTEMA_EDI_FTP_LOG_EDI42R_FETCH_LIST_URL = AppConstants.HTTP_ROOT_SERVLET_JSERVICES + "/syjservicesbcore/syjsEDI42R.do";
	static public String SYSTEMA_EDI_FTP_LOG_EDI43R_FETCH_LIST_URL = AppConstants.HTTP_ROOT_SERVLET_JSERVICES + "/syjservicesbcore/syjsEDI43R.do";
}
