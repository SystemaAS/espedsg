package no.systema.tror.controller;

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
import no.systema.transportdisp.model.jsonjackson.workflow.order.JsonTransportDispWorkflowSpecificOrderRecord;
import no.systema.transportdisp.model.jsonjackson.workflow.order.invoice.JsonTransportDispWorkflowSpecificOrderInvoiceContainer;
import no.systema.transportdisp.model.jsonjackson.workflow.order.invoice.JsonTransportDispWorkflowSpecificOrderInvoiceReadyMarkContainer;
import no.systema.transportdisp.model.jsonjackson.workflow.order.invoice.JsonTransportDispWorkflowSpecificOrderInvoiceRecord;
import no.systema.transportdisp.url.store.TransportDispUrlDataStore;
import no.systema.transportdisp.util.TransportDispConstants;
import no.systema.main.url.store.MainUrlDataStore;
import no.systema.main.util.AppConstants;
import no.systema.main.util.JsonDebugger;
import no.systema.main.util.MessageNoteManager;
import no.systema.main.util.NumberFormatterLocaleAware;
import no.systema.main.util.DateTimeManager;
import no.systema.main.util.StringManager;

import no.systema.main.util.io.FileContentRenderer;
import no.systema.main.model.SystemaWebUser;
import no.systema.main.model.jsonjackson.general.notisblock.JsonNotisblockContainer;


//eBooking
import no.systema.tror.url.store.TrorUrlDataStore;
import no.systema.tror.util.TrorConstants;
import no.systema.tror.util.RpgReturnResponseHandler;
import no.systema.tror.util.manager.CodeDropDownMgr;
import no.systema.tror.model.jsonjackson.JsonTrorOrderHeaderContainer;
import no.systema.tror.model.jsonjackson.order.invoice.JsonTrorOrderLandImportInvoiceContainer;
import no.systema.tror.model.jsonjackson.order.invoice.JsonTrorOrderLandImportInvoiceRecord;

//import no.systema.ebooking.model.jsonjackson.JsonMainOrderHeaderFraktbrevRecord;
//import no.systema.ebooking.model.jsonjackson.JsonMainOrderHeaderMessageNoteContainer;
//import no.systema.ebooking.model.jsonjackson.JsonMainOrderHeaderMessageNoteRecord;

import no.systema.tror.model.jsonjackson.JsonTrorOrderHeaderRecord;
import no.systema.ebooking.model.jsonjackson.JsonMainOrderTypesNewRecord;
//import no.systema.ebooking.model.jsonjackson.order.childwindow.JsonEbookingCustomerContainer;
//import no.systema.ebooking.model.jsonjackson.order.childwindow.JsonEbookingCustomerRecord;
import no.systema.ebooking.service.EbookingChildWindowService;
import no.systema.tror.service.html.dropdown.TrorDropDownListPopulationService;
import no.systema.tror.service.landimport.TrorMainOrderHeaderLandimportService;
import no.systema.tror.mapper.url.request.UrlRequestParameterMapper;
import no.systema.tror.validator.TrorOrderHeaderValidator;
import no.systema.tvinn.sad.z.maintenance.sadimport.service.gyldigekoder.MaintSadImportKodts4Service;
import no.systema.z.main.maintenance.service.MaintMainKodtaService;


/**
 * Tror - Order Header Controller 
 * 
 * @author oscardelatorre
 * @date Aug 28, 2017
 * 
 */

