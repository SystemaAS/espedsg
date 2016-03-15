
/**
 * 
 */
package no.systema.overview.ufortolledeoppdrag.util.jfreechart;

import org.jfree.*;

import java.io.*;
import java.util.*;
import java.text.NumberFormat;
import java.awt.Font;
import java.awt.Color;
import org.apache.log4j.Logger;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartUtilities;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.labels.*;
import org.jfree.chart.axis.*;
import org.jfree.chart.urls.StandardCategoryURLGenerator;
import org.jfree.ui.TextAnchor;
import org.jfree.chart.renderer.category.BarRenderer;
import org.jfree.data.category.DefaultCategoryDataset;

import no.systema.overview.ufortolledeoppdrag.controller.UoppdragGateController;
import no.systema.overview.ufortolledeoppdrag.model.jsonjackson.JsonTopicRecordDiagramChart;
import no.systema.overview.ufortolledeoppdrag.util.jfreechart.manager.IJFreeChartDimension;

/**
 * jFreeChart class for showing the general overview distribution of ufortollede oppdrag
 * 
 * @author oscardelatorre
 * @date Aug 27, 2013
 * 
 * 
 */
public class GeneralDistributionChart {
	private static final Logger logger = Logger.getLogger(GeneralDistributionChart.class.getName());
	
	private static final String FILE_TYPE_PNG = ".png";
	private static final String FILE_TYPE_JPEG = ".jpg";
	//chart dimensions
	private static final int CHART_WIDTH = 1000;
    private static final int CHART_HEIGHT = 430; //max(440) since the monitor/display otherwise would have to scroll after each refresh...
    
	
	
