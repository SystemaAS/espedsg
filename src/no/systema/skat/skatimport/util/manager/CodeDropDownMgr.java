	/**
 * 
 */
package no.systema.skat.skatimport.util.manager;

import java.util.*;

import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;

import no.systema.main.model.SystemaWebUser;
import no.systema.main.service.UrlCgiProxyService;
import no.systema.skat.model.external.url.*;
import no.systema.skat.model.jsonjackson.codes.JsonSkatCode2Container;
import no.systema.skat.model.jsonjackson.codes.JsonSkatCode2Record;
import no.systema.skat.model.jsonjackson.codes.JsonSkatCodeContainer;
import no.systema.skat.model.jsonjackson.codes.JsonSkatCodeRecord;
import no.systema.skat.service.html.dropdown.SkatDropDownListPopulationService;
import no.systema.skat.url.store.SkatUrlDataStore;
import no.systema.skat.util.SkatConstants;
import no.systema.skat.skatimport.util.SkatImportConstants;

/**
 * The class handles general gui drop downs aspect population for SKAT IMPORT
 *
 * This Manager is not instantiated by the Spring Container at start up. 
 * Instead, it is instantiated by a controller when needed.
 * 
 * 
 * 
 * @author oscardelatorre
 * @date Jan 27, 2014
 * 
 * 
 */

public class CodeDropDownMgr {
	private static final Logger logger = Logger.getLogger(CodeDropDownMgr.class.getName());
	//
	public static final String CODE_001_ANGIVELSESARTER = "001";
	public static final String CODE_005_CERTIFIKAT_R44_2 = "005";
	public static final String CODE_006_TOLDSTED = "006";
	public static final String CODE_007_EMBALLAGE_R31 = "007";
	public static final String CODE_008_COUNTRY = "008";
	public static final String CODE_009_OPLYSNINGSTYPE_R44_9 = "009";
	public static final String CODE_011_PROCEDURE_R37 = "011";
	public static final String CODE_012_PDOKUMENTATIONSKODER_R44_4 = "012";
	public static final String CODE_013_PREFERENCE_R36 = "013";
	public static final String CODE_015_STATUS_KODER = "015";
	public static final String CODE_017_TRANSPORTDOK_SUMMARISKA_R40 = "017";
	public static final String CODE_018_TRANSPORTKODER_R25R26 = "018";
	public static final String CODE_019_VAB_CERTIFIKAT_R44_3 = "019";
	public static final String CODE_020_CURRENCY = "020";
	public static final String CODE_021_SUPP_VAREOPL_R44_6 = "021";
	public static final String CODE_022_SUPP_ENHEDER_R41_1 = "022";
	public static final String CODE_109_BETALING_FOR_TRANSPORT_RS29 = "109";
	//Borrowed from Export
	public static final String CODE_106_INCOTERMS = "106";
	
	
	
