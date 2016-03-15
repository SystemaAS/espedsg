package no.systema.overview.sendingerlevtid.controller;

import java.io.File;
import java.util.*;

import org.apache.log4j.Logger;
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
import no.systema.overview.sendingerlevtid.url.store.UrlDataStore;
import no.systema.overview.sendingerlevtid.model.jsonjackson.JsonTopicContainer;
import no.systema.overview.sendingerlevtid.model.jsonjackson.JsonTopicRecord;
import no.systema.overview.sendingerlevtid.service.SendingerlevtidService;

import no.systema.overview.sendingerlevtid.util.SendingerlevtidConstants;
import no.systema.overview.sendingerlevtid.util.freechart.GeneralPieChart;
import no.systema.overview.sendingerlevtid.filter.SearchFilterGateChart;
import no.systema.overview.util.io.FileManager;


//application imports
import no.systema.main.model.SystemaWebUser;
import no.systema.main.util.AppConstants;
import no.systema.main.util.JsonDebugger;


/**
 * Gateway to the Sendinger / Leveringstid Application
 * 
 * The user will be prompted with a general distribution Pie chart and will go forward from there...
 * 
 * 
 * @author oscardelatorre
 * @date Oct 4, 2013
 * 
 */

@Controller
public class SendingerlevtidGateController {
	private static final Logger logger = Logger.getLogger(SendingerlevtidGateController.class.getName());
	private ModelAndView loginView = new ModelAndView("login");
	private JsonDebugger jsonDebugger = new JsonDebugger();
	/**
	 * Main door of the Kvalitets målning module. The end user will be prompt with a huge filter (html form) in order
	 * to restrain the graph domain in to which deliver a result in a later graph based on this selection
	 * 
	 * @param session
	 * @param request
	 * @return
	 */
	@RequestMapping(value="sendingerlevtidgatefilter.do", method={RequestMethod.GET})
	public ModelAndView sendingerlevtidgatefilter(HttpSession session, HttpServletRequest request){
		ModelAndView successView = new ModelAndView("sendingerlevtidgatefilter");
		SystemaWebUser appUser = (SystemaWebUser)session.getAttribute(AppConstants.SYSTEMA_WEB_USER_KEY);
		
		String sessionId = session.getId();
		Map model = new HashMap();
		
		//init relevant variables on session 
		
		SearchFilterGateChart searchFilterGateChart  = null;
		try{
			searchFilterGateChart = (SearchFilterGateChart)session.getAttribute(sessionId + SendingerlevtidConstants.DOMAIN_SEARCH_FILTER_GATE_CHAR);
		}catch(Exception e){
			logger.info("[INFO] Class cast exception... no harm. The filter will be initialized...");
		}
		
		if(appUser==null){
			return this.loginView;
		}else{
			if(searchFilterGateChart!=null){
				model.put(SendingerlevtidConstants.DOMAIN_SEARCH_FILTER_GATE_CHAR, searchFilterGateChart);
			}else{
				model.put(SendingerlevtidConstants.DOMAIN_SEARCH_FILTER_GATE_CHAR, new SearchFilterGateChart());
			}
		}
		successView.addObject(SendingerlevtidConstants.DOMAIN_MODEL, model);
		
		return successView;
	}
	
