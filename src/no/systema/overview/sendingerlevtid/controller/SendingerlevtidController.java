package no.systema.overview.sendingerlevtid.controller;

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

import no.systema.overview.sendingerlevtid.filter.*;
import no.systema.overview.sendingerlevtid.model.jsonjackson.JsonTopicContainer;
import no.systema.overview.sendingerlevtid.model.jsonjackson.JsonTopicRecord;
import no.systema.overview.sendingerlevtid.util.SendingerlevtidConstants;
//import no.systema.overview.ufortolledeoppdrag.util.manager.SearchFilterMgr;


//application imports
import no.systema.main.model.SystemaWebUser;
import no.systema.main.util.AppConstants;
//models


/**
 * 
 * Working controller (after passing the gateway) to the Sendinger / Leveringstid Application
 * 
 * The controller will manage functionality beyond the general graph.
 * 
 * 
 * @author oscardelatorre
 * @date Oct 4, 2013
 * 
 * 
 */

@Controller
public class SendingerlevtidController {
	private static final Logger logger = Logger.getLogger(SendingerlevtidController.class.getName());
	private ModelAndView loginView = new ModelAndView("login");
	
	/**
	 * 
	 * @param user
	 * @param result
	 * @param request
	 * @return
	 */
	@RequestMapping(value="sendingerlevtid.do", method={RequestMethod.GET, RequestMethod.POST})
	public ModelAndView sendingerlevtid(HttpSession session, HttpServletRequest request){
		
		ModelAndView successView = new ModelAndView("sendingerlevtid");
		SystemaWebUser appUser = (SystemaWebUser)session.getAttribute(AppConstants.SYSTEMA_WEB_USER_KEY);
		//get container from session
		String sessionId = session.getId();
		
		
		//get filter from session in order to present values (if needed)
		SearchFilterGateChart searchFilterGateChart  = (SearchFilterGateChart)session.getAttribute(sessionId + SendingerlevtidConstants.DOMAIN_SEARCH_FILTER_GATE_CHAR);
		JsonTopicContainer jsonTopicContainer = (JsonTopicContainer)session.getAttribute(sessionId + SendingerlevtidConstants.SESSION_SENDLEV_JSON_CONTAINER_GRAPH);
		//some subset may be needed since we want to show different results
		Map<String,String> filterMap = this.populateSearchFilterGateChart(request);
		Map model = new HashMap();
		
		String IMG_SORT_PNG_DEFAULT = "sort_down.png";
		String IMG_SORT_PNG_NEUTRAL = "sort_neutral.png";
		
		if(appUser==null){
			return this.loginView;
		}else{

			if(jsonTopicContainer!=null){
		    		try{
		    			Collection<JsonTopicRecord> fullList = jsonTopicContainer.getKvalList();
		    			Collection<JsonTopicRecord> targetList = new ArrayList<JsonTopicRecord>();
		    			//We must present subsets or all the list
		    			String tmp = null;
		    			String result = (String)filterMap.get("result");
		    			String podARes = (String)filterMap.get("podARes");
		    			if(result!=null || podARes!=null){
		    				this.setCategories(model, result, podARes);
		    				for(JsonTopicRecord record : fullList){
		    					try{
		    						if(filterMap.get("result")!=null ){
			    						tmp = (String)filterMap.get("result");
			    						if(tmp.equals(record.getResultat())){
			    							targetList.add(record);
			    						}
			    					}else if(filterMap.get("podARes")!=null ){
			    						tmp = (String)filterMap.get("podARes");
			    						if(tmp.equals(record.getPodARes())){
			    							targetList.add(record);
			    						}
			    					}
			    				}catch(Exception e){
			    					e.printStackTrace();
			    				}
		    				}
		    			}else{
		    				//when the whole list is required
    						targetList = fullList;
		    			}
		    			
		    			//At this point we are done with the selection. The rest is rendering to the GUI elements
		    			model.put(SendingerlevtidConstants.DOMAIN_LIST, targetList);
	    				model.put(SendingerlevtidConstants.DOMAIN_LIST_SIZE, targetList.size());
	    				
		    			
	    				model.put(SendingerlevtidConstants.ASPECT_IMG_SORT_PNG, IMG_SORT_PNG_DEFAULT);
	    				model.put(SendingerlevtidConstants.ASPECT_IMG_SORT_PNG_NEUTRAL, IMG_SORT_PNG_NEUTRAL);
	    				model.put(SendingerlevtidConstants.DOMAIN_SEARCH_FILTER_GATE_CHAR, searchFilterGateChart);
	    				
	    				//update the session list with the correct subset on targetList (this is needed for the UC on sort/per column in method=uoppdragSortByColumn in this same class
	    				session.setAttribute(sessionId + SendingerlevtidConstants.SESSION_SENDLEV_SUBSET_LIST, targetList );
	    				
	    				logger.info("#########################");
	    				logger.info("SUBSET-list size (INIT): " + targetList.size());
	    				//put the final model (other than the chart) available for view
		            successView.addObject(SendingerlevtidConstants.DOMAIN_MODEL, model);
		            
		    		}catch(Exception e){
		    			e.printStackTrace();
		    			session.setAttribute(AppConstants.ASPECT_ERROR_MESSAGE, "[ERROR fatal] jsonPayload is NULL. Check logs...");
		    		}
		    		
		    	}else{
		    		session.setAttribute(AppConstants.ASPECT_ERROR_MESSAGE, "[ERROR fatal] jsonContainer is NULL. Check logs...");
		    	}
		}
		
		return successView;
	}
	