@Controller
@SessionAttributes(AppConstants.SYSTEMA_WEB_USER_KEY)
@Scope("session")
public class TrorMainOrderHeaderLandImportControllerInvoice {
	private static final JsonDebugger jsonDebugger = new JsonDebugger(1500);
	private static Logger logger = Logger.getLogger(TrorMainOrderHeaderLandImportControllerInvoice.class.getName());
	private ModelAndView loginView = new ModelAndView("login");
	private ApplicationContext context;
	private LoginValidator loginValidator = new LoginValidator();
	//
	private CodeDropDownMgr codeDropDownMgr = new CodeDropDownMgr();
	private NumberFormatterLocaleAware numberFormatter = new NumberFormatterLocaleAware();
	private StringManager strMgr = new StringManager();
	private DateTimeManager dateMgr = new DateTimeManager();
	//private ReflectionUrlStoreMgr reflectionUrlStoreMgr = new ReflectionUrlStoreMgr();
	@PostConstruct
	public void initIt() throws Exception {
		if("DEBUG".equals(AppConstants.LOG4J_LOGGER_LEVEL)){
			logger.setLevel(Level.DEBUG);
		}
	}
	private String INVOICE_ITEM_LINE_GROUP_TOTAL_RECORD_LEGEND = "TOTAL";
	private String INVOICE_MINUS_CHARACTER = "-";
	
	
	/**
	 * 
	 * @param session
	 * @param request
	 * @return
	 */
	@RequestMapping(value="Xtror_mainorderlandimport_invoice.do",  params="action=doInit", method={RequestMethod.GET, RequestMethod.POST })
	public ModelAndView doInit(@ModelAttribute ("record") JsonTrorOrderHeaderRecord recordToValidate, BindingResult bindingResult, HttpSession session, HttpServletRequest request){
		
		Map model = new HashMap();
		SystemaWebUser appUser = (SystemaWebUser)session.getAttribute(AppConstants.SYSTEMA_WEB_USER_KEY);
		//String messageFromContext = this.context.getMessage("user.label",new Object[0], request.getLocale());
		ModelAndView successView = new ModelAndView("tror_mainorderlandimport_invoice");
		logger.info("Method: doInit");
		//check user (should be in session already)
		if(appUser==null){
			return loginView;
			
		}else{
			String avdTmp = request.getParameter("heavd");
			String opdTmp = request.getParameter("heopd");
			model.put("heavd", avdTmp);
			model.put("heopd", opdTmp);
			
			//this.setDefaultValues(recordToValidate, appUser);
			
			//model.put(TrorConstants.DOMAIN_RECORD, recordToValidate);
			//this.setCodeDropDownMgr(appUser, model);
			
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
	@RequestMapping(value="tror_mainorderlandimport_invoice.do", method={RequestMethod.GET, RequestMethod.POST} )
	public ModelAndView doGetInvoice(@ModelAttribute ("record") JsonTrorOrderHeaderRecord recordToValidate, BindingResult bindingResult, HttpSession session, HttpServletRequest request){
		this.context = TdsAppContext.getApplicationContext();
		Map model = new HashMap();
		
		ModelAndView successView = new ModelAndView("tror_mainorderlandimport_invoice");
		SystemaWebUser appUser = this.loginValidator.getValidUser(session);
		String parentTrip = recordToValidate.getHepro();
		
		//check user (should be in session already)
		if(appUser==null){
			return loginView;
			
		}else{
			logger.info(Calendar.getInstance().getTime() + " CONTROLLER start - timestamp");
			
			if(recordToValidate.getHeavd()!=null && !"".equals(recordToValidate.getHeavd()) && 
				recordToValidate.getHeopd()!=null && !"".equals(recordToValidate.getHeopd())){
		    	final String BASE_URL = TrorUrlDataStore.TROR_BASE_FETCH_MAIN_ORDER_INVOICE_URL;
		    	//http://gw.systema.no/sycgip/TJGE25R.pgm?user=JOVO&avd=80&opd=201523&lin=&type=A
		    	
	    		//add URL-parameters
	    		StringBuffer urlRequestParams = new StringBuffer();
	    		urlRequestParams.append("user=" + appUser.getUser());
	    		urlRequestParams.append("&avd=" + recordToValidate.getHeavd());
	    		urlRequestParams.append("&opd=" + recordToValidate.getHeopd());
	    		urlRequestParams.append("&lin="); //always blank=all lines
	    		//I=Vis kun Inntektslinjer=kun linjer for utgående faktura vises (ikke kostnader  / ikke slettemerkedede)
	    		//O = Open = Vis kun ÅPNE inntekstlinjer = Linjer som ennå ikke er fakturert / kan endres.. 
	    		//A=Vis ALLE linjer (vis inntektslinjer uavhengig av status OG også kostnadslinjer...) 
	    		//urlRequestParams.append("&type=" + recordToValidate.getItemsType()); 
	    		urlRequestParams.append("&type=A");
	    		
	    		logger.info(Calendar.getInstance().getTime() + " CGI-start timestamp");
		    	logger.info("URL: " + BASE_URL);
		    	logger.info("URL PARAMS: " + urlRequestParams);
			    String jsonPayload = this.urlCgiProxyService.getJsonContent(BASE_URL, urlRequestParams.toString());
			    //Debug --> 
			  	//logger.debug(jsonDebugger.debugJsonPayloadWithLog4J(jsonPayload));
			  	logger.debug(this.jsonDebugger.debugJsonPayloadWithLog4J(jsonPayload));
	    		logger.info(Calendar.getInstance().getTime() +  " CGI-end timestamp");
	    		if(jsonPayload!=null){
	    			JsonTrorOrderLandImportInvoiceContainer container = this.trorMainOrderHeaderLandimportService.getOrderInvoiceContainer(jsonPayload);
	    			if(container!=null){
	    				//ready mark status
	    				//TODO ? JsonTransportDispWorkflowSpecificOrderInvoiceReadyMarkContainer readyMarkContainer = this.getReadyMark(appUser, recordToValidate.getHeavd(), recordToValidate.getHeopd());
	    				//TODO ? container.setReadyMarkStatus(readyMarkContainer.getStatus());
	    				//set domain object
	    				this.setDomainObjectsInView(model, container, session);
	    			}
	    		}
	    		
	    		
			}
			
			//populate drop downs
			this.setCodeDropDownMgr(appUser, model);
			this.setDropDownsFromFiles(model);
			
    		//--------------------------------------
			//Final successView with domain objects
			//--------------------------------------
			model.put("parentTrip", parentTrip);
			successView.addObject(TransportDispConstants.DOMAIN_MODEL , model);
	    	
    		logger.info(Calendar.getInstance().getTime() + " CONTROLLER end - timestamp");
		    return successView;
		}
	}
	/**
	 * 
	 * @param appUser
	 * @param model
	 */
	private void setCodeDropDownMgr(SystemaWebUser appUser, Map model){
		/*TODO ? this.codeDropDownMgr.populateCodesHtmlDropDownsFromJsonString(this.urlCgiProxyService, this.trorDropDownListPopulationService,
				model,appUser,CodeDropDownMgr.CODE_2_COUNTRY, null, null);
		this.codeDropDownMgr.populateHtmlDropDownsFromJsonStringGebyrCodes(this.urlCgiProxyService, this.trorDropDownListPopulationService, 
				model, appUser);
				*/
	}
	/**
	 * 
	 * @param model
	 */
	private void setDropDownsFromFiles(Map<String, Object> model){
		model.put(TrorConstants.RESOURCE_MODEL_KEY_CURRENCY_CODE_LIST, this.trorDropDownListPopulationService.getCurrencyList());
	}
	/**
	 * 
	 * @param model
	 * @param container
	 * @param session
	 */
	private void setDomainObjectsInView(Map model, JsonTrorOrderLandImportInvoiceContainer container, HttpSession session){
		List<JsonTrorOrderLandImportInvoiceRecord> list = new ArrayList<JsonTrorOrderLandImportInvoiceRecord>();
		//could be two options
		if(container.getInvoiceLineUpdate()!=null){
			for (JsonTrorOrderLandImportInvoiceRecord record: container.getInvoiceLineUpdate()){
				//DEBUG logger.info("A RECORD:" + record.getFask());
				list.add(record);
			}
		}else if(container.getInvoiceLines()!=null){
			for (JsonTrorOrderLandImportInvoiceRecord record: container.getInvoiceLines()){
				//DEBUG logger.info("A RECORD:" + record.getFask());
				list.add(record);
			}
		}
		//[1] Sort the list after: fask(code part (S,K,X,etc) and fafakt(inv.nr)
		Collections.sort(list, new JsonTrorOrderLandImportInvoiceRecord.OrderByCodeAndInvoiceNr());
		if(list!=null){
			logger.info("TripleAAA:" + list.size());
		}
		//[1.1] Group the list in sub-lists of related records
		Map listGroupsMap = this.setListGroups(list);
		//[2] Set totals in each group
		list = this.getListWithTotals(listGroupsMap);
		
		//always keep track of the total nr of item lines
		String nrOfItems = String.valueOf(list.size());
		container.setTotalNumberOfItemLines(nrOfItems);
		
		logger.info("putting on model...");
		model.put(TransportDispConstants.DOMAIN_CONTAINER, container);
		model.put(TransportDispConstants.DOMAIN_LIST, list);
		//put the objects in session ONLY for the validation errors routine in an UPDATE. Otherwise we do have to retrive th
		session.setAttribute(session.getId() + TransportDispConstants.DOMAIN_CONTAINER, container);
		session.setAttribute(session.getId() + TransportDispConstants.DOMAIN_LIST, list);
	}
	
	/**
	 * 
	 * @param listGroupsMap
	 * @return
	 */
	private List<JsonTrorOrderLandImportInvoiceRecord> getListWithTotals(Map<Integer, List<JsonTrorOrderLandImportInvoiceRecord>> listGroupsMap){
		List<JsonTrorOrderLandImportInvoiceRecord> listWithTotals = new ArrayList<JsonTrorOrderLandImportInvoiceRecord>();
		int TWO_DECIMALS = 2;
		boolean DECIMAL_THOUSAND_FORMAT = false;
		String LOCALE_NORWAY = "no";
		boolean isCostRecord = false;
		
		//Iterate through the Map of lists
		Iterator it = listGroupsMap.entrySet().iterator();
	    while (it.hasNext()) {
	        Map.Entry pair = (Map.Entry)it.next();
	        //logger.info(pair.getKey() + " = " + pair.getValue());
	        List<JsonTrorOrderLandImportInvoiceRecord> group = (List<JsonTrorOrderLandImportInvoiceRecord>)pair.getValue();
	        Double fablnTotal = 0.00D;
	        for (JsonTrorOrderLandImportInvoiceRecord groupRecord : group){
	        	if(groupRecord.getFabeln()!=null && !"".equals(groupRecord.getFabeln())){
	        		if(groupRecord.getFabeln().contains("-")){
	        			//negative sign on back-end (credit note)
	        			groupRecord.getFabeln().replace("-", "");
	        			//make it negative and update the total sum negatively
	        			fablnTotal += -1 * this.getDoubleField(groupRecord.getFabeln());
	        		}else{
	        			fablnTotal += this.getDoubleField(groupRecord.getFabeln());
	        		}
	        		if("K".equals(groupRecord.getFakda())){
	        			isCostRecord = true;
	        		}
	        	}else{
	        		if(this.INVOICE_ITEM_LINE_GROUP_TOTAL_RECORD_LEGEND.equals(groupRecord.getFaVT())){
	        			//add the total to this group
	        			groupRecord.setFabeln(this.numberFormatter.getString(fablnTotal, TWO_DECIMALS, DECIMAL_THOUSAND_FORMAT, LOCALE_NORWAY));
	        			//add the "-" character at the end of the string to comply to the cost-field formatting
	        			if(isCostRecord){ 
	        				groupRecord.setFabeln(groupRecord.getFabeln() + this.INVOICE_MINUS_CHARACTER);
	        				isCostRecord = false;
        				}
	        			fablnTotal = 0.00D;
	        		}else{
	        			fablnTotal += 0;
	        		}
	        	
	        	}
	        	listWithTotals.add(groupRecord);
	        }
	    }
		return listWithTotals; 
	}
	/**
	 * 
	 * @param value
	 * @return
	 */
	private Double getDoubleField(String value){
		Double retval = 0.00D;
		retval = this.numberFormatter.getDouble(this.getAbsoluteValue(value));
		return retval;
	}
	/**
	 * 
	 * @param value
	 * @return
	 */
	private String getAbsoluteValue(String value){
		String retval = value;
		int minus = value.indexOf(this.INVOICE_MINUS_CHARACTER);
		if(minus > -1){
			retval = value.replace(this.INVOICE_MINUS_CHARACTER, "");
			
		}
		
		return retval;
	}
	/**
	 * 
	 * @param list
	 * @return
	 */
	private Map<Integer,List<JsonTrorOrderLandImportInvoiceRecord>> setListGroups(List<JsonTrorOrderLandImportInvoiceRecord> list){
		Map<Integer,List<JsonTrorOrderLandImportInvoiceRecord>> map = new HashMap<Integer, List<JsonTrorOrderLandImportInvoiceRecord>>();
		List<JsonTrorOrderLandImportInvoiceRecord> newList = new ArrayList<JsonTrorOrderLandImportInvoiceRecord>();
		String previousCode = "";
		int index = 0;
		Integer mapIndex = 0;
		for (JsonTrorOrderLandImportInvoiceRecord record: list){
			//logger.info("Inside:" + record.getFabeln());
			if (index==0){
				previousCode = record.getFask() + record.getFafakt();
				newList.add(record);
				map.put(mapIndex, newList);
			}else{
				if (previousCode.equals(record.getFask() + record.getFafakt())){
					newList.add(record);
					//add the total record for the last record
					if(index + 1==list.size()){
						//add the total record
						newList.add(this.addTotalRecord());
						map.put(mapIndex, newList);
					}
				}else{
					//add the total record
					newList.add(this.addTotalRecord());
					//logger.info("populating map with list:" + newList.size());
					map.put(mapIndex, newList);
					//init new list with this very current record
					mapIndex++;
					newList = new ArrayList<JsonTrorOrderLandImportInvoiceRecord>();
					newList.add(record);
					map.put(mapIndex, newList);
				}
				previousCode = record.getFask() + record.getFafakt();
			}
			index++;
		}
		return map; 
	}
	/**
	 * 
	 * @return
	 */
	private JsonTrorOrderLandImportInvoiceRecord addTotalRecord(){
		JsonTrorOrderLandImportInvoiceRecord record = new JsonTrorOrderLandImportInvoiceRecord();
		record.setFaVT(this.INVOICE_ITEM_LINE_GROUP_TOTAL_RECORD_LEGEND);
		return record;
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
	
	@Qualifier ("ebookingChildWindowService")
	private EbookingChildWindowService ebookingChildWindowService;
	@Autowired
	@Required
	public void setEbookingChildWindowService (EbookingChildWindowService value){ this.ebookingChildWindowService = value; }
	public EbookingChildWindowService getEbookingChildWindowService(){ return this.ebookingChildWindowService; }
	
}

