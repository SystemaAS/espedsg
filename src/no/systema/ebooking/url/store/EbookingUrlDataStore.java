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
	//http://gw.systema.no/sycgip/TBOK001R.pgm?user=OSCAR
	static public String EBOOKING_BASE_MAIN_ORDER_LIST_URL = AppConstants.HTTP_ROOT_CGI + "/sycgip/TBOK001R.pgm";
	
	//http://gw.systema.no/sycgip/TBOK002R.pgm?user=OSCAR&mode=G&HEUNIK=&HEREFF=&newavd=80&newmodul=H&newmodul=E&newlandkode=DK&newsidesk=S&newtext=
	//mode=G Get med &HEUNIK / &HEREFF=blank   betyr å hente defaulter   MEN  &HEUNIK / &HEREFF ulik blank = hente en konkret sak (for visning/edit mm..)
	static public String EBOOKING_BASE_FETCH_SPECIFIC_ORDER_URL = AppConstants.HTTP_ROOT_CGI + "/sycgip/TBOK002R.pgm";
	
	
	//--------------------
	//[3] EDIT Order
	//mode=A  = Add
	//mode=U  = Update
	//mode=D  = Delete
	//OG et tillegg ...:
	//&mode=G  = Get    
	//(og G=Get med &HEUNIK / &HEREFF=blank   betyr å hente defaulter   MEN  &HEUNIK / &HEREFF ulik blank = hente en konkret sak (for visning/edit mm..)
	//----------------------
	//http://gw.systema.no/sycgip/TBOK002R.pgm?user=OSCAR&mode=G&HEUNIK=&HEREFF=&newavd=80&newmodul=H&newmodul=E&newlandkode=DK&newsidesk=S&newtext=
	static public String EBOOKING_BASE_UPDATE_SPECIFIC_ORDER_URL = AppConstants.HTTP_ROOT_CGI + "/sycgip/TBOK002R.pgm";
	

	//----------------
	//Child window
	//----------------
	//(FRA)-->http://gw.systema.no/sycgip/TJINQSTED.pgm?user=JOVO&varlk=FRALK&VARKOD=FRA&SOKLK=NO&WSKUNPA=A (A, P eller blank) 
	//(TIL)-->http://gw.systema.no/sycgip/TJINQSTED.pgm?user=JOVO&varlk=TILLK&VARKOD=TIL&SOKLK=NO&
	static public String EBOOKING_BASE_CHILDWINDOW_POSTAL_CODES_URL = AppConstants.HTTP_ROOT_CGI + "/sycgip/TJINQSTED.pgm";
	
	
	
}
