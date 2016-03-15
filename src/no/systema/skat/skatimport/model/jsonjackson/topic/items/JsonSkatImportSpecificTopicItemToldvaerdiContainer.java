/**
 * 
 */
package no.systema.skat.skatimport.model.jsonjackson.topic.items;

import java.util.Collection;



/**
 * @author oscardelatorre
 * @date Mar 07, 2014
 * 
 */
public class JsonSkatImportSpecificTopicItemToldvaerdiContainer {
	private String user = null;
	public void setUser(String value) {  this.user = value; }
	public String getUser() { return this.user;}
	
	
	private String errMsg = null;
	public void setErrMsg(String value) {  this.errMsg = value; }
	public String getErrMsg() { return this.errMsg;}
	
	private Collection<JsonSkatImportSpecificTopicItemToldvaerdiRecord> tv1calc;
	public void setTv1calc(Collection<JsonSkatImportSpecificTopicItemToldvaerdiRecord> value){ this.tv1calc = value; }
	public Collection<JsonSkatImportSpecificTopicItemToldvaerdiRecord> getTv1calc(){ return tv1calc; }
	
	
	
}
