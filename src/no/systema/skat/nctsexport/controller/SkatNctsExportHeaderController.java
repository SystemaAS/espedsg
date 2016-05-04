package no.systema.skat.nctsexport.controller;

import java.util.*;

import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.springframework.validation.BindingResult;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Required;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Scope;

import javax.annotation.PostConstruct;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.web.bind.ServletRequestDataBinder;
import org.springframework.web.bind.WebDataBinder;


//application imports
import no.systema.main.service.UrlCgiProxyService;
import no.systema.main.service.UrlCgiProxyServiceImpl;
import no.systema.main.util.AppConstants;
import no.systema.main.util.JavaReflectionUtil;
import no.systema.main.util.JsonDebugger;
import no.systema.main.util.NumberFormatterLocaleAware;

import no.systema.skat.model.jsonjackson.avdsignature.JsonSkatAvdelningContainer;
import no.systema.skat.model.jsonjackson.avdsignature.JsonSkatAvdelningRecord;
import no.systema.skat.model.jsonjackson.avdsignature.JsonSkatSignatureContainer;
import no.systema.skat.model.jsonjackson.avdsignature.JsonSkatSignatureRecord;

import no.systema.skat.service.html.dropdown.SkatDropDownListPopulationService;
import no.systema.skat.skatexport.model.jsonjackson.topic.JsonSkatExportSpecificTopicContainer;
import no.systema.skat.skatexport.model.jsonjackson.topic.JsonSkatExportSpecificTopicRecord;
import no.systema.skat.skatexport.model.jsonjackson.topic.JsonSkatExportTopicInvoiceContainer;
import no.systema.skat.skatexport.model.jsonjackson.topic.JsonSkatExportTopicInvoiceRecord;
import no.systema.skat.skatexport.model.jsonjackson.topic.items.JsonSkatExportSpecificTopicItemContainer;
import no.systema.skat.skatexport.model.jsonjackson.topic.items.JsonSkatExportSpecificTopicItemRecord;
import no.systema.skat.skatexport.service.SkatExportSpecificTopicItemService;
import no.systema.skat.skatexport.service.SkatExportSpecificTopicService;
import no.systema.skat.skatexport.url.store.SkatExportUrlDataStore;
import no.systema.skat.nctsexport.model.jsonjackson.topic.JsonSkatNctsExportSpecificTopicContainer;
import no.systema.skat.nctsexport.model.jsonjackson.topic.JsonSkatNctsExportTopicCopiedFromTransportUppdragContainer;
import no.systema.skat.nctsexport.validator.SkatNctsExportHeaderValidator;

import no.systema.main.model.SystemaWebUser;
import no.systema.skat.nctsexport.model.jsonjackson.topic.JsonSkatNctsExportSpecificTopicContainer;
import no.systema.skat.nctsexport.model.jsonjackson.topic.JsonSkatNctsExportSpecificTopicRecord;
import no.systema.skat.nctsexport.model.jsonjackson.topic.JsonSkatNctsExportTopicCopiedContainer;
import no.systema.skat.nctsexport.model.topic.SkatNctsExportSpecificTopicTotalItemLinesObject;
import no.systema.skat.nctsexport.model.jsonjackson.topic.items.JsonSkatNctsExportSpecificTopicItemContainer;
import no.systema.skat.nctsexport.model.jsonjackson.topic.items.JsonSkatNctsExportSpecificTopicItemRecord;

import no.systema.skat.nctsexport.service.SkatNctsExportSpecificTopicService;
import no.systema.skat.nctsexport.service.SkatNctsExportSpecificTopicItemService;

import no.systema.skat.nctsexport.service.html.dropdown.SkatNctsExportDropDownListPopulationService;
import no.systema.skat.nctsexport.url.store.SkatNctsExportUrlDataStore;
import no.systema.skat.nctsexport.util.RpgReturnResponseHandler;
import no.systema.skat.nctsexport.util.manager.CodeDropDownMgr;
import no.systema.skat.url.store.SkatUrlDataStore;
import no.systema.skat.util.SkatConstants;
import no.systema.skat.nctsexport.mapper.url.request.UrlRequestParameterMapper;

/**
 * NCTS Export Topic Controller 
 * 
 * @author oscardelatorre
 * @date Apr 16, 2014
 * 
 */
@Controller
@Scope("session")
public class SkatNctsExportHeaderController {
	private static final JsonDebugger jsonDebugger = new JsonDebugger();
	private static final Logger logger = Logger.getLogger(SkatNctsExportHeaderController.class.getName());
	private UrlRequestParameterMapper urlRequestParameterMapper = new UrlRequestParameterMapper();
	private ModelAndView loginView = new ModelAndView("login");
	private CodeDropDownMgr codeDropDownMgr = new CodeDropDownMgr();
	private NumberFormatterLocaleAware numberFormatter = new NumberFormatterLocaleAware();
	
	private ApplicationContext context;
	
	@InitBinder
    protected void initBinder(WebDataBinder binder) {
		//binder.setValidator(new NctsExportValidator()); //it must have spring form tags in the html otherwise = meaningless
    }
	
	@PostConstruct
	public void initIt() throws Exception {
		if("DEBUG".equals(AppConstants.LOG4J_LOGGER_LEVEL)){
			logger.setLevel(Level.DEBUG);
		}
	}
	/**
	 * Renders the create GUI view (without any logic)
	 * 
	 * @param session
	 * @param request
	 * @return
	 */
	@RequestMapping(value="skatnctsexport_edit.do",  params="action=doPrepareCreate", method={RequestMethod.GET, RequestMethod.POST })
	public ModelAndView doPrepareCreate(HttpSession session, HttpServletRequest request){
		
		Map model = new HashMap();
		SystemaWebUser appUser = (SystemaWebUser)session.getAttribute(AppConstants.SYSTEMA_WEB_USER_KEY);
		//String messageFromContext = this.context.getMessage("user.label",new Object[0], request.getLocale());
		ModelAndView successView = new ModelAndView("skatnctsexport_edit");
		logger.info("Method: doPrepareCreate [RequestMapping-->skatnctsexport_edit.do]");
		//check user (should be in session already)
		if(appUser==null){
			return loginView;
			
		}else{
            //add gui lists here
    		this.setCodeDropDownMgr(appUser, model);	
    		this.populateAvdelningHtmlDropDownsFromJsonString(model, appUser);
    		this.populateSignatureHtmlDropDownsFromJsonString(model, appUser);
    		//domain
    		successView.addObject(SkatConstants.DOMAIN_MODEL, model);
    		successView.addObject(SkatConstants.EDIT_ACTION_ON_TOPIC, SkatConstants.ACTION_CREATE);
		}
		
		return successView;
	}
	