	/**
	 * 
	 * @param user
	 * @param result
	 * @param request
	 * @return
	 */
	@RequestMapping(value="sendingerlevtidgate.do", method={RequestMethod.GET, RequestMethod.POST})
	public ModelAndView sendingerlevtidgate(HttpSession session, HttpServletRequest request){
		
		ModelAndView successView = new ModelAndView("sendingerlevtidgate");
		SystemaWebUser appUser = (SystemaWebUser)session.getAttribute(AppConstants.SYSTEMA_WEB_USER_KEY);
		
		String sessionId = session.getId();
		Map model = new HashMap();
		
		//init relevant variables on session 
		session.removeAttribute(sessionId + SendingerlevtidConstants.SESSION_SENDLEV_JSON_CONTAINER_GRAPH);
		session.removeAttribute(sessionId + SendingerlevtidConstants.SESSION_SENDLEV_SUBSET_LIST);
		
		
		//get filter if applicable otherwise remove it since this is a refresh
		SearchFilterGateChart searchFilterGateChart  = null;
		String action = request.getParameter("action");
		if("doBack".equals(action)){
			//this is usually a situation when the user returns to the main chart without refreshing (in order to maintain the search conditions once entered)
			searchFilterGateChart  = (SearchFilterGateChart)session.getAttribute(sessionId + SendingerlevtidConstants.DOMAIN_SEARCH_FILTER_GATE_CHAR);
		}else{
			//----------------------------------------------
			//get Search Filter and populate (bind) it here
			//----------------------------------------------
			//remove filter from session
			session.removeAttribute(sessionId + SendingerlevtidConstants.DOMAIN_SEARCH_FILTER_GATE_CHAR);
			//populate now the filter with the form input fields
			searchFilterGateChart  = new SearchFilterGateChart() ;
			ServletRequestDataBinder binder = new ServletRequestDataBinder(searchFilterGateChart);
	        binder.bind(request);
	        //store the filter in session. From this point on all coming tabs must fetch the filter from session in order to
	        //navigate back and forward in the differente tabs (charts, list) based on the filter selection 
	        	session.setAttribute(sessionId + SendingerlevtidConstants.DOMAIN_SEARCH_FILTER_GATE_CHAR,searchFilterGateChart );

		}
		
		if(appUser==null){
			return this.loginView;
		}else{
			
			String BASE_URL = null;
			String urlRequestParamsKeys = null;
			BASE_URL = UrlDataStore.OVERVIEW_SENDINGER_LEVTID_MAINLIST_URL;
			urlRequestParamsKeys = this.getRequestUrlKeyParameters(appUser, searchFilterGateChart);
			
			//url params
			//String urlRequestParamsKeys = this.getRequestUrlKeyParameters(appUser);
			//for debug purposes in GUI
			session.setAttribute(SendingerlevtidConstants.ACTIVE_URL_RPG, BASE_URL  + "==>params: " + urlRequestParamsKeys.toString()); 
			
			logger.info(Calendar.getInstance().getTime() + " CGI-start timestamp");
		    	logger.info("URL: " + BASE_URL);
		    	logger.info("URL PARAMS: " + urlRequestParamsKeys);
		    	//--------------------------------------
		    	//EXECUTE the FETCH (RPG program) here
		    	//--------------------------------------
		    	String jsonPayload = this.urlCgiProxyService.getJsonContent(BASE_URL, urlRequestParamsKeys);
    			
		    	JsonTopicContainer jsonTopicContainer = null;
		    	if(jsonPayload!=null){
		    		try{
		    			//Debug --> 
		    			this.jsonDebugger.debugJsonPayload(jsonPayload);
				    	
		    			jsonTopicContainer = this.sendingerlevtidService.getContainer(jsonPayload);
		    			logger.info(Calendar.getInstance().getTime() +  " CGI-end timestamp");
				    	
		    			if(jsonTopicContainer!=null){
		    				Collection<JsonTopicRecord> kvalList = jsonTopicContainer.getKvalList();
		    				
		    				this.populateSearchFilterDropDowns(kvalList, model);
		    				//------------------------------
			    			//START search filter conditions
			    			//------------------------------
			    			//if the user chose a filter condition we must then filter the complete list in order to have the main set based on the filter conditions
			    			/*if(searchFilterGateChart.getSign()!=null || searchFilterGateChart.getAvd()!=null){
			    				logger.info("################################ Inside WASH list");
			    				kvalList = this.buildNewListWithSearchFilter(kvalList, searchFilterGateChart);
			    				model.put(SendingerlevtidConstants.DOMAIN_SEARCH_FILTER_GATE_CHAR, searchFilterGateChart);
			    			}*/
			    			//------------------------------
			    			//END search filter conditions
			    			//------------------------------
			    			//put the graph and the JSON container in session since we will use the info until a refresh (in another user activity)
			    			//from this point forward we will be getting the dataset from the session until we return to this method (refresh)
			    			jsonTopicContainer.setKvalList(kvalList); //refresh the list in case some filter values were applicable
		    				session.setAttribute(sessionId + SendingerlevtidConstants.SESSION_SENDLEV_JSON_CONTAINER_GRAPH, jsonTopicContainer );
		    				//save the searchFilter object as well for use until next refresh
		    				//session.setAttribute(sessionId + SendingerlevtidConstants.DOMAIN_SEARCH_FILTER_GATE_CHAR, searchFilterGateChart);
			    			
		    				model.put(SendingerlevtidConstants.DOMAIN_LIST, jsonTopicContainer.getKvalList());
			    			model.put(SendingerlevtidConstants.DOMAIN_SENDINGER_LIST_SIZE, jsonTopicContainer.getKvalList().size());
			    			model.put(SendingerlevtidConstants.DOMAIN_CONTAINER, jsonTopicContainer);
			    			
		    				//put the final model (other than the chart) available for view
			            successView.addObject(SendingerlevtidConstants.DOMAIN_MODEL, model);
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
	 * This method gets the total set of values for drop downs. We use the original list (without filtering)
	 * since there is no such a decouple function
	 * 
	 * @param list
	 * @param model
	 */
	
	private void populateSearchFilterDropDowns(Collection<JsonTopicRecord> list, Map model){
		HashSet<String> hsSign= new HashSet<String>();
		HashSet<String> hsAvd= new HashSet<String>();
		
		for(JsonTopicRecord record : list){
			try{
				//hsSign.add(record.getSign());
				hsAvd.add(record.getAvd());
			}catch(Exception e){
				e.printStackTrace();
			}
		}
		model.put("hsSign", hsSign);
		model.put("hsAvd", hsAvd);
		
	}
	
	
	/**
	 * 
	 * @param appUser
	 * @param searchFilterGateChart
	 * @return
	 * 
	 */
	private String getRequestUrlKeyParameters(SystemaWebUser appUser, SearchFilterGateChart searchFilterGateChart){
		StringBuffer urlRequestParamsKeys = new StringBuffer();
		//String action = request.getParameter("action");
		//user=OSCAR&dato=20130926&datot=20131030&agentg=95700&ied=D&iott=D&tt1=POD&tt2=058&tt3=011&tt4=012&tt5=070&maxSN=99999&maxRC=99999
		
		urlRequestParamsKeys.append("user=" + appUser.getUser());
		if(searchFilterGateChart!=null){
			if(searchFilterGateChart.getDato()!=null && !"".equals(searchFilterGateChart.getDato())){
				urlRequestParamsKeys.append(SendingerlevtidConstants.URL_CHAR_DELIMETER_FOR_PARAMS_WITH_HTML_REQUEST + "dato=" + searchFilterGateChart.getDato());
			}
			if(searchFilterGateChart.getDatot()!=null && !"".equals(searchFilterGateChart.getDatot())){
				urlRequestParamsKeys.append(SendingerlevtidConstants.URL_CHAR_DELIMETER_FOR_PARAMS_WITH_HTML_REQUEST + "datot=" + searchFilterGateChart.getDatot());
				
			}
			if(searchFilterGateChart.getAgent()!=null && !"".equals(searchFilterGateChart.getAgent())){
				urlRequestParamsKeys.append(SendingerlevtidConstants.URL_CHAR_DELIMETER_FOR_PARAMS_WITH_HTML_REQUEST + "agent=" + searchFilterGateChart.getAgent());
				
			}
			if(searchFilterGateChart.getAgentg()!=null && !"".equals(searchFilterGateChart.getAgentg())){
				urlRequestParamsKeys.append(SendingerlevtidConstants.URL_CHAR_DELIMETER_FOR_PARAMS_WITH_HTML_REQUEST + "agentg=" + searchFilterGateChart.getAgentg());
				
			}
			if(searchFilterGateChart.getIed()!=null && !"".equals(searchFilterGateChart.getIed())){
				urlRequestParamsKeys.append(SendingerlevtidConstants.URL_CHAR_DELIMETER_FOR_PARAMS_WITH_HTML_REQUEST + "ied=" + searchFilterGateChart.getIed());
				
			}
			//IOTT BLOCK
			if(searchFilterGateChart.getIott()!=null && !"".equals(searchFilterGateChart.getIott())){
				urlRequestParamsKeys.append(SendingerlevtidConstants.URL_CHAR_DELIMETER_FOR_PARAMS_WITH_HTML_REQUEST + "iott=" + searchFilterGateChart.getIott());
				
			}
			if(searchFilterGateChart.getTt1()!=null && !"".equals(searchFilterGateChart.getTt1())){
				urlRequestParamsKeys.append(SendingerlevtidConstants.URL_CHAR_DELIMETER_FOR_PARAMS_WITH_HTML_REQUEST + "tt1=" + searchFilterGateChart.getTt1());
				
			}
			if(searchFilterGateChart.getTt2()!=null && !"".equals(searchFilterGateChart.getTt2())){
				urlRequestParamsKeys.append(SendingerlevtidConstants.URL_CHAR_DELIMETER_FOR_PARAMS_WITH_HTML_REQUEST + "tt2=" + searchFilterGateChart.getTt2());
				
			}
			if(searchFilterGateChart.getTt3()!=null && !"".equals(searchFilterGateChart.getTt3())){
				urlRequestParamsKeys.append(SendingerlevtidConstants.URL_CHAR_DELIMETER_FOR_PARAMS_WITH_HTML_REQUEST + "tt3=" + searchFilterGateChart.getTt3());
				
			}
			if(searchFilterGateChart.getTt4()!=null && !"".equals(searchFilterGateChart.getTt4())){
				urlRequestParamsKeys.append(SendingerlevtidConstants.URL_CHAR_DELIMETER_FOR_PARAMS_WITH_HTML_REQUEST + "tt4=" + searchFilterGateChart.getTt4());
				
			}
			if(searchFilterGateChart.getTt5()!=null && !"".equals(searchFilterGateChart.getTt5())){
				urlRequestParamsKeys.append(SendingerlevtidConstants.URL_CHAR_DELIMETER_FOR_PARAMS_WITH_HTML_REQUEST + "tt5=" + searchFilterGateChart.getTt5());
				
			}
			//IOTT BLOCK (b)
			if(searchFilterGateChart.getIottb()!=null && !"".equals(searchFilterGateChart.getIottb())){
				urlRequestParamsKeys.append(SendingerlevtidConstants.URL_CHAR_DELIMETER_FOR_PARAMS_WITH_HTML_REQUEST + "iottb=" + searchFilterGateChart.getIottb());
				
			}
			if(searchFilterGateChart.getTt1b()!=null && !"".equals(searchFilterGateChart.getTt1b())){
				urlRequestParamsKeys.append(SendingerlevtidConstants.URL_CHAR_DELIMETER_FOR_PARAMS_WITH_HTML_REQUEST + "tt1b=" + searchFilterGateChart.getTt1b());
				
			}
			if(searchFilterGateChart.getTt2b()!=null && !"".equals(searchFilterGateChart.getTt2b())){
				urlRequestParamsKeys.append(SendingerlevtidConstants.URL_CHAR_DELIMETER_FOR_PARAMS_WITH_HTML_REQUEST + "tt2b=" + searchFilterGateChart.getTt2b());
				
			}
			if(searchFilterGateChart.getTt3b()!=null && !"".equals(searchFilterGateChart.getTt3b())){
				urlRequestParamsKeys.append(SendingerlevtidConstants.URL_CHAR_DELIMETER_FOR_PARAMS_WITH_HTML_REQUEST + "tt3b=" + searchFilterGateChart.getTt3b());
				
			}
			if(searchFilterGateChart.getTt4b()!=null && !"".equals(searchFilterGateChart.getTt4b())){
				urlRequestParamsKeys.append(SendingerlevtidConstants.URL_CHAR_DELIMETER_FOR_PARAMS_WITH_HTML_REQUEST + "tt4b=" + searchFilterGateChart.getTt4b());
			}
			if(searchFilterGateChart.getTt5b()!=null && !"".equals(searchFilterGateChart.getTt5b())){
				urlRequestParamsKeys.append(SendingerlevtidConstants.URL_CHAR_DELIMETER_FOR_PARAMS_WITH_HTML_REQUEST + "tt5b=" + searchFilterGateChart.getTt5b());
			}
			if(searchFilterGateChart.getTur()!=null && !"".equals(searchFilterGateChart.getTur())){
				urlRequestParamsKeys.append(SendingerlevtidConstants.URL_CHAR_DELIMETER_FOR_PARAMS_WITH_HTML_REQUEST + "tur=" + searchFilterGateChart.getTur());
			}
			if(searchFilterGateChart.getIols()!=null && !"".equals(searchFilterGateChart.getIols())){
				urlRequestParamsKeys.append(SendingerlevtidConstants.URL_CHAR_DELIMETER_FOR_PARAMS_WITH_HTML_REQUEST + "iols=" + searchFilterGateChart.getIols());
			}
			if(searchFilterGateChart.getLs1()!=null && !"".equals(searchFilterGateChart.getLs1())){
				urlRequestParamsKeys.append(SendingerlevtidConstants.URL_CHAR_DELIMETER_FOR_PARAMS_WITH_HTML_REQUEST + "ls1=" + searchFilterGateChart.getLs1());
			}
			if(searchFilterGateChart.getLs2()!=null && !"".equals(searchFilterGateChart.getLs2())){
				urlRequestParamsKeys.append(SendingerlevtidConstants.URL_CHAR_DELIMETER_FOR_PARAMS_WITH_HTML_REQUEST + "ls2=" + searchFilterGateChart.getLs2());
			}
			
			if(searchFilterGateChart.getLevyn()!=null && !"".equals(searchFilterGateChart.getLevyn())){
				urlRequestParamsKeys.append(SendingerlevtidConstants.URL_CHAR_DELIMETER_FOR_PARAMS_WITH_HTML_REQUEST + "levyn=" + searchFilterGateChart.getLevyn());
			}
			if(searchFilterGateChart.getPoayn()!=null && !"".equals(searchFilterGateChart.getPoayn())){
				urlRequestParamsKeys.append(SendingerlevtidConstants.URL_CHAR_DELIMETER_FOR_PARAMS_WITH_HTML_REQUEST + "poayn=" + searchFilterGateChart.getPoayn());
			}
			if(searchFilterGateChart.getTrans()!=null && !"".equals(searchFilterGateChart.getTrans())){
				urlRequestParamsKeys.append(SendingerlevtidConstants.URL_CHAR_DELIMETER_FOR_PARAMS_WITH_HTML_REQUEST + "trans=" + searchFilterGateChart.getTrans());
			}
			if(searchFilterGateChart.getMottpost()!=null && !"".equals(searchFilterGateChart.getMottpost())){
				urlRequestParamsKeys.append(SendingerlevtidConstants.URL_CHAR_DELIMETER_FOR_PARAMS_WITH_HTML_REQUEST + "mottpost=" + searchFilterGateChart.getMottpost());
			}
			if(searchFilterGateChart.getViskl()!=null && !"".equals(searchFilterGateChart.getViskl())){
				urlRequestParamsKeys.append(SendingerlevtidConstants.URL_CHAR_DELIMETER_FOR_PARAMS_WITH_HTML_REQUEST + "viskl=" + searchFilterGateChart.getViskl());
			}
			if(searchFilterGateChart.getAvd()!=null && !"".equals(searchFilterGateChart.getAvd())){
				urlRequestParamsKeys.append(SendingerlevtidConstants.URL_CHAR_DELIMETER_FOR_PARAMS_WITH_HTML_REQUEST + "avd=" + searchFilterGateChart.getAvd());
			}
			
			//always include this threshold
			urlRequestParamsKeys.append(SendingerlevtidConstants.URL_CHAR_DELIMETER_FOR_PARAMS_WITH_HTML_REQUEST + "maxSN=" + searchFilterGateChart.getMaxSn());
			urlRequestParamsKeys.append(SendingerlevtidConstants.URL_CHAR_DELIMETER_FOR_PARAMS_WITH_HTML_REQUEST + "maxRC=" + searchFilterGateChart.getMaxRc());
			
		}
			
		return urlRequestParamsKeys.toString();
	}
		
	
	//Wired - SERVICES
	@Qualifier ("urlCgiProxyService")
	private UrlCgiProxyService urlCgiProxyService;
	@Autowired
	@Required
	public void setUrlCgiProxyService (UrlCgiProxyService value){ this.urlCgiProxyService = value; }
	public UrlCgiProxyService getUrlCgiProxyService(){ return this.urlCgiProxyService; }
		
	
	@Qualifier ("sendingerlevtidService")
	private SendingerlevtidService sendingerlevtidService;
	@Autowired
	@Required
	public void setSendingerlevtidService (SendingerlevtidService value) { this.sendingerlevtidService = value; }
	public SendingerlevtidService getSendingerlevtidService(){ return this.sendingerlevtidService; }
}

