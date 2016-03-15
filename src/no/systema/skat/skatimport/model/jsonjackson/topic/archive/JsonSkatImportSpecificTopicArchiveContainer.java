/**
 * 
 */
package no.systema.skat.skatimport.model.jsonjackson.topic.archive;

import java.util.Collection;



/**
 * @author oscardelatorre
 * @date Mar 4, 2014
 *
 */
public class JsonSkatImportSpecificTopicArchiveContainer {
	private String user = null;
	public void setUser(String value) {  this.user = value; }
	public String getUser() { return this.user;}
	
	
	private String errMsg = null;
	public void setErrMsg(String value) {  this.errMsg = value; }
	public String getErrMsg() { return this.errMsg;}
	
	private Collection<JsonSkatImportSpecificTopicArchiveRecord> archiveElements;
	public void setArchiveElements(Collection<JsonSkatImportSpecificTopicArchiveRecord> value){ this.archiveElements = value; }
	public Collection<JsonSkatImportSpecificTopicArchiveRecord> getArchiveElements(){ return archiveElements; }
	
	
}