	/**
	 * Creates or Updates a new Topic (Arende)
	 * @param recordToValidate
	 * @param bindingResult
	 * @param session
	 * @param request
	 * @return
	 */
	
	
	@RequestMapping(value="skatnctsexport_edit.do")
	public ModelAndView doNctsExportEdit(@ModelAttribute ("record") JsonSkatNctsExportSpecificTopicRecord recordToValidate, BindingResult bindingResult, HttpSession session, HttpServletRequest request){
		
		ModelAndView successView = new ModelAndView("skatnctsexport_edit");
		String method = "doNctsExportEdit [RequestMapping-->skatnctsexport_edit.do]";
		RpgReturnResponseHandler rpgReturnResponseHandler = new RpgReturnResponseHandler();
		SystemaWebUser appUser = (SystemaWebUser)session.getAttribute(AppConstants.SYSTEMA_WEB_USER_KEY);
		logger.info("Method: " + method);
		
		SkatNctsExportSpecificTopicTotalItemLinesObject totalItemLinesObject = new SkatNctsExportSpecificTopicTotalItemLinesObject();
		//---------------------------------
		//Crucial request parameters (Keys
		//---------------------------------
		String action = request.getParameter("action");
		String opd = request.getParameter("opd");
		String avd = request.getParameter("avd");
		String sign = request.getParameter("sign");
		String dkxh_0035 = request.getParameter("dkxh_0035"); //test indicator (only in production)
		
		
		//Action (doFetch, doUpdate, doCreate)
		logger.info("Action:" + action);
		logger.info("Opd:" + opd);
		Map model = new HashMap();
		
		if(appUser==null){
			return this.loginView;
		}else{
			if(action!=null){
				//get some item lines total fields (∑)
				totalItemLinesObject = this.getRequiredSumsInItemLines(avd, opd, appUser);
				//-------------
				//FETCH RECORD
				//-------------
				if(SkatConstants.ACTION_FETCH.equals(action)){
					logger.info("FETCH record transaction...");
					//---------------------------
					//get BASE URL = RPG-PROGRAM
		            //---------------------------
					String BASE_URL = SkatNctsExportUrlDataStore.NCTS_EXPORT_BASE_FETCH_SPECIFIC_TOPIC_URL;
					//url params
					String urlRequestParamsKeys = this.getRequestUrlKeyParameters(action, avd, opd, sign, appUser);
					//for debug purposes in GUI
					session.setAttribute(SkatConstants.ACTIVE_URL_RPG_SKAT, BASE_URL  + "==>params: " + urlRequestParamsKeys.toString()); 
					
					logger.info(Calendar.getInstance().getTime() + " CGI-start timestamp");
			    	logger.info("URL: " + BASE_URL);
			    	logger.info("URL PARAMS: " + urlRequestParamsKeys);
			    	//--------------------------------------
			    	//EXECUTE the FETCH (RPG program) here
			    	//--------------------------------------
			    	String jsonPayload = this.urlCgiProxyService.getJsonContent(BASE_URL, urlRequestParamsKeys);
					//Debug --> 
			    	logger.debug(jsonDebugger.debugJsonPayloadWithLog4J(jsonPayload));
			    	logger.info(Calendar.getInstance().getTime() +  " CGI-end timestamp");
			    	if(jsonPayload!=null){
			    		JsonSkatNctsExportSpecificTopicContainer jsonNctsExportSpecificTopicContainer = this.skatNctsExportSpecificTopicService.getNctsExportSpecificTopicContainer(jsonPayload);
			    		this.fetchSecurityRecord(jsonNctsExportSpecificTopicContainer, appUser.getUser(), avd, opd );
			    		//add gui lists here
			    		this.setCodeDropDownMgr(appUser, model);	
			    		this.setDomainObjectsInView(session, model, jsonNctsExportSpecificTopicContainer, totalItemLinesObject);
			    		successView.addObject(SkatConstants.DOMAIN_MODEL, model);
			    		//put the doUpdate action since we are preparing the record for an update (when saving)
			    		successView.addObject(SkatConstants.EDIT_ACTION_ON_TOPIC, SkatConstants.ACTION_UPDATE);
			    		
			    	}else{
			    		logger.fatal("NO CONTENT on jsonPayload from URL... ??? <Null>");
			    		return loginView;
			    	}
		    	//----------------------------
				//CREATE and/or UPDATE RECORD
				//----------------------------	
				}else if(SkatConstants.ACTION_UPDATE.equals(action)){
					boolean isValidCreatedRecordTransactionOnRPG = true;
					//---------------------
					//Validation Light GUI
					//---------------------
					SkatNctsExportHeaderValidator validator = new SkatNctsExportHeaderValidator();
					logger.info("VALIDATING...");
					if(opd!=null && !"".equals(opd)){
						//Update...
						//nothing
					}else{
						logger.info("avdXX: " + avd);
						logger.info("signXX: " + sign);
						
						//Create
						//we must lend these dropdown variables to the validation object
						recordToValidate.setThavd(avd);
						recordToValidate.setThsg(sign);
						
					}
					validator.validate(recordToValidate, bindingResult);
					//test indicator in validation field
					recordToValidate.setDkxh_0035(dkxh_0035);
					
				    //check for ERRORS
					if(bindingResult.hasErrors()){
					    	logger.info("[ERROR Validation] Record does not validate)");
					    	//put domain objects and do go back to the original view...
					    	recordToValidate.setThavd(avd);
					    	recordToValidate.setThsg(sign);
					    	this.setDomainObjectsInView(session, model, recordToValidate,totalItemLinesObject);
					    	isValidCreatedRecordTransactionOnRPG = false;
					    	if(opd==null || "".equals(opd)){
					    		action = SkatConstants.ACTION_CREATE;
					    	}
				    }else{
				    	
				    		JsonSkatNctsExportSpecificTopicRecord jsonNctsExportSpecificTopicRecord = null;
						String tuid = null;
						
						if(opd!=null && !"".equals(opd)){
							logger.info("PURE UPDATE transaction...");
							//PURE UDPATE transaction
							//this means that the update is an update of an existing record
							jsonNctsExportSpecificTopicRecord = new JsonSkatNctsExportSpecificTopicRecord();
							ServletRequestDataBinder binder = new ServletRequestDataBinder(jsonNctsExportSpecificTopicRecord);
							
				            binder.bind(request);
				            //test indicator in validation field
				            jsonNctsExportSpecificTopicRecord.setDkxh_0035(dkxh_0035);
				            //binder.registerCustomEditor(...); // if needed
							logger.info("THNAS [after bind]: " + jsonNctsExportSpecificTopicRecord.getThnas());
							logger.info("THTUID [after bind]: " + jsonNctsExportSpecificTopicRecord.getThtuid());
							//update sum attributes
							totalItemLinesObject = this.getRequiredSumsInItemLines(avd, opd, appUser);
							//We do fetch this number here in order to update the recordToValidate (for validation purposes) 
							recordToValidate.setSumOfAntalKolliInItemLines(totalItemLinesObject.getSumOfAntalKolliInItemLines());
							recordToValidate.setSumOfAntalItemLines(totalItemLinesObject.getSumOfAntalItemLines());
						
				            
						}else{
							logger.info("CREATE NEW follow by UDATE transaction...");
							//CREATE AND UPDATE transaction
							//This means that the update will be done AFTER a creation of an empty record. All this in the same transaction. 2 STEPS involved: (1)create and (2)update
							//---------------------------------------------------------------------------------------------
							//STEP[1] Generate new Topic key seeds (avd,opd,sign,tullid) by creating an empty new record. 
							//---------------------------------------------------------------------------------------------
							jsonNctsExportSpecificTopicRecord = this.createNewTopicHeaderKeySeeds(session, request, appUser, avd, sign);
							if(jsonNctsExportSpecificTopicRecord!=null){
								//avd = jsonNctsExportSpecificTopicRecord.getThavd();
								//sign = jsonNctsExportSpecificTopicRecord.getThsg();
								opd = jsonNctsExportSpecificTopicRecord.getThtdn();
								tuid = jsonNctsExportSpecificTopicRecord.getThtuid();
								
								//take the rest from GUI.
								jsonNctsExportSpecificTopicRecord = new JsonSkatNctsExportSpecificTopicRecord();
								ServletRequestDataBinder binder = new ServletRequestDataBinder(jsonNctsExportSpecificTopicRecord);
					            //binder.registerCustomEditor(...); // if needed
					            binder.bind(request);
					            
					            //Now set back with the generated values since the bind method above erases them...
					            jsonNctsExportSpecificTopicRecord.setThavd(avd);
					            jsonNctsExportSpecificTopicRecord.setThtdn(opd);
					            jsonNctsExportSpecificTopicRecord.setThsg(sign);
					            jsonNctsExportSpecificTopicRecord.setThtuid(tuid);
					            //test indicator in validation field
					            jsonNctsExportSpecificTopicRecord.setDkxh_0035(dkxh_0035);
								
							}else{
								//Some kind of error occurred. Set the transaction as invalid...
								isValidCreatedRecordTransactionOnRPG = false;
							}
						}
						//--------------------------------------------------
						//At this point we are ready to do an update
						//--------------------------------------------------
						if(isValidCreatedRecordTransactionOnRPG){
				            //---------------------------
							//get BASE URL = RPG-PROGRAM
				            //---------------------------
							String BASE_URL = SkatNctsExportUrlDataStore.NCTS_EXPORT_BASE_UPDATE_SPECIFIC_TOPIC_URL;
							
							//-----------------------------------
							//add URL-parameter "mode=U" (Update)
							//-----------------------------------
							String urlRequestParamsKeys = this.getRequestUrlKeyParameters(action, avd, opd, sign, appUser);
							//build the url parameters (from GUI-form) to send on a GET/POST method AFTER the keyParameters
							String urlRequestParamsTopic = this.urlRequestParameterMapper.getUrlParameterValidString((jsonNctsExportSpecificTopicRecord));
							//put the final valid param. string
							String urlRequestParams = urlRequestParamsKeys + urlRequestParamsTopic;
							//for debug purposes in GUI
							session.setAttribute(SkatConstants.ACTIVE_URL_RPG_SKAT, BASE_URL); 
					    	
							logger.info(Calendar.getInstance().getTime() + " CGI-start timestamp");
					    	logger.info("URL: " + BASE_URL);
					    	logger.info("URL PARAMS: " + urlRequestParams);
					    	//----------------------------------------------------------------------------
					    	//EXECUTE the UPDATE (RPG program) here (STEP [2] when creating a new record)
					    	//----------------------------------------------------------------------------
					    	String rpgReturnPayload = this.urlCgiProxyService.getJsonContent(BASE_URL, urlRequestParams);
							//Debug --> 
					    	logger.info("Checking errMsg in rpgReturnPayload" + rpgReturnPayload);
					    	//we must evaluate a return RPG code in order to know if the Update was OK or not
					    	rpgReturnResponseHandler.evaluateRpgResponseOnTopicUpdate(rpgReturnPayload);
					    	if(rpgReturnResponseHandler.getErrorMessage()!=null && !"".equals(rpgReturnResponseHandler.getErrorMessage())){
					    		rpgReturnResponseHandler.setErrorMessage("[ERROR] FATAL on UPDATE: " + rpgReturnResponseHandler.getErrorMessage());
					    		this.setFatalError(model, rpgReturnResponseHandler, jsonNctsExportSpecificTopicRecord);
					    		isValidCreatedRecordTransactionOnRPG = false;
					    	}else{
					    		//Update succefully done!
					    		logger.info("[INFO] Record successfully updated, OK ");
					    		
					    		//------------------------------------
					    		//Update/Create now Sikkerhet record
					    		//------------------------------------
					    		//Check if there is a record minimum of information in order to go on with security record - update
					    		if(this.isQualifiedForUpdateSecurityRecord(jsonNctsExportSpecificTopicRecord)){
						    		String mode = SkatConstants.MODE_ADD; //Add - default
						    		if(this.existsSecurityRecord(appUser.getUser(), avd, opd)){
						    			mode = SkatConstants.MODE_UPDATE; //Update
						    		}
						    		if(!this.updateSecurityRecord(jsonNctsExportSpecificTopicRecord, mode, appUser.getUser(), avd, opd)){
						    			this.setFatalError(model, rpgReturnResponseHandler, jsonNctsExportSpecificTopicRecord);
							    		isValidCreatedRecordTransactionOnRPG = false;	
						    		}
					    		}
					    		//put domain objects
					    		this.setDomainObjectsInView(session, model, jsonNctsExportSpecificTopicRecord,totalItemLinesObject);
					    	}
						}else{
							rpgReturnResponseHandler.setErrorMessage("[ERROR] FATAL on CREATE, at tuid, syop generation : " + rpgReturnResponseHandler.getErrorMessage());
							this.setFatalError(model, rpgReturnResponseHandler, jsonNctsExportSpecificTopicRecord);
							isValidCreatedRecordTransactionOnRPG = false;
						}
				    }
					
		            //add gui lists here
					this.setCodeDropDownMgr(appUser, model);	
					this.populateAvdelningHtmlDropDownsFromJsonString(model, appUser);
					this.populateSignatureHtmlDropDownsFromJsonString(model, appUser);
					//this.populateCodesHtmlDropDownsFromJsonString(model, appUser, JsonSkatNctsCodeContainer.KOD_DEKL_TYP);
					
					successView.addObject("model" , model);
					successView.addObject(SkatConstants.DOMAIN_MODEL, model);
		            //Edit or Create offset
				    	if(isValidCreatedRecordTransactionOnRPG){
			            	successView.addObject(SkatConstants.EDIT_ACTION_ON_TOPIC, SkatConstants.ACTION_UPDATE);
		            }else{
		            		//Validation errors have been generated and we must offset to some state (set or changed above in some flow)
			            	successView.addObject(SkatConstants.EDIT_ACTION_ON_TOPIC, action);
		            }
					
				//------------------------
				//CREATE-ADD [PURE] RECORD
				//-------------------------
				}else if(SkatConstants.ACTION_CREATE.equals(action)){
					//OBSOLETE
					//This action is not used as an isolated create. It is realized in the UPDATE above in 2 transactions
					//Could be needed in the future.
					
				//------------------
				//REMOVE RECORD
				//------------------	
				}else if(SkatConstants.ACTION_DELETE.equals(action)){
					//Remove Topic
					//Could be delete OR set a remove status...(no physical delete)
					//TODO
				}
			}
			
	    	return successView;
		}
	}
	/**
	 * 
	 * @param jsonNctsExportSpecificTopicRecord
	 * @return
	 */
	private boolean isQualifiedForUpdateSecurityRecord(JsonSkatNctsExportSpecificTopicRecord jsonNctsExportSpecificTopicRecord){
		boolean retval = false;
		if(jsonNctsExportSpecificTopicRecord.getThdta()!=null && !"".equals(jsonNctsExportSpecificTopicRecord.getThdta())){
			retval = true;
		}
		if(jsonNctsExportSpecificTopicRecord.getThtkbm()!=null && !"".equals(jsonNctsExportSpecificTopicRecord.getThtkbm())){
			retval = true;
		}
		return retval;
	}
	/**
	 * 
	 * @param session
	 * @param request
	 * @return
	 */
	@RequestMapping(value="skatnctsexport_send.do")
	public ModelAndView doNctsExportSend(HttpSession session, HttpServletRequest request){
		
		SystemaWebUser appUser = (SystemaWebUser)session.getAttribute(AppConstants.SYSTEMA_WEB_USER_KEY);
		appUser.setActiveMenu(SystemaWebUser.ACTIVE_MENU_SKAT_NCTS_EXPORT);
		ModelAndView successView = new ModelAndView("redirect:skatnctsexport.do?action=doFind&sign=" + appUser.getSkatSign());
		RpgReturnResponseHandler rpgReturnResponseHandler = new RpgReturnResponseHandler();
		
		//---------------------------------
		//Crucial request parameters (Keys
		//---------------------------------
		String action = SkatConstants.ACTION_SEND;
		String opd = request.getParameter("opd");
		String avd = request.getParameter("avd");
		String sign = request.getParameter("sign");
		
		//Action (doFetch, doUpdate, doCreate)
		logger.info("Action:" + action);
		Map model = new HashMap();
		
		
		if(appUser==null){
			return this.loginView;
		}else{
		    //---------------------------
			//get BASE URL = RPG-PROGRAM
            //---------------------------
			String BASE_URL = SkatNctsExportUrlDataStore.NCTS_EXPORT_BASE_UPDATE_SPECIFIC_TOPIC_URL;
			
			//-----------------------------------
			//add URL-parameter "mode=S" (Send)
			//-----------------------------------
			String urlRequestParamsKeys = this.getRequestUrlKeyParameters(action, avd, opd, sign, appUser);
			//there is only key parameters in doSend. No other topic (record) specific parameters from GUI or such
			String urlRequestParams = urlRequestParamsKeys;
			//for debugging purposes
			session.setAttribute(SkatConstants.ACTIVE_URL_RPG_SKAT, BASE_URL); 
			
			logger.info(Calendar.getInstance().getTime() + " CGI-start timestamp");
		    	logger.info("URL: " + BASE_URL);
		    	logger.info("URL PARAMS: " + urlRequestParams);
		    	//----------------------------------------------------------------------------
		    	//EXECUTE the UPDATE (RPG program) here (STEP [2] when creating a new record)
		    	//----------------------------------------------------------------------------
		    	String rpgReturnPayload = this.urlCgiProxyService.getJsonContent(BASE_URL, urlRequestParams);
				//Debug --> 
		    	logger.info("Checking errMsg in rpgReturnPayload" + rpgReturnPayload);
		    	//we must evaluate a return RPG code in order to know if the Update was OK or not
		    	rpgReturnResponseHandler.evaluateRpgResponseOnTopicUpdate(rpgReturnPayload);
		    	if(rpgReturnResponseHandler.getErrorMessage()!=null && !"".equals(rpgReturnResponseHandler.getErrorMessage())){
		    		rpgReturnResponseHandler.setErrorMessage("[ERROR] FATAL on UPDATE: " + rpgReturnResponseHandler.getErrorMessage());
		    		//this.setFatalError(model, rpgReturnResponseHandler, jsonTdsExportSpecificTopicRecord);
		    		//TODO ERROR HANDLING HERE... stay in the same page ?
		    	}else{
		    		//Update succefully done!
		    		logger.info("[INFO] Record successfully sent [changed status], OK ");
		    		//put domain objects
		    		//this.setDomainObjectsInView(session, model, jsonTdsExportSpecificTopicRecord);
		    		//TODO SUCCESS should stay at the same side or not? Right now we go to the list of topics
		    	}
			
		}
		return successView;
	}
	
	
	/**
	 * Prints a specific topic
	 * @param session
	 * @param request
	 * @return
	 */
	@RequestMapping(value="skatncts_export_edit_printTopic.do",  method={RequestMethod.GET, RequestMethod.POST })
	public ModelAndView doNctsExportEditPrintTopic(HttpSession session, HttpServletRequest request){
		Map model = new HashMap();
		SystemaWebUser appUser = (SystemaWebUser)session.getAttribute(AppConstants.SYSTEMA_WEB_USER_KEY);
		ModelAndView successView = new ModelAndView("redirect:skatnctsexport.do?action=doFind&sign=" + appUser.getSkatSign());
		
		String method = "doNctsExportEditPrintTopic [RequestMapping-->skatncts_export_edit_printTopic.do]";
		logger.info("Method: [RequestMapping-->skatncts_export_edit_printTopic.do]");
		String opd = request.getParameter("opd");
		String avd = request.getParameter("avd");
		
		
		//check user (should be in session already)
		if(appUser==null){
			return loginView;
		}else{
			//-------------------------------------
			//get BASE URL = RPG-PROGRAM for PRINT
            //-------------------------------------
			String BASE_URL = SkatNctsExportUrlDataStore.NCTS_EXPORT_BASE_PRINT_FOR_SPECIFIC_TOPIC_URL;
			//url params
			String urlRequestParamsKeys = this.getRequestUrlKeyParametersForPrint( avd, opd, appUser);
			//for debug purposes in GUI
			session.setAttribute(SkatConstants.ACTIVE_URL_RPG_SKAT, BASE_URL  + "==>params: " + urlRequestParamsKeys.toString()); 
			
			logger.info(Calendar.getInstance().getTime() + " CGI-start timestamp");
		    	logger.info("URL: " + BASE_URL);
		    	logger.info("URL PARAMS: " + urlRequestParamsKeys);
		    	//--------------------------------------
		    	//EXECUTE the Print (RPG program) here
		    	//--------------------------------------
		    	String jsonPayload = this.urlCgiProxyService.getJsonContent(BASE_URL, urlRequestParamsKeys);
			//Debug --> 
		    	logger.debug(jsonDebugger.debugJsonPayloadWithLog4J(jsonPayload));
		    	logger.info(Calendar.getInstance().getTime() +  " CGI-end timestamp");
		    //END of PRINT here and now
		    		
		}
		
		return successView;
	}
	
	
	/**
	 *
	 * Copies one topic(ärende) to a new one (clones the source topic)
	 * STEP 1: Copy by getting JSON with the new record (new opd, new avd, new sign)
	 * STEP 2: Fetch the record as if it was a selection of a topic in a list
	 * 
	 * @param session
	 * @param request
	 * @return
	 */
	
