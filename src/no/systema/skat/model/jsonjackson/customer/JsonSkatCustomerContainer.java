/**
 * 
 */
package no.systema.skat.model.jsonjackson.customer;

import java.util.Collection;
/**
 * @author oscardelatorre
 * @date Apr 18, 2014
 */
public class JsonSkatCustomerContainer {
	
	private String user = null;
	public void setUser(String value){ this.user = value;}
	public String getUser(){ return this.user; }
	
	private String sonavn = null;
	public void setSonavn(String value){ this.sonavn = value;}
	public String getSonavn(){ return this.sonavn; }
	
	private String knr = null;
	public void setKnr(String value){ this.knr = value;}
	public String getKnr(){ return this.knr; }
	
	private String errMsg = null;
	public void setErrMsg(String value){ this.errMsg = value;}
	public String getErrMsg(){ return this.errMsg; }
	
	private Collection<JsonSkatCustomerRecord> customerlist = null;
	public void setCustomerlist(Collection<JsonSkatCustomerRecord> value){ this.customerlist = value;}
	public Collection<JsonSkatCustomerRecord> getCustomerlist(){ return this.customerlist; }
	
}
