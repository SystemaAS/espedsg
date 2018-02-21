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


import no.systema.jservices.common.dao.DokefDao;
import no.systema.jservices.common.dao.DokefimDao;

import no.systema.jservices.common.json.JsonDtoContainer;
import no.systema.jservices.common.json.JsonReader;

import no.systema.main.model.SystemaWebUser;
import no.systema.main.util.StringManager;
import no.systema.main.validator.UserValidator;

import no.systema.main.service.UrlCgiProxyService;
import no.systema.main.util.AppConstants;
import no.systema.main.util.JsonDebugger;
import no.systema.main.util.MessageNoteManager;
import no.systema.tror.model.jsonjackson.JsonTrorOrderHeaderRecord;
import no.systema.tror.service.html.dropdown.TrorDropDownListPopulationService;
import no.systema.tror.service.flyimport.TrorMainOrderHeaderFlyimportService;
import no.systema.tror.url.store.TrorUrlDataStore;
import no.systema.tror.util.RpgReturnResponseHandler;
import no.systema.tror.util.TrorConstants;
import no.systema.tror.util.manager.CodeDropDownMgr;
import no.systema.tror.util.manager.FlyImportExportManager;
import no.systema.tror.util.manager.OrderContactInformationManager;
import no.systema.tror.util.manager.FreightBillMessageNoteManager;
import no.systema.tror.mapper.url.request.UrlRequestParameterMapper;
import no.systema.tror.model.CundfAuxObject;
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
import no.systema.tror.validator.TrorOrderFlyFraktbrevImpValidator;
import no.systema.tvinn.sad.z.maintenance.nctsexport.service.MaintNctsExportTrkodfService;

/**
 * Tror - Air Freight Bill Controller (flyfraktbrev) 
 * 
 * @author oscardelatorre
 * @date Feb 19, 2018
 * 
 */