	@RequestMapping(value="skatnctsexport_copyTopic.do", method={RequestMethod.POST} )
	public ModelAndView doCopyTopic( HttpSession session, HttpServletRequest request){
		SystemaWebUser appUser = (SystemaWebUser)session.getAttribute(AppConstants.SYSTEMA_WEB_USER_KEY);
		ModelAndView successView = new ModelAndView("skatnctsexport_edit");
		ModelAndView fallbackOnErrorView = new ModelAndView("redirect:skatnctsexport.do?action=doFind&sign=" + appUser.getSkatSign());
		
		JsonSkatNctsExportTopicCopiedContainer jsonNctsExportTopicCopiedContainer = null;
		String method = "doCopyTopic [RequestMapping-->skatnctsexport_copyTopic.do]";
		logger.info("Method: " + method);
		Map model = new HashMap();
		
		//We must get all parameters from the enumeration since all have sequence counter number
		String action=null;
		String avd=null;
		String opd=null;
		String newAvd=null;
		String newSign=null;
		
		Enumeration requestParameters = request.getParameterNames();
	    while (requestParameters.hasMoreElements()) {
	        String element = (String) requestParameters.nextElement();
	        String value = request.getParameter(element);

	        if (element != null && value != null) {
	        		logger.info("####################################################");
        			logger.info("param Name : " + element + " value: " + value);
        			if(element.startsWith("originalAvd")){
        				avd = value;
        			}else if(element.startsWith("originalOpd")){
        				opd = value;
        			}else if(element.startsWith("newAvd")){
        				newAvd = value;
        			}else if(element.startsWith("newSign")){
        				newSign = value;
        			}else if(element.startsWith("action")){
        				action = value;
        			}
        		}
	    	}
	    
	
		//check user (should be in session already)
		if(appUser==null){
			return loginView;
		}else{
			//--------------------
			//STEP 1: COPY record
			//--------------------
			logger.info("starting COPY record transaction...");
			String BASE_URL = SkatNctsExportUrlDataStore.NCTS_EXPORT_BASE_UPDATE_SPECIFIC_TOPIC_URL;
			String urlRequestParamsKeys = this.getRequestUrlKeyParametersForCopy(avd, newAvd, newSign, opd, appUser);
			//for debug purposes in GUI
			session.setAttribute(SkatConstants.ACTIVE_URL_RPG_SKAT, BASE_URL  + "==>params: " + urlRequestParamsKeys.toString()); 
			
			logger.info(Calendar.getInstance().getTime() + " CGI-start timestamp");
	    	logger.info("URL: " + BASE_URL);
	    	logger.info("URL PARAMS: " + urlRequestParamsKeys);
	    	//--------------------------------------
	    	//EXECUTE (RPG program) here
	    	//--------------------------------------
	    	String jsonPayload = this.urlCgiProxyService.getJsonContent(BASE_URL, urlRequestParamsKeys);
			//Debug --> 
	    	logger.debug(jsonDebugger.debugJsonPayloadWithLog4J(jsonPayload));
	    	logger.info(Calendar.getInstance().getTime() +  " CGI-end timestamp");
	    	if(jsonPayload!=null){
	    		jsonNctsExportTopicCopiedContainer = this.skatNctsExportSpecificTopicService.getNctsExportTopicCopiedContainer(jsonPayload);
	    		if(jsonNctsExportTopicCopiedContainer!=null){
	    			//Check for errors
	    			if(jsonNctsExportTopicCopiedContainer.getErrMsg()!=null && !"".equals(jsonNctsExportTopicCopiedContainer.getErrMsg())){
	    				logger.fatal("[ERROR FATAL] errMsg containing: " + jsonNctsExportTopicCopiedContainer.getErrMsg());
	    				return fallbackOnErrorView;
	    			}
	    		}
	    	}else{
	    		logger.fatal("NO CONTENT on jsonPayload from URL... ??? <Null>");
	    		return loginView;
			}
			
	    	//At this point we do now have a cloned record with its own data. The only thing left is to present it in edit mode
	    	//--------------------
			//STEP 2: FETCH record
			//--------------------
			logger.info("starting FETCH record transaction...");
			//---------------------------
			//get BASE URL = RPG-PROGRAM
            //---------------------------
			BASE_URL = SkatNctsExportUrlDataStore.NCTS_EXPORT_BASE_FETCH_SPECIFIC_TOPIC_URL;
			//url params
			urlRequestParamsKeys = this.getRequestUrlKeyParameters(action, jsonNctsExportTopicCopiedContainer.getNewavd(), jsonNctsExportTopicCopiedContainer.getNewopd(), jsonNctsExportTopicCopiedContainer.getNewsign(), appUser);
			//for debug purposes in GUI
			session.setAttribute(SkatConstants.ACTIVE_URL_RPG_SKAT, BASE_URL  + "==>params: " + urlRequestParamsKeys.toString()); 
			
			logger.info(Calendar.getInstance().getTime() + " CGI-start timestamp");
	    	logger.info("URL: " + BASE_URL);
	    	logger.info("URL PARAMS: " + urlRequestParamsKeys);
	    	//--------------------------------------
	    	//EXECUTE the FETCH (RPG program) here
	    	//--------------------------------------
	    	jsonPayload = this.urlCgiProxyService.getJsonContent(BASE_URL, urlRequestParamsKeys);
			//Debug --> 
	    	logger.debug(jsonDebugger.debugJsonPayloadWithLog4J(jsonPayload));
	    	logger.info(Calendar.getInstance().getTime() +  " CGI-end timestamp");
	    	if(jsonPayload!=null){
	    		JsonSkatNctsExportSpecificTopicContainer jsonNctsExportSpecificTopicContainer = this.skatNctsExportSpecificTopicService.getNctsExportSpecificTopicContainer(jsonPayload);
	    		//add gui lists here
	    		this.setCodeDropDownMgr(appUser, model);	
	    		
	    		this.setDomainObjectsInView(session, model, jsonNctsExportSpecificTopicContainer );
	    		successView.addObject(SkatConstants.DOMAIN_MODEL, model);
			//put the doUpdate action since we are preparing the record for an update (when saving)
			successView.addObject(SkatConstants.EDIT_ACTION_ON_TOPIC, SkatConstants.ACTION_UPDATE);
	    		
	    	}else{
	    		logger.fatal("NO CONTENT on jsonPayload from URL... ??? <Null>");
	    		return loginView;
			}
			return successView;
		}
		
	}
	
