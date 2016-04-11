package no.systema.skat.skatexport.controller;

import java.util.*;


import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Required;
import org.springframework.context.annotation.Scope;

import javax.annotation.PostConstruct;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;


import org.springframework.web.bind.ServletRequestDataBinder;
import org.springframework.web.bind.WebDataBinder;
//import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;

//import no.systema.tds.service.MainHdTopicService;
import no.systema.main.service.UrlCgiProxyService;
import no.systema.main.service.UrlCgiProxyServiceImpl;
import no.systema.main.context.TdsServletContext;
import no.systema.main.model.SystemaWebUser;
import no.systema.main.util.AppConstants;
import no.systema.main.util.JsonDebugger;

import no.systema.skat.service.html.dropdown.SkatDropDownListPopulationService;
import no.systema.skat.util.SkatConstants;
import no.systema.skat.service.SkatTaricVarukodService;
import no.systema.skat.skatexport.util.manager.CodeDropDownMgr;
import no.systema.skat.skatexport.service.SkatExportSpecificTopicService;
import no.systema.skat.skatexport.mapper.url.request.UrlRequestParameterMapper;
import no.systema.skat.skatexport.url.store.SkatExportUrlDataStore;
import no.systema.skat.skatexport.util.RpgReturnResponseHandler;
import no.systema.skat.skatexport.model.jsonjackson.topic.JsonSkatExportTopicInvoiceContainer;
import no.systema.skat.skatexport.model.jsonjackson.topic.JsonSkatExportTopicInvoiceRecord;
import no.systema.skat.skatexport.model.jsonjackson.topic.JsonSkatExportSpecificTopicRecord;
import no.systema.skat.skatexport.util.SkatExportCalculator;
import no.systema.skat.skatexport.service.html.dropdown.SkatExportDropDownListPopulationService;
import no.systema.skat.skatexport.validator.SkatExportInvoiceValidator;
import no.systema.skat.skatexport.model.jsonjackson.topic.JsonSkatExportSpecificTopicFaktTotalContainer;
import no.systema.skat.skatexport.model.jsonjackson.topic.JsonSkatExportSpecificTopicFaktTotalRecord;

/**
 * SKAT Export create invoice gateway
 * 
 * @author oscardelatorre
 * @date Apr 6, 2016
 * 
 */
@Controller
//@SessionAttributes(AppConstants.SYSTEMA_WEB_USER_KEY)
@Scope("session")
public class SkatExportHeaderInvoiceController {
	private static final JsonDebugger jsonDebugger = new JsonDebugger();
	private static final Logger logger = Logger.getLogger(SkatExportHeaderInvoiceController.class.getName());
	private UrlRequestParameterMapper urlRequestParameterMapper = new UrlRequestParameterMapper();
	private SkatExportCalculator skatExportCalculator = new SkatExportCalculator();
	private CodeDropDownMgr codeDropDownMgr = new CodeDropDownMgr();
	
	private ModelAndView loginView = new ModelAndView("login");
	private final String NOT_FOUND = "NOT FOUND";
	private final String MATCH = "MATCH";
	
	
	@InitBinder
    protected void initBinder(WebDataBinder binder) {

    }
	
	@PostConstruct
	public void initIt() throws Exception {
		if("DEBUG".equals(AppConstants.LOG4J_LOGGER_LEVEL)){
			logger.setLevel(Level.DEBUG);
		}
	}	
	
