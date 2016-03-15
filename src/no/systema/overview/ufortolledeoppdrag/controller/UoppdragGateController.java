package no.systema.overview.ufortolledeoppdrag.controller;

import java.io.File;
import java.util.*;

import org.apache.log4j.Logger;
import org.springframework.validation.BindingResult;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.ServletRequestDataBinder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Required;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

//import no.systema.tds.service.MainHdTopicService;
import no.systema.main.service.UrlCgiProxyService;
import no.systema.overview.ufortolledeoppdrag.url.store.UrlDataStore;
import no.systema.overview.ufortolledeoppdrag.model.jsonjackson.JsonTopicContainer;
import no.systema.overview.ufortolledeoppdrag.model.jsonjackson.JsonTopicRecord;
import no.systema.overview.ufortolledeoppdrag.model.jsonjackson.JsonTopicRecordAvdGroup;
import no.systema.overview.ufortolledeoppdrag.model.jsonjackson.JsonTopicRecordDiagramChart;
import no.systema.overview.ufortolledeoppdrag.service.UoppdragService;
import no.systema.overview.ufortolledeoppdrag.service.StandardFallbackChartListFor3DBarService;

import no.systema.overview.ufortolledeoppdrag.util.UoppdragConstants;
import no.systema.overview.ufortolledeoppdrag.util.jfreechart.GeneralDistributionChart;
import no.systema.overview.ufortolledeoppdrag.util.jfreechart.manager.IJFreeChartDimension;
import no.systema.overview.ufortolledeoppdrag.util.jfreechart.manager.JFreeChartDynamicBarDimensionMgr;
import no.systema.overview.ufortolledeoppdrag.util.manager.SearchFilterMgr;
import no.systema.overview.ufortolledeoppdrag.model.DiagramColorChartMetaData;
import no.systema.overview.ufortolledeoppdrag.filter.SearchFilterGateChart;

import no.systema.overview.util.io.FileManager;


//application imports
import no.systema.main.model.SystemaWebUser;
import no.systema.main.util.AppConstants;
import no.systema.main.util.JsonDebugger;
//models
import no.systema.main.util.io.TextFileReaderService;


/**
 * Gateway to the Ufortollede Oppdrag Application
 * 
 * The user will be prompted with a general distribution Bar chart and will go forward from there...
 * 
 * 
 * @author oscardelatorre
 * @date Aug 27, 2013
 * 
 */

@Controller
public class UoppdragGateController {
	private final String DROP_DOWN_KEY_SIGN = "hsSign";
	private final String DROP_DOWN_KEY_TARIFFOR = "hsTariffor";
	private final String DROP_DOWN_KEY_AVD = "hsAvd";
	private final String DROP_DOWN_KEY_AVD_AVDNAVN = "hsAvdAvdNavn";
	private final String DROP_DOWN_KEY_AVD_GROUP_LIST = "avdGroupsList";
	
	private final String DROP_DOWN_KEY_TOLLAGERKOD = "hsTollagerkod";
	private final String DROP_DOWN_KEY_TOLLAGERKOD_SIZE = "hsTollagerkodSize";
	private final String DROP_DOWN_KEY_TOLLAGERDELKOD = "hsTollagerdelkod";
	private final String DROP_DOWN_KEY_TOLLAGERDELKOD_SIZE = "hsTollagerdelkodSize";
	
	
	
	private static final Logger logger = Logger.getLogger(UoppdragGateController.class.getName());
	private ModelAndView loginView = new ModelAndView("login");
	private StandardFallbackChartListFor3DBarService standardFallbackChartListFor3DBarService = new StandardFallbackChartListFor3DBarService();
	private JsonDebugger jsonDebugger = new JsonDebugger();
	//
	
