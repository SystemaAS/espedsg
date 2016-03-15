/**
 * 
 */
package no.systema.skat.nctsimport.model.jsonjackson.topic.archive;

import java.util.Collection;



/**
 * @author oscardelatorre
 * @date Apr 24, 2014
 *
 */
public class JsonSkatNctsImportSpecificTopicArchiveContainer {
	private String user = null;
	public void setUser(String value) {  this.user = value; }
	public String getUser() { return this.user;}
	
	
	private String errMsg = null;
	public void setErrMsg(String value) {  this.errMsg = value; }
	public String getErrMsg() { return this.errMsg;}
	
	private Collection<JsonSkatNctsImportSpecificTopicArchiveRecord> archiveElements;
	public void setArchiveElements(Collection<JsonSkatNctsImportSpecificTopicArchiveRecord> value){ this.archiveElements = value; }
	public Collection<JsonSkatNctsImportSpecificTopicArchiveRecord> getArchiveElements(){ return archiveElements; }
	
	
}
