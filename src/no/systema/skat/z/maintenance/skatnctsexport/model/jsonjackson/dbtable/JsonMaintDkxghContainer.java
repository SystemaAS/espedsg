/**
 * 
 */
package no.systema.skat.z.maintenance.skatnctsexport.model.jsonjackson.dbtable;

import java.util.Collection;

/**
 * @author oscardelatorre
 * @date Sep 2, 2016
 *
 */
public class JsonMaintDkxghContainer {
	private String user = null;
	public void setUser(String value) {  this.user = value; }
	public String getUser() { return this.user;}
	
	private String errMsg = null;
	public void setErrMsg(String value) {  this.errMsg = value; }
	public String getErrMsg() { return this.errMsg;}
	
	
	private Collection<JsonMaintDkxghRecord> list;
	public void setList(Collection<JsonMaintDkxghRecord> value){ this.list = value; }
	public Collection<JsonMaintDkxghRecord> getList(){ return list; }
	
}
