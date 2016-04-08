package no.systema.skat.skatexport.controller;

import java.util.*;


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


import no.systema.skat.util.SkatConstants;
import no.systema.skat.url.store.SkatUrlDataStore;

import no.systema.skat.service.html.dropdown.SkatDropDownListPopulationService;
import no.systema.skat.skatexport.service.html.dropdown.SkatExportDropDownListPopulationService;
import no.systema.skat.skatexport.util.RpgReturnResponseHandler;
import no.systema.skat.skatexport.util.SkatExportTweaker;
//import no.systema.skat.skatimport.util.manager.TollvaerdideklarationMgr;

import no.systema.skat.service.SkatTaricVarukodService;
import no.systema.skat.model.jsonjackson.codes.JsonSkatTaricVarukodContainer;
import no.systema.skat.model.jsonjackson.codes.JsonSkatTaricVarukodRecord;

//EXPORT
import no.systema.skat.skatexport.url.store.SkatExportUrlDataStore;
import no.systema.skat.skatexport.service.SkatExportSpecificTopicItemService;
import no.systema.skat.skatexport.mapper.url.request.UrlRequestParameterMapper;
import no.systema.skat.skatexport.util.manager.CodeDropDownMgr;
import no.systema.skat.skatexport.validator.SkatExportItemsValidator;
import no.systema.skat.skatexport.model.jsonjackson.topic.JsonSkatExportSpecificTopicRecord;
import no.systema.skat.skatexport.model.jsonjackson.topic.items.JsonSkatExportSpecificTopicItemContainer;
import no.systema.skat.skatexport.model.jsonjackson.topic.items.JsonSkatExportSpecificTopicItemRecord;
import no.systema.skat.skatexport.util.SkatExportCalculator;
import no.systema.skat.skatexport.util.manager.SkatExportItemsAutoControlMgr;
import no.systema.skat.util.SkatConstants;
import no.systema.skat.model.jsonjackson.codes.JsonSkatTaricVarukodRecord;
import no.systema.skat.model.jsonjackson.codes.JsonSkatTaricVarukodContainer;
import no.systema.skat.model.jsonjackson.codes.JsonSkatTaricVarukodRecord;




/**
 * SKAT Export create items gateway
 * 
 * @author oscardelatorre
 * @date Mar 17, 2014
 * 
 */

@Controller
//@SessionAttributes(AppConstants.SYSTEMA_WEB_USER_KEY)
@Scope("session")
public class SkatExportItemsController {
	private static final Logger logger = Logger.getLogger(SkatExportItemsController.class.getName());
	private static final JsonDebugger jsonDebugger = new JsonDebugger();
	private UrlRequestParameterMapper urlRequestParameterMapper = new UrlRequestParameterMapper();
	private CodeDropDownMgr codeDropDownMgr = new CodeDropDownMgr();
	private SkatExportCalculator skatExportCalculator = new SkatExportCalculator();
	private SkatExportTweaker skatExportTweaker = new SkatExportTweaker();
	
