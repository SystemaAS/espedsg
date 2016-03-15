/**
 * 
 */
package jfreechart;

import org.jfree.*;

import java.awt.Color;
import java.awt.Font;
import java.io.File;
import java.io.IOException;
import java.text.NumberFormat;
import java.util.Calendar;
import java.util.Date;
import org.jfree.chart.urls.CategoryURLGenerator;
import org.jfree.chart.urls.StandardCategoryURLGenerator;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartUtilities;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.labels.StandardCategoryItemLabelGenerator;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.renderer.category.BarRenderer;
import org.jfree.chart.renderer.category.CategoryItemRenderer;
import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.data.time.Day;
import org.jfree.data.time.TimeSeries;
import org.jfree.data.time.TimeSeriesCollection;

/**
 * @author oscardelatorre
 *
 */
public class JfreeChartBarTester {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		JfreeChartBarTester tester = new JfreeChartBarTester();
		tester.run();

	}
	
	
	public void run(){
 
        DefaultCategoryDataset  categoryDataset = new DefaultCategoryDataset();
 
        //Enrollment in Bachelors level
        //categoryDataset.setValue(2003, "Bachelors", "2005");
        //categoryDataset.setValue(1350, "Bachelors", "2006");
        //categoryDataset.setValue(2408, "Bachelors", "2007");
        //categoryDataset.setValue(2607, "Bachelors","2008");
        categoryDataset.setValue(5, "7 dgr el mer","");
 
        //Enrollment in Masters level
        //categoryDataset.setValue(985, "Masters", "2005");
        //categoryDataset.setValue(1400, "Masters", "2006");
        //categoryDataset.setValue(1634, "Masters", "2007");
        //categoryDataset.setValue(978, "Masters", "2008");
        categoryDataset.setValue(3, "4 til 6 dgr", "");
 
        
        //Enrollment in PhD level
        //categoryDataset.setValue(356, "PhD", "2005");
        //categoryDataset.setValue(390, "PhD", "2006");
        //categoryDataset.setValue(350, "PhD", "2007");
        //categoryDataset.setValue(687, "PhD", "2008");
        categoryDataset.setValue(1, "1 till 3 dgr", "");
        
        JFreeChart chart = ChartFactory.createBarChart3D
                     ("Ufortollede Oppdrag (c) www.systema.no", // Title
                      "Dager",              // X-Axis label
                      "Antall Ufortollede Oppdrag",// Y-Axis label
                      categoryDataset,         // Dataset
                      PlotOrientation.VERTICAL,
                      true,                     // Show legend
                      true,					   // Use tooltips
                      true 					   // Configure chart to generate URL's?
                     );
        
        chart.setTitle(new org.jfree.chart.title.TextTitle(" Ufortollede Oppdrag",new java.awt.Font("SansSerif", java.awt.Font.BOLD, 18)));
        //make some changes on the chart. In our case we must remove the legend (footer)
        //chart.removeLegend();
        
        //move the text inside the 3D-Bar
        CategoryPlot plot = (CategoryPlot) chart.getPlot();
        BarRenderer barRenderer = (BarRenderer) plot.getRenderer();
        barRenderer.setSeriesPaint(0, Color.green);
        barRenderer.setSeriesPaint(1, Color.yellow);
        barRenderer.setSeriesPaint(2, Color.red);
        
        //
        barRenderer.setBaseItemLabelGenerator(
        	    new StandardCategoryItemLabelGenerator("{0}", NumberFormat.getInstance())); //could be "{0} {1} {2} {3}"
        barRenderer.setBaseItemLabelFont(new Font("Verdana", Font.PLAIN, 12));
        barRenderer.setBaseItemLabelsVisible(true);
        //	CategoryURLGenerator generator = new StandardCategoryURLGenerator("http://www.google.com"); 
        barRenderer.setBaseItemURLGenerator(new StandardCategoryURLGenerator("http://www.google.com")); 
        //barRenderer.setBaseItemURLGenerator(generator);
        //categoryItemRenderer.setSeriesItemURLGenerator(1, generator);
        //categoryItemRenderer.setBaseItemURLGenerator(generator);
        //generator.generateURL(categoryDataset, 0, 0);
        //Save
        saveChart(chart);
    }
 
	/**
	 * 
	 * @param chart
	 */
    public void saveChart(JFreeChart chart){
        //String fileName="/ownfiles/" + Calendar.getInstance().getTimeInMillis() + "_myCategoryChart.jpg";
        String fileName="/ownfiles/myCategoryChart.jpg";
        try {
            /**
             * This utility saves the JFreeChart as a JPEG
             * First Parameter: FileName
             * Second Parameter: Chart To Save
             * Third Parameter: Height Of Picture
             * Fourth Parameter: Width Of Picture
             */
	        ChartUtilities.saveChartAsJPEG(new File(fileName), chart, 800, 600);
	    } catch (IOException e) {
	        e.printStackTrace();
	        System.err.println("Problem occurred creating chart.");
	    }
    }
    
	
}
