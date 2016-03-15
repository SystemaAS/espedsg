/**
 * 
 */
package no.systema.skat.model.jsonjackson.avdsignature;

import java.util.Collection;

/**
 * @author oscardelatorre
 * @Mar 9, 2013
 */
public class JsonSkatSignatureContainer {
	private String user = null;
	public void setUser(String value){ this.user = value;}
	public String getUser(){ return this.user; }
	
	private String ie = null;
	public void setIe(String value){ this.ie = value;}
	public String getIe(){ return this.ie; }
	
	private String errMsg = null;
	public void setErrMsg(String value){ this.errMsg = value;}
	public String getErrMsg(){ return this.errMsg; }
	
	private Collection<JsonSkatSignatureRecord> signaturer = null;
	public void setSignaturer(Collection<JsonSkatSignatureRecord> value){ this.signaturer = value;}
	public Collection<JsonSkatSignatureRecord> getSignaturer(){ return this.signaturer; }
}