@Controller
@SessionAttributes(AppConstants.SYSTEMA_WEB_USER_KEY)
@Scope("session")
public class TrorMainOrderHeaderFlyControllerAirFreightBill {
	private static Logger logger = Logger.getLogger(TrorMainOrderHeaderFlyControllerAirFreightBill.class.getName());
	private ModelAndView loginView = new ModelAndView("login");
	private static final JsonDebugger jsonDebugger = new JsonDebugger(1500);
	private UrlRequestParameterMapper urlRequestParameterMapper = new UrlRequestParameterMapper();
	private CodeDropDownMgr codeDropDownMgr = new CodeDropDownMgr();
	private StringManager strMgr = new StringManager();
	private RpgReturnResponseHandler rpgReturnResponseHandler = new RpgReturnResponseHandler();
	private FlyImportExportManager flyMgr = new FlyImportExportManager();
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
	@RequestMapping(value="tror_mainorderfly_airfreightbill_gate.do", method={RequestMethod.GET, RequestMethod.POST })
	public ModelAndView tror_mainorderland_airfreightbill_gate(@ModelAttribute ("record") DokefimDao recordToValidate, BindingResult bindingResult, HttpSession session, HttpServletRequest request){
		ModelAndView successView = null; 
		SystemaWebUser appUser = (SystemaWebUser)session.getAttribute(AppConstants.SYSTEMA_WEB_USER_KEY);
		Map model = new HashMap();
		String sign = request.getParameter("sign");
		
		
		if (appUser == null) {
			return this.loginView;
		} else {
			List<DokefimDao> list = this.fetchFlyImportFraktbrevList(model, appUser, recordToValidate.getImavd(), recordToValidate.getImopd(), recordToValidate.getImlop());
			if(list!=null && list.size()>1){
				successView = new ModelAndView("tror_mainorderfly_airfreightbill_list");
				model.put("list", list);
				
				successView.addObject(MainMaintenanceConstants.DOMAIN_MODEL, model);
			}else{
				//this will send the request with an implicit action of doFetch
				successView = new ModelAndView("redirect:tror_mainorderfly_airfreightbill_edit.do?" + "&dfavd=" + recordToValidate.getImavd() + "&sign=" + sign + "&dfopd=" + recordToValidate.getImopd());
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
	@RequestMapping(value="tror_mainorderfly_airfreightbill_list_edit.do", method={RequestMethod.GET, RequestMethod.POST })
	public ModelAndView tror_mainorderland_freightbill_list_edit(@ModelAttribute ("record") DokefimDao recordToValidate, BindingResult bindingResult, HttpSession session, HttpServletRequest request){
		SystemaWebUser appUser = (SystemaWebUser)session.getAttribute(AppConstants.SYSTEMA_WEB_USER_KEY);
		Map model = new HashMap();
		String action = request.getParameter("action");
		String updateId = request.getParameter("updateId");
		String sign = request.getParameter("sign");
		ModelAndView successView = new ModelAndView("redirect:tror_mainorderfly_airfreightbill_gate.do?" + "&dfavd=" + recordToValidate.getImavd() + "&sign=" + sign + "&dfopd=" + recordToValidate.getImopd());
		
		StringBuffer errMsg = new StringBuffer();
		JsonTrorOrderHeaderRecord headf = new JsonTrorOrderHeaderRecord();
		
		int dmlRetval = 0;
		DokefimDao savedRecord = null;
		
		if (appUser == null) {
			return this.loginView;
		} else {
			
			if (MainMaintenanceConstants.ACTION_CREATE.equals(action)) {  //New
				logger.info("Inside - CREATE NEW");
				//TODO --> COVI this.adjustFields( recordToValidate,  headf);
				// Validate
				TrorOrderFlyFraktbrevImpValidator validator = new TrorOrderFlyFraktbrevImpValidator();
				validator.validate(recordToValidate, bindingResult);
				if (bindingResult.hasErrors()) {
					logger.info("[ERROR Validation] Record does not validate)");
					model.put(MainMaintenanceConstants.DOMAIN_RECORD, recordToValidate);
				} else {
					//TODO -->COVI recordToValidate.setDfsg(sign);
					//TODO -->COVI savedRecord = updateRecord(appUser, recordToValidate, MainMaintenanceConstants.MODE_ADD, errMsg);
					
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
				//TODO -->COVI savedRecord = updateRecord(appUser, recordToValidate, MainMaintenanceConstants.MODE_DELETE, errMsg);
				
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
	@RequestMapping(value="tror_mainorderfly_airfreightbill_imp_edit.do", method={RequestMethod.GET, RequestMethod.POST })
	public ModelAndView tror_mainorderland_freightbill_edit(@ModelAttribute ("record") DokefimDao recordToValidate, BindingResult bindingResult, HttpSession session, HttpServletRequest request){
		ModelAndView successView = new ModelAndView("tror_mainorderfly_airfreightbill_imp");
		SystemaWebUser appUser = (SystemaWebUser)session.getAttribute(AppConstants.SYSTEMA_WEB_USER_KEY);
		Map<String,Object> model = new HashMap<String,Object>();
		String action = request.getParameter("action");
		String updateId = request.getParameter("updateId");
		StringBuffer errMsg = new StringBuffer();
		
		DokefimDao savedRecord = null;
		
		if (appUser == null) {
			return this.loginView;
		} else {
			
			if (MainMaintenanceConstants.ACTION_CREATE.equals(action)) {  //New
				logger.info("Inside - CREATE NEW");
				//this.adjustFields( recordToValidate,  headf);
				// Validate
				TrorOrderFlyFraktbrevImpValidator validator = new TrorOrderFlyFraktbrevImpValidator();
				validator.validate(recordToValidate, bindingResult);
				if (bindingResult.hasErrors()) {
					logger.info("[ERROR Validation] Record does not validate)");
					model.put("action", MainMaintenanceConstants.ACTION_CREATE);
					model.put(MainMaintenanceConstants.DOMAIN_RECORD, recordToValidate);
				} else {
					/*
					savedRecord = updateRecord(appUser, recordToValidate, MainMaintenanceConstants.MODE_ADD, errMsg);
					if (savedRecord == null) {
						logger.info("[ERROR Validation] Record does not validate)");
						model.put(MainMaintenanceConstants.ASPECT_ERROR_MESSAGE, errMsg.toString());
						model.put("action", MainMaintenanceConstants.ACTION_CREATE);
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
						this.fetchMessageNotes(model, appUser, record);
						//fetch contact information
						this.fetchContactInformation(appUser, record, model, contactInfoMap);
						
						model.put("action", MainMaintenanceConstants.ACTION_UPDATE);
						model.put(MainMaintenanceConstants.DOMAIN_RECORD, record);
					}
					*/
				}

			} else if (MainMaintenanceConstants.ACTION_UPDATE.equals(action)) { //Update
				logger.info("Inside - UPDATE...");
				//Validate
				TrorOrderFlyFraktbrevImpValidator validator = new TrorOrderFlyFraktbrevImpValidator();
				validator.validate(recordToValidate, bindingResult);
				if (bindingResult.hasErrors()) {
					logger.info("[ERROR Validation] Record does not validate)");
					model.put("action", MainMaintenanceConstants.ACTION_UPDATE);
					model.put(MainMaintenanceConstants.DOMAIN_RECORD, recordToValidate);
					
				} else {
					/*
					savedRecord = updateRecord(appUser, recordToValidate, MainMaintenanceConstants.MODE_UPDATE, errMsg);
					if (savedRecord == null) {
						logger.info("[ERROR Validation] Record does not validate)");
						model.put(MainMaintenanceConstants.ASPECT_ERROR_MESSAGE, errMsg.toString());
						model.put(MainMaintenanceConstants.DOMAIN_RECORD, recordToValidate);
					} else {
						
						//get record now (refreshed)
						DokefimDao record = fetchRecord(appUser, recordToValidate.getImavd(), recordToValidate.getImopd(), recordToValidate.getImlop(), model);
						
						model.put("action", MainMaintenanceConstants.ACTION_UPDATE);
						model.put(MainMaintenanceConstants.DOMAIN_RECORD, record);
					}
					*/
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
				DokefimDao recordDokefimDao = this.fetchRecord(model, appUser, recordToValidate.getImavd(), recordToValidate.getImopd(), recordToValidate.getImlop());
				
				if(recordDokefimDao!=null && recordDokefimDao.getImopd()>0){
					//get invoice data (currency & amount... 
					//12.Jan.2018: TODO--> after meeting (CB,JOVO,OT)a lot of issues CRUD must be resolved before we allow the end-user to input these 2 fields on GUI.
					model.put("action", MainMaintenanceConstants.ACTION_UPDATE);
					model.put(MainMaintenanceConstants.DOMAIN_RECORD, recordDokefimDao);
				}else{
					//User will prepare the view for a future create-new fraktbrev. 
					model.put("action", MainMaintenanceConstants.ACTION_CREATE);
					//Here we prepare the form with default values from the "Oppdrag"
					model.put(MainMaintenanceConstants.DOMAIN_RECORD, recordToValidate);
				}
				
			}
			//get dropdowns
			//this.setCodeDropDownMgr(appUser, model);
			
		
			//model.put("dfavd", recordToValidate.getDfavd());
			//model.put("dfopd", recordToValidate.getDfopd());
			//model.put("dffbnr", recordToValidate.getDffbnr());
			successView.addObject(MainMaintenanceConstants.DOMAIN_MODEL, model);
			
			return successView;		
		}
		
	}
	
	
	/**
	 * 
	 * @param appUser
	 * @param dfavd
	 * @param dfopd
	 * @param dffbnr
	 * @return
	 */
	
	private DokefimDao fetchRecord(Map model, SystemaWebUser appUser, int imavd, int imopd, int imlop) {
		DokefimDao dao = null;
		List<DokefimDao> list = this.fetchFlyImportFraktbrevList(model, appUser, imavd, imopd, imlop);
		for (DokefimDao record : list ){
			dao = record;
		}
		
		return dao;
	
	}
	
	/**
	 * 
	 * @param model
	 * @param appUser
	 * @param imavd
	 * @param imopd
	 * @param imlop
	 * @return
	 */
	private List<DokefimDao> fetchFlyImportFraktbrevList(Map model, SystemaWebUser appUser, int imavd, int imopd, int imlop) {
		List<DokefimDao> retval = new ArrayList<DokefimDao>();
		
		JsonReader<JsonDtoContainer<DokefimDao>> jsonReader = new JsonReader<JsonDtoContainer<DokefimDao>>();
		jsonReader.set(new JsonDtoContainer<DokefimDao>());
		final String BASE_URL = TrorUrlDataStore.TROR_BASE_FETCH_DOKEFIM_URL;
		StringBuilder urlRequestParams = new StringBuilder();
		urlRequestParams.append("user=" + appUser.getUser());
		urlRequestParams.append("&imavd=" + imavd);
		urlRequestParams.append("&imopd=" + imopd);
		if(imlop>0){
			urlRequestParams.append("&imlop=" + imlop);
		}
		
		logger.info("URL: " + BASE_URL);
		logger.info("PARAMS: " + urlRequestParams.toString());
		String jsonPayload = urlCgiProxyService.getJsonContent(BASE_URL, urlRequestParams.toString());
		logger.info("jsonPayload=" + jsonPayload);
		DokefimDao record = null;
		JsonDtoContainer<DokefimDao> container = (JsonDtoContainer<DokefimDao>) jsonReader.get(jsonPayload);
		if (container != null) {
			retval = container.getDtoList();
			for(DokefimDao dao : container.getDtoList()){
				this.getCustomerInfo(appUser, String.valueOf(dao.getHekns()), model );
			}
		}
		return retval;
	
	}
	/**
	 * 
	 * @param appUser
	 * @param kundnr
	 */
	private void getCustomerInfo(SystemaWebUser appUser, String kundnr, Map model ){
		//-------------------------------------------
		//Now get all info of the specific kund nr.
		//-------------------------------------------
		String BASE_URL = MaintenanceMainUrlDataStore.MAINTENANCE_MAIN_BASE_SYCUNDFR_GET_LIST_URL;
		StringBuilder urlRequestParams = new StringBuilder();
		urlRequestParams.append("user=" + appUser.getUser());
		if (kundnr != null ){
			urlRequestParams.append("&kundnr=" + kundnr);
			if(appUser.getCompanyCode() != null) {
				urlRequestParams.append("&firma=" + appUser.getCompanyCode());
			}
			//
			logger.info("URL: " + BASE_URL);
    		logger.info("PARAMS: " + urlRequestParams.toString());
    		String jsonPayload = this.urlCgiProxyService.getJsonContent(BASE_URL, urlRequestParams.toString());
    		Collection<JsonMaintMainCundfRecord> cundfList = new ArrayList<JsonMaintMainCundfRecord>();
    		if (jsonPayload != null) {
    			JsonMaintMainCundfContainer container = this.maintMainCundfService.getList(jsonPayload);
    			if (container != null) {
    				
    				for(JsonMaintMainCundfRecord  record : container.getList()){
    					//must be an integer key since DocefimDao.hekns = int
    					model.put(Integer.valueOf(kundnr), record.getKnavn());
    					break;
    				}
   
    			}
    		}
			
		}
	}
	/**
	 * 
	 * @param record
	 * @param model
	 */
	/*
	private void setSpecialValuesForPresentation(SystemaWebUser appUser, DokufDao record, Map model ){
		String UNITOFMEASURE_1 = "uom1";
		String UNITOFMEASURE_1_LENGTH = "uom1Length";
		
		
		String UOM_SEPARATOR = " ";
		if(strMgr.isNotNull(record.getDfvs())){
			int index = record.getDfvs().indexOf(UOM_SEPARATOR);
			if(index>=0){
				String uom = record.getDfvs().substring(0,index);
				//Check if uom is valid
				if(this.flyMgr.findUnitOfMeasure(this.urlCgiProxyService, this.maintNctsExportTrkodfService, appUser, uom)){
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
		
	}*/
	
	
	/**
	 * 
	 * @param appUser
	 * @param record
	 * @param mode
	 * @param errMsg
	 * @return
	 */
	/*
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
	*/
	
	/**
	 * 
	 * @param appUser
	 * @param model
	 */
	/*
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
	*/
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

	
	@Qualifier ("trorMainOrderHeaderFlyimportService")
	private TrorMainOrderHeaderFlyimportService trorMainOrderHeaderFlyimportService;
	@Autowired
	@Required
	public void setTrorMainOrderHeaderFlyimportService (TrorMainOrderHeaderFlyimportService value){ this.trorMainOrderHeaderFlyimportService = value; }
	public TrorMainOrderHeaderFlyimportService getTrorMainOrderHeaderFlyimportService(){ return this.trorMainOrderHeaderFlyimportService; }
	
	
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