	/**
	 * 
	 * @param session
	 * @param request
	 * @return
	 */
	@RequestMapping(value="skatnctsexport_doFetchTopicFromTransportUppdrag.do", method={RequestMethod.POST} )
	public ModelAndView doFetchTopicFromTransportUppdrag( HttpSession session, HttpServletRequest request){
		JsonSkatNctsExportTopicCopiedFromTransportUppdragContainer jsonContainer = null;
		SystemaWebUser appUser = (SystemaWebUser)session.getAttribute(AppConstants.SYSTEMA_WEB_USER_KEY);
		ModelAndView successView = new ModelAndView("skatnctsexport_edit");
		//fallback view (usually on errors)
		ModelAndView fallbackView = new ModelAndView("skatnctsexport_edit");
		fallbackView.addObject("action", "doPrepareCreate");
		//this view is when the end user choose not to copy at all. He/She will start from scratch (empty form (header))
		ModelAndView cleanNewView = new ModelAndView("redirect:skatnctsexport_edit.do?action=doPrepareCreate");
		
		String method = "[RequestMapping-->skatnctsexport_doFetchTopicFromTransportUppdrag.do]";
		logger.info("Method: " + method);
		Map model = new HashMap();
		
		//We must get all parameters from the enumeration since all have sequence counter number
		String action=request.getParameter("actionGS");;
		String avd=request.getParameter("selectedAvd");
		String opd=request.getParameter("selectedOpd");
		String extRefNr=request.getParameter("selectedExtRefNr"); //Domino ref in Dachser DK AS
		
		//check user (should be in session already)
		if(appUser==null){
			return loginView;
		}else{
			
			if( (extRefNr!=null && !"".equals(extRefNr)) || ( (opd!=null && !"".equals(opd)) && (avd!=null && !"".equals(avd))) ){
				//--------------------
				//STEP 1: CLONE record
				//--------------------
				logger.info("starting PROCESS record transaction...");
				String BASE_URL = SkatNctsExportUrlDataStore.NCTS_EXPORT_BASE_UPDATE_SPECIFIC_TOPIC_URL;
				String urlRequestParamsKeys = this.getRequestUrlKeyParametersForCopyTopicFromTransportUppdrag(avd, opd, extRefNr, appUser);
				//for debug purposes in GUI
				session.setAttribute(SkatConstants.ACTIVE_URL_RPG_SKAT, BASE_URL  + "==>params: " + urlRequestParamsKeys.toString()); 
				
				logger.info(Calendar.getInstance().getTime() + " CGI-start timestamp");
		    	logger.info("URL: " + BASE_URL);
		    	logger.info("URL PARAMS: " + urlRequestParamsKeys);
		    	//--------------------------------------
		    	//EXECUTE (RPG program) here
		    	//--------------------------------------
		    	String jsonPayload = this.urlCgiProxyService.getJsonContent(BASE_URL, urlRequestParamsKeys);
		    	//Debug --> 
		    	logger.debug(jsonDebugger.debugJsonPayloadWithLog4J(jsonPayload));
		    	logger.info(Calendar.getInstance().getTime() +  " CGI-end timestamp");
		    	if(jsonPayload!=null){
		    		jsonContainer = this.skatNctsExportSpecificTopicService.getSkatNctsExportTopicCopiedFromTransportUppdragContainer(jsonPayload);
		    		if(jsonContainer!=null){
		    			//Check for errors
		    			if(jsonContainer.getErrMsg()!=null && !"".equals(jsonContainer.getErrMsg())){
		    				logger.info("[WARN] errMsg containing: " + jsonContainer.getErrMsg());
		    				logger.info("[WARN] redirecting to doPrepareCreate");
		    				//Send the error message to the redirect view.
		    				//request.setAttribute("errorMessageOnCopyFromTransportOppdrag", jsonContainer.getErrMsg());
		    				model.put(SkatConstants.ASPECT_ERROR_MESSAGE, jsonContainer.getErrMsg());
		    				model.put(SkatConstants.ASPECT_ERROR_META_INFO, "Vid kopiering av TransportUppdrag...");
		    				fallbackView.addObject(SkatConstants.DOMAIN_MODEL, model);
		    				
		    				return fallbackView;
		    			}else{
		    				//At this point we do have a cloned record ready to be presented.
		    				//Before we do present this record we must create a quasi-item line such as the requirement demand
		    				JsonSkatNctsExportSpecificTopicItemRecord nctsExportTargetItemRecord = new JsonSkatNctsExportSpecificTopicItemRecord();
		    				this.initDefaultFirstItemLine(nctsExportTargetItemRecord, appUser.getUser(),jsonContainer.getThavd(), jsonContainer.getThtdn());
		    				this.createDefaultFirstItemLine(nctsExportTargetItemRecord, appUser.getUser(),jsonContainer.getThavd(), jsonContainer.getThtdn());
		    			}
		    		}
		    	}else{
		    		logger.fatal("NO CONTENT on jsonPayload from URL... ??? <Null>");
					return loginView;
				}    
				
		    	//At this point we do now have a cloned record with its own data. The only thing left is to present it in edit mode
		    	//--------------------
				//STEP 2: FETCH record
				//--------------------
				logger.info("starting FETCH record transaction...");
				//---------------------------
				//get BASE URL = RPG-PROGRAM
	            //---------------------------
				BASE_URL = SkatNctsExportUrlDataStore.NCTS_EXPORT_BASE_FETCH_SPECIFIC_TOPIC_URL;
				//url params
				urlRequestParamsKeys = this.getRequestUrlKeyParameters(action, jsonContainer.getThavd(), jsonContainer.getThtdn(), jsonContainer.getThsg(), appUser);
				//for debug purposes in GUI
				session.setAttribute(SkatConstants.ACTIVE_URL_RPG_SKAT, BASE_URL  + "==>params: " + urlRequestParamsKeys.toString()); 
				
				logger.info(Calendar.getInstance().getTime() + " CGI-start timestamp");
		    	logger.info("URL: " + BASE_URL);
		    	logger.info("URL PARAMS: " + urlRequestParamsKeys);
		    	//--------------------------------------
		    	//EXECUTE the FETCH (RPG program) here
		    	//--------------------------------------
		    	jsonPayload = this.urlCgiProxyService.getJsonContent(BASE_URL, urlRequestParamsKeys);
				//Debug --> 
		    	logger.debug(jsonDebugger.debugJsonPayloadWithLog4J(jsonPayload));
		    	logger.info(Calendar.getInstance().getTime() +  " CGI-end timestamp");
		    	if(jsonPayload!=null){
		    		JsonSkatNctsExportSpecificTopicContainer jsonSpecificTopicContainer = this.skatNctsExportSpecificTopicService.getNctsExportSpecificTopicContainer(jsonPayload);
	    			//populate gui
		    		this.setCodeDropDownMgr(appUser, model);	
		    		this.setDomainObjectsInView(session, model, jsonSpecificTopicContainer);
		    		successView.addObject(SkatConstants.DOMAIN_MODEL, model);
		    		//put the doUpdate action since we are preparing the record for an update (when saving)
		    		successView.addObject(SkatConstants.EDIT_ACTION_ON_TOPIC, SkatConstants.ACTION_UPDATE);	
		    	}else{
		    		logger.fatal("[ERROR fatal] NO CONTENT on jsonPayload from URL... ??? <Null>");
		    		return loginView;
		    	}
			}else{
				logger.warn("[INFO] Opdnr is NULL. Redirecting to: skatnctsexport_edit.do?action=doPrepareCreate... ");
				//return new ModelAndView("redirect:tdsimport_edit.do?action=doPrepareCreate");
				return cleanNewView;
			}			
			return successView;
		}
	}
	
	/**
	 * 
	 * @param session
	 * @param request
	 * @return
	 * 
	 */
	@RequestMapping(value="skatnctsexport_updateStatus.do")
	public ModelAndView doUpdateStatus(HttpSession session, HttpServletRequest request){
		SystemaWebUser appUser = (SystemaWebUser)session.getAttribute(AppConstants.SYSTEMA_WEB_USER_KEY);
		appUser.setActiveMenu(SystemaWebUser.ACTIVE_MENU_SKAT_NCTS_EXPORT);
		ModelAndView successView = new ModelAndView("redirect:skatnctsexport.do?action=doFind&sign=" + appUser.getSkatSign());
		
		RpgReturnResponseHandler rpgReturnResponseHandler = new RpgReturnResponseHandler();
		
		//---------------------------------
		//Crucial request parameters (Keys
		//---------------------------------
		String opd = request.getParameter("currentOpd");
		String avd = request.getParameter("currentAvd");
		String newStatus = request.getParameter("selectedStatus");
		Map model = new HashMap();
		
		if(appUser==null){
			return this.loginView;
		}else{
			
			//---------------------------
			//get BASE URL = RPG-PROGRAM
            //---------------------------
			String BASE_URL = SkatNctsExportUrlDataStore.NCTS_EXPORT_BASE_UPDATE_STATUS_URL;
			
			//-------------------
			//add URL-parameter 
			//-------------------
			String urlRequestParamsKeys = this.getRequestUrlKeyParametersForUpdateStatus(avd, opd, newStatus, appUser);
			//there are only key parameters in doSend. No other topic (record) specific parameters from GUI or such
			String urlRequestParams = urlRequestParamsKeys;
			
			logger.info(Calendar.getInstance().getTime() + " CGI-start timestamp");
		    	logger.info("URL: " + BASE_URL);
		    	logger.info("URL PARAMS: " + urlRequestParams);
		    	//----------------------------------------------------------------------------
		    	//EXECUTE the UPDATE (RPG program) here (STEP [2] when creating a new record)
		    	//----------------------------------------------------------------------------
		    	String rpgReturnPayload = this.urlCgiProxyService.getJsonContent(BASE_URL, urlRequestParams);
			//Debug --> 
		    	logger.info("Checking errMsg in rpgReturnPayload" + rpgReturnPayload);
		    	//we must evaluate a return RPG code in order to know if the Update was OK or not
		    	rpgReturnResponseHandler.evaluateRpgResponseOnTopicUpdate(rpgReturnPayload);
		    	if(rpgReturnResponseHandler.getErrorMessage()!=null && !"".equals(rpgReturnResponseHandler.getErrorMessage())){
		    		rpgReturnResponseHandler.setErrorMessage("[ERROR] FATAL on UPDATE: " + rpgReturnResponseHandler.getErrorMessage());
		    		//TODO ERROR HANDLING HERE... stay in the same page ?
		    	}else{
		    		//Update succefully done!
		    		logger.info("[INFO] Status successfully updated [changed status], OK ");
		    		//put domain objects
		    		//this.setDomainObjectsInView(session, model, jsonTdsImportSpecificTopicRecord);
		    		//TODO SUCCESS should stay at the same side or not? Right now we go to the list of topics
		    	}
		}
		return successView;
	}
	
