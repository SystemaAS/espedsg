/**
 * 
 */
package no.systema.overview.sendingerlevtid.model.jsonjackson;

import java.util.Collection;
import no.systema.main.util.NumberFormatterLocaleAware;
/**
 * @author oscardelatorre
 * @date Oct 4, 2013
 * 
 */
public class JsonTopicContainer {

	private String user = null;
	public void setUser(String value) {  this.user = value; }
	public String getUser() { return this.user;}
	
	private Collection<JsonTopicRecord> kvalList = null;
	public void setKvalList(Collection<JsonTopicRecord> value) {  this.kvalList = value; }
	public Collection<JsonTopicRecord> getKvalList() { return this.kvalList;}
	
	private String antOk = null;
	public void setAntOk(String value) {  this.antOk = value; }
	public String getAntOk() { return this.antOk;}
	
	private String antDef = null;
	public void setAntDef(String value) {  this.antDef = value; }
	public String getAntDef() { return this.antDef;}
	
	private String leveringsKval = null;
	public void setLeveringsKval(String value) {  this.leveringsKval = value; }
	public String getLeveringsKval() { return this.leveringsKval;}
	
	//this field is needed when using values in the GUI view that requires google charts or other USA-based number convention
	private Double leveringsKvalDbl = 0.00D;
	public Double getLeveringsKvalDbl() { 
		NumberFormatterLocaleAware formatter = new NumberFormatterLocaleAware();
		this.leveringsKvalDbl = formatter.getDouble(leveringsKval);
		return this.leveringsKvalDbl;
	}
	
	private String leveringsKvalNotOk = null;
	public String getLeveringsKvalNotOk() {
		NumberFormatterLocaleAware formatter = new NumberFormatterLocaleAware();
		Double sourceNumber = 100- leveringsKvalDbl;
		this.leveringsKvalNotOk = formatter.getDoubleEuropeanFormat(sourceNumber, 2);
		return this.leveringsKvalNotOk;
	}
	
	private String antUDef = null;
	public void setAntUDef(String value) {  this.antUDef = value; }
	public String getAntUDef() { return this.antUDef;}
	
	private String antOkArchived = null;
	public void setAntOkArchived(String value) {  this.antOkArchived = value; }
	public String getAntOkArchived() { return this.antOkArchived;}
	
	private String antDefArchived = null;
	public void setAntDefArchived(String value) {  this.antDefArchived = value; }
	public String getAntDefArchived() { return this.antDefArchived;}
	
	private String archievedKval = null;
	public void setArchievedKval(String value) {  this.archievedKval = value; }
	public String getArchievedKval() { return this.archievedKval;}
	//this field is needed when using values in the GUI view that requires google charts or other USA-based number convention
	
	private Double archievedKvalDbl = 0.00D;
	public Double getArchievedKvalDbl(){
		NumberFormatterLocaleAware formatter = new NumberFormatterLocaleAware();
		this.archievedKvalDbl = formatter.getDouble(this.archievedKval);
		return archievedKvalDbl;
	}

	private String archievedKvalNotOk = null;
	public String getArchievedKvalNotOk() {
		NumberFormatterLocaleAware formatter = new NumberFormatterLocaleAware();
		Double sourceNumber = 100- this.archievedKvalDbl;
		this.archievedKvalNotOk = formatter.getDoubleEuropeanFormat(sourceNumber, 2);
		return this.archievedKvalNotOk;
	}
	
	private String antUDefArch = null;
	public void setAntUDefArch(String value) {  this.antUDefArch = value; }
	public String getAntUDefArch() { return this.antUDefArch;}
	
	private String antSndOK = null;
	public void setAntSndOK(String value) {  this.antSndOK = value; }
	public String getAntSndOK() { return this.antSndOK;}
	
	private String antRecLest = null;
	public void setAntRecLest(String value) {  this.antRecLest = value; }
	public String getAntRecLest() { return this.antRecLest;}
	
	
}
