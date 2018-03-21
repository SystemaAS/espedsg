/**
 * 
 */
package no.systema.tror.model.jsonjackson.fellesutskrift;

import java.util.Collection;



/**
 * @author oscardelatorre
 * @date Mar 21, 2018
 *
 */
public class JsonTrorOrderFellesutskriftContainer {
	private String user = null;
	public void setUser(String value) {  this.user = value; }
	public String getUser() { return this.user;}
	
	
	private String errMsg = null;
	public void setErrMsg(String value) {  this.errMsg = value; }
	public String getErrMsg() { return this.errMsg;}
	
	private Collection<JsonTrorOrderFellesutskriftRecord> fellesutskrift;
	public void setFellesutskrift(Collection<JsonTrorOrderFellesutskriftRecord> value){ this.fellesutskrift = value; }
	public Collection<JsonTrorOrderFellesutskriftRecord> getFellesutskrift(){ return fellesutskrift; }
	
	
}
