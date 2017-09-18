/**
 * 
 */
package no.systema.tror.model.jsonjackson.logging;

import java.util.Collection;



/**
 * @author oscardelatorre
 * @date Sep 18, 2017
 *
 */
public class JsonTrorOrderHeaderLoggingLargeTextContainer {
	private String user = null;
	public void setUser(String value) {  this.user = value; }
	public String getUser() { return this.user;}
	
	private String fmn = null;
	public void setFmn(String value) {  this.fmn = value; }
	public String getFmn() { return this.fmn;}
	
	private String errMsg = null;
	public void setErrMsg(String value) {  this.errMsg = value; }
	public String getErrMsg() { return this.errMsg;}
	
	private Collection<JsonTrorOrderHeaderLoggingLargeTextRecord> loggtext;
	public void setLoggtext(Collection<JsonTrorOrderHeaderLoggingLargeTextRecord> value){ this.loggtext = value; }
	public Collection<JsonTrorOrderHeaderLoggingLargeTextRecord> getLoggtext(){ return loggtext; }
	
	
}
