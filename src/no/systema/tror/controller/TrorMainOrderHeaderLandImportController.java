package no.systema.tror.controller;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.lang.reflect.Field;
import java.util.*;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

import org.apache.commons.lang3.builder.ReflectionToStringBuilder;
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
import no.systema.tror.model.jsonjackson.logging.JsonTrorOrderHeaderTrackAndTraceLoggingContainer;
import no.systema.tror.model.jsonjackson.logging.JsonTrorOrderHeaderTrackAndTraceLoggingRecord;
import no.systema.transportdisp.model.jsonjackson.workflow.order.JsonTransportDispWorkflowSpecificOrderArchivedDocsRecord;
import no.systema.transportdisp.url.store.TransportDispUrlDataStore;
import no.systema.main.util.AppConstants;
import no.systema.main.util.JsonDebugger;
import no.systema.main.util.MessageNoteManager;
import no.systema.main.util.NumberFormatterLocaleAware;
import no.systema.main.util.DateTimeManager;
import no.systema.main.util.StringManager;
import no.systema.main.model.SystemaWebUser;



//eBooking
import no.systema.tror.url.store.TrorUrlDataStore;
import no.systema.tror.util.TrorConstants;
import no.systema.tror.util.RpgReturnResponseHandler;
import no.systema.tror.util.manager.CodeDropDownMgr;
import no.systema.tror.util.manager.LandImportExportManager;
import no.systema.tror.util.manager.OrderContactInformationManager;

import no.systema.tror.model.jsonjackson.JsonTrorOrderHeaderContainer;
import no.systema.tror.model.jsonjackson.JsonTrorOrderHeaderRecordStatus;
import no.systema.tror.model.jsonjackson.JsonTrorOrderHeaderDummyContainer;
import no.systema.transportdisp.model.workflow.order.OrderContactInformationObject;

//import no.systema.ebooking.model.jsonjackson.JsonMainOrderHeaderFraktbrevRecord;
//import no.systema.ebooking.model.jsonjackson.JsonMainOrderHeaderMessageNoteContainer;
//import no.systema.ebooking.model.jsonjackson.JsonMainOrderHeaderMessageNoteRecord;

import no.systema.tror.model.jsonjackson.JsonTrorOrderHeaderRecord;
import no.systema.ebooking.model.jsonjackson.JsonMainOrderTypesNewRecord;
import no.systema.jservices.common.dao.DokufDao;
import no.systema.jservices.common.dao.DokufeDao;
import no.systema.jservices.common.dao.TrackfDao;
import no.systema.jservices.common.json.JsonDtoContainer;
import no.systema.jservices.common.json.JsonReader;
import no.systema.jservices.common.util.StringUtils;
import no.systema.tror.service.html.dropdown.TrorDropDownListPopulationService;
import no.systema.tror.service.landimport.TrorMainOrderHeaderLandimportService;
import no.systema.tror.service.TrorMainOrderHeaderService;
import no.systema.tror.mapper.url.request.UrlRequestParameterMapper;
import no.systema.tror.validator.TrorOrderHeaderValidator;
import no.systema.tvinn.sad.z.maintenance.nctsexport.service.MaintNctsExportTrkodfService;
import no.systema.tvinn.sad.z.maintenance.sadimport.service.gyldigekoder.MaintSadImportKodts4Service;
import no.systema.z.main.maintenance.service.MaintMainKodtaService;


/**
 * Tror - Order Header Controller 
 * 
 * @author oscardelatorre
 * @date Jul 04, 2017
 * 
 */

@Controller
@SessionAttributes(AppConstants.SYSTEMA_WEB_USER_KEY)
@Scope("session")
public class TrorMainOrderHeaderLandImportController {
	private static final JsonDebugger jsonDebugger = new JsonDebugger(1500);
	private static Logger logger = Logger.getLogger(TrorMainOrderHeaderLandImportController.class.getName());
	private ModelAndView loginView = new ModelAndView("login");
	private ApplicationContext context;
	private LoginValidator loginValidator = new LoginValidator();
	//
	private LandImportExportManager landImportMgr = new LandImportExportManager();
	private CodeDropDownMgr codeDropDownMgr = new CodeDropDownMgr();
	private UrlRequestParameterMapper urlRequestParameterMapper = new UrlRequestParameterMapper();
	private RpgReturnResponseHandler rpgReturnResponseHandler = new RpgReturnResponseHandler();
	private MessageNoteManager messageNoteMgr = new MessageNoteManager();
	private NumberFormatterLocaleAware numberFormatter = new NumberFormatterLocaleAware();
	private StringManager strMgr = new StringManager();
	private DateTimeManager dateMgr = new DateTimeManager();
	private OrderContactInformationManager orderContactInformationMgr = null;
	
	//private ReflectionUrlStoreMgr reflectionUrlStoreMgr = new ReflectionUrlStoreMgr();
	private final String DELSYSTEM_LAND_IMPORT = "A";
	String TRACK_TRACE_ACTION_UPDATE = "doUpdate";
	String TRACK_TRACE_CREATE = null;
	private final String TRACK_TRACE_STATUS_STR = "STR"; //STARTED
	private final String TRACK_TRACE_STATUS_CHG = "CHG"; //CHANGED
	//
	private final String PARTY_CONSIGNOR_CN = "CN";
	private final String PARTY_CONSIGNEE_CZ = "CZ";
	
	
	@PostConstruct
	public void initIt() throws Exception {
		if("DEBUG".equals(AppConstants.LOG4J_LOGGER_LEVEL)){
			logger.setLevel(Level.DEBUG);
		}
		//init managers
		orderContactInformationMgr = new OrderContactInformationManager(this.urlCgiProxyService);
	}
	
