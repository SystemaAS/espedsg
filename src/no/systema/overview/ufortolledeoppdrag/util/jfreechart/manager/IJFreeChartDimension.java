/**
 * 
 */
package no.systema.overview.ufortolledeoppdrag.util.jfreechart.manager;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;

import no.systema.overview.ufortolledeoppdrag.model.jsonjackson.JsonTopicRecord;
import no.systema.overview.ufortolledeoppdrag.model.jsonjackson.JsonTopicRecordDiagramChart;

/**
 * This is the interface for all jFree charts special implementations
 * (Daily, every 2 days, every 3 days or other)
 * 
 * The standard implementation will produce jfree chart per day (daily basis)
 * 
 * @author oscardelatorre
 * @date Sep 8, 2013
 * 
 *
 */
public interface IJFreeChartDimension {
	
	public void buildChart(Collection<JsonTopicRecord> ufortList, Collection<JsonTopicRecordDiagramChart> chartList);
	
	public void setCategoryMap(Map<Integer, Integer> value);
	public Map<Integer, Integer> getCategoryMap();
	//chart dimensions
	public Collection<JsonTopicRecordDiagramChart>getChartList();
	//total number of categories
	public int getTotalNumberOfCategories();
	

	//domain limit values (lower and upper)
	public void setLowerLimit(int value);
	public int getLowerLimit();
	public void setUpperLimit(int value);
	public int getUpperLimit();
	public void setIntervalLength(int value);
	public int getIntervalLength();
	
	
	//color limits
	public void setGreenLimit(int value);
	public int getGreenLimit();
	public void setYellowLimit(int value);
	public int getYellowLimit();
	public void setRedLimit(int value);
	public int getRedLimit();
	public void setGrayLimit(int value);
	public int getGrayLimit();
	
	//color lists
	public void addToGreenList (int value);
	public List<Integer>getGreenList ();
	public void addToYellowList (int value);
	public List<Integer>getYellowList ();
	public void addToRedList (int value);
	public List<Integer>getRedList ();
	public void addToGrayList (int value);
	public List<Integer>getGrayList ();
	
	
	


}
