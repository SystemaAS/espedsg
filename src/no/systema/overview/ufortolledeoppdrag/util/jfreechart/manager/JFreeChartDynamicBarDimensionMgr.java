/**
 * 
 */
package no.systema.overview.ufortolledeoppdrag.util.jfreechart.manager;

import java.util.*;

import org.apache.log4j.Logger;

import jsonjackson.overview.ufortolledeoppdrag.JsonTesterTopic;

import no.systema.overview.ufortolledeoppdrag.model.jsonjackson.JsonTopicRecordDiagramChart;
import no.systema.overview.ufortolledeoppdrag.model.jsonjackson.JsonTopicRecord;
/**
 * Implementation for a dynamic (days) chart
 * The user profile's interval and color bars are used here
 * 
 * 
 * 
 * @author oscardelatorre
 * @date Sep 12, 2013
 * 
 * 
 */
public class JFreeChartDynamicBarDimensionMgr implements IJFreeChartDimension{
	private static final Logger logger = Logger.getLogger(JFreeChartDynamicBarDimensionMgr.class.getName());
	
	private final String COLOR_GRAY="gray";
	private final String COLOR_GREY="grey";
	private final String COLOR_GREEN="green";
	private final String COLOR_YELLOW="yellow";
	private final String COLOR_RED="red";
	
	private Map<Integer, Integer> categoryMap = new HashMap<Integer, Integer>();
	public void setCategoryMap(Map<Integer, Integer> value){ this.categoryMap = value; }
	public Map<Integer, Integer> getCategoryMap(){ return this.categoryMap;}

	private Collection<JsonTopicRecordDiagramChart> chartList = null;
	public Collection<JsonTopicRecordDiagramChart> getChartList(){ return this.chartList; }
	
	private int totalNumberOfCategories = 0;
	public int getTotalNumberOfCategories(){ return this.totalNumberOfCategories;}

	
	private int lowerLimit = 0;
	public void setLowerLimit(int value){ this.lowerLimit = value; }
	public int getLowerLimit(){ return this.lowerLimit;}

	private int upperLimit = 10;
	public void setUpperLimit(int value){ this.upperLimit = value; }
	public int getUpperLimit(){ return this.upperLimit;}
	
	private int intervalLength = 3;
	public void setIntervalLength(int value){ this.intervalLength = value; }
	public int getIntervalLength(){ return this.intervalLength;}
	
	private int greyLimit = -1;
	public void setGreyLimit(int value){ this.greyLimit = value; }
	public int getGreyLimit(){ return this.greyLimit;}
	
	private int grayLimit = -1;
	public void setGrayLimit(int value){ this.grayLimit = value; }
	public int getGrayLimit(){ return this.grayLimit;}
	
	private int greenLimit = 3;
	public void setGreenLimit(int value){ this.greenLimit = value; }
	public int getGreenLimit(){ return this.greenLimit;}
	
	private int yellowLimit = 6;
	public void setYellowLimit(int value){ this.yellowLimit = value; }
	public int getYellowLimit(){ return this.yellowLimit;}
	
	private int redLimit = 10;
	public void setRedLimit(int value){ this.redLimit = value; }
	public int getRedLimit(){ return this.redLimit;}
	
	List<Integer> grayList = new ArrayList();
	public void addToGrayList (int value){ this.grayList.add(value); }
	public List<Integer>getGrayList (){ return this.grayList; }
	
	List<Integer> greyList = new ArrayList();
	public void addToGreyList (int value){ this.greyList.add(value); }
	public List<Integer>getGreyList (){ return this.greyList; }
	
	List<Integer> greenList = new ArrayList();
	public void addToGreenList (int value){ this.greenList.add(value); }
	public List<Integer>getGreenList (){ return this.greenList; }
	
	List<Integer> yellowList = new ArrayList();
	public void addToYellowList (int value){ this.yellowList.add(value); }
	public List<Integer>getYellowList (){ return this.yellowList; }
	
	List<Integer> redList = new ArrayList();
	public void addToRedList (int value){ this.redList.add(value); }
	public List<Integer>getRedList (){ return this.redList; }
	
	
	/**
	 * 
	 * @param ufortList
	 * @param chartList
	 * 
	 */
	public void buildChart(Collection<JsonTopicRecord> ufortList, Collection<JsonTopicRecordDiagramChart> chartListParam){
		String prompt= "["+ JFreeChartDynamicBarDimensionMgr.class.getCanonicalName() + "]";
		//we must sort in ascending order in order to secure the scale in the x-axis
		this.chartList = chartListParam;
		Collections.sort((List)this.chartList);
		
		int categoryCounter = 0;
		int mapId = 0;
		Iterator it = this.chartList.iterator();
		while(it.hasNext()){
			JsonTopicRecordDiagramChart chartRecord = (JsonTopicRecordDiagramChart)it.next();
			int numberOfDagerPerCategory = 0;
			boolean categoryValueExists = false;
			//Debug
			for(JsonTopicRecord record : ufortList){
				//logger.info( prompt + " Dager:" + record.getDager());
				
				if(record.getDager()==null || "".equals(record.getDager())){
					record.setDager("0");		    						
				}
				try{
					Integer tmp = Integer.parseInt(record.getDager());
					if(chartRecord.isSingleValue()){
						if(tmp==chartRecord.getStart()){
							numberOfDagerPerCategory++;
							chartRecord.incrementCounterForThisCategory();
							mapId = categoryCounter;
							//mapId = categoryCounter + "_" + chartRecord.getStart();
							//logger.info(" | Color:" + chartRecord.getColor());
							//logger.info(" | Limits:" + chartRecord.getStart() + " to " + chartRecord.getEnd());
							//logger.info(" NR in this CAT: " + chartRecord.getNumberOfDager()); 

							this.categoryMap.put(mapId, numberOfDagerPerCategory);
							if(!categoryValueExists){
								//we must save the total of categories IF and only IF there is at least one value
								categoryValueExists = true;
							}
						}
					}else{
						if(tmp>=chartRecord.getStart() && tmp<=chartRecord.getEnd()){
							numberOfDagerPerCategory++;
							chartRecord.incrementCounterForThisCategory();
							mapId = categoryCounter;
							//logger.info(" | Color:" + chartRecord.getColor());
							//logger.info(" | Limits:" + chartRecord.getStart() + " to " + chartRecord.getEnd());
							//logger.info(" NR in this CAT: " + chartRecord.getNumberOfDager()); 

							this.categoryMap.put(mapId, numberOfDagerPerCategory);
							if(!categoryValueExists){
								//we must save the total of categories IF and only IF there is at least one value
								categoryValueExists = true;
							}
						}
					}
					
				}catch(Exception e){
					e.printStackTrace();
				}
			}
			
			//now some category specific crucial values or behaviors 
			if(categoryValueExists){
				this.totalNumberOfCategories++;
				logger.info("NR of CATEGORIES: "+ this.totalNumberOfCategories);
				//logger.info();
				
			}else{
				it.remove();
			}
			categoryCounter++;
			
		}
		//Now we must sort the 
		//Debug
		Iterator iter = this.categoryMap.entrySet().iterator();
	    while (iter.hasNext()) {
	        Map.Entry pairs = (Map.Entry)iter.next();
	        logger.info(prompt + "MAP ###############" + pairs.getKey() + " = " + pairs.getValue());
	        //iter.remove(); // avoids a ConcurrentModificationException
	    }
		logger.info("AA");
	}
	
}
