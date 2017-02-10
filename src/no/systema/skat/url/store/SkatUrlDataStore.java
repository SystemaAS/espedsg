/**
 * 
 */
package no.systema.skat.url.store;
import no.systema.main.util.AppConstants;

/**
 * Static URLs
 * @author oscardelatorre
 * @date Jan 23, 2014
 * 
 */
public final class SkatUrlDataStore {
	
	
	//----------------------------------
	//[1] FETCH HEADER FUNCTIONS GENERAL
	//----------------------------------
	
	//FETCH CUSTOMER(S)
	static public String SKAT_FETCH_CUSTOMER_URL = AppConstants.HTTP_ROOT_CGI + "/sycgip/TDKG003R.pgm";
	//http://gw.systema.no/sycgip/TDKG003R.pgm?user=OSCAR&knr=6&sonavn=SA
	
	//FETCH UTFARTSTULL KONTOR
	static public String SKAT_FETCH_UTFARTS_TULLKONTOR_URL = AppConstants.HTTP_ROOT_CGI + "/sycgip/TDKG004R.pgm";//?user=OSCAR&sonavn=SVINESUND&kod=SE...";
	// GENERAL CODES - SKAT - SKAT 
	// (008,020,etc)
	static public String SKAT_CODES_URL = AppConstants.HTTP_ROOT_CGI + "/sycgip/TDKG005R.pgm"; 
	//http://gw.systema.no/sycgip/TDKG005R.pgm?user=OSCAR&typ=008
	
	//FETCH VARUKODER-TARIC (ITEM LINES)
	static public String SKAT_FETCH_TARIC_VARUKODER_ITEMS_URL = AppConstants.HTTP_ROOT_CGI + "/sycgip/TDKG006R.pgm";
	//http://gw.systema.no/sycgip/TDKG006R.pgm?user=OSCAR&ie=I&kod=8514 or (ie=I/E). NCTS=(I/E)
	
	
	//CURRENCY RATES
	static public String SKAT_FETCH_CURRENCY_RATE_URL = AppConstants.HTTP_ROOT_CGI + "/sycgip/TDKG007R.pgm"; //?user=OSCAR&kod=SEK...		
	
	// GENERAL CODES - SKAT - SKAT[NCTS] (Since we discovered discrepancies towards TDKG005R.pgm (above).
	// A special function is needed for the NCTS domain
	// (Kollislag (017), Dokumentkod (013), etc)
	/* typ kan ha följande värde:
		012=Språkkod                      
		013=Dokumentkod                  
		014=Tidigare dokument              
		017=Kollityp                      
		031=Deklarationstyp              
		039=Tilläggsupplysning            
		047=Kontrollresultat              
		064=Känslig vara                  
		096=Speciella omständigheter      
		105=Tillgångskod för garanti      
		106=Tullkontor referansenr        
		116=Betalningssätt transportkostnad 
		301=Satus koder NCTS-Import */
	
	static public String SKAT_NCTS_CODES_URL = AppConstants.HTTP_ROOT_CGI + "/sycgip/TDCG005R.pgm"; 
	//http://gw.systema.no/sycgip/TDCG005R.pgm?user=OSCAR&typ=012 
		
	
	//----------------------------------
	//FETCH AVDELNING AND SIGNATURE
	//----------------------------------
    //ie=E	SKAT export
    //ie=I	SKAT import
    //ie=X	NCTS export
    //ie=N	NCTS import
	//ie=A	Transportuppdrag
	//ie=B	Norsk Import (SAD-import)
	//ie=C	Norsk Export (SAD-export)	
	static public String SKAT_FETCH_AVDELNINGAR_URL = AppConstants.HTTP_ROOT_CGI + "/sycgip/TDKG008R.pgm"; //?user=OSCAR&ie=E
	
	//ie=F	SKAT import/export
	//ie=N	SKAT-NCTS import/export	
	static public String SKAT_FETCH_SIGNATURE_URL = AppConstants.HTTP_ROOT_CGI + "/sycgip/TDKG009R.pgm"; //?user=OSCAR&ie=F
	
	//-------------------------
	// Authorization on SKAT
	//-------------------------
	static public String SKAT_GET_AUTHORIZATION_CODE = AppConstants.HTTP_ROOT_CGI + "/sycgip/TDKG010R.pgm"; //user=OSCAR	
		
	
	//-----------------------------
	//Validation routines (general)
	//-----------------------------
	//http://gw.systema.no/sycgip/TSVG016R.pgm?user=OSCAR&ie=I&kod=9404300000&lk=CA 
	//lk = landkod, kod=Varukod 
	//Use case triggering this pgm. TDS Import - Item lines  TODO!!!
	static public String SKAT_CHECK_EXTRA_MANGDENHET = AppConstants.HTTP_ROOT_CGI + "/sycgip/TSVG016R.pgm"; 
	
	
	//-------------------------------
	//Name for a given SIGN (general)
	//-------------------------------
	//http://gw.systema.no/sycgip/TSVG017R.pgm?user=OSCAR&avd=1&sign=OT
	//static public String SKAT_FETCH_SIGNATURE_NAME_URL = AppConstants.HTTP_ROOT_CGI + "/sycgip/TSVG017R.pgm"; 
	
	
}
