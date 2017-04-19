	/**
 * 
 */
package no.systema.skat.skatexport.util.manager;

import java.util.*;

import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;

import no.systema.main.model.SystemaWebUser;
import no.systema.main.service.UrlCgiProxyService;
import no.systema.skat.model.external.url.*;

import no.systema.skat.model.jsonjackson.codes.JsonSkatCodeContainer;
import no.systema.skat.model.jsonjackson.codes.JsonSkatCodeRecord;
import no.systema.skat.service.html.dropdown.SkatDropDownListPopulationService;
import no.systema.skat.url.store.SkatUrlDataStore;
import no.systema.skat.util.SkatConstants;
import no.systema.skat.model.jsonjackson.codes.JsonSkatCode2Container;
import no.systema.skat.model.jsonjackson.codes.JsonSkatCode2Record;
import no.systema.skat.skatexport.util.SkatExportConstants;


/**
 * The class handles general gui drop downs aspect population for SKAT IMPORT
 *
 * This Manager is not instantiated by the Spring Container at start up. 
 * Instead, it is instantiated by a controller when needed.
 * 
 * 
 * 
 * @author oscardelatorre
 * @date Feb 26, 2014
 * 
 * 	                                                     
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
		 127=Statuskoder (typer)                                                       
 * 
 */

public class CodeDropDownMgr {
	private static final Logger logger = Logger.getLogger(CodeDropDownMgr.class.getName());
	//
	public static final String CODE_008_COUNTRY = "008";
	public static final String CODE_017_TRANSPORTDOK_SUMMARISKA_R40 = "017";
	public static final String CODE_022_SUPP_ENHEDER = "022"; //same as import
	
	public static final String CODE_102_ANGIVELSESARTER = "102";
	public static final String CODE_103_TOLDSTED = "103";
	public static final String CODE_104_ANGIVELSESTYPE = "104";
	public static final String CODE_106_INCOTERMS = "106";
	public static final String CODE_107_CURRENCY = "107";
	public static final String CODE_108_TRANSPORTMADE = "108";
	public static final String CODE_109_BETALNINGSMADE = "109";
	public static final String CODE_110_EMBALLAGE_R31 = "110";
	public static final String CODE_112_PROCEDURE_R37 = "112";
	public static final String CODE_113_CERTIFIKAT_R44_2 = "113";
	public static final String CODE_114_VAB_CERTIFIKAT_R44_3 = "114";
	public static final String CODE_115_FN_FARLIG_GODS_R44_4 = "115";
	public static final String CODE_116_TRANSPORTDOK_TYPE_R44_5_1 = "116";
	public static final String CODE_117_UDGANGSTOLDSTED = "117";
	public static final String CODE_118_TRANSPORTDOK_SUMMARISKA_KATEGORY_R40 = "118";
	public static final String CODE_119_EXPORTARTER = "119";
	public static final String CODE_122_ERKLAERINGER_YM = "122";
	public static final String CODE_123_T_STATUS = "123";
	public static final String CODE_124_SUPPL_ENHEDER_YM = "124";
	public static final String CODE_126_EU_ANGIVELSESARTER = "126";
	public static final String CODE_127_STATUS_KODER = "127";
	
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
			
			String CODES_URL = SkatUrlDataStore.SKAT_CODES_URL;
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
			/*logger.info("CODES_URL:" + CODES_URL);
			logger.info("CODES PARAMS:" + urlRequestParamsKeys.toString());
			logger.info(utfPayload);
			*/
			
			JsonSkatCodeContainer codeContainer = skatDropDownListPopulationService.getCodeContainer(utfPayload);
			List<JsonSkatCodeRecord> list = new ArrayList();
			
