/**
 * 
 */
package no.systema.tror.model.jsonjackson.budget;

import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

/**
 * @author oscardelatorre
 * @date Sep 18, 2017
 *
 */
public class JsonTrorOrderHeaderBudgetContainer {
	
	private String totalNumberOfItemLines = null;
	public void setTotalNumberOfItemLines(String value) {  this.totalNumberOfItemLines = value; }
	public String getTotalNumberOfItemLines() { return this.totalNumberOfItemLines;}
	
	
	private String user = null;
	public void setUser(String value) {  this.user = value; }
	public String getUser() { return this.user;}
	
	private String avd = null;
	public void setAvd(String value) {  this.avd = value; }
	public String getAvd() { return this.avd;}
	
	private String opd = null;
	public void setOpd(String value) {  this.opd = value; }
	public String getOpd() { return this.opd;}
	
	private String tur = null;
	public void setTur(String value) {  this.tur = value; }
	public String getTur() { return this.tur;}
	
	private String type = null;
	public void setType(String value) {  this.type = value; }
	public String getType() { return this.type;}
	
	private String errMsg = null;
	public void setErrMsg(String value) {  this.errMsg = value; }
	public String getErrMsg() { return this.errMsg;}
	
	
	private Collection<JsonTrorOrderHeaderBudgetRecord> budgetLines;
	public void setBudgetLines(Collection<JsonTrorOrderHeaderBudgetRecord> value){ this.budgetLines = value; }
	public Collection<JsonTrorOrderHeaderBudgetRecord> getBudgetLines(){ return budgetLines; }
	
	private Collection<JsonTrorOrderHeaderBudgetRecord> budgetLineUpdate;
	public void setBudgetLineUpdate(Collection<JsonTrorOrderHeaderBudgetRecord> value){ this.budgetLineUpdate = value; }
	public Collection<JsonTrorOrderHeaderBudgetRecord> getBudgetLineUpdate(){ return budgetLineUpdate; }
	
	
}
