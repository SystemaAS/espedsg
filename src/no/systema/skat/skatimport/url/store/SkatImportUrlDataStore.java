/**
 * 
 */
package no.systema.skat.skatimport.url.store;
import no.systema.main.util.AppConstants;
/**
 * Static URLs
 * @author oscardelatorre
 * @date Jan 27, 2014
 * 
 */
public final class SkatImportUrlDataStore {
	
	
	//----------------------------
	//[1] FETCH ARENDE LIST
	//----------------------------
	static public String SKAT_IMPORT_BASE_TOPICLIST_URL = AppConstants.HTTP_ROOT_CGI + "/sycgip/TDKI000R.pgm";
	//fetch external references
	//http://10.13.1.22/sycgip/tdke004r.pgm?user=A25DEMO
	static public String SKAT_IMPORT_BASE_FETCH_TOPIC_LIST_EXTERNAL_REFERENCES_URL = AppConstants.HTTP_ROOT_CGI + "/sycgip/TDKI004R.pgm";
	
	//----------------------------
	//[2] FETCH A SPECIFIC ARENDE
	//----------------------------
	//TEST static public String XX_EXPORT_BASE_SPECIFIC_TOPIC_URL = "http://gw.systema.no/sycgip/TDKI001R.pgm?user=OSCAR&avd=1&opd=139";
	static public String SKAT_IMPORT_BASE_FETCH_SPECIFIC_TOPIC_URL = AppConstants.HTTP_ROOT_CGI + "/sycgip/TDKI001R.pgm";
	//[2.1 FETCH Fakt.total from Invoices]
	static public String SKAT_IMPORT_BASE_FETCH_SPECIFIC_TOPIC_FAKT_TOTAL_URL = AppConstants.HTTP_ROOT_CGI + "/sycgip/TDKI033R.pgm";
	
	//------------------------------
	//[3] EDIT A SPECIFIC ARENDE
	//(mode=A (Add new), 
	//mode=U (Update existing), 
	//mode=D (Delete existing topic)	
	//mode=C (Copy selected topic)... from Norskexport or fallback to the origin: transportuppdrag
	//mode=S (Send topic)
	//------------------------------		
	static public String SKAT_IMPORT_BASE_UPDATE_SPECIFIC_TOPIC_URL = AppConstants.HTTP_ROOT_CGI + "/sycgip/TDKI002R.pgm";
	//---------------------------------------
	//[4] RECALCULATION on ITEMS' stat.value
	//---------------------------------------
	static public String SKAT_IMPORT_BASE_RECALCULATE_SPECIFIC_TOPIC_ITEM_STATVALUE_URL = AppConstants.HTTP_ROOT_CGI + "/sycgip/TDKI003R.pgm";

	//-----------------------------
	//[5] FETCH ITEM RECORDS (LIST)
	//-----------------------------
	static public String SKAT_IMPORT_BASE_FETCH_TOPIC_ITEMLIST_URL = AppConstants.HTTP_ROOT_CGI + "/sycgip/TDKI010R.pgm";
	
	//[5.1] FETCH SPECIFIC ITEM (for an update)
	static public String SKAT_IMPORT_BASE_FETCH_SPECIFIC_TOPIC_ITEM_URL = AppConstants.HTTP_ROOT_CGI + "/sycgip/TDKI011R.pgm";
	
	
	//-----------------------------------
	//[6] EDIT A SPECIFIC ITEM RECORD
	//(mode=A (Add new), 
	//mode=U (Update existing), 
	//mode=D (Delete existing topic)
	//-----------------------------------
	static public String SKAT_IMPORT_BASE_UPDATE_SPECIFIC_TOPIC_ITEM_URL = AppConstants.HTTP_ROOT_CGI + "/sycgip/TDKI012R.pgm";
	//http://gw.systema.no/sycgip/TDKI012R.pgm?user=OSCAR&avd=1&opd=80001&lin=2&mode=A
	
