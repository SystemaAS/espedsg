/**
 * 
 */
package no.systema.skat.nctsexport.util.manager;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;

import no.systema.main.model.SystemaWebUser;
import no.systema.main.service.UrlCgiProxyService;
import no.systema.skat.model.external.url.UrlISOLanguageObject;
import no.systema.skat.model.external.url.UrlSkatCountryObject;
import no.systema.skat.model.external.url.UrlSkatCurrencyObject;

import no.systema.skat.model.jsonjackson.codes.JsonSkatNctsCodeContainer;
import no.systema.skat.model.jsonjackson.codes.JsonSkatNctsCodeRecord;
import no.systema.skat.service.html.dropdown.SkatDropDownListPopulationService;
import no.systema.skat.url.store.SkatUrlDataStore;
import no.systema.skat.util.SkatConstants;
import no.systema.skat.nctsexport.util.SkatNctsExportConstants;

/**
 * The class handles general gui drop downs aspect population
 *
 * This Manager is not instantiated by the Spring Container at start up. 
 * Instead, it is instantiated by a controller when needed.
 * 
 * 
 *  
 * 
 * @author oscardelatorre
 * @date Apr 16, 2014
 * 
 */
public class CodeDropDownMgr {
	private static final Logger logger = Logger.getLogger(CodeDropDownMgr.class.getName());
	/*
		012= KOD_SPRAK
		013= KOD_DOK
		014= KOD_TIDIGARE_DOK
		017= KOD_KOLLI_TYP
		031= KOD_DEKL_TYP
		039= KOD_TILLAGSUPP
		047= KOD_KONTROLLRESULTAT
		064= KOD_KANSLIGVARA
		096= KOD_SPEC_OMST
		105= KOD_TILLGANGASKOD_GARANTI
		106= KOD_TULLKONTOR_REF
		108= KOD_TRANSPORTMÅDE
		116= KOD_BETALNINGSSATT_TRANSPORTKOSTNAD
		302= KOD_STATUS_KODER_NCTS_EXPORT
		
		BORROWED FROM SKAT EXPORT/IMPORT
		008= Country code
		107= Currency code
		
		
	*/
	public static final String CODE_008_COUNTRY = "008";
	public static final String CODE_107_CURRENCY = "107";
	
	public static final String CODE_012_SPRAK = "012";
	public static final String CODE_013_DOKTYPE = "013";
	public static final String CODE_014_TIDIGARE_DOK = "104";
	public static final String CODE_017_KOLLI = "017";
	public static final String CODE_031_DEKLTYPE = "031";
	public static final String CODE_039_TILLAGSUPP = "039";
	public static final String CODE_047_KONTROLL_RESULTAT = "047";
	public static final String CODE_064_KANSLIGVARA = "064";
	public static final String CODE_096_SPEC_OMST = "096";
	public static final String CODE_105_TILLGANGSKOD_GARANTI = "105";
	public static final String CODE_106_TULLKONTOR_REF = "106";
	public static final String CODE_108_TRANSPORTMADE_KODER = "108";
	public static final String CODE_116_BETALNING_TRANSPORT = "116";
	public static final String CODE_302_STATUS_KODER = "302";
	
	
	
