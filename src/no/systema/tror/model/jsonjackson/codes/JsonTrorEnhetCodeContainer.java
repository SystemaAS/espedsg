/**
 * 
 */
package no.systema.tror.model.jsonjackson.codes;

import java.util.Collection;


/**
 * General Code Container for Tror general codes
 * 
 * 
 *
 * @author oscardelatorre
 * @date Aug 15, 2017
 *
 */
public class JsonTrorEnhetCodeContainer {
	private String user = null;
	public void setUser(String value){ this.user = value;}
	public String getUser(){ return this.user; }
	
	private String errMsg = null;
	public void setErrMsg(String value){ this.errMsg = value;}
	public String getErrMsg(){ return this.errMsg; }
	
	private Collection<JsonTrorEnhetCodeRecord> list = null;
	public void setList(Collection<JsonTrorEnhetCodeRecord> value){ this.list = value;}
	public Collection<JsonTrorEnhetCodeRecord> getList(){ return this.list; }
	
}