	/**
	 * 
	 * @param urlCgiProxyService
	 * @param skatDropDownListPopulationService
	 * @param model
	 * @param appUser
	 * 
	 * @param paramTYP
	 * @param paramKODE2
	 * @param paramKODE3
	 * @param map (hash for values)
	 * 
	 */
	public void populateCodesHtmlDropDownsFromJsonString(UrlCgiProxyService urlCgiProxyService, SkatDropDownListPopulationService skatDropDownListPopulationService,
														Map model, SystemaWebUser appUser, String paramTYP, String paramKODE2, String paramKODE3, Map map){
		//fill in html lists here
		try{
			String CODES_URL = SkatUrlDataStore.SKAT_CODES_URL;
			StringBuffer urlRequestParamsKeys = new StringBuffer();
			urlRequestParamsKeys.append("user=" + appUser.getUser());
			urlRequestParamsKeys.append(SkatConstants.URL_CHAR_DELIMETER_FOR_PARAMS_WITH_HTML_REQUEST + "typ=" + paramTYP);
			if(paramKODE2 !=null){
				urlRequestParamsKeys.append(SkatConstants.URL_CHAR_DELIMETER_FOR_PARAMS_WITH_HTML_REQUEST + "kode2=" + paramKODE2);
			}
			if(paramKODE3 !=null){
				/* Obsolete since we should see all duplicates
				urlRequestParamsKeys.append(SkatConstants.URL_CHAR_DELIMETER_FOR_PARAMS_WITH_HTML_REQUEST + "kode3=" + paramKODE3);
				*/
			}
			
			if(CODE_017_TRANSPORTDOK_SUMMARISKA_R40.equalsIgnoreCase(paramTYP)){
				if(map !=null && map.size()>0){
					String dkih_25 = (String)map.get("dkih_25");
					String dkih_26 = (String)map.get("dkih_26");
					if(dkih_25!=null){
						urlRequestParamsKeys.append(SkatConstants.URL_CHAR_DELIMETER_FOR_PARAMS_WITH_HTML_REQUEST + "dkih_25=" + dkih_25);
					}
					if(dkih_26!=null){
						urlRequestParamsKeys.append(SkatConstants.URL_CHAR_DELIMETER_FOR_PARAMS_WITH_HTML_REQUEST + "dkih_26=" + dkih_26);
					}
				}
			}
			
			//Debug
			//logger.info("CODES_URL:" + CODES_URL);
			//logger.info("CODES PARAMS:" + urlRequestParamsKeys.toString());

			//Now build the payload and send to the back end via the drop down service
			String utfPayload = urlCgiProxyService.getJsonContent(CODES_URL, urlRequestParamsKeys.toString());
			//debug
			//logger.info(utfPayload);
			
			JsonSkatCodeContainer codeContainer = skatDropDownListPopulationService.getCodeContainer(utfPayload);
			List<JsonSkatCodeRecord> list = new ArrayList();
			
			//Take some exception into consideration here or run the default to populate the final list
			HashSet<String> hashSet_019= new HashSet<String>();
			for(JsonSkatCodeRecord codeRecord: codeContainer.getKodlista()){
				if(CODE_017_TRANSPORTDOK_SUMMARISKA_R40.equalsIgnoreCase(paramTYP)){
					String prefix = codeRecord.getDkkd_kd2();
					String record = prefix + codeRecord.getDkkd_kd();
					codeRecord.setDkkd_kd(record);
					//logger.info("(1)" + codeRecord.getDkkd_kd());
					list.add(codeRecord);
				}else if(CODE_019_VAB_CERTIFIKAT_R44_3.equalsIgnoreCase(paramTYP)){
					hashSet_019.add(codeRecord.getDkkd_kd());
				}else{
					//default
					list.add(codeRecord);
				}
			}
			//We must wash the duplicates and sort the fields in this special case
			if(CODE_019_VAB_CERTIFIKAT_R44_3.equalsIgnoreCase(paramTYP)){
				list = this.getSortedListOn_019(hashSet_019, codeContainer);
			}
			//
			
			if(CODE_001_ANGIVELSESARTER.equalsIgnoreCase(paramTYP)){
				model.put(SkatImportConstants.RESOURCE_MODEL_KEY_CODE_001_ANGIVELSESARTER_LIST, list);
				
			}else if(CODE_005_CERTIFIKAT_R44_2.equalsIgnoreCase(paramTYP)){
				model.put(SkatImportConstants.RESOURCE_MODEL_KEY_CODE_005_CERTIFIKAT_R44_2_LIST, list);
				model.put(SkatImportConstants.URL_EXTERNAL_CERTIFIKATCODES_R44_2_SKAT_CODE, new UrlSkatCertifikatObject() );
				
			}else if(CODE_006_TOLDSTED.equalsIgnoreCase(paramTYP)){
				model.put(SkatImportConstants.RESOURCE_MODEL_KEY_CODE_006_TOLDSTED_LIST, list);
				model.put(SkatImportConstants.URL_EXTERNAL_TOLDSTED_CODE, new UrlSkatToldstedObject() );
				
			}else if(CODE_007_EMBALLAGE_R31.equalsIgnoreCase(paramTYP)){
				model.put(SkatImportConstants.RESOURCE_MODEL_KEY_CODE_007_EMBALLAGE_R31_LIST, list);
				model.put(SkatImportConstants.URL_EXTERNAL_KOLLIARTCODES_31_SKAT_CODE, new UrlSkatKolliartObject() );
		    	
			}else if(CODE_008_COUNTRY.equalsIgnoreCase(paramTYP)){
				model.put(SkatImportConstants.RESOURCE_MODEL_KEY_CODE_008_COUNTRY_LIST,list);
				model.put(SkatImportConstants.URL_EXTERNAL_LANDCODES_SKAT_CODE, new UrlSkatCountryObject() );
		    	
			}else if(CODE_009_OPLYSNINGSTYPE_R44_9.equalsIgnoreCase(paramTYP)){
				model.put(SkatImportConstants.RESOURCE_MODEL_KEY_CODE_009_OPLYSNINGSTYPE_LIST,list);
				model.put(SkatImportConstants.URL_EXTERNAL_OPLYSNINGSTYPECODES_44_9_SKAT_CODE, new UrlSkatOplysningstypeObject() );

			}else if(CODE_011_PROCEDURE_R37.equalsIgnoreCase(paramTYP)){
				model.put(SkatImportConstants.RESOURCE_MODEL_KEY_CODE_011_PROCEDURE_R37_LIST, list);
				model.put(SkatImportConstants.URL_EXTERNAL_PROCEDURECODES_37_SKAT_CODE, new UrlSkatProcedureObject() );

			}else if(CODE_012_PDOKUMENTATIONSKODER_R44_4.equalsIgnoreCase(paramTYP)){
				model.put(SkatImportConstants.RESOURCE_MODEL_KEY_CODE_012_PDOKUMENTATIONSKODER_R44_4_LIST,list);
				model.put(SkatImportConstants.URL_EXTERNAL_PREFERENCE_DOKUMENTATIONCODES_44_4_SKAT_CODE, new UrlSkatPreferenceDokumentationObject() );
			
			}else if(CODE_013_PREFERENCE_R36.equalsIgnoreCase(paramTYP)){
				model.put(SkatImportConstants.RESOURCE_MODEL_KEY_CODE_013_PREFERENCE_LIST, list);
				model.put(SkatImportConstants.URL_EXTERNAL_PREFERENCECODES_36_SKAT_CODE, new UrlSkatPreferenceObject() );

			}else if(CODE_015_STATUS_KODER.equalsIgnoreCase(paramTYP)){
				model.put(SkatImportConstants.RESOURCE_MODEL_KEY_CODE_015_STATUS_KODER_LIST, list);
				
			}else if(CODE_017_TRANSPORTDOK_SUMMARISKA_R40.equalsIgnoreCase(paramTYP)){
				model.put(SkatImportConstants.RESOURCE_MODEL_KEY_CODE_017_TRANSPORTDOK_SUMMARISKA_R40_LIST, list);
				model.put(SkatImportConstants.URL_EXTERNAL_TRANSPORT_SUMMARISKA_DOKUMENTCODES_40_SKAT_CODE, new UrlSkatTransportSummariskaDokumentObject() );
				
			}else if(CODE_018_TRANSPORTKODER_R25R26.equalsIgnoreCase(paramTYP)){
				model.put(SkatImportConstants.RESOURCE_MODEL_KEY_CODE_018_TRANSPORTKODER_R25R26_LIST, list);
				
			}else if(CODE_019_VAB_CERTIFIKAT_R44_3.equalsIgnoreCase(paramTYP)){
				model.put(SkatImportConstants.RESOURCE_MODEL_KEY_CODE_019_VAB_CERTIFIKAT_R44_3_LIST, list);
				model.put(SkatImportConstants.URL_EXTERNAL_VAB_CERTIFIKATCODES_R44_3_SKAT_CODE, new UrlSkatVabCertifikatObject() );
				
			}else if(CODE_020_CURRENCY.equalsIgnoreCase(paramTYP)){
				model.put(SkatImportConstants.RESOURCE_MODEL_KEY_CODE_020_CURRENCY_LIST,list);
				model.put(SkatImportConstants.URL_EXTERNAL_CURRENCYCODES_SKAT_CODE, new UrlSkatCurrencyObject() );
				
			}else if(CODE_021_SUPP_VAREOPL_R44_6.equalsIgnoreCase(paramTYP)){
				model.put(SkatImportConstants.RESOURCE_MODEL_KEY_CODE_021_SUPP_VAREOPL_R44_6_LIST, list);
			
			}else if(CODE_022_SUPP_ENHEDER_R41_1.equalsIgnoreCase(paramTYP)){
				model.put(SkatImportConstants.RESOURCE_MODEL_KEY_CODE_022_SUPP_ENHEDER_R41_1_LIST, list);
				model.put(SkatImportConstants.URL_EXTERNAL_UOM_SKAT_CODE, new UrlSkatUnitOfMeasureObject() );
				
			}else if(CODE_109_BETALING_FOR_TRANSPORT_RS29.equalsIgnoreCase(paramTYP)){
				model.put(SkatImportConstants.RESOURCE_MODEL_KEY_CODE_109_BETALING_FOR_TRANSPORT_RS29_LIST, list);
				
			}else if(CODE_106_INCOTERMS.equalsIgnoreCase(paramTYP)){
				model.put(SkatImportConstants.RESOURCE_MODEL_KEY_CODE_106_INCOTERMS_LIST, list);
				
			}
			
			//general external links to put in model
			model.put(SkatImportConstants.URL_EXTERNAL_TOLD_KONTINGENT_CODE, new UrlSkatKontingentObject() );
			
		}catch(Exception e){
			e.printStackTrace();
		}
		
	}
	