	/**
	 * This method fetches all data from the back-end (deep refresh)
	 *  
	 * @param user
	 * @param result
	 * @param request
	 * @return
	 */
	@RequestMapping(value="uoppdraggate.do", method={RequestMethod.GET, RequestMethod.POST})
	public ModelAndView uoppdraggate(HttpSession session, HttpServletRequest request){
		logger.info("[INFO] Inside uoppdraggate...");
		
		ModelAndView successView = new ModelAndView("uoppdraggate");
		SystemaWebUser appUser = (SystemaWebUser)session.getAttribute(AppConstants.SYSTEMA_WEB_USER_KEY);
		
		String sessionId = session.getId();
		Map model = new HashMap();
		
		//get filter if applicable otherwise remove it since this is a refresh
		SearchFilterGateChart searchFilterGateChart  = null;
		String action = request.getParameter("action");
		String deepSubmit = request.getParameter("deepSubmit");
		String chartTickerInterval = request.getParameter("chartTickerInterval");
		
		//special session variable in order to be able to refresh the chart according to drop-down id="autoRefresh"
		session.setAttribute("chartTickerInterval_SESSION", "-99"); //init
		if(chartTickerInterval!=null && !"".equals(chartTickerInterval)){
			session.setAttribute("chartTickerInterval_SESSION", chartTickerInterval);
		}
		
		
		JsonTopicContainer jsonTopicContainer = null;
    	
		if("doBack".equals(action)){
			//this is usually a situation when the user returns to the main chart without refreshing (in order to maintain the search conditions once entered)
			searchFilterGateChart  = (SearchFilterGateChart)session.getAttribute(sessionId + UoppdragConstants.DOMAIN_SEARCH_FILTER_GATE_CHAR);
		}else{
			//populate filter
			searchFilterGateChart  = new SearchFilterGateChart();
			ServletRequestDataBinder binder = new ServletRequestDataBinder(searchFilterGateChart);
            binder.bind(request);
			
		}
		
		if(appUser==null){
			return this.loginView;
		}else{
			String jsonPayload = null;
			//deep submit means no AS400 jsonPayload. 
			if(deepSubmit!=null){
				//init relevant variables on session 
				session.removeAttribute(sessionId + UoppdragConstants.SESSION_UOPP_JSON_CONTAINER_GRAPH);
				session.removeAttribute(sessionId + UoppdragConstants.SESSION_UOPP_SUBSET_LIST);
				
				//--------------------------------------
				//EXECUTE the FETCH (RPG program) here
	    			//--------------------------------------
				String BASE_URL = UrlDataStore.OVERVIEW_UFORTOLLEDE_OPPDRAG_MAINLIST_URL;
				//url params
				String urlRequestParamsKeys = this.getRequestUrlKeyParameters(appUser);
				//for debug purposes in GUI
				session.setAttribute(UoppdragConstants.ACTIVE_URL_RPG, BASE_URL  + "==>params: " + urlRequestParamsKeys.toString()); 
				
				logger.info(Calendar.getInstance().getTime() + " CGI-start timestamp");
			    	logger.info("URL: " + BASE_URL);
			    	logger.info("URL PARAMS: " + urlRequestParamsKeys);
			    	jsonPayload = this.urlCgiProxyService.getJsonContent(BASE_URL, urlRequestParamsKeys);
			}else{
				//NO back-end involved
				//get filter from session in order to present values (if needed)
				//SearchFilterGateChart searchFilterGateChart  = (SearchFilterGateChart)session.getAttribute(sessionId + UoppdragConstants.DOMAIN_SEARCH_FILTER_GATE_CHAR);
				logger.info("[INFO] getting jsonPayload from JsonTopicContainer.getJsonPayload()");
				jsonTopicContainer = (JsonTopicContainer)session.getAttribute(sessionId + UoppdragConstants.SESSION_UOPP_JSON_CONTAINER_GRAPH);
				jsonPayload = jsonTopicContainer.getJsonPayload();
			}
		    	
			if(jsonPayload!=null){
		    		try{
		    			//Debug --> 
				    	this.jsonDebugger.debugJsonPayload(jsonPayload);
				    	
				    	jsonTopicContainer = this.uoppdragService.getContainer(jsonPayload);
		    			logger.info(Calendar.getInstance().getTime() +  " CGI-end timestamp");
				    	
		    			if(jsonTopicContainer!=null){
		    				jsonTopicContainer.setJsonPayload(jsonPayload);
			    			
		    				Collection<JsonTopicRecord> ufortList = jsonTopicContainer.getUfortList();
		    				Collection<JsonTopicRecordDiagramChart> chartListRaw = jsonTopicContainer.getChartList();
		    				
		    				//Check if the chartList has been configured in the user profile. If not, use the standard implementation
		    				if(chartListRaw!=null && chartListRaw.size()>0){
		    					//nothing since the user profile has a chart configuration defined (AS400)
		    				}else{
		    					//get standard fall-back implementation (from file JSON string)
		    					chartListRaw = standardFallbackChartListFor3DBarService.getStdImplementationFromTextFile(this.uoppdragService);
		    					jsonTopicContainer.setChartList(chartListRaw);
		    				}
		    				//fill chart with categories (since JSON does not have this required field)
		    				int categoryId = 0;
		    				Collection<JsonTopicRecordDiagramChart> chartList = new ArrayList<JsonTopicRecordDiagramChart>();
		    				for (JsonTopicRecordDiagramChart record : jsonTopicContainer.getChartList()){
		    					record.setCategoryId(categoryId);
		    					chartList.add(record);
		    					categoryId++;
		    				}
		    				jsonTopicContainer.setChartList(chartList);

		    				//At this point we now have a charList with its category Id per category (color chart)
		    				this.populateSearchFilterDropDownsForDatasetCoupledValues(jsonTopicContainer , model);
		    				    
		    				//------------------------------
			    			//START search filter conditions
			    			//------------------------------
			    			//if the user chose a filter condition we must then filter the complete list in order to have the main set based on the filter conditions
			    			if(this.filterIsPopulated(searchFilterGateChart)){
			    				Collection<JsonTopicRecord> matchedList = new ArrayList<JsonTopicRecord>();
			    				
			    				Set<Map.Entry<String, String>> setOfEntries = searchFilterGateChart.getPopulatedFields().entrySet();
			    				if(setOfEntries.size()>0){
			    					//logger.info("################## setOfEntries: " + setOfEntries.size());
			    					//logger.info("SUBSET-list-Filtered (size): " + ufortList.size());
			    					matchedList = new SearchFilterMgr().filterUoppragDataset(searchFilterGateChart, ufortList);
			    				}else{
			    					logger.info("XXXXXXXXXXXXXXXXXX setOfEntries = 0");
				    				logger.info("SUBSET-list (size): " + ufortList.size());
				    				matchedList = ufortList;
			    				}
			    				ufortList = matchedList;
			    				model.put(UoppdragConstants.DOMAIN_SEARCH_FILTER_GATE_CHAR, searchFilterGateChart);
			    			}
			    			//------------------------------
			    			//END search filter conditions
			    			//------------------------------
			    			//Debug
			    			//this.debugLagerKode(ufortList);
			    			
			    			//put the graph and the JSON container in session since we will use the info until a refresh (in another user activity)
			    			//from this point forward we will be getting the dataset from the session until we return to this method (refresh)
			    			jsonTopicContainer.setUfortList(ufortList); //refresh the list in case some filter values were applicable
		    				session.setAttribute(sessionId + UoppdragConstants.SESSION_UOPP_JSON_CONTAINER_GRAPH, jsonTopicContainer );
		    				//save the searchFilter object as well for use until next refresh
		    				session.setAttribute(sessionId + UoppdragConstants.DOMAIN_SEARCH_FILTER_GATE_CHAR, searchFilterGateChart);
			    			
		    				//----------------------------------
		    				//START build the chart dynamically
		    				//----------------------------------
			    			//IJFreeChartDimension jfreeChartDimensionMgr = new JFreeChartStandardBarDimensionMgr();
			    			IJFreeChartDimension jfreeChartDimensionMgr = new JFreeChartDynamicBarDimensionMgr();
		    				jfreeChartDimensionMgr.buildChart(ufortList, chartList);
			    			model.put(UoppdragConstants.DOMAIN_JFREE_CHART_MANAGER, jfreeChartDimensionMgr);
			    			model.put(UoppdragConstants.DOMAIN_LIST, ufortList);
			    			model.put(UoppdragConstants.DOMAIN_UFORT_LIST_SIZE, ufortList.size());
			    			model.put(UoppdragConstants.DOMAIN_CHART_CATEGORIES_LIST, chartList);
			    			
			    			
		    				//Delete the previous chart file
		    				new FileManager().deleteOldChartFile((File)session.getAttribute(sessionId));
		    				//(1)Start now with the new chart (for this request)
		    				GeneralDistributionChart jfreeChart = new GeneralDistributionChart();
		    				String targetDirectory = request.getSession().getServletContext().getRealPath(FileManager.JFREE_CHARTS_ROOT_DIRECTORY);
		    	            //(2) set target File graphFileName = this.getGraphFileName(targetDirectory);
		    				jfreeChart.setGraphFileName(targetDirectory);
		    				logger.info("[INFO] " + Calendar.getInstance().getTime() +  " START for producing jFreeChart");
					    	//(3) produce the chart and store the file name in session in order to delete it in the next iteration
		    				//jfreeChart.produceBarChartJPEG_DynamicDays(jfreeChartDimensionMgr);
		    				jfreeChart.produceBarChartJPEG_DynamicDays(jfreeChartDimensionMgr);
		    				logger.info("[INFO] " + Calendar.getInstance().getTime() +  " END for producing jFreeChart");
		    				//-------------------------------
		    				//END build the chart dynamically
		    				//-------------------------------
			    			
		    				//put the chart available for view and in session
			            successView.addObject("jfreeChartFile",jfreeChart.getGraphFileName().getName());  
			            session.setAttribute(sessionId, jfreeChart.getGraphFileName());
	    					
			            //Debug
			            /*for(JsonTopicRecordAvdGroup record : jsonTopicContainer.getAvdGroups()){
			            		logger.info("DEBUG getAvdGroups: " + record.getAvdList());
			            		
			            }*/
			            
			            //put the final model (other than the chart) available for view
			            successView.addObject(UoppdragConstants.DOMAIN_MODEL, model);
		    			}else{
		    				session.setAttribute(AppConstants.ASPECT_ERROR_MESSAGE, "[ERROR fatal] jsonContainer is NULL. Check logs...");
		    			}
		    			
		    		}catch(Exception e){
		    			e.printStackTrace();
		    			session.setAttribute(AppConstants.ASPECT_ERROR_MESSAGE, "[ERROR fatal] jsonPayload is NULL. Check logs...");
		    		}
		    	}
			
		}
		
		return successView;
	}
	
