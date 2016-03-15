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
public class JFreeChartTesterController {
	private static final Logger logger = Logger.getLogger(JFreeChartTesterController.class.getName());
	
	
	@RequestMapping(value="imgPieChart.do", method={RequestMethod.GET})
	public void drawPieChart(HttpServletResponse response){
		
		//---------
		//Pie Chart
		//---------
		response.setContentType("image/png");
		
		DefaultPieDataset pdSet = new DefaultPieDataset();
		pdSet.setValue("Mac", 21);
		pdSet.setValue("Linux", 30);
		pdSet.setValue("Windows", 40);
		pdSet.setValue("Others", 9);
		String title = "Ufortollede Oppdrag (c) www.systema.no";
		JFreeChart chart = ChartFactory.createPieChart3D (title, pdSet,true,true,false );
		
		try{
			ChartUtilities.writeChartAsPNG(response.getOutputStream(), chart, 500, 200);
			response.getOutputStream().close();
		}catch (Exception e){
			e.printStackTrace();
		}
		
	}
	
	/**
	 */
	@RequestMapping(value="uoppdraggate_NotActiveJFreeChart.do", method={RequestMethod.GET})
	public ModelAndView init(HttpServletRequest request, HttpServletResponse response){
		
		return new ModelAndView("uoppdraggate");
	}
	
	
	
}

