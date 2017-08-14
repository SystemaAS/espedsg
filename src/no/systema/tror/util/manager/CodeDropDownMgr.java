	/**
 * 
 */
package no.systema.tror.util.manager;

import java.util.*;

import org.apache.log4j.Logger;

import no.systema.main.model.SystemaWebUser;
import no.systema.main.service.UrlCgiProxyService;
//eBooking
import no.systema.tror.url.store.TrorUrlDataStore;
import no.systema.tror.util.TrorConstants;
import no.systema.tror.model.jsonjackson.codes.JsonTrorCodeContainer;
import no.systema.tror.model.jsonjackson.codes.JsonTrorCodeRecord;
import no.systema.tror.service.html.dropdown.TrorDropDownListPopulationService;
/*
import no.systema.ebooking.model.jsonjackson.codes.JsonEbookingFrankaturContainer;
import no.systema.ebooking.model.jsonjackson.codes.JsonEbookingFrankaturRecord;
import no.systema.ebooking.model.jsonjackson.codes.JsonEbookingOppdragTypeContainer;
import no.systema.ebooking.model.jsonjackson.codes.JsonEbookingOppdragTypeRecord;
import no.systema.ebooking.service.html.dropdown.EbookingDropDownListPopulationService;
*/


/**
 * The class handles general gui drop downs aspect population for Work with Trips - Transport Disponering
 *
 * This Manager is not instantiated by the Spring Container at start up. 
 * Instead, it is instantiated by a controller when needed.
 * 
 * 
 * @author oscardelatorre
 * @date Aug 11, 2017
 *                    
 */

