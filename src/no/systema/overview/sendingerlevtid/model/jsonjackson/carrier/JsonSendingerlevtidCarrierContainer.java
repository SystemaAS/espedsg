/**
 * 
 */
package no.systema.overview.sendingerlevtid.model.jsonjackson.carrier;

import java.util.Collection;

/**
 * 
 * @author oscardelatorre
 * @date Oct 16, 2013
 * 
 */
public class JsonSendingerlevtidCarrierContainer {
	
	private String user = null;
	public void setUser(String value) {  this.user = value; }
	public String getUser() { return this.user;}
	
	private String sonavn = null;
	public void setSonavn(String value) {  this.sonavn = value; }
	public String getSonavn() { return this.sonavn;}
	
	private String tnrL = null;
	public void setTnrL(String value) {  this.tnrL = value; }
	public String getTnrL() { return this.tnrL;}
	
	private String errMsg = null;
	public void setErrMsg(String value) {  this.errMsg = value; }
	public String getErrMsg() { return this.errMsg;}
	
	private Collection<JsonSendingerlevtidCarrierRecord> transporterlist = null;
	public void setTransporterlist(Collection<JsonSendingerlevtidCarrierRecord> value) { this.transporterlist = value; }
	public Collection<JsonSendingerlevtidCarrierRecord> getTransporterlist(){return this.transporterlist;}

	
}
