/**
 * 
 */
package no.systema.overview.ufortolledeoppdrag.model.jsonjackson;

import java.lang.reflect.Array;
import java.lang.reflect.Field;
import java.util.*;

/**
 * 
 * 
 * @author oscardelatorre
 * @date Oct 19, 2013
 * 
 */
public class JsonTopicRecordAvdGroup implements Comparable<JsonTopicRecordAvdGroup> {

	private String avdGrpNavn = null;
	public void setAvdGrpNavn(String value) {  this.avdGrpNavn = value; }
	public String getAvdGrpNavn() { return this.avdGrpNavn;}
	
	private String avdList = null;
	public void setAvdList(String value) {  this.avdList = value; }
	public String getAvdList() { return this.avdList;}
	
	/**
	 * this will sort by std default = "dager" (primary key)
	 */
	@Override
	public int compareTo(JsonTopicRecordAvdGroup record) {
		//NOT IMPLEMENTED yet
		return 0;
	}
	
	/**
	 * 
	 * will sort asc
	 *
	 */
	public static class OrderByName implements Comparator<JsonTopicRecordAvdGroup> {
        @Override
        public int compare(JsonTopicRecordAvdGroup o1, JsonTopicRecordAvdGroup o2) {
        		return o1.avdGrpNavn.compareTo(o2.avdGrpNavn);
        }	
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