	/**
	 * 
	 * @param urlCgiProxyService
	 * @param skatDropDownListPopulationService
	 * @param model
	 * @param appUser
	 * @param paramTYP
	 */
	public void populateCodesHtmlDropDownsFromJsonString2(UrlCgiProxyService urlCgiProxyService, SkatDropDownListPopulationService skatDropDownListPopulationService,
			Map model, SystemaWebUser appUser, String paramTYP){
			//fill in html lists here
			try{
			
			String CODES_URL = SkatUrlDataStore.SKAT_CODES2_URL;
			StringBuffer urlRequestParamsKeys = new StringBuffer();
			urlRequestParamsKeys.append("user=" + appUser.getUser());
			urlRequestParamsKeys.append(SkatConstants.URL_CHAR_DELIMETER_FOR_PARAMS_WITH_HTML_REQUEST + "type=" + paramTYP);
			//logger.info("CODES_URL:" + CODES_URL);
			//logger.info("CODES PARAMS:" + urlRequestParamsKeys.toString());
			String utfPayload = urlCgiProxyService.getJsonContent(CODES_URL, urlRequestParamsKeys.toString());
			//debug
			//logger.info(utfPayload);
			
			JsonSkatCode2Container codeContainer = skatDropDownListPopulationService.getCodeContainer2(utfPayload);
			List<JsonSkatCode2Record> list = new ArrayList();
			
			//Take some exception into consideration here or run the default to populate the final list
			for(JsonSkatCode2Record codeRecord: codeContainer.getArkivkodelist()){
				//default
				list.add(codeRecord);
				//logger.info("CODE_RECORD: " + codeRecord.getArtype());
			}
			model.put(SkatImportConstants.RESOURCE_MODEL_KEY_CODE_ARCHIVE_CODE_LIST,list);
			
			
			
			//we put tolltariffen here since there is no other related list on ITEMS jsp
			//model.put(SadImportConstants.URL_EXTERNAL_TOLLTARIFFEN, new UrlTvinnSadTolltariffenObject() );
			
			}catch(Exception e){
				e.printStackTrace();
			}
			
	}
	/**
	 * 
	 * @param hashSet_019
	 * @param codeContainer
	 * @return
	 */
	private List<JsonSkatCodeRecord> getSortedListOn_019 (HashSet<String> hashSet_019, JsonSkatCodeContainer codeContainer){
		//Now wash the duplicates from the list
		List<JsonSkatCodeRecord> newList = new ArrayList<JsonSkatCodeRecord>();
		outer: for(String field : hashSet_019){
			inner: for(JsonSkatCodeRecord codeRecord: codeContainer.getKodlista()){
				if(field.equals(codeRecord.getDkkd_kd())){
					//logger.info("MATCH_019: " + field);
					newList.add(codeRecord);
					continue outer;
				}
			}
		}
		return this.getSortedListByColumn(newList);
	}
	
