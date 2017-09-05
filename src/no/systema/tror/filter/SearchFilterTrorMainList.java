/**
 * 
 */
package no.systema.tror.filter;

import java.lang.reflect.Field;
import java.util.*;

import org.apache.log4j.Logger;

import no.systema.main.util.StringManager;

/**
 * This search class is used at the GUI search behavior
 * It is MANDATORY to have the same attribute name convention as the JSON-object fetched from the JSON-payload at the back-end.
 * The reason for this is the java-reflection mechanism used when searching (since no SQL or other mechanism is used)
 * By using java reflection to match the object fields, these 2 (the JSON object and its SearchFilter object) must have the same attribute name 
 * 
 * @author oscardelatorre
 * @date   Jun 24, 2016
 * 
 */
public class SearchFilterTrorMainList {
	private static final Logger logger = Logger.getLogger(SearchFilterTrorMainList.class.getName());
	private StringManager strMgr = new StringManager();
	
	private String avd = null;
	public void setAvd(String value) {  this.avd = value; }
	public String getAvd() { return this.avd;}
	
	private String sign = null;
	public void setSign(String value) {  this.sign = value; }
	public String getSign() { return this.sign;}
	
	private String orderNr = null;
	public void setOrderNr(String value) {  this.orderNr = value; }
	public String getOrderNr() { return this.orderNr;}
	
	private String date = null;
	public void setDate(String value) {  this.date = value; }
	public String getDate() { return this.date;}
	
	private String fromDate = null;
	public void setFromDate(String value) {  this.fromDate = value; }
	public String getFromDate() { return this.fromDate;}
	
	private String toDate = null;
	public void setToDate(String value) {  this.toDate = value; }
	public String getToDate() { return this.toDate;}
	
	private String sender = null;
	public void setSender(String value) {  this.sender = value; }
	public String getSender() { return this.sender;}
	
	private String receiver = null;
	public void setReceiver(String value) {  this.receiver = value; }
	public String getReceiver() { return this.receiver;}
	
	private String from = null;
	public void setFrom(String value) {  this.from = value; }
	public String getFrom() { return this.from;}
	
	private String to = null;
	public void setTo(String value) {  this.to = value; }
	public String getTo() { return this.to;}
	
	private String status = null;
	public void setStatus(String value) {  this.status = value; }
	public String getStatus() { return this.status;}
	
	private String ttype = null;
	public void setTtype(String value) {  this.ttype = value; }
	public String getTtype() { return this.ttype;}
	
	private String godsNr = null;
	public void setGodsNr(String value) {  this.godsNr = value; }
	public String getGodsNr() { return this.godsNr;}
	
	
	/**
	 * In order to check if ALL fields are empty
	 * @return
	 */
	public boolean isEmpty(){
		boolean retval = true;
		if(strMgr.isNotNull(this.getAvd())){
			retval = false;
		}
		if(strMgr.isNotNull(this.getOrderNr())){
			retval = false;
		}
		if(strMgr.isNotNull(this.getSign())){
			retval = false;
		}
		if(strMgr.isNotNull(this.getDate())){
			retval = false;
		}
		if(strMgr.isNotNull(this.getSender())){
			retval = false;
		}
		if(strMgr.isNotNull(this.getReceiver())){
			retval = false;
		}
		if(strMgr.isNotNull(this.getFrom())){
			retval = false;
		}
		if(strMgr.isNotNull(this.getTo())){
			retval = false;
		}
		
		
		return retval;
	}
	
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