	/**
	 * 
	 * @param user
	 * @param result
	 * @param request
	 * @return
	 */
	@RequestMapping(value="skatexport_edit_invoice.do",  method={RequestMethod.GET, RequestMethod.POST })
	public ModelAndView skatExportListEditInvoice(@ModelAttribute ("record") JsonSkatExportTopicInvoiceRecord recordToValidate, BindingResult bindingResult, HttpSession session, HttpServletRequest request){
		boolean bindingErrorsExist = false;
		logger.info("Inside: skatExportListEditInvoice");
		
		ModelAndView successView = new ModelAndView("skatexport_edit_invoice");
		RpgReturnResponseHandler rpgReturnResponseHandler = new RpgReturnResponseHandler();
		JsonSkatExportTopicInvoiceRecord jsonSkatExportTopicInvoiceRecord = recordToValidate;
		
		SystemaWebUser appUser = (SystemaWebUser)session.getAttribute(AppConstants.SYSTEMA_WEB_USER_KEY);
		
		Map model = new HashMap();
		String urlRequestParamsKeys = null;
		//Catch required action (doFetch or doUpdate)
		String action = request.getParameter("action");
		logger.info("ACTION: " + action);
		
		if(appUser==null){
			return this.loginView;
		}else{
			
			session.setAttribute(SkatConstants.ACTIVE_URL_RPG_SKAT, SkatConstants.ACTIVE_URL_RPG_INITVALUE);
			session.setAttribute(SkatConstants.ACTIVE_URL_RPG_UPDATE_SKAT, SkatConstants.ACTIVE_URL_RPG_INITVALUE);
			
			boolean isValidCreatedRecordTransactionOnRPG = true;
			//Keys and header information
			String opd = request.getParameter("opd");
			String avd = request.getParameter("avd");
			String sign = request.getParameter("sign");
			String status = request.getParameter("status");
			String datum = request.getParameter("datum");
			
			//this fragment gets some header fields needed for the validator
			JsonSkatExportSpecificTopicRecord headerRecord = (JsonSkatExportSpecificTopicRecord)session.getAttribute(SkatConstants.DOMAIN_RECORD_TOPIC_SKAT);
			String invoiceTotalAmount = headerRecord.getDkeh_222();
			
			
			//this key is only used with a real Update. When empty it will be a signal for a CREATE NEW (Add)
			if(recordToValidate.getIsModeUpdate()!=null && "true".equals(recordToValidate.getIsModeUpdate())){
				logger.info("[INFO isModeUdate] " + recordToValidate.getIsModeUpdate());
			}
			model.put("avd", avd);
			model.put("opd", opd);
			model.put("sign", sign);
			model.put("status", status);
			model.put("datum", datum);
			
			
			if(SkatConstants.ACTION_UPDATE.equals(action)){
				SkatExportInvoiceValidator validator = new SkatExportInvoiceValidator();
				logger.info("Host via HttpServletRequest.getHeader('Host'): " + request.getHeader("Host"));
			    validator.validate(recordToValidate, bindingResult);
			    //check for ERRORS
				if(bindingResult.hasErrors()){
				    	logger.info("[ERROR Validation] Record does not validate)");
				    	logger.info("[INFO faktnr] " + recordToValidate.getDkef_fatx());
				    	bindingErrorsExist = true;
				    	model.put("record", recordToValidate);
				    	
			    }else{
			    	if(recordToValidate.getIsModeUpdate()!=null && "true".equals(recordToValidate.getIsModeUpdate())){
						//-------
						//UPDATE
						//-------
						logger.info("UPDATE(only) ITEM (existent invoice) on process...");
					}else{
						//Minimum
						if(recordToValidate.getDkef_fatx()!=null && !"".equals(recordToValidate.getDkef_fatx())){
							//-------
							//CREATE
							//-------
							logger.info("CREATE and UPDATE on ITEM (new fresh invoice) on process...");
							//This means that the update will be done AFTER a creation of an empty record. All this in the same transaction. 2 STEPS involved: (1)create and (2)update
							//-----------------------------------------------------------------------------------------
							//STEP[1] Generate new Item line key seeds (avd,opd,sftxt) by creating an empty new record. 
							//		  This step is ONLY applicable for new item lines 
							//-------------------------------------------------------------------------------------------
							jsonSkatExportTopicInvoiceRecord  = this.createNewItemKeySeeds(recordToValidate, avd, opd, session, request, appUser);
							if(jsonSkatExportTopicInvoiceRecord!=null){
								String newId = jsonSkatExportTopicInvoiceRecord.getDkef_fatx();
								//take the rest from GUI.
								jsonSkatExportTopicInvoiceRecord = new JsonSkatExportTopicInvoiceRecord();
								ServletRequestDataBinder binder = new ServletRequestDataBinder(jsonSkatExportTopicInvoiceRecord);
					            //binder.registerCustomEditor(...); // if needed
					            binder.bind(request);
					            logger.info("[INFO] populate svef_fatx:" + newId);
							}else{
								isValidCreatedRecordTransactionOnRPG = false;
							}
						}else{
							isValidCreatedRecordTransactionOnRPG = false;
						}
					}
					//--------------------------------------------------
					//At this point we are ready to do an update
					//--------------------------------------------------
					if(isValidCreatedRecordTransactionOnRPG){
						logger.info("[INFO] Valid STEP[1] Add Record(if applicable) successfully created, OK ");
						//adjust after bind (both UPDATE or CREATE)
						this.adjustFieldsAfterBind(request, jsonSkatExportTopicInvoiceRecord);
						
						//---------------------------
						//get BASE URL = RPG-PROGRAM
			            //---------------------------
						String BASE_URL_UPDATE = SkatExportUrlDataStore.SKAT_EXPORT_BASE_UPDATE_SPECIFIC_TOPIC_INVOICE_URL;
						urlRequestParamsKeys = this.getRequestUrlKeyParametersUpdate(jsonSkatExportTopicInvoiceRecord.getDkef_fatx(), avd, opd, appUser, SkatConstants.MODE_UPDATE);
						String urlRequestParamsTopicItem = this.urlRequestParameterMapper.getUrlParameterValidString((jsonSkatExportTopicInvoiceRecord));
						//put the final valid param. string
						String urlRequestParams = urlRequestParamsKeys + urlRequestParamsTopicItem;
						//for debug purposes in GUI
						session.setAttribute(SkatConstants.ACTIVE_URL_RPG_UPDATE_SKAT, BASE_URL_UPDATE + "==>params: " + urlRequestParams.toString()); 
						
						logger.info(Calendar.getInstance().getTime() + " CGI-start timestamp");
				    	logger.info("URL: " + jsonDebugger.getBASE_URL_NoHostName(BASE_URL_UPDATE));
				    	logger.info("URL PARAMS: " + urlRequestParams);
				    	//----------------------------------------------------------------------------
				    	//EXECUTE the UPDATE (RPG program) here (STEP [2] when creating a new record)
				    	//----------------------------------------------------------------------------
						String rpgReturnPayload = this.urlCgiProxyService.getJsonContent(BASE_URL_UPDATE, urlRequestParams);
					    	
						//Debug --> 
				    	logger.info("Checking errMsg in rpgReturnPayload" + rpgReturnPayload);
				    	//we must evaluate a return RPG code in order to know if the Update was OK or not
				    	rpgReturnResponseHandler.evaluateRpgResponseOnTopicInvoiceCreateOrUpdate(rpgReturnPayload);
				    	if(rpgReturnResponseHandler.getErrorMessage()!=null && !"".equals(rpgReturnResponseHandler.getErrorMessage())){
				    		rpgReturnResponseHandler.setErrorMessage("[ERROR] FATAL on UPDATE: " + rpgReturnResponseHandler.getErrorMessage());
				    		this.setFatalError(model, rpgReturnResponseHandler, jsonSkatExportTopicInvoiceRecord);
				    		
				    	}else{
				    		//Update succefully done!
				    		logger.info("[INFO] Valid STEP[2] Update -- Record successfully updated, OK ");
				    		//put domain objects (it is not necessary since the fetch below (onFetch) will clean this up anyway)
				    	}
					}else{
						if(rpgReturnResponseHandler.getErrorMessage()!=null && !"".equals(rpgReturnResponseHandler.getErrorMessage()) ){
							rpgReturnResponseHandler.setErrorMessage("[ERROR] FATAL on CREATE, at tuid, syop generation : " + rpgReturnResponseHandler.getErrorMessage());
							this.setFatalError(model, rpgReturnResponseHandler, jsonSkatExportTopicInvoiceRecord);
						}
					}
			    }
				
			}else if(SkatConstants.ACTION_DELETE.equals(action)){

				logger.info("[INFO] Delete record start process... ");
				String lineIdToDelete = request.getParameter("fak");
				
				//---------------------------
				//get BASE URL = RPG-PROGRAM
	            //---------------------------
				String BASE_URL_DELETE = SkatExportUrlDataStore.SKAT_EXPORT_BASE_UPDATE_SPECIFIC_TOPIC_INVOICE_URL;
				String urlRequestParams = this.getRequestUrlKeyParametersUpdate(lineIdToDelete, avd, opd, appUser,SkatConstants.MODE_DELETE );
				
				logger.info(Calendar.getInstance().getTime() + " CGI-start timestamp");
		    	logger.info("URL: " + jsonDebugger.getBASE_URL_NoHostName(BASE_URL_DELETE));
		    	logger.info("URL PARAMS: " + urlRequestParams);
		    	//----------------------------------------------------------------------------
		    	//EXECUTE the UPDATE (RPG program) here (STEP [2] when creating a new record)
		    	//----------------------------------------------------------------------------
		    	String rpgReturnPayload = this.urlCgiProxyService.getJsonContent(BASE_URL_DELETE, urlRequestParams);
			    	
				//Debug --> 
		    	logger.info("Checking errMsg in rpgReturnPayload" + rpgReturnPayload);
		    	//we must evaluate a return RPG code in order to know if the Update was OK or not
		    	rpgReturnResponseHandler.evaluateRpgResponseOnTopicItemCreateOrUpdate(rpgReturnPayload);
		    	if(rpgReturnResponseHandler.getErrorMessage()!=null && !"".equals(rpgReturnResponseHandler.getErrorMessage())){
		    		rpgReturnResponseHandler.setErrorMessage("[ERROR] FATAL on UPDATE: " + rpgReturnResponseHandler.getErrorMessage());
		    		this.setFatalError(model, rpgReturnResponseHandler, jsonSkatExportTopicInvoiceRecord);
		    	}else{
		    		//Delete succefully done!
		    		logger.info("[INFO] Valid Delete -- Record successfully deleted, OK ");
		    	}
				
			}
			
			//FETCH the ITEM LIST of existent ITEMs for this TOPIC
			//---------------------------
			//get BASE URL = RPG-PROGRAM
            //---------------------------
			String BASE_URL_FETCH = SkatExportUrlDataStore.SKAT_EXPORT_BASE_FETCH_TOPIC_INVOICELIST_URL;
			urlRequestParamsKeys = this.getRequestUrlKeyParameters(request, avd, opd, appUser);
			
			logger.info(Calendar.getInstance().getTime() + " CGI-start timestamp");
			logger.info("FETCH av item list... ");
	    	logger.info("URL: " + jsonDebugger.getBASE_URL_NoHostName(BASE_URL_FETCH));
	    	logger.info("URL PARAMS: " + urlRequestParamsKeys);
	    	//--------------------------------------
	    	//EXECUTE the FETCH (RPG program) here
	    	//--------------------------------------
			String jsonPayloadFetch = this.urlCgiProxyService.getJsonContent(BASE_URL_FETCH, urlRequestParamsKeys);
			//for debug purposes in GUI
			session.setAttribute(SkatConstants.ACTIVE_URL_RPG_SKAT, BASE_URL_FETCH + "==>params: " + urlRequestParamsKeys.toString() + 
					" " + "(fetched list):" + jsonPayloadFetch); 
			
			//Debug --> 
	    	logger.debug(jsonDebugger.debugJsonPayloadWithLog4J(jsonPayloadFetch));
	    	logger.info(Calendar.getInstance().getTime() +  " CGI-end timestamp");
	    	JsonSkatExportTopicInvoiceContainer jsonSkatExportTopicInvoiceContainer = this.skatExportSpecificTopicService.getSkatExportTopicInvoiceContainerContainer(jsonPayloadFetch);
	    	if(jsonSkatExportTopicInvoiceContainer!=null){
	    		//get totals from AS400
	    		JsonSkatExportSpecificTopicFaktTotalRecord sumFaktTotalRecord = this.getInvoiceTotalFromInvoices(avd, opd, appUser);
	    		jsonSkatExportTopicInvoiceContainer.setCalculatedValidCurrency(sumFaktTotalRecord.getTot_vakd());
	    		jsonSkatExportTopicInvoiceContainer.setCalculatedItemLinesTotalAmount(sumFaktTotalRecord.getTot_fabl());
	    		logger.info("CalculatedItemLinesTotalAmount:" + jsonSkatExportTopicInvoiceContainer.getCalculatedItemLinesTotalAmount());
	    		
	    	} 
			
    		//after remove
	    	this.setCodeDropDownMgr(appUser, model);
    		this.setDomainObjectsForListInView(model, jsonSkatExportTopicInvoiceContainer);
			
    		
    		//this next step is necessary for the default values on "create new" record
    		if(bindingErrorsExist){
    			this.setDefaultDomainItemRecordInView(model, jsonSkatExportTopicInvoiceContainer, recordToValidate);
    		}else{
    			this.setDefaultDomainItemRecordInView(model, jsonSkatExportTopicInvoiceContainer, null);
    		}
    		
	    	successView.addObject(SkatConstants.DOMAIN_MODEL,model);
			//successView.addObject(Constants.EDIT_ACTION_ON_TOPIC, Constants.ACTION_FETCH);
	    	logger.info("END of method");
	    	return successView;
		}
		
	}
	
