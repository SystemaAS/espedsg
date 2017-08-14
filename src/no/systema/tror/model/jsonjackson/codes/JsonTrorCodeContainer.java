/**
 * 
 */
package no.systema.tror.model.jsonjackson.codes;

import java.util.Collection;

import no.systema.tds.model.jsonjackson.codes.JsonTdsCodeRecord;

/**
 * General Code Container for Ebooking general codes
 * 
 * 
 *
 * @author oscardelatorre
 * @date Jan 07, 2017
 *
 */
public class JsonTrorCodeContainer {
	private String user = null;
	public void setUser(String value){ this.user = value;}
	public String getUser(){ return this.user; }
	
	private String errMsg = null;
	public void setErrMsg(String value){ this.errMsg = value;}
	public String getErrMsg(){ return this.errMsg; }
	
	private Collection<JsonTrorCodeRecord> list = null;
	public void setList(Collection<JsonTrorCodeRecord> value){ this.list = value;}
	public Collection<JsonTrorCodeRecord> getList(){ return this.list; }
	
}
