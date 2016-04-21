package no.systema.skat.nctsexport.controller;

import java.util.*;

import org.apache.log4j.Level;
import org.apache.log4j.Logger;
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
import org.springframework.context.annotation.Scope;

import javax.annotation.PostConstruct;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;


import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.ServletRequestDataBinder;
//import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;

//import no.systema.tds.service.MainHdTopicService;
import no.systema.main.service.UrlCgiProxyService;
import no.systema.main.model.SystemaWebUser;
import no.systema.main.util.AppConstants;
import no.systema.main.util.JsonDebugger;

import no.systema.skat.nctsexport.mapper.url.request.UrlRequestParameterMapper;
import no.systema.skat.nctsexport.model.jsonjackson.topic.items.JsonSkatNctsExportSpecificTopicItemContainer;
import no.systema.skat.nctsexport.model.jsonjackson.topic.items.JsonSkatNctsExportSpecificTopicItemRecord;

import no.systema.skat.nctsexport.service.SkatNctsExportSpecificTopicItemService;
import no.systema.skat.nctsexport.service.html.dropdown.SkatNctsExportDropDownListPopulationService;
import no.systema.skat.nctsexport.url.store.SkatNctsExportUrlDataStore;
import no.systema.skat.nctsexport.util.RpgReturnResponseHandler;
import no.systema.skat.nctsexport.validator.SkatNctsExportItemsValidator;

import no.systema.skat.util.SkatConstants;
import no.systema.skat.nctsexport.util.manager.CodeDropDownMgr;
import no.systema.skat.service.html.dropdown.SkatDropDownListPopulationService;
import no.systema.skat.nctsexport.model.jsonjackson.topic.items.JsonSkatNctsExportSpecificTopicItemSecurityContainer;
import no.systema.skat.skatexport.model.jsonjackson.topic.JsonSkatExportTopicListContainer;
import no.systema.skat.skatexport.url.store.SkatExportUrlDataStore;
import no.systema.skat.skatexport.service.SkatExportTopicListService;

/**
 * 
 * Skat NCTS-Export create items gateway
 * 
 * @author oscardelatorre
 * @date Apr 22, 2014
 * 
 * 
 */

@Controller
//@SessionAttributes(AppConstants.SYSTEMA_WEB_USER_KEY)
@Scope("session")
public class SkatNctsExportItemsController {
	private static final JsonDebugger jsonDebugger = new JsonDebugger();
	private static final Logger logger = Logger.getLogger(SkatNctsExportItemsController.class.getName());
	private UrlRequestParameterMapper urlRequestParameterMapper = new UrlRequestParameterMapper();
	private ModelAndView loginView = new ModelAndView("login");
	private CodeDropDownMgr codeDropDownMgr = new CodeDropDownMgr();