			//Take some exception into consideration here or run the default to populate the final list
			for(JsonSkatCodeRecord codeRecord: codeContainer.getKodlista()){
				if(CODE_017_TRANSPORTDOK_SUMMARISKA_R40.equalsIgnoreCase(paramTYP)){
					String prefix = codeRecord.getDkkd_kd2();
					String record = prefix + codeRecord.getDkkd_kd();
					codeRecord.setDkkd_kd(record);
					list.add(codeRecord);
				}else{
					//default
					list.add(codeRecord);
				}
			}
			
			if(CodeDropDownMgr.CODE_008_COUNTRY.equalsIgnoreCase(paramTYP)){
				model.put(SkatExportConstants.RESOURCE_MODEL_KEY_CODE_008_COUNTRY_LIST,list);
				model.put(SkatExportConstants.URL_EXTERNAL_LANDCODES_SKAT_CODE, new UrlSkatCountryObject() );
		    	
			}else if(CodeDropDownMgr.CODE_017_TRANSPORTDOK_SUMMARISKA_R40.equalsIgnoreCase(paramTYP)){
				model.put(SkatExportConstants.RESOURCE_MODEL_KEY_CODE_017_TRANSPORTDOK_SUMMARISKA_R40_LIST, list);
				model.put(SkatExportConstants.URL_EXTERNAL_TRANSPORT_SUMMARISKA_DOKUMENTCODES_40_SKAT_CODE, new UrlSkatTransportSummariskaDokumentObject() );
				
			}else if(CodeDropDownMgr.CODE_022_SUPP_ENHEDER.equalsIgnoreCase(paramTYP)){
				model.put(SkatExportConstants.RESOURCE_MODEL_KEY_CODE_022_SUPP_ENHEDER_R41_1_LIST, list);
				model.put(SkatExportConstants.URL_EXTERNAL_UOM_SKAT_CODE, new UrlSkatUnitOfMeasureObject() );
				
			}else if(CodeDropDownMgr.CODE_102_ANGIVELSESARTER.equalsIgnoreCase(paramTYP)){
				model.put(SkatExportConstants.RESOURCE_MODEL_KEY_CODE_102_ANGIVELSESARTER_LIST, list);
				
			}else if(CodeDropDownMgr.CODE_103_TOLDSTED.equalsIgnoreCase(paramTYP)){
				model.put(SkatExportConstants.RESOURCE_MODEL_KEY_CODE_103_TOLDSTED_LIST, list);
				
			}else if(CodeDropDownMgr.CODE_104_ANGIVELSESTYPE.equalsIgnoreCase(paramTYP)){
				model.put(SkatExportConstants.RESOURCE_MODEL_KEY_CODE_104_ANGIVELSESTYPE_LIST, list);
				
			}else if(CodeDropDownMgr.CODE_106_INCOTERMS.equalsIgnoreCase(paramTYP)){
				model.put(SkatExportConstants.RESOURCE_MODEL_KEY_CODE_106_INCOTERMS_LIST, list);
				
			}else if(CodeDropDownMgr.CODE_107_CURRENCY.equalsIgnoreCase(paramTYP)){
				model.put(SkatExportConstants.RESOURCE_MODEL_KEY_CODE_107_CURRENCY_LIST, list);
				model.put(SkatExportConstants.URL_EXTERNAL_CURRENCYCODES_SKAT_CODE, new UrlSkatCurrencyObject() );
				
			}else if(CodeDropDownMgr.CODE_108_TRANSPORTMADE.equalsIgnoreCase(paramTYP)){
				model.put(SkatExportConstants.RESOURCE_MODEL_KEY_CODE_108_TRANSPORTMADE_LIST, list);
				
			}else if(CodeDropDownMgr.CODE_109_BETALNINGSMADE.equalsIgnoreCase(paramTYP)){
				model.put(SkatExportConstants.RESOURCE_MODEL_KEY_CODE_109_BETALNINGSMADE_LIST, list);
				
			}else if(CodeDropDownMgr.CODE_110_EMBALLAGE_R31.equalsIgnoreCase(paramTYP)){
				model.put(SkatExportConstants.RESOURCE_MODEL_KEY_CODE_110_EMBALLAGE_R31_LIST,list);
				model.put(SkatExportConstants.URL_EXTERNAL_STAMDATALISTE_SKAT_CODE, new UrlExportSkatStamDataListeObject() );
				
			}else if(CodeDropDownMgr.CODE_112_PROCEDURE_R37.equalsIgnoreCase(paramTYP)){
				model.put(SkatExportConstants.RESOURCE_MODEL_KEY_CODE_112_PROCEDURE_R37_LIST, list);
				model.put(SkatExportConstants.URL_EXTERNAL_STAMDATALISTE_SKAT_CODE, new UrlExportSkatStamDataListeObject() );
				
			}else if(CodeDropDownMgr.CODE_113_CERTIFIKAT_R44_2.equalsIgnoreCase(paramTYP)){
				model.put(SkatExportConstants.RESOURCE_MODEL_KEY_CODE_113_CERTIFIKAT_R44_2_LIST, list);
				model.put(SkatExportConstants.URL_EXTERNAL_CERTIFIKATCODES_R44_2_SKAT_CODE, new UrlSkatCertifikatObject() );
				
			}else if(CodeDropDownMgr.CODE_114_VAB_CERTIFIKAT_R44_3.equalsIgnoreCase(paramTYP)){
				model.put(SkatExportConstants.RESOURCE_MODEL_KEY_CODE_114_VAB_CERTIFIKAT_R44_3_LIST, list);
				model.put(SkatExportConstants.URL_EXTERNAL_VAB_CERTIFIKATCODES_R44_3_SKAT_CODE, new UrlSkatCertifikatObject() );
				
			}else if(CodeDropDownMgr.CODE_115_FN_FARLIG_GODS_R44_4.equalsIgnoreCase(paramTYP)){
				model.put(SkatExportConstants.RESOURCE_MODEL_KEY_CODE_115_FN_FARLIG_GODS_R44_4_LIST, list);
				model.put(SkatExportConstants.URL_EXTERNAL_STAMDATALISTE_SKAT_CODE, new UrlExportSkatStamDataListeObject() );
				
			}else if(CodeDropDownMgr.CODE_116_TRANSPORTDOK_TYPE_R44_5_1.equalsIgnoreCase(paramTYP)){
				model.put(SkatExportConstants.RESOURCE_MODEL_KEY_CODE_116_TRANSPORTDOK_TYPE_R44_5_1_LIST, list);
				//Don't needed since others above already send it...
				//model.put(SkatExportConstants.URL_EXTERNAL_STAMDATALISTE_SKAT_CODE, new UrlExportSkatStamDataListeObject() );
				
			}else if(CodeDropDownMgr.CODE_117_UDGANGSTOLDSTED.equalsIgnoreCase(paramTYP)){
				model.put(SkatExportConstants.RESOURCE_MODEL_KEY_CODE_117_UDGANGSTOLDSTED_LIST, list);
				
			}else if(CodeDropDownMgr.CODE_119_EXPORTARTER.equalsIgnoreCase(paramTYP)){
				model.put(SkatExportConstants.RESOURCE_MODEL_KEY_CODE_119_EXPORTARTER_LIST, list);
				model.put(SkatExportConstants.URL_EXTERNAL_EXPORTARTER_CODE, new UrlExportSkatExportArterObject() );
				
			}else if(CodeDropDownMgr.CODE_122_ERKLAERINGER_YM.equalsIgnoreCase(paramTYP)){
				model.put(SkatExportConstants.RESOURCE_MODEL_KEY_CODE_122_ERKLAERINGER_YM_LIST, list);
				
			}else if(CodeDropDownMgr.CODE_123_T_STATUS.equalsIgnoreCase(paramTYP)){
				model.put(SkatExportConstants.RESOURCE_MODEL_KEY_CODE_123_T_STATUS_LIST, list);
				
			}else if(CodeDropDownMgr.CODE_124_SUPPL_ENHEDER_YM.equalsIgnoreCase(paramTYP)){
				model.put(SkatExportConstants.RESOURCE_MODEL_KEY_CODE_124_SUPPL_ENHEDER_YM_LIST, list);
				
			}else if(CodeDropDownMgr.CODE_126_EU_ANGIVELSESARTER.equalsIgnoreCase(paramTYP)){
				model.put(SkatExportConstants.RESOURCE_MODEL_KEY_CODE_126_EU_ANGIVELSESARTER_LIST, list);
				
			}else if(CodeDropDownMgr.CODE_127_STATUS_KODER.equalsIgnoreCase(paramTYP)){
				model.put(SkatExportConstants.RESOURCE_MODEL_KEY_CODE_127_STATUS_KODER_LIST, list);
				
			}
			
			
			
		}catch(Exception e){
			e.printStackTrace();
		}
		
	}
	/**
	 * 
	 * @param urlCgiProxyService
	 * @param tvinnSadDropDownListPopulationService
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
			model.put(SkatExportConstants.RESOURCE_MODEL_KEY_CODE_ARCHIVE_CODE_LIST,list);
			
			
			
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
	 * 
	 * @param model
	 * @param session
	 */
	public void setCodeMgrListsInSession(Map model, HttpSession session){
		session.setAttribute("model." + SkatExportConstants.RESOURCE_MODEL_KEY_CODE_008_COUNTRY_LIST, (List)model.get(SkatExportConstants.RESOURCE_MODEL_KEY_CODE_008_COUNTRY_LIST) );
		session.setAttribute("model." + SkatExportConstants.RESOURCE_MODEL_KEY_CODE_017_TRANSPORTDOK_SUMMARISKA_R40_LIST, (List)model.get(SkatExportConstants.RESOURCE_MODEL_KEY_CODE_017_TRANSPORTDOK_SUMMARISKA_R40_LIST) );
		session.setAttribute("model." + SkatExportConstants.RESOURCE_MODEL_KEY_CODE_022_SUPP_ENHEDER_R41_1_LIST, (List)model.get(SkatExportConstants.RESOURCE_MODEL_KEY_CODE_022_SUPP_ENHEDER_R41_1_LIST) );
		session.setAttribute("model." + SkatExportConstants.RESOURCE_MODEL_KEY_CODE_102_ANGIVELSESARTER_LIST, (List)model.get(SkatExportConstants.RESOURCE_MODEL_KEY_CODE_102_ANGIVELSESARTER_LIST) );
		session.setAttribute("model." + SkatExportConstants.RESOURCE_MODEL_KEY_CODE_103_TOLDSTED_LIST, (List)model.get(SkatExportConstants.RESOURCE_MODEL_KEY_CODE_103_TOLDSTED_LIST) );
		session.setAttribute("model." + SkatExportConstants.RESOURCE_MODEL_KEY_CODE_104_ANGIVELSESTYPE_LIST, (List)model.get(SkatExportConstants.RESOURCE_MODEL_KEY_CODE_104_ANGIVELSESTYPE_LIST) );
		session.setAttribute("model." + SkatExportConstants.RESOURCE_MODEL_KEY_CODE_106_INCOTERMS_LIST, (List)model.get(SkatExportConstants.RESOURCE_MODEL_KEY_CODE_106_INCOTERMS_LIST) );
		session.setAttribute("model." + SkatExportConstants.RESOURCE_MODEL_KEY_CODE_107_CURRENCY_LIST, (List)model.get(SkatExportConstants.RESOURCE_MODEL_KEY_CODE_107_CURRENCY_LIST) );
		session.setAttribute("model." + SkatExportConstants.RESOURCE_MODEL_KEY_CODE_108_TRANSPORTMADE_LIST, (List)model.get(SkatExportConstants.RESOURCE_MODEL_KEY_CODE_108_TRANSPORTMADE_LIST) );
		session.setAttribute("model." + SkatExportConstants.RESOURCE_MODEL_KEY_CODE_109_BETALNINGSMADE_LIST, (List)model.get(SkatExportConstants.RESOURCE_MODEL_KEY_CODE_109_BETALNINGSMADE_LIST) );
		//
		session.setAttribute("model." + SkatExportConstants.RESOURCE_MODEL_KEY_CODE_110_EMBALLAGE_R31_LIST, (List)model.get(SkatExportConstants.RESOURCE_MODEL_KEY_CODE_110_EMBALLAGE_R31_LIST) );
		session.setAttribute("model." + SkatExportConstants.RESOURCE_MODEL_KEY_CODE_112_PROCEDURE_R37_LIST, (List)model.get(SkatExportConstants.RESOURCE_MODEL_KEY_CODE_112_PROCEDURE_R37_LIST) );
		session.setAttribute("model." + SkatExportConstants.RESOURCE_MODEL_KEY_CODE_113_CERTIFIKAT_R44_2_LIST, (List)model.get(SkatExportConstants.RESOURCE_MODEL_KEY_CODE_113_CERTIFIKAT_R44_2_LIST) );
		session.setAttribute("model." + SkatExportConstants.RESOURCE_MODEL_KEY_CODE_114_VAB_CERTIFIKAT_R44_3_LIST, (List)model.get(SkatExportConstants.RESOURCE_MODEL_KEY_CODE_114_VAB_CERTIFIKAT_R44_3_LIST) );
		session.setAttribute("model." + SkatExportConstants.RESOURCE_MODEL_KEY_CODE_115_FN_FARLIG_GODS_R44_4_LIST, (List)model.get(SkatExportConstants.RESOURCE_MODEL_KEY_CODE_115_FN_FARLIG_GODS_R44_4_LIST) );
		session.setAttribute("model." + SkatExportConstants.RESOURCE_MODEL_KEY_CODE_116_TRANSPORTDOK_TYPE_R44_5_1_LIST, (List)model.get(SkatExportConstants.RESOURCE_MODEL_KEY_CODE_116_TRANSPORTDOK_TYPE_R44_5_1_LIST) );
		//
		session.setAttribute("model." + SkatExportConstants.RESOURCE_MODEL_KEY_CODE_117_UDGANGSTOLDSTED_LIST, (List)model.get(SkatExportConstants.RESOURCE_MODEL_KEY_CODE_117_UDGANGSTOLDSTED_LIST) );
		session.setAttribute("model." + SkatExportConstants.RESOURCE_MODEL_KEY_CODE_119_EXPORTARTER_LIST, (List)model.get(SkatExportConstants.RESOURCE_MODEL_KEY_CODE_119_EXPORTARTER_LIST) );
		session.setAttribute("model." + SkatExportConstants.RESOURCE_MODEL_KEY_CODE_122_ERKLAERINGER_YM_LIST, (List)model.get(SkatExportConstants.RESOURCE_MODEL_KEY_CODE_122_ERKLAERINGER_YM_LIST) );
		session.setAttribute("model." + SkatExportConstants.RESOURCE_MODEL_KEY_CODE_123_T_STATUS_LIST, (List)model.get(SkatExportConstants.RESOURCE_MODEL_KEY_CODE_123_T_STATUS_LIST) );
		session.setAttribute("model." + SkatExportConstants.RESOURCE_MODEL_KEY_CODE_124_SUPPL_ENHEDER_YM_LIST, (List)model.get(SkatExportConstants.RESOURCE_MODEL_KEY_CODE_124_SUPPL_ENHEDER_YM_LIST) );
		session.setAttribute("model." + SkatExportConstants.RESOURCE_MODEL_KEY_CODE_126_EU_ANGIVELSESARTER_LIST, (List)model.get(SkatExportConstants.RESOURCE_MODEL_KEY_CODE_126_EU_ANGIVELSESARTER_LIST) );
		session.setAttribute("model." + SkatExportConstants.RESOURCE_MODEL_KEY_CODE_127_STATUS_KODER_LIST, (List)model.get(SkatExportConstants.RESOURCE_MODEL_KEY_CODE_127_STATUS_KODER_LIST) );
		
		
	}
	
	/**
	 * 
	 * @param model
	 * @param session
	 */
	public void getCodeMgrListsFromSession(Map model, HttpSession session){
		List list = new ArrayList();
		list = (List)session.getAttribute("model." + SkatExportConstants.RESOURCE_MODEL_KEY_CODE_008_COUNTRY_LIST);
		model.put(SkatExportConstants.RESOURCE_MODEL_KEY_CODE_008_COUNTRY_LIST, list);
		list = (List)session.getAttribute("model." + SkatExportConstants.RESOURCE_MODEL_KEY_CODE_017_TRANSPORTDOK_SUMMARISKA_R40_LIST);
		model.put(SkatExportConstants.RESOURCE_MODEL_KEY_CODE_017_TRANSPORTDOK_SUMMARISKA_R40_LIST, list);
		
		list = (List)session.getAttribute("model." + SkatExportConstants.RESOURCE_MODEL_KEY_CODE_022_SUPP_ENHEDER_R41_1_LIST);
		model.put(SkatExportConstants.RESOURCE_MODEL_KEY_CODE_022_SUPP_ENHEDER_R41_1_LIST, list);
		list = (List)session.getAttribute("model." + SkatExportConstants.RESOURCE_MODEL_KEY_CODE_102_ANGIVELSESARTER_LIST);
		model.put(SkatExportConstants.RESOURCE_MODEL_KEY_CODE_102_ANGIVELSESARTER_LIST, list);
		list = (List)session.getAttribute("model." + SkatExportConstants.RESOURCE_MODEL_KEY_CODE_103_TOLDSTED_LIST);
		model.put(SkatExportConstants.RESOURCE_MODEL_KEY_CODE_103_TOLDSTED_LIST, list);
		list = (List)session.getAttribute("model." + SkatExportConstants.RESOURCE_MODEL_KEY_CODE_104_ANGIVELSESTYPE_LIST);
		model.put(SkatExportConstants.RESOURCE_MODEL_KEY_CODE_104_ANGIVELSESTYPE_LIST, list);
		list = (List)session.getAttribute("model." + SkatExportConstants.RESOURCE_MODEL_KEY_CODE_106_INCOTERMS_LIST);
		model.put(SkatExportConstants.RESOURCE_MODEL_KEY_CODE_106_INCOTERMS_LIST, list);
		list = (List)session.getAttribute("model." + SkatExportConstants.RESOURCE_MODEL_KEY_CODE_107_CURRENCY_LIST);
		model.put(SkatExportConstants.RESOURCE_MODEL_KEY_CODE_107_CURRENCY_LIST, list);
		list = (List)session.getAttribute("model." + SkatExportConstants.RESOURCE_MODEL_KEY_CODE_108_TRANSPORTMADE_LIST);
		model.put(SkatExportConstants.RESOURCE_MODEL_KEY_CODE_108_TRANSPORTMADE_LIST, list);
		list = (List)session.getAttribute("model." + SkatExportConstants.RESOURCE_MODEL_KEY_CODE_109_BETALNINGSMADE_LIST);
		model.put(SkatExportConstants.RESOURCE_MODEL_KEY_CODE_109_BETALNINGSMADE_LIST, list);
		list = (List)session.getAttribute("model." + SkatExportConstants.RESOURCE_MODEL_KEY_CODE_110_EMBALLAGE_R31_LIST);
		model.put(SkatExportConstants.RESOURCE_MODEL_KEY_CODE_110_EMBALLAGE_R31_LIST, list);
		list = (List)session.getAttribute("model." + SkatExportConstants.RESOURCE_MODEL_KEY_CODE_112_PROCEDURE_R37_LIST);
		model.put(SkatExportConstants.RESOURCE_MODEL_KEY_CODE_112_PROCEDURE_R37_LIST, list);
		list = (List)session.getAttribute("model." + SkatExportConstants.RESOURCE_MODEL_KEY_CODE_113_CERTIFIKAT_R44_2_LIST);
		model.put(SkatExportConstants.RESOURCE_MODEL_KEY_CODE_113_CERTIFIKAT_R44_2_LIST, list);
		//
		list = (List)session.getAttribute("model." + SkatExportConstants.RESOURCE_MODEL_KEY_CODE_114_VAB_CERTIFIKAT_R44_3_LIST);
		model.put(SkatExportConstants.RESOURCE_MODEL_KEY_CODE_114_VAB_CERTIFIKAT_R44_3_LIST, list);
		list = (List)session.getAttribute("model." + SkatExportConstants.RESOURCE_MODEL_KEY_CODE_115_FN_FARLIG_GODS_R44_4_LIST);
		model.put(SkatExportConstants.RESOURCE_MODEL_KEY_CODE_115_FN_FARLIG_GODS_R44_4_LIST, list);
		list = (List)session.getAttribute("model." + SkatExportConstants.RESOURCE_MODEL_KEY_CODE_116_TRANSPORTDOK_TYPE_R44_5_1_LIST);
		model.put(SkatExportConstants.RESOURCE_MODEL_KEY_CODE_116_TRANSPORTDOK_TYPE_R44_5_1_LIST, list);
		list = (List)session.getAttribute("model." + SkatExportConstants.RESOURCE_MODEL_KEY_CODE_117_UDGANGSTOLDSTED_LIST);
		model.put(SkatExportConstants.RESOURCE_MODEL_KEY_CODE_117_UDGANGSTOLDSTED_LIST, list);
		list = (List)session.getAttribute("model." + SkatExportConstants.RESOURCE_MODEL_KEY_CODE_119_EXPORTARTER_LIST);
		model.put(SkatExportConstants.RESOURCE_MODEL_KEY_CODE_119_EXPORTARTER_LIST, list);
		list = (List)session.getAttribute("model." + SkatExportConstants.RESOURCE_MODEL_KEY_CODE_122_ERKLAERINGER_YM_LIST);
		model.put(SkatExportConstants.RESOURCE_MODEL_KEY_CODE_122_ERKLAERINGER_YM_LIST, list);
		//
		list = (List)session.getAttribute("model." + SkatExportConstants.RESOURCE_MODEL_KEY_CODE_123_T_STATUS_LIST);
		model.put(SkatExportConstants.RESOURCE_MODEL_KEY_CODE_123_T_STATUS_LIST, list);
		list = (List)session.getAttribute("model." + SkatExportConstants.RESOURCE_MODEL_KEY_CODE_124_SUPPL_ENHEDER_YM_LIST);
		model.put(SkatExportConstants.RESOURCE_MODEL_KEY_CODE_124_SUPPL_ENHEDER_YM_LIST, list);
		list = (List)session.getAttribute("model." + SkatExportConstants.RESOURCE_MODEL_KEY_CODE_126_EU_ANGIVELSESARTER_LIST);
		model.put(SkatExportConstants.RESOURCE_MODEL_KEY_CODE_126_EU_ANGIVELSESARTER_LIST, list);
		list = (List)session.getAttribute("model." + SkatExportConstants.RESOURCE_MODEL_KEY_CODE_127_STATUS_KODER_LIST);
		model.put(SkatExportConstants.RESOURCE_MODEL_KEY_CODE_127_STATUS_KODER_LIST, list);
		
		
	}
	
}
