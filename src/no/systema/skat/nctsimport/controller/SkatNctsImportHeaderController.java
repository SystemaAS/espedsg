package no.systema.skat.nctsimport.controller;

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
import no.systema.main.util.AppConstants;
import no.systema.main.util.JsonDebugger;
import no.systema.skat.model.external.url.UrlISOLanguageObject;
import no.systema.skat.model.jsonjackson.avdsignature.JsonSkatAvdelningContainer;
import no.systema.skat.model.jsonjackson.avdsignature.JsonSkatAvdelningRecord;
import no.systema.skat.model.jsonjackson.avdsignature.JsonSkatSignatureContainer;
import no.systema.skat.model.jsonjackson.avdsignature.JsonSkatSignatureRecord;
import no.systema.skat.model.jsonjackson.codes.JsonSkatNctsCodeContainer;
import no.systema.skat.model.jsonjackson.codes.JsonSkatNctsCodeRecord;
import no.systema.skat.service.html.dropdown.SkatDropDownListPopulationService;
import no.systema.skat.skatimport.url.store.SkatImportUrlDataStore;
import no.systema.skat.nctsimport.validator.SkatNctsImportHeaderValidator;

import no.systema.main.model.SystemaWebUser;
import no.systema.skat.nctsimport.model.jsonjackson.topic.JsonSkatNctsImportSpecificTopicContainer;
import no.systema.skat.nctsimport.model.jsonjackson.topic.JsonSkatNctsImportSpecificTopicRecord;

import no.systema.skat.nctsimport.service.SkatNctsImportSpecificTopicService;
import no.systema.skat.nctsimport.service.html.dropdown.SkatNctsImportDropDownListPopulationService;
import no.systema.skat.nctsimport.url.store.SkatNctsImportUrlDataStore;
import no.systema.skat.nctsimport.util.RpgReturnResponseHandler;
import no.systema.skat.url.store.SkatUrlDataStore;
import no.systema.skat.util.SkatConstants;
import no.systema.skat.nctsimport.util.manager.CodeDropDownMgr;
import no.systema.skat.nctsimport.mapper.url.request.UrlRequestParameterMapper;


/**
 * SKAT-NCTS Import Topic Controller 
 * 
 * @author oscardelatorre
 * @date Apr 24, 2014
 */