	/**
	 * 
	 * @param nctsExportTargetItemRecord
	 * @param applicationUser
	 * @param avd
	 * @param opd
	 */
	private void initDefaultFirstItemLine(JsonSkatNctsExportSpecificTopicItemRecord nctsExportTargetItemRecord, String applicationUser, String avd, String opd){
		//------------------------
		//STEP(1) - Header values
		//------------------------
		String BASE_URL = SkatExportUrlDataStore.SKAT_EXPORT_BASE_FETCH_SPECIFIC_TOPIC_URL;
		//url params
		String urlRequestParamsKeys = "user=" + applicationUser + "&avd=" + avd + "&opd=" + opd; // + "&xref=" + xref;
		//for debug purposes in GUI
		 
		logger.info(Calendar.getInstance().getTime() + " CGI-start timestamp");
		logger.info("URL: " + BASE_URL);
		logger.info("URL PARAMS: " + urlRequestParamsKeys);
   	 	
		String jsonPayload = this.urlCgiProxyService.getJsonContent(BASE_URL, urlRequestParamsKeys);
		//Debug --> 
		logger.info(Calendar.getInstance().getTime() +  " CGI-end timestamp");
		if(jsonPayload!=null){
			JsonSkatExportSpecificTopicContainer container = this.skatExportSpecificTopicService.getSkatExportSpecificTopicContainer(jsonPayload);
			if(container!=null && container.getOneorder()!=null){
				for(JsonSkatExportSpecificTopicRecord record : container.getOneorder()){
					//Debug
					//logger.info(record.getDkeh_17a());
					nctsExportTargetItemRecord.setTvalk("DK");
					nctsExportTargetItemRecord.setTvdty("830");
					nctsExportTargetItemRecord.setTvblk(record.getDkeh_17a());
					
 			  	}
	   		}
	   	 }
		//---------------------------
		//STEP(2) - Item line values
		//---------------------------
		BASE_URL = SkatExportUrlDataStore.SKAT_EXPORT_BASE_FETCH_TOPIC_ITEMLIST_URL;
			
		logger.info(Calendar.getInstance().getTime() + " CGI-start timestamp");
		logger.info("FETCH av item list... ");
		logger.info("URL: " + BASE_URL);
		logger.info("URL PARAMS: " + urlRequestParamsKeys);
		//--------------------------------------
		//EXECUTE the FETCH (RPG program) here
		//--------------------------------------
		String jsonPayloadFetch = this.urlCgiProxyService.getJsonContent(BASE_URL, urlRequestParamsKeys);
		 //Debug --> 
		 logger.debug(jsonDebugger.debugJsonPayloadWithLog4J(jsonPayloadFetch));
		 //logger.info(Calendar.getInstance().getTime() +  " CGI-end timestamp");
		 if(jsonPayloadFetch!=null){
			 JsonSkatExportSpecificTopicItemContainer container = this.skatExportSpecificTopicItemService.getSkatExportSpecificTopicItemContainer(jsonPayloadFetch);
			 if(container!=null){
				 for(JsonSkatExportSpecificTopicItemRecord record : container.getOrderList()){
					 //Debug
					 nctsExportTargetItemRecord.setTvvnt(record.getDkev_331());
					 nctsExportTargetItemRecord.setTvvt(record.getDkev_315());
					 //logger.info("Varenr:" + nctsExportTargetItemRecord.getTvvnt());
					 //logger.info("Varebesk.:" + nctsExportTargetItemRecord.getTvvt());
					 nctsExportTargetItemRecord.setTvvktb(record.getDkev_35());
					 nctsExportTargetItemRecord.setTvvktn(record.getDkev_38());
					 break; //only the first item line (if any)
				 }
			 }
		 }	
	}
	/**
	 * 
	 * @param nctsExportTargetItemRecord
	 * @param applicationUser
	 * @param avd
	 * @param opd
	 */
	private void createDefaultFirstItemLine(JsonSkatNctsExportSpecificTopicItemRecord nctsExportTargetItemRecord, String applicationUser, String avd, String opd){
		
		JsonSkatNctsExportSpecificTopicItemRecord tmpRecord  = this.createNewItemKeySeeds(applicationUser, avd, opd);
		if(tmpRecord!=null){
			String newLineNr = tmpRecord.getTvli();
			logger.info("[INFO] New LineNr:" + newLineNr);
			
			//take the rest from GUI.
			nctsExportTargetItemRecord.setTvli(newLineNr);
			nctsExportTargetItemRecord.setTvtdn(opd);
			nctsExportTargetItemRecord.setTvavd(avd);
			logger.info("[INFO] New line number (on item record):" + nctsExportTargetItemRecord.getTvli());
            //some mandatory validation
			this.validateDefaultNctsItemNr(nctsExportTargetItemRecord);
            logger.info("[INFO] Varubeskrivning (on item record):" + nctsExportTargetItemRecord.getTvvt());
            logger.info("[INFO] Varekode (on item record):" + nctsExportTargetItemRecord.getTvvnt());
            logger.info("[INFO] Bruttov. (on item record):" + nctsExportTargetItemRecord.getTvvktb());
			logger.info("[INFO] Nettov. (on item record):" + nctsExportTargetItemRecord.getTvvktn());
			logger.info("[INFO] Avs.land (on item record):" + nctsExportTargetItemRecord.getTvalk());
			logger.info("[INFO] Best.land (on item record):" + nctsExportTargetItemRecord.getTvblk());
			logger.info("[INFO] 44.Doc.type (on item record):" + nctsExportTargetItemRecord.getTvdty());
			//
			String BASE_URL_UPDATE = SkatNctsExportUrlDataStore.NCTS_EXPORT_BASE_UPDATE_SPECIFIC_TOPIC_ITEM_URL;
			StringBuffer urlRequestParamsKeys = new StringBuffer();
			urlRequestParamsKeys.append("user=" + applicationUser);
			urlRequestParamsKeys.append(SkatConstants.URL_CHAR_DELIMETER_FOR_PARAMS_WITH_HTML_REQUEST + "avd=" + nctsExportTargetItemRecord.getTvavd().trim());
			urlRequestParamsKeys.append(SkatConstants.URL_CHAR_DELIMETER_FOR_PARAMS_WITH_HTML_REQUEST + "opd=" + nctsExportTargetItemRecord.getTvtdn().trim());
			urlRequestParamsKeys.append(SkatConstants.URL_CHAR_DELIMETER_FOR_PARAMS_WITH_HTML_REQUEST + "lin=" + nctsExportTargetItemRecord.getTvli().trim());
			urlRequestParamsKeys.append(SkatConstants.URL_CHAR_DELIMETER_FOR_PARAMS_WITH_HTML_REQUEST + "mode=" + SkatConstants.MODE_UPDATE);
			//execute
			String urlRequestParamsTopicItem = this.urlRequestParameterMapper.getUrlParameterValidString((nctsExportTargetItemRecord));
			//put the final valid param. string
			String urlRequestParams = urlRequestParamsKeys + urlRequestParamsTopicItem;
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
	    	RpgReturnResponseHandler rpgReturnResponseHandler = new RpgReturnResponseHandler();
	    	rpgReturnResponseHandler.evaluateRpgResponseOnTopicItemCreateOrUpdate(rpgReturnPayload);
	    	if(rpgReturnResponseHandler.getErrorMessage()!=null && !"".equals(rpgReturnResponseHandler.getErrorMessage())){
	    		rpgReturnResponseHandler.setErrorMessage("[ERROR] FATAL on UPDATE: " + rpgReturnResponseHandler.getErrorMessage());
	    		//this.setFatalError(modelTODO, rpgReturnResponseHandler, nctsExportTargetItemRecord);
	    		
	    	}else{
	    		//Update succefully done!
	    		logger.info("[INFO] Valid default Item Line CREATE -- Record successfully updated, OK ");
	    	}
			
		}else{
			//Error here
		}
	}
	
	/**
	 * 
	 * @param nctsExportTargetItemRecord
	 */
	private void validateDefaultNctsItemNr(JsonSkatNctsExportSpecificTopicItemRecord nctsExportTargetItemRecord){
		//Commodity code
		if(nctsExportTargetItemRecord.getTvvnt()!=null && !"".equals(nctsExportTargetItemRecord.getTvvnt())){
			String tmp = nctsExportTargetItemRecord.getTvvnt();
			if(tmp.length()>6){
				nctsExportTargetItemRecord.setTvvnt(tmp.substring(0,6));
			}
			//Description
			if(nctsExportTargetItemRecord.getTvvt()!=null && !"".equals(nctsExportTargetItemRecord.getTvvt())){
				//OK
			}else{
				nctsExportTargetItemRecord.setTvvt("CLONE DEFAULT");
			}
		}else{
			nctsExportTargetItemRecord.setTvvnt("660000");
			nctsExportTargetItemRecord.setTvvt("CLONE DEFAULT");
		}
		
		
		
	}
    
	/**
	 * 
	 * @param applicationUser
	 * @param avd
	 * @param opd
	 * @return
	 */
	private JsonSkatNctsExportSpecificTopicItemRecord createNewItemKeySeeds(String applicationUser, String avd, String opd){
		
	RpgReturnResponseHandler rpgReturnResponseHandler = new RpgReturnResponseHandler();
	//request variables
	JsonSkatNctsExportSpecificTopicItemRecord jsonSkatNctsExportSpecificTopicItemRecord = new JsonSkatNctsExportSpecificTopicItemRecord();
	
	try{
		//---------------------------
		//get BASE URL = RPG-PROGRAM
        //---------------------------
		String BASE_URL = SkatNctsExportUrlDataStore.NCTS_EXPORT_BASE_UPDATE_SPECIFIC_TOPIC_ITEM_URL;
		
		//-------------------------------------------------------------------------------------------
		// STEP[PREPARE CREATION] --> generate new opd and tuid (if applicable) in order to be able to Add (Create)
		//-------------------------------------------------------------------------------------------
		logger.info("STEP[1] GET SEEDS and CREATE RECORD...");
		String numberOfItemLinesInTopicStr = "1";
		StringBuffer urlRequestParamsForSeed = new StringBuffer();
		//
		urlRequestParamsForSeed.append("user=" + applicationUser);
		urlRequestParamsForSeed.append(SkatConstants.URL_CHAR_DELIMETER_FOR_PARAMS_WITH_HTML_REQUEST + "avd=" + avd);
		urlRequestParamsForSeed.append(SkatConstants.URL_CHAR_DELIMETER_FOR_PARAMS_WITH_HTML_REQUEST + "opd=" + opd);
		urlRequestParamsForSeed.append(SkatConstants.URL_CHAR_DELIMETER_FOR_PARAMS_WITH_HTML_REQUEST + "lin=" + numberOfItemLinesInTopicStr);
		urlRequestParamsForSeed.append(SkatConstants.URL_CHAR_DELIMETER_FOR_PARAMS_WITH_HTML_REQUEST + "mode=" + SkatConstants.MODE_ADD);
		logger.info("New SEEDs URL: " + BASE_URL);
		logger.info("PARAMS for SEED: " + urlRequestParamsForSeed.toString());
				
		//Get the counter from RPG (new opd Id)
		String rpgSeedNumberPayload = this.urlCgiProxyService.getJsonContent(BASE_URL, urlRequestParamsForSeed.toString());
		// Map the JSON response to the new seeds (thtdn,tvli)
		// We are not using std JSON conversion since the RPGs strings are not the same. Should be the same as
		// the header fields. The RPG output should be changed in order to comply to the field specification...
		rpgReturnResponseHandler.evaluateRpgResponseOnTopicItemCreateOrUpdate(rpgSeedNumberPayload);
		
		//we must complete the GUI-json with the value from a line nr seed here
		if(rpgReturnResponseHandler.getErrorMessage()!=null && !"".equals(rpgReturnResponseHandler.getErrorMessage()) ){
			logger.info("[ERROR] No mandatory seeds (tvli, opd) were generated correctly)! look at std output log. [errMsg]" + rpgReturnResponseHandler.getErrorMessage());
			jsonSkatNctsExportSpecificTopicItemRecord = null;
			
		}else{
			jsonSkatNctsExportSpecificTopicItemRecord.setTvtdn(rpgReturnResponseHandler.getThtdn());
			jsonSkatNctsExportSpecificTopicItemRecord.setTvli(rpgReturnResponseHandler.getTvli());
		}
	
	}catch(Exception e){
		e.printStackTrace();
	}

	return jsonSkatNctsExportSpecificTopicItemRecord;
}
	
	
	/**
	 * 
	 * @param avd
	 * @param opd
	 * @param extRefNr
	 * @param appUser
	 * @return
	 */
	private String getRequestUrlKeyParametersForCopyTopicFromTransportUppdrag(String avd, String opd, String extRefNr, SystemaWebUser appUser){
		final String MODE = "GS";
		StringBuffer urlRequestParamsKeys = new StringBuffer();
		
		urlRequestParamsKeys.append("user=" + appUser.getUser());
		urlRequestParamsKeys.append(SkatConstants.URL_CHAR_DELIMETER_FOR_PARAMS_WITH_HTML_REQUEST + "thavd=" + avd);
		if(opd!=null && !"".equals(opd)){
			urlRequestParamsKeys.append(SkatConstants.URL_CHAR_DELIMETER_FOR_PARAMS_WITH_HTML_REQUEST + "thtdn=" + opd);
		}else if (extRefNr!=null && !"".equals(extRefNr)){
			urlRequestParamsKeys.append(SkatConstants.URL_CHAR_DELIMETER_FOR_PARAMS_WITH_HTML_REQUEST + "thxref=" + extRefNr);
		}
		urlRequestParamsKeys.append(SkatConstants.URL_CHAR_DELIMETER_FOR_PARAMS_WITH_HTML_REQUEST + "sign=" + appUser.getSkatSign());
		urlRequestParamsKeys.append(SkatConstants.URL_CHAR_DELIMETER_FOR_PARAMS_WITH_HTML_REQUEST + "mode=" + MODE);
		
		return urlRequestParamsKeys.toString();	
	}
	
