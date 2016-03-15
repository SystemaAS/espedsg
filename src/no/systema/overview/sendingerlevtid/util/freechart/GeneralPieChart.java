
/**
 * 
 */
package no.systema.overview.sendingerlevtid.util.freechart;


import org.apache.log4j.Logger;
import org.jfree.*;

import java.io.*;
import java.util.*;
import java.text.NumberFormat;
import java.awt.Font;
import java.awt.Color;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartUtilities;
import org.jfree.chart.JFreeChart;

import org.jfree.data.general.DefaultPieDataset;
import org.jfree.chart.plot.PiePlot3D;
import org.jfree.util.Rotation;
import org.jfree.chart.labels.StandardPieSectionLabelGenerator;

import no.systema.overview.sendingerlevtid.model.jsonjackson.JsonTopicContainer;
import no.systema.main.util.NumberFormatterLocaleAware;
/**
 * jFreeChart class for showing the general overview distribution of Measuring Quality (Sendinger och Leveringstid)
 * 
 * @author oscardelatorre
 * @date Oct 4, 2013
 * 
 * 
 */
public class GeneralPieChart {
	private static final Logger logger = Logger.getLogger(GeneralPieChart.class.getName());
	
	private static final String FILE_TYPE_PNG = ".png";
	private static final String FILE_TYPE_JPEG = ".jpg";
	//chart dimensions
	private static final int CHART_WIDTH = 400;
    private static final int CHART_HEIGHT = 180;
    //
    private final String PERCENTAGE_CHAR = "%";
    private 	NumberFormatterLocaleAware numberFormatter = new NumberFormatterLocaleAware();

    /**
     * 
     * @param jsonTopicContainer
     * @param type
     */
    public void produceChartJPEG(JsonTopicContainer jsonTopicContainer, String type){
		DefaultPieDataset  pieDataset = new DefaultPieDataset();
		String title = "default";
		Double okPercentageDbl = 0.00D;
		Double notOkPercentageDbl = 0.00D;
		
		if("levQA".equalsIgnoreCase(type)){
			title = "Leveringskvalitet";
			okPercentageDbl = this.numberFormatter.getDouble(jsonTopicContainer.getLeveringsKval());
			notOkPercentageDbl = 100 - okPercentageDbl;
		}else{
			title = "POD Archived-kvalitet";
			okPercentageDbl = this.numberFormatter.getDouble(jsonTopicContainer.getArchievedKval());
			notOkPercentageDbl = 100 - okPercentageDbl;
		}
		
		pieDataset.setValue(okPercentageDbl + PERCENTAGE_CHAR, new Double(okPercentageDbl));
        pieDataset.setValue(notOkPercentageDbl + PERCENTAGE_CHAR, new Double(notOkPercentageDbl));
	    
	    boolean legend = false; boolean tooltips = false; boolean urls = false;
	    //Now draw the pie with its 2 pieces here
	    JFreeChart chart = ChartFactory.createPieChart3D(title, pieDataset, legend, tooltips, urls);
	    PiePlot3D plot3 = (PiePlot3D) chart.getPlot();
    		plot3.setSectionPaint(okPercentageDbl + PERCENTAGE_CHAR, Color.green);
    		plot3.setSectionPaint(notOkPercentageDbl + PERCENTAGE_CHAR, Color.red);		
	    
	    //Cosmetics
	    plot3.setStartAngle(290);
        plot3.setDirection(Rotation.ANTICLOCKWISE);
        
        //Pie aspects
        plot3.setCircular(false);
	    plot3.setForegroundAlpha(0.6f);
	    plot3.setBackgroundAlpha(0.0f);
        plot3.setDarkerSides(true);
        plot3.setOutlineVisible(false); //frame / no frame
        plot3.setLabelFont(new Font("Verdana", Font.PLAIN, 10));
        //change font on title
        chart.setTitle(new org.jfree.chart.title.TextTitle(title ,new java.awt.Font("SansSerif", java.awt.Font.BOLD, 18)));
        
        
        //serialize the chart
        this.saveChartAsJPEG(chart);
    }
    
    
    
	
	/**
	 * Serializes as JPEG
	 * 
	 * @param chart
	 * @param dirPath
	 * @param fileName
	 */
    private void saveChartAsJPEG(JFreeChart chart){
        
        try {
            /**
             * This utility saves the JFreeChart as a JPEG
             * First Parameter: FileName
             * Second Parameter: Chart To Save
             * Third Parameter: Height Of Picture
             * Fourth Parameter: Width Of Picture
             */
        		logger.info(this.graphFileName);
	        ChartUtilities.saveChartAsJPEG(this.graphFileName, chart, CHART_WIDTH, CHART_HEIGHT);
	    } catch (IOException e) {
	        e.printStackTrace();
	        System.err.println("Problem occurred creating chart.");
	    }
    }
    
    
    
    
    private File graphFileName = null;
    public File getGraphFileName(){ return this.graphFileName; }
    /**
     * 
     * @param targetDirectory
     * @return
     */
    public void setGraphFileName(String targetDirectory){
        String targetAbsoluteDirectory = targetDirectory + "/";
        Random rn= new Random();
        int id =rn.nextInt(2000);
        //file name (unique)
        String fileName = String.valueOf(Calendar.getInstance().getTimeInMillis()) + "_" + id + this.FILE_TYPE_JPEG;
        //complete file object
        File chartFile = new File(targetAbsoluteDirectory + fileName);

        this.graphFileName = chartFile;
    }
    
    

	
}
