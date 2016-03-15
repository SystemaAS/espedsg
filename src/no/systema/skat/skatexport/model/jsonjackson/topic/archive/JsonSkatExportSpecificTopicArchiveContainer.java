/**
 * 
 */
package no.systema.skat.skatexport.model.jsonjackson.topic.archive;

import java.util.Collection;



/**
 * @author oscardelatorre
 * @date Mar 11, 2014
 *
 */
public class JsonSkatExportSpecificTopicArchiveContainer {
	private String user = null;
	public void setUser(String value) {  this.user = value; }
	public String getUser() { return this.user;}
	
	
	private String errMsg = null;
	public void setErrMsg(String value) {  this.errMsg = value; }
	public String getErrMsg() { return this.errMsg;}
	
	private Collection<JsonSkatExportSpecificTopicArchiveRecord> archiveElements;
	public void setArchiveElements(Collection<JsonSkatExportSpecificTopicArchiveRecord> value){ this.archiveElements = value; }
	public Collection<JsonSkatExportSpecificTopicArchiveRecord> getArchiveElements(){ return archiveElements; }
	
	
}
