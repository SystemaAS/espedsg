	/**
 * 
 */
package no.systema.tror.util.manager;

import java.util.*;

import org.apache.log4j.Logger;

import no.systema.main.model.SystemaWebUser;
import no.systema.main.service.UrlCgiProxyService;
import no.systema.transportdisp.model.jsonjackson.workflow.order.invoice.childwindow.JsonTransportDispGebyrCodeContainer;
import no.systema.transportdisp.model.jsonjackson.workflow.order.invoice.childwindow.JsonTransportDispGebyrCodeRecord;
import no.systema.transportdisp.service.html.dropdown.TransportDispDropDownListPopulationService;
import no.systema.transportdisp.url.store.TransportDispUrlDataStore;
import no.systema.transportdisp.util.TransportDispConstants;
//eBooking
import no.systema.tror.url.store.TrorUrlDataStore;
import no.systema.tror.util.TrorConstants;
import no.systema.tvinn.sad.z.maintenance.sadimport.model.jsonjackson.dbtable.gyldigekoder.JsonMaintSadImportKodts4Container;
import no.systema.tvinn.sad.z.maintenance.sadimport.model.jsonjackson.dbtable.gyldigekoder.JsonMaintSadImportKodts4Record;
import no.systema.tvinn.sad.z.maintenance.sadimport.url.store.TvinnSadMaintenanceImportUrlDataStoreGyldigeKoder;
import no.systema.tror.model.jsonjackson.codes.JsonTrorCodeContainer;
import no.systema.tror.model.jsonjackson.codes.JsonTrorCodeRecord;
import no.systema.tror.model.jsonjackson.codes.JsonTrorCountryCodeContainer;
import no.systema.tror.model.jsonjackson.codes.JsonTrorCountryCodeRecord;
import no.systema.tror.model.jsonjackson.codes.JsonTrorCurrencyCodeContainer;
import no.systema.tror.model.jsonjackson.codes.JsonTrorCurrencyCodeRecord;
import no.systema.tror.model.jsonjackson.codes.JsonTrorOppdragsTypeCodeContainer;
import no.systema.tror.model.jsonjackson.codes.JsonTrorOppdragsTypeCodeRecord;
import no.systema.tror.model.jsonjackson.codes.JsonTrorIncotermsCodeContainer;
import no.systema.tror.model.jsonjackson.codes.JsonTrorIncotermsCodeRecord;
import no.systema.tror.model.jsonjackson.codes.JsonTrorProductCodeContainer;
import no.systema.tror.model.jsonjackson.codes.JsonTrorProductCodeRecord;
import no.systema.tror.model.jsonjackson.codes.JsonTrorEnhetCodeContainer;
import no.systema.tror.model.jsonjackson.codes.JsonTrorEnhetCodeRecord;
import no.systema.tror.model.jsonjackson.codes.JsonTrorSignatureCodeContainer;
import no.systema.tror.model.jsonjackson.codes.JsonTrorSignatureCodeRecord;