	/**
	 * 
	 * @param avd
	 * @param opd
	 * @param appUser
	 * @return
	 */
	private JsonSkatExportSpecificTopicFaktTotalRecord getInvoiceTotalFromInvoices( String avd, String opd, SystemaWebUser appUser){
		//--------------------------
		//get BASE URL = RPG-PROGRAM
        //---------------------------
		JsonSkatExportSpecificTopicFaktTotalRecord returnRecord = null;
		
		String BASE_URL_FETCH = SkatExportUrlDataStore.SKAT_EXPORT_BASE_FETCH_SPECIFIC_TOPIC_FAKT_TOTAL_URL;
		String urlRequestParamsKeys = "user=" + appUser.getUser() + "&avd=" + avd + "&opd=" + opd;
		
		logger.info(Calendar.getInstance().getTime() + " CGI-start timestamp");
		logger.info("FETCH av item list... ");
    	logger.info("URL: " + BASE_URL_FETCH);
    	logger.info("URL PARAMS: " + urlRequestParamsKeys);
    	//--------------------------------------
    	//EXECUTE the FETCH (RPG program) here
    	//--------------------------------------
		String jsonPayload = this.urlCgiProxyService.getJsonContent(BASE_URL_FETCH, urlRequestParamsKeys);
		//Debug --> 
		logger.debug(jsonDebugger.debugJsonPayloadWithLog4J(jsonPayload));
		
    	logger.info(Calendar.getInstance().getTime() +  " CGI-end timestamp");
    	JsonSkatExportSpecificTopicFaktTotalContainer container = this.skatExportSpecificTopicService.getSkatExportSpecificTopicFaktTotalContainer(jsonPayload);
    	if(container!=null){
	    	for(JsonSkatExportSpecificTopicFaktTotalRecord record : container.getInvTot()){
				 returnRecord = record;
	    	}
    	}
		
		return returnRecord;
	}
	
