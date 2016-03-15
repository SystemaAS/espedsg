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

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartUtilities;
import org.jfree.chart.JFreeChart;
import org.jfree.data.general.DefaultPieDataset;
import org.jfree.chart.plot.PiePlot3D;

/**
 * @author oscardelatorre
 *
 */
public class JfreeChartPieTester {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		JfreeChartPieTester tester = new JfreeChartPieTester();
		tester.run();

	}
	
	/**
	 * 
	 */
	public void run(){
 
		DefaultPieDataset  pieDataset = new DefaultPieDataset();
 
		pieDataset.setValue("97%",new Integer(97));
        pieDataset.setValue("3%",new Integer(3));
	    
	    boolean legend = true;boolean tooltips = true;boolean urls = false;
	    
	    JFreeChart chart = ChartFactory.createPieChart3D("Leveringskvalitet", pieDataset, false, false, false);
	    PiePlot3D plot3 = (PiePlot3D) chart.getPlot();
	    plot3.setSectionPaint("97%", Color.green);
	    plot3.setSectionPaint("3%", Color.red);
	    
        plot3.setForegroundAlpha(0.6f);
        plot3.setCircular(true);
        
        //chart.setTitle(new org.jfree.chart.title.TextTitle(" Ufortollede Oppdrag",new java.awt.Font("SansSerif", java.awt.Font.BOLD, 18)));
        //make some changes on the chart. In our case we must remove the legend (footer)
        //chart.removeLegend();
        
        //move the text inside the 3D-Bar
        /*CategoryPlot plot = (CategoryPlot) chart.getPlot();
        BarRenderer barRenderer = (BarRenderer) plot.getRenderer();
        barRenderer.setSeriesPaint(0, Color.green);
        barRenderer.setSeriesPaint(1, Color.yellow);
        barRenderer.setSeriesPaint(2, Color.red);
        */
        //
        /*
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
         * 
         */
        
        saveChart(chart);
    }
 
	/**
	 * 
	 * @param chart
	 */
    public void saveChart(JFreeChart chart){
        //String fileName="/ownfiles/" + Calendar.getInstance().getTimeInMillis() + "_myCategoryChart.jpg";
        String fileName="/ownfiles/myPieChart.jpg";
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
