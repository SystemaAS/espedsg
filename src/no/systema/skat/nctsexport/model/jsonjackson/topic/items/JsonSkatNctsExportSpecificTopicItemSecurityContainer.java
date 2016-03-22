/**
 * 
 */
package no.systema.skat.nctsexport.model.jsonjackson.topic.items;

import java.util.Collection;



/**
 * @author oscardelatorre
 * @date Mar 22, 2016
 *
 */
public class JsonSkatNctsExportSpecificTopicItemSecurityContainer {
	private String user = null;
	public void setUser(String value) {  this.user = value; }
	public String getUser() { return this.user;}
	
	private String avd = null;
	public void setAvd(String value) {  this.avd = value; }
	public String getAvd() { return this.avd;}
	
	private String opd = null;
	public void setOpd(String value) {  this.opd = value; }
	public String getOpd() { return this.opd;}
	
	private String lin = null;
	public void setLin(String value) {  this.lin = value; }
	public String getLin() { return this.lin;}
	
	private String errMsg = null;
	public void setErrMsg(String value) {  this.errMsg = value; }
	public String getErrMsg() { return this.errMsg;}
		
	private Collection<JsonSkatNctsExportSpecificTopicItemSecurityRecord> securityline;
	public void setSecurityline(Collection<JsonSkatNctsExportSpecificTopicItemSecurityRecord> value){ this.securityline = value; }
	public Collection<JsonSkatNctsExportSpecificTopicItemSecurityRecord> getSecurityline(){ return securityline; }
	
}
