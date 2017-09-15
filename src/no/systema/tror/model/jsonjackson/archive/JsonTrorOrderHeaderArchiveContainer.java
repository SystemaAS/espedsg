/**
 * 
 */
package no.systema.tror.model.jsonjackson.archive;

import java.util.Collection;



/**
 * @author oscardelatorre
 * @date Sep 15, 2017
 *
 */
public class JsonTrorOrderHeaderArchiveContainer {
	private String user = null;
	public void setUser(String value) {  this.user = value; }
	public String getUser() { return this.user;}
	
	
	private String errMsg = null;
	public void setErrMsg(String value) {  this.errMsg = value; }
	public String getErrMsg() { return this.errMsg;}
	
	private Collection<JsonTrorOrderHeaderArchiveRecord> archiveElements;
	public void setArchiveElements(Collection<JsonTrorOrderHeaderArchiveRecord> value){ this.archiveElements = value; }
	public Collection<JsonTrorOrderHeaderArchiveRecord> getArchiveElements(){ return archiveElements; }
	
	
}
