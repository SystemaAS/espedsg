/**
 * 
 */
package no.systema.overview.ufortolledeoppdrag.filter;

import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;

/**
 * @author oscardelatorre
 * @date Aug 29, 2013
 * 
 */
public class SearchFilterGateChart extends SearchFilterUoppdrag {
	//private static final Logger logger = Logger.getLogger(SearchFilterGateChart.class.getName());
	
	private String autoRefresh = null;
	public void setAutoRefresh(String value) {  this.autoRefresh = value; }
	public String getAutoRefresh() { return this.autoRefresh;}
	
	/**
	 * Gets the populated values by reflection
	 * @param searchFilter
	 * @return
	 * @throws Exception
	 */
	public Map<String,String> getPopulatedFields() throws Exception{
		Map<String,String> map = new HashMap<String,String>();
		
		Class cl = Class.forName(this.getClass().getCanonicalName());
		//we must access the super class fields in this filter
		Class cls = cl.getSuperclass();
		
		Field[] fields = cls.getDeclaredFields();
		List<Field> list = Arrays.asList(fields);
		for(Field field : list){
			field.setAccessible(true);
			//logger.info("FIELD NAME: " + field.getName() + "VALUE:" + (String)field.get(this));
			String value = (String)field.get(this);
			if(value!=null && !"".equals(value)){
				//logger.info(field.getName() + " Value:" + value);
				map.put(field.getName(), value);
			}
		}
		
		return map;
	}
	
	
}