public class CodeDropDownMgr {
	private static final Logger logger = Logger.getLogger(CodeDropDownMgr.class.getName());
	//Codes
	public static final String CODE_TYPE_DELSYSTEM = "DELSYS";
	
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
	public void populateCodesHtmlDropDownsFromJsonString(UrlCgiProxyService urlCgiProxyService, TrorDropDownListPopulationService listPopulationService,
														Map model, SystemaWebUser appUser, String paramTYP){
		//fill in html lists here
		try{
			
			String CODES_URL = TrorUrlDataStore.TROR_GENERAL_CODES_URL;
			StringBuffer urlRequestParamsKeys = new StringBuffer();
			urlRequestParamsKeys.append("user=" + appUser.getUser());
			urlRequestParamsKeys.append(TrorConstants.URL_CHAR_DELIMETER_FOR_PARAMS_WITH_HTML_REQUEST + "kftyp=" + paramTYP);
			
			//Now build the payload and send to the back end via the drop down service
			//logger.info("CODES_URL:" + CODES_URL);
			//logger.info("CODES PARAMS:" + urlRequestParamsKeys.toString());
			String utfPayload = urlCgiProxyService.getJsonContent(CODES_URL, urlRequestParamsKeys.toString());
			//debug
			//logger.info(utfPayload);
			JsonTrorCodeContainer codeContainer = listPopulationService.getContainer(utfPayload);
			List<JsonTrorCodeRecord> list = new ArrayList();
			
			//Take some exception into consideration here or run the default to populate the final list
			for(JsonTrorCodeRecord codeRecord: codeContainer.getList()){
				//default
				list.add(codeRecord);
				//logger.info("CODE_RECORD: " + codeRecord.getZtxt());
			}
			
			if(this.CODE_TYPE_DELSYSTEM.equalsIgnoreCase(paramTYP)){
				model.put(TrorConstants.RESOURCE_MODEL_KEY_DELSYSTEM_CODE_LIST, list);
				
			}
			
		}catch(Exception e){
			e.printStackTrace();
		}
		
	}
	/**
	 * 
	 * @param urlCgiProxyService
	 * @param listPopulationService
	 * @param model
	 * @param appUser
	 */
	/*
	public void populateHtmlDropDownsFromJsonStringFrankatur(UrlCgiProxyService urlCgiProxyService, EbookingDropDownListPopulationService listPopulationService,
		Map model, SystemaWebUser appUser){
		//fill in html lists here
		try{
			String URL = EbookingUrlDataStore.EBOOKING_GENERAL_FRANKATUR_INCOTERMS_URL;
			StringBuffer urlRequestParamsKeys = new StringBuffer();
			urlRequestParamsKeys.append("user=" + appUser.getUser());
			
			//Now build the payload and send to the back end via the drop down service
			//logger.info("URL:" + URL);
			String utfPayload = urlCgiProxyService.getJsonContent(URL, urlRequestParamsKeys.toString());
			//logger.info(utfPayload);
			JsonEbookingFrankaturContainer frankaturContainer = listPopulationService.getFrankaturContainer(utfPayload);
			
			//Take some exception into consideration here or run the default to populate the final list
			for(JsonEbookingFrankaturRecord record: frankaturContainer.getFrankaturer()){
				//logger.info("FRANKATUR RECORD: " + record.getFranka());
			}
			model.put(EbookingConstants.RESOURCE_MODEL_KEY_INCOTERMS_LIST,frankaturContainer.getFrankaturer());
			
		}catch(Exception e){
			e.printStackTrace();
		}
			
	}	
	*/
	/**
	 * 
	 * @param urlCgiProxyService
	 * @param listPopulationService
	 * @param model
	 * @param appUser
	 */
	/*
	public void populateHtmlDropDownsFromJsonStringOppdragsType(UrlCgiProxyService urlCgiProxyService, EbookingDropDownListPopulationService listPopulationService,
			Map model, SystemaWebUser appUser){
			//fill in html lists here
			try{
				String URL = EbookingUrlDataStore.EBOOKING_GENERAL_OPPDRAGSTYPE_URL;
				StringBuffer urlRequestParamsKeys = new StringBuffer();
				urlRequestParamsKeys.append("user=" + appUser.getUser());
				
				//Now build the payload and send to the back end via the drop down service
				//logger.info("URL:" + URL);
				String utfPayload = urlCgiProxyService.getJsonContent(URL, urlRequestParamsKeys.toString());
				//logger.info(utfPayload);
				JsonEbookingOppdragTypeContainer container = listPopulationService.getOppdragTypeContainer(utfPayload);
				
				//Take some exception into consideration here or run the default to populate the final list
				for(JsonEbookingOppdragTypeRecord record: container.getOppdragsTyper()){
					//logger.info("FRANKATUR RECORD: " + record.getFranka());
				}
				model.put(EbookingConstants.RESOURCE_MODEL_KEY_OPPDRAGSTYPE_LIST, container.getOppdragsTyper());
				
			}catch(Exception e){
				e.printStackTrace();
			}
				
		}	
		
		/**
		 * 
		 * @param urlCgiProxyService
		 * @param listPopulationService
		 * @param model
		 * @param appUser
		 * @param paramsMap
		 */
	/*
		public void populateHtmlDropDownsFromJsonStringGebyrCodes(UrlCgiProxyService urlCgiProxyService, TransportDispDropDownListPopulationService listPopulationService,
			Map model, SystemaWebUser appUser){
			//fill in html lists here
			try{
				String URL = TransportDispUrlDataStore.TRANSPORT_DISP_BASE_CHILDWINDOW_GEBYR_CODES_URL;
				StringBuffer urlRequestParamsKeys = new StringBuffer();
				urlRequestParamsKeys.append("user=" + appUser.getUser() + "&fullinfo=N");
				
				//Now build the payload and send to the back end via the drop down service
				logger.info("URL:" + URL);
				logger.info("PARAMS:" + urlRequestParamsKeys.toString());
				
				String utfPayload = urlCgiProxyService.getJsonContent(URL, urlRequestParamsKeys.toString());
				//logger.info(utfPayload);
				JsonTransportDispGebyrCodeContainer container = listPopulationService.getGebyrCodeContainer(utfPayload);
				
				//Take some exception into consideration here or run the default to populate the final list
				for(JsonTransportDispGebyrCodeRecord record: container.getGebyrKoder()){
					//logger.info("GEBYR RECORD: " + record.getKgekod());
				}
				model.put(TransportDispConstants.RESOURCE_MODEL_KEY_GEBYRCODES_LIST, container.getGebyrKoder());
				
			}catch(Exception e){
				e.printStackTrace();
			}
				
		}	
	
	*/
}
