package no.systema.skat.skatimport.controller;

import java.util.*;


import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.InitBinder;
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

import no.systema.main.service.UrlCgiProxyService;
import no.systema.main.service.UrlCgiProxyServiceImpl;
import no.systema.main.context.TdsServletContext;
import no.systema.main.model.SystemaWebUser;
import no.systema.main.util.AppConstants;
import no.systema.main.util.JsonDebugger;
import no.systema.main.util.HttpSessionManager;
import no.systema.skat.skatimport.util.manager.SkatImportItemsAutoControlMgr;
import no.systema.skat.skatimport.service.SkatImportSpecificTopicItemService;
import no.systema.skat.skatimport.mapper.url.request.UrlRequestParameterMapper;
import no.systema.skat.skatimport.model.jsonjackson.topic.items.JsonSkatImportSpecificTopicItemContainer;
import no.systema.skat.skatimport.model.jsonjackson.topic.items.JsonSkatImportSpecificTopicItemRecord;
import no.systema.skat.skatimport.model.jsonjackson.topic.items.JsonSkatImportSpecificTopicItemToldvaerdiRecord;
//import no.systema.skat.skatimport.model.jsonjackson.topic.items.JsonSkatImportSpecificTopicItemAvgifterRecord;
import no.systema.skat.skatimport.model.jsonjackson.topic.JsonSkatImportSpecificTopicRecord;
import no.systema.skat.skatimport.url.store.SkatImportUrlDataStore;
import no.systema.skat.util.SkatConstants;
import no.systema.skat.util.manager.SkatAutoCopyItemLinesMgr;
import no.systema.skat.url.store.SkatUrlDataStore;

import no.systema.skat.service.html.dropdown.SkatDropDownListPopulationService;
import no.systema.skat.skatimport.service.html.dropdown.SkatImportDropDownListPopulationService;
import no.systema.skat.skatimport.util.RpgReturnResponseHandler;
import no.systema.skat.skatimport.util.SkatImportCalculator;
import no.systema.skat.skatimport.util.SkatImportConstants;
import no.systema.skat.skatimport.util.SkatImportTweaker;
import no.systema.skat.skatimport.util.manager.CodeDropDownMgr;
import no.systema.skat.skatimport.util.manager.TollvaerdideklarationMgr;

import no.systema.skat.skatimport.validator.SkatImportItemsValidator;

import no.systema.skat.service.SkatTaricVarukodService;
import no.systema.skat.model.jsonjackson.codes.JsonSkatTaricVarukodContainer;
import no.systema.skat.model.jsonjackson.codes.JsonSkatTaricVarukodRecord;




/**
 * SKAT Import create items gateway
 * 
 * @author oscardelatorre
 * @date Jan 24, 2014
 * 
 */

@Controller
//@SessionAttributes(AppConstants.SYSTEMA_WEB_USER_KEY)
@Scope("session")
public class SkatImportItemsController {
	private static final Logger logger = Logger.getLogger(SkatImportItemsController.class.getName());
	private static final JsonDebugger jsonDebugger = new JsonDebugger();
	private static final HttpSessionManager httpSessionManager = new HttpSessionManager();
	