	//---------------------------------------------------------------
	//[6.0.1] AutoCopy (optional feature) after a valid UPDATE (ITEMS)
	//---------------------------------------------------------------
	static public String SKAT_IMPORT_BASE_AUTO_COPY_AFTER_UPDATE_SPECIFIC_TOPIC_ITEM_URL = AppConstants.HTTP_ROOT_CGI + "/sycgip/TDKI024R.pgm";	
	
	//--------------------------------------
	//[6.0.2] Update AutoControl error line  
	//--------------------------------------
	static public String SKAT_IMPORT_BASE_UPDATE_SPECIFIC_TOPIC_ITEM_AUTOCONTROL_ERROR_URL = AppConstants.HTTP_ROOT_CGI + "/sycgip/TDKI026R.pgm";
	//http://gw.systema.no/sycgip/tdki026r.pgm?user=OSCAR&avd=1&opd=900077&lin=1&dkiv_err=X
	
	
	//-----------------------------------
	//[6.1] LIST/EDIT  INVOICE RECORD(s)
	//(mode=A (Add new), 
	//mode=U (Update existing), 
	//mode=D (Delete existing topic)
	//-----------------------------------
	static public String SKAT_IMPORT_BASE_FETCH_TOPIC_INVOICELIST_URL = AppConstants.HTTP_ROOT_CGI + "/sycgip/TDKI030R.pgm";
	//http://gw.systema.no/sycgip/tdki030r.pgm?user=OSCAR&avd=1&opd=52919
	static public String SKAT_IMPORT_BASE_FETCH_SPECIFIC_TOPIC_INVOICE_URL = AppConstants.HTTP_ROOT_CGI + "/sycgip/TDKI031R.pgm";
	//http://gw.systema.no/sycgip/tdki031r.pgm?user=OSCAR&avd=1&opd=52919&fak=SE444197610900
	static public String SKAT_IMPORT_BASE_UPDATE_SPECIFIC_TOPIC_INVOICE_URL = AppConstants.HTTP_ROOT_CGI + "/sycgip/TDKI032R.pgm";	
	//http://gw.systema.no/sycgip/tdki032r.pgm?user=OSCAR&mode=U&avd=1&opd=52919&fak=SE444197610900..................................
	
	//------------------------------------------
	//[6.2] LIST/Get  External INVOICE RECORD(s)
	//------------------------------------------
	static public String SKAT_IMPORT_BASE_FETCH_TOPIC_INVOICELIST_EXTERNAL_URL = AppConstants.HTTP_ROOT_CGI + "/sycgip/TDKI060R.pgm";
	//http://gw.systema.no/sycgip/tdki060r.pgm?user=OSCAR
	static public String SKAT_IMPORT_BASE_FETCH_SPECIFIC_TOPIC_INVOICE_EXTERNAL_URL = AppConstants.HTTP_ROOT_CGI + "/sycgip/TDKI061R.pgm";	
	//http://gw.systema.no/sycgip/tdki061r.pgm?user=OSCAR&reff=6441&unik=4009282
	static public String SKAT_IMPORT_BASE_UPDATE_SPECIFIC_TOPIC_INVOICE_EXTERNAL_URL = AppConstants.HTTP_ROOT_CGI + "/sycgip/TDKI062R.pgm";	
	//http://gw.systema.no/sycgip/tdki062r.pgm?user=OSCAR&avd=1&opd=91127&mode=U&reff=6445&unik=4012087
	
	
	//------------------------------------------
	//[7] LOG and ARCHIVE for a SPECIFIC ARENDE
	//------------------------------------------
	//This section contains external functions such as LOGGING, ARCHIVE	
	static public String SKAT_IMPORT_BASE_LOG_LIST_FOR_SPECIFIC_TOPIC_URL = AppConstants.HTTP_ROOT_CGI + "/sycgip/TDKI015R.pgm";	
	//http://gw.systema.no/sycgip/TDKI015R.pgm?user=OSCAR&avd=1&opd=218&typ=DKI 
	
	static public String SKAT_IMPORT_BASE_LOG_LARGE_TEXT_FOR_SPECIFIC_TOPIC_URL = AppConstants.HTTP_ROOT_CGI + "/sycgip/TDKI016R.pgm";	
	//http://gw.systema.no/sycgip/TDKI016R.pgm?user=OSCAR&fmn=84278
	