	/**
	 * 
	 * @param originalList
	 * @param column
	 * @param imgSortPng
	 * @return
	 */
	private List<JsonSkatCodeRecord> getSortedListByColumn(Collection originalList){
		List<JsonSkatCodeRecord> sortedList = (List)originalList;
		Collections.sort(sortedList, new JsonSkatCodeRecord.OrderByDkkd_kd() );
		return sortedList;
	}
	
	
	
	/**
	 * In order to boost performance issues
	 * @param model
	 * @param session
	 */
	public void setCodeMgrListsInSession(Map model, HttpSession session){
		session.setAttribute("model." + SkatImportConstants.RESOURCE_MODEL_KEY_CODE_001_ANGIVELSESARTER_LIST, (List)model.get(SkatImportConstants.RESOURCE_MODEL_KEY_CODE_001_ANGIVELSESARTER_LIST) );
		session.setAttribute("model." + SkatImportConstants.RESOURCE_MODEL_KEY_CODE_005_CERTIFIKAT_R44_2_LIST, (List)model.get(SkatImportConstants.RESOURCE_MODEL_KEY_CODE_005_CERTIFIKAT_R44_2_LIST) );
		session.setAttribute("model." + SkatImportConstants.RESOURCE_MODEL_KEY_CODE_006_TOLDSTED_LIST, (List)model.get(SkatImportConstants.RESOURCE_MODEL_KEY_CODE_006_TOLDSTED_LIST) );
		session.setAttribute("model." + SkatImportConstants.RESOURCE_MODEL_KEY_CODE_007_EMBALLAGE_R31_LIST, (List)model.get(SkatImportConstants.RESOURCE_MODEL_KEY_CODE_007_EMBALLAGE_R31_LIST) );
		session.setAttribute("model." + SkatImportConstants.RESOURCE_MODEL_KEY_CODE_008_COUNTRY_LIST, (List)model.get(SkatImportConstants.RESOURCE_MODEL_KEY_CODE_008_COUNTRY_LIST) );
		session.setAttribute("model." + SkatImportConstants.RESOURCE_MODEL_KEY_CODE_009_OPLYSNINGSTYPE_LIST, (List)model.get(SkatImportConstants.RESOURCE_MODEL_KEY_CODE_009_OPLYSNINGSTYPE_LIST) );
		session.setAttribute("model." + SkatImportConstants.RESOURCE_MODEL_KEY_CODE_011_PROCEDURE_R37_LIST, (List)model.get(SkatImportConstants.RESOURCE_MODEL_KEY_CODE_011_PROCEDURE_R37_LIST) );
		session.setAttribute("model." + SkatImportConstants.RESOURCE_MODEL_KEY_CODE_012_PDOKUMENTATIONSKODER_R44_4_LIST, (List)model.get(SkatImportConstants.RESOURCE_MODEL_KEY_CODE_012_PDOKUMENTATIONSKODER_R44_4_LIST) );
		session.setAttribute("model." + SkatImportConstants.RESOURCE_MODEL_KEY_CODE_013_PREFERENCE_LIST, (List)model.get(SkatImportConstants.RESOURCE_MODEL_KEY_CODE_013_PREFERENCE_LIST) );
		session.setAttribute("model." + SkatImportConstants.RESOURCE_MODEL_KEY_CODE_015_STATUS_KODER_LIST, (List)model.get(SkatImportConstants.RESOURCE_MODEL_KEY_CODE_015_STATUS_KODER_LIST) );
		session.setAttribute("model." + SkatImportConstants.RESOURCE_MODEL_KEY_CODE_017_TRANSPORTDOK_SUMMARISKA_R40_LIST, (List)model.get(SkatImportConstants.RESOURCE_MODEL_KEY_CODE_017_TRANSPORTDOK_SUMMARISKA_R40_LIST) );
		session.setAttribute("model." + SkatImportConstants.RESOURCE_MODEL_KEY_CODE_018_TRANSPORTKODER_R25R26_LIST, (List)model.get(SkatImportConstants.RESOURCE_MODEL_KEY_CODE_018_TRANSPORTKODER_R25R26_LIST) );
		session.setAttribute("model." + SkatImportConstants.RESOURCE_MODEL_KEY_CODE_019_VAB_CERTIFIKAT_R44_3_LIST, (List)model.get(SkatImportConstants.RESOURCE_MODEL_KEY_CODE_019_VAB_CERTIFIKAT_R44_3_LIST) );
		session.setAttribute("model." + SkatImportConstants.RESOURCE_MODEL_KEY_CODE_020_CURRENCY_LIST, (List)model.get(SkatImportConstants.RESOURCE_MODEL_KEY_CODE_020_CURRENCY_LIST) );
		session.setAttribute("model." + SkatImportConstants.RESOURCE_MODEL_KEY_CODE_021_SUPP_VAREOPL_R44_6_LIST, (List)model.get(SkatImportConstants.RESOURCE_MODEL_KEY_CODE_021_SUPP_VAREOPL_R44_6_LIST) );
		session.setAttribute("model." + SkatImportConstants.RESOURCE_MODEL_KEY_CODE_022_SUPP_ENHEDER_R41_1_LIST, (List)model.get(SkatImportConstants.RESOURCE_MODEL_KEY_CODE_022_SUPP_ENHEDER_R41_1_LIST) );
		session.setAttribute("model." + SkatImportConstants.RESOURCE_MODEL_KEY_CODE_109_BETALING_FOR_TRANSPORT_RS29_LIST, (List)model.get(SkatImportConstants.RESOURCE_MODEL_KEY_CODE_109_BETALING_FOR_TRANSPORT_RS29_LIST) );
		session.setAttribute("model." + SkatImportConstants.RESOURCE_MODEL_KEY_CODE_106_INCOTERMS_LIST, (List)model.get(SkatImportConstants.RESOURCE_MODEL_KEY_CODE_106_INCOTERMS_LIST) );
		
	}
	/**
	 * After some Metrics this method boosts performance.
	 * @param model
	 * @param session
	 */
	public void getCodeMgrListsFromSession(Map model, HttpSession session){
		List list = new ArrayList();
		list = (List)session.getAttribute("model." + SkatImportConstants.RESOURCE_MODEL_KEY_CODE_001_ANGIVELSESARTER_LIST);
		model.put(SkatImportConstants.RESOURCE_MODEL_KEY_CODE_001_ANGIVELSESARTER_LIST, list);
		list = (List)session.getAttribute("model." + SkatImportConstants.RESOURCE_MODEL_KEY_CODE_005_CERTIFIKAT_R44_2_LIST);
		model.put(SkatImportConstants.RESOURCE_MODEL_KEY_CODE_005_CERTIFIKAT_R44_2_LIST, list);
		list = (List)session.getAttribute("model." + SkatImportConstants.RESOURCE_MODEL_KEY_CODE_006_TOLDSTED_LIST);
		model.put(SkatImportConstants.RESOURCE_MODEL_KEY_CODE_006_TOLDSTED_LIST, list);
		list = (List)session.getAttribute("model." + SkatImportConstants.RESOURCE_MODEL_KEY_CODE_007_EMBALLAGE_R31_LIST);
		model.put(SkatImportConstants.RESOURCE_MODEL_KEY_CODE_007_EMBALLAGE_R31_LIST, list);
		list = (List)session.getAttribute("model." + SkatImportConstants.RESOURCE_MODEL_KEY_CODE_008_COUNTRY_LIST);
		model.put(SkatImportConstants.RESOURCE_MODEL_KEY_CODE_008_COUNTRY_LIST, list);
		list = (List)session.getAttribute("model." + SkatImportConstants.RESOURCE_MODEL_KEY_CODE_009_OPLYSNINGSTYPE_LIST);
		model.put(SkatImportConstants.RESOURCE_MODEL_KEY_CODE_009_OPLYSNINGSTYPE_LIST, list);
		list = (List)session.getAttribute("model." + SkatImportConstants.RESOURCE_MODEL_KEY_CODE_011_PROCEDURE_R37_LIST);
		model.put(SkatImportConstants.RESOURCE_MODEL_KEY_CODE_011_PROCEDURE_R37_LIST, list);
		list = (List)session.getAttribute("model." + SkatImportConstants.RESOURCE_MODEL_KEY_CODE_012_PDOKUMENTATIONSKODER_R44_4_LIST);
		model.put(SkatImportConstants.RESOURCE_MODEL_KEY_CODE_012_PDOKUMENTATIONSKODER_R44_4_LIST, list);
		list = (List)session.getAttribute("model." + SkatImportConstants.RESOURCE_MODEL_KEY_CODE_013_PREFERENCE_LIST);
		model.put(SkatImportConstants.RESOURCE_MODEL_KEY_CODE_013_PREFERENCE_LIST, list);
		list = (List)session.getAttribute("model." + SkatImportConstants.RESOURCE_MODEL_KEY_CODE_015_STATUS_KODER_LIST);
		model.put(SkatImportConstants.RESOURCE_MODEL_KEY_CODE_015_STATUS_KODER_LIST, list);
		list = (List)session.getAttribute("model." + SkatImportConstants.RESOURCE_MODEL_KEY_CODE_017_TRANSPORTDOK_SUMMARISKA_R40_LIST);
		model.put(SkatImportConstants.RESOURCE_MODEL_KEY_CODE_017_TRANSPORTDOK_SUMMARISKA_R40_LIST, list);
		list = (List)session.getAttribute("model." + SkatImportConstants.RESOURCE_MODEL_KEY_CODE_018_TRANSPORTKODER_R25R26_LIST);
		model.put(SkatImportConstants.RESOURCE_MODEL_KEY_CODE_018_TRANSPORTKODER_R25R26_LIST, list);
		list = (List)session.getAttribute("model." + SkatImportConstants.RESOURCE_MODEL_KEY_CODE_019_VAB_CERTIFIKAT_R44_3_LIST);
		model.put(SkatImportConstants.RESOURCE_MODEL_KEY_CODE_019_VAB_CERTIFIKAT_R44_3_LIST, list);
		list = (List)session.getAttribute("model." + SkatImportConstants.RESOURCE_MODEL_KEY_CODE_020_CURRENCY_LIST);
		model.put(SkatImportConstants.RESOURCE_MODEL_KEY_CODE_020_CURRENCY_LIST, list);
		list = (List)session.getAttribute("model." + SkatImportConstants.RESOURCE_MODEL_KEY_CODE_021_SUPP_VAREOPL_R44_6_LIST);
		model.put(SkatImportConstants.RESOURCE_MODEL_KEY_CODE_021_SUPP_VAREOPL_R44_6_LIST, list);
		list = (List)session.getAttribute("model." + SkatImportConstants.RESOURCE_MODEL_KEY_CODE_022_SUPP_ENHEDER_R41_1_LIST);
		model.put(SkatImportConstants.RESOURCE_MODEL_KEY_CODE_022_SUPP_ENHEDER_R41_1_LIST, list);
		list = (List)session.getAttribute("model." + SkatImportConstants.RESOURCE_MODEL_KEY_CODE_109_BETALING_FOR_TRANSPORT_RS29_LIST);
		model.put(SkatImportConstants.RESOURCE_MODEL_KEY_CODE_109_BETALING_FOR_TRANSPORT_RS29_LIST, list);
		list = (List)session.getAttribute("model." + SkatImportConstants.RESOURCE_MODEL_KEY_CODE_106_INCOTERMS_LIST);
		model.put(SkatImportConstants.RESOURCE_MODEL_KEY_CODE_106_INCOTERMS_LIST, list);
		
	}
	
	
}
