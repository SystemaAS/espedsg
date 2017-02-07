package no.systema.ebooking.controller;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.lang.reflect.Field;
import java.util.*;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;


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
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.springframework.web.bind.ServletRequestDataBinder;


//application imports
import no.systema.main.context.TdsAppContext;
import no.systema.main.service.UrlCgiProxyService;
import no.systema.main.service.general.notisblock.NotisblockService;
import no.systema.main.validator.LoginValidator;
import no.systema.main.url.store.MainUrlDataStore;
import no.systema.main.util.AppConstants;
import no.systema.main.util.JsonDebugger;
import no.systema.main.util.MessageNoteManager;
import no.systema.main.util.io.FileContentRenderer;
import no.systema.main.model.SystemaWebUser;
import no.systema.main.model.jsonjackson.general.notisblock.JsonNotisblockContainer;
import no.systema.transportdisp.model.jsonjackson.workflow.order.JsonTransportDispWorkflowSpecificOrderContainer;
import no.systema.transportdisp.model.jsonjackson.workflow.order.JsonTransportDispWorkflowSpecificOrderMessageNoteContainer;
import no.systema.transportdisp.model.jsonjackson.workflow.order.JsonTransportDispWorkflowSpecificOrderMessageNoteRecord;
import no.systema.transportdisp.model.jsonjackson.workflow.order.JsonTransportDispWorkflowSpecificOrderRecord;
import no.systema.transportdisp.url.store.TransportDispUrlDataStore;
import no.systema.transportdisp.util.TransportDispConstants;

//eBooking
import no.systema.ebooking.url.store.EbookingUrlDataStore;
import no.systema.ebooking.util.EbookingConstants;
import no.systema.ebooking.util.RpgReturnResponseHandler;
import no.systema.ebooking.util.manager.CodeDropDownMgr;
import no.systema.ebooking.model.jsonjackson.JsonMainOrderHeaderContainer;
import no.systema.ebooking.model.jsonjackson.JsonMainOrderHeaderFraktbrevContainer;
import no.systema.ebooking.model.jsonjackson.JsonMainOrderHeaderFraktbrevRecord;
import no.systema.ebooking.model.jsonjackson.JsonMainOrderHeaderMessageNoteContainer;
import no.systema.ebooking.model.jsonjackson.JsonMainOrderHeaderMessageNoteRecord;

import no.systema.ebooking.model.jsonjackson.JsonMainOrderHeaderRecord;
import no.systema.ebooking.model.jsonjackson.JsonMainOrderTypesNewRecord;
import no.systema.ebooking.service.EbookingMainOrderHeaderService;
import no.systema.ebooking.service.html.dropdown.EbookingDropDownListPopulationService;
import no.systema.ebooking.mapper.url.request.UrlRequestParameterMapper;
import no.systema.ebooking.validator.OrderHeaderValidator;






/**
 * ebooking Order Header Controller 
 * 
 * @author oscardelatorre
 * @date Jan 06, 2017
 * 
 */

@Controller
@SessionAttributes(AppConstants.SYSTEMA_WEB_USER_KEY)
@Scope("session")
public class EbookingMainOrderHeaderController {
	private static final JsonDebugger jsonDebugger = new JsonDebugger(4000);
	private static Logger logger = Logger.getLogger(EbookingMainOrderHeaderController.class.getName());
	private ModelAndView loginView = new ModelAndView("login");
	private ApplicationContext context;
	private LoginValidator loginValidator = new LoginValidator();
	//
	private CodeDropDownMgr codeDropDownMgr = new CodeDropDownMgr();
	private UrlRequestParameterMapper urlRequestParameterMapper = new UrlRequestParameterMapper();
	private RpgReturnResponseHandler rpgReturnResponseHandler = new RpgReturnResponseHandler();
	private MessageNoteManager messageNoteMgr = new MessageNoteManager();
	
	//private ReflectionUrlStoreMgr reflectionUrlStoreMgr = new ReflectionUrlStoreMgr();
	@PostConstruct
	public void initIt() throws Exception {
		if("DEBUG".equals(AppConstants.LOG4J_LOGGER_LEVEL)){
			logger.setLevel(Level.DEBUG);
		}
	}
	
