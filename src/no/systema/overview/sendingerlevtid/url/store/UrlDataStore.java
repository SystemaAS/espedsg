/**
 * 
 */
package no.systema.overview.sendingerlevtid.url.store;

import no.systema.main.util.AppConstants;

/**
 * @author oscardelatorre
 * @date Oct 4, 2013
 * 
 */
public class UrlDataStore {
	
	//----------------------------
	//[1] FETCH MAIN TOPIC LIST
	//----------------------------
	//http://gw.systema.no/sycgip/TJKVAL.pgm?user=KADMONSG
	//http://gw.systema.no/sycgip/TJKVAL.pgm?user=KADMONSG&dato=20130926&datot=20130930&avd=11&maxSN=5000&maxRC=5000
	static public String OVERVIEW_SENDINGER_LEVTID_MAINLIST_URL = AppConstants.HTTP_ROOT_CGI + "/sycgip/TJKVAL.pgm";
	
	//FILTER!!!
	//http://gw.systema.no/sycgip/TJKVAL.pgm?
	//user=OSCAR&dato=20130926&datot=20131030&agentg=95700&ied=D&iott=D&tt1=POD&tt2=058&tt3=011&tt4=012&tt5=070&maxSN=99999&maxRC=99999
	
	//Search transportör
	static public String OVERVIEW_SENDINGER_LEVTID_FETCH_CARRIER_URL = AppConstants.HTTP_ROOT_CGI + "/sycgip/TJGE003.pgm";
	
	//Search customer information (general)
	static public String OVERVIEW_CUSTOMER_FETCH_INFO_URL = AppConstants.HTTP_ROOT_CGI + "/sycgip/TJGE007.pgm";
	//http://gw.systema.no/sycgip/TJGE007.pgm?user=OSCAR&kundn=1 
	
}