	/**
	 * Generates key seeds for an upcoming update (the generation of this keys creates also a new record ready to be updated)
	 * The method must be seen as STEP ONE in an upcoming update [same transaction].
	 * 
	 * @param session
	 * @param request
	 * @param user
	 * @param avd
	 * @param sign
	 * 
	 * @return 
	 */
	private JsonSkatNctsExportSpecificTopicRecord createNewTopicHeaderKeySeeds(HttpSession session, HttpServletRequest request, SystemaWebUser user, String avd, String sign){
		RpgReturnResponseHandler rpgReturnResponseHandler = new RpgReturnResponseHandler();
		JsonSkatNctsExportSpecificTopicRecord jsonNctsExportSpecificTopicRecord = new JsonSkatNctsExportSpecificTopicRecord();
		//---------------------------
		//get BASE URL = RPG-PROGRAM
        //---------------------------
		String BASE_URL = SkatNctsExportUrlDataStore.NCTS_EXPORT_BASE_UPDATE_SPECIFIC_TOPIC_URL;
		
		//----------------------------------------------------------------------------------------------------------
		// STEP[PREPARE CREATION] --> generate new opd and tuid (if applicable) in order to be able to Add (Create)
		//----------------------------------------------------------------------------------------------------------
		logger.info("STEP[1] GET SEEDS and CREATE RECORD...");
		StringBuffer urlRequestParamsForSeed = new StringBuffer();
		urlRequestParamsForSeed.append("user=" + user.getUser());
		//for debug purposes in GUI
		session.setAttribute(SkatConstants.ACTIVE_URL_RPG_SKAT, BASE_URL);
				
		String avdIdForCreate = avd;
		String signatureForCreate = sign;
		urlRequestParamsForSeed.append(SkatConstants.URL_CHAR_DELIMETER_FOR_PARAMS_WITH_HTML_REQUEST + "thavd=" + avdIdForCreate);
		urlRequestParamsForSeed.append(SkatConstants.URL_CHAR_DELIMETER_FOR_PARAMS_WITH_HTML_REQUEST + "sign=" + signatureForCreate);
		urlRequestParamsForSeed.append(SkatConstants.URL_CHAR_DELIMETER_FOR_PARAMS_WITH_HTML_REQUEST + "mode=" + SkatConstants.MODE_ADD);
		logger.info("BASE_URL: " + BASE_URL);
		logger.info("PARAMS for SEED: " + urlRequestParamsForSeed.toString());
		
		//Get the counter from RPG (new thtdn (opd) Id)
		String rpgSeedNumberPayload = this.urlCgiProxyService.getJsonContent(BASE_URL, urlRequestParamsForSeed.toString());
		logger.info("RpgSeedNumberPayload: " + rpgSeedNumberPayload);
		
		// Map the JSON response to the new seeds (syop,tuid and ombud fields)
		// We are not using std JSON conversion since the RPGs strings are not the same. Should be the same as
		// the header fields. The RPG output should be changed in order to comply to the field specification...
		rpgReturnResponseHandler.getNewSeedsOpdAndTuidRequiredForCreateNewTopic(rpgSeedNumberPayload);
		logger.info("### thtdn from RPG PROGRAM: " + rpgReturnResponseHandler.getThtdn());
		logger.info("### thtuid from RPG PROGRAM: " + rpgReturnResponseHandler.getThtuid());
		
		//we must complete the GUI-json sypo and tuid with the value from a seedTuid here
		if(rpgReturnResponseHandler.getThtdn()!=null && rpgReturnResponseHandler.getThtuid()!=null){
			jsonNctsExportSpecificTopicRecord.setThtdn(rpgReturnResponseHandler.getThtdn().trim());
			jsonNctsExportSpecificTopicRecord.setThtuid(rpgReturnResponseHandler.getThtuid().trim());
			jsonNctsExportSpecificTopicRecord.setLrnNr(rpgReturnResponseHandler.getThtuid().trim());
			jsonNctsExportSpecificTopicRecord.setThavd(avdIdForCreate);
			jsonNctsExportSpecificTopicRecord.setThsg(signatureForCreate);
			
			
		}else{
			logger.info("[ERROR] No mandatory seeds (lrn/mrn, opd) were generated correctly)! look at std output log. [errMsg]" + rpgReturnResponseHandler.getErrorMessage());
			jsonNctsExportSpecificTopicRecord = null;
		}
        
		return jsonNctsExportSpecificTopicRecord;
	}
	
	/**
	 * log Errors in Aspects and Domain objects in order to render on GUI
	 * @param model
	 * @param rpgReturnResponseHandler
	 * @param jsonTdsExportSpecificTopicRecord
	 */
	private void setFatalError(Map model, RpgReturnResponseHandler rpgReturnResponseHandler, JsonSkatNctsExportSpecificTopicRecord jsonNctsExportSpecificTopicRecord){
		logger.info(rpgReturnResponseHandler.getErrorMessage());
		this.setAspectsInView(model, rpgReturnResponseHandler);
		//No refresh on jsonRecord is done for the GUI (form fields). Must be implemented right here, if required. !!
        this.setDomainObjectsInView(model, jsonNctsExportSpecificTopicRecord);
	}

	/**
	 * 
	 * @param jsonNctsExportSpecificTopicContainer
	 * @param user
	 * @param avd
	 * @param opd
	 */
	private void fetchSecurityRecord(JsonSkatNctsExportSpecificTopicContainer jsonNctsExportSpecificTopicContainer, String user, String avd, String opd){
		String method = "fetchSecurityRecord";
		logger.info("starting " + method + " transaction...");
		
		String BASE_URL = SkatNctsExportUrlDataStore.NCTS_EXPORT_BASE_FETCH_SPECIFIC_SIKKERHET_TOPIC_URL;
		String urlRequestParamsKeys = "user=" + user + "&avd=" + avd + "&opd=" + opd;   
		logger.info(Calendar.getInstance().getTime() + " CGI-start timestamp");
    	logger.info("URL: " + BASE_URL);
    	logger.info("URL PARAMS: " + urlRequestParamsKeys);
    	JsonSkatNctsExportSpecificTopicRecord sikkerhetRecord = null;
    	JsonSkatNctsExportSpecificTopicRecord targetRecord = null;
    	
    	for (JsonSkatNctsExportSpecificTopicRecord topicRecord : jsonNctsExportSpecificTopicContainer.getOneorder()){
    		targetRecord = topicRecord;
    	}
    	
    	//------------------------
    	//EXECUTE (Sikkerhet) here
    	//------------------------
    	String jsonPayload = this.urlCgiProxyService.getJsonContent(BASE_URL, urlRequestParamsKeys);
    	logger.debug(jsonDebugger.debugJsonPayloadWithLog4J(jsonPayload));
    	logger.info(Calendar.getInstance().getTime() +  " CGI-end timestamp");
    	if(jsonPayload!=null){
    		JsonSkatNctsExportSpecificTopicContainer topicSikkerhetContainer = this.skatNctsExportSpecificTopicService.getNctsExportSpecificTopicContainer(jsonPayload);
        	if(topicSikkerhetContainer!=null && topicSikkerhetContainer.getSecurityhead()!=null){
        		for (JsonSkatNctsExportSpecificTopicRecord topicSikkerhetRecord : topicSikkerhetContainer.getSecurityhead()){
        			sikkerhetRecord = topicSikkerhetRecord;
        			//logger.info("A(thlosd):" + sikkerhetRecord.getThlosd());
            	}
        		//copy delta to target record
        		if(sikkerhetRecord!=null && targetRecord!=null){
	        		JavaReflectionUtil.setFields(sikkerhetRecord, targetRecord);
	        		//logger.info(method + "targetRecord(thnas):" + targetRecord.getThnas());
	        		//logger.info(method + "targetRecord(thlosd):" + targetRecord.getThlosd());
	        		Collection newList = new ArrayList();
	        		newList.add(targetRecord);
	        		jsonNctsExportSpecificTopicContainer.setOneorder(newList);
        		}
        	}
    	}
	}
	
	/**
	 * @param user
	 * @param avd
	 * @param opd
	 * @return
	 */
	private boolean existsSecurityRecord(String user, String avd, String opd){
		boolean retval = false;
		
		String method = "existsSecurityRecord";
		logger.info("starting " + method + " transaction...");
		
		String BASE_URL = SkatNctsExportUrlDataStore.NCTS_EXPORT_BASE_FETCH_SPECIFIC_SIKKERHET_TOPIC_URL;
		String urlRequestParamsKeys = "user=" + user + "&avd=" + avd + "&opd=" + opd;   
		logger.info(Calendar.getInstance().getTime() + " CGI-start timestamp");
    	logger.info("URL: " + BASE_URL);
    	logger.info("URL PARAMS: " + urlRequestParamsKeys);
    	
    	//------------------------
    	//EXECUTE (Sikkerhet) here
    	//------------------------
    	String jsonPayload = this.urlCgiProxyService.getJsonContent(BASE_URL, urlRequestParamsKeys);
    	logger.debug(jsonDebugger.debugJsonPayloadWithLog4J(jsonPayload));
    	logger.info(Calendar.getInstance().getTime() +  " CGI-end timestamp");
    	if(jsonPayload!=null){
    		JsonSkatNctsExportSpecificTopicContainer topicSikkerhetContainer = this.skatNctsExportSpecificTopicService.getNctsExportSpecificTopicContainer(jsonPayload);
        	if(topicSikkerhetContainer!=null && topicSikkerhetContainer.getSecurityhead()!=null){
        		if(topicSikkerhetContainer.getSecurityhead().size()>0){
        			retval = true;
        		}
        	}
    	}
    	return retval;
	}
	
