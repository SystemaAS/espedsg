/**
 * 
 */
package no.systema.overview.ufortolledeoppdrag.model.jsonjackson.tvinnlog;

import java.util.Collection;



/**
 * @author oscardelatorre
 * @date Oct 24, 2013
 *
 */
public class JsonTopicTvinnLogLargeTextContainer {
	private String user = null;
	public void setUser(String value) {  this.user = value; }
	public String getUser() { return this.user;}
	
	private String fmn = null;
	public void setFmn(String value) {  this.fmn = value; }
	public String getFmn() { return this.fmn;}
	
	private String errMsg = null;
	public void setErrMsg(String value) {  this.errMsg = value; }
	public String getErrMsg() { return this.errMsg;}
	
	private Collection<JsonTopicTvinnLogLargeTextRecord> loggtext;
	public void setLoggtext(Collection<JsonTopicTvinnLogLargeTextRecord> value){ this.loggtext = value; }
	public Collection<JsonTopicTvinnLogLargeTextRecord> getLoggtext(){ return loggtext; }
	
	
}