	/**
	 * Set aspects  objects
	 * @param model
	 * @param rpgReturnResponseHandler
	 */
	private void setAspectsInView (Map model, RpgReturnResponseHandler rpgReturnResponseHandler){
		model.put(SkatConstants.ASPECT_ERROR_MESSAGE, rpgReturnResponseHandler.getErrorMessage());
		//extra error information
		StringBuffer errorMetaInformation = new StringBuffer();
		errorMetaInformation.append(rpgReturnResponseHandler.getUser());
		//errorMetaInformation.append(rpgReturnResponseHandler.getOpd());
		model.put(SkatConstants.ASPECT_ERROR_META_INFO, errorMetaInformation);
		
	}
	/**
	 * Sets domain objects
	 * @param model
	 * @param container
	 * 
	 */
	private void setDomainObjectsForListInView(Map model, JsonSkatExportTopicInvoiceContainer container){
		List list = new ArrayList();
		if(container!=null){
			for (JsonSkatExportTopicInvoiceRecord record : container.getInvList()){
				//this.adjustDatesOnFetch(record);
				list.add(record);
			}
		}
		model.put(SkatConstants.DOMAIN_LIST, list);
		model.put(SkatConstants.DOMAIN_RECORD_ITEM_CONTAINER_INVOICE_TOPIC, container);
	}
	/**
	 * Sets domain objects
	 * @param model
	 * @param record
	 */
	private void setDomainObjectsInView(Map model, JsonSkatExportTopicInvoiceRecord record){
		//this.adjustDatesOnFetch(record);
		model.put(SkatConstants.DOMAIN_RECORD, record);
	}
	
