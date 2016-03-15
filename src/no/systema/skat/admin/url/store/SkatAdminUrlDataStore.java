/**
 * 
 */
package no.systema.skat.admin.url.store;
import no.systema.main.util.AppConstants;
/**
 * 
 * Static URLs
 * @author oscardelatorre
 * @date Feb 20, 2014
 * 
 * 
 */
public final class SkatAdminUrlDataStore {
	
	//--------------------------------
	//[1] FETCH Transportuppdrag LIST
	//--------------------------------
	static public String SKAT_ADMIN_BASE_TRANSPORT_LIST_URL = AppConstants.HTTP_ROOT_CGI + "/sycgip/TDKG018R.pgm";
	//http://gw.systema.no/sycgip/TDKG018R.pgm?user=OSCAR&avd=1&sign=JOVO&DATUM=20131001 
	
	//----------------------------
	//[1] FETCH Norsk Import LIST
	//----------------------------
	static public String SKAT_ADMIN_BASE_NORSKIMPORT_LIST_URL = AppConstants.HTTP_ROOT_CGI + "/sycgip/TDKG019R.pgm";
	//http://gw.systema.no/sycgip/TDKG019R.pgm?user=OSCAR&avd=1&sign=JOVO&DATUM=20131001
		
	//----------------------------
	//[1] FETCH Norsk Export LIST
	//----------------------------
	static public String SKAT_ADMIN_BASE_NORSKEXPORT_LIST_URL = AppConstants.HTTP_ROOT_CGI + "/sycgip/TDKG020R.pgm"; 
	//http://gw.systema.no/sycgip/TDKG020R.pgm?user=OSCAR&DATUM=20101001
		
	
}