	/**
	 * 
	 * @param list
	 */
	private void debugLagerKode (Collection<JsonTopicRecord> list){
		Set hashSet = new HashSet();
		
		for (JsonTopicRecord record : list){
			if(record.getTollagerkod()!=null && !"".equals(record.getTollagerkod())){
				hashSet.add(record.getTollagerkod());
				logger.info("TOLLAGER - raw - LIST (A):" + record.getTollagerkod() );
				
			}
		}
		
		for(Iterator iter = hashSet.iterator();iter.hasNext();){
			String value = (String)iter.next();
			if(value!=null && !"".equals(value)){
				logger.info("TOLLAGER - final - COMBO LIST (B):" + value);
			}
		}
	}
	
	/**
	 * 
	 * @param searchFilterGateChart
	 * @return
	 */
	private boolean filterIsPopulated(SearchFilterGateChart searchFilterGateChart){
		boolean retval = false;
		
		try{
			Set<Map.Entry<String, String>> setOfEntries = searchFilterGateChart.getPopulatedFields().entrySet();
			if(setOfEntries.size()>0){
				for(Map.Entry<String, String> entry : setOfEntries) {
					String searchFilterField = entry.getKey();
					String searchFilterValue = entry.getValue();
					if(searchFilterValue!=null && !"".equals(searchFilterValue)){
						retval = true;
					}
				}
			}	
		}catch(Exception e){
			e.printStackTrace();
		}
			
		return retval;
	}
	