	/**
	 * The method populates a default record (including original records before a validation error)
	 * The requirement here is to propose some predefined default values to the end-user.
	 *  
	 * @param model
	 * @param container
	 * @param recordToValidate
	 * 
	 */
	private void setDefaultDomainItemRecordInView(Map model, JsonSkatExportTopicInvoiceContainer container, JsonSkatExportTopicInvoiceRecord recordToValidate){
		List list = new ArrayList();
		JsonSkatExportTopicInvoiceRecord defaultRecord = new JsonSkatExportTopicInvoiceRecord();
		
		if(container!=null){
			
			//meaning that there were validation errors
			if(recordToValidate!=null){
				defaultRecord = recordToValidate;//in order to retain the original values before the validation errors
				model.put(SkatConstants.DOMAIN_RECORD, defaultRecord);
				
			}else{
				model.put(SkatConstants.DOMAIN_RECORD, defaultRecord);				
			}
		}
	}
	
	
	
	/**
	 * 
	 * @param model
	 * @param rpgReturnResponseHandler
	 * @param record
	 */
	private void setFatalError(Map model, RpgReturnResponseHandler rpgReturnResponseHandler, JsonSkatExportTopicInvoiceRecord record){
		logger.info(rpgReturnResponseHandler.getErrorMessage());
		this.setAspectsInView(model, rpgReturnResponseHandler);
		//No refresh on jsonRecord is done for the GUI (form fields). Must be implemented right here, if required. !!
        this.setDomainObjectsInView(model, record);
	}
	
