/**
 * 
 */
package no.systema.skat.nctsimport.url.store;
import no.systema.main.util.AppConstants;
/**
 * 
 * Static URLs
 * @author oscardelatorre
 * @date Apr 23, 2014
 * 
 */
public final class SkatNctsImportUrlDataStore {
	
	//(A)ÄRENDE
	//----------------------------
	//[1] FETCH ARENDE LIST
	//----------------------------
	static public String NCTS_IMPORT_BASE_TOPICLIST_URL = AppConstants.HTTP_ROOT_CGI + "/sycgip/TDCI000R.pgm"; 
	//http://gw.systema.no/sycgip/TDCI000R.pgm?user=OSCAR&avd=1&sign=YBC";
	
	//---------------------------------------------------------------
	//[2] FETCH A SPECIFIC ARENDE or Default values for a NEW ARENDE
	//---------------------------------------------------------------
	static public String NCTS_IMPORT_BASE_FETCH_SPECIFIC_TOPIC_URL = AppConstants.HTTP_ROOT_CGI + "/sycgip/TDCI001R.pgm";
	//http://gw.systema.no/sycgip/TDCI001R.pgm?user=OSCAR&avd=1&opd=50013	
	//http://gw.systema.no/sycgip/TDCI001R.pgm?user=OSCAR&avd=1 (for default values with CREATE NEW)
		
	//------------------------------
	//[3] EDIT A SPECIFIC ARENDE
	//(mode=A (Add new), 
	//mode=U (Update existing), 
	//mode=D (Delete existing topic)	
	//mode=C (Copy existing topic)...does not exist in NCTS import
	//mode=S (Send topic)
	//------------------------------		
	static public String NCTS_IMPORT_BASE_UPDATE_SPECIFIC_TOPIC_URL = AppConstants.HTTP_ROOT_CGI + "/sycgip/TDCI002R.pgm";
	//http://gw.systema.no/sycgip/TDCI002R.pgm?user=OSCAR&tiavd=1&titdn=40066&mode=S
	
	//Refresh ARENDE TODO!!!!
	static public String NCTS_IMPORT_BASE_REFRESH_SPECIFIC_TOPIC_URL = AppConstants.HTTP_ROOT_CGI + "/sycgip/TDCI004R.pgm";
	
	
	
	//(B)HÄNDELSER
	//------------------------------------------
	//[5] FETCH ITEM RECORDS (LIST) - HÄNDELSER 
	//------------------------------------------
	static public String NCTS_IMPORT_BASE_FETCH_TOPIC_ITEMLIST_URL = AppConstants.HTTP_ROOT_CGI + "/sycgip/TDCI010R.pgm";
	//http://gw.systema.no/sycgip/TDCI010R.pgm?user=OSCAR&avd=1&opd=40036
	
	//[5.1] FETCH SPECIFIC ITEM (for an update)
	static public String NCTS_IMPORT_BASE_FETCH_SPECIFIC_TOPIC_ITEM_URL = AppConstants.HTTP_ROOT_CGI + "/sycgip/TDCI011R.pgm";
	
	//---------------------------------------------
	//[5.2] EDIT A SPECIFIC ITEM RECORD - HÄNDELSE
	//(mode=A (Add new), 
	//mode=U (Update existing), 
	//mode=D (Delete existing topic)
	//---------------------------------------------
	static public String NCTS_IMPORT_BASE_UPDATE_SPECIFIC_TOPIC_ITEM_URL = AppConstants.HTTP_ROOT_CGI + "/sycgip/TDCI012R.pgm";	
	//http://gw.systema.no/sycgip/TDCI012R.pgm?user=OSCAR&avd=1&opd=50079&lin=3&mode=D	
	
	
	
	//(C)LOSSNING
	//--------------------------------------
	//[4] FETCH Unloading (LOSSNING) header
	//--------------------------------------
	static public String NCTS_IMPORT_BASE_FETCH_SPECIFIC_TOPIC_UNLOADING_URL = AppConstants.HTTP_ROOT_CGI + "/sycgip/TDCI005R.pgm";
	//http://gw.systema.no/sycgip/TDCI005R.pgm?user=OSCAR&avd=1&opd=40030
	
