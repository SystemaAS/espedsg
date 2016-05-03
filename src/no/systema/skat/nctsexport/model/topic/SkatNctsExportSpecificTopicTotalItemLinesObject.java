/**
 * 
 */
package no.systema.skat.nctsexport.model.topic;

/**
 * @author oscardelatorre
 * @date Nov 21, 2014
 * 
 * This object is used as a place holder instead of a Map.
 * Mainly because there are different return types. The object is only used within a controller domain.
 * 
 */
public class SkatNctsExportSpecificTopicTotalItemLinesObject {
	
	private Integer sumOfAntalKolliInItemLines = 0;
	public void setSumOfAntalKolliInItemLines(Integer value) {  this.sumOfAntalKolliInItemLines = value; }
	public Integer getSumOfAntalKolliInItemLines() {return this.sumOfAntalKolliInItemLines;}
	
	
	private Integer sumOfAntalItemLines = 0;
	public void setSumOfAntalItemLines(Integer value) {  this.sumOfAntalItemLines = value; }
	public Integer getSumOfAntalItemLines() {return this.sumOfAntalItemLines;}
	
	
	
	/* N/A ?
	private double sumTotalAmountItemLines = 0.00D;
	public void setSumTotalAmountItemLines(Double value) {  this.sumTotalAmountItemLines = value; }
	public Double getSumTotalAmountItemLines() {return this.sumTotalAmountItemLines;}
	*/
	
	
}