	/**
	 * 
	 * @param jsonSkatExportTopicInvoiceRecord
	 * @param avd
	 * @param opd
	 * @param session
	 * @param request
	 * @param appUser
	 * @return
	 */
	private JsonSkatExportTopicInvoiceRecord createNewItemKeySeeds(JsonSkatExportTopicInvoiceRecord record, String avd, String opd, HttpSession session, HttpServletRequest request, SystemaWebUser appUser){
		RpgReturnResponseHandler rpgReturnResponseHandler = new RpgReturnResponseHandler();
		
		//---------------------------
		//get BASE URL = RPG-PROGRAM
        //---------------------------
		String BASE_URL = SkatExportUrlDataStore.SKAT_EXPORT_BASE_UPDATE_SPECIFIC_TOPIC_INVOICE_URL;
		
		//-------------------------------------------------------------------------------------------
		// STEP[PREPARE CREATION] --> generate new opd and tuid (if applicable) in order to be able to Add (Create)
		//-------------------------------------------------------------------------------------------
		logger.info("STEP[1] GET SEEDS and CREATE RECORD...");
		//logger.info("STEP[1] numberOfItemLinesInTopicStr: " + numberOfItemLinesInTopicStr);
		StringBuffer urlRequestParamsForSeed = new StringBuffer();
		urlRequestParamsForSeed.append("user=" + appUser.getUser());
		urlRequestParamsForSeed.append(SkatConstants.URL_CHAR_DELIMETER_FOR_PARAMS_WITH_HTML_REQUEST + "avd=" + avd);
		urlRequestParamsForSeed.append(SkatConstants.URL_CHAR_DELIMETER_FOR_PARAMS_WITH_HTML_REQUEST + "opd=" + opd);
		urlRequestParamsForSeed.append(SkatConstants.URL_CHAR_DELIMETER_FOR_PARAMS_WITH_HTML_REQUEST + "fak=" + record.getDkef_fatx());
		urlRequestParamsForSeed.append(SkatConstants.URL_CHAR_DELIMETER_FOR_PARAMS_WITH_HTML_REQUEST + "mode=" + SkatConstants.MODE_ADD);
		logger.info("URL for SEED: " + jsonDebugger.getBASE_URL_NoHostName(BASE_URL));
		logger.info("PARAMS for SEED: " + urlRequestParamsForSeed.toString());
		//for debug purposes in GUI
		session.setAttribute(SkatConstants.ACTIVE_URL_RPG_SKAT, BASE_URL + " ==>params: " + urlRequestParamsForSeed.toString() );
				
		//Get the counter from RPG (new opd Id)
		String rpgSeedNumberPayload = this.urlCgiProxyService.getJsonContent(BASE_URL, urlRequestParamsForSeed.toString());
		
		// Map the JSON response to the new seeds (syop,syli)
		// We are not using std JSON conversion since the RPGs strings are not the same. Should be the same as
		// the header fields. The RPG output should be changed in order to comply to the field specification...
		rpgReturnResponseHandler.evaluateRpgResponseOnTopicItemCreateOrUpdate(rpgSeedNumberPayload);
		
		//we must complete the GUI-json with the value from a line nr seed here
		if(rpgReturnResponseHandler.getErrorMessage()!=null && !"".equals(rpgReturnResponseHandler.getErrorMessage()) ){
			logger.info("[ERROR] No mandatory seeds were generated correctly)! look at std output log. [errMsg]" + rpgReturnResponseHandler.getErrorMessage());
			record = null;
		}
        
		return record;
	}
	/**
	 * 
	 * @param request
	 * @param avd
	 * @param opd
	 * @param appUser
	 * @return
	 */
	private String getRequestUrlKeyParameters(HttpServletRequest request, String avd, String opd, SystemaWebUser appUser){
		StringBuffer urlRequestParamsKeys = new StringBuffer();
		urlRequestParamsKeys.append("user=" + appUser.getUser());
		
		urlRequestParamsKeys.append(SkatConstants.URL_CHAR_DELIMETER_FOR_PARAMS_WITH_HTML_REQUEST + "avd=" + avd);
		urlRequestParamsKeys.append(SkatConstants.URL_CHAR_DELIMETER_FOR_PARAMS_WITH_HTML_REQUEST + "opd=" + opd);
		
		return urlRequestParamsKeys.toString();
	}
	/**
	 * 
	 * Parameters for a creation of a "next" item line
	 * 
	 * @param lineId
	 * @param avd
	 * @param opd
	 * @param appUser
	 * @return
	 */
	private String getRequestUrlKeyParametersUpdate(String invoiceId, String avd, String opd, SystemaWebUser appUser, String mode){
		StringBuffer urlRequestParamsKeys = new StringBuffer();
		
		urlRequestParamsKeys.append("user=" + appUser.getUser());
		urlRequestParamsKeys.append(SkatConstants.URL_CHAR_DELIMETER_FOR_PARAMS_WITH_HTML_REQUEST + "avd=" + avd);
		urlRequestParamsKeys.append(SkatConstants.URL_CHAR_DELIMETER_FOR_PARAMS_WITH_HTML_REQUEST + "opd=" + opd);
		urlRequestParamsKeys.append(SkatConstants.URL_CHAR_DELIMETER_FOR_PARAMS_WITH_HTML_REQUEST + "fak=" + invoiceId);
		urlRequestParamsKeys.append(SkatConstants.URL_CHAR_DELIMETER_FOR_PARAMS_WITH_HTML_REQUEST + "mode=" + mode);
		
		return urlRequestParamsKeys.toString();
	}
	
