/**
 * 
 */
package no.systema.overview.ufortolledeoppdrag.model.jsonjackson.archive;

import java.util.Collection;



/**
 * @author oscardelatorre
 * @date Aug 6, 2013
 *
 */
public class JsonTopicArchiveContainer {
	private String user = null;
	public void setUser(String value) {  this.user = value; }
	public String getUser() { return this.user;}
	
	
	private String errMsg = null;
	public void setErrMsg(String value) {  this.errMsg = value; }
	public String getErrMsg() { return this.errMsg;}
	
	private Collection<JsonTopicArchiveRecord> archiveElements;
	public void setArchiveElements(Collection<JsonTopicArchiveRecord> value){ this.archiveElements = value; }
	public Collection<JsonTopicArchiveRecord> getArchiveElements(){ return archiveElements; }
	
	
}