	/**
	 * 
	 * @param recordToValidate
	 * @param mode
	 * @param user
	 * @param avd
	 * @param opd
	 * @return
	 */
	private boolean updateSecurityRecord(JsonSkatNctsExportSpecificTopicRecord recordToValidate, String mode, String user, String avd, String opd){
		boolean retval = true;
		String method = "updateSecurityRecord";
		logger.info("starting " + method + " transaction...");
		
		RpgReturnResponseHandler rpgReturnResponseHandler = new RpgReturnResponseHandler();
		//-------------
		//get BASE URL
        //-------------
		String BASE_URL = SkatNctsExportUrlDataStore.NCTS_EXPORT_BASE_UPDATE_SPECIFIC_SIKKERHET_TOPIC_URL;
		//-----------------------------------------------------
		//add URL-parameter "mode=U" (Update), (A)dd, (D)elete
		//-----------------------------------------------------
		String urlRequestParamsKeys = "user="+ user + "&mode=" + mode + "&thavd=" + avd + "&thtdn=" + opd;
		//build the url parameters (from GUI-form) to send on a GET/POST method AFTER the keyParameters
		String urlRequestParamsTopicSikkerhet = this.urlRequestParameterMapper.getUrlParameterValidString((recordToValidate));
		//put the final valid param. string
		String urlRequestParams = urlRequestParamsKeys + urlRequestParamsTopicSikkerhet;
		
		logger.info(Calendar.getInstance().getTime() + " CGI-start timestamp");
    	logger.info("URL: " + jsonDebugger.getBASE_URL_NoHostName(BASE_URL));
    	logger.info("URL PARAMS: " + urlRequestParams);
    	//--------------------
    	//EXECUTE the UPDATE 
    	//--------------------
    	String rpgReturnPayload = this.urlCgiProxyService.getJsonContent(BASE_URL, urlRequestParams);
		//Debug --> 
    	logger.info("Checking errMsg in rpgReturnPayload" + rpgReturnPayload);
    	//we must evaluate a return RPG code in order to know if the Update was OK or not
    	rpgReturnResponseHandler.evaluateRpgResponseOnTopicUpdate(rpgReturnPayload);
    	if(rpgReturnResponseHandler.getErrorMessage()!=null && !"".equals(rpgReturnResponseHandler.getErrorMessage())){
    		rpgReturnResponseHandler.setErrorMessage("[ERROR] FATAL on UPDATE: " + rpgReturnResponseHandler.getErrorMessage());
    		retval = false;
    		
    	}else{
    		//Update successfully done!
    		logger.info("[INFO] Record SECURITY-SIKKERHET successfully updated, OK ");
    	}
    	
    	return retval;
	}
	/**
	 * Gets the key parameter string (such as avd, opd, user, mode)
	 * @param action
	 * @param avd
	 * @param opd
	 * @param sign
	 * @param appUser
	 * @return
	 */
	private String getRequestUrlKeyParameters(String action, String avd, String opd, String sign, SystemaWebUser appUser){
		StringBuffer urlRequestParamsKeys = new StringBuffer();
		//String action = request.getParameter("action");
		
		if(SkatConstants.ACTION_FETCH.equals(action)){
			urlRequestParamsKeys.append("user=" + appUser.getUser());
			urlRequestParamsKeys.append(SkatConstants.URL_CHAR_DELIMETER_FOR_PARAMS_WITH_HTML_REQUEST + "avd=" + avd);
			urlRequestParamsKeys.append(SkatConstants.URL_CHAR_DELIMETER_FOR_PARAMS_WITH_HTML_REQUEST + "opd=" + opd);
			
		}else if(SkatConstants.ACTION_UPDATE.equals(action)){
			urlRequestParamsKeys.append("user=" + appUser.getUser());
			urlRequestParamsKeys.append(SkatConstants.URL_CHAR_DELIMETER_FOR_PARAMS_WITH_HTML_REQUEST + "avd=" + avd); //thavd is the one used in the AS400 pgm (sends in the jsonRecord bean but this must be sent, in addition)
			urlRequestParamsKeys.append(SkatConstants.URL_CHAR_DELIMETER_FOR_PARAMS_WITH_HTML_REQUEST + "opd=" + opd); //thtdn is the one used in the AS400 pgm (sends in the jsonRecord bean but this must be sent, in addition)
			urlRequestParamsKeys.append(SkatConstants.URL_CHAR_DELIMETER_FOR_PARAMS_WITH_HTML_REQUEST + "mode=" + SkatConstants.MODE_UPDATE);
			
		}else if(SkatConstants.ACTION_CREATE.equals(action)){
			urlRequestParamsKeys.append("user=" + appUser.getUser());
			urlRequestParamsKeys.append(SkatConstants.URL_CHAR_DELIMETER_FOR_PARAMS_WITH_HTML_REQUEST + "avd=" + avd); //thavd
			urlRequestParamsKeys.append(SkatConstants.URL_CHAR_DELIMETER_FOR_PARAMS_WITH_HTML_REQUEST + "opd=" + opd); //thtdn
			urlRequestParamsKeys.append(SkatConstants.URL_CHAR_DELIMETER_FOR_PARAMS_WITH_HTML_REQUEST + "sign=" + sign);
			urlRequestParamsKeys.append(SkatConstants.URL_CHAR_DELIMETER_FOR_PARAMS_WITH_HTML_REQUEST + "mode=" + SkatConstants.MODE_ADD);
			
		}else if(SkatConstants.ACTION_SEND.equals(action)){
			urlRequestParamsKeys.append("user=" + appUser.getUser());
			urlRequestParamsKeys.append(SkatConstants.URL_CHAR_DELIMETER_FOR_PARAMS_WITH_HTML_REQUEST + "thavd=" + avd);
			urlRequestParamsKeys.append(SkatConstants.URL_CHAR_DELIMETER_FOR_PARAMS_WITH_HTML_REQUEST + "thtdn=" + opd);
			urlRequestParamsKeys.append(SkatConstants.URL_CHAR_DELIMETER_FOR_PARAMS_WITH_HTML_REQUEST + "mode=" + SkatConstants.MODE_SEND);
			
		}
		return urlRequestParamsKeys.toString();
	}
	
	/**
	 * Print parameters
	 * @param avd
	 * @param opd
	 * @param appUser
	 * @return
	 */
	private String getRequestUrlKeyParametersForPrint(String avd, String opd, SystemaWebUser appUser){
		StringBuffer urlRequestParamsKeys = new StringBuffer();
		
		urlRequestParamsKeys.append("user=" + appUser.getUser());
		urlRequestParamsKeys.append(SkatConstants.URL_CHAR_DELIMETER_FOR_PARAMS_WITH_HTML_REQUEST + "avd=" + avd);
		urlRequestParamsKeys.append(SkatConstants.URL_CHAR_DELIMETER_FOR_PARAMS_WITH_HTML_REQUEST + "opd=" + opd);
		return urlRequestParamsKeys.toString();	
	}
	
	/**
	 * 
	 * @param avd
	 * @param newAvd
	 * @param newSign
	 * @param opd
	 * @param appUser
	 * @return
	 */
	private String getRequestUrlKeyParametersForCopy(String avd, String newAvd, String newSign, String opd, SystemaWebUser appUser){
		//user=OSCAR&avd=1&newavd=2&opd=218&mode=C&newsign=OT 
		final String MODE_COPY = "C";
		StringBuffer urlRequestParamsKeys = new StringBuffer();
		
		urlRequestParamsKeys.append("user=" + appUser.getUser());
		urlRequestParamsKeys.append(SkatConstants.URL_CHAR_DELIMETER_FOR_PARAMS_WITH_HTML_REQUEST + "thavd=" + avd);
		urlRequestParamsKeys.append(SkatConstants.URL_CHAR_DELIMETER_FOR_PARAMS_WITH_HTML_REQUEST + "newavd=" + newAvd);
		urlRequestParamsKeys.append(SkatConstants.URL_CHAR_DELIMETER_FOR_PARAMS_WITH_HTML_REQUEST + "thtdn=" + opd);
		urlRequestParamsKeys.append(SkatConstants.URL_CHAR_DELIMETER_FOR_PARAMS_WITH_HTML_REQUEST + "mode=" + MODE_COPY);
		urlRequestParamsKeys.append(SkatConstants.URL_CHAR_DELIMETER_FOR_PARAMS_WITH_HTML_REQUEST + "newsign=" + newSign);

		return urlRequestParamsKeys.toString();	
	}
	
	/**
	 * 
	 * @param avd
	 * @param opd
	 * @param newStatus
	 * @param appUser
	 * @return
	 */
	private String getRequestUrlKeyParametersForUpdateStatus(String avd, String opd, String newStatus, SystemaWebUser appUser){
		
		StringBuffer urlRequestParamsKeys = new StringBuffer();
		
		urlRequestParamsKeys.append("user=" + appUser.getUser());
		urlRequestParamsKeys.append(SkatConstants.URL_CHAR_DELIMETER_FOR_PARAMS_WITH_HTML_REQUEST + "avd=" + avd);
		urlRequestParamsKeys.append(SkatConstants.URL_CHAR_DELIMETER_FOR_PARAMS_WITH_HTML_REQUEST + "opd=" + opd);
		if(newStatus != null & !"".equals(newStatus)){
			urlRequestParamsKeys.append(SkatConstants.URL_CHAR_DELIMETER_FOR_PARAMS_WITH_HTML_REQUEST + "status=" + newStatus);
		}
		return urlRequestParamsKeys.toString();	
	}
	/**
	 * Populates the html object (model map for the JSTL)
	 * 
	 * @param session
	 * @param model
	 * @param jsonNctsExportSpecificTopicContainer
	 * @return
	 */
	private void setDomainObjectsInView(HttpSession session, Map model, JsonSkatNctsExportSpecificTopicContainer jsonNctsExportSpecificTopicContainer, SkatNctsExportSpecificTopicTotalItemLinesObject totalItemLinesObject){
		//SET HEADER RECORDS  (from RPG)
		for (JsonSkatNctsExportSpecificTopicRecord record : jsonNctsExportSpecificTopicContainer.getOneorder()){
			if(totalItemLinesObject!=null){
				record.setSumOfAntalKolliInItemLines(totalItemLinesObject.getSumOfAntalKolliInItemLines());
				record.setSumOfAntalItemLines(totalItemLinesObject.getSumOfAntalItemLines());
				
			}
			model.put(SkatConstants.DOMAIN_RECORD, record);
			//put the header topic in session for the coming item lines
			session.setAttribute(SkatConstants.DOMAIN_RECORD_TOPIC_SKAT, record);
		}

	}
	
	/**
	 * 
	 * @param session
	 * @param model
	 * @param jsonNctsExportSpecificTopicContainer
	 */
	private void setDomainObjectsInView(HttpSession session, Map model, JsonSkatNctsExportSpecificTopicContainer jsonNctsExportSpecificTopicContainer){
		//SET HEADER RECORDS  (from RPG)
		for (JsonSkatNctsExportSpecificTopicRecord record : jsonNctsExportSpecificTopicContainer.getOneorder()){
			model.put(SkatConstants.DOMAIN_RECORD, record);
			//put the header topic in session for the coming item lines
			session.setAttribute(SkatConstants.DOMAIN_RECORD_TOPIC_SKAT, record);
		}
	}
	
	/**
	 * Populates the html object (model map for the JSTL)
	 * 
	 * @param model
	 * @param record
	 * @return
	 */
	private void setDomainObjectsInView(HttpSession session, Map model, JsonSkatNctsExportSpecificTopicRecord record, SkatNctsExportSpecificTopicTotalItemLinesObject totalItemLinesObject){
		if(totalItemLinesObject!=null){
			record.setSumOfAntalKolliInItemLines(totalItemLinesObject.getSumOfAntalKolliInItemLines());
			record.setSumOfAntalItemLines(totalItemLinesObject.getSumOfAntalItemLines());
			
		}
		//SET HEADER RECORDS  (from RPG)
		model.put(SkatConstants.DOMAIN_RECORD, record);
		//put the header topic in session for the coming item lines
		session.setAttribute(SkatConstants.DOMAIN_RECORD_TOPIC_SKAT, record);
	}
	/**
	 * 
	 * 
	 * @param model
	 * @param jsonTdsExportSpecificTopicRecord
	 */
	private void setDomainObjectsInView(Map model, JsonSkatNctsExportSpecificTopicRecord jsonNctsExportSpecificTopicRecord){
		//SET HEADER RECORDS  (from RPG)
		model.put(SkatConstants.DOMAIN_RECORD, jsonNctsExportSpecificTopicRecord);
	}
	/**
	 * Sets aspects 
	 * Usually error objects, log objects, other non-domain related objects
	 * @param model
	 */
	
	private void setAspectsInView (Map model, RpgReturnResponseHandler rpgReturnResponseHandler){
		model.put(SkatConstants.ASPECT_ERROR_MESSAGE, rpgReturnResponseHandler.getErrorMessage());
		//extra error information
		StringBuffer errorMetaInformation = new StringBuffer();
		errorMetaInformation.append(rpgReturnResponseHandler.getUser());
		errorMetaInformation.append(rpgReturnResponseHandler.getThtdn());
		model.put(SkatConstants.ASPECT_ERROR_META_INFO, errorMetaInformation);
		
	}
			
	/**
	 * 
	 * @param model
	 * @return
	 */
	private void populateHtmlDropDownsFromFile(Map model){
		//model.put(SkatConstants.RESOURCE_MODEL_KEY_LANGUAGE_LIST, this.dropDownListPopulationService.getLanguageList());
		//model.put(SkatConstants.URL_EXTERNAL_LANGUAGECODES_TARIC_CODE, new UrlISOLanguageObject());
	}
	
