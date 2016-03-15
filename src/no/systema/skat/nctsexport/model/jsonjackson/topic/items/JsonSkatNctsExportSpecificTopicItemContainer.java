/**
 * 
 */
package no.systema.skat.nctsexport.model.jsonjackson.topic.items;

import java.util.Collection;

import no.systema.main.util.NumberFormatterLocaleAware;


/**
 * @author oscardelatorre
 * @date Apr 14, 2014
 *
 */
public class JsonSkatNctsExportSpecificTopicItemContainer {
	private String user = null;
	public void setUser(String value) {  this.user = value; }
	public String getUser() { return this.user;}
	
	private String tvavd = null;
	public void setTvavd(String value) {  this.tvavd = value; }
	public String getTvavd() { return this.tvavd;}
	
	private String tvtdn = null;
	public void setTvtdn(String value) {  this.tvtdn = value; }
	public String getTvtdn() { return this.tvtdn;}
	
	private String errMsg = null;
	public void setErrMsg(String value) {  this.errMsg = value; }
	public String getErrMsg() { return this.errMsg;}
		
	
	private String lastSelectedItemLineNumber = null;
	public void setLastSelectedItemLineNumber(String value) {  this.lastSelectedItemLineNumber = value; }
	public String getLastSelectedItemLineNumber() { return this.lastSelectedItemLineNumber;}
	
	
	private Collection<JsonSkatNctsExportSpecificTopicItemRecord> orderList;
	public void setOrderList(Collection<JsonSkatNctsExportSpecificTopicItemRecord> value){ this.orderList = value; }
	public Collection<JsonSkatNctsExportSpecificTopicItemRecord> getOrderList(){ return orderList; }
	
}
