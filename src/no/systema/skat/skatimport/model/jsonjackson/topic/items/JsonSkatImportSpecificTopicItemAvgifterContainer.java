/**
 * 
 */
package no.systema.skat.skatimport.model.jsonjackson.topic.items;

import java.util.Collection;



/**
 * @author oscardelatorre
 * @date Feb 11, 2014
 * 
 */
public class JsonSkatImportSpecificTopicItemAvgifterContainer {
	private String user = null;
	public void setUser(String value) {  this.user = value; }
	public String getUser() { return this.user;}
	
	
	private String errMsg = null;
	public void setErrMsg(String value) {  this.errMsg = value; }
	public String getErrMsg() { return this.errMsg;}
	
	
	private Collection<JsonSkatImportSpecificTopicItemAvgifterRecord> statvaluecalc;
	public void setStatvaluecalc(Collection<JsonSkatImportSpecificTopicItemAvgifterRecord> value){ this.statvaluecalc = value; }
	public Collection<JsonSkatImportSpecificTopicItemAvgifterRecord> getStatvaluecalc(){ return statvaluecalc; }
	
	
	
}
