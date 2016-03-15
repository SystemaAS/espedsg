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

import com.googlecode.charts4j.Color;
import com.googlecode.charts4j.*;




/**
 * 
 * @author oscardelatorre
 * @date Sep 18, 2013
 * 
 */

@Controller
public class Chart4jTesterController {
	private static final Logger logger = Logger.getLogger(Chart4jTesterController.class.getName());
	private ModelAndView loginView = new ModelAndView("login");
	
	
	@RequestMapping(value="uoppdraggate_c4j.do", method={RequestMethod.GET})
	public String drawPieAndBarChart(ModelMap model){
		//---------
		//Pie Chart
		//---------
		Slice s1 = Slice.newSlice(15, Color.newColor("CACACA"),"Mac","Mac");
		Slice s2 = Slice.newSlice(50, Color.newColor("DF7417"),"Windows","Windows");
		Slice s3 = Slice.newSlice(25, Color.newColor("951800"),"Linux","Linux");
		Slice s4 = Slice.newSlice(10, Color.newColor("01A1DB"),"Others","Others");
		
		PieChart pieChart = GCharts.newPieChart(s1,s2,s3,s4);
		pieChart.setTitle("Google Pie chart (chart4j) at Systema");
		pieChart.setSize(500,200);
		pieChart.setThreeD(true);
		
		model.addAttribute("pieUrl", pieChart.toURLString());
		
		//----------
		//Bar Chart
		//----------
		BarChartPlot plot1 = Plots.newBarChartPlot(Data.newData(25, 43, 12, 30));
		plot1.setColor(Color.GREEN,0);
		plot1.setColor(Color.YELLOW,1);
		plot1.setColor(Color.RED,2);
		plot1.setColor(Color.AQUAMARINE,3);
		
        //BarChartPlot plot2 = Plots.newBarChartPlot(Data.newData(8, 35, 11, 5), Color.ORANGERED, "Team B");
        //BarChartPlot plot3 = Plots.newBarChartPlot(Data.newData(10, 20, 30, 30), Color.LIMEGREEN, "Team C");
      
        // Instantiating chart.
        //BarChart chart = GCharts.newBarChart(plot1, plot2, plot3);
		BarChart chart = GCharts.newBarChart(plot1);
		
        // Defining axis info and styles
        AxisStyle axisStyle = AxisStyle.newAxisStyle(Color.BLACK, 13, AxisTextAlignment.CENTER);
        AxisLabels oppdrag = AxisLabelsFactory.newAxisLabels("Ufortollede oppdrag", 50.0);
        oppdrag.setAxisStyle(axisStyle);
        AxisLabels dager = AxisLabelsFactory.newAxisLabels("Dager", 50.0);
        dager.setAxisStyle(axisStyle);

        // Adding axis info to chart.
        chart.addXAxisLabels(AxisLabelsFactory.newAxisLabels("0-3", "4-7", "8-10", "10-9999"));
        chart.addYAxisLabels(AxisLabelsFactory.newNumericRangeAxisLabels(0, 100));
        chart.addYAxisLabels(oppdrag);
        chart.addXAxisLabels(dager);

        chart.setSize(600, 450);
        chart.setBarWidth(100);
        chart.setSpaceWithinGroupsOfBars(20);
        chart.setDataStacked(true);
        chart.setTitle("Ufortollede Oppdrag", Color.BLACK, 16);
        chart.setGrid(100, 10, 3, 2);
        chart.setBackgroundFill(Fills.newSolidFill(Color.ANTIQUEWHITE));
        LinearGradientFill fill = Fills.newLinearGradientFill(0, Color.LAVENDER, 100);
        fill.addColorAndOffset(Color.WHITE, 0);
        chart.setAreaFill(fill);
        
		
        model.addAttribute("barUrl", chart.toURLString());
		
		
		//name of viewer (JSP)
		return "uoppdraggate_c4j";
	}	
}

