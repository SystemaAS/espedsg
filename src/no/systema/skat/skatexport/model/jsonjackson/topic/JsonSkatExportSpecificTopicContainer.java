/**
 * 
 */
package no.systema.skat.skatexport.model.jsonjackson.topic;

import java.util.Collection;



/**
 * @author oscardelatorre
 * @date Feb 26, 2014
 *
 */
public class JsonSkatExportSpecificTopicContainer {
	private String user = null;
	public void setUser(String value) {  this.user = value; }
	public String getUser() { return this.user;}
	
	private String avd = null;
	public void setAvd(String value) {  this.avd = value; }
	public String getAvd() { return this.avd;}
	
	private String opd = null;
	public void setOpd(String value) {  this.opd = value; }
	public String getOpd() { return this.opd;}
	
	private String errMsg = null;
	public void setErrMsg(String value) {  this.errMsg = value; }
	public String getErrMsg() { return this.errMsg;}
	
	private Collection<JsonSkatExportSpecificTopicRecord> oneorder;
	public void setOneorder(Collection<JsonSkatExportSpecificTopicRecord> value){ this.oneorder = value; }
	public Collection<JsonSkatExportSpecificTopicRecord> getOneorder(){ return oneorder; }
	
	
}