	private ModelAndView loginView = new ModelAndView("login");
	private final String NOT_FOUND = "NOT FOUND";
	private final String MATCH = "MATCH";
	
	
	@InitBinder
    protected void initBinder(WebDataBinder binder) {

    }
	
	
	/**
	 * 
	 * @param user
	 * @param result
	 * @param request
	 * @return
	 */
	@RequestMapping(value="skatexport_edit_items.do")
	public ModelAndView skatExportEditItem(@ModelAttribute ("record") JsonSkatExportSpecificTopicItemRecord recordToValidate, BindingResult bindingResult, HttpSession session, HttpServletRequest request){
		logger.info("Inside: skatExportEditItem");
		ModelAndView successView = new ModelAndView("skatexport_edit_items");
		RpgReturnResponseHandler rpgReturnResponseHandler = new RpgReturnResponseHandler();
		JsonSkatExportSpecificTopicItemRecord jsonSkatExportSpecificTopicItemRecord = null;
		
		SystemaWebUser appUser = (SystemaWebUser)session.getAttribute(AppConstants.SYSTEMA_WEB_USER_KEY);
		
		Map model = new HashMap();
		String urlRequestParamsKeys = null;
		//Catch required action (doFetch or doUpdate)
		String action = request.getParameter("action");
		logger.info("ACTION: " + action);
		
		if(appUser==null){
			return this.loginView;
		}else{
			appUser.setActiveMenu(SystemaWebUser.ACTIVE_MENU_SKAT_EXPORT);
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
			//this fragment gets some header fields needed for the validator
			JsonSkatExportSpecificTopicRecord headerRecord = (JsonSkatExportSpecificTopicRecord)session.getAttribute(SkatConstants.DOMAIN_RECORD_TOPIC_SKAT);
			logger.info("HeaderRecord dkeh_aart: " + headerRecord.getDkeh_aart());
			
			//this key is only used with a real Update. When empty it will be a signal for a CREATE NEW (Add)
			String lineNr = request.getParameter("dkev_syli");
			if(lineNr!=null && !"".equals(lineNr)){
				//nothing
			}else{
				//this branch is necessary in order to get the line Nr after a validation error (ref. below att bindingResult.hasErrors in this same method)
				lineNr = (String)session.getAttribute("dkev_syli_SESSION");
			}
			//this row counter(lastSelectedItemLineNumber) is only used to add aspects/behavior to the list of rows (color, scroll(top/down)etc
			String lastSelectedItemLineNumber = request.getParameter("lastSelectedItemLineNumber");
			
			model.put("avd", avd);
			model.put("opd", opd);
			model.put("sign", sign);
			model.put("refnr", refnr);
			model.put("status", status);
			model.put("datum", datum);
			
			if(SkatConstants.ACTION_UPDATE.equals(action)){
				
				//-----------
				//Validation
				//-----------
				//we must validate towards the back-end here in order to avoid injection problems in Validator
				//The validation routine for Taric Varukod pinpoints those input values in which the user HAVE NOT used the search routine
				String varukodValidNumber = this.getTaricVarukod(appUser.getUser(), recordToValidate.getDkev_331());
				logger.info("#############:" + varukodValidNumber);
				if(!this.MATCH.equals(varukodValidNumber)){
					recordToValidate.setDkev_331(null);
				}
				
				//put some header records in aux.attributes (in order to send to validator)... Add more if applicable
				recordToValidate.setHeader_dkeh_aart(headerRecord.getDkeh_aart());
				//recordToValidate.setExtraMangdEnhet(this.getMandatoryMangdEnhetDirective(appUser.getUser(), recordToValidate));
				
				
				SkatExportItemsValidator validator = new SkatExportItemsValidator();
				logger.info("Host via HttpServletRequest.getHeader('Host'): " + request.getHeader("Host"));
			    validator.validate(recordToValidate, bindingResult);
			    //check for ERRORS
				if(bindingResult.hasErrors()){
				    	logger.info("[ERROR Validation] Item Record does not validate)");
				    	logger.info("[INFO lineNr] " + lineNr);
				    	
				    	model.put("record", recordToValidate);
				    	if(lineNr!=null && !"".equals(lineNr)){
				    		logger.info("[INFO lineNr] ... filling old value: lineNr:" + lineNr);
				    		session.setAttribute("dkev_syli_SESSION", lineNr);
					    	recordToValidate.setDkev_syop(opd);
				    		recordToValidate.setDkev_syav(avd);
				    	}
			    }else{
					if(lineNr!=null && !"".equals(lineNr)){
						//clean
						session.removeAttribute("dkev_syli_SESSION");
				    	
						//-------
						//UPDATE
						//-------
						logger.info("UPDATE(only) ITEM (existent item) on process...");
						//take the rest from GUI.
						jsonSkatExportSpecificTopicItemRecord = new JsonSkatExportSpecificTopicItemRecord();
						ServletRequestDataBinder binder = new ServletRequestDataBinder(jsonSkatExportSpecificTopicItemRecord);
			            //binder.registerCustomEditor(...); // if needed
			            binder.bind(request);
			            
			            jsonSkatExportSpecificTopicItemRecord.setDkev_syli(lineNr);
			            jsonSkatExportSpecificTopicItemRecord.setDkev_syop(opd);
			            jsonSkatExportSpecificTopicItemRecord.setDkev_syav(avd);
			            //populate 401-2 values
			            this.set401And402Values(jsonSkatExportSpecificTopicItemRecord);
			            
					}else{
						//-------
						//CREATE
						//-------
						logger.info("CREATE and UPDATE on ITEM (new fresh item) on process...");
						//This means that the update will be done AFTER a creation of an empty record. All this in the same transaction. 2 STEPS involved: (1)create and (2)update
						//-----------------------------------------------------------------------------------------
						//STEP[1] Generate new Item line key seeds (avd,opd,syli) by creating an empty new record. 
						//		  This step is ONLY applicable for new item lines 
						//-------------------------------------------------------------------------------------------
						jsonSkatExportSpecificTopicItemRecord  = this.createNewItemKeySeeds(session, request, appUser);
						if(jsonSkatExportSpecificTopicItemRecord!=null){
							String newLineNr = jsonSkatExportSpecificTopicItemRecord.getDkev_syli();
							
							//take the rest from GUI.
							jsonSkatExportSpecificTopicItemRecord = new JsonSkatExportSpecificTopicItemRecord();
							ServletRequestDataBinder binder = new ServletRequestDataBinder(jsonSkatExportSpecificTopicItemRecord);
				            //binder.registerCustomEditor(...); // if needed
				            binder.bind(request);
				            
				            logger.info("[INFO] populate the dkiv_syli: " + newLineNr);
				            //put back the generated seed and the header keys (syop, syavd)
				            jsonSkatExportSpecificTopicItemRecord.setDkev_syli(newLineNr);
				            jsonSkatExportSpecificTopicItemRecord.setDkev_32(newLineNr);
				            jsonSkatExportSpecificTopicItemRecord.setDkev_syop(opd);
				            jsonSkatExportSpecificTopicItemRecord.setDkev_syav(avd);
				            //populate 401-2 values
				            this.set401And402Values(jsonSkatExportSpecificTopicItemRecord);
				            
						}else{
							isValidCreatedRecordTransactionOnRPG = false;
						}
						
					}
					//--------------------------------------------------
					//At this point we are ready to do an update
					//--------------------------------------------------
					if(isValidCreatedRecordTransactionOnRPG){
						//Extra check on [Invoice Due Amount (svev_fabl)] vs [Statistiskt varde (svev_stva)]
			            //if svev_stva = null then copy the svev_fabl (fakturabelopp)
			            //this.updateStatisticalValue(jsonTdsImportSpecificTopicItemRecord);
			            //this.updateTullvardeValue(jsonTdsImportSpecificTopicItemRecord);
			            
			            logger.info("[INFO] Valid STEP[1] Add Record(if applicable) successfully created, OK ");
						//---------------------------
						//get BASE URL = RPG-PROGRAM
			            //---------------------------
						String BASE_URL_UPDATE = SkatExportUrlDataStore.SKAT_EXPORT_BASE_UPDATE_SPECIFIC_TOPIC_ITEM_URL;
						logger.info("[INFO] UPDATE to be done with lineNr [dkiv_syli]: " + jsonSkatExportSpecificTopicItemRecord.getDkev_syli());
						
						urlRequestParamsKeys = this.getRequestUrlKeyParametersUpdate(jsonSkatExportSpecificTopicItemRecord.getDkev_syli(), avd, opd, appUser, SkatConstants.MODE_UPDATE);
						String urlRequestParamsTopicItem = this.urlRequestParameterMapper.getUrlParameterValidString((jsonSkatExportSpecificTopicItemRecord));
						//put the final valid param. string
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
					    		this.setFatalError(model, rpgReturnResponseHandler, jsonSkatExportSpecificTopicItemRecord);
					    		
					    	}else{
					    		//Update succefully done!
					    		logger.info("[INFO] Valid STEP[2] Update -- Record successfully updated, OK ");
					    		//put domain objects (it is not necessary since the fetch below (onFetch) will clean this up anyway)
					    	}
					}else{
						rpgReturnResponseHandler.setErrorMessage("[ERROR] FATAL on CREATE, at tuid, syop generation : " + rpgReturnResponseHandler.getErrorMessage());
						this.setFatalError(model, rpgReturnResponseHandler, jsonSkatExportSpecificTopicItemRecord);
					}
			    }
				
			}else if(SkatConstants.ACTION_DELETE.equals(action)){
				logger.info("[INFO] Delete record start process... ");
				String lineNrToDelete = request.getParameter("lin");
				
				//---------------------------
				//get BASE URL = RPG-PROGRAM
	            //---------------------------
				String BASE_URL_DELETE = SkatExportUrlDataStore.SKAT_EXPORT_BASE_UPDATE_SPECIFIC_TOPIC_ITEM_URL;
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
			    		this.setFatalError(model, rpgReturnResponseHandler, jsonSkatExportSpecificTopicItemRecord);
			    		
			    	}else{
			    		//Delete succefully done!
			    		logger.info("[INFO] Valid Delete -- Record successfully deleted, OK ");
			    	}
				
			}
			
