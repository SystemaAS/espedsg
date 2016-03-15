/**
 * 
 */
package no.systema.overview.ufortolledeoppdrag.model.jsonjackson;

import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

/**
 * The class must implement Comparable in order to be sorted (at some point when used) in a given list
 * 
 * @author oscardelatorre
 * @date Sep 12, 2013
 * 
 */
public class JsonTopicRecordDiagramChart implements Comparable<JsonTopicRecordDiagramChart>  {
	//This field is required in order to handle the order of categories that the user has
	//The order of colors is dynamic but the category id is always ASC in the chartList (JSON)
	private Integer categoryId = 0;
	public void setCategoryId(Integer value) {  this.categoryId = (value !=null) ? value : 0; }
	public Integer getCategoryId() { return this.categoryId;}
	
	private Integer start = 0;
	public void setStart(Integer value) {  this.start = (value !=null) ? value : 0; }
	public Integer getStart() { return this.start;}
	
	private Integer end = 0;
	public void setEnd(Integer value) {  this.end = (value !=null) ? value : 0; }
	public Integer getEnd() { return this.end;}
	
	private String color = null;
	public void setColor(String value) {  this.color = value; }
	public String getColor() { return this.color.trim().toLowerCase();}
	
	private boolean singleValue = false;
	public boolean isSingleValue() { 
		//when the interval is only one value
		if(this.start == this.end){
			return true;
		}else{
			return this.singleValue;
		}
	}
	
	
	private Integer numberOfDager = 0;
	public void incrementCounterForThisCategory() {  this.numberOfDager++; }
	public Integer getNumberOfDager() { return this.numberOfDager;}
	public void setNumberOfDager( Integer value) {  this.numberOfDager = value;}
	
	
	
	
	/**
	 * allows for a primary sort by "start" attribute
	 */
	@Override
	public int compareTo(JsonTopicRecordDiagramChart record) {
		//must do an explicit convert in order to sort by "dager" numerical
		int d1 = this.start;
		int d2 = record.start;
		//this will give the desired effect with natural order (asc =1) and reverseOrder (desc = -1)
		return d1 > d2 ? 1 : (d1 < d2 ? -1 : 0);
	}
	/**
	 * Used for java reflection in other classes
	 * @return
	 * @throws Exception
	 */
	public List<Field> getFields() throws Exception{
		Class cl = Class.forName(this.getClass().getCanonicalName());
		Field[] fields = cl.getDeclaredFields();
		List<Field> list = Arrays.asList(fields);
		
		return list;
	}

	
}
