/**
 * 
 */
package no.systema.skat.skatimport.model.jsonjackson.topic.items;

import java.util.Collection;



/**
 * @author oscardelatorre
 * @date May 21, 2014
 * 
 */
public class JsonSkatImportSpecificTopicItemToldvaerdiTransportContainer {
	private String user = null;
	public void setUser(String value) {  this.user = value; }
	public String getUser() { return this.user;}
	
	
	private String errMsg = null;
	public void setErrMsg(String value) {  this.errMsg = value; }
	public String getErrMsg() { return this.errMsg;}
	
	private Collection<JsonSkatImportSpecificTopicItemToldvaerdiRecord> t17at19calc;
	public void setT17at19calc(Collection<JsonSkatImportSpecificTopicItemToldvaerdiRecord> value){ this.t17at19calc = value; }
	public Collection<JsonSkatImportSpecificTopicItemToldvaerdiRecord> getT17at19calc(){ return t17at19calc; }
	
	
}