	private UrlRequestParameterMapper urlRequestParameterMapper = new UrlRequestParameterMapper();
	private CodeDropDownMgr codeDropDownMgr = new CodeDropDownMgr();
	private SkatImportCalculator skatImportCalculator = new SkatImportCalculator();
	private SkatImportTweaker skatImportTweaker = new SkatImportTweaker();
	private SkatAutoCopyItemLinesMgr skatAutoCopyMgr = null;
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
	@RequestMapping(value="skatimport_edit_items.do")
	public ModelAndView skatImportEditItem(@ModelAttribute ("record") JsonSkatImportSpecificTopicItemRecord recordToValidate, BindingResult bindingResult, HttpSession session, HttpServletRequest request){
		boolean bindingErrorsExist = false;
		logger.info("Inside: skatImportEditItem");
		ModelAndView successView = new ModelAndView("skatimport_edit_items");
		RpgReturnResponseHandler rpgReturnResponseHandler = new RpgReturnResponseHandler();
		JsonSkatImportSpecificTopicItemRecord jsonSkatImportSpecificTopicItemRecord = null;
		
		SystemaWebUser appUser = (SystemaWebUser)session.getAttribute(AppConstants.SYSTEMA_WEB_USER_KEY);
		
		Map model = new HashMap();
		String urlRequestParamsKeys = null;
		//Catch required action (doFetch or doUpdate)
		String action = request.getParameter("action");
		logger.info("###ACTION: " + action);
		logger.info("###CERT_CODE_FLAG: " + recordToValidate.getCertificateCodeMandatoryFlag());
		
		//initialize and populate the auto copy manager if applicable
		this.skatAutoCopyMgr = new SkatAutoCopyItemLinesMgr();
		this.skatAutoCopyMgr.setAutoCopyAttributes(request);
		
		if(appUser==null){
			return this.loginView;
		}else{
			appUser.setActiveMenu(SystemaWebUser.ACTIVE_MENU_SKAT_IMPORT);
			session.setAttribute(SkatConstants.ACTIVE_URL_RPG_SKAT, SkatConstants.ACTIVE_URL_RPG_INITVALUE);
			session.setAttribute(SkatConstants.ACTIVE_URL_RPG_UPDATE_SKAT, SkatConstants.ACTIVE_URL_RPG_INITVALUE);
			
			
			boolean isValidCreatedRecordTransactionOnRPG = true;
			//Keys and header information
			String opd = request.getParameter("opd");
			String avd = request.getParameter("avd");
			String sign = request.getParameter("sign");
			String refnr = request.getParameter("refnr");
			String status = request.getParameter("status");
			String datum = request.getParameter("datum");
			String invoiceTotalAmount = request.getParameter("fabl");
			//new line
			String renew = request.getParameter("renew");
			if(renew!=null && !"".equals(renew)){
				//clean
				session.removeAttribute("dkiv_syli_SESSION");
			}
			
			//this fragment gets some header fields needed for the validator
			JsonSkatImportSpecificTopicRecord headerRecord = (JsonSkatImportSpecificTopicRecord)session.getAttribute(SkatConstants.DOMAIN_RECORD_TOPIC_SKAT_IMPORT);
			
			
			//this key is only used with a real Update. When empty it will be a signal for a CREATE NEW (Add)
			String lineNr = request.getParameter("dkiv_syli");
			if(lineNr!=null && !"".equals(lineNr)){
				//nothing
			}else{
				//this branch is necessary in order to get the line Nr after a validation error (ref. below att bindingResult.hasErrors in this same method)
				lineNr = (String)session.getAttribute("dkiv_syli_SESSION");
			}
			//this row counter(lastSelectedItemLineNumber) is only used to add aspects/behavior to the list of rows (color, scroll(top/down)etc
			String lastSelectedItemLineNumber = request.getParameter("lastSelectedItemLineNumber");
			
			model.put("avd", avd);
			model.put("opd", opd);
			model.put("sign", sign);
			model.put("refnr(tullid)", refnr);
			model.put("status", status);
			model.put("datum", datum);
			
			if(SkatConstants.ACTION_UPDATE.equals(action)){
				
				//-----------
				//Validation
				//-----------
				//we must validate towards the back-end here in order to avoid injection problems in Validator
				//The validation routine for Taric Varukod pinpoints those input values in which the user HAVE NOT used the search routine
				String varukodValidNumber = this.getTaricVarukod(appUser.getUser(), recordToValidate.getDkiv_331());
				if(!this.MATCH.equals(varukodValidNumber)){
					recordToValidate.setDkiv_331(null);
				}
				
				//put some header records in aux.attributes (in order to send to validator)... Add more if applicable
				recordToValidate.setHeader_dkih_aart(headerRecord.getDkih_aart());
				//recordToValidate.setExtraMangdEnhet(this.getMandatoryMangdEnhetDirective(appUser.getUser(), recordToValidate));
				
				
				SkatImportItemsValidator validator = new SkatImportItemsValidator();
				logger.info("Host via HttpServletRequest.getHeader('Host'): " + request.getHeader("Host"));
			    validator.validate(recordToValidate, bindingResult);
			    //check for ERRORS
				if(bindingResult.hasErrors()){
				    	logger.info("[ERROR Validation] Item Record does not validate)");
				    	logger.info("[INFO lineNr] " + lineNr);
				    	bindingErrorsExist = true;
				    	//model.put("record", recordToValidate);
				    	if(lineNr!=null && !"".equals(lineNr)){
				    		logger.info("[INFO lineNr] ... filling old value: lineNr:" + lineNr);
				    		session.setAttribute("dkiv_syli_SESSION", lineNr);
					    	recordToValidate.setDkiv_syop(opd);
				    		recordToValidate.setDkiv_syav(avd);
				    	}
			    }else{
					if(lineNr!=null && !"".equals(lineNr)){
						//clean
						session.removeAttribute("dkiv_syli_SESSION");
				    	
						//-------
						//UPDATE
						//-------
						logger.info("UPDATE(only) ITEM (existent item) on process...");
						//take the rest from GUI.
						jsonSkatImportSpecificTopicItemRecord = new JsonSkatImportSpecificTopicItemRecord();
						ServletRequestDataBinder binder = new ServletRequestDataBinder(jsonSkatImportSpecificTopicItemRecord);
			            //binder.registerCustomEditor(...); // if needed
			            binder.bind(request);
			            
			            jsonSkatImportSpecificTopicItemRecord.setDkiv_syli(lineNr);
			            jsonSkatImportSpecificTopicItemRecord.setDkiv_syop(opd);
			            jsonSkatImportSpecificTopicItemRecord.setDkiv_syav(avd);
			            //populate 401-2 values
			            this.set401And402Values(jsonSkatImportSpecificTopicItemRecord);
			            
			            
					}else{
						//-------
						//CREATE
						//-------
						logger.info("STATION ONE - dkiv_42:" + recordToValidate.getDkiv_42());
						logger.info("CREATE and UPDATE on ITEM (new fresh item) on process...");
						//This means that the update will be done AFTER a creation of an empty record. All this in the same transaction. 2 STEPS involved: (1)create and (2)update
						//-----------------------------------------------------------------------------------------
						//STEP[1] Generate new Item line key seeds (avd,opd,syli) by creating an empty new record. 
						//		  This step is ONLY applicable for new item lines 
						//-------------------------------------------------------------------------------------------
						jsonSkatImportSpecificTopicItemRecord  = this.createNewItemKeySeeds(session, request, appUser);
						if(jsonSkatImportSpecificTopicItemRecord!=null){
							String newLineNr = jsonSkatImportSpecificTopicItemRecord.getDkiv_syli();
							
							//take the rest from GUI.
							jsonSkatImportSpecificTopicItemRecord = new JsonSkatImportSpecificTopicItemRecord();
							ServletRequestDataBinder binder = new ServletRequestDataBinder(jsonSkatImportSpecificTopicItemRecord);
				            //binder.registerCustomEditor(...); // if needed
				            binder.bind(request);
				            
				            logger.info("[INFO] populate the dkiv_syli:" + newLineNr);
				            //put back the generated seed and the header keys (syop, syavd)
				            jsonSkatImportSpecificTopicItemRecord.setDkiv_syli(newLineNr);
				            jsonSkatImportSpecificTopicItemRecord.setDkiv_32(newLineNr);
				            jsonSkatImportSpecificTopicItemRecord.setDkiv_syop(opd);
				            jsonSkatImportSpecificTopicItemRecord.setDkiv_syav(avd);
				            //populate 401-2 values
				            this.set401And402Values(jsonSkatImportSpecificTopicItemRecord);
				            
				            
				            //-----------------------------------------------------------------------------
				            //Now calculate the some fields in TOLLVÆRDIDEKLARATION always with CREATE NEW
				            //-----------------------------------------------------------------------------
				            TollvaerdideklarationMgr tvMgr = new TollvaerdideklarationMgr(this.skatImportSpecificTopicItemService, this.urlCgiProxyService);
				            tvMgr.calculateToldVaerdiItemLinesTotalAmount(jsonSkatImportSpecificTopicItemRecord, headerRecord);
	            			logger.info("_t12: " + jsonSkatImportSpecificTopicItemRecord.getDkiv_t12());
	            			logger.info("_t24: " + jsonSkatImportSpecificTopicItemRecord.getDkiv_t24());
				            
				            //Now calculate the transport costs (EU-internal and external)
				            //Note!: This function is also calculated in the AjaxController.
				            String urlRequestParamsKeysTransportCosts = this.getRequestUrlKeyParametersForTollvaerdiTransportCosts(jsonSkatImportSpecificTopicItemRecord.getDkiv_42(),appUser,request);
				            JsonSkatImportSpecificTopicItemToldvaerdiRecord transportRecord = tvMgr.calculateTransportCostsOnItem(appUser.getUser(), urlRequestParamsKeysTransportCosts);
				            //handover right here!
							jsonSkatImportSpecificTopicItemRecord.setDkiv_t17a(transportRecord.getDkiv_t17a());
							jsonSkatImportSpecificTopicItemRecord.setDkiv_t19(transportRecord.getDkiv_t19());	
							logger.info("FINAL dkiv_t17a: " + jsonSkatImportSpecificTopicItemRecord.getDkiv_t17a());
				            logger.info("FINAL dkiv_t19: " + jsonSkatImportSpecificTopicItemRecord.getDkiv_t19());
				            
						}else{
							isValidCreatedRecordTransactionOnRPG = false;
						}
						
					}
					//--------------------------------------------------
					//At this point we are ready to do an update
					//--------------------------------------------------
					if(isValidCreatedRecordTransactionOnRPG){
						logger.info("[INFO] Valid STEP[1] Add Record(if applicable) successfully created, OK ");
						//---------------------------
						//get BASE URL = RPG-PROGRAM
			            //---------------------------
						String BASE_URL_UPDATE = SkatImportUrlDataStore.SKAT_IMPORT_BASE_UPDATE_SPECIFIC_TOPIC_ITEM_URL;
						logger.info("[INFO] UPDATE to be done with lineNr [dkiv_syli]: " + jsonSkatImportSpecificTopicItemRecord.getDkiv_syli());
						logger.info("[INFO] Varepostnr [dkiv_32]: " + jsonSkatImportSpecificTopicItemRecord.getDkiv_32());
						//
						urlRequestParamsKeys = this.getRequestUrlKeyParametersUpdate(jsonSkatImportSpecificTopicItemRecord.getDkiv_syli(), avd, opd, appUser, SkatConstants.MODE_UPDATE);
						String urlRequestParamsTopicItem = this.urlRequestParameterMapper.getUrlParameterValidString((jsonSkatImportSpecificTopicItemRecord));
						//put the valid param. string
						String urlRequestParams = urlRequestParamsKeys + urlRequestParamsTopicItem;
						
						//for debug purposes in GUI
						session.setAttribute(SkatConstants.ACTIVE_URL_RPG_UPDATE_SKAT, BASE_URL_UPDATE + "==>params: " + urlRequestParams.toString()); 
						
						logger.info(Calendar.getInstance().getTime() + " CGI-start timestamp");
				    	logger.info("URL: " + BASE_URL_UPDATE);
				    	logger.info("URL PARAMS: " + urlRequestParams);
					    	
				    	//----------------------------------------------------------------------------
				    	//EXECUTE the UPDATE (RPG program) here (STEP [2] when creating a new record)
				    	//----------------------------------------------------------------------------
						String rpgReturnPayload = this.urlCgiProxyService.getJsonContent(BASE_URL_UPDATE, urlRequestParams);
						//Debug --> 
				    	logger.info("Checking errMsg in rpgReturnPayload" + rpgReturnPayload);
				    	//we must evaluate a return RPG code in order to know if the Update was OK or not
				    	rpgReturnResponseHandler.evaluateRpgResponseOnTopicItemCreateOrUpdate(rpgReturnPayload);
				    	if(rpgReturnResponseHandler.getErrorMessage()!=null && !"".equals(rpgReturnResponseHandler.getErrorMessage())){
				    		rpgReturnResponseHandler.setErrorMessage("[ERROR] FATAL on UPDATE: " + rpgReturnResponseHandler.getErrorMessage());
				    		this.setFatalError(model, rpgReturnResponseHandler, jsonSkatImportSpecificTopicItemRecord);
				    		
				    	}else{
				    		//Update succefully done!
				    		logger.info("[INFO] Valid STEP[2] Update -- Record successfully updated, OK ");
				    		//-------------------------------------
				    		//Build AutoCopy string if applicable
				    		//-------------------------------------
				    		if(this.skatAutoCopyMgr.isAutoCopyActive() && this.skatAutoCopyMgr.getCopyLineStartLineNrInt()>0){
								logger.info("[INFO] AutoCopy STEP after Update is activated and STARTs here");
								//---------------------------------------------------------------------------------
								//EXECUTE the UPDATE (RPG program) here (STEP [2] when updating an existing record
								//---------------------------------------------------------------------------------
								boolean rpgReturnPayloadAutoCopy = this.skatAutoCopyMgr.executeAutoCopySkatImport(avd, opd, appUser.getUser(), jsonSkatImportSpecificTopicItemRecord, this.urlCgiProxyService);
								//do something if false. At this point it has not been specified how...
				    		}
				    	}
					    	
					}else{
						rpgReturnResponseHandler.setErrorMessage("[ERROR] FATAL on CREATE, at tuid, syop generation : " + rpgReturnResponseHandler.getErrorMessage());
						this.setFatalError(model, rpgReturnResponseHandler, jsonSkatImportSpecificTopicItemRecord);
					}
			    }
				
			}else if(SkatConstants.ACTION_DELETE.equals(action)){
				logger.info("[INFO] Delete record start process... ");
				String lineNrToDelete = request.getParameter("lin");
				
				//---------------------------
				//get BASE URL = RPG-PROGRAM
	            //---------------------------
				String BASE_URL_DELETE = SkatImportUrlDataStore.SKAT_IMPORT_BASE_UPDATE_SPECIFIC_TOPIC_ITEM_URL;
				String urlRequestParams = this.getRequestUrlKeyParametersUpdate(lineNrToDelete, avd, opd, appUser,SkatConstants.MODE_DELETE );
				
				logger.info(Calendar.getInstance().getTime() + " CGI-start timestamp");
		    	logger.info("URL: " + BASE_URL_DELETE);
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
		    		this.setFatalError(model, rpgReturnResponseHandler, jsonSkatImportSpecificTopicItemRecord);
		    		
		    	}else{
		    		//Delete succefully done!
		    		logger.info("[INFO] Valid Delete -- Record successfully deleted, OK ");
		    	}
			}
			
			//FETCH the ITEM LIST of existent ITEMs for this TOPIC
			//---------------------------
			//get BASE URL = RPG-PROGRAM
            //---------------------------
			String BASE_URL_FETCH = SkatImportUrlDataStore.SKAT_IMPORT_BASE_FETCH_TOPIC_ITEMLIST_URL;
			urlRequestParamsKeys = this.getRequestUrlKeyParameters(request, avd, opd, appUser);
			
			logger.info(Calendar.getInstance().getTime() + " CGI-start timestamp");
			logger.info("FETCH av item list... ");
	    	logger.info("URL: " + BASE_URL_FETCH);
	    	logger.info("URL PARAMS: " + urlRequestParamsKeys);
	    	//--------------------------------------
	    	//EXECUTE the FETCH (RPG program) here
	    	//--------------------------------------
			String jsonPayloadFetch = this.urlCgiProxyService.getJsonContent(BASE_URL_FETCH, urlRequestParamsKeys);
			//for debug purposes in GUI
			session.setAttribute(SkatConstants.ACTIVE_URL_RPG_SKAT, BASE_URL_FETCH + "==>params: " + urlRequestParamsKeys.toString() + 
					" " + "(fetched item list):" + jsonPayloadFetch); 
			//Debug --> 
			logger.debug(jsonDebugger.debugJsonPayloadWithLog4J(jsonPayloadFetch));
	    	logger.info(Calendar.getInstance().getTime() +  " CGI-end timestamp");
	    	JsonSkatImportSpecificTopicItemContainer jsonSkatImportSpecificTopicItemContainer = this.skatImportSpecificTopicItemService.getSkatImportSpecificTopicItemContainer(jsonPayloadFetch);
	    	if(jsonSkatImportSpecificTopicItemContainer!=null){
			    Double calculatedItemLinesTotalAmount = this.skatImportCalculator.getItemLinesTotalAmount(jsonSkatImportSpecificTopicItemContainer);
			    Double diffItemLinesTotalAmountWithInvoiceTotalAmount = this.skatImportCalculator.getDiffBetweenCalculatedTotalAmountAndTotalAmount(invoiceTotalAmount, calculatedItemLinesTotalAmount);
			    logger.info("calculatedItemLinesTotalAmount:" + calculatedItemLinesTotalAmount);
			    logger.info("diffItemLinesTotalAmountWithInvoiceTotalAmount:" + diffItemLinesTotalAmountWithInvoiceTotalAmount);
			    jsonSkatImportSpecificTopicItemContainer.setCalculatedItemLinesTotalAmount(calculatedItemLinesTotalAmount);
		    	jsonSkatImportSpecificTopicItemContainer.setDiffItemLinesTotalAmountWithInvoiceTotalAmount(diffItemLinesTotalAmountWithInvoiceTotalAmount);
		    	//some aspects for GUI
		    	jsonSkatImportSpecificTopicItemContainer.setLastSelectedItemLineNumber(lastSelectedItemLineNumber);
		    	//logger.info("(B)##### lastSelectedItemLineNumber:" + lastSelectedItemLineNumber);
	    	}
	    	
	    	//drop downs populated from back-end
	    	//This is done to boost performance
	    	this.setCodeDropDownMgr(appUser, model, headerRecord);
	    	
	    	/* OBSOLETE - Not safe
	    	String codeMgrExists = (String)session.getAttribute(SkatConstants.SESSION_CODE_MANAGER_EXISTS_SKATIMPORT);
	    	if(codeMgrExists!=null){
	    		this.codeDropDownMgr.getCodeMgrListsFromSession(model, session);
	    	}else{
	    		this.setCodeDropDownMgr(appUser, model, headerRecord);
	    		this.codeDropDownMgr.setCodeMgrListsInSession(model, session);
	    		session.setAttribute(SkatConstants.SESSION_CODE_MANAGER_EXISTS_SKATIMPORT, SkatConstants.SESSION_CODE_MANAGER_EXISTS_SKATIMPORT );
	    	}
	    	*/
	    	//logger.debug("BB");
    		//drop downs populated from a txt file
    		model.put(SkatConstants.RESOURCE_MODEL_KEY_BERAKNINGSENHET_LIST, this.skatImportDropDownListPopulationService.getBerakningsEnheterList());
    		this.setDomainObjectsForListInView(session, model, jsonSkatImportSpecificTopicItemContainer);
			//this next step is necessary for the default values on "create new" record
    		//some values are the same for each item line (e.g. 40. Summarisk angivelse)
    		if(bindingErrorsExist){
    			logger.info("############# _442a:" + recordToValidate.getDkiv_442a());
    			this.setDefaultDomainItemRecordInView(model, jsonSkatImportSpecificTopicItemContainer, recordToValidate, headerRecord);
    		}else{
    			this.setDefaultDomainItemRecordInView(model, jsonSkatImportSpecificTopicItemContainer, null, headerRecord);
    		}	    		
			successView.addObject("model",model);
			//successView.addObject(Constants.EDIT_ACTION_ON_TOPIC, Constants.ACTION_FETCH);
			logger.debug("END OF METHOD");
	    	return successView;
		}
	}
	
	
	/**
	 * 
	 * @param recordToValidate
	 * @param bindingResult
	 * @param session
	 * @param request
	 * @return
	 */
	@RequestMapping(value="skatimport_edit_items_autocontrol.do")
	public ModelAndView skatImportEditItemAutoControl(@ModelAttribute ("record") JsonSkatImportSpecificTopicItemRecord recordToValidate, BindingResult bindingResult, HttpSession session, HttpServletRequest request){
		logger.info("Inside: skatImportEditItemAutoControl");
		final String AUTO_CONTROL_ERROR_FLAG_VALUE = "X";
		final String ERROR_FRAME_STD_OUTPUT = "-------------------------------------------------------------";
		
		ModelAndView successView = null;
		RpgReturnResponseHandler rpgReturnResponseHandler = new RpgReturnResponseHandler();
		JsonSkatImportSpecificTopicItemRecord jsonSkatImportSpecificTopicItemRecord = null;
		
		SystemaWebUser appUser = (SystemaWebUser)session.getAttribute(AppConstants.SYSTEMA_WEB_USER_KEY);
		//this fragment gets some header fields needed for the validator
		JsonSkatImportSpecificTopicRecord headerRecord = (JsonSkatImportSpecificTopicRecord)session.getAttribute(SkatConstants.DOMAIN_RECORD_TOPIC_SKAT_IMPORT);
		
		Map model = new HashMap();
		String urlRequestParamsKeys = null;
		
		if(appUser==null){
			return this.loginView;
		}else{
			StringBuffer params = new StringBuffer();
			params.append("user=" + appUser.getUser() + "&avd=" + recordToValidate.getDkiv_syav() + "&opd=" + recordToValidate.getDkiv_syop());
			successView = new ModelAndView("redirect:skatimport_edit_items.do?" + params);
			
			//FETCH the ITEM LIST of existent ITEMs for this TOPIC
			//---------------------------
			//get BASE URL = RPG-PROGRAM
            //---------------------------
			String BASE_URL_FETCH = SkatImportUrlDataStore.SKAT_IMPORT_BASE_FETCH_TOPIC_ITEMLIST_URL;
			urlRequestParamsKeys = this.getRequestUrlKeyParameters(request, recordToValidate.getDkiv_syav(), recordToValidate.getDkiv_syop(), appUser);
			logger.info(Calendar.getInstance().getTime() + " CGI-start timestamp");
			logger.info("FETCH av item list... ");
	    	logger.info("URL: " + BASE_URL_FETCH);
	    	logger.info("URL PARAMS: " + urlRequestParamsKeys);
	    	//--------------------------------------
	    	//EXECUTE the FETCH (RPG program) here
	    	//--------------------------------------
			String jsonPayloadFetch = this.urlCgiProxyService.getJsonContent(BASE_URL_FETCH, urlRequestParamsKeys);
			//for debug purposes in GUI
			session.setAttribute(SkatConstants.ACTIVE_URL_RPG_SKAT, BASE_URL_FETCH + "==>params: " + urlRequestParamsKeys.toString() + " " + "(fetched item list):" + jsonPayloadFetch); 
			//Debug --> 
	    	logger.debug(jsonDebugger.debugJsonPayloadWithLog4J(jsonPayloadFetch));
	    	logger.info(Calendar.getInstance().getTime() +  " CGI-end timestamp");
	    	
	    	JsonSkatImportSpecificTopicItemContainer container = this.skatImportSpecificTopicItemService.getSkatImportSpecificTopicItemContainer(jsonPayloadFetch);
	    	if(container!=null){
	    		//INIT Autocontrol Manager...
	    		SkatImportItemsAutoControlMgr autoControlMgr = new SkatImportItemsAutoControlMgr(this.urlCgiProxyService, this.skatImportSpecificTopicItemService);
	    		
	    		//Iterate on every line to control the requirements
	    		for(JsonSkatImportSpecificTopicItemRecord record: container.getOrderList()){
	    			//Fill in Taric Values in the record to prepare for some validity checks
	    			boolean isBatch = true;
	    			this.backEndValidationOnTolltariff(appUser, headerRecord, record, isBatch);
	    			//Init record
	    			autoControlMgr.setRecord(record);
	    			autoControlMgr.setHeaderRecord(headerRecord);
	    			//DEBUG -->
	    			logger.info(record.getDkiv_315a());
	    			
	    			if(record!=null && record.isValidTolltariff()){
	    				//---------------------------
		    			//START with calculations
		    			//---------------------------
	    				String idDebug = record.getDkiv_syli() + "-" + record.getDkiv_331();
		    			logger.info("Check Calculations " + idDebug);
		    			autoControlMgr.calculateNetWeight(headerRecord, appUser);
		    			//Update (back-end) the record after the above backEndValdiationOnTolltariff and upcoming calculations...
	    				autoControlMgr.updateItemRecord(appUser.getUser());
						
	    				//---------------------------
		    			//START with validations now
		    			//---------------------------
						//Begin with the validity checks
		    			//logger.info("level check (1) " + idDebug);
						autoControlMgr.checkValidGrossAndNetWeight();
						if(autoControlMgr.isValidRecord()){
							//Go to level 2
							//logger.info("level check (2) " + idDebug);
	    					autoControlMgr.checkManyCertificatesExist();
	    					if(autoControlMgr.isValidRecord()){
	    						//Go to level 3
	    						//logger.info("level check (3) " + idDebug);
	    						autoControlMgr.checkValidMandatoryFields_41();
	        					if(autoControlMgr.isValidRecord()){
	    							//Go to level FINAL MandatoryFields (must be the last check)
		    						//Nothing more below this level. New requirements must be insert between previous level and this FINAL level!
		    						autoControlMgr.checkValidMandatoryFields();
									if(autoControlMgr.isValidRecord()){
										//Update in order to remove previous error flags, if any...
		    		    				autoControlMgr.updateItemWithAutoControlError(appUser.getUser(), null );
		    		    			}else{
		    		    				//Set error
		    		    				logger.info(ERROR_FRAME_STD_OUTPUT);
			    						logger.info("ERROR level (FINAL) - Mandatory Fields" + idDebug);
			    						logger.info(ERROR_FRAME_STD_OUTPUT);
			    						autoControlMgr.updateItemWithAutoControlError(appUser.getUser(), AUTO_CONTROL_ERROR_FLAG_VALUE);
		    		    			}
	        					}else{
	        						//Set error
	        						logger.info(ERROR_FRAME_STD_OUTPUT);
	        						logger.info("ERROR level (3) - checkValidMandatoryFields_41" + idDebug);
	        						logger.info(ERROR_FRAME_STD_OUTPUT);
	        						autoControlMgr.updateItemWithAutoControlError(appUser.getUser(), AUTO_CONTROL_ERROR_FLAG_VALUE);
	        					}
	    					}else{
	    						//Set error
	    						logger.info(ERROR_FRAME_STD_OUTPUT);
	    						logger.info("ERROR level (2) - checkManyCertificatesExist" + idDebug);
	    						logger.info(ERROR_FRAME_STD_OUTPUT);
	    						autoControlMgr.updateItemWithAutoControlError(appUser.getUser(), AUTO_CONTROL_ERROR_FLAG_VALUE);
	    					}
						}else{
							//Set error
							logger.info(ERROR_FRAME_STD_OUTPUT);
							logger.info("ERROR level (1) - Weights" + idDebug);
							logger.info(ERROR_FRAME_STD_OUTPUT);
							autoControlMgr.updateItemWithAutoControlError(appUser.getUser(), AUTO_CONTROL_ERROR_FLAG_VALUE);
						}
	    			}else{
	    				//Set error INIT (Tolltariff)
	    				//Begin with the validity checks
		    			String idDebug = record.getDkiv_syli() + "-" + record.getDkiv_331();
						logger.info(ERROR_FRAME_STD_OUTPUT);
						logger.info("ERROR level (INIT) - Varukod nr:" + idDebug);
						logger.info(ERROR_FRAME_STD_OUTPUT);
						autoControlMgr.updateItemWithAutoControlError(appUser.getUser(), AUTO_CONTROL_ERROR_FLAG_VALUE);
	    			}
	    			
				}
    		}
			
    	}
    	return successView;
	}
	
	/**
	 * 
	 * @param appUser
	 * @param headerRecord
	 * @param record
	 * @param isBatch
	 */
	private void backEndValidationOnTolltariff(SystemaWebUser appUser, JsonSkatImportSpecificTopicRecord headerRecord, JsonSkatImportSpecificTopicItemRecord record, boolean isBatch){
		//String ITEM_NR_SUFFIX_CHARACTERS_INVALID = "???";
		//we must validate towards the back-end here in order to avoid injection problems in Validator
		//The validation routine for Taric Varukod pinpoints those input values in which the user HAVE NOT used the search-taric-number routine
		JsonSkatTaricVarukodRecord jsonSkatTaricVarukodRecord = this.getTaricVarukod(appUser.getUser(), record.getDkiv_331(), headerRecord.getDkih_15());
		if(jsonSkatTaricVarukodRecord!=null){
			logger.info("VALID varukod:" + record.getDkiv_331());
			this.setValuesOnRecordToValidate(jsonSkatTaricVarukodRecord, record, isBatch);
			
		}else{
			logger.info("INVALID varukod:" + record.getDkiv_331());
			//only with validation of a specific record (no batch)
			/*
			if(!isBatch){
				if(record.getDkev_331()!=null){
					Integer length = record.getDkev_331().length();
					if(length>=3){
						record.setSvvntValid(false);
					}else{
						//put a suffix to indicate invalid number (in a single validation use case)
						record.setSvvntValid(false);
					}
				}
			}else{
				record.setValidTolltariff(false);
			}
			*/
			record.setValidTolltariff(false);
		}
		
		
	}
	/**
	 * 
	 * @param applicationUser
	 * @param taricVarukod
	 * @param selkbCountryCode
	 * @return
	 */
	private JsonSkatTaricVarukodRecord getTaricVarukod(String applicationUser, String taricVarukod, String selkaCountryCode) {
		logger.info("Inside getTaricVarukod()");
		JsonSkatTaricVarukodRecord retval = null;
		
		String TYPE_IE = "I";
		try{
		  String BASE_URL = SkatUrlDataStore.SKAT_FETCH_TARIC_VARUKODER_ITEMS_URL;
		  String urlRequestParamsKeys = "user=" + applicationUser + "&ie=" + TYPE_IE + "&kod=" + taricVarukod; // + "&selka=" + selkaCountryCode;
		  UrlCgiProxyService urlCgiProxyService = new UrlCgiProxyServiceImpl();
		  String jsonPayload = urlCgiProxyService.getJsonContent(BASE_URL, urlRequestParamsKeys);
		  JsonSkatTaricVarukodContainer container = this.skatTaricVarukodService.getContainer(jsonPayload);
		  if(container!=null){
			  for(JsonSkatTaricVarukodRecord record : container.getTariclist()){
				  if(taricVarukod!=null && taricVarukod.equals(record.getDktara02())){
					  //logger.info("MATCH on VAREKOD. !!!!: " + record.getDktara02());
					  retval = record;
				  }
			  }	
		  }
		}catch(Exception e){
			e.printStackTrace();
		}
	  
		return retval;
  	}
	
	/**
	 * 
	 * @param jsonSkatTaricVarukodRecord
	 * @param recordToValidate
	 * @param isBatch
	 */
	private void setValuesOnRecordToValidate(JsonSkatTaricVarukodRecord jsonSkatTaricVarukodRecord, JsonSkatImportSpecificTopicItemRecord recordToValidate, boolean isBatch){
		/*
		//since the varukod is valid then we proceed to set more dependencies. This routine was not possible to implement as in TDS or SKAT
		if( "J".equals(jsonSkatTaricVarukodRecord.getTastk()) ){
			recordToValidate.setExtraMangdEnhet("Y");
		}else{
			recordToValidate.setExtraMangdEnhet("N");
		}
		//fiskeavgift
		if(  (jsonSkatTaricVarukodRecord.getFfsvavt()!=null && !"".equals(jsonSkatTaricVarukodRecord.getFfsvavt())) &&
				 (jsonSkatTaricVarukodRecord.getFfsvavtp()!=null && !"".equals(jsonSkatTaricVarukodRecord.getFfsvavtp())) &&	
				 (jsonSkatTaricVarukodRecord.getFfsvavts()!=null && !"".equals(jsonSkatTaricVarukodRecord.getFfsvavts())) ){
			
				recordToValidate.setMandatoryFiskAvgift(true);
				
				//only when Autocontrol is used...
				if(isBatch){
					recordToValidate.setSvavt(jsonSkatTaricVarukodRecord.getFfsvavt());
					recordToValidate.setSvavtp(jsonSkatTaricVarukodRecord.getFfsvavtp());
					recordToValidate.setSvavts(jsonSkatTaricVarukodRecord.getFfsvavts());
				}
		}
		*/
	}
	
	
	
	/**
	 * Populates all the 401 and 402 values 
	 * @param jsonSkatImportSpecificTopicItemRecord
	 */
	private void set401And402Values(JsonSkatImportSpecificTopicItemRecord jsonSkatImportSpecificTopicItemRecord){
		jsonSkatImportSpecificTopicItemRecord.setDkiv_401a(this.skatImportTweaker.getSummariskAngivelse_401a(jsonSkatImportSpecificTopicItemRecord));
        jsonSkatImportSpecificTopicItemRecord.setDkiv_402a(this.skatImportTweaker.getSummariskAngivelse_402a(jsonSkatImportSpecificTopicItemRecord));
        jsonSkatImportSpecificTopicItemRecord.setDkiv_401b(this.skatImportTweaker.getSummariskAngivelse_401b(jsonSkatImportSpecificTopicItemRecord));
        jsonSkatImportSpecificTopicItemRecord.setDkiv_402b(this.skatImportTweaker.getSummariskAngivelse_402b(jsonSkatImportSpecificTopicItemRecord));
        jsonSkatImportSpecificTopicItemRecord.setDkiv_401c(this.skatImportTweaker.getSummariskAngivelse_401c(jsonSkatImportSpecificTopicItemRecord));
        jsonSkatImportSpecificTopicItemRecord.setDkiv_402c(this.skatImportTweaker.getSummariskAngivelse_402c(jsonSkatImportSpecificTopicItemRecord));
        jsonSkatImportSpecificTopicItemRecord.setDkiv_401d(this.skatImportTweaker.getSummariskAngivelse_401d(jsonSkatImportSpecificTopicItemRecord));
        jsonSkatImportSpecificTopicItemRecord.setDkiv_402d(this.skatImportTweaker.getSummariskAngivelse_402d(jsonSkatImportSpecificTopicItemRecord));
        jsonSkatImportSpecificTopicItemRecord.setDkiv_401e(this.skatImportTweaker.getSummariskAngivelse_401e(jsonSkatImportSpecificTopicItemRecord));
        jsonSkatImportSpecificTopicItemRecord.setDkiv_402e(this.skatImportTweaker.getSummariskAngivelse_402e(jsonSkatImportSpecificTopicItemRecord));
        jsonSkatImportSpecificTopicItemRecord.setDkiv_401f(this.skatImportTweaker.getSummariskAngivelse_401f(jsonSkatImportSpecificTopicItemRecord));
        jsonSkatImportSpecificTopicItemRecord.setDkiv_402f(this.skatImportTweaker.getSummariskAngivelse_402f(jsonSkatImportSpecificTopicItemRecord));
        jsonSkatImportSpecificTopicItemRecord.setDkiv_401g(this.skatImportTweaker.getSummariskAngivelse_401g(jsonSkatImportSpecificTopicItemRecord));
        jsonSkatImportSpecificTopicItemRecord.setDkiv_402g(this.skatImportTweaker.getSummariskAngivelse_402g(jsonSkatImportSpecificTopicItemRecord));
        jsonSkatImportSpecificTopicItemRecord.setDkiv_401h(this.skatImportTweaker.getSummariskAngivelse_401h(jsonSkatImportSpecificTopicItemRecord));
        jsonSkatImportSpecificTopicItemRecord.setDkiv_402h(this.skatImportTweaker.getSummariskAngivelse_402h(jsonSkatImportSpecificTopicItemRecord));
        jsonSkatImportSpecificTopicItemRecord.setDkiv_401i(this.skatImportTweaker.getSummariskAngivelse_401i(jsonSkatImportSpecificTopicItemRecord));
        jsonSkatImportSpecificTopicItemRecord.setDkiv_402i(this.skatImportTweaker.getSummariskAngivelse_402i(jsonSkatImportSpecificTopicItemRecord));
        //
        jsonSkatImportSpecificTopicItemRecord.setDkiv_401j(this.skatImportTweaker.getSummariskAngivelse_401j(jsonSkatImportSpecificTopicItemRecord));
        jsonSkatImportSpecificTopicItemRecord.setDkiv_402j(this.skatImportTweaker.getSummariskAngivelse_402j(jsonSkatImportSpecificTopicItemRecord));
        jsonSkatImportSpecificTopicItemRecord.setDkiv_401k(this.skatImportTweaker.getSummariskAngivelse_401k(jsonSkatImportSpecificTopicItemRecord));
        jsonSkatImportSpecificTopicItemRecord.setDkiv_402k(this.skatImportTweaker.getSummariskAngivelse_402k(jsonSkatImportSpecificTopicItemRecord));
        jsonSkatImportSpecificTopicItemRecord.setDkiv_401l(this.skatImportTweaker.getSummariskAngivelse_401l(jsonSkatImportSpecificTopicItemRecord));
        jsonSkatImportSpecificTopicItemRecord.setDkiv_402l(this.skatImportTweaker.getSummariskAngivelse_402l(jsonSkatImportSpecificTopicItemRecord));
        jsonSkatImportSpecificTopicItemRecord.setDkiv_401m(this.skatImportTweaker.getSummariskAngivelse_401m(jsonSkatImportSpecificTopicItemRecord));
        jsonSkatImportSpecificTopicItemRecord.setDkiv_402m(this.skatImportTweaker.getSummariskAngivelse_402m(jsonSkatImportSpecificTopicItemRecord));
        jsonSkatImportSpecificTopicItemRecord.setDkiv_401n(this.skatImportTweaker.getSummariskAngivelse_401n(jsonSkatImportSpecificTopicItemRecord));
        jsonSkatImportSpecificTopicItemRecord.setDkiv_402n(this.skatImportTweaker.getSummariskAngivelse_402n(jsonSkatImportSpecificTopicItemRecord));
        jsonSkatImportSpecificTopicItemRecord.setDkiv_401o(this.skatImportTweaker.getSummariskAngivelse_401o(jsonSkatImportSpecificTopicItemRecord));
        jsonSkatImportSpecificTopicItemRecord.setDkiv_402o(this.skatImportTweaker.getSummariskAngivelse_402o(jsonSkatImportSpecificTopicItemRecord));
        jsonSkatImportSpecificTopicItemRecord.setDkiv_401p(this.skatImportTweaker.getSummariskAngivelse_401p(jsonSkatImportSpecificTopicItemRecord));
        jsonSkatImportSpecificTopicItemRecord.setDkiv_402p(this.skatImportTweaker.getSummariskAngivelse_402p(jsonSkatImportSpecificTopicItemRecord));
        jsonSkatImportSpecificTopicItemRecord.setDkiv_401q(this.skatImportTweaker.getSummariskAngivelse_401q(jsonSkatImportSpecificTopicItemRecord));
        jsonSkatImportSpecificTopicItemRecord.setDkiv_402q(this.skatImportTweaker.getSummariskAngivelse_402q(jsonSkatImportSpecificTopicItemRecord));
        jsonSkatImportSpecificTopicItemRecord.setDkiv_401r(this.skatImportTweaker.getSummariskAngivelse_401r(jsonSkatImportSpecificTopicItemRecord));
        jsonSkatImportSpecificTopicItemRecord.setDkiv_402r(this.skatImportTweaker.getSummariskAngivelse_402r(jsonSkatImportSpecificTopicItemRecord));
        jsonSkatImportSpecificTopicItemRecord.setDkiv_401s(this.skatImportTweaker.getSummariskAngivelse_401s(jsonSkatImportSpecificTopicItemRecord));
        jsonSkatImportSpecificTopicItemRecord.setDkiv_402s(this.skatImportTweaker.getSummariskAngivelse_402s(jsonSkatImportSpecificTopicItemRecord));
	}
	
	/**
	 * 
	 * @param applicationUser
	 * @param varukod
	 * @return
	 */
	private String getTaricVarukod(String applicationUser, String taricVarukod) {
		logger.info("Inside getTaricVarukod()");
		String retval = null;
		
		String TYPE_IE = "I";
		try{
		  String BASE_URL = SkatUrlDataStore.SKAT_FETCH_TARIC_VARUKODER_ITEMS_URL;
		  String urlRequestParamsKeys = "user=" + applicationUser + "&ie=" + TYPE_IE + "&kod=" + taricVarukod;
		  UrlCgiProxyService urlCgiProxyService = new UrlCgiProxyServiceImpl();
		  String jsonPayload = urlCgiProxyService.getJsonContent(BASE_URL, urlRequestParamsKeys);
		  JsonSkatTaricVarukodContainer container = this.skatTaricVarukodService.getContainer(jsonPayload);
		  if(container!=null){
			  for(JsonSkatTaricVarukodRecord record : container.getTariclist()){
				  if( taricVarukod.equals(record.getDktara02()) ){
					  //logger.info("MATCH on VARUKOD !!!!: " + record.getDktara02());
					  retval = this.MATCH;
				  }
			  }	
		  }
		}catch(Exception e){
			e.printStackTrace();
		}
	  
		return retval;
  	}
	
	/**
	 * Checks and updates the statistical value (which is mandatory) when needed.
	 * 
	 * @param record
	 */
	/*private void updateStatisticalValue(JsonTdsImportSpecificTopicItemRecord record){
		String statisticalValue = record.getSviv_stva();
		
		if(statisticalValue!=null && !"".equals(statisticalValue)){
			//Nothing. The user has a value which might be different than the total invoice due value.
		}else{
			//copy invoice amount to the statistical value
			record.setSviv_stva(record.getSviv_fabl());
		}
		
	}*/
	
	/**
	 * Checks and updates the tullvarde value when needed.
	 * @param record
	 */
	/*
	private void updateTullvardeValue(JsonTdsImportSpecificTopicItemRecord record){
		String tullvardeValue = record.getSviv_stva2();
		
		if(tullvardeValue!=null && !"".equals(tullvardeValue)){
			//Nothing. The user has a value which might be different than the total invoice due value.
		}else{
			//copy invoice amount to the statistical value
			record.setSviv_stva2(record.getSviv_fabl());
		}
		
	}*/
	
	
	
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
		errorMetaInformation.append(rpgReturnResponseHandler.getDkiv_syop());
		model.put(SkatConstants.ASPECT_ERROR_META_INFO, errorMetaInformation);
		
	}
	/**
	 * Sets domain objects
	 * @param model
	 * @param container
	 * 
	 */
	private void setDomainObjectsForListInView(HttpSession session, Map model, JsonSkatImportSpecificTopicItemContainer container){
		List list = new ArrayList();
		if(container!=null){
			for (JsonSkatImportSpecificTopicItemRecord record : container.getOrderList()){
				list.add(record);
			}
		}
		//check for possible default values on new record (GUI). Populate with values of the last record (if they were checked)
		if(this.skatAutoCopyMgr.isAutoCopyActive() && "-1".equals(this.skatAutoCopyMgr.getCopyLineStartLineNr()) ){
			for(String record: this.skatAutoCopyMgr.getlistOfParameterNamesToAutoCopy()){
				//TODO COVI Your code here....
				//DEBUG
				//logger.info("####VALUES to put:" + record);
			}
		}
		model.put(SkatConstants.DOMAIN_LIST, list);
		model.put(SkatConstants.DOMAIN_RECORD_ITEM_CONTAINER_TOPIC, container);
		//set a session variable in order to make the list available to an external view controller (Excel/PDF- Controller)
		session.setAttribute(session.getId() + SkatConstants.SESSION_LIST, list);
				
	}
	/**
	 * Sets domain objects
	 * @param model
	 * @param record
	 */
	private void setDomainObjectsInView(Map model, JsonSkatImportSpecificTopicItemRecord record){
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
	private void setDefaultDomainItemRecordInView(Map model, JsonSkatImportSpecificTopicItemContainer container, JsonSkatImportSpecificTopicItemRecord recordToValidate, JsonSkatImportSpecificTopicRecord headerRecord){
		List list = new ArrayList();
		final String EJ_OMFATTET = "EJ OMFATTET"; //Kingsr. DK requirement
		JsonSkatImportSpecificTopicItemRecord defaultRecord = new JsonSkatImportSpecificTopicItemRecord();
		
		if(container!=null){
			int lineNr = 0;
			String ejOmfattetLastline = "";
			defaultRecord.setDkiv_34(headerRecord.getDkih_15());
			
			for (JsonSkatImportSpecificTopicItemRecord record : container.getOrderList()){
				lineNr++;
				if(lineNr==1){
					//Only check the first line for these values
					defaultRecord.setDkiv_402a(this.skatImportTweaker.setSummariskAngivelse_402a(record));
					defaultRecord.setDkiv_403a(record.getDkiv_403a());
					//logger.info("[DEBUG _402a] " +  record.getDkiv_402a());
					//logger.info("[DEBUG _403a] " +  record.getDkiv_403a());
					if(record.getDkiv_442a()!=null && record.getDkiv_442a().toUpperCase().contains("EJ OMFATT") ){
						ejOmfattetLastline = EJ_OMFATTET;
					}
				}else{
					//EJ OMFATTET text should ONLY be put only-and-only-if the last item line had it on. It will be the default for the next line
					if(record.getDkiv_442a()!=null && record.getDkiv_442a().toUpperCase().contains("OMFATT") ){
						ejOmfattetLastline = EJ_OMFATTET;
					}else{
						ejOmfattetLastline = "";
					}
				
				}
			}
			defaultRecord.setDkiv_442a(ejOmfattetLastline);
			//meaning that there were validation errors
			if(recordToValidate!=null){
				recordToValidate.setDkiv_402a(defaultRecord.getDkiv_402a());
				recordToValidate.setDkiv_403a(defaultRecord.getDkiv_403a());
				if(defaultRecord.getDkiv_442a()!=null && !"".equals(defaultRecord.getDkiv_442a())){
					recordToValidate.setDkiv_442a(defaultRecord.getDkiv_442a());
				}
				recordToValidate.setDkiv_34(defaultRecord.getDkiv_34());
				
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
	private void setFatalError(Map model, RpgReturnResponseHandler rpgReturnResponseHandler, JsonSkatImportSpecificTopicItemRecord record){
		logger.info(rpgReturnResponseHandler.getErrorMessage());
		this.setAspectsInView(model, rpgReturnResponseHandler);
		//No refresh on jsonRecord is done for the GUI (form fields). Must be implemented right here, if required. !!
        this.setDomainObjectsInView(model, record);
	}
	
	/**
	 * 
	 * Creates the record (Add) for a later update in the same transaction
	 * @param session
	 * @param request
	 * @param appUser
	 * @return
	 */
	private JsonSkatImportSpecificTopicItemRecord createNewItemKeySeeds(HttpSession session, HttpServletRequest request, SystemaWebUser appUser){
		RpgReturnResponseHandler rpgReturnResponseHandler = new RpgReturnResponseHandler();
		//request variables
		String numberOfItemLinesInTopicStr = request.getParameter("numberOfItemLinesInTopic");
		if(numberOfItemLinesInTopicStr==null || "".equals(numberOfItemLinesInTopicStr)){
			numberOfItemLinesInTopicStr = "0";
		}
			
		String opd = request.getParameter("opd");
		String avd = request.getParameter("avd");
		
		JsonSkatImportSpecificTopicItemRecord jsonSkatImportSpecificTopicItemRecord = new JsonSkatImportSpecificTopicItemRecord();
		//---------------------------
		//get BASE URL = RPG-PROGRAM
        //---------------------------
		String BASE_URL = SkatImportUrlDataStore.SKAT_IMPORT_BASE_UPDATE_SPECIFIC_TOPIC_ITEM_URL;
		
		//-------------------------------------------------------------------------------------------
		// STEP[PREPARE CREATION] --> generate new opd and tuid (if applicable) in order to be able to Add (Create)
		//-------------------------------------------------------------------------------------------
		logger.info("STEP[1] GET SEEDS and CREATE RECORD...");
		logger.info("STEP[1] numberOfItemLinesInTopicStr: " + numberOfItemLinesInTopicStr);
		StringBuffer urlRequestParamsForSeed = new StringBuffer();
		urlRequestParamsForSeed.append("user=" + appUser.getUser());
		urlRequestParamsForSeed.append(SkatConstants.URL_CHAR_DELIMETER_FOR_PARAMS_WITH_HTML_REQUEST + "avd=" + avd);
		urlRequestParamsForSeed.append(SkatConstants.URL_CHAR_DELIMETER_FOR_PARAMS_WITH_HTML_REQUEST + "opd=" + opd);
		Integer numberOfItemLinesInTopic = -99;
		try{
			numberOfItemLinesInTopic = Integer.valueOf(numberOfItemLinesInTopicStr);
			//add one
			numberOfItemLinesInTopic++;
			logger.info("New item line nr: " + numberOfItemLinesInTopic);
		}catch(Exception e){
			//nothing
		}
		urlRequestParamsForSeed.append(SkatConstants.URL_CHAR_DELIMETER_FOR_PARAMS_WITH_HTML_REQUEST + "lin=" + numberOfItemLinesInTopic);
		urlRequestParamsForSeed.append(SkatConstants.URL_CHAR_DELIMETER_FOR_PARAMS_WITH_HTML_REQUEST + "mode=" + SkatConstants.MODE_ADD);
		logger.info("URL for SEED: " + BASE_URL);
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
			logger.info("[ERROR] No mandatory seeds (syli, opd) were generated correctly)! look at std output log. [errMsg]" + rpgReturnResponseHandler.getErrorMessage());
			jsonSkatImportSpecificTopicItemRecord = null;
			
		}else{
			jsonSkatImportSpecificTopicItemRecord.setDkiv_syop(rpgReturnResponseHandler.getDkiv_syop());
			jsonSkatImportSpecificTopicItemRecord.setDkiv_syli(rpgReturnResponseHandler.getDkiv_syli());
		}
        
		return jsonSkatImportSpecificTopicItemRecord;
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
	 * @param lineNumber
	 * @param avd
	 * @param opd
	 * @param appUser
	 * @param mode
	 * @return
	 */
	private String getRequestUrlKeyParametersUpdate(String lineNumber, String avd, String opd, SystemaWebUser appUser, String mode){
		StringBuffer urlRequestParamsKeys = new StringBuffer();
		
		urlRequestParamsKeys.append("user=" + appUser.getUser());
		urlRequestParamsKeys.append(SkatConstants.URL_CHAR_DELIMETER_FOR_PARAMS_WITH_HTML_REQUEST + "avd=" + avd);
		urlRequestParamsKeys.append(SkatConstants.URL_CHAR_DELIMETER_FOR_PARAMS_WITH_HTML_REQUEST + "opd=" + opd);
		urlRequestParamsKeys.append(SkatConstants.URL_CHAR_DELIMETER_FOR_PARAMS_WITH_HTML_REQUEST + "lin=" + lineNumber);
		urlRequestParamsKeys.append(SkatConstants.URL_CHAR_DELIMETER_FOR_PARAMS_WITH_HTML_REQUEST + "mode=" + mode);
		
		return urlRequestParamsKeys.toString();
	}

	/**
	 * 
	 * @param dkiv_42
	 * @param appUser
	 * @param request
	 * @return
	 */
	private String getRequestUrlKeyParametersForTollvaerdiTransportCosts(String dkiv_42, SystemaWebUser appUser, HttpServletRequest request ){
		StringBuffer urlRequestParamsKeys = new StringBuffer();
		
		//Toldværdideklaration fields necessary only after the create-new line (not on successive updates)
		String session_dkih_12 = request.getParameter("session_dkih_12");
		String session_dkih_12e = request.getParameter("session_dkih_12e");
		String session_dkih_222 = request.getParameter("session_dkih_222");

		
		urlRequestParamsKeys.append("user=" + appUser.getUser());
		urlRequestParamsKeys.append(SkatConstants.URL_CHAR_DELIMETER_FOR_PARAMS_WITH_HTML_REQUEST + "dkiv_42=" + dkiv_42);
		urlRequestParamsKeys.append(SkatConstants.URL_CHAR_DELIMETER_FOR_PARAMS_WITH_HTML_REQUEST + "dkih_12=" + session_dkih_12);
		urlRequestParamsKeys.append(SkatConstants.URL_CHAR_DELIMETER_FOR_PARAMS_WITH_HTML_REQUEST + "dkih_12e=" + session_dkih_12e);
		urlRequestParamsKeys.append(SkatConstants.URL_CHAR_DELIMETER_FOR_PARAMS_WITH_HTML_REQUEST + "dkih_222=" + session_dkih_222);
		
		return urlRequestParamsKeys.toString();
	}
	
	/**
	 * 
	 * @param appUser
	 * @param model
	 * @param headerRecord
	 * 
	 */
	private void setCodeDropDownMgr(SystemaWebUser appUser, Map model, JsonSkatImportSpecificTopicRecord headerRecord){
	    	//values for map 
		Map map = new HashMap();
		map.put("dkih_25", headerRecord.getDkih_25());
		map.put("dkih_26", headerRecord.getDkih_26());
		
			this.codeDropDownMgr.populateCodesHtmlDropDownsFromJsonString(this.urlCgiProxyService, this.skatDropDownListPopulationService,
						model,appUser,CodeDropDownMgr.CODE_001_ANGIVELSESARTER, null, null,null);
	
	    	this.codeDropDownMgr.populateCodesHtmlDropDownsFromJsonString(this.urlCgiProxyService, this.skatDropDownListPopulationService,
						model,appUser,CodeDropDownMgr.CODE_005_CERTIFIKAT_R44_2, null, null,null);
	    	
	    	this.codeDropDownMgr.populateCodesHtmlDropDownsFromJsonString(this.urlCgiProxyService, this.skatDropDownListPopulationService,
    						model,appUser,CodeDropDownMgr.CODE_006_TOLDSTED, null, null,null);
	    	
	    	this.codeDropDownMgr.populateCodesHtmlDropDownsFromJsonString(this.urlCgiProxyService, this.skatDropDownListPopulationService,
						model,appUser,CodeDropDownMgr.CODE_007_EMBALLAGE_R31, null, null,null);
	    	
	    	this.codeDropDownMgr.populateCodesHtmlDropDownsFromJsonString(this.urlCgiProxyService, this.skatDropDownListPopulationService,
					 	model,appUser,CodeDropDownMgr.CODE_008_COUNTRY, null, null,null);
			
	    	this.codeDropDownMgr.populateCodesHtmlDropDownsFromJsonString(this.urlCgiProxyService, this.skatDropDownListPopulationService,
    						model,appUser,CodeDropDownMgr.CODE_009_OPLYSNINGSTYPE_R44_9, null, null,null);
		
		this.codeDropDownMgr.populateCodesHtmlDropDownsFromJsonString(this.urlCgiProxyService, this.skatDropDownListPopulationService,
						model,appUser,CodeDropDownMgr.CODE_011_PROCEDURE_R37, null, null,null);
		
		this.codeDropDownMgr.populateCodesHtmlDropDownsFromJsonString(this.urlCgiProxyService, this.skatDropDownListPopulationService,
						model,appUser,CodeDropDownMgr.CODE_012_PDOKUMENTATIONSKODER_R44_4, null, null,null);
		
		this.codeDropDownMgr.populateCodesHtmlDropDownsFromJsonString(this.urlCgiProxyService, this.skatDropDownListPopulationService,
						model,appUser,CodeDropDownMgr.CODE_013_PREFERENCE_R36, null, null,null);
		
		this.codeDropDownMgr.populateCodesHtmlDropDownsFromJsonString(this.urlCgiProxyService, this.skatDropDownListPopulationService,
				 		model,appUser,CodeDropDownMgr.CODE_015_STATUS_KODER, null, null,null);
		
		this.codeDropDownMgr.populateCodesHtmlDropDownsFromJsonString(this.urlCgiProxyService, this.skatDropDownListPopulationService,
						model,appUser,CodeDropDownMgr.CODE_017_TRANSPORTDOK_SUMMARISKA_R40, null, headerRecord.getDkih_26(), map);
		
		this.codeDropDownMgr.populateCodesHtmlDropDownsFromJsonString(this.urlCgiProxyService, this.skatDropDownListPopulationService,
						model,appUser,CodeDropDownMgr.CODE_019_VAB_CERTIFIKAT_R44_3, null, null, null);
		
		this.codeDropDownMgr.populateCodesHtmlDropDownsFromJsonString(this.urlCgiProxyService, this.skatDropDownListPopulationService,
						model,appUser,CodeDropDownMgr.CODE_020_CURRENCY, null, null, null);
	
		this.codeDropDownMgr.populateCodesHtmlDropDownsFromJsonString(this.urlCgiProxyService, this.skatDropDownListPopulationService,
					 	model,appUser,CodeDropDownMgr.CODE_021_SUPP_VAREOPL_R44_6, null, null, null );
		
		this.codeDropDownMgr.populateCodesHtmlDropDownsFromJsonString(this.urlCgiProxyService, this.skatDropDownListPopulationService,
						model,appUser,CodeDropDownMgr.CODE_022_SUPP_ENHEDER_R41_1, null, null, null);
		
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
	
	
	
	@Qualifier ("skatImportDropDownListPopulationService")
	private SkatImportDropDownListPopulationService skatImportDropDownListPopulationService;
	@Autowired
	public void setSkatImportDropDownListPopulationService (SkatImportDropDownListPopulationService value){ this.skatImportDropDownListPopulationService=value; }
	public SkatImportDropDownListPopulationService getSkatImportDropDownListPopulationService(){return this.skatImportDropDownListPopulationService;}
	
	@Qualifier ("skatDropDownListPopulationService")
	private SkatDropDownListPopulationService skatDropDownListPopulationService;
	@Autowired
	public void setSkatDropDownListPopulationService (SkatDropDownListPopulationService value){ this.skatDropDownListPopulationService=value; }
	public SkatDropDownListPopulationService getSkatDropDownListPopulationService(){return this.skatDropDownListPopulationService;}
	
	@Qualifier ("skatImportSpecificTopicItemService")
	private SkatImportSpecificTopicItemService skatImportSpecificTopicItemService;
	@Autowired
	@Required
	public void setSkatImportSpecificTopicItemService (SkatImportSpecificTopicItemService value){ this.skatImportSpecificTopicItemService = value; }
	public SkatImportSpecificTopicItemService getSkatImportSpecificTopicItemService(){ return this.skatImportSpecificTopicItemService; }
	
	
	 
}