	/**
	 * 
	 * @param model
	 * @param appUser
	 */
	private void populateAvdelningHtmlDropDownsFromJsonString(Map model, SystemaWebUser appUser){
		//fill in html lists here
		String NCTS_EXPORT_IE = "X"; //Export
		try{
			String BASE_URL = SkatUrlDataStore.SKAT_FETCH_AVDELNINGAR_URL;
			StringBuffer urlRequestParamsKeys = new StringBuffer();
			urlRequestParamsKeys.append("user=" + appUser.getUser());
			urlRequestParamsKeys.append(SkatConstants.URL_CHAR_DELIMETER_FOR_PARAMS_WITH_HTML_REQUEST + "ie=" + NCTS_EXPORT_IE);
			//Now build the URL and send to the back end via the drop down service
			String url = this.urlCgiProxyService.getJsonContent(BASE_URL, urlRequestParamsKeys.toString());
			logger.info("AVD BASE_URL:" + BASE_URL);
			logger.info("AVD BASE_PARAMS:" + urlRequestParamsKeys.toString());
			
			JsonSkatAvdelningContainer container = this.skatDropDownListPopulationService.getAvdelningContainer(url);
			List<JsonSkatAvdelningRecord> list = new ArrayList();
			for(JsonSkatAvdelningRecord record: container.getAvdelningar()){
				list.add(record);
			}
			model.put(SkatConstants.RESOURCE_MODEL_KEY_AVD_LIST, list);
			
			
		}catch(Exception e){
			e.printStackTrace();
		}
		
	}	
	/**
	 * 
	 * @param model
	 * @param appUser
	 */
	private void populateSignatureHtmlDropDownsFromJsonString(Map model, SystemaWebUser appUser){
		//fill in html lists here
		String NCTS_EXPORT_IMPORT_IE = "N"; //NCTS = ie=N 
		
		try{
			String BASE_URL = SkatUrlDataStore.SKAT_FETCH_SIGNATURE_URL;
			StringBuffer urlRequestParamsKeys = new StringBuffer();
			urlRequestParamsKeys.append("user=" + appUser.getUser());
			urlRequestParamsKeys.append(SkatConstants.URL_CHAR_DELIMETER_FOR_PARAMS_WITH_HTML_REQUEST + "ie=" + NCTS_EXPORT_IMPORT_IE);
			//Now build the URL and send to the back end via the drop down service
			String url = this.urlCgiProxyService.getJsonContent(BASE_URL, urlRequestParamsKeys.toString());
			logger.info("SIGN BASE_URL:" + BASE_URL);
			logger.info("SIGN BASE_PARAMS:" + urlRequestParamsKeys.toString());
			
			JsonSkatSignatureContainer container = this.skatDropDownListPopulationService.getSignatureContainer(url);
			List<JsonSkatSignatureRecord> list = new ArrayList();
			for(JsonSkatSignatureRecord record: container.getSignaturer()){
				list.add(record);
			}
			model.put(SkatConstants.RESOURCE_MODEL_KEY_SIGN_LIST, list);
			
			
		}catch(Exception e){
			e.printStackTrace();
		}
		
	}
	
	/**
	 * 
	 * @param avd
	 * @param opd
	 * @param appUser
	 * @return
	 */
	
	 private SkatNctsExportSpecificTopicTotalItemLinesObject getRequiredSumsInItemLines(String avd, String opd, SystemaWebUser appUser){
		SkatNctsExportSpecificTopicTotalItemLinesObject totalItemLinesObject = new SkatNctsExportSpecificTopicTotalItemLinesObject();
		//-----------------------------------------------------
		//FETCH the ITEM LIST of existent ITEMs for this TOPIC
		//-----------------------------------------------------
		//get BASE URL = RPG-PROGRAM
        //---------------------------
		String BASE_URL_FETCH = SkatNctsExportUrlDataStore.NCTS_EXPORT_BASE_FETCH_TOPIC_ITEMLIST_URL;
		String urlRequestParamsKeys = "user=" + appUser.getUser() + "&avd=" + avd + "&opd=" + opd;
		
		logger.info(Calendar.getInstance().getTime() + " CGI-start timestamp");
		logger.info("FETCH av item list... ");
	    	logger.info("URL: " + BASE_URL_FETCH);
	    	logger.info("URL PARAMS: " + urlRequestParamsKeys);
	    	//--------------------------------------
	    	//EXECUTE the FETCH (RPG program) here
	    	//--------------------------------------
		String jsonPayloadFetch = this.urlCgiProxyService.getJsonContent(BASE_URL_FETCH, urlRequestParamsKeys);
		//Debug --> 
		logger.debug(jsonDebugger.debugJsonPayloadWithLog4J(jsonPayloadFetch));
	    	logger.info(Calendar.getInstance().getTime() +  " CGI-end timestamp");
	    	JsonSkatNctsExportSpecificTopicItemContainer jsonSkatNctsExportSpecificTopicItemContainer = this.getSkatNctsExportSpecificTopicItemService().getNctsExportSpecificTopicItemContainer(jsonPayloadFetch);
	    	//now to the real logic
	    	int antalKolli = 0;
	    	double grossWeight = 0.0D;
	    	int numberOfItemLines = 0;
	    	
	    	if(jsonSkatNctsExportSpecificTopicItemContainer!=null){
		    	for(JsonSkatNctsExportSpecificTopicItemRecord record : jsonSkatNctsExportSpecificTopicItemContainer.getOrderList()){
		    		numberOfItemLines++;
		    		
		    		if(record.getTvnt()!=null && !"".equals(record.getTvnt())){
		    			try{
		    				antalKolli += Integer.parseInt(record.getTvnt());
		    			}catch(Exception e){
		    				logger.info("[ERROR] on ANTAL KOLLI CATCH");
		    			}
		    		}
		    		if(record.getTvnt2()!=null && !"".equals(record.getTvnt2())){
		    			try{
		    				antalKolli += Integer.parseInt(record.getTvnt2());
		    			}catch(Exception e){
		    				logger.info("[ERROR] on ANTAL KOLLI CATCH");
		    			}
		    		}
		    		if(record.getTvnt3()!=null && !"".equals(record.getTvnt3())){
		    			try{
		    				antalKolli += Integer.parseInt(record.getTvnt3());
		    			}catch(Exception e){
		    				logger.info("[ERROR] on ANTAL KOLLI CATCH");
		    			}
		    		}
		    		if(record.getTvnt4()!=null && !"".equals(record.getTvnt4())){
		    			try{
		    				antalKolli += Integer.parseInt(record.getTvnt4());
		    			}catch(Exception e){
		    				logger.info("[ERROR] on ANTAL KOLLI CATCH");
		    			}
		    		}
		    		if(record.getTvnt5()!=null && !"".equals(record.getTvnt5())){
		    			try{
		    				antalKolli += Integer.parseInt(record.getTvnt5());
		    			}catch(Exception e){
		    				logger.info("[ERROR] on ANTAL KOLLI CATCH");
		    			}
		    		}
		    		//bruttovikt
		    		if(record.getTvvktb()!=null && !"".equals(record.getTvvktb())){
		    			try{
		    				grossWeight += Double.parseDouble(record.getTvvktb());
		    			}catch(Exception e){
		    				logger.info("[ERROR] on ANTAL KOLLI CATCH");
		    			}
		    		}
		    	}
	    	}
	    	//This is to flag the fact that no antal kolli exist DESPITE the fact that there is 1 or more item lines
	    	//to be used in validation...
	    	if(numberOfItemLines>0 && antalKolli==0){
	    		antalKolli = -1;
	    	}
	    	totalItemLinesObject.setSumOfAntalItemLines(numberOfItemLines);
	    	totalItemLinesObject.setSumOfAntalKolliInItemLines(antalKolli);
	    	
	    	//DEBUG
	    	logger.info("AntalKolli: " + totalItemLinesObject.getSumOfAntalKolliInItemLines());
	    	logger.info("AntalItems: " + totalItemLinesObject.getSumOfAntalItemLines());
	    	
	    
	    	return totalItemLinesObject;
	}
	
	  
	 
	
	/**
	 * Population of codes (GUI drop-downs)
	 * 
	 * @param appUser
	 * @param model
	 */
	private void setCodeDropDownMgr(SystemaWebUser appUser, Map model){
		this.codeDropDownMgr.populateCodesHtmlDropDownsFromJsonString(this.urlCgiProxyService, this.skatDropDownListPopulationService,
				 model,appUser,CodeDropDownMgr.CODE_008_COUNTRY, null, null);
		this.codeDropDownMgr.populateCodesHtmlDropDownsFromJsonString(this.urlCgiProxyService, this.skatDropDownListPopulationService,
				 model,appUser,CodeDropDownMgr.CODE_012_SPRAK, null, null);
		this.codeDropDownMgr.populateCodesHtmlDropDownsFromJsonString(this.urlCgiProxyService, this.skatDropDownListPopulationService,
				 model,appUser,CodeDropDownMgr.CODE_031_DEKLTYPE, null, null);
		this.codeDropDownMgr.populateCodesHtmlDropDownsFromJsonString(this.urlCgiProxyService, this.skatDropDownListPopulationService,
				 model,appUser,CodeDropDownMgr.CODE_107_CURRENCY, null, null);
		this.codeDropDownMgr.populateCodesHtmlDropDownsFromJsonString(this.urlCgiProxyService, this.skatDropDownListPopulationService,
				 model,appUser,CodeDropDownMgr.CODE_108_TRANSPORTMADE_KODER, null, null);
		this.codeDropDownMgr.populateCodesHtmlDropDownsFromJsonString(this.urlCgiProxyService, this.skatDropDownListPopulationService,
				 model,appUser,CodeDropDownMgr.CODE_096_SPEC_OMST, null, null);
		this.codeDropDownMgr.populateCodesHtmlDropDownsFromJsonString(this.urlCgiProxyService, this.skatDropDownListPopulationService,
				 model,appUser,CodeDropDownMgr.CODE_116_BETALNING_TRANSPORT, null, null);
		this.codeDropDownMgr.populateCodesHtmlDropDownsFromJsonString(this.urlCgiProxyService, this.skatDropDownListPopulationService,
				 model,appUser,CodeDropDownMgr.CODE_302_STATUS_KODER, null, null);
		
	}
	
	
	//SERVICES
	@Qualifier ("urlCgiProxyService")
	private UrlCgiProxyService urlCgiProxyService;
	@Autowired
	@Required
	public void setUrlCgiProxyService (UrlCgiProxyService value){ this.urlCgiProxyService = value; }
	public UrlCgiProxyService getUrlCgiProxyService(){ return this.urlCgiProxyService; }
	
	@Qualifier ("skatNctsExportSpecificTopicService")
	private SkatNctsExportSpecificTopicService skatNctsExportSpecificTopicService;
	@Autowired
	@Required
	public void setSkatNctsExportSpecificTopicService (SkatNctsExportSpecificTopicService value){ this.skatNctsExportSpecificTopicService = value; }
	public SkatNctsExportSpecificTopicService getSkatNctsExportSpecificTopicService(){ return this.skatNctsExportSpecificTopicService; }
	
	
	@Qualifier ("skatNctsExportSpecificTopicItemService")
	private SkatNctsExportSpecificTopicItemService skatNctsExportSpecificTopicItemService;
	@Autowired
	@Required
	public void setSkatNctsExportSpecificTopicItemService (SkatNctsExportSpecificTopicItemService value){ this.skatNctsExportSpecificTopicItemService = value; }
	public SkatNctsExportSpecificTopicItemService getSkatNctsExportSpecificTopicItemService(){ return this.skatNctsExportSpecificTopicItemService; }
	
	
	@Qualifier ("dropDownPopulationService")
	private SkatNctsExportDropDownListPopulationService dropDownListPopulationService;
	@Autowired
	public void setDropDownPopulationService (SkatNctsExportDropDownListPopulationService value){ this.dropDownListPopulationService=value; }
	public SkatNctsExportDropDownListPopulationService getDropDownListPopulationService(){return this.dropDownListPopulationService;}
	
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
	  
	@Qualifier 
	private SkatExportSpecificTopicItemService skatExportSpecificTopicItemService;
	@Autowired
	@Required	
	public void setSkatExportSpecificTopicItemService(SkatExportSpecificTopicItemService value){this.skatExportSpecificTopicItemService = value;}
	public SkatExportSpecificTopicItemService getSkatImportSpecificTopicItemService(){ return this.skatExportSpecificTopicItemService; }
	  
	
}

