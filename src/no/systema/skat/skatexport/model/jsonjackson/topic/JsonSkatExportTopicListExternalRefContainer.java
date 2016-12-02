/**
 * 
 */
package no.systema.skat.skatexport.model.jsonjackson.topic;

import java.util.Collection;



/**
 * @author oscardelatorre
 * @date Dec 02, 2016
 *
 */
public class JsonSkatExportTopicListExternalRefContainer {
	private String user = null;
	public void setUser(String value) {  this.user = value; }
	public String getUser() { return this.user;}
	
	private String errMsg = null;
	public void setErrMsg(String value) {  this.errMsg = value; }
	public String getErrMsg() { return this.errMsg;}
	
	
	private Collection<JsonSkatExportTopicListExternalRefRecord> extList;
	public void setExtList(Collection<JsonSkatExportTopicListExternalRefRecord> value){ this.extList = value; }
	public Collection<JsonSkatExportTopicListExternalRefRecord> getExtList(){ return extList; }
	
}
