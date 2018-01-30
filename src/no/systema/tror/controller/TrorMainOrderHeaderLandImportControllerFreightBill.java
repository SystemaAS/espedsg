package no.systema.tror.controller;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.builder.ReflectionToStringBuilder;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Required;
//import javax.annotation.PostConstruct;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;

import no.systema.ebooking.model.jsonjackson.JsonMainOrderTypesNewRecord;
import no.systema.jservices.common.dao.DokufDao;
import no.systema.jservices.common.dao.DokufeDao;
import no.systema.jservices.common.dao.Dok29Dao;
import no.systema.jservices.common.dao.Dok36Dao;
import no.systema.jservices.common.dao.FaktDao;
import no.systema.jservices.common.json.JsonDtoContainer;
import no.systema.jservices.common.json.JsonReader;
import no.systema.jservices.common.util.GSINCheckDigit;
import no.systema.main.model.SystemaWebUser;
import no.systema.main.util.StringManager;
import no.systema.main.validator.UserValidator;
import no.systema.transportdisp.model.workflow.order.OrderContactInformationObject;
import no.systema.main.service.UrlCgiProxyService;
import no.systema.main.util.AppConstants;
import no.systema.main.util.JsonDebugger;
import no.systema.main.util.MessageNoteManager;
import no.systema.tror.model.jsonjackson.JsonTrorOrderHeaderContainer;
import no.systema.tror.model.jsonjackson.JsonTrorOrderHeaderRecord;
import no.systema.tror.service.html.dropdown.TrorDropDownListPopulationService;
import no.systema.tror.service.landimport.TrorMainOrderHeaderLandimportService;
import no.systema.tror.url.store.TrorUrlDataStore;
import no.systema.tror.util.RpgReturnResponseHandler;
import no.systema.tror.util.TrorConstants;
import no.systema.tror.util.manager.CodeDropDownMgr;
import no.systema.tror.util.manager.LandImportExportManager;
import no.systema.tror.util.manager.OrderContactInformationManager;
import no.systema.tror.util.manager.FreightBillMessageNoteManager;
import no.systema.tror.mapper.url.request.UrlRequestParameterMapper;
import no.systema.z.main.maintenance.model.jsonjackson.dbtable.JsonMaintMainCundfContainer;
import no.systema.z.main.maintenance.model.jsonjackson.dbtable.JsonMaintMainCundfRecord;
import no.systema.z.main.maintenance.model.jsonjackson.dbtable.JsonMaintMainFirmContainer;
import no.systema.z.main.maintenance.model.jsonjackson.dbtable.JsonMaintMainFirmRecord;
import no.systema.z.main.maintenance.model.jsonjackson.dbtable.JsonMaintMainKodtaContainer;
import no.systema.z.main.maintenance.model.jsonjackson.dbtable.JsonMaintMainKodtaRecord;
import no.systema.z.main.maintenance.service.MaintMainCundfService;
import no.systema.z.main.maintenance.service.MaintMainFirmService;
import no.systema.z.main.maintenance.service.MaintMainKodtaService;
import no.systema.z.main.maintenance.url.store.MaintenanceMainUrlDataStore;
import no.systema.z.main.maintenance.util.MainMaintenanceConstants;
import no.systema.tror.validator.TrorOrderFraktbrevValidator;
import no.systema.tvinn.sad.z.maintenance.nctsexport.service.MaintNctsExportTrkodfService;

/**
 * Tror - Freight Bill Controller 
 * 
 * @author Fredrik Möller
 * @date Aug 20, 2017
 * 
 */

@Controller
@SessionAttributes(AppConstants.SYSTEMA_WEB_USER_KEY)
@Scope("session")
public class TrorMainOrderHeaderLandImportControllerFreightBill {
	private static Logger logger = Logger.getLogger(TrorMainOrderHeaderLandImportControllerFreightBill.class.getName());
	private ModelAndView loginView = new ModelAndView("login");
	private static final JsonDebugger jsonDebugger = new JsonDebugger(1500);
	private UrlRequestParameterMapper urlRequestParameterMapper = new UrlRequestParameterMapper();
	private CodeDropDownMgr codeDropDownMgr = new CodeDropDownMgr();
	private StringManager strMgr = new StringManager();
	private RpgReturnResponseHandler rpgReturnResponseHandler = new RpgReturnResponseHandler();
	private LandImportExportManager landImportMgr = new LandImportExportManager();
	private MessageNoteManager messageNoteMgr = new MessageNoteManager();
	private FreightBillMessageNoteManager freightBillMessageNoteManager = null;
	private OrderContactInformationManager orderContactInformationMgr = null;
	
	private final String KEY_ID_TRAN_TBL = "idTran";
	private final String KEY_ID_FIRFB_TBL = "idFirfb";
	private final String CARRIAGE_RETURN_PLAIN = "\n";
	private final String MESSAGE_NOTE_PARTY_TYPE_CONSIGNEE = "consignee";
	private final String MESSAGE_NOTE_PARTY_TYPE_CARRIER = "carrier";
	//
	private final String PARTY_CONSIGNOR_CN = "CN";
	private final String PARTY_CONSIGNEE_CZ = "CZ";
	//Contact information fields
	private final String ownSenderPartId = "ownSenderPartId";
	private final String ownSenderContactName = "ownSenderContactName";
	private final String ownSenderMobile = "ownSenderMobile";
	private final String ownSenderEmail = "ownSenderEmail";
	
	private final String ownReceiverPartId = "ownReceiverPartId";
	private final String ownReceiverContactName = "ownReceiverContactName";
	private final String ownReceiverMobile = "ownReceiverMobile";
	private final String ownReceiverEmail = "ownReceiverEmail";
	
	
	
