package no.systema.overview.ufortolledeoppdrag.controller;

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
import no.systema.overview.ufortolledeoppdrag.url.store.UrlDataStore;
import no.systema.overview.ufortolledeoppdrag.filter.*;
import no.systema.overview.ufortolledeoppdrag.model.jsonjackson.JsonTopicContainer;
import no.systema.overview.ufortolledeoppdrag.model.jsonjackson.JsonTopicRecord;
import no.systema.overview.ufortolledeoppdrag.service.UoppdragService;
import no.systema.overview.ufortolledeoppdrag.util.UoppdragConstants;
import no.systema.overview.ufortolledeoppdrag.util.manager.SearchFilterMgr;



//application imports
import no.systema.main.model.SystemaWebUser;
import no.systema.main.util.AppConstants;
//models


/**
 * Working controller (after passing the gateway) to the Ufortollede Oppdrag Application
 * 
 * The controller will manage functionality beyond the general graph.
 * 
 * 
 * @author oscardelatorre
 * @date Aug 28, 2013
 * 
 */

@Controller
public class UoppdragController {
	private static final Logger logger = Logger.getLogger(UoppdragController.class.getName());
	private ModelAndView loginView = new ModelAndView("login");
	//add here more columns and do not forget to add the new column in the method:getSortedListByColumn in this same class
	private final String[] sortableColumns = {"dager", "tariffor", "avd", "sign", "godsnr", "extRef"};
	
	
	/**
	 * 
	 * @param user
	 * @param result
	 * @param request
	 * @return
	 */
	@RequestMapping(value="uoppdrag.do", method={RequestMethod.GET})
	public ModelAndView uoppdrag(HttpSession session, HttpServletRequest request){
		
		ModelAndView successView = new ModelAndView("uoppdrag");
		SystemaWebUser appUser = (SystemaWebUser)session.getAttribute(AppConstants.SYSTEMA_WEB_USER_KEY);
		//get container from session
		String sessionId = session.getId();
		
		//get filter from session in order to present values (if needed)
		SearchFilterGateChart searchFilterGateChart  = (SearchFilterGateChart)session.getAttribute(sessionId + UoppdragConstants.DOMAIN_SEARCH_FILTER_GATE_CHAR);
		JsonTopicContainer jsonTopicContainer = (JsonTopicContainer)session.getAttribute(sessionId + UoppdragConstants.SESSION_UOPP_JSON_CONTAINER_GRAPH);
		
		//get some crucial request parameters
		boolean intervalIsSingleValue = false;
		String uoType = request.getParameter("uoType");
		String startParam = request.getParameter("llim");
		String endParam = request.getParameter("ulim");
		int start = 0;
		int end = 0;
		if(startParam!=null && !"".equals(startParam)){
			//when the interval is not a range but only a single value
			if(startParam.equals(endParam)){
				intervalIsSingleValue = true;
			}
			start = Integer.parseInt(startParam);
		}
		if(endParam!=null && !"".equals(endParam)){
			end = Integer.parseInt(endParam);
		}
		
		String IMG_SORT_PNG_DEFAULT = "sort_down.png";
		String IMG_SORT_PNG_NEUTRAL = "sort_neutral.png";
		
		
		Map model = new HashMap();
		
		if(appUser==null){
			return this.loginView;
		}else{

			if(jsonTopicContainer!=null){
		    		try{
		    			Collection<JsonTopicRecord> fullList = jsonTopicContainer.getUfortList();
	    				Collection<JsonTopicRecord> targetList = new ArrayList<JsonTopicRecord>();
		    			//accumulate and sort data for the jfreeChart
	    				for(JsonTopicRecord record : fullList){
	    					try{
		    					int tmp = Integer.parseInt(record.getDager());
		    					
		    					if("partial".equals(uoType)){
		    						if(intervalIsSingleValue){
		    							if(tmp==start){ targetList.add(record); }
		    						}else{
			    						if(start<=tmp && tmp<=end){ targetList.add(record); }
		    						}
		    					}else{
		    						//fall-back for all categories
		    						targetList.add(record);
		    					}
		    					
		    					
		    				}catch(Exception e){
		    					e.printStackTrace();
		    				}
	    				}
	    				model.put(UoppdragConstants.DOMAIN_LIST, targetList);
	    				model.put(UoppdragConstants.DOMAIN_LIST_SIZE, targetList.size());
	    				model.put(UoppdragConstants.DOMAIN_LIST_TYPE, uoType);
	    				model.put(UoppdragConstants.DOMAIN_LIMIT_LOWER, startParam);
	    				model.put(UoppdragConstants.DOMAIN_LIMIT_UPPER, endParam);
	    				model.put(UoppdragConstants.DOMAIN_LIST_INTERVAL, startParam + " - " + endParam);
	    				
	    				model.put(UoppdragConstants.ASPECT_IMG_SORT_PNG, IMG_SORT_PNG_DEFAULT);
	    				model.put(UoppdragConstants.ASPECT_IMG_SORT_PNG_NEUTRAL, IMG_SORT_PNG_NEUTRAL);
	    				model.put(UoppdragConstants.DOMAIN_SEARCH_FILTER_GATE_CHAR, searchFilterGateChart);
	    				
	    				//update the session list with the correct subset on targetList (this is needed for the UC on sort/per column in method=uoppdragSortByColumn in this same class
	    				session.setAttribute(sessionId + UoppdragConstants.SESSION_UOPP_SUBSET_LIST, targetList );
	    				logger.info("#########################");
	    				logger.info("SUBSET-list size (INIT): " + targetList.size());
	    				//put the final model (other than the chart) available for view
		            successView.addObject(UoppdragConstants.DOMAIN_MODEL, model);
		            
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
	 * @param session
	 * @param request
	 * @return
	 */
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
	

	/**
	 * Sorts the given column
	 * 
	 * @param originalList
	 * @param column
	 * @return
	 */
	private List<JsonTopicRecord> getSortedListByColumn(Collection originalList, String column, String imgSortPng){
		List<JsonTopicRecord> sortedList = (List)originalList;
		//sort ascending by pkey (dager)
		
		if(this.isSortableColumn(column)){
			if(imgSortPng.contains("down")){
				if("dager".equalsIgnoreCase(column)){
    					Collections.sort(sortedList);
				}else if("sign".equalsIgnoreCase(column)){
					Collections.sort(sortedList, new JsonTopicRecord.OrderBySign());
				}else if("tariffor".equalsIgnoreCase(column)){
					Collections.sort(sortedList, new JsonTopicRecord.OrderByTariffor());
				}else if("avd".equalsIgnoreCase(column)){
					Collections.sort(sortedList, new JsonTopicRecord.OrderByAvd());
				}else if("godsnr".equalsIgnoreCase(column)){
					Collections.sort(sortedList, new JsonTopicRecord.OrderByGodsnr());
				}else if("extRef".equalsIgnoreCase(column)){
					Collections.sort(sortedList, new JsonTopicRecord.OrderByExtRef());
				}
			}else if(imgSortPng.contains("up") || imgSortPng.contains("neutral")){
				if("dager".equalsIgnoreCase(column)){
					Collections.sort(sortedList, Collections.reverseOrder());
				}else if("sign".equalsIgnoreCase(column)){
					Collections.sort(sortedList, Collections.reverseOrder(new JsonTopicRecord.OrderBySign()));
				}else if("tariffor".equalsIgnoreCase(column)){
					Collections.sort(sortedList, Collections.reverseOrder(new JsonTopicRecord.OrderByTariffor()));
				}else if("avd".equalsIgnoreCase(column)){
					Collections.sort(sortedList, Collections.reverseOrder(new JsonTopicRecord.OrderByAvd()));
				}else if("godsnr".equalsIgnoreCase(column)){
					Collections.sort(sortedList, Collections.reverseOrder(new JsonTopicRecord.OrderByGodsnr()));
				}else if("extRef".equalsIgnoreCase(column)){
					Collections.sort(sortedList, Collections.reverseOrder(new JsonTopicRecord.OrderByExtRef()));
				}
				
			}else{
				//unsupported case
				Collections.sort(sortedList, Collections.reverseOrder());
			}
		}

		return sortedList;
	}
	
	/**
	 * 
	 * @param column
	 * @param imgSortPng
	 * @return
	 */
	private String updateColumnPng(String column, String imgSortPng ){ 
		String ARROW_UP_PNG = "sort_up.png";
		String ARROW_DOWN_PNG = "sort_down.png";
		
		String retval = imgSortPng;
		//deal with the image(gif,png)
		if(this.isSortableColumn(column)){
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
	
	/**
	 * 
	 * @param columnName
	 * @return
	 */
	private boolean isSortableColumn(String columnName){
		boolean retval = false;
		if(columnName!=null){
			List<String> list = Arrays.asList(this.sortableColumns);
			for(String column: list){
				if(columnName.equals(column)){
					retval = true;
					break;
				}
			}
		}
		
		return retval;
	}
	
}