	/**
	 * 
	 * @param session
	 * @param request
	 * @return
	 */
	@RequestMapping(value="tror_mainorderlandimport.do",  params="action=doInit", method={RequestMethod.GET, RequestMethod.POST })
	public ModelAndView doInit(@ModelAttribute ("record") JsonTrorOrderHeaderRecord recordToValidate, BindingResult bindingResult, HttpSession session, HttpServletRequest request){
		
		Map model = new HashMap();
		SystemaWebUser appUser = (SystemaWebUser)session.getAttribute(AppConstants.SYSTEMA_WEB_USER_KEY);
		//String messageFromContext = this.context.getMessage("user.label",new Object[0], request.getLocale());
		ModelAndView successView = new ModelAndView("tror_mainorderlandimport");
		logger.info("Method: doInit");
		//check user (should be in session already)
		if(appUser==null){
			return loginView;
		}else{
			
			recordToValidate = this.getDefaultValuesFromDbDummy(recordToValidate, appUser);
			this.adjustDefaultValues(recordToValidate, appUser);
			model.put(TrorConstants.DOMAIN_RECORD, recordToValidate);
			this.setCodeDropDownMgr(appUser, model);
			
		}
		successView.addObject(TrorConstants.DOMAIN_MODEL , model);	
		return successView;
		
	}
		/**
	 * 
	 * @param session
	 * @param request
	 * @return
	 */
	@RequestMapping(value="tror_mainorderlandimport.do", method={RequestMethod.GET, RequestMethod.POST} )
	public ModelAndView doMainOrderEdit(@ModelAttribute ("record") JsonTrorOrderHeaderRecord recordToValidate, BindingResult bindingResult, HttpSession session, HttpServletRequest request){
		this.context = TdsAppContext.getApplicationContext();
		Map model = new HashMap();
		
		String action = request.getParameter("action");
		boolean isValidRecord = true;
		
		String orderStatus = recordToValidate.getHest(); //Since this is not comming from the back-end
		
		//logger.info("ORDER TOTALS STRING:" +  orderLineTotalsString);
		//special case on Create New comming from the order list "Create new order"
		String selectedTypeWithCreateNew = request.getParameter("selectedType");
		
		ModelAndView successView = new ModelAndView("tror_mainorderlandimport");
		SystemaWebUser appUser = this.loginValidator.getValidUser(session);
		
		//check user (should be in session already)
		if(appUser==null){
			return loginView;
			
		}else{
			logger.info(Calendar.getInstance().getTime() + " CONTROLLER start - timestamp");
			
			if(TrorConstants.ACTION_UPDATE.equals(action)){
				//Validation here TODO ... 
				//don't forget model.put("selectedTypeWithCreateNew", selectedTypeWithCreateNew) --> if selectedTypeWithCreateNew!=null...
				//...
				TrorOrderHeaderValidator validator = new TrorOrderHeaderValidator();
				logger.info("Host via HttpServletRequest.getHeader('Host'): " + request.getHeader("Host"));
				//validate
			    validator.validate(recordToValidate, bindingResult);
			    if(bindingResult.hasErrors()){
		    		logger.info("[ERROR Validation] record does not validate)");
		    		isValidRecord = false;
		    		//set always status as in list (since we do not get this value from back-end)
					recordToValidate.setHest(orderStatus);
		    		model.put(TrorConstants.DOMAIN_RECORD, recordToValidate);
			    }else{
			    	//adjust some db-fields
			    	this.adjustFields(recordToValidate);
			    	
					//Start DML operations if applicable
					StringBuffer errMsg = new StringBuffer();
					int dmlRetval = 0;
					if(recordToValidate.getHeopd()!=null && !"".equals(recordToValidate.getHeopd())){
						//update
						logger.info("doUpdate");
						//update with integrated back-end validity (in case of user parameterized )
						dmlRetval = this.updateRecord(model, appUser.getUser(), recordToValidate, TrorConstants.MODE_UPDATE, errMsg);
						
						if(dmlRetval==0){
							logger.info("[INFO] Record successfully updated, OK ");
							TrackfDao trackfDao = this.prepareTrackfDao(recordToValidate, this.TRACK_TRACE_STATUS_CHG);
							this.logTrackAndTraceGeneral(appUser, trackfDao, this.TRACK_TRACE_ACTION_UPDATE, this.TRACK_TRACE_CREATE);
							//update dokufe - contact information
							OrderContactInformationObject orderContactInfoObj = this.setOrderContactInformationObject(recordToValidate);
							this.orderContactInformationMgr.updateContactInformation(appUser, orderContactInfoObj, this.setDokufeDao(orderContactInfoObj));
						}
					}else{
						//create new
						logger.info("doCreate");
						dmlRetval = this.updateRecord(model, appUser.getUser(), recordToValidate, TrorConstants.MODE_ADD, errMsg);
						model.put("selectType", "");
						if(dmlRetval==0){
							//TODO orderStatus = "E"; //since we do not get the value from back-end
							logger.info("[INFO] Record successfully created, OK ");
							TrackfDao trackfDao = this.prepareTrackfDao(recordToValidate, this.TRACK_TRACE_STATUS_STR);
							this.logTrackAndTraceGeneral(appUser, trackfDao, this.TRACK_TRACE_ACTION_UPDATE, this.TRACK_TRACE_CREATE);
							//update dokufe - contact information
							OrderContactInformationObject orderContactInfoObj = this.setOrderContactInformationObject(recordToValidate);
							this.orderContactInformationMgr.updateContactInformation(appUser, orderContactInfoObj, this.setDokufeDao(orderContactInfoObj)); 
							
						}
					}
					if(dmlRetval<0){
						isValidRecord = false;
						model.put(TrorConstants.DOMAIN_RECORD, recordToValidate);
					}
			    }
				
			}else if(TrorConstants.ACTION_DELETE.equals(action)){
				
			}
			
			//--------------
			//Fetch record
			//--------------
			if(strMgr.isNotNull(recordToValidate.getHeopd()) && strMgr.isNotNull(recordToValidate.getHeavd()) ){
				if(isValidRecord){
					logger.info("HEOPD:" + recordToValidate.getHeopd());
					JsonTrorOrderHeaderRecord headerOrderRecord = this.getOrderRecord(appUser, model, recordToValidate.getHeavd(), recordToValidate.getHeopd());
					//adjust some fields for presentation purposes
					this.setSpecialValuesForPresentation(appUser, headerOrderRecord, model);
					//split godsnr
					this.splitGodsnr(headerOrderRecord);
					//populate track and trace
					this.populateTrackAndTrace(appUser, headerOrderRecord);
					//populate kontaktuppgifter
					OrderContactInformationObject orderContactInformationObject = this.setOrderContactInformationObject(headerOrderRecord);
					this.orderContactInformationMgr.getContactInformation(appUser, this.setDokufeDao(orderContactInformationObject), this.PARTY_CONSIGNOR_CN, true, orderContactInformationObject);
					this.orderContactInformationMgr.getContactInformation(appUser, this.setDokufeDao(orderContactInformationObject), this.PARTY_CONSIGNEE_CZ, true, orderContactInformationObject);
					this.setHeaderRecordContactInformation(headerOrderRecord, orderContactInformationObject);				
					
					
					//set always status as in list (since we do not get this value from back-end)
					//TODO headerOrderRecord.setStatus(orderStatus);
					//domain objects
					model.put(TrorConstants.DOMAIN_RECORD, headerOrderRecord);
					session.setAttribute(TrorConstants.SESSION_RECORD_ORDER_TROR_LAND, headerOrderRecord);
					session.setAttribute(TrorConstants.SESSION_SUBSYSTEM_ORDER_TROR, TrorConstants.SESSION_SUBSYSTEM_ORDER_TROR_LANDIMPORT);
				}else{
					//adjust for presentation
					this.setSpecialValuesForPresentation(appUser, recordToValidate, model);
					//adjust some db-fields
			    	this.adjustFields(recordToValidate);
		    		//split godsnr
					this.splitGodsnr(recordToValidate);
					//populate track and trace
					this.populateTrackAndTrace(appUser, recordToValidate);
					//put record in model
					model.put(TrorConstants.DOMAIN_RECORD, recordToValidate);
					
				}
			}
			this.setCodeDropDownMgr(appUser, model);
			
			//TODO this.setDropDownsFromFiles(model);
			//populate model
			if(action==null || "".equals(action)){
				action = "doUpdate";
			}
			model.put("action", action);
			
			//get dropdowns
			Collection<JsonTransportDispWorkflowSpecificOrderArchivedDocsRecord> list = (ArrayList)model.get("archivedDocList");
			 if(list!=null && list.size()>0){
				 //logger.info("WOW2!!!");
			 }else{
				 //logger.info("WHAT???");
			 }
			successView.addObject(TrorConstants.DOMAIN_MODEL , model);
			
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
	@RequestMapping(value="tror_mainorderlandimport_copy.do", method={RequestMethod.GET, RequestMethod.POST} )
	public ModelAndView doMainOrderCopy(@ModelAttribute ("record") JsonTrorOrderHeaderRecord recordToValidate, BindingResult bindingResult, HttpSession session, HttpServletRequest request){
		this.context = TdsAppContext.getApplicationContext();
		Map model = new HashMap();
		
		
		ModelAndView successView = new ModelAndView("tror_mainorderlandimport");
		SystemaWebUser appUser = this.loginValidator.getValidUser(session);
		
		//check user (should be in session already)
		if(appUser==null){
			return loginView;
			
		}else{
			logger.info(Calendar.getInstance().getTime() + " CONTROLLER start - timestamp");
			
			//Fetch the source Oppd
			logger.info("HEOPD:" + recordToValidate.getHeopd());
			JsonTrorOrderHeaderRecord headerOrderRecord = this.getOrderRecord(appUser, model, recordToValidate.getHeavd(), recordToValidate.getHeopd());
			//adjust some fields for presentation purposes
			this.setSpecialValuesForPresentation(appUser, headerOrderRecord, model);
			//split godsnr
			this.splitGodsnr(headerOrderRecord);
			//populate track and trace
			//TODO ? this.populateTrackAndTrace(appUser, headerOrderRecord);
			//populate kontaktuppgifter
			/*OrderContactInformationObject orderContactInformationObject = this.setOrderContactInformationObject(headerOrderRecord);
			this.orderContactInformationMgr.getContactInformation(appUser, this.setDokufeDao(orderContactInformationObject), this.PARTY_CONSIGNOR_CN, true, orderContactInformationObject);
			this.orderContactInformationMgr.getContactInformation(appUser, this.setDokufeDao(orderContactInformationObject), this.PARTY_CONSIGNEE_CZ, true, orderContactInformationObject);
			this.setHeaderRecordContactInformation(headerOrderRecord, orderContactInformationObject);				
			*/
			//Clean up
			this.cleanKeyFieldsWhenCopy(headerOrderRecord);
			//
			this.setCodeDropDownMgr(appUser, model);
			model.put("action", "doUpdate");
			//put record in model
			model.put(TrorConstants.DOMAIN_RECORD, headerOrderRecord);
			successView.addObject(TrorConstants.DOMAIN_MODEL , model);
			logger.info("Host via HttpServletRequest.getHeader('Host'): " + request.getHeader("Host"));
			
			return successView;

			
		}
		
	}
	/**
	 * populate record from neutra object
	 * 
	 * @param headerOrderRecord
	 * @param orderContactInformationObject
	 */
	private void setHeaderRecordContactInformation(JsonTrorOrderHeaderRecord headerOrderRecord, OrderContactInformationObject orderContactInformationObject){
		
		headerOrderRecord.setOwnSenderContactName(orderContactInformationObject.getOwnSenderContactName());
		headerOrderRecord.setOwnSenderMobile(orderContactInformationObject.getOwnSenderMobile());
		headerOrderRecord.setOwnSenderEmail(orderContactInformationObject.getOwnSenderEmail());
		//
		headerOrderRecord.setOwnReceiverContactName(orderContactInformationObject.getOwnReceiverContactName());
		headerOrderRecord.setOwnReceiverMobile(orderContactInformationObject.getOwnReceiverMobile());
		headerOrderRecord.setOwnReceiverEmail(orderContactInformationObject.getOwnReceiverEmail());
		
	}
	/**
	 * 
	 * @param headerOrderRecord
	 */
	private void cleanKeyFieldsWhenCopy(JsonTrorOrderHeaderRecord headerOrderRecord){
		//opd
		headerOrderRecord.setHeopd(null);
		//
		int today = Integer.parseInt(dateMgr.getCurrentDate_ISO("yyyyMMdd"));
		headerOrderRecord.setHedtop(String.valueOf(today));
		//
		headerOrderRecord.setHedtr(null);
		headerOrderRecord.setHentf(null);
		headerOrderRecord.setHekdtm(null);
		headerOrderRecord.setHetle(null);
		headerOrderRecord.setHetll(null);
		headerOrderRecord.setHegnn(null);
		//
		headerOrderRecord.setHepk1(null);
		headerOrderRecord.setHepk2(null);
		headerOrderRecord.setHepk3(null);
		headerOrderRecord.setHepk4(null);
		headerOrderRecord.setHepk5(null);
		headerOrderRecord.setHepk6(null);
		headerOrderRecord.setHepk7(null);
		headerOrderRecord.setHepk8(null);
		headerOrderRecord.setHepk9(null);
		//
		headerOrderRecord.setTravd0(null);
		headerOrderRecord.setTropd0(null);
		headerOrderRecord.setTravd1(null);
		headerOrderRecord.setTropd1(null);
		headerOrderRecord.setTravd2(null);
		headerOrderRecord.setTropd2(null);
		
		
	}
	/**
	 * 
	 * @param headerOrderRecord
	 * @return
	 */
	private DokufeDao setDokufeDao (OrderContactInformationObject orderContactInfoObj){
		DokufeDao dao = new DokufeDao();
		dao.setFe_dfavd(Integer.valueOf(orderContactInfoObj.getAvd()));
		dao.setFe_dfopd(Integer.valueOf(orderContactInfoObj.getOpd()));
		dao.setFe_dffbnr(Integer.valueOf(orderContactInfoObj.getOwnPartyFbnr()));
		return dao;
	}
	/**
	 * 
	 * @param headerOrderRecord
	 * @return
	 */
	private OrderContactInformationObject setOrderContactInformationObject (JsonTrorOrderHeaderRecord headerOrderRecord){
		OrderContactInformationObject obj = new OrderContactInformationObject();
		obj.setAvd(headerOrderRecord.getHeavd());
		obj.setOpd(headerOrderRecord.getHeopd());
		
		//
		obj.setOwnSenderContactName(headerOrderRecord.getOwnSenderContactName());
		obj.setOwnSenderMobile(headerOrderRecord.getOwnSenderMobile());
		obj.setOwnSenderEmail(headerOrderRecord.getOwnSenderEmail());
		//
		obj.setOwnReceiverContactName(headerOrderRecord.getOwnReceiverContactName());
		obj.setOwnReceiverMobile(headerOrderRecord.getOwnReceiverMobile());
		obj.setOwnReceiverEmail(headerOrderRecord.getOwnReceiverEmail());
		
		return obj;
	}
	
	
	
	/**
	 * 
	 * @param appUser
	 * @param recordToLog
	 * @param action
	 * @param updateId
	 * @return
	 */
	private int logTrackAndTraceGeneral(SystemaWebUser appUser, TrackfDao recordToLog, String action, String updateId){
		int retval = 0;
		
		//Params
		StringBuffer params = new StringBuffer();
		//params.append("&bnr=" + lineId);
		params.append("&ttavd=" + recordToLog.getTtavd());
		params.append("&ttopd=" + recordToLog.getTtopd());
		logger.info("ACTION: " + action);
		
		logger.info(Calendar.getInstance().getTime() + " CONTROLLER start - timestamp");
		StringBuffer errMsg = new StringBuffer();
		int dmlRetval = 0;
		
		if (TrorConstants.ACTION_UPDATE.equals(action)) {
			if (updateId != null && !"".equals(updateId)) {
				//logger.info("UPDATE!!!");
				//To implement? -->dmlRetval = this.landImportMgr.updateRecord(appUser, recordToLog, TrorConstants.MODE_UPDATE, errMsg, this.urlCgiProxyService, this.urlRequestParameterMapper);
			} else {
				//logger.info("CREATE NEW!!!"); Will always be the case... as for today (20171221)
				dmlRetval = this.landImportMgr.updateRecord(appUser, recordToLog, TrorConstants.MODE_ADD, errMsg, this.urlCgiProxyService, this.urlRequestParameterMapper);
			}
		} else if (TrorConstants.ACTION_DELETE.equals(action)) {
			//logger.info("DELETE !!!");
			//To implement? -->dmlRetval = this.landImportMgr.updateRecord(appUser, recordToLog, TrorConstants.MODE_DELETE, errMsg, this.urlCgiProxyService, this.urlRequestParameterMapper);
		}
		// check for Update errors
		if (dmlRetval < 0) {
			retval = TrorConstants.ERROR_CODE;
			logger.info("ERROR FATAL: Track and Trace log has not been updated. Check the method: logTrackAndTraceGeneral on ...Controller");
		}
		return retval;
	}
	
	/**
	 * 
	 * @param recordToValidate
	 * @param status  STR=STARTED, CHG=CHANGED although all records are new. Every record is a log in time and space (timestamp)
	 * @return
	 */
	private TrackfDao prepareTrackfDao(JsonTrorOrderHeaderRecord recordToValidate, String status){
		int today = Integer.parseInt(dateMgr.getCurrentDate_ISO("yyyyMMdd"));
		int now = Integer.parseInt(dateMgr.getCurrentDate_ISO("HHmmss"));
		
		TrackfDao dao = new TrackfDao();
		//keys
		dao.setTtuser(recordToValidate.getHesg());
		dao.setTtavd(Integer.parseInt(recordToValidate.getHeavd()));
		dao.setTtopd(Integer.parseInt(recordToValidate.getHeopd()));
		dao.setTtacti(status);
		//time stamps
		dao.setTtdate(today);
		dao.setTtdatl(today);
		dao.setTttime(now);
		dao.setTttiml(now);
		//
		if(this.TRACK_TRACE_STATUS_STR.equals(status)){
			dao.setTttext("Order entered");
			dao.setTttexl("Ordre registrert");
			
		}else if(this.TRACK_TRACE_STATUS_CHG.equals(status)){
			dao.setTttext("Order is changed");
			dao.setTttexl("Oppdrag er endret");
		}
		dao.setTtmanu("X");//meaning "not manual"
		
		return dao;
	}
	
	/**
	 * 
	 * @param appUser
	 * @param record
	 * @param model
	 */
	private void setSpecialValuesForPresentation(SystemaWebUser appUser, JsonTrorOrderHeaderRecord record, Map model ){
		String UNITOFMEASURE_1 = "uom1";
		String UNITOFMEASURE_1_LENGTH = "uom1Length";
		
		
		String UOM_SEPARATOR = " ";
		if(strMgr.isNotNull(record.getHevs1())){
			int index = record.getHevs1().indexOf(UOM_SEPARATOR);
			if(index>=0){
				String uom = record.getHevs1().substring(0,index);
				//Check if uom is valid
				if(this.landImportMgr.findUnitOfMeasure(this.urlCgiProxyService, this.maintNctsExportTrkodfService, appUser, uom)){
					model.put(UNITOFMEASURE_1, uom);
					model.put(UNITOFMEASURE_1_LENGTH, uom.length());
					logger.info("UOM!!!!!!!!!!!!!!!!:" + uom);
				}else{
					logger.info("INVALID uom!!!!!");
					model.put(UNITOFMEASURE_1, "");
					model.put(UNITOFMEASURE_1_LENGTH, 0);
				}
			}
		}
	}
	/**
	 * 
	 * @param recordToValidate
	 * @param appUser
	 */
	private void adjustDefaultValues(JsonTrorOrderHeaderRecord recordToValidate, SystemaWebUser appUser ){
		
		//signatur
		if(strMgr.isNull(recordToValidate.getHesg())){
			recordToValidate.setHesg(appUser.getSignatur());
		}else{
			if(!recordToValidate.getHesg().equals(appUser.getSignatur())) {
				recordToValidate.setHesg(appUser.getSignatur());
			}
		}
		
		//this values must be changed
		recordToValidate.setHeopd(null);
		recordToValidate.setHedtop(dateMgr.getCurrentDate_ISO());
		recordToValidate.setHedtr(dateMgr.getCurrentDate_ISO());
		recordToValidate.setHeur(this.DELSYSTEM_LAND_IMPORT);
		//split godsnr
		this.splitGodsnr(recordToValidate);
	}
	
	
	/**
	 * 
	 * @param appUser
	 * @param recordToValidate
	 * @return
	 */
	private JsonTrorOrderHeaderRecord getDefaultValuesFromDbDummy(JsonTrorOrderHeaderRecord recordToValidate, SystemaWebUser appUser ){
			
		JsonTrorOrderHeaderRecord record = new JsonTrorOrderHeaderRecord();
		
		final String BASE_URL = TrorUrlDataStore.TROR_BASE_FETCH_SPECIFIC_DEFAULT_BILIMPORT_VALUES_FROM_DBDUMMY_URL;
		//add URL-parameters
		StringBuffer urlRequestParams = new StringBuffer();
		urlRequestParams.append("user=" + appUser.getUser());
		urlRequestParams.append("&heavd=" + recordToValidate.getHeavd() );
			
		logger.info(Calendar.getInstance().getTime() + " CGI-start timestamp");
    	logger.info("URL: " + BASE_URL);
    	logger.info("URL PARAMS: " + urlRequestParams);
    	String jsonPayload = this.urlCgiProxyService.getJsonContent(BASE_URL, urlRequestParams.toString());
    	//Debug --> 
    	logger.debug(jsonDebugger.debugJsonPayloadWithLog4J(jsonPayload));
    	logger.info(Calendar.getInstance().getTime() +  " CGI-end timestamp");
    	if(jsonPayload!=null){
    		JsonTrorOrderHeaderDummyContainer container = this.trorMainOrderHeaderLandimportService.getOrderHeaderDummyContainer(jsonPayload);
    		//model.put(TrorConstants.DOMAIN_CONTAINER_OPEN_ORDERS, container);
    		if(container!=null){
    			if(container.getList()!=null){
	    			for( JsonTrorOrderHeaderRecord headerRecord: container.getList()){
	    				record = headerRecord;
	    				
		    		}
    			}
    		}
    	}		
	
		return record;
	}
	/**
	 * Special split on Goods no.
	 * 
	 * @param recordToValidate
	 */
	private void splitGodsnr(JsonTrorOrderHeaderRecord recordToValidate){
		String str = recordToValidate.getHegn();
		if(strMgr.isNotNull(str)){
			if(str.length()>=4){
				String ownHegn1 = str.substring(0, 4);
				//logger.info("A:"+ ownHegn1);
				recordToValidate.setOwnHegn1(ownHegn1);
				if(str.length()>=9){
					String ownHegn2 = str.substring(4,9);
					//logger.info("B:"+ ownHegn2);
					recordToValidate.setOwnHegn2(ownHegn2);
					if(str.length()>=10){
						if(str.length()>=15){
							String ownHegn3 = str.substring(9,15);
							//logger.info("C:"+ ownHegn3);
							recordToValidate.setOwnHegn3(ownHegn3);
						}else{
							String ownHegn3 = str.substring(9);
							//logger.info("D:"+ ownHegn3);
							recordToValidate.setOwnHegn3(ownHegn3);
						}
					}
				}else{
					String ownHegn2 = str.substring(4);
					//logger.info("Y:"+ ownHegn2);
					recordToValidate.setOwnHegn2(ownHegn2);
				}
			}else{
				String ownHegn1 = str;
				//logger.info("Z:"+ ownHegn1);
				recordToValidate.setOwnHegn1(ownHegn1);
			}
		}
		
	}

	/**
	 * 
	 * @param recordToValidate
	 */
	private void adjustFields(JsonTrorOrderHeaderRecord recordToValidate){
		//Vareslag must be concatenated since there is no Enhet-field in target db-table. The enhet is part of the Vareslag as the 2-first characters of hevs1, hevs2
		String SPACE = " ";
		if(strMgr.isNotNull(recordToValidate.getOwnEnhet1()) && strMgr.isNotNull(recordToValidate.getHevs1()) ){
			recordToValidate.setHevs1(recordToValidate.getOwnEnhet1() + SPACE + recordToValidate.getHevs1());
		}
		//OBSOLETE 
		/*
		if(strMgr.isNotNull(recordToValidate.getOwnEnhet2()) && strMgr.isNotNull(recordToValidate.getHevs2()) ){
			recordToValidate.setHevs2(recordToValidate.getOwnEnhet2() + SPACE + recordToValidate.getHevs2());
		}
		*/
		
		//Godsnr
		recordToValidate.setHegn(recordToValidate.getOwnHegn1() + recordToValidate.getOwnHegn2() + recordToValidate.getOwnHegn3());
		
		//Decimal numbers for db update
		recordToValidate.setHevalp(this.strMgr.adjustNullStringToDecimalForDbUpdate(recordToValidate.getHevalp()));
		recordToValidate.setHehbre(this.strMgr.adjustNullStringToDecimalForDbUpdate(recordToValidate.getHehbre()));
		recordToValidate.setHelbre(this.strMgr.adjustNullStringToDecimalForDbUpdate(recordToValidate.getHelbre()));
		recordToValidate.setHellen(this.strMgr.adjustNullStringToDecimalForDbUpdate(recordToValidate.getHellen()));
		recordToValidate.setHelm(this.strMgr.adjustNullStringToDecimalForDbUpdate(recordToValidate.getHelm()));
		recordToValidate.setHem3(this.strMgr.adjustNullStringToDecimalForDbUpdate(recordToValidate.getHem3()));
		recordToValidate.setTrverb(this.strMgr.adjustNullStringToDecimalForDbUpdate(recordToValidate.getTrverb()));
		recordToValidate.setTrettb(this.strMgr.adjustNullStringToDecimalForDbUpdate(recordToValidate.getTrettb()));
		recordToValidate.setTrfrab(this.strMgr.adjustNullStringToDecimalForDbUpdate(recordToValidate.getTrfrab()));
		
	
	}
	
	/**
	 * 
	 * @param appUser
	 * @param orderRecord
	 */
	private void populateTrackAndTrace(SystemaWebUser appUser, JsonTrorOrderHeaderRecord headerOrderRecord){		
		//===========
		 //FETCH LIST
		 //===========
		 logger.info("Inside: populateTrackAndTrace");
		 //prepare the access CGI with RPG back-end
		 String BASE_URL = TransportDispUrlDataStore.TRANSPORT_DISP_GENERAL_TRACK_AND_TRACE_URL;
		 String urlRequestParamsKeys = "user=" + appUser.getUser() + "&avd=" + headerOrderRecord.getHeavd() + "&opd=" + headerOrderRecord.getHeopd();
		 logger.info("URL: " + BASE_URL);
		 logger.info("PARAMS: " + urlRequestParamsKeys);
		 logger.info(Calendar.getInstance().getTime() +  " CGI-start timestamp");
		 String jsonPayload = this.urlCgiProxyService.getJsonContent(BASE_URL, urlRequestParamsKeys);
		 logger.info(Calendar.getInstance().getTime() +  " CGI-end timestamp");
		 logger.debug(jsonDebugger.debugJsonPayloadWithLog4J(jsonPayload));
		 
		 Collection<JsonTrorOrderHeaderTrackAndTraceLoggingRecord> list = new ArrayList<JsonTrorOrderHeaderTrackAndTraceLoggingRecord>();
		 if(jsonPayload!=null){
		 	try{
		 		JsonTrorOrderHeaderTrackAndTraceLoggingContainer container = this.trorMainOrderHeaderService.getTrackAndTraceLoggingContainer(jsonPayload);
				if(container!=null){
					list = container.getTrackTraceEvents();
					for(JsonTrorOrderHeaderTrackAndTraceLoggingRecord record : list){
						//DEBUG -->logger.info("####Link:" + record.getDoclnk());
					}
				}
				
		 	}catch(Exception e){
		 		e.printStackTrace();
		 	}
		 }
		//populate the list on parent record
		 headerOrderRecord.setTrackAndTraceloggingRecord(list);
		 
	}
	
	
	
	
	
	/**
	 * 
	 * @param recordToValidate
	 * @param bindingResult
	 * @param session
	 * @param request
	 * @return
	 */
	@RequestMapping(value="tror_mainorderlandimport_updateStatus.do", method={RequestMethod.GET, RequestMethod.POST })
	public ModelAndView doUpdateStatus(@ModelAttribute ("record") JsonTrorOrderHeaderRecord recordToValidate, BindingResult bindingResult, HttpSession session, HttpServletRequest request){
		
		Map model = new HashMap();
		SystemaWebUser appUser = (SystemaWebUser)session.getAttribute(AppConstants.SYSTEMA_WEB_USER_KEY);
		ModelAndView successView = null;
		
		String opd = request.getParameter("currentOpd");
		String avd = request.getParameter("currentAvd");
		String newStatus = request.getParameter("selectedStatus");
		//---------------------------------------------------------------------------
		//This request mapping is called from the order header OR from the order list
		//---------------------------------------------------------------------------
		if(strMgr.isNotNull(opd) && strMgr.isNotNull(avd)){
			//meaning the call was from order header
			successView = new ModelAndView("redirect:tror_mainorderlandimport.do?action=doFetch&heavd=" + avd + "&heopd=" + opd);
		}else{
			//meaning the call was from the order list (main entry point of Oppdragsregistrering)
			successView = new ModelAndView("redirect:tror_mainorderlist.do?lang=" + appUser.getUsrLang() + "&action=doFind");
			Enumeration requestParameters = request.getParameterNames();
		    while (requestParameters.hasMoreElements()) {
		        String element = (String) requestParameters.nextElement();
		        String value = request.getParameter(element);
		        if (element != null && value != null) {
	        		if(element.startsWith("currentAvd")){
        				avd = value;
        			}else if(element.startsWith("currentOpd")){
        				opd = value;
        			}else if(element.startsWith("selectedStatus")){
        				newStatus = value;
        			}
        		}
	    	}
		}
		logger.info("Method: doUpdateStatus");
		//check user (should be in session already)
		if(appUser==null){
			return loginView;
		}else{
			String BASE_URL = TrorUrlDataStore.TROR_BASE_UPDATE_SPECIFIC_ORDER_STATUS_URL;
			String urlRequestParamsKeys = "user=" + appUser.getUser() + "&mode=U" + "&heavd=" + avd + "&heopd=" + opd + "&hest=" + newStatus;
			
			logger.info(Calendar.getInstance().getTime() + " CGI-start timestamp");
	    	logger.info("URL: " + jsonDebugger.getBASE_URL_NoHostName(BASE_URL));
	    	logger.info("URL PARAMS:" + urlRequestParamsKeys);
	    	
	    	
	    	String jsonPayload = this.urlCgiProxyService.getJsonContent(BASE_URL, urlRequestParamsKeys);
	    	//Debug --> 
	    	logger.debug(jsonDebugger.debugJsonPayloadWithLog4J(jsonPayload));
	    	logger.info(Calendar.getInstance().getTime() +  " CGI-end timestamp");
	    	
	    	if(jsonPayload!=null){
	    		
	    		JsonTrorOrderHeaderContainer container = this.trorMainOrderHeaderLandimportService.getOrderHeaderContainerStatusUpdate(jsonPayload);
	    		if(container!=null){
	    			for(JsonTrorOrderHeaderRecordStatus record : container.getList()){
	    				logger.info("Status:" + record.getStatus());
	    				if("ok".equals(record.getStatus())){
	    					//Update successfully done!
	    		    		logger.info("[INFO] Record successfully updated, OK ");
	    				}else{
	    					logger.info("[ERROR] error at update!! Check it ... ");
	    				}
	    			}
	    		}
	    	}
		}
		return successView;
	}
	
	
	/**
	 * help function
	 * @param value
	 * @return
	 */
	private String updateToDecimal(String value){
		String retval = "0";
		if(strMgr.isNotNull(value)){
			retval = value.replace("." , ",");
		}
		return retval;
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
	private int updateRecord(Map model, String applicationUser, JsonTrorOrderHeaderRecord recordToValidate, String mode, StringBuffer errMsg){
		int retval = 0;
		
		final String BASE_URL = TrorUrlDataStore.TROR_BASE_UPDATE_SPECIFIC_ORDER_URL;
		String urlRequestParamsKeys = "user=" + applicationUser + "&mode=" + mode;
		String urlRequestParams = this.urlRequestParameterMapper.getUrlParameterValidString((recordToValidate));
		//put the final valid param. string
		urlRequestParams = urlRequestParamsKeys + urlRequestParams;
		
		logger.info(Calendar.getInstance().getTime() + " CGI-start timestamp");
    	logger.info("URL: " + jsonDebugger.getBASE_URL_NoHostName(BASE_URL));
    	logger.info("URL PARAMS:" + urlRequestParams);
    	
    	
    	String jsonPayload = this.urlCgiProxyService.getJsonContent(BASE_URL, urlRequestParams.toString());
    	//Debug --> 
    	logger.debug(jsonDebugger.debugJsonPayloadWithLog4J(jsonPayload));
    	logger.info(Calendar.getInstance().getTime() +  " CGI-end timestamp");
    	
    	if(jsonPayload!=null){
    		JsonTrorOrderHeaderContainer container = this.trorMainOrderHeaderLandimportService.getOrderHeaderContainer(jsonPayload);
    		if(container!=null){
    			if(this.strMgr.isNotNull(container.getErrMsg()) && !"null".equals(container.getErrMsg()) ) {
    				rpgReturnResponseHandler = new RpgReturnResponseHandler(); //init
    				rpgReturnResponseHandler.setErrorMessage("[ERROR] FATAL on UPDATE: " +  container.getErrMsg());
    				this.setFatalError(model, rpgReturnResponseHandler, recordToValidate);
    				retval = -1; 
    				
    			}else{
    				for(JsonTrorOrderHeaderRecord record: container.getDtoList()){
    					//new opd is born
    					recordToValidate.setHeopd(record.getHeopd());
    				}
    				//Update successfully done!
		    		logger.info("[INFO] Record successfully updated, OK ");
    			}
    		}
    	}
    	return retval;
	}
	
	/**
	 * 
	 * @param appUser
	 * @param model
	 * @param orderTypes
	 * @param heavd
	 * @param heopd
	 * @return
	 */
	private JsonTrorOrderHeaderRecord getOrderRecord(SystemaWebUser appUser, Map model, String heavd, String heopd ){
		JsonTrorOrderHeaderRecord record = new JsonTrorOrderHeaderRecord();
			
		final String BASE_URL = TrorUrlDataStore.TROR_BASE_FETCH_SPECIFIC_ORDER_URL;
		//add URL-parameters
		StringBuffer urlRequestParams = new StringBuffer();
		urlRequestParams.append("user=" + appUser.getUser());
		if( strMgr.isNotNull(heavd) && strMgr.isNotNull(heopd) ){
			//Meaning fetching to an update
			urlRequestParams.append("&heavd=" + heavd + "&heopd=" + heopd );
		}else{
			//Meaning preparing a create new ...
			urlRequestParams.append("&heavd=&heopd=");
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
    		JsonTrorOrderHeaderContainer container = this.trorMainOrderHeaderLandimportService.getOrderHeaderContainer(jsonPayload);
    		model.put(TrorConstants.DOMAIN_CONTAINER_OPEN_ORDERS, container);
    		if(container!=null){
    			if(container.getDtoList()!=null){
	    			for( JsonTrorOrderHeaderRecord headerRecord: container.getDtoList()){
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
	private void setDomainObjectsInView(Map model, JsonTrorOrderHeaderRecord record){
		model.put(TrorConstants.DOMAIN_RECORD, record);
	}
	
	/**
	 * 
	 * @param appUser
	 * @param model
	 */
	private void setCodeDropDownMgr(SystemaWebUser appUser, Map model){
		//Sign / AVD
		this.codeDropDownMgr.populateCodesHtmlDropDownsFromJsonSignature(this.urlCgiProxyService, this.trorDropDownListPopulationService, model, appUser);
		this.codeDropDownMgr.populateCodesHtmlDropDownsFromJsonAvdelning(this.urlCgiProxyService, this.maintMainKodtaService, model, appUser);
		//general
		this.codeDropDownMgr.populateCodesHtmlDropDownsFromJsonString(urlCgiProxyService, this.trorDropDownListPopulationService, model, appUser, this.codeDropDownMgr.CODE_TYPE_DELSYSTEM);
		this.codeDropDownMgr.populateCodesHtmlDropDownsFromJsonCountry(this.urlCgiProxyService, this.trorDropDownListPopulationService, model, appUser);
		this.codeDropDownMgr.populateCodesHtmlDropDownsFromJsonIncoterms(this.urlCgiProxyService, this.trorDropDownListPopulationService, model, appUser);
		this.codeDropDownMgr.populateCodesHtmlDropDownsFromJsonOppdragsType(this.urlCgiProxyService, this.trorDropDownListPopulationService, model, appUser);
		this.codeDropDownMgr.populateCodesHtmlDropDownsFromJsonProduct(this.urlCgiProxyService, this.trorDropDownListPopulationService, model, appUser);
		this.codeDropDownMgr.populateCodesHtmlDropDownsFromJsonEnhet(this.urlCgiProxyService, this.trorDropDownListPopulationService, model, appUser);
		this.codeDropDownMgr.populateCodesHtmlDropDownsFromJsonTransporttype(this.urlCgiProxyService, this.maintSadImportKodts4Service, model, appUser);
		this.codeDropDownMgr.populateCodesHtmlDropDownsFromJsonCurrency(this.urlCgiProxyService, this.trorDropDownListPopulationService, model, appUser);
		
	}
	
	private void setDropDownsFromFiles(Map<String, Object> model){
		/*
		model.put(TrorConstants.RESOURCE_MODEL_KEY_CURRENCY_CODE_LIST, this.ebookingDropDownListPopulationService.getCurrencyList());
		*/
	}


	/**
	 * 
	 * @param model
	 * @param selectedTypeWithCreateNew
	 * @return
	 */
	private JsonMainOrderTypesNewRecord getDefaultValuesForCreateNewOrder(Map model, String selectedTypeWithCreateNew){
		/*
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
		*/
		return null;
	}
	
	/**
	 * 
	 * @param model
	 * @param rpgReturnResponseHandler
	 * @param record
	 */
	private void setFatalError(Map model, RpgReturnResponseHandler rpgReturnResponseHandler, JsonTrorOrderHeaderRecord record){
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
		model.put(TrorConstants.ASPECT_ERROR_MESSAGE, rpgReturnResponseHandler.getErrorMessage());
		//extra error information
		StringBuffer errorMetaInformation = new StringBuffer();
		errorMetaInformation.append(rpgReturnResponseHandler.getUser());
		errorMetaInformation.append(rpgReturnResponseHandler.getHereff());
		model.put(TrorConstants.ASPECT_ERROR_META_INFO, errorMetaInformation);
	}
	
	
	//SERVICES
	@Qualifier ("urlCgiProxyService")
	private UrlCgiProxyService urlCgiProxyService;
	@Autowired
	@Required
	public void setUrlCgiProxyService (UrlCgiProxyService value){ this.urlCgiProxyService = value; }
	public UrlCgiProxyService getUrlCgiProxyService(){ return this.urlCgiProxyService; }
	
	@Qualifier ("trorMainOrderHeaderLandimportService")
	private TrorMainOrderHeaderLandimportService trorMainOrderHeaderLandimportService;
	@Autowired
	@Required
	public void setTrorMainOrderHeaderLandimportService (TrorMainOrderHeaderLandimportService value){ this.trorMainOrderHeaderLandimportService = value; }
	public TrorMainOrderHeaderLandimportService getTrorMainOrderHeaderLandimportService(){ return this.trorMainOrderHeaderLandimportService; }
	
	
	
	@Qualifier ("trorMainOrderHeaderService")
	private TrorMainOrderHeaderService trorMainOrderHeaderService;
	@Autowired
	@Required
	public void setTrorMainOrderHeaderService (TrorMainOrderHeaderService value){ this.trorMainOrderHeaderService = value; }
	public TrorMainOrderHeaderService getTrorMainOrderHeaderService(){ return this.trorMainOrderHeaderService; }
	
	
	@Qualifier ("trorDropDownListPopulationService")
	private TrorDropDownListPopulationService trorDropDownListPopulationService;
	@Autowired
	@Required
	public void setTrorDropDownListPopulationService (TrorDropDownListPopulationService value){ this.trorDropDownListPopulationService = value; }
	public TrorDropDownListPopulationService getTrorDropDownListPopulationService(){ return this.trorDropDownListPopulationService; }
	
	@Qualifier ("maintMainKodtaService")
	private MaintMainKodtaService maintMainKodtaService;
	@Autowired
	@Required
	public void setMaintMainKodtaService (MaintMainKodtaService value){ this.maintMainKodtaService = value; }
	public MaintMainKodtaService getMaintMainKodtaService(){ return this.maintMainKodtaService; }
	
	
	@Qualifier ("maintSadImportKodts4Service")
	private MaintSadImportKodts4Service maintSadImportKodts4Service;
	@Autowired
	@Required
	public void setMaintSadImportKodts4Service (MaintSadImportKodts4Service value){ this.maintSadImportKodts4Service = value; }
	public MaintSadImportKodts4Service getMaintSadImportKodts4Service(){ return this.maintSadImportKodts4Service; }
	
	
	@Qualifier ("notisblockService")
	private NotisblockService notisblockService;
	@Autowired
	public void setNotisblockService (NotisblockService value){ this.notisblockService=value; }
	public NotisblockService getNotisblockService(){return this.notisblockService;}
	
	
	@Qualifier ("maintNctsExportTrkodfService")
	private MaintNctsExportTrkodfService  maintNctsExportTrkodfService;
	@Autowired
	@Required
	public void setMaintNctsExportTrkodfService (MaintNctsExportTrkodfService value){ this.maintNctsExportTrkodfService = value; }
	public MaintNctsExportTrkodfService getMaintNctsExportTrkodfService(){ return this.maintNctsExportTrkodfService; }
	
	
}