	@PostConstruct
	public void initIt() throws Exception {
		//init managers
		freightBillMessageNoteManager = new FreightBillMessageNoteManager(this.urlCgiProxyService);
		orderContactInformationMgr = new OrderContactInformationManager(this.urlCgiProxyService);
	}
	/**
	 * 
	 * @param recordToValidate
	 * @param bindingResult
	 * @param session
	 * @param request
	 * @return
	 */
	@RequestMapping(value="tror_mainorderland_freightbill_gate.do", method={RequestMethod.GET, RequestMethod.POST })
	public ModelAndView tror_mainorderland_freightbill_gate(@ModelAttribute ("record") DokufDao recordToValidate, BindingResult bindingResult, HttpSession session, HttpServletRequest request){
		ModelAndView successView = null; 
		SystemaWebUser appUser = (SystemaWebUser)session.getAttribute(AppConstants.SYSTEMA_WEB_USER_KEY);
		Map model = new HashMap();
		String sign = request.getParameter("sign");
		
		
		if (appUser == null) {
			return this.loginView;
		} else {
			List<DokufDao> list = this.fetchFraktbrevList(appUser, recordToValidate.getDfavd(), recordToValidate.getDfopd(), recordToValidate.getDffbnr());
			if(list!=null && list.size()>1){
				successView = new ModelAndView("tror_mainorderland_freightbill_list");
				model.put("list", list);
				successView.addObject(MainMaintenanceConstants.DOMAIN_MODEL, model);
			}else{
				//this will send the request with an implicit action of doFetch
				successView = new ModelAndView("redirect:tror_mainorderland_freightbill_edit.do?" + "&dfavd=" + recordToValidate.getDfavd() + "&sign=" + sign + "&dfopd=" + recordToValidate.getDfopd());
			}
		}
		return successView;
	}
	/**
	 * 
	 * @param recordToValidate
	 * @param bindingResult
	 * @param session
	 * @param request
	 * @return
	 */
	@RequestMapping(value="tror_mainorderland_freightbill_list_edit.do", method={RequestMethod.GET, RequestMethod.POST })
	public ModelAndView tror_mainorderland_freightbill_list_edit(@ModelAttribute ("record") DokufDao recordToValidate, BindingResult bindingResult, HttpSession session, HttpServletRequest request){
		SystemaWebUser appUser = (SystemaWebUser)session.getAttribute(AppConstants.SYSTEMA_WEB_USER_KEY);
		Map model = new HashMap();
		String action = request.getParameter("action");
		String updateId = request.getParameter("updateId");
		String sign = request.getParameter("sign");
		ModelAndView successView = new ModelAndView("redirect:tror_mainorderland_freightbill_gate.do?" + "&dfavd=" + recordToValidate.getDfavd() + "&sign=" + sign + "&dfopd=" + recordToValidate.getDfopd());
		
		StringBuffer errMsg = new StringBuffer();
		JsonTrorOrderHeaderRecord headf = new JsonTrorOrderHeaderRecord();
		
		int dmlRetval = 0;
		DokufDao savedRecord = null;
		
		if (appUser == null) {
			return this.loginView;
		} else {
			
			if (MainMaintenanceConstants.ACTION_CREATE.equals(action)) {  //New
				logger.info("Inside - CREATE NEW");
				this.adjustFields( recordToValidate,  headf);
				// Validate
				TrorOrderFraktbrevValidator validator = new TrorOrderFraktbrevValidator();
				validator.validate(recordToValidate, bindingResult);
				if (bindingResult.hasErrors()) {
					logger.info("[ERROR Validation] Record does not validate)");
					model.put(MainMaintenanceConstants.DOMAIN_RECORD, recordToValidate);
				} else {
					recordToValidate.setDfsg(sign);
					savedRecord = updateRecord(appUser, recordToValidate, MainMaintenanceConstants.MODE_ADD, errMsg);
					/*TODO -- error handling ... (change successview among others ...)
					if (savedRecord == null) {
						logger.info("[ERROR Validation] Record does not validate)");
						model.put(MainMaintenanceConstants.ASPECT_ERROR_MESSAGE, errMsg.toString());
						model.put(MainMaintenanceConstants.DOMAIN_RECORD, recordToValidate);
					} else {
						DokufDao record = fetchRecord(appUser, recordToValidate.getDfavd(), recordToValidate.getDfopd(), recordToValidate.getDffbnr(), model);
						model.put(MainMaintenanceConstants.DOMAIN_RECORD, record);
					}*/
				}

			}  else if (MainMaintenanceConstants.ACTION_DELETE.equals(action)) { //Delete
				logger.info("Inside - DELETE");
				savedRecord = updateRecord(appUser, recordToValidate, MainMaintenanceConstants.MODE_DELETE, errMsg);
				/* TODO -- error handling ... (change successview among others ...)
				if (savedRecord == null) {
					logger.info("[ERROR Validation] Record does not validate)");
					model.put(MainMaintenanceConstants.ASPECT_ERROR_MESSAGE, errMsg.toString());
					model.put(MainMaintenanceConstants.DOMAIN_RECORD, recordToValidate);
					
				} else {
					DokufDao record = fetchRecord(appUser, recordToValidate.getDfavd(), recordToValidate.getDfopd(), recordToValidate.getDffbnr(), model);
					model.put(MainMaintenanceConstants.DOMAIN_RECORD, record);
				}*/
				

			} 
			
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
	@RequestMapping(value="tror_mainorderland_freightbill_edit.do", method={RequestMethod.GET, RequestMethod.POST })
	public ModelAndView tror_mainorderland_freightbill_edit(@ModelAttribute ("record") DokufDao recordToValidate, BindingResult bindingResult, HttpSession session, HttpServletRequest request){
		ModelAndView successView = new ModelAndView("tror_mainorderland_freightbill");
		SystemaWebUser appUser = (SystemaWebUser)session.getAttribute(AppConstants.SYSTEMA_WEB_USER_KEY);
		Map<String,Object> model = new HashMap<String,Object>();
		String action = request.getParameter("action");
		String updateId = request.getParameter("updateId");
		StringBuffer errMsg = new StringBuffer();
		JsonTrorOrderHeaderRecord headf = new JsonTrorOrderHeaderRecord();
		headf.setOwnEnhet1(request.getParameter("ownEnhet1"));
		headf.setOwnEnhet2(request.getParameter("ownEnhet2"));
		
		Map<String,Object> contactInfoMap = new HashMap<String,Object>();
		this.setMapOrderContactInformationParams(model, contactInfoMap, request);
		
		DokufDao savedRecord = null;
		
		if (appUser == null) {
			return this.loginView;
		} else {
			
			if (MainMaintenanceConstants.ACTION_CREATE.equals(action)) {  //New
				logger.info("Inside - CREATE NEW");
				this.adjustFields( recordToValidate,  headf);
				// Validate
				TrorOrderFraktbrevValidator validator = new TrorOrderFraktbrevValidator();
				validator.validate(recordToValidate, bindingResult);
				if (bindingResult.hasErrors()) {
					logger.info("[ERROR Validation] Record does not validate)");
					model.put(MainMaintenanceConstants.DOMAIN_RECORD, recordToValidate);
				} else {
					Map keyMap = new HashMap();
					this.calculateDf1004UniqueGUID(appUser, recordToValidate, keyMap);
					//recordToValidate.setDf1004("70701550001423698");
					//logger.info("#############:" + recordToValidate.getDf1004());
					savedRecord = updateRecord(appUser, recordToValidate, MainMaintenanceConstants.MODE_ADD, errMsg);
					if (savedRecord == null) {
						logger.info("[ERROR Validation] Record does not validate)");
						model.put(MainMaintenanceConstants.ASPECT_ERROR_MESSAGE, errMsg.toString());
						model.put(MainMaintenanceConstants.DOMAIN_RECORD, recordToValidate);
					} else {
						String id = (String)keyMap.get(KEY_ID_FIRFB_TBL);
						if(strMgr.isNotNull(id)){
							this.updateFirfbCounter(appUser.getUser(), id);
						}
						//process message notes
						this.processMessageNotes(model, appUser, request, recordToValidate);
						//update dokufe - contact information
						OrderContactInformationObject orderContactInfoObj = this.setOrderContactInformationObject(recordToValidate, contactInfoMap);
						this.orderContactInformationMgr.updateContactInformation(appUser, orderContactInfoObj, this.setDokufeDao(orderContactInfoObj));
						
						DokufDao record = fetchRecord(appUser, recordToValidate.getDfavd(), recordToValidate.getDfopd(), recordToValidate.getDffbnr(), model);
						this.fetchMessageNotes(model, appUser, recordToValidate);
						//fetch contact information
						this.fetchContactInformation(appUser, recordToValidate, model, contactInfoMap);
						
						model.put("action", MainMaintenanceConstants.ACTION_UPDATE);
						model.put(MainMaintenanceConstants.DOMAIN_RECORD, record);
					}
				}

			} else if (MainMaintenanceConstants.ACTION_UPDATE.equals(action)) { //Update
				this.adjustFields( recordToValidate,  headf);
				//Validate
				TrorOrderFraktbrevValidator validator = new TrorOrderFraktbrevValidator();
				validator.validate(recordToValidate, bindingResult);
				if (bindingResult.hasErrors()) {
					logger.info("[ERROR Validation] Record does not validate)");
					model.put(MainMaintenanceConstants.DOMAIN_RECORD, recordToValidate);
				} else {
					
					savedRecord = updateRecord(appUser, recordToValidate, MainMaintenanceConstants.MODE_UPDATE, errMsg);
					if (savedRecord == null) {
						logger.info("[ERROR Validation] Record does not validate)");
						model.put(MainMaintenanceConstants.ASPECT_ERROR_MESSAGE, errMsg.toString());
						model.put(MainMaintenanceConstants.DOMAIN_RECORD, recordToValidate);
					} else {
						//process message notes
						this.processMessageNotes(model, appUser, request, recordToValidate);
						//update dokufe - contact information
						OrderContactInformationObject orderContactInfoObj = this.setOrderContactInformationObject(recordToValidate, contactInfoMap);
						this.orderContactInformationMgr.updateContactInformation(appUser, orderContactInfoObj, this.setDokufeDao(orderContactInfoObj));
						
						//get record now (refreshed)
						DokufDao record = fetchRecord(appUser, recordToValidate.getDfavd(), recordToValidate.getDfopd(), recordToValidate.getDffbnr(), model);
						this.fetchMessageNotes(model, appUser, recordToValidate);
						//fetch contact information
						this.fetchContactInformation(appUser, recordToValidate, model, contactInfoMap);
						
						model.put("action", MainMaintenanceConstants.ACTION_UPDATE);
						model.put(MainMaintenanceConstants.DOMAIN_RECORD, record);
					}
				}
			} else if (MainMaintenanceConstants.ACTION_DELETE.equals(action)) { //Delete
				/*
				this.adjustFields( recordToValidate,  headf);
				//Validate
				TrorOrderFraktbrevValidator validator = new TrorOrderFraktbrevValidator();
				validator.validate(recordToValidate, bindingResult);
				if (bindingResult.hasErrors()) {
					logger.info("[ERROR Validation] Record does not validate)");
					model.put(MainMaintenanceConstants.DOMAIN_RECORD, recordToValidate);
				} else {
					savedRecord = updateRecord(appUser, recordToValidate, MainMaintenanceConstants.MODE_DELETE, errMsg);
					if (savedRecord == null) {
						logger.info("[ERROR Validation] Record does not validate)");
						model.put(MainMaintenanceConstants.ASPECT_ERROR_MESSAGE, errMsg.toString());
						model.put(MainMaintenanceConstants.DOMAIN_RECORD, recordToValidate);
					} else {
						DokufDao record = fetchRecord(appUser, recordToValidate.getDfavd(), recordToValidate.getDfopd(), recordToValidate.getDffbnr(), model);
						model.put(MainMaintenanceConstants.DOMAIN_RECORD, record);
					}
				}
				*/
				
			} else { // Fetch
				logger.info("FETCH branch");
				DokufDao recordDokufDao = null;
				//List list = this.fetchFraktbrevList(appUser, recordToValidate.getDfavd(), recordToValidate.getDfopd(), recordToValidate.getDffbnr());
				recordDokufDao = fetchRecord(appUser, recordToValidate.getDfavd(), recordToValidate.getDfopd(), recordToValidate.getDffbnr(), model);
				this.fetchMessageNotes(model, appUser, recordDokufDao);
				//fetch contact information
				this.fetchContactInformation(appUser, recordToValidate, model, contactInfoMap);
				
				
				if(recordDokufDao!=null && strMgr.isNotNull(recordDokufDao.getDf1004())){
					//get invoice data (currency & amount... 
					//12.Jan.2018: TODO--> after meeting (CB,JOVO,OT)a lot of issues CRUD must be resolved before we allow the end-user to input these 2 fields on GUI.
					//This Use Case has been flaged with low-priority
					this.getInvoiceAmount(appUser, recordDokufDao, model);
					model.put("action", MainMaintenanceConstants.ACTION_UPDATE);
					model.put(MainMaintenanceConstants.DOMAIN_RECORD, recordDokufDao);
				}else{
					//User will prepare the view for a future create-new fraktbrev. 
					model.put("action", MainMaintenanceConstants.ACTION_CREATE);
					//Here we prepare the form with default values from the "Oppdrag"
					JsonTrorOrderHeaderRecord orderHeader = this.getOrderHeadfRecord(appUser, model, null, String.valueOf(recordToValidate.getDfavd()), String.valueOf(recordToValidate.getDfopd()) );
					this.handoverOppdragValuesToFraktbrev(appUser, recordToValidate, orderHeader);
					model.put(MainMaintenanceConstants.DOMAIN_RECORD, recordToValidate);
				}
				
			}
			//get dropdowns
			this.setCodeDropDownMgr(appUser, model);
			
			model.put("dfavd", recordToValidate.getDfavd());
			model.put("dfopd", recordToValidate.getDfopd());
			model.put("dffbnr", recordToValidate.getDffbnr());
			successView.addObject(MainMaintenanceConstants.DOMAIN_MODEL, model);
			
			return successView;		
		}

	}
	/**
	 * Get all contact information
	 * @param appUser
	 * @param dokufDao
	 * @param model
	 * @param contactInfoMap
	 */
	private void fetchContactInformation(SystemaWebUser appUser, DokufDao dokufDao, Map<String,Object> model, Map<String,Object> contactInfoMap){
		OrderContactInformationObject orderContactInformationObject = this.setOrderContactInformationObject(dokufDao, contactInfoMap);
		this.orderContactInformationMgr.getContactInformation(appUser, this.setDokufeDao(orderContactInformationObject), this.PARTY_CONSIGNOR_CN, true, orderContactInformationObject);
		this.orderContactInformationMgr.getContactInformation(appUser, this.setDokufeDao(orderContactInformationObject), this.PARTY_CONSIGNEE_CZ, true, orderContactInformationObject);
		this.setModelContactInformation(model, orderContactInformationObject);				
	}
	/**
	 * Gets the request params once and for all since this params ARE NOT in Dokuf
	 * @param model
	 * @param contactInfoMap
	 * @param request
	 */
	private void setMapOrderContactInformationParams(Map<String,Object> model, Map<String,Object> contactInfoMap, HttpServletRequest request){
		//This map is for coming updates
		contactInfoMap.put(this.ownSenderPartId, request.getParameter(this.ownSenderPartId));
		contactInfoMap.put(this.ownSenderContactName, request.getParameter(this.ownSenderContactName));
		contactInfoMap.put(this.ownSenderMobile, request.getParameter(this.ownSenderMobile));
		contactInfoMap.put(this.ownSenderEmail, request.getParameter(this.ownSenderEmail));
		contactInfoMap.put(this.ownReceiverPartId, request.getParameter(this.ownReceiverPartId));
		contactInfoMap.put(this.ownReceiverContactName, request.getParameter(this.ownReceiverContactName));
		contactInfoMap.put(this.ownReceiverMobile, request.getParameter(this.ownReceiverMobile));
		contactInfoMap.put(this.ownReceiverEmail, request.getParameter(this.ownReceiverEmail));
		//this map is just as a fallback for validation errors. The values here will be overridden in a successful update
		model.put(this.ownSenderPartId, request.getParameter(this.ownSenderPartId));
		model.put(this.ownSenderContactName, request.getParameter(this.ownSenderContactName));
		model.put(this.ownSenderMobile, request.getParameter(this.ownSenderMobile));
		model.put(this.ownSenderEmail, request.getParameter(this.ownSenderEmail));
		model.put(this.ownReceiverPartId, request.getParameter(this.ownReceiverPartId));
		model.put(this.ownReceiverContactName, request.getParameter(this.ownReceiverContactName));
		model.put(this.ownReceiverMobile, request.getParameter(this.ownReceiverMobile));
		model.put(this.ownReceiverEmail, request.getParameter(this.ownReceiverEmail));
		
	}
	/**
	 * 
	 * @param dokufDao
	 * @param contactInfoMap
	 * @return
	 */
	private OrderContactInformationObject setOrderContactInformationObject (DokufDao dokufDao, Map<String,Object> contactInfoMap){
		OrderContactInformationObject obj = new OrderContactInformationObject();
		obj.setAvd(String.valueOf(dokufDao.getDfavd()));
		obj.setOpd(String.valueOf(dokufDao.getDfopd()));
		
		//
		obj.setOwnSenderContactName((String)contactInfoMap.get(this.ownSenderContactName));
		obj.setOwnSenderMobile((String)contactInfoMap.get(this.ownSenderEmail));
		obj.setOwnSenderEmail((String)contactInfoMap.get(this.ownSenderEmail));
		//
		obj.setOwnReceiverContactName((String)contactInfoMap.get(this.ownReceiverContactName));
		obj.setOwnReceiverMobile((String)contactInfoMap.get(this.ownReceiverMobile));
		obj.setOwnReceiverEmail((String)contactInfoMap.get(this.ownReceiverEmail));
		
		return obj;
	}
	/**
	 * 
	 * @param orderContactInfoObj
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
	 * @param model
	 * @param orderContactInformationObject
	 */
	private void setModelContactInformation(Map<String, Object> model, OrderContactInformationObject orderContactInformationObject){
		
		model.put(this.ownSenderContactName, orderContactInformationObject.getOwnSenderContactName());
		model.put(this.ownSenderMobile,orderContactInformationObject.getOwnSenderMobile());
		model.put(this.ownSenderEmail,orderContactInformationObject.getOwnSenderEmail());
		//
		model.put(this.ownReceiverContactName,orderContactInformationObject.getOwnReceiverContactName());
		model.put(this.ownReceiverMobile,orderContactInformationObject.getOwnReceiverMobile());
		model.put(this.ownReceiverEmail,orderContactInformationObject.getOwnReceiverEmail());
		
	}
	/**
	 * 
	 * @param model
	 * @param appUser
	 * @param dokufDao
	 */
	private void fetchMessageNotes(Map model, SystemaWebUser appUser, DokufDao dokufDao){
		this.freightBillMessageNoteManager.fetchMessageNoteConsignee(model, appUser, dokufDao);
		this.freightBillMessageNoteManager.fetchMessageNoteCarrier(model, appUser, dokufDao);
	}
	/**
	 * 
	 * @param model
	 * @param appUser
	 * @param dokufDao
	 */
	/*
	private void fetchMessageNoteConsignee(Map model, SystemaWebUser appUser, DokufDao dokufDao){
		StringBuffer guiMessageNotePayload = new StringBuffer();
		
		JsonReader<JsonDtoContainer<Dok29Dao>> jsonReader = new JsonReader<JsonDtoContainer<Dok29Dao>>();
		jsonReader.set(new JsonDtoContainer<Dok29Dao>());
		final String BASE_URL = TrorUrlDataStore.TROR_BASE_FETCH_DOK29_URL;
		StringBuilder urlRequestParams = new StringBuilder();
		urlRequestParams.append("user=" + appUser.getUser());
		urlRequestParams.append("&d29avd=" + dokufDao.getDfavd());
		urlRequestParams.append("&d29opd=" + dokufDao.getDfopd());
		urlRequestParams.append("&d29fnr=" + dokufDao.getDffbnr());
		
		
		logger.info("URL: " + BASE_URL);
		logger.info("PARAMS: " + urlRequestParams.toString());
		String jsonPayload = urlCgiProxyService.getJsonContent(BASE_URL, urlRequestParams.toString());
		logger.info("jsonPayload=" + jsonPayload);
		DokufDao record = null;
		JsonDtoContainer<Dok29Dao> container = (JsonDtoContainer<Dok29Dao>) jsonReader.get(jsonPayload);
		if (container != null) {
			for (Dok29Dao dao : container.getDtoList()) {
				guiMessageNotePayload.append(dao.getD29txt() + this.CARRIAGE_RETURN_PLAIN);
			}
		}
		model.put("messageNoteConsignee", guiMessageNotePayload.toString());
		
	}
	/**
	 * 
	 * @param model
	 * @param appUser
	 * @param dokufDao
	 */
	/*
	private void fetchMessageNoteCarrier(Map model, SystemaWebUser appUser, DokufDao dokufDao){
		StringBuffer guiMessageNotePayload = new StringBuffer();
		
		JsonReader<JsonDtoContainer<Dok36Dao>> jsonReader = new JsonReader<JsonDtoContainer<Dok36Dao>>();
		jsonReader.set(new JsonDtoContainer<Dok36Dao>());
		final String BASE_URL = TrorUrlDataStore.TROR_BASE_FETCH_DOK36_URL;
		StringBuilder urlRequestParams = new StringBuilder();
		urlRequestParams.append("user=" + appUser.getUser());
		urlRequestParams.append("&d36avd=" + dokufDao.getDfavd());
		urlRequestParams.append("&d36opd=" + dokufDao.getDfopd());
		urlRequestParams.append("&d36fnr=" + dokufDao.getDffbnr());
		
		
		logger.info("URL: " + BASE_URL);
		logger.info("PARAMS: " + urlRequestParams.toString());
		String jsonPayload = urlCgiProxyService.getJsonContent(BASE_URL, urlRequestParams.toString());
		logger.info("jsonPayload=" + jsonPayload);
		DokufDao record = null;
		JsonDtoContainer<Dok36Dao> container = (JsonDtoContainer<Dok36Dao>) jsonReader.get(jsonPayload);
		if (container != null) {
			for (Dok36Dao dao : container.getDtoList()) {
				guiMessageNotePayload.append(dao.getD36txt() + this.CARRIAGE_RETURN_PLAIN);
			}
		}
		model.put("messageNoteCarrier", guiMessageNotePayload.toString());
		
	}
	*/
	/**
	 * 
	 * @param model
	 * @param appUser
	 * @param request
	 * @param recordToValidate
	 */
	
	private void processMessageNotes(Map model, SystemaWebUser appUser, HttpServletRequest request, DokufDao recordToValidate){
		String messageNoteConsignee = request.getParameter("messageNoteConsignee");
		String messageNoteCarrier = request.getParameter("messageNoteCarrier");
		
		List<String> messageNoteConsigneeList = this.messageNoteMgr.getChunksOfMessageNoteAsList(messageNoteConsignee);
		List<String> messageNoteCarrierList = this.messageNoteMgr.getChunksOfMessageNoteAsList(messageNoteCarrier);
		this.freightBillMessageNoteManager.updateMessageNote(model, messageNoteConsigneeList, appUser, MESSAGE_NOTE_PARTY_TYPE_CONSIGNEE, recordToValidate);
		this.freightBillMessageNoteManager.updateMessageNote(model, messageNoteCarrierList, appUser, MESSAGE_NOTE_PARTY_TYPE_CARRIER, recordToValidate);
	}
	/**
	 * 
	 * @param model
	 * @param messageNote
	 * @param appUser
	 */
	/*
	private void updateMessageNote(Map model, List<String> messageNotePayload, SystemaWebUser appUser, String partyType, DokufDao recordToValidate){
		String CARRIAGE_RETURN = "[\n\r]";
		String NULL_PAYLOAD = null;
		//logger.info("A" + messageNotePayload); 
		if(messageNotePayload!=null && !messageNotePayload.isEmpty()){
			if(this.MESSAGE_NOTE_PARTY_TYPE_CONSIGNEE.equals(partyType)){
				//delete all
				this.updateMessageNoteConsignee(NULL_PAYLOAD, model, appUser, recordToValidate, TrorConstants.MODE_DELETE);
				for(String linePayload: messageNotePayload){
					linePayload = linePayload.replaceAll(CARRIAGE_RETURN, "");
					logger.info("CONSIGNEE MESSAGE NOTE #########:" + linePayload);
					//insert this line
					this.updateMessageNoteConsignee(linePayload, model, appUser, recordToValidate, TrorConstants.MODE_ADD);
					
				}
			}else if (this.MESSAGE_NOTE_PARTY_TYPE_CARRIER.equals(partyType)){
				//delete all
				this.updateMessageNoteCarrier(NULL_PAYLOAD, model, appUser, recordToValidate, TrorConstants.MODE_DELETE);
				for(String linePayload: messageNotePayload){
					linePayload = linePayload.replaceAll(CARRIAGE_RETURN, "");
					logger.info("CARRIER MESSAGE NOTE #########:" + linePayload);
					//insert this line
					this.updateMessageNoteCarrier(linePayload, model, appUser, recordToValidate, TrorConstants.MODE_ADD);
				}
			}
			
		}
	}
	/**
	 * 
	 * @param payload
	 * @param model
	 * @param appUser
	 * @param recordToValidate
	 * @param MODE
	 */
	/*
	private void updateMessageNoteConsignee(String payload, Map model, SystemaWebUser appUser, DokufDao recordToValidate, String MODE){
		
		JsonReader<JsonDtoContainer<Dok29Dao>> jsonReader = new JsonReader<JsonDtoContainer<Dok29Dao>>();
		jsonReader.set(new JsonDtoContainer<Dok29Dao>());
		final String BASE_URL = TrorUrlDataStore.TROR_BASE_DOK29_DML_UPDATE_URL;
		StringBuilder urlRequestParams = new StringBuilder();
		urlRequestParams.append("user=" + appUser.getUser());
		urlRequestParams.append("&mode=" + MODE);
		urlRequestParams.append("&d29avd=" + recordToValidate.getDfavd());
		urlRequestParams.append("&d29opd=" + recordToValidate.getDfopd());
		urlRequestParams.append("&d29fnr=" + recordToValidate.getDffbnr());
		if(!MODE.equals("D")){
			urlRequestParams.append("&d29txt=" + payload);
		}
		
		logger.info("URL: " + BASE_URL);
		logger.info("PARAMS: " + urlRequestParams.toString());
		String jsonPayload = urlCgiProxyService.getJsonContent(BASE_URL, urlRequestParams.toString());
		logger.info("jsonPayload=" + jsonPayload);
		DokufDao record = null;
		JsonDtoContainer<Dok29Dao> container = (JsonDtoContainer<Dok29Dao>) jsonReader.get(jsonPayload);
		if (container != null) {
			if(strMgr.isNotNull(container.getErrMsg())){
				logger.info("ERROR on update from DOK29 .... check this out!" + " MODE:" + MODE);
			}
		}
	}
	/**
	 * 
	 * @param payload
	 * @param model
	 * @param appUser
	 * @param recordToValidate
	 * @param MODE
	 */
	/*
	private void updateMessageNoteCarrier(String payload, Map model, SystemaWebUser appUser, DokufDao recordToValidate, String MODE){
		
		JsonReader<JsonDtoContainer<Dok36Dao>> jsonReader = new JsonReader<JsonDtoContainer<Dok36Dao>>();
		jsonReader.set(new JsonDtoContainer<Dok36Dao>());
		final String BASE_URL = TrorUrlDataStore.TROR_BASE_DOK36_DML_UPDATE_URL;
		StringBuilder urlRequestParams = new StringBuilder();
		urlRequestParams.append("user=" + appUser.getUser());
		urlRequestParams.append("&mode=" + MODE);
		urlRequestParams.append("&d36avd=" + recordToValidate.getDfavd());
		urlRequestParams.append("&d36opd=" + recordToValidate.getDfopd());
		urlRequestParams.append("&d36fnr=" + recordToValidate.getDffbnr());
		if(!MODE.equals("D")){
			urlRequestParams.append("&d36txt=" + payload);
		}
		logger.info("URL: " + BASE_URL);
		logger.info("PARAMS: " + urlRequestParams.toString());
		String jsonPayload = urlCgiProxyService.getJsonContent(BASE_URL, urlRequestParams.toString());
		logger.info("jsonPayload=" + jsonPayload);
		DokufDao record = null;
		JsonDtoContainer<Dok36Dao> container = (JsonDtoContainer<Dok36Dao>) jsonReader.get(jsonPayload);
		if (container != null) {
			if(strMgr.isNotNull(container.getErrMsg())){
				logger.info("ERROR on update from DOK36 .... check this out!" + " MODE:" + MODE);
			}
		}
	}
	
	/**
	 * Creates the unique GUID (df1004) for the new fraktbrev...
	 * @param appUser
	 * @param recordToValidate
	 */
	
	private void calculateDf1004UniqueGUID(SystemaWebUser appUser, DokufDao recordToValidate, Map keyMap){
		String BASE_URL = MaintenanceMainUrlDataStore.MAINTENANCE_MAIN_BASE_SYFIRMR_GET_LIST_URL;
		String urlRequestParams = "user=" + appUser.getUser();
		
		logger.info(Calendar.getInstance().getTime() + " CGI-start timestamp");
    	logger.info("URL: " + jsonDebugger.getBASE_URL_NoHostName(BASE_URL));
    	logger.info("URL PARAMS: " + urlRequestParams);
    	String jsonPayload = this.urlCgiProxyService.getJsonContent(BASE_URL, urlRequestParams);
    	//DEBUG
    	this.jsonDebugger.debugJsonPayload(jsonPayload, 1000);
    	if(jsonPayload!=null){
			//lists
    		JsonMaintMainFirmContainer container = this.maintMainFirmService.getList(jsonPayload);
	        if(container!=null){
	        	for(JsonMaintMainFirmRecord record : container.getList()){
	        		logger.info("Calculate Df1004GUID");
	        		this.constructDf1004GUID(record, recordToValidate);
	        		//for the update of counter (firecn) on table FIRFB done in caller function
	        		keyMap.put(KEY_ID_FIRFB_TBL, record.getFifirm());
	        		break;
	        	}
	        }
    	}
	}
	
	/**
	 * 
	 * @param applicationUser
	 * @param id
	 */
	private void updateFirfbCounter(String applicationUser, String id){
		String BASE_URL = TrorUrlDataStore.TROR_BASE_UPDATE_FIRFB_COUNTER_URL;
		String urlRequestParamsKeys = "user=" + applicationUser + "&fifirm=" + id;
		logger.info(Calendar.getInstance().getTime() + " CGI-start timestamp");
    	logger.info("URL: " + jsonDebugger.getBASE_URL_NoHostName(BASE_URL));
    	logger.info("URL PARAMS: " + urlRequestParamsKeys);
    	String jsonPayload = this.urlCgiProxyService.getJsonContent(BASE_URL, urlRequestParamsKeys);
		
    	//extract
    	if(jsonPayload!=null){
			//lists
    		JsonMaintMainFirmContainer container = this.maintMainFirmService.doUpdate(jsonPayload);
	        if(container!=null){
	        	if(container.getErrMsg()!=null && !"".equals(container.getErrMsg())){
	        		logger.info(container.getErrMsg());
	        		//TODO error handling. Not today. Today is friday ... ;-)
					/*
	        		if(container.getErrMsg().toUpperCase().startsWith("ERROR")){
	        			errMsg.append(container.getErrMsg());
	        			retval = MainMaintenanceConstants.ERROR_CODE;
	        		}*/
	        	}else{
	        		//All = OK
	        	}
	        }
    	} 
       	
	}
		
	/**
	 * 
	 * @param snlaValue
	 * @param snleValue
	 * @param recnValue
	 * @param recordToValidate
	 */
	private void constructDf1004GUID(JsonMaintMainFirmRecord record, DokufDao recordToValidate){
		String newRecnValue = this.strMgr.leadingStringWithNumericFiller(record.getFirecn(), 9, "0");
		String df1004WithoutCheckDigit = record.getFisnla() + record.getFisnle() + newRecnValue;
		String checkDigit = GSINCheckDigit.calculate(df1004WithoutCheckDigit);
		//final number String (17)
		String df1004 = df1004WithoutCheckDigit + checkDigit;
		recordToValidate.setDf1004(df1004);
	}
	
	/**
	 * 
	 * @param appUser
	 * @param dokufDao
	 * @param model
	 */
	private void getInvoiceAmount(SystemaWebUser appUser, DokufDao dokufDao, Map model){
		//===========
		//FETCH LIST
		//===========
		JsonReader<JsonDtoContainer<FaktDao>> jsonReader = new JsonReader<JsonDtoContainer<FaktDao>>();
		jsonReader.set(new JsonDtoContainer<FaktDao>());
		
		 logger.info("Inside: getInvoiceAmount");
		 //prepare the access CGI with RPG back-end
		 String BASE_URL = TrorUrlDataStore.TROR_BASE_FETCH_FAKTR_URL;
		 StringBuffer urlRequestParamsKeys = new StringBuffer();
		 urlRequestParamsKeys.append("user=" + appUser.getUser());
		 urlRequestParamsKeys.append("&faavd=" + dokufDao.getDfavd() + "&faopd=" + dokufDao.getDfopd() + "&fafrbn=" + dokufDao.getDffbnr());
		 
		 logger.info("URL: " + BASE_URL);
		 logger.info("PARAMS: " + urlRequestParamsKeys);
		 logger.info(Calendar.getInstance().getTime() +  " CGI-start timestamp");
		 logger.info(Calendar.getInstance().getTime() +  " CGI-end timestamp");
		 
		 List<FaktDao> daoList = new ArrayList<FaktDao>();
		 String jsonPayload = this.urlCgiProxyService.getJsonContent(BASE_URL, urlRequestParamsKeys.toString());
		 logger.debug(jsonDebugger.debugJsonPayloadWithLog4J(jsonPayload));
		 
		 if(jsonPayload!=null){
		 	try{
		 		JsonDtoContainer<FaktDao> container = (JsonDtoContainer<FaktDao>) jsonReader.get(jsonPayload);
				if(container!=null){
					for(FaktDao record : container.getDtoList()){
						model.put("ownInvoiceCurrency", record.getFaval());
						model.put("ownInvoiceAmount", record.getFabeln());
						logger.info(record.getFaval() + " " + record.getFabeln());
						
					}
				}
				
		 	}catch(Exception e){
		 		e.printStackTrace();
		 	}
		 }
	}
	/**
	 * 
	 * @param appUser
	 * @param recordToValidate
	 * @param orderHeader
	 */
	private void handoverOppdragValuesToFraktbrev(SystemaWebUser appUser, DokufDao recordToValidate, JsonTrorOrderHeaderRecord orderHeader){
		try{
			//some keys
			recordToValidate.setDfsg(orderHeader.getHesg());
			//-------
			//PARTS
			//-------
			//set correct Sender
			this.setCorrectSender(appUser, recordToValidate, orderHeader);
			//set correct Invoicee
			this.setCorrectInvoicee(recordToValidate, orderHeader);
			//Mottaker must be truncated Dffase = 25 length and henas = 30
			if(strMgr.isNotNull(orderHeader.getHenas()) && orderHeader.getHenas().length()>25){
				recordToValidate.setDffase(orderHeader.getHenas().substring(0,24));
			}else{
				recordToValidate.setDffase(orderHeader.getHenas());
			}
			
			if(strMgr.isNotNull(orderHeader.getHeknk())){
				recordToValidate.setDfknsm(Integer.parseInt(orderHeader.getHeknk()));
			}
			
			recordToValidate.setDfnavm(orderHeader.getHenak());
			recordToValidate.setDfad1m(orderHeader.getHeadk1());
			recordToValidate.setDfad3m(orderHeader.getHeadk3());
			//end PARTS
			
			//other
			recordToValidate.setDfcmn("N"); //Edifact
			recordToValidate.setDfntla(Integer.parseInt(orderHeader.getHent())); //Merkelappar in header
			//item lines
			recordToValidate.setDfnt(recordToValidate.getDfntla());
			recordToValidate.setDfgm(orderHeader.getHegm1());
			recordToValidate.setDfgm2(orderHeader.getHegm2());
			recordToValidate.setDfvs(orderHeader.getHevs1());
			recordToValidate.setDfvs2(orderHeader.getHevs2());
			
			if(strMgr.isNotNull(orderHeader.getHevkt())){
				recordToValidate.setDfvkt(Integer.parseInt(orderHeader.getHevkt()));
			}
			if(strMgr.isNotNull(orderHeader.getHem3())){
				recordToValidate.setDfm3(BigDecimal.valueOf(Double.parseDouble(orderHeader.getHem3()))); 
			}
			if(strMgr.isNotNull(orderHeader.getHelm())){
				recordToValidate.setDflm(BigDecimal.valueOf(Double.parseDouble(orderHeader.getHelm()))); 
			}
			
		}catch (Exception e){
			logger.info("Handover ERROR some where...(BigDecimals, Integers ?)" + e.toString());
		}
	}
	/**
	 * 
	 * @param appUser
	 * @param recordToValidate
	 * @param orderHeader
	 */
	private void setCorrectSender(SystemaWebUser appUser, DokufDao recordToValidate, JsonTrorOrderHeaderRecord orderHeader){
		Collection<JsonMaintMainKodtaRecord> list = new ArrayList<JsonMaintMainKodtaRecord>();
		//prepare the access CGI with RPG back-end
		
		String BASE_URL = MaintenanceMainUrlDataStore.MAINTENANCE_MAIN_BASE_SYFA14R_GET_LIST_URL;
		String urlRequestParamsKeys = "user=" + appUser.getUser() + "&koaavd=" + orderHeader.getHeavd();
		String jsonPayload = this.urlCgiProxyService.getJsonContent(BASE_URL, urlRequestParamsKeys);
		//debugger
		logger.debug(jsonDebugger.debugJsonPayloadWithLog4J(jsonPayload));
		logger.info(Calendar.getInstance().getTime() +  " CGI-end timestamp");
    	if(jsonPayload!=null){
    		JsonMaintMainKodtaContainer container = this.maintMainKodtaService.getList(jsonPayload);
    		String kundnr = "";
    		String firma = "";
    		
    		if(container!=null){
    			list = container.getList();
    			for(JsonMaintMainKodtaRecord  record : list){
    				kundnr = record.getKoaknr();
    				firma = record.getKoafir();
    				break;
    			}
    		}
    		//-------------------------------------------
    		//Now get all info of the specific kund nr.
    		//-------------------------------------------
    		BASE_URL = MaintenanceMainUrlDataStore.MAINTENANCE_MAIN_BASE_SYCUNDFR_GET_LIST_URL;
    		StringBuilder urlRequestParams = new StringBuilder();
    		urlRequestParams.append("user=" + appUser.getUser());
    		if (kundnr != null && firma != null) {
    			urlRequestParams.append("&kundnr=" + kundnr);
    			urlRequestParams.append("&firma=" + firma);
    			//
    			logger.info("URL: " + BASE_URL);
        		logger.info("PARAMS: " + urlRequestParams.toString());
        		jsonPayload = this.urlCgiProxyService.getJsonContent(BASE_URL, urlRequestParams.toString());
        		Collection<JsonMaintMainCundfRecord> cundfList = new ArrayList<JsonMaintMainCundfRecord>();
        		if (jsonPayload != null) {
        			JsonMaintMainCundfContainer containerCundf = this.maintMainCundfService.getList(jsonPayload);
        			if (container != null) {
        				for(JsonMaintMainCundfRecord  record : containerCundf.getList()){
        					recordToValidate.setDfknss(Integer.valueOf(record.getKundnr()) ); 
        					recordToValidate.setDfnavs(record.getKnavn());
        					recordToValidate.setDfad1s(record.getAdr1());
        					recordToValidate.setDfad3s(record.getAdr3());
        					if( strMgr.isNotNull(record.getPostnr()) ){
        						recordToValidate.setDfpnls(Integer.valueOf(record.getPostnr()) );
        					}
        					break;
        				}
       
        			}
        		}
    			
    		}

    		
    	}
	}
	
	/**
	 * 
	 * @param recordToValidate
	 * @param orderHeader
	 */
	private void setCorrectInvoicee(DokufDao recordToValidate, JsonTrorOrderHeaderRecord orderHeader){
		//source values
		String INVOICEE_X_FLAG_FROM_ORDER = "X";
		//target values
		String INVOICEE_IS_SELGER = "S";
		String INVOICEE_IS_MOTTAKER = "M";
		String INVOICEE_IS_ANNEN = "A";
		
		
		//CHECK if BOTH where selected and pick the one not-having "X"
		String sellerFlagFromOrder = orderHeader.getHekdfs();
		String buyerFlagFromOrder = orderHeader.getHekdfk();
		
		if( INVOICEE_X_FLAG_FROM_ORDER.equals(sellerFlagFromOrder) && INVOICEE_X_FLAG_FROM_ORDER.equals(buyerFlagFromOrder) ){
			recordToValidate.setDfbela(INVOICEE_IS_ANNEN);
			
		}else{
			//default
			recordToValidate.setDfbela(INVOICEE_IS_MOTTAKER);

			//now let's see if we can override the default
			if (INVOICEE_X_FLAG_FROM_ORDER.equals(buyerFlagFromOrder)){
				recordToValidate.setDfbela(INVOICEE_IS_SELGER);
			}
		}
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
	public JsonTrorOrderHeaderRecord getOrderHeadfRecord(SystemaWebUser appUser, Map model, JsonMainOrderTypesNewRecord orderTypes, String heavd, String heopd ){
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
	 * @param recordToValidate
	 * @param headf
	 */
	private void adjustFields(DokufDao recordToValidate, JsonTrorOrderHeaderRecord headf){
		//Vareslag must be concatenated since there is no Enhet-field in target db-table. The enhet is part of the Vareslag as the 2-first characters of dfvs, dfvs2
		String SPACE = " ";
		if(strMgr.isNotNull(headf.getOwnEnhet1()) && strMgr.isNotNull(recordToValidate.getDfvs()) ){
			recordToValidate.setDfvs(headf.getOwnEnhet1() + SPACE + recordToValidate.getDfvs());
		}
		//OBSOLETE
		/*
		if(strMgr.isNotNull(headf.getOwnEnhet2()) && strMgr.isNotNull(recordToValidate.getDfvs2()) ){
			recordToValidate.setDfvs2(headf.getOwnEnhet2() + SPACE + recordToValidate.getDfvs2());
		}*/
	
	}
	/**
	 * 
	 * @param appUser
	 * @param dfavd
	 * @param dfopd
	 * @param dffbnr
	 * @return
	 */
	private DokufDao fetchRecord(SystemaWebUser appUser, int dfavd, int dfopd, int dffbnr, Map model) {
		
		JsonReader<JsonDtoContainer<DokufDao>> jsonReader = new JsonReader<JsonDtoContainer<DokufDao>>();
		jsonReader.set(new JsonDtoContainer<DokufDao>());
		final String BASE_URL = TrorUrlDataStore.TROR_BASE_FETCH_DOKUF_URL;
		StringBuilder urlRequestParams = new StringBuilder();
		urlRequestParams.append("user=" + appUser.getUser());
		urlRequestParams.append("&dfavd=" + dfavd);
		urlRequestParams.append("&dfopd=" + dfopd);
		if(dffbnr>0){
			urlRequestParams.append("&dffbnr=" + dffbnr);
		}
		
		logger.info("URL: " + BASE_URL);
		logger.info("PARAMS: " + urlRequestParams.toString());
		String jsonPayload = urlCgiProxyService.getJsonContent(BASE_URL, urlRequestParams.toString());
		logger.info("jsonPayload=" + jsonPayload);
		DokufDao record = null;
		JsonDtoContainer<DokufDao> container = (JsonDtoContainer<DokufDao>) jsonReader.get(jsonPayload);
		if (container != null) {
			for (DokufDao dao : container.getDtoList()) {
				record = dao;
				//adjust some fields for presentation purposes
				this.setSpecialValuesForPresentation(appUser, record, model);
			}
		}
		return record;
	
	}	
	/**
	 * 
	 * @param appUser
	 * @param dfavd
	 * @param dfopd
	 * @param dffbnr
	 * @param model
	 * @return
	 */
	private List<DokufDao> fetchFraktbrevList(SystemaWebUser appUser, int dfavd, int dfopd, int dffbnr) {
		List<DokufDao> retval = new ArrayList<DokufDao>();
		JsonReader<JsonDtoContainer<DokufDao>> jsonReader = new JsonReader<JsonDtoContainer<DokufDao>>();
		jsonReader.set(new JsonDtoContainer<DokufDao>());
		final String BASE_URL = TrorUrlDataStore.TROR_BASE_FETCH_DOKUF_URL;
		StringBuilder urlRequestParams = new StringBuilder();
		urlRequestParams.append("user=" + appUser.getUser());
		urlRequestParams.append("&dfavd=" + dfavd);
		urlRequestParams.append("&dfopd=" + dfopd);
		if(dffbnr>0){
			urlRequestParams.append("&dffbnr=" + dffbnr);
		}
		
		logger.info("URL: " + BASE_URL);
		logger.info("PARAMS: " + urlRequestParams.toString());
		String jsonPayload = urlCgiProxyService.getJsonContent(BASE_URL, urlRequestParams.toString());
		logger.info("jsonPayload=" + jsonPayload);
		DokufDao record = null;
		JsonDtoContainer<DokufDao> container = (JsonDtoContainer<DokufDao>) jsonReader.get(jsonPayload);
		if (container != null) {
			retval = container.getDtoList();
			
		}
		return retval;
	
	}	
	/**
	 * 
	 * @param record
	 * @param model
	 */
	private void setSpecialValuesForPresentation(SystemaWebUser appUser, DokufDao record, Map model ){
		String UNITOFMEASURE_1 = "uom1";
		String UNITOFMEASURE_1_LENGTH = "uom1Length";
		
		
		String UOM_SEPARATOR = " ";
		if(strMgr.isNotNull(record.getDfvs())){
			int index = record.getDfvs().indexOf(UOM_SEPARATOR);
			if(index>=0){
				String uom = record.getDfvs().substring(0,index);
				//Check if uom is valid
				if(this.landImportMgr.findUnitOfMeasure(this.urlCgiProxyService, this.maintNctsExportTrkodfService, appUser, uom)){
					model.put(UNITOFMEASURE_1, uom);
					model.put(UNITOFMEASURE_1_LENGTH, uom.length());
					//logger.info("UOM!!!!!!!!!!!!!!!!:" + uom);
				}else{
					//logger.info("INVALID uom!!!!!");
					model.put(UNITOFMEASURE_1, "");
					model.put(UNITOFMEASURE_1_LENGTH, 0);
				}
			}
		}
	}
	
	
	/**
	 * 
	 * @param appUser
	 * @param record
	 * @param mode
	 * @param errMsg
	 * @return
	 */
	private DokufDao updateRecord(SystemaWebUser appUser, DokufDao record, String mode, StringBuffer errMsg) {
		DokufDao savedRecord = null;
		JsonReader<JsonDtoContainer<DokufDao>> jsonReader = new JsonReader<JsonDtoContainer<DokufDao>>();
		jsonReader.set(new JsonDtoContainer<DokufDao>());
		final String BASE_URL = TrorUrlDataStore.TROR_BASE_DOKUF_DML_UPDATE_URL;
		String urlRequestParamsKeys = "user=" + appUser.getUser() + "&mode=" + mode + "&lang=" +appUser.getUsrLang();
		String urlRequestParams = this.urlRequestParameterMapper.getUrlParameterValidString(record);
		urlRequestParams = urlRequestParamsKeys + urlRequestParams;

		logger.info(Calendar.getInstance().getTime() + " CGI-start timestamp");
		logger.info("URL: " + jsonDebugger.getBASE_URL_NoHostName(BASE_URL));
		logger.info("URL PARAMS: " + urlRequestParams);
		List<DokufDao> list = new ArrayList<DokufDao>();
		String jsonPayload = this.urlCgiProxyService.getJsonContent(BASE_URL, urlRequestParams);
		logger.debug(jsonDebugger.debugJsonPayloadWithLog4J(jsonPayload));
		
		if (jsonPayload != null) {
			JsonDtoContainer<DokufDao> container = (JsonDtoContainer<DokufDao>) jsonReader.get(jsonPayload);
			if (container != null) {
				if (container.getErrMsg() != null && !"".equals(container.getErrMsg())) {
					errMsg.append(container.getErrMsg());
					return null;
				}
				list = (List<DokufDao>) container.getDtoList();
				for (DokufDao dao : list) {
					savedRecord = dao;
				}
			}
			
		}
		logger.info("savedRecord="+ReflectionToStringBuilder.toString(savedRecord));
		return savedRecord;
	}	
	
	/**
	 * 
	 * @param appUser
	 * @param model
	 */
	private void setCodeDropDownMgr(SystemaWebUser appUser, Map model){
		
		this.codeDropDownMgr.populateCodesHtmlDropDownsFromJsonString(urlCgiProxyService, this.trorDropDownListPopulationService, model, appUser, this.codeDropDownMgr.CODE_TYPE_MLAPKOD);
		this.codeDropDownMgr.populateCodesHtmlDropDownsFromJsonProductLandimporFraktbrev(this.urlCgiProxyService, this.trorDropDownListPopulationService, model, appUser);
		this.codeDropDownMgr.populateCodesHtmlDropDownsFromJsonEnhet(this.urlCgiProxyService, this.trorDropDownListPopulationService, model, appUser);
		
		//Sign / AVD
		//this.codeDropDownMgr.populateCodesHtmlDropDownsFromJsonSignature(this.urlCgiProxyService, this.trorDropDownListPopulationService, model, appUser);
		//this.codeDropDownMgr.populateCodesHtmlDropDownsFromJsonAvdelning(this.urlCgiProxyService, this.maintMainKodtaService, model, appUser);
		
		//this.codeDropDownMgr.populateCodesHtmlDropDownsFromJsonCountry(this.urlCgiProxyService, this.trorDropDownListPopulationService, model, appUser);
		//this.codeDropDownMgr.populateCodesHtmlDropDownsFromJsonIncoterms(this.urlCgiProxyService, this.trorDropDownListPopulationService, model, appUser);
		//this.codeDropDownMgr.populateCodesHtmlDropDownsFromJsonOppdragsType(this.urlCgiProxyService, this.trorDropDownListPopulationService, model, appUser);
		//this.codeDropDownMgr.populateCodesHtmlDropDownsFromJsonTransporttype(this.urlCgiProxyService, this.maintSadImportKodts4Service, model, appUser);
		//this.codeDropDownMgr.populateCodesHtmlDropDownsFromJsonCurrency(this.urlCgiProxyService, this.trorDropDownListPopulationService, model, appUser);
						
		
		
	}
	
	//SERVICES
	@Qualifier ("urlCgiProxyService")
	private UrlCgiProxyService urlCgiProxyService;
	@Autowired
	@Required
	public void setUrlCgiProxyService (UrlCgiProxyService value){ this.urlCgiProxyService = value; }
	public UrlCgiProxyService getUrlCgiProxyService(){ return this.urlCgiProxyService; }

	
	@Qualifier ("trorDropDownListPopulationService")
	private TrorDropDownListPopulationService trorDropDownListPopulationService;
	@Autowired
	@Required
	public void setTrorDropDownListPopulationService (TrorDropDownListPopulationService value){ this.trorDropDownListPopulationService = value; }
	public TrorDropDownListPopulationService getTrorDropDownListPopulationService(){ return this.trorDropDownListPopulationService; }
	
	@Qualifier ("maintNctsExportTrkodfService")
	private MaintNctsExportTrkodfService  maintNctsExportTrkodfService;
	@Autowired
	@Required
	public void setMaintNctsExportTrkodfService (MaintNctsExportTrkodfService value){ this.maintNctsExportTrkodfService = value; }
	public MaintNctsExportTrkodfService getMaintNctsExportTrkodfService(){ return this.maintNctsExportTrkodfService; }

	@Qualifier ("trorMainOrderHeaderLandimportService")
	private TrorMainOrderHeaderLandimportService trorMainOrderHeaderLandimportService;
	@Autowired
	@Required
	public void setTrorMainOrderHeaderLandimportService (TrorMainOrderHeaderLandimportService value){ this.trorMainOrderHeaderLandimportService = value; }
	public TrorMainOrderHeaderLandimportService getTrorMainOrderHeaderLandimportService(){ return this.trorMainOrderHeaderLandimportService; }
	
	
	@Qualifier ("maintMainKodtaService")
	private MaintMainKodtaService maintMainKodtaService;
	@Autowired
	@Required
	public void setMaintMainKodtaService (MaintMainKodtaService value){ this.maintMainKodtaService = value; }
	public MaintMainKodtaService getMaintMainKodtaService(){ return this.maintMainKodtaService; }
	
	@Qualifier ("maintMainCundfService")
	private MaintMainCundfService maintMainCundfService;
	@Autowired
	@Required
	public void setMaintMainCundfService (MaintMainCundfService value){ this.maintMainCundfService = value; }
	public MaintMainCundfService getMaintMainCundfService(){ return this.maintMainCundfService; }
	
	@Qualifier ("maintMainFirmService")
	private MaintMainFirmService maintMainFirmService;
	@Autowired
	@Required
	public void setMaintMainFirmService (MaintMainFirmService value){ this.maintMainFirmService = value; }
	public MaintMainFirmService getMaintMainFirmService(){ return this.maintMainFirmService; }
	
	
	
}

