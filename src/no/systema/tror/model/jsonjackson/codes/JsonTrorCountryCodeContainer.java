/**
 * 
 */
package no.systema.tror.model.jsonjackson.codes;

import java.util.Collection;

import no.systema.tds.model.jsonjackson.codes.JsonTdsCodeRecord;

/**
 * General Code Container for Tror general codes
 * 
 * 
 *
 * @author oscardelatorre
 * @date Aug 14, 2017
 *
 */
public class JsonTrorCountryCodeContainer {
	private String user = null;
	public void setUser(String value){ this.user = value;}
	public String getUser(){ return this.user; }
	
	private String errMsg = null;
	public void setErrMsg(String value){ this.errMsg = value;}
	public String getErrMsg(){ return this.errMsg; }
	
	private Collection<JsonTrorCountryCodeRecord> dtoList = null;
	public void setDtoList(Collection<JsonTrorCountryCodeRecord> value){ this.dtoList = value;}
	public Collection<JsonTrorCountryCodeRecord> getDtoList(){ return this.dtoList; }
	
}
