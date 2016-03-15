package no.systema.overview.ufortolledeoppdrag.controller.view;

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
import javax.servlet.http.HttpServletResponse;
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
 * Working controller for the Ufortollede Oppdrag non JSP-pages
 * The controller will manage all export functionality to different view formats such as:
 * 
 * (1) Excel, PDF, other are implemented here
 * 
 * 
 * 
 * @author oscardelatorre
 * @date Aug 28, 2013
 * 
 */

@Controller
public class UoppdragViewController {
	private static final Logger logger = Logger.getLogger(UoppdragViewController.class.getName());
	private ModelAndView loginView = new ModelAndView("login");
	
	/**
	 * 
	 * @param user
	 * @param result
	 * @param request
	 * @return
	 * 
	 */
	@RequestMapping(value="uoppListExcelView.do", method={RequestMethod.GET})
	public ModelAndView uoppListExcelView(HttpSession session, HttpServletRequest request, HttpServletResponse response){
		
		SystemaWebUser appUser = (SystemaWebUser)session.getAttribute(AppConstants.SYSTEMA_WEB_USER_KEY);
		List<JsonTopicRecord> targetedList = null;
		List<JsonTopicRecord> targetedListFiltered = null;
		List<JsonTopicRecord> excelList = null;
		
        //--> with browser dialogbox: response.setHeader ("Content-disposition", "attachment; filename=\"edifactPayload.txt\"");
        response.setHeader ("Content-disposition", "filename=\"uoppListExcelView.xls\"");

		if(appUser==null){
			return this.loginView;
		}else{
			targetedList = (List)session.getAttribute(session.getId() + UoppdragConstants.SESSION_UOPP_SUBSET_LIST);
			targetedListFiltered = (List)session.getAttribute(session.getId() + UoppdragConstants.SESSION_UOPP_SUBSET_LIST_FILTERED);
			excelList = targetedList;
			if(targetedListFiltered!=null && targetedListFiltered.size()>0){
				excelList = targetedListFiltered;
			}
		}	
		//this name is the one configured in /WEB-INF/views.xml
		final String EXCEL_VIEW = "uoppListExcelView";
        
		return new ModelAndView(EXCEL_VIEW, "listUoppdrag", excelList);
        
	}
}