	/**
	 * 
	 * @param model
	 * @param result
	 * @param podARes
	 */
	private void setCategories(Map model, String result, String podARes){
		if("Y".equals(result)){
			model.put(SendingerlevtidConstants.DOMAIN_LIST_TYPE, "Leveringskvalitet" ); //tab legend
			model.put("offset", "OK" );
			
		}else if("N".equals(result)){
			model.put(SendingerlevtidConstants.DOMAIN_LIST_TYPE, "Leveringskvalitet" ); //tab legend
			model.put("offset", "Ikke Ok" );
			
		}else if("Y".equals(podARes)){
			model.put(SendingerlevtidConstants.DOMAIN_LIST_TYPE, "POD Archived -kvalitet" ); //tab legend
			model.put("offset", "Ok" );
			
		}else if("N".equals(podARes)){
			model.put(SendingerlevtidConstants.DOMAIN_LIST_TYPE, "POD Archived -kvalitet" ); //tab legend
			model.put("offset", "Ikke Ok" );
			
		}
	}
	/**
	 * 
	 * @param searchFilterGateChart
	 * @param request
	 */
	private Map<String,String> populateSearchFilterGateChart(HttpServletRequest request){
		Map<String,String> map = new HashMap<String,String>();
		//get buttons to see which one was pressed and populate the filter since we must filter a subset
		String submitOK_LevQA = request.getParameter("submitOK_LevQA");
		String submitNOK_LevQA = request.getParameter("submitNOK_LevQA");
		String submitOK_ArkQA = request.getParameter("submitOK_ArkQA");
		String submitNOK_ArkQA = request.getParameter("submitNOK_ArkQA");
		//only one button will be NOT NULL
		if(submitOK_LevQA!=null && !"".equals(submitOK_LevQA)){
			map.put("result","Y");
		}else if(submitNOK_LevQA!=null && !"".equals(submitNOK_LevQA)){
			map.put("result","N");
		}else if(submitOK_ArkQA!=null && !"".equals(submitOK_ArkQA)){
			map.put("podARes","Y");
		}else if(submitNOK_ArkQA!=null && !"".equals(submitNOK_ArkQA)){
			map.put("podARes","N");
		}
		return map;
	}

	
	/**
	 * 
	 * @param session
	 * @param request
	 * @return
	 */
	/*
	@RequestMapping(value="uoppdragFindByField.do", method={RequestMethod.POST})
	public ModelAndView uoppdragFindByField(HttpSession session, HttpServletRequest request){
		
		ModelAndView successView = new ModelAndView("uoppdrag");
		SystemaWebUser appUser = (SystemaWebUser)session.getAttribute(AppConstants.SYSTEMA_WEB_USER_KEY);
		//get sublist from session
		String sessionId = session.getId();
		//get chart filter from session in order to present values (if needed)
		SearchFilterGateChart searchFilterGateChart  = (SearchFilterGateChart)session.getAttribute(sessionId + UoppdragConstants.DOMAIN_SEARCH_FILTER_GATE_CHAR);
		
		//Bind the request searchFilter here
		SearchFilterUoppdrag searchFilter = new SearchFilterUoppdrag();
		//remove from session (init) in order to manage the new iteration
		session.removeAttribute(sessionId + UoppdragConstants.DOMAIN_SEARCH_FILTER);
        ServletRequestDataBinder binder = new ServletRequestDataBinder(searchFilter);
        //binder.registerCustomEditor(...); // if needed
        binder.bind(request);
        //set in session in case the sortByColumn method is used later on in the same web page
        session.setAttribute(sessionId + UoppdragConstants.DOMAIN_SEARCH_FILTER, searchFilter);
        
        
		Collection<JsonTopicRecord> originalList  = (List<JsonTopicRecord>)session.getAttribute(sessionId + UoppdragConstants.SESSION_UOPP_SUBSET_LIST);
		
		//request params
		String uoType = request.getParameter("uoType");
		String startParam = request.getParameter("llim");
		String endParam = request.getParameter("ulim");
		
		String IMG_SORT_PNG_DEFAULT = "sort_down.png";
		String IMG_SORT_PNG_NEUTRAL = "sort_neutral.png";
		
		Map model = new HashMap();
		
		if(appUser==null){
			return this.loginView;
		}else{

	    		try{
	    			Collection<JsonTopicRecord> matchedList = new ArrayList<JsonTopicRecord>();
	    			//We do use our implementation of search filter match by java reflection
	    			Set<Map.Entry<String, String>> setOfEntries = searchFilter.getPopulatedFields().entrySet();
				if(setOfEntries.size()>0){
					logger.info("################## setOfEntries: " + setOfEntries.size());
					logger.info("SUBSET-list-Filtered (size): " + originalList.size());
					matchedList = new SearchFilterMgr().filterUoppragDataset(searchFilter, originalList);
					session.setAttribute(sessionId + UoppdragConstants.SESSION_UOPP_SUBSET_LIST_FILTERED, matchedList);
					
	    			}else{
	    				logger.info("XXXXXXXXXXXXXXXXXX setOfEntries = 0");
	    				logger.info("SUBSET-list (size): " + originalList.size());
	    				matchedList = originalList;
	    				session.removeAttribute(sessionId + UoppdragConstants.SESSION_UOPP_SUBSET_LIST_FILTERED);
	    				
	    			}
	    			//At this point we now have a filtered dataset
				//go on with the rest of the logic
				model.put(UoppdragConstants.DOMAIN_LIST, matchedList);
    				model.put(UoppdragConstants.DOMAIN_LIST_SIZE, matchedList.size());
    				model.put(UoppdragConstants.DOMAIN_LIST_TYPE, uoType);
    				model.put(UoppdragConstants.DOMAIN_LIMIT_LOWER, startParam);
    				model.put(UoppdragConstants.DOMAIN_LIMIT_UPPER, endParam);
    				model.put(UoppdragConstants.DOMAIN_LIST_INTERVAL, startParam + " - " + endParam);
    				
    				model.put(UoppdragConstants.ASPECT_IMG_SORT_PNG, IMG_SORT_PNG_DEFAULT);
    				model.put(UoppdragConstants.ASPECT_IMG_SORT_PNG_NEUTRAL, IMG_SORT_PNG_NEUTRAL);
    				
    				model.put(UoppdragConstants.DOMAIN_SEARCH_FILTER_GATE_CHAR, searchFilterGateChart);
    				model.put(UoppdragConstants.DOMAIN_SEARCH_FILTER, searchFilter);
    				
    				
    				//put the final model (other than the chart) available for view
	            successView.addObject(UoppdragConstants.DOMAIN_MODEL, model);
	            
	            
	    		}catch(Exception e){
	    			e.printStackTrace();
	    			session.setAttribute(AppConstants.ASPECT_ERROR_MESSAGE, "[ERROR fatal] jsonPayload is NULL. Check logs...");
	    		}
		    		
		}
		
		return successView;
	}
	*/
	/**
	 * This method sorts in a per-column-basis.
	 * Could be implemented also as an AJAX asynch. jQuery call though with other issues to take care such as getting the data set from a http session.
	 * 
	 * For the time being this method is used in the Ufortollede oppdrag - UC (onClick column)
	 * 
	 * @param user
	 * @param result
	 * @param request
	 * @return
	 */
	/*
	@RequestMapping(value="uoppdragSortByColumn.do", method={RequestMethod.GET, RequestMethod.POST})
	public ModelAndView uoppdragSortByColumn(HttpSession session, HttpServletRequest request){
		
		ModelAndView successView = new ModelAndView("uoppdrag");
		SystemaWebUser appUser = (SystemaWebUser)session.getAttribute(AppConstants.SYSTEMA_WEB_USER_KEY);
		//get sublist from session
		String sessionId = session.getId();
		//get filter from session in order to present values (if needed)
		SearchFilterGateChart searchFilterGateChart  = (SearchFilterGateChart)session.getAttribute(sessionId + UoppdragConstants.DOMAIN_SEARCH_FILTER_GATE_CHAR);
		SearchFilterUoppdrag searchFilter  = (SearchFilterUoppdrag)session.getAttribute(sessionId + UoppdragConstants.DOMAIN_SEARCH_FILTER);
				
		Collection<JsonTopicRecord> list  = null;
		list  = (List<JsonTopicRecord>)session.getAttribute(sessionId + UoppdragConstants.SESSION_UOPP_SUBSET_LIST_FILTERED);
		if(list!=null && list.size()>0){
			//nothing since we will be using the filtered subset list (populated in the uoppdragFindByField-method in this same class)
		}else{
			//fetch the original subset list
			list  = (List<JsonTopicRecord>)session.getAttribute(sessionId + UoppdragConstants.SESSION_UOPP_SUBSET_LIST);
			
		}
		
		//request params
		String uoType = request.getParameter("uoType");
		String startParam = request.getParameter("llim");
		String endParam = request.getParameter("ulim");
		
		String column = request.getParameter("col");
		String imgSortPng = request.getParameter("sort");
		String imgSortNeutralPng = "sort_neutral.png";
		
		Map model = new HashMap();
		
		if(appUser==null){
			return this.loginView;
		}else{

	    		try{
	    			List<JsonTopicRecord> sortedList = this.getSortedListByColumn(list, column, imgSortPng);
	    			imgSortPng = this.updateColumnPng(column, imgSortPng);
	    			
    				model.put(UoppdragConstants.DOMAIN_LIST, sortedList);
    				model.put(UoppdragConstants.DOMAIN_LIST_SIZE, sortedList.size());
    				model.put(UoppdragConstants.DOMAIN_LIST_TYPE, uoType);
    				model.put(UoppdragConstants.DOMAIN_LIMIT_LOWER, startParam);
    				model.put(UoppdragConstants.DOMAIN_LIMIT_UPPER, endParam);
    				model.put(UoppdragConstants.DOMAIN_LIST_INTERVAL, startParam + " - " + endParam);
    				
    				model.put(UoppdragConstants.ASPECT_ACTIVE_SORT_COLUMN, column);
    				model.put(UoppdragConstants.ASPECT_IMG_SORT_PNG, imgSortPng);
    				model.put(UoppdragConstants.ASPECT_IMG_SORT_PNG_NEUTRAL, imgSortNeutralPng);
    				
    				model.put(UoppdragConstants.DOMAIN_SEARCH_FILTER_GATE_CHAR, searchFilterGateChart);
    				model.put(UoppdragConstants.DOMAIN_SEARCH_FILTER, searchFilter);
    				
    				//put the final model (other than the chart) available for view
	            successView.addObject(UoppdragConstants.DOMAIN_MODEL, model);
	            
	    		}catch(Exception e){
	    			e.printStackTrace();
	    			session.setAttribute(AppConstants.ASPECT_ERROR_MESSAGE, "[ERROR fatal] jsonPayload is NULL. Check logs...");
	    		}
		    		
		}
		
		return successView;
	}
	*/