@Controller
@Scope("session")
public class SkatNctsImportHeaderController {
	private static final JsonDebugger jsonDebugger = new JsonDebugger(800);
	private static final Logger logger = Logger.getLogger(SkatNctsImportHeaderController.class.getName());
	private UrlRequestParameterMapper urlRequestParameterMapper = new UrlRequestParameterMapper();
	private ModelAndView loginView = new ModelAndView("login");
	private CodeDropDownMgr codeDropDownMgr = new CodeDropDownMgr();

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
	@RequestMapping(value="skatnctsimport_edit.do",  params="action=doPrepareCreate", method={RequestMethod.GET, RequestMethod.POST })
	public ModelAndView doPrepareCreate(HttpSession session, HttpServletRequest request){
		
		Map model = new HashMap();
		SystemaWebUser appUser = (SystemaWebUser)session.getAttribute(AppConstants.SYSTEMA_WEB_USER_KEY);
		//String messageFromContext = this.context.getMessage("user.label",new Object[0], request.getLocale());
		ModelAndView successView = new ModelAndView("skatnctsimport_edit");
		logger.info("Method: doPrepareCreate [RequestMapping-->skatnctsimport_edit.do]");
		//check user (should be in session already)
		if(appUser==null){
			return loginView;
			
		}else{
			
            //add gui lists here
	    		this.setCodeDropDownMgr(appUser, model);
	    		this.populateAvdelningHtmlDropDownsFromJsonString(model, appUser);
	    		this.populateSignatureHtmlDropDownsFromJsonString(model, appUser);
	    		//domain
	    		successView.addObject("model", model);
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
	@RequestMapping(value="skatnctsimport_edit.do")
	public ModelAndView doNctsImportEdit(@ModelAttribute ("record") JsonSkatNctsImportSpecificTopicRecord recordToValidate, BindingResult bindingResult, HttpSession session, HttpServletRequest request){
		
		ModelAndView successView = new ModelAndView("skatnctsimport_edit");
		String method = "doNctsImportEdit [RequestMapping-->skatnctsimport_edit.do]";
		RpgReturnResponseHandler rpgReturnResponseHandler = new RpgReturnResponseHandler();
		SystemaWebUser appUser = (SystemaWebUser)session.getAttribute(AppConstants.SYSTEMA_WEB_USER_KEY);
		logger.info("Method: " + method);
		
		//---------------------------------
		//Crucial request parameters (Keys
		//---------------------------------
		String action = request.getParameter("action");
		String opd = request.getParameter("opd");
		String avd = request.getParameter("avd");
		String sign = request.getParameter("sign");
		String status = request.getParameter("status");
		String datum = request.getParameter("datum");
		String dknh_0035 = request.getParameter("dknh_0035"); //test indicator (only in production)
		
		
		//Action (doFetch, doUpdate, doCreate)
		logger.info("Action:" + action);
		logger.info("Opd:" + opd);
		logger.info("Status:" + status);
		Map model = new HashMap();
		
		if(appUser==null){
			return this.loginView;
		}else{
			
			if(action!=null){
				//-------------
				//FETCH RECORD
				//-------------
				
				if(SkatConstants.ACTION_FETCH.equals(action)){
					logger.info("FETCH record transaction...");
					
					//---------------------------
					//get BASE URL = RPG-PROGRAM
		            //---------------------------
					String BASE_URL = SkatNctsImportUrlDataStore.NCTS_IMPORT_BASE_FETCH_SPECIFIC_TOPIC_URL;
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
				    		
				    	JsonSkatNctsImportSpecificTopicContainer jsonSkatNctsImportSpecificTopicContainer = this.skatNctsImportSpecificTopicService.getNctsImportSpecificTopicContainer(jsonPayload);
			            //add gui lists here
				    	this.setCodeDropDownMgr(appUser, model);

				    	this.setDomainObjectsInView(session, model, jsonSkatNctsImportSpecificTopicContainer);
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
					SkatNctsImportHeaderValidator validator = new SkatNctsImportHeaderValidator();
					logger.info("VALIDATING...");
					if(opd!=null && !"".equals(opd)){
						//Update...
						//nothing
					}else{
						logger.info("avdXX: " + avd);
						logger.info("signXX: " + sign);
						//Create
						//we must lend these dropdown variables to the validation object
						recordToValidate.setTiavd(avd);
						recordToValidate.setTisg(sign);
					}
					//required validation only for production avd
					if( !"1".equals(recordToValidate.getTestAvdFlag()) ){
						validator.validate(recordToValidate, bindingResult);
					}
					//test indicator in validation field
					recordToValidate.setDknh_0035(dknh_0035);

					
				    //check for ERRORS
					if(bindingResult.hasErrors()){
					    	logger.info("[ERROR Validation] Record does not validate)");
					    	//put domain objects and do go back to the original view...
					    	recordToValidate.setTiavd(avd);
					    	recordToValidate.setTisg(sign);
					    	this.setDomainObjectsInView(session, model, recordToValidate);
					    	isValidCreatedRecordTransactionOnRPG = false;
					    	if(opd==null || "".equals(opd)){
					    		action = SkatConstants.ACTION_CREATE;
					    	}
				    }else{
				    		JsonSkatNctsImportSpecificTopicRecord jsonNctsImportSpecificTopicRecord = null;
						String tuid = null;
						
						if(opd!=null && !"".equals(opd)){
							logger.info("PURE UPDATE transaction...");
							//PURE UDPATE transaction
							//this means that the update is an update of an existing record
							jsonNctsImportSpecificTopicRecord = new JsonSkatNctsImportSpecificTopicRecord();
							ServletRequestDataBinder binder = new ServletRequestDataBinder(jsonNctsImportSpecificTopicRecord);
							
				            binder.bind(request);
				            logger.info("TIENKL [after bind]: " + jsonNctsImportSpecificTopicRecord.getTienkl());
				            //test indicator in validation field
				            jsonNctsImportSpecificTopicRecord.setDknh_0035(dknh_0035);
	
				            
						}else{
							logger.info("CREATE NEW follow by UDATE transaction...");
							//CREATE AND UPDATE transaction
							//This means that the update will be done AFTER a creation of an empty record. All this in the same transaction. 2 STEPS involved: (1)create and (2)update
							//---------------------------------------------------------------------------------------------
							//STEP[1] Generate new Topic key seeds (avd,opd,sign,tullid) by creating an empty new record. 
							//---------------------------------------------------------------------------------------------
							jsonNctsImportSpecificTopicRecord = this.createNewTopicHeaderKeySeeds(session, request, appUser, avd, sign);
							if(jsonNctsImportSpecificTopicRecord!=null){
								avd = jsonNctsImportSpecificTopicRecord.getTiavd();
								sign = jsonNctsImportSpecificTopicRecord.getTisg();
								opd = jsonNctsImportSpecificTopicRecord.getTitdn();
								
								//take the rest from GUI.
								jsonNctsImportSpecificTopicRecord = new JsonSkatNctsImportSpecificTopicRecord();
								ServletRequestDataBinder binder = new ServletRequestDataBinder(jsonNctsImportSpecificTopicRecord);
					            //binder.registerCustomEditor(...); // if needed
					            binder.bind(request);
					            
					            //Now set back with the generated values since the bind method above erases them...
					            jsonNctsImportSpecificTopicRecord.setTiavd(avd);
					            jsonNctsImportSpecificTopicRecord.setTitdn(opd);
					            jsonNctsImportSpecificTopicRecord.setTisg(sign);
					            //test indicator in validation field
					            jsonNctsImportSpecificTopicRecord.setDknh_0035(dknh_0035);
		
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
							String BASE_URL = SkatNctsImportUrlDataStore.NCTS_IMPORT_BASE_UPDATE_SPECIFIC_TOPIC_URL;
							
							//-----------------------------------
							//add URL-parameter "mode=U" (Update)
							//-----------------------------------
							String urlRequestParamsKeys = this.getRequestUrlKeyParameters(action, avd, opd, sign, appUser);
							//build the url parameters (from GUI-form) to send on a GET/POST method AFTER the keyParameters
							String urlRequestParamsTopic = this.urlRequestParameterMapper.getUrlParameterValidString((jsonNctsImportSpecificTopicRecord));
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
						    		this.setFatalError(model, rpgReturnResponseHandler, jsonNctsImportSpecificTopicRecord);
						    		isValidCreatedRecordTransactionOnRPG = false;
						    	}else{
						    		//Update succefully done!
						    		logger.info("[INFO] Record successfully updated, OK ");
						    		//Godsnr. must be updated from the rpgReturn since there is a suffix added that must update the domain object
						    		jsonNctsImportSpecificTopicRecord.setTign(rpgReturnResponseHandler.getTign());
						    		//put domain objects
						    		this.setDomainObjectsInView(session, model, jsonNctsImportSpecificTopicRecord);
						    	}
							}else{
								rpgReturnResponseHandler.setErrorMessage("[ERROR] FATAL on CREATE, at tuid, syop generation : " + rpgReturnResponseHandler.getErrorMessage());
								this.setFatalError(model, rpgReturnResponseHandler, jsonNctsImportSpecificTopicRecord);
								isValidCreatedRecordTransactionOnRPG = false;
							}
				    }
					
		            //add gui lists here
		    			this.setCodeDropDownMgr(appUser, model);
		    			this.populateAvdelningHtmlDropDownsFromJsonString(model, appUser);
					this.populateSignatureHtmlDropDownsFromJsonString(model, appUser);
					
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
	 * @param session
	 * @param request
	 * @return
	 */
	@RequestMapping(value="skatnctsimport_send.do")
	public ModelAndView doNctsImportSend(HttpSession session, HttpServletRequest request){
		SystemaWebUser appUser = (SystemaWebUser)session.getAttribute(AppConstants.SYSTEMA_WEB_USER_KEY);
		appUser.setActiveMenu(SystemaWebUser.ACTIVE_MENU_SKAT_NCTS_IMPORT);
		ModelAndView successView = new ModelAndView("redirect:skatnctsimport.do?action=doFind&sign=" + appUser.getSkatSign());
		
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
			String BASE_URL = SkatNctsImportUrlDataStore.NCTS_IMPORT_BASE_UPDATE_SPECIFIC_TOPIC_URL;
			
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
		    	//EXECUTE the UPDATE (RPG program) here (STEP [2] after the SEND-step)
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
		    		logger.info("[INFO] Record successfully sent [changed status], OK ");
		    		//put domain objects
		    		//this.setDomainObjectsInView(session, model, jsonNctsImportSpecificTopicRecord);
		    		//TODO SUCCESS should stay at the same side or not? Right now we go to the list of topics
		    	}
			
		}
		return successView;
	}
	
	/**
	 * 
	 * @param session
	 * @param request
	 * @return
	 */
	@RequestMapping(value="skatnctsimport_updateStatus.do")
	public ModelAndView doUpdateStatus(HttpSession session, HttpServletRequest request){
		SystemaWebUser appUser = (SystemaWebUser)session.getAttribute(AppConstants.SYSTEMA_WEB_USER_KEY);
		appUser.setActiveMenu(SystemaWebUser.ACTIVE_MENU_SKAT_NCTS_IMPORT);
		ModelAndView successView = new ModelAndView("redirect:skatnctsimport.do?action=doFind&sign=" + appUser.getSkatSign());
		
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
			String BASE_URL = SkatNctsImportUrlDataStore.NCTS_IMPORT_BASE_UPDATE_STATUS_URL;
			
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
	private JsonSkatNctsImportSpecificTopicRecord createNewTopicHeaderKeySeeds(HttpSession session, HttpServletRequest request, SystemaWebUser user, String avd, String sign){
		RpgReturnResponseHandler rpgReturnResponseHandler = new RpgReturnResponseHandler();
		JsonSkatNctsImportSpecificTopicRecord jsonSkatNctsImportSpecificTopicRecord = new JsonSkatNctsImportSpecificTopicRecord();
		//---------------------------
		//get BASE URL = RPG-PROGRAM
        //---------------------------
		String BASE_URL = SkatNctsImportUrlDataStore.NCTS_IMPORT_BASE_UPDATE_SPECIFIC_TOPIC_URL;
		
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
		urlRequestParamsForSeed.append(SkatConstants.URL_CHAR_DELIMETER_FOR_PARAMS_WITH_HTML_REQUEST + "tiavd=" + avdIdForCreate);
		urlRequestParamsForSeed.append(SkatConstants.URL_CHAR_DELIMETER_FOR_PARAMS_WITH_HTML_REQUEST + "sign=" + signatureForCreate);
		urlRequestParamsForSeed.append(SkatConstants.URL_CHAR_DELIMETER_FOR_PARAMS_WITH_HTML_REQUEST + "mode=" + SkatConstants.MODE_ADD);
		logger.info("BASE_URL: " + BASE_URL);
		logger.info("PARAMS for SEED: " + urlRequestParamsForSeed.toString());
		
		//Get the counter from RPG (new titdn (opd) Id)
		String rpgSeedNumberPayload = this.urlCgiProxyService.getJsonContent(BASE_URL, urlRequestParamsForSeed.toString());
		logger.info("RpgSeedNumberPayload: " + rpgSeedNumberPayload);
		
		// Map the JSON response to the new seeds (syop,tuid and ombud fields)
		// We are not using std JSON conversion since the RPGs strings are not the same. Should be the same as
		// the header fields. The RPG output should be changed in order to comply to the field specification...
		rpgReturnResponseHandler.getNewSeedsOpdAndTuidRequiredForCreateNewTopic(rpgSeedNumberPayload);
		logger.info("### titdn from RPG PROGRAM: " + rpgReturnResponseHandler.getTitdn());
		
		//we must complete the GUI-json sypo and tuid with the value from a seedTuid here
		if(rpgReturnResponseHandler.getTitdn()!=null ){
			jsonSkatNctsImportSpecificTopicRecord.setTitdn(rpgReturnResponseHandler.getTitdn().trim());
			jsonSkatNctsImportSpecificTopicRecord.setTiavd(avdIdForCreate);
			jsonSkatNctsImportSpecificTopicRecord.setTisg(signatureForCreate);
			
		}else{
			logger.info("[ERROR] No mandatory seeds (lrn/mrn, opd) were generated correctly. [rpgReturnResposeHandler msg]>>" + rpgReturnResponseHandler.getErrorMessage());
			jsonSkatNctsImportSpecificTopicRecord = null;
		}
        
		return jsonSkatNctsImportSpecificTopicRecord;
	}
	
	/**
	 * log Errors in Aspects and Domain objects in order to render on GUI
	 * @param model
	 * @param rpgReturnResponseHandler
	 * @param record
	 */
	private void setFatalError(Map model, RpgReturnResponseHandler rpgReturnResponseHandler, JsonSkatNctsImportSpecificTopicRecord record){
		logger.info(rpgReturnResponseHandler.getErrorMessage());
		this.setAspectsInView(model, rpgReturnResponseHandler);
		//No refresh on jsonRecord is done for the GUI (form fields). Must be implemented right here, if required. !!
        this.setDomainObjectsInView(model, record);
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
			urlRequestParamsKeys.append(SkatConstants.URL_CHAR_DELIMETER_FOR_PARAMS_WITH_HTML_REQUEST + "avd=" + avd); 
			urlRequestParamsKeys.append(SkatConstants.URL_CHAR_DELIMETER_FOR_PARAMS_WITH_HTML_REQUEST + "opd=" + opd); 
			urlRequestParamsKeys.append(SkatConstants.URL_CHAR_DELIMETER_FOR_PARAMS_WITH_HTML_REQUEST + "mode=" + SkatConstants.MODE_UPDATE);
			
		}else if(SkatConstants.ACTION_CREATE.equals(action)){
			urlRequestParamsKeys.append("user=" + appUser.getUser());
			urlRequestParamsKeys.append(SkatConstants.URL_CHAR_DELIMETER_FOR_PARAMS_WITH_HTML_REQUEST + "avd=" + avd); 
			urlRequestParamsKeys.append(SkatConstants.URL_CHAR_DELIMETER_FOR_PARAMS_WITH_HTML_REQUEST + "opd=" + opd); 
			urlRequestParamsKeys.append(SkatConstants.URL_CHAR_DELIMETER_FOR_PARAMS_WITH_HTML_REQUEST + "sign=" + sign);
			urlRequestParamsKeys.append(SkatConstants.URL_CHAR_DELIMETER_FOR_PARAMS_WITH_HTML_REQUEST + "mode=" + SkatConstants.MODE_ADD);
			
		}else if(SkatConstants.ACTION_SEND.equals(action)){
			urlRequestParamsKeys.append("user=" + appUser.getUser());
			urlRequestParamsKeys.append(SkatConstants.URL_CHAR_DELIMETER_FOR_PARAMS_WITH_HTML_REQUEST + "tiavd=" + avd);
			urlRequestParamsKeys.append(SkatConstants.URL_CHAR_DELIMETER_FOR_PARAMS_WITH_HTML_REQUEST + "titdn=" + opd);
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
		urlRequestParamsKeys.append(SkatConstants.URL_CHAR_DELIMETER_FOR_PARAMS_WITH_HTML_REQUEST + "tiavd=" + avd);
		urlRequestParamsKeys.append(SkatConstants.URL_CHAR_DELIMETER_FOR_PARAMS_WITH_HTML_REQUEST + "newavd=" + newAvd);
		urlRequestParamsKeys.append(SkatConstants.URL_CHAR_DELIMETER_FOR_PARAMS_WITH_HTML_REQUEST + "titdn=" + opd);
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
	private void setDomainObjectsInView(HttpSession session, Map model, JsonSkatNctsImportSpecificTopicContainer container){
		//SET HEADER RECORDS  (from RPG)
		for (JsonSkatNctsImportSpecificTopicRecord record : container.getOneorder()){
			model.put(SkatConstants.DOMAIN_RECORD, record);
			//put the header topic in session for the coming item lines
			session.setAttribute(SkatConstants.DOMAIN_RECORD_TOPIC_SKAT_NCTS_IMPORT, record);
		}

	}
	
	/**
	 * Populates the html object (model map for the JSTL)
	 * 
	 * @param model
	 * @param record
	 * @return
	 */
	private void setDomainObjectsInView(HttpSession session, Map model, JsonSkatNctsImportSpecificTopicRecord record){
		//SET HEADER RECORDS  (from RPG)
		model.put(SkatConstants.DOMAIN_RECORD, record);
		//put the header topic in session for the coming item lines
		session.setAttribute(SkatConstants.DOMAIN_RECORD_TOPIC_SKAT_NCTS_IMPORT, record);
	}
	/**
	 * 
	 * 
	 * @param model
	 * @param record
	 */
	private void setDomainObjectsInView(Map model, JsonSkatNctsImportSpecificTopicRecord record){
		//SET HEADER RECORDS  (from RPG)
		model.put(SkatConstants.DOMAIN_RECORD, record);
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
		errorMetaInformation.append(rpgReturnResponseHandler.getTitdn());
		model.put(SkatConstants.ASPECT_ERROR_META_INFO, errorMetaInformation);
		
	}
			
	
	
	/**
	 * 
	 * @param model
	 * @param appUser
	 */
	private void populateAvdelningHtmlDropDownsFromJsonString(Map model, SystemaWebUser appUser){
		//fill in html lists here
		String NCTS_IMPORT_IE = "N"; //Import
		try{
			String BASE_URL = SkatUrlDataStore.SKAT_FETCH_AVDELNINGAR_URL;
			StringBuffer urlRequestParamsKeys = new StringBuffer();
			urlRequestParamsKeys.append("user=" + appUser.getUser());
			urlRequestParamsKeys.append(SkatConstants.URL_CHAR_DELIMETER_FOR_PARAMS_WITH_HTML_REQUEST + "ie=" + NCTS_IMPORT_IE);
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
		String NCTS_IMPORT_IE = "N"; //NCTS = ie=N 
		
		try{
			String BASE_URL = SkatUrlDataStore.SKAT_FETCH_SIGNATURE_URL;
			StringBuffer urlRequestParamsKeys = new StringBuffer();
			urlRequestParamsKeys.append("user=" + appUser.getUser());
			urlRequestParamsKeys.append(SkatConstants.URL_CHAR_DELIMETER_FOR_PARAMS_WITH_HTML_REQUEST + "ie=" + NCTS_IMPORT_IE);
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
				 model,appUser,CodeDropDownMgr.CODE_017_KOLLI, null, null);
		this.codeDropDownMgr.populateCodesHtmlDropDownsFromJsonString(this.urlCgiProxyService, this.skatDropDownListPopulationService,
				 model,appUser,CodeDropDownMgr.CODE_013_DOKTYPE, null, null);
		this.codeDropDownMgr.populateCodesHtmlDropDownsFromJsonString(this.urlCgiProxyService, this.skatDropDownListPopulationService,
				 model,appUser,CodeDropDownMgr.CODE_301_STATUS_KODER, null, null);
		
	}
	
	
	
	//SERVICES
	@Qualifier ("urlCgiProxyService")
	private UrlCgiProxyService urlCgiProxyService;
	@Autowired
	@Required
	public void setUrlCgiProxyService (UrlCgiProxyService value){ this.urlCgiProxyService = value; }
	public UrlCgiProxyService getUrlCgiProxyService(){ return this.urlCgiProxyService; }
	
	
	@Qualifier ("skatNctsImportSpecificTopicService")
	private SkatNctsImportSpecificTopicService skatNctsImportSpecificTopicService;
	@Autowired
	@Required
	public void setSkatNctsImportSpecificTopicService (SkatNctsImportSpecificTopicService value){ this.skatNctsImportSpecificTopicService = value; }
	public SkatNctsImportSpecificTopicService getSkatNctsImportSpecificTopicService(){ return this.skatNctsImportSpecificTopicService; }
	
	
	@Qualifier ("skatNctsImportDropDownListPopulationService")
	private SkatNctsImportDropDownListPopulationService skatNctsImportDropDownListPopulationService;
	@Autowired
	public void setSkatNctsImportDropDownListPopulationService (SkatNctsImportDropDownListPopulationService value){ this.skatNctsImportDropDownListPopulationService = value; }
	public SkatNctsImportDropDownListPopulationService getSkatNctsImportDropDownListPopulationService(){return this.skatNctsImportDropDownListPopulationService;}
	
	@Qualifier ("skatDropDownListPopulationService")
	private SkatDropDownListPopulationService skatDropDownListPopulationService;
	@Autowired
	public void setSkatDropDownListPopulationService (SkatDropDownListPopulationService value){ this.skatDropDownListPopulationService = value; }
	public SkatDropDownListPopulationService getSkatDropDownListPopulationService(){return this.skatDropDownListPopulationService;}
	
	
}

