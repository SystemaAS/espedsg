/**
 * 
 */
package no.systema.skat.skatexport.url.store;
import no.systema.main.util.AppConstants;
/**
 * Static URLs
 * 
 * @author oscardelatorre
 * @date Feb 26, 2014
 * 
 */
public final class SkatExportUrlDataStore {
	
	//----------------------------
	//[1] FETCH ARENDE LIST
	//----------------------------
	static public String SKAT_EXPORT_BASE_TOPICLIST_URL = AppConstants.HTTP_ROOT_CGI + "/sycgip/TDKE000R.pgm";
	//fetch external references
	//http://10.13.1.22/sycgip/tdke004r.pgm?user=A25DEMO
	static public String SKAT_EXPORT_BASE_FETCH_TOPIC_LIST_EXTERNAL_REFERENCES_URL = AppConstants.HTTP_ROOT_CGI + "/sycgip/TDKE004R.pgm";
	
	//----------------------------
	//[2] FETCH A SPECIFIC ARENDE
	//----------------------------
	//TEST static public String XX_EXPORT_BASE_SPECIFIC_TOPIC_URL = "http://gw.systema.no/sycgip/TDKI001R.pgm?user=OSCAR&avd=1&opd=139";
	static public String SKAT_EXPORT_BASE_FETCH_SPECIFIC_TOPIC_URL = AppConstants.HTTP_ROOT_CGI + "/sycgip/TDKE001R.pgm";
	//[2.1 FETCH Fakt.total from Invoices]
	static public String SKAT_EXPORT_BASE_FETCH_SPECIFIC_TOPIC_FAKT_TOTAL_URL = AppConstants.HTTP_ROOT_CGI + "/sycgip/TDKE033R.pgm";
	
	//------------------------------
	//[3] EDIT A SPECIFIC ARENDE
	//(mode=A (Add new), 
	//mode=U (Update existing), 
	//mode=D (Delete existing topic)	
	//mode=C (Copy selected topic)... from Norskexport or fallback to the origin: transportuppdrag
	//mode=S (Send topic)
	//------------------------------		
	static public String SKAT_EXPORT_BASE_UPDATE_SPECIFIC_TOPIC_URL = AppConstants.HTTP_ROOT_CGI + "/sycgip/TDKE002R.pgm";	
	
	//http://gw.systema.no/sycgip/tdke052r.pgm?user=CB&avd=2&opd=7000033&dtm2=201701172100
	static public String SKAT_EXPORT_BASE_UPDATE_BULK_SEND_SPECIFIC_TOPICS_URL = AppConstants.HTTP_ROOT_CGI + "/sycgip/TDKE052R.pgm";	
	
	
	//-----------------------------
	//[5] FETCH ITEM RECORDS (LIST)
	//-----------------------------
	static public String SKAT_EXPORT_BASE_FETCH_TOPIC_ITEMLIST_URL = AppConstants.HTTP_ROOT_CGI + "/sycgip/TDKE010R.pgm";
	
	//[5.1] FETCH SPECIFIC ITEM (for an update)
	static public String SKAT_EXPORT_BASE_FETCH_SPECIFIC_TOPIC_ITEM_URL = AppConstants.HTTP_ROOT_CGI + "/sycgip/TDKE011R.pgm";
	
	//-----------------------------------
	//[6] EDIT A SPECIFIC ITEM RECORD
	//(mode=A (Add new), 
	//mode=U (Update existing), 
	//mode=D (Delete existing topic)
	//-----------------------------------
	static public String SKAT_EXPORT_BASE_UPDATE_SPECIFIC_TOPIC_ITEM_URL = AppConstants.HTTP_ROOT_CGI + "/sycgip/TDKE012R.pgm";
	//http://gw.systema.no/sycgip/TDKE012R.pgm?user=OSCAR&avd=1&opd=80001&lin=2&mode=A
	
	//--------------------------------------
	//[6.0.1] Update AutoControl error line  
	//--------------------------------------
	static public String SKAT_EXPORT_BASE_UPDATE_SPECIFIC_TOPIC_ITEM_AUTOCONTROL_ERROR_URL = AppConstants.HTTP_ROOT_CGI + "/sycgip/TDKE026R.pgm";
	//http://gw.systema.no/sycgip/tdke026r.pgm?user=OSCAR&avd=1&opd=900077&lin=1&dkev_err=X
	
	//--------------------------------------------------------------------
	//[6.0.2] EDIT/SAVE (Statistiskt værdi MÅSTE kalkyleras). Inga avgifter 
	//--------------------------------------------------------------------
	//http://gw.systema.no/sycgip/TDKE017R.pgm?user=OSCAR&dkeh_221=USD&dkeh_221b=6.80&dkeh_222=2000
	static public String SKAT_EXPORT_BASE_AVGIFTS_CALCULATION_URL = AppConstants.HTTP_ROOT_CGI + "/sycgip/TDKE017R.pgm";	
	