	/**
	 * Sorts the given column
	 * 
	 * @param originalList
	 * @param column
	 * @return
	 */
	/*
	private List<JsonTopicRecord> getSortedListByColumn(Collection originalList, String column, String imgSortPng){
		List<JsonTopicRecord> sortedList = (List)originalList;
		//sort ascending by pkey (dager)
		if("dager".equalsIgnoreCase(column)){
			if(imgSortPng.contains("down")){
	    			Collections.sort(sortedList);
			}else if(imgSortPng.contains("up")){
				Collections.sort(sortedList, Collections.reverseOrder());
			}else{
				//neutral png
				Collections.sort(sortedList, Collections.reverseOrder());
			}
		}else if("sign".equalsIgnoreCase(column)){
			if(imgSortPng.contains("down")){
	    			Collections.sort(sortedList, new JsonTopicRecord.OrderBySign());
			}else if(imgSortPng.contains("up")){
				Collections.sort(sortedList, Collections.reverseOrder(new JsonTopicRecord.OrderBySign()));
			}else{
				//neutral png
				Collections.sort(sortedList, Collections.reverseOrder(new JsonTopicRecord.OrderBySign()));
			}
		}else if("avd".equalsIgnoreCase(column)){
			if(imgSortPng.contains("down")){
	    			Collections.sort(sortedList, new JsonTopicRecord.OrderByAvd());
			}else if(imgSortPng.contains("up")){
				Collections.sort(sortedList, Collections.reverseOrder(new JsonTopicRecord.OrderByAvd()));
			}else{
				//neutral png
				Collections.sort(sortedList, Collections.reverseOrder(new JsonTopicRecord.OrderByAvd()));
			}
		}else{
			//more implementations here
		}
		
		return sortedList;
	}
	*/
	/**
	 * 
	 * @param column
	 * @param imgSortPng
	 * @return
	 */
	/*
	private String updateColumnPng(String column, String imgSortPng ){ 
		String ARROW_UP_PNG = "sort_up.png";
		String ARROW_DOWN_PNG = "sort_down.png";
		
		String retval = imgSortPng;
		
		//deal with the icon
		if("dager".equalsIgnoreCase(column)){
			if(imgSortPng!=null){
				if(imgSortPng.contains("down")){
					retval = ARROW_UP_PNG;
				}else{
					retval = ARROW_DOWN_PNG;
				}
			}else{
				retval = ARROW_DOWN_PNG;
			}
		}else if("sign".equalsIgnoreCase(column)){
			if(imgSortPng!=null){
				if(imgSortPng.contains("down")){
					retval = ARROW_UP_PNG;
				}else{
					retval = ARROW_DOWN_PNG;
				}
			}else{
				retval = ARROW_DOWN_PNG;
			}
		}else if("avd".equalsIgnoreCase(column)){
			if(imgSortPng!=null){
				if(imgSortPng.contains("down")){
					retval = ARROW_UP_PNG;
				}else{
					retval = ARROW_DOWN_PNG;
				}
			}else{
				retval = ARROW_DOWN_PNG;
			}
		}
		
		return retval;
	}
	*/
}