	/**
	 * 
	 * @param session
	 * @param request
	 * @return
	 */
	@RequestMapping(value="ebooking_mainorder.do", method={RequestMethod.GET, RequestMethod.POST} )
	public ModelAndView doFind(@ModelAttribute ("record") JsonMainOrderHeaderRecord recordToValidate, BindingResult bindingResult, HttpSession session, HttpServletRequest request){
		this.context = TdsAppContext.getApplicationContext();
		Map model = new HashMap();
		
		//String hereff = request.getParameter("hereff");
		//String heunik = request.getParameter("heunik");
		String action = request.getParameter("action");
		boolean isValidRecord = true;
		
		//special case on Create New comming from the order list "Create new order"
		String selectedTypeWithCreateNew = request.getParameter("selectedType");
		JsonMainOrderTypesNewRecord orderTypes = this.getDefaultValuesForCreateNewOrder(model, selectedTypeWithCreateNew); 
		
		//String messageFromContext = this.context.getMessage("user.label",new Object[0], request.getLocale());
		
		ModelAndView successView = new ModelAndView("ebooking_mainorder");
		SystemaWebUser appUser = this.loginValidator.getValidUser(session);
		
		//check user (should be in session already)
		if(appUser==null){
			return loginView;
			
		}else{
			//appUser.setActiveMenu(SystemaWebUser.ACTIVE_MENU_EBOOKING);
			//appUser.setUrlStoreProps(this.reflectionUrlStoreMgr.printProperties("no.systema.transportdisp.url.store.TransportDispUrlDataStore", "html")); //Debug info om UrlStore
			logger.info(Calendar.getInstance().getTime() + " CONTROLLER start - timestamp");
			
			if(EbookingConstants.ACTION_UPDATE.equals(action)){
				//Validation here TODO ... 
				//don't forget model.put("selectedTypeWithCreateNew", selectedTypeWithCreateNew) --> if selectedTypeWithCreateNew!=null...
				//...
				OrderHeaderValidator validator = new OrderHeaderValidator();
				logger.info("Host via HttpServletRequest.getHeader('Host'): " + request.getHeader("Host"));
				//populate all order lines with end-user input in order to validate that at least one line exists.
				this.populateOrderLineRecordsWithUserInput(request, recordToValidate);
				//validate
			    validator.validate(recordToValidate, bindingResult);
			    if(bindingResult.hasErrors()){
		    		logger.info("[ERROR Validation] record does not validate)");
		    		isValidRecord = false;
		    		//populate fallbacks ...
		    		this.populateMessageNotes( appUser, recordToValidate);
		    		this.populateFraktbrev( appUser, recordToValidate);
					model.put(EbookingConstants.DOMAIN_RECORD, recordToValidate);
		    		
			    }else{	
					//Start DML operations if applicable
					StringBuffer errMsg = new StringBuffer();
					int dmlRetval = 0;
					if(recordToValidate.getHeunik()!=null && !"".equals(recordToValidate.getHeunik())){
						//update
						logger.info("doUpdate");
						dmlRetval = this.updateRecord(model, appUser.getUser(), recordToValidate, EbookingConstants.MODE_UPDATE, errMsg);
						if(dmlRetval==0){
							logger.info("[INFO] Record successfully updated, OK ");
							logger.info("[START]: process children <meessageNotes>, <itemLines>, etc update... ");
							//Update the message notes (2 steps: 1.Delete the original ones, 2.Create the new ones)
				    		this.processNewMessageNotes(recordToValidate, appUser);
				    		//Update the order lines
				    		this.processOrderLines(recordToValidate, appUser);
							//postUpdate events on back-end
			    			//this.processPostUpdateEvents(recordToValidate, appUser);
			    			logger.info("[END]: children update");
						}
					}else{
						//create new
						logger.info("doCreate");
						dmlRetval = this.updateRecord(model, appUser.getUser(), recordToValidate, EbookingConstants.MODE_ADD, errMsg);
						model.put("selectType", "");
						if(dmlRetval==0){
							//TODO
							
						}
						
					}
					if(dmlRetval<0){
						isValidRecord = false;
						//populate fall backs
						this.populateMessageNotes( appUser, recordToValidate);
						this.populateFraktbrev( appUser, recordToValidate);
						//domain objects
						model.put(EbookingConstants.DOMAIN_RECORD, recordToValidate);
					}
			    }
				
			}else if(EbookingConstants.ACTION_DELETE.equals(action)){
				
			}
			
			//--------------
			//Fetch record
			//--------------
			if(isValidRecord){
				JsonMainOrderHeaderRecord headerOrderRecord = this.getOrderRecord(appUser, model, orderTypes, recordToValidate.getHereff(), recordToValidate.getHeunik());
				//populate all message notes
				this.populateMessageNotes( appUser, headerOrderRecord);
				//populate fraktbrev lines
				this.populateFraktbrev( appUser, headerOrderRecord);
				//domain objects
				model.put(EbookingConstants.DOMAIN_RECORD, headerOrderRecord);
			}
			//get dropdowns
			this.setCodeDropDownMgr(appUser, model);
			//populate model
			if(action==null || "".equals(action)){
				action = "doUpdate";
			}
			model.put("action", action);
			successView.addObject(EbookingConstants.DOMAIN_MODEL , model);
			
			logger.info("Host via HttpServletRequest.getHeader('Host'): " + request.getHeader("Host"));
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
	@RequestMapping(value="ebooking_delete_order_line.do",  method={RequestMethod.GET} )
	public ModelAndView doDeleteOrderLine(@ModelAttribute ("record") JsonMainOrderHeaderRecord recordToValidate, BindingResult bindingResult, HttpSession session, HttpServletRequest request){
		this.context = TdsAppContext.getApplicationContext();
		logger.info("#HEUNIK:" + recordToValidate.getHeunik());
		logger.info("#HREFF:" + recordToValidate.getHereff());
		//logger.info("#HESTL4:" + recordToValidate.getHestl4());
		//set the order line nr in a place-holder
		recordToValidate.setOrderLineToDelete(request.getParameter("lin"));
		logger.info("#LINENR:" + recordToValidate.getOrderLineToDelete());
		
		ModelAndView successView = new ModelAndView("redirect:ebooking_mainorder.do?action=doFetch&heunik=" + recordToValidate.getHeunik() + "&hereff=" + recordToValidate.getHereff());
		SystemaWebUser appUser = this.loginValidator.getValidUser(session);
		Map model = new HashMap();
		
		//check user (should be in session already)
		if(appUser==null){
			return loginView;
			
		}else{
			logger.info(Calendar.getInstance().getTime() + " CONTROLLER start - timestamp");
    		//UPDATE (Delete)
			logger.info("UPDATE (DELETE) transaction...");
			
			final String UPDATE_BASE_URL = EbookingUrlDataStore.EBOOKING_BASE_WORKFLOW_UPDATE_LINE_MAIN_ORDER_FRAKTBREV_URL;
			String urlRequestKeyParams = this.getRequestUrlKeyParameters(recordToValidate, appUser, EbookingConstants.MODE_DELETE );
			logger.info(Calendar.getInstance().getTime() + " CGI-start timestamp");
			logger.info("URL: " + UPDATE_BASE_URL);
	    	logger.info("URL PARAMS: " + urlRequestKeyParams );
	    	//-----------------------------------------------
	    	//EXECUTE the UPDATE - DELETE (RPG program) here 
	    	//-----------------------------------------------
	    	 
	    	String rpgReturnPayload = this.urlCgiProxyService.getJsonContent(UPDATE_BASE_URL, urlRequestKeyParams);
			//Debug --> 
	    	logger.info("Checking errMsg in rpgReturnPayload [UPDATE - DELETE]:" + rpgReturnPayload);
	    	//we must evaluate a return RPG code in order to know if the Update was OK or not
	    	rpgReturnResponseHandler.evaluateRpgResponseOnEditSpecificOrder(rpgReturnPayload);
	    	if(rpgReturnResponseHandler.getErrorMessage()!=null && !"".equals(rpgReturnResponseHandler.getErrorMessage())){
	    		rpgReturnResponseHandler.setErrorMessage("[ERROR] FATAL on DELETE: " + rpgReturnResponseHandler.getErrorMessage());
	    		this.setFatalError(model, rpgReturnResponseHandler, recordToValidate);
	    	}else{
	    		//Update successfully done!
	    		logger.info("[INFO] Record successfully updated, OK ");
    		}	
	    	
    		return successView;
		
		}
	}
	/**
	 * 
	 * @param recordToValidate
	 * @param appUser
	 * @param mode
	 * @return
	 */
	private String getRequestUrlKeyParameters(JsonMainOrderHeaderRecord recordToValidate, SystemaWebUser appUser, String mode){
		StringBuffer urlRequestParamsKeys = new StringBuffer();
		
		if(EbookingConstants.MODE_UPDATE.equalsIgnoreCase(mode) || EbookingConstants.MODE_ADD.equalsIgnoreCase(mode)){
			urlRequestParamsKeys.append("user=" + appUser.getUser());
			urlRequestParamsKeys.append("&unik=" + recordToValidate.getHeunik());
			urlRequestParamsKeys.append("&reff=" + recordToValidate.getHereff());
			urlRequestParamsKeys.append("&mode=" + mode);
			
			
		}else if(EbookingConstants.MODE_DELETE.equalsIgnoreCase(mode)){
			urlRequestParamsKeys.append("user=" + appUser.getUser());
			urlRequestParamsKeys.append("&unik=" + recordToValidate.getHeunik());
			urlRequestParamsKeys.append("&reff=" + recordToValidate.getHereff());
			urlRequestParamsKeys.append("&fbn=1");
			urlRequestParamsKeys.append("&lin=" + recordToValidate.getOrderLineToDelete());
			urlRequestParamsKeys.append("&mode=" + TransportDispConstants.MODE_DELETE);
			
		}
		
		return urlRequestParamsKeys.toString();
	}
	
	/**
	 * 
	 * @param recordToValidate
	 * @param appUser
	 */
	private void processNewMessageNotes(JsonMainOrderHeaderRecord recordToValidate, SystemaWebUser appUser){
		if(recordToValidate !=null){
			//CONSIGNEE (RECEIVER)
			if(recordToValidate.getMessageNoteConsignee()!=null && !"".equals(recordToValidate.getMessageNoteConsignee())){
				//Delete all values
				this.deleteOriginalMessageNote(JsonMainOrderHeaderRecord.MESSAGE_NOTE_CONSIGNEE, recordToValidate, appUser);
				//Add new values
				String [] messageNoteConsignee = this.messageNoteMgr.getChunksOfMessageNote(recordToValidate.getMessageNoteConsignee());
				this.updateMessageNote(messageNoteConsignee, JsonMainOrderHeaderRecord.MESSAGE_NOTE_CONSIGNEE, recordToValidate, appUser);
			}else{
				if(recordToValidate.getMessageNoteConsigneeOriginal()!=null && !"".equals(recordToValidate.getMessageNoteConsigneeOriginal())){
					//Delete all values
					this.deleteOriginalMessageNote(JsonMainOrderHeaderRecord.MESSAGE_NOTE_CONSIGNEE, recordToValidate, appUser);
				}
			}
			/**
			//CARRIER
			if(recordToValidate.getMessageNoteCarrier()!=null && !"".equals(recordToValidate.getMessageNoteCarrier())){
				//Delete all values
				this.deleteOriginalMessageNote(JsonMainOrderHeaderRecord.MESSAGE_NOTE_CARRIER, recordToValidate, appUser);
				//Add new values
				String [] messageNoteCarrier = this.messageNoteMgr.getChunksOfMessageNote(recordToValidate.getMessageNoteCarrier());
				this.updateMessageNote(messageNoteCarrier, JsonMainOrderHeaderRecord.MESSAGE_NOTE_CARRIER, recordToValidate, appUser);
			}else{
				//In case the user removes all lines without writing new ones
				if(recordToValidate.getMessageNoteCarrierOriginal()!=null && !"".equals(recordToValidate.getMessageNoteCarrierOriginal())){
					//Delete all values
					this.deleteOriginalMessageNote(JsonMainOrderHeaderRecord.MESSAGE_NOTE_CARRIER, recordToValidate, appUser);
				}
			}
			
			
			//INTERNAL
			if(recordToValidate.getMessageNoteInternal()!=null && !"".equals(recordToValidate.getMessageNoteInternal())){
				//Delete all values
				this.deleteOriginalMessageNote(JsonMainOrderHeaderRecord.MESSAGE_NOTE_INTERNAL, recordToValidate, appUser);
				//Add new values
				String [] messageNoteInternal = this.messageNoteMgr.getChunksOfMessageNote(recordToValidate.getMessageNoteInternal());
				this.updateMessageNote(messageNoteInternal, JsonMainOrderHeaderRecord.MESSAGE_NOTE_INTERNAL, recordToValidate, appUser);
			}else{
				if(recordToValidate.getMessageNoteInternalOriginal()!=null && !"".equals(recordToValidate.getMessageNoteInternalOriginal())){
					//Delete all values
					this.deleteOriginalMessageNote(JsonMainOrderHeaderRecord.MESSAGE_NOTE_INTERNAL, recordToValidate, appUser);
				}
			}
			**/
		}
	}
	
	/**
	 * 
	 * @param messageNote
	 * @param messageParty
	 * @param record
	 * @param appUser
	 */
	private void updateMessageNote(String[] messageNote, String messageParty, JsonMainOrderHeaderRecord record, SystemaWebUser appUser){
		String CARRIAGE_RETURN = "[\n\r]";
		List<String> messageNotePayload = Arrays.asList(messageNote);
		int lineNr = 0;
		
		for(String linePayload: messageNotePayload){
			linePayload = linePayload.replaceAll(CARRIAGE_RETURN, "");
			linePayload = linePayload.trim();
			lineNr++;
			//---------------------------
			//get BASE URL = RPG-PROGRAM
	        //---------------------------
			if(linePayload!=null && !"".equals(linePayload)){
				String BASE_URL_UPDATE = MainUrlDataStore.SYSTEMA_NOTIS_BLOCK_UPDATE_ITEMLINE_URL;
				boolean doUpdate = false;
				//------------------
				//add URL-parameter
				//------------------
				StringBuffer urlRequestParamsKeysBuffer = new StringBuffer();
				urlRequestParamsKeysBuffer.append("user=" + appUser.getUser());
				urlRequestParamsKeysBuffer.append("&frunik=" + record.getHeunik());
				urlRequestParamsKeysBuffer.append("&frreff=" + record.getHereff());
				urlRequestParamsKeysBuffer.append("&frtdt=" + record.getMessageNoteDate());
				
				if(JsonMainOrderHeaderRecord.MESSAGE_NOTE_CONSIGNEE.equals(messageParty)){
					if(lineNr==1){
						doUpdate = true;
						urlRequestParamsKeysBuffer.append("&frtli=" + lineNr);
					}else if (lineNr==2){
						doUpdate = true;
						urlRequestParamsKeysBuffer.append("&frtli=" + lineNr);
					}
				}else if(JsonMainOrderHeaderRecord.MESSAGE_NOTE_CARRIER.equals(messageParty)){
					if(lineNr==1){
						doUpdate = true;
						urlRequestParamsKeysBuffer.append("&frtli=3"); //always 1:st line for carrier
					}else if(lineNr==2){
						doUpdate = true;
						urlRequestParamsKeysBuffer.append("&frtli=4"); //always 2:nd line for carrier
					}
				}else if(JsonMainOrderHeaderRecord.MESSAGE_NOTE_INTERNAL.equals(messageParty)){
					if(lineNr==1){
						doUpdate = true;
						urlRequestParamsKeysBuffer.append("&frtli=5"); //always 1:st line for internal
					}else if(lineNr==2){
						doUpdate = true;
						urlRequestParamsKeysBuffer.append("&frtli=6"); //always 2:nd line for internal
					}
				}else{
					//nothing
				}
				
				urlRequestParamsKeysBuffer.append("&frttxt=" + linePayload);
				urlRequestParamsKeysBuffer.append("&frtkod=" + messageParty);		 
				urlRequestParamsKeysBuffer.append("&mode=A");
				if(doUpdate){
					String urlRequestParams = urlRequestParamsKeysBuffer.toString();
					logger.info("URL: " + BASE_URL_UPDATE);
					logger.info("PARAMS: " + urlRequestParams);
					//logger.info(Calendar.getInstance().getTime() +  " CGI-start timestamp");
					String jsonPayload = this.urlCgiProxyService.getJsonContent(BASE_URL_UPDATE, urlRequestParams);
					logger.info(jsonPayload);
					//logger.info(Calendar.getInstance().getTime() +  " CGI-end timestamp");
					 
					JsonNotisblockContainer jsonNotisblockContainer = this.notisblockService.getNotisblockListContainer(jsonPayload);
					//logger.info("JsonNotisblockContainer:" + jsonNotisblockContainer);
					if(jsonNotisblockContainer!=null){
						//logger.info("A:" + jsonNotisblockContainer.getErrMsg());
						if( !"".equals(jsonNotisblockContainer.getErrMsg()) ){
							//Debug
							logger.info("[ERROR]:" + jsonNotisblockContainer.getErrMsg());
						}
					}
				}
			}
		}
	}
	/**
	 * 
	 * @param messageParty
	 * @param record
	 * @param appUser
	 */
	private void deleteOriginalMessageNote( String messageParty, JsonMainOrderHeaderRecord record, SystemaWebUser appUser){
		//we do this in order to secure a total delete no matter how many lines, otherwise it gets complex in case validation errors arise
		int TOTAL_NUMBER_OF_LINES_CEILING_FOR_DELETE_LOOP = 10;
		for(int lineNr=1; lineNr<=TOTAL_NUMBER_OF_LINES_CEILING_FOR_DELETE_LOOP;lineNr++){
			boolean doUpdate = false;
			//---------------------------
			//get BASE URL = RPG-PROGRAM
	        //---------------------------
			String BASE_URL_UPDATE = MainUrlDataStore.SYSTEMA_NOTIS_BLOCK_UPDATE_ITEMLINE_URL;
			//------------------
			//add URL-parameter
			//------------------
			StringBuffer urlRequestParamsKeysBuffer = new StringBuffer();
			urlRequestParamsKeysBuffer.append("user=" + appUser.getUser());
			urlRequestParamsKeysBuffer.append("&frunik=" + record.getHeunik());
			urlRequestParamsKeysBuffer.append("&frreff=" + record.getHereff());
			urlRequestParamsKeysBuffer.append("&frtdt=" + record.getMessageNoteDate());
			
			if(JsonMainOrderHeaderRecord.MESSAGE_NOTE_CONSIGNEE.equals(messageParty)){
				if(lineNr==1 || lineNr==2){
					doUpdate = true;
					urlRequestParamsKeysBuffer.append("&frtli=" + lineNr);
				}
			}else if(JsonMainOrderHeaderRecord.MESSAGE_NOTE_CARRIER.equals(messageParty)){
				if(lineNr==3 || lineNr==4){
					doUpdate = true;
					urlRequestParamsKeysBuffer.append("&frtli=" + lineNr);
				}
			}else if(JsonMainOrderHeaderRecord.MESSAGE_NOTE_INTERNAL.equals(messageParty)){
				if(lineNr==5 || lineNr==6){
					doUpdate = true;
					urlRequestParamsKeysBuffer.append("&frtli=" + lineNr);
				}
			}else{
				//nothing
			}
			//N/A -->urlRequestParamsKeysBuffer.append("&frtkod=" + messageParty);
			urlRequestParamsKeysBuffer.append("&mode=D");
			String urlRequestParams = urlRequestParamsKeysBuffer.toString();
			//DEBUG
			if(doUpdate){
				logger.info("URL: " + BASE_URL_UPDATE);
				logger.info("PARAMS: " + urlRequestParams);
				logger.info(Calendar.getInstance().getTime() +  " CGI-start timestamp");
				String jsonPayload = this.urlCgiProxyService.getJsonContent(BASE_URL_UPDATE, urlRequestParams);
				//DEBUG only 5
				logger.info(jsonPayload);
				logger.info(Calendar.getInstance().getTime() +  " CGI-end timestamp");
				JsonNotisblockContainer jsonNotisblockContainer = this.notisblockService.getNotisblockListContainer(jsonPayload);
				//logger.info("JsonNotisblockContainer:" + jsonNotisblockContainer);
				if(jsonNotisblockContainer!=null){
					//logger.info("A:" + jsonNotisblockContainer.getErrMsg());
					if( !"".equals(jsonNotisblockContainer.getErrMsg()) ){
						//Debug
						logger.info("[WARNING (delete lines)]:" + jsonNotisblockContainer.getErrMsg());
					}
				}
			}
		}
	}
	/**
	 * 
	 * @param appUser
	 * @param orderRecord
	 */
	private void populateMessageNotes(SystemaWebUser appUser, JsonMainOrderHeaderRecord orderRecord){
		
		Collection<JsonMainOrderHeaderMessageNoteRecord> messageNoteConsignee = null;
		Collection<JsonMainOrderHeaderMessageNoteRecord> messageNoteCarrier = null;
		Collection<JsonMainOrderHeaderMessageNoteRecord> messageNoteInternal = null;
		
		messageNoteConsignee = this.fetchMessageNote(appUser.getUser(), orderRecord, JsonMainOrderHeaderRecord.MESSAGE_NOTE_CONSIGNEE);
		messageNoteCarrier = this.fetchMessageNote(appUser.getUser(), orderRecord, JsonMainOrderHeaderRecord.MESSAGE_NOTE_CARRIER);
		messageNoteInternal = this.fetchMessageNote(appUser.getUser(), orderRecord, JsonMainOrderHeaderRecord.MESSAGE_NOTE_INTERNAL);
		
		StringBuffer brConsignee = new StringBuffer();
		for(JsonMainOrderHeaderMessageNoteRecord record: messageNoteConsignee ){
			if(record.getFrtli()!=null || !"".equals(record.getFrtli())){
				brConsignee.append(record.getFrttxt() + "\n");
				//get the date
				if(record.getFrtdt()!=null || !"".equals(record.getFrtdt())){
					orderRecord.setMessageNoteDate(record.getFrtdt());
				}
			}
			
		}
		StringBuffer brCarrier = new StringBuffer();
		for(JsonMainOrderHeaderMessageNoteRecord record: messageNoteCarrier ){
			if(record.getFrtli()!=null || !"".equals(record.getFrtli())){
				brCarrier.append(record.getFrttxt() + "\n");
				if(record.getFrtdt()!=null || !"".equals(record.getFrtdt())){
					//get the date if no date has been fetched before
					if(orderRecord.getMessageNoteDate()==null){
						orderRecord.setMessageNoteDate(record.getFrtdt());
					}
				}
			}
		}
		StringBuffer brInternal = new StringBuffer();
		for(JsonMainOrderHeaderMessageNoteRecord record: messageNoteInternal ){
			if(record.getFrtkod()==null || "".equals(record.getFrtkod())){ //since we must filter in this specific type (blank)
				if(record.getFrtli()!=null || !"".equals(record.getFrtli())){
					brInternal.append(record.getFrttxt() + "\n");
					if(record.getFrtdt()!=null || !"".equals(record.getFrtdt())){
						//get the date if no date has been fetched before
						if(orderRecord.getMessageNoteDate()==null){
							orderRecord.setMessageNoteDate(record.getFrtdt());
						}
					}
				}
			}
		}
		//populate final message notes now
		orderRecord.setMessageNoteConsignee(brConsignee.toString());
		orderRecord.setMessageNoteCarrier(brCarrier.toString());
		orderRecord.setMessageNoteInternal(brInternal.toString());
		
	}
	
	/**
	 * 
	 * @param applicationUser
	 * @param orderRecord
	 * @param type
	 * @return
	 */
	public Collection<JsonMainOrderHeaderMessageNoteRecord> fetchMessageNote(String applicationUser, JsonMainOrderHeaderRecord orderRecord, String type){
		Collection<JsonMainOrderHeaderMessageNoteRecord> outputList = new ArrayList<JsonMainOrderHeaderMessageNoteRecord>();
		//===========
		//FETCH LIST
		//===========
		//get BASE URL
    		final String BASE_LIST_URL = EbookingUrlDataStore.EBOOKING_BASE_WORKFLOW_FETCH_MAIN_ORDER_MESSAGE_NOTE_URL;
    		//add URL-parameters
    		StringBuffer urlRequestParams = new StringBuffer();
    		urlRequestParams.append("user=" + applicationUser);
    		if(orderRecord.getHeunik()!=null && !"".equals(orderRecord.getHeunik())){ urlRequestParams.append("&unik=" + orderRecord.getHeunik()); }
    		if(orderRecord.getHereff()!=null && !"".equals(orderRecord.getHereff())){ urlRequestParams.append("&reff=" + orderRecord.getHereff()); }
    		if(type!=null && !"".equals(type)){ urlRequestParams.append("&part=" + type); }
    		
    		
    		logger.info(Calendar.getInstance().getTime() + " CGI-start timestamp");
	    	logger.info("URL: " + BASE_LIST_URL);
	    	logger.info("URL PARAMS: " + urlRequestParams);
	    	String jsonPayload = this.urlCgiProxyService.getJsonContent(BASE_LIST_URL, urlRequestParams.toString());
		//Debug --> 
	    	logger.debug(jsonPayload);
	    	logger.info(Calendar.getInstance().getTime() +  " CGI-end timestamp");
	    	if(jsonPayload!=null){
	    		JsonMainOrderHeaderMessageNoteContainer messageNoteContainer = this.ebookingMainOrderHeaderService.getMessageNoteContainer(jsonPayload);
	    		outputList = messageNoteContainer.getFreetextlist();
	    		for(JsonMainOrderHeaderMessageNoteRecord note: outputList){
	    			//logger.info(note.getFrttxt());
	    		}
			logger.info(Calendar.getInstance().getTime() + " CONTROLLER end - timestamp");
		}
	    
	    	return outputList;
		
	}
	
	/**
	 * 
	 * @param request
	 * @param recordToValidate
	 */
	private void populateOrderLineRecordsWithUserInput(HttpServletRequest request, JsonMainOrderHeaderRecord recordToValidate){
		int totalNumberOfLines = this.getTotalNumberOfLines(recordToValidate);
		List<JsonMainOrderHeaderFraktbrevRecord> list = new ArrayList<JsonMainOrderHeaderFraktbrevRecord>();
		JsonMainOrderHeaderFraktbrevRecord fraktbrevRecord = null;
		
		for(int counter=1;counter<=totalNumberOfLines;counter++){
			fraktbrevRecord = new JsonMainOrderHeaderFraktbrevRecord();
			String lineNr = request.getParameter("fvlinr_" + counter);
			if(lineNr!=null && !"".equals(lineNr)){
				fraktbrevRecord.setFvlinr(lineNr);
			}
			fraktbrevRecord.setFmmrk1(request.getParameter("fmmrk1_" + counter));
			fraktbrevRecord.setFvant(request.getParameter("fvant_" + counter));
			fraktbrevRecord.setFvpakn(request.getParameter("fvpakn_" + counter));
			fraktbrevRecord.setFvvt(request.getParameter("fvvt_" + counter));
			fraktbrevRecord.setFvvkt(request.getParameter("fvvkt_" + counter));
			fraktbrevRecord.setFvvol(request.getParameter("fvvol_" + counter));
			fraktbrevRecord.setFvlm(request.getParameter("fvlm_" + counter));
			fraktbrevRecord.setFvlm2(request.getParameter("fvlm2_" + counter));
			fraktbrevRecord.setFvlen(request.getParameter("fvlen_" + counter));
			fraktbrevRecord.setFvbrd(request.getParameter("fvbrd_" + counter));
			fraktbrevRecord.setFvhoy(request.getParameter("fvhoy_" + counter));
			//farlig goods
			fraktbrevRecord.setFfunnr(request.getParameter("ffunnr_" + counter));
			fraktbrevRecord.setFfembg(request.getParameter("ffembg_" + counter));
			fraktbrevRecord.setFfindx(request.getParameter("ffindx_" + counter));
			
			fraktbrevRecord.setFfantk(request.getParameter("ffantk_" + counter));
			fraktbrevRecord.setFfante(request.getParameter("ffante_" + counter));
			fraktbrevRecord.setFfenh(request.getParameter("ffenh_" + counter));
			list.add(fraktbrevRecord);
			
		}
		logger.info("********** order lines list, SIZE:" + list.size());
		recordToValidate.setFraktbrevList(list);
	}
	/**
	 * 
	 * @param recordToValidate
	 * @return
	 */
	private int getTotalNumberOfLines(JsonMainOrderHeaderRecord recordToValidate){
		//check the total number of lines
		int totalNumberOfLines = EbookingConstants.CONSTANT_TOTAL_NUMBER_OF_ORDER_LINES; //Default
		if(!"".equals(recordToValidate.getTotalNumberOfLines()) && recordToValidate.getTotalNumberOfLines()!=null){
			try{
				int tmpLimit = Integer.parseInt(recordToValidate.getTotalNumberOfLines());
				if(tmpLimit>totalNumberOfLines){
					totalNumberOfLines = Integer.parseInt(recordToValidate.getTotalNumberOfLines());
				}
			}catch(Exception e){
				StringWriter errors = new StringWriter();
				e.printStackTrace(new PrintWriter(errors));
				logger.info(errors);
			}
		}
		return totalNumberOfLines;
	}
	
	/**
	 * 
	 * @param recordToValidate
	 * @param appUser
	 */
	private void processOrderLines(JsonMainOrderHeaderRecord recordToValidate, SystemaWebUser appUser){
		logger.info("Inside:processOrderLines");
		//check the total number of lines in order to input a new linenr
		int i=1;
		for(JsonMainOrderHeaderFraktbrevRecord fraktbrevRecord : recordToValidate.getFraktbrevList()){
			String lineNr = fraktbrevRecord.getFvlinr();
			/* Debug
		 	logger.info("RETURN RECORD fvli:" + fraktbrevRecord.getFvlinr());
			logger.info("RETURN RECORD desc:" + fraktbrevRecord.getFvvt());
			logger.info("RETURN RECORD ant:" + fraktbrevRecord.getFvant());
			logger.info("RETURN RECORD brd:" + fraktbrevRecord.getFvbrd());
			logger.info("RETURN RECORD lm:" + fraktbrevRecord.getFvlm());
			*/
			String mode = EbookingConstants.MODE_ADD;
			if(lineNr!=null && !"".equals(lineNr) ){ 
				mode = EbookingConstants.MODE_UPDATE; }
			else{
				//this line is new!
				lineNr = String.valueOf(i);
			}
			if(this.validMandatoryFieldsFraktbrev(fraktbrevRecord)){
				//Start with the update (mode=(A)dd,(D)elete,(U)pdate)
				String BASE_URL_UPDATE = EbookingUrlDataStore.EBOOKING_BASE_WORKFLOW_UPDATE_LINE_MAIN_ORDER_FRAKTBREV_URL;
				//------------------
				//add URL-parameter
				//------------------
				StringBuffer urlRequestParamsKeysBuffer = new StringBuffer();
				urlRequestParamsKeysBuffer.append("user=" + appUser.getUser());
				//urlRequestParamsKeysBuffer.append("&avd=" + recordToValidate.getHeavd()); OBSOLETE for eBooking, but ok for history vs Bring (Work with trips)
				//urlRequestParamsKeysBuffer.append("&opd=" + recordToValidate.getHeopd()); OBSOLETE for eBooking, but ok for history vs Bring (Work with trips)
				urlRequestParamsKeysBuffer.append("&unik=" + recordToValidate.getHeunik());
				urlRequestParamsKeysBuffer.append("&reff=" + recordToValidate.getHereff());
				urlRequestParamsKeysBuffer.append("&fbn=1");
				urlRequestParamsKeysBuffer.append("&lin=" + lineNr);
				urlRequestParamsKeysBuffer.append(this.getFvUrlRequestParamsForUpdate(fraktbrevRecord));
				urlRequestParamsKeysBuffer.append("&mode=" + mode);
				
				String urlRequestParams = urlRequestParamsKeysBuffer.toString();
				logger.info("URL: " + BASE_URL_UPDATE);
				logger.info("PARAMS: " + urlRequestParams);
				//logger.info(Calendar.getInstance().getTime() +  " CGI-start timestamp");
				String jsonPayload = this.urlCgiProxyService.getJsonContent(BASE_URL_UPDATE, urlRequestParams);
				logger.info(jsonPayload);
				//logger.info(Calendar.getInstance().getTime() +  " CGI-end timestamp");
				if(jsonPayload!=null){ 
					JsonMainOrderHeaderFraktbrevContainer fraktbrevContainer = this.ebookingMainOrderHeaderService.getFraktbrevContainer(jsonPayload);
					//logger.info("JsonNotisblockContainer:" + jsonNotisblockContainer);
					if(fraktbrevContainer!=null){
						//logger.info("A:" + jsonNotisblockContainer.getErrMsg());
						if( !"".equals(fraktbrevContainer.getErrMsg()) ){
							//Debug
							logger.info("[ERROR]:" + fraktbrevContainer.getErrMsg());
						}
					}
				}
			}
			//counter to keep track of lines with mode=A (new ones...)
			i++;
			
		}
		
	}
	/**
	 * 
	 * @param fraktbrevRecord
	 * @return
	 */
	private boolean validMandatoryFieldsFraktbrev(JsonMainOrderHeaderFraktbrevRecord fraktbrevRecord){
		boolean retval = false;
		String ant = fraktbrevRecord.getFvant();
		String vkt = fraktbrevRecord.getFvvkt();
		String desc = fraktbrevRecord.getFvvt();
		
		if( (ant!=null && !"".equals(ant))  && 
			(vkt!=null && !"".equals(vkt))  && 
			(desc!=null && !"".equals(desc))){
	
			retval = true;
		}
		return retval;
	}
	/**
	 * 
	 * @param fraktbrevRecord
	 * @return
	 */
	private String getFvUrlRequestParamsForUpdate(JsonMainOrderHeaderFraktbrevRecord fraktbrevRecord){
		StringBuffer urlRequestParams = new StringBuffer();
		//Build the param-string
		if(fraktbrevRecord.getFvlinr()!=null && !"".equals(fraktbrevRecord.getFvlinr())){
			urlRequestParams.append("&fvlinr=" + fraktbrevRecord.getFvlinr());
		}
		urlRequestParams.append("&fmmrk1=" + fraktbrevRecord.getFmmrk1());
		urlRequestParams.append("&fvant=" + fraktbrevRecord.getFvant());
		urlRequestParams.append("&fvpakn=" + fraktbrevRecord.getFvpakn());
		urlRequestParams.append("&fvvt=" + fraktbrevRecord.getFvvt());
		urlRequestParams.append("&fvvkt=" + fraktbrevRecord.getFvvkt());
		urlRequestParams.append("&fvvol=" + fraktbrevRecord.getFvvol());
		urlRequestParams.append("&fvlm=" + fraktbrevRecord.getFvlm());
		urlRequestParams.append("&fvlm2=" + fraktbrevRecord.getFvlm2());
		urlRequestParams.append("&fvlen=" + fraktbrevRecord.getFvlen());
		urlRequestParams.append("&fvbrd=" + fraktbrevRecord.getFvbrd());
		urlRequestParams.append("&fvhoy=" + fraktbrevRecord.getFvhoy());
		//farlig goods
		urlRequestParams.append("&ffunnr=" + fraktbrevRecord.getFfunnr());
		urlRequestParams.append("&ffembg=" + fraktbrevRecord.getFfembg());
		urlRequestParams.append("&ffindx=" + fraktbrevRecord.getFfindx());
		
		urlRequestParams.append("&ffantk=" + fraktbrevRecord.getFfantk());
		urlRequestParams.append("&ffante=" + fraktbrevRecord.getFfante());
		urlRequestParams.append("&ffenh=" + fraktbrevRecord.getFfenh());
		
		return urlRequestParams.toString();
	}
	/**
	 * 
	 * @param appUser
	 * @param orderRecord
	 */
	private void populateFraktbrev(SystemaWebUser appUser, JsonMainOrderHeaderRecord orderRecord){
		
		//---------------------------
		//get BASE URL = RPG-PROGRAM
        //---------------------------
		String BASE_URL = EbookingUrlDataStore.EBOOKING_BASE_WORKFLOW_FETCH_LIST_MAIN_ORDER_FRAKTBREV_URL;
		
		StringBuffer urlRequestParamsKeys = new StringBuffer();
		urlRequestParamsKeys.append("user=" + appUser.getUser());
		urlRequestParamsKeys.append("&unik=" + orderRecord.getHeunik());
		urlRequestParamsKeys.append("&reff=" + orderRecord.getHereff());
		urlRequestParamsKeys.append("&fbn=1");
		
		logger.info(Calendar.getInstance().getTime() + " CGI-start timestamp");
    	List<JsonMainOrderHeaderFraktbrevRecord> fraktbrevList = new ArrayList<JsonMainOrderHeaderFraktbrevRecord>();
    	
    		
    	//Only with EXISTENT ORDER
    	if( (orderRecord.getHereff()!=null && !"".equals(orderRecord.getHereff())) &&
			(orderRecord.getHeunik()!=null && !"".equals(orderRecord.getHeunik())) 	){
			//----------------------------------------------------------------------------
	    	//EXECUTE the UPDATE (RPG program) here (STEP [2] when creating a new record)
	    	//----------------------------------------------------------------------------
    		logger.info("URL: " + BASE_URL);
        	logger.info("URL PARAMS: " + urlRequestParamsKeys.toString());
        	String jsonPayload = this.urlCgiProxyService.getJsonContent(BASE_URL, urlRequestParamsKeys.toString());
	    	//Debug -->
		    logger.info(jsonPayload);
		    if(jsonPayload!=null){
		    	JsonMainOrderHeaderFraktbrevContainer container = this.ebookingMainOrderHeaderService.getFraktbrevContainer(jsonPayload);
				if(container!=null){
		    		for (JsonMainOrderHeaderFraktbrevRecord fraktbrevRecord: container.getAwblinelist()){
						fraktbrevList.add(fraktbrevRecord);
					}
					//Ensures that the array ALWAYS shows the required 4 lines (with value or empty)
		    		this.populateEmptyFraktbrevList(fraktbrevList);
				}
	    	}
    	}else{
    		this.populateEmptyFraktbrevList(fraktbrevList);
    	}
    	logger.info(Calendar.getInstance().getTime() + " CGI-stop timestamp");
    	
    	//populate the list on parent record
		orderRecord.setFraktbrevList(fraktbrevList);
	}
	/**
	 * 
	 * @param fraktbrevList
	 */
	private void populateEmptyFraktbrevList (List<JsonMainOrderHeaderFraktbrevRecord> fraktbrevList){
		if(fraktbrevList==null || fraktbrevList.size()<EbookingConstants.CONSTANT_TOTAL_NUMBER_OF_ORDER_LINES){
			int start = fraktbrevList.size();
			for(int i = ++start;i<=EbookingConstants.CONSTANT_TOTAL_NUMBER_OF_ORDER_LINES;i++){
				//logger.info("#########################:" + i);
				fraktbrevList.add(new JsonMainOrderHeaderFraktbrevRecord());
			}
		}
	}
	
	/**
	 * 
	 * @param model
	 * @param applicationUser
	 * @param recordToValidate
	 * @param mode
	 * @param errMsg
	 * @return
	 */
	private int updateRecord(Map model, String applicationUser, JsonMainOrderHeaderRecord recordToValidate, String mode, StringBuffer errMsg){
		int retval = 0;
		
		final String BASE_URL = EbookingUrlDataStore.EBOOKING_BASE_UPDATE_SPECIFIC_ORDER_URL;
		String urlRequestParamsKeys = "user=" + applicationUser + "&mode=" + mode;
		String urlRequestParams = this.urlRequestParameterMapper.getUrlParameterValidString((recordToValidate));
		//put the final valid param. string
		urlRequestParams = urlRequestParamsKeys + urlRequestParams;
		
		logger.info(Calendar.getInstance().getTime() + " CGI-start timestamp");
    	logger.info("URL: " + jsonDebugger.getBASE_URL_NoHostName(BASE_URL));
    	logger.info("URL PARAMS: " + urlRequestParams);
    	
    	
    	String rpgReturnPayload = this.urlCgiProxyService.getJsonContent(BASE_URL, urlRequestParams);
    	logger.info(rpgReturnPayload);
    	rpgReturnResponseHandler.evaluateRpgResponseOnUpdate(rpgReturnPayload);
    	if(rpgReturnResponseHandler.getErrorMessage()!=null && !"".equals(rpgReturnResponseHandler.getErrorMessage())){
    		rpgReturnResponseHandler.setErrorMessage("[ERROR] FATAL on UPDATE: " + rpgReturnResponseHandler.getErrorMessage());
    		this.setFatalError(model, rpgReturnResponseHandler, recordToValidate);
    		//isValidCreatedRecordTransactionOnRPG = false;
    		retval = -1; 
    		
    	}else{
    		//Update successfully done!
    		logger.info("[INFO] Record successfully updated, OK ");
    		//newly created id. Set it in the recordToValidate in order to fetch (refresh) later on
    		if(EbookingConstants.MODE_ADD.equals(mode)){
    			recordToValidate.setHeunik(rpgReturnResponseHandler.getHeunik());
    		}
    		
    		/*TODO messages ...
    		this.updateMessageNote(messageNote, recordToValidate.getTuavd(), recordToValidate.getTupro(), appUser);
    		
    		//set message note (after update aka refresh)
    		Collection<JsonTransportDispWorkflowSpecificTripMessageNoteRecord> messageNoteAfterUpdate = null;
    		messageNoteAfterUpdate = this.controllerAjaxCommonFunctionsMgr.fetchMessageNote(appUser.getUser(), recordToValidate.getTuavd(), recordToValidate.getTupro());
    		StringBuffer br = new StringBuffer();
    		for(JsonTransportDispWorkflowSpecificTripMessageNoteRecord record:messageNoteAfterUpdate ){
    			br.append(record.getFrttxt() + "\n");
    		}
    		recordToValidate.setMessageNote(br.toString());
    		//logger.info(recordToValidate.getMessageNote());
    		*/ 
			//put domain objects
	    	//this.setDomainObjectsInView(session, model, recordToValidate );
	    	
    	}
    	
    	  	
    	return retval;
	}
	
	/**
	 * 
	 * @param appUser
	 * @param model
	 * @param orderTypes
	 * @param hereff
	 * @param heunik
	 * @return
	 */
	private JsonMainOrderHeaderRecord getOrderRecord(SystemaWebUser appUser, Map model, JsonMainOrderTypesNewRecord orderTypes, String hereff, String heunik ){
		JsonMainOrderHeaderRecord record = new JsonMainOrderHeaderRecord();
			
		final String BASE_URL = EbookingUrlDataStore.EBOOKING_BASE_FETCH_SPECIFIC_ORDER_URL;
		//add URL-parameters
		StringBuffer urlRequestParams = new StringBuffer();
		urlRequestParams.append("user=" + appUser.getUser() + "&mode=G");
		if( (hereff!=null && !"".equals(hereff)) && (heunik!=null && !"".equals(heunik)) ){
			//Meaning fetching to an update
			urlRequestParams.append("&heunik=" + heunik + "&hereff=" + hereff );
		}else{
			//Meaning preparing a create new ...
			urlRequestParams.append("&heunik=&hereff=");
			urlRequestParams.append("&newavd=" + orderTypes.getNewAvd() + "&newmodul=" + orderTypes.getNewModul()+ "&newmodul2=" + orderTypes.getNewModul2());
			urlRequestParams.append("&newlandkode=" + orderTypes.getNewLandKode() + "&newsidesk=" + orderTypes.getNewSideSK() + "&newtext=" +  orderTypes.getNewText());
		}
		
		//session.setAttribute(TransportDispConstants.ACTIVE_URL_RPG_TRANSPORT_DISP, BASE_URL + "==>params: " + urlRequestParams.toString()); 
    	logger.info(Calendar.getInstance().getTime() + " CGI-start timestamp");
    	logger.info("URL: " + BASE_URL);
    	logger.info("URL PARAMS: " + urlRequestParams);
    	String jsonPayload = this.urlCgiProxyService.getJsonContent(BASE_URL, urlRequestParams.toString());
    	//Debug --> 
    	logger.debug(jsonDebugger.debugJsonPayloadWithLog4J(jsonPayload));
    	logger.info(Calendar.getInstance().getTime() +  " CGI-end timestamp");
    	if(jsonPayload!=null){
    		JsonMainOrderHeaderContainer container = this.ebookingMainOrderHeaderService.getContainer(jsonPayload);
    		model.put(EbookingConstants.DOMAIN_CONTAINER_OPEN_ORDERS, container);
    		if(container!=null){
    			if(container.getOneorder()!=null){
	    			for( JsonMainOrderHeaderRecord headerRecord: container.getOneorder()){
	    				record = headerRecord;
		    		}
    			}
    		}
    	}		
    	
		return record;
	}
	
	/**
	 * 
	 * @param model
	 * @param record
	 */
	private void setDomainObjectsInView(Map model, JsonMainOrderHeaderRecord record){
		model.put(EbookingConstants.DOMAIN_RECORD, record);
	}
	
	/**
	 * 
	 * @param appUser
	 * @param model
	 */
	private void setCodeDropDownMgr(SystemaWebUser appUser, Map model){
		this.codeDropDownMgr.populateCodesHtmlDropDownsFromJsonString(this.urlCgiProxyService, this.ebookingDropDownListPopulationService,
				 model,appUser,CodeDropDownMgr.CODE_2_COUNTRY, null, null);
		
	}


	/**
	 * 
	 * @param model
	 * @param selectedTypeWithCreateNew
	 * @return
	 */
	private JsonMainOrderTypesNewRecord getDefaultValuesForCreateNewOrder(Map model, String selectedTypeWithCreateNew){
		final String FIELD_SEPARATOR = "@";
		JsonMainOrderTypesNewRecord record = new JsonMainOrderTypesNewRecord();
		//this will be true ONLY when the record is new. Normal Updates of existent records will not be in this category...
		if(selectedTypeWithCreateNew!=null && !"".equals(selectedTypeWithCreateNew)){
			if(selectedTypeWithCreateNew.contains(FIELD_SEPARATOR)){
				String[] str = selectedTypeWithCreateNew.split(FIELD_SEPARATOR);
				if(str.length==6){
					record = new JsonMainOrderTypesNewRecord();
					record.setNewAvd(str[0]);
					record.setNewModul(str[1]);
					record.setNewModul2(str[2]);
					record.setNewLandKode(str[3]);
					record.setNewSideSK(str[4]);
					record.setNewText(str[5]);
					//save to future validation errors
					model.put("selectedType", selectedTypeWithCreateNew);
				}
				
			}
		}
		return record;
	}
	
	/**
	 * 
	 * @param model
	 * @param rpgReturnResponseHandler
	 * @param record
	 */
	private void setFatalError(Map model, RpgReturnResponseHandler rpgReturnResponseHandler, JsonMainOrderHeaderRecord record){
		logger.info(rpgReturnResponseHandler.getErrorMessage());
		this.setAspectsInView(model, rpgReturnResponseHandler);
		//No refresh on jsonRecord is done for the GUI (form fields). Must be implemented right here, if required. !!
        this.setDomainObjectsInView(model, record);
	}
	/**
	 * 
	 * @param model
	 * @param rpgReturnResponseHandler
	 */
	private void setAspectsInView (Map model, RpgReturnResponseHandler rpgReturnResponseHandler){
		model.put(EbookingConstants.ASPECT_ERROR_MESSAGE, rpgReturnResponseHandler.getErrorMessage());
		//extra error information
		StringBuffer errorMetaInformation = new StringBuffer();
		errorMetaInformation.append(rpgReturnResponseHandler.getUser());
		errorMetaInformation.append(rpgReturnResponseHandler.getHereff());
		model.put(EbookingConstants.ASPECT_ERROR_META_INFO, errorMetaInformation);
	}
	
	
	//SERVICES
	@Qualifier ("urlCgiProxyService")
	private UrlCgiProxyService urlCgiProxyService;
	@Autowired
	@Required
	public void setUrlCgiProxyService (UrlCgiProxyService value){ this.urlCgiProxyService = value; }
	public UrlCgiProxyService getUrlCgiProxyService(){ return this.urlCgiProxyService; }
	
	@Qualifier ("ebookingMainOrderHeaderService")
	private EbookingMainOrderHeaderService ebookingMainOrderHeaderService;
	@Autowired
	@Required
	public void setEbookingMainOrderHeaderService (EbookingMainOrderHeaderService value){ this.ebookingMainOrderHeaderService = value; }
	public EbookingMainOrderHeaderService getEbookingMainOrderHeaderService(){ return this.ebookingMainOrderHeaderService; }
	
	
	@Qualifier ("ebookingDropDownListPopulationService")
	private EbookingDropDownListPopulationService ebookingDropDownListPopulationService;
	@Autowired
	@Required
	public void setEbookingDropDownListPopulationService (EbookingDropDownListPopulationService value){ this.ebookingDropDownListPopulationService = value; }
	public EbookingDropDownListPopulationService getEbookingDropDownListPopulationService(){ return this.ebookingDropDownListPopulationService; }
	
	
	@Qualifier ("notisblockService")
	private NotisblockService notisblockService;
	@Autowired
	public void setNotisblockService (NotisblockService value){ this.notisblockService=value; }
	public NotisblockService getNotisblockService(){return this.notisblockService;}
	
	
}

