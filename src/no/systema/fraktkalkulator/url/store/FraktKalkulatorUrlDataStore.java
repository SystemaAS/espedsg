/**
 * 
 */
package no.systema.fraktkalkulator.url.store;
import no.systema.main.util.AppConstants;

/**
 * 
 * Static URLs
 * @author oscardelatorre
 * @date Feb 21, 2015
 * 
 * 
 */
public final class FraktKalkulatorUrlDataStore {
	
	
	//----------------------------------
	//[1] FETCH HEADER FUNCTIONS GENERAL
	//----------------------------------
	
	//FETCH CUSTOMER(S)
	static public String FRAKTKALKULATOR_FETCH_CUSTOMER_URL = AppConstants.HTTP_ROOT_CGI + "/sycgip/TNOG003R.pgm";
	//http://gw.systema.no/sycgip/TNOG003R.pgm?user=OSCAR&knr=6&sonavn=SA

	//FETCH POSTAL CODES (FRA)
	static public String FRAKTKALKULATOR_FETCH_POSTAL_CODES_URL = AppConstants.HTTP_ROOT_CGI + "/sycgip/TJINQSTED.pgm";
	//(FRA)-->http://gw.systema.no/sycgip/TJINQSTED.pgm?user=JOVO&varlk=FRALK&VARKOD=FRA&SOKLK=NO&WSKUNPA=A (A, P eller blank) 
	//(TIL)-->http://gw.systema.no/sycgip/TJINQSTED.pgm?user=JOVO&varlk=TILLK&VARKOD=TIL&SOKLK=NO& 
		
	
	//FETCH DEFAULT USER DATA
	static public String FRAKTKALKULATOR_FETCH_USER_DATA_URL = AppConstants.HTTP_ROOT_CGI + "/sycgip/TJSYFRA.pgm";
	//http://gw.systema.no/sycgip/TJSYFRA.pgm?user=OSCAR 
	
	//FETCH RESULT DATA
	static public String FRAKTKALKULATOR_FETCH_RESULT_DATA_URL = AppConstants.HTTP_ROOT_CGI + "/sycgip/TJSYFRA1.pgm";
	//http://gw.systema.no/sycgip/TJSYFRA1.pgm?user=JOVO&fra=1000&til=1000 
		
	
	//FETCH PROD DATA (drop down)
	static public String FRAKTKALKULATOR_FETCH_DROPDOWN_PRODUCT_DATA_URL = AppConstants.HTTP_ROOT_CGI + "/sycgip/TJSYFRAP.pgm";
	//http://gw.systema.no/sycgip/TJSYFRAP.pgm?user=OSCAR  
			
	//FETCH LEV.VILLKOR DATA (drop down)
	static public String FRAKTKALKULATOR_FETCH_DROPDOWN_INCOTERMS_DATA_URL = AppConstants.HTTP_ROOT_CGI + "/sycgip/TJSYFRAL.pgm";
	//http://gw.systema.no/sycgip/TJSYFRAL.pgm?user=OSCAR   
		
	//FETCH TJEN.TYPE DATA (drop down)
	static public String FRAKTKALKULATOR_FETCH_DROPDOWN_TJENESTE_TYPE_DATA_URL = AppConstants.HTTP_ROOT_CGI + "/sycgip/TJSYFRAT.pgm";
	//http://gw.systema.no/sycgip/TJSYFRAT.pgm?user=OSCAR   

	//FETCH OPPD.TYPE DATA (drop down)
	static public String FRAKTKALKULATOR_FETCH_DROPDOWN_OPPDRAG_TYPE_DATA_URL = AppConstants.HTTP_ROOT_CGI + "/sycgip/TJSYFRAO.pgm";
	//http://gw.systema.no/sycgip/TJSYFRAO.pgm?user=OSCAR   
				
	
}
