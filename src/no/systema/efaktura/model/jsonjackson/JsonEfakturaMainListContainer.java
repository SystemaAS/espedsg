/**
 * 
 */
package no.systema.efaktura.model.jsonjackson;

import java.util.Collection;

/**
 * @author oscardelatorre
 * @date Jun 22, 2015
 *
 */
public class JsonEfakturaMainListContainer {
	
	private String user = null;
	public void setUser(String value) {  this.user = value; }
	public String getUser() { return this.user;}
	
	private String avd = null;
	public void setAvd(String value) {  this.avd = value; }
	public String getAvd() { return this.avd;}
	
	private String dtf = null;
	public void setDtf(String value) {  this.dtf = value; }
	public String getDtf() { return this.dtf;}
	
	private String dtt = null;
	public void setDtt(String value) {  this.dtt = value; }
	public String getDtt() { return this.dtt;}
	
	private String errMsg = null;
	public void setErrMsg(String value) {  this.errMsg = value; }
	public String getErrMsg() { return this.errMsg;}

	private Collection<JsonEfakturaMainListRecord> orderList;
	public void setOrderList(Collection<JsonEfakturaMainListRecord> value){ this.orderList = value; }
	public Collection<JsonEfakturaMainListRecord> getOrderList(){ return orderList; }
	
	
}
