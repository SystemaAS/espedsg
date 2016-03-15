/**
 * 
 */
package no.systema.skat.skatimport.util;

/**
 * All type of system constants for SKAT in general
 * 
 * @author oscardelatorre
 * @date Feb 26, 2014
 * 
 *
 */
public final class SkatImportConstants {

	/**
		001=Angivelsesarter                       
		002=Angivelsesmedier                     
		003=Beregningsarter, præferencekoder     
		004=Beregningslinier, arter               
		005=Certifikatkoder R44.2                 
		006=Ekspeditionssted RA.2                 
		007=Emballagekoder R31.4                 
		008=Landekoder R15, R34                   
		009=Oplysningstypekoder R44.9             
		010=Procedurekoder og bevillingskoder     
		011=Procedurekoder R37                   
		012=Præferencedokumentationskoder R44.4   
		013=Præferencekoder R36                   
		014=Præferencekoder, toldordninger, noter 
		015=Statuskoder, toldordninger, noter     
		016=Svartekstkoder                       
		017=Transportdokumentkoder R40           
		018=Transportkoder R25, R26               
		019=VAB, certifikatkoder R44.3, R44.2     
		020=Valutakoder R22       
	*/
	
	public static final String RESOURCE_MODEL_KEY_CODE_001_ANGIVELSESARTER_LIST = "angivelsesArterCodeList";
	public static final String RESOURCE_MODEL_KEY_CODE_005_CERTIFIKAT_R44_2_LIST = "certifikatKoderR44_2CodeList"; //CertifikatKoderR44
	public static final String RESOURCE_MODEL_KEY_CODE_006_TOLDSTED_LIST = "toldstedCodeList"; //Ekspeditionssted
	public static final String RESOURCE_MODEL_KEY_CODE_007_EMBALLAGE_R31_LIST = "emballageCodeList";
	public static final String RESOURCE_MODEL_KEY_CODE_008_COUNTRY_LIST = "countryCodeList";
	public static final String RESOURCE_MODEL_KEY_CODE_009_OPLYSNINGSTYPE_LIST = "oplysningsTypeCodeList"; //Oplysningstypekoder
	public static final String RESOURCE_MODEL_KEY_CODE_011_PROCEDURE_R37_LIST = "procedureKoderR37CodeList"; //Procedurekoder R37
	public static final String RESOURCE_MODEL_KEY_CODE_012_PDOKUMENTATIONSKODER_R44_4_LIST = "pdokumentationsKoderR44_4CodeList"; //PræferenceDokumentationskoder R44.4
	public static final String RESOURCE_MODEL_KEY_CODE_013_PREFERENCE_LIST = "preferenceCodeList"; //Præferencekoder
	public static final String RESOURCE_MODEL_KEY_CODE_015_STATUS_KODER_LIST = "statusCodeList";
	
	public static final String RESOURCE_MODEL_KEY_CODE_017_TRANSPORTDOK_SUMMARISKA_R40_LIST = "transportdocsSummariskadocsCodeList";
	public static final String RESOURCE_MODEL_KEY_CODE_018_TRANSPORTKODER_R25R26_LIST = "transportR25R26CodeList";
	public static final String RESOURCE_MODEL_KEY_CODE_019_VAB_CERTIFIKAT_R44_3_LIST = "vabCertifikatKoderR44_3CodeList"; //VAB-certifikatKoderR44.3
	public static final String RESOURCE_MODEL_KEY_CODE_020_CURRENCY_LIST = "currencyCodeList"; 
	public static final String RESOURCE_MODEL_KEY_CODE_021_SUPP_VAREOPL_R44_6_LIST = "supplerandeVareoplysningsKoderR44_6CodeList"; 
	public static final String RESOURCE_MODEL_KEY_CODE_022_SUPP_ENHEDER_R41_1_LIST = "uomKoderR411CodeList"; 
	public static final String RESOURCE_MODEL_KEY_CODE_109_BETALING_FOR_TRANSPORT_RS29_LIST = "betalningForTransportRS29CodeList"; 
	public static final String RESOURCE_MODEL_KEY_CODE_106_INCOTERMS_LIST = "incotermsCodeList";

	
	//external URLs (if applicable)
	public static final String URL_EXTERNAL_TOLDSTED_CODE = "toldstedCodesURL";
	public static final String URL_EXTERNAL_LANDCODES_SKAT_CODE = "skatLandCodesURL";
	public static final String URL_EXTERNAL_CURRENCYCODES_SKAT_CODE = "skatCurrencyCodesURL";
	public static final String URL_EXTERNAL_UOM_SKAT_CODE = "skatUnitOfMeasureCodesURL";
	public static final String URL_EXTERNAL_TOLD_KONTINGENT_CODE = "skatToldKontingenterCodesURL";
	
	
	public static final String URL_EXTERNAL_KOLLIARTCODES_31_SKAT_CODE = "skatKolliart31CodesURL";
	public static final String URL_EXTERNAL_PREFERENCECODES_36_SKAT_CODE = "skatPreference36CodesURL";
	public static final String URL_EXTERNAL_PROCEDURECODES_37_SKAT_CODE = "skatProcedure37CodesURL";
	public static final String URL_EXTERNAL_OPLYSNINGSTYPECODES_44_9_SKAT_CODE = "skatOplysningstype44_9CodesURL";
	public static final String URL_EXTERNAL_CERTIFIKATCODES_R44_2_SKAT_CODE = "skatCertifikatk44_2CodesURL";
	public static final String URL_EXTERNAL_VAB_CERTIFIKATCODES_R44_3_SKAT_CODE = "skatVabCertifikatk44_3CodesURL";
	public static final String URL_EXTERNAL_PREFERENCE_DOKUMENTATIONCODES_44_4_SKAT_CODE = "skatPreferenceDokumentation44_4CodesURL";
	public static final String URL_EXTERNAL_TRANSPORT_SUMMARISKA_DOKUMENTCODES_40_SKAT_CODE = "skatTransportSummariskaDokument40CodesURL";
	
	
	   
}
