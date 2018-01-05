package no.systema.tror.controller;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import org.apache.commons.lang3.builder.ReflectionToStringBuilder;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Required;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;

import no.systema.ebooking.model.jsonjackson.JsonMainOrderTypesNewRecord;
import no.systema.jservices.common.dao.DokufDao;
import no.systema.jservices.common.json.JsonDtoContainer;
import no.systema.jservices.common.json.JsonReader;
import no.systema.main.model.SystemaWebUser;
import no.systema.main.util.StringManager;
import no.systema.main.service.UrlCgiProxyService;
import no.systema.main.util.AppConstants;
import no.systema.main.util.JsonDebugger;
import no.systema.tror.model.jsonjackson.JsonTrorOrderHeaderContainer;
import no.systema.tror.model.jsonjackson.JsonTrorOrderHeaderRecord;
import no.systema.tror.service.html.dropdown.TrorDropDownListPopulationService;
import no.systema.tror.service.landimport.TrorMainOrderHeaderLandimportService;
import no.systema.tror.url.store.TrorUrlDataStore;
import no.systema.tror.util.RpgReturnResponseHandler;
import no.systema.tror.util.manager.CodeDropDownMgr;
import no.systema.tror.util.manager.LandImportExportManager;
import no.systema.z.main.maintenance.mapper.url.request.UrlRequestParameterMapper;
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
	/**
	 * 
	 * @param recordToValidate
	 * @param bindingResult
	 * @param session
	 * @param request
	 * @return
	 */
	@RequestMapping(value="tror_mainorderlandimport_freightbill_edit.do", method={RequestMethod.GET, RequestMethod.POST })
	public ModelAndView mainmaintenancecundf_vareimp_se_edit(@ModelAttribute ("record") DokufDao recordToValidate, BindingResult bindingResult, HttpSession session, HttpServletRequest request){
		ModelAndView successView = new ModelAndView("tror_mainorderlandimport_freightbill");
		SystemaWebUser appUser = (SystemaWebUser)session.getAttribute(AppConstants.SYSTEMA_WEB_USER_KEY);
		Map model = new HashMap();
		String action = request.getParameter("action");
		String updateId = request.getParameter("updateId");
		StringBuffer errMsg = new StringBuffer();
		JsonTrorOrderHeaderRecord headf = new JsonTrorOrderHeaderRecord();
		headf.setOwnEnhet1(request.getParameter("ownEnhet1"));
		headf.setOwnEnhet2(request.getParameter("ownEnhet2"));
		
		
		int dmlRetval = 0;
		DokufDao savedRecord = null;
		
		if (appUser == null) {
			return this.loginView;
		} else {
			
			if (MainMaintenanceConstants.ACTION_CREATE.equals(action)) {  //New
				this.adjustFields( recordToValidate,  headf);
				// Validate
				TrorOrderFraktbrevValidator validator = new TrorOrderFraktbrevValidator();
				validator.validate(recordToValidate, bindingResult);
				if (bindingResult.hasErrors()) {
					logger.info("[ERROR Validation] Record does not validate)");
					model.put(MainMaintenanceConstants.DOMAIN_RECORD, recordToValidate);
				} else {
					savedRecord = updateRecord(appUser, recordToValidate, MainMaintenanceConstants.MODE_ADD, errMsg);
					if (savedRecord == null) {
						logger.info("[ERROR Validation] Record does not validate)");
						model.put(MainMaintenanceConstants.ASPECT_ERROR_MESSAGE, errMsg.toString());
						model.put(MainMaintenanceConstants.DOMAIN_RECORD, recordToValidate);
					} else {
						DokufDao record = fetchRecord(appUser, recordToValidate.getDfavd(), recordToValidate.getDfopd(), recordToValidate.getDffbnr(), model);
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
						DokufDao record = fetchRecord(appUser, recordToValidate.getDfavd(), recordToValidate.getDfopd(), recordToValidate.getDffbnr(), model);
						model.put(MainMaintenanceConstants.DOMAIN_RECORD, record);
					}
				}
			} else { // Fetch
				logger.info("FETCH branch");
				DokufDao record = fetchRecord(appUser, recordToValidate.getDfavd(), recordToValidate.getDfopd(), recordToValidate.getDffbnr(), model);
				if(record!=null && strMgr.isNotNull(record.getDf1004())){
					model.put("action", MainMaintenanceConstants.ACTION_UPDATE);
					model.put(MainMaintenanceConstants.DOMAIN_RECORD, record);
				}else{
					//User will prepare the view for a future create-new fraktbrev. 
					model.put("action", MainMaintenanceConstants.ACTION_CREATE);
					//Here we prepare the form with default values from the "Oppdrag"
					JsonTrorOrderHeaderRecord orderHeader = this.getOrderHedfRecord(appUser, model, null, String.valueOf(recordToValidate.getDfavd()), String.valueOf(recordToValidate.getDfopd()) );
					this.handoverOppdragValuesToFraktbrev(recordToValidate, orderHeader);
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
	 * Set default values for the end-user 
	 * 
	 * @param recordToValidate
	 * @param orderHeader
	 */
	private void handoverOppdragValuesToFraktbrev(DokufDao recordToValidate, JsonTrorOrderHeaderRecord orderHeader){
		recordToValidate.setDffase(orderHeader.getHenas());
		recordToValidate.setDfnavm(orderHeader.getHenak());
		recordToValidate.setDfad1m(orderHeader.getHeadk1());
		recordToValidate.setDfad3m(orderHeader.getHeadk3());
		//TODO more ...!
		
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
	public JsonTrorOrderHeaderRecord getOrderHedfRecord(SystemaWebUser appUser, Map model, JsonMainOrderTypesNewRecord orderTypes, String heavd, String heopd ){
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
		String urlRequestParams = urlRequestParameterMapper.getUrlParameterValidString(record);
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
	
	
}

