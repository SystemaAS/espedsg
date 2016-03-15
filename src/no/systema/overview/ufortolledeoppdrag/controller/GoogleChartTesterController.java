package no.systema.overview.ufortolledeoppdrag.controller;

import java.util.*;

import org.apache.log4j.Logger;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.ui.ModelMap;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.ServletRequestDataBinder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Required;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartUtilities;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.labels.*;
import org.jfree.chart.axis.*;
import org.jfree.chart.urls.StandardCategoryURLGenerator;
import org.jfree.data.general.*;

import javax.servlet.http.*;




/**
 * 
 * @author oscardelatorre
 * @date Sep 18, 2013
 * 
 */

@Controller
public class GoogleChartTesterController {
	private static final Logger logger = Logger.getLogger(GoogleChartTesterController.class.getName());
	
	/**
	 */
	@RequestMapping(value="uoppdraggate_gc.do", method={RequestMethod.GET})
	public ModelAndView init(HttpServletRequest request, HttpServletResponse response){
		ModelAndView successView = new ModelAndView("uoppdraggate_gc");
		
		return successView;
	}
	
	
	
}

