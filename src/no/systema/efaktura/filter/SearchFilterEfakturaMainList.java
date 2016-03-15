/**
 * 
 */
package no.systema.efaktura.filter;

import java.lang.reflect.Field;
import java.util.*;

import org.apache.log4j.Logger;

/**
 * This search class is used at the GUI search behavior
 * It is MANDATORY to have the same attribute name convention as the JSON-object fetched from the JSON-payload at the back-end.
 * The reason for this is the java-reflection mechanism used when searching (since no SQL or other mechanism is used)
 * By using java reflection to match the object fields, these 2 (the JSON object and its SearchFilter object) must have the same attribute name 
 * 
 * @author oscardelatorre
 * @date   Jun 22, 2015
 * 
 */
public class SearchFilterEfakturaMainList {
	private static final Logger logger = Logger.getLogger(SearchFilterEfakturaMainList.class.getName());
	
	private String avd = null;
	public void setAvd(String value) {  this.avd = value; }
	public String getAvd() { return this.avd;}
	
	private String faktnr = null;
	public void setFaktnr(String value) {  this.faktnr = value; }
	public String getFaktnr() { return this.faktnr;}
	
	private String kundenr = null;
	public void setKundenr(String value) {  this.kundenr = value; }
	public String getKundenr() { return this.kundenr;}
	
	private String rfa = null;
	public void setRfa(String value) {  this.rfa = value; }
	public String getRfa() { return this.rfa;}
	
	private String from = null;
	public void setFrom(String value) {  this.from = value; }
	public String getFrom() { return this.from;}
	
	private String to = null;
	public void setTo(String value) {  this.to = value; }
	public String getTo() { return this.to;}
	
	private String status = null;
	public void setStatus(String value) {  this.status = value; }
	public String getStatus() { return this.status;}
	
	/**
	 * Gets the populated values by reflection
	 * @param searchFilte
	 * 
	 * @return
	 * @throws Exception
	 */
	public Map<String,String> getPopulatedFields() throws Exception{
		Map<String,String> map = new HashMap<String,String>();
		
		Class cl = Class.forName(this.getClass().getCanonicalName());
		Field[] fields = cl.getDeclaredFields();
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