	/**
	 * We must adjust some fields that require it (presentation requirements)
	 * @param record
	 */
	/*
	private void adjustDatesOnFetch(JsonTdsImportTopicInvoiceRecord record){
		String dateSfdtNO = this.dateFormatter.convertToDate_NO(record.getSfdt());
		//fields
		record.setSfdt(dateSfdtNO);
	}
	*/
	/**
	 * 
	 * @param request
	 * @param record
	 */
	
	private void adjustFieldsAfterBind(HttpServletRequest request, JsonSkatExportTopicInvoiceRecord record){
		String factor = request.getParameter("factor");
		//fields
		record.setDkef_omr(factor);
	}
	
	/**
	 * 
	 * @param appUser
	 * @param model
	 */
	private void setCodeDropDownMgr(SystemaWebUser appUser, Map model){
		this.codeDropDownMgr.populateCodesHtmlDropDownsFromJsonString(this.urlCgiProxyService, this.skatDropDownListPopulationService,
				 model,appUser,CodeDropDownMgr.CODE_107_CURRENCY, null, null);
		
		/*this.codeDropDownMgr.populateCodesHtmlDropDownsFromJsonString(this.urlCgiProxyService, this.skatDropDownListPopulationService,
				 model,appUser,CodeDropDownMgr.CODE_008_COUNTRY, null, null);
	
		this.codeDropDownMgr.populateCodesHtmlDropDownsFromJsonString(this.urlCgiProxyService, this.skatDropDownListPopulationService,
				 model,appUser,CodeDropDownMgr.CODE_102_ANGIVELSESARTER, null, null);
		
		this.codeDropDownMgr.populateCodesHtmlDropDownsFromJsonString(this.urlCgiProxyService, this.skatDropDownListPopulationService,
				 model,appUser,CodeDropDownMgr.CODE_103_TOLDSTED, null, null);
		
		this.codeDropDownMgr.populateCodesHtmlDropDownsFromJsonString(this.urlCgiProxyService, this.skatDropDownListPopulationService,
				 model,appUser,CodeDropDownMgr.CODE_104_ANGIVELSESTYPE, null, null);
		
		this.codeDropDownMgr.populateCodesHtmlDropDownsFromJsonString(this.urlCgiProxyService, this.skatDropDownListPopulationService,
				 model,appUser,CodeDropDownMgr.CODE_106_INCOTERMS, null, null);
		
		
		this.codeDropDownMgr.populateCodesHtmlDropDownsFromJsonString(this.urlCgiProxyService, this.skatDropDownListPopulationService,
				 model,appUser,CodeDropDownMgr.CODE_108_TRANSPORTMADE, null, null);
		
		this.codeDropDownMgr.populateCodesHtmlDropDownsFromJsonString(this.urlCgiProxyService, this.skatDropDownListPopulationService,
				 model,appUser,CodeDropDownMgr.CODE_109_BETALNINGSMADE, null, null);
		
		this.codeDropDownMgr.populateCodesHtmlDropDownsFromJsonString(this.urlCgiProxyService, this.skatDropDownListPopulationService,
				 model,appUser,CodeDropDownMgr.CODE_117_UDGANGSTOLDSTED, null, null);
		
		this.codeDropDownMgr.populateCodesHtmlDropDownsFromJsonString(this.urlCgiProxyService, this.skatDropDownListPopulationService,
				 model,appUser,CodeDropDownMgr.CODE_119_EXPORTARTER, null, null);
		
		this.codeDropDownMgr.populateCodesHtmlDropDownsFromJsonString(this.urlCgiProxyService, this.skatDropDownListPopulationService,
				 model,appUser,CodeDropDownMgr.CODE_122_ERKLAERINGER_YM, null, null);
		
		this.codeDropDownMgr.populateCodesHtmlDropDownsFromJsonString(this.urlCgiProxyService, this.skatDropDownListPopulationService,
				 model,appUser,CodeDropDownMgr.CODE_123_T_STATUS, null, null);
		
		this.codeDropDownMgr.populateCodesHtmlDropDownsFromJsonString(this.urlCgiProxyService, this.skatDropDownListPopulationService,
				 model,appUser,CodeDropDownMgr.CODE_124_SUPPL_ENHEDER_YM, null, null);
		
		this.codeDropDownMgr.populateCodesHtmlDropDownsFromJsonString(this.urlCgiProxyService, this.skatDropDownListPopulationService,
				 model,appUser,CodeDropDownMgr.CODE_126_EU_ANGIVELSESARTER, null, null);
		
		this.codeDropDownMgr.populateCodesHtmlDropDownsFromJsonString(this.urlCgiProxyService, this.skatDropDownListPopulationService,
				 model,appUser,CodeDropDownMgr.CODE_127_STATUS_KODER, null, null);
		*/
}
	
