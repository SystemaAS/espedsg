/**
 * 
 */
package no.systema.skat.skatexport.model.jsonjackson.topic.items;

import java.util.Collection;



/**
 * @author oscardelatorre
 * @date Mar 10, 2014
 * 
 */
public class JsonSkatExportSpecificTopicItemToldvaerdiContainer {
	private String user = null;
	public void setUser(String value) {  this.user = value; }
	public String getUser() { return this.user;}
	
	
	private String errMsg = null;
	public void setErrMsg(String value) {  this.errMsg = value; }
	public String getErrMsg() { return this.errMsg;}
	
	
	private Collection<JsonSkatExportSpecificTopicItemToldvaerdiRecord> tv1calc;
	public void setTv1calc(Collection<JsonSkatExportSpecificTopicItemToldvaerdiRecord> value){ this.tv1calc = value; }
	public Collection<JsonSkatExportSpecificTopicItemToldvaerdiRecord> getTv1calc(){ return tv1calc; }
	
	
	
}
