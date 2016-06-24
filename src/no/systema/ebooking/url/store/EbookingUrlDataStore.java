package no.systema.ebooking.url.store;

import no.systema.main.util.AppConstants;

/**
 * 
 * Static URLs
 * @author oscardelatorre
 * @date Feb 2, 2016
 * 
 * 
 */
public class EbookingUrlDataStore {
	
	//----------------------------
	//[1] FETCH MAIN LOG LIST
	//----------------------------
	static public String EBOOKING_BASE_MAIN_ORDER_LIST_URL = AppConstants.HTTP_ROOT_CGI + "/sycgip/TBOK001R.pgm";
	//http://gw.systema.no/sycgip/TBOK001R.pgm?user=OSCAR
	
	//----------------
	//Child window
	//----------------
	static public String EBOOKING_BASE_CHILDWINDOW_POSTAL_CODES_URL = AppConstants.HTTP_ROOT_CGI + "/sycgip/TJINQSTED.pgm";
	//(FRA)-->http://gw.systema.no/sycgip/TJINQSTED.pgm?user=JOVO&varlk=FRALK&VARKOD=FRA&SOKLK=NO&WSKUNPA=A (A, P eller blank) 
	//(TIL)-->http://gw.systema.no/sycgip/TJINQSTED.pgm?user=JOVO&varlk=TILLK&VARKOD=TIL&SOKLK=NO&
	
	
	
}