	/**
	 * 
	 * This method gets the total set of values for drop downs. We use the original list (without filtering)
	 * since there is no such a decoupled function
	 * 
	 * @param container
	 * @param model
	 */
	private void populateSearchFilterDropDownsForDatasetCoupledValues(JsonTopicContainer container, Map model){
		//Implement the Sets as TreeSets in order to respect sort ordering (asc)
		Set<String> hsSign= new TreeSet<String>();
		Set<String> hsTariffor= new TreeSet<String>();
		Set<String> hsAvd= new TreeSet<String>();
		Set<String> hsAvdAvdNavn= new TreeSet<String>();
		Set<String> hsTollagerkod= new TreeSet<String>();
		Set<String> hsTollagerdelkod= new TreeSet<String>();
		
		//List<JsonTopicRecord> sortedList = this.getSortedListByColumn(list, column, imgSortPng);
		
		for(JsonTopicRecord record : container.getUfortList()){
			try{
				hsSign.add(record.getSign());
				hsAvd.add(record.getAvd());
				//default
				String avdAvdNavn = record.getAvd();
				if(record.getAvdNavn()!=null && !"".equals(record.getAvdNavn())){
					avdAvdNavn += " " + record.getAvdNavn();
				}
				hsAvdAvdNavn.add(avdAvdNavn);
				hsTariffor.add(record.getTariffor());
				if(record.getTollagerkod()!=null && !"".equals(record.getTollagerkod().trim())){
					hsTollagerkod.add(record.getTollagerkod());
					//logger.info("############## tollagerkod:" + record.getTollagerkod());
				}
				if(record.getTollagerdelkod()!=null && !"".equals(record.getTollagerdelkod().trim())){
					hsTollagerdelkod.add(record.getTollagerdelkod());
					//logger.info("############## tollagerdelkod:" + record.getTollagerdelkod());
					
				}
				
			}catch(Exception e){
				e.printStackTrace();
			}
		}
		//Sort the avd groups since this is an own list in its own right
		List<JsonTopicRecordAvdGroup> sortedGroupList = (List)container.getAvdGroups();
		Collections.sort(sortedGroupList, new JsonTopicRecordAvdGroup.OrderByName());
		model.put(this.DROP_DOWN_KEY_AVD_GROUP_LIST, sortedGroupList);
    
		
		//Now put all the Sets
		model.put(this.DROP_DOWN_KEY_SIGN, hsSign);
		model.put(this.DROP_DOWN_KEY_TARIFFOR, hsTariffor);
		model.put(this.DROP_DOWN_KEY_AVD, hsAvd);
		model.put(this.DROP_DOWN_KEY_AVD_AVDNAVN, hsAvdAvdNavn);
		
		//Tollagerkod
		model.put(this.DROP_DOWN_KEY_TOLLAGERKOD, hsTollagerkod);
		model.put(this.DROP_DOWN_KEY_TOLLAGERKOD_SIZE, hsTollagerkod.size());
		logger.info("tollager_SIZE:" + hsTollagerkod.size());
		//Tollagerdelkod
		model.put(this.DROP_DOWN_KEY_TOLLAGERDELKOD, hsTollagerdelkod);
		model.put(this.DROP_DOWN_KEY_TOLLAGERDELKOD_SIZE, hsTollagerdelkod.size());
		logger.info("tollagerDelkod_SIZE:" + hsTollagerdelkod.size());
		
		//This is required since we do have to save this values once. The reason is because there is no decoupled function
		//for this values and we must grab them from the JSON string...
		container.setHtmlDropDownSign(hsSign);
		container.setHtmlDropDownTariffor(hsTariffor);
		container.setHtmlDropDownAvd(hsAvd);
		container.setHtmlDropDownAvdAvdNavn(hsAvdAvdNavn);
		
		container.setHtmlDropDownTollagerkod(hsTollagerkod);
		container.setHtmlDropDownTollagerdelkod(hsTollagerdelkod);
		
	}
	
	
	/**
	 * 
	 * @param appUser
	 * @return
	 */
	private String getRequestUrlKeyParameters(SystemaWebUser appUser){
		StringBuffer urlRequestParamsKeys = new StringBuffer();
		//String action = request.getParameter("action");
		urlRequestParamsKeys.append("user=" + appUser.getUser());
		//more params here ...urlRequestParamsKeys.append(UoppdragConstants.URL_CHAR_DELIMETER_FOR_PARAMS_WITH_HTML_REQUEST + "avd=" + avd);
		
		return urlRequestParamsKeys.toString();
	}
	
	
	
	//Wired - SERVICES
	@Qualifier ("urlCgiProxyService")
	private UrlCgiProxyService urlCgiProxyService;
	@Autowired
	@Required
	public void setUrlCgiProxyService (UrlCgiProxyService value){ this.urlCgiProxyService = value; }
	public UrlCgiProxyService getUrlCgiProxyService(){ return this.urlCgiProxyService; }
		
	
	@Qualifier ("uoppdragService")
	private UoppdragService uoppdragService;
	@Autowired
	@Required
	public void setUoppdragService (UoppdragService value) { this.uoppdragService = value; }
	public UoppdragService getUoppdragService(){ return this.uoppdragService; }
}

