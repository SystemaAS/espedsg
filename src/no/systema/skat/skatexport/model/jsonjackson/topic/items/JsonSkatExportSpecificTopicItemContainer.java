/**
 * 
 */
package no.systema.skat.skatexport.model.jsonjackson.topic.items;

import java.util.Collection;
import java.util.Locale;
import java.text.NumberFormat;
import no.systema.main.util.NumberFormatterLocaleAware;

/**
 * @author oscardelatorre
 * @date Mar 10, 2014
 * 
 */
public class JsonSkatExportSpecificTopicItemContainer {
	private String user = null;
	public void setUser(String value) {  this.user = value; }
	public String getUser() { return this.user;}
	
	private String dkev_syav = null;
	public void setDkev_syav(String value) {  this.dkev_syav = value; }
	public String getDkev_syav() { return this.dkev_syav;}
	
	private String dkev_syop = null;
	public void setDkev_syop(String value) {  this.dkev_syop = value; }
	public String getDkev_syop() { return this.dkev_syop;}
	
	private String errMsg = null;
	public void setErrMsg(String value) {  this.errMsg = value; }
	public String getErrMsg() { return this.errMsg;}
	
	private Double calculatedItemLinesTotalAmount = 0.000D;
	public void setCalculatedItemLinesTotalAmount(Double value) {  this.calculatedItemLinesTotalAmount = value; }
	public String getCalculatedItemLinesTotalAmount() {
		NumberFormatterLocaleAware formatter = new NumberFormatterLocaleAware();
		return formatter.getDoubleEuropeanFormat(this.calculatedItemLinesTotalAmount, 3);
	}

	private Double diffItemLinesTotalAmountWithInvoiceTotalAmount = 0.000D;
	public void setDiffItemLinesTotalAmountWithInvoiceTotalAmount(Double value) {  this.diffItemLinesTotalAmountWithInvoiceTotalAmount = value; }
	public String getDiffItemLinesTotalAmountWithInvoiceTotalAmount() {
		NumberFormatterLocaleAware formatter = new NumberFormatterLocaleAware();
		return formatter.getDoubleEuropeanFormat(this.diffItemLinesTotalAmountWithInvoiceTotalAmount, 3);
	}
	
	private String lastSelectedItemLineNumber = null;
	public void setLastSelectedItemLineNumber(String value) {  this.lastSelectedItemLineNumber = value; }
	public String getLastSelectedItemLineNumber() { return this.lastSelectedItemLineNumber;}
	
	
	private Collection<JsonSkatExportSpecificTopicItemRecord> orderList;
	public void setOrderList(Collection<JsonSkatExportSpecificTopicItemRecord> value){ this.orderList = value; }
	public Collection<JsonSkatExportSpecificTopicItemRecord> getOrderList(){ return orderList; }
	
	
	
}
