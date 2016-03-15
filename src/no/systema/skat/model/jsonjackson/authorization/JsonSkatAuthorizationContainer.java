/**
 * 
 */
package no.systema.skat.model.jsonjackson.authorization;

import java.util.Collection;

/**
 * @author oscardelatorre
 * @date Mar 4, 2014
 */
public class JsonSkatAuthorizationContainer {
	private String user = null;
	public void setUser(String value){ this.user = value;}
	public String getUser(){ return this.user; }
	
	private String usrAS400 = null;
	public void setUsrAS400(String value){ this.usrAS400 = value;}
	public String getUsrAS400(){ return this.usrAS400; }
	
	private String errMsg = null;
	public void setErrMsg(String value){ this.errMsg = value;}
	public String getErrMsg(){ return this.errMsg; }
	
	private Collection<JsonSkatAuthorizationRecord> skatBrugertilladelse = null;
	public void setSkatBrugertilladelse(Collection<JsonSkatAuthorizationRecord> value){ this.skatBrugertilladelse = value;}
	public Collection<JsonSkatAuthorizationRecord> getSkatBrugertilladelse(){ return this.skatBrugertilladelse; }
	
}