			//FETCH the ITEM LIST of existent ITEMs for this TOPIC
			//---------------------------
			//get BASE URL = RPG-PROGRAM
            //---------------------------
			String BASE_URL_FETCH = SkatExportUrlDataStore.SKAT_EXPORT_BASE_FETCH_TOPIC_ITEMLIST_URL;
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
	    	//logger.info(jsonPayloadFetch);
	    	logger.info(Calendar.getInstance().getTime() +  " CGI-end timestamp");
	    	JsonSkatExportSpecificTopicItemContainer jsonSkatExportSpecificTopicItemContainer = this.skatExportSpecificTopicItemService.getSkatExportSpecificTopicItemContainer(jsonPayloadFetch);
	    	if(jsonSkatExportSpecificTopicItemContainer!=null){
			    Double calculatedItemLinesTotalAmount = this.skatExportCalculator.getItemLinesTotalAmount(jsonSkatExportSpecificTopicItemContainer);
			    Double diffItemLinesTotalAmountWithInvoiceTotalAmount = this.skatExportCalculator.getDiffBetweenCalculatedTotalAmountAndTotalAmount(invoiceTotalAmount, calculatedItemLinesTotalAmount);
			    logger.info("calculatedItemLinesTotalAmount:" + calculatedItemLinesTotalAmount);
			    logger.info("diffItemLinesTotalAmountWithInvoiceTotalAmount:" + diffItemLinesTotalAmountWithInvoiceTotalAmount);
			    jsonSkatExportSpecificTopicItemContainer.setCalculatedItemLinesTotalAmount(calculatedItemLinesTotalAmount);
			    jsonSkatExportSpecificTopicItemContainer.setDiffItemLinesTotalAmountWithInvoiceTotalAmount(diffItemLinesTotalAmountWithInvoiceTotalAmount);
			    //some aspects for GUI
			    jsonSkatExportSpecificTopicItemContainer.setLastSelectedItemLineNumber(lastSelectedItemLineNumber);
			    //logger.info("(B)##### lastSelectedItemLineNumber:" + lastSelectedItemLineNumber);
	    	}
	    	//drop downs populated from back-end
	    	this.setCodeDropDownMgr(appUser, model, headerRecord);
    		