	/**
	 * 
	 * @param urlCgiProxyService
	 * @param skatDropDownListPopulationService
	 * @param model
	 * @param appUser
	 * @param paramTYP
	 * @param paramKODE2
	 * @param paramKODE3
	 */
	public void populateCodesHtmlDropDownsFromJsonString(UrlCgiProxyService urlCgiProxyService, SkatDropDownListPopulationService skatDropDownListPopulationService,
		Map model, SystemaWebUser appUser, String paramTYP, String paramKODE2, String paramKODE3){
		//fill in html lists here
		try{
			//default url store
			String CODES_URL = SkatUrlDataStore.SKAT_NCTS_CODES_URL;
			
			//Exception for CODE_URL
			if(CodeDropDownMgr.CODE_008_COUNTRY.equalsIgnoreCase(paramTYP) || CodeDropDownMgr.CODE_107_CURRENCY.equalsIgnoreCase(paramTYP) ){
				CODES_URL = SkatUrlDataStore.SKAT_CODES_URL;
			}
			
			
			StringBuffer urlRequestParamsKeys = new StringBuffer();
			urlRequestParamsKeys.append("user=" + appUser.getUser());
			urlRequestParamsKeys.append(SkatConstants.URL_CHAR_DELIMETER_FOR_PARAMS_WITH_HTML_REQUEST + "typ=" + paramTYP);
			if(paramKODE2 !=null){
				urlRequestParamsKeys.append(SkatConstants.URL_CHAR_DELIMETER_FOR_PARAMS_WITH_HTML_REQUEST + "kode2=" + paramKODE2);
			}
			if(paramKODE3 !=null){
				urlRequestParamsKeys.append(SkatConstants.URL_CHAR_DELIMETER_FOR_PARAMS_WITH_HTML_REQUEST + "kode3=" + paramKODE3);
			}
			//Now build the payload and send to the back end via the drop down service
			String utfPayload = urlCgiProxyService.getJsonContent(CODES_URL, urlRequestParamsKeys.toString());
			/*
			logger.info("CODES PARAMS:" + urlRequestParamsKeys.toString());
			logger.info(utfPayload);
			*/
			
			JsonSkatNctsCodeContainer codeContainer = skatDropDownListPopulationService.getNctsCodeContainer(utfPayload);
			List<JsonSkatNctsCodeRecord> list = new ArrayList();
			for(JsonSkatNctsCodeRecord codeRecord: codeContainer.getKodlista()){
				//default
				list.add(codeRecord);
				 
			}
			if(CodeDropDownMgr.CODE_008_COUNTRY.equalsIgnoreCase(paramTYP)){
				model.put(SkatNctsExportConstants.RESOURCE_MODEL_KEY_CODE_008_COUNTRY_LIST,list);
				model.put(SkatNctsExportConstants.URL_EXTERNAL_LANDCODES_SKAT_CODE, new UrlSkatCountryObject() );
			
			}else if(CodeDropDownMgr.CODE_107_CURRENCY.equalsIgnoreCase(paramTYP)){
				model.put(SkatNctsExportConstants.RESOURCE_MODEL_KEY_CODE_107_CURRENCY_LIST,list);
				model.put(SkatNctsExportConstants.URL_EXTERNAL_CURRENCYCODES_SKAT_CODE, new UrlSkatCurrencyObject() );
			
			}else if(CodeDropDownMgr.CODE_012_SPRAK.equalsIgnoreCase(paramTYP)){
				model.put(SkatNctsExportConstants.RESOURCE_MODEL_KEY_CODE_NCTSEX_012_SPRAK_LIST, list);
				model.put(SkatNctsExportConstants.URL_EXTERNAL_LANGUAGECODES_SKAT_CODE, new UrlISOLanguageObject() );
			
			}else if(CodeDropDownMgr.CODE_013_DOKTYPE.equalsIgnoreCase(paramTYP)){
				model.put(SkatNctsExportConstants.RESOURCE_MODEL_KEY_CODE_NCTSEX_013_DOKTYPE_LIST, list);
				
			}else if(CodeDropDownMgr.CODE_014_TIDIGARE_DOK.equalsIgnoreCase(paramTYP)){
				model.put(SkatNctsExportConstants.RESOURCE_MODEL_KEY_CODE_NCTSEX_014_TIDIGAREDOKS_LIST, list);
				
			}else if(CodeDropDownMgr.CODE_017_KOLLI.equalsIgnoreCase(paramTYP)){
				model.put(SkatNctsExportConstants.RESOURCE_MODEL_KEY_CODE_NCTSEX_017_KOLLI_LIST, list);
				
			}else if(CodeDropDownMgr.CODE_031_DEKLTYPE.equalsIgnoreCase(paramTYP)){
				model.put(SkatNctsExportConstants.RESOURCE_MODEL_KEY_CODE_NCTSEX_031_DEKLTYPE_LIST, list);
				
			}else if(CodeDropDownMgr.CODE_039_TILLAGSUPP.equalsIgnoreCase(paramTYP)){
				model.put(SkatNctsExportConstants.RESOURCE_MODEL_KEY_CODE_NCTSEX_039_TILLAGSUPP_LIST, list);
				
			}else if(CodeDropDownMgr.CODE_047_KONTROLL_RESULTAT.equalsIgnoreCase(paramTYP)){
				model.put(SkatNctsExportConstants.RESOURCE_MODEL_KEY_CODE_NCTSEX_047_KONTROLL_RESULTAT_LIST, list);
				
			}else if(CodeDropDownMgr.CODE_064_KANSLIGVARA.equalsIgnoreCase(paramTYP)){
				model.put(SkatNctsExportConstants.RESOURCE_MODEL_KEY_CODE_NCTSEX_064_KANSLIGVARA_LIST, list);
				
			}else if(CodeDropDownMgr.CODE_096_SPEC_OMST.equalsIgnoreCase(paramTYP)){
				model.put(SkatNctsExportConstants.RESOURCE_MODEL_KEY_CODE_NCTSEX_096_SPEC_OMST_LIST, list);
				
			}else if(CodeDropDownMgr.CODE_105_TILLGANGSKOD_GARANTI.equalsIgnoreCase(paramTYP)){
				model.put(SkatNctsExportConstants.RESOURCE_MODEL_KEY_CODE_NCTSEX_105_TILLGANGASKOD_GARANTI_LIST, list);
			
			}else if(CodeDropDownMgr.CODE_106_TULLKONTOR_REF.equalsIgnoreCase(paramTYP)){
				model.put(SkatNctsExportConstants.RESOURCE_MODEL_KEY_CODE_NCTSEX_106_TULLKONTOR_REF_LIST, list);
			
			}else if(CodeDropDownMgr.CODE_108_TRANSPORTMADE_KODER.equalsIgnoreCase(paramTYP)){
				model.put(SkatNctsExportConstants.RESOURCE_MODEL_KEY_CODE_NCTSEX_108_TRANSPORTMADE_LIST, list);
			
			}else if(CodeDropDownMgr.CODE_116_BETALNING_TRANSPORT.equalsIgnoreCase(paramTYP)){
				model.put(SkatNctsExportConstants.RESOURCE_MODEL_KEY_CODE_NCTSEX_116_BETALNINGSSATT_TRANSPORTKOSTNAD_LIST, list);
			
			}else if(CodeDropDownMgr.CODE_302_STATUS_KODER.equalsIgnoreCase(paramTYP)){
				model.put(SkatNctsExportConstants.RESOURCE_MODEL_KEY_CODE_NCTSEX_302_STATUS_KODER_LIST, list);
			}	
		
		}catch(Exception e){
			e.printStackTrace();
		}
		
	}
	
}
