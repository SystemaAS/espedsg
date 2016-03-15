/**
 * 
 */
package no.systema.skat.skatexport.model.jsonjackson.topic.items;

import java.util.Collection;



/**
 * @author oscardelatorre
 * @date Mar 10, 2014
 * 
 */
public class JsonSkatExportSpecificTopicItemAvgifterContainer {
	private String user = null;
	public void setUser(String value) {  this.user = value; }
	public String getUser() { return this.user;}
	
	
	private String errMsg = null;
	public void setErrMsg(String value) {  this.errMsg = value; }
	public String getErrMsg() { return this.errMsg;}
	
	
	private Collection<JsonSkatExportSpecificTopicItemAvgifterRecord> statvaluecalc;
	public void setStatvaluecalc(Collection<JsonSkatExportSpecificTopicItemAvgifterRecord> value){ this.statvaluecalc = value; }
	public Collection<JsonSkatExportSpecificTopicItemAvgifterRecord> getStatvaluecalc(){ return statvaluecalc; }
	
	
	
}