    		//drop downs populated from a txt file
    		model.put(SkatConstants.RESOURCE_MODEL_KEY_BERAKNINGSENHET_LIST, this.skatExportDropDownListPopulationService.getBerakningsEnheterList());
    		this.setDomainObjectsForListInView(session, model, jsonSkatExportSpecificTopicItemContainer);
			
			successView.addObject("model",model);
			//successView.addObject(Constants.EDIT_ACTION_ON_TOPIC, Constants.ACTION_FETCH);
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
	@RequestMapping(value="skatexport_edit_items_autocontrol.do")
	public ModelAndView skatExportEditItemAutoControl(@ModelAttribute ("record") JsonSkatExportSpecificTopicItemRecord recordToValidate, BindingResult bindingResult, HttpSession session, HttpServletRequest request){
		logger.info("Inside: skatExportEditItemAutoControl");
		final String AUTO_CONTROL_ERROR_FLAG_VALUE = "X";
		final String ERROR_FRAME_STD_OUTPUT = "-------------------------------------------------------------";
		
		ModelAndView successView = null;
		RpgReturnResponseHandler rpgReturnResponseHandler = new RpgReturnResponseHandler();
		JsonSkatExportSpecificTopicItemRecord jsonSkatExportSpecificTopicItemRecord = null;
		
		SystemaWebUser appUser = (SystemaWebUser)session.getAttribute(AppConstants.SYSTEMA_WEB_USER_KEY);
		//this fragment gets some header fields needed for the validator
		JsonSkatExportSpecificTopicRecord headerRecord = (JsonSkatExportSpecificTopicRecord)session.getAttribute(SkatConstants.DOMAIN_RECORD_TOPIC_SKAT);
		
		Map model = new HashMap();
		String urlRequestParamsKeys = null;
		
		if(appUser==null){
			return this.loginView;
		}else{
			StringBuffer params = new StringBuffer();
			params.append("user=" + appUser.getUser() + "&avd=" + recordToValidate.getDkev_syav() + "&opd=" + recordToValidate.getDkev_syop());
			successView = new ModelAndView("redirect:skatexport_edit_items.do?" + params);
			
			//FETCH the ITEM LIST of existent ITEMs for this TOPIC
			//---------------------------
			//get BASE URL = RPG-PROGRAM
            //---------------------------
			String BASE_URL_FETCH = SkatExportUrlDataStore.SKAT_EXPORT_BASE_FETCH_TOPIC_ITEMLIST_URL;
			urlRequestParamsKeys = this.getRequestUrlKeyParameters(request, recordToValidate.getDkev_syav(), recordToValidate.getDkev_syop(), appUser);
			
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
	    	
	    	JsonSkatExportSpecificTopicItemContainer container = this.skatExportSpecificTopicItemService.getSkatExportSpecificTopicItemContainer(jsonPayloadFetch);
	    	if(container!=null){
	    		//INIT Autocontrol Manager...
	    		SkatExportItemsAutoControlMgr autoControlMgr = new SkatExportItemsAutoControlMgr(this.urlCgiProxyService, this.skatExportSpecificTopicItemService);
	    		
	    		//Iterate on every line to control the requirements
	    		for(JsonSkatExportSpecificTopicItemRecord record: container.getOrderList()){
	    			//Fill in Taric Values in the record to prepare for some validity checks
	    			boolean isBatch = true;
	    			this.backEndValidationOnTolltariff(appUser, headerRecord, record, isBatch);
	    			//Init record
	    			autoControlMgr.setRecord(record);
	    			autoControlMgr.setHeaderRecord(headerRecord);
	    			//DEBUG -->logger.info(record.getDkev_315());
	    			if(record!=null && record.isValidTolltariff()){
	    				//---------------------------
		    			//START with calculations
		    			//---------------------------
	    				String idDebug = record.getDkev_syli() + "-" + record.getDkev_331();
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
	    					autoControlMgr.checkProviantRestrictions();
	    					if(autoControlMgr.isValidRecord()){
	    						//Go to level 3
	    						//logger.info("level check (3) " + idDebug);
	    						autoControlMgr.checkSuppEnhetsvalue_41();
	        					if(autoControlMgr.isValidRecord()){
	    							//Go to level 4
		    						//logger.info("level check (4) " + idDebug);
		    						autoControlMgr.checkIdentAfOplag_49();
		    						if(autoControlMgr.isValidRecord()){
		    							//Go to level FINAL MandatoryFields (must be the last check)
			    						//Nothing more below this level. New requirements must be insert between previous level and this FINAL level!
			    						/*autoControlMgr.checkValidMandatoryFields();
										if(autoControlMgr.isValidRecord()){
											//Update in order to remove previous error flags, if any...
			    		    				autoControlMgr.updateItemWithAutoControlError(appUser.getUser(), null );
			    		    			}else{
			    		    				//Set error
			    		    				logger.info(ERROR_FRAME_STD_OUTPUT);
				    						logger.info("ERROR level (FINAL) - Mandatory Fields" + idDebug);
				    						logger.info(ERROR_FRAME_STD_OUTPUT);
				    						autoControlMgr.updateItemWithAutoControlError(appUser.getUser(), AUTO_CONTROL_ERROR_FLAG_VALUE);
			    		    			}*/
		    						}else{
		    							//Set error
		    							logger.info(ERROR_FRAME_STD_OUTPUT);
			    						logger.info("ERROR level (4) - checkIdentAfOplag_49" + idDebug);
			    						logger.info(ERROR_FRAME_STD_OUTPUT);
			    						autoControlMgr.updateItemWithAutoControlError(appUser.getUser(), AUTO_CONTROL_ERROR_FLAG_VALUE);
		    						}
	        					}else{
	        						//Set error
	        						logger.info(ERROR_FRAME_STD_OUTPUT);
	        						logger.info("ERROR level (3) - checkSuppEnhetsvalue_41" + idDebug);
	        						logger.info(ERROR_FRAME_STD_OUTPUT);
	        						autoControlMgr.updateItemWithAutoControlError(appUser.getUser(), AUTO_CONTROL_ERROR_FLAG_VALUE);
	        					}
	    					}else{
	    						//Set error
	    						logger.info(ERROR_FRAME_STD_OUTPUT);
	    						logger.info("ERROR level (2) - checkProviantRestrictions" + idDebug);
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
		    			String idDebug = record.getDkev_syli() + "-" + record.getDkev_331();
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
	private void backEndValidationOnTolltariff(SystemaWebUser appUser, JsonSkatExportSpecificTopicRecord headerRecord, JsonSkatExportSpecificTopicItemRecord record, boolean isBatch){
		//String ITEM_NR_SUFFIX_CHARACTERS_INVALID = "???";
		//we must validate towards the back-end here in order to avoid injection problems in Validator
		//The validation routine for Taric Varukod pinpoints those input values in which the user HAVE NOT used the search-taric-number routine
		JsonSkatTaricVarukodRecord jsonSkatTaricVarukodRecord = this.getTaricVarukod(appUser.getUser(), record.getDkev_331(), headerRecord.getDkeh_17a());
		if(jsonSkatTaricVarukodRecord!=null){
			//logger.info("VALID varukod...");
			//since the varukod is valid then we proceed to set more dependencies. This routine was not possible to implement as in TDS or SKAT
			this.setValuesOnRecordToValidate(jsonSkatTaricVarukodRecord, record, isBatch);
			
		}else{
			logger.info("INVALID varukod...");
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
	private JsonSkatTaricVarukodRecord getTaricVarukod(String applicationUser, String taricVarukod, String selkbCountryCode) {
		logger.info("Inside getTaricVarukod()");
		JsonSkatTaricVarukodRecord retval = null;
		
		String TYPE_IE = "E";
		try{
		  String BASE_URL = SkatUrlDataStore.SKAT_FETCH_TARIC_VARUKODER_ITEMS_URL;
		  String urlRequestParamsKeys = "user=" + applicationUser + "&ie=" + TYPE_IE + "&kod=" + taricVarukod + "&selkb=" + selkbCountryCode;
		  UrlCgiProxyService urlCgiProxyService = new UrlCgiProxyServiceImpl();
		  String jsonPayload = urlCgiProxyService.getJsonContent(BASE_URL, urlRequestParamsKeys);
		  JsonSkatTaricVarukodContainer container = this.skatTaricVarukodService.getContainer(jsonPayload);
		  if(container!=null){
			  for(JsonSkatTaricVarukodRecord record : container.getTariclist()){
				  //logger.info("MATCH on VAREKOD. !!!!: " + record.getTatanr());
				  //logger.info("MATCH on VAREBESKRIV. !!!!: " + record.getBeskr1());
				  retval = record;
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
	private void setValuesOnRecordToValidate(JsonSkatTaricVarukodRecord jsonSkatTaricVarukodRecord, JsonSkatExportSpecificTopicItemRecord recordToValidate, boolean isBatch){
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
	private void set401And402Values(JsonSkatExportSpecificTopicItemRecord jsonSkatExportSpecificTopicItemRecord){
		jsonSkatExportSpecificTopicItemRecord.setDkev_401a(this.skatExportTweaker.getSummariskAngivelse_401a(jsonSkatExportSpecificTopicItemRecord));
        jsonSkatExportSpecificTopicItemRecord.setDkev_402a(this.skatExportTweaker.getSummariskAngivelse_402a(jsonSkatExportSpecificTopicItemRecord));
        jsonSkatExportSpecificTopicItemRecord.setDkev_401b(this.skatExportTweaker.getSummariskAngivelse_401b(jsonSkatExportSpecificTopicItemRecord));
        jsonSkatExportSpecificTopicItemRecord.setDkev_402b(this.skatExportTweaker.getSummariskAngivelse_402b(jsonSkatExportSpecificTopicItemRecord));
        jsonSkatExportSpecificTopicItemRecord.setDkev_401c(this.skatExportTweaker.getSummariskAngivelse_401c(jsonSkatExportSpecificTopicItemRecord));
        jsonSkatExportSpecificTopicItemRecord.setDkev_402c(this.skatExportTweaker.getSummariskAngivelse_402c(jsonSkatExportSpecificTopicItemRecord));
        jsonSkatExportSpecificTopicItemRecord.setDkev_401d(this.skatExportTweaker.getSummariskAngivelse_401d(jsonSkatExportSpecificTopicItemRecord));
        jsonSkatExportSpecificTopicItemRecord.setDkev_402d(this.skatExportTweaker.getSummariskAngivelse_402d(jsonSkatExportSpecificTopicItemRecord));
        jsonSkatExportSpecificTopicItemRecord.setDkev_401e(this.skatExportTweaker.getSummariskAngivelse_401e(jsonSkatExportSpecificTopicItemRecord));
        jsonSkatExportSpecificTopicItemRecord.setDkev_402e(this.skatExportTweaker.getSummariskAngivelse_402e(jsonSkatExportSpecificTopicItemRecord));
        jsonSkatExportSpecificTopicItemRecord.setDkev_401f(this.skatExportTweaker.getSummariskAngivelse_401f(jsonSkatExportSpecificTopicItemRecord));
        jsonSkatExportSpecificTopicItemRecord.setDkev_402f(this.skatExportTweaker.getSummariskAngivelse_402f(jsonSkatExportSpecificTopicItemRecord));
        jsonSkatExportSpecificTopicItemRecord.setDkev_401g(this.skatExportTweaker.getSummariskAngivelse_401g(jsonSkatExportSpecificTopicItemRecord));
        jsonSkatExportSpecificTopicItemRecord.setDkev_402g(this.skatExportTweaker.getSummariskAngivelse_402g(jsonSkatExportSpecificTopicItemRecord));
        jsonSkatExportSpecificTopicItemRecord.setDkev_401h(this.skatExportTweaker.getSummariskAngivelse_401h(jsonSkatExportSpecificTopicItemRecord));
        jsonSkatExportSpecificTopicItemRecord.setDkev_402h(this.skatExportTweaker.getSummariskAngivelse_402h(jsonSkatExportSpecificTopicItemRecord));
        jsonSkatExportSpecificTopicItemRecord.setDkev_401i(this.skatExportTweaker.getSummariskAngivelse_401i(jsonSkatExportSpecificTopicItemRecord));
        jsonSkatExportSpecificTopicItemRecord.setDkev_402i(this.skatExportTweaker.getSummariskAngivelse_402i(jsonSkatExportSpecificTopicItemRecord));
        //
        jsonSkatExportSpecificTopicItemRecord.setDkev_401j(this.skatExportTweaker.getSummariskAngivelse_401j(jsonSkatExportSpecificTopicItemRecord));
        jsonSkatExportSpecificTopicItemRecord.setDkev_402j(this.skatExportTweaker.getSummariskAngivelse_402j(jsonSkatExportSpecificTopicItemRecord));
        jsonSkatExportSpecificTopicItemRecord.setDkev_401k(this.skatExportTweaker.getSummariskAngivelse_401k(jsonSkatExportSpecificTopicItemRecord));
        jsonSkatExportSpecificTopicItemRecord.setDkev_402k(this.skatExportTweaker.getSummariskAngivelse_402k(jsonSkatExportSpecificTopicItemRecord));
        jsonSkatExportSpecificTopicItemRecord.setDkev_401l(this.skatExportTweaker.getSummariskAngivelse_401l(jsonSkatExportSpecificTopicItemRecord));
        jsonSkatExportSpecificTopicItemRecord.setDkev_402l(this.skatExportTweaker.getSummariskAngivelse_402l(jsonSkatExportSpecificTopicItemRecord));
        jsonSkatExportSpecificTopicItemRecord.setDkev_401m(this.skatExportTweaker.getSummariskAngivelse_401m(jsonSkatExportSpecificTopicItemRecord));
        jsonSkatExportSpecificTopicItemRecord.setDkev_402m(this.skatExportTweaker.getSummariskAngivelse_402m(jsonSkatExportSpecificTopicItemRecord));
        jsonSkatExportSpecificTopicItemRecord.setDkev_401n(this.skatExportTweaker.getSummariskAngivelse_401n(jsonSkatExportSpecificTopicItemRecord));
        jsonSkatExportSpecificTopicItemRecord.setDkev_402n(this.skatExportTweaker.getSummariskAngivelse_402n(jsonSkatExportSpecificTopicItemRecord));
        jsonSkatExportSpecificTopicItemRecord.setDkev_401o(this.skatExportTweaker.getSummariskAngivelse_401o(jsonSkatExportSpecificTopicItemRecord));
        jsonSkatExportSpecificTopicItemRecord.setDkev_402o(this.skatExportTweaker.getSummariskAngivelse_402o(jsonSkatExportSpecificTopicItemRecord));
        jsonSkatExportSpecificTopicItemRecord.setDkev_401p(this.skatExportTweaker.getSummariskAngivelse_401p(jsonSkatExportSpecificTopicItemRecord));
        jsonSkatExportSpecificTopicItemRecord.setDkev_402p(this.skatExportTweaker.getSummariskAngivelse_402p(jsonSkatExportSpecificTopicItemRecord));
        jsonSkatExportSpecificTopicItemRecord.setDkev_401q(this.skatExportTweaker.getSummariskAngivelse_401q(jsonSkatExportSpecificTopicItemRecord));
        jsonSkatExportSpecificTopicItemRecord.setDkev_402q(this.skatExportTweaker.getSummariskAngivelse_402q(jsonSkatExportSpecificTopicItemRecord));
        jsonSkatExportSpecificTopicItemRecord.setDkev_401r(this.skatExportTweaker.getSummariskAngivelse_401r(jsonSkatExportSpecificTopicItemRecord));
        jsonSkatExportSpecificTopicItemRecord.setDkev_402r(this.skatExportTweaker.getSummariskAngivelse_402r(jsonSkatExportSpecificTopicItemRecord));
        jsonSkatExportSpecificTopicItemRecord.setDkev_401s(this.skatExportTweaker.getSummariskAngivelse_401s(jsonSkatExportSpecificTopicItemRecord));
        jsonSkatExportSpecificTopicItemRecord.setDkev_402s(this.skatExportTweaker.getSummariskAngivelse_402s(jsonSkatExportSpecificTopicItemRecord));
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
		
		String IMPORT_IE = "E";
		try{
		  String BASE_URL = SkatUrlDataStore.SKAT_FETCH_TARIC_VARUKODER_ITEMS_URL;
		  String urlRequestParamsKeys = "user=" + applicationUser + "&ie=" + IMPORT_IE + "&kod=" + taricVarukod;
		  UrlCgiProxyService urlCgiProxyService = new UrlCgiProxyServiceImpl();
		  String jsonPayload = urlCgiProxyService.getJsonContent(BASE_URL, urlRequestParamsKeys);
		  JsonSkatTaricVarukodContainer container = this.skatTaricVarukodService.getContainer(jsonPayload);
		  if(container!=null){
			  for(JsonSkatTaricVarukodRecord record : container.getTariclist()){
				  logger.info("MATCH on VARUKOD !!!!: " + record.getDktara02());
				  retval = this.MATCH;
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
		errorMetaInformation.append(rpgReturnResponseHandler.getDkev_syop());
		model.put(SkatConstants.ASPECT_ERROR_META_INFO, errorMetaInformation);
		
	}
	/**
	 * Sets domain objects
	 * @param model
	 * @param container
	 * @param session
	 * 
	 */
	private void setDomainObjectsForListInView(HttpSession session, Map model, JsonSkatExportSpecificTopicItemContainer container){
		List list = new ArrayList();
		if(container!=null){
			for (JsonSkatExportSpecificTopicItemRecord record : container.getOrderList()){
				list.add(record);
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
	private void setDomainObjectsInView(Map model, JsonSkatExportSpecificTopicItemRecord record){
		model.put(SkatConstants.DOMAIN_RECORD, record);
	}
	
	/**
	 * 
	 * @param model
	 * @param rpgReturnResponseHandler
	 * @param record
	 */
	private void setFatalError(Map model, RpgReturnResponseHandler rpgReturnResponseHandler, JsonSkatExportSpecificTopicItemRecord record){
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
	private JsonSkatExportSpecificTopicItemRecord createNewItemKeySeeds(HttpSession session, HttpServletRequest request, SystemaWebUser appUser){
		RpgReturnResponseHandler rpgReturnResponseHandler = new RpgReturnResponseHandler();
		//request variables
		String numberOfItemLinesInTopicStr = request.getParameter("numberOfItemLinesInTopic");
		if(numberOfItemLinesInTopicStr==null || "".equals(numberOfItemLinesInTopicStr)){
			numberOfItemLinesInTopicStr = "0";
		}
			
		String opd = request.getParameter("opd");
		String avd = request.getParameter("avd");
		
		JsonSkatExportSpecificTopicItemRecord jsonSkatExportSpecificTopicItemRecord = new JsonSkatExportSpecificTopicItemRecord();
		//---------------------------
		//get BASE URL = RPG-PROGRAM
        //---------------------------
		String BASE_URL = SkatExportUrlDataStore.SKAT_EXPORT_BASE_UPDATE_SPECIFIC_TOPIC_ITEM_URL;
		
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
			jsonSkatExportSpecificTopicItemRecord = null;
			
		}else{
			jsonSkatExportSpecificTopicItemRecord.setDkev_syop(rpgReturnResponseHandler.getDkev_syop());
			jsonSkatExportSpecificTopicItemRecord.setDkev_syli(rpgReturnResponseHandler.getDkev_syli());
		}
        
		return jsonSkatExportSpecificTopicItemRecord;
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
	 * @param lineNumber
	 * @param avd
	 * @param opd
	 * @param appUser
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
	 * @param appUser
	 * @param model
	 * @param headerRecord
	 * 
	 */
	private void setCodeDropDownMgr(SystemaWebUser appUser, Map model, JsonSkatExportSpecificTopicRecord headerRecord){
	    	//general code population
		this.codeDropDownMgr.populateCodesHtmlDropDownsFromJsonString(this.urlCgiProxyService, this.skatDropDownListPopulationService,
				model,appUser,CodeDropDownMgr.CODE_008_COUNTRY, null, null);

		this.codeDropDownMgr.populateCodesHtmlDropDownsFromJsonString(this.urlCgiProxyService, this.skatDropDownListPopulationService,
				model,appUser,CodeDropDownMgr.CODE_017_TRANSPORTDOK_SUMMARISKA_R40, null, headerRecord.getDkeh_26());

		this.codeDropDownMgr.populateCodesHtmlDropDownsFromJsonString(this.urlCgiProxyService, this.skatDropDownListPopulationService,
				model,appUser,CodeDropDownMgr.CODE_022_SUPP_ENHEDER, null, null);

		this.codeDropDownMgr.populateCodesHtmlDropDownsFromJsonString(this.urlCgiProxyService, this.skatDropDownListPopulationService,
				model,appUser,CodeDropDownMgr.CODE_110_EMBALLAGE_R31, null, null);
		
		this.codeDropDownMgr.populateCodesHtmlDropDownsFromJsonString(this.urlCgiProxyService, this.skatDropDownListPopulationService,
				model,appUser,CodeDropDownMgr.CODE_112_PROCEDURE_R37, null, null);

		this.codeDropDownMgr.populateCodesHtmlDropDownsFromJsonString(this.urlCgiProxyService, this.skatDropDownListPopulationService,
				model,appUser,CodeDropDownMgr.CODE_113_CERTIFIKAT_R44_2, null, null);
		
		this.codeDropDownMgr.populateCodesHtmlDropDownsFromJsonString(this.urlCgiProxyService, this.skatDropDownListPopulationService,
				model,appUser,CodeDropDownMgr.CODE_114_VAB_CERTIFIKAT_R44_3, null, null);
		
		this.codeDropDownMgr.populateCodesHtmlDropDownsFromJsonString(this.urlCgiProxyService, this.skatDropDownListPopulationService,
				model,appUser,CodeDropDownMgr.CODE_115_FN_FARLIG_GODS_R44_4, null, null);
		
		this.codeDropDownMgr.populateCodesHtmlDropDownsFromJsonString(this.urlCgiProxyService, this.skatDropDownListPopulationService,
				model,appUser,CodeDropDownMgr.CODE_116_TRANSPORTDOK_TYPE_R44_5_1, null, null);
		
		this.codeDropDownMgr.populateCodesHtmlDropDownsFromJsonString(this.urlCgiProxyService, this.skatDropDownListPopulationService,
				model,appUser,CodeDropDownMgr.CODE_124_SUPPL_ENHEDER_YM, null, null);
		
		this.codeDropDownMgr.populateCodesHtmlDropDownsFromJsonString(this.urlCgiProxyService, this.skatDropDownListPopulationService,
				 model,appUser,CodeDropDownMgr.CODE_127_STATUS_KODER, null, null);
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
	
	
	
	@Qualifier ("skatExportDropDownListPopulationService")
	private SkatExportDropDownListPopulationService skatExportDropDownListPopulationService;
	@Autowired
	public void setSkatExportDropDownListPopulationService (SkatExportDropDownListPopulationService value){ this.skatExportDropDownListPopulationService=value; }
	public SkatExportDropDownListPopulationService getSkatExportDropDownListPopulationService(){return this.skatExportDropDownListPopulationService;}
	
	@Qualifier ("skatDropDownListPopulationService")
	private SkatDropDownListPopulationService skatDropDownListPopulationService;
	@Autowired
	public void setSkatDropDownListPopulationService (SkatDropDownListPopulationService value){ this.skatDropDownListPopulationService=value; }
	public SkatDropDownListPopulationService getSkatDropDownListPopulationService(){return this.skatDropDownListPopulationService;}
	
	@Qualifier ("skatExportSpecificTopicItemService")
	private SkatExportSpecificTopicItemService skatExportSpecificTopicItemService;
	@Autowired
	@Required
	public void setSkatExportSpecificTopicItemService (SkatExportSpecificTopicItemService value){ this.skatExportSpecificTopicItemService = value; }
	public SkatExportSpecificTopicItemService getSkatExportSpecificTopicItemService(){ return this.skatExportSpecificTopicItemService; }
	
	
	 
}

