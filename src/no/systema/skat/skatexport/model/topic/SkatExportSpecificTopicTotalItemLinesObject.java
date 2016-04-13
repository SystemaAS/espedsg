/**
 * 
 */
package no.systema.skat.skatexport.model.topic;

/**
 * @author oscardelatorre
 * @date Oct 27, 2014
 * 
 * This object is used as a place holder instead of a Map.
 * Mainly because there are different return types. The object is only used within a controller domain.
 * 
 */
public class SkatExportSpecificTopicTotalItemLinesObject {
	
	private Integer sumOfAntalKolliInItemLines = 0;
	public void setSumOfAntalKolliInItemLines(Integer value) {  this.sumOfAntalKolliInItemLines = value; }
	public Integer getSumOfAntalKolliInItemLines() {return this.sumOfAntalKolliInItemLines;}
	
	
	private Integer sumOfAntalItemLines = 0;
	public void setSumOfAntalItemLines(Integer value) {  this.sumOfAntalItemLines = value; }
	public Integer getSumOfAntalItemLines() {return this.sumOfAntalItemLines;}
	
	
	private double sumTotalAmountItemLines = 0.00D;
	public void setSumTotalAmountItemLines(Double value) {  this.sumTotalAmountItemLines = value; }
	public Double getSumTotalAmountItemLines() {return this.sumTotalAmountItemLines;}
	
	
	//Used when different currencies exist. The main currency must be = DKK
	private String fakturaListTotValidCurrency = null;
	public void setFakturaListTotValidCurrency(String value) {  this.fakturaListTotValidCurrency = value; }
	public String getFakturaListTotValidCurrency() { return this.fakturaListTotValidCurrency; }
	
	private String fakturaListTotSum = null;
	public void setFakturaListTotSum(String value) {  this.fakturaListTotSum = value; }
	public String getFakturaListTotSum() { return this.fakturaListTotSum; }
	
	private String fakturaListTotKurs = null;
	public void setFakturaListTotKurs(String value) {  this.fakturaListTotKurs = value; }
	public String getFakturaListTotKurs() { return this.fakturaListTotKurs; }
	
	
}
