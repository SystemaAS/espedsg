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
public class JsonTrorOrderHeaderTrackAndTraceLoggingContainer {
	
	private String user = null;
	public void setUser(String value) {  this.user = value; }
	public String getUser() { return this.user;}
	
	private String avdelning = null;
	public void setAvdelning(String value) {  this.avdelning = value; }
	public String getAvdelning() { return this.avdelning;}
	
	private String oppdrag = null;
	public void setOppdrag(String value) {  this.oppdrag = value; }
	public String getOppdrag() { return this.oppdrag;}
	
	
	private String errMsg = null;
	public void setErrMsg(String value) {  this.errMsg = value; }
	public String getErrMsg() { return this.errMsg;}
	
	private Collection<JsonTrorOrderHeaderTrackAndTraceLoggingRecord> trackTraceEvents;
	public void setTrackTraceEvents(Collection<JsonTrorOrderHeaderTrackAndTraceLoggingRecord> value){ this.trackTraceEvents = value; }
	public Collection<JsonTrorOrderHeaderTrackAndTraceLoggingRecord> getTrackTraceEvents(){ return trackTraceEvents; }
	
	
}
