/**
 * 
 */
package no.systema.overview.ufortolledeoppdrag.url.store;

import no.systema.main.util.AppConstants;

/**
 * @author oscardelatorre
 * @date Aug 27, 2013
 * 
 */
public class UrlDataStore {
	
	//----------------------------
	//[1] FETCH MAIN TOPIC LIST
	//----------------------------
	//http://gw.systema.no/sycgip/esuft02.pgm?user=OSCAR&OutType=J
	static public String OVERVIEW_UFORTOLLEDE_OPPDRAG_MAINLIST_URL = AppConstants.HTTP_ROOT_CGI + "/sycgip/tjufort.pgm";
	
	//----------------------
	// Archive
	//----------------------
	//http://gw.systema.no/sycgip/TJGE001.pgm?user=JOVO&avd=1&opd=52919
	static public String UOPPDRAG_BASE_ARCHIVE_FOR_SPECIFIC_TOPIC_URL = AppConstants.HTTP_ROOT_CGI + "/sycgip/TJGE001.pgm";	//this function is actually general for all modules
	
	//-----------------------
	// Track and Trace log
	//-----------------------
	//http://gw.systema.no/sycgip/TJGE002.pgm?user=OSCAR&avd=2&opd=20553
	static public String UOPPDRAG_BASE_LOG_FOR_SPECIFIC_TOPIC_URL = AppConstants.HTTP_ROOT_CGI + "/sycgip/TJGE002.pgm";//Logging
	
	//-----------------------------------
	// Render specific topic (read only)
	//------------------------------------
	//http://gw.systema.no/sycgip/TJGE004.pgm?user=OSCAR&avd=2&opd=20553
	static public String UOPPDRAG_BASE_RENDER_SPECIFIC_TOPIC_URL = AppConstants.HTTP_ROOT_CGI + "/sycgip/TJGE004.pgm";//Render Oppdrag
	
	//--------------------------
	// Tvinn-log (EDI-log)
	//--------------------------
	//http://gw.systema.no/sycgip/TJGE005.pgm?user=JOVO&avd=1&opd=155236&typ=SADINO
	//&typ kan FORELØPIG være: SADINO = Sad Import Norge , SADENO = SAD Eksport Norge,  TDSI = TDS Import , TDSE = TDS Eksport  eller BLANK = ALLT     
	static public String UOPPDRAG_BASE_TVINN_LOG_FOR_SPECIFIC_TOPIC_URL = AppConstants.HTTP_ROOT_CGI + "/sycgip/TJGE005.pgm";//Render Oppdrag
	
	//Show Large Text on TVINN
	//http://gw.systema.no/sycgip/TJGE006.pgm?user=JOVO&fmn=55723 
	static public String UOPPDRAG_BASE_TVINN_LOG_LARGE_TEXT_FOR_SPECIFIC_TOPIC_URL = AppConstants.HTTP_ROOT_CGI + "/sycgip/TJGE006.pgm";
	
	
}
