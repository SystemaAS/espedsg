/**
 * 
 */
package no.systema.overview.sendingerlevtid.model.jsonjackson.carrier;

/**
 * 
 * @author oscardelatorre
 * @date Oct 16, 2013
 * 
 */
public class JsonSendingerlevtidCarrierRecord {

	private String tranNr = null;
	public void setTranNr(String value) {  this.tranNr = value; }
	public String getTranNr() { return this.tranNr;}
	
	private String tnavn = null;
	public void setTnavn(String value) {  this.tnavn = value; }
	public String getTnavn() { return this.tnavn;}
	
	private String tranType = null;
	public void setTranType(String value) {  this.tranType = value; }
	public String getTranType() { return this.tranType;}
	
	private String kundNr = null;
	public void setKundNr(String value) {  this.kundNr = value; }
	public String getKundNr() { return this.kundNr;}
	
	private String leveNr = null;
	public void setLeveNr(String value) {  this.leveNr = value; }
	public String getLeveNr() { return this.leveNr;}
	
	
}
