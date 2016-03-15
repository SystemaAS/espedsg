/**
 * 
 */
package no.systema.skat.nctsimport.model.jsonjackson.topic.items;

import java.util.Collection;


/**
 * 
 * @author oscardelatorre
 * @date Apr 24, 2014
 * 
 *
 */
public class JsonSkatNctsImportSpecificTopicItemContainer {
	private String user = null;
	public void setUser(String value) {  this.user = value; }
	public String getUser() { return this.user;}
	
	private String tiavd = null;
	public void setTiavd(String value) {  this.tiavd = value; }
	public String getTiavd() { return this.tiavd;}
	
	private String titdn = null;
	public void setTitdn(String value) {  this.titdn = value; }
	public String getTitdn() { return this.titdn;}
	
	private String errMsg = null;
	public void setErrMsg(String value) {  this.errMsg = value; }
	public String getErrMsg() { return this.errMsg;}
		
	private Collection<JsonSkatNctsImportSpecificTopicItemRecord> orderList;
	public void setOrderList(Collection<JsonSkatNctsImportSpecificTopicItemRecord> value){ this.orderList = value; }
	public Collection<JsonSkatNctsImportSpecificTopicItemRecord> getOrderList(){ return orderList; }
	
}