	static public String SKAT_IMPORT_BASE_ARCHIVE_FOR_SPECIFIC_TOPIC_URL = AppConstants.HTTP_ROOT_CGI + "/sycgip/TJGE001.pgm";	//this function is actually general for all modules byt
	//http://gw.systema.no/sycgip/TJGE001.pgm?user=JOVO&avd=1&opd=52919
		
	//------------------------------------------
	//[8] PRINT document for a SPECIFIC ARENDE
	//------------------------------------------
	static public String SKAT_IMPORT_BASE_PRINT_FOR_SPECIFIC_TOPIC_URL = AppConstants.HTTP_ROOT_CGI + "/sycgip/TDKI014R.pgm";	//this function is actually general for all modules byt
	//http://gw.systema.no/sycgip/TDKI014R.pgm?user=OSCAR&avd=1&opd=218	

	//----------------------------------------------
	//[9] OMBUD default values for every new record
	//----------------------------------------------
	//http://gw.systema.no/sycgip/TSVI018R.pgm?user=OSCAR&avd=1
	static public String SKAT_IMPORT_BASE_FETCH_OMBUD_DEFAULT_DATA_URL = AppConstants.HTTP_ROOT_CGI + "/sycgip/TDKI018R.pgm";	
	
	//---------------------------------
	// Tullværdideklaration - AS400
	//---------------------------------
	//Print
	//http://gw.systema.no/sycgip/TDKI021R.pgm?user=OSCAR&avd=1&opd=600008
	static public String SKAT_IMPORT_BASE_PRINT_FOR_TOLDVAERDI_DEKLARATION_URL = AppConstants.HTTP_ROOT_CGI + "/sycgip/TDKI021R.pgm";
	//Calculation
	//http://gw.systema.no/sycgip/TDKI022R.pgm?user=OSCAR&dkiv_42=325&dkiv_t11b=25&dkih_221b=6,5000&dkiv_t17c=355  
	static public String SKAT_IMPORT_BASE_GET_TOLDVAERDI_URL = AppConstants.HTTP_ROOT_CGI + "/sycgip/TDKI022R.pgm";
	//Calculation on EU and non-EU freight
	static public String SKAT_IMPORT_BASE_GET_TOLDVAERDI_INTERNAL_EXTERNAL_TRANSPORT_URL = AppConstants.HTTP_ROOT_CGI + "/sycgip/TDKI023R.pgm";
		
	//-------------------------------------------
	// Certifikatkoder on item lines Rubrik 44.2
	//-------------------------------------------
	static public String SKAT_IMPORT_BASE_GET_CERTIFICATE_CODES_ON_442_URL = AppConstants.HTTP_ROOT_CGI + "/sycgip/TDKI025R.pgm";
		
	
	//-----------------------------
	// Change status (Admin Role)
	//-----------------------------
	static public String SKAT_IMPORT_BASE_UPDATE_STATUS_URL = AppConstants.HTTP_ROOT_CGI + "/sycgip/TDKI051R.pgm";
	
	
	//
	//--------------------------------------------------
	//[10] Updates a message type (DNU, HNU) to another
	// Used in Use Case: Begäran om klarering...
	//--------------------------------------------------
	//http://gw.systema.no/sycgip/TSVI019R.pgm?user=OSCAR&avd=1&opd=156&svih_mtyp=HRT 
	//static public String SKAT_IMPORT_BASE_UPDATE_MESSAGETYPE_URL = AppConstants.HTTP_ROOT_CGI + "/sycgip/TSVI019R.pgm";	
		
	//--------------------------------------------------------
	//[11] Bilagda handlingar (item lines AJAX help function)
	//--------------------------------------------------------
	//http://gw.systema.no/sycgip/TSVI020R.pgm?user=CB&avd=1&opd=171  
	//static public String SKAT_IMPORT_BASE_GET_BILAGDA_HANDLIGAR_URL = AppConstants.HTTP_ROOT_CGI + "/sycgip/TSVI020R.pgm";	
		
		
	
}