	//----------------------------------------
	//[4.1] UPDATE Unloading (LOSSNING) header
	//----------------------------------------
	static public String NCTS_IMPORT_BASE_UPDATE_SPECIFIC_TOPIC_UNLOADING_URL = AppConstants.HTTP_ROOT_CGI + "/sycgip/TDCI006R.pgm";
	//http://gw.systema.no/sycgip/TDCI006R.pgm?user=OSCAR&tiavd=1&titdn=40030&mode=U...rest of all ni* fields
		
	//-------------------------------------------------------
	//[4.2] FETCH ITEM RECORDS (LIST) - Unloading (LOSSNING) 
	//-------------------------------------------------------
	static public String NCTS_IMPORT_BASE_FETCH_TOPIC_UNLOADING_ITEMLIST_URL = AppConstants.HTTP_ROOT_CGI + "/sycgip/TDCI015R.pgm";	
	//http://gw.systema.no/sycgip/TDCI015R.pgm?user=OSCAR&avd=1&opd=40030
	
	//[4.3] FETCH SPECIFIC ITEM (for an update)
	static public String NCTS_IMPORT_BASE_FETCH_SPECIFIC_TOPIC_UNLOADING_ITEM_URL = AppConstants.HTTP_ROOT_CGI + "/sycgip/TDCI016R.pgm";
	
	//--------------------------------------------
	//[4.4] EDIT A SPECIFIC ITEM RECORD - (LOSSNING)
	//mode=A (Add new), 
	//mode=U (Update existing), 
	//mode=D (Delete existing topic)
	//--------------------------------------------
	static public String NCTS_IMPORT_BASE_UPDATE_TOPIC_UNLOADING_ITEM_URL = AppConstants.HTTP_ROOT_CGI + "/sycgip/TDCI017R.pgm";	
	//http://gw.systema.no/sycgip/TDCI017R.pgm?user=OSCAR&avd=1&opd=40030&lin=3&mode=D
		
	
	//------------------------------------------
	//[7] LOG and ARCHIVE for a SPECIFIC ARENDE
	//------------------------------------------
	//This section contains external functions such as LOGGING, ARCHIVE, etc, for a specific topic within TDS EXPORT	
	
	//NOTE: be aware that this function is the same as in SKAT-NCTS-Export. The only variation is PARAM: typ=6
	static public String NCTS_IMPORT_BASE_LOG_LIST_FOR_SPECIFIC_TOPIC_URL = AppConstants.HTTP_ROOT_CGI + "/sycgip/TDCE015R.pgm";	
	//http://gw.systema.no/sycgip/TNCE015R.pgm?user=OSCAR&avd=1&opd=40026&typ=6
	static public String NCTS_IMPORT_BASE_LOG_LARGE_TEXT_FOR_SPECIFIC_TOPIC_URL = AppConstants.HTTP_ROOT_CGI + "/sycgip/TDCE016R.pgm";	
	//http://gw.systema.no/sycgip/TNCE016R.pgm?user=OSCAR&fmn=85847 
		
	/* Typ kan vara:
		0 = Norsk SAD Import  
		1 = Norsk SAD Eksport
		2 = Norsk NCTS Eksport      
		3 = Norsk NCTS Import      
		4 = All edifact
		5 = Svensk NCTS Export  
		6 = Svensk NCTS Import */	
	
	static public String NCTS_IMPORT_BASE_ARCHIVE_FOR_SPECIFIC_TOPIC_URL = AppConstants.HTTP_ROOT_CGI + "/sycgip/TJGE001.pgm";	 
	//http://gw.systema.no/sycgip/TJGE001.pgm?user=JOVO&avd=1&opd=???
			
	//-----------------------------
	// Change status (Admin Role)
	//-----------------------------
	static public String NCTS_IMPORT_BASE_UPDATE_STATUS_URL = AppConstants.HTTP_ROOT_CGI + "/sycgip/TDCI051R.pgm";
		
	
}