import no.systema.tror.service.html.dropdown.TrorDropDownListPopulationService;
import no.systema.z.main.maintenance.model.jsonjackson.dbtable.JsonMaintMainKodtaContainer;
import no.systema.z.main.maintenance.model.jsonjackson.dbtable.JsonMaintMainKodtaRecord;
import no.systema.z.main.maintenance.service.MaintMainKodtaService;
import no.systema.z.main.maintenance.url.store.MaintenanceMainUrlDataStore;
import no.systema.tvinn.sad.z.maintenance.sadimport.service.gyldigekoder.MaintSadImportKodts4Service;

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
	public static final String CODE_TYPE_MLAPKOD = "MLAPKOD";
	public static final String CODE_TYPE_TRACKT = "TRACKT";
	
	
	/**
	 * 
	 * @param urlCgiProxyService
	 * @param skatDropDownListPopulationService
	 * @param model
	 * @param appUser
	 * @param paramTYP
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
				
			}else if(this.CODE_TYPE_MLAPKOD.equalsIgnoreCase(paramTYP)){
				model.put(TrorConstants.RESOURCE_MODEL_KEY_MLAPKOD_CODE_LIST, list);
			}else{
				model.put(TrorConstants.RESOURCE_MODEL_KEY_GENERIC_CODE_LIST, list);
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
	public void populateCodesHtmlDropDownsFromJsonCountry(UrlCgiProxyService urlCgiProxyService, TrorDropDownListPopulationService listPopulationService,
			Map model, SystemaWebUser appUser){
		//fill in html lists here
		try{
		
			String CODES_URL = TrorUrlDataStore.TROR_COUNTRY_CODES_URL;
			StringBuffer urlRequestParamsKeys = new StringBuffer();
			urlRequestParamsKeys.append("user=" + appUser.getUser());
			
			//Now build the payload and send to the back end via the drop down service
			//logger.info("CODES_URL:" + CODES_URL);
			//logger.info("CODES PARAMS:" + urlRequestParamsKeys.toString());
			String utfPayload = urlCgiProxyService.getJsonContent(CODES_URL, urlRequestParamsKeys.toString());
			//debug
			//logger.info(utfPayload);
			JsonTrorCountryCodeContainer codeContainer = listPopulationService.getCountryContainer(utfPayload);
			List<JsonTrorCountryCodeRecord> list = new ArrayList<JsonTrorCountryCodeRecord>();
			
			//Take some exception into consideration here or run the default to populate the final list
			for(JsonTrorCountryCodeRecord codeRecord: codeContainer.getDtoList()){
				//default
				list.add(codeRecord);
				//logger.info(codeRecord.getKlklk());
			}
			
			model.put(TrorConstants.RESOURCE_MODEL_KEY_COUNTRY_CODE_LIST, list);
			
		
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
	public void populateCodesHtmlDropDownsFromJsonCurrency(UrlCgiProxyService urlCgiProxyService, TrorDropDownListPopulationService listPopulationService,
			Map model, SystemaWebUser appUser){
		//fill in html lists here
		try{
		
			String CODES_URL = TrorUrlDataStore.TROR_CURRENCY_CODES_URL;
			StringBuffer urlRequestParamsKeys = new StringBuffer();
			urlRequestParamsKeys.append("user=" + appUser.getUser());
			
			//Now build the payload and send to the back end via the drop down service
			//logger.info("CODES_URL:" + CODES_URL);
			//logger.info("CODES PARAMS:" + urlRequestParamsKeys.toString());
			String utfPayload = urlCgiProxyService.getJsonContent(CODES_URL, urlRequestParamsKeys.toString());
			//debug
			//logger.info(utfPayload);
			JsonTrorCurrencyCodeContainer codeContainer = listPopulationService.getCurrencyContainer(utfPayload);
			List<JsonTrorCurrencyCodeRecord> list = new ArrayList();
			
			//Take some exception into consideration here or run the default to populate the final list
			for(JsonTrorCurrencyCodeRecord codeRecord: codeContainer.getDtoList()){
				//default
				list.add(codeRecord);
			}
			
			model.put(TrorConstants.RESOURCE_MODEL_KEY_CURRENCY_CODE_LIST, list);
			
		
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
	public void populateCodesHtmlDropDownsFromJsonRateClass(UrlCgiProxyService urlCgiProxyService, TrorDropDownListPopulationService listPopulationService,
			Map model, SystemaWebUser appUser){
		//fill in html lists here
		try{
			/*
			String CODES_URL = TrorUrlDataStore.TROR_TODO_RATECLASS_CODES_URL;
			StringBuffer urlRequestParamsKeys = new StringBuffer();
			urlRequestParamsKeys.append("user=" + appUser.getUser());
			
			//Now build the payload and send to the back end via the drop down service
			//logger.info("CODES_URL:" + CODES_URL);
			//logger.info("CODES PARAMS:" + urlRequestParamsKeys.toString());
			String utfPayload = urlCgiProxyService.getJsonContent(CODES_URL, urlRequestParamsKeys.toString());
			//debug
			//logger.info(utfPayload);
			JsonTrorCurrencyCodeContainer codeContainer = listPopulationService.getCurrencyContainer(utfPayload);
			List<JsonTrorCurrencyCodeRecord> list = new ArrayList();
			
			//Take some exception into consideration here or run the default to populate the final list
			for(JsonTrorCurrencyCodeRecord codeRecord: codeContainer.getDtoList()){
				//default
				list.add(codeRecord);
			}
			*/
			List<String> list = new ArrayList();
			list.add("B");list.add("C");list.add("E");
			list.add("K");list.add("M");list.add("N");
			list.add("Q");list.add("R");list.add("S");
			list.add("U");list.add("X");list.add("Y");
			
			model.put(TrorConstants.RESOURCE_MODEL_KEY_RATE_CLASS_CODE_LIST, list);
			
		
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
	
	public void populateCodesHtmlDropDownsFromJsonOppdragsType(UrlCgiProxyService urlCgiProxyService, TrorDropDownListPopulationService listPopulationService,
			Map model, SystemaWebUser appUser){
		//fill in html lists here
				try{
				
					String CODES_URL = TrorUrlDataStore.TROR_OPPDRAGSTYPE_CODES_URL;
					StringBuffer urlRequestParamsKeys = new StringBuffer();
					urlRequestParamsKeys.append("user=" + appUser.getUser());
					
					//Now build the payload and send to the back end via the drop down service
					//logger.info("CODES_URL:" + CODES_URL);
					//logger.info("CODES PARAMS:" + urlRequestParamsKeys.toString());
					String utfPayload = urlCgiProxyService.getJsonContent(CODES_URL, urlRequestParamsKeys.toString());
					//debug
					//logger.info(utfPayload);
					JsonTrorOppdragsTypeCodeContainer codeContainer = listPopulationService.getOppdragsTypeContainer(utfPayload);
					List<JsonTrorOppdragsTypeCodeRecord> list = new ArrayList();
					
					//Take some exception into consideration here or run the default to populate the final list
					for(JsonTrorOppdragsTypeCodeRecord codeRecord: codeContainer.getDtoList()){
						//default
						list.add(codeRecord);
					}
					
					model.put(TrorConstants.RESOURCE_MODEL_KEY_OPPDRAGSTYPE_LIST, list);
					
				
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
		public void populateCodesHtmlDropDownsFromJsonIncoterms(UrlCgiProxyService urlCgiProxyService, TrorDropDownListPopulationService listPopulationService,
				Map model, SystemaWebUser appUser){
				//fill in html lists here
				try{
				
					String CODES_URL = TrorUrlDataStore.TROR_INCOTERMS_CODES_URL;
					StringBuffer urlRequestParamsKeys = new StringBuffer();
					urlRequestParamsKeys.append("user=" + appUser.getUser());
					
					//Now build the payload and send to the back end via the drop down service
					//logger.info("CODES_URL:" + CODES_URL);
					//logger.info("CODES PARAMS:" + urlRequestParamsKeys.toString());
					String utfPayload = urlCgiProxyService.getJsonContent(CODES_URL, urlRequestParamsKeys.toString());
					//debug
					//logger.info(utfPayload);
					JsonTrorIncotermsCodeContainer codeContainer = listPopulationService.getIncotermsContainer(utfPayload);
					List<JsonTrorIncotermsCodeRecord> list = new ArrayList();
					
					//Take some exception into consideration here or run the default to populate the final list
					for(JsonTrorIncotermsCodeRecord codeRecord: codeContainer.getDtoList()){
						//default
						list.add(codeRecord);
					}
					
					model.put(TrorConstants.RESOURCE_MODEL_KEY_INCOTERMS_LIST, list);
					
				
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
		public void populateCodesHtmlDropDownsFromJsonProduct(UrlCgiProxyService urlCgiProxyService, TrorDropDownListPopulationService listPopulationService,
				Map model, SystemaWebUser appUser){
				//fill in html lists here
				try{
				
					String CODES_URL = TrorUrlDataStore.TROR_PRODUCT_CODES_URL;
					StringBuffer urlRequestParamsKeys = new StringBuffer();
					urlRequestParamsKeys.append("user=" + appUser.getUser());
					urlRequestParamsKeys.append("&kftyp=PRODTYPE");
					
					//Now build the payload and send to the back end via the drop down service
					//logger.info("CODES_URL:" + CODES_URL);
					//logger.info("CODES PARAMS:" + urlRequestParamsKeys.toString());
					String utfPayload = urlCgiProxyService.getJsonContent(CODES_URL, urlRequestParamsKeys.toString());
					//debug
					//logger.info(utfPayload);
					JsonTrorProductCodeContainer codeContainer = listPopulationService.getProductContainer(utfPayload);
					List<JsonTrorProductCodeRecord> list = new ArrayList();
					
					//Take some exception into consideration here or run the default to populate the final list
					for(JsonTrorProductCodeRecord codeRecord: codeContainer.getDtoList()){
						//default
						list.add(codeRecord);
						//logger.info("PROD:" + codeRecord.getKfkod	());
					}
					
					model.put(TrorConstants.RESOURCE_MODEL_KEY_PRODUCT_CODE_LIST, list);
					
				
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
		public void populateCodesHtmlDropDownsFromJsonProductLandimporFraktbrev(UrlCgiProxyService urlCgiProxyService, TrorDropDownListPopulationService listPopulationService,
				Map model, SystemaWebUser appUser){
				//fill in html lists here
				try{
				
					String CODES_URL = TrorUrlDataStore.TROR_GENERAL_CODES_URL;
					StringBuffer urlRequestParamsKeys = new StringBuffer();
					urlRequestParamsKeys.append("user=" + appUser.getUser());
					urlRequestParamsKeys.append("&kftyp=FRBREVPRO");
					
					//Now build the payload and send to the back end via the drop down service
					//logger.info("CODES_URL:" + CODES_URL);
					//logger.info("CODES PARAMS:" + urlRequestParamsKeys.toString());
					String utfPayload = urlCgiProxyService.getJsonContent(CODES_URL, urlRequestParamsKeys.toString());
					//debug
					//logger.info(utfPayload);
					JsonTrorProductCodeContainer codeContainer = listPopulationService.getProductContainer(utfPayload);
					List<JsonTrorProductCodeRecord> list = new ArrayList();
					
					//Take some exception into consideration here or run the default to populate the final list
					for(JsonTrorProductCodeRecord codeRecord: codeContainer.getList()){
						//default
						list.add(codeRecord);
						//logger.info("PROD:" + codeRecord.getKfkod	());
					}
					
					model.put(TrorConstants.RESOURCE_MODEL_KEY_PRODUCT_CODE_LIST, list);
					
				
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
		public void populateCodesHtmlDropDownsFromJsonEnhet(UrlCgiProxyService urlCgiProxyService, TrorDropDownListPopulationService listPopulationService,
				Map model, SystemaWebUser appUser){
				//fill in html lists here
				try{
				
					String CODES_URL = TrorUrlDataStore.TROR_ENHET_CODES_URL;
					StringBuffer urlRequestParamsKeys = new StringBuffer();
					urlRequestParamsKeys.append("user=" + appUser.getUser());
					urlRequestParamsKeys.append("&tkunik=017");
					
					//Now build the payload and send to the back end via the drop down service
					//logger.info("CODES_URL:" + CODES_URL);
					//logger.info("CODES PARAMS:" + urlRequestParamsKeys.toString());
					String utfPayload = urlCgiProxyService.getJsonContent(CODES_URL, urlRequestParamsKeys.toString());
					//debug
					//logger.info(utfPayload);
					JsonTrorEnhetCodeContainer codeContainer = listPopulationService.getEnhetContainer(utfPayload);
					List<JsonTrorEnhetCodeRecord> list = new ArrayList();
					
					//Take some exception into consideration here or run the default to populate the final list
					for(JsonTrorEnhetCodeRecord codeRecord: codeContainer.getList()){
						//default
						list.add(codeRecord);
						//logger.info(codeRecord.getTkkode());
					}
					
					model.put(TrorConstants.RESOURCE_MODEL_KEY_ENHET_CODE_LIST, list);
					
				
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
		public void populateCodesHtmlDropDownsFromJsonSignature(UrlCgiProxyService urlCgiProxyService, TrorDropDownListPopulationService listPopulationService,
				Map model, SystemaWebUser appUser){
				//fill in html lists here
				try{
				
					String CODES_URL = TrorUrlDataStore.TROR_SIGNATURES_URL;
					StringBuffer urlRequestParamsKeys = new StringBuffer();
					urlRequestParamsKeys.append("user=" + appUser.getUser());
					
					//Now build the payload and send to the back end via the drop down service
					//logger.info("CODES_URL:" + CODES_URL);
					//logger.info("CODES PARAMS:" + urlRequestParamsKeys.toString());
					String utfPayload = urlCgiProxyService.getJsonContent(CODES_URL, urlRequestParamsKeys.toString());
					//debug
					//logger.info(utfPayload);
					JsonTrorSignatureCodeContainer codeContainer = listPopulationService.getSignatureContainer(utfPayload);
					List<JsonTrorSignatureCodeRecord> list = new ArrayList();
					
					//Take some exception into consideration here or run the default to populate the final list
					for(JsonTrorSignatureCodeRecord codeRecord: codeContainer.getDtoList()){
						//default
						list.add(codeRecord);
						//logger.info(codeRecord.getTkkode());
					}
					
					model.put(TrorConstants.RESOURCE_MODEL_KEY_SIGNATURES_LIST, list);
					
				
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
		public void populateCodesHtmlDropDownsFromJsonAvdelning(UrlCgiProxyService urlCgiProxyService, MaintMainKodtaService specialListPopulationService,
				Map model, SystemaWebUser appUser){
				//fill in html lists here
				try{
				
					String BASE_URL = MaintenanceMainUrlDataStore.MAINTENANCE_MAIN_BASE_SYFA14R_GET_LIST_URL;
					String urlRequestParams = "user=" + appUser.getUser();
					logger.info(Calendar.getInstance().getTime() + " CGI-start timestamp");
			    	logger.info("URL: " + BASE_URL);
			    	logger.info("URL PARAMS: " + urlRequestParams);
			    	String jsonPayload = urlCgiProxyService.getJsonContent(BASE_URL, urlRequestParams);
			    	//extract
			    	List<JsonMaintMainKodtaRecord> list = new ArrayList();
			    	if(jsonPayload!=null){
						//lists
			    		JsonMaintMainKodtaContainer container = specialListPopulationService.getList(jsonPayload);
				        if(container!=null){
				        	list = (List)container.getList();
				        }
			    	}
			    	
					
					model.put(TrorConstants.RESOURCE_MODEL_KEY_AVD_LIST, list);
					
				
				}catch(Exception e){
					e.printStackTrace();
				}
		}

		/**
		 * 
		 * @param urlCgiProxyService
		 * @param specialListPopulationService
		 * @param model
		 * @param appUser
		 */
		public void populateCodesHtmlDropDownsFromJsonTransporttype(UrlCgiProxyService urlCgiProxyService, MaintSadImportKodts4Service specialListPopulationService,
				Map model, SystemaWebUser appUser){
		String BASE_URL = TvinnSadMaintenanceImportUrlDataStoreGyldigeKoder.TVINN_SAD_MAINTENANCE_IMPORT_BASE_SAD002_KODTS4R_GET_LIST_URL;
		StringBuffer urlRequestParams = new StringBuffer();
		urlRequestParams.append("user="+ appUser.getUser());
		
		logger.info(Calendar.getInstance().getTime() + " CGI-start timestamp");
    	logger.info("URL: " + BASE_URL);
    	logger.info("URL PARAMS: " + urlRequestParams);
    	String jsonPayload = urlCgiProxyService.getJsonContent(BASE_URL, urlRequestParams.toString());
    	//extract
    	List<JsonMaintSadImportKodts4Record> list = new ArrayList();
    	if(jsonPayload!=null){
			//lists
    		JsonMaintSadImportKodts4Container container = specialListPopulationService.getList(jsonPayload);
	        if(container!=null){
	        	list = (List)container.getList();
	        }
    	}
    	model.put(TrorConstants.RESOURCE_MODEL_KEY_TRANSPORTTYPE_CODE_LIST, list);
    	
	}
		
		/**
		 * 
		 * @param urlCgiProxyService
		 * @param listPopulationService
		 * @param model
		 * @param appUser
		 */
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
		
		
		
}