	//SERVICES
	@Qualifier ("urlCgiProxyService")
	private UrlCgiProxyService urlCgiProxyService;
	@Autowired
	@Required
	public void setUrlCgiProxyService (UrlCgiProxyService value){ this.urlCgiProxyService = value; }
	public UrlCgiProxyService getUrlCgiProxyService(){ return this.urlCgiProxyService; }
	
	
	
	@Qualifier 
	private SkatTaricVarukodService skatTaricVarukodService;
	@Autowired
	@Required	
	public void setSkatTaricVarukodService(SkatTaricVarukodService value){this.skatTaricVarukodService = value;}
	public SkatTaricVarukodService getSkatTaricVarukodService(){ return this.skatTaricVarukodService; }
	
	
	/*
	@Qualifier ("dropDownListPopulationService")
	private DropDownListPopulationService dropDownListPopulationService;
	@Autowired
	public void setSadImportDropDownListPopulationService (DropDownListPopulationService value){ this.dropDownListPopulationService=value; }
	public DropDownListPopulationService getDropDownListPopulationService(){return this.dropDownListPopulationService;}
	*/
	
	@Qualifier ("skatDropDownListPopulationService")
	private SkatDropDownListPopulationService skatDropDownListPopulationService;
	@Autowired
	public void setSkatDropDownListPopulationService (SkatDropDownListPopulationService value){ this.skatDropDownListPopulationService=value; }
	public SkatDropDownListPopulationService getSkatDropDownListPopulationService(){return this.skatDropDownListPopulationService;}
	
	
	
	@Qualifier ("skatExportSpecificTopicService")
	private SkatExportSpecificTopicService skatExportSpecificTopicService;
	@Autowired
	@Required
	public void setSkatExportSpecificTopicService (SkatExportSpecificTopicService value){ this.skatExportSpecificTopicService = value; }
	public SkatExportSpecificTopicService getSkatExportSpecificTopicService(){ return this.skatExportSpecificTopicService; }
	
	
	 
}

