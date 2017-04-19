/**
 * 
 */
package no.systema.skat.skatexport.util;

/**
 * All type of system constants for SKAT in general
 * 
 * @author oscardelatorre
 * @date Feb 26, 2014
 * 
 *
 */
public final class SkatExportConstants {
	/**
	EKSPORT:                                                          
		 101=Svartekstkoder                              
		 102=Angivelsesart                                     
		 103=Ekspeditionssted                                       
		 104=Angivelsestype R1.1                  
		 105=Bestemmelsesland                        
		 106=Leveringsbetingelser            
		 107=Valutakoder                                                           
		 108=Transportmåde                                                         
		 109=Bet.måte transportutgifter                                            
		 110=Emballagekoder                                                        
		 111=Indikator R.S32                                                       
		 112=Procedurekoder R37                                                    
		 113=Certifikattyper                                                       
		 114=VAB-kode R44.3                                                        
		 115=FN-kode R44.4                                                         
		 116=Transportdokumenter R44.5.1                                           
		 117=Udgangstoldsted                                                       
		 118=Summarisk angivelse                                                   
		 119=Eksportartkoder 
		 121=Procedurekoder ECS-YM
		 122=Erklæringskoder 
		 123=T-status
		 124=Alternativ mængde
		 125=Lokationskoder i eEksport
		 126=Angivelsestype R1.2
		  
		 =====================
   		 BORROWED from IMPORT
   		 =====================
   		 008=Landekoder, R15
   		 017=Summariska dok, R40 (item lines)
	 */
	public static final String RESOURCE_MODEL_KEY_CODE_008_COUNTRY_LIST = "countryCodeList";
	public static final String RESOURCE_MODEL_KEY_CODE_017_TRANSPORTDOK_SUMMARISKA_R40_LIST = "transportdocsSummariskadocsCodeList";
	public static final String RESOURCE_MODEL_KEY_CODE_022_SUPP_ENHEDER_R41_1_LIST = "uomKoderR411CodeList"; 
	
	public static final String RESOURCE_MODEL_KEY_CODE_102_ANGIVELSESARTER_LIST = "angivelsesArterCodeList";
	public static final String RESOURCE_MODEL_KEY_CODE_103_TOLDSTED_LIST = "toldstedCodeList";
	public static final String RESOURCE_MODEL_KEY_CODE_104_ANGIVELSESTYPE_LIST = "angivelsesTypeCodeList";
	public static final String RESOURCE_MODEL_KEY_CODE_106_INCOTERMS_LIST = "incotermsCodeList";
	public static final String RESOURCE_MODEL_KEY_CODE_107_CURRENCY_LIST = "currencyCodeList";
	public static final String RESOURCE_MODEL_KEY_CODE_108_TRANSPORTMADE_LIST = "transportmadeCodeList";
	public static final String RESOURCE_MODEL_KEY_CODE_109_BETALNINGSMADE_LIST = "betalningsmadeCodeList";
	public static final String RESOURCE_MODEL_KEY_CODE_110_EMBALLAGE_R31_LIST = "emballageCodeList";
	
	public static final String RESOURCE_MODEL_KEY_CODE_112_PROCEDURE_R37_LIST = "procedureKoderR37CodeList"; //Procedurekoder R37
	public static final String RESOURCE_MODEL_KEY_CODE_113_CERTIFIKAT_R44_2_LIST = "certifikatKoderR44_2CodeList"; //CertifikatKoderR44
	public static final String RESOURCE_MODEL_KEY_CODE_114_VAB_CERTIFIKAT_R44_3_LIST = "vabCertifikatKoderR44_3CodeList"; //VAB-certifikatKoderR44.3
	public static final String RESOURCE_MODEL_KEY_CODE_115_FN_FARLIG_GODS_R44_4_LIST = "fnFarligGodsCodeList"; //FN-KoderR44.4
	public static final String RESOURCE_MODEL_KEY_CODE_116_TRANSPORTDOK_TYPE_R44_5_1_LIST = "transportdocTypeCodeList"; //
	public static final String RESOURCE_MODEL_KEY_CODE_117_UDGANGSTOLDSTED_LIST = "udgangstoldstedCodeList";
	public static final String RESOURCE_MODEL_KEY_CODE_119_EXPORTARTER_LIST = "exportArterCodeList";
	public static final String RESOURCE_MODEL_KEY_CODE_122_ERKLAERINGER_YM_LIST = "erklaeringerCodeList";
	public static final String RESOURCE_MODEL_KEY_CODE_123_T_STATUS_LIST = "tStatusCodeList";
	public static final String RESOURCE_MODEL_KEY_CODE_124_SUPPL_ENHEDER_YM_LIST = "suppUomKoderCodeList";
	public static final String RESOURCE_MODEL_KEY_CODE_126_EU_ANGIVELSESARTER_LIST = "euAngivelsesArterCodeList";
	public static final String RESOURCE_MODEL_KEY_CODE_127_STATUS_KODER_LIST = "statusCodeList";
	public static final String RESOURCE_MODEL_KEY_CODE_ARCHIVE_CODE_LIST = "typeArchiveCodeList";
	
	//external URLs (if applicable)
	public static final String URL_EXTERNAL_TOLDSTEDXXX_CODE = "toldstedXXXCodesURL";
	public static final String URL_EXTERNAL_LANDCODES_SKAT_CODE = "skatLandCodesURL";
	public static final String URL_EXTERNAL_CURRENCYCODES_SKAT_CODE = "skatCurrencyCodesURL";
	public static final String URL_EXTERNAL_UOM_SKAT_CODE = "skatUnitOfMeasureCodesURL";
	public static final String URL_EXTERNAL_KOLLIARTCODES_31_SKAT_CODE = "skatKolliart31CodesURL";
	public static final String URL_EXTERNAL_TRANSPORT_SUMMARISKA_DOKUMENTCODES_40_SKAT_CODE = "skatTransportSummariskaDokument40CodesURL";
	public static final String URL_EXTERNAL_CERTIFIKATCODES_R44_2_SKAT_CODE = "skatCertifikatk44_2CodesURL";
	public static final String URL_EXTERNAL_VAB_CERTIFIKATCODES_R44_3_SKAT_CODE = "skatVabCertifikatk44_3CodesURL";
	public static final String URL_EXTERNAL_STAMDATALISTE_SKAT_CODE = "skatStamdataCodesURL";
	public static final String URL_EXTERNAL_EXPORTARTER_CODE = "exportArterCodesURL";

	   
}
