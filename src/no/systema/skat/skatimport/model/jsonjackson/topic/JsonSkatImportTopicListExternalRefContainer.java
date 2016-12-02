/**
 * 
 */
package no.systema.skat.skatimport.model.jsonjackson.topic;

import java.util.Collection;



/**
 * @author oscardelatorre
 * @date Dec 02, 2016
 *
 */
public class JsonSkatImportTopicListExternalRefContainer {
	private String user = null;
	public void setUser(String value) {  this.user = value; }
	public String getUser() { return this.user;}
	
	private String errMsg = null;
	public void setErrMsg(String value) {  this.errMsg = value; }
	public String getErrMsg() { return this.errMsg;}
	
	
	private Collection<JsonSkatImportTopicListExternalRefRecord> extList;
	public void setExtList(Collection<JsonSkatImportTopicListExternalRefRecord> value){ this.extList = value; }
	public Collection<JsonSkatImportTopicListExternalRefRecord> getExtList(){ return extList; }
	
}