	@InitBinder
    protected void initBinder(WebDataBinder binder) {
        //binder.setValidator(new TdsExportItemsValidator());
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
	@RequestMapping(value="skatnctsexport_edit_items.do")
	public ModelAndView skatNctsExportEditItem(@ModelAttribute ("record") JsonSkatNctsExportSpecificTopicItemRecord recordToValidate, BindingResult bindingResult, HttpSession session, HttpServletRequest request){
		logger.info("Inside: skatNctsExportEditItem");
		ModelAndView successView = new ModelAndView("skatnctsexport_edit_items");
		RpgReturnResponseHandler rpgReturnResponseHandler = new RpgReturnResponseHandler();
		JsonSkatNctsExportSpecificTopicItemRecord jsonSkatNctsExportSpecificTopicItemRecord = null;
		
		SystemaWebUser appUser = (SystemaWebUser)session.getAttribute(AppConstants.SYSTEMA_WEB_USER_KEY);
		
		Map model = new HashMap();
		String urlRequestParamsKeys = null;
		//Catch required action (doFetch or doUpdate)
		String action = request.getParameter("action");
		logger.info("ACTION: " + action);
		
		if(appUser==null){
			return this.loginView;
		}else{
			appUser.setActiveMenu(SystemaWebUser.ACTIVE_MENU_SKAT_NCTS_EXPORT);
			session.setAttribute(SkatConstants.ACTIVE_URL_RPG_SKAT, SkatConstants.ACTIVE_URL_RPG_INITVALUE); 
			session.setAttribute(SkatConstants.ACTIVE_URL_RPG_UPDATE_SKAT, SkatConstants.ACTIVE_URL_RPG_INITVALUE);
			
			boolean isValidCreatedRecordTransactionOnRPG = true;
			//Keys and header information
			String opd = request.getParameter("opd");
			String avd = request.getParameter("avd");
			String sign = request.getParameter("sign");
			String tullId = request.getParameter("tullId");
			String mrnNr = request.getParameter("mrnNr");
			
			String status = request.getParameter("status");
			String datum = request.getParameter("datum");
			//this key is only used with a real Update. When empty it will be a signal for a CREATE NEW (Add)
			String lineNr = request.getParameter("tvli");
			if(lineNr!=null && !"".equals(lineNr)){
				//nothing
			}else{
				//this branch is necessary in order to get the line Nr after a validation error (ref. below att bindingResult.hasErrors in this same method)
				lineNr = (String)session.getAttribute("tvli_SESSION");
			}
			//this row counter(lastSelectedItemLineNumber) is only used to add aspects/behavior to the list of rows (color, scroll(top/down)etc
			String lastSelectedItemLineNumber = request.getParameter("lastSelectedItemLineNumber");
			
			model.put("avd", avd);
			model.put("opd", opd);
			model.put("sign", sign);
			model.put("tullId", tullId);
			model.put("status", status);
			model.put("datum", datum);
			model.put("mrnNr", mrnNr);
			
			logger.info("AA" + recordToValidate.getTvdref());
		    
			if(SkatConstants.ACTION_UPDATE.equals(action)){
				//-----------
				//Validation
				//-----------
				SkatNctsExportItemsValidator validator = new SkatNctsExportItemsValidator();
				logger.info("Host via HttpServletRequest.getHeader('Host'): " + request.getHeader("Host"));
				//-------------------------------------------------------
				//this is only for validation of gross weight 
				//in first item line (manadatory), this is the only way
				//-------------------------------------------------------
				String numberOfItemLinesInTopicStr = request.getParameter("numberOfItemLinesInTopic");
				if(numberOfItemLinesInTopicStr==null || "".equals(numberOfItemLinesInTopicStr)){
					numberOfItemLinesInTopicStr = "0";
				}
				recordToValidate.setNumberOfItemLinesInTopicStr(numberOfItemLinesInTopicStr);
				//in order to flag an update one-line item
				if(lineNr!=null && !"".equals(lineNr)){
					if("1".equals(numberOfItemLinesInTopicStr)){
						recordToValidate.setNumberOfItemLinesInTopicStr("-99");
					}
				}
				//Check if oppdrag ref (if any) is valid
				if(!"".equals(recordToValidate.getTvtdn2())){
					recordToValidate.setValidOppdragRef(this.isValidOppdragRef(appUser, recordToValidate));
				}
				validator.validate(recordToValidate, bindingResult);
				
			    //check for ERRORS
				if(bindingResult.hasErrors()){
					recordToValidate.setTvli(null);
				    	logger.info("[ERROR] Validation Item Record does not validate)");
				    	logger.info("[INFO] lineNr " + lineNr);
				    	
				    	model.put("record", recordToValidate);
				    	if(lineNr!=null && !"".equals(lineNr)){
				    		logger.info("[INFO] lineNr ... filling old value: lineNr:" + lineNr);
				    		session.setAttribute("tvli_SESSION", lineNr);
				    		recordToValidate.setTvli(lineNr);
					    	recordToValidate.setTvtdn(opd);
				    		recordToValidate.setTvavd(avd);
				    	}
			    }else{
					if(lineNr!=null && !"".equals(lineNr)){
						//clean
						session.removeAttribute("tvli_SESSION");
						//-------
						//UPDATE
						//-------
						logger.info("UPDATE(only) ITEM (existent item) in process...");
						//take the rest from GUI.
						jsonSkatNctsExportSpecificTopicItemRecord = new JsonSkatNctsExportSpecificTopicItemRecord();
						ServletRequestDataBinder binder = new ServletRequestDataBinder(jsonSkatNctsExportSpecificTopicItemRecord);
			            //binder.registerCustomEditor(...); // if needed
			            binder.bind(request);
			            //put back the generated seed and the header keys (avd,opd)
			            jsonSkatNctsExportSpecificTopicItemRecord.setTvavd(avd);
			            jsonSkatNctsExportSpecificTopicItemRecord.setTvtdn(opd);
			            //put back the values of sensitive goods
			            jsonSkatNctsExportSpecificTopicItemRecord.setTvfv(recordToValidate.getTvfv());
			            jsonSkatNctsExportSpecificTopicItemRecord.setTvfvnt(recordToValidate.getTvfvnt());
			            
			            logger.info("[DEBUG] UPDATE on Line nr: " + jsonSkatNctsExportSpecificTopicItemRecord.getTvli().trim());
			            logger.info("[DEBUG] UPDATE on Opd: " + jsonSkatNctsExportSpecificTopicItemRecord.getTvtdn());
			            logger.info("[DEBUG] UPDATE on Avd: " + jsonSkatNctsExportSpecificTopicItemRecord.getTvavd());
			            logger.info("[DEBUG] UPDATE on tvtdsk: " + jsonSkatNctsExportSpecificTopicItemRecord.getTvtdsk());
			            logger.info("[DEBUG] UPDATE on tvtdo: " + jsonSkatNctsExportSpecificTopicItemRecord.getTvtdo());
						
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
						jsonSkatNctsExportSpecificTopicItemRecord  = this.createNewItemKeySeeds(session, request, appUser);
						if(jsonSkatNctsExportSpecificTopicItemRecord!=null){
							String newLineNr = jsonSkatNctsExportSpecificTopicItemRecord.getTvli();
							logger.info("[INFO] New LineNr:" + newLineNr);
							//take the rest from GUI.
							jsonSkatNctsExportSpecificTopicItemRecord = new JsonSkatNctsExportSpecificTopicItemRecord();
							ServletRequestDataBinder binder = new ServletRequestDataBinder(jsonSkatNctsExportSpecificTopicItemRecord);
				            //binder.registerCustomEditor(...); // if needed
				            binder.bind(request);
				            
				            jsonSkatNctsExportSpecificTopicItemRecord.setTvli(newLineNr);
				            //put back the generated seed and the header keys (avd,opd)
				            jsonSkatNctsExportSpecificTopicItemRecord.setTvtdn(opd);
				            jsonSkatNctsExportSpecificTopicItemRecord.setTvavd(avd);
				            logger.info("[INFO] Varubeskrivning (on item record):" + jsonSkatNctsExportSpecificTopicItemRecord.getTvvt());
				            logger.info("[INFO] New line number (on item record):" + jsonSkatNctsExportSpecificTopicItemRecord.getTvli());
				            logger.info("[INFO] Yderligare oplys. (on item record):" + jsonSkatNctsExportSpecificTopicItemRecord.getTvdty());
				            logger.info("[INFO] Doc.ref (on item record):" + jsonSkatNctsExportSpecificTopicItemRecord.getTvdref());
				            //put back the values of sensitive goods
				            jsonSkatNctsExportSpecificTopicItemRecord.setTvfv(recordToValidate.getTvfv());
				            jsonSkatNctsExportSpecificTopicItemRecord.setTvfvnt(recordToValidate.getTvfvnt());
				            
						}else{
							isValidCreatedRecordTransactionOnRPG = false;
						}
					}
					//--------------------------------------------------
					//At this point we are ready to do an update
					//--------------------------------------------------
					if(isValidCreatedRecordTransactionOnRPG){
			            logger.info("[INFO] Valid previous step successfully processed, OK ");
			            logger.info("[INFO] Ready to move forward to do the UPDATE");
						//---------------------------
						//get BASE URL = RPG-PROGRAM
			            //---------------------------
						String BASE_URL_UPDATE = SkatNctsExportUrlDataStore.NCTS_EXPORT_BASE_UPDATE_SPECIFIC_TOPIC_ITEM_URL;
						urlRequestParamsKeys = this.getRequestUrlKeyParametersUpdate(jsonSkatNctsExportSpecificTopicItemRecord, appUser, SkatConstants.MODE_UPDATE);
						String urlRequestParamsTopicItem = this.urlRequestParameterMapper.getUrlParameterValidString((jsonSkatNctsExportSpecificTopicItemRecord));
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
				    		this.setFatalError(model, rpgReturnResponseHandler, jsonSkatNctsExportSpecificTopicItemRecord);
				    		
				    	}else{
				    		//Update succefully done!
				    		logger.info("[INFO] Valid STEP[2] Update -- Record successfully updated, OK ");
				    		//------------------------------------
				    		//Update/Create now Security-Sikkerhet record
				    		//------------------------------------
				    		//Check if there is a record minimum of information in order to go on with security record - update
				    		if(jsonSkatNctsExportSpecificTopicItemRecord.getTvtkbm()!=null && !"".equals(jsonSkatNctsExportSpecificTopicItemRecord.getTvtkbm())){
					    		String mode = SkatConstants.MODE_ADD; //Add - default
					    		if(this.existsSecurityRecord(appUser.getUser(), avd, opd, jsonSkatNctsExportSpecificTopicItemRecord.getTvli())){
					    			mode = SkatConstants.MODE_UPDATE; //Update
					    		}
				    			if(!this.updateSecurityRecord(jsonSkatNctsExportSpecificTopicItemRecord, mode, appUser.getUser(), avd, opd)){
					    			this.setFatalError(model, rpgReturnResponseHandler, jsonSkatNctsExportSpecificTopicItemRecord);
					    		}
				    		}
				    		//---------------------------
				    		//REFRESH ON SOME VARIABLES
				    		//---------------------------
				    		//Update some variables on header such as (1)Number of item lines, (2)Kolliantal and (3)Gross weight-Bruttovikt
				    		this.refreshHeaderVariables(appUser.getUser(), avd, opd);
				    	}
					}else{
						rpgReturnResponseHandler.setErrorMessage("[ERROR] FATAL on CREATE, at tuid, syop generation : " + rpgReturnResponseHandler.getErrorMessage());
						this.setFatalError(model, rpgReturnResponseHandler, jsonSkatNctsExportSpecificTopicItemRecord);
					}
			    }
				
			}else if(SkatConstants.ACTION_DELETE.equals(action)){
				logger.info("[INFO] Delete record start process... ");
				String lineNrToDelete = request.getParameter("lin");
				
				//---------------------------
				//get BASE URL = RPG-PROGRAM
	            //---------------------------
				String BASE_URL_DELETE = SkatNctsExportUrlDataStore.NCTS_EXPORT_BASE_UPDATE_SPECIFIC_TOPIC_ITEM_URL;
				jsonSkatNctsExportSpecificTopicItemRecord = new JsonSkatNctsExportSpecificTopicItemRecord();
				jsonSkatNctsExportSpecificTopicItemRecord.setTvli(lineNrToDelete);
				jsonSkatNctsExportSpecificTopicItemRecord.setTvavd(avd);
				jsonSkatNctsExportSpecificTopicItemRecord.setTvtdn(opd);
				String urlRequestParams = this.getRequestUrlKeyParametersUpdate(jsonSkatNctsExportSpecificTopicItemRecord, appUser,SkatConstants.MODE_DELETE );
				
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
		    		this.setFatalError(model, rpgReturnResponseHandler, jsonSkatNctsExportSpecificTopicItemRecord);
		    		
		    	}else{
		    		//Delete successfully done!
		    		logger.info("[INFO] Valid Delete -- Record successfully deleted, OK ");
		    		
		    		if(!this.updateSecurityRecord(jsonSkatNctsExportSpecificTopicItemRecord, SkatConstants.MODE_DELETE, appUser.getUser(), avd, opd)){
		    			this.setFatalError(model, rpgReturnResponseHandler, jsonSkatNctsExportSpecificTopicItemRecord);
		    		}
		    		//---------------------------
		    		//REFRESH ON SOME VARIABLES
		    		//---------------------------
		    		//Update some variables on header such as (1)Number of item lines, (2)Kolliantal and (3)Gross weight-Bruttovikt
		    		this.refreshHeaderVariables(appUser.getUser(), avd, opd);
		    	}
			
			}
			
			//FETCH the ITEM LIST of existent ITEMs for this TOPIC
			//---------------------------
			//get BASE URL = RPG-PROGRAM
            //---------------------------
			String BASE_URL_FETCH = SkatNctsExportUrlDataStore.NCTS_EXPORT_BASE_FETCH_TOPIC_ITEMLIST_URL;
			
			urlRequestParamsKeys = this.getRequestUrlKeyParameters(request, avd, opd, appUser);
			//for debug purposes in GUI
			session.setAttribute(SkatConstants.ACTIVE_URL_RPG_SKAT, BASE_URL_FETCH + "==>params: " + urlRequestParamsKeys.toString()); 
			
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
	    	JsonSkatNctsExportSpecificTopicItemContainer jsonSkatNctsExportSpecificTopicItemContainer = this.skatNctsExportSpecificTopicItemService.getNctsExportSpecificTopicItemContainer(jsonPayloadFetch);
	    	if(jsonSkatNctsExportSpecificTopicItemContainer!=null){
		    	//some aspects for GUI
		    	jsonSkatNctsExportSpecificTopicItemContainer.setLastSelectedItemLineNumber(lastSelectedItemLineNumber);
		    	logger.info("(B)##### lastSelectedItemLineNumber:" + lastSelectedItemLineNumber);
	    	}
	    	//add gui lists here
    		//this.setCodeDropDownMgr(appUser, model);
    		
    		String codeMgrExists = (String)session.getAttribute(SkatConstants.SESSION_CODE_MANAGER_EXISTS_SKATEXPORT_NCTS);
	    	if(codeMgrExists!=null){
	    		this.codeDropDownMgr.getCodeMgrListsFromSession(model, session);
	    	}else{
	    		this.setCodeDropDownMgr(appUser, model);
	    		this.codeDropDownMgr.setCodeMgrListsInSession(model, session);
	    		session.setAttribute(SkatConstants.SESSION_CODE_MANAGER_EXISTS_SKATEXPORT_NCTS, SkatConstants.SESSION_CODE_MANAGER_EXISTS_SKATEXPORT_NCTS );
	    	}
    		
    		//domain objects
	    	this.setDomainObjectsForListInView(session, model, jsonSkatNctsExportSpecificTopicItemContainer);
			successView.addObject(SkatConstants.DOMAIN_MODEL,model);
			
			return successView;
		}
	}
	
	/**
	 * 
	 * @param appUser
	 * @param recordToValidate
	 * @return
	 */
	private boolean isValidOppdragRef(SystemaWebUser appUser, JsonSkatNctsExportSpecificTopicItemRecord recordToValidate){
		boolean retval = false;
		//get BASE URL
		final String BASE_URL = SkatExportUrlDataStore.SKAT_EXPORT_BASE_TOPICLIST_URL;
		//add URL-parameters
		String urlRequestParams = "user=" + appUser.getUser() + "&opd=" + recordToValidate.getTvtdn2();
		String jsonPayload = this.urlCgiProxyService.getJsonContent(BASE_URL, urlRequestParams);
		//Debug --> 
		logger.debug(jsonDebugger.debugJsonPayloadWithLog4J(jsonPayload));
    	logger.info(Calendar.getInstance().getTime() +  " CGI-end timestamp");
    	if(jsonPayload!=null){
    		JsonSkatExportTopicListContainer container = this.skatExportTopicListService.getSkatExportTopicListContainer(jsonPayload);
    		//logger.info("AA");
    		if(container!=null){
    			//logger.info("BB");
    			if(container.getOrderList()!=null && container.getOrderList().size()==1){
    				//logger.info("CC");
    				retval = true;
    			}
    		}
    	}
		return retval;
	}
	/**
	 * 
	 * @param appUser
	 * @param avd
	 * @param opd
	 */
	private void refreshHeaderVariables(String appUser, String avd, String opd){
		//---------------------------
		//REFRESH ON SOME VARIABLES
		//---------------------------
		//Update some variables on header such as (1)Number of item lines, (2)Kolliantal and (3)Gross weight-Bruttovikt
		logger.info("[INFO] REFRESH of topic ... starting now...");
		String BASE_URL_REFRESH = SkatNctsExportUrlDataStore.NCTS_EXPORT_BASE_REFRESH_SPECIFIC_TOPIC_URL;
		String urlRequestParams = "user=" + appUser + "&avd=" + avd + "&opd=" + opd; 
		
		logger.info("URL: " + BASE_URL_REFRESH);
		logger.info("URL PARAMS: " + urlRequestParams);
		String rpgReturnPayload = this.urlCgiProxyService.getJsonContent(BASE_URL_REFRESH, urlRequestParams);
		logger.info(rpgReturnPayload);	
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
		errorMetaInformation.append(rpgReturnResponseHandler.getThtdn());
		model.put(SkatConstants.ASPECT_ERROR_META_INFO, errorMetaInformation);
		
	}
	/**
	 * Sets domain objects
	 * @param model
	 * @param container
	 * 
	 */
	private void setDomainObjectsForListInView(HttpSession session, Map model, JsonSkatNctsExportSpecificTopicItemContainer container){
		List list = new ArrayList();
		if(container!=null){
			for (JsonSkatNctsExportSpecificTopicItemRecord record : container.getOrderList()){
				list.add(record);
				//logger.info("[INFO] tvdref --> " + record.getTvdref());
				
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
	 * @param jsonNctsExportSpecificTopicItemRecord
	 */
	private void setDomainObjectsInView(Map model, JsonSkatNctsExportSpecificTopicItemRecord record){
		model.put(SkatConstants.DOMAIN_RECORD, record);
	}
	
	/**
	 * 
	 * @param model
	 * @param rpgReturnResponseHandler
	 * @param jsonNctsExportSpecificTopicItemRecord
	 */
	private void setFatalError(Map model, RpgReturnResponseHandler rpgReturnResponseHandler, JsonSkatNctsExportSpecificTopicItemRecord record){
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
	private JsonSkatNctsExportSpecificTopicItemRecord createNewItemKeySeeds(HttpSession session, HttpServletRequest request, SystemaWebUser appUser){
		
		RpgReturnResponseHandler rpgReturnResponseHandler = new RpgReturnResponseHandler();
		//request variables
		String numberOfItemLinesInTopicStr = request.getParameter("numberOfItemLinesInTopic");
		if(numberOfItemLinesInTopicStr==null || "".equals(numberOfItemLinesInTopicStr)){
			numberOfItemLinesInTopicStr = "0";
		}else{
			numberOfItemLinesInTopicStr = numberOfItemLinesInTopicStr.trim();
		}
			
		String opd = request.getParameter("opd");
		String avd = request.getParameter("avd");
		
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
			StringBuffer urlRequestParamsForSeed = new StringBuffer();
			urlRequestParamsForSeed.append("user=" + appUser.getUser());
			urlRequestParamsForSeed.append(SkatConstants.URL_CHAR_DELIMETER_FOR_PARAMS_WITH_HTML_REQUEST + "avd=" + avd);
			urlRequestParamsForSeed.append(SkatConstants.URL_CHAR_DELIMETER_FOR_PARAMS_WITH_HTML_REQUEST + "opd=" + opd);
			Integer numberOfItemLinesInTopic = -99;
			
			numberOfItemLinesInTopic = Integer.valueOf(numberOfItemLinesInTopicStr);
			//add one
			numberOfItemLinesInTopic++;
			logger.info("New item line nr: " + numberOfItemLinesInTopic);
			
			urlRequestParamsForSeed.append(SkatConstants.URL_CHAR_DELIMETER_FOR_PARAMS_WITH_HTML_REQUEST + "lin=" + numberOfItemLinesInTopic);
			urlRequestParamsForSeed.append(SkatConstants.URL_CHAR_DELIMETER_FOR_PARAMS_WITH_HTML_REQUEST + "mode=" + SkatConstants.MODE_ADD);
			logger.info("New SEEDs URL: " + BASE_URL);
			logger.info("PARAMS for SEED: " + urlRequestParamsForSeed.toString());
			//for debug purposes in GUI
			session.setAttribute(SkatConstants.ACTIVE_URL_RPG_SKAT, BASE_URL + " ==>params: " + urlRequestParamsForSeed.toString() );
					
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
	 * @param numberOfItemLinesInTopic
	 * @param avd
	 * @param opd
	 * @param appUser
	 * @return
	 */
	private String getRequestUrlKeyParametersUpdate(JsonSkatNctsExportSpecificTopicItemRecord record, SystemaWebUser appUser, String mode){
		StringBuffer urlRequestParamsKeys = new StringBuffer();
		
		urlRequestParamsKeys.append("user=" + appUser.getUser());
		urlRequestParamsKeys.append(SkatConstants.URL_CHAR_DELIMETER_FOR_PARAMS_WITH_HTML_REQUEST + "avd=" + record.getTvavd().trim());
		urlRequestParamsKeys.append(SkatConstants.URL_CHAR_DELIMETER_FOR_PARAMS_WITH_HTML_REQUEST + "opd=" + record.getTvtdn().trim());
		urlRequestParamsKeys.append(SkatConstants.URL_CHAR_DELIMETER_FOR_PARAMS_WITH_HTML_REQUEST + "lin=" + record.getTvli().trim());
		urlRequestParamsKeys.append(SkatConstants.URL_CHAR_DELIMETER_FOR_PARAMS_WITH_HTML_REQUEST + "mode=" + mode.trim());
		
		return urlRequestParamsKeys.toString();
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
				 model,appUser,CodeDropDownMgr.CODE_013_DOKTYPE, null, null);
		this.codeDropDownMgr.populateCodesHtmlDropDownsFromJsonString(this.urlCgiProxyService, this.skatDropDownListPopulationService,
				 model,appUser,CodeDropDownMgr.CODE_017_KOLLI, null, null);
		this.codeDropDownMgr.populateCodesHtmlDropDownsFromJsonString(this.urlCgiProxyService, this.skatDropDownListPopulationService,
				 model,appUser,CodeDropDownMgr.CODE_031_DEKLTYPE, null, null);
		this.codeDropDownMgr.populateCodesHtmlDropDownsFromJsonString(this.urlCgiProxyService, this.skatDropDownListPopulationService,
				 model,appUser,CodeDropDownMgr.CODE_107_CURRENCY, null, null);
		this.codeDropDownMgr.populateCodesHtmlDropDownsFromJsonString(this.urlCgiProxyService, this.skatDropDownListPopulationService,
				 model,appUser,CodeDropDownMgr.CODE_302_STATUS_KODER, null, null);
		this.codeDropDownMgr.populateCodesHtmlDropDownsFromJsonString(this.urlCgiProxyService, this.skatDropDownListPopulationService,
				 model,appUser,CodeDropDownMgr.CODE_116_BETALNING_TRANSPORT, null, null);
		
	}
	
	/**
	 * 
	 * @param user
	 * @param avd
	 * @param opd
	 * @param lin
	 * @return
	 */
	private boolean existsSecurityRecord(String user, String avd, String opd, String lin){
		boolean retval = false;
		
		String method = "existsSecurityRecord";
		logger.info("starting " + method + " transaction...");
		
		String BASE_URL = SkatNctsExportUrlDataStore.NCTS_EXPORT_BASE_FETCH_SPECIFIC_SIKKERHET_TOPIC_ITEM_URL;
		String urlRequestParamsKeys = "user=" + user + "&avd=" + avd + "&opd=" + opd  + "&lin=" + lin;   
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
    		JsonSkatNctsExportSpecificTopicItemSecurityContainer securityItemContainer = this.skatNctsExportSpecificTopicItemService.getNctsExportSpecificTopicItemSecurityContainer(jsonPayload);
        	if(securityItemContainer!=null && securityItemContainer.getSecurityline()!=null){
        		if(securityItemContainer.getSecurityline().size()>0){
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
	private boolean updateSecurityRecord(JsonSkatNctsExportSpecificTopicItemRecord recordToValidate, String mode, String user, String avd, String opd){
		boolean retval = true;
		String method = "updateSecurityRecord";
		logger.info("starting " + method + " transaction...");
		
		RpgReturnResponseHandler rpgReturnResponseHandler = new RpgReturnResponseHandler();
		//-------------
		//get BASE URL
        //-------------
		String BASE_URL = SkatNctsExportUrlDataStore.NCTS_EXPORT_BASE_UPDATE_SPECIFIC_SIKKERHET_TOPIC_ITEM_URL;
		
		//-----------------------------------------------------
		//add URL-parameter "mode=U" (Update), (A)dd, (D)elete
		//-----------------------------------------------------
		String urlRequestParamsKeys = "user="+ user + "&mode=" + mode + "&tvavd=" + avd + "&tvtdn=" + opd + "&tvli=" + recordToValidate.getTvli() ;
		
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
    	rpgReturnResponseHandler.evaluateRpgResponseOnTopicItemCreateOrUpdate(rpgReturnPayload);
    	if(rpgReturnResponseHandler.getErrorMessage()!=null && !"".equals(rpgReturnResponseHandler.getErrorMessage())){
    		rpgReturnResponseHandler.setErrorMessage("[ERROR] FATAL on UPDATE: " + rpgReturnResponseHandler.getErrorMessage());
    		retval = false;
    	}else{
    		//Update successfully done!
    		logger.info("[INFO] Record SECURITY-SIKKERHET successfully updated, OK ");
    		//Update now Sikkerhet - part
    	}
    	return retval;
	}
	
	
	//SERVICES
	@Qualifier ("urlCgiProxyService")
	private UrlCgiProxyService urlCgiProxyService;
	@Autowired
	@Required
	public void setUrlCgiProxyService (UrlCgiProxyService value){ this.urlCgiProxyService = value; }
	public UrlCgiProxyService getUrlCgiProxyService(){ return this.urlCgiProxyService; }
		
	@Qualifier ("skatNctsExportSpecificTopicItemService")
	private SkatNctsExportSpecificTopicItemService skatNctsExportSpecificTopicItemService;
	@Autowired
	@Required
	public void setSkatNctsExportSpecificTopicItemService (SkatNctsExportSpecificTopicItemService value){ this.skatNctsExportSpecificTopicItemService = value; }
	public SkatNctsExportSpecificTopicItemService getSkatNctsExportSpecificTopicItemService(){ return this.skatNctsExportSpecificTopicItemService; }
	 
	@Qualifier ("skatDropDownListPopulationService")
	private SkatDropDownListPopulationService skatDropDownListPopulationService;
	@Autowired
	public void setSkatDropDownListPopulationService (SkatDropDownListPopulationService value){ this.skatDropDownListPopulationService=value; }
	public SkatDropDownListPopulationService getSkatDropDownListPopulationService(){return this.skatDropDownListPopulationService;}
	 
	@Qualifier ("skatNctsExportDropDownListPopulationService")
	private SkatNctsExportDropDownListPopulationService skatNctsExportDropDownListPopulationService;
	@Autowired
	public void setSkatNctsExportDropDownListPopulationService (SkatNctsExportDropDownListPopulationService value){ this.skatNctsExportDropDownListPopulationService=value; }
	public SkatNctsExportDropDownListPopulationService getSkatNctsExportDropDownListPopulationService(){return this.skatNctsExportDropDownListPopulationService;}
	 
	@Qualifier ("skatExportTopicListService")
	private SkatExportTopicListService skatExportTopicListService;
	@Autowired
	@Required
	public void setSkatExportTopicListService (SkatExportTopicListService value){ this.skatExportTopicListService = value; }
	public SkatExportTopicListService getSkatExportTopicListService(){ return this.skatExportTopicListService; }
	
	 
}