	/**
	 * Here is where the bulk of the chart construction takes place
	 * 
	 * We build the chart through 3 different steps
	 * (1) Build the categoryDataset (dynamically) based on "Dager" or other key y-axis of the diagram
	 * (2) Define and create the JFreeChart object (with the dataset created in step (1)
	 * (3) Define the colors of the bars (dynamically)
	 * 
	 * @param jfreeChartDimensionMgr
	 * 
	 */
	public void produceBarChartJPEG_DynamicDays(IJFreeChartDimension jfreeChartDimensionMgr){
		Collection<JsonTopicRecordDiagramChart> chartList = jfreeChartDimensionMgr.getChartList();
		Map<Integer, Integer> categoryMap = jfreeChartDimensionMgr.getCategoryMap();
		
		DefaultCategoryDataset  categoryDataset = new DefaultCategoryDataset();
        String columnPrefix = "dg ";
        //chart legends
        final String CHART_TITLE = " Ufortollede oppdrag per dag ";
        final String AXIS_Y = "Antall ufortollede oppdrag";
        final String AXIS_X = null;
        
        //--------------------------------------------------------
        //(1) BUILD the categoryDataset (based on "Dager"
        //
        //iterate through chartList meta record and child map
        //there is no other way to make this dynamically otherwise
        //--------------------------------------------------------

	    //Now we must interact with
        //(1) The chart list (since this has the order asc of the intervals (bars)
        //(2) The Map that has the accumulated number of days for the category (chart list already ordered ASC)
        for(JsonTopicRecordDiagramChart chartRecord : chartList){
        		//fetch the Map now (for each chartRecord)
        		logger.info("START------ Category: " + chartRecord.getCategoryId());
        		logger.info("START------------");
        		
        		Iterator it = categoryMap.entrySet().iterator();
		    while (it.hasNext()) {
		        Map.Entry pairs = (Map.Entry)it.next();
		        int key = (Integer)pairs.getKey();
		        if(key==chartRecord.getCategoryId()){
		        		//logger.info("MAP ###############" + pairs.getKey() + " = " + pairs.getValue());
		        		//it.remove(); // avoids a ConcurrentModificationException
		        		String barName = "";
		        		if(chartRecord.isSingleValue()){
		        			barName = columnPrefix + String.valueOf(chartRecord.getStart());
	        				//logger.info("MATCH on single value **********" + key);
	        				categoryDataset.addValue((Integer)pairs.getValue(), barName,"");
		        			logger.info("Bar name:" + barName);
		        			
		        		}else{
		        			if(chartRecord.getStart()==-9999){
		        				barName = "<" + String.valueOf(chartRecord.getEnd());
		        			}else if(chartRecord.getEnd()==9999){
		        				barName =  String.valueOf(chartRecord.getStart() + "<");
		        			}else{
		        				barName = columnPrefix + String.valueOf(chartRecord.getStart()) + "-" + String.valueOf(chartRecord.getEnd());
		        			}
		        			categoryDataset.addValue((Integer)pairs.getValue(), barName,"");
		        			logger.info("Bar name::" + barName);
		        			logger.info("**TOTAL NR CAT:" + (Integer)pairs.getValue());		        			
		        			
		        		}
		        }
		    }
		    
	    }
        //------------------------------------------------------------------
        //(2) Create the chart (init) with the newly created categoryDataset
        //------------------------------------------------------------------
        JFreeChart chart = ChartFactory.createBarChart3D
                     ("Ufortollede oppdrag (c) www.systema.no", // Title
                      AXIS_X,              // X-Axis label
                      AXIS_Y,// Y-Axis label
                      categoryDataset,         // Dataset
                      PlotOrientation.VERTICAL,
                      true,                     // Show legend
                      true,
                      false
                     );
        //make some changes on the chart. In our case we must remove the legend (footer)
        chart.removeLegend();
        
        //move the text inside the 3D-Bar
        CategoryPlot plot = (CategoryPlot) chart.getPlot();
        BarRenderer barRenderer = (BarRenderer) plot.getRenderer();
        
        //--------------------------------------------------------
        //(3) SET barRenderer Series colors
        // The implementation defaults to the gray scale IN CASE no green, yellow, red schema
        	// has been setup in the user profile at the back-end
        //
        // Iterate through chartList meta record and child map
        // There is no other way to make this dynamically otherwise
        //--------------------------------------------------------
        int seriesCounter = 0;
        for(JsonTopicRecordDiagramChart chartRecord : chartList){
	    		//fetch the Map now (for each chartRecord)
	    		Iterator it = categoryMap.entrySet().iterator();
		    while (it.hasNext()) {
		        Map.Entry pairs = (Map.Entry)it.next();
		        int key = (Integer)pairs.getKey();
		        Integer value = (Integer)pairs.getValue();
		        //logger.info("Map: " + key + value);
		        //logger.info(chartRecord.getColor());
		        if(key==chartRecord.getCategoryId()){
		        		it.remove(); // avoids a ConcurrentModificationException
		        		if(chartRecord.getColor().toLowerCase().contains("green")){
		        			barRenderer.setSeriesPaint(seriesCounter++, Color.green);
		        		}else if(chartRecord.getColor().toLowerCase().contains("yellow")){
		        			barRenderer.setSeriesPaint(seriesCounter++, Color.yellow);
		        		}else if(chartRecord.getColor().toLowerCase().contains("red")){
		        			barRenderer.setSeriesPaint(seriesCounter++, Color.red);
		        		}else if(chartRecord.getColor().toLowerCase().contains("gray")){
		        			barRenderer.setSeriesPaint(seriesCounter++, Color.gray);
		        		}else if(chartRecord.getColor().toLowerCase().contains("grey")){
		        			barRenderer.setSeriesPaint(seriesCounter++, Color.gray);
		        		}else{
		        			barRenderer.setSeriesPaint(seriesCounter++, Color.pink);
		        		}
		        }
		    }
        }
        barRenderer.setBaseItemLabelGenerator(
        	    new StandardCategoryItemLabelGenerator("{0}", NumberFormat.getInstance())); //could be "{0} {1} {2} {3}"
        barRenderer.setBaseItemLabelFont(new Font("Verdana", Font.PLAIN, 11));
        barRenderer.setBaseItemLabelsVisible(true);
        
        
        /*Maybe URL inside an element ???
          ItemLabelPosition itemlabelposition1 = new ItemLabelPosition(ItemLabelAnchor.OUTSIDE12, TextAnchor.CENTER_LEFT, TextAnchor.CENTER_LEFT,
                -1.5707963267948966D);
          barRenderer.setPositiveItemLabelPositionFallback(itemlabelposition1);
          CategoryAxis categoryaxis = plot.getDomainAxis();
          categoryaxis.setCategoryLabelPositions(CategoryLabelPositions.UP_45);
          //categoryaxis.setLabelPaint(gp0);
          barRenderer.setBaseToolTipGenerator(new StandardCategoryToolTipGenerator());
          barRenderer.setBaseItemURLGenerator(new StandardCategoryURLGenerator(" ", "series","category"));
        	  barRenderer.setBaseItemURLGenerator(new StandardCategoryURLGenerator("www.google.com"));
        */
        
        //change cosmetics
        chart.setTitle(new org.jfree.chart.title.TextTitle(CHART_TITLE, new java.awt.Font("SansSerif", java.awt.Font.BOLD, 18)));
        
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