	//-----------------------------------
	//[6.1] LIST/EDIT  INVOICE RECORD(s)
	//(mode=A (Add new), 
	//mode=U (Update existing), 
	//mode=D (Delete existing topic)
	//-----------------------------------
	static public String SKAT_EXPORT_BASE_FETCH_TOPIC_INVOICELIST_URL = AppConstants.HTTP_ROOT_CGI + "/sycgip/TDKE030R.pgm";
	//http://gw.systema.no/sycgip/tdke030r.pgm?user=OSCAR&avd=1&opd=52919
	static public String SKAT_EXPORT_BASE_FETCH_SPECIFIC_TOPIC_INVOICE_URL = AppConstants.HTTP_ROOT_CGI + "/sycgip/TDKE031R.pgm";
	//http://gw.systema.no/sycgip/tdke031r.pgm?user=OSCAR&avd=1&opd=52919&fak=SE444197610900
	static public String SKAT_EXPORT_BASE_UPDATE_SPECIFIC_TOPIC_INVOICE_URL = AppConstants.HTTP_ROOT_CGI + "/sycgip/TDKE032R.pgm";	
	//http://gw.systema.no/sycgip/tdke032r.pgm?user=OSCAR&mode=U&avd=1&opd=52919&fak=SE444197610900..................................
	
	//------------------------------------------
	//[6.2] LIST/Get  External INVOICE RECORD(s)
	//------------------------------------------
	static public String SKAT_EXPORT_BASE_FETCH_TOPIC_INVOICELIST_EXTERNAL_URL = AppConstants.HTTP_ROOT_CGI + "/sycgip/TDKE060R.pgm";
	//http://gw.systema.no/sycgip/tdke060r.pgm?user=OSCAR
	static public String SKAT_EXPORT_BASE_FETCH_SPECIFIC_TOPIC_INVOICE_EXTERNAL_URL = AppConstants.HTTP_ROOT_CGI + "/sycgip/TDKE061R.pgm";	
	//http://gw.systema.no/sycgip/tdke061r.pgm?user=OSCAR&reff=6441&unik=4009282
	static public String SKAT_EXPORT_BASE_UPDATE_SPECIFIC_TOPIC_INVOICE_EXTERNAL_URL = AppConstants.HTTP_ROOT_CGI + "/sycgip/TDKE062R.pgm";	
	//http://gw.systema.no/sycgip/tdke062r.pgm?user=OSCAR&avd=1&opd=91127&mode=U&reff=6445&unik=4012087
	
	
	//------------------------------------------
	//[7] LOG and ARCHIVE for a SPECIFIC ARENDE
	//------------------------------------------
	//This section contains external functions such as LOGGING, ARCHIVE	
	static public String SKAT_EXPORT_BASE_LOG_LIST_FOR_SPECIFIC_TOPIC_URL = AppConstants.HTTP_ROOT_CGI + "/sycgip/TDKE015R.pgm";	
	//http://gw.systema.no/sycgip/TDKE015R.pgm?user=OSCAR&avd=1&opd=218&typ=DKI 
	
	static public String SKAT_EXPORT_BASE_LOG_LARGE_TEXT_FOR_SPECIFIC_TOPIC_URL = AppConstants.HTTP_ROOT_CGI + "/sycgip/TDKE016R.pgm";	
	//http://gw.systema.no/sycgip/TDKE016R.pgm?user=OSCAR&fmn=84278
	
	static public String SKAT_EXPORT_BASE_ARCHIVE_FOR_SPECIFIC_TOPIC_URL = AppConstants.HTTP_ROOT_CGI + "/sycgip/TJGE001.pgm";	//this function is actually general for all modules byt
	//http://gw.systema.no/sycgip/TJGE001.pgm?user=JOVO&avd=1&opd=52919
		
	//------------------------------------------
	//[8] PRINT document for a SPECIFIC ARENDE
	//------------------------------------------
	static public String SKAT_EXPORT_BASE_PRINT_FOR_SPECIFIC_TOPIC_URL = AppConstants.HTTP_ROOT_CGI + "/sycgip/TDKE014R.pgm";	//this function is actually general for all modules byt
	//http://gw.systema.no/sycgip/TDKE014R.pgm?user=OSCAR&avd=1&opd=218	

	//----------------------------------------------
	//[9] OMBUD default values for every new record
	//----------------------------------------------
	//http://gw.systema.no/sycgip/TDKE018R.pgm?user=OSCAR&avd=1
	static public String SKAT_EXPORT_BASE_FETCH_OMBUD_DEFAULT_DATA_URL = AppConstants.HTTP_ROOT_CGI + "/sycgip/TDKE018R.pgm";	
	
	//--------------------------------------------------
	//[10] Updates a message type (DNU, HNU) to another
	// Used in Use Case: Begäran om klarering...
	//--------------------------------------------------
	//http://gw.systema.no/sycgip/TSVI019R.pgm?user=OSCAR&avd=1&opd=156&svih_mtyp=HRT 
	//static public String SKAT_EXPORT_BASE_UPDATE_MESSAGETYPE_URL = AppConstants.HTTP_ROOT_CGI + "/sycgip/TSVI019R.pgm";	
		
	//--------------------------------------------------------
	//[11] Bilagda handlingar (item lines AJAX help function)
	//--------------------------------------------------------
	//http://gw.systema.no/sycgip/TSVI020R.pgm?user=CB&avd=1&opd=171  
	//static public String SKAT_EXPORT_BASE_GET_BILAGDA_HANDLIGAR_URL = AppConstants.HTTP_ROOT_CGI + "/sycgip/TSVI020R.pgm";	
		
	
	//-----------------------------
	// Change status (Admin Role)
	//-----------------------------
	static public String SKAT_EXPORT_BASE_UPDATE_STATUS_URL = AppConstants.HTTP_ROOT_CGI + "/sycgip/TDKE051R.pgm";
		
	
}
