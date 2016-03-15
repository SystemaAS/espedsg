/**
 * 
 */
package no.systema.skat.model.jsonjackson.avdsignature;

import java.util.Collection;

/**
 * @author oscardelatorre
 * @date Nov 6, 2013
 */
public class JsonSkatSignatureNameContainer {
	private String user = null;
	public void setUser(String value){ this.user = value;}
	public String getUser(){ return this.user; }
	
	private String sign = null;
	public void setSign(String value){ this.sign = value;}
	public String getSign(){ return this.sign; }
	
	private String errMsg = null;
	public void setErrMsg(String value){ this.errMsg = value;}
	public String getErrMsg(){ return this.errMsg; }
	
	private Collection<JsonSkatSignatureNameRecord> getsignname = null;
	public void setGetsignname(Collection<JsonSkatSignatureNameRecord> value){ this.getsignname = value;}
	public Collection<JsonSkatSignatureNameRecord> getGetsignname(){ return this.getsignname; }
}
